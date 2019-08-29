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

    function getURLParams(param) {
        layer.load(2);
        var params = $tool.getQueryParam();
        return params[param];
    }

    var gid = getURLParams("id");

    var tableIns = table.render({
        elem: '#showuser',
        height: '390',
        url: getRootPath() + "/man/selectManagerAllUser.action?gid=" + gid,
        limit: 5,
        totalRow: true,
        limits: [5, 10, 15, 20, 25, 30],
        page: {
            prev: '上一页',
            next: '下一页',
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh', 'skip']
        }
        , title: '角色列表'
        , autoSort: false
        , page: true
        , cols: [[
             {field: 'uid', title: 'ID' , width:'20%'}
            , {field: 'username', title: '用户名'}
            , {field: 'email', title: '邮箱',}
            , {field: 'gender', title: '性别'}
            , {field: 'realname', title: '真实姓名'}
            , {field: 'address', title: '地址'}
            , {field: 'phone', title: '手机号'}
            , {field: 'ecode', title: '邮编'}
            , {
                field: 'createtime', title: '创建时间', templet: function (time) {
                    return $tool.getDatetime(time.createtime)
                }
            }
            , {fixed: 'right', title: '操作', align: 'center', width: '14%', toolbar: '#toolBar'}
        ]],
        done: function (res, curr, count) {
            layer.closeAll("loading");
            var brforeCurr = curr; // 获得当前页码
            var dataLength = res.data.length; // 获得当前页的记录数
            var count = res.count; // 获得总记录数
            if (dataLength == 0 && count != 0) { //如果当前页的记录数为0并且总记录数不为0
                table.reload("showuser", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
        }
    });

    form.on('submit(quertUser)' , function(obj){
        tableIns.reload('showuser' , {
            where:{
                'username' : obj.field.username,
            }
        });
    });

    table.on('tool(showuser)' , function (obj){
        switch(obj.event){
            case 'delete' :
                deleteGroup(obj.data);
                break;
        }
    })
    function deleteGroup(data){
        layer.confirm('你确认要卸任吗', function (confirmIndex) {
            layer.load();
            var req = {
                'uid':data.uid,
            }
            $api.doPost(getRootPath() + "/man/deleteGroupByUid.action", req, function (result) {
                if (result) {
                    layer.closeAll("loading");
                    layer.msg('卸任成功', {time: 1000}, function () {
                        /*if (dataLength == 1) {
                            table.reload('admin', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                        } else {
                            table.reload('admin', {page: {curr: pageNum}});
                        }*/
                        tableIns.reload();
                    });
                } else {
                    layer.closeAll("loading");
                    layer.msg('卸任失败', {time: 1000});
                }
            });
        });
    }

});