package com.scs.web.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * @author suyuxi
 * @className UploadController
 * @Description TODO
 * @Date 2019/11/21
 * @Version 1.0
 **/

@MultipartConfig
@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            Collection<Part> parts = req.getParts();
            long max = 1024 * 1024 * 5;
            long sumSize = 0;
            for (Part part : parts) {
                long size = part.getSize();
                sumSize += size;
            }
            if (sumSize == 0 ){
                req.setAttribute("msg", "请选择文件");
            }else {
                if (sumSize < max) {
                    for (Part part : parts) {
                        System.out.println("大小" + part.getSize());
                        String name = part.getSubmittedFileName();
                        int index = name.lastIndexOf(".");
                        String houzhui = name.substring(index);
                        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + houzhui;
                        LocalDate date = LocalDate.now();
                        System.out.println(uuidFileName);
                        String path = req.getSession().getServletContext().getRealPath("") + date;
                        File file = new File(path);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        System.out.println(date);
                        part.write(file.getPath() + "/" + uuidFileName);
                        System.out.println(file.getPath() + "/" + uuidFileName);
                        resp.setContentType("img/jpg");
                        req.setAttribute("msg", "上传成功");
                        req.setAttribute("url", "http://localhost:8080/" + "/" + uuidFileName);
                    }
                } else {
                    req.setAttribute("msg", "文件过大，上传失败");
                }
            }
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }
}
