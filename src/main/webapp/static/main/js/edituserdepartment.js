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
    var oldDnameId;
    var usercount;

    function getURLParams(param) {
        var params = $tool.getQueryParam();
        return params[param];
    }

    $(function () {
        var req = {
            'uid': getURLParams("uid"),
        };
        $api.doPost(getRootPath() + '/user/selectUserIsDepartment.action', req, function (res) {
            oldDnameId = res.data.did;
        });
        $api.doPost(getRootPath() + '/user/userSelectDepartment.action', {}, function (res) {
            if (res.data != null) {
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].maxnumber == res.data[i].usercount) {
                        if (oldDnameId == res.data[i].id) {
                            $("#dname").append('<option value="' + res.data[i].id + '"' + 'disabled selected>' + res.data[i].dname + '</option>');
                        } else {
                            $("#dname").append('<option value="' + res.data[i].id + '"' + ' disabled>' + res.data[i].dname + "(员工已满)" + '</option>');
                        }
                    } else if (oldDnameId == res.data[i].id) {
                        $("#dname").append('<option value="' + res.data[i].id + '"' + 'selected>' + res.data[i].dname + '</option>');
                    } else {
                        $("#dname").append('<option value="' + res.data[i].id + '"' + '>' + res.data[i].dname + '</option>');
                    }
                }
                form.render('select');
            }
        })
    });
    form.on('submit(*)' , function(data){
        if(oldDnameId == data.field.dname){
            layer.msg('你没有修改不能提交', {time : 1000});
        }else{
            var req = {
                'uid' : getURLParams("uid"),
                'did' : data.field.dname,
            }
            $api.doPost(getRootPath()+'/user/updateUserIsDepartment.action' , req , function(res){
                if(res){
                    layer.msg('修改成功' , {time : 1000} , function(){
                        layer.closeAll();
                    })
                }else{
                    layer.msg('修改失败' , {time : 1000} , function(){
                        layer.closeAll();
                    })
                }
            } )
        }
        return false;
    })
});