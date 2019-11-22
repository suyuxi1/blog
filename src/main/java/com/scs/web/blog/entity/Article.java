package com.scs.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author suyuxi
 * @className article
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
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
}


//@Data
//public class Article {
//    private Long id;
//    private Long userID;
//    private String picture;
//    private String avatar;
//    private String author;
//    private LocalDateTime create_time;
//    private String title;
//    private String content;
//    private String likes;
//    private String noLikes;
//    private String reply;
//    private String introduction;
//}
