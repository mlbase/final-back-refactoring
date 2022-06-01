package com.portfolio.socialbooksotre.users.service;

import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean register(Users users){

        boolean registerCheck = true;

        String encodedPassword = passwordEncoder.encode(users.getPassword());
        Users registerUser = Users.builder().
                password(encodedPassword).
                name(users.getName()).
                email(users.getEmail()).
                address(users.getAddress()).
                build();
        usersRepository.save(registerUser);

        Optional<Users> newUser = usersRepository.findByEmail(users.getEmail());

        if(newUser.isEmpty()){
            return registerCheck;
        }

        return registerCheck;
    }

    public Users login(String id, String password){
        Optional<Users> loginUser = usersRepository.findByName(id);
        Users loginedUser = new Users();


        if(loginUser.isEmpty()){
            return loginedUser;
        }

        boolean passwordCheck = passwordEncoder.matches(password,loginUser.get().getPassword());

        if(!passwordCheck){
            return loginedUser;
        }

        loginedUser = loginUser.get();

        return loginedUser;

    }
}
