package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Charge;

@Repository("chargeDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到ChargeServiceImpl中
public interface ChargeDAO {

	/**
	* ChargeDAO 接口 可以按名称直接调用charge.xml配置文件的SQL语句
	*/

	// 插入物业收费表数据 调用mapper包charge.xml里的insertCharge配置 返回值0(失败),1(成功)
	public int insertCharge(Charge charge);

	// 更新物业收费表数据 调用mapper包charge.xml里的updateCharge配置 返回值0(失败),1(成功)
	public int updateCharge(Charge charge);

	// 按主键删除物业收费表数据 调用mapper包charge.xml里的deleteCharge配置 返回值0(失败),1(成功)
	public int deleteCharge(String chargeid);

	// 批量删除物业收费表数据 调用mapper包charge.xml里的deleteChargeByIds配置 返回值0(失败),大于0(成功)
	public int deleteChargeByIds(String[] ids);

	// 查询物业收费表全部数据 调用mapper包charge.xml里的getAllCharge配置 返回List<Charge>类型的数据
	public List<Charge> getAllCharge();

	// 按照Charge类里面的值精确查询 调用mapper包charge.xml里的getChargeByCond配置 返回List<Charge>类型的数据
	public List<Charge> getChargeByCond(Charge charge);

	// 按照Charge类里面的值模糊查询 调用mapper包charge.xml里的getChargeByLike配置 返回List<Charge>类型的数据
	public List<Charge> getChargeByLike(Charge charge);

	// 按主键查询物业收费表返回单一的Charge实例 调用mapper包charge.xml里的getChargeById配置
	public Charge getChargeById(String chargeid);

}


