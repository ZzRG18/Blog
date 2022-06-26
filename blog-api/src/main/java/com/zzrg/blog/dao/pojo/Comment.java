package com.zzrg.blog.dao.pojo;

import lombok.Data;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/24
 */
@Data
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
