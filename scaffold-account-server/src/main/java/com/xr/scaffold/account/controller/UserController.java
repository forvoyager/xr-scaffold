package com.xr.scaffold.account.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-07-24 17:33:00
 * <b>@description</b>:
 */
@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/getUserName")
  public Object getUserName(Long userId) throws Exception{
    return userId+System.currentTimeMillis();
  }

}
