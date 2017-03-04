package cn.edu.nenu.clzc.controller.system;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;

@WebServlet(value={RequestConstant.USER_CONTROLLER})
public class UserController extends ControllerExtend {

	private static final long serialVersionUID = 1L;
	
	public void index() throws ServletException, IOException{
		requestDispatcher(ViewUriConstant.INDEX);
	}
	
	/**
	* @Title: addUserInfo
	* @Description: 增加用户信息
	*  @author Adward
	* @param @param user
	* @param @return
	* @param @throws Exception    
	* @return 
	* @throws
	 */
	@RequestMapping(value=RequestConstant.ADD_USER)
	public void addMessage() throws ServletException, IOException {
		UserMessageVo userMessageVo = new UserMessageVo();
		userMessageVo = getBean(UserMessageVo.class);
		userMessageVo.setMessageTime(new Date());
		try {
			userMessageService.addMessage(userMessageVo);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddMessageException.getInfo(),e);
			to500(ControllerExceptionEnum.AddMessageException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.FRONT_PAGE);
	}
}
