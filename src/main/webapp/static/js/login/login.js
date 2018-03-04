// document.getElementById("name").focus();
$(function () {
    $("#submit").click(function(){
        var userNameOrEmail = $("#username_or_email").val();
        var password = $("#password").val();
        var data = 'userNameOrEmail=' + userNameOrEmail + '&' + 'password=' + password;
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
    })
    // function login() {
    //
    // }
});