package com.zzrg.blog.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzrg.blog.dao.dos.Archives;
import com.zzrg.blog.dao.pojo.Article;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
public interface ArticleMapper extends BaseMapper<Article> {


    List<Archives> listArchives();


    /**
     * 根据流量数量查询排名前limit的文章
     * @param limit 排名
     * @return 符合条件的文章集合
     */
//    List<Article> findHotArticleByViewCounts(int limit);

    /**
     * 根据创建时间查询前limit的文章
     * @param limit 文章个数
     * @return 符合条件的文章信息集合
     */
//    List<Article> findNewArticlesByCreateDate(int limit);

    /**
     * 文章归档
     * @return 归档信息
     */
//    List<ArticleArchives> listArticleArchives();

    /**
     * 根据类别id，标签id，年，月查询文章信息
     * @param page
     * @param categoryId
     * @param tagId
     * @param year
     * @param month
     * @return
//     */
    IPage<Article> listArticle(Page<Article> page,
                               Long categoryId,
                               Long tagId,
                               String year,
                               String month);

}
