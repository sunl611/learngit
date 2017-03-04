package cn.edu.nenu.clzc.controller.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;

/**
 * 
 * @author 我要睡觉了
 * @Title UserMessageBackController.java
 * @Description TODO
 * @time 2016年11月27日 下午11:45:31
 */

@WebServlet(value=RequestConstant.USERMESSAGE_BACK_CONTROLLER)
public class UserMessageBackController extends ControllerExtend {

	
	/**
	 * 
	 * @Title: DispatchBackMessage
	 * @Description: 跳转留言板后台首页
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_BACK_MESSAGE)
	public void DispatchBackMessage() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.BACK_PAGE);
	}
	
	
	/**
	 * 
	 * @Title: ajaxGetBackMessage
	 * @Description: 后台查看所有留言
	 * @return: void
	 * @throws ServletException
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_BACK_MESSAGE1)
	@ResponseBody
	public void ajaxGetBackMessage() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		int size = 0;
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		try {
			list = userMessageService.selectAllMessage(currentPage);
			size = userMessageService.selectAllPage();
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.BackPageException.getInfo(),e);
			to500(ControllerExceptionEnum.BackPageException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}
	
	
	/**
	 * 
	 * @Title: dispatchSelectByName
	 * @Description: 跳转到模糊查询结果页面
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_SELECT_BY_NAME)
	public void dispatchSelectByName() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.SELECT_BY_NAME);
	}
	
	
	/**
	 * 
	 * @Title: ajaxSelectByName
	 * @Description: 模糊查询结果页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_SELECT_BY_NAME)
	@ResponseBody
	public void ajaxSelectByName() throws ServletException, IOException {
		String userName = getString("userName");
		int currentPage = getInt("currentPage");
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		int size = 1;
		try {
			list = userMessageService.selectByUserName(userName, currentPage);
			size = userMessageService.selectByUserNamePage(userName);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.SelectByNameException.getInfo(),e);
			to500(ControllerExceptionEnum.SelectByNameException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}
	
	
	/**
	 * 
	 * @Title: updateMessage
	 * @Description: 更改一条留言是否在前台可见
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.UPDATE_MESSAGE)
	public void updateMessage() throws ServletException, IOException {
		String id = getString("id");
		String messageIsDelete = getString("messageIdDelete");
		try {
			userMessageService.updateMeaage(id, messageIsDelete);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.UpdateMessageException.getInfo(),e);
			to500(ControllerExceptionEnum.UpdateMessageException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.BACK_PAGE);
	}
	
	
	/**
	 * 
	 * @Title: dispatchReplyMessage
	 * @Description: 跳转到后台的回复留言页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_REPLY_MESSAGE)
	public void dispatchReplyMessage() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_MESSAGE);
	}
	
	
	/**
	 *  
	 * @Title: replyMessage
	 * @Description: 后台增添或者回复一条留言
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.REPLY_MESSAGE)
	public void replyMessage() throws ServletException, IOException {
		UserMessageVo userMessageVo = new UserMessageVo();
		userMessageVo = getBean(UserMessageVo.class);
		userMessageVo.setMessageTime(new Date());
		try {
			userMessageService.addMessage(userMessageVo);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddMessageException.getInfo(),e);
			to500(ControllerExceptionEnum.AddMessageException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.BACK_PAGE);
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
