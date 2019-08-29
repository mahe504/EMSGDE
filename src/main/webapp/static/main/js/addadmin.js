layui.config({
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

    var tableIns = table.render({
        elem: '#addAdmin',
        height: '390',
        url: getRootPath() + "/admin/selectUserNoAdmin.action",
        toolbar: '#toolbarDemo',
        limit: 5,
        totalRow: true,
        limits: [5, 10, 15, 20, 25, 30],
        page: {
            prev: '上一页',
            next: '下一页',
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh', 'skip']
        }
        , title: '管理员列表'
        , autoSort: false
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'uid', title: 'ID', fixed: 'left', unresize: true}
            , {field: 'username', title: '用户名'}
            , {field: 'email', title: '邮箱',}
            , {field: 'gender', title: '性别'}
            , {field: 'realname', title: '真实姓名'}
            , {
                field: 'birthday', title: '出生年', templet: function (time) {
                    return $tool.getDatetime(time.birthday)
                }
            }
            , {field: 'address', title: '地址'}
            , {field: 'phone', title: '手机号'}
            , {field: 'ecode', title: '邮编'}
            , {
                field: 'createtime', title: '创建时间', templet: function (time) {
                    return $tool.getDatetime(time.createtime)
                }
            }
            , {
                field: 'status',
                title: '登录状态',
                align: 'center',
                templet: '#statusTpl ',
                width: '12%',
                event: 'openStatus'
            }
            , {fixed: 'right', title: '操作', align: 'center', width: '14%', toolbar: '#toolBar'}
        ]],
        done: function (res, curr, count) {
            var brforeCurr = curr; // 获得当前页码
            var dataLength = res.data.length; // 获得当前页的记录数
            var count = res.count; // 获得总记录数
            if (dataLength == 0 && count != 0) { //如果当前页的记录数为0并且总记录数不为0
                table.reload("addAdmin", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
        },
    });
    table.on('toolbar(addAdmin)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                var data = checkStatus.data;
                if (data.length != 0) {
                    layer.alert(JSON.stringify(data));
                } else {
                    layer.alert("你没有选中行", {time: 1000});
                }
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个', {time: 1000});
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选', {time: 1000})
                break;
            case 'addAll':
                var data = checkStatus.data;
                addAll(data);
                obj.parent.table.reload();
                break;
        }
    })
    table.on('tool(addAdmin)', function (obj) {
        var layEvent = obj.event;
        switch (layEvent) {
            case  'add':
                addAdmin(obj.data.uid);
                /* var index = parent.layer.getFrameIndex(window.name);
                 alert(index)
//关闭父级页面的表格
                 parent.layui.table.reload('admin',{page:{curr:1}});*/
                break;
        }
    });

    function addAdmin(uid) {
        layer.confirm('你确认要添加吗', function (confirmIndex) {
            layer.load();
            req = {
                uid: uid,
            };
            $api.doPost(getRootPath() + "/admin/addAdminByUid.action", req, function (result) {
                if (result) {
                    layer.closeAll("loading");
                    layer.msg('添加成功', {time: 1000}, function () {
                        tableIns.reload();
                        /*window.parent.layui.table.reload('admin', {page: {curr: 1}})*/
                    });
                } else {
                    layer.closeAll("loading");
                    layer.msg('添加失败', {time: 1000});
                }
            });
        });
    }

    function addAll(data) {
        if (data.length != 0) {
            layer.confirm('你确认要全部添加吗', function (confirmIndex) {
                var id = "";
                for (var i = 0; i < data.length; i++) {
                    id = id + data[i].uid + ",";
                }
                var req = {
                    "uid": id,
                };
                $api.doPost(getRootPath() + "/admin/addAdminByUid.action", req, function (res) {
                    if (res) {
                        layer.msg("批量添加成功", {time: 1000}, function () {
                            tableIns.reload();
                            /*parent.layui.table.reload('admin', {
                                where: {
                                    page: {curr: 1}
                                }
                            })*/
                            //obj.del(); //删除对应行（tr）的DOM结构
                            //重新加载表格

                        });
                    } else {
                        layer.msg('添加失败！', {time: 1000});
                    }
                });
            });
        } else {
            layer.msg('你没有选中数据', {time: 1000});
        }

    }

    form.on('submit(queryAdmin)', function (data) {
        var username = data.field.username;
        tableIns.reload({
            where: {
                username: username,
            }
        })
    })

});