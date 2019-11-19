package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {
    /**
     * 用户登录功能
     */
    Map<String, Object> signIn(UserDto userdto);


    /**
     * 用户注册
     */
    Map<String, Object> insertIn(UserDto userDto) throws SQLException;


}
