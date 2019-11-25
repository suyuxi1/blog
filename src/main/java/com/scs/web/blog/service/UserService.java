package com.scs.web.blog.service;

//import com.scs.web.blog.domain.dto.UserDto;

import java.sql.SQLException;
import java.util.Map;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.util.Result;

public interface UserService {

    /**
     * 用户登录功能
     *
     * @param userDto
     * @return
     */
    Result signIn(UserDto userDto);

    /**
     * 获取热门用户信息
     * @return
     */
    Result getHotUsers();

    /**
     * 获取分页用户信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage,int count);

    /**
     * 根据id查询用户详情数据
     * @param id
     * @return
     */
    Result getUser(long id);

    /**
     * 根据昵称或简介模糊搜索用户
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);
//    /**
//     * 用户登录功能
//     */
//    Map<String, Object> signIn(UserDto userdto);
//
//
    /**
     * 用户注册
     */
    Map<String, Object> insertIn(UserDto userDto) throws SQLException;


}
