package com.scs.web.blog.controller;

import com.scs.web.blog.util.UserDataUtil;
import com.scs.web.blog.verify.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author suyuxi
 * @className CodeController
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
//        String code = UserDataUtil.getNumberCode();
////        BufferedImage img = ImageUtil.getImage(code, 200, 100);
////        resp.setContentType("image/jpg");
////        OutputStream out = resp.
    }
}
