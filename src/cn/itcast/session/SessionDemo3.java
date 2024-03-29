package cn.itcast.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sessionDemo3")
public class SessionDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用session共享数据
        //1。获取session
        HttpSession session = request.getSession();

        System.out.println(session);

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
