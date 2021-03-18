package com.revature.services;

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;

public class ReimbursementServiceTest {

	ReimbursementService rServ = new ReimbursementService();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddReimbursement() {
		Reimbursement reim = new Reimbursement();
		reim.setAuthor(1);
		reim.setStatus(ReimbursementStatus.PENDING);
		reim.setType(ReimbursementType.OTHER);
		reim.setAmount(100);
		Reimbursement test = rServ.addReimbursement(reim);
		assertEquals(1, test.getAuthor());
	}

	@Test
	public void testUpdateReimbursement() {
		Reimbursement test = rServ.updateReimbursement(7, 1, "APPROVED");
		assertEquals("APPROVED",test.getStatus().name() );
	}

	@Test
	public void testGetUserReimbursement() {
		User user = new User();
		user.setUsername("admin");
		user.setId(1);
		user.setPassword("password");
		assertTrue((rServ.getUserReimbursement(user).size()>0));
	}

	@Test
	public void testGetReimbursements() {
		User user = new User();
		user.setUsername("admin");
		user.setId(1);
		user.setPassword("password");
		assertTrue((rServ.getUserReimbursement(user).size()>0));
	}

}
