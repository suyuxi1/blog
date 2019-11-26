package com.scs.web.blog.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.listener.MySessionContext;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author suyuxi
 * @className UserController
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/

@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();


//    /**
//     * 根据请求地址，取得最后的内容，结合请求方法来决定分发到不同的方法
//     *
//     * @param uri
//     * @return
//     */
//    private String getPatten(String uri) {
//        int len = "/api/user".length();
//        logger.info("user接口卡获取到的请求为"+len);
//        logger.info("请求为"+uri.substring(len));
//        return uri.substring(len);
//    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user".equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getUsersByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getUsersByKeywords(resp, keywords);
            } else {
                getHotUsers(req, resp);
            }
        } else {
            getUser(req, resp);
        }
    }

    private void getHotUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.getHotUsers();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUsersByPage(HttpServletResponse resp, int page, int count) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.selectByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUsersByKeywords(HttpServletResponse resp, String keywords) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = userService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }


    private void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Gson gson = new GsonBuilder().create();
        Result result = userService.getUser(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        System.out.println("uri请求为"+uri);
        if ("/api/user/sign-in".equals(uri)) {
            signIn(req, resp);
        } else if ("/api/user/sign-up".equals(uri)) {
            signUp(req, resp);
        } else if ("/api/user/check".equals(uri)) {
            check(req, resp);
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入登录请求");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        String inputCode = userDto.getCode().trim();
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        System.out.println("客户端传来的JSESSIONID：" + sessionId);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        String correctCode = session.getAttribute("code").toString();
        System.out.println("正确的验证码：" + correctCode);
        PrintWriter out = resp.getWriter();
        if (inputCode.equalsIgnoreCase(correctCode)) {
            System.out.println("进入登录方法");
            Result result = userService.signIn(userDto);
            out.print(gson.toJson(result));
        } else {
            Result result = Result.failure(ResultCode.USER_VERIFY_CODE_ERROR);
            out.print(gson.toJson(result));
        }
        out.close();
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("验证账号");
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("注册");
    }
}
//@WebServlet(urlPatterns = "/api/user/*")
//public class UserController extends HttpServlet {
//    private static Logger logger = LoggerFactory.getLogger(UserController.class);
//    private UserService userService = ServiceFactory.getUserServiceInstance();
//    /**
//     * 根据请求地址，取得最后的内容，结合请求方法来决定分发到不同的方法
//     *
//     * @param uri
//     * @return
//     */
//    private String getPatten(String uri) {
//        int len = "/api/user".length();
//        logger.info("user接口卡获取到的请求为"+len);
//        logger.info("请求为"+uri.substring(len));
//        return uri.substring(len);
//    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //去掉了地址共同的前缀，对不同的部分进行分发
//        String patten = getPatten(req.getRequestURI());
//        switch (patten) {
//            case "/hot":
//                getHotUser(req, resp);
//                break;
//            case "/list?page=*":
//                getPageUser(req, resp);
//                break;
//            case "/*":
//                getUser(req, resp);
//                break;
//        }
//    }
//    private void getHotUser(HttpServletRequest req, HttpServletResponse resp) {
////        Gson gson = new GsonBuilder().create();
////        Result result = userService.getHotUsers();
////        PrintWriter out = resp.getWriter();
////        out.print(gson.toJson(result));
////        out.close();
//    }
//
//    private void getPageUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    }
//
//    private void getUser(HttpServletRequest req, HttpServletResponse resp) {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String patten = getPatten(req.getRequestURI());
//
//        switch (patten) {
//            case "/sign-in":
//                signIn(req, resp);
//                break;
//            case "/check":
//                check(req, resp);
//                break;
//            case "/sign-up":
//                try {
//                    signUp(req, resp);
//                } catch (SQLException e) {
//                    logger.error("注册异常");
//                }
//                break;
//        }
//    }
//
//
//    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        BufferedReader reader = req.getReader();
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        logger.info("登录用户信息： " + stringBuilder.toString());
//        Gson gson = new GsonBuilder().create();
//        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
//        Map<String, Object> map = userService.signIn(userDto);
//        String msg = (String) map.get("msg");
//        ResponseObject ro;
//        if (msg.equals(Message.SIGN_IN_SUCCESS)) {
//            ro = ResponseObject.success(200, msg, map.get("data"));
//        } else {
//            ro = ResponseObject.success(200, msg);
//        }
//        PrintWriter out = resp.getWriter();
//        out.print(gson.toJson(ro));
//        out.close();
//    }
//
//    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
//        BufferedReader reader = req.getReader();
//        StringBuilder stringBuilder = new StringBuilder();
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        logger.info("登录用户信息： " + stringBuilder.toString());
//        Gson gson = new GsonBuilder().create();
//        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
//        Map<String, Object> map = userService.insertIn(userDto);
//        String msg = (String) map.get("msg");
//        ResponseObject ro;
//        if (msg.equals((Message.INSERT_SUCCESS))){
//            ro = ResponseObject.success(200,msg,map.get("data"));
//        }else{
//            ro = ResponseObject.success(200,msg);
//        }
//        PrintWriter out = resp.getWriter();
//        out.print(gson.toJson(ro));
//        out.close();
//    }
//
//
//    private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//}
