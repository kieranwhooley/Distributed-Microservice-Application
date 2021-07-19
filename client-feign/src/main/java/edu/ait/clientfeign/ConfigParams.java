package edu.ait.clientfeign;

public class ConfigParams {

    private String configName;
    private String configInfo;

    public ConfigParams(String configName, String configInfo) {
        this.configName = configName;
        this.configInfo = configInfo;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(String configInfo) {
        this.configInfo = configInfo;
    }
}
