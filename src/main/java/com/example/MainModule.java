package com.example;

import com.example.http.model.HttpRoute;
import com.example.http.route.DataFilterRequestHandler;
import com.example.http.server.HttpServer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;

import java.util.Set;

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
    Router providesRouter(Set<HttpRoute> httpRoutes) {
        Router router = Router.router(vertx);
        httpRoutes.forEach(
                route ->
                        router.route(route.method(), route.uri())
                                .handler(route.handler()));
        return router;
    }

    @ProvidesIntoSet
    HttpRoute provideDataFilterHttpRoute(DataFilterRequestHandler dataFilterRequestHandler) {
        return HttpRoute.builder()
                .uri("/v1/data-filter")
                .method(HttpMethod.GET)
                .handler(dataFilterRequestHandler)
                .build();
    }

    @Provides
    @Singleton
    Vertx providesVertx() {
        return vertx;
    }

    @Provides
    @Singleton
    FileSystem providesFileSystem(Vertx vertx) {
        return vertx.fileSystem();
    }

    @Provides
    @Singleton
    String providesCsvPath() {
        return "./conf/BoulderTrailHeads.csv";
    }

    @Provides
    @Singleton
    int providesServerPort() {
        return 8080;
    }
}
