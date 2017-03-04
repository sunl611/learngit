package cn.edu.nenu.clzc.dao.system;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.vo.system.SysPermissionVo;

public class SysPermissionDao extends AbstractDao {
	/**
	 * 
	* @Title: insert
	* @Description:插入权限
	* @param @param sysPermissionVo
	* @param @return
	* @param @throws Exception    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean insert(SysPermissionVo sysPermissionVo) throws Exception {
		String sql = "INSERT INTO sys_permission(permission_value,permission_info,permission_time) VALUES(?,?,?)";
		
		List< Object> params = new ArrayList<Object>();
		
		params.add(sysPermissionVo.getPermissionValue());
		params.add(sysPermissionVo.getPermissionInfo());
		params.add(sysPermissionVo.getPermissionTime());
		
		String returnString = null;
		try {
			returnString = insert(sql, params.toArray());
		} catch (Exception e) {
			logger.error(DaoExceptionEnum.SysPermissionDaoInsertFaild.getInfo(),e);
			throw e;
		}
		
		return returnString == null;
	}

}
