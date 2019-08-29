layui.config({
    base: getRootPath() + '/static/common/js/'
}).extend({
    $tool: 'tool',
}).use(['form', 'upload', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === 'undefined' ? layui.layer : parent.layer,
        upload = layui.upload;
    init();
    form.verify({
        inpuemail: [/^([A-Za-z0-9_\-.])+@([A-Za-z0-9_\-.])+.([A-Za-z]{2,4})$/, '请输入正确的邮箱']
    });
    form.verify({
        inpuphone: [/^1\d{10}$/, '请输入正确的手机号',]
    })
    form.verify({
        inpuecode: [/^[1-9][0-9]{5}$/, '请输入正确的邮编',]
    })
    var oldA;
    form.verify({
        address1: function (value) { //value：表单的值、item：表单的DOM对象
            if (value == "") {
                return "地址不能为空";
            }
        },
    })
    var email1, gender1, phone1, ecode1, address1;

    function init() {
        $.ajax({
            cache: false,
            async: false,
            method: "post",
            dataType: "json",
            url: getRootPath() + "/user/selectUser.action",
            success: function (res) {
                for (var i = 0; i < res.length; i++) {
                    email1 = res[i].email;
                    gender1 = res[i].gender;
                    phone1 = res[i].phone;
                    ecode1 = res[i].ecode;
                    address1 = res[i].address;
                    if (res != null) {
                        form.val('userfrm', {
                            username: res[i].username,
                            realname: res[i].realname,
                            email: res[i].email,
                            gender: res[i].gender,
                            role: res[i].rname,
                            phone: res[i].phone,
                            ecode: res[i].ecode,
                            address: res[i].address,
                        })
                        if (res[i].pic != "" && res[i].pic != null) {
                            $("#uploadPic").remove();
                            $("#piclable").append('<img class="layui-upload-img" style="width: 60px; height: 60px" src="../upload/pic/' + res[i].pic + '" id="uploadPic">')
                        } else {
                            $("#piclable").append('<img class="layui-upload-img" style="width: 60px; height: 60px" src="../static/common/images/img2.jpg" id="uploadPic">')
                        }

                    }

                }
            }
        });

    }

    $("#editBtn").click(function () {
        $("#editBtn").addClass("layui-btn-disabled");
        $("#noeditBtn").removeClass("layui-btn-disabled");
        $("#submit").removeClass("layui-btn-disabled");
        $("#emailInput").removeAttr("readonly");
        $("#phoneInput").removeAttr("readonly");
        $("#ecodeInput").removeAttr("readonly");
        $("#adressInput").removeAttr("readonly");
        $("#uploadBtn").removeClass("layui-btn-disabled");
        $("#nan").attr("disabled", false);
        $("#nv").attr("disabled", false);
        $("#fileBtn").attr("disabled", false);
        form.render('radio', 'userfrm');

    });
    $("#noeditBtn").click(function () {
        $("#noeditBtn").addClass("layui-btn-disabled");
        $("#editBtn").removeClass("layui-btn-disabled");
        $("#submit").addClass("layui-btn-disabled");
        $("#emailInput").attr("readonly", true);
        $("#phoneInput").attr("readonly", true);
        $("#ecodeInput").attr("readonly", true);
        $("#adressInput").attr("readonly", true);
        $("#nan").attr("disabled", true);
        $("#nv").attr("disabled", true);
        $("#fileBtn").attr("disabled", true);
        $("#uploadBtn").addClass("layui-btn-disabled");
        $(".layui-upload-img").remove();
        form.val('userfrm', {
            email: email1,
            gender: gender1,
            phone: phone1,
            ecode: ecode1,
            address: address1,
        })
    });

    var upLoadIns = upload.render({
        elem: '#fileBtn',
        url: getRootPath() + "/upload/picUpload.action",
        size: "2000kb",
        accept: 'images',
        /*acceptMime: 'image/jpg, image/png',*/
        fileAccept: 'image/*',//只能打开图片格式
        multiple: false,
        auto: false,//变成点击上传
        bindAction: '#uploadBtn',//点击之后上传
        choose: function (obj) {
            var files = obj.pushFile();
            obj.preview(function (index, file, result) {
                oldA = result;
                $("#uploadPic").remove();//删除img
                $("#uploadBtn").removeClass("layui-disabled");
                $("#piclable").append('<img class="layui-upload-img" style="width: 60px; height: 60px" src="' + result + '" id="uploadPic">');
                $("#uploadPic").attr({"src": result});
            });
        },
        before: function (obj) {
            layer.load();
        },
        done: function (obj, index, upload) {
            if (obj.code == '0000') {
                layer.closeAll('loading');
                layer.msg('上传头像成功重新登录可看', {time: 1000});
            } else {
                layer.closeAll('loading');
                layer.msg('上传头像失败', {time: 1000});
                var item = this.item;
            }
        },
        error: function () {
            layer.msg('上传头像失败', {time: 1000});
            layer.closeAll('loading');
        },
    });
    form.on('submit(*)', function (data) {
        var d = data.field;
        req = {
            email: data.field.email,
            ecode: data.field.ecode,
            gender: data.field.gender,
            address: data.field.address,
            phone:data.field.phone,
        };
        if (d.email == email1 && d.ecode == ecode1 && d.gender == gender1 && d.address == address1) {
            layer.msg('你没有修改请勿提交', {time: 1000});
            return false;
        } else {
            layer.load();
            $.ajax({
                async: false,
                cache: false,
                url: getRootPath() + "/user/chanageUser.action",
                data: req,
                success: function (res) {
                    layer.closeAll('loading');
                    if (res) {
                        layer.msg('修改成功', {time: 1000} , function () {
                            init();
                            $("#noeditBtn").click();
                        });

                    } else {
                        layer.msg('修改失败', {time: 1000});
                    }
                }
            })
        }
        return false;
    });
    form.render();

});