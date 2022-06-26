package com.zzrg.blog.controller;

import com.zzrg.blog.service.CategoryService;
import com.zzrg.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 写文章文章分类
     * @return
     */
    @GetMapping
    public Result categories(){
        return  categoryService.findAll();
    }

    /**
     * 文章分类导航
     * @return
     */
    @GetMapping("detail")
    public Result categoriesDetail(){
        return  categoryService.findAllDetail();
    }

    /**
     * 分类详情
     * @return
     */
    @GetMapping("detail/{id}")
    public Result categoriesDetailById(@PathVariable("id") long id){
        return  categoryService.categoriesDetailById(id);
    }
}
