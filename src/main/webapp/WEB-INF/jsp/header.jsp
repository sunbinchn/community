<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    if (request.getParameter("query") != null) {
        pageContext.setAttribute("QUERY", request.getParameter("query"));
    }
%>
<div class="container">
    <input id="user_id_input" hidden="true" value="${sessionScope.userId}"/>
    <c:choose>
        <c:when test="${!empty QUERY}">
            <input value="${QUERY}" hidden="true" id="queryInput"/>
        </c:when>
        <c:otherwise>
            <input hidden="true" id="queryInput"/>
        </c:otherwise>
    </c:choose>
    <input value="${SERVER_REQUEST_URL}" hidden="true" id="server_request_url"/>
    <nav class="navbar navbar-default">
        <div class="container-fluid" style="background-color: antiquewhite;">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <%--<img src="${PATH}static/images/logo.png">--%>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active" id="homePageLi"><a href="${PATH}explore/all/latest">首页</a></li>
                    <li id="resourceLi"><a href="${PATH}explore/category-7/latest">资源</a></li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="搜索文章、资源或人" id="head-criteria-input">
                    </div>
                    <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                </form>
                <% if (request.getSession().getAttribute("username") == null){ %>
                <form class="navbar-form navbar-right">
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target=".login-modal">登录</button>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target=".register-modal">注册</button>
                </form>
                <% } else { %>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.username} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/community/writeArticle/index">写文章</a></li>
                            <li><a href="/community/userHomePage/${sessionScope.userId}/read">我的主页</a></li>
                            <li><a href="/community/userHomePage/${sessionScope.userId}/love">我喜欢的</a></li>
                            <li><a href="/community/userHomePage/${sessionScope.userId}/keep">我的收藏</a></li>
                            <li><a href="/community/userHomePage/${sessionScope.userId}/article">我的文章</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/community/userInfo/profile">个人信息</a></li>
                            <li><a href="/community/userInfo/password">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <c:if test="${role eq 2}">
                                <li><a href="/community/manage/user/index">用户管理</a></li>
                                <li><a href="/community/manage/article/index">文章管理</a></li>
                                <li><a href="/community/manage/articleType/index">类型管理</a></li>
                            </c:if>
                            <li><a href="/community/login/logOut">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
                <% } %>
                <form class="navbar-form navbar-right">
                    <button type="button" class="btn btn-primary" id="write-article-button">写文章</button>
                </form>

                <!-- 登录模态框 -->
                <div class="modal fade login-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">登录</h3>
                            </div>
                            <div class="modal-body">
                                <form method="post" onsubmit="return;">
                                    <p id="login_error_msg" class="color-red"></p>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="username_or_email" placeholder="请填写用户名或邮箱">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="password" placeholder="请输入密码">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block" id="model_login_btn">登录</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div style="width: 100%;text-align: left;">
                                    没有账号？<a id="model_login_to_register">注册</a><a href="${PATH}userRecoverPassword/index" style="float: right">忘记密码</a>
                                </div>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal-end -->

                <!-- 注册模态框 -->
                <div class="modal fade register-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">注册</h3>
                            </div>
                            <div class="modal-body">
                                <form method="post" onsubmit="return;">
                                    <p id="register_error_msg" class="color-red"></p>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="reg_username" placeholder="请填写用户名(数字、字母、下划线)" maxlength="16" onkeyup="value=value.replace(/[^(\-)\w\.\/]/ig,'')">
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="reg_email" placeholder="请填写邮箱">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="reg_password" placeholder="请输入密码">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block" id="model_register_btn">注册</button>
                                    <div style="text-align: center;margin-top: 5px">
                                        <a id="model_register_to_login">已有账号登陆</a>
                                    </div>
                                </form>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal-end -->

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
