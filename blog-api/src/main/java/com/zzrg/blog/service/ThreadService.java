package com.zzrg.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zzrg.blog.dao.mapper.ArticleMapper;
import com.zzrg.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/24
 */
@Component
public class ThreadService {
    //期望操作在线程池中 执行 不会影响原来的主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {

        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts +1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        //为了多线程环境下的 线程安全
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        //update article set view_count = 100 where view_count 99 and id=11
        articleMapper.update(articleUpdate,updateWrapper);


    }
}
