<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //The path starts with a "/" character but does not end with a "/"
    pageContext.setAttribute("PATH", request.getContextPath() + "/");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>开源技术社区-登录页</title>
    <script type="text/javascript" src="${PATH}static/js/jquery-2.0.3.min.js"></script>
    <link href="${PATH}static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${PATH}static/css/signin.css" rel="stylesheet">
    <script src="${PATH}static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="${PATH}static/js/login/login.js"></script>
    <%
        //如果用户在别的页面登录过了，在这个页面直接跳转就好
        if (request.getSession().getAttribute("name") != null && !"".equals(request.getSession().getAttribute("name"))) {
            response.sendRedirect("/store/servlet/toIndexServlet");
        }
    %>
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" onsubmit="return;">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="username_or_email" class="sr-only">Email address</label>
        <input type="text" id="username_or_email" class="form-control" placeholder="用户名和uoy" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" class="form-control" placeholder="密码" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">Sign in</button>
    </form>

</div>

</body>
</html>
