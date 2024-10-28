package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.Article;
import com.boot.entity.Banner;
import com.boot.entity.Charge;
import com.boot.entity.Complains;
import com.boot.entity.Parks;
import com.boot.entity.Repair;
import com.boot.entity.Users;
import com.boot.service.ArticleService;
import com.boot.service.BannerService;
import com.boot.service.ChargeService;
import com.boot.service.ComplainsService;
import com.boot.service.ParksService;
import com.boot.service.RepairService;
import com.boot.service.UsersService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/index", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class IndexController extends BaseController {
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ArticleService articleService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BannerService bannerService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ChargeService chargeService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ComplainsService complainsService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ParksService parksService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private RepairService repairService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private UsersService usersService;

	// TODO Auto-generated method stub

	@GetMapping(value = "front.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> front() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Banner> bannerList = this.bannerService.getAllBanner();
		map.put("bannerList", bannerList);
		return map;
	}

	// 前台首页
	@GetMapping(value = "index.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Banner> bannerList = this.bannerService.getAllBanner();
		List<Banner> frontList = new ArrayList<Banner>();
		for (Banner banner : bannerList) {
			List<Article> articleList = this.articleService.getArticleByBanner(banner.getBannerid());
			banner.setArticleList(articleList);
			frontList.add(banner);
		}
		map.put("frontList", frontList);
		List<Article> topList = this.articleService.getTopArticle();
		map.put("topList", topList);
		List<Article> favList = this.articleService.getFlvArticle();
		map.put("favList", favList);
		return map;
	}

	@GetMapping(value = "article.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> article(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Banner banner = this.bannerService.getBannerById(id);
		Article article = new Article();
		article.setBannerid(id);
		List<Article> list = this.articleService.getArticleByCond(article);
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		map.put("banner", banner);
		return map;
	}

	@GetMapping(value = "read.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> read(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Article article = this.articleService.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleService.updateArticle(article);
		map.put("article", article);
		return map;
	}

	// 用户登录
	@PostMapping(value = "login.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Users usersEntity = new Users();
		usersEntity.setUsername(username);
		List<Users> userslist = this.usersService.getUsersByCond(usersEntity);
		if (userslist.size() == 0) {
			map.put("success", false);
			map.put("message", "用户名不存在");
		} else {
			Users users = userslist.get(0);
			if (password.equals(users.getPassword())) {
				map.put("success", true);
				map.put("message", "登录成功");
				map.put("userid", users.getUsersid());
				map.put("username", users.getUsername());
				map.put("realname", users.getRealname());
			} else {
				map.put("success", false);
				map.put("message", "密码错误");
			}
		}
		return map;
	}

	// 修改密码
	@PostMapping(value = "editpwd.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> editpwd(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		String userid = obj.getString("userid");
		String password = obj.getString("password");
		String repassword = obj.getString("repassword");
		int num = 0;
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			num = this.usersService.updateUsers(users);
			if (num > 0) {
				map.put("success", true);
				map.put("code", num);
				map.put("message", "修改成功");
			} else {
				map.put("success", false);
				map.put("code", num);
				map.put("message", "修改失败");
			}
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "旧密码错误");
		}
		return map;
	}

	// 查看个人信息
	@GetMapping(value = "userinfo.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> userinfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = this.usersService.getUsersById(id);
		map.put("users", users);
		return map;
	}

	// 修改个人信息
	@PostMapping(value = "personal.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> personal(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = this.usersService.getUsersById(obj.getString("usersid"));
		users.setUsername(obj.getString("username"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		int num = this.usersService.updateUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "注册成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "注册失败");
		}
		return map;
	}

	// 添加意见反馈信息
	@PostMapping(value = "addComplains.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> addComplains(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Complains complains = new Complains();
		complains.setAddtime(VeDate.getStringDateShort());
		complains.setStatus("未回复");
		complains.setReps(" ");
		complains.setTitle(obj.getString("title"));
		complains.setContents(obj.getString("contents"));
		complains.setUsersid(obj.getString("userid"));
		int num = this.complainsService.insertComplains(complains);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "反馈成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "反馈失败");
		}
		return map;
	}

	// 我的意见反馈信息
	@RequestMapping(value = "myComplains.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> myComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = new Complains();
		complains.setUsersid(userid);
		// 定义当前页和分页条数
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Complains> complainsList = this.complainsService.getComplainsByCond(complains);
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", complainsList.size());
		map.put("data", complainsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	// 通过AJAX在表格中显示物业收费数据
	@GetMapping(value = "myCharge.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> myCharge(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String userid) {
		System.out.println("userid ==> " + userid);
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Charge> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Charge charge = new Charge();
		charge.setUsersid(userid);
		List<Charge> list = this.chargeService.getChargeByCond(charge);
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("list", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "myParks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> myParks(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String userid) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Parks> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Parks parks = new Parks();
		parks.setUsersid(userid);
		List<Parks> list = this.parksService.getParksByCond(parks);
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

	// 新增业主报修
	@PostMapping(value = "addRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> addRepair(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Repair repair = new Repair();
		repair.setRno(obj.getString("rno")); // 为维修单号赋值
		repair.setUsersid(obj.getString("userid")); // 为报修业主赋值
		repair.setReason(obj.getString("reason")); // 为报修原因赋值
		repair.setContents(obj.getString("contents")); // 为故障描述赋值
		repair.setAddtime(VeDate.getStringDateShort()); // 为报修日期赋值
		repair.setStatus("待维修"); // 为状态赋值
		repair.setEmploy(""); // 为维修人赋值
		repair.setMemo(""); // 为备注赋值
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

	// 通过AJAX在表格中显示业主报修数据
	@GetMapping(value = "myRepair.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> myRepair(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String userid) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Repair> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Repair repair = new Repair();
		repair.setUsersid(userid);
		List<Repair> list = this.repairService.getRepairByCond(repair);
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

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/*", method = RequestMethod.OPTIONS)
	public ResponseEntity handleOptions() {
		return (ResponseEntity) ResponseEntity.noContent();
	}

	// TODO Auto-generated method stub
}
