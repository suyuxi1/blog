package com.scs.web.blog.util;


import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.User;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 * @author suyuxi
 * @className JSoupSpider
 * @Description JSoup爬虫工具
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {


    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

//    //静态的公有无参方法，返回List<Student>
//    public static List<Student> getStudents(){
//        //声明文档变量
//        Document document = null;
//        //通过JSoup连接目标页面
//        try{
//            document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
//
//        }catch (IOException e){
//            System.err.println("连接失败");
//        }
//        //选取所有class为col-xs-8的元素集合
//        Elements divs = document.getElementsByClass("col-xs-8");
//        //创建结合，初始化大小
//        List<Student> studentList = new ArrayList<>(divs.size());
//        //对div遍历
//        divs.forEach(div->{
//            //取出class为wrap的节点
//            Element wrapDiv = div.child(0);
//            Element link = wrapDiv.child(0);
//            Elements linkChildren = link.children();
//            Student student = new Student();
//            student.setUsername(linkChildren.get(1).text());
//            student.setAvatar("http:" + linkChildren.get(0).attr("src"));
//            student.setCreateTime(LocalDateTime.now());
//            student.setDescription(linkChildren.get(2).text());
//            studentList.add(student);
//        });
//        return studentList;
//    }

    public static List<User> getUsers() {

        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(UserDataUtil.getMobile());
                user.setPassword(UserDataUtil.getPassword());
                user.setGender(UserDataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(UserDataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }
    public static List<Article> getArticles(){
        Document document = null;
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i<=10; i++){
            try{
                document = Jsoup.connect("https://book.douban.com/review/best/?start=" + 2*i*10).get();
            }catch (IOException e){
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("main review-item");
            divs.forEach(div->{
                Article article = new Article();
                article.setUserID((long) UserDataUtil.getUserID());
                article.setPicture(div.child(0).child(0).attr("src"));
                article.setAvatar(div.child(0).child(0).attr("src"));
                article.setAuthor(div.child(1).child(1).text());
                article.setCreate_time(Timestamp.valueOf(div.child(1).children().last().text()).toLocalDateTime());
                article.setTitle(div.child(2).child(0).text());

                String href = div.child(2).child(0).child(0).attr("href");
                Document document1 = null;
                try{
                    document1 = Jsoup.connect(href).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element element = document1.getElementById("link-report");

                article.setIntroduction(div.child(2).child(1).child(0).text());
                article.setContent(element.child(0).html());
                article.setLikes(div.child(2).child(3).child(0).child(1).text());
                article.setNoLikes(div.child(2).child(3).child(1).text());
                article.setReply(div.child(2).child(3).child(2).text());
                articleList.add(article);

            });
        }
        return articleList;

    }


    public static void main(String[] args) throws Exception {


        Document document = Jsoup.connect("https://book.douban.com/review/best/?start=20").get();
        Elements card = document.getElementsByClass("main review-item");
        System.out.println(card.size());
        card.forEach(div-> {
//            System.out.println(div.child(2).child(0).text());
//            System.out.println(div.child(2).child(0).child(0).attr("href"));
            String href = div.child(2).child(0).child(0).attr("href");
            Document document1 = null;
            try{
                document1 = Jsoup.connect(href).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element element = document1.getElementById("link-report");
            System.out.println(element.child(0).html());







        });
        }

}
