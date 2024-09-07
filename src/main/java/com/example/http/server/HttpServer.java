package com.example.http.server;

import com.example.LifeCycle;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer implements LifeCycle {
    @Override
    public Future<Void> start() {
        log.info("Starting HttpServer");
        return Future.succeededFuture();
    }

    @Override
    public Future<Void> stop() {
        log.info("Stopping HttpServer");
        return Future.succeededFuture();
    }
}
