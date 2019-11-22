package com.scs.web.blog.dao;

import org.junit.Test;

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TopicDaoTest {
    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();
    @Test
    public void batchInsert() throws SQLException {
        int i[] = topicDao.batchInsert(JSoupSpider.getTopics());
        System.out.println("专题数为："+i.length);

    }
}