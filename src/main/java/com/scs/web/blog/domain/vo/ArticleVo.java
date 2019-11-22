package com.scs.web.blog.domain.vo;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @author suyuxi
 * @className ArticleVo
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Long userId;
    private Long topicId;
    private String title;
    private String summary;
    private String thumbnail;
    private String content;
    private Integer likes;
    private Integer comments;
    private LocalDateTime createTime;
    private String nickname;
    private String avatar;
    private String topicName;
    private String logo;
}