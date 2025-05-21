package com.pfpj.sm;

public class SM4Utils {
    public static String encrypt(String data, String mode, String key, String iv) {
        return "encrypted(" + data + ")";
    }

    public static String decrypt(String data, String mode, String key, String iv) {
        return "decrypted(" + data + ")";
    }
}
