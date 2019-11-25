package com.scs.web.blog.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author mq_xu
 * @ClassName User
 * @Description 用户实体类
 * @Date 9:47 2019/11/9
 * @Version 1.0
 **/
@Data
public class User {
    private Long userId;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String introduction;
    private String banner;
    private String email;
    private String homepage;
    private Integer follows;
    private Integer fans;
    private Integer articles;
    private LocalDateTime createTime;
    private Short status;
}
