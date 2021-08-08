package com.xr.scaffold.account.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-08 16:10:00
 * <b>@description</b>: 自定义注解，用于获取登录用户信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
