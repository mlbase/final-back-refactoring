package com.portfolio.socialbooksotre;

import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import com.portfolio.socialbooksotre.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

public class DemoAuthentication {

    @Autowired
    UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    public boolean save(Users users){
        return usersService.login(users.getName(), users.getPassword()) == null ? true:false;
    }
}
