package com.example;

import io.vertx.core.Future;

public interface LifeCycle {

    Future<Void> start();
    Future<Void> stop();
}
