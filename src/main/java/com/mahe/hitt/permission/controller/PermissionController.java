package com.mahe.hitt.permission.controller;

import com.mahe.hitt.entity.Meun;
import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.permission.service.PermissionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @Author 马鹤
 * @Date 2019/7/15--18:31
 * @Description 权限的控制器
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionServiceImpl;

    /*
    *   根据你的操作的页面查询你的操作集
    * */
    @RequestMapping(value="/selectPermission.action")
    @ResponseBody
    public JSONArray selectPermission(@RequestParam(value = "title") String title , HttpSession session){
        return JSONArray.fromObject(permissionServiceImpl.selectPermission(title , session));
    }

    /*
    *   返回带有信息的操作集
    * */
    @RequestMapping(value="/selectPermissionMsg.action" , method = RequestMethod.POST)
    @ResponseBody
    public IResult selectPermissionMsg(@RequestParam(value = "title") String title , HttpSession session){
        return new ResultBean<Collection<Meun>>(permissionServiceImpl.selectPermission(title , session));
    }
}
