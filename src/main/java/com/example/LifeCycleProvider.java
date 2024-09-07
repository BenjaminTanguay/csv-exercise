package com.example;

import jakarta.inject.Inject;
import lombok.Getter;

import java.util.Set;

public class LifeCycleProvider {

    @Getter
    private final Set<LifeCycle> lifeCycles;

    @Inject
    public LifeCycleProvider(Set<LifeCycle> lifeCycles) {
        this.lifeCycles = lifeCycles;
    }

}
