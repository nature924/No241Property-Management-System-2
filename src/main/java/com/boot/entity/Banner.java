package com.boot.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 网站栏目表的实体类
public class Banner {
	private String bannerid = "B" + VeDate.getStringId(); // 生成主键编号
	private String bannername; // 栏目名称
	private String addtime; // 创建日期
	private String memo; // 备注
	private List<Article> articleList = new ArrayList<Article>();// 新闻公告映射

	public String getBannerid() {
		return this.bannerid;
	}

	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	public String getBannername() {
		return this.bannername;
	}

	public void setBannername(String bannername) {
		this.bannername = bannername;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<Article> getArticleList() {
		return this.articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	// 重载方法 生成JSON类型字符串
	@Override
	public String toString() {
		return this.toJsonString();
	}

	// 直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("bannerid", this.bannerid); // 主键编号
		jsonString.put("bannername", this.bannername); // 栏目名称
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		return jsonString.toString();
	}

}
