package com.wzt.service;

import com.wzt.po.User;

/**
 * Create By coder_tao on 2020/3/29 0029
 */
public interface UserService {
    User checkUser(String username, String password);
}
