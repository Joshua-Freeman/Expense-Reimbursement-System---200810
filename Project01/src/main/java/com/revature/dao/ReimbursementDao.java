package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDao {

	private Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
	
	public Reimbursement addReimbursement(Reimbursement r) {
		try {
			String sql = "insert into "
					+ "ERS_REIMBURSEMENT(REIMB_AMOUNT, REIM_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, "
					+ "REIMB_RECEIPT, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) "
					+ "values(?,?,?,?,?,?,?,"
					+ "(select REIMB_STATUS_ID from ERS_REIMBURSEMENT_STATUS where REIMB_STATUS_ID = ?),"
					+ "(select REIMB_TYPE_ID from ERS_REIMBURSEMENT_TYPE where REIMB_TYPE_ID = ?));";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, r.getAmount());
			ps.setTimestamp(2, r.getSubmitted());
			ps.setTimestamp(3, r.getResolved());
			ps.setString(4, r.getDescription());
			ps.setBinaryStream(5, r.getReceipt());
			ps.setInt(6, r.getAuthor());
			ps.setInt(7, r.getResolver());
			ps.setString(8, r.getStatus().name());
			ps.setString(9, r.getType().name());
			ps.execute();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	public Reimbursement getReimbursement(Integer reimId) {
//		Reimbursement reim = null;
//		String sql = "SELECT * FROM accounts WHERE account_id = ?;";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, reimId);
//			ps.execute();
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				reim = new Reimbursement(
//						rs.getInt(1),rs.getDouble(2),
//						rs.getTimestamp(3),rs.getTimestamp(4),
//						rs.getString(5),rs.getBinaryStream(6),
//						rs.getInt(7),rs.getInt(8),
//						ReimbursementType.valueOf(rs.getString(10)),
//						ReimbursementStatus.valueOf(rs.getString(9)));
//			}
//			return reim;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return reim;
//	}

	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		String sql = "select * "
				+ "from ERS_REIMBURSEMENT "
				+ "inner join ERS_REIMBURSEMENT_STATUS using(REIMB_STATUS_ID) "
				+ "inner join ERS_REIMBURSEMENT_TYPE using(REIMB_TYPE_ID);";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				reims.add(new Reimbursement(
						rs.getInt(3),rs.getDouble(4),
						rs.getTimestamp(5),rs.getTimestamp(6),
						rs.getString(7),rs.getBinaryStream(8),
						rs.getInt(9),rs.getInt(10),
						ReimbursementType.valueOf(rs.getString(12)),
						ReimbursementStatus.valueOf(rs.getString(11))));
			}
			return reims;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reims;
	}
	

//	public List<Reimbursement> getAccountsByUser(User u) {
//		List<Reimbursement> accounts = new ArrayList<Reimbursement>();
//		String sql = "SELECT * FROM accounts WHERE owner_id = ?;";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, u.getId());
//			ps.execute();
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				accounts.add(new Reimbursement(
//						rs.getInt(1),rs.getInt(2),
//						rs.getDouble(3),rs.getString(4),
//						rs.getBoolean(5)));
//			}
//			return accounts;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return accounts;
//	}
	
//	public Reimbursement updateReimbursement(Reimbursement r) {
//		try {
//			String sql = "UPDATE accounts set" + 
//						" owner_id = ?," + 
//						" balance = ?," + 
//						" type = ?," + 
//						" approved = ?" + 
//						" WHERE account_id = ?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, r.getOwnerId());
//			ps.setDouble(2, r.getBalance());
//			ps.setString(3, r.getType().name());
//			ps.setBoolean(4, r.isApproved());
//			ps.setInt(5, r.getId());
//			ps.execute();
//			return r;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

//	public boolean removeReimbursement(Reimbursement a) {
//		try {
//			String sql = "DELETE FROM accounts WHERE account_id = ?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, a.getId());
//			ps.execute();
//			return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
}
