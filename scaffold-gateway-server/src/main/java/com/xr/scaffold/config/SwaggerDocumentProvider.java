package com.xr.scaffold.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang.changyan@foundbyte.com
 * @Time: 2020-07-25 13:38:00
 * @Description:
 */
@Component
public class SwaggerDocumentProvider implements SwaggerResourcesProvider {

  @Autowired
  private RouteLocator routeLocator;

  @Autowired
  private GatewayProperties gatewayProperties;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();

    List<String> routes = new ArrayList<>();
    routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

    gatewayProperties.getRoutes().stream()
            .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
            .forEach(routeDefinition -> {
              routeDefinition.getPredicates().stream()
                      .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                      .forEach(predicateDefinition -> {
                        resources.add(swaggerResource(
                                routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", "/v2/api-docs"),
                                "2.0"
                        ));
                      });
            });

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
