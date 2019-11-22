package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.TopicVo;
import com.scs.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;
public interface TopicDao {
    /**
     * 批量新增专题
     *
     * @param topicList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Topic> topicList) throws SQLException;

    /**
     * 获取热门专题
     * @return
     * @throws SQLException
     */
    List<Topic> selectHotTopics() throws SQLException;


    /**
     * 分页显示专题信息
     * @param currentPage
     * @param pageCount
     * @return
     * @throws SQLException
     */
    List<Topic> selectByPage(int currentPage,int pageCount)throws SQLException;

    /**
     * 根据id获取专题详情
     * @param id
     * @return
     * @throws SQLException
     */
    TopicVo getTopic(long id)throws SQLException;

}
