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

    $(function(){
        $("#lvInput").blur(function(){
            if($("#lvInput").val()!=""){
                $api.doPost(getRootPath()+'/wages/selectWagesByLv.action' , {'lv':$("#lvInput").val()} , function(res){
                    if (res.data) {
                        status = 1;
                        layer.msg('已经有此等级了，不要继续添加', {time: 1000});
                    } else {
                        status = 0;
                        layer.msg('此等级可以添加', {time: 1000});
                    }
                })
            }
        })
    });
    form.on('submit(*)', function (data) {
        layer.load(2,{
            time: 1000,
            shade:1,
            shadeClose:false,
        })
        if (status == 0) {
            var req = {
                'lv': data.field.lv,
                'wnumber': data.field.wnumber,
            }
            $api.doPost(getRootPath() + '/wages/addUWages.action', req, function (res) {
                if (res.data) {
                    $("#lvInput").attr("value",'');
                    $("#wnumberInput").attr("value",'');
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
            $("#lvInput").attr("value",'');
            $("#wnumberInput").attr("value",'');
            layer.msg('已经有此部门了，不能提交', {time: 1000});
        }
        return false;//如果不给就会发生error问题
    })
});