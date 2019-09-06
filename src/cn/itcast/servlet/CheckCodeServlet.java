package cn.itcast.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = 100;
        int height = 50;

        //1.创建一对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);//填充颜色
        g.fillRect(0, 0, width, height);//填充范围，起点加上宽高

        //2.2画边框
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);

        String str = "QWERTYUIOPASDFHGJKLZXCVBNMqwertyuoipasdfghjklzxcvbnm0123456789";
        //生成随机脚标
        Random ran = new Random();

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i < 5 ; i++){
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);//随机字符

            sb.append(ch);

            //2.3写验证码
            g.drawString(ch+"", width/5*i, height/2);
        }

        String checkCode_session = sb.toString();
        //将验证码存入session中
        request.getSession().setAttribute("checkCode_session", checkCode_session);

        //画干扰线
        g.setColor(Color.GREEN);

        //生成随机坐标点
        for(int i = 0 ; i < 10 ; i++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }


/*        g.drawString("A", 20, 25);
        g.drawString("B", 40, 25);
        g.drawString("C", 60, 25);
        g.drawString("D", 80, 25);*/

        //3.将图片输出到页面展示 
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
