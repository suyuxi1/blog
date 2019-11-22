package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_article (user_id,title,summary,thumbnail,content,likes,comments,create_time) VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pst.setLong(1, article.getUserId());
//                pst.setLong(2, article.getTopicId());
                pst.setString(2, article.getTitle());
                pst.setString(3, article.getSummary());
                pst.setString(4, article.getThumbnail());
                pst.setString(5, article.getContent());
                pst.setInt(6, article.getLikes());
                pst.setInt(7, article.getComments());
                pst.setObject(8, article.getCreateTime());
                pst.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        int[] result = pst.executeBatch();
        connection.commit();
//        DbUtil.close(connection, pst);
        return result;
    }




    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.id,a.user_id,a.topic_id,a.title,a.summary,a.thumbnail,a.comments,a.likes,a.create_time," +
                "b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "ORDER BY a.comments DESC " +
                "LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        //调用封装的方法，将结果集解析成List
        List<ArticleVo> articleVoList = convert(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVoList;
    }

    @Override
    public List<ArticleVo> selectByPage(int currentPage, int pageCount) throws SQLException {
        return null;
    }


    @Override
    public List<ArticleVo> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.title LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = convert(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByTopicId(long topicId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, topicId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = convert(rs);
//        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        ArticleVo articleVo = convert(rs).get(0);
        rs.previous();
        articleVo.setContent(rs.getString("content"));
//        DbUtil.close(connection, pst, rs);
        return articleVo;
    }

    private List<ArticleVo> convert(ResultSet rs) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticleVo articleVo = new ArticleVo();
                articleVo.setId(rs.getLong("id"));
                articleVo.setUserId(rs.getLong("user_id"));
                articleVo.setTopicId(rs.getLong("topic_id"));
                articleVo.setTitle(rs.getString("title"));
                articleVo.setThumbnail(rs.getString("thumbnail"));
                articleVo.setSummary(rs.getString("summary"));
                articleVo.setLikes(rs.getInt("likes"));
                articleVo.setComments(rs.getInt("comments"));
                articleVo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                articleVo.setNickname(rs.getString("nickname"));
                articleVo.setAvatar(rs.getString("avatar"));
                articleVo.setTopicName(rs.getString("topic_name"));
                articleVo.setLogo(rs.getString("logo"));
                articleVoList.add(articleVo);
            }
        } catch (SQLException e) {
            logger.error("文章数据结果集解析异常");
        }
        return articleVoList;
    }
}


//public class ArticleDaoImpl implements ArticleDao {
//    @Override
//    public int[] batckInsert(List<Article> AticleList) throws SQLException {
//        Connection connection = DbUtil.getConnection();
//        connection.setAutoCommit(false);
//        String sql = "INSERT INTO t_article (user_id,picture,avatar,author,create_time,title,content,likes,noLikes,reply,introduction) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        AticleList.forEach(article -> {
//            try {
//                pstmt.setLong(1,article.getUserID());
//                pstmt.setString(2, article.getPicture());
//                pstmt.setString(3, article.getAvatar());
//                pstmt.setString(4, article.getAuthor());
//                pstmt.setObject(5, article.getCreate_time());
//                pstmt.setString(6, article.getTitle());
//                pstmt.setString(7, article.getContent());
//                pstmt.setString(8, article.getLikes());
//                pstmt.setString(9, article.getNoLikes());
//                pstmt.setString(10, article.getReply());
//                pstmt.setString(11, article.getIntroduction());
//                pstmt.addBatch();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
//        int[] result = pstmt.executeBatch();
//        connection.commit();
//        pstmt.close();
//        connection.close();
//        return result;
//    }
//
//    @Override
//    public List<Article> findAll() throws SQLException {
//        List<Article> articleList = new ArrayList<>();
//        Connection connection = DbUtil.getConnection();
//        connection.setAutoCommit(false);
//        String sql = "SELECT * FROM t_article" ;
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);
//        while(rs.next()){
//            Article article = new Article();
//            article.setId(rs.getLong("id"));
//            article.setUserID(rs.getLong("user_id"));
//            article.setPicture(rs.getString("picture"));
//            article.setAvatar(rs.getString("avatar"));
//            article.setAuthor(rs.getString("author"));
//            article.setCreate_time(rs.getTimestamp("create_time").toLocalDateTime());
//            article.setTitle(rs.getString("title"));
//            article.setContent(rs.getString("content"));
//            article.setLikes(rs.getString("likes"));
//            article.setNoLikes(rs.getString("noLikes"));
//            article.setReply(rs.getString("reply"));
//            article.setIntroduction(rs.getString("introduction"));
//            articleList.add(article);
//
//        }
////        connection.commit();
////        stmt.close();
////        connection.close();
//        return articleList;
//    }


//}


