function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(projectName);
}
var flag = "close";
function closeLog() {
    alert(flag);
    if (flag == "close") {
        $.ajax({
            async: false,
            cache: false,
            url: getRootPath() + "/login/logout.action",
        });
    }
}
function fLog() {
    flag = "f";
}

//禁止浏览器使用backspace键后退网页
function banBackSpace(e) {
    var ev = e || window.event;
//各种浏览器下获取事件对象
    var obj = ev.relatedTarget || ev.srcElement || ev.target || ev.currentTarget;
//按下Backspace键
    if(ev.keyCode == 8) {
        var tagName = obj.nodeName //标签名称
//如果标签不是input或者textarea则阻止Backspace
        if(tagName != 'INPUT' && tagName != 'TEXTAREA') {
            return stopIt(ev);
        }
        var tagType = obj.type.toUpperCase(); //标签类型
//input标签除了下面几种类型，全部阻止Backspace
        if(tagName == 'INPUT' && (tagType != 'TEXT' && tagType != 'TEXTAREA' && tagType != 'PASSWORD')) {
            return stopIt(ev);
        }
//input或者textarea输入框如果不可编辑则阻止Backspace
        if((tagName == 'INPUT' || tagName == 'TEXTAREA') && (obj.readOnly == true || obj.disabled == true)) {
            return stopIt(ev);
        }
    }
}
function stopIt(ev) {
    if(ev.preventDefault) {
//preventDefault()方法阻止元素发生默认的行为
        ev.preventDefault();
    }
    if(ev.returnValue) {
//IE浏览器下用window.event.returnValue = false;实现阻止元素发生默认的行为
        ev.returnValue = false;
    }
    return false;
}
//对功能按键的获取
document.onkeydown = banBackSpace;


    //防止页面后退
    history.pushState(null, null, document.URL);
window.addEventListener('popstate', function() {
    history.pushState(null, null, document.URL);
});
