package com.scs.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author suyuxi
 * @className article
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class Article {
    private Long id;
    private Long userID;
    private String picture;
    private String avatar;
    private String author;
    private LocalDateTime create_time;
    private String title;
    private String content;
    private String likes;
    private String noLikes;
    private String reply;


}
