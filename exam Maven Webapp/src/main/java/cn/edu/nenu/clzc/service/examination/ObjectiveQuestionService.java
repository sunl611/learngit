package cn.edu.nenu.clzc.service.examination;

import cn.edu.nenu.clzc.commons.entites.student.ObjectiveQuestion;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;

public class ObjectiveQuestionService extends ServiceExtend {

	
	/**
	 * 
	 * @Title: addObjQuestion
	 * @Description: 增加一道学生完成的客观题
	 * @return: String
	 * @throws ContextException 
	 */
	public String addObjQuestion(ObjectiveQuestion objectiveQuestion) throws ContextException {
		String id = null;
		try {
			id = objectiveQuestionDao.addObjQuestion(objectiveQuestion);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: addTeacherComment
	 * @Description: 老师批卷时进行备注
	 * @return: int
	 * @throws ContextException 
	 */
	public int addTeacherComment(String id, String teacherComment) throws ContextException {
		int temp = 0;
		try {
			temp = objectiveQuestionDao.addTeacherComment(id, teacherComment);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: judgeQuestion
	 * @Description: 判断该客观题学生答案正误
	 * @return: int
	 * @throws ContextException 
	 */
	public int judgeQuestion(String id, String value) throws ContextException {
		int temp = 0;
		try {
			temp = objectiveQuestionDao.judgeQuestion(id, value);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectObjQuestions
	 * @Description: 根据小题题号查询出学生的答案和老师的批改
	 * @return: ObjectiveQuestion
	 * @throws ContextException 
	 */
	public ObjectiveQuestion selectObjQuestions(String questionId) throws ContextException {
		ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
		try {
			objectiveQuestion = objectiveQuestionDao.selectObjQuestions(questionId);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return objectiveQuestion;
	}
	
	
	
}
