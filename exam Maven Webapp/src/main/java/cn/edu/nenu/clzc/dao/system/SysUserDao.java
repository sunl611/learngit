package cn.edu.nenu.clzc.dao.system;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.entites.system.SysUser;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.system.SysUserVo;
import cn.itcast.jdbc.TxQueryRunner;

public class SysUserDao extends AbstractDao {
	/**
	* @Title: getRolesByUsername
	* @Description:根据用户名获取角色信息
	* @param @param username
	* @param @return
	* @param @throws Exception    设定文件
	* @return Set<String>    返回类型
	* @throws
	 */
	public Set<String> getRolesByUsername(String username) throws Exception {
		String sql="SELECT DISTINCT sys_role.role_name FROM sys_role INNER JOIN sys_user_role ON sys_role.id = sys_user_role.role_id INNER JOIN sys_user ON sys_user_role.user_id = sys_user.id WHERE sys_user.username=? OR sys_user.user_phone=?";
		List<String> columnList = null;
		Object[] params = {username,username,username};
		
		try {
			columnList = query(sql, new ColumnListHandler<String>(),params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.GetRolesByUsernameFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.GetRolesByUsernameFaild.getInfo());
		}
		return new HashSet<String>(columnList);
	}
	/**
	 * 
	* @Title: getPermissionsByUsername
	* @Description: 根据用户信息获取权限信息
	* @param @param username
	* @param @return
	* @param @throws Exception    设定文件
	* @return Set<String>    返回类型
	* @throws
	 */
	public Set<String> getPermissionsByUsername(String username) throws Exception {
		String sql="SELECT DISTINCT sys_permission.permission_value FROM sys_user INNER JOIN sys_user_role ON sys_user_role.user_id = sys_user.id INNER JOIN sys_role ON sys_role.id = sys_user_role.role_id INNER JOIN sys_role_permission ON sys_role_permission.role_id = sys_role.id INNER JOIN sys_permission ON sys_permission.id = sys_role_permission.permission_id WHERE sys_user.username =? OR sys_user.user_phone =?";
		List<String> columnList=null;
		Object[] params = {username,username,username};
		try {
			columnList= query(sql, new ColumnListHandler<String>(),params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.GetPermissionsByUsernameFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.GetPermissionsByUsernameFaild.getInfo());
		}
		return new HashSet<String>(columnList);
	}
	
	
	/**
	* @Title: getUserByUsername
	* @Description: 根据用户登录（用户名or电话）得到用户
	* @param @param username
	* @param @return
	* @param @throws Exception    设定文件
	* @return SysUserVo    返回类型
	* @throws
	 */
	public SysUserVo getUserByUsername(String username) throws Exception {
		String sql="SELECT sys_user.id, sys_user.`password`, sys_user.username, sys_user.user_show_name, sys_user.user_info, sys_user.user_image, sys_user.user_type, sys_user.user_phone, sys_user.user_is_lock, sys_user.user_salt, sys_user.user_time, sys_user.user_is_delete, sys_user.user_word FROM sys_user WHERE sys_user.username =?  OR sys_user.user_phone =?"; 
		SysUserVo userVo=null;
		Object[] params = {username,username};
		
		try {
			 userVo = query(sql, new BeanHandler<SysUserVo>(SysUserVo.class),params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.GetUserByUsernameFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.GetUserByUsernameFaild.getInfo());
		}
		return userVo;
	}
	

/**
* @Title: insertUser
* @Description: 后台管理管理员添加一条用户信息（账户密码）
* @param @param user
* @param @return
* @param @throws Exception    
* @return 
* @throws
 */
	public String insertUser(SysUser user) throws Exception {
		String username = user.getUsername();
		String password = user.getPassword();
		String usershowname = user.getUserShowName();
		String userinfo = user.getUserInfo();
		String userimagine = user.getUserImage();
		String usertype = user.getUserType();
		String userphone = user.getUserPhone();
		String userislock = user.getUserIsLock();
		
		Object[] params = {username, password,usershowname, userinfo, userimagine, usertype, userphone,  userislock};
		String sql = "insert into sys_user (username, password, userword, usershowname, userinfo, userimagine, usertype, userphone, userislock) values (?, ?, ?, ?,?,?,?,?,?,?,?)";
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.InsertUserFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.InsertUserFaild.getInfo());
		}
		return id;
	}
	
	
	/**
	* @Title: updateUserInfo
	* @Description: 修改个人信息
	*  @author Adward
	* @param @param user
	* @param @return
	* @param @throws Exception    
	* @return 
	* @throws
	 */
	
	public int updateUser(String id, SysUser user) throws Exception {
		String username = user.getUsername();
		String password = user.getPassword();
		String usershowname = user.getUserShowName();
		String userinfo = user.getUserInfo();
		String userimagine = user.getUserImage();
		String usertype = user.getUserType();
		String userphone = user.getUserPhone();
		String userislock = user.getUserIsLock();
		String useremail = user.getUserEmail();
		Object[] params = {username, password,usershowname, userinfo, userimagine, usertype, userphone,  userislock,useremail};
		Integer updateColumn=null;
		QueryRunner qr=new TxQueryRunner();
		String sql="update sys_user set username = username, password=password,user_showname=usershowname, user_info=userinfo, user_imagine=userimagine, user_type=usertype, user_phone=userphone, user_is_lock=userislock,user_email=useremail where user_id=?";
		try{
			updateColumn = qr.update(sql,params);
		} catch (SQLException e) {
			logger.error(DaoExceptionEnum.UpdateUserFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.UpdateUserFaild.getInfo());
		}
		return updateColumn;
	}

	/**
	* @Title: deleteUserInfo
	* @Description: 删除查询用户的信息
	* @author Adward
	* @param @param id
	* @param @param userIsDelete
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int deleteQuestion(String id, String userIsDelete) throws Exception {
		String sql = "UPDATE sys_user SET userIsDelete = ? WHERE id = ?";
		Object[] params = {userIsDelete, id};
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.DeleteUserFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.DeleteUserFaild.getInfo());
		}
		return i;
	}
}


