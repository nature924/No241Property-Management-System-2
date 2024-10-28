package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Charge;
@Service("chargeService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface ChargeService {
	// 插入物业收费表数据 调用chargeDAO里的insertCharge配置
	public int insertCharge(Charge charge);

	// 更新物业收费表数据 调用chargeDAO里的updateCharge配置
	public int updateCharge(Charge charge);

	// 按主键删除物业收费表数据 调用chargeDAO里的deleteCharge配置
	public int deleteCharge(String chargeid);

	// 批量删除物业收费表数据 调用mapper包charge.xml里的deleteChargeByIds配置 返回值0(失败),大于0(成功)
	public int deleteChargeByIds(String[] ids);

	// 查询全部数据 调用chargeDAO里的getAllCharge配置
	public List<Charge> getAllCharge();

	// 按照Charge类里面的字段名称精确查询 调用chargeDAO里的getChargeByCond配置
	public List<Charge> getChargeByCond(Charge charge);

	// 按照Charge类里面的字段名称模糊查询 调用chargeDAO里的getChargeByLike配置
	public List<Charge> getChargeByLike(Charge charge);

	// 按主键查询表返回单一的Charge实例 调用chargeDAO里的getChargeById配置
	public Charge getChargeById(String chargeid);

}

