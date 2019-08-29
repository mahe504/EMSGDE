/**
 * 通用配置
 * Created by mahe
 * 本页面能获取项目的工程名的前缀等同于${pageContext.request.contextPath}
 */
/*开发环境*/
var runEnv = 'dev';
var curWwwPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
var localhostPaht=curWwwPath.substring(0,pos);
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
// $config不建议直接引用，除了用于指定模块基目录。可以通过$tool模块中的方法获取
var $config = {
    apiContext: runEnv === 'product' ? localhostPaht+projectName+"/" : localhostPaht+projectName+"/", // api请求地址
    resUrl: runEnv === 'product' ? 'http://localhost:10086/' : localhostPaht+projectName+"/" // 前端资源访问根目录,生产环境请设置为托管前端资源的位置
};

