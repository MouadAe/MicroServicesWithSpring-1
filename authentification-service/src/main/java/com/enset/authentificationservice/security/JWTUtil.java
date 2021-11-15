package com.enset.authentificationservice.security;

public class JWTUtil {
    public static final String SECRET = "mySecretKey";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 162*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 10*24*3600*1000;
}
