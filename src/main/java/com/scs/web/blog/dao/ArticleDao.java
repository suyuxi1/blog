package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Article;

import com.scs.web.blog.domain.vo.ArticleVo;

import java.sql.SQLException;
import java.util.List;
public interface ArticleDao {
    /**
     * 批量新增文章
     *
     * @param articleList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Article> articleList) throws SQLException;
        /**
     * 查找所有文章
     * @return List
     * @throws SQLException
     */
    List<Article> findAll() throws SQLException;


    /**
     * 查询热门文章，返回视图集合
     *
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectHotArticles() throws SQLException;

    /**
     * 分页获得文章数据
     *
     * @param currentPage
     * @param pageCount
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByPage(int currentPage, int pageCount) throws SQLException;


    /**
     * 根据关键字模糊查询所有文章
     *
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByKeywords(String keywords) throws SQLException;


    /**
     * 根据专题id查询所有文章
     * @param topicId
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByTopicId(long topicId) throws SQLException;

    /**
     * 根据id获取文章详情
     *
     * @param id
     * @return
     * @throws SQLException
     */
    ArticleVo getArticle(long id) throws SQLException;
}



//public interface ArticleDao {
//
//    /**
//     * 批量新增作品
//     * @param aticleList
//     * @return int[]
//     * @throws SQLException
//     */
//    int[] batckInsert(List<Article> aticleList) throws SQLException;
//
//    /**
//     * 查找所有文章
//     * @return List
//     * @throws SQLException
//     */
//    List<Article> findAll() throws SQLException;
//
//
//    /**
//     * 查询热门文章，返回视图集合
//     *
//     * @return
//     * @throws SQLException
//     */
//    List<ArticleVo> selectHotArticles() throws SQLException;
//
//    /**
//     * 分页获得文章数据
//     *
//     * @param currentPage
//     * @param pageCount
//     * @return
//     * @throws SQLException
//     */
//    List<ArticleVo> selectByPage(int currentPage, int pageCount) throws SQLException;
//
//
//    /**
//     * 根据关键字模糊查询所有文章
//     *
//     * @param keywords
//     * @return
//     * @throws SQLException
//     */
//    List<ArticleVo> selectByKeywords(String keywords) throws SQLException;
//
//
//    /**
//     * 根据专题id查询所有文章
//     * @param topicId
//     * @return
//     * @throws SQLException
//     */
//    List<ArticleVo> selectByTopicId(long topicId) throws SQLException;
//
//    /**
//     * 根据id获取文章详情
//     *
//     * @param id
//     * @return
//     * @throws SQLException
//     */
//    ArticleVo getArticle(long id) throws SQLException;
//
//}
