package cn.edu.nenu.clzc.service.test.message;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.message.UserMessage;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;
import cn.edu.nenu.clzc.service.message.UserMessageService;

/**
 * 
 * @author 杨可欣
 * @Title UserMessageServiceTest.java
 * @Description TODO
 * @time 2016年11月21日 下午6:48:50
 */

public class UserMessageServiceTest extends BaseTest {

	UserMessageService userMessageService = new UserMessageService();
	

	@Test
	public void testAddMessage() throws ContextException {
		UserMessage userMessage = new UserMessage();
		userMessage.setMessageContent("又回复1");
		userMessage.setRelativeMessageId("baef3e0c-4ce6-4137-add5-7ad7f910e3a2");
		userMessage.setUserInfoId("a");
		userMessageService.addMessage(userMessage);
	}

	@Test
	public void testUpdateMeaage() throws ContextException {
		System.out.println(userMessageService.updateMeaage("b4bdf4ab-3afe-47ef-a7a5-99c1edab7c51", "1"));
	}

	@Test
	public void testSelectAllMessage() throws ContextException {
		List<UserMessageVo> list = userMessageService.selectAllMessage(1);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getMessageContent());
		}
	}

	@Test
	public void testSelectByUserName() throws ContextException {
		List<UserMessageVo> list = userMessageService.selectByUserName("dongs", 1);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getMessageContent());
		}
	}

	@Test
	public void testSelectFirstPageMessage() throws ContextException {
		List<UserMessageVo> list = userMessageService.selectFirstPageMessage(1);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getMessageContent());
		}
	}

	@Test
	public void testSelectAllPage() throws ContextException {
		int i;
		i = userMessageService.selectAllPage();
		System.out.println(i);
	}

	@Test
	public void testSelectFirstPageMessagePage() throws ContextException {
		int i;
		i = userMessageService.selectFirstPageMessagePage();
		System.out.println(i);
	}
	
	@Test
	public void testSelectByUserNamePage() throws ContextException {
		int i;
		i = userMessageService.selectByUserNamePage("dongs");
		System.out.println(i);
	}
	
	@Test
	public void testSelectReply() throws ContextException {
		UserMessageVo userMessageVo = new UserMessageVo();
		userMessageVo = userMessageService.selectReply("b4bdf4ab-3afe-47ef-a7a5-99c1edab7c51");
		System.out.println(userMessageVo.getMessageContent());
	}

	@Test
	public void testSelectAllReply() throws ContextException {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		list = userMessageService.selectAllReply("b4bdf4ab-3afe-47ef-a7a5-99c1edab7c51");
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getMessageContent());
		}
	}

}
