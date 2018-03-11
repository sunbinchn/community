$(function () {
    init_category();
    init_event();
    init_show_type();


    function init_show_type() {
        var url = window.location.href.split('?')[0];
        var subUrl = url.substring(0,url.lastIndexOf('/'));
        var $showTypeList = $('.aw-nav-tabs > li > a');
        //$showTypeList.eq(0),$($showTypeList[0]),$($showTypeList)三种都可以操作，但是他们是不相等的，可以在debugger中看到,content,prevObject不尽相同
        $showTypeList.eq(0).attr('href', subUrl+'/latest');
        $showTypeList.eq(1).attr('href', subUrl+'/hot');
        $showTypeList.eq(2).attr('href', subUrl+'/recommend');
        if (url.endsWith("latest")) {
            $showTypeList.eq(0).parent().addClass('active');
        } else if (url.endsWith("hot")) {
            $showTypeList.eq(1).parent().addClass('active');
        } else if (url.endsWith("recommend")) {
            $showTypeList.eq(2).parent().addClass('active');
        }
    }

    function init_event() {
        //login in
        $("#model_login_btn").click(function () {
            var username_or_email = $("#username_or_email").val();
            var password = $("#password").val();
            var data = 'userNameOrEmail=' + username_or_email + '&' + 'password=' + password;
            $.ajax({
                url: "/community/login/signin",
                type: "post",
                data: data,
                success: function (result) {
                    if (result.success) {
                        window.location.href = '/community/index.html'
                    }
                },
            });
            return false;
        });
        $("#model_register_btn").click(function () {
            var reg_username = $("#reg_username").val();
            var reg_email = $("#reg_email").val();
            var reg_password = $("#reg_password").val();
            var data = 'userName=' + reg_username + '&' + 'email=' + reg_email + '&password=' + reg_password;
            $.ajax({
                url: "/community/login/register",
                type: "post",
                data: data,
                success: function (result) {
                    if (result.success) {
                        $(".register-modal").modal('hide');
                        $(".login-modal").modal('show');
                    }
                },
            });
            return false;
        });
        $(".login-modal").on("shown.bs.modal",function (e) {
            $("#username_or_email").focus();
        });
        $(".register-modal").on("shown.bs.modal",function (e) {
            $("#reg_username").focus();
        });
        $("#model_login_to_register").click(function () {
            $(".login-modal").modal('hide');
            $(".register-modal").modal('show');
        });
        $("#model_register_to_login").click(function () {
            $(".register-modal").modal('hide');
            $(".login-modal").modal('show');
        })
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
    }
})

