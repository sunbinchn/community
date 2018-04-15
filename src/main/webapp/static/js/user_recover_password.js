$(function () {
    init_event();

    function init_event() {
        $("#update-password-btn").click(function () {
            var newPassword = $("#newPassword").val();
            var newPassword1 = $("#newPassword1").val();
            if(_.isEmpty(newPassword)) {
                $("#newPassword-error-span").text("密码不能为空");
                return false;
            }
            if(_.isEmpty(newPassword1)) {
                $("#newPassword1-error-span").text("密码不能为空");
                return false;
            }
            if(newPassword != newPassword1) {
                $("#newPassword1-error-span").text("两次密码不一致");
                return false;
            }
            var data = {};
            data.password = newPassword;
            data.token = $("#tokenInput").val();
            $.ajax({
                url : '/community/userRecoverPassword/update',
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success : function (result) {
                    if (result.success) {
                        $("#update-password-btn").attr("disabled","disabled");
                        $("#update-success-label").text('您已成功更新密码，3s后将跳转到首页！');
                        setTimeout("location ='/community/explore/all/latest'",3000);
                    } else {
                        $("#newPassword-error-span").text(result.message);
                    }
                }
            });
            return false;
        });
        $("#recover-password-btn").click(function () {
            var userName = $("#userName").val();
            var email = $("#email").val();
            if (_.isEmpty(userName)) {
                $("#userName-error-span").text('');
                return false;
            }
            if (_.isEmpty(email)) {
                $("#email-error-span").text('');
                return false;
            }
            var data = {};
            data.userName = userName;
            data.email = email;
            $.ajax({
                url : '/community/userRecoverPassword/perRequest',
                type : 'POST',
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(data),
                success : function (result) {
                    if (result.success) {
                        $("#recover-password-btn").attr("disabled","disabled");
                        $("#email-success-label").text('发送邮件成功，请注意查收后，根据邮件内容提示进行操作！')
                    } else {
                        $("#userName-error-span").text(result.message);
                    }
                }
            });
            return false;
        });
    }

});