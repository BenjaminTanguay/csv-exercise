package com.example;

import com.example.dao.DaoModule;
import com.example.http.HttpModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainModule extends AbstractModule {

    private final String configuration;
    private final Vertx vertx;


    @Override
    protected void configure() {
        install(new DaoModule());
        install(new HttpModule());
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
