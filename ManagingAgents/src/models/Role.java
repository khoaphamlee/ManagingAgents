package models;

public class Role {
    private int roleId;
    private boolean role1;
    private boolean role2;
    private boolean role3;
    private boolean role4;
    private boolean role5;
    
    private String position;
    private int userId;

    public Role() {
        super();
    }
    
    public Role(int roleId, boolean role1, boolean role2, boolean role3, boolean role4, boolean role5, String position, int userId) {
        super();
        this.roleId = roleId;
        this.role1 = role1;
        this.role2 = role2;
        this.role3 = role3;
        this.role4 = role4;
        this.role5 = role5;
        
        this.position = position;
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isRole1() {
        return role1;
    }

    public void setRole1(boolean role1) {
        this.role1 = role1;
    }

    public boolean isRole2() {
        return role2;
    }

    public void setRole2(boolean role2) {
        this.role2 = role2;
    }

    public boolean isRole3() {
        return role3;
    }

    public void setRole3(boolean role3) {
        this.role3 = role3;
    }

    public boolean isRole4() {
        return role4;
    }

    public void setRole4(boolean role4) {
        this.role4 = role4;
    }

    public boolean isRole5() {
        return role5;
    }

    public void setRole5(boolean role5) {
        this.role5 = role5;
    }

    

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ""	;
    }
}
