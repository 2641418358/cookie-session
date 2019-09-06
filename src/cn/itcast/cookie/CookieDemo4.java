package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cookie的快速入门
 */
@WebServlet("/cookieDome4")
public class CookieDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie cookie = new Cookie("msg", "setMaxAge");
        //2.设置Cookie的存活事件
        //cookie.setMaxAge(30);//将cookie持久化到硬盘，30s后自动删除cookie文件
        cookie.setMaxAge(-1);//负数，默认值，关闭浏览器即摧毁cookie
        cookie.setMaxAge(0);//0.删除cookie
        //3.发送Cookie
        response.addCookie(cookie);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
