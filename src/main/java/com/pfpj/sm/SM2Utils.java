package com.pfpj.sm;

public class SM2Utils {
    public String encrypt(String publicKey, String data) {
        return "encrypted(" + data + ")";
    }

    public String decrypt(String privateKey, String encryptedData) {
        return "decrypted(" + encryptedData + ")";
    }

    public Signature sign(String merchantId, String privateKey, String data, String publicKey) {
        return new Signature();
    }

    public boolean verifySign(String merchantId, String sopPublicKey, String data, Signature signature) {
        return true; // Always passes for mock
    }
}