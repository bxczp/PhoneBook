package com.bx.service;


import com.bx.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    public Admin login(Admin admin);
}
