package cn.edu.nenu.clzc.service.test.student;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.student.StudentDone;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.student.StudentDoneService;

public class StudentDoneDaoServiceTest extends BaseTest {
 
	StudentDoneService studentDoneService = new StudentDoneService();
	
	@Test
	public void testAddStudentDone() throws ContextException {
		StudentDone studentDone = new StudentDone();
		studentDone.setStudentId("a");
		studentDone.setExaminationOrTestId("25");
		studentDone.setStudentDoneType("2");
		studentDone.setStudentDoneTime(new Date());
		System.out.println(studentDoneService.addStudentDone(studentDone));
		
		System.out.println("addStudentDoneSuccess");
	}

	@Test
	public void testSelectStudentDone() throws ContextException {
		List<StudentDone> list = studentDoneService.selectStudentDone();
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getExaminationOrTestId());
			System.out.println(list.get(i).getStudentDoneType());
		}
		
	}

}
