package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@AllArgsConstructor
@Slf4j
public class MainVerticle extends AbstractVerticle {

    private final Set<LifeCycle> lifeCycles;

    @Override
    public void start(Promise<Void> startPromise) {
        Future.all(lifeCycles.stream().map(LifeCycle::start).toList())
                .onSuccess(v -> {
                    log.info("MainVerticle started successfully");
                    startPromise.complete();
                })
                .onFailure(throwable -> {
                    log.error("MainVerticle failed to start", throwable);
                    startPromise.fail(throwable);
                });
    }

    @Override
    public void stop(Promise<Void> stopPromise) {
        Future.all(lifeCycles.stream().map(LifeCycle::stop).toList())
                .onSuccess(v -> {
                    log.info("MainVerticle stopped successfully");
                    stopPromise.complete();
                })
                .onFailure(throwable -> {
                    log.error("MainVerticle failed to stop", throwable);
                    stopPromise.fail(throwable);
                });
    }
}
