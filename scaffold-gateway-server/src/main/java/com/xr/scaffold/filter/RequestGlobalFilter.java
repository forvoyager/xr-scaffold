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
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-10-10 13:32:00
 * <b>@description</b>: 打印请求日志
 */
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private static final String TIME = "Time";
  private static final String POST_BODY = "POST_BODY";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    exchange.getAttributes().put(TIME, System.currentTimeMillis());

    ServerHttpRequest serverHttpRequest = exchange.getRequest();
    String uri = serverHttpRequest.getURI().toString();

    String method = serverHttpRequest.getMethodValue();
    if ("POST".equals(method)) {
      return DataBufferUtils.join(exchange.getRequest().getBody())
              .flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                try {
                  exchange.getAttributes().put(POST_BODY, new String(bytes, "utf-8"));
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

                return chain.filter(exchange.mutate().request(mutatedRequest).build()).then(
                        Mono.fromRunnable(() -> {
                          Long start = exchange.getAttribute(TIME);
                          if(start != null){
                            logger.info("[POST]{}, with param:{}, cost(ms):{}", uri, exchange.getAttribute(POST_BODY), System.currentTimeMillis()-start);
                          }
                        })
                );
              });
    }

    return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
              Long start = exchange.getAttribute(TIME);
              if(start != null){
                MultiValueMap<String, String> requestQueryParams = serverHttpRequest.getQueryParams();
                logger.info("[GET]{}, with param:{}, cost(ms):{}", uri, requestQueryParams.toString(), System.currentTimeMillis()-start);
              }
            })
    );
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
