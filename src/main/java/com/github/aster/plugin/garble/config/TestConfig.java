package com.github.aster.plugin.garble.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.github.aster.print")
public class TestConfig {

    private String prefix;

    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


}
