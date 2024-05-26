package models;

public class Permission_Item {
	private int PermissionItem_Id;
	private String PermissionItem_Name;
	public Permission_Item() {
		super();
	}
	public Permission_Item(int permission_Item_Id, String permission_Item_Name) {
		super();
		PermissionItem_Id = permission_Item_Id;
		PermissionItem_Name = permission_Item_Name;
	}
	public int getPermissionItem_Id() {
		return PermissionItem_Id;
	}
	public void setPermissionItem_Id(int permission_Item_Id) {
		PermissionItem_Id = permission_Item_Id;
	}
	public String getPermissionItem_Name() {
		return PermissionItem_Name;
	}
	public void setPermissionItem_Name(String permission_Item_Name) {
		PermissionItem_Name = permission_Item_Name;
	}
	@Override
	public String toString() {
		return "Permission_Item [PermissionItem_Id=" + PermissionItem_Id + ", PermissionItem_Name="
				+ PermissionItem_Name + "]";
	}
	
}
