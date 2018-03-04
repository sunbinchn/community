<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //The path starts with a "/" character but does not end with a "/"
    pageContext.setAttribute("PATH", request.getContextPath() + "/");
%>

<html>
<head>
    <title>开源技术社区</title>
    <!--http://localhost:8888/community/ -->
    <script type="text/javascript" src="${PATH}static/js/jquery-2.0.3.min.js"></script>
    <link href="${PATH}static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${PATH}static/css/common.css" rel="stylesheet">
    <script src="${PATH}static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="${PATH}static/js/category/category.js"></script>

</head>
<body>
<%@ include file="../header.jsp" %>

</body>
</html>
