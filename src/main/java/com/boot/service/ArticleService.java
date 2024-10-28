package com.boot.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.boot.entity.Article;

@Service("articleService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface ArticleService {
	// 插入网站内容表数据 调用articleDAO里的insertArticle配置
	public int insertArticle(Article article);

	// 更新网站内容表数据 调用articleDAO里的updateArticle配置
	public int updateArticle(Article article);

	// 按主键删除网站内容表数据 调用articleDAO里的deleteArticle配置
	public int deleteArticle(String articleid);

	// 批量删除网站内容表数据 调用mapper包article.xml里的deleteArticleByIds配置 返回值0(失败),大于0(成功)
	public int deleteArticleByIds(String[] ids);

	// 查询全部数据 调用articleDAO里的getAllArticle配置
	public List<Article> getAllArticle();

	public List<Article> getFlvArticle();

	public List<Article> getTopArticle();

	public List<Article> getArticleByBanner(String bannerid);

	// 按照Article类里面的字段名称精确查询 调用articleDAO里的getArticleByCond配置
	public List<Article> getArticleByCond(Article article);

	// 按照Article类里面的字段名称模糊查询 调用articleDAO里的getArticleByLike配置
	public List<Article> getArticleByLike(Article article);

	// 按主键查询表返回单一的Article实例 调用articleDAO里的getArticleById配置
	public Article getArticleById(String articleid);

}
