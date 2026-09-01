package com.automation.driver;

import com.automation.config.ConfigManager;
import com.automation.config.EnvironmentConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static AndroidDriver createDriver() {
        ConfigManager configManager = ConfigManager.getInstance();
        EnvironmentConfig.AndroidConfig androidConfig = configManager.getAndroidConfig();
        var timeoutConfig = configManager.getTimeoutConfig();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(androidConfig.getDeviceName())
                .setPlatformVersion(androidConfig.getPlatformVersion())
                .setAutomationName(androidConfig.getAutomationName())
                .setAppPackage(androidConfig.getAppPackage())
                .setAppActivity(androidConfig.getAppActivity())
                .setNoReset(androidConfig.isNoReset())
                .setFullReset(androidConfig.isFullReset())
                .setAutoGrantPermissions(androidConfig.isAutoGrantPermissions());

        // Set app path if configured
        String appPath = androidConfig.getAppPath();
        if (appPath != null && !appPath.isEmpty()) {
            Path resolvedPath = Paths.get(appPath).toAbsolutePath();
            if (resolvedPath.toFile().exists()) {
                options.setApp(resolvedPath.toString());
                logger.info("App path set to: {}", resolvedPath);
            } else {
                logger.warn("App file not found at: {}. Skipping app installation.", resolvedPath);
            }
        }

        String serverUrl = configManager.getAppiumConfig().getServerUrl();
        logger.info("Creating AndroidDriver with server URL: {}", serverUrl);
        logger.info("Device: {}, Platform: {}", androidConfig.getDeviceName(), androidConfig.getPlatformVersion());

        verifyServerReachable(serverUrl);

        try {
            AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutConfig.getImplicit()));
            logger.info("AndroidDriver created successfully");
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + serverUrl, e);
        }
    }

    private static void verifyServerReachable(String serverUrl) {
        String statusUrl = serverUrl + "/status";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(statusUrl).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            connection.disconnect();
            if (code != 200) {
                throw new RuntimeException(
                        "Appium server returned HTTP " + code + " at " + statusUrl
                                + ". Check that the basePath in your environment config matches the --base-path "
                                + "the server was started with.");
            }
            logger.info("Appium server is reachable at {}", statusUrl);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Cannot reach Appium server at " + statusUrl
                            + ". Ensure the server is running and the host/port/basePath in your environment "
                            + "config are correct.", e);
        }
    }
}
