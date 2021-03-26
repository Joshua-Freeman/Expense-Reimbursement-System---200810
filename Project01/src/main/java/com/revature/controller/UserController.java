package com.revature.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.services.UserService;

public class UserController {

	UserService uServ = new UserService();

	public void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		HttpSession session = req.getSession();
		try {
			User user = uServ.login(req.getParameter("username"), req.getParameter("password"));
			session.setAttribute("user", user);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
		catch(InvalidCredentialsException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
		}
	}

	public void register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(req.getInputStream(), User.class);

		try {
			uServ.register(user);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
		catch(InvalidCredentialsException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND, "Username is taken");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
	}

}
