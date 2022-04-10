package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import com.xr.recommend.common.entity.DatasourceEntity;
import com.xr.recommend.common.service.IDatasourceService;
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
* <b>description</b>: 推荐数据源 HTTP服务 <br>
*/
@Api(tags = "推荐数据源相关操作")
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

  @DubboReference
  private IDatasourceService datasourceService;

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return DatasourceEntity 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<DatasourceEntity> upsert(@RequestBody DatasourceEntity entity) throws Exception {
    datasourceService.saveOrUpdate(entity);
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
  public ResultDto<Integer> insertBatch(@RequestBody List<DatasourceEntity> entityList) throws Exception {
    datasourceService.saveBatch(entityList);
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
    return ResultDto.successData(datasourceService.removeById(action_id));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param action_id 主键ID
   * @param cluster 主节点 or 从节点
   * @return DatasourceEntity
   */
  @GetMapping("/select/{cluster}/{action_id}")
  public ResultDto<DatasourceEntity> selectById(@PathVariable("action_id") long action_id, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.getById(action_id));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<DatasourceEntity> 列表
   */
  @GetMapping("/select/{cluster}/ids")
  public ResultDto<List<DatasourceEntity>> selectByIds(@RequestParam Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(datasourceService.listByIds(idList));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<DatasourceEntity> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<DatasourceEntity>> selectList(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return DatasourceEntity
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<DatasourceEntity> selectOne(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
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
  public ResultDto<PageData<DatasourceEntity>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestParam(required = false) Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(null);
  }

}
