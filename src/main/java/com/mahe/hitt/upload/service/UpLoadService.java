package com.mahe.hitt.upload.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Author 马鹤
 * @Date 2019/7/18--
 * @Description 文件上传的业务接口
 **/
public interface UpLoadService {

    Boolean insertUserPic(MultipartFile file , HttpSession session);
}
