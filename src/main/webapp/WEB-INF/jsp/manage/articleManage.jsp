<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../taglib.jsp" %>
<%
    //The path starts with a "/" character but does not end with a "/"
    pageContext.setAttribute("PATH", request.getContextPath() + "/");
%>

<html>
<head>
    <title>开源技术社区</title>
    <!--http://localhost:8888/community/ -->
    <%@ include file="../common.jsp" %>
    <script src="${PATH}static/js/manage/articleManage.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
    <div class="container">
    <!-- 右侧内容栏 -->
    <div class="col-md-12">
        <h1>文章管理</h1>
        <table class="table table-bordered table-hover" id="emp_table_id" style="margin-top: 5px; margin-bottom: 0px;">
            <thead>
            <tr>
                <th>#</th>
                <th style="width: 150px;">标题</th>
                <th style="min-width: 50px;">类型</th>
                <th style="min-width: 50px;">分类</th>
                <th style="min-width: 50px;">是否推荐</th>
                <th style="min-width: 50px;">发表时间</th>
                <th style="min-width: 50px;">最后更新时间</th>
                <th style="min-width: 50px;">作者名</th>
                <th style="min-width: 80px;">状态</th>
                <th style="min-width: 80px;">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articleItem" items="${pageInfo.list}" varStatus="status">
                <c:choose>
                    <c:when test="${status.count%2 eq 1}">
                        <tr class="success" data-id="${articleItem.id}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td><a href="${PATH}detail/get/${articleItem.id}">${articleItem.title}</a></td>
                            <td>${articleItem.original eq 1 ? "原创" : "转载"}</td>
                            <td>${articleItem.articleType.name}</td>
                            <td>${articleItem.isRecommend eq 1 ? "是" : "否"}</td>
                            <td><fmt:formatDate value="${articleItem.createTime}"  type="DATE" /></td>
                            <td><fmt:formatDate value="${articleItem.updateTime}"  type="DATE" /></td>
                            <td>${articleItem.user.userName}</td>
                            <td>${articleItem.isPass eq 1 ? "已审核" : (articleItem.isPass eq 0 ? "未审核" : "已删除")}</td>
                            <td>
                                <a class="toggle-pass">${articleItem.isPass eq 1 ? "取消审核" : "通过审核"}</a>
                                <c:if test="${articleItem.isPass != -1}">
                                    <a class="delete-article">${articleItem.isPass eq -1 ? "取消删除" : "删除"}</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr  class="info" data-id="${articleItem.id}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td><a href="${PATH}detail/get/${articleItem.id}">${articleItem.title}</a></td>
                            <td>${articleItem.original eq 1 ? "原创" : "转载"}</td>
                            <td>${articleItem.articleType.name}</td>
                            <td>${articleItem.isRecommend eq 1 ? "是" : "否"}</td>
                            <td><fmt:formatDate value="${articleItem.createTime}"  type="DATE" /></td>
                            <td><fmt:formatDate value="${articleItem.updateTime}"  type="DATE" /></td>
                            <td>${articleItem.user.userName}</td>
                            <td>${articleItem.isPass eq 1 ? "已审核" : (articleItem.isPass eq 0 ? "未审核" : "已删除")}</td>
                            <td>
                                <a class="toggle-pass">${articleItem.isPass eq 1 ? "取消审核" : "通过审核"}</a>
                                <c:if test="${articleItem.isPass != -1}">
                                    <a class="delete-article">${articleItem.isPass eq -1 ? "取消删除" : "删除"}</a>
                                </c:if>
                            </td>
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
                                <a href="${PATH}manage/article/index?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                                    <li><a href="${PATH}manage/article/index?pn=${curNum}">${curNum}</a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${PATH}manage/article/index?pn=${pageInfo.pageNum+1}" aria-label="Next">
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
</div>
</body>
</html>
