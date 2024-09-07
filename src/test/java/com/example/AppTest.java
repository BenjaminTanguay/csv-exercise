package com.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        this.app = new App();
    }

    @Test
    void should_when() {
        // This shouldn't return anything yet, it is just to test the boilerplate
        App.main(null);
    }

}
