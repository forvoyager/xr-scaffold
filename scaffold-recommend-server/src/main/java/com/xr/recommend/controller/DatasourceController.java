package com.xr.recommend.controller;

import com.xr.recommend.common.model.DatasourceModel;
import com.xr.recommend.common.service.IDatasourceService;
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
* <b>description</b>: 推荐数据源 HTTP服务 <br>
*/
@RestController
public class DatasourceController {

  @Resource
  private IDatasourceService datasourceService;

  /**
   * <p>
   * 插入一条记录
   * </p>
   *
   * @param entity 实体对象
   * @return
   */
  @RequestMapping("/datasource/insert")
  public ResultDto insert(@RequestBody DatasourceModel entity) throws Exception {
    datasourceService.insert(entity);
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
  @RequestMapping("/datasource/insert/batch")
  public ResultDto<Integer> insertBatch(@RequestBody List<DatasourceModel> entityList) throws Exception {
    datasourceService.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return DatasourceModel 插入/更新成功的对象
   */
  @RequestMapping("/datasource/upsert")
  public ResultDto<DatasourceModel> upsert(@RequestBody DatasourceModel entity) throws Exception {
    datasourceService.upsert(entity);
    return ResultDto.success();
  }

  /**
   * <p>
   * 根据 主键ID 删除
   * </p>
   *
   * @param datasource_id 主键ID
   * @return 删除的行数
   */
  @RequestMapping("/datasource/delete/{datasource_id}")
  public ResultDto<Integer> deleteById(@PathVariable("datasource_id") long datasource_id) throws Exception {
    return ResultDto.successData(datasourceService.deleteById(datasource_id));
  }

  /**
   * <p>
   * 根据 condition 条件，删除记录
   * </p>
   *
   * @param condition 删除条件
   * @return Integer 删除的行数
   */
  @RequestMapping("/datasource/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(datasourceService.delete(condition));
  }

  /**
   * <p>
   * 根据 model 修改数据
   * </p>
   *
   * @param entity 实体对象
   * @return 更新的行数
   */
  @RequestMapping("/datasource/update/model")
  public ResultDto<Long> update(@RequestBody DatasourceModel entity) throws Exception {
    return ResultDto.successData(datasourceService.update(entity));
  }

  /**
   * <p>
   * 根据map条件 修改
   * </p>
   *
   * @param columnMap 更新数据/更新条件
   * @return 更新的行数
   */
  @RequestMapping("/datasource/update/map")
  public ResultDto<Long> updateByMap(@RequestBody Map<String, Object> columnMap) throws Exception {
    return ResultDto.successData(datasourceService.update(columnMap));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param datasource_id 主键ID
   * @param master 主节点 or 从节点
   * @return DatasourceModel
   */
  @RequestMapping("/datasource/select/{master}/{datasource_id}")
  public ResultDto<DatasourceModel> selectById(@PathVariable("datasource_id") long datasource_id, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectById(datasource_id, master));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param master 主节点 or 从节点
   * @return List<DatasourceModel> 列表
   */
  @RequestMapping("/datasource/select/{master}/batch")
  public ResultDto<List<DatasourceModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectByIds(idList, master));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return List<DatasourceModel> 列表
   */
  @RequestMapping("/datasource/select/{master}/list")
  public ResultDto<List<DatasourceModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectList(condition, master));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param master 主节点 or 从节点
   * @return DatasourceModel
   */
  @RequestMapping("/datasource/select/{master}/one")
  public ResultDto<DatasourceModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectOne(condition, master));
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
  @RequestMapping("/datasource/select/{master}/count")
  public ResultDto<Long> selectCount(@RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectCount(condition, master));
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
  @RequestMapping("/datasource/select/{master}/page/{page}/{size}")
  public ResultDto<PageData<DatasourceModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(datasourceService.selectPage(page, size, condition, master));
  }

}
