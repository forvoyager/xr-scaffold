package com.xr.scaffold.config;

import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang.changyan@foundbyte.com
 * @Time: 2020-07-25 13:38:00
 * @Description:
 */
@Component
public class SwaggerDocumentProvider implements SwaggerResourcesProvider {

  @Resource
  private RouteDefinitionLocator routeDefinitionLocator;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();

    routeDefinitionLocator.getRouteDefinitions().subscribe(
            routeDefinition -> routeDefinition.getPredicates().forEach(
                    predicateDefinition -> resources.add(
                            this.swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", "/v2/api-docs"), "2.0")
                    )));

    return resources;
  }

  private SwaggerResource swaggerResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }
}
