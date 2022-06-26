package com.zzrg.blog.controller;

import com.zzrg.blog.service.SysUserService;
import com.zzrg.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.getUserInfoByToken(token);
    }

}
