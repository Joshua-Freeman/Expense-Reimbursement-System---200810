package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.User.UserRole;

public class ReimbursementService {
	ReimbursementDao rdao = new ReimbursementDao();
	
	public List<Reimbursement> getUserReim(User u){
		List<Reimbursement> reim = null;
		
		
		return reim;
		
	}
	
	public List<Reimbursement> getAllReim(User u) throws Exception{
		List<Reimbursement> reim = null;
		if(u.getRole() != UserRole.MANAGER) {
			throw new Exception();
		}
		reim = rdao.getReimbursements();
		return reim;
	}
	

}
