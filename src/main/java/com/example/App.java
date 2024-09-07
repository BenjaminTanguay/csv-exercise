package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;


@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        try {
            Vertx vertx = Vertx.vertx();
            String configuration = "configuration";
            Injector injector = Guice.createInjector(new MainModule(configuration, vertx));

            Set<LifeCycle> lifeCycles = injector.getInstance(LifeCycleProvider.class).getLifeCycles();

            vertx.deployVerticle(new MainVerticle(lifeCycles));
        } catch (Exception e) {
            log.info("Error starting the application", e);
        }

    }
}
