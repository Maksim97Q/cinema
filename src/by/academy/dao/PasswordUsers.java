package by.academy.dao;

import by.academy.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUsers {

    public StringBuilder password(User user) {
        try {
            String str = user.getPassword();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
