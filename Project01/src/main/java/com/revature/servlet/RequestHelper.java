package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;

public class RequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		UserController ucon = new UserController();
		ReimbursementController rcon = new ReimbursementController();
		
		switch(req.getRequestURI()) {
		
		case "/Project01/revature/user/login":
			ucon.login(req,res);
			break;
			
		case "/Project01/revature/user/register":
			ucon.register(req,res);
			break;
			
		case "/Project01/revature/reimbursement/submit":
			rcon.submit(req,res);
			break;
			
		case "/Project01/revature/reimbursement/approve":
			rcon.update(req,res);
			break;
			
		case "/Project01/revature/reimbursement/users":
			rcon.users(req,res);
			break;
			
		case "/Project01/revature/reimbursement/all":
			rcon.all(req,res);
			break;
		
		default:
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			
		}
	}

}
