/**
 * 
 */
package gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import gt.model.BookLoc;
import gt.model.BookLog;
import gt.model.User;

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
	
	/*
	 * get the reader account info
	 */
	public User getReaderInfo(Connection con, User user) throws Exception{
		
		User resultUser = null;
		String sql = "select * from user where User_id =?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, user.getUserId());
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
	 * update the user account info
	 */
	public int updateInfo(Connection con, User user, String userIdStr) throws Exception{
		
		String sql = "update user set User_id=?, Email=?, Name=? where User_id=?";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(4, userIdStr);
		pstate.setString(1, user.getUserId());
		pstate.setString(2, user.getEmail());
		pstate.setString(3, user.getName());
		
		return pstate.executeUpdate();

	}
	
	/*
	 * check the updated username has been used or not
	 */
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
	
	/*
	 * update user info when borrow or return book
	 */
	public int borrowReturnBook(Connection con, User user) throws Exception{
		
		String sql ="update user set Borrowed_count=? where User_id=?";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, user.getBorrowedCount());
		pstate.setString(2, user.getUserId());
		
		return pstate.executeUpdate();
	}
	
	/*
	 * add new borrow log when borrow the book
	 */
	public int addNewBorrowLog(Connection con, User user, BookLoc bookLoc) throws Exception{
		
		String sql="insert into borrow_return values(?,?,?,?,null)";
		
		PreparedStatement pstate = con.prepareStatement(sql);
	
//		set log id
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp now= new Timestamp(System.currentTimeMillis());//get system time
		long logId = Long.valueOf(df.format(now));

//		set borrow date
		java.sql.Date borrowDate = new java.sql.Date(System.currentTimeMillis());
		
		pstate.setLong(1, logId);
		pstate.setInt(2, bookLoc.getBookId());
		pstate.setString(3, user.getUserId());
		pstate.setDate(4, borrowDate);
		
		return pstate.executeUpdate();
	}
	
	/*
	 * update the borrow log when return the book
	 */
	public int updateBorrowLog(Connection con, User user, BookLog bookLog) throws Exception{
		
		String sql = "update borrow_return set Return_date=? where Book_id=? and User_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setDate(1, bookLog.getReturnDate());
		pstate.setInt(2, bookLog.getBookId());
		pstate.setString(3, user.getUserId());
		
		return pstate.executeUpdate();
	}
	
	
	
}
