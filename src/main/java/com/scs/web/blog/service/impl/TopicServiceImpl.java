package com.scs.web.blog.service.impl;


import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.TopicDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.domain.vo.TopicVo;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.TopicService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
/**
 * @author suyuxi
 * @className TopicServiceImpl
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public Result getHotTopics() {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.selectHotTopics();
        } catch (SQLException e) {
            logger.error("获取热门专题出现异常");
        }
        if (topicList != null) {
            return Result.success(topicList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getPageTopics(int currentPage, int pageCount) {
        return null;
    }

    @Override
    public Result getTopic(long id) {
        TopicVo topicVo = null;
        try {
            topicVo = topicDao.getTopic(id);
        } catch (SQLException e) {
            logger.error("根据id获取专题详情出现异常");
        }
        if (topicVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByTopicId(topicVo.getId());
                topicVo.setArticleList(articleVoList);

            } catch (SQLException e) {
                logger.error("根据专题id获取所有文章出现异常");
            }
            return Result.success(topicVo);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
