package com.zzrg.blog.dao.pojo;

import lombok.Data;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Data
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;
}
