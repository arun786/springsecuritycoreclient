package com.arun.springsecuritycoreclient.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author arun on 8/30/20
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("resource.server")
@Getter
@Setter
public class ResourceServerProperties {
    private String user;
    private String password;
}
