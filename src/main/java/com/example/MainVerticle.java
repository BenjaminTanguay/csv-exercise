package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class MainVerticle extends AbstractVerticle {

    private final Set<LifeCycle> lifeCycles;

    @Override
    public void start(Promise<Void> startPromise) {
        Future.all(lifeCycles.stream().map(LifeCycle::start).toList())
                .onSuccess(v -> startPromise.complete())
                .onFailure(startPromise::fail);
    }

    @Override
    public void stop(Promise<Void> stopPromise) {
        Future.all(lifeCycles.stream().map(LifeCycle::stop).toList())
                .onSuccess(v -> stopPromise.complete())
                .onFailure(stopPromise::fail);
    }
}
