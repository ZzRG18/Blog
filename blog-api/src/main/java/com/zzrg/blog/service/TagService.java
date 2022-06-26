package com.zzrg.blog.service;

import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.TagVo;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
public interface TagService {
    /**
     * 通过文章id查询获得标签，有一张表专门映射文章id和标签id
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 查询最热标签
     * @param limit
     * @return
     */
    Result findHotTags(int limit);

    /**
     * 写文章添加标签
     * @return
     */
    Result findAll();

    /**
     * 首页标签导航
     * @return
     */
    Result findAllDetail();

    /**
     * 标签详情
     * @param id
     * @return
     */
    Result findAllDetailById(long id);
}
