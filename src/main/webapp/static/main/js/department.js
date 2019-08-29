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
        elem: '#department',
        height: '390',
        url: getRootPath() + "/department/selectDepartment.action",
        toolbar: '#toolbarDemo',
        limit: 5,
        totalRow: true,
        limits: [5, 10, 15, 20, 25, 30],
        page: {
            prev: '上一页',
            next: '下一页',
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh', 'skip']
        }
        , title: '部门列表'
        , autoSort: false
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', fixed: 'left', unresize: true}
            , {field: 'dname', title: '部门名称', edit: 'text'}
            , {field: 'createuser', title: '创建者',}
            , {
                field: 'dtabletime', title: '创建日期', templet: function (time) {
                    return $tool.getDatetime(time.dtabletime)
                }
            }
            , {field: 'updateuser', title: '修改者'}
            , {
                field: 'updatetime', title: '修改日期', templet: function (time) {
                    return $tool.getDatetime(time.updatetime)
                }
            }
            , {field: 'usercount', title: '人数'},
            {field: 'maxnumber', title: '部门最多人数'}
            , {fixed: 'right', title: '操作', align: 'center', width: '20%', toolbar: '#toolBar'}
        ]],
        done: function (res, curr, count) {
            var brforeCurr = curr; // 获得当前页码
            var dataLength = res.data.length; // 获得当前页的记录数
            var count = res.count; // 获得总记录数
            if (dataLength == 0 && count != 0) { //如果当前页的记录数为0并且总记录数不为0
                table.reload("department", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
            $api.doPost(getRootPath() + '/permission/selectPermissionMsg.action', {'title': '部门列表'}, function (result) {
                var str = "";
                for (var i = 0; i < result.data.length; i++) {
                    str = str + result.data[i].title;
                }
                if (str.indexOf("删除") == -1) {
                    $(".layui-btn-danger").remove();
                    /*css({
                           "display":""
                       });*/
                }
                if (str.indexOf("修改") == -1) {
                    $(".layui-btn-warm").remove()
                    /*.css({
                           "display":""
                       });*/
                }
            })
        }
    });
    table.on('tool(department)', function (obj) {
        switch (obj.event) {
            case 'sel':
                showDepartmentAllUser(obj.data);
                break;
            case 'edit':
                editDepartment(obj);
                break;
            case 'delete':
                deleteDepartment(obj.data);
                break;
        }
    })
    table.on('toolbar(department)', function (obj) {
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
        ;
    });
    form.on('submit(queryDepartment)', function (data) {
        var dname = data.field.dname;
        var createuser = data.field.createuser;
        tableIns.reload({
            where: {
                dname: dname,
                createuser: createuser,
            }
        })
    });
    form.on('submit(addDepartmentBtn)', function (data) {
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 1,
            shadeClose: false,
            shade: 0.8, //开启遮罩关闭
            title: '添加部门',
            maxmin: true, //开启最大化最小化按钮
            area: ['500px', '350px'],
            type: 2,

            content: getRootPath() + '/page/department/adddepartment.html',
            success:function(layero){
                layero.find('.layui-layer-min').remove();
            },
            cancel: function (index, layero) {//点击关闭事件
                tableIns.reload();
            },
            end:function(){
                tableIns.reload();
            }
        });
    })

    function showDepartmentAllUser(data) {
        var dname = data.dname;
        var id = data.id;
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 5,
            shadeClose: true,
            shade: 0.8, //开启遮罩关闭
            title: dname + '的所有员工',
            maxmin: true, //开启最大化最小化按钮
            area: ['1000px', '520px'],
            type: 2,
            content: getRootPath() + '/page/department/showuser.html?id='+id,
            success:function(layero){
                layero.find('.layui-layer-min').remove();
            },
            cancel: function (index, layero) {//点击关闭事件
                tableIns.reload();
            },
            end:function(){
                tableIns.reload();
            }
        });
    }

    function deleteDepartment(data) {
        layer.confirm('你确认要删除吗', function (confirmIndex) {
            layer.load();
            var req = {
                'id': data.id,
            }
            $api.doPost(getRootPath() + "/department/deleteDepartmentById.action", req, function (result) {
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
                    id = id + data[i].id + ",";
                }
                var req = {
                    'id': id,
                };
                $api.doPost(getRootPath() + "/department/deleteDepartmentById.action", req, function (res) {
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

    function editDepartment(obj) {
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 5,
            shadeClose: true,
            method:'get',
            shade: 0.8, //开启遮罩关闭
            title: '修改' + obj.data.dname + '的信息',
            maxmin: true, //开启最大化最小化按钮
            area: ['550px', '350px'],
            type: 2,
            content: getRootPath() + '/page/department/editdepartment.html?id='+obj.data.id+'&usercount='+obj.data.usercount,
            success:function(layero){
                layero.find('.layui-layer-min').remove();
            },
            cancel: function (index, layero) {//点击关闭事件
                tableIns.reload();
            },
            end:function () {
                tableIns.reload();
            }

        });
    }
});