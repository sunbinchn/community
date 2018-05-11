<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>

<html>
<head>
    <title>开源技术社区</title>
    <!--http://localhost:8888/community/ -->
    <script type="text/javascript" src="${PATH}static/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${PATH}static/js/underscore.js"></script>
    <link href="${PATH}static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${PATH}static/css/common.css" rel="stylesheet">
    <script src="${PATH}static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="${PATH}static/js/writeArticle.js"></script>
    <script src="${PATH}static/js/common.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="${PATH}static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${PATH}static/ueditor/ueditor.all.js"></script>
    <!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
    <script type="text/javascript" src="${PATH}static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
    <div  class="container">
        <div class="row" style="margin-bottom: 10px;">
            <div class="form-group" style="margin-left: 18px;">
                <select id="select-original" name="select-original"
                        class="selectpicker show-tick col-sm-2" data-live-search="false" style="width: 100px;height: 28px;">
                    <option value="-1">请选择</option>
                    <option value="0">转载</option>
                    <option value="1">原创</option>
                </select>
                <input id="titleInput" type="text" id="title" class="col-sm-4" placeholder="请输入文章标题" required autofocus>
                <span id="error-msg-span" class="col-sm-1" style="width:170px;padding:8px 12px;color:red"></span>
            </div>
        </div>
        <!-- 加载编辑器的容器 -->
        <script id="container" name="content" type="text/plain" style="width:980px;height:500px;">
        </script>
    </div>
    <div  class="container" style="margin-top:10px">
        <span class="col-sm-1" style="width:100px;padding: 8px 12px;">选择分类：</span>
        <ul class="nav nav-pills col-sm-9">
            <c:forEach items="${articleTypeList}" var="articleType"  varStatus="status" begin="0" end="8" step="1">
            <li role="presentation" data-id="${articleType.id}"><a href="" style="padding: 8px 12px;">${articleType.name}</a></li>
            </c:forEach>
            <c:if test="${fn:length(articleTypeList) gt 9}">
                <li class="dropup">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                aria-expanded="false">其他<span class="caret"></span></a>
                    <ul class="dropdown-menu" id="otherUL">
                        <c:forEach items="${articleTypeList}" var="articleType" begin="9" step="1">
                             <li role="presentation" data-id="${articleType.id}"><a href="" style="padding: 8px 12px;">${articleType.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </ul>


        <button id="article-sumbit-button" type="button" class="btn btn-success" style="float: right;")>提交</button>
    </div>
    </div>
    <%@ include file="footer.jsp" %>

</body>
</html>