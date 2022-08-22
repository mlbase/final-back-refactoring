package com.portfolio.socialbooksotre;

import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import com.portfolio.socialbooksotre.users.service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.portfolio.socialbooksotre.jwt.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@SpringBootTest
class SocialbooksotreApplicationTests {

	@Autowired
	UsersService usersService;
	@Autowired
	UsersRepository usersRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void register() {

		Users registerUser = Users.builder().
				password("admin").
				name("admin").
				email("admin@admin").
				address("본사주소").
				build();
		System.out.println("registerUser.toString() = " + registerUser.toString());
		boolean registerCheck = usersService.register(registerUser);

		if(registerCheck){
			System.out.println(usersRepository.findByName("admin").toString());
		}
		System.out.println("회원가입 실패");
	}

	@Test
	void login() {
		Users loginedUser = usersService.login("admin", "admin");

		if(loginedUser != null){
			System.out.println("loginedUser.toString() = " + loginedUser.toString());
		}


		System.out.println("login 실패");
	}

	@Test
	void delete() {
		Users testUser = usersRepository.findByName("admin").get();

		if(testUser != null){
			usersRepository.delete(testUser);
		}
	}

	@Test
	@WithMockUser
	void TokenGenerationTest(){

		AuthenticationFacade authenticationFacade = new AuthenticationFacade();
		Authentication authentication = authenticationFacade.getAuthentication();

		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Long now = (new Date().getTime());

		Date AcessTokenExpiresIn = new Date(now + 1800000L);

		byte[] KeyBytes = Decoders.BASE64.decode("and0dG9rZW7rp4zrk6TquLDrhIjrrLTtnpjrk6Tri6Tsp4Tsp5zsnbTqsbDrp4zrqofri6zs");
		Key key = Keys.hmacShaKeyFor(KeyBytes);

		String acessToken = Jwts.builder().setSubject(authentication.getName())
				.claim("auth",authorities)
				.setExpiration(AcessTokenExpiresIn)
				.signWith(key).compact();

		System.out.println("acessToken = " + acessToken);
	}
}
