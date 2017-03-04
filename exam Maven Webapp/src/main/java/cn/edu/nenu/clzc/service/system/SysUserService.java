package cn.edu.nenu.clzc.service.system;

import java.util.Set;

import cn.edu.nenu.clzc.commons.entites.system.SysUser;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.vo.system.SysUserVo;

public class SysUserService extends ServiceExtend {

	public Set<String> getRolesByUsername(String username) throws ContextException {
		Set<String>  rolesByUsername=null;
		try {
			rolesByUsername = sysUserDao.getRolesByUsername(username);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return rolesByUsername;
	}

	public Set<String> getPermissionsByUsername(String username) throws ContextException {
		Set<String>  permissionsByUsername=null;
		try {
			permissionsByUsername = sysUserDao.getPermissionsByUsername(username);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return permissionsByUsername;
	}

	public SysUserVo getUserByUsername(String username) throws ContextException {
		
		SysUserVo sysUserVo=null;
		try {
			sysUserVo=sysUserDao.getUserByUsername(username);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return sysUserVo;
	}
	/**
	 * 
	 * @author adward
	 * @Title 
	 * @Description 用户修改自己的信息
	 	 */
	public int updateUser(String id,  SysUser user) throws ContextException {
		int i = 0;
		try {
			i = sysUserDao.updateUser(id, user);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	

}
