package com.xr.scaffold.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-11-15 13:34:00
 * @Description: 自定义限流异常处理
 */
public class CustomBlockRequestHandler implements BlockRequestHandler {

  private static final String DEFAULT_BLOCK_MSG_PREFIX = "Blocked: ";

  private static final Map<String, Object> defaultBlockResponse = new HashMap<>();

  static {
    defaultBlockResponse.put("code", HttpStatus.TOO_MANY_REQUESTS.value());
    defaultBlockResponse.put("message", "系统繁忙，请稍后重试。");
  }

  @Override
  public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
    if (acceptsHtml(exchange)) {
      return htmlErrorResponse(ex);
    }

    // JSON result by default.
    return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(defaultBlockResponse));
  }

  private boolean acceptsHtml(ServerWebExchange exchange) {
    try {
      List<MediaType> acceptedMediaTypes = exchange.getRequest().getHeaders().getAccept();
      acceptedMediaTypes.remove(MediaType.ALL);
      MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
      return acceptedMediaTypes.stream().anyMatch(MediaType.TEXT_HTML::isCompatibleWith);
    } catch (InvalidMediaTypeException ex) {
      return false;
    }
  }

  private Mono<ServerResponse> htmlErrorResponse(Throwable ex) {
    return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
            .contentType(MediaType.TEXT_PLAIN)
            .bodyValue(DEFAULT_BLOCK_MSG_PREFIX + ex.getClass().getSimpleName());
  }
}

