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
import com.boot.entity.Parks;
import com.boot.service.ParksService;
import com.github.pagehelper.Page;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/parks", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ParksController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ParksService parksService;

	// 预处理 获取基础参数
	@GetMapping(value = "createParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createParks() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	// 新增停车位
	@PostMapping(value = "insertParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertParks(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Parks parks = new Parks();
		parks.setPno(obj.getString("pno")); // 为车位号赋值
		parks.setAddress(obj.getString("address")); // 为位置赋值
		parks.setSquare(obj.getString("square")); // 为车位面积赋值
		parks.setStatus("空闲"); // 为状态赋值
		parks.setUsersid(""); // 为业主赋值
		parks.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.parksService.insertParks(parks);
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

	// 按主键删除一个停车位
	@GetMapping(value = "deleteParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteParks(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.parksService.deleteParks(id);
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

	// 按主键批量删除停车位
	@PostMapping(value = "deleteParksByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteParksByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String parksid : ids) {
			num += this.parksService.deleteParks(parksid);
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

	// 修改停车位
	@PostMapping(value = "updateParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateParks(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Parks parks = this.parksService.getParksById(obj.getString("parksid")); // 获取object中parksid字段
		parks.setPno(obj.getString("pno")); // 为车位号赋值
		parks.setAddress(obj.getString("address")); // 为位置赋值
		parks.setSquare(obj.getString("square")); // 为车位面积赋值
		parks.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.parksService.updateParks(parks);
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

	// 更新停车位状态
	@PostMapping(value = "status.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> status(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		String id = obj.getString("parksid");
		String usersid = obj.getString("usersid");
		System.out.println("usersid ==> " + usersid);
		Map<String, Object> map = new HashMap<String, Object>();
		Parks parks = this.parksService.getParksById(id);
		String status = "使用";
		parks.setStatus(status);
		parks.setUsersid(usersid);
		int num = this.parksService.updateParks(parks);
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

	// 查询全部停车位数据 在下拉菜单中显示
	@GetMapping(value = "getAllParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Parks> getAllParks() {
		return this.parksService.getAllParks();
	}

	// 查询全部停车位数据 在下拉菜单中显示
	@GetMapping(value = "getParksMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getParksMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Parks parks = new Parks();
		parks.setPno(keywords);
		List<Parks> list = this.parksService.getParksByLike(parks);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示停车位数据
	@GetMapping(value = "getParksByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getParksByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Parks> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Parks> list = this.parksService.getAllParks();
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

	// 通过AJAX在表格中显示停车位数据
	@GetMapping(value = "getParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getParks(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Parks> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Parks parks = new Parks();
		parks.setPno(keywords);
		List<Parks> list = this.parksService.getParksByLike(parks);
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

	// 按主键查询停车位数据
	@GetMapping(value = "getParksById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Parks getParksById(String id) {
		Parks parks = this.parksService.getParksById(id);
		return parks;
	}

	// TODO Auto-generated method stub
}
