package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.User.UserRole;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	
	public Reimbursement addReimbursement(Reimbursement reim) {
		return rDao.addReimbursement(reim);
	}
	
	public Reimbursement updateReimbursement(int idReim,int idUser, String status) {
		Reimbursement reim = rDao.getReimbursementById(idReim);
		reim.setResolver(idUser);
		reim.setStatus(ReimbursementStatus.valueOf(status));
		return rDao.updateReimbursement(reim);
	}

	public List<Reimbursement> getUserReimbursement(User user) {	
		return rDao.getUserReimbursement(user);
	}

	public List<Reimbursement> getReimbursements() {	
		return rDao.getReimbursements();
	}
}
