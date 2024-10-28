package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 网站内容表的实体类
public class Article {
	private String articleid = "A"+VeDate.getStringId(); // 生成主键编号
	private String title; // 标题
	private String bannerid; // 栏目
	private String image; // 图片
	private String istop; // 是否置顶
	private String isflv; // 是否轮播
	private String contents; // 内容
	private String addtime; // 发布日期
	private String hits; // 点击数
	private String bannername; // 映射数据
	private Banner banner;// 多对一映射类
	public String getArticleid() {
		return this.articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBannerid() {
		return this.bannerid;
	}

	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIstop() {
		return this.istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

	public String getIsflv() {
		return this.isflv;
	}

	public void setIsflv(String isflv) {
		this.isflv = isflv;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public Banner getBanner() {
		return this.banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

	public String getBannername() {
		return this.bannername;
	}

	public void setBannername(String bannername) {
		this.bannername = bannername;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("articleid", this.articleid); // 主键编号
		jsonString.put("title", this.title); // 标题
		jsonString.put("bannerid", this.bannerid); // 栏目
		jsonString.put("image", this.image); // 图片
		jsonString.put("istop", this.istop); // 是否置顶
		jsonString.put("isflv", this.isflv); // 是否轮播
		jsonString.put("contents", this.contents); // 内容
		jsonString.put("addtime", this.addtime); // 发布日期
		jsonString.put("hits", this.hits); // 点击数
		jsonString.put("Banner", this.banner); // 多对一映射类
		jsonString.put("bannername", this.bannername); // 映射数据
		return jsonString.toString();
	}




}




