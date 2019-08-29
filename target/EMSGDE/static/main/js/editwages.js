layui.config({//基础目录
    base: getRootPath() + '/static/common/js/'//定义基目录
}).extend({
    $tool: 'tool',
    $api: 'api',
    ajaxAOP: 'ajaxAOP',
}).use(['form', 'layer', '$tool', '$api', 'ajaxAOP', 'table', 'laypage', 'jquery', 'element'], function () {
    var form = layui.form,
        layer = parent.layer === 'undenfined' ? layui.layer : parent.layer,
        $tool = layui.$tool,
        $api = layui.$api,
        ajaxAOP = layui.ajaxAOP,
        table = layui.table,
        pageNum = 0,
        dataLength = 0,
        laypage = layui.laypage,
        jquery = layui.jquery,
        element = layui.element;

    var oldLv, oldWnumber

    function getURLParams(param) {
        layer.load(2);
        var params = $tool.getQueryParam();
        return params[param];
    }

    $(function () {
        $api.doPost(getRootPath() + '/wages/selectWagesById.action', {'id': getURLParams("id")}, function (res) {
            if (res.data != null) {
                oldLv = res.data.lv, oldWnumber = res.data.wnumber,
                    form.val('editwages', {
                        'id': getURLParams("id"),
                        'lv': res.data.lv,
                        'wnumber': res.data.wnumber,
                    });

            } else {
                layer.msg("数据获取失败！,请重新操作", {time: 1000}, function () {
                    layer.closeAll();
                });
            }
        })
    });
    form.on('submit(*)', function (obj) {
        layer.load();
        if (oldLv == $("#lvInput").val() && oldWnumber == $("#wnumberInput").val()) {
            layer.msg("你还没有修改内容不能提交", {time: 1000});
            layer.closeAll("loading");
        } else {
            /*$api.doPost(getRootPath() + '/wages/selectWagesByLv.action', {'lv': $("#lvInput").val()}, function (res) {
                if (res.data) {
                    status = 1;
                    layer.msg('已经有此等级了，不能提交修改', {time: 1000} , function(){
                        layer.closeAll("loading");
                    });
                } else {*/
            status = 0;
            var req = {
                'id': obj.field.id,
                'lv': obj.field.lv,
                'wnumber': obj.field.wnumber,
            }
            $api.doPost(getRootPath() + '/wages/updateWagesById.action', req, function (res) {
                if (res) {
                    layer.msg('修改成功', {time: 1000}, function () {
                        layer.closeAll();
                    })
                }
                /*}*/  /*  });*/

            })
        }
        return false;
    })
});