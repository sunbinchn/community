<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">

    <div class="row">
        <div class="aw-content-wrap clearfix">

            <!-- 左侧内容栏 -->
            <div class="col-sm-12 col-md-9 aw-main-content">
                <h2 id = "category-list-h2">发现</h2>
                <ul class="nav nav-tabs aw-nav-tabs">
                    <li role="presentation" ><a href="">最新</a></li>
                    <li role="presentation" ><a href="">热门</a></li>
                    <li role="presentation" ><a href="">推荐</a></li>
                </ul>

                <div class="content-explore-list">
                    <div class="explore-body">
                        <div class="explore-common-list">
                            <c:forEach items="${pageInfo.list}" var="article" varStatus="articleStatus">
                                <div class="item" style="<c:if test="${articleStatus.count eq 1}">border-top: 0;</c:if>" data-id="${article.id}">
                                    <a class="user-icon">
                                        <img src="${PATH}static/images/${article.user.icon.url}" alt="">
                                    </a>
                                    <div class="explore-title">
                                        <p>
                                            <a href="${PATH}people/${article.user.userName}" class="user-name">${article.user.userName}</a>
                                            <span class="text-color-999">发表了文章 • ${fn:length(article.commentList)} 个评论 • ${fn:length(article.readUserList)} 次浏览 •
                                                <fmt:formatDate value="${article.createTime}"  type="both" /></span>
                                        </p>
                                        <h4><a href="${PATH}detail/get/${article.id}" target="_blank">${article.title}</a></h4>
                                    </div>

                                    <div class="explore-buttons">
                                        <%--<button type="button" class="btn btn-sm">--%>
                                            <%--<span class="glyphicon glyphicon-heart"></span>--%>
                                        <%--</button>--%>
                                        <button type="button" class="btn btn-sm loveButton">
                                            <span class="glyphicon glyphicon-heart-empty"></span>
                                            <span>${fn:length(article.loveUserList)}</span>
                                        </button>
                                        <%--<button type="button" class="btn btn-sm">--%>
                                            <%--<span class="glyphicon glyphicon-star"></span>--%>
                                        <%--</button>--%>
                                        <button type="button" class="btn btn-sm keepButton">
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                        </button>
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
                <div>

                    <h4>你可能感兴趣的人</h4>
                    <ul class="list-group">
                        <a class="list-group-item" href="#">
                            <img src="${PATH}static/images/user/user_icon_gril.png" style="margin-right: 10px; width: 30px;">腊肉土豆焖饭
                        </a>
                        <a class="list-group-item" href="#">
                            <img src="${PATH}static/images/user/user_icon_gril.png" style="margin-right: 10px; width: 30px;">腊肉土豆焖饭
                        </a>
                        <a class="list-group-item" href="#">
                            <img src="${PATH}static/images/user/user_icon_gril.png" style="margin-right: 10px; width: 30px;">腊肉土豆焖饭
                        </a>
                        <a class="list-group-item" href="#">
                            <img src="${PATH}static/images/user/user_icon_gril.png" style="margin-right: 10px; width: 30px;">腊肉土豆焖饭
                        </a>
                        <a class="list-group-item" href="#">
                            <img src="${PATH}static/images/user/user_icon_gril.png" style="margin-right: 10px; width: 30px;">腊肉土豆焖饭
                        </a>
                    </ul>
                </div>
            </div>
        </div>

    </div>

</div>