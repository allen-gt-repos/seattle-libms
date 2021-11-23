/**
 * 
 */
package gt.model;

/**
 * @author Wang, Yinuo
 * User entity relation model
 */
public class User {
	
	private String userId;
	private String password;
	private String name;
	private String email;
	private int userType;
	private int borrowedCount;
	
	
	/*
	 * default constructor
	 */
	public User() {
		super();
	}
	/*
	 * parameter constructor
	 */
	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	
	public User(String userId, String password, String name, String email, int userType, int borrowedCount) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.borrowedCount = borrowedCount;
	}
	
	/*
	 * get data and set data 
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getBorrowedCount() {
		return borrowedCount;
	}
	public void setBorrowedCount(int borrowedCount) {
		this.borrowedCount = borrowedCount;
	}
	
	
	
	

}
