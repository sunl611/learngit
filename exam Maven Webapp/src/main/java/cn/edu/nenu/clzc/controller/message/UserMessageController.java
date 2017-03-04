package cn.edu.nenu.clzc.controller.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;

/**
 * 
 * @author 我要睡觉了、杨可欣
 * @Title UserMessageInFrontController.java
 * @Description TODO
 * @time 2016年11月21日 下午11:39:18
 */

@WebServlet(value=RequestConstant.USERMESSAGE_CONTROLLER)
public class UserMessageController extends ControllerExtend {
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @Title: dispatchMessage
	 * @Description: 跳转到前台留言板首页
	 * @return: void
	 * @throws: ServletException, IOException
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_FRONT_MESSAGE, method=SystemConstant.GET)
	public void dispatchFrontMessage() throws ServletException, IOException{
		requestDispatcher(ViewUriConstant.FRONT_PAGE);
	}
	
	
	/**
	 * 
	 * @Title: ajaxGetFrontMessage
	 * @Description: ajax请求前台留言首页
	 * @return: void
	 * @throws: ServletException, IOException
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_FRONT_MESSAGE, method=SystemConstant.POST)
	@ResponseBody
	public void ajaxGetFrontMessage() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		int size = 0;
		try {
			list = userMessageService.selectFirstPageMessage(currentPage);
			size = userMessageService.selectFirstPageMessagePage();
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.FrontPageException.getInfo(),e);
			to500(ControllerExceptionEnum.FrontPageException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}
	
	
	/**
	 * 
	 * @Title: dispatchAddMessage
	 * @Description: 跳转到添加留言的页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_ADD_MESSAGE)
	public void dispatchAddMessage() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_MESSAGE);
	}
	
	
	/**
	 * 
	 * @Title: addMessage
	 * @Description: 前台增添或者回复一条留言
	 * @return: void
	 * @throws: ServletException, IOException
	 */
	@RequestMapping(value=RequestConstant.ADD_MESSAGE)
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
	
	
	/**
	 * 
	 * @Title: ajaxGetAllReply
	 * @Description: 查看所有回复和追评
	 * @return: void
	 * @throws: ServletException, IOException
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_ALL_REPLY)
	@ResponseBody
	public void ajaxGetAllReply() throws ServletException, IOException {
		String id = getString("id");
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		try {
			list = userMessageService.selectAllReply(id);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetAllReplyException.getInfo(),e);
			to500(ControllerExceptionEnum.GetAllReplyException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("replylist", list);
		sendJson();
	}
	
}
