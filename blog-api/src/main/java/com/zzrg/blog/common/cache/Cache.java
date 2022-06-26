package com.zzrg.blog.common.cache;

import java.lang.annotation.*;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1 * 60 * 1000;

    //缓存标识
    String name() default "";

}
