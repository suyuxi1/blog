package com.scs.web.blog.util;


import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author suyuxi
 * @className StringUtil
 * @Description T字符串工具类
 * @Date 2019/11/22
 * @Version 1.0
 **/
public class StringUtil {
    /**
     * 提取字符串中的数字字符为一个字符串数组
     *
     * @param sourceStr
     * @return
     */
    public static String[] getDigital(String sourceStr) {
        String[] result = new String[sourceStr.length()];
        //这个2是指连续数字的最少个数
        Pattern p = Pattern.compile("\\d{2,}");
        Matcher m = p.matcher(sourceStr);
        int i = 0;
        while (m.find()) {
            result[i] = m.group();
            i++;
        }
        return result;
    }

    public static String getRandomCode() {
        //设置字符
        char[] chars = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        //设置随机数
        Random random = new Random();
        // 获取4位随机数
        StringBuilder stringBuilder = new StringBuilder();
        int index;
        for (int i = 0; i < 4; i++) {
            //获取随机chars下标
            index = random.nextInt(chars.length);
            stringBuilder.append(chars[index]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        Pattern p = Pattern.compile("\\d{2,}");//这个2是指连续数字的最少个数
//        String u = "收录了 27093 篇文章，35936 人关注";
//        Matcher m = p.matcher(u);
//        int i = 0;
//        while (m.find()) {
//            System.out.println(m.group());
//            i++;
//        }
    }
}