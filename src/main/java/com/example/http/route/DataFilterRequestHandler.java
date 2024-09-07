package com.example.http.route;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataFilterRequestHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        log.info("HTTP Request in on uri {}", routingContext.request().uri());


        String response = "Hello world";
        log.debug("HTTP Request out on uri {}: ", routingContext.request().uri(), response);
        routingContext.response()
                .putHeader("content-type", "text/plain")
                .end(response);
    }
}
