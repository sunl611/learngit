package cn.edu.nenu.clzc.controller.examination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.fileupload.RequestContext;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.entites.examination.ParamEdition;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestions;
import cn.edu.nenu.clzc.commons.entites.examination.ParamUnit;
import cn.edu.nenu.clzc.commons.entites.student.StudentDone;
import cn.edu.nenu.clzc.commons.entites.teacher.TeacherExamination;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
import cn.edu.nenu.clzc.commons.utils.PageBean;
import cn.edu.nenu.clzc.commons.vo.examination.ParamEditionVo;
import cn.edu.nenu.clzc.commons.vo.examination.ParamQuestionsVo;
import cn.edu.nenu.clzc.commons.vo.examination.ParamUnitVo;

/**
 * 
 * @author 我要睡觉了
 * @Title ExaminationBackController.java
 * @Description 试卷部分的Controller
 * @time 2016年12月8日 下午9:47:20
 */

@WebServlet(value=RequestConstant.EXAMINATION_BACK_CONTROLLER)
public class ExaminationBackController extends ControllerExtend {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 * @Title: dispatchBackEdition
	 * @Description: 跳转到后台的册展现页
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_BACK_EDITION, method=SystemConstant.GET)
	public void dispatchBackEdition() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.BACK_EDITION);
	}
	
	
	/**
	 * 
	 * @Title: ajaxGetBackEdition
	 * @Description: ajax请求后台显示册
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_BACK_EDITION)
	@ResponseBody
	public void ajaxGetBackEdition() throws ServletException, IOException {
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();
		try {
			list = paramEditionService.selectAllEdition();
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.BackEditionException.getInfo(),e);
			to500(ControllerExceptionEnum.BackEditionException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("editionList", list);
		sendJson();
	}
	
	
	
	/**
	 * 
	 * @Title: ajaxGetBackUnit 
	 * @Description: ajax请求后台显示单元
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_BACK_UNIT)
	@ResponseBody
	public void ajaxGetBackUnit() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		String editionId = getString("editionId");
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		try {
			list = paramUnitService.selectUnitByEdition(editionId, currentPage);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.BackUnitException.getInfo(),e);
			to500(ControllerExceptionEnum.BackUnitException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("unitList", list);
		sendJson();
	}
	
	
	/**
	 * 
	 * @Title: dispatchBackExamination
	 * @Description: 跳转到后端试卷展示页面
	 * @return:void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_BACK_EXAMINATION)
	public void dispatchBackExamination() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.BACK_EXAMINATION);
	}

	
	/**
	 * 
	 * @Title: ajaxGetBackExamination
	 * @Description: ajax请求获取后端试卷展示页面
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_BACK_EXAMINATION)
	@ResponseBody
	public void ajaxGetBackExamination() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		String unitId = getString("unidId");
		String examinationType = getString("examinationType");
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		int size = 0;
		try {
			list = teacherExaminationService.selectAllExaminationByUnit(unitId, examinationType, currentPage);
			size = teacherExaminationService.selectAllExaminationByUnitPage(unitId, examinationType);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.BackExaminationException.getInfo(),e);
			to500(ControllerExceptionEnum.BackExaminationException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}
	
	
	/**
	 * 
	 * @Title: dispatchQuestions
	 * @Description: 跳转到具体试卷页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_QUESTIONS)
	public void dispatchQuestions() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.BACK_QUESTIONS);
	}
	 
	
	/**
	 * 
	 * @Title: ajaxGetBackQuestions
	 * @Description: ajax请求显示一张试卷的所有大题
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_BACK_QUESTIONS)
	public void ajaxGetBackQuestions() throws ServletException, IOException {
		String examinationId = getString("examinationId");
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		try {
			list = paramQuestionsService.selectAllQuestionsByExam(examinationId);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.BackQuestionsException.getInfo(),e);
			to500(ControllerExceptionEnum.BackQuestionsException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("questionsList", list);
		sendJson();
	}
	
	
	/**
	 * 
	 * @Title: dispatchAddEdition
	 * @Description: 跳转到添加册的页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_ADD_EDITION)
	public void dispatchAddEdition() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_EDITION);
	}
	
	
	/**
	 * 
	 * @Title: addEdition
	 * @Description: 添加一个册
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.ADD_EDITION)
	public void addEdition() throws ServletException, IOException {
		ParamEdition paramEdition = getBean(ParamEdition.class);
		try {
			paramEditionService.addEdition(paramEdition);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddEditionException.getInfo(),e);
			to500(ControllerExceptionEnum.AddEditionException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.BACK_EDITION);
	}
	
	
	/**
	 * 
	 * @Title: dispatchAddUnit
	 * @Description: 跳转至增加单元页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_ADD_UNIT)
	public void dispatchAddUnit() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_UNIT);
	}
	
	
	/**
	 * 
	 * @Title: addUnit
	 * @Description: 添加一个单元
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.ADD_UNIT)
	public void addUnit() throws ServletException, IOException {
		ParamUnit paramUnit = getBean(ParamUnitVo.class);
		try {
			paramUnitService.addUnit(paramUnit);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddUnitException.getInfo(),e);
			to500(ControllerExceptionEnum.AddUnitException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.BACK_EDITION);
	}
	
	
	/**
	 * 
	 * @Title: ajaxDeleteEdition
	 * @Description: 更改一个册的显示状态
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_UPDATE_EDITION)
	@ResponseBody
	public void ajaxUpdateEdition() throws ServletException, IOException {
		String id = getString("unitid");
		String value = getString("value");
		try {
			paramEditionService.updateEditiion(id, value);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.UpdateEditionException.getInfo(),e);
			to500(ControllerExceptionEnum.UpdateEditionException.getInfo(), ViewUriConstant.ERROR500);
		}
	}
	
	
	/**
	 * 
	 * @Title: ajaxUpdateUnit
	 * @Description: 更改一个单元是否可见
	 * @return: void
	 * @throws: ServletException
	 * @throws: IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_UPDATE_UNIT)
	@ResponseBody
	public void ajaxUpdateUnit() throws ServletException, IOException {
		String id = getString("unitId");
		String value = getString("unitId");
		try {
			paramUnitService.updateUnit(id, value);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.UpdateUnitException.getInfo(),e);
			to500(ControllerExceptionEnum.UpdateUnitException.getInfo(), ViewUriConstant.ERROR500);
		}
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: dispatchAddExamination
	 * @Description: 跳转到添加试卷的页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_ADD_EXAMINATION)
	public void dispatchAddExamination() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_EXAMINATION);
	}
	
	
	/**
	 * 
	 * @Title: addExamination
	 * @Description: 添加一套试卷的基本信息
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.ADD_EXAMINATION)
	public void addExamination() throws ServletException, IOException {
		TeacherExamination teacherExamination = getBean(TeacherExamination.class);
		
		try {
			teacherExaminationService.addExamination(teacherExamination);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddExaminationException.getInfo(),e);
			to500(ControllerExceptionEnum.AddExaminationException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.BACK_EXAMINATION);
	}
	
	
	/**
	 * 
	 * @Title: ajaxUpdateExamination
	 * @Description: ajax请求更改一个试卷是否可见
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_UPDATE_EXAMINATION)
	@ResponseBody
	public void ajaxUpdateExamination() throws ServletException, IOException {
		String id = getString("examinationId");
		String value = getString("value");
		try {
			teacherExaminationService.deleteExamination(id, value);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.UpdateExaminationException.getInfo(),e);
			to500(ControllerExceptionEnum.UpdateExaminationException.getInfo(), ViewUriConstant.ERROR500);
		}
	}
	
	
	/**
	 * 
	 * @Title: dispatchAddQuestions
	 * @Description: 跳转到添加大题的页面
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_ADD_QUESTIONS)
	public void dispatchAddQuestions() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_QUESTIONS);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @Title: dispatchStudentDone
	 * @Description: 跳转到该学生做过的试卷显示页
	 * @return: void
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value=RequestConstant.DISPATCH_STUDENT_DONE)
	public void dispatchStudentDone() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.STUDENT_DONE);
	}
	
	
	/**
	 * 
	 * @Title: ajaxGetDoneQuestions
	 * @Description: ajax请求出学生做过的试卷列表
	 * @return: void
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value=RequestConstant.AJAX_GET_DONE_EXAMINATION)
	@ResponseBody
	public void ajaxGetDoneExamination() throws ServletException, IOException {
		String studentId = getString("studentId");
		List<TeacherExamination> examinationlist = new ArrayList<TeacherExamination>();

		try {
			List<StudentDone> list = studentDoneService.selectExaminationOrTestId(studentId);
			
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetDoneExaminationException.getInfo(),e);
			to500(ControllerExceptionEnum.GetDoneExaminationException.getInfo(), ViewUriConstant.ERROR500);
		}
	}
	
	
	
	
	
	
}
