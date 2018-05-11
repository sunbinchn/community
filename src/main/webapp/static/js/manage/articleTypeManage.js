$(function () {
    var UPDATE = '修改';
    var SAVE = '保存';
    init_event();


    function init_event() {
        $("#add-button").click(function () {
            $('.addArticleType-modal').modal('show');
            return false;
        });
        $("#insert-articleType-button").click(function () {
            var obj = {};
            obj.name = $("#add-typeName").val();
            obj.priority = $("#add-typePriority").val();
            obj.description = $("#add-description").val();
            if (_.isEmpty(obj.name)) {
                $("#error-msg-label2").text('类型名称不能为空');
                return false;
            }
            if (_.isEmpty(obj.priority)) {
                $("#error-msg-label2").text('优先级不能为空');
                return false;
            }
            $("#error-msg-label2").text('');
            $.ajax({
                url: "/community/manage/articleType/insert",
                type: "post",
                contentType : 'application/json;charset=utf-8',
                data: JSON.stringify(obj),
                success: function (result) {
                    if (result.success) {
                        location.reload();
                    } else {
                        alert(result.message);
                    }
                }
            });
            return false;
        });
        $("#add-typeName").change(function () {
            var name = $("#add-typeName").val();
            if (!_.isEmpty(name)) {
                $.ajax({
                    url: "/community/manage/articleType/findByName?name="+name,
                    type: "get",
                    contentType : 'application/json;charset=utf-8',
                    success: function (result) {
                        if (result.success) {
                            $("#error-msg-label2").text(result.message);
                        } else {
                            $("#error-msg-label2").text('');
                        }
                    }
                });
            }
        });

        $(".update-name").click(function () {
            if($(this).text() === UPDATE) {
                var name = $(this).prev().text();
                $(this).prev().remove();
                $(this).before($('<input value="'+ name +'"/>'));
                $(this).text(SAVE);
            } else if($(this).text() === SAVE) {
                var name = $(this).prev().val();
                if (_.isEmpty(name)) {
                    alert('类型名称不能为空,请重新输入！');
                    return false;
                }
                var $button = $(this);
                var obj = {};
                obj.name = name;
                obj.id = $button.parent().parent().attr('data-id');
                $.ajax({
                    url: "/community/manage/articleType/updateName",
                    type: "post",
                    contentType : 'application/json;charset=utf-8',
                    data: JSON.stringify(obj),
                    success: function (result) {
                        if (result.success) {
                            $button.prev().remove();
                            $button.before($('<span>'+ name +'</span>'));
                            $button.text(UPDATE);
                        } else {
                            alert(result.message);
                        }
                    }
                });
                return false;
            }
        });
        $(".update-description").click(function () {
            if($(this).text() === UPDATE) {
                var value = $(this).prev().text();
                $(this).prev().remove();
                $(this).before($('<input value="'+ value +'"/>'));
                $(this).text(SAVE);
            } else if($(this).text() === SAVE) {
                var value = $(this).prev().val();
                var $button = $(this);
                var obj = {};
                obj.description = value;
                obj.id = $button.parent().parent().attr('data-id');
                $.ajax({
                    url: "/community/manage/articleType/updateDescription",
                    type: "post",
                    contentType : 'application/json;charset=utf-8',
                    data: JSON.stringify(obj),
                    success: function (result) {
                        if (result.success) {
                            $button.prev().remove();
                            $button.before($('<span>'+ value +'</span>'));
                            $button.text(UPDATE);
                        } else {
                            alert(result.message);
                        }
                    }
                });
            }
            return false;
        });
        $("#add-typePriority").keyup(onlyIntegerFunction);
        function onlyIntegerFunction() {
            if (this.value.length == 1) {
                this.value = this.value.replace(/[^1-9]/g, "")
            } else {
                this.value = this.value.replace(/\D/g, "")
            }
        };
        $(".update-priority").click(function () {
            if($(this).text() === UPDATE) {
                var value = $(this).prev().text();
                $(this).prev().remove();
                var $input = $('<input type="text" value="'+ value +'"/>');
                $input.keyup(onlyIntegerFunction);
                $(this).before($input);
                $(this).text(SAVE);
            } else if($(this).text() === SAVE) {
                var value = $(this).prev().val();
                var $button = $(this);
                var obj = {};
                obj.priority = value;
                obj.id = $button.parent().parent().attr('data-id');
                $.ajax({
                    url: "/community/manage/articleType/updatePriority",
                    type: "post",
                    contentType : 'application/json;charset=utf-8',
                    data: JSON.stringify(obj),
                    success: function (result) {
                        if (result.success) {
                            $button.prev().remove();
                            $button.before($('<span>'+ value +'</span>'));
                            $button.text(UPDATE);
                        } else {
                            alert(result.message);
                        }
                    }
                });
            }
            return false;
        });
        //
    }
});