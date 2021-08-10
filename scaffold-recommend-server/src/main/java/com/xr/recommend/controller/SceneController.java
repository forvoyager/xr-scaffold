package com.xr.recommend.controller;

import com.xr.recommend.common.model.SceneModel;
import com.xr.recommend.common.service.ISceneService;
import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-08-10 13:19:29 <br>
* <b>description</b>: 推荐场景 HTTP服务 <br>
*/
@RestController
public class SceneController {

  @Resource
  private ISceneService sceneService;

  /**
   * <p>
   * 插入一条记录
   * </p>
   *
   * @param entity 实体对象
   * @return
   */
  @RequestMapping("/scene/insert")
  public ResultDto insert(@RequestBody SceneModel entity) throws Exception {
    sceneService.insert(entity);
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
  @RequestMapping("/scene/insert/batch")
  public ResultDto<Integer> insertBatch(@RequestBody List<SceneModel> entityList) throws Exception {
    sceneService.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return SceneModel 插入/更新成功的对象
   */
  @RequestMapping("/scene/upsert")
  public ResultDto<SceneModel> upsert(@RequestBody SceneModel entity) throws Exception {
    sceneService.upsert(entity);
    return ResultDto.success();
  }

  /**
   * <p>
   * 根据 主键ID 删除
   * </p>
   *
   * @param scene_id 主键ID
   * @return 删除的行数
   */
  @RequestMapping("/scene/delete/{scene_id}")
  public ResultDto<Integer> deleteById(@PathVariable("scene_id") long scene_id) throws Exception {
    return ResultDto.successData(sceneService.deleteById(scene_id));
  }

  /**
   * <p>
   * 根据 condition 条件，删除记录
   * </p>
   *
   * @param condition 删除条件
   * @return Integer 删除的行数
   */
  @RequestMapping("/scene/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(sceneService.delete(condition));
  }

  /**
   * <p>
   * 根据 model 修改数据
   * </p>
   *
   * @param entity 实体对象
   * @return 更新的行数
   */
  @RequestMapping("/scene/update/model")
  public ResultDto<Long> update(@RequestBody SceneModel entity) throws Exception {
    return ResultDto.successData(sceneService.update(entity));
  }

  /**
   * <p>
   * 根据map条件 修改
   * </p>
   *
   * @param columnMap 更新数据/更新条件
   * @return 更新的行数
   */
  @RequestMapping("/scene/update/map")
  public ResultDto<Long> updateByMap(@RequestBody Map<String, Object> columnMap) throws Exception {
    return ResultDto.successData(sceneService.update(columnMap));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param scene_id 主键ID
   * @param master 主节点 or 从节点
   * @return SceneModel
   */
  @RequestMapping("/scene/select/{master}/{scene_id}")
  public ResultDto<SceneModel> selectById(@PathVariable("scene_id") long scene_id, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectById(scene_id, master));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param master 主节点 or 从节点
   * @return List<SceneModel> 列表
   */
  @RequestMapping("/scene/select/{master}/batch")
  public ResultDto<List<SceneModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectByIds(idList, master));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return List<SceneModel> 列表
   */
  @RequestMapping("/scene/select/{master}/list")
  public ResultDto<List<SceneModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectList(condition, master));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return SceneModel
   */
  @RequestMapping("/scene/select/{master}/one")
  public ResultDto<SceneModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectOne(condition, master));
  }

  /**
   * <p>
   * 根据 condition 条件，查询总记录数
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return long 记录数
   */
  @RequestMapping("/scene/select/{master}/count")
  public ResultDto<Long> selectCount(@RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectCount(condition, master));
  }

  /**
   * <p>
   * 翻页查询
   * </p>
   *
   * @param page 第几页
   * @param size 每页记录数
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return
   */
  @RequestMapping("/scene/select/{master}/page/{page}/{size}")
  public ResultDto<PageData<SceneModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(sceneService.selectPage(page, size, condition, master));
  }

}
