package com.zzrg.blog.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/25
 */

// Type 代表可以放在类上面 Method 代表放在方法上面
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";


}
