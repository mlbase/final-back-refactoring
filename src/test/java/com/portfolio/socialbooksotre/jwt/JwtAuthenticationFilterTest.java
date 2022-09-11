package com.portfolio.socialbooksotre.jwt;

import com.portfolio.socialbooksotre.users.dto.response.UserResponseDto;
import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.service.UsersService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JwtAuthenticationFilterTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UsersService usersService;

    @Before
    void setup() {
        Users users = Users.builder().name("test")
                .email("test@test.com")
                .password("1234")
                .address("test address")
                .build();

        usersService.register(users);

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new TestingAuthenticationToken(users.getName(), users.getPassword(), "ROLE_USER"));
        SecurityContextHolder.setContext(securityContext);

    }


    @Test
    void getAuthenticationTest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
    }
}