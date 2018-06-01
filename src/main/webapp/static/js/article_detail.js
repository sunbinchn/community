$(function () {
    var userId = $("#user_id_input").val();

    init_event();

    function init_event() {
        //on事件可以保证动态添加的dom节点同样拥有这个事件
        $('body').on('click', ".comment-love-btn", function(){
            if (_.isEmpty(userId)) {
                $(".login-modal").modal('show');
                $("#username_or_email").focus();
                return false;
            }
            var $commentItem = $($(this).parents('.comment-item')[0]);
            var targetCommentId = $commentItem.attr('data-id');
            var data = 'commentId='+targetCommentId+'&userId='+userId;
            $.ajax({
                type : 'post',
                data : data,
                url : '/community/comment/addLoveCount',
                success: function(result) {
                    if(result.success) {
                        var $loveCountSpan = $($commentItem.find('.comment-love-btn').children()[1]);
                        if (result.data === 'delete') {
                            $loveCountSpan.text(parseFloat($loveCountSpan.text()) - 1);
                        } else if (result.data === 'insert') {
                            $loveCountSpan.text(parseFloat($loveCountSpan.text()) + 1);
                        }

                    }
                }
            })
        });
        $('body').on('click', '.inner-submit-button', function() {
            var textarea = $(this).siblings('textarea');
            var content = textarea.val().trim();
            if (_.isEmpty(content)) {
                return false;
            }
            content = xssCheck(content);
            var commentItem = $($(this).parents('.comment-item')[0]);
            var targetCommentId = commentItem.attr('data-id');
            var placeholder = textarea.attr('placeholder');
            if (!_.isEmpty(placeholder) && placeholder.startsWith('回复')) {
                targetCommentId = $('.inner-submit-button').siblings('textarea').attr('data-target-id');
            }
            if (!_.isEmpty(targetCommentId)) {
                var data = 'targetCommentId='+targetCommentId + '&userId=' + userId + '&content=' + content;
                $.ajax({
                    type : 'POST',
                    url : "/community/comment/nestComment/insert",
                    data : data,
                    success : function(result) {
                        if (result.success) {
                            var nestCommentBox = commentItem.children('.nest-comment-box');
                            var comment = result.data;
                            addNestCommentRecord(nestCommentBox, comment);
                            textarea.val('');
                            textarea.attr('placeholder','你想对ta说的话');
                        }
                    }
                })
            }
        });
        $('body').on('click','.comment-nest-comment', function() {
            var commentItem = $($(this).parents('.comment-item')[0]);
            var nestCommentBox = commentItem.children('.nest-comment-box');
            nestCommentBox.find('.inner-comment-list').html('');
            if (nestCommentBox.css('display') === 'none') { //打开
                nestCommentBox.css('display', 'block');
                var commentId = commentItem.attr('data-id');
                $.ajax({
                    type: "GET",
                    url: "/community/comment/nestCommentList/get/" + commentId,
                    success: function(result){
                        if (result.success) {
                            var commentList = result.data;
                            if (!_.isEmpty(commentList)) {
                                _.each(commentList, function(comment){
                                    addNestCommentRecord(nestCommentBox, comment);
                                });
                            }
                        }
                    }
                }); //ajax end
            } else { //关闭
                nestCommentBox.css('display', 'none');
            }

        });
        $('#detail_login_a').click(function() {
            $(".login-modal").modal('show');
            $("#username_or_email").focus();
        });
        $('#detail_register_a').click(function() {
            $(".register-modal").modal('show');
            $("#reg_username").focus();
        });
        $('.detail_login_a1').click(function() {
            $(".login-modal").modal('show');
            $("#username_or_email").focus();
        });
        $('.detail_register_a1').click(function() {
            $(".register-modal").modal('show');
            $("#reg_username").focus();
        });
        $('body').on('mouseover', '.comment-inner-content', function(){
            $(this).next('.inner-submit-button').removeAttr('disabled');
        });
        $('body').on('mouseout', '.comment-inner-content', function(){
            if (_.isEmpty($(this).val())) {
                $(this).next('.inner-submit-button').attr('disabled','disabled')
            } else {
                $(this).next('.inner-submit-button').removeAttr('disabled');
            }
        });
        $('body').on('mouseover', '.comment-input', function(){
            $('.comment-input').next('.submit-div').children('.submit-button').removeAttr('disabled');
        });
        $('body').on('mouseout', '.comment-input', function(){
            if (_.isEmpty($(this).val())) {
                $('.comment-input').next('.submit-div').children('.submit-button').attr('disabled','disabled')
            } else {
                $('.comment-input').next('.submit-div').children('.submit-button').removeAttr('disabled');
            }
        });
        $('body').on('click', '#comment-sumbit-button', function() {
            var content = $(".comment-form .comment-input").val();
            var path = window.location.pathname;
            var articleId = path.substr(path.lastIndexOf('/') +1);
            var target_comment_id ;
            var obj = {};
            obj.content = content = xssCheck(content);
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
                        // var lastItem = $('.article-comment-list .comment-item:last');
                        var commentItem = $('<div class="comment-item" data-id="'+ comment.id +'"></div>');
                        var commentHead = $('<div class="comment-head"></div>');
                        var commentBody = $('<div class="comment-body"></div>');
                        var commentFoot = $('<div class="comment-footer"></div>');
                        var nestCommentBox = $('<div class="nest-comment-box"><div class="inner-box"><div class="inner-comment-list"><div class="inner-comment-item"><div class="comment-head"><a class="comment-img" href=""><img src="/community/static/images/user/user_icon_boy.png" alt=""></a><p><a href=""></a></p></div><div class="comment-body"><div class="markitup-box"></div></div></div><div class="inner-comment-item"><div class="comment-head"><a class="comment-img" href=""><img src="/community/static/images/user/user_icon_boy.png" alt=""></a><p><a href=""></a></p></div><div class="comment-body"><div class="markitup-box"></div></div></div></div><textarea cols="2" rows="1" placeholder="你想对ta说的话" class="comment-inner-content" data-target-id=""></textarea><button type="button" class="btn btn-success inner-submit-button" disabled="disabled">评论</button></div></div>');
                        $('<a class="comment-img" href=""><img src="/community/static/images/'+comment.user.icon.url+'" alt="'+comment.user.userName+'"></a>').appendTo(commentHead);
                        $('<p><a href="">'+comment.user.userName+'</a></p>').appendTo(commentHead);
                        $('<div class="markitup-box">'+comment.content+'</div>’').appendTo(commentBody);
                        $('<div class="meta"><button type="button" class="btn btn-default btn-xs comment-love-btn"><span class="glyphicon glyphicon-triangle-top" style="color: #777573;"></span><span style="color: #1b1a19;">0</span></button><span class="comment-nest-comment"><span class="glyphicon glyphicon-comment" style="color: #737270;"></span><span id="nest-comment-span" style="color:#737171;">0条评论</span></span><span class="pull-right text-color-999">'+new Date(comment.createTime).Format("yyyy-MM-dd HH:mm:ss")+'</span></div>').appendTo(commentFoot);
                        commentHead.appendTo(commentItem);
                        commentBody.appendTo(commentItem);
                        commentFoot.appendTo(commentItem);
                        nestCommentBox.appendTo(commentItem);
                        // commentItem.appendTo($('.article-comment-list'));
                        // lastItem.after(commentItem);
                        $('.comment-form').before(commentItem);
                        $('.comment-input').val('');
                    } else {
                        //评论失败，系统异常
                    }
                }
            });
        });
    }
    function addNestCommentRecord(nestCommentBox, comment) {
        var innerCommentItemDiv = $('<div class="inner-comment-item" data-id="'+ comment.id +'"></div>');
        var innerCommentHeadDiv =  $('<div class="comment-head"></div>');
        var innerA = $('<a class="comment-img" href=""></a>');
        var innerAImg = $('<img src="/community/static/images/'+comment.user.icon.url+'" alt="'+ comment.user.userName +'">')
        innerAImg.appendTo(innerA);
        innerA.appendTo(innerCommentHeadDiv);
        var innerP = $('<p><a href="">'+ comment.user.userName +'</a></p>');
        innerP.appendTo(innerA);
        innerP.appendTo(innerCommentHeadDiv);
        var innerCommentBodyDiv = $('<div class="comment-body"><div class="markitup-box">'+ comment.content +'<span class="pull-right" style="color: #999;font-size: 12px;">'+dateFtt('yyyy-MM-dd hh:mm:ss',new Date(comment.createTime))+'</span></div></div>');
        innerCommentHeadDiv.appendTo(innerCommentItemDiv);
        innerCommentBodyDiv.appendTo(innerCommentItemDiv);
        innerCommentItemDiv.appendTo(nestCommentBox.find('.inner-comment-list'));
        nestCommentBox.find('.inner-comment-list').children().css('border-bottom','1px solid #f5f5f5');
        nestCommentBox.find('.inner-comment-list').children().last().css('border-bottom','none');
    };
    function dateFtt(fmt,date)
    {
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

});