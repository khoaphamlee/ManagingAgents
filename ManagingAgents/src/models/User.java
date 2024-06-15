package models;

import java.sql.Date;

public class User {
    private int User_Id;
    
    private String UserName;
    private String Password;
    private String Email;
    private String Name;
    private String Address;
    private Date BirthDate;
    private String SDT;
    private String Gender;
    private boolean Status;

    public User() {
        super();
    }

    public User(int user_Id, String userName, String password, String email, String name, String address,
                Date birthDate, String sdt, String gender, boolean status) {
        super();
        User_Id = user_Id;
        
        UserName = userName;
        Password = password;
        Email = email;
        Name = name;
        Address = address;
        BirthDate = birthDate;
        SDT = sdt;
        Gender = gender;
        Status = status;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String sdt) {
        SDT = sdt;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    
}
