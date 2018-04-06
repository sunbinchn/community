$(function () {
    init_event();


    function init_event() {
        $(".shutUpClass").click(function () {
            var userId = $($(this).parents("tr")[0]).attr('data-id');
            $.ajax({
                type: "GET",
                url: "/community/manage/user/shutUp/" + userId,
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            });
            return false;
        });
        $(".cancelShutUpClass").click(function () {
            var userId = $($(this).parents("tr")[0]).attr('data-id');
            $.ajax({
                type: "GET",
                url: "/community/manage/user/cancel/shutUp/" + userId,
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            });
            return false;
        });
        $(".banClass").click(function () {
            var userId = $($(this).parents("tr")[0]).attr('data-id');
            $.ajax({
                type: "GET",
                url: "/community/manage/user/ban/" + userId,
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            });
            return false;
        });
        $(".cancelBanClass").click(function () {
            var userId = $($(this).parents("tr")[0]).attr('data-id');
            $.ajax({
                type: "GET",
                url: "/community/manage/user/cancel/ban/" + userId,
                success: function (result) {
                    if (result.success) {
                        window.location.reload();
                    }
                }
            });
            return false;
        });
    }
});