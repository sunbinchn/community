$(function () {

    init_event();

    function init_event() {
        $('#comment-sumbit-button').click(function() {
            var content = $(".comment-form .comment-input").val();
            var userId = $("#user_id_input").val();
            var path = window.location.pathname;
            var articleId = path.substr(path.lastIndexOf('/') +1);
            var target_comment_id ;
            var obj = {};
            obj.content = content;
            obj.article = {};
            obj.article.id = articleId;
            obj.user = {};
            obj.user.userId = userId;
            obj.targetComment = {};
            obj.targetComment.id = target_comment_id;
            // var data = 'content=' + content + '&' + 'user_id=' + userId + '&articleId=' + articleId + '&targetCommentId=' + target_comment_id;
            $.ajax({
                url: "/community/comment/insert",
                type: "post",
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(obj),
                success: function (result) {
                    if (result && result.success) {
                        var comment = result.data;
                        var lastItem = $('.article-comment-list .comment-item:last');
                        var commentItem = $('<div class="comment-item"></div>');
                        var commentHead = $('<div class="comment-head"></div>');
                        var commentBody = $('<div class="comment-body"></div>');
                        var commentFoot = $('<div class="comment-footer"></div>');
                        $('<a class="comment-img" href=""><img src="/community/static/images/'+comment.user.icon.url+'" alt="'+comment.user.userName+'"></a>').appendTo(commentHead);
                        $('<p><a href="">'+comment.user.userName+'</a></p>').appendTo(commentHead);
                        $('<div class="markitup-box">'+comment.content+'</div>’').appendTo(commentBody);
                        $('<div class="meta"><span class="pull-right text-color-999">'+new Date(comment.createTime).Format("yyyy-MM-dd HH:mm:ss")+'</span></div>').appendTo(commentFoot);
                        commentHead.appendTo(commentItem);
                        commentBody.appendTo(commentItem);
                        commentFoot.appendTo(commentItem);
                        lastItem.after(commentItem);
                        $('.comment-input').val('');
                    } else {
                        //评论失败，系统异常
                    }
                },
            });
        });
    }

});