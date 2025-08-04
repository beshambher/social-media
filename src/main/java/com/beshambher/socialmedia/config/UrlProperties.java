package com.beshambher.socialmedia.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("server.url")
public class UrlProperties {
    private String[] whitelist;
}
