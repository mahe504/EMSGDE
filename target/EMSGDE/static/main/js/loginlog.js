layui.config({//基础目录
    base: getRootPath() + '/static/common/js/'//定义基目录
}).extend({
    $tool: 'tool',

}).use(['table', 'laypage', 'layer', '$tool', 'form'], function () {
    var table = layui.table,
        laypage = layui.laypage,
        pageNum = 0,
        form = layui.form,
        $tool = layui.$tool,
        dataLength = 0,
        layer = parent.layer === undefined ? layui.layer : parent.layer;

    var tableIns = table.render({
        elem: '#loginlog'
        , height: 390
        //,width: 600
        , title: '用户登录日志数据表'
        , url: getRootPath() + '/main/loginlogList.action'
        //,size: 'lg'
        , autoSort: false

        //,loading: false
        , totalRow: true
        , limit: 5
        , limits: [5, 10, 15, 20, 25, 30]
        , toolbar: '#toolbarDemo'
        //,defaultToolbar: ['filter']
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: '21%', fixed: 'left', unresize: true}
            , {field: 'logusername', title: '登录的用户名', width: '21%',}
            , {
                field: 'lastlogtime', title: '最后登录的时间', width: '20%', templet: function (time) {
                    return $tool.getDatetime(time.lastlogtime)
                }
            }
            , {field: 'logip', title: '最后登录的IP地址', width: '16%'}
            , {fixed: 'right', title: '操作', align: 'center', width: '18%', toolbar: '#toolBar'}
        ]],
        page: {
            prev:'上一页',
            next:'下一页',
            layout: [ 'prev', 'page', 'next',  'limit', 'count', 'refresh', 'skip']
        },
        done: function (res, curr, count) {
            layer.load(2);
            var brforeCurr = curr; // 获得当前页码
            var dataLength = res.data.length; // 获得当前页的记录数
            var count = res.count; // 获得总记录数
            if(dataLength == 0 && count != 0){ //如果当前页的记录数为0并且总记录数不为0
                table.reload("admin",{ // 刷新表格到上一页
                    page:{
                        curr:brforeCurr-1
                    }
                });
            };
            pageNum = curr, dataLength = res.data.length;
            $.ajax({
                cache: false,
                async: false,
                dataType: "json",
                data: "title=" + "控制台信息",
                url: getRootPath() + "/permission/selectPermission.action",
                success: function (result) {
                    if (result) {
                        var str = "";
                        for (var i = 0; i < result.length; i++) {
                            str = str + result[i].title;
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
                        layer.closeAll("loading");
                    }
                }
            })

        }
    });
    table.on('toolbar(loginlog)', function (obj) {
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
    table.on('tool(loginlog)', function (obj) {
        var layEvent = obj.event;
        var row = obj.data;
        switch (layEvent) {
            case 'query':
                layer.alert(JSON.stringify(obj.data));
                break;
            case 'delete':
                deleteLoginlog(row.id);
                break;
        }
        ;
    })
    form.on('submit(queryUser)', function (data) {
        var logusername = data.field.logusername;
        tableIns.reload({
            where: {
                logusername: logusername,
            }
        })
    });

    function deleteLoginlog(id) {
        layer.confirm('你确认要删除吗', function (confirmIndex) {
            layer.close(confirmIndex);
            var req = {
                id: id,
            };
            $.ajax({
                cache: false,
                async: false,
                data: req,
                url: getRootPath() + "/main/deleteLoginlogById.action",
                method: "post",
                success: function (res) {
                    if (res) {
                        layer.msg("删除成功", {time: 1000}, function () {
                            //obj.del(); //删除对应行（tr）的DOM结构
                            //重新加载表格
                           /* if (dataLength == 1) {
                                table.reload('loginlog', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                            } else {
                                table.reload('loginlog', {page: {curr: pageNum}});
                            }*/
                            tableIns.reload();
                        });
                    }
                }
            })
        })
    }

    function deleteAll(data) {
        if (data.length != 0) {
            var id = "";
            for (var i = 0; i < data.length; i++) {
                id = id + data[i].id + ",";
            }
            var req = {
                "id": id,
            };
            $.ajax({
                cache: false,
                async: false,
                url: getRootPath() + "/main/deleteLoginlogById.action",
                data: req,
                success: function (res) {
                    if (res) {
                        layer.msg("批量删除成功", {time: 1000}, function () {
                            tableIns.reload();
                            //obj.del(); //删除对应行（tr）的DOM结构
                            //重新加载表格
                            /*if (dataLength == 1) {
                                table.reload('loginlog', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                            } else {
                                table.reload('loginlog', {page: {curr: pageNum - 1}});
                            }*/
                        });
                    }
                }
            });
        } else {
            layer.msg('你没有选中数据', {time: 1000});
        }

    }
});