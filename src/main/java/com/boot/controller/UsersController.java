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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.Users;
import com.boot.service.UsersService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class UsersController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private UsersService usersService;

	// 预处理 获取基础参数
	@GetMapping(value = "createUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createUsers() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增小区业主
	@PostMapping(value = "insertUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertUsers(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = new Users();
		users.setUsername(obj.getString("username")); //  为用户名赋值
		users.setPassword(obj.getString("password")); //  为密码赋值
		users.setRealname(obj.getString("realname")); //  为姓名赋值
		users.setSex(obj.getString("sex")); //  为性别赋值
		users.setBirthday(obj.getString("birthday")); //  为出生日期赋值
		users.setBuildsid(obj.getString("buildsid")); //  为楼栋赋值
		users.setAddress(obj.getString("address")); //  为地址赋值
		users.setContact(obj.getString("contact")); //  为联系方式赋值
		users.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值 
		users.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.usersService.insertUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "保存成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "保存失败");
		}
		return map;
	}

	// 按主键删除一个小区业主
	@GetMapping(value = "deleteUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteUsers(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.usersService.deleteUsers(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "删除成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "删除失败");
		}
		return map;
	}

	// 按主键批量删除小区业主
	@PostMapping(value = "deleteUsersByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteUsersByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String usersid : ids) {
			num += this.usersService.deleteUsers(usersid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "删除成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "删除失败");
		}
		return map;
	}

	// 修改小区业主
	@PostMapping(value = "updateUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateUsers(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = this.usersService.getUsersById(obj.getString("usersid")); // 获取object中usersid字段
		users.setUsername(obj.getString("username")); //  为用户名赋值
		users.setRealname(obj.getString("realname")); //  为姓名赋值
		users.setSex(obj.getString("sex")); //  为性别赋值
		users.setBirthday(obj.getString("birthday")); //  为出生日期赋值
		users.setBuildsid(obj.getString("buildsid")); //  为楼栋赋值
		users.setAddress(obj.getString("address")); //  为地址赋值
		users.setContact(obj.getString("contact")); //  为联系方式赋值
		users.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.usersService.updateUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "修改成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "修改失败");
		}
		return map;
	}

	// 查询全部小区业主数据 在下拉菜单中显示
	@GetMapping(value = "getAllUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Users> getAllUsers() {
		return this.usersService.getAllUsers();
	}

	// 查询全部小区业主数据 在下拉菜单中显示
	@GetMapping(value = "getUsersMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getUsersMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = new Users();
		users.setUsername(keywords);
		List<Users> list = this.usersService.getUsersByLike(users);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示小区业主数据
	@GetMapping(value = "getUsersByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getUsersByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Users> list = this.usersService.getAllUsers();
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	// 通过AJAX在表格中显示小区业主数据
	@GetMapping(value = "getUsers.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getUsers(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Users users = new Users();
		users.setUsername(keywords);
		List<Users> list = this.usersService.getUsersByLike(users);
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	// 按主键查询小区业主数据
	@GetMapping(value = "getUsersById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Users getUsersById(String id) {
		Users users = this.usersService.getUsersById(id);
		return users;
	}

	// TODO Auto-generated method stub
}


