package com.example.dao;

import com.example.LifeCycle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class CsvDaoSmallTest {

    private static final String CSV_PATH = "classpath:BoulderTrailHeads.csv";

    private LifeCycle csvDao;


    @BeforeEach
    void setUp(Vertx vertx) {
        this.csvDao = new CvsDao(CSV_PATH, vertx);
    }

    @Test
    void should_returnSuccess_when_started(VertxTestContext vertxTestContext) {
        // GIVEN

        // WHEN
        Future<Void> future = csvDao.start();

        // THEN
        future
                .onSuccess(ok -> vertxTestContext.completeNow())
                .onFailure(vertxTestContext::failNow);
    }


    @Test
    void should_returnSuccess_when_stopped(VertxTestContext vertxTestContext) {
        // GIVEN

        // WHEN
        Future<Void> future = csvDao.stop();

        // THEN
        future
                .onSuccess(ok -> vertxTestContext.completeNow())
                .onFailure(vertxTestContext::failNow);
    }

}
