package com.swissre.exercise;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class ApplicationProperties {
    private final Properties properties;

    public ApplicationProperties(Path propertiesFile) {
        properties = new Properties();
        try (InputStream props = Files.newInputStream(propertiesFile, StandardOpenOption.READ)) {
            properties.load(props);
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file", e);
        }
    }
    public int getNumberOfHeadersToSkip() {
        return Integer.parseInt(properties.getProperty("csv.skip.num.headers"));
    }

    public double getAboveSalaryBoundary() {
        return Double.parseDouble(properties.getProperty("salary.percentage.max"));
    }

    public double getBelowSalaryBoundary() {
        return Double.parseDouble(properties.getProperty("salary.percentage.min"));
    }

    public int getMaxTreeLevel() {
        return Integer.parseInt(properties.getProperty("tree.level.max"));
    }
}
