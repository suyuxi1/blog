package com.scs.web.blog.service;

import com.scs.web.blog.entity.Student;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {

    private StudentService studentService = ServiceFactory.getStudentServiceInstance();
    @Test
    public void listStudent() {
        List<Student> studentList = studentService.listStudent();
        studentList.forEach(System.out::println);
    }
}