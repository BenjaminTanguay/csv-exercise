package com.example.dao;

import com.example.LifeCycle;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;

public class DaoModule extends AbstractModule {

    @ProvidesIntoSet
    public LifeCycle provideDaoLifeCycle(CsvDao csvDao) {
        return csvDao;
    }

    @Provides
    @Singleton
    String providesCsvPath() {
        return "./conf/input.csv";
    }

    @Provides
    @Singleton
    CsvMapper providesCsvMapper() {
        return new CsvMapper();
    }

    @Provides
    @Singleton
    Dao providesDao(CsvDao csvDao) {
        return csvDao;
    }

    @Provides
    @Singleton
    FileSystem providesFileSystem(Vertx vertx) {
        return vertx.fileSystem();
    }
}