package com.srs.service.auth;

import com.srs.po.SysUser;

public interface AuthService {

    /**
     * 页面初始化的数据
     *
     * @param sysUser 当前用户
     *
     * @return json化后的数据
     */
    String init ( SysUser sysUser );

}
