package com.gavaskar.app.ws.security;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(10);
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET = UUID.randomUUID().toString();
}
