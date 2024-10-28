package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ChargeDAO;
import com.boot.entity.Charge;
import com.boot.service.ChargeService;

@Service("chargeService") //
public class ChargeServiceImpl implements ChargeService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ChargeDAO chargeDAO;
	@Override // 继承接口的新增物业收费表数据 返回值0(失败),1(成功)
	public int insertCharge(Charge charge) {
		return this.chargeDAO.insertCharge(charge);
	}

	@Override // 继承接口的更新物业收费表数据 返回值0(失败),1(成功)
	public int updateCharge(Charge charge) {
		return this.chargeDAO.updateCharge(charge);
	}

	@Override // 继承接口的按主键删除物业收费表数据 返回值0(失败),1(成功)
	public int deleteCharge(String chargeid) {
		return this.chargeDAO.deleteCharge(chargeid);
	}

	@Override // 继承接口的批量删除物业收费表数据 返回值0(失败),大于0(成功)
	public int deleteChargeByIds(String[] ids) {
		return this.chargeDAO.deleteChargeByIds(ids);
	}

	@Override // 继承接口的查询物业收费表全部数据
	public List<Charge> getAllCharge() {
		return this.chargeDAO.getAllCharge();
	}

	@Override // 继承接口的按条件精确查询物业收费表数据
	public List<Charge> getChargeByCond(Charge charge) {
		return this.chargeDAO.getChargeByCond(charge);
	}

	@Override // 继承接口的按条件模糊查询物业收费表数据
	public List<Charge> getChargeByLike(Charge charge) {
		return this.chargeDAO.getChargeByLike(charge);
	}

	@Override // 继承接口的按主键查询物业收费表数据 返回Entity实例
	public Charge getChargeById(String chargeid) {
		return this.chargeDAO.getChargeById(chargeid);
	}

}

