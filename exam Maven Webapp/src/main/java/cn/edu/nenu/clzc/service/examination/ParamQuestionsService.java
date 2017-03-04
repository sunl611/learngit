package cn.edu.nenu.clzc.service.examination;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestions;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestionsArticle;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.vo.examination.ParamQuestionVo;
import cn.edu.nenu.clzc.commons.vo.examination.ParamQuestionsVo;

/**
 * 
 * @author 我要睡觉了
 * @Title ParamQuestionsService.java
 * @Description 大题的service
 * @time 2016年12月7日 上午8:54:54
 */

public class ParamQuestionsService extends ServiceExtend {

	
	/**
	 * 
	 * @Title: addQuestions
	 * @Description: 添加一道大题，同时添加该大题的大题文章和包含的小题
	 * @return: String 
	 * @throws ContextException 
	 */
	public String addQuestions(ParamQuestionsVo paramQuestionsVo, List<ParamQuestionVo> questionlist) throws ContextException {
		String id = null;
		ParamQuestionsArticle paramQuestionsArticle = new ParamQuestionsArticle();
		// 大题文章的属性提取出来
		String questionsArticle = paramQuestionsVo.getQuestionsArticle();
		String questionsArticleInfo = paramQuestionsVo.getQuestionsArticleInfo();
		// 存进大题文章对象
		paramQuestionsArticle.setArticle(questionsArticle);
		paramQuestionsArticle.setArticleInfo(questionsArticleInfo);
		
		try {
			id = paramQuestionsDao.addQuestions(paramQuestionsVo);
			if(questionsArticle != null && questionsArticleInfo != null) {
				paramQuestionsArticle.setArticleQuestionsId(id);
				paramQuestionsArticleDao.addQuestionsArticle(paramQuestionsArticle);
			}
			for(int i = 0; i < questionlist.size(); i++) {
				questionlist.get(i).setQuestionsId(id);
				paramQuestionDao.addQuestion(questionlist.get(i));
			}
			
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: deleteQuestions
	 * @Description: 更改大题是否可见
	 * @return: int
	 * @throws ContextException 
	 */
	public int deleteQuestions(String id, String value) throws ContextException {
		int temp = 0;
		try {
			temp = paramQuestionsDao.deleteQuestions(id, value);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectQuestionsByExam
	 * @Description: 按照试卷查询出所有可见的大题
	 * @return: List<ParamQuestions>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsVo> selectQuestionsByExam(String examinationId) throws ContextException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		try {
			list = paramQuestionsDao.selectQuestionsByExam(examinationId);
			for(int i = 0; i < list.size(); i++) {
				String questionsArticle = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticle();
				String questionsArticleInfo = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticleInfo();
				list.get(i).setQuestionsArticle(questionsArticle);
				list.get(i).setQuestionsArticleInfo(questionsArticleInfo);
				List<ParamQuestionVo> questionlist = paramQuestionDao.selectQuestion(list.get(i).getId());
				for(int j = 0; j < questionlist.size(); j++) {
					getOutline(questionlist.get(j));
				}
				list.get(i).setQuestionlist(questionlist);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectAllQuestionsByExam
	 * @Description: 按照试卷查询出所有的大题
	 * @return: List<ParamQuestions>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsVo> selectAllQuestionsByExam(String examinationId) throws ContextException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		try {
			list = paramQuestionsDao.selectAllQuestionsByExam(examinationId);
			for(int i = 0; i < list.size(); i++) {
				String questionsArticle = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticle();
				String questionsArticleInfo = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticleInfo();
				list.get(i).setQuestionsArticle(questionsArticle);
				list.get(i).setQuestionsArticleInfo(questionsArticleInfo);
				List<ParamQuestionVo> questionlist = paramQuestionDao.selectAllQuestion(list.get(i).getId());
				for(int j = 0; j < questionlist.size(); j++) {
					getOutline(questionlist.get(j));
				}
				list.get(i).setQuestionlist(questionlist);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectQuestionsByType
	 * @Description: 按照题型查询出所有可见的大题
	 * @return: List<ParamQuestions>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsVo> selectQuestionsByType(String typeId) throws ContextException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		try {
			list = paramQuestionsDao.selectQuestionsByType(typeId);
			for(int i = 0; i < list.size(); i++) {
				String questionsArticle = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticle();
				String questionsArticleInfo = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticleInfo();
				list.get(i).setQuestionsArticle(questionsArticle);
				list.get(i).setQuestionsArticleInfo(questionsArticleInfo);
				List<ParamQuestionVo> questionlist = paramQuestionDao.selectQuestion(list.get(i).getId());
				for(int j = 0; j < questionlist.size(); j++) {
					getOutline(questionlist.get(j));
				}
				list.get(i).setQuestionlist(questionlist);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectQuestionsByType
	 * @Description: 按照题型查询出所有的大题
	 * @return: List<ParamQuestions>
	 * @throws ContextException 
	 */
	public List<ParamQuestionsVo> selectAllQuestionsByType(String typeId) throws ContextException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		try {
			list = paramQuestionsDao.selectAllQuestionsByType(typeId);
			for(int i = 0; i < list.size(); i++) {
				String questionsArticle = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticle();
				String questionsArticleInfo = paramQuestionsArticleDao.selectQuestionsArticlesByuestionsArticlesId(list.get(i).getId()).getArticleInfo();
				list.get(i).setQuestionsArticle(questionsArticle);
				list.get(i).setQuestionsArticleInfo(questionsArticleInfo);
				List<ParamQuestionVo> questionlist = paramQuestionDao.selectAllQuestion(list.get(i).getId());
				for(int j = 0; j < questionlist.size(); j++) {
					getOutline(questionlist.get(j));
				}
				list.get(i).setQuestionlist(questionlist);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	

	
	/**
	 * 
	 * @Title: getOutline
	 * @Description: 将选择类型的题的题干和选项放入数组中
	 * @return: String[]
	 */
	public String[] getOutline(ParamQuestionVo paramQuestionVo) {
		int size = paramQuestionVo.getQuestionOutline().length();
		String[] outline = paramQuestionVo.getQuestionOutline().split("#", size);
		paramQuestionVo.setOutline(outline);
		return outline;
	}
	
	

}
