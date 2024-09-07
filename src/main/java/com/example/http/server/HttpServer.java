package com.example.http.server;

import com.example.LifeCycle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer implements LifeCycle {

    private final Router router;
    private final Vertx vertx;
    private final int port;

    @Inject
    public HttpServer(Router router, Vertx vertx, int port) {
        this.router = router;
        this.vertx = vertx;
        this.port = port;
    }

    @Override
    public Future<Void> start() {
        Promise<Void> promise = Promise.promise();
        log.info("Starting HttpServer");

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(port)
                .onSuccess(ok -> {
                    promise.complete();
                    log.info("HTTP server started on port 8080");
                })
                .onFailure(throwable -> {
                    log.error("HTTP server failed to start", throwable);
                    promise.fail(throwable);

                });

        return promise.future();
    }

    @Override
    public Future<Void> stop() {
        log.info("Stopping HttpServer");
        return Future.succeededFuture();
    }
}
