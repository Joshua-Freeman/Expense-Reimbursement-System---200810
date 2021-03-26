package com.revature.services;



import org.apache.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.models.User;
import com.revature.models.User.UserRole;

public class UserService {
	
	Logger log = Logger.getLogger(UserService.class.getName());
	UserDao udao = new UserDao();
	
	public User login(String username, String password) throws InvalidCredentialsException{
		User user = udao.getUser(username, password);
		if(user == null) {
			log.warn("User " + username + " has tried to log in");
			throw new InvalidCredentialsException();
		}
		log.info("User " + user.getUsername() + " has logged in");
		return user; 
	}
	
	public User register(User user) throws InvalidCredentialsException {
		User check = udao.getUser(user.getUsername());
		if (check == null) {
			user.setRole(UserRole.EMPLOYEE);
			udao.addUser(user);
			log.info("User " + user.getUsername() + " has registered");
			return user;
		} else {
			throw new InvalidCredentialsException();
		}
	}

}
