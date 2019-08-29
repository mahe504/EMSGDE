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

    var status = 0;

    $(function () {
        $("#dnameInput").blur(function () {
            if ($("#dnameInput").val() != '') {
                if (!new RegExp("^[\u4E00-\u9FA5a-zA-Z0-9_]*$").test($("#dnameInput").val())) {
                    layer.msg('不要瞎填哦！' , {time:1000});
                } else {
                    var req = {
                        dname: $("#dnameInput").val(),
                    }
                    $api.doPost(getRootPath() + '/department/selectDepartmentByDname.action', req, function (res) {
                        if (res.data) {
                            status = 1;
                            layer.msg('已经有此部门了，不要继续添加', {time: 1000});
                        } else {
                            status = 0;
                            layer.msg('此部门可以添加', {time: 1000});
                        }
                    })
                }
            }
        })
    })
    form.on('submit(*)', function (data) {
        layer.load(2,{
            time: 1000,
            shade:1,
            shadeClose:false,
        })
        if (status == 0) {
            var req = {
                'dname': data.field.dname,
                'maxnumber': data.field.maxnumber,
            }
            $api.doPost(getRootPath() + '/department/addDepartment.action', req, function (res) {
                if (res.data) {
                    layer.msg('添加成功', {time: 1000}, function () {
                        layer.closeAll();
                    });
                } else {
                    layer.msg('添加失败呦', {time: 1000}, function () {
                        layer.closeAll("loading");
                    });
                }
            });
        } else {
            layer.msg('已经有此部门了，不能提交', {time: 1000});
        }
        return false;//如果不给就会发生error问题
    })
});