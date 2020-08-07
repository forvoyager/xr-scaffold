package com.xr.scaffold.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-07-23 20:02:00
 * <b>@description</b>:
 */
@Component
public class ServiceFallbackProvider implements FallbackProvider {

  private static final String SYSTEM_BUSY_RESPONSE = "{\"code\":201,\"message\":\"系统繁忙请稍后重试\"}";

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public String getRoute() {
    return "*";
  }

  @Override
  public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
    logger.error("route:{} fallback, reason:{}", route, cause.getCause().getMessage());
    return new ClientHttpResponse() {
      @Override
      public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        return getStatusCode().value();
      }

      @Override
      public String getStatusText() throws IOException {
        return getStatusCode().getReasonPhrase();
      }

      @Override
      public void close() {

      }

      @Override
      public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(SYSTEM_BUSY_RESPONSE.getBytes("utf-8"));
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return httpHeaders;
      }
    };
  }
}
