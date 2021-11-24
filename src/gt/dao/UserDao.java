/**
 * 
 */
package gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.core.exceptions.ClosedOnExpiredPasswordException;

import gt.model.User;
import gt.util.StringUtil;

/**
 * @author Wang, Yinuo
 * User entity data access object class
 */
public class UserDao {
	
	/*
	 * User login function
	 */
	public User login(Connection con, User loginUser) throws Exception{
		
		User resultUser = null;
		String sql = "select * from user where User_id =? and Password =?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, loginUser.getUserId());
		pstate.setString(2, loginUser.getPassword());
//		do the query
		ResultSet rs = pstate.executeQuery();
//		if query result is not empty
		if(rs.next()) {
			resultUser = new User();
			resultUser.setUserId(rs.getString("User_id"));
			resultUser.setPassword(rs.getString("Password"));
			resultUser.setName(rs.getString("Name"));
			resultUser.setEmail(rs.getString("Email"));
			resultUser.setBorrowedCount(rs.getInt("Borrowed_count"));
			resultUser.setUserType(rs.getInt("User_type")); 
		}
		
		return resultUser;
		
	}
	
	/*
	 * Add new user account function
	 */
	public int addNewUser(Connection con, User newUser) throws Exception{
		
		String sql = "insert into user "
				+ "(User_id, Password, Name, User_type, Email, Borrowed_count) values(?,?,?,?,?,?)";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1,newUser.getUserId());
		pstate.setString(2, newUser.getPassword());
		pstate.setString(3,newUser.getName());
		pstate.setInt(4, newUser.getUserType());
		pstate.setString(5, newUser.getEmail());
		pstate.setInt(6, newUser.getBorrowedCount());
		
		return pstate.executeUpdate();
	}
	
	/*
	 * 
	 * update the user password
	 */
	public int changePassword(Connection con, User user, String newPwd) throws Exception{
		
		String sql = "update user set Password=? where User_id=?";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, newPwd);
		pstate.setString(2, user.getUserId());
		
		return pstate.executeUpdate();
		
	}
	
	
	public int updateInfo(Connection con, User user, String userIdStr) throws Exception{
		
		String sql = "update user set User_id=?, Email=?, Name=? where User_id=?";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(4, userIdStr);
		pstate.setString(1, user.getUserId());
		pstate.setString(2, user.getEmail());
		pstate.setString(3, user.getName());
		
		return pstate.executeUpdate();

	}
	
	public boolean searchExistedUsername(Connection con, String userIdStr) throws Exception{
		
		String sql = "select * from user where User_id=?";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, userIdStr);
		
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	
	
	
	
	
	
}
