package cn.edu.nenu.clzc.service.message;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.message.UserMessage;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;
import cn.edu.nenu.clzc.dao.message.UserMessageDao;

/**
 * 
 * @author 我要睡觉了
 * @Title UserMessageService.java
 * @Description 留言板的service层
 * @time 2016年11月14日 下午7:51:22
 */

public class UserMessageService extends ServiceExtend {
	
	// 增添一条留言的方法
	public String addMessage(UserMessage userMessage) throws ContextException {
		String id = null;
		try {
			id = userMessageDao.addMessage(userMessage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	
	
	
	// 修改一条留言的显示状态的方法
	public int updateMeaage(String id, String messageIsDelete) throws ContextException {
		int i = 0;
		try {
			i = userMessageDao.updateMeaage(id, messageIsDelete);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	
	
	
	// 查看所有留言的分页列表的方法
	public List<UserMessageVo> selectAllMessage(int currentPage) throws ContextException {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		try {
			list = userMessageDao.selectAllMessage(currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	
	// 搜索用户的用户名来查找对应的留言的方法
	public List<UserMessageVo> selectByUserName(String userName, int currentPage) throws ContextException {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		try {
			list = userMessageDao.selectByUserName(userName, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	
	
	// 留言板首页的分页显示方法
	public List<UserMessageVo> selectFirstPageMessage(int currentPage) throws ContextException {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		try {
			list = userMessageDao.selectFirstPageMessage(currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	
	
	
	// 计算所有留言的分页页数的方法
	public int selectAllPage() throws ContextException {
		int size = 0;
		try {
			size = userMessageDao.selectAllPage();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	
	
	
	
	
	
	// 计算前台可以显示的留言的分页页数的方法
	public int selectFirstPageMessagePage() throws ContextException {
		int size = 0;
		try {
			size = userMessageDao.selectFirstPageMessagePage();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	
	
	
	
	
	// 计算模糊查询的分页页数的方法
	public int selectByUserNamePage(String value) throws ContextException {
		int size = 0;
		try {
			size = userMessageDao.selectByUserNamePage(value);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	
	
	
	
	
	
	// 查看一条留言的回复
	public UserMessageVo selectReply(String id) throws ContextException {
		UserMessageVo userMessageVo = null;
		try {
			userMessageVo = userMessageDao.selectById(id);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return userMessageVo;
	}
	
	
	
	
	
	
	
	
	
	// 查看一条留言的全部回复以及追评
	public List<UserMessageVo> selectAllReply(String id) throws ContextException {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		UserMessageVo userMessageVo = null;
		try {
			userMessageVo = userMessageDao.selectById(id);
			while(userMessageVo != null){
				list.add(userMessageVo);
				userMessageVo = userMessageDao.selectById(userMessageVo.getId());
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	
	
}
