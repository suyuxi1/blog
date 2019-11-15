package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suyuxi
 * @className ArticleDaoImpl
 * @Description TODO
 * @Date 2019/11/10
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public int[] batckInsert(List<Article> AticleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_douban (user_id,picture,avatar,author,create_time,title,content,likes,noLikes,reply) VALUES (?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        AticleList.forEach(article -> {
            try {
                pstmt.setLong(1,article.getUserID());
                pstmt.setString(2, article.getPicture());
                pstmt.setString(3, article.getAvatar());
                pstmt.setString(4, article.getAuthor());
                pstmt.setObject(5, article.getCreate_time());
                pstmt.setString(6, article.getTitle());
                pstmt.setString(7, article.getContent());
                pstmt.setString(8, article.getLikes());
                pstmt.setString(9, article.getNoLikes());
                pstmt.setString(10, article.getReply());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        pstmt.close();
        connection.close();
        return result;
    }

    @Override
    public List<Article> findAll() throws SQLException {
        List<Article> articleList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM t_douban ORDER BY id DESC " ;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setUserID(rs.getLong("user_id"));
            article.setPicture(rs.getString("picture"));
            article.setAvatar(rs.getString("avatar"));
            article.setAuthor(rs.getString("author"));
            article.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setLikes(rs.getString("likes"));
            article.setNoLikes(rs.getString("noLikes"));
            article.setReply(rs.getString("reply"));
            articleList.add(article);

        }
        connection.commit();
        stmt.close();
        connection.close();
        return articleList;
    }


}
