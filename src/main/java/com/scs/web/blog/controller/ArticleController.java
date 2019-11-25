package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.ResponseObject;
import com.scs.web.blog.util.Result;
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
@WebServlet(urlPatterns = {"/api/article", "/api/article/*"})
public class ArticleController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String uri = req.getRequestURI().trim();
        if ("/api/article/getAllArticle".equals(uri)){
                getAllArticles(req, resp);
        }else {
            if ("/api/article".equals(uri)) {
                String page = req.getParameter("page");
                String keywords = req.getParameter("keywords");
                String count = req.getParameter("count");
                if (page != null) {
                    getArticlesByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
                }
                else if (keywords != null) {
                    getArticlesByKeywords(resp, keywords);
                }
                else {
                    getHotArticles(req, resp);
                }
            } else {
                getArticle(req, resp);
            }
        }

    }

    private void getAllArticles(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        List<ArticleVo> articleVoList = null;
        try{
            articleVoList = DaoFactory.getArticleDaoInstance().findAll();
            System.out.println(articleVoList.size());
        }catch (SQLException e){
            logger.error("查询用户出现异常");
        }
        int code = resp.getStatus();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String msg = code == 200? "成功" : "失败";
        ResponseObject ro = ResponseObject.success(code,msg,articleVoList);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
    }


    private void getHotArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getHotArticles();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getArticlesByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Result result = articleService.getArticle(Long.parseLong(id));
        Gson gson = new GsonBuilder().create();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}