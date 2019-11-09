package com.scs.web.blog.domain;

import lombok.Data;

/**
 * @author suyuxi
 * @className UserDto
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class UserDto {
    private String mobile;
    private String password;

    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }
    public UserDto(){

    }
}
