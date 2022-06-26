package com.zzrg.blog.controller;

import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.utils.UserThreadLocal;
import com.zzrg.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test() {
//        SysUser
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}