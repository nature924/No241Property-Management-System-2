package com.boot.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.BannerDAO;
import com.boot.entity.Banner;
import com.boot.service.BannerService;

@Service("bannerService") //
public class BannerServiceImpl implements BannerService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private BannerDAO bannerDAO;
	@Override // 继承接口的新增网站栏目表数据 返回值0(失败),1(成功)
	public int insertBanner(Banner banner) {
		return this.bannerDAO.insertBanner(banner);
	}

	@Override // 继承接口的更新网站栏目表数据 返回值0(失败),1(成功)
	public int updateBanner(Banner banner) {
		return this.bannerDAO.updateBanner(banner);
	}

	@Override // 继承接口的按主键删除网站栏目表数据 返回值0(失败),1(成功)
	public int deleteBanner(String bannerid) {
		return this.bannerDAO.deleteBanner(bannerid);
	}

	@Override // 继承接口的批量删除网站栏目表数据 返回值0(失败),大于0(成功)
	public int deleteBannerByIds(String[] ids) {
		return this.bannerDAO.deleteBannerByIds(ids);
	}

	@Override // 继承接口的查询网站栏目表全部数据
	public List<Banner> getAllBanner() {
		return this.bannerDAO.getAllBanner();
	}

	@Override // 继承接口的按条件精确查询网站栏目表数据
	public List<Banner> getBannerByCond(Banner banner) {
		return this.bannerDAO.getBannerByCond(banner);
	}

	@Override // 继承接口的按条件模糊查询网站栏目表数据
	public List<Banner> getBannerByLike(Banner banner) {
		return this.bannerDAO.getBannerByLike(banner);
	}

	@Override // 继承接口的按主键查询网站栏目表数据 返回Entity实例
	public Banner getBannerById(String bannerid) {
		return this.bannerDAO.getBannerById(bannerid);
	}

}

