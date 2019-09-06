<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    <%
        //1.获取cookie对象
        Cookie[] cookies = request.getCookies();

        boolean flag = false;

        //2.遍历数组
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                //获取cookie名称
                String name = cookie.getName();
                //判断名称是否为lastTime
                if("lastTime".equals(name)){
                    //有，不是第一次访问
                    flag = true;

                    //设置Cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    System.out.println("编码前:" + str_date);
                    //URL编码
                    str_date = URLEncoder.encode(str_date, "utf-8");
                    System.out.println("编码后:" + str_date);

                    cookie.setValue(str_date);
                    //设置Cookie存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);


                    //响应数据
                    //获取cookie的时间
                    String value = cookie.getValue();
                    System.out.println("解码前：" + value);
                    //URL解码
                    value = URLDecoder.decode(value, "utf-8");
                    System.out.println("解码后：" + value);

                    out.write("<h1>欢迎回来，您上次访问时间为：" + value + "<h1/>");
                    break;
                }
            }
        }
        if(cookies ==  null || cookies.length == 0 || flag == false){
            //没有。第一次访问
            //设置Cookie的value
            //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);

            System.out.println("编码前:" + str_date);
            //URL编码
            str_date = URLEncoder.encode(str_date, "utf-8");
            System.out.println("编码后:" + str_date);

            Cookie cookie = new Cookie("lastTime", str_date);
            //设置Cookie存活时间
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
    %>
    <h1>您好，欢迎你首次访问：<h1/>
    <%
        }
    %>
</body>
</html>
