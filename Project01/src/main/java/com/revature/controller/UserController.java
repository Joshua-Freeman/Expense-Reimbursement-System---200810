package com.revature.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.models.User.UserRole;

public class UserController {

	UserDao udao = new UserDao();

	public void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		HttpSession session = req.getSession();

		User user = udao.getUser(req.getParameter("username"), req.getParameter("password"));

		if (user == null) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
		} else {
			session.setAttribute("user", user);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
	}

	public void register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(req.getInputStream(), User.class);

		User check = udao.getUser(user.getId());
		if (check != null) {
			res.sendError(HttpServletResponse.SC_CONFLICT, "Username is taken");
		} else {
			user.setRole(UserRole.EMPLOYEE);
			udao.addUser(user);
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
	}

}
