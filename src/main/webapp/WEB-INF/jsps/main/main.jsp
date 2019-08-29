<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>企业员工管理系统</title>
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <link rel="icon" href="/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/main.css" media="all"/>
    <script src="${pageContext.request.contextPath}/static/path.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/leftNav.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/index.js"></script>

</head>
<%--<body class="layui-layout-body" onunload="javascript:closeLog()" onbeforeunload="javascript:fLog()">--%>

<body class="main_body" onunload="closeLog()" onbeforeunload="fLog() ">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header  layui-bg-black">
        <div class="layui-main layui-bg-black">
            <a href="javascript:;" class="logo" style="color:white">企业管理系统</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:" class="iconfont hideMenu icon-menu1 layui-bg-black"></a>
            <!-- 天气信息 -->
            <div class="weather" pc style="margin-top: 10px">
                <iframe allowtransparency="true" frameborder="0" width="180" height="36" scrolling="no"
                        src="//tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=0&v=0&d=1&bd=0&k=000000&f=ffffff&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=60124&w=180&h=36&align=center"></iframe>
                <%--<div id="tp-weather-widget"></div>
                <script>(function (T, h, i, n, k, P, a, g, e) {
                    g = function () {
                        P = h.createElement(i);
                        a = h.getElementsByTagName(i)[0];
                        P.src = k;
                        P.charset = "utf-8";
                        P.async = 1;
                        a.parentNode.insertBefore(P, a)
                    };
                    T["ThinkPageWeatherWidgetObject"] = n;
                    T[n] || (T[n] = function () {
                        (T[n].q = T[n].q || []).push(arguments)
                    });
                    T[n].l = +new Date();
                    if (T.attachEvent) {
                        T.attachEvent("onload", g)
                    } else {
                        T.addEventListener("load", g, false)
                    }
                }(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))</script>
                <script>tpwidget("init", {
                    "flavor": "slim",
                    "location": "WX4FBXXFKE4F",
                    "geolocation": "enabled",
                    "language": "zh-chs",
                    "unit": "c",
                    "theme": "chameleon",
                    "container": "tp-weather-widget",
                    "bubble": "disabled",
                    "alarmType": "badge",
                    "color": "#FFFFFF",
                    "uid": "U9EC08A15F",
                    "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                });
                tpwidget("show");</script>--%>
            </div>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item showNotice" id="showNotice" pc>
                    <a href="javascript:"><i class="iconfont icon-gonggao"></i><cite>最新公告</cite></a>
                </li>


                <li class="layui-nav-item" pc>
                    <a href="javascript:">
                        <c:if test="${empty sessionScope.user.pic}">
                            <img src="${pageContext.request.contextPath}/static/common/images/img2.jpg"
                                 class="layui-nav-img layui-bg-gray">
                        </c:if>
                        <c:if test="${not empty sessionScope.user.pic}">
                            <img src="${pageContext.request.contextPath}/upload/pic/${sessionScope.user.pic}"
                                 class="layui-nav-img layui-bg-gray">
                        </c:if>
                        <c:if test="${sessionScope.user.gender=='男'}">
                            Mr.
                        </c:if>
                        <c:if test="${sessionScope.user.gender=='女'}">
                            Mis.
                        </c:if>
                        ${sessionScope.user.realname}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" data-url="${pageContext.request.contextPath}/main/proFile.action"><i
                                class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
                        <%--<dd><a href="javascript:" data-url="page/system/personCenter/changePwd.html"><i
                                class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>--%>
                        <%--<dd><a href="javascript:" class="changeSkin"><i
                                class="iconfont icon-huanfu"></i><cite>更换皮肤</cite></a></dd>--%>
                        <dd>
                            <a href="javascript:logouta()"><i class="iconfont icon-loginout"></i>退出系统</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-cyan" id="admin-side">
        <div class="user-photo">
            <a class="img" title="我的头像"><img src="static/common/images/face.jpg"></a>
            <p>你好！<span class="userName">${sessionScope.user.realname}${sessionScope.rolename}</span>, 欢迎登录</p>
        </div>
        <div class="navBar layui-side-scroll"></div>
    </div>

    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>控制台信息</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:"><i class="iconfont icon-caozuo"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i>
                            刷新当前</a></dd>
                        <dd><a href="javascript:" class="closePageOther"><i class="iconfont icon-prohibit"></i>
                            关闭其他</a></dd>
                        <dd><a href="javascript:" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a>
                        </dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="${pageContext.request.contextPath}/main/showmain.action"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p>企业个人管理系统 版本V1.0 @2019-2020 @Author Mahe |
            <a href="http://www.beian.gov.cn/portal/recordQuery" style="color:blue" target="_blank">备案许可证：黑ICP备190047731号</a>
            <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-small">博客地址</a>
            <a href="javascript:" class="layui-btn layui-btn-danger layui-btn-small dashang">打赏作者</a>
        </p>
    </div>
</div>

<!-- 移动导航 -->
<%--<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>--%>
<script>
    function logouta() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.load(2);
            layer.msg('退出成功', {time: 2000, shade: 0.5}, function () {
                layer.closeAll("loading");
                window.sessionStorage.clear();
                window.location.href = getRootPath() + "/login/logout.action";
            })
        });
    };
</script>

</body>
</html>