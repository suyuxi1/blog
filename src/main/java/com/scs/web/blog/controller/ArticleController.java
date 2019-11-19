package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @author suyuxi
 * @className ArticleController
 * @Description TODO
 * @Date 2019/11/12
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/article"})
public class ArticleController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        List<Article> articleList = null;
        try{
            articleList = DaoFactory.getArticleDaoInstance().findAll();
            System.out.println(articleList.size());
        }catch (SQLException e){
            logger.error("查询用户出现异常");
        }
        int code = resp.getStatus();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String msg = code == 200? "成功" : "失败";
        ResponseObject ro = ResponseObject.success(code,msg,articleList);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
    }

}
