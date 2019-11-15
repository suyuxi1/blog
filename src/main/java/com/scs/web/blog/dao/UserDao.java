package com.scs.web.blog.dao;

import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {



    /**
     * 批量新增用户
     * @param userList
     * @return int[]
     * @throws SQLException
     */
    int[] batckInsert(List<User> userList) throws SQLException;


    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;

//    /**
//     * 用户登录账号
//     * @param user
//     * @return user
//     * @throws SQLException
//     */
//    User sign (User user) throws  SQLException;
}
