package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    /**
     * 新增学生
     * @param student
     * @return int
     * @throws SQLException
     */
    int insert(Student student) throws SQLException;


    /**
     * 批量新增学生
     * @param studentList
     * @return int[]
     * @throws SQLException
     */
    int[] batckInsert(List<Student> studentList) throws SQLException;

    /**
     *查询所有学生
     * @return
     */
    List<Student> selectAll() throws SQLException;

}
