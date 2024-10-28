package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.AdminDAO;
import com.boot.entity.Admin;
import com.boot.service.AdminService;

@Service("adminService") //
public class AdminServiceImpl implements AdminService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private AdminDAO adminDAO;
	@Override // 继承接口的新增管理员表数据 返回值0(失败),1(成功)
	public int insertAdmin(Admin admin) {
		return this.adminDAO.insertAdmin(admin);
	}

	@Override // 继承接口的更新管理员表数据 返回值0(失败),1(成功)
	public int updateAdmin(Admin admin) {
		return this.adminDAO.updateAdmin(admin);
	}

	@Override // 继承接口的按主键删除管理员表数据 返回值0(失败),1(成功)
	public int deleteAdmin(String adminid) {
		return this.adminDAO.deleteAdmin(adminid);
	}

	@Override // 继承接口的批量删除管理员表数据 返回值0(失败),大于0(成功)
	public int deleteAdminByIds(String[] ids) {
		return this.adminDAO.deleteAdminByIds(ids);
	}

	@Override // 继承接口的查询管理员表全部数据
	public List<Admin> getAllAdmin() {
		return this.adminDAO.getAllAdmin();
	}

	@Override // 继承接口的按条件精确查询管理员表数据
	public List<Admin> getAdminByCond(Admin admin) {
		return this.adminDAO.getAdminByCond(admin);
	}

	@Override // 继承接口的按条件模糊查询管理员表数据
	public List<Admin> getAdminByLike(Admin admin) {
		return this.adminDAO.getAdminByLike(admin);
	}

	@Override // 继承接口的按主键查询管理员表数据 返回Entity实例
	public Admin getAdminById(String adminid) {
		return this.adminDAO.getAdminById(adminid);
	}

}

