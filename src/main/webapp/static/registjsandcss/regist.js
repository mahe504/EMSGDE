layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    laydate.render({
        elem: '#birthday' //指定元素
        , type: 'year'
    });
    /* var upload = layui.upload;
     upload.render({
         elem: '#fileBtn'
         , url: '../updatePersonalById.action'
         , accept: 'file'
         , auto: false
         , bindAction: '#uploadBtn'
         , done: function (res) {
             alert(res.data.src);
             $("[name=userImage]").val(res.data.src);
         }
     });*/
});
$(function () {

    $("#userInput").blur(function () {
        if ($(this).val() == "") {
            $("#usernamelab").css({"color": "red"});
            $("#usernamelab").text("请输入用户名！");
        } else {
            if ($(this).val().length > 16 || $(this).val().length < 2) {
                $("#usernamelab").css({"color": "red"});
                $("#usernamelab").text("用户名应该在2-16位之间！");
            } else {
                var req = /^[0-9a-zA-Z]{2,16}$/;
                if (!req.test($(this).val())) {
                    $("#usernamelab").css({"color": "red"});
                    $("#usernamelab").text("用户名格式不正确，应该由数字或者大小写字母组成！");
                } else {
                    var data = "username=" + $("#userInput").val();
                    var url = getRootPath() + "/regist/selectUsername.action";
                    $.post(url, data, function (res) {
                        if (res) {
                            $("#usernamelab").css({"color": "green"});
                            $("#usernamelab").text("该用户名可以注册！");
                        } else {
                            $("#usernamelab").css({"color": "red"});
                            $("#usernamelab").text("该用户名已经被注册了，请重新填写 ！");
                        }
                    });
                }
            }
        }
    });
    $("#realnameInput").blur(function () {
        if ($(this).val() == "") {
            $("#realnamelab").css({"color": "red"});
            $("#realnamelab").text("请输入真实姓名！");
        } else {
            $("#realnamelab").css({"color": "green"});
            $("#realnamelab").text("正确");
        }
    });
    $("#passwordInput").blur(function () {
        if ($(this).val() == "") {
            $("#passwordlab").css({"color": "red"});
            $("#passwordlab").text("请输入密码！");
        } else {
            if ($(this).val().length > 12 || $(this).val().length < 1) {
                $("#passwordlab").css({"color": "red"});
                $("#passwordlab").text("密码应该在1-12之间！")
            } else {
                var req = /^[a-zA-Z0-9]{4,10}$/;
                if (!req.test($(this).val())) {
                    $("#passwordlab").css({"color": "red"});
                    $("#passwordlab").text("密码输入不正确，应该由1-12位的数字加字母组成！");
                } else {
                    $("#passwordlab").css({"color": "green"});
                    $("#passwordlab").text("正确");
                }
            }
        }
    });
    $("#newpasswordInput").blur(function () {
        if ($(this).val() == "") {
            $("#newpasswordlab").css({"color": "red"});
            $("#newpasswordlab").text("请输入确认密码！");
        } else {
            if ($(this).val() == $("#passwordInput").val()) {
                $("#newpasswordlab").css({"color": "green"});
                $("#newpasswordlab").text("正确");
            } else {
                $("#newpasswordlab").css({"color": "red"});
                $("#newpasswordlab").text("两次输入的密码不一致");
            }
        }
    });
    $("#emailInput").blur(function () {
        if ($(this).val() == "") {
            $("#emaillab").css({"color": "red"});
            $("#emaillab").text("请输入邮箱！");
        } else {
            var req = /^([A-Za-z0-9_\-.])+@([A-Za-z0-9_\-.])+.([A-Za-z]{2,4})$/;
            if (!req.test($(this).val())) {
                $("#emaillab").css({"color": "red"});
                $("#emaillab").text("请输入正确的邮箱！");
            } else {
                $("#emaillab").css({"color": "green"});
                $("#emaillab").text("正确");
            }
        }
    });
    $("#birthday").blur(function () {
        var date = new Date();
        var newDate = date.getFullYear();
        if (parseInt($(this).val()) > parseInt(newDate)) {
            $("#birthdaylabel").css({"color": "red"});
            $("#birthdaylabel").text("请填写正确的出生年！");
        } else {
            if ($(this).val() == "") {
                $("#birthdaylabel").css({"color": "red"});
                $("#birthdaylabel").text("请填写出生年！");
            } else {

                $("#birthdaylabel").css({"color": "green"});
                $("#birthdaylabel").text("正确");
            }
        }
    });
    $("#phoneInput").blur(function () {
        if ($(this).val() == "") {
            $("#phonelabel").css({"color": "red"});
            $("#phonelabel").text("请输入手机号！");
        } else {
            if ($(this).val().length != 11) {
                $("#phonelabel").css({"color": "red"});
                $("#phonelabel").text("手机号的长度为11位！");
            } else {
                var req = /^1\d{10}$/;
                if (!req.test($(this).val())) {
                    $("#phonelabel").css({"color": "red"});
                    $("#phonelabel").text("请输入正确的手机号！");
                } else {
                    $("#phonelabel").css({"color": "green"});
                    $("#phonelabel").text("正确");
                }
            }
        }
    });
    $("#ecodeInput").blur(function () {
        if ($(this).val() == "") {
            $("#ecodelabel").css({"color": "red"});
            $("#ecodelabel").text("请输入邮编！");
        } else {
            var req = /^[1-9][0-9]{5}$/;
            if (!req.test($(this).val())) {
                $("#ecodelabel").css({"color": "red"});
                $("#ecodelabel").text("请输入正确的邮政编码！");
            } else {
                $("#ecodelabel").css({"color": "green"});
                $("#ecodelabel").text("正确");

            }
        }
    });
    $("#userInput").focus(function () {
        $("#usernamelab").text("");
    });
    $("#realnameInput").focus(function () {
        $("#realnamelab").text("");
    });
    $("#passwordInput").focus(function () {
        $("#passwordlab").text("");
    });
    $("#newpasswordInput").focus(function () {
        $("#newpasswordlab").text("");
    });
    $("#emailInput").focus(function () {
        $("#emaillab").text("");
    });
    $("#phoneInput").focus(function () {
        $("#phonelabel").text("");
    });
    $("#ecodeInput").focus(function () {
        $("#ecodelabel").text("");
    });
    $("#birthday").focus(function () {
        $("#birthdaylabel").text("");
    });
    $(".layui-btn").click(function () {
        if ($("#userInput").val() == "" || $("#realnameInput").val() == "" || $("#passwordInput").val() == "" || $("#newpasswordInput").val() == "" || $("#emailInput").val() == "" || $("#phoneInput").val() == "" || $("#ecodeInput").val() == "" || $("#birthdayInput").val() == "" || $("#genderInput").val() == "" || $("#addressInput").val() == "") {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 0,
                    icon: 6,
                    title: "注册信息",
                    content: "请你把信息填写全！",
                    area: 'auto',
                    shadeClose: true,
                    btnAlign: 'c',
                    id: 'layerDemo' + 2,
                });
            });
        } else {
            var data = $("#registfrm").serialize();
            var url = getRootPath() + "/regist/registUser.action";
            $.ajax({
                async: false,
                cache: false,
                type: "post",
                url: url,
                data: data,
                success: function (res) {
                    if (res == "注册成功") {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.open({
                                type: 1,
                                title: '注册信息',
                                icon: 6,
                                closeBtn: false,
                                area: '300px;',
                                shade: 0.8,
                                id: 'LAY_layuipro',
                                btn: ['立即登录', '继续注册'],
                                btnAlign: 'c',
                                moveType: 1,
                                content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">注册成功</div>',
                                success: function (layero) {
                                    var btn = layero.find('.layui-layer-btn');
                                    btn.find('.layui-layer-btn0').attr({
                                        href: getRootPath() + '/index.jsp'
                                    });
                                    btn.find('.layui-layer-btn1').attr({
                                        href: getRootPath() + '/regist/regist.html'
                                    });
                                }
                            })
                        });
                    } else {
                        if (res == "注册失败") {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.open({
                                    type: 0,
                                    icon: 5,
                                    title: "注册信息",
                                    content: "注册失败，请重新注册",
                                    area: 'auto',
                                    id: 'layerDemo' + 4,
                                });
                            });
                        } else {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.open({
                                    type: 0,
                                    icon: 5,
                                    title: "注册信息",
                                    content: "发生的未知的错误！",
                                    area: 'auto',
                                    id: 'layerDemo' + 1,
                                });
                            });
                            location.reload();
                        }
                    }
                }
            });
        }
    });
});