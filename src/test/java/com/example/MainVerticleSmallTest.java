package com.example;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(VertxExtension.class)
@ExtendWith(MockitoExtension.class)
class MainVerticleSmallTest {

    private Set<LifeCycle> lifeCycles;

    @Mock private LifeCycle lifeCycle1;
    @Mock private LifeCycle lifeCycle2;

    private MainVerticle mainVerticle;

    @BeforeEach
    void setUp() {
        this.lifeCycles = Set.of(lifeCycle1, lifeCycle2);
        this.mainVerticle = new MainVerticle(lifeCycles);
    }

    @Test
    void should_completeStartPromise_when_allOk(VertxTestContext vertxTestContext) {
        // GIVEN
        when(lifeCycle1.start()).thenReturn(Future.succeededFuture());
        when(lifeCycle2.start()).thenReturn(Future.succeededFuture());
        Promise<Void> promise = Promise.promise();

        // WHEN
        mainVerticle.start(promise);

        // THEN
        promise.future()
                .onSuccess(ok -> vertxTestContext.verify(() -> {
                    verify(lifeCycle1, times(1)).start();
                    verify(lifeCycle2, times(1)).start();
                    vertxTestContext.completeNow();
                }))
                .onFailure(vertxTestContext::failNow);

    }


    @Test
    void should_failStartPromise_when_lifeCycleFailsToStart(VertxTestContext vertxTestContext) {
        // GIVEN
        Exception exception = new Exception("oh no!");
        when(lifeCycle1.start()).thenReturn(Future.failedFuture(exception));
        when(lifeCycle2.start()).thenReturn(Future.succeededFuture());
        Promise<Void> promise = Promise.promise();

        // WHEN
        mainVerticle.start(promise);

        // THEN
        promise.future()
                .onSuccess(ok -> vertxTestContext.failNow("Expected a failure but got a success instead"))
                .onFailure(throwable -> {
                    vertxTestContext.verify(() -> {
                        assertThat(throwable).isEqualTo(exception);
                        vertxTestContext.completeNow();
                    });
                });
    }

    @Test
    void should_completeStopPromise_when_allOk(VertxTestContext vertxTestContext) {
        // GIVEN
        when(lifeCycle1.stop()).thenReturn(Future.succeededFuture());
        when(lifeCycle2.stop()).thenReturn(Future.succeededFuture());
        Promise<Void> promise = Promise.promise();

        // WHEN
        mainVerticle.stop(promise);

        // THEN
        promise.future()
                .onSuccess(ok -> vertxTestContext.verify(() -> {
                    verify(lifeCycle1, times(1)).stop();
                    verify(lifeCycle2, times(1)).stop();
                    vertxTestContext.completeNow();
                }))
                .onFailure(vertxTestContext::failNow);
    }

    @Test
    void should_failStopPromise_when_lifeCycleFailsToStart(VertxTestContext vertxTestContext) {
        // GIVEN
        Exception exception = new Exception("oh no!");
        when(lifeCycle1.stop()).thenReturn(Future.failedFuture(exception));
        when(lifeCycle2.stop()).thenReturn(Future.succeededFuture());
        Promise<Void> promise = Promise.promise();

        // WHEN
        mainVerticle.stop(promise);

        // THEN
        promise.future()
                .onSuccess(ok -> vertxTestContext.failNow("Expected a failure but got a success instead"))
                .onFailure(throwable -> {
                    vertxTestContext.verify(() -> {
                        assertThat(throwable).isEqualTo(exception);
                        vertxTestContext.completeNow();
                    });
                });
    }
}