package cn.edu.nenu.clzc.dao.system;

import java.sql.SQLException;
import java.util.Date;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.entites.system.SysRolePermission;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;

public class SysRolePermissionDao extends AbstractDao {
	/**
	* @Title: insertSysRolePermission
	* @Description:添加一条用户信息权限
	* @author Adward
	* @param @return
	* @param @throws Exception    设定文件
	* @return int  返回类型
	* @throws
	 */
	public String insertSysRolePermission(SysRolePermission sysRolePermission) throws Exception {
		String permissionId = sysRolePermission.getPermissionId();
		String roleId = sysRolePermission.getRoleId();
		String sysroleRolePermissionIsDelete = sysRolePermission.getSysroleRolePermissionIsDelete();
		Date sysRolePermissionTime = new Date();
		String sql = "INSERT INTO sys_role_permission (permissionId, roleId, sysroleRolePermissionIsDelete,sysRolePermissionTime) VALUES (?, ?, ?, ?)";
		Object[] params = {permissionId, roleId, sysroleRolePermissionIsDelete,sysRolePermissionTime};
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddSysRolePermissionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddSysRolePermissionFaild.getInfo());
		}
		return id;
	}
	
	/**
	* @Title: insertSysRolePermission
	* @Description:删除一条用户信息权限
	* @author Adward
	* @param @return
	* @param @throws Exception    设定文件
	* @return int  返回类型
	* @throws
	 */
	public int deleteSysRolePermission(String id, String sysroleRolePermissionIsDelete) throws Exception {
		String sql = "UPDATE sys_role_permission SET sysrole_role_permission_is_delete = ? WHERE id = ?";
		Object[] params = {sysroleRolePermissionIsDelete, id};
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.DeleteSysRolePermissionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.DeleteSysRolePermissionFaild.getInfo());
		}
		return i;
	}
}
