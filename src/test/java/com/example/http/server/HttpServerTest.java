package com.example.http.server;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
@ExtendWith(MockitoExtension.class)
class HttpServerTest {

    private static final String HELLO_ROUTE = "/hello";
    private static final String EXPECTED_RESPONSE = "expected_response";
    private static final int PORT = 1234;

    private HttpServer httpServer;


    @BeforeEach
    void setUp(Vertx vertx) {
        Router router = Router.router(vertx);
        router.get(HELLO_ROUTE).handler(this::handleHelloRoute);
        this.httpServer = new HttpServer(router, vertx, PORT);
    }

    @Test
    void should_returnSuccess_when_started(VertxTestContext vertxTestContext) {
        // GIVEN

        // WHEN
        Future<Void> future = httpServer.start();

        // THEN
        future
                .onSuccess(ok -> vertxTestContext.completeNow())
                .onFailure(vertxTestContext::failNow);
    }

    @Test
    void should_returnSuccess_when_started(Vertx vertx, VertxTestContext vertxTestContext) {
        // GIVEN
        WebClient client = WebClient.create(vertx);
        httpServer.start()

        // WHEN
                .compose(ok -> client.get(PORT, "localhost", HELLO_ROUTE).send())
        // THEN
                .onSuccess(response -> vertxTestContext.verify(() -> {
                    assertThat(response.body().toString()).isEqualTo(EXPECTED_RESPONSE);
                    vertxTestContext.completeNow();
                }))
                .onFailure(vertxTestContext::failNow);
    }

    @Test
    void should_returnSuccess_when_stopped(VertxTestContext vertxTestContext) {
        // GIVEN

        // WHEN
        Future<Void> future = httpServer.stop();

        // THEN
        future
                .onSuccess(ok -> vertxTestContext.completeNow())
                .onFailure(vertxTestContext::failNow);
    }

    private void handleHelloRoute(RoutingContext routingContext) {
        // Respond with "Hello, Vert.x!"
        routingContext.response()
                .putHeader("content-type", "text/plain")
                .end(EXPECTED_RESPONSE);
    }
}