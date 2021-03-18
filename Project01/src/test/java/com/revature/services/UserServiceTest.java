package com.revature.services;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.models.User.UserRole;

public class UserServiceTest {
	UserService uServ = new UserService();
	UserDao uDao = new UserDao();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		

	}

	@Test
	public void testLogin() {
		
		User user = uServ.login("admin", "password");
		assertEquals("admin", user.getUsername());
		assertEquals("password", user.getPassword());
	}

	@Test
	public void testRegister() {

		User user = new User();
		user.setUsername("test10");
		user.setPassword("password");
		user.setRole(UserRole.EMPLOYEE);
		User test = uServ.register(user);
		assertEquals("test10", test.getUsername());
		assertTrue(uDao.removeUser(user));
	}

}
