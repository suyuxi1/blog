package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.entity.Student;

import com.scs.web.blog.util.DbUtil;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyuxi
 * @className StudentDaoImpl
 * @Description TODO
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class StudentDaoImpl implements StudentDao {
    @Override
    public int insert(Student student) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, student.getUsername());
        pstmt.setString(2, student.getAvatar());
        pstmt.setTimestamp(3, Timestamp.valueOf(student.getCreateTime()));
        int n = pstmt.executeUpdate();
        connection.commit();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int[] batckInsert(List<Student> studentList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_student VALUES (NULL,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        studentList.forEach(student -> {
            try{
                pstmt.setString(1,student.getUsername());
                pstmt.setString(2,student.getAvatar());
                pstmt.setString(3,student.getDescription());
                pstmt.setTimestamp(4,Timestamp.valueOf(student.getCreateTime()));
                //预处理事务级添加
                pstmt.addBatch();
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        //提交
        connection.commit();
        //关闭操作
        pstmt.close();
        connection.close();
        return result;
    }

    @Override
    public List<Student> selectAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_student ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setUsername(rs.getString("username"));
            student.setAvatar(rs.getString("avatar"));
            student.setDescription(rs.getString("description"));
            Timestamp timestamp = rs.getTimestamp("create_time");
            student.setCreateTime(timestamp.toLocalDateTime());
            studentList.add(student);
        }
        return studentList;
    }
}
