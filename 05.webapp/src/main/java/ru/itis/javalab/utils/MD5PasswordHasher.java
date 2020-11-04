package ru.itis.javalab.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordHasher {

    public static String getHashedPassword(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        md.update(password.getBytes());

        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
