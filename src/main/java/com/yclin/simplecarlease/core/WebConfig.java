package com.yclin.simplecarlease.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LinYuchang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class WebConfig implements WebMvcConfigurer {

    protected final ApplicationProperties properties;

    public WebConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        String prefix = properties.getApiPrefix() + properties.getApiVersion();
        configurer.addPathPrefix(prefix, c -> c.isAnnotationPresent(CarLeaseController.class));
        log.info("configured api prefix as: " + prefix);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor());
        log.info("added AccessInterceptor");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (properties.getEnabledCors()) {
            registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedOrigins("*")
                    .allowedMethods("*");
        }
    }
}
