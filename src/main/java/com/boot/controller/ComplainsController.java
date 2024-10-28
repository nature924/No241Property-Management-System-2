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
import com.boot.entity.Complains;
import com.boot.service.ComplainsService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/complains", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ComplainsController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ComplainsService complainsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createComplains() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增意见反馈
	@PostMapping(value = "insertComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertComplains(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Complains complains = new Complains();
		complains.setUsersid(""); // 为业主用户赋值
		complains.setTitle(""); // 为标题赋值
		complains.setContents(obj.getString("contents")); // 为内容赋值
		complains.setAddtime(VeDate.getStringDateShort()); // 为日期赋值
		complains.setStatus(""); // 为状态赋值
		complains.setReps(obj.getString("reps")); // 为回复内容赋值
		int num = this.complainsService.insertComplains(complains);
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

	// 按主键删除一个意见反馈
	@GetMapping(value = "deleteComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteComplains(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.complainsService.deleteComplains(id);
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

	// 按主键批量删除意见反馈
	@PostMapping(value = "deleteComplainsByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteComplainsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String complainsid : ids) {
			num += this.complainsService.deleteComplains(complainsid);
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

	// 修改意见反馈
	@PostMapping(value = "updateComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateComplains(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Complains complains = this.complainsService.getComplainsById(obj.getString("complainsid")); // 获取object中complainsid字段
		complains.setContents(obj.getString("contents")); // 为内容赋值
		complains.setReps(obj.getString("reps")); // 为回复内容赋值
		complains.setStatus("已回复");
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.complainsService.updateComplains(complains);
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

	// 更新意见反馈状态
	@GetMapping(value = "status.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> status(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = this.complainsService.getComplainsById(id);
		String status = "";
		if ("".equals(complains.getStatus())) {
			complains.setStatus(status);
		}
		int num = this.complainsService.updateComplains(complains);
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

	// 查询全部意见反馈数据 在下拉菜单中显示
	@GetMapping(value = "getAllComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Complains> getAllComplains() {
		return this.complainsService.getAllComplains();
	}

	// 查询全部意见反馈数据 在下拉菜单中显示
	@GetMapping(value = "getComplainsMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getComplainsMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = new Complains();
		complains.setUsersid(keywords);
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示意见反馈数据
	@GetMapping(value = "getComplainsByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getComplainsByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Complains> list = this.complainsService.getAllComplains();
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

	// 通过AJAX在表格中显示意见反馈数据
	@GetMapping(value = "getComplains.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Complains complains = new Complains();
		complains.setUsersid(keywords);
		List<Complains> list = this.complainsService.getComplainsByLike(complains);
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

	// 按主键查询意见反馈数据
	@GetMapping(value = "getComplainsById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Complains getComplainsById(String id) {
		Complains complains = this.complainsService.getComplainsById(id);
		return complains;
	}

	// TODO Auto-generated method stub
}
