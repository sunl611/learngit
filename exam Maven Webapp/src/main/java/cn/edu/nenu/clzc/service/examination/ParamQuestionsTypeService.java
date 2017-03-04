package cn.edu.nenu.clzc.service.examination;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestionsType;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;

/**
 * 
 * @author 杨可欣
 * @Title ParamQuestionsTypeService.java
 * @Description 大题类型service
 * @time 2016年12月2日 上午10:28:38
 */

public class ParamQuestionsTypeService extends ServiceExtend {

	
	/**
	 * 
	 * @Title: addQuestionsType
	 * @Description: 增加一种大题题型
	 * @return: String
	 * @throws ContextException 
	 */
	public String addQuestionsType(ParamQuestionsType paramQuestionsType) throws ContextException {
		String id = null;
		try {
			id = paramQuestionsTypeDao.addQuestionsType(paramQuestionsType);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: deleteQuestionsType
	 * @Description: 删除一种题型
	 * @return: int
	 * @throws ContextException 
	 */
	public int deleteQuestionsType(String id, String questionsTypeIsDelete) throws ContextException {
		int i = 0;
		try {
			i = paramQuestionsTypeDao.deleteQuestionsType(id, questionsTypeIsDelete);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	
	/**
	 * 
	 * @Title: selectType
	 * @Description: 查找可见题型
	 * @return: List<ParamQuestionsType>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsType> selectType() throws ContextException {
		List<ParamQuestionsType> list = new ArrayList<ParamQuestionsType>();
		try {
			list = paramQuestionsTypeDao.selectType();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectType
	 * @Description: 查找所有题型
	 * @return: List<ParamQuestionsType>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsType> selectAllType() throws ContextException {
		List<ParamQuestionsType> list = new ArrayList<ParamQuestionsType>();
		try {
			list = paramQuestionsTypeDao.selectAllType();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
}
