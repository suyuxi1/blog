package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;
/**
 * @author suyuxi
 * @className UserVo
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleList;
    private List<Topic> topicList;
    private List<User> fansList;

}
