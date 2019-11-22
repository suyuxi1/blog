package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;
public interface TopicService {
    /**
     * 获取热门专题
     * @param id
     * @return
     */
    Result getHotTopics();

    /**
     * 分页查询专题
     * @param currentPage
     * @param pageCount
     * @return
     */
    Result getPageTopics(int currentPage,int pageCount);


    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTopic(long id);
}
