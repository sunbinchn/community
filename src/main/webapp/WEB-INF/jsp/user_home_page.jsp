<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <link href="${PATH}static/css/user_home_page.css" rel="stylesheet">
    <link href="${PATH}static/css/content.css" rel="stylesheet">
    <%@ include file="common.jsp" %>
    <script src="${PATH}static/js/user_home_page.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <input type="text" name="targetUserId" id="targetUserId" hidden="true" value="${userInfo.userId}">
    <!-- 左侧内容栏 -->
    <div class="col-sm-12 col-md-9 user-info-panel">
        <div class="user-icon-div">
            <img src="${PATH}static/images/${userInfo.icon.url}" alt="" style="width: 100px"/>
        </div>
        <div class="user-info-div">
            <h3>${userInfo.userName}</h3>
            <span>${userInfo.signature}</span>
        </div>
        <div>
            <c:choose>
                <c:when test="${sessionScope.userId eq userInfo.userId}">
                    <button type="button" class="btn btn-primary edit-my-profile">编辑个人资料</button>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${userRelationVo.isIdolOfCurrentUser}">
                            <button type="button" class="btn btn-primary toggle-user-relation">已关注</button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-primary toggle-user-relation">关注</button>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!-- 右侧栏 -->
    <div class="col-sm-12 col-md-3" style="margin-top: 30px;border-bottom: 1px solid #EDEDED;">
        <div class="half-div" style="border-right: 1px solid #EDEDED;">
            <a>
                <div>关注了</div>
                <div><span  id="idolCount">${userRelationVo.idolCount}</span></div>
            </a>
        </div>
        <div class="half-div">
            <a>
                <div>关注者</div>
                <div><span id="fansCount">${userRelationVo.fansCount}</span></div>
            </a>
        </div>
    </div>
    <!-- 左侧内容栏 -->
    <div class="col-sm-12 col-md-9 aw-main-content" style="margin-top: -30px">
        <ul class="nav nav-tabs aw-nav-tabs">
            <li role="presentation"  id="readLi"><a href="/community/userHomePage/${userInfo.userId}/read">浏览记录<span class="badge">${readCount}</span></a></li>
            <li role="presentation"  id="loveLi"><a href="/community/userHomePage/${userInfo.userId}/love">喜欢<span class="badge">${loveCount}</span></a></li>
            <li role="presentation"  id="keepLi"><a href="/community/userHomePage/${userInfo.userId}/keep">收藏<span class="badge">${keepCount}</span></a></li>
            <li role="presentation"  id="articleLi"><a href="/community/userHomePage/${userInfo.userId}/article">文章<span class="badge">${articleCount}</span></a></li>
            <li role="presentation"  id="idolLi"><a href="/community/userHomePage/${userInfo.userId}/idol">关注<span class="badge">${userRelationVo.idolCount}</span></a></li>
            <li role="presentation"  id="fansLi"><a href="/community/userHomePage/${userInfo.userId}/fans">粉丝<span class="badge">${userRelationVo.fansCount}</span></a></li>
        </ul>

        <div class="content-explore-list">
            <div class="explore-body">
                <div class="explore-common-list">
                    <c:forEach items="${pageInfo.list}" var="article" varStatus="articleStatus">
                        <div class="item" style="<c:if test="${articleStatus.count eq 1}">border-top: 0;</c:if>" data-id="${article.id}">
                            <a class="user-icon" href="${PATH}userHomePage/${article.user.userId}/read" target="_blank">
                                <img src="${PATH}static/images/${article.user.icon.url}" alt="">
                            </a>
                            <div class="explore-title">
                                <p>
                                    <a href="${PATH}userHomePage/${article.user.userId}/read" class="user-name" target="_blank">${article.user.userName}</a>
                                    <span class="text-color-999">发表了文章 • <a href="${PATH}detail/get/${article.id}#comment-anchor" target="_blank">${fn:length(article.commentList)}</a> 个评论 • ${fn:length(article.readUserList)} 次浏览 •
                                                <fmt:formatDate value="${article.createTime}"  type="both" /></span>
                                </p>
                                <h4><a href="${PATH}detail/get/${article.id}" target="_blank">${article.title}</a></h4>
                            </div>

                            <div class="explore-buttons">
                                <button type="button" class="btn btn-sm loveButton">
                                    <c:choose>
                                        <c:when test="${article.isLovedCurrentUser}">
                                            <img src="${PATH}static/images/love.png" style="vertical-align: baseline;">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${PATH}static/images/unlove.png" style="vertical-align: baseline;">
                                        </c:otherwise>
                                    </c:choose>
                                    <span>${fn:length(article.loveUserList)}</span>
                                </button>
                                <button type="button" class="btn btn-sm keepButton">
                                    <c:choose>
                                        <c:when test="${article.isKeepCurrentUser}">
                                            <img src="${PATH}static/images/keep.png" style="vertical-align: baseline;">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${PATH}static/images/unkeep.png" style="vertical-align: baseline;">
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="explore-foot">
            <c:if test="${pageInfo.pages gt 1}">
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
            </c:if>
            </div> <!-- explore-foot end -->

        </div>

    </div>

    <!-- 右侧栏 -->
    <div class="col-sm-12 col-md-3 aw-side-bar">
    </div>
</div>
</div>
</body>
</html>
