package com.zzrg.blog.vo.params;

import com.zzrg.blog.vo.CategoryVo;
import com.zzrg.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Data
public class ArticleParam {
    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
