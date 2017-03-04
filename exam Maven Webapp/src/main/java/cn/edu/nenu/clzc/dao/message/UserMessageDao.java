package cn.edu.nenu.clzc.dao.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanHandler;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.message.UserMessage;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.edu.nenu.clzc.commons.vo.message.UserMessageVo;

/**
 * 
 * @author 我要睡觉了
 * @Title UserMessageDao.java
 * @Description 留言板的Dao层
 * @time 2016年11月13日 上午10:07:39
 */

public class UserMessageDao extends AbstractDao {
	
	// 增加一条留言
	public String addMessage(UserMessage userMessage) throws Exception {
		String messageContent = userMessage.getMessageContent();
		Date messageTime = new Date();
		String userInfoId = userMessage.getUserInfoId();
		String relativeMessageId = userMessage.getRelativeMessageId();
		Object[] params = {messageContent, messageTime, userInfoId, relativeMessageId};
		String sql = "insert into user_message (message_content, message_time, user_info_id, relative_message_id) values (?, ?, ?, ?)";
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddMessageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddMessageFaild.getInfo());
		}
		return id;
	}
	
	
	
	
	
	
	// 更改一条留言的显示状态
	public int updateMeaage(String id, String messageIsDelete) throws Exception {
		Object[] params = {messageIsDelete, id};
		String sql = "update user_message set message_is_delete = ? where id = ?";
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.UpdateMessageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.UpdateMessageFaild.getInfo());
		}
		return i;
	}
	
	
	
	
	
	
	
	// 查看所有留言的分页List
	public List<UserMessageVo> selectAllMessage(int currentPage) throws Exception {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		// 带着留言者的用户名查询出所有留言，并实现分页的查询语句
		String sql = "select user_message.id, sys_user.username, user_message.message_content, user_message.message_time, user_message.user_info_id, user_message.relative_message_id, user_message.message_is_delete from user_message inner join sys_user on user_message.user_info_id = sys_user.id order by user_message.message_time DESC limit ?, ?";
		Object[] params = {(currentPage-1)*PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<UserMessageVo>(UserMessageVo.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectAllMessageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectAllMessageFaild.getInfo());
		}
		return list;
	}
	
	
	
	
	
	
	//计算所有留言的分页页数的方法
	public int selectAllPage() throws Exception {
		String sql = "select count(*) from user_message inner join sys_user on user_message.user_info_id = sys_user.id";
		// 计算总记录数
		int totalRow = 0;
		try {
			totalRow = query(sql, new ScalarHandler<Long>()).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectAllPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectAllPageFaild.getInfo());
		}
		return size(totalRow);		
	}
	
	
	
	
	
	
	
	
	
	// 计算前台页面可以显示的留言的分页页数
	
	
	public int selectFirstPageMessagePage() throws Exception {
		String sql = "select count(*) from user_message inner join sys_user on user_message.user_info_id = sys_user.id where user_message.message_is_delete = '0'";
		// 计算总记录数
		int totalRow = 0;
		try {
			totalRow = query(sql, new ScalarHandler<Long>()).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectFirstPageMessagePageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectFirstPageMessagePageFaild.getInfo());
		}
		return size(totalRow);		
	}
	
	
	
	
	
	
	
	
	
	
	// 计算模糊查询留言分页页数的方法
	public int selectByUserNamePage(String value) throws Exception {
		String sql = "select count(*) from user_message inner join sys_user on user_message.user_info_id = sys_user.id where sys_user.username like ?";
		Object param = "%"+value+"%";
		// 计算总记录数
		int totalRow = 0;
		try {
			totalRow = query(sql, new ScalarHandler<Long>(), param).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectByUserNamePageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectByUserNamePageFaild.getInfo());
		}
		return size(totalRow);		
	}




	
	// 搜索用户的用户名来查找对应的留言
	public List<UserMessageVo> selectByUserName(String userName, int currentPage) throws Exception {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		String sql = "select user_message.id, sys_user.username, user_message.message_content, user_message.message_time, user_message.user_info_id, user_message.relative_message_id, user_message.message_is_delete from user_message inner join sys_user on user_message.user_info_id = sys_user.id where sys_user.username like ? order by user_message.message_time DESC limit ?, ?";
		Object[] params = {"%"+userName+"%", (currentPage-1)*PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<UserMessageVo>(UserMessageVo.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectByUserNameFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectByUserNameFaild.getInfo());
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	// 留言板首页的分页显示
	public List<UserMessageVo> selectFirstPageMessage(int currentPage) throws Exception {
		List<UserMessageVo> list = new ArrayList<UserMessageVo>();
		// 带着留言者的用户名查询出所有可以显示的留言，并实现分页的查询语句
		String sql = "select user_message.id, sys_user.username, user_message.message_content, user_message.message_time, user_message.user_info_id, user_message.relative_message_id, user_message.message_is_delete from user_message inner join sys_user on user_message.user_info_id = sys_user.id where user_message.message_is_delete = '0' order by user_message.message_time DESC limit ?, ?";
		Object[] params = {(currentPage-1)*PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<UserMessageVo>(UserMessageVo.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectFirstPageMessageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectFirstPageMessageFaild.getInfo());
		}
		return list;
	}
	

	
	
	
	
	
	
	
	// 根据留言的id搜索它的回复
	public UserMessageVo selectById(String id) throws Exception {
		String sql = "select user_message.id, sys_user.username, user_message.message_content, user_message.message_time, user_message.user_info_id, user_message.relative_message_id, user_message.message_is_delete from user_message inner join sys_user on user_message.user_info_id = sys_user.id where relative_message_id = ?";
		UserMessageVo userMessageVo = null;
		try {
			userMessageVo = query(sql, new ExpandBeanHandler<UserMessageVo>(UserMessageVo.class),id);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectByIdFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectByIdFaild.getInfo());
		}
		return userMessageVo;
	}
	
	
	
	
	
	
}
