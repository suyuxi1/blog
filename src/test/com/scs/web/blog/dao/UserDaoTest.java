package com.scs.web.blog.dao;



import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.List;


public class UserDaoTest {

   private  static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    @Test
    public void batckInsert(){
        try{
            int[] result = userDao.batckInsert(JSoupSpider.getUsers());
            if(result.length != 0){
                logger.info("成功新增" + result.length + "个用户");
            }
        }catch (SQLException e){
            logger.error("批量新增用户异常");
        }

    }


    @Test
    public void selectHotUsers() throws SQLException {
        List list = DaoFactory.getUserDaoInstance().selectHotUsers();
        System.out.println(list);
    }

    @Test
    public void selectByPage() throws SQLException {
        List list = DaoFactory.getUserDaoInstance().selectByPage(2,3);
        System.out.println(list);
    }

    @Test
    public void getUser() throws SQLException {
        UserVo  userVo = DaoFactory.getUserDaoInstance().getUser(25);
        System.out.println(userVo);
    }


    @Test
    public void selectByKeywords() throws SQLException {
        List<User> list= DaoFactory.getUserDaoInstance().selectByKeywords("老师");
        System.out.println(list);
    }
}