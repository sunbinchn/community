<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <%@ include file="common.jsp" %>
    <script src="${PATH}static/js/user_recover_password.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <h2>找回密码</h2>
    <input value="${param.token}" hidden="true" id="tokenInput">
    <c:choose>
        <c:when test="${!empty param.token}">
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label for="newPassword" class="col-sm-2 control-label">新密码：</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="newPassword" id="newPassword">
                    </div>
                    <span style="color: red;" id="newPassword-error-span"></span>
                </div>
                <div class="form-group">
                    <label for="newPassword1" class="col-sm-2 control-label">确认密码：</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="newPassword1" id="newPassword1">
                    </div>
                    <span style="color: red;" id="newPassword1-error-span"></span>
                </div>
                <div class="form-group">
                    <label  class="col-md-offset-2 control-label" style="color: darkcyan" id="update-success-label"></label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id="update-password-btn">确认</button>
                    </div>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label for="userName" class="col-sm-2 control-label">用户名：</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="userName" id="userName">
                    </div>
                    <span style="color: red;" id="userName-error-span"></span>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">邮箱：</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="email" id="email">
                    </div>
                    <span style="color: red;" id="email-error-span"></span>
                </div>
                <div class="form-group">
                    <label  class="col-md-offset-2 control-label" style="color: darkcyan" id="email-success-label"></label>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id="recover-password-btn">确认</button>
                    </div>
                </div>
            </form>
        </c:otherwise>
    </c:choose>


</div>
<%@ include file="footer.jsp" %>
</body>
</html>
