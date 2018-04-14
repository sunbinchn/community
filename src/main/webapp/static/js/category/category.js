$(function () {
    var query = $("#queryInput").val();
    var server_request_url = $("#server_request_url").val();
    init_category();
    init_event();
    init_show_type();
    init_UIShow();


    function init_UIShow() {
        if (!_.isEmpty(query)) {
            $("#head-criteria-input").val(query);
        }
    }

    function init_show_type() {
        var url = window.location.href.split('?')[0];
        var subUrl = url.substring(0,url.lastIndexOf('/'));
        var $showTypeList = $('.aw-nav-tabs > li > a');
        //$showTypeList.eq(0),$($showTypeList[0]),$($showTypeList)三种都可以操作，但是他们是不相等的，可以在debugger中看到,content,prevObject不尽相同
        $showTypeList.eq(0).attr('href', subUrl+'/latest');
        $showTypeList.eq(1).attr('href', subUrl+'/hot');
        $showTypeList.eq(2).attr('href', subUrl+'/recommend');
        if (url.endsWith("latest") || url.endsWith("latest#")) {
            $showTypeList.eq(0).parent().addClass('active');
        } else if (url.endsWith("hot") || url.endsWith("hot#")) {
            $showTypeList.eq(1).parent().addClass('active');
        } else if (url.endsWith("recommend") || url.endsWith("recommend#")) {
            $showTypeList.eq(2).parent().addClass('active');
        }
    }

    function init_event() {
        $('#head-criteria-input').bind('keypress', function (event) {
            if (event.keyCode == "13") { //enter
                var queryVal = $("#head-criteria-input").val();
                if (_.isEmpty(queryVal)) {
                    window.location.href = server_request_url;
                } else {
                    window.location.href = server_request_url + '?query='+queryVal;
                }
                return false;
            }
        });
        //article title click
        $(".explore-title > h4 > a").click(function() {
            $(this).addClass("color-999");
        });
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

    function init_category() {
        var pathname = window.location.pathname;
        if (pathname.indexOf('explore/all') > -1 || pathname.indexOf('index.html') > -1) {
            $("#category-list-id > li:eq(0)").addClass('active');
        } else if (pathname.indexOf('explore/category-1') > -1) {
            $("#category-list-id > li:eq(1)").addClass('active');
        } else if (pathname.indexOf('explore/category-2') > -1) {
            $("#category-list-id > li:eq(2)").addClass('active');
        } else if (pathname.indexOf('explore/category-3') > -1) {
            $("#category-list-id > li:eq(3)").addClass('active');
        } else if (pathname.indexOf('explore/category-4') > -1) {
            $("#category-list-id > li:eq(4)").addClass('active');
        } else if (pathname.indexOf('explore/category-5') > -1) {
            $("#category-list-id > li:eq(5)").addClass('active');
        } else if (pathname.indexOf('explore/category-6') > -1) {
            $("#category-list-id > li:eq(6)").addClass('active');
        } else if (pathname.indexOf('explore/category-7') > -1) {
            $("#category-list-id > li:eq(7)").addClass('active');
        } else if (pathname.indexOf('explore/category-8') > -1) {
            $("#category-list-id > li:eq(8)").addClass('active');
        }
        $("#category-list-h2").text($("#category-list-id .active > a").html());
    }
})

