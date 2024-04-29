package com.enterprise.auth.service;

import com.enterprise.auth.dao.UserDaoService;
import com.enterprise.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserDaoService userDaoService;

    public void register(User user){
        userDaoService.save(user);
    }
}
