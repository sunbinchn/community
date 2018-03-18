<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <%@ include file="../common.jsp" %>
    <link href="${PATH}static/css/content.css" rel="stylesheet">
    <script src="${PATH}static/js/category/category.js"></script>

</head>
<body>
<%@ include file="../header.jsp" %>
<div class="container category">
    <div class="row">
        <div class="col-sm-12">
            <ul class="list" id="category-list-id">
                <li><a href="${PATH}explore/all/latest">全部</a></li>
                <c:forEach items="${articleTypeList}" var="articleType">
                    <li>
                        <a href="${PATH}explore/category-${articleType.id}/latest">${articleType.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@ include file="../content.jsp" %>

</body>
</html>
