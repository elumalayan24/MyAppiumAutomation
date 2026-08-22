package com.automation.config;

public class AppiumConfig {
    private String host;
    private int port;
    private String basePath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getServerUrl() {
        return String.format("http://%s:%d%s", host, port, basePath);
    }
}
