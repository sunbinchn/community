<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../taglib.jsp" %>

<html>
<head>
    <title>开源技术社区</title>
    <!--http://localhost:8888/community/ -->
    <%@ include file="../common.jsp" %>
    <script src="${PATH}static/js/manage/articleTypeManage.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
    <div class="container">
    <!-- 右侧内容栏 -->
    <div class="col-md-12">
        <h1>文章类型管理</h1>
        <button type="button" class="btn btn-info btn-sm" id="add-button" style="margin-top: 5px;">添加</button>
        <table class="table table-bordered table-hover" style="margin-top: 5px; margin-bottom: 0px;">
            <thead>
            <tr>
                <th>类型名称</th>
                <th>优先级</th>
                <th>描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articleType" items="${pageInfo.list}" varStatus="status">
                <c:choose>
                    <c:when test="${status.count%2 eq 1}">
                        <tr class="success" data-id="${articleType.id}">
                            <td><span>${articleType.name}</span><button type="button" class="btn btn-success btn-xs update-name" style="float: right">修改</button></td>
                            <td><span>${articleType.priority}</span><button type="button" class="btn btn-success btn-xs update-priority" style="float: right">修改</button></td>
                            <td><span>${articleType.description}</span><button type="button" class="btn btn-success btn-xs update-description" style="float: right">修改</button></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr  class="info" data-id="${articleType.id}">
                            <td><span>${articleType.name}</span><button type="button" class="btn btn-success btn-xs update-name" style="float: right">修改</button></td>
                            <td><span>${articleType.priority}</span><button type="button" class="btn btn-success btn-xs update-priority" style="float: right">修改</button></td>
                            <td><span>${articleType.description}</span><button type="button" class="btn btn-success btn-xs update-description" style="float: right">修改</button></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
        <!-- 分页 -->
        <c:if test="${pageInfo.pages gt 1}">
            <div style="float:right">
            <c:if test="${pageInfo.pages gt 1}">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${PATH}manage/articleType/index?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="curNum">
                            <c:choose>
                                <c:when test="${pageInfo.pageNum eq curNum}" >
                                    <li class="active"><span>${curNum}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${PATH}manage/articleType/index?pn=${curNum}">${curNum}</a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${PATH}manage/articleType/index?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
            </div> <!-- 分页结束 -->
        </c:if>
    </div>
        <!-- 添加用户模态框 -->
        <div class="modal fade addArticleType-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h5 class="modal-title">添加新类型</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group" id="error-msg-div2" >
                            <label class="control-label" id="error-msg-label2" style="color:red;"></label>
                        </div>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">类型名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="add-typeName" hidden="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-typePriority" class="col-sm-2 control-label">优先级</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="add-typePriority">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-sm-2 control-label">描述</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="add-description">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-6">
                                    <button type="submit" class="btn btn-primary" style="width: 120px" id="insert-articleType-button">添加</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">

                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal-end -->
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
