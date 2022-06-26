package com.zzrg.blog.utils;

import com.zzrg.blog.dao.pojo.SysUser;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/23
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
