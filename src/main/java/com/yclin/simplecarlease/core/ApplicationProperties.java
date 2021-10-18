package com.yclin.simplecarlease.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LinYuchang
 */
@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    protected String apiVersion = "";

    protected String apiPrefix = "";

    protected String schemaLocation = "classpath:schema/*.sql";

    protected Long tokenExpireTime = 24 * 3600 * 1000L;

    protected Boolean enabledCors = true;
}
