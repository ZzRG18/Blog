package com.zzrg.blog.service;

import com.zzrg.blog.dao.pojo.SysUser;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.UserVo;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
public interface SysUserService {

    SysUser findUser(String account, String pwd);

    /**
     * 根据Token查询用户信息
     * @param token
     * @return
     */
    Result getUserInfoByToken(String token);

    /**
     *根据用户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 根据文章id获取用户信息
     */
    SysUser findUserById(Long userId);

    /**
     * 根据serVoId 附给serVo
     * @param userVoId
     * @return
     */
    UserVo findUserVoById(Long userVoId);
}
