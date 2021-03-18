package com.revature.services;




import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.models.User;
import com.revature.models.User.UserRole;

public class UserService {
	
	UserDao udao = new UserDao();
	
	public User login(String username, String password) throws InvalidCredentialsException{
		User user = udao.getUser(username, password);
		if(user == null) {
			throw new InvalidCredentialsException();
		}
		return user; 
	}
	
	public User register(User user) throws InvalidCredentialsException {
		User check = udao.getUser(user.getUsername());
		if (check == null) {
			user.setRole(UserRole.EMPLOYEE);
			udao.addUser(user);
			return user;
		} else {
			throw new InvalidCredentialsException();
		}
	}

}
