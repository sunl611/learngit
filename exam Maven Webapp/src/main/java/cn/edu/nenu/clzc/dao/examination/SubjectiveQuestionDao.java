package cn.edu.nenu.clzc.dao.examination;

import java.sql.SQLException;
import java.util.Date;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanHandler;
import cn.edu.nenu.clzc.commons.entites.student.SubjectiveQuestion;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;

/**
 * 
 * @author 我要睡觉了
 * @Title SubjectiveQuestionDao.java
 * @Description 学生完成的主观小题
 * @time 2016年12月8日 上午12:06:01
 */

public class SubjectiveQuestionDao extends AbstractDao {

	
	/**
	 * 
	 * @Title: addSubQuestion
	 * @Description: 添加一道学生完成的主观小题
	 * @return: String
	 * @throws Exception 
	 */
	public String addSubQuestion(SubjectiveQuestion subjectiveQuestion) throws Exception {
		String studentId = subjectiveQuestion.getStudentId();
		String examinationId = subjectiveQuestion.getExaminationId();
		String studentAnswer = subjectiveQuestion.getStudentAnswer();
		String teacherComment = subjectiveQuestion.getTeacherComment();
		Date answerTime = new Date();
		String sql = "INSERT INTO student_subjective_question_answer (student_id, examination_id, student_answer, teacher_comment, answer_time) VALUES (?, ?, ?, ?, ?)";
		Object[] params = {studentId, examinationId, studentAnswer, teacherComment, answerTime};
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddSubQuestionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddSubQuestionFaild.getInfo());
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: addTeacherComment
	 * @Description: 老师添加一条批注
	 * @return: int
	 * @throws Exception 
	 */
	public int addTeacherComment(String id, String teacherComment) throws Exception {
		int temp = 0;
		String sql = "UPDATE student_subjective_question_answer SET teacher_comment = ? WHERE id = ?";
		Object[] params = {teacherComment, id};
		try {
			temp = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.AddSubTeacherCommentFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddSubTeacherCommentFaild.getInfo());
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectSubQuestion
	 * @Description: 根据小题id查询出学生的写过的试题
	 * @return: SubjectiveQuestion
	 * @throws Exception 
	 */
	public SubjectiveQuestion selectSubQuestion(String questionId) throws Exception {
		SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
		String sql = "SELECT * from student_subjective_question_answer WHERE question_id = ?";
		Object param = questionId;
		try {
			subjectiveQuestion = query(sql, new ExpandBeanHandler<SubjectiveQuestion>(SubjectiveQuestion.class), param);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectSubQuestionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectSubQuestionFaild.getInfo());
		}
		return subjectiveQuestion;
	}
	
	

	
}
