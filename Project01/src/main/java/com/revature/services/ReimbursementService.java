package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.Reimbursement.ReimbursementStatus;


public class ReimbursementService {
	
	Logger log = Logger.getLogger(ReimbursementService.class.getName());
	ReimbursementDao rDao = new ReimbursementDao();
	
	public Reimbursement addReimbursement(Reimbursement reim) {
		log.info("User " + reim.getAuthor() + " has added a reimbursement");
		return rDao.addReimbursement(reim);
	}
	
	public Reimbursement updateReimbursement(int idReim,int idUser, String status) {
		Reimbursement reim = rDao.getReimbursementById(idReim);
		reim.setResolver(idUser);
		reim.setStatus(ReimbursementStatus.valueOf(status));
		log.info("Manager  " + idReim + " has updated a reimbursement");
		return rDao.updateReimbursement(reim);
	}

	public List<Reimbursement> getUserReimbursement(User user) {	
		log.info("User  " + user.getId() + " has pulled their reimbursement");
		return rDao.getUserReimbursement(user);
	}

	public List<Reimbursement> getReimbursements() {	
		log.info("Manager has pulled reimbursement");
		return rDao.getReimbursements();
	}
}
