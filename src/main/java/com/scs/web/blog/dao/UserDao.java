package com.scs.web.blog.dao;

import com.scs.web.blog.entity.User;
import lombok.Data;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {
    /**
     * 新增用户
     * @param  user
     * @return int[]
     * @throws SQLException
     */
    int insert(User user) throws SQLException;


    /**
     * 批量新增学生
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
}
