package com.zzrg.blog.controller;



import com.zzrg.blog.common.aop.LogAnnotation;
import com.zzrg.blog.common.cache.Cache;
import com.zzrg.blog.service.ArticleService;
import com.zzrg.blog.vo.ArticleVo;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.ArticleParam;
import com.zzrg.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */

//Json数据进行交互
@RestController
@RequestMapping("/articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     * 首页文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    //AOP加上此注解 代表要对接此接口日志
    @LogAnnotation(module = "文章",operator = "获取文章列表")
    @Cache(expire = 50 * 60 * 1000,name="listArticle")
    public Result listArticle(@RequestBody PageParams pageParams){
        //Result是统一结果返回
        //ArticleVo 页面接收的数据
        return articleService.listArticle(pageParams);
    }
    /**
     * 首页 最热文章
     * @return
     */
    @PostMapping("/hot")
    @Cache(expire = 50 * 60 * 1000,name="hos_article")
    public Result hotArticle(){
        int limit = 3;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return
     */
    @PostMapping("/new")
    @Cache(expire = 50 * 60 * 1000,name="new_article")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }
    /**
     * 文章归档
     * @return
     */
    @PostMapping("/listArchives")
    @Cache(expire = 50 * 60 * 1000,name="articleArchives")
    public Result articleArchives(){
        return articleService.listArchives();
    }

    /**
     * 文章评论
     * @param id
     * @return
     */
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id){
        return articleService.findArticleById(id);
    }

    /**
     * 文章发布
     * @param articleParam
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }


}
