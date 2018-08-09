package com.example.movieRecoSys.credential.jwt;

public class SecurityConstants {
    public static final String SECRET = "SecretJWTtokenKeYGen";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
