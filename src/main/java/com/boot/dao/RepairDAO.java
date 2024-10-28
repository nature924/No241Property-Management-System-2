package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Repair;

@Repository("repairDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到RepairServiceImpl中
public interface RepairDAO {

	/**
	* RepairDAO 接口 可以按名称直接调用repair.xml配置文件的SQL语句
	*/

	// 插入业主报修表数据 调用mapper包repair.xml里的insertRepair配置 返回值0(失败),1(成功)
	public int insertRepair(Repair repair);

	// 更新业主报修表数据 调用mapper包repair.xml里的updateRepair配置 返回值0(失败),1(成功)
	public int updateRepair(Repair repair);

	// 按主键删除业主报修表数据 调用mapper包repair.xml里的deleteRepair配置 返回值0(失败),1(成功)
	public int deleteRepair(String repairid);

	// 批量删除业主报修表数据 调用mapper包repair.xml里的deleteRepairByIds配置 返回值0(失败),大于0(成功)
	public int deleteRepairByIds(String[] ids);

	// 查询业主报修表全部数据 调用mapper包repair.xml里的getAllRepair配置 返回List<Repair>类型的数据
	public List<Repair> getAllRepair();

	// 按照Repair类里面的值精确查询 调用mapper包repair.xml里的getRepairByCond配置 返回List<Repair>类型的数据
	public List<Repair> getRepairByCond(Repair repair);

	// 按照Repair类里面的值模糊查询 调用mapper包repair.xml里的getRepairByLike配置 返回List<Repair>类型的数据
	public List<Repair> getRepairByLike(Repair repair);

	// 按主键查询业主报修表返回单一的Repair实例 调用mapper包repair.xml里的getRepairById配置
	public Repair getRepairById(String repairid);

}


