package com.portfolio.socialbooksotre.users.repository;

import com.portfolio.socialbooksotre.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByName(String name);

    Optional<Users> findByEmail(String email);
}
