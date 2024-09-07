package com.example;

import com.example.http.server.HttpServer;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.vertx.core.Vertx;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainModule extends AbstractModule {

    private final String configuration;
    private final Vertx vertx;

    @ProvidesIntoSet
    public LifeCycle provideHelloWorldLifeCycle(HttpServer httpServer) {
        return httpServer;
    }

}
