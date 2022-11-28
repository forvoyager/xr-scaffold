package ${cfg.parentPath}.${package.ModuleName}.client;

import ${cfg.parentPath}.${package.ModuleName}.StarterAutoConfiguration;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.${table.entityName?replace("Model","")}Dto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.BasePageQueryDto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.PageDataDto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.ResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: ${author}
 * @since: ${cfg.datetime}
 * @description: ${table.comment} feign客户端
 */
@FeignClient(name = StarterAutoConfiguration.SERVER_NAME, contextId = "${table.entityName?replace("Model","")?uncap_first}")
public interface ${table.entityName?replace("Model","")}Client {

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/insert")
  public ResultDto<${table.entityName?replace("Model","")}Dto> insert(@RequestBody ${table.entityName?replace("Model","")}Dto dto) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/insert/batch")
  public ResultDto<Long> insertBatch(@RequestBody List<${table.entityName?replace("Model","")}Dto> dtoList) throws Exception;

  @DeleteMapping("/${table.entityName?replace("Model","")?uncap_first}/delete/byId")
  public ResultDto<Long> deleteById(@RequestParam ${table.fields[0].columnType.type} ${table.fields[0].propertyName}) throws Exception;

  @DeleteMapping("/${table.entityName?replace("Model","")?uncap_first}/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception;

  @PutMapping("/${table.entityName?replace("Model","")?uncap_first}/update/byId")
  public ResultDto<Long> updateById(@RequestBody ${table.entityName?replace("Model","")}Dto dto) throws Exception;

  @PutMapping("/${table.entityName?replace("Model","")?uncap_first}/update")
  public ResultDto<Long> update(@RequestBody Map<String, Object> condition) throws Exception;

  @GetMapping("/${table.entityName?replace("Model","")?uncap_first}/select/byId")
  public ResultDto<${table.entityName?replace("Model","")}Dto> selectById(@RequestParam ${table.fields[0].columnType.type} ${table.fields[0].propertyName}) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/byIds")
  public ResultDto<List<${table.entityName?replace("Model","")}Dto>> selectByIds(@RequestBody Collection<? extends Serializable> ${table.fields[0].propertyName}List) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/list")
  public ResultDto<List<${table.entityName?replace("Model","")}Dto>> selectList(@RequestBody Map<String, Object> condition) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/one")
  public ResultDto<${table.entityName?replace("Model","")}Dto> selectOne(@RequestBody Map<String, Object> condition) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/count")
  public ResultDto<Long> selectCount(@RequestBody Map<String, Object> condition) throws Exception;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/page")
  public ResultDto<PageDataDto<${table.entityName?replace("Model","")}Dto>> selectPage(@RequestBody BasePageQueryDto queryDto) throws Exception;
}
