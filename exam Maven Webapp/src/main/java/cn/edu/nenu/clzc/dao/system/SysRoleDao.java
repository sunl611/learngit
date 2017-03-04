package cn.edu.nenu.clzc.dao.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.handlers.ColumnListHandler;

//import com.mysql.jdbc.PreparedStatement;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.entites.system.SysRole;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.system.SysPermissionVo;

public class SysRoleDao extends AbstractDao {
	// 增加一个角色
	public boolean insert(SysRole sysRole) throws Exception {
		String sql = "insert into sys_role (id,role_name,role_available,role_time) value(?,?,?,?)";

		List<Object> params = new ArrayList<Object>();
		params.add(sysRole.getId());
		params.add(sysRole.getRoleName());
		params.add(sysRole.getRoleAvailable());
		params.add(sysRole.getRoleTime());

		String returnString = null;
		try {
			returnString = insert(sql, params.toArray());
		} catch (Exception e) {
			logger.error( DaoExceptionEnum.AddRoleFaild.getInfo(), e);
			throw e;
		}

		return returnString == null;
	}

	public Set<String> setRoles(String role_name) throws Exception {
		//查询一个角色
		String sql = "SELECT DISTINCT sys_role.role_name FROM sys_role WHERE sys_role.role_name =?OR sys_role.role_available =?OR sys_role.role_time =?OR sys_role.role_is_delete =?";
		List<String> columnList = null;
		Object[] params = { role_name, role_name, role_name, role_name };

		try {
			columnList = query(sql, new ColumnListHandler<String>(), params);
			System.out.println("rilegole");
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.GetRolesFaild.getInfo(), e);
			throw new Exception( DaoExceptionEnum.GetRolesFaildd.getInfo());
		}
		return new HashSet<String>(columnList);
	}
}
