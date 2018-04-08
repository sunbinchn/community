$(function(){
    init_event();

    function init_event() {
        $("#update-userInfo-btn").click(function() {
            var iconFile = $("#icon")[0].files[0];
            if (iconFile != undefined) {
                var size = iconFile.size;
                var fileName = iconFile.name;
                if (size > 5242880) {
                    $("#icon_error_span").text('文件不能大于5M');
                    return false;
                }
                if (!(fileName.endsWith('.jpg') || fileName.endsWith('.png'))) {
                    $("#icon_error_span").text('只支持jpg、png文件格式上传');
                    return false;
                }
            }
            if (_.isEmpty($("#email").val())) {
                $("#email_error_span").text('邮箱不能为空！');
                return false;
            }
        });
        $("#nickname").change(function () {
            if(!_.isEmpty($(this).val())) {
                var nickName = $(this).val();
                $.ajax({
                    url: '/community/userInfo/nickNameQuery?nickName=' + nickName,
                    type: 'GET',
                    success: function (result) {
                        if (result.success) {
                            $("#yesImg").attr('display','block');
                            $("#nickname_error_span").text('');
                        } else {
                            $("#yesImg").attr('display','none');
                            $("#nickname_error_span").text(result.message);
                        }
                    }
                })
            }
        });
        $("#email").change(function () {
            if (_.isEmpty($(this).val())) {
                $("#email_error_span").text('邮箱不能为空！');
            } else {
                $("#email_error_span").text('');
            }
        });
        $("#icon").change(function () {
            $("#icon_error_span").text('');
            var iconFile = $("#icon")[0].files[0];
            if (iconFile != undefined) {
                var size = iconFile.size;
                var fileName = iconFile.name;
                if (size > 5242880) {
                    $("#icon_error_span").text('文件不能大于5M');
                    return false;
                }
                if (!(fileName.endsWith('.jpg') || fileName.endsWith('.png'))) {
                    $("#icon_error_span").text('只支持jpg、png文件格式上传');
                    return false;
                }
            }
        });
    }

});