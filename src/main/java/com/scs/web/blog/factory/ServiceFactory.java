package com.scs.web.blog.factory;

import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.service.StudentService;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.service.impl.ArticleServiceImpl;
import com.scs.web.blog.service.impl.StudentServiceImpl;
import com.scs.web.blog.service.impl.UserServiceImpl;

/**
 * @author suyuxi
 * @className ServiceFactory
 * @Description TODO
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {
    public static StudentService getStudentServiceInstance(){
        return new StudentServiceImpl();
    }

    public static UserService getUserServiceInstance(){
        return (UserService) new UserServiceImpl();
    }

    public static ArticleService getArticleServiceInstance(){
        return new ArticleServiceImpl();

    }
}
