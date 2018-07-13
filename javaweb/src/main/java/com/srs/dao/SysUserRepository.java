package com.srs.dao;

import com.srs.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    SysUser findByUsername ( String username );
}
