package models;

public class User {
	private int User_Id;
	private int IdRole;
	private String UserName;
	private String Password;
	private boolean User_Status;
	public User() {
		super();
	}
	public User(int user_Id, int idRole, String username, String password, boolean User_Status) {
		super();
		User_Id = user_Id;
		IdRole = idRole;
		UserName = username;
		Password = password;
		User_Status  = true;
	}
	
	public boolean getUser_Status() {
		return User_Status;
	}
	public void setUser_Status(boolean user_Status) {
		User_Status = user_Status;
	}
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	public int getIdRole() {
		return IdRole;
	}
	public void setIdRole(int idRole) {
		IdRole = idRole;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String username) {
		UserName = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "User [User_Id=" + User_Id + ", IdRole=" + IdRole + ", UserName=" + UserName + ", Password=" + Password
				+ "]";
	}
	
}
