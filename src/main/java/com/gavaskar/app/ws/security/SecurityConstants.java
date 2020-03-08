package com.gavaskar.app.ws.security;

import com.gavaskar.app.ws.SpringApplicationContext;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(10);
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }
}

