package com.automation.driver;

import com.automation.config.AppiumConfig;
import com.automation.config.ConfigManager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public class AppiumServerManager {

    private static final Logger logger = LoggerFactory.getLogger(AppiumServerManager.class);
    private static AppiumDriverLocalService service;

    private AppiumServerManager() {
    }

    public static synchronized void startServer() {
        AppiumConfig appiumConfig = ConfigManager.getInstance().getAppiumConfig();

        if (isServerRunning(appiumConfig)) {
            logger.info("Appium server already running at {}", appiumConfig.getServerUrl());
            return;
        }

        logger.info("Starting Appium server on port {}", appiumConfig.getPort());
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(appiumConfig.getHost())
                .usingPort(appiumConfig.getPort())
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withTimeout(Duration.ofSeconds(60));

        String basePath = appiumConfig.getBasePath();
        if (basePath != null && !basePath.isEmpty()) {
            builder.withArgument(GeneralServerFlag.BASEPATH, basePath);
        }

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        logger.info("Appium server started at {}", service.getUrl());
    }

    public static synchronized void stopServer() {
        if (service != null && service.isRunning()) {
            logger.info("Stopping Appium server");
            service.stop();
            service = null;
            logger.info("Appium server stopped");
        }
    }

    private static boolean isServerRunning(AppiumConfig config) {
        try {
            URL statusUrl = new URL(config.getServerUrl() + "/status");
            HttpURLConnection connection = (HttpURLConnection) statusUrl.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isRunning() {
        return service != null && service.isRunning();
    }
}
