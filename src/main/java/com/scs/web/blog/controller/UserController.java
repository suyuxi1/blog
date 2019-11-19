package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author suyuxi
 * @className UserController
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/user/*")
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    /**
     * 根据请求地址，取得最后的内容，结合请求方法来决定分发到不同的方法
     *
     * @param uri
     * @return
     */
    private String getPatten(String uri) {
        int len = "/api/user".length();
        logger.info("user接口卡获取到的请求为"+len);
        logger.info("请求为"+uri.substring(len));
        return uri.substring(len);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //去掉了地址共同的前缀，对不同的部分进行分发
        String patten = getPatten(req.getRequestURI());
        switch (patten) {
            case "/hot":
                getHotUser(req, resp);
                break;
            case "/list?page=*":
                getPageUser(req, resp);
                break;
            case "/*":
                getUser(req, resp);
                break;
        }

    }

    private void getHotUser(HttpServletRequest req, HttpServletResponse resp) {
//        Gson gson = new GsonBuilder().create();
//        Result result = userService.getHotUsers();
//        PrintWriter out = resp.getWriter();
//        out.print(gson.toJson(result));
//        out.close();
    }

    private void getPageUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    private void getUser(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patten = getPatten(req.getRequestURI());

        switch (patten) {
            case "/sign-in":
                signIn(req, resp);
                break;
            case "/check":
                check(req, resp);
                break;
            case "/sign-up":
                try {
                    signUp(req, resp);
                } catch (SQLException e) {
                    logger.error("注册异常");
                }
                break;
        }
    }


    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息： " + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = userService.signIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if (msg.equals(Message.SIGN_IN_SUCCESS)) {
            ro = ResponseObject.success(200, msg, map.get("data"));
        } else {
            ro = ResponseObject.success(200, msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息： " + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = userService.insertIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if (msg.equals((Message.INSERT_SUCCESS))){
            ro = ResponseObject.success(200,msg,map.get("data"));
        }else{
            ro = ResponseObject.success(200,msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }


    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


//    @Override
//    public void init() throws ServletException {
//        logger.info("UserController初始化");
//    }

}
