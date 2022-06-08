package com.portfolio.socialbooksotre;

import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import com.portfolio.socialbooksotre.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;

public class DemoAuthentication {

    @Autowired
    UsersService usersService;

    @WithMockUser()
    @PreAuthorize("isAuthenticated()")
    public boolean save(Users users){
        return usersService.login(users.getName(), users.getPassword()) == null ? true:false;
    }
}
