package com.xr.scaffold.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-07-25 13:38:00
 * @Description:
 */
@Component
@Primary
public class SwaggerDocumentProvider implements SwaggerResourcesProvider {

  @Autowired
  private RouteLocator routeLocator;

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    List<Route> routes = routeLocator.getRoutes();
    routes.forEach(route -> {
      resources.add(swaggerResource(route.getId(), route.getPrefix() + "/v2/api-docs", "2.0"));
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
