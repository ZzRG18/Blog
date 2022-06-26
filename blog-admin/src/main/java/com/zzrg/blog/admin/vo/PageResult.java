package com.zzrg.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}