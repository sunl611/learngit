package cn.edu.nenu.clzc.commons.entites.system;

import java.util.Date;

public class SysPermission {
    private String id;

    private String permissionValue;

    private String permissionInfo;

    private Date permissionTime;

    private String permissionIsDelete;

    private String permissionAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(String permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public Date getPermissionTime() {
        return permissionTime;
    }

    public void setPermissionTime(Date permissionTime) {
        this.permissionTime = permissionTime;
    }

    public String getPermissionIsDelete() {
        return permissionIsDelete;
    }

    public void setPermissionIsDelete(String permissionIsDelete) {
        this.permissionIsDelete = permissionIsDelete;
    }

    public String getPermissionAvailable() {
        return permissionAvailable;
    }

    public void setPermissionAvailable(String permissionAvailable) {
        this.permissionAvailable = permissionAvailable;
    }
}