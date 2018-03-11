<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">

    <div class="row">
        <div class="aw-content-wrap clearfix">

            <!-- 左侧内容栏 -->
            <div class="col-sm-12 col-md-9 aw-main-content">
                <h2>发现</h2>
                <ul class="nav nav-tabs aw-nav-tabs">
                    <li role="presentation" ><a href="">最新</a></li>
                    <li role="presentation" ><a href="">热门</a></li>
                    <li role="presentation" ><a href="">推荐</a></li>
                </ul>

                <div class="content-explore-list">
                    <div class="explore-body">
                        <div class="explore-common-list">
                            <c:forEach items="${pageInfo.list}" var="article" varStatus="articleStatus">
                                <div class="item" style="<c:if test="${articleStatus.count eq 1}">border-top: 0;</c:if>">
                                    <a class="user-icon">
                                        <img src="${PATH}static/images/${article.user.icon.url}" alt="">
                                    </a>
                                    <div class="explore-title">
                                        <p>
                                            <a href="${PATH}people/${article.user.userName}" class="user-name">${article.user.userName}</a>
                                            <span class="text-color-999">发表了文章 • ${fn:length(article.commentList)} 个评论 • ${article.readCount} 次浏览 •
                                                <fmt:formatDate value="${article.createTime}"  type="both" /></span>
                                        </p>
                                        <h4>${article.title}</h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="explore-foot">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <c:if test="${pageInfo.hasPreviousPage}">
                                    <li>
                                        <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
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
                                            <li><a href="${SERVER_REQUEST_URL}?pn=${curNum}">${curNum}</a></li>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <li>
                                        <a href="${SERVER_REQUEST_URL}?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div> <!-- explore-foot end -->

                </div>

            </div>

            <!-- 右侧栏 -->
            <div class="col-sm-12 col-md-3 aw-side-bar">


            </div>
        </div>

    </div>

</div>