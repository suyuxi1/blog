package com.scs.web.blog.verify;

import javax.imageio.ImageIO;
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
 * @Date 2019/11/22
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.生成验证码
        String code = StringUtil.getRandomString();
        //2.生成图片
        BufferedImage img = ImageUtil.getImage( 200, 100,code);
        //3.设置resp的响应内容类型
        resp.setContentType("image/jpg");
        //4.将图片通过输出流返回给客户端
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img, "jpg", out);
        out.close();
    }
}
