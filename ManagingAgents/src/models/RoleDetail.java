package models;

public class RoleDetail {
	private int RoleDetail_Id;
	private int Id_Role;
	private int Id_PermissionItem;
	private String Permission;
	public RoleDetail() {
		super();
	}
	public RoleDetail(int roleDetail_Id, int id_Role, int id_PermissionItem, String permission) {
		super();
		RoleDetail_Id = roleDetail_Id;
		Id_Role = id_Role;
		Id_PermissionItem = id_PermissionItem;
		Permission = permission;
	}
	public int getRoleDetail_Id() {
		return RoleDetail_Id;
	}
	public void setRoleDetail_Id(int roleDetail_Id) {
		RoleDetail_Id = roleDetail_Id;
	}
	public int getId_Role() {
		return Id_Role;
	}
	public void setId_Role(int id_Role) {
		Id_Role = id_Role;
	}
	public int getId_PermissionItem() {
		return Id_PermissionItem;
	}
	public void setId_PermissionItem(int id_PermissionItem) {
		Id_PermissionItem = id_PermissionItem;
	}
	public String getPermission() {
		return Permission;
	}
	public void setPermission(String permission) {
		Permission = permission;
	}
	@Override
	public String toString() {
		return "RoleDetail [RoleDetail_Id=" + RoleDetail_Id + ", Id_Role=" + Id_Role + ", Id_PermissionItem="
				+ Id_PermissionItem + ", Permission=" + Permission + "]";
	}
	
}
