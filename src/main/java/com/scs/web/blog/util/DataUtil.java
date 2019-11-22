package com.scs.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author suyuxi
 * @className UserDataUtil
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class DataUtil {

    /**
     * 获取电话号码
     *
     * @return
     */
    public static String getMobile() {
        StringBuilder stringBuilder = new StringBuilder("139");
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public static String getPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    public static String getGender() {
        String[] genders = new String[]{"男", "女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }

    public static LocalDate getBirthday() {
//        LocalDate beginData = LocalDate.of(1988, 10, 24);
//        LocalDate endData = LocalDate.of(1998,2,25);

        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    public static int getUserID() {
        Random random = new Random();
        int userID = random.nextInt(73);
        return userID;
    }

    /**
     * 生成时间
     * @return
     */
    public static LocalDateTime getCreateTime(){
        LocalDateTime now = LocalDateTime.now();
        Random random = new Random();
        int bound = random.nextInt(999);
        return now.minusHours(bound);
    }

    public static String getNumberCode() {
        StringBuilder stringBuilder = new StringBuilder();
        int digitalBound = 10;
        int charBound = 26;
        char[] chars = new char[digitalBound+charBound*2];
        int index = 0;
        for (int i = 0; i< digitalBound; i++){
            chars[i] = (char) ('0' +index);
            index++;
        }
        index = 0;
        for (int i = digitalBound; i< charBound+digitalBound; i++){
            chars[i] = (char) ('A' +index);
            index++;
        }
        index = 0;
        for (int i = digitalBound+charBound; i< charBound*2+digitalBound; i++){
            chars[i] = (char) ('a' +index);
            index++;
        }

        Random random = new Random();
        for (int i = 0; i< 4; i++) {
            int num = random.nextInt(62);
            stringBuilder.append(chars[num]);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {

        for (int i=0 ; i<5; i++){
            System.out.println("生成的数字验证码为：" + DataUtil.getNumberCode());
        }



    }
}
