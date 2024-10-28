package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.boot.entity.Banner;

@Repository("bannerDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到BannerServiceImpl中
public interface BannerDAO {

	/**
	 * BannerDAO 接口 可以按名称直接调用banner.xml配置文件的SQL语句
	 */

	// 插入网站栏目表数据 调用mapper包banner.xml里的insertBanner配置 返回值0(失败),1(成功)
	public int insertBanner(Banner banner);

	// 更新网站栏目表数据 调用mapper包banner.xml里的updateBanner配置 返回值0(失败),1(成功)
	public int updateBanner(Banner banner);

	// 按主键删除网站栏目表数据 调用mapper包banner.xml里的deleteBanner配置 返回值0(失败),1(成功)
	public int deleteBanner(String bannerid);

	// 批量删除网站栏目表数据 调用mapper包banner.xml里的deleteBannerByIds配置 返回值0(失败),大于0(成功)
	public int deleteBannerByIds(String[] ids);

	// 查询网站栏目表全部数据 调用mapper包banner.xml里的getAllBanner配置 返回List<Banner>类型的数据
	public List<Banner> getAllBanner();

	// 按照Banner类里面的值精确查询 调用mapper包banner.xml里的getBannerByCond配置 返回List<Banner>类型的数据
	public List<Banner> getBannerByCond(Banner banner);

	// 按照Banner类里面的值模糊查询 调用mapper包banner.xml里的getBannerByLike配置 返回List<Banner>类型的数据
	public List<Banner> getBannerByLike(Banner banner);

	// 按主键查询网站栏目表返回单一的Banner实例 调用mapper包banner.xml里的getBannerById配置
	public Banner getBannerById(String bannerid);

}
