package com.zzrg.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Data
public class Permission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String path;

    private String description;

}
