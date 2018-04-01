
$(function () {

    var ue = UE.getEditor('container');
    ue.addListener("ready", function () {
        query_article();
        init_event();
    });
    function query_article() {
        var params = getRequest();
        if (!_.isEmpty(params) && !_.isEmpty(params.articleId)) {
            $.ajax({
                url: "/community/writeArticle/get?articleId=" + params.articleId,
                type: "get",
                success: function (result) {
                    $("#select-original").val(result.original);
                    $("#titleInput").val(result.title);
                    $("#container").attr('data-id',result.articleId);
                    $($(".nav-pills > li")[result.articleTypeId -1]).addClass('active')
                    ue.setContent(result.content);
                }
            })
        }
    }
    function init_event() {
        $(".nav-pills > li").click(function(){
            $(".nav-pills > li").removeClass('active');
            $(this).addClass('active');
            return false;
        });
        $("#article-sumbit-button").click(function () {
            var data = {};
            data.original = $("#select-original").val() == -1 ? null : $("#select-original").val();
            data.title = $("#titleInput").val();
            data.content = ue.getContent();
            data.articleTypeId = $(".nav-pills > li").filter('.active').attr('data-id');
            data.articleId = $("#container").attr('data-id');
            if (_.isEmpty(data.original)) {
                $("#error-msg-span").text('文章类型不能为空');
                return false;
            }
            if (_.isEmpty(data.title)) {
                $("#error-msg-span").text('文章标题不能为空');
                return false;
            }
            if (_.isEmpty(data.content)) {
                $("#error-msg-span").text('文章内容不能为空');
                return false;
            }
            if (_.isEmpty(data.articleTypeId)) {
                $("#error-msg-span").text('文章类别不能为空');
                return false;
            }
            $.ajax({
                url: "/community/writeArticle/save",
                type: "post",
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success: function (result) {
                    if (result.success) {
                        $("#container").attr('data-id',result.message);
                        window.open("/community/detail/get/" + result.message);
                    }
                }
            });
            return false;
        });
    }
    function getRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

});