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

    var oldRname, oldRcode;

    function getURLParams(param) {
        var params = $tool.getQueryParam();
        return params[param];
    }

    $(function () {
        var req = {
            'id': getURLParams("id"),
        };
        $api.doPost(getRootPath() + '/manager/selectRoleById.action', req, function (res) {
            if (res.data != null) {
                oldRname = res.data.rname;
                oldRcode = res.data.rcode;
                form.val('editrole',{
                    'id': res.data.id,
                    'gname': res.data.gname,
                    'gcode': res.data.gcode,
                });
            }
        });
    });
    var status = 0;
    $(function () {
        $("#gnameInput").blur(function () {
            if ($("#gnameInput").val() != "" && $("#gnameInput").val() != oldRname) {
                var req = {
                    'gname': $("#gnameInput").val(),
                }
                $api.doPost(getRootPath() + '/manager/selectRolecodeOrRolenameIsTrue.action', req, function (res) {
                    if (res.data) {
                        status = 0;
                        layer.msg('已经有此角色的请换一个名称', {time: 1000}, function () {
                            $("#gnameInput").attr('value', '');
                        })

                    } else {
                        status = 1;
                    }
                })
            }
        });
        $("#gcodeInput").blur(function () {
            if ($("#gcodeInput").val() != "" && $("#gcodeInput").val() != oldRcode) {
                var req = {
                    'gcode': $("#gcodeInput").val(),
                }
                $api.doPost(getRootPath() + '/manager/selectRolecodeOrRolenameIsTrue.action', req, function (res) {
                    if (res.data) {
                        status = 0;
                        layer.msg('已经有此角色代号了，请换一个试试', {time: 1000}, function () {
                            $("#gcodeInput").attr('value', '');
                        });
                    } else {
                        status = 1;
                    }
                })
            }
        });
    });
    form.on('submit(*)', function (obj) {
        if ($("#gnameInput").val() == oldRname && $("#gcodeInput").val() == oldRcode) {
            layer.msg('你还没有修改信息，不能提交', {time: 1000});
        } else if (status == 1) {
            var req = {
                'id': obj.field.id,
                'gcode': obj.field.gcode,
                'gname': obj.field.gname,
            }
            $api.doPost(getRootPath() + '/manager/chanageRole.action', req, function (res) {
                if (res.data) {
                    layer.msg('修改成功', {time: 1000}, function () {
                        layer.closeAll();
                    })
                } else {
                    layer.msg('修改失败', {time: 1000}, function () {
                    })
                }
            })
        } else {
            layer.msg('请填写正确的信息', {time: 1000}, function () {
            });
        }
        return false;
    });

});