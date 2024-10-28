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
import com.boot.entity.Charge;
import com.boot.service.ChargeService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/charge", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ChargeController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ChargeService chargeService;

	// 预处理 获取基础参数
	@GetMapping(value = "createCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createCharge() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("cno", "C" + VeDate.getStringDatex());
		return map;
	}

	// 新增物业收费
	@PostMapping(value = "insertCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertCharge(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Charge charge = new Charge();
		charge.setCno(obj.getString("cno")); // 为收费单号赋值
		charge.setBuildsid(obj.getString("buildsid")); // 为楼栋赋值
		charge.setUsersid(obj.getString("usersid")); // 为业主赋值
		charge.setCateid(obj.getString("cateid")); // 为收费类型赋值
		charge.setTotal(obj.getString("total")); // 为总计赋值
		charge.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值
		charge.setStatus("欠费"); // 为状态赋值
		charge.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.chargeService.insertCharge(charge);
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

	// 按主键删除一个物业收费
	@GetMapping(value = "deleteCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteCharge(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.chargeService.deleteCharge(id);
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

	// 按主键批量删除物业收费
	@PostMapping(value = "deleteChargeByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteChargeByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String chargeid : ids) {
			num += this.chargeService.deleteCharge(chargeid);
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

	// 修改物业收费
	@PostMapping(value = "updateCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateCharge(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Charge charge = this.chargeService.getChargeById(obj.getString("chargeid")); // 获取object中chargeid字段
		charge.setCno(obj.getString("cno")); // 为收费单号赋值
		charge.setBuildsid(obj.getString("buildsid")); // 为楼栋赋值
		charge.setUsersid(obj.getString("usersid")); // 为业主赋值
		charge.setCateid(obj.getString("cateid")); // 为收费类型赋值
		charge.setTotal(obj.getString("total")); // 为总计赋值
		charge.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.chargeService.updateCharge(charge);
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

	// 更新物业收费状态
	@GetMapping(value = "status.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> status(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Charge charge = this.chargeService.getChargeById(id);
		String status = "已缴费";
		charge.setStatus(status);
		int num = this.chargeService.updateCharge(charge);
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

	// 查询全部物业收费数据 在下拉菜单中显示
	@GetMapping(value = "getAllCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Charge> getAllCharge() {
		return this.chargeService.getAllCharge();
	}

	// 查询全部物业收费数据 在下拉菜单中显示
	@GetMapping(value = "getChargeMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getChargeMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Charge charge = new Charge();
		charge.setCno(keywords);
		List<Charge> list = this.chargeService.getChargeByLike(charge);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示物业收费数据
	@GetMapping(value = "getChargeByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getChargeByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Charge> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Charge> list = this.chargeService.getAllCharge();
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

	// 通过AJAX在表格中显示物业收费数据
	@GetMapping(value = "getCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getCharge(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Charge> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Charge charge = new Charge();
		charge.setCno(keywords);
		List<Charge> list = this.chargeService.getChargeByLike(charge);
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

	// 按主键查询物业收费数据
	@GetMapping(value = "getChargeById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Charge getChargeById(String id) {
		Charge charge = this.chargeService.getChargeById(id);
		return charge;
	}

	// TODO Auto-generated method stub
}
