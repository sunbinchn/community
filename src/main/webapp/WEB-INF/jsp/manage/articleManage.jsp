<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../taglib.jsp" %>
<%
    if (request.getParameter("criteria") != null) {
        pageContext.setAttribute("CRITERIA", request.getParameter("criteria"));
    }
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
    <c:choose>
        <c:when test="${!empty CRITERIA}">
            <input value="${CRITERIA}" hidden="true" id="criteriaInput"/>
        </c:when>
        <c:otherwise>
            <input hidden="true" id="criteriaInput"/>
        </c:otherwise>
    </c:choose>

    <div class="container">
    <!-- 右侧内容栏 -->
    <div class="col-md-12">
        <h1>文章管理</h1>
        <div style="margin-bottom: 10px">
            <label class="radio-inline">
                <input type="radio" name="unchecked" id="unchecked">未审核
            </label>
            <label class="radio-inline">
                <input type="radio" name="deleted" id="deleted">已删除
            </label>
            <label class="radio-inline">
                <input type="radio" name="checked" id="checked" >已审核
            </label>
            <label class="radio-inline">
                <input type="radio" name="checked" id="recommend" >已推荐
            </label>
            <label class="radio-inline">
                <input type="radio" name="all" id="all">全部
            </label>
            <label for="criteria" style="margin-left:20px">过滤条件：</label>
            <input type="text" class="col-sm-2" id="criteria" placeholder="文章名或用户名" style="float: none;">
            <button type="button" class="btn btn-info btn-sm" id="criteria-button">过滤</button>
        </div>
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
                <th style="min-width: 170px;">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="articleItem" items="${pageInfo.list}" varStatus="status">
                <c:choose>
                    <c:when test="${status.count%2 eq 1}">
                        <tr class="success" data-id="${articleItem.id}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td><a href="${PATH}detail/get/${articleItem.id}"  target="_blank">${articleItem.title}</a></td>
                            <td>${articleItem.original eq 1 ? "原创" : "转载"}</td>
                            <td>${articleItem.articleType.name}</td>
                            <td>${articleItem.isRecommend eq 1 ? "是" : "否"}</td>
                            <td><fmt:formatDate value="${articleItem.createTime}"  type="DATE" /></td>
                            <td><fmt:formatDate value="${articleItem.updateTime}"  type="DATE" /></td>
                            <td><a href="${PATH}userHomePage/${articleItem.user.userId}/read" target="_blank">${articleItem.user.userName}</a></td>
                            <td>${articleItem.isPass eq 1 ? "已审核" : (articleItem.isPass eq 0 ? "未审核" : "已删除")}</td>
                            <td>
                                <a class="toggle-pass">${articleItem.isPass eq 1 ? "取消审核" : "通过审核"}</a>
                                <c:if test="${articleItem.isPass != -1}">
                                    <a class="delete-article">删除</a>
                                </c:if>
                                <c:choose>
                                    <c:when test="${articleItem.isRecommend eq 1}">
                                        <a class="toggle-recommend">取消推荐</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="toggle-recommend">推荐</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr  class="info" data-id="${articleItem.id}">
                            <td>${status.count + (pageInfo.pageNum-1) * pageInfo.pageSize}</td>
                            <td><a href="${PATH}detail/get/${articleItem.id}" target="_blank">${articleItem.title}</a></td>
                            <td>${articleItem.original eq 1 ? "原创" : "转载"}</td>
                            <td>${articleItem.articleType.name}</td>
                            <td>${articleItem.isRecommend eq 1 ? "是" : "否"}</td>
                            <td><fmt:formatDate value="${articleItem.createTime}"  type="DATE" /></td>
                            <td><fmt:formatDate value="${articleItem.updateTime}"  type="DATE" /></td>
                            <td><a href="${PATH}userHomePage/${articleItem.user.userId}/read" target="_blank">${articleItem.user.userName}</a></td>
                            <td>${articleItem.isPass eq 1 ? "已审核" : (articleItem.isPass eq 0 ? "未审核" : "已删除")}</td>
                            <td>
                                <a class="toggle-pass">${articleItem.isPass eq 1 ? "取消审核" : "通过审核"}</a>
                                <c:if test="${articleItem.isPass != -1}">
                                    <a class="delete-article">删除</a>
                                </c:if>
                                <c:choose>
                                    <c:when test="${articleItem.isRecommend eq 1}">
                                        <a class="toggle-recommend">取消推荐</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="toggle-recommend">推荐</a>
                                    </c:otherwise>
                                </c:choose>
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
                                <c:choose>
                                    <c:when test="${!empty CRITERIA}">
                                        <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum-1}&criteria=${CRITERIA}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:if>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="curNum">
                            <c:choose>
                                <c:when test="${pageInfo.pageNum eq curNum}" >
                                    <li class="active"><span>${curNum}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${!empty CRITERIA}">
                                            <li><a href="${SERVER_REQUEST_URL}?pn=${curNum}&criteria=${CRITERIA}">${curNum}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="${SERVER_REQUEST_URL}?pn=${curNum}">${curNum}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                            <c:choose>
                                <c:when test="${!empty CRITERIA}">
                                    <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum+1}&criteria=${CRITERIA}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
            </div> <!-- 分页结束 -->
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
