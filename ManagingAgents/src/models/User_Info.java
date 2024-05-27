package models;

import java.sql.Date;

public class User_Info {
	private int UserInfo_Id;
	private int IdUser;
	private String UserInfo_Name;
	private String UserInfo_Address;
	private Date UserInfo_BirthDate;
	private String UserInfo_Email;
	private String UserInfo_Phone;

	public User_Info() {
		super();
	}
	
	public User_Info(int userInfo_Id, int idUser, String userInfo_Name, String userInfo_Address,
			Date userInfo_BirthDate, String userInfo_Email, String userInfo_Phone) {
		super();
		UserInfo_Id = userInfo_Id;
		IdUser = idUser;
		UserInfo_Name = userInfo_Name;
		UserInfo_Address = userInfo_Address;
		UserInfo_BirthDate = userInfo_BirthDate;
		UserInfo_Email = userInfo_Email;
		UserInfo_Phone = userInfo_Phone;
	}

	public int getUserInfo_Id() {
		return UserInfo_Id;
	}
	public void setUserInfo_Id(int userInfo_Id) {
		UserInfo_Id = userInfo_Id;
	}
	public int getIdUser() {
		return IdUser;
	}
	public void setIdUser(int idUser) {
		IdUser = idUser;
	}
	public String getUserInfo_Name() {
		return UserInfo_Name;
	}
	public void setUserInfo_Name(String userInfo_Name) {
		UserInfo_Name = userInfo_Name;
	}
	public String getUserInfo_Address() {
		return UserInfo_Address;
	}
	public void setUserInfo_Address(String userInfo_Address) {
		UserInfo_Address = userInfo_Address;
	}
	public Date getUserInfo_BirthDate() {
		return UserInfo_BirthDate;
	}
	public void setUserInfo_BirthDate(Date userInfo_BirthDate) {
		UserInfo_BirthDate = userInfo_BirthDate;
	}

	public String getUserInfo_Email() {
		return UserInfo_Email;
	}

	public void setUserInfo_Email(String userInfo_Email) {
		UserInfo_Email = userInfo_Email;
	}
	
	public String getUserInfo_Phone() {
		return UserInfo_Phone;
	}

	public void setUserInfo_Phone(String userInfo_Phone) {
		UserInfo_Phone = userInfo_Phone;
	}

	@Override
	public String toString() {
		return "User_Info [UserInfo_Id=" + UserInfo_Id + ", IdUser=" + IdUser + ", UserInfo_Name=" + UserInfo_Name
				+ ", UserInfo_Address=" + UserInfo_Address + ", UserInfo_BirthDate=" + UserInfo_BirthDate
				+ ", UserInfo_Email=" + UserInfo_Email + "]";
	}
	
	
}
