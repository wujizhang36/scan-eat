package com.snowflake.basic.modules.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RsaKeyConfig {

    @Value("${bully.rsa.key.public}")
    private String publicKey;

    @Value("${bully.rsa.key.private}")
    private String privateKey;

    public static String PUBLIC_KEY;
    public static String PRIVATE_KEY;

    @PostConstruct
    public void init() {
        PUBLIC_KEY = publicKey;
        PRIVATE_KEY = privateKey;
    }
}

