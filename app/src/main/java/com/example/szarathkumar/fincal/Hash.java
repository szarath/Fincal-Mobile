package com.example.szarathkumar.fincal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Szarath on 2016/09/05.
 */
public  class Hash {

    public static String Hash(String s)
    {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes());
            byte byteData[] = md.digest();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
