<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <%@ include file="common.jsp" %>
    <script src="${PATH}static/js/user_password.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <h2>修改密码</h2>
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label for="oldPassword" class="col-sm-2 control-label">当前密码：</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" name="oldPassword" id="oldPassword">
            </div>
            <span id="oldPassword_error_span" style="color:red"></span>
            <a>忘记密码？</a>
        </div>
        <div class="form-group">
            <label for="newPassword1" class="col-sm-2 control-label">新密码：</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" name="newPassword1" id="newPassword1">
            </div>
            <span id="newPassword1_error_span" style="color:red"></span>
        </div>
        <div class="form-group">
            <label for="newPassword2" class="col-sm-2 control-label">确认密码：</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" name="newPassword2" id="newPassword2">
            </div>
            <span id="newPassword2_error_span" style="color:red"></span>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" id="update-password-btn">保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
