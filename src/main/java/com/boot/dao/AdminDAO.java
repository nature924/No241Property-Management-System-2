package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Admin;

@Repository("adminDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到AdminServiceImpl中
public interface AdminDAO {

	/**
	* AdminDAO 接口 可以按名称直接调用admin.xml配置文件的SQL语句
	*/

	// 插入管理员表数据 调用mapper包admin.xml里的insertAdmin配置 返回值0(失败),1(成功)
	public int insertAdmin(Admin admin);

	// 更新管理员表数据 调用mapper包admin.xml里的updateAdmin配置 返回值0(失败),1(成功)
	public int updateAdmin(Admin admin);

	// 按主键删除管理员表数据 调用mapper包admin.xml里的deleteAdmin配置 返回值0(失败),1(成功)
	public int deleteAdmin(String adminid);

	// 批量删除管理员表数据 调用mapper包admin.xml里的deleteAdminByIds配置 返回值0(失败),大于0(成功)
	public int deleteAdminByIds(String[] ids);

	// 查询管理员表全部数据 调用mapper包admin.xml里的getAllAdmin配置 返回List<Admin>类型的数据
	public List<Admin> getAllAdmin();

	// 按照Admin类里面的值精确查询 调用mapper包admin.xml里的getAdminByCond配置 返回List<Admin>类型的数据
	public List<Admin> getAdminByCond(Admin admin);

	// 按照Admin类里面的值模糊查询 调用mapper包admin.xml里的getAdminByLike配置 返回List<Admin>类型的数据
	public List<Admin> getAdminByLike(Admin admin);

	// 按主键查询管理员表返回单一的Admin实例 调用mapper包admin.xml里的getAdminById配置
	public Admin getAdminById(String adminid);

}


