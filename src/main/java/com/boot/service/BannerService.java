package com.boot.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Banner;
@Service("bannerService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface BannerService {
	// 插入网站栏目表数据 调用bannerDAO里的insertBanner配置
	public int insertBanner(Banner banner);

	// 更新网站栏目表数据 调用bannerDAO里的updateBanner配置
	public int updateBanner(Banner banner);

	// 按主键删除网站栏目表数据 调用bannerDAO里的deleteBanner配置
	public int deleteBanner(String bannerid);

	// 批量删除网站栏目表数据 调用mapper包banner.xml里的deleteBannerByIds配置 返回值0(失败),大于0(成功)
	public int deleteBannerByIds(String[] ids);

	// 查询全部数据 调用bannerDAO里的getAllBanner配置
	public List<Banner> getAllBanner();

	// 按照Banner类里面的字段名称精确查询 调用bannerDAO里的getBannerByCond配置
	public List<Banner> getBannerByCond(Banner banner);

	// 按照Banner类里面的字段名称模糊查询 调用bannerDAO里的getBannerByLike配置
	public List<Banner> getBannerByLike(Banner banner);

	// 按主键查询表返回单一的Banner实例 调用bannerDAO里的getBannerById配置
	public Banner getBannerById(String bannerid);

}

