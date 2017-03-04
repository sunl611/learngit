package cn.edu.nenu.clzc.service.test.examination;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.student.ObjectiveQuestion;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.examination.ObjectiveQuestionService;

public class ObjectiveQuestionServiceTest extends BaseTest {

	ObjectiveQuestionService objectiveQuestionService = new ObjectiveQuestionService();
	
	@Test
	public void testAddObjQuestion() throws ContextException {
		ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
		objectiveQuestion.setStudentId("s1");
		objectiveQuestion.setExaminationId("1");
		objectiveQuestion.setQuestionId("1");
		objectiveQuestion.setStudentAnswer("答案1");
		objectiveQuestion.setTeacherComment("评语1");
		objectiveQuestion.setAnswerRightOrWrong("1");
		objectiveQuestionService.addObjQuestion(objectiveQuestion);
	}

	@Test
	public void testAddTeacherComment() throws ContextException {
		String teacherComment = "写的不错";
		String id = "5ed7346e-6c35-4f31-8141-bddbadfbf533";
		objectiveQuestionService.addTeacherComment(id, teacherComment);		
	}

	@Test
	public void testJudgeQuestion() throws ContextException {
		String id = "5ed7346e-6c35-4f31-8141-bddbadfbf533";
		String value = "1";
		objectiveQuestionService.judgeQuestion(id, value);
	}

	@Test
	public void testSelectObjQuestions() throws ContextException {
		String questionId = "1";
		ObjectiveQuestion objectiveQuestion = new ObjectiveQuestion();
		objectiveQuestion = objectiveQuestionService.selectObjQuestions(questionId);
		System.out.println(objectiveQuestion.getStudentAnswer());
	}

}
