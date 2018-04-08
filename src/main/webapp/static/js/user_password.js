$(function(){
    init_event();

    function init_event() {
        $("#update-password-btn").click(function(){
            var oldPassword = $("#oldPassword").val();
            var newPassword1 = $("#newPassword1").val();
            var newPassword2 = $("#newPassword2").val();
            if (_.isEmpty(oldPassword)) {
                $("#oldPassword_error_span").text('当前密码不能为空');
                return false;
            }
            if (_.isEmpty(newPassword1)) {
                $("#newPassword1_error_span").text('新密码不能为空');
                return false;
            }
            if (_.isEmpty(newPassword2)) {
                $("#newPassword2_error_span").text('确认密码不能为空');
                return false;
            }
            if (newPassword1 != newPassword2) {
                $("#newPassword2_error_span").text('两次密码不一致');
                return false;
            }
            var userVo = {};
            userVo.oldPassword = oldPassword;
            userVo.newPassword = newPassword1;
            $.ajax({
                url: '/community/userInfo/updatePassword',
                type: 'POST',
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(userVo),
                success: function (result) {
                    $("#oldPassword").text('');
                    $("#newPassword1").text('');
                    $("#newPassword2").text('');
                    if (result.success) {
                        alert('保存成功');
                    } else {
                        $("#oldPassword_error_span").text(result.message);
                    }
                }
            })
            return false;
        });
        $("#oldPassword").blur(function () {
            if (_.isEmpty($(this).val())) {
                $("#oldPassword_error_span").text('当前密码不能为空');
            } else {
                $("#oldPassword_error_span").text('');
            }
        });
        $("#newPassword1").blur(function () {
            if (_.isEmpty($(this).val())) {
                $("#newPassword1_error_span").text('新密码不能为空');
            } else {
                $("#newPassword1_error_span").text('');
            }
        });
        $("#newPassword2").blur(function () {
            if (_.isEmpty($(this).val())) {
                $("#newPassword2_error_span").text('确认密码不能为空');
            } else {
                $("#newPassword2_error_span").text('');
            }
        });
    }

});