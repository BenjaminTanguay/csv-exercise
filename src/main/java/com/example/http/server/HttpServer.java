package com.example.http.server;

import com.example.LifeCycle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer implements LifeCycle {

    private final Router router;
    private final Vertx vertx;

    @Inject
    public HttpServer(Router router, Vertx vertx) {
        this.router = router;
        this.vertx = vertx;
    }

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
