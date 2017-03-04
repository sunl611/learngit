package cn.edu.nenu.clzc.commons.entites.system;

import java.util.Date;

public class SysUser {
    private String id;

    private String password;

    private String username;

    private String userShowName;

    private String userInfo;

    private String userImage;

    private String userType;

    private String userPhone;

    private String userQq;

    private String userIsLock;

    private String userSalt;

    private Date userTime;

    private String userIsDelete;   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserShowName() {
        return userShowName;
    }

    public void setUserShowName(String userShowName) {
        this.userShowName = userShowName;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserIsLock() {
        return userIsLock;
    }
 
    public void setUserIsLock(String userIsLock) {
        this.userIsLock = userIsLock;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public String getUserIsDelete() {
        return userIsDelete;
    }

    public void setUserIsDelete(String userIsDelete) {
        this.userIsDelete = userIsDelete;
    }

    public String getUserQq() {
        return userQq;
    }
    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }
    
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", password=" + password + ", username="
				+ username + ", sysRoleId=" + ", userShowName="
				+ userShowName + ", userInfo=" + userInfo + ", userImage="
				+ userImage + ", userType=" + userType + ", userPhone="
				+ userPhone + ", userQq=" + userQq + ", userIsLock=" + userIsLock 
				+ ", userSalt=" + userSalt	+ ", userTime=" + userTime + ""
				+ ", userIsDelete=" + userIsDelete
	            + "]";
	}
    
}