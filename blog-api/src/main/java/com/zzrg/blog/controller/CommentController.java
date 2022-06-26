package com.zzrg.blog.controller;

import com.zzrg.blog.service.CommentsService;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/24
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 获取评论信息
     * @param articleId
     * @return
     */
    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);

    }

    /**
     * 写评论
     * @param commentParam
     * @return
     */
    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){

        return commentsService.comment(commentParam);
    }

}
