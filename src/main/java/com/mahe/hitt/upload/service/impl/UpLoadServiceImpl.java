package com.mahe.hitt.upload.service.impl;

import com.mahe.hitt.entity.User;
import com.mahe.hitt.exception.CheckException;
import com.mahe.hitt.mapper.UpLoadMapper;
import com.mahe.hitt.upload.service.UpLoadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/18--10:08
 * @Description
 **/
@Service
public class UpLoadServiceImpl implements UpLoadService {

	@Autowired
	private UpLoadMapper upLoadMapper;

	@Override
	public Boolean insertUserPic(MultipartFile file, HttpSession session) {
		String filePathName = session.getServletContext().getRealPath("/upload/pic/");// 获取路径文件存储
		String fileNewName = "";// 获取文件的新的名称
		String fileName = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		if (file != null) {
			fileName = file.getOriginalFilename();// 获取文件的原名称
			fileNewName = ((User) session.getAttribute("user")).getUsername()+"-"+sdf.format(date)+ "."
					+ FilenameUtils.getExtension(fileName);// 拼接新名称
		}
		System.out.println("44" + filePathName);
		System.out.println("33" + fileNewName);
		File uploadDir = new File(filePathName + fileNewName);
		if (!uploadDir.isDirectory()) {
			// 如果不存在，创建文件夹
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
		}
		try {
			System.out.println("11" + uploadDir);
			file.transferTo(uploadDir);
			System.out.println("22" + file);
			if (upLoadMapper.insertUserPic(fileNewName, ((User) session.getAttribute("user")).getUid())) {
				((User) session.getAttribute("user")).setPic(fileNewName);
				return true;
			}
		} catch (IOException e) {
			throw new CheckException("失败");
		}
		return true;
	}
}
