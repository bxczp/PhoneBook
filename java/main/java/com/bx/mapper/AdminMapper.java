package com.bx.mapper;

import com.bx.entity.Admin;

public interface AdminMapper {

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);

}
