package com.zzrg.blog.controller;

import com.zzrg.blog.service.LoginService;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;
    //sso 单点登录，后期如果把登录注册功能 提出去（单独的服务，可以独立提供接口服务）
    @PostMapping
    public Result register(@RequestBody LoginParam loginParam){
        //sso 单点登录，后期如果把登录注册功能 提出去（单独的服务，可以独立提供接口服务）
        return loginService.register(loginParam);
    }
}
