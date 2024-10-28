package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ParksDAO;
import com.boot.entity.Parks;
import com.boot.service.ParksService;

@Service("parksService") //
public class ParksServiceImpl implements ParksService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ParksDAO parksDAO;
	@Override // 继承接口的新增停车位表数据 返回值0(失败),1(成功)
	public int insertParks(Parks parks) {
		return this.parksDAO.insertParks(parks);
	}

	@Override // 继承接口的更新停车位表数据 返回值0(失败),1(成功)
	public int updateParks(Parks parks) {
		return this.parksDAO.updateParks(parks);
	}

	@Override // 继承接口的按主键删除停车位表数据 返回值0(失败),1(成功)
	public int deleteParks(String parksid) {
		return this.parksDAO.deleteParks(parksid);
	}

	@Override // 继承接口的批量删除停车位表数据 返回值0(失败),大于0(成功)
	public int deleteParksByIds(String[] ids) {
		return this.parksDAO.deleteParksByIds(ids);
	}

	@Override // 继承接口的查询停车位表全部数据
	public List<Parks> getAllParks() {
		return this.parksDAO.getAllParks();
	}

	@Override // 继承接口的按条件精确查询停车位表数据
	public List<Parks> getParksByCond(Parks parks) {
		return this.parksDAO.getParksByCond(parks);
	}

	@Override // 继承接口的按条件模糊查询停车位表数据
	public List<Parks> getParksByLike(Parks parks) {
		return this.parksDAO.getParksByLike(parks);
	}

	@Override // 继承接口的按主键查询停车位表数据 返回Entity实例
	public Parks getParksById(String parksid) {
		return this.parksDAO.getParksById(parksid);
	}

}

