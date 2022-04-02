package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.service.IActionService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-12-09 17:09:28 <br>
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
   * @return ActionEntity 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<ActionEntity> upsert(@RequestBody ActionEntity entity) throws Exception {
    actionService.saveOrUpdate(entity);
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
  public ResultDto<Integer> insertBatch(@RequestBody List<ActionEntity> entityList) throws Exception {
    actionService.saveBatch(entityList);
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
    return ResultDto.successData(actionService.removeById(action_id));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param action_id 主键ID
   * @param cluster 主节点 or 从节点
   * @return ActionEntity
   */
  @GetMapping("/select/{cluster}/{action_id}")
  public ResultDto<ActionEntity> selectById(@PathVariable("action_id") long action_id, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.getById(action_id));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<ActionEntity> 列表
   */
  @GetMapping("/select/{cluster}/ids")
  public ResultDto<List<ActionEntity>> selectByIds(@RequestParam Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(actionService.listByIds(idList));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<ActionEntity> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<ActionEntity>> selectList(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return ActionEntity
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<ActionEntity> selectOne(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
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
  public ResultDto<Long> selectCount(@RequestParam(required = false) Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
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
  public ResultDto<PageData<ActionEntity>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestParam(required = false) Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
  }

}
