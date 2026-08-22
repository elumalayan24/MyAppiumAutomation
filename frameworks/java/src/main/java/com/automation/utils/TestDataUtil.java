package com.automation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(TestDataUtil.class);
    private static final Gson gson = new Gson();
    private static final Map<String, JsonObject> cache = new ConcurrentHashMap<>();

    private TestDataUtil() {
    }

    public static JsonObject loadTestData(String fileName) {
        return cache.computeIfAbsent(fileName, TestDataUtil::readJsonFile);
    }

    public static <T> T loadTestData(String fileName, String key, Class<T> clazz) {
        JsonObject data = loadTestData(fileName);
        JsonElement element = data.get(key);
        if (element == null) {
            throw new RuntimeException("Key '" + key + "' not found in " + fileName);
        }
        return gson.fromJson(element, clazz);
    }

    public static <T> T loadTestData(String fileName, Class<T> clazz) {
        JsonObject data = loadTestData(fileName);
        return gson.fromJson(data, clazz);
    }

    private static JsonObject readJsonFile(String fileName) {
        // Try classpath first
        var stream = TestDataUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (stream != null) {
            logger.info("Loading test data from classpath: {}", fileName);
            return JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
        }

        // Fall back to shared test-data directory
        Path sharedPath = Paths.get("../../shared/test-data", fileName);
        if (Files.exists(sharedPath)) {
            logger.info("Loading test data from shared: {}", sharedPath);
            try (var reader = Files.newBufferedReader(sharedPath, StandardCharsets.UTF_8)) {
                return JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException e) {
                throw new RuntimeException("Failed to read test data: " + sharedPath, e);
            }
        }

        throw new RuntimeException("Test data file not found: " + fileName);
    }

    public static void clearCache() {
        cache.clear();
        logger.info("Test data cache cleared");
    }
}
