package ${basePackageName}.${moduleName}.controller;

import ${basePackageName}.${moduleName}.common.model.${modelName?cap_first}Model;
import ${basePackageName}.${moduleName}.common.service.I${modelName?cap_first}Service;
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
* <b>author</b>: ${author}
* <b>time</b>: ${time} <br>
* <b>description</b>: ${comments} HTTP服务 <br>
*/
@Api(tags = "${comments}相关操作")
@RestController
@RequestMapping("/${modelName}")
public class ${modelName?cap_first}Controller {

  @Resource
  private I${modelName?cap_first}Service ${modelName}Service;

  /**
   * <p>
   * 存在则更新，否则插入
   * </p>
   *
   * @param entity 实体对象
   * @return ${modelName?cap_first}Model 插入/更新成功的对象
   */
  @PostMapping("/upsert")
  public ResultDto<${modelName?cap_first}Model> upsert(@RequestBody ${modelName?cap_first}Model entity) throws Exception {
    ${modelName}Service.upsert(entity);
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
  public ResultDto<Integer> insertBatch(@RequestBody List<${modelName?cap_first}Model> entityList) throws Exception {
    ${modelName}Service.insertBatch(entityList);
    return ResultDto.success();
  }

  /**
   * <p>
   * 根据 主键ID 删除
   * </p>
   *
   * @param ${primaryField} 主键ID
   * @return 删除的行数
   */
  @DeleteMapping("/delete/{${primaryField}}")
  public ResultDto<Integer> deleteById(@PathVariable("${primaryField}") long ${primaryField}) throws Exception {
    return ResultDto.successData(${modelName}Service.deleteById(${primaryField}));
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
    return ResultDto.successData(${modelName}Service.delete(condition));
  }

  /**
   * <p>
   * 根据 主键ID 查询
   * </p>
   *
   * @param ${primaryField} 主键ID
   * @param cluster 主节点 or 从节点
   * @return ${modelName?cap_first}Model
   */
  @GetMapping("/select/{cluster}/{${primaryField}}")
  public ResultDto<${modelName?cap_first}Model> selectById(@PathVariable("${primaryField}") long ${primaryField}, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(${modelName}Service.selectById(${primaryField}, cluster));
  }

  /**
   * <p>
   * 根据 ID 批量查询
   * </p>
   *
   * @param idList 主键ID列表
   * @param cluster 主节点 or 从节点
   * @return List<${modelName?cap_first}Model> 列表
   */
  @GetMapping("/select/{cluster}/ids")
  public ResultDto<List<${modelName?cap_first}Model>> selectByIds(@RequestParam Collection<? extends Serializable> idList, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(${modelName}Service.selectByIds(idList, cluster));
  }

  /**
   * <p>
   * 查询（根据 condition 条件）
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return List<${modelName?cap_first}Model> 列表
   */
  @GetMapping("/select/{cluster}/list")
  public ResultDto<List<${modelName?cap_first}Model>> selectList(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(${modelName}Service.selectList(condition, cluster));
  }

  /**
   * <p>
   * 根据 condition 条件，查询一条记录
   * </p>
   *
   * @param condition 查询条件
   * @param cluster 主节点 or 从节点
   * @return ${modelName?cap_first}Model
   */
  @GetMapping("/select/{cluster}/one")
  public ResultDto<${modelName?cap_first}Model> selectOne(@RequestParam Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(${modelName}Service.selectOne(condition, cluster));
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
    return ResultDto.successData(${modelName}Service.selectCount(condition, cluster));
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
  public ResultDto<PageData<${modelName?cap_first}Model>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestParam(required = false) Map<String, String> condition, @PathVariable("cluster") Cluster cluster) throws Exception {
    return ResultDto.successData(${modelName}Service.selectPage(page, size, condition, cluster));
  }

}
