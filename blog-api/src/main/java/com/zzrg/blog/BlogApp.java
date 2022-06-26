package com.zzrg.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
@SpringBootApplication
@MapperScan("com.zzrg.blog.dao")
public class BlogApp {

    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class,args);
    }
}
