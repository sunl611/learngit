package cn.edu.nenu.clzc.dao.examination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanHandler;
import cn.edu.nenu.clzc.commons.entites.student.ObjectiveQuestion;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;

/**
 * 
 * @author 我要睡觉了
 * @Title ObjectiveQuestionDao.java
 * @Description 学生完成的客观小题
 * @time 2016年12月7日 下午6:30:38
 */

public class ObjectiveQuestionDao extends AbstractDao {

	
	/**
	 * 
	 * @Title: addObjQuestion
	 * @Description: 增加一道学生完成的客观题
	 * @return: String 
	 * @throws Exception 
	 */
	public String addObjQuestion(ObjectiveQuestion objectiveQuestion) throws Exception {
		String studentId = objectiveQuestion.getStudentId();
		String examinationId = objectiveQuestion.getExaminationId();
		String questionId = objectiveQuestion.getQuestionId();
		String studentAnswer = objectiveQuestion.getStudentAnswer();
		String teacherComment = objectiveQuestion.getTeacherComment();
		String answerRightOrWrong = objectiveQuestion.getAnswerRightOrWrong();
		Date answerTime = new Date();
		String sql = "INSERT INTO student_objective_question_answer (student_id, examination_id, question_id, student_answer, teacher_comment, answer_right_or_wrong, answer_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {studentId, examinationId, questionId, studentAnswer, teacherComment, answerRightOrWrong, answerTime};
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddObjQuestionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddObjQuestionFaild.getInfo());
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: addTeacherComment
	 * @Description: 老师批卷时进行备注
	 * @return: int
	 * @throws Exception 
	 */
	public int addTeacherComment(String id, String teacherComment) throws Exception {
		int temp = 0;
		String sql = "UPDATE student_objective_question_answer SET teacher_comment = ? WHERE id = ?";
		Object[] params = {teacherComment, id};
		try {
			temp = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.AddTeacherCommentFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddTeacherCommentFaild.getInfo());
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: judgeQuestion
	 * @Description: 判断该客观题学生答案正误
	 * @return:int
	 * @throws Exception 
	 */
	public int judgeQuestion(String id, String value) throws Exception {
		int temp = 0;
		String sql = "UPDATE student_objective_question_answer SET answer_right_or_wrong = ? WHERE id = ?";
		Object[] params = {value, id};
		try {
			temp = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.JudgeQuestionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.JudgeQuestionFaild.getInfo());
		}
		return temp;
	}
	
	
	
	/**
	 * 
	 * @Title: selectObjQuestions
	 * @Description: 根据小题题号查询出学生的答案和老师的批改
	 * @return: ObjectiveQuestion
	 * @throws Exception 
	 */
	public ObjectiveQuestion selectObjQuestions(String questionId) throws Exception {
		ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
		String sql = "SELECT * from student_objective_question_answer WHERE question_id = ?";
		Object param = questionId;
		try {
			objectiveQuestion = query(sql, new ExpandBeanHandler<ObjectiveQuestion>(ObjectiveQuestion.class), param);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectObjQuestionsFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectObjQuestionsFaild.getInfo());
		}
		return objectiveQuestion;
	}
	
	
}
