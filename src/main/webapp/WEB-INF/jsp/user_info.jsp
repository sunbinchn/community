<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <%@ include file="common.jsp" %>
    <script src="${PATH}static/js/user_info.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
        <h2>个人资料</h2>
        <form class="form-horizontal" enctype="multipart/form-data" action="${PATH}userInfo/update" method="post">
            <div class="form-group">
                <label class="col-sm-2 control-label">头像：</label>
                <div class="col-sm-9">
                    <img src="${PATH}static/images/user/user_icon_boy.png" style="width: 50px">
                    <div class="col-sm-5">
                        <span style="color: #909090;font-size: 1rem;margin-bottom: 1.5rem;">支持 jpg、png 格式大小 5M 以内的图片</span>
                        <input type="file" name="icon" id="icon" style="display: unset">
                        <span style="color:red" id="icon_error_span"></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="nickname" class="col-sm-2 control-label">昵称：</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="nickname" id="nickname" placeholder="填写你的昵称">
                </div>
                <img id="yesImg" src="${PATH}/static/images/yes.png" style="width: 30px;display: none"/>
                <span id="nickname_error_span" style="color:red"></span>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">邮箱：</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" name="email" id="email" placeholder="填写你的邮箱">
                </div>
                <span id="email_error_span" style="color:red"></span>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">电话：</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="填写你的电话">
                </div>
            </div>
            <div class="form-group">
                <label for="signature" class="col-sm-2 control-label">个性签名：</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="signature" id="signature" placeholder="填写你的个性签名">
                </div>
            </div>
            <div class="form-group">
                <label for="jobTitle" class="col-sm-2 control-label">职位：</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="jobTitle" id="jobTitle" placeholder="填写你的职位">
                </div>
            </div>
            <div class="form-group">
                <label for="company" class="col-sm-2 control-label">公司：</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="company" id="company" placeholder="填写你的公司">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default" id="update-userInfo-btn">保存</button>
                </div>
            </div>
        </form>
</div>
</body>
</html>
