package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;

import java.util.Map;

public interface UserService {
    /**
     * 用户登录功能
     */
    Map<String, Object> signIn(UserDto userdto);
}
