package com.zzrg.blog.service;

import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.ArticleParam;
import com.zzrg.blog.vo.params.PageParams;


/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
public interface ArticleService {

    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);
    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 文章发布服务
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);
}


