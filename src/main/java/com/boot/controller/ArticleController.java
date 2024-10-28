package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.Article;
import com.boot.service.ArticleService;
import com.boot.util.VeDate;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/article", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ArticleController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ArticleService articleService;

	// 预处理 获取基础参数
	@GetMapping(value = "createArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createArticle() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增网站内容
	@PostMapping(value = "insertArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertArticle(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Article article = new Article();
		article.setTitle(obj.getString("title")); //  为标题赋值
		article.setBannerid(obj.getString("bannerid")); //  为栏目赋值
		article.setImage(obj.getString("image")); //  为图片赋值
		article.setIstop(obj.getString("istop")); //  为是否置顶赋值
		article.setIsflv(obj.getString("isflv")); //  为是否轮播赋值
		article.setContents(obj.getString("contents")); //  为内容赋值
		article.setAddtime(VeDate.getStringDateShort()); // 为发布日期赋值 
		article.setHits("0"); //  为点击数赋值
		int num = this.articleService.insertArticle(article);
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

	// 按主键删除一个网站内容
	@GetMapping(value = "deleteArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteArticle(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.articleService.deleteArticle(id);
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

	// 按主键批量删除网站内容
	@PostMapping(value = "deleteArticleByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteArticleByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String articleid : ids) {
			num += this.articleService.deleteArticle(articleid);
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

	// 修改网站内容
	@PostMapping(value = "updateArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateArticle(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Article article = this.articleService.getArticleById(obj.getString("articleid")); // 获取object中articleid字段
		article.setTitle(obj.getString("title")); //  为标题赋值
		article.setBannerid(obj.getString("bannerid")); //  为栏目赋值
		article.setImage(obj.getString("image")); //  为图片赋值
		article.setIstop(obj.getString("istop")); //  为是否置顶赋值
		article.setIsflv(obj.getString("isflv")); //  为是否轮播赋值
		article.setContents(obj.getString("contents")); //  为内容赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.articleService.updateArticle(article);
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

	// 查询全部网站内容数据 在下拉菜单中显示
	@GetMapping(value = "getAllArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Article> getAllArticle() {
		return this.articleService.getAllArticle();
	}

	// 查询全部网站内容数据 在下拉菜单中显示
	@GetMapping(value = "getArticleMap.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getArticleMap(String keywords) {
		Map<String, Object> map = new HashMap<String, Object>();
		Article article = new Article();
		article.setTitle(keywords);
		List<Article> list = this.articleService.getArticleByLike(article);
		map.put("data", list);
		map.put("total", list.size());
		return map;
	}

	// 通过AJAX在表格中显示网站内容数据
	@GetMapping(value = "getArticleByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getArticleByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Article> list = this.articleService.getAllArticle();

        System.out.println("------------"+list);
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

	// 通过AJAX在表格中显示网站内容数据
	@GetMapping(value = "getArticle.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getArticle(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Article article = new Article();
		article.setTitle(keywords);
		List<Article> list = this.articleService.getArticleByLike(article);
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

	// 按主键查询网站内容数据
	@GetMapping(value = "getArticleById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Article getArticleById(String id) {
		Article article = this.articleService.getArticleById(id);
		return article;
	}

	// TODO Auto-generated method stub
}


