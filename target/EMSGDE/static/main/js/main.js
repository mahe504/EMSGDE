/**
 * 首页内容.js
 * */
layui.config({
    base: $config.resUrl + 'static/common/js/'//定义基目录
}).extend({
    $tool: 'tool'
}).use(['form', 'element', 'layer', 'carousel', 'jquery', '$tool', 'table', 'laypage'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery,
        carousel = layui.carousel,
        $tool = layui.$tool,
        table = layui.table,
        laypage = layui.laypage;
    var tableIns;
    function init() {
        carousel.render({
            elem: '#car'
            , width: '100%' //设置容器宽度
            , height: '400px'
            , arrow: 'always' //始终显示箭头
        });
    }

    init();

    $(".panel a").on("click", function () {
        window.parent.addTab($(this));
    });



   /* function definetable() {
        tableIns: table.render({
            elem: '#loginlogtable',
            height: 395,
            url: $tool.getContext() + '/main/loginlogList.action',
            page: true,
            method: 'post',
            count: 100,
            skin: 'row' //表格风格
            ,even: true,
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'logusername', title: '登录名', width: 80}
                , {field: 'lastlogtime', title: '登录时间', width: 80, sort: true}
                , {field: 'logip', title: '登录IP', width: 80}
            ]]/!*, done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                console.log(res);
            }*!/
        });
    }

    definetable();*/
});
