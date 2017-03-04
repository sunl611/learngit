package cn.edu.nenu.clzc.service.system;

import cn.edu.nenu.clzc.commons.enumeration.exception.ServiceExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.vo.system.SysPermissionVo;

public class SysPermissionService  extends ServiceExtend{
	/**
	 * @throws ContextException 
	 * 添加权限
	* @Title: insert
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param sysPermissionVo
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean insert(SysPermissionVo sysPermissionVo) throws ContextException{
		if (sysPermissionVo == null) 
			throw new ContextException(ServiceExceptionEnum.SYSPERMISSIONVONULL.getInfo(),new NullPointerException());
		
		
		boolean inserted = false;
		try {
			inserted = sysPermissionDao.insert(sysPermissionVo);
		} catch (Exception e) {
			logger.error(ServiceExceptionEnum.SysPermissionServiceInsertFaild.getInfo(),e);
			throw new ContextException(e);
		} 
		
		return inserted;
	}
}
