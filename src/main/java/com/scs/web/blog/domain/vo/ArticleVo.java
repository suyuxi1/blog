package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

/**
 * @author suyuxi
 * @className ArticleVo
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class ArticleVo {

    private Article article;
    private User author;
    private Topic topic;
}