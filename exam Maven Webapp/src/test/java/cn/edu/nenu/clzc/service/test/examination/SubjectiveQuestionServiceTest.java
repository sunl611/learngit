package cn.edu.nenu.clzc.service.test.examination;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.student.SubjectiveQuestion;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.examination.SubjectiveQuestionService;

public class SubjectiveQuestionServiceTest extends BaseTest {

	SubjectiveQuestionService subjectiveQuestionService = new SubjectiveQuestionService();
	
	@Test
	public void testAddSubQuestion() throws ContextException {
		SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
		subjectiveQuestion.setStudentId("1");
		subjectiveQuestion.setExaminationId("1");
		subjectiveQuestion.setStudentAnswer("答案啊哈哈");
		subjectiveQuestion.setTeacherComment("糟糕极了");
		subjectiveQuestionService.addSubQuestion(subjectiveQuestion);
	}

	@Test
	public void testAddTeacherComment() throws ContextException {
		String teacherComment = "纳尼";
		String id = "";
		subjectiveQuestionService.addTeacherComment(id, teacherComment);
	}

	@Test
	public void testSelectSubQuestion() throws ContextException {
		SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
		subjectiveQuestion = subjectiveQuestionService.selectSubQuestion("");
		System.out.println(subjectiveQuestion.getStudentAnswer());
	}

}
