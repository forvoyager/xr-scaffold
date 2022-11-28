package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.${table.entityName?replace("Model","")}Dto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.BasePageQueryDto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.PageDataDto;
import ${cfg.parentPath}.${package.ModuleName}.common.dto.ResultDto;
import ${package.Entity}.${table.entityName};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author: ${author}
 * @since: ${cfg.datetime}
 * @description: ${table.comment} 前端控制器
 */
@Api(tags = "${table.comment} 接口")
@RestController
public class ${table.controllerName} {

  @Autowired
  private ${table.serviceName} ${table.entityName?replace("Model","")?uncap_first}Service;

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/insert")
  public ResultDto<${table.entityName?replace("Model","")}Dto> insert(@RequestBody ${table.entityName?replace("Model","")}Dto dto) throws Exception{
    ${table.entityName} model = new ${table.entityName}();
    BeanUtils.copyProperties(dto, model);
    ${table.entityName?replace("Model","")?uncap_first}Service.save(model);
    dto.set${table.fields[0].propertyName?cap_first}(model.get${table.fields[0].propertyName?cap_first}());
    return ResultDto.successData(dto);
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/insert/batch")
  public ResultDto<Long> insertBatch(@RequestBody List<${table.entityName?replace("Model","")}Dto> dtoList) throws Exception{
    ${table.entityName} model = null;
    List<${table.entityName}> models = new ArrayList<>();
    for(${table.entityName?replace("Model","")}Dto dto : dtoList){
      model = new ${table.entityName}();
      BeanUtils.copyProperties(dto, model);
      models.add(model);
    }
    ${table.entityName?replace("Model","")?uncap_first}Service.saveBatch(models);
    return ResultDto.successData(dtoList.size());
  }

  @DeleteMapping("/${table.entityName?replace("Model","")?uncap_first}/delete/byId")
  public ResultDto<Long> deleteById(@RequestParam ${table.fields[0].columnType.type} ${table.fields[0].propertyName}) throws Exception{
    ${table.entityName?replace("Model","")?uncap_first}Service.removeById(${table.fields[0].propertyName});
    return ResultDto.success();
  }

  @DeleteMapping("/${table.entityName?replace("Model","")?uncap_first}/delete")
  public ResultDto<Long> delete(@RequestBody Map<String, Object> condition) throws Exception{
    // todo
    return ResultDto.success();
  }

  @PutMapping("/${table.entityName?replace("Model","")?uncap_first}/update/byId")
  public ResultDto<Long> updateById(@RequestBody ${table.entityName?replace("Model","")}Dto dto) throws Exception{
    if(dto.get${table.fields[0].propertyName?cap_first}() == null){ return ResultDto.failure(); }

    ${table.entityName} model = new ${table.entityName}();
    BeanUtils.copyProperties(dto, model);

    UpdateWrapper<${table.entityName}> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq(${table.entityName}.${table.fields[0].columnName?upper_case}, dto.get${table.fields[0].propertyName?cap_first}());
    ${table.entityName?replace("Model","")?uncap_first}Service.update(model, updateWrapper);
    return ResultDto.success();
  }

  @PutMapping("/${table.entityName?replace("Model","")?uncap_first}/update")
  public ResultDto<Long> update(@RequestBody Map<String, Object> condition) throws Exception{
    // todo
    return ResultDto.success();
  }

  @GetMapping("/${table.entityName?replace("Model","")?uncap_first}/select/byId")
  public ResultDto<${table.entityName?replace("Model","")}Dto> selectById(@RequestParam ${table.fields[0].columnType.type} ${table.fields[0].propertyName}) throws Exception{
    ${table.entityName} model = ${table.entityName?replace("Model","")?uncap_first}Service.getById(${table.fields[0].propertyName});
    ${table.entityName?replace("Model","")}Dto dto = new ${table.entityName?replace("Model","")}Dto();
    BeanUtils.copyProperties(model, dto);
    return ResultDto.successData(dto);
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/byIds")
  public ResultDto<List<${table.entityName?replace("Model","")}Dto>> selectByIds(@RequestBody Collection<? extends Serializable> ${table.fields[0].propertyName}List) throws Exception{
    if(${table.fields[0].propertyName}List == null || ${table.fields[0].propertyName}List.isEmpty()){ return ResultDto.successData(Collections.EMPTY_LIST); }

    ${table.entityName?replace("Model","")}Dto dto = null;
    List<${table.entityName?replace("Model","")}Dto> dtos = new ArrayList<>();
    List<${table.entityName}> models = ${table.entityName?replace("Model","")?uncap_first}Service.listByIds(${table.fields[0].propertyName}List);
    for(${table.entityName} model : models){
      dto = new ${table.entityName?replace("Model","")}Dto();
      BeanUtils.copyProperties(model, dto);
      dtos.add(dto);
    }
    return ResultDto.successData(dtos);
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/list")
  public ResultDto<List<${table.entityName?replace("Model","")}Dto>> selectList(@RequestBody Map<String, Object> condition) throws Exception{
    ${table.entityName?replace("Model","")}Dto dto = null;
    List<${table.entityName?replace("Model","")}Dto> dtos = new ArrayList<>();
    List<${table.entityName}> models = null; // todo query
    for(${table.entityName} model : models){
      dto = new ${table.entityName?replace("Model","")}Dto();
      BeanUtils.copyProperties(model, dto);
      dtos.add(dto);
    }
    return ResultDto.successData(dtos);
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/one")
  public ResultDto<${table.entityName?replace("Model","")}Dto> selectOne(@RequestBody Map<String, Object> condition) throws Exception{
    // ${table.entityName?replace("Model","")?uncap_first}Service.getOne() // todo
    return ResultDto.success();
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/count")
  public ResultDto<Long> selectCount(@RequestBody Map<String, Object> condition) throws Exception{
    // ${table.entityName?replace("Model","")?uncap_first}Service.count() // todo
    return ResultDto.successData(0);
  }

  @PostMapping("/${table.entityName?replace("Model","")?uncap_first}/select/page")
  public ResultDto<PageDataDto<${table.entityName?replace("Model","")}Dto>> selectPage(@RequestBody BasePageQueryDto queryDto) throws Exception{
    Page<${table.entityName}> pageData = new Page(queryDto.getCurrent(), queryDto.getSize());
    QueryWrapper<${table.entityName}> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc(${table.entityName}.${table.fields[0].columnName?upper_case});
    pageData = ${table.entityName?replace("Model","")?uncap_first}Service.page(pageData, queryWrapper);

    List<${table.entityName?replace("Model","")}Dto> ${table.entityName?replace("Model","")?uncap_first}Dtos = new ArrayList<>();
    Optional.ofNullable(pageData.getRecords()).orElse(Collections.EMPTY_LIST).forEach(new Consumer<${table.entityName}>() {
      @Override
      public void accept(${table.entityName} o) {
        ${table.entityName?replace("Model","")}Dto dto = new ${table.entityName?replace("Model","")}Dto();
        BeanUtils.copyProperties(o, dto);
        ${table.entityName?replace("Model","")?uncap_first}Dtos.add(dto);
      }
    });

    PageDataDto<${table.entityName?replace("Model","")}Dto> data = new PageDataDto();
    data.setCurrent(pageData.getCurrent());
    data.setSize(pageData.getSize());
    data.setData(${table.entityName?replace("Model","")?uncap_first}Dtos);
    data.setTotalPages(pageData.getPages());
    data.setTotalRecords(pageData.getTotal());
    data.setHasNext(pageData.hasNext());
    return ResultDto.successData(data);
  }

}
