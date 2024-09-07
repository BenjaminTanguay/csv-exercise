package com.example;

import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldLifeCycle implements LifeCycle {
    @Override
    public Future<Void> start() {
        log.info("Starting HelloWorldLifeCycle");
        return Future.succeededFuture();
    }

    @Override
    public Future<Void> stop() {
        log.info("Stopping HelloWorldLifeCycle");
        return Future.succeededFuture();
    }
}
