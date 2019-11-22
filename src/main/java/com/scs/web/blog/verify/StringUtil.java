package com.scs.web.blog.verify;


import java.util.Random;
/**
 * @author suyuxi
 * @className StringUtil
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
public class StringUtil {
    final static int MAX = 4;
    static char c;

    public static String getRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int index;
        char c;
        String[] choice = {"数字", "大写字母", "小写字母"};
        for (int i = 0; i < MAX; i++) {
            index = random.nextInt(3);
            switch (index) {
                case 0:
                case 1:
                case 2:
                    char result = getChar(index);
                    stringBuilder.append(result);
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static char getChar(int item) {

        int digitalBound = 10;
        int charBound = 26;
        Random random = new Random();
        int index;
        if (item == 0) {
            index = random.nextInt(digitalBound);
            c = (char) ('0' + index);
        } else if (item == 1) {
            index = random.nextInt(charBound);
            c = (char) ('A' + index);
        } else {
            index = random.nextInt(charBound);
            c = (char) ('a' + index);
        }
        return c;
    }

    public static void main(String[] args) {
        for(int i = 0;i<MAX;i++){

            System.out.println(StringUtil.getRandomString());
        }
    }
}