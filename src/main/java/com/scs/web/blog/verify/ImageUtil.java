package com.scs.web.blog.verify;

import com.scs.web.blog.util.DataUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author suyuxi
 * @className ImageUtil
 * @Description 头像工具类
 * @Date 2019/11/20
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage getImage( int width, int height,String content){
        BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = (Graphics2D) img.getGraphics();
        Color foreColor = new Color(26,160,52);
        Color bgColor = new Color(60,63,63);
        g.setColor(bgColor);
        g.drawRect(0, 0 ,200, 100);
        g.setColor(foreColor);
        Font font = new Font("微软雅黑", Font.BOLD,30);
        g.setFont(font);
        g.drawString(content,50,50);
       return  img;


    }

    public static void main(String[] args) throws IOException {
        String code = DataUtil.getNumberCode();
        BufferedImage img = ImageUtil.getImage(200, 100,code);
        File file = new File("E:/2019/T.jpg");
        ImageIO.write(img, "jpg", file);
    }
}
