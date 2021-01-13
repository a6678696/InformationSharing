package com.ledao.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author LeDao
 * @company
 * @create 2021-01-13 5:24
 */
public class Md5 {

    public static final String KEY_MD5 = "MD5";

    public static String getResult(String inputStr) {

        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bigInteger.toString(16);
    }

    public static void main(String[] args) {
        try {
            String inputStr = "666666";
            System.out.println("加密前:"+inputStr);
            System.out.println("MD5加密后:" + getResult(inputStr));
            getResult(inputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
