package com.zzrg.blog.service;


import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.params.LoginParam;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
