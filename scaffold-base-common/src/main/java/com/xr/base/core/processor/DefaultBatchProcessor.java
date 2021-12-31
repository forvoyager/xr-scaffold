package com.xr.base.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.Closeable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-12-21 11:13:00
 * <b>@description</b>: 批量数据处理器，封装默认处理逻辑。
 * 用法：
 * 1、继承此抽象类。
 * 2、实现doProcess(List<E> data)，批处理逻辑
 * 3、调用add(E data)添加数据，然后批处理器就自动处理
 * 4、根据需要重写getDataBuffer/getThreadPoolExecutor/getBatchSize等方法调整批处理器的细节。
 */
public abstract class DefaultBatchProcessor<E> implements Closeable {

  /*
  线程控制信号量（只有1个permit）。
  异步线程发现没有数据需要处理时会阻塞，等待数据。
  processor收到数据时会释放1个permit，异步线程继续处理数据。
   */
  private Semaphore semaphore = new Semaphore(1);
  protected Logger logger = LoggerFactory.getLogger(getClass());
  protected ZoneId defaultZone = ZoneId.of("Asia/Shanghai");
  protected DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  protected AtomicBoolean isShutdown = new AtomicBoolean();

  /**
   * 数据缓冲区（每个处理器独立缓冲区，防止互相干扰）
   */
  private ArrayBlockingQueue<E> dataBuffer;

  /**
   * 线程池（每个处理器独立线程池，防止互相干扰）
   */
  private ExecutorService threadPoolExecutor;

  /**
   * 分批处理数据的批次大小，默认20
   */
  private int batchSize = 20;

  public DefaultBatchProcessor(){
    try {
      this.start();
    }catch (Exception e){
      throw new IllegalStateException(e);
    }
  }

  protected ArrayBlockingQueue<E> getDataBuffer() {
    // 默认缓冲区
    return new ArrayBlockingQueue<E>(10000);
  }

  protected ExecutorService getThreadPoolExecutor() {
    // 默认线程池
    int core = Runtime.getRuntime().availableProcessors() + 1;
    return new ThreadPoolExecutor(
            1, core, 15, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>(5000),
            new CustomThreadFactory(getClass().getSimpleName())
    );
  }

  protected int getBatchSize() {
    return 20;
  }

  /**
   * 添加数据
   * @param data
   * @throws Exception
   */
  public void add(E data) throws Exception{
    // 添加成功返回true 否则返回false
    if(this.dataBuffer.offer(data)){
      if(semaphore.availablePermits() == 0){
        // 有数据了，唤醒线程处理
        semaphore.release();
      }
    }
  }

  /**
   * 数据处理实现
   * @param data 需要处理的数据
   */
  protected abstract void doProcess(List<E> data) throws Exception;

  private void start() throws Exception{

    this.dataBuffer = this.getDataBuffer();
    this.threadPoolExecutor = this.getThreadPoolExecutor();
    this.batchSize = this.getBatchSize();

    // 参数校验
    if(
            this.dataBuffer == null ||
            this.threadPoolExecutor==null ||
            this.batchSize < 0
    ){
      throw new Exception("处理器配置不正确，无法启动。");
    }

    // 先拿掉1个permit，异步线程因无数据进入阻塞状态
    semaphore.acquire();

    logger.info("batch processor {} started.", this.getClass().getSimpleName());

    // 启动线程处理数据
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (true){
          try {
            if(Thread.currentThread().isInterrupted()){
              logger.error("thread interrupt，请检查原因。");
              break;
            }

            List<E> dataList = pollData(dataBuffer, batchSize);
            if(dataList.size() == 0){
              logger.info("no data, waiting...");
              semaphore.acquire();
            }
            if(dataList.size() > 0){
              submitTask(() -> {
                while (true){
                  try {
                    doProcess(dataList);
                    break;
                  }catch (Exception e){
                    logger.error("batch process failed, retry again, reason:", e);
                  }
                }
              });
            }
          }catch (Exception e){
            logger.error("拉取消息失败，稍后重试：", e);
          }
        }
      }
    }).start();

  }

  /**
   * 拉取需要处理的数据
   * @param buffer
   * @param batchSize
   * @param <T>
   * @return
   * @throws Exception
   */
  private <T> List<T> pollData(ArrayBlockingQueue<T> buffer, int batchSize) throws Exception{
    T data = null;
    List<T> dataList = new ArrayList<>();

    for(int i = 0; i<batchSize; i++){
      // 10秒仍然没有消息就先处理当前的消息
      data = buffer.poll(1, TimeUnit.SECONDS);
      if(data == null){
        // poll取不到数据，队列空了，跳出，先将
        break;
      }
      dataList.add(data);
    }

    logger.info("poll data，size:{}", dataList.size());
    return dataList;
  }

  /**
   * 添加任务
   * @param task
   */
  private void submitTask(Runnable task){
    if(task == null){
      throw new IllegalArgumentException("任务为空，无法执行。");
    }

    while (true) {
      try {
        this.threadPoolExecutor.submit(task);
        break;
      }catch (RejectedExecutionException ree){
        logger.warn("线程池已满，稍后重试添加。");

        // 线程池任务队列满了，稍等一下继续提交任务
        try {
          Thread.sleep(1000);
        }catch (Exception e){}

      }
    }

  }

  /**
   * 返回线性增长的间隔时间
   * [0, 5]，返回1000ms
   * [6, 10]，返回2000ms
   * [11, ...]，返回failedCount*500ms
   * @param failedCount
   * @return
   */
  protected long getSleepMillis(int failedCount) {
    if(failedCount<5){
      return 1000;
    } else if(failedCount<10){
      return 2000;
    }

    // failedCount*0.5*1000
    return failedCount*500;
  }

  /**
   * The default thread factory
   */
  protected static class CustomThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    CustomThreadFactory(String threadPoolNamePrefix) {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() :
              Thread.currentThread().getThreadGroup();
      namePrefix = threadPoolNamePrefix + "-";
    }

    public Thread newThread(Runnable r) {
      Thread t = new Thread(group, r,
              namePrefix + threadNumber.getAndIncrement(),
              0);
      if (t.isDaemon())
        t.setDaemon(false);
      if (t.getPriority() != Thread.NORM_PRIORITY)
        t.setPriority(Thread.NORM_PRIORITY);
      return t;
    }
  }

  public void close() {
    if (!this.isShutdown.getAndSet(true)) {
      if (     this.threadPoolExecutor != null &&
              !this.threadPoolExecutor.isTerminated() &&
              !this.threadPoolExecutor.isShutdown()) {
        try {
          logger.info("thread pool shutdown initiated...");
          this.threadPoolExecutor.shutdown();
          logger.info("thread pool shutdown completed.");
        } catch (Exception e) {
          logger.warn("thread pool interrupted during closing, case:{}", e);
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
