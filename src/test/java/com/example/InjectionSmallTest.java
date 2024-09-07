package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

@Slf4j
@ExtendWith(VertxExtension.class)
public class InjectionSmallTest {

    private String configuration;

    @BeforeEach
    void setup() {
        this.configuration = "configuration";

    }

    @Test
    void should_allowInjection_when_allOk(Vertx vertx) {
        // GIVEN

        // WHEN
        try {
            Injector injector = Guice.createInjector(new MainModule(configuration, vertx));
            Set<LifeCycle> lifeCycles = injector.getInstance(LifeCycleProvider.class).getLifeCycles();
        } catch (Exception e) {
            log.info("Error starting the application", e);
        }

        // THEN
        // No error expected!
    }

}
