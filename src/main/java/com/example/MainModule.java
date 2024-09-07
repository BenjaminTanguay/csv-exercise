package com.example;

import com.example.http.server.HttpServer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainModule extends AbstractModule {

    private final String configuration;
    private final Vertx vertx;

    @ProvidesIntoSet
    public LifeCycle provideHelloWorldLifeCycle(HttpServer httpServer) {
        return httpServer;
    }

    @Provides
    @Singleton
    Router providesRouter() {
        return Router.router(vertx);
    }

    @Provides
    @Singleton
    Vertx providesVertx() {
        return vertx;
    }

    @Provides
    @Singleton
    int providesServerPort() {
        return 8080;
    }
}
