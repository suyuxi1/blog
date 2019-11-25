package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


public class ArticleDaoTest {

    private static Logger logger = LoggerFactory.getLogger(ArticleDao.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
//    @Test
//    public void batckInsert() throws SQLException {
//        try{
//            int[] result = articleDao.batckInsert(JSoupSpider.getArticles());
//            if(result.length != 0){
//                logger.info("成功新添" + result.length + "篇文章");
//            }
//        }catch (SQLException e){
//            logger.error("批量新增文章异常");
//        }
//
//
//    }
//
   @Test
    public void findAll(){
        try{
            List<ArticleVo> articleVoList ;
            articleVoList = articleDao.findAll();
            if (articleVoList != null){
                System.out.println("查找数据成功");
                for(int i=0; i<10 ;i++){
                    ArticleVo articleVo = articleVoList.get(i);
                    System.out.println("用户id："+articleVo.getAuthor().getUserId());
                    System.out.println("内容："+articleVo.getArticle().getContent());
                }
            }
        }catch (SQLException e){
            logger.error("查询数据库数据异常");
            e.printStackTrace();
        }



    }


}