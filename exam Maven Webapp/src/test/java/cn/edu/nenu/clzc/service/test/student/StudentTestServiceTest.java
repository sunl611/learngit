package cn.edu.nenu.clzc.service.test.student;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.student.StudentTest;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.examination.StudentTestService;

public class StudentTestServiceTest extends BaseTest {

	StudentTestService studentTestService = new StudentTestService();
	
	@Test
	public void testAddStudentTest() throws ContextException {
		StudentTest studentTest = new StudentTest();
		studentTest.setStudentId("s");
		studentTest.setStudentTestType("1");
		studentTest.setStudentTestInfo("添加一个自组卷");
		studentTest.setQuestionsId("1");
		studentTest.setStudentTestTime(new Date());
		System.out.println(studentTestService.addStudentTest(studentTest));
		
		System.out.println("addTestSuccess");
		
		
		
		
	}

	@Test
	public void testDeleteStudentTest() throws ContextException {
		System.out.println(studentTestService.deleteStudentTest("2","1"));
		System.out.println("delTestSuccess");
		
	}

	

}
