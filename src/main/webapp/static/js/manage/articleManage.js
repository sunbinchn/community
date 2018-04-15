$(function () {
    var criteria = $("#criteriaInput").val();
    var server_request_url = $("#server_request_url").val();
    initUIShow();
    init_event();

    function initUIShow() {
        var url = window.location.href.split('?')[0];
        var showType = url.substr(url.lastIndexOf('/')+1,url.length)
        if (showType === 'index') {
            $("#unchecked").prop("checked",true);
        } else if (showType === 'deleted') {
            $("#deleted").prop("checked",true);
        } else if (showType === 'checked') {
            $("#checked").prop("checked",true);
        } else if (showType === 'recommend') {
            $("#recommend").prop("checked",true);
        }  else if (showType === 'all') {
            $("#all").prop("checked",true);
        }
        if (!_.isEmpty(criteria)) {
            $("#criteria").val(criteria);
            $("#criteria-button").removeAttr('disabled');
        }
        $('#criteria').focus();
    }
    function init_event() {
        $('#criteria').bind('keypress', function (event) {
            if (event.keyCode == "13") { //enter
                $("#criteria-button").click();
            }
        });
        $("#criteria-button").click(function () {
            var criteriaVal = $("#criteria").val();
            if (_.isEmpty(criteriaVal)) {
                window.location.href = server_request_url;
            } else {
                window.location.href = server_request_url + '?criteria='+criteriaVal;
            }
        });
        $("#unchecked").click(function () {
            window.location.href = '/community/manage/article/index';
        });
        $("#deleted").click(function () {
            window.location.href = '/community/manage/article/deleted';
        });
        $("#checked").click(function () {
            window.location.href = '/community/manage/article/checked';
        });
        $("#recommend").click(function () {
            window.location.href = '/community/manage/article/recommend';
        });
        $("#all").click(function () {
            window.location.href = '/community/manage/article/all';
        });
        $(".toggle-pass").click(function () {
            var articleId = $($(this).parents('tr')[0]).attr('data-id');
            $.ajax({
                url: '/community/manage/article/togglePass/' + articleId,
                type: 'GET',
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            })
        });
        $(".toggle-recommend").click(function () {
            var articleId = $($(this).parents('tr')[0]).attr('data-id');
            $.ajax({
                url: '/community/manage/article/toggleRecommend/' + articleId,
                type: 'GET',
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            })
        });
        $(".delete-article").click(function () {
            var articleId = $($(this).parents('tr')[0]).attr('data-id');
            $.ajax({
                url: '/community/manage/article/delete/' + articleId,
                type: 'GET',
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            })
        });
        // $("#criteria").mouseover(function () {
        //     $("#criteria-button").removeAttr('disabled');
        // });
        // $("#criteria").mouseout(function () {
        //     if (_.isEmpty($(this).val())) {
        //         $("#criteria-button").attr('disabled','disabled')
        //     } else {
        //         $("#criteria-button").removeAttr('disabled');
        //     }
        // });
    }
});