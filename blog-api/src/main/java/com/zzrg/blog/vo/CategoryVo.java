package com.zzrg.blog.vo;

import lombok.Data;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@Data
public class CategoryVo {

    //id，图标路径，图标名称
    private String id;

    private String avatar;

    private String categoryName;

    private String description;

}