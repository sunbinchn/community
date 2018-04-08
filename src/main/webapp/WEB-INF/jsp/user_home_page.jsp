<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <link href="${PATH}static/css/user_home_page.css" rel="stylesheet">
    <%@ include file="common.jsp" %>
    <script src="${PATH}static/js/user_info.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <!-- 左侧内容栏 -->
    <div class="col-sm-12 col-md-9 user-info-panel">
        <div class="user-icon-div">
            <img src="${PATH}static/images/${userInfo.icon.url}" alt="" style="width: 100px"/>
        </div>
        <div class="user-info-div">
            <h3>${userInfo.userName}</h3>
            <span>${userInfo.signature}</span>
        </div>
        <div>
            <button type="button" class="btn btn-primary edit-my-profile">编辑个人资料</button>
        </div>
    </div>
    <!-- 右侧栏 -->
    <div class="col-sm-12 col-md-3">
        <h1>阿萨德</h1>
    </div>
    <!-- 左侧内容栏 -->
    <div class="col-sm-12 col-md-9">
        <ul class="nav nav-tabs aw-nav-tabs">
            <li role="presentation" class="active"><a href="">喜欢<span class="badge">4</span></a></li>
            <li role="presentation" ><a href="">收藏</a></li>
            <li role="presentation" ><a href="">关注</a></li>
        </ul>
    </div>
</div>
</body>
</html>
