package cn.edu.nenu.clzc.commons.entites.system;

import java.util.Date;

public class SysRolePermission {
    private String id;

    private String permissionId;

    private String roleId;

    private Date sysRolePermissionTime;

    private String sysroleRolePermissionIsDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getSysRolePermissionTime() {
        return sysRolePermissionTime;
    }

    public void setSysRolePermissionTime(Date sysRolePermissionTime) {
        this.sysRolePermissionTime = sysRolePermissionTime;
    }

    public String getSysroleRolePermissionIsDelete() {
        return sysroleRolePermissionIsDelete;
    }

    public void setSysroleRolePermissionIsDelete(String sysroleRolePermissionIsDelete) {
        this.sysroleRolePermissionIsDelete = sysroleRolePermissionIsDelete;
    }
}