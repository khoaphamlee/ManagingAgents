package models;

public class User {
	private int User_Id;
	private int IdRole;
	private String UserName;
	private String Password;
	public User() {
		super();
	}
	public User(int user_Id, int idRole, String username, String password) {
		super();
		User_Id = user_Id;
		IdRole = idRole;
		UserName = username;
		Password = password;
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
