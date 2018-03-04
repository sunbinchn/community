<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
                <form class="navbar-form navbar-right">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target=".login-modal">登录</button>
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target=".register-modal">注册</button>
                    <%--<li><a href="#">Link</a></li>--%>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"--%>
                           <%--aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="#">Action</a></li>--%>
                            <%--<li><a href="#">Another action</a></li>--%>
                            <%--<li><a href="#">Something else here</a></li>--%>
                            <%--<li role="separator" class="divider"></li>--%>
                            <%--<li><a href="#">Separated link</a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </form>
                <!-- 登录模态框 -->
                <div class="modal fade login-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h1 class="modal-title">登录</h1>
                            </div>
                            <div class="modal-body">
                                <form method="post" onsubmit="return;">
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
                                    没有账号？<a id="model_login_to_register">注册</a><a herf="#" style="float: right">忘记密码</a>
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
                                <h1 class="modal-title">注册</h1>
                            </div>
                            <div class="modal-body">
                                <form method="post" onsubmit="return;">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="reg_username" placeholder="请填写用户名">
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
