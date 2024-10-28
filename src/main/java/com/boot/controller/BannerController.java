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
import com.boot.entity.Banner;
import com.boot.service.BannerService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/banner", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class BannerController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BannerService bannerService;

	// 预处理 获取基础参数
	@GetMapping(value = "createBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createBanner() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增网站栏目
	@PostMapping(value = "insertBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertBanner(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Banner banner = new Banner();
		banner.setBannername(obj.getString("bannername")); //  为栏目名称赋值
		banner.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值 
		banner.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.bannerService.insertBanner(banner);
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

	// 按主键删除一个网站栏目
	@GetMapping(value = "deleteBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBanner(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.bannerService.deleteBanner(id);
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

	// 按主键批量删除网站栏目
	@PostMapping(value = "deleteBannerByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBannerByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String bannerid : ids) {
			num += this.bannerService.deleteBanner(bannerid);
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

	// 修改网站栏目
	@PostMapping(value = "updateBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateBanner(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Banner banner = this.bannerService.getBannerById(obj.getString("bannerid")); // 获取object中bannerid字段
		banner.setBannername(obj.getString("bannername")); //  为栏目名称赋值
		banner.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.bannerService.updateBanner(banner);
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

	// 查询全部网站栏目数据 在下拉菜单中显示
	@GetMapping(value = "getAllBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Banner> getAllBanner() {
		return this.bannerService.getAllBanner();
	}

	// 查询全部网站栏目数据 在下拉菜单中显示
	@GetMapping(value = "getBannerMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBannerMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Banner banner = new Banner();
		banner.setBannername(keywords);
		List<Banner> list = this.bannerService.getBannerByLike(banner);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示网站栏目数据
	@GetMapping(value = "getBannerByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBannerByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Banner> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Banner> list = this.bannerService.getAllBanner();
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

	// 通过AJAX在表格中显示网站栏目数据
	@GetMapping(value = "getBanner.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBanner(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Banner> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Banner banner = new Banner();
		banner.setBannername(keywords);
		List<Banner> list = this.bannerService.getBannerByLike(banner);
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

	// 按主键查询网站栏目数据
	@GetMapping(value = "getBannerById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Banner getBannerById(String id) {
		Banner banner = this.bannerService.getBannerById(id);
		return banner;
	}

	// TODO Auto-generated method stub
}


