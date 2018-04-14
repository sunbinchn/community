$(function () {
    init_event();


    function init_event() {
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
    }
});