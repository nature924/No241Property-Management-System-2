package com.boot.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.boot.util.VeDate;

@Controller //定义为控制器
@RequestMapping(value = "/ajaxupload", produces = "application/json; charset=utf-8")// 设置路径
@CrossOrigin // 允许从不同的域访问其资源
public class AjaxUploadController extends BaseController {

	@PostMapping("upload.action")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam(value = "image", required = false) MultipartFile file) {
		String path = this.getRequest().getSession().getServletContext().getRealPath("/") + File.separator + "upfiles/";
		String imageFileName = file.getOriginalFilename();
		int i = imageFileName.lastIndexOf(".");
		String name = String.valueOf(VeDate.getStringDatex());
		String type = imageFileName.substring(i + 1);
		imageFileName = name + "." + type;
		File targetFile = new File(path, imageFileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			file.transferTo(targetFile);
			map.put("success", true);
			map.put("url", imageFileName);
			map.put("message", "上传成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", "上传失败");
		}
		return map;
	}
}
































