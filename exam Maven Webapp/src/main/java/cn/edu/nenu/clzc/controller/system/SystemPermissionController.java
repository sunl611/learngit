package cn.edu.nenu.clzc.controller.system;

import java.io.IOException;import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.entites.system.SysRole;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
import cn.edu.nenu.clzc.commons.vo.system.SysPermissionVo;
@WebServlet(value=RequestConstant.SYSPERMISSION_CONTROLLER)
public class SystemPermissionController extends ControllerExtend {

	 /** * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	* @Title: dispatchAdd
	* @Description: 跳转到填加权限的jsp
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value=RequestConstant.TO_ADD_PERMISSION)
	public void dispatchAdd() throws ServletException, IOException{
		requestDispatcher(ViewUriConstant.ADD_PERMISSION);
	}
	
	@RequestMapping(value=RequestConstant.ADD_PERMISSION,method="post")
	public void add() throws ServletException, IOException{
		 SysPermissionVo sysPermissionVo = getBean(SysPermissionVo.class);
		 sysPermissionVo.setPermissionTime(new Date());
		 
		try {
			sysPermissionService.insert(sysPermissionVo);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddPermissionFaild.getInfo(),e);
			to500(ControllerExceptionEnum.AddPermissionFaild.getInfo(), ViewUriConstant.ERROR500);
		}
		sendRedirect(ViewUriConstant.TO_PERMISSION_LIST);
	}
	
	@RequestMapping(value = RequestConstant.TO_PERMISSION_LIST )
	public void dispatchList() throws ServletException, IOException{
		requestDispatcher(ViewUriConstant.PERMISSION_LIST);
	}

}
