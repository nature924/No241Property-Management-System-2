package com.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.Admin;
import com.boot.service.AdminService;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/login", produces = "application/json; charset=utf-8")// 设置路径
@CrossOrigin // 允许从不同的域访问其资源
public class LoginController extends BaseController {

	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private AdminService adminService;

	// 管理员登录
	@PostMapping(value = "login.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Admin adminEntity = new Admin();
		adminEntity.setUsername(username);
		List<Admin> adminlist = this.adminService.getAdminByCond(adminEntity);
		if (adminlist.size() == 0) {
			map.put("success", false);
			map.put("message", "用户名不存在");
		} else {
			Admin admin = adminlist.get(0);
			if (password.equals(admin.getPassword())) {
				map.put("success", true);
				map.put("message", "登录成功");
				map.put("adminid", admin.getAdminid());
				map.put("adminname", admin.getUsername());
				map.put("realname", admin.getRealname());
				map.put("role", "管理员");
			} else {
				map.put("success", false);
				map.put("message", "密码错误");
			}
		}
		return map;
	}

	// 管理员退出登录
	@GetMapping("exit.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> exit() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}

}










