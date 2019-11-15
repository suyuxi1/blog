package com.scs.web.blog.factory;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.StudentDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.dao.impl.ArticleDaoImpl;
import com.scs.web.blog.dao.impl.StudentDaoImpl;
import com.scs.web.blog.dao.impl.UserDaoImpl;

/**
 * @author suyuxi
 * @className DaoFactory
 * @Description TODO
 * @Date 2019/11/6
 * @Version 1.0
 **/
public class DaoFactory {
    public static StudentDao getStudentDaoInstance(){
        return new StudentDaoImpl();
    }

    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }

    public static ArticleDao getArticleDaoInstance(){
        return new ArticleDaoImpl();
    }

}
