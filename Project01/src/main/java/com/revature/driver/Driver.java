package com.revature.driver;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class Driver {

	public static void main(String[] args) {
		UserDao udao = new UserDao();
		ReimbursementDao rdao = new ReimbursementDao();
		List<User> users = udao.getAllUsers();
		List<Reimbursement> reims = rdao.getReimbursements();
		System.out.println(reims.get(0).getAmount());

	}

}
