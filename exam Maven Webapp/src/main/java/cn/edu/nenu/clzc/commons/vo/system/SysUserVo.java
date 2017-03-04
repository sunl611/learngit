package cn.edu.nenu.clzc.commons.vo.system;

import cn.edu.nenu.clzc.commons.entites.system.SysUser;

public class SysUserVo extends SysUser {

	
	private String roleAvailable;
	
	
	public String getRoleAvailable() {
		return roleAvailable;
	}


	public void setRoleAvailable(String roleAvailable) {
		this.roleAvailable = roleAvailable;
	}


	//返回加密盐等于userSalt+username
	public String getCredentialsSalt(){
		return getUserSalt()+getUsername();
	}
	
}
