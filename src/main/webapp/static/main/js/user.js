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

    var tableIns = table.render({
        elem: '#user',
        height: '390',
        url: getRootPath() + "/user/selectUserAll.action",
        toolbar: '#toolbarDemo',
        limit: 5,
        totalRow: true,
        limits: [5, 10, 15, 20, 25, 30],
        page: {
            prev: '上一页',
            next: '下一页',
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh', 'skip']
        }
        , title: '员工列表'
        , autoSort: false
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'uid', title: 'ID', width: '6%'}
            , {field: 'realname', title: '真实姓名'}
            , {field: 'dname', title: '所在部门' , teplet : function (d) {
                    if(d==''){
                        return '无';
                    }
                }}
            , {field: 'email', title: '邮箱',}
            , {field: 'gender', title: '性别'}
            , {field: 'address', title: '地址'}
            , {field: 'wages', title: '工资'}
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
                table.reload("user", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
        }
    });

    table.on('tool(user)', function (obj) {
        switch (obj.event) {
            case 'edit' :
                editUser(obj);
                break;
            case 'delete' :
                deleteUser(obj.data);
                break;
        }
    })

    table.on('toolbar(user)', function (obj) {
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
    });

    function editUser(obj) {
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 5,
            shadeClose: true,
            method: 'post',
            shade: 0.8, //开启遮罩关闭
            title: '修改' + obj.data.realname + '的部门',
            maxmin: true, //开启最大化最小化按钮
            area: ['600px', '300px'],
            type: 2,
            content: getRootPath() + '/page/user/edituserdepartment.html?uid=' + obj.data.uid ,
            success: function (layero) {
                layero.find('.layui-layer-min').remove();
            },
            cancel: function (index, layero) {//点击关闭事件
                tableIns.reload();
            },
            end: function () {
                tableIns.reload();
            }
        });
    };

    function deleteUser(data) {
        layer.confirm('你确认要删除吗', function (confirmIndex) {
            layer.load();
            req = {
                uid: data.uid,
            };
            $api.doPost(getRootPath() + "/user/deleteUserByUid.action", req, function (result) {
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
                $api.doPost(getRootPath() + "/user/deleteUserByUid.action", req, function (res) {
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

    form.on('submit(*)', function (data) {
        tableIns.reload({
            where: {
                'dname': data.field.dname,
                'realname': data.field.realname,
            }
        })
    });

    form.on('submit(rufh)' , function(){
        table.reload('user');
        return false;
    })
    form.on('submit(adduserBtn)' , function(data){
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 5,
            shadeClose: true,
            method: 'post',
            shade: 0.8, //开启遮罩关闭
            title: '添加员工',
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '500px'],
            type: 2,
            content: getRootPath() + '/page/user/adduser.html',
            success: function (layero) {
                layero.find('.layui-layer-min').remove();
            },
            cancel: function (index, layero) {//点击关闭事件
                tableIns.reload();
            },
            end: function () {
                tableIns.reload();
            }
        });
    })
});