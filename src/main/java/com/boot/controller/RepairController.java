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
import com.boot.entity.Repair;
import com.boot.service.RepairService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/repair", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class RepairController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private RepairService repairService;

	// 预处理 获取基础参数
	@GetMapping(value = "createRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createRepair() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("rno", "R" + VeDate.getStringDatex());
		return map;
	}

	// 新增业主报修
	@PostMapping(value = "insertRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertRepair(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Repair repair = new Repair();
		repair.setRno(obj.getString("rno")); // 为维修单号赋值
		repair.setUsersid(""); // 为报修业主赋值
		repair.setReason(""); // 为报修原因赋值
		repair.setContents(""); // 为故障描述赋值
		repair.setAddtime(VeDate.getStringDateShort()); // 为报修日期赋值
		repair.setStatus(""); // 为状态赋值
		repair.setEmploy(obj.getString("employ")); // 为维修人赋值
		repair.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.repairService.insertRepair(repair);
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

	// 按主键删除一个业主报修
	@GetMapping(value = "deleteRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteRepair(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.repairService.deleteRepair(id);
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

	// 按主键批量删除业主报修
	@PostMapping(value = "deleteRepairByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteRepairByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String repairid : ids) {
			num += this.repairService.deleteRepair(repairid);
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

	// 修改业主报修
	@PostMapping(value = "updateRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateRepair(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Repair repair = this.repairService.getRepairById(obj.getString("repairid")); // 获取object中repairid字段
		repair.setRno(obj.getString("rno")); // 为维修单号赋值
		repair.setEmploy(obj.getString("employ")); // 为维修人赋值
		repair.setMemo(obj.getString("memo")); // 为备注赋值
		repair.setStatus("确认维修");
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.repairService.updateRepair(repair);
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

	// 更新业主报修状态
	@GetMapping(value = "status.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> status(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Repair repair = this.repairService.getRepairById(id);
		String status = "";
		if ("".equals(repair.getStatus())) {
			repair.setStatus(status);
		}
		int num = this.repairService.updateRepair(repair);
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

	// 查询全部业主报修数据 在下拉菜单中显示
	@GetMapping(value = "getAllRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Repair> getAllRepair() {
		return this.repairService.getAllRepair();
	}

	// 查询全部业主报修数据 在下拉菜单中显示
	@GetMapping(value = "getRepairMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getRepairMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Repair repair = new Repair();
		repair.setRno(keywords);
		List<Repair> list = this.repairService.getRepairByLike(repair);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示业主报修数据
	@GetMapping(value = "getRepairByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getRepairByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Repair> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Repair> list = this.repairService.getAllRepair();
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

	// 通过AJAX在表格中显示业主报修数据
	@GetMapping(value = "getRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getRepair(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Repair> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Repair repair = new Repair();
		repair.setRno(keywords);
		List<Repair> list = this.repairService.getRepairByLike(repair);
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

	// 按主键查询业主报修数据
	@GetMapping(value = "getRepairById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Repair getRepairById(String id) {
		Repair repair = this.repairService.getRepairById(id);
		return repair;
	}

	// TODO Auto-generated method stub
}
