package com.xr.recommend.controller;

import com.xr.recommend.common.model.DatasourceModel;
import com.xr.recommend.common.service.IDatasourceService;
import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-08-10 14:31:18 <br>
* <b>description</b>: 推荐数据源 HTTP服务 <br>
*/
@Api(tags = "推荐数据源相关操作")
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

  @Resource
  private IDatasourceService datasourceService;

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return DatasourceModel 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<DatasourceModel> upsert(@RequestBody DatasourceModel entity) throws Exception {
    datasourceService.upsert(entity);
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
  public ResultDto<Integer> insertBatch(@RequestBody List<DatasourceModel> entityList) throws Exception {
    datasourceService.insertBatch(entityList);
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
  @DeleteMapping("/delete/{datasource_id}")
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
  @DeleteMapping("/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(datasourceService.delete(condition));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param datasource_id 主键ID
   * @param cluster 主节点 or 从节点
   * @return DatasourceModel
   */
  @GetMapping("/select/{cluster}/{datasource_id}")
  public ResultDto<DatasourceModel> selectById(@PathVariable("datasource_id") long datasource_id, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.selectById(datasource_id, cluster));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<DatasourceModel> 列表
   */
  @GetMapping("/select/{cluster}/batch")
  public ResultDto<List<DatasourceModel>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.selectByIds(idList, cluster));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<DatasourceModel> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<DatasourceModel>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.selectList(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return DatasourceModel
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<DatasourceModel> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.selectOne(condition, cluster));
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
    return ResultDto.successData(datasourceService.selectCount(condition, cluster));
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
  public ResultDto<PageData<DatasourceModel>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody(required = false) Map<String, Object> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.selectPage(page, size, condition, cluster));
  }

}
