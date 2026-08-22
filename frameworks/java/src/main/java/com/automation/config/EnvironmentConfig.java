package com.automation.config;

public class EnvironmentConfig {
    private AppiumConfig appium;
    private AndroidConfig android;
    private TimeoutConfig timeouts;

    public AppiumConfig getAppium() {
        return appium;
    }

    public void setAppium(AppiumConfig appium) {
        this.appium = appium;
    }

    public AndroidConfig getAndroid() {
        return android;
    }

    public void setAndroid(AndroidConfig android) {
        this.android = android;
    }

    public TimeoutConfig getTimeouts() {
        return timeouts;
    }

    public void setTimeouts(TimeoutConfig timeouts) {
        this.timeouts = timeouts;
    }

    public static class AndroidConfig {
        private String deviceName;
        private String platformVersion;
        private String automationName;
        private String appPath;
        private String appPackage;
        private String appActivity;
        private boolean noReset;
        private boolean fullReset;
        private boolean autoGrantPermissions;

        public String getDeviceName() { return deviceName; }
        public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

        public String getPlatformVersion() { return platformVersion; }
        public void setPlatformVersion(String platformVersion) { this.platformVersion = platformVersion; }

        public String getAutomationName() { return automationName; }
        public void setAutomationName(String automationName) { this.automationName = automationName; }

        public String getAppPath() { return appPath; }
        public void setAppPath(String appPath) { this.appPath = appPath; }

        public String getAppPackage() { return appPackage; }
        public void setAppPackage(String appPackage) { this.appPackage = appPackage; }

        public String getAppActivity() { return appActivity; }
        public void setAppActivity(String appActivity) { this.appActivity = appActivity; }

        public boolean isNoReset() { return noReset; }
        public void setNoReset(boolean noReset) { this.noReset = noReset; }

        public boolean isFullReset() { return fullReset; }
        public void setFullReset(boolean fullReset) { this.fullReset = fullReset; }

        public boolean isAutoGrantPermissions() { return autoGrantPermissions; }
        public void setAutoGrantPermissions(boolean autoGrantPermissions) { this.autoGrantPermissions = autoGrantPermissions; }
    }

    public static class TimeoutConfig {
        private int implicit;
        private int explicit;
        private int pageLoad;

        public int getImplicit() { return implicit; }
        public void setImplicit(int implicit) { this.implicit = implicit; }

        public int getExplicit() { return explicit; }
        public void setExplicit(int explicit) { this.explicit = explicit; }

        public int getPageLoad() { return pageLoad; }
        public void setPageLoad(int pageLoad) { this.pageLoad = pageLoad; }
    }
}
