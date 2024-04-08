package com.swissre.exercise.service.impl;

import com.swissre.exercise.ApplicationProperties;

import java.nio.file.Path;

public class ApplicationPropertiesFactory {
    public static ApplicationProperties forTests() {
        return new ApplicationProperties(Path.of("src/main/resources/application.properties"));
    }
}
