package ${cfg.parentPath}.${package.ModuleName}.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: ${author}
 * @since: ${cfg.datetime}
 * @description: ${table.comment} 数据传输对象
 */
public class ${table.entityName?replace("Model","")}Dto implements Serializable {

  <#list table.fields as field>
  @ApiModelProperty(value = "${field.comment}")
  private ${field.columnType.type} ${field.propertyName};

  </#list>

  <#list table.fields as field>
  public ${field.columnType.type} get${field.propertyName?cap_first}() {
    return this.${field.propertyName};
  }

  public ${table.entityName?replace("Model","")}Dto set${field.propertyName?cap_first}(${field.columnType.type} ${field.propertyName}) {
    this.${field.propertyName} = ${field.propertyName};
    return this;
  }

  </#list>
}

