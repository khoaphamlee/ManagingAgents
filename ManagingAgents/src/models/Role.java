package models;

public class Role {
	private int Role_Id;
	private String Role_Name;
	public Role(int role_Id, String role_Name) {
		super();
		Role_Id = role_Id;
		Role_Name = role_Name;
	}
	public Role() {
		super();
	}
	@Override
	public String toString() {
		return "Role [Role_Id=" + Role_Id + ", Role_Name=" + Role_Name + "]";
	}
	public int getRole_Id() {
		return Role_Id;
	}
	public void setRole_Id(int role_Id) {
		Role_Id = role_Id;
	}
	public String getRole_Name() {
		return Role_Name;
	}
	public void setRole_Name(String role_Name) {
		Role_Name = role_Name;
	}
}
