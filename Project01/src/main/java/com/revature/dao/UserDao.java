package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserDao {

	private Connection conn = ConnectionUtil.getConnectionUtil().getConnection();

	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		String sql = "select * from ERS_USERS inner join ERS_USER_ROLES on"+
				" (ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID);";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				users.add(new User(
						rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),
						UserRole.valueOf(rs.getString(9))));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User getUser(Integer userId) {
		try {
			String sql = "select * from ERS_USERS inner join ERS_USER_ROLES on "+
					"(ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID) where USER_ROLE_ID = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			User user = null;
			while(rs.next()) {
				user = (new User(
						rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),
						UserRole.valueOf(rs.getString(9))));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUser(String username, String pass) {
		try {
			String sql = "select * from ERS_USERS inner join ERS_USER_ROLES on "+
					"(ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID)" +
					" where ERS_USERNAME = ? and ERS_PASSWORD = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			User user = null;
			while(rs.next()) {
				user = (new User(
						rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),
						UserRole.valueOf(rs.getString(9))));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public User addUser(User user) {
		try {
			String sql = "insert into ERS_USERS(ERS_USERNAME,ERS_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_ROLE_ID) "
					+ "values (?,?,?,?,?,(select ERS_USER_ROLE_ID from ERS_USER_ROLES where USER_ROLE = ?));";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getRole().name());
			ps.execute();
			return user;
		} catch (SQLException e) {

		}
		return null;
	}

	public User updateUser(User u) {
		try {
			String sql = "update ERS_USERS set "
					+ "ERS_PASSWORD = ?, USER_FIRST_NAME = ?, "
					+ "USER_LAST_NAME = ?, USER_EMAIL = ? "
					+ "where ERS_USERNAME = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getUsername());
			ps.execute();
			return u;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean removeUser(User u) {
		try {
			String sql = "DELETE FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
