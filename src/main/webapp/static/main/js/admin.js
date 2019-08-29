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
        editSta=0,
        element = layui.element;
    /*
    *   表格
    * */
    var tableIns = table.render({
        elem: '#admin',
        height: '390',
        url: getRootPath() + "/admin/selectAdminAll.action",
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
                width: '11%',
                event: 'openStatus'
            }
            , {fixed: 'right', title: '操作', align: 'center', width: '14%', toolbar: '#toolBar'}
        ]],
        done: function (res, curr, count) {
            /*
            *   页面刷新的时候重新计算页面的数据
            * */
            var brforeCurr = curr; // 获得当前页码
            var dataLength = res.data.length; // 获得当前页的记录数
            var count = res.count; // 获得总记录数
            if (dataLength == 0 && count != 0) { //如果当前页的记录数为0并且总记录数不为0
                table.reload("admin", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
        }


    });

    table.on('toolbar(admin)', function (obj) {
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
            case 'deleteAll':
                var data = checkStatus.data;
                deleteAll(data)
                break;
        }
    })

    table.on('tool(admin)', function (obj) {
        var layEvent = obj.event;
        switch (layEvent) {
            case  'edit':
                if ($(this).text() == "取消修改") {
                    editBtn(obj.data.uid, true, this);
                } else {
                    editBtn(obj.data.uid, false, this);
                }
                break;
            case 'delete':
                deleteAdmin(obj.data.uid);
                break;
        }
        if (editSta==0) {
        } else {
            if (obj.event == 'openStatus') { //监听状态
                editStatus = obj.data.status == '1' ? '0' : '1';
                layer.msg('用户' + (obj.data.status == '1' ? '禁用' : '启用' + '~'));
                //发送ajax
                layer.load();
                var req = {
                    uid: obj.data.uid,
                    status: editStatus,
                };
                $api.doPost(getRootPath() + '/admin/editAdminStatus.action', req, function (result) {
                    if (result) {
                        layer.closeAll("loading");
                        layer.msg('修改成功', {time: 1000});
                    } else {
                        layer.closeAll("loading");
                        layer.msg('修改失败', {time: 1000});
                    }
                });

                //更新表格数据
                obj.update({
                    status: editStatus
                });
                /* layer.load({time:2000}function(){

                 })*/
                tableIns.reload();
            }
        }

    });

    function  editBtn(uid, flag, x) {
        $("#no" + uid).attr({"disabled": flag});
        $("#off" + uid).attr({"disabled": flag});
        if (flag) {
            $(x).text("修改");
            editSta=0;
            $("#no" + uid).next().addClass("layui-disabled");
            $("#no" + uid).next().addClass("layui-radio-disbaled");
            $("#off" + uid).next().addClass("layui-disabled");
            $("#off" + uid).next().addClass("layui-radio-disbaled");
        } else {
            $(x).text("取消修改");
            editSta=1;
            $("#no" + uid).next().removeClass("layui-disabled");
            $("#no" + uid).next().removeClass("layui-radio-disbaled");
            $("#off" + uid).next().removeClass("layui-disabled");
            $("#off" + uid).next().removeClass("layui-radio-disbaled");
        }
    }

    function deleteAdmin(uid) {
        layer.confirm('你确认要删除吗', function (confirmIndex) {
            layer.load();
            req = {
                uid: uid,
            };
            $api.doPost(getRootPath() + "/admin/deleteAdminByUid.action", req, function (result) {
                if (result) {
                    layer.closeAll("loading");
                    layer.msg('删除成功', {time: 1000}, function () {
                        /*if (dataLength == 1) {
                            table.reload('admin', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                        } else {
                            table.reload('admin', {page: {curr: pageNum}});
                        }*/
                        tableIns.reload();
                    });
                } else {
                    layer.closeAll("loading");
                    layer.msg('删除失败', {time: 1000});
                }
            });
        });
    }

    function deleteAll(data) {
        if (data.length != 0) {
            layer.confirm('你确认要删除吗', function (confirmIndex) {
                var id = "";
                for (var i = 0; i < data.length; i++) {
                    id = id + data[i].uid + ",";
                }
                var req = {
                    "uid": id,
                };
                $api.doPost(getRootPath() + "/admin/deleteAdminByUid.action", req, function (res) {
                    if (res) {
                        layer.msg("批量删除成功", {time: 1000}, function () {

                            tableIns.reload();
                            //obj.del(); //删除对应行（tr）的DOM结构
                            //重新加载表格
                            /*if (dataLength == 1) {
                                table.reload('admin', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                            } else {
                                table.reload('admin', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                            }*/
                        });
                    } else {
                        layer.msg('删除失败！', {time: 1000});
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
    /*form.on('button(addAdminBtn)',function(data){
        alert(1);
        return false;

    })*/
    $(function () {
        $("#addAdminBtn").click(function () {
            layer.open({
                skin: 'layui-layer-lan', //样式类名
                closeBtn: 2, //不显示关闭按钮
                anim: 6,
                shadeClose: true,
                shade: 0.8, //开启遮罩关闭
                title: '添加管理员',
                maxmin: true, //开启最大化最小化按钮
                area: ['1000px', '500px'],
                content: '',
                type: 2,
                content: getRootPath() + '/page/admin/addAdmin.html',
                success:function(layero){
                    layero.find('.layui-layer-min').remove();
                },
                cancel: function(index, layero){//点击关闭事件
                    tableIns.reload();
                },
                end:function(){
                    tableIns.reload();
                }
            })
        })
        return false;
    })
});