package com.mahe.hitt.upload.controller;

import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.upload.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 马鹤
 * @Date 2019/7/18--10：04
 * @Description 用户上传图片控制器
 **/
@Controller
@RequestMapping("/upload")
public class PicUploadController {

    @Autowired
    private UpLoadService upLoadServiceImpl;

    @RequestMapping(value="/picUpload.action")
    @ResponseBody
    public IResult upload(MultipartFile file , HttpServletRequest request ,  HttpSession session){

        return new ResultBean(upLoadServiceImpl.insertUserPic(file , session));
    }
}
