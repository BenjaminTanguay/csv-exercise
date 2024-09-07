package com.example.dao;

import com.example.LifeCycle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CvsDao implements LifeCycle {

    private final String csvPath;
    private final FileSystem fileSystem;

    private String csv;

    @Inject
    public CvsDao(String csvPath, FileSystem fileSystem) {
        this.csvPath = csvPath;
        this.fileSystem = fileSystem;
    }

    @Override
    public Future<Void> start() {
        Promise<Void> promise = Promise.promise();

        fileSystem.readFile(csvPath)
                .map(Buffer::toString)
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
}
