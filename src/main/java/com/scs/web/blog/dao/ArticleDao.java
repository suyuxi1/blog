package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {

    /**
     * 批量新增作品
     * @param AticleList
     * @return int[]
     * @throws SQLException
     */
    int[] batckInsert(List<Article> AticleList) throws SQLException;

    /**
     * 查找所有文章
     * @return List
     * @throws SQLException
     */
    List<Article> findAll() throws SQLException;

}
