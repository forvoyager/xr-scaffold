package ${basePackageName}.${moduleName}.controller;

import ${basePackageName}.${moduleName}.common.controller.I${modelName?cap_first}Controller;
import ${basePackageName}.${moduleName}.common.model.${modelName?cap_first}Model;
import ${basePackageName}.${moduleName}.service.I${modelName?cap_first}Service;
import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class ${modelName?cap_first}Controller implements I${modelName?cap_first}Controller {

  @Resource
  private I${modelName?cap_first}Service ${modelName}Service;

  @Override
  public ResultDto<${modelName?cap_first}Model> insert(@RequestBody ${modelName?cap_first}Model entity) throws Exception {
    return ResultDto.successData(${modelName}Service.insert(entity));
  }

  @Override
  public ResultDto<Integer> insertBatch(@RequestBody List<${modelName?cap_first}Model> entityList) throws Exception {
    return ResultDto.successData(${modelName}Service.insertBatch(entityList));
  }

  @Override
  public ResultDto<${modelName?cap_first}Model> insertOrUpdate(@RequestBody ${modelName?cap_first}Model entity) throws Exception {
    return ResultDto.successData(${modelName}Service.insertOrUpdate(entity));
  }

  @Override
  public ResultDto<Long> deleteById(@PathVariable("${primaryField}") long ${primaryField}) throws Exception {
    return ResultDto.successData(${modelName}Service.deleteById(${primaryField}));
  }

  @Override
  public ResultDto<Long> deleteByMap(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(${modelName}Service.deleteByMap(condition));
  }

  @Override
  public ResultDto<Long> update(@RequestBody ${modelName?cap_first}Model entity) throws Exception {
    return ResultDto.successData(${modelName}Service.update(entity));
  }

  @Override
  public ResultDto<Long> updateByMap(@RequestBody Map<String, Object> condition) throws Exception {
    return ResultDto.successData(${modelName}Service.updateByMap(condition));
  }

  @Override
  public ResultDto<${modelName?cap_first}Model> selectById(@PathVariable("${primaryField}") long ${primaryField}, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectById(${primaryField}, master));
  }

  @Override
  public ResultDto<List<${modelName?cap_first}Model>> selectByIds(@RequestBody Collection<? extends Serializable> idList, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectByIds(idList, master));
  }

  @Override
  public ResultDto<List<${modelName?cap_first}Model>> selectList(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectList(condition, master));
  }

  @Override
  public ResultDto<${modelName?cap_first}Model> selectOne(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectOne(condition, master));
  }

  @Override
  public ResultDto<Map<${primaryFieldType}, ${modelName?cap_first}Model>> selectMap(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectMap(condition, master));
  }

  @Override
  public ResultDto<Long> selectCount(@RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectCount(condition, master));
  }

  @Override
  public ResultDto<PageData<${modelName?cap_first}Model>> selectPage(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Map<String, Object> condition, @PathVariable("master") Cluster master) throws Exception {
    return ResultDto.successData(${modelName}Service.selectPage(page, size, condition, master));
  }

}
