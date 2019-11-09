package com.scs.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author suyuxi
 * @className UserDataUtil
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserDataUtil {

    /**
     * 获取电话号码
     * @return
     */
    public static String getMobile(){
        StringBuilder stringBuilder = new StringBuilder("139");
        Random random = new Random();
        for (int i = 0; i < 8; i++){
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }
    public static String getPassword(){
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    public static String getGender(){
        String[] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }

    public static LocalDate getBirthday(){
//        LocalDate beginData = LocalDate.of(1988, 10, 24);
//        LocalDate endData = LocalDate.of(1998,2,25);

        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println(UserDataUtil.getBirthday());
        }

    }
}
