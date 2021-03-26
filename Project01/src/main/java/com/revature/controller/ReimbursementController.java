package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.services.ReimbursementService;

public class ReimbursementController {

	ReimbursementService rServ = new ReimbursementService();
	
	public void submit(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reim = new Reimbursement();
		reim.setAmount(Double.valueOf(req.getParameter("amount")));
		reim.setType(ReimbursementType.valueOf(req.getParameter("type")));
		reim.setDescription(req.getParameter("description"));
		reim.setAuthor(user.getId());
		reim.setStatus(ReimbursementStatus.PENDING);
		rServ.addReimbursement(reim);
		res.getWriter().write(new ObjectMapper().writeValueAsString(reim));
	}

	public void update(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		Reimbursement reim = mapper.readValue(req.getInputStream(), Reimbursement.class);
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		Reimbursement reim = rServ.updateReimbursement(Integer.valueOf(req.getParameter("id")),
				user.getId(), req.getParameter("status"));
		res.getWriter().write(new ObjectMapper().writeValueAsString(reim));
		
	}

	public void users(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		List<Reimbursement> reims = rServ.getUserReimbursement(user);
		res.getWriter().write(new ObjectMapper().writeValueAsString(reims));	
	}

	public void all(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(!user.getRole().name().equals(UserRole.MANAGER.name())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "You need to be a manager.");
		}
		else {
			List<Reimbursement> reims = rServ.getReimbursements();
			res.getWriter().write(new ObjectMapper().writeValueAsString(reims));
		}
	}

}
