package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {



    /**
     * 新增用户
     * @param userDto
     * @return
     * @throws SQLException
     */
    int insert(UserDto userDto) throws SQLException;

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

    /**
     * 查询热门用户
     * @return
     * @throws SQLException
     */
    List<User> selectHotUsers()throws SQLException;

    /**
     * 查询分页用户
     *
     * @return
     * @throws SQLException
     */
    List<User> selectByPage(int currentPage, int count) throws SQLException;

    /**
     * 根据id查询用户
     * @param  id
     * @return
     * @throws SQLException
     */
    UserVo getUser(long id) throws SQLException;


    /**
     * 模糊搜索用户
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<User> selectByKeywords(String keywords) throws SQLException;

}
