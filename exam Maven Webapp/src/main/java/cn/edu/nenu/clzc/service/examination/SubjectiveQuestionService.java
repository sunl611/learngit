package cn.edu.nenu.clzc.service.examination;

import cn.edu.nenu.clzc.commons.entites.student.SubjectiveQuestion;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;

/**
 * 
 * @author 我要睡觉了
 * @Title SubjectiveQuestionService.java
 * @Description 学生做过的主观题
 * @time 2016年12月8日 上午1:27:07
 */

public class SubjectiveQuestionService extends ServiceExtend {

	
	/**
	 * 
	 * @Title: addSubQuestion
	 * @Description: 添加一道学生完成的主观小题
	 * @return: String
	 * @throws ContextException 
	 */
	public String addSubQuestion(SubjectiveQuestion subjectiveQuestion) throws ContextException {
		String id = null;
		try {
			id = subjectiveQuestionDao.addSubQuestion(subjectiveQuestion);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: addTeacherComment
	 * @Description: 老师添加一条批注
	 * @return: int
	 * @throws ContextException 
	 */
	public int addTeacherComment(String id, String teacherComment) throws ContextException {
		int temp = 0;
		try {
			temp = subjectiveQuestionDao.addTeacherComment(id, teacherComment);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectSubQuestion
	 * @Description: 根据小题id查询出学生的写过的试题
	 * @return: SubjectiveQuestion
	 * @throws ContextException 
	 */
	public SubjectiveQuestion selectSubQuestion(String questionId) throws ContextException {
		SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
		try {
			subjectiveQuestion = subjectiveQuestionDao.selectSubQuestion(questionId);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return subjectiveQuestion;
	}
	
	
	
	
}
