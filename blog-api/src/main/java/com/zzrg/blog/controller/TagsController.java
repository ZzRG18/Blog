package com.zzrg.blog.controller;

import com.zzrg.blog.service.TagService;
import com.zzrg.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
@RestController
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public Result hot(){
        int limit = 6;
        return tagService.findHotTags(limit);
    }

    /**
     * 写文章添加标签
     * @return
     */
    @GetMapping
    public Result findAll(){
        return tagService.findAll();
    }

    /**
     * 标签当导航
     * @return
     */
    @GetMapping("detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    /**
     * 标签分类详情
     * @return
     */
    @GetMapping("detail/{id}")
    public Result findAllDetailById(@PathVariable("id") long id){
        return tagService.findAllDetailById(id);
    }
}
