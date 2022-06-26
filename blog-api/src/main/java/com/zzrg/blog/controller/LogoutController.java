package com.zzrg.blog.controller;

import com.zzrg.blog.service.LoginService;
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
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public Result currentUser(@RequestHeader("Authorization") String token){

        return loginService.logout(token);
    }
}
