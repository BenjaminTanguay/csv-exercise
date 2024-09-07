package com.example.dao;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(VertxExtension.class)
public class CsvDaoTest {

    private static final String FILE_NAME = "BoulderTrailHeads.csv";

    private CvsDao csvDao;


    @BeforeEach
    void setUp(Vertx vertx) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(FILE_NAME);
        String csvPath = Paths.get(resource.toURI()).toString();
        this.csvDao = new CvsDao(csvPath, vertx.fileSystem());
    }

    @Test
    void should_returnSuccess_when_started(VertxTestContext vertxTestContext) {
        // GIVEN

        // WHEN
        Future<Void> future = csvDao.start();

        // THEN
        future
                .onSuccess(ok -> vertxTestContext.verify(() -> {
                    assertThat(csvDao.isCsvLoaded()).isTrue();
                    vertxTestContext.completeNow();
                }))
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

    @Test
    void should_filterFields_when_filterMethodCalled(VertxTestContext vertxTestContext) {
        // GIVEN
        Map<String, String> filters = new HashMap<>();
        csvDao.start()

        // WHEN
                .map(ok -> csvDao.filterRecords(filters))

        // THEN
                .onSuccess(ok -> vertxTestContext.completeNow())
                .onFailure(vertxTestContext::failNow);

    }

}
