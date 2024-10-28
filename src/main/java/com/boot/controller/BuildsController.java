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
import com.boot.entity.Builds;
import com.boot.service.BuildsService;
import com.github.pagehelper.Page;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/builds", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class BuildsController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BuildsService buildsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createBuilds() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	// 新增楼栋
	@PostMapping(value = "insertBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertBuilds(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Builds builds = new Builds();
		builds.setBuildsname(obj.getString("buildsname")); //  为楼栋名称赋值
		builds.setAddress(obj.getString("address")); //  为楼栋位置赋值
		builds.setNum(obj.getString("num")); //  为单元数赋值
		builds.setFloor(obj.getString("floor")); //  为楼层数赋值
		builds.setManager(obj.getString("manager")); //  为负责人赋值
		builds.setContact(obj.getString("contact")); //  为联系方式赋值
		builds.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.buildsService.insertBuilds(builds);
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

	// 按主键删除一个楼栋
	@GetMapping(value = "deleteBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBuilds(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.buildsService.deleteBuilds(id);
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

	// 按主键批量删除楼栋
	@PostMapping(value = "deleteBuildsByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBuildsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String buildsid : ids) {
			num += this.buildsService.deleteBuilds(buildsid);
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

	// 修改楼栋
	@PostMapping(value = "updateBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateBuilds(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Builds builds = this.buildsService.getBuildsById(obj.getString("buildsid")); // 获取object中buildsid字段
		builds.setBuildsname(obj.getString("buildsname")); //  为楼栋名称赋值
		builds.setAddress(obj.getString("address")); //  为楼栋位置赋值
		builds.setNum(obj.getString("num")); //  为单元数赋值
		builds.setFloor(obj.getString("floor")); //  为楼层数赋值
		builds.setManager(obj.getString("manager")); //  为负责人赋值
		builds.setContact(obj.getString("contact")); //  为联系方式赋值
		builds.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.buildsService.updateBuilds(builds);
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

	// 查询全部楼栋数据 在下拉菜单中显示
	@GetMapping(value = "getAllBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Builds> getAllBuilds() {
		return this.buildsService.getAllBuilds();
	}

	// 查询全部楼栋数据 在下拉菜单中显示
	@GetMapping(value = "getBuildsMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBuildsMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Builds builds = new Builds();
		builds.setBuildsname(keywords);
		List<Builds> list = this.buildsService.getBuildsByLike(builds);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示楼栋数据
	@GetMapping(value = "getBuildsByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBuildsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Builds> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Builds> list = this.buildsService.getAllBuilds();
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

	// 通过AJAX在表格中显示楼栋数据
	@GetMapping(value = "getBuilds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBuilds(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Builds> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Builds builds = new Builds();
		builds.setBuildsname(keywords);
		List<Builds> list = this.buildsService.getBuildsByLike(builds);
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

	// 按主键查询楼栋数据
	@GetMapping(value = "getBuildsById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Builds getBuildsById(String id) {
		Builds builds = this.buildsService.getBuildsById(id);
		return builds;
	}

	// TODO Auto-generated method stub
}


