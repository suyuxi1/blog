package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Student;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class StudentDaoTest {

    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        int[] n = studentDao.batckInsert(JSoupSpider.getStudents());
        assertEquals(24,n.length);

    }

    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setUsername("用户三");
        student.setAvatar("1.jpg");
        student.setCreateTime(LocalDateTime.now());
        int n = studentDao.insert(student);
        assertEquals(1,n);
        System.out.println("受影响的行数："+n);


    }
}