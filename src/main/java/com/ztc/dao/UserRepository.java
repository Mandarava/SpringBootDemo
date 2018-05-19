package com.ztc.dao;

import com.ztc.entity.AccountCredentials;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zt 2018/5/19 17:22
 */
public interface UserRepository extends JpaRepository<AccountCredentials, Long> {

    AccountCredentials findByUsername(String username);

}
