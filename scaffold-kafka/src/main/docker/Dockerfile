# 基础镜像
FROM java:openjdk-8-jdk-alpine

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器，重命名为app.jar
ADD scaffold-kafka.jar app.jar
RUN sh -c 'touch /app.jar'

#修改默认时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 配置容器启动后执行的命令
ENV JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms512m -Xmx1g -XX:NewSize=512m -XX:MaxNewSize=512m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:+DisableExplicitGC"
ENTRYPOINT ["sh", "-c" , "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]