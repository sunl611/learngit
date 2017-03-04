package cn.edu.nenu.clzc.commons.enumeration.exception;

import cn.edu.nenu.clzc.commons.core.ExceptionEnum;

public enum ServiceExceptionEnum implements ExceptionEnum{
	SYSPERMISSIONVONULL("添加的权限值对象为null!",10001),
	
	SysPermissionServiceInsertFaild("插入权限信息失败",10002);
	
	
	
	private int id;
	private String info;

	ServiceExceptionEnum(String message,int id){
		this.id=id;
		this.info=message;
	}
	
	
	public int getId() {
		return id;
	}

	public String getInfo() {
		return info;
	}

	
}
