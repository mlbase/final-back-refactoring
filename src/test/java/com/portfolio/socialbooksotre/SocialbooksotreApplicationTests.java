package com.portfolio.socialbooksotre;

import com.portfolio.socialbooksotre.users.entity.Users;
import com.portfolio.socialbooksotre.users.repository.UsersRepository;
import com.portfolio.socialbooksotre.users.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		usersRepository.save(registerUser);
		/*boolean registerCheck = usersService.register(registerUser);

		if(registerCheck){
			System.out.println(usersRepository.findByName("admin").toString());
		}

		System.out.println("회원가입 실패");*/
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
}
