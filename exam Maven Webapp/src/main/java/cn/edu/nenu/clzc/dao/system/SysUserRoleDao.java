package cn.edu.nenu.clzc.dao.system;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.system.SysUserRole;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import funs.ConnectionContext;

public class SysUserRoleDao extends AbstractDao {
	/**
	* @Title: insert
	* @Description: 添加用户角色信息sys_user_role
	* @author Adward
	* @param @return
	* @param @throws Exception    设定文件
	* @return int  返回类型
	* @throws
	 */
	public String insertSysUserRole(String sql,SysUserRole sysuserrole) throws Exception {
		String user_id = sysuserrole.getUserId();
		String role_id = sysuserrole.getRoleId();
		Object[] params = {user_id, role_id};
		sql = "insert into sys_user_role (user_id,role_id) values (?, ?)";
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.InsertSysUserRoleFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.InsertSysUserRoleFaild.getInfo());
		}
		return id;
	}
	/**
	* @Title: updateSysUserRole
	* @Description: 更新用户角色匹配信息
	* @author Adward
	* @param @return
	* @param @throws Exception    设定文件
	* @return int  返回类型
	* @throws
	 */
	public  int updateSysUserRole(String sql, SysUserRole sysuserrole) throws ContextException{
		Integer updateColumn=null;
		String user_id = sysuserrole.getUserId();
		String role_id = sysuserrole.getRoleId();
		Object[] params = {user_id, role_id};
		try {
			Connection connection = ConnectionContext.getInstance().getConnection();
			updateColumn = qr.update(connection,sql, params);
		} catch (SQLException e) {
			throw new ContextException("更新结果集失败！",e);
		}
		return updateColumn;
	}
	
	/**
	* @Title: insert
	* @Description:根据userid查询sysuserrole
	* @author Adward
	* @param @return
	* @param @throws Exception    设定文件
	* @return int  返回类型
	* @throws
	 */
	public List<SysUserRole> selectSysUserRole(String userId) throws Exception {
		List<SysUserRole> list = new ArrayList<SysUserRole>();
		String sql = "SELECT * from sys_user_role WHERE userId = ? ";
		Object param = userId;
		try {
			list = query(sql, new ExpandBeanListHandler<SysUserRole>(SysUserRole.class), param);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectSysUserRoleFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectSysUserRoleFaild.getInfo());
		}
		return list;
	}
}
