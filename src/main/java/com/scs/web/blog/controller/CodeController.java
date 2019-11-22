package com.scs.web.blog.controller;

import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.verify.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
//        String code = UserDataUtil.getNumberCode();
//        HttpSession session = req.getSession();
//        System.out.println(session.getId());
//        session.setAttribute("code", code);
//        int width = 130;
//        int height = 35;
//        Random random = new Random();
//        Color color = new Color(130,180,45);
//
//    }
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String code = DataUtil.getNumberCode();

    HttpSession session = req.getSession();
    System.out.println(session.getId());
    session.setAttribute("code", code);

    BufferedImage image = ImageUtil.getImage(code, 200, 100);
    //设置响应内容类型
    resp.setContentType("image/jpg");
    OutputStream out = resp.getOutputStream();
    ImageIO.write(image,"jpg", out);
    out.close();
}

}
