$(function () {
    var currentUserId = $("#user_id_input").val();
    var url = window.location.href.split('?')[0];
    var subUrl = url.substring(url.lastIndexOf('/')+1);
    if (subUrl == 'read') {
        $("#readLi").addClass('active');
    } else if (subUrl == 'love') {
        $("#loveLi").addClass('active');
    } else if (subUrl == 'keep') {
        $("#keepLi").addClass('active');
    } else if (subUrl == 'idol') {
        $("#idolLi").addClass('active');
    } else if (subUrl == 'fans') {
        $("#fansLi").addClass('active');
    } else if (subUrl == 'article') {
        $("#articleLi").addClass('active');
    } else if (subUrl == 'notPassOfArticle') {
        $("#notPassArticleLi").addClass('active');
    }
    init_event();

    function init_event() {
        $(".edit-my-profile").click(function () {
           window.location.href = '/community/userInfo/profile';
        });
        $(".list-user-relation-toggle").click(function () {
            if (_.isEmpty(currentUserId)) {
                $(".login-modal").modal('show');
                return false;
            }
            var targetUserId = $(this).attr('data-id');
            var that = $(this);
            $.ajax({
                url: '/community/userRelation/toggle/'+ targetUserId,
                type : "GET",
                success: function (result) {
                    if (result.success) {
                        if (result.message == 'cancel') {
                            that.text('关注');
                            that.addClass('btn-default');
                            that.removeClass('btn-success');
                        } else {
                            that.text('已关注');
                            that.addClass('btn-success');
                            that.removeClass('btn-default');
                        }
                    } else {

                    }
                }
            });
            return false;
        });
        $(".toggle-user-relation").click(function () {
            if (_.isEmpty(currentUserId)) {
                $(".login-modal").modal('show');
                return false;
            }
            var targetUserId = $("#targetUserId").val();
            var that = $(this);
            $.ajax({
                url: '/community/userRelation/toggle/'+ targetUserId,
                type : "GET",
                success: function (result) {
                    if (result.success) {
                        if (result.message === 'cancel') {
                            that.text('关注');
                            that.addClass('btn-default');
                            that.removeClass('btn-success');
                            $("#fansCount").text(parseInt($("#fansCount").text()) - 1);
                        } else {
                            that.text('已关注');
                            that.addClass('btn-success');
                            that.removeClass('btn-default');
                            $("#fansCount").text(parseInt($("#fansCount").text()) + 1);
                        }
                    } else {

                    }
                }
            })
        });
        // $("#readLi").click(function () {
        //     $("#readLi").removeClass('active');
        //     $("#loveLi").removeClass('active');
        //     $("#keepLi").removeClass('active');
        //     $(this).addClass('active');
        // });
        $(".loveButton").click(function () {
            var userId = $("#user_id_input").val();
            if (_.isEmpty(userId)) {
                $(".login-modal").modal('show');
            } else {
                var articleId = $($(this).parents('.item')[0]).attr('data-id');
                var $countSpan = $($(this).children('span')[0]);
                var $loveImg = $($(this).children('img')[0]);
                $.ajax({
                    url: '/community/userArticleLove/toggle?articleId=' + articleId,
                    type: 'GET',
                    success: function (result) {
                        if (result.success) {
                            if (result.message == 'insert') {
                                $countSpan.text(parseInt($countSpan.text())+1);
                                $loveImg.attr('src', '/community/static/images/love.png');
                            } else {
                                $countSpan.text(parseInt($countSpan.text())-1);
                                $loveImg.attr('src', '/community/static/images/unlove.png');
                            }

                        }
                    }
                })
            }
            return false;
        });
        $(".keepButton").click(function () {
            var userId = $("#user_id_input").val();
            if (_.isEmpty(userId)) {
                $(".login-modal").modal('show');
            } else {
                var articleId = $($(this).parents('.item')[0]).attr('data-id');
                var $keepImg = $($(this).children('img')[0]);
                $.ajax({
                    url: '/community/userArticleKeep/toggle?articleId=' + articleId,
                    type: 'GET',
                    success: function (result) {
                        if (result.success) {
                            if (result.message == 'insert') {
                                $keepImg.attr('src', '/community/static/images/keep.png');
                            } else {
                                $keepImg.attr('src', '/community/static/images/unkeep.png');
                            }
                        }
                    }
                })
            }
            return false;
        });
    }
})