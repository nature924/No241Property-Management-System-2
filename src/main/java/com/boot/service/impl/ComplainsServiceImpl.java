package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ComplainsDAO;
import com.boot.entity.Complains;
import com.boot.service.ComplainsService;

@Service("complainsService") //
public class ComplainsServiceImpl implements ComplainsService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ComplainsDAO complainsDAO;
	@Override // 继承接口的新增意见反馈表数据 返回值0(失败),1(成功)
	public int insertComplains(Complains complains) {
		return this.complainsDAO.insertComplains(complains);
	}

	@Override // 继承接口的更新意见反馈表数据 返回值0(失败),1(成功)
	public int updateComplains(Complains complains) {
		return this.complainsDAO.updateComplains(complains);
	}

	@Override // 继承接口的按主键删除意见反馈表数据 返回值0(失败),1(成功)
	public int deleteComplains(String complainsid) {
		return this.complainsDAO.deleteComplains(complainsid);
	}

	@Override // 继承接口的批量删除意见反馈表数据 返回值0(失败),大于0(成功)
	public int deleteComplainsByIds(String[] ids) {
		return this.complainsDAO.deleteComplainsByIds(ids);
	}

	@Override // 继承接口的查询意见反馈表全部数据
	public List<Complains> getAllComplains() {
		return this.complainsDAO.getAllComplains();
	}

	@Override // 继承接口的按条件精确查询意见反馈表数据
	public List<Complains> getComplainsByCond(Complains complains) {
		return this.complainsDAO.getComplainsByCond(complains);
	}

	@Override // 继承接口的按条件模糊查询意见反馈表数据
	public List<Complains> getComplainsByLike(Complains complains) {
		return this.complainsDAO.getComplainsByLike(complains);
	}

	@Override // 继承接口的按主键查询意见反馈表数据 返回Entity实例
	public Complains getComplainsById(String complainsid) {
		return this.complainsDAO.getComplainsById(complainsid);
	}

}

