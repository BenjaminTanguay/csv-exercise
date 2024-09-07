package com.example.http;

import com.example.LifeCycle;
import com.example.http.model.HttpRoute;
import com.example.http.route.DataFilterRequestHandler;
import com.example.http.server.HttpServer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

import java.util.Set;

public class HttpModule extends AbstractModule {

    @ProvidesIntoSet
    public LifeCycle provideHttpServerLifeCycle(HttpServer httpServer) {
        return httpServer;
    }

    @Provides
    @Singleton
    Router providesRouter(Vertx vertx, Set<HttpRoute> httpRoutes) {
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
}