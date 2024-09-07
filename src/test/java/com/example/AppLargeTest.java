package com.example;


import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@ExtendWith(VertxExtension.class)
public class AppLargeTest {

    private WebClient webClient;

    @BeforeEach
    void setUp(Vertx vertx) {
        webClient = WebClient.create(vertx);
    }

    @Test
    void should_when(VertxTestContext vertxTestContext) {
        // GIVEN
        App.main(null);
        // We wait for the app to be up.
        await().atLeast(5, TimeUnit.SECONDS);

        // WHEN
        Future<HttpResponse<Buffer>> future = webClient.get(8080, "localhost", "/v1/data-filter").send();

        // THEN
        future.onSuccess(response -> vertxTestContext.verify(() -> {
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(response.bodyAsString()).isEqualTo("Hello world");
            vertxTestContext.completeNow();
        }))
                .onFailure(vertxTestContext::failNow);

    }

}
