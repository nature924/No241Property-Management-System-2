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
import com.boot.entity.Cate;
import com.boot.service.CateService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/cate", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class CateController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private CateService cateService;

	// 预处理 获取基础参数
	@GetMapping(value = "createCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createCate() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增收费类型
	@PostMapping(value = "insertCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertCate(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Cate cate = new Cate();
		cate.setCatename(obj.getString("catename")); //  为类型名称赋值
		cate.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值 
		cate.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.cateService.insertCate(cate);
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

	// 按主键删除一个收费类型
	@GetMapping(value = "deleteCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteCate(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.cateService.deleteCate(id);
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

	// 按主键批量删除收费类型
	@PostMapping(value = "deleteCateByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteCateByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String cateid : ids) {
			num += this.cateService.deleteCate(cateid);
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

	// 修改收费类型
	@PostMapping(value = "updateCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateCate(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Cate cate = this.cateService.getCateById(obj.getString("cateid")); // 获取object中cateid字段
		cate.setCatename(obj.getString("catename")); //  为类型名称赋值
		cate.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.cateService.updateCate(cate);
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

	// 查询全部收费类型数据 在下拉菜单中显示
	@GetMapping(value = "getAllCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Cate> getAllCate() {
		return this.cateService.getAllCate();
	}

	// 查询全部收费类型数据 在下拉菜单中显示
	@GetMapping(value = "getCateMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getCateMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cate cate = new Cate();
		cate.setCatename(keywords);
		List<Cate> list = this.cateService.getCateByLike(cate);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示收费类型数据
	@GetMapping(value = "getCateByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getCateByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Cate> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Cate> list = this.cateService.getAllCate();
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

	// 通过AJAX在表格中显示收费类型数据
	@GetMapping(value = "getCate.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getCate(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Cate> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Cate cate = new Cate();
		cate.setCatename(keywords);
		List<Cate> list = this.cateService.getCateByLike(cate);
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

	// 按主键查询收费类型数据
	@GetMapping(value = "getCateById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Cate getCateById(String id) {
		Cate cate = this.cateService.getCateById(id);
		return cate;
	}

	// TODO Auto-generated method stub
}


