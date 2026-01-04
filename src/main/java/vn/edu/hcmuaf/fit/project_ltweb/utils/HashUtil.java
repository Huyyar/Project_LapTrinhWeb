package vn.edu.hcmuaf.fit.project_ltweb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
        public String passwordHash(String password ){
            try {
                MessageDigest md = MessageDigest.getInstance("MD5") ;
                byte[] messageDigest = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : messageDigest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
        return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Lỗi thuật toán băm: " + e.getMessage());
            }
        }

}
