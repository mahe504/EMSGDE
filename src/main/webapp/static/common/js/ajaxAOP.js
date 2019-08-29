layui.define(['jquery', 'layer', '$tool'], function (exports) {

    var layer = parent.layer === 'undefined' ? layui.layer : parent.layer,
        layuiJquery = layui.jquery,
        $tool = layui.$tool;


    (function ($) {//闭包函数先于js执行
        var _ajax = $.ajax;
        //重新写jquery.ajax , opt为ajax的对象
        $.ajax = function (opt) {
            var fn = {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                },//请求失败执行的函数
                success: function (data, textStatus) {
                },//请求成功之后执行的函数
                complete: function (XMLHttpRequest, textStatus) {
                },//发出请求之后执行的函数
                beforeSend: function (XMLHttpRequest) {
                }//发出请求前执行的函数
            }
            if (opt.error) {
                fn.error = opt.error;
            }
            if (opt.success) {
                fn.success = opt.success;
            }
            if (opt.beforeSend) {
                fn.beforeSend = opt.beforeSend;
            }
            if (opt.complete) {
                fn.complete = opt.complete;
            }
            //$extends合并函数后覆盖前相同的覆盖没有的追加
            var _opt = $.extend(opt, {
                //增强错误的方法
                error: function (xhr, textStatus, errorThrown) {
                    if (xhr.status == 401) {
                        window.location.href = getRootPath() + "/page/error/401.html";
                        return;
                    } else if (xhr.status == 403) {
                        window.sessionStorage.removeItem("user");
                        window.sessionStorage.removeItem("permissions");
                        window.sessionStorage.removeItem("roles");
                        window.sessionStorage.removeItem("meuns");
                        //防止在窗口就跳到登录页面
                        window.parent.parent.parent.parent.location.href = getRootPath() + "/index.jsp";
                        return;
                    } else if (xhr.status == 404) {
                        window.location.href = getRootPath() + "/page/error/404.html";
                        return;
                    }
                    //执行原先的错误方法
                    fn.error(xhr, textStatus, errorThrown)
                },
                //增强成功的方法
                success: function (data, textStatus) {
                    //错误码不为0000的弹出错误信息
                    if ('0000' != data.code) {
                        layer.msg(data.msg);
                        return;
                    }
                    //执行之前的成功方法
                    fn.success(data, textStatus);
                },
                beforeSend: function (XMLHttpRequest) {
                    //执行前显示遮罩层
                    layer.load(2, {shade: 0});
                    XMLHttpRequest.setRequestHeader("token", "jkajldfjald;kf");
                    //执行之前的方法
                    fn.beforeSend(XMLHttpRequest);
                },
                complete: function (xhr, textStatus) {
                    //清除遮罩层
                    layer.closeAll("loading");
                    fn.complete(xhr, textStatus)
                }
            });
            return _ajax(_opt);
        };
    })(layuiJquery);
    exports('ajaxAOP', {});
});