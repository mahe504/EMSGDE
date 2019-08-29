

layui.define(['$tool', 'jquery'], function (exports) {

    var $tool = layui.$tool,
        $ = layui.jquery;

    /*
    *   封装post方法
    * */
    function doPost(url, req, successCallback, errorCallback) {
        $.ajax({
            url: url,
            data: req,
            type: 'post',
            success: function (data) {
                successCallback(data);
            },
            error: function (data) {
                errorCallback(data);
            }
        });
    }

    /*
    *   封装一个get方法
    * */
    function doGet(url, req, successCallback, errorCallback) {
        $.ajax({
            url: url,
            data: req,
            type: "get",
            success: function (data) {
                successCallback(data);
            },
            error: function (data) {
                errorCallback(data);
            }
        })
    }

    /*
    *   封装一个多参数的post方法
    * */
    function doComplexPost(url, req, config, successCallback, errorCallback) {
        var defaultConfig = {
            url: url,
            data: req,
            type: "post",
            success: function (data) {
                successCallback(data);
            },
            error: function (data) {
                errorCallback(data);
            }
        };
        var ajaxConfig = $.extend({}, config, defaultConfig);//合并参数 config里面的参数不变 ， 合并到新的对象中
        $.ajax(ajaxConfig);//执行自己封装的ajax方法
    }

    var API = {
        doPost : function(url, req, successCallback, errorCallback){
            doPost(url, req, successCallback, errorCallback);
        },
        doGet : function(url, req, successCallback, errorCallback){
            doGet(url, req, successCallback, errorCallback)
        },
        doComplexPost : function(url, req, config, successCallback, errorCallback){
            doComplexPost(url, req, config, successCallback, errorCallback)
        },
    };
    exports('$api', API);
});