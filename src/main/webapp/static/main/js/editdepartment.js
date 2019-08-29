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
    var oldDname, oldMaxnumber, oldUsercount;

    function getURLParams(param) {
        layer.load(2);
        var params = $tool.getQueryParam();
        return params[param];
    }

    $api.doPost(getRootPath() + '/department/selectDepartmentById.action', {id: getURLParams("id")}, function (res) {
        oldDname = res.data.dname , oldMaxnumber = res.data.maxnumber;
        layer.closeAll("loading");
        form.val('editdepartment', {
            id: getURLParams("id"),
            dname: res.data.dname,
            maxnumber: res.data.maxnumber,
        });
    })
    form.on('submit(*)', function (data) {
        if (Number(data.field.maxnumber) <Number(getURLParams("usercount"))) {
            layer.msg('现在的员工数量已经超过最多数量!不能提交!', {
                time: 1000
            }, function () {
                layer.closeAll("loading");
            });
            return false;
        } else if ($("#dnameInput").val() == oldDname && $("#maxnumberInput").val() == oldMaxnumber) {
            layer.msg('你还没有修改内容，不能提交', {
                time: 1000
            }, function () {
                layer.closeAll("loading");
            });
            return false;
        } else {
            $api.doPost(getRootPath() + '/department/editDepartmentById.action', {
                id: data.field.id,
                dname: data.field.dname,
                maxnumber: data.field.maxnumber
            }, function (res) {
                oldDname = data.field.dname, oldMaxnumber = data.field.maxnumber;
                if (res) {
                    layer.msg('修改成功', {time: 1000}, function () {
                        layer.closeAll();
                    })
                }
            })
        }
        return false;
    })

});