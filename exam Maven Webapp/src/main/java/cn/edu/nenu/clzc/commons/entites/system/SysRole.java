package cn.edu.nenu.clzc.commons.entites.system;

import java.util.Date;

public class SysRole {
    private String id;

    private String roleName;

    private String roleAvailable;

    private Date roleTime;

    private String roleIsDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAvailable() {
        return roleAvailable;
    }

    public void setRoleAvailable(String roleAvailable) {
        this.roleAvailable = roleAvailable;
    }

    public Date getRoleTime() {
        return roleTime;
    }

    public void setRoleTime(Date roleTime) {
        this.roleTime = roleTime;
    }

    public String getRoleIsDelete() {
        return roleIsDelete;
    }

    public void setRoleIsDelete(String roleIsDelete) {
        this.roleIsDelete = roleIsDelete;
    }
}