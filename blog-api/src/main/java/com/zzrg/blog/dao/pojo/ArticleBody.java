package com.zzrg.blog.dao.pojo;

import lombok.Data;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@Data
public class ArticleBody {

    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
