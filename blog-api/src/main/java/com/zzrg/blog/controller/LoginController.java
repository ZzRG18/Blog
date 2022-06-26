package com.zzrg.blog.controller;

import com.zzrg.blog.service.LoginService;
import com.zzrg.blog.service.SysUserService;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@RestController
@RequestMapping("login")
public class LoginController {
//    @Autowired
//    private SysUserService sysUserService;
//    这个只是操作用户的而我们需要的是个业务

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录 就需要验证用户 访问用户表
        return loginService.login(loginParam);
    }
}
