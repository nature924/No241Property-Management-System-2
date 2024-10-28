package com.boot.entity;

import com.alibaba.fastjson.JSONObject;
import com.boot.util.VeDate;

// 物业收费表的实体类
public class Charge {
	private String chargeid = "C"+VeDate.getStringId(); // 生成主键编号
	private String cno; // 收费单号
	private String buildsid; // 楼栋
	private String usersid; // 业主
	private String cateid; // 收费类型
	private String total; // 总计
	private String addtime; // 创建日期
	private String status; // 状态
	private String memo; // 备注
	private String buildsname; // 映射数据
	private String address; // 映射数据
	private String catename; // 映射数据
	private Builds builds;// 多对一映射类
	private Users users;// 多对一映射类
	private Cate cate;// 多对一映射类
	public String getChargeid() {
		return this.chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getBuildsid() {
		return this.buildsid;
	}

	public void setBuildsid(String buildsid) {
		this.buildsid = buildsid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Builds getBuilds() {
		return this.builds;
	}

	public void setBuilds(Builds builds) {
		this.builds = builds;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Cate getCate() {
		return this.cate;
	}

	public void setCate(Cate cate) {
		this.cate = cate;
	}

	public String getBuildsname() {
		return this.buildsname;
	}

	public void setBuildsname(String buildsname) {
		this.buildsname = buildsname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("chargeid", this.chargeid); // 主键编号
		jsonString.put("cno", this.cno); // 收费单号
		jsonString.put("buildsid", this.buildsid); // 楼栋
		jsonString.put("usersid", this.usersid); // 业主
		jsonString.put("cateid", this.cateid); // 收费类型
		jsonString.put("total", this.total); // 总计
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("status", this.status); // 状态
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Builds", this.builds); // 多对一映射类
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("Cate", this.cate); // 多对一映射类
		jsonString.put("buildsname", this.buildsname); // 映射数据
		jsonString.put("address", this.address); // 映射数据
		jsonString.put("catename", this.catename); // 映射数据
		return jsonString.toString();
	}




}




