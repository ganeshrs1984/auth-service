package com.enterprise.auth.dao;

import com.enterprise.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoService extends JpaRepository<User,String> {

    User findByEmailAndPassword(String userName, String password);
}
