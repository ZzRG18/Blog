package com.zzrg.blog.service;

import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.CommentParam;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/24
 */
public interface CommentsService {

    /**
     * 根据文章Id查询所有的评论
     * @param articleId
     * @returnm
     */
    Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}
