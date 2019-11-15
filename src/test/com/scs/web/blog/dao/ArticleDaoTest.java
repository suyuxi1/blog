package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


public class ArticleDaoTest {

    private static Logger logger = LoggerFactory.getLogger(ArticleDao.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    @Test
    public void batckInsert() throws SQLException {
        try{
            int[] result = articleDao.batckInsert(JSoupSpider.getArticles());
            if(result.length != 0){
                logger.info("成功新添" + result.length + "篇文章");
            }
        }catch (SQLException e){
            logger.error("批量新增文章异常");
        }


    }

    @Test
    public void findAll() throws SQLException {
        try{
            List<Article> articleList ;
            articleList = articleDao.findAll();
            if (articleList != null){
                System.out.println("查找数据成功");
            }
        }catch (SQLException e){
            logger.error("查询数据库数据异常");
        }



    }
}