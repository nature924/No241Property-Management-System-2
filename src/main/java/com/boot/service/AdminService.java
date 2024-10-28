package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Admin;
@Service("adminService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface AdminService {
	// 插入管理员表数据 调用adminDAO里的insertAdmin配置
	public int insertAdmin(Admin admin);

	// 更新管理员表数据 调用adminDAO里的updateAdmin配置
	public int updateAdmin(Admin admin);

	// 按主键删除管理员表数据 调用adminDAO里的deleteAdmin配置
	public int deleteAdmin(String adminid);

	// 批量删除管理员表数据 调用mapper包admin.xml里的deleteAdminByIds配置 返回值0(失败),大于0(成功)
	public int deleteAdminByIds(String[] ids);

	// 查询全部数据 调用adminDAO里的getAllAdmin配置
	public List<Admin> getAllAdmin();

	// 按照Admin类里面的字段名称精确查询 调用adminDAO里的getAdminByCond配置
	public List<Admin> getAdminByCond(Admin admin);

	// 按照Admin类里面的字段名称模糊查询 调用adminDAO里的getAdminByLike配置
	public List<Admin> getAdminByLike(Admin admin);

	// 按主键查询表返回单一的Admin实例 调用adminDAO里的getAdminById配置
	public Admin getAdminById(String adminid);

}

