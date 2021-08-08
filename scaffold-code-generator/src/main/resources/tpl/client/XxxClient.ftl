package ${basePackageName}.${moduleName}.client.api;

import ${basePackageName}.${moduleName}.common.controller.I${modelName?cap_first}Controller;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <b>author</b>: ${author}
 * <b>time</b>: ${time} <br>
 * <b>description</b>: ${comments} 相关操作feign客户端 <br>
 */
@FeignClient(value = "${r"${"}${moduleName}.service.application.name}")
public interface ${modelName?cap_first}Client extends I${modelName?cap_first}Controller {
}
