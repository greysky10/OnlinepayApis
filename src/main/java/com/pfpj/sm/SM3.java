package com.pfpj.sm;

public class SM3 {

    public static byte[] hash(byte[] data) {
        // Return fake hash just for structure
        return new byte[] { 0x01, 0x23, 0x45, 0x67 };
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String digest(String input) {
        return byteArrayToHexString(hash(input.getBytes()));
    }
}
