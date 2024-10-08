package com.example.dao;

import com.example.LifeCycle;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Singleton
public class CsvDao implements LifeCycle, Dao {

    private final String csvPath;
    private final FileSystem fileSystem;
    private final CsvMapper csvMapper;

    private List<Map<String, String>> csv;

    @Inject
    public CsvDao(String csvPath, FileSystem fileSystem, CsvMapper csvMapper) {
        this.csvPath = csvPath;
        this.fileSystem = fileSystem;
        this.csvMapper = csvMapper;
    }

    @Override
    public Future<Void> start() {
        Promise<Void> promise = Promise.promise();

        fileSystem.readFile(csvPath)
                .map(Buffer::toString)
                .map(csvContent -> {
                    CsvSchema schema = CsvSchema.emptySchema().withHeader();

                    try (MappingIterator<Map<String, String>> it = csvMapper.readerFor(Map.class)
                            .with(schema)
                            .readValues(csvContent)) {

                        return it.readAll();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .onSuccess(csv -> {
                    this.csv = csv;
                    log.info("Csv loaded successfully");
                    promise.complete();
                })
                .onFailure(throwable -> {
                    log.error("Couldn't load csv", throwable);
                    promise.fail(throwable);
                });

        return promise.future();
    }

    @Override
    public Future<Void> stop() {
        return Future.succeededFuture();
    }

    public boolean isCsvLoaded() {
        return csv != null;
    }

    public int lines() {
        return csv.size();
    }

    @Override
    public List<Map<String, String>> filterRecords(Map<String, String> filters) {

        return this.csv.stream()
                .filter(map -> filters.entrySet().stream().allMatch(filter -> map.containsKey(filter.getKey()) && map.get(filter.getKey()).equals(filter.getValue())))
                .toList();

    }
}
