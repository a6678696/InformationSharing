package com.ledao.util;

/**
 * 字符串加解密(牛客网华为机试题目)
 * 题目描述
 * 1、对输入的字符串进行加解密，并输出。
 * <p>
 * 2、加密方法为：
 * <p>
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 * <p>
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 * <p>
 * 其他字符不做变化。
 * <p>
 * 3、解密方法为加密的逆过程。
 * <p>
 * <p>
 * 本题含有多组样例输入。
 *
 * @author LeDao
 * @company
 * @create 2021-01-14 3:35
 */
public class MyEncryption {

    public static void main(String[] args) {
        String str = "111";
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
