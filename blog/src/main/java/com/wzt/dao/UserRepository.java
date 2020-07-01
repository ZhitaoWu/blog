package com.wzt.dao;

import com.wzt.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create By coder_tao on 2020/3/29 0029
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

}
