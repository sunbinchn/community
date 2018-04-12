$(function () {
    var url = window.location.href.split('?')[0];
    var subUrl = url.substring(url.lastIndexOf('/')+1);
    if (subUrl == 'read') {
        $("#readLi").addClass('active');
    } else if (subUrl == 'love') {
        $("#loveLi").addClass('active');
    } else if (subUrl == 'keep') {
        $("#keepLi").addClass('active');
    }
    init_event();

    function init_event() {
        $(".edit-my-profile").click(function () {
           window.location.href = '/community/userInfo/profile';
        });
        $(".toggle-user-relation").click(function () {
            var targetUserId = $("#targetUserId").val();
            $.ajax({
                url: '/community/userRelation/toggle/'+ targetUserId,
                type : "GET",
                success: function (result) {
                    if (result.success) {
                        if (result.message == 'cancel') {
                            $(".toggle-user-relation").text('关注');
                            $("#fansCount").text(parseInt($("#fansCount").text()) - 1);
                        } else {
                            $(".toggle-user-relation").text('已关注');
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