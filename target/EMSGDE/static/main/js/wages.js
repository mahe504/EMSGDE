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
        elem: '#wages',
        height: '400',
        url: getRootPath() + "/wages/selectWagesAll.action",
        limit: 5,
        totalRow: true,
        limits: [5, 10, 15, 20, 25, 30],
        page: {
            prev: '上一页',
            next: '下一页',
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh', 'skip']
        }
        , title: '工资等级'
        , autoSort: false
        , page: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', width: '6%'}
            , {field: 'lv', title: '级别'}
            , {field: 'wnumber', title: '工资'}
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
                table.reload("wages", { // 刷新表格到上一页
                    page: {
                        curr: brforeCurr - 1
                    }
                });
            }
        }
    });
    form.on('submit(rufh)' , function(){
        table.reload('wages');
        return false;

    })
    form.on('submit(*)' , function(data){
        tableIns.reload({
            where : {
                'lv':data.field.lv,
            }
        });
        return false;
    });
    table.on('tool(wages)',function(obj){
        switch (obj.event) {
            case 'edit':
                editWages(obj.data);
                break;
            case 'delete':
                deleteWages(obj.data);
                break;
        }
    });
    function editWages(data){
        layer.open({
            skin: 'layui-layer-lan', //样式类名
            closeBtn: 2, //不显示关闭按钮
            anim: 5,
            shadeClose: true,
            method: 'post',
            shade: 0.8, //开启遮罩关闭
            title: '修改工资',
            maxmin: true, //开启最大化最小化按钮
            area: ['500px', '300px'],
            type: 2,
            content: getRootPath() + '/page/wages/editwages.html?id='+data.id,
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
    }
    function deleteWages(data){
        layer.confirm('你确认要删除吗，不建议删除建议修改！', function (confirmIndex) {
            layer.load();
            req = {
                id: data.id,
            };
            $api.doPost(getRootPath() + "/wages/deleteWagesById.action", req, function (result) {
                if (result) {
                    layer.closeAll("loading");
                    layer.msg('删除成功，你等着后悔吧！', {time: 1000}, function () {
                        /*if (dataLength == 1) {
                            table.reload('admin', {page: {curr: pageNum > 1 ? (pageNum - 1) : 1}});
                        } else {
                            table.reloadI('admin', {page: {curr: pageNum}});
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
});