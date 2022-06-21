package com.portfolio.socialbooksotre.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.socialbooksotre.users.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;

        setFilterProcessesUrl("api/users/");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("USERNAMEPASSWORD_FLITER");
        ObjectMapper om = new ObjectMapper();
        Optional<Users> users = null;
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
        return super.attemptAuthentication(request, response);
    }
}
