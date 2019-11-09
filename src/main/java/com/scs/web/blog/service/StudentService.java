package com.scs.web.blog.service;

import com.scs.web.blog.entity.Student;

import java.util.List;

public interface StudentService {
    /**
     * 获取所有学生
     * @return
     */
    List<Student> listStudent();

}
