package com.ledao.util;

/**
 * @author LeDao
 * @company
 * @create 2021-01-14 3:35
 */
public class MyEncryption {

    public static void main(String[] args) {
        String str = "111111";
        System.out.println(jiami(str));
    }


    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String jiami(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (c == 'z') {
                charArr[i] = 'A';
            } else if (c == 'Z') {
                charArr[i] = 'a';
            } else if (c == '9') {
                charArr[i] = '0';
            } else if (c >= 'a' && c < 'z') {
                int num = c + 1;
                char c2 = (char) num;
                charArr[i] = Character.toUpperCase(c2);
            } else if (c >= 'A' && c < 'Z') {
                int num = c + 1;
                char c2 = (char) num;
                charArr[i] = Character.toLowerCase(c2);
            } else if (c >= '0' && c < '9') {
                int num = c + 1;
                charArr[i] = (char) num;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : charArr) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String jiemi(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (c == 'a') {
                charArr[i] = 'Z';
            } else if (c == 'A') {
                charArr[i] = 'z';
            } else if (c == '0') {
                charArr[i] = '9';
            } else if (c > 'a' && c <= 'z') {
                int num = c - 1;
                char c2 = (char) num;
                charArr[i] = Character.toUpperCase(c2);
            } else if (c > 'A' && c <= 'Z') {
                int num = c - 1;
                char c2 = (char) num;
                charArr[i] = Character.toLowerCase(c2);
            } else if (c > '0' && c <= '9') {
                int num = c - 1;
                charArr[i] = (char) num;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : charArr) {
            sb.append(c);
        }
        return sb.toString();
    }
}
