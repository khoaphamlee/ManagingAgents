package controllers;

import java.sql.Date;

public class CurrentUser {
	public static int userId;
	
	public static String userName;
	public static String userPassword;
	public static String userEmail;
	public static String userAddress;
	public static Date userBirth;
	public static String userSDT;
	public static String userGender;
	

	public static void Reset() {
		
		userName = userPassword = userEmail = userAddress = userGender = userSDT = null;
		userBirth = null;
	}
}
