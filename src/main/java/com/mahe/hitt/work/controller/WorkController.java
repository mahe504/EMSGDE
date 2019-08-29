package com.mahe.hitt.work.controller;

import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.work.service.WorkService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 马鹤
 * @Date 2019/7/25--9:41
 * @Description 出勤率的控制器
 **/
@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkService workServiceImpl;

    @RequestMapping("/workList.action")
    public String workList(){
        return "forward:/WEB-INF/jsps/work/work.html";
    }

    @RequestMapping(value="/getWorkData.action" , method= RequestMethod.POST)
    @ResponseBody
    public IResult getWorkData(){
        System.out.println(workServiceImpl.getWorkData());
        return new ResultBean<JSONArray>(workServiceImpl.getWorkData());
    }
}
