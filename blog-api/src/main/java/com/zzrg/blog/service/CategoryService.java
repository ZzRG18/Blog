package com.zzrg.blog.service;


import com.zzrg.blog.vo.CategoryVo;
import com.zzrg.blog.vo.Result;


/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);

    /**
     * 写文章添加分类
     * @return
     */
    Result findAll();

    /**
     * 导航文章分类
     * @return
     */
    Result findAllDetail();

    /**
     * 分类详情
     * @param id
     * @return
     */
    Result categoriesDetailById(long id);
}
