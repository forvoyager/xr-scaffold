package com.xr.scaffold.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-07-23 20:18:00
 * <b>@description</b>:
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerHandler {

  @Autowired
  private SwaggerDocumentProvider swaggerDocumentProvider;

  @RequestMapping(value = "/configuration/security")
  public ResponseEntity<SecurityConfiguration> securityConfiguration() {
    return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
  }

  @RequestMapping(value = "/configuration/ui")
  public ResponseEntity<UiConfiguration> uiConfiguration() {
    return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
  }

  @RequestMapping
  public ResponseEntity<List<SwaggerResource>> swaggerResources() {
    return new ResponseEntity<>(swaggerDocumentProvider.get(), HttpStatus.OK);
  }

}
