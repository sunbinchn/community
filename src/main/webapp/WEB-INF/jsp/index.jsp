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
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <img alt="Brand" src="${PATH}static/images/logo.png">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
<div class="container category">
    <div class="row">
        <div class="col-sm-12">
            <ul class="list" id="category-list-id">
                <li><a href="${PATH}explore/all">全部</a></li>
                <li>
                    <a href="${PATH}explore/category-1">智慧运维</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-2">编程艺术</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-3">数据库</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-4">大数据-云计算</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-5">产品架构</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-6">开源项目</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-7">学习资源</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-8">求职招聘</a>
                </li>
                <li>
                    <a href="${PATH}explore/category-9">互联网资讯</a>
                </li>
                <li>
                    <a href="/extension/link/links.htm" target="_blank">友情链接</a>
                </li>
            </ul>
        </div>
    </div>
</div>

</div>


</body>
</html>
