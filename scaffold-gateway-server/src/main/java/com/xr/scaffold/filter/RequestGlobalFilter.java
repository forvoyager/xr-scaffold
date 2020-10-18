package com.xr.scaffold.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-10-10 13:32:00
 * <b>@description</b>: 打印请求日志
 */
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest serverHttpRequest = exchange.getRequest();
    String uri = serverHttpRequest.getURI().toString();

    String method = serverHttpRequest.getMethodValue();
    if ("POST".equals(method)) {
      return DataBufferUtils.join(exchange.getRequest().getBody())
              .flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                try {
                  String bodyString = new String(bytes, "utf-8");
                  logger.info("[POST]{}, with param:{}", uri, bodyString);
                  exchange.getAttributes().put("POST_BODY", bodyString);
                } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
                }
                DataBufferUtils.release(dataBuffer);
                Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                  DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                  return Mono.just(buffer);
                });

                ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                  @Override
                  public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                  }
                };
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
              });
    } else if ("GET".equals(method)) {
      MultiValueMap<String, String> requestQueryParams = serverHttpRequest.getQueryParams();
      logger.info("[GET]{}, with param:{}", uri, requestQueryParams.toString());

      return chain.filter(exchange);
    }

    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
