package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import com.xr.recommend.common.model.ActionModel;
import com.xr.recommend.common.service.IActionService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-08-10 14:31:18 <br>
* <b>description</b>: 行为数据 HTTP服务 <br>
*/
@Api(tags = "行为数据相关操作")
@RestController
@RequestMapping("/action")
public class ActionController {

  @DubboReference
  private IActionService actionService;

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return ActionModel 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<ActionModel> upsert(@RequestBody ActionModel entity) throws Exception {
    actionService.upsert(entity);
    return ResultDto.success();
  }

  /**
   * <p>
   * 插入（批量）
   * </p>
   *
   * @param entityList 对象列表
   * @return 插入成功的记录数
   */
  @PostMapping("/insert/batch")
  public ResultDto<Integer> insertBatch(@RequestBody List<ActionModel> entityList) throws Exception {
    actionService.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 根据 主键ID 删除
   * </p>
   *
   * @param action_id 主键ID
   * @return 删除的行数
   */
  @DeleteMapping("/delete/{action_id}")
  public ResultDto<Integer> deleteById(@PathVariable("action_id") long action_id) throws Exception {
    return ResultDto.successData(actionService.deleteById(action_id));
  }

  /**
   * <p>
   * 根据 condition 条件，删除记录
   * </p>
   *
   * @param condition 删除条件
   * @return Integer 删除的行数
   */
  @DeleteMapping("/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(actionService.delete(condition));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param action_id 主键ID
   * @param cluster 主节点 or 从节点
   * @return ActionModel
   */
  @GetMapping("/select/{cluster}/{action_id}")
  public ResultDto<ActionModel> selectById(@PathVariable("action_id") long action_id, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectById(action_id, cluster));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<ActionModel> 列表
   */
  @GetMapping("/select/{cluster}/batch")
  public ResultDto<List<ActionModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectByIds(idList, cluster));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<ActionModel> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<ActionModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectList(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return ActionModel
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<ActionModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectOne(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询总记录数
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return long 记录数
   */
  @GetMapping("/select/{cluster}/count")
  public ResultDto<Long> selectCount(@RequestBody(required = false) Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectCount(condition, cluster));
  }

  /**
   * <p>
   * 翻页查询
   * </p>
   *
   * @param page 第几页
   * @param size 每页记录数
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return
   */
  @GetMapping("/select/{cluster}/page/{page}/{size}")
  public ResultDto<PageData<ActionModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.selectPage(page, size, condition, cluster));
  }

}
