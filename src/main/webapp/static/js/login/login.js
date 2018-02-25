// document.getElementById("name").focus();
$(function () {
    $("#submit").click(function(){
        var userName = $("#name").val();
        var password = $("#password").val();
        var data = 'userName=' + userName + '&' + 'password=' + password;
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