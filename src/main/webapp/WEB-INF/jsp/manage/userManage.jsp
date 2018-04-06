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
    <%@ include file="../common.jsp" %>
    <script src="${PATH}static/js/manage/userManage.js"></script>
</head>
<body>
<%@ include file="../header.jsp" %>
    <div class="container">
    <!-- 右侧内容栏 -->
    <div class="col-md-12">
        <h1>用户管理</h1>
        <table class="table table-bordered table-hover" id="emp_table_id" style="margin-top: 5px; margin-bottom: 0px;">
            <thead>
            <tr>
                <th>#</th>
                <th>用户名</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>个性签名</th>
                <th>访问数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userItem" items="${pageInfo.list}" varStatus="status">
                <c:choose>
                    <c:when test="${status.count%2 eq 1}">
                        <tr class="success" data-id="${userItem.userId}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td>${userItem.userName}</td>
                            <td>${userItem.email}</td>
                            <td>${userItem.phone}</td>
                            <td>${userItem.signature}</td>
                            <td>${userItem.visits}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${userItem.isShutUp eq 0}">
                                        <a class="shutUpClass">禁言</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="cancelShutUpClass">解禁</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${userItem.isBan eq 0}">
                                        <a class="banClass">封号</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="cancelBanClass">解封</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr  class="info" data-id="${userItem.userId}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td>${userItem.userName}</td>
                            <td>${userItem.email}</td>
                            <td>${userItem.phone}</td>
                            <td>${userItem.signature}</td>
                            <td>${userItem.visits}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${userItem.isShutUp eq 0}">
                                        <a class="shutUpClass">禁言</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="cancelShutUpClass">解禁</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${userItem.isBan eq 0}">
                                        <a class="banClass">封号</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="cancelBanClass">解封</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
        <!-- 分页 -->
        <c:if test="${pageInfo.pages gt 1}">
            <div style="float:right">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${PATH}manage/user/index?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                                    <li><a href="${PATH}manage/user/index?pn=${curNum}">${curNum}</a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${PATH}manage/user/index?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div> <!-- 分页结束 -->
        </c:if>
    </div>
</div>
</body>
</html>
