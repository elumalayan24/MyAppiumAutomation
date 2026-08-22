package com.automation.config;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static volatile ConfigManager instance;
    private final EnvironmentConfig config;
    private final String environment;

    private ConfigManager() {
        this.environment = System.getenv().getOrDefault("TEST_ENV", "dev");
        logger.info("Loading configuration for environment: {}", environment);
        this.config = loadConfig();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private EnvironmentConfig loadConfig() {
        Gson gson = new Gson();

        // Try local env override first (from test resources)
        InputStream localStream = getClass().getClassLoader()
                .getResourceAsStream("environments/" + environment + ".json");
        if (localStream != null) {
            logger.info("Loading local environment config: environments/{}.json", environment);
            return gson.fromJson(new InputStreamReader(localStream, StandardCharsets.UTF_8), EnvironmentConfig.class);
        }

        // Fall back to shared config
        Path sharedConfig = Paths.get("../../shared/configs", environment + ".json");
        if (Files.exists(sharedConfig)) {
            logger.info("Loading shared config: {}", sharedConfig);
            try (var reader = Files.newBufferedReader(sharedConfig, StandardCharsets.UTF_8)) {
                return gson.fromJson(reader, EnvironmentConfig.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read shared config: " + sharedConfig, e);
            }
        }

        throw new RuntimeException("No configuration found for environment: " + environment);
    }

    public EnvironmentConfig getConfig() {
        return config;
    }

    public AppiumConfig getAppiumConfig() {
        return config.getAppium();
    }

    public EnvironmentConfig.AndroidConfig getAndroidConfig() {
        return config.getAndroid();
    }

    public EnvironmentConfig.TimeoutConfig getTimeoutConfig() {
        return config.getTimeouts();
    }

    public String getEnvironment() {
        return environment;
    }

    public static void reset() {
        instance = null;
    }
}
