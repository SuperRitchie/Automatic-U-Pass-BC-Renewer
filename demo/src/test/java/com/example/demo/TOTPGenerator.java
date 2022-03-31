package com.example.demo;

import org.jboss.aerogear.security.otp.Totp;

public class TOTPGenerator {
    /**
     * Method is used to get the TOTP based on the security token
     * @return
     */
    public static String getTwoFactorCode(){

        Totp totp = new Totp(System.getenv("UNI_MFA")); // 2FA secret key
        return totp.now();
    }

    public static String getTwoFactorCode(String key) {
        Totp totp = new Totp(key);
        return totp.now();
    }
}