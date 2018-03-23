$(function () {
    init_event();
    init_date();
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
                        location.reload();
                    } else {
                        $('#login_error_msg').text(result.message);
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
                    } else {
                        $('#register_error_msg').text(result.message);
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
    function init_date() {
        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    }
})

