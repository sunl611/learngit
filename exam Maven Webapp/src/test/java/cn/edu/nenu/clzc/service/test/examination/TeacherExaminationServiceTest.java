package cn.edu.nenu.clzc.service.test.examination;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.teacher.TeacherExamination;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.examination.TeacherExaminationService;

public class TeacherExaminationServiceTest extends BaseTest {

	TeacherExaminationService teacherExaminationService = new TeacherExaminationService();
	
	@Test
	public void testAddExamination() throws ContextException {
		TeacherExamination teacherExamination = new TeacherExamination();
		teacherExamination.setUnitId("1");
		teacherExamination.setExaminationPersistTime(56.00);
		teacherExamination.setExaminationCreateUsername("董老师");
		teacherExamination.setExaminationInfo("做了肯定挂");
		teacherExamination.setExaminationType("0");
		teacherExaminationService.addExamination(teacherExamination);
	}

	@Test
	public void testDeleteExamination() throws ContextException {
		teacherExaminationService.deleteExamination("1", "1");
	}

	@Test
	public void testSelectExaminationByUnit() throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		list = teacherExaminationService.selectExaminationByUnit("1", "0", 1);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getExaminationInfo());
		}
	}

	@Test
	public void testSelectExaminationByUnitPage() throws ContextException {
		System.out.println(teacherExaminationService.selectExaminationByUnitPage("1", "0"));
	}

	@Test
	public void testSelectAllExaminationByUnit() throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		list = teacherExaminationService.selectAllExaminationByUnit("1", "0", 1);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getExaminationInfo());
		}
	}

	@Test
	public void testSelectAllExaminationByUnitPage() throws ContextException {
		System.out.println(teacherExaminationService.selectAllExaminationByUnitPage("1", "0"));
	}

	@Test
	public void testSelectExaminationByInfo() throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		list = teacherExaminationService.selectExaminationByInfo("择", "0", 1);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getExaminationInfo());
		}
	}

	@Test
	public void testSelectExaminationByInfoPage() throws ContextException {
		System.out.println(teacherExaminationService.selectExaminationByInfoPage("选择", "0"));
	}

}
