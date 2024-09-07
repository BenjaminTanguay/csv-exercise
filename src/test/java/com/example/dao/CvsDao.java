package com.example.dao;

import com.example.LifeCycle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import jakarta.inject.Inject;

public class CvsDao implements LifeCycle {

    private final String csvPath;
    private final Vertx vertx;

    @Inject
    public CvsDao(String csvPath, Vertx vertx) {
        this.csvPath = csvPath;
        this.vertx = vertx;
    }

    @Override
    public Future<Void> start() {
        return Future.succeededFuture();
    }

    @Override
    public Future<Void> stop() {
        return Future.succeededFuture();
    }
}
