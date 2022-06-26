package com.zzrg.blog.admin.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/26
 */
public class MySimpleGrantedAuthority implements GrantedAuthority {
    private String authority;
    private String path;

    public MySimpleGrantedAuthority() {
    }

    public MySimpleGrantedAuthority(String authority) {
        this.authority = authority;
    }

    public MySimpleGrantedAuthority(String authority, String path) {
        this.authority = authority;
        this.path = path;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }
}
