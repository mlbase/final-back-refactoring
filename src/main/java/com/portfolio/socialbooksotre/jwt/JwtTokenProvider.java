package com.portfolio.socialbooksotre.jwt;

import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    
    private static final String AUTHORITIES_KEY ="auth";
    private static final String BEARER_TYPE ="Bearer";
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 *1000L; //30 Minutes
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 *1000L; //7 days

    private final Key key;

    private final AuthenticationFacade authenticationFacade;

    private UsersRepository usersRepository;

    public JwtTokenProvider(@Value("${jwt.secrete}") String secreteKey, UsersRepository usersRepository, AuthenticationFacade facade) {
        byte[] KeyBytes = Decoders.BASE64.decode(secreteKey);
        this.key = Keys.hmacShaKeyFor(KeyBytes);
        this.usersRepository = usersRepository;
        this.authenticationFacade = facade;
    }

    public String TokenGenerator(Authentication authentication){

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.info("authentication 권환 {}", authorities);

        Long now = (new Date().getTime());

        Date AcessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String AcessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(AcessTokenExpiresIn)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

        return AcessToken;
    }

    public Authentication getAuthentication(String jwtToken){
        Claims claims = parseClaims(jwtToken);

        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(),"",authorities);

        return new UsernamePasswordAuthenticationToken(principal, "",authorities);
    }

    private Claims parseClaims(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
    }
}
