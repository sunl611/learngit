package cn.edu.nenu.clzc.controller.examination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import com.mysql.cj.mysqlx.devapi.DatabaseObjectDescription;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestionsType;
import cn.edu.nenu.clzc.commons.entites.student.StudentDone;
import cn.edu.nenu.clzc.commons.entites.student.StudentTest;
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
* @ClassName: ExaminationController
* @Description: 试卷前台controller层(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年12月11日 下午5:56:02
 */
@WebServlet(value = RequestConstant.EXAMINATION_CONTROLLER)
public class ExaminationController extends ControllerExtend {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	* @Title: dispatchFrontExamination
	* @Description: 跳转到前台试卷首页(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.DISPATCH_FRONT_EXAMINATION, method = SystemConstant.GET)
	public void dispatchFrontExamination() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.EXAMINATION_FRONT_PAGE);
	}

	/**
	 * 
	* @Title: ajaxGetFrontExaminationEdition
	* @Description: ajax请求前台试卷首页显示的册(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_FRONT_EXAMINATION, method = SystemConstant.POST)
	@ResponseBody
	public void ajaxGetFrontExaminationEdition() throws ServletException, IOException {
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();
		int size = 1;
		try {
			list = paramEditionService.selectAllEdition();
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.ExaminationEditionException.getInfo(), e);
			to500(ControllerExceptionEnum.ExaminationEditionException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}

	/**
	 * 
	* @Title: ajaxGetUnitByEditionId
	* @Description: ajax请求-前台试卷首页根据册的ID显示对应的单元(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_UNIT_BY_EDITIONID)
	public void ajaxGetUnitByEditionId() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		String editionId = getString("editionId");
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		int size = 0;
		try {
			list = paramUnitService.selectUnitByEdition(editionId, currentPage);
			size = paramUnitService.selectFirstUnitPage();
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetUnitByEditionIdException.getInfo(), e);
			to500(ControllerExceptionEnum.GetUnitByEditionIdException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}

	/**
	 * 
	* @Title: ajaxGetExaminationByUnit
	* @Description: ajax请求-前台试卷首页根据单元显示老师的分配试卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_EXAMINATION_BY_UNIT)
	public void ajaxGetExaminationByUnit() throws ServletException, IOException {
		int currentPage = getInt("currentPage");
		String unitId = getString("unitId");
		String examinationType = getString("examinationType");
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		int size = 0;
		try {
			list = teacherExaminationService.selectExaminationByUnit(unitId, examinationType, currentPage);
			size = teacherExaminationService.selectExaminationByUnitPage(unitId, examinationType);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetExaminationByUnitException.getInfo(), e);
			to500(ControllerExceptionEnum.GetExaminationByUnitException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}

	/**
	 * 
	* @Title: ajaxGetStudentTest
	* @Description: ajax请求-前台显示学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_STUDENT_TEST)
	public void ajaxGetStudentTest() throws ServletException, IOException {
		
		String studentId = getString("studentId");
		List<StudentTest> list = new ArrayList<StudentTest>();
		int size = 1;
		try {
			list = studentTestService.selectStudentTestByStudentId(studentId);
			
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetStudentTestException.getInfo(), e);
			to500(ControllerExceptionEnum.GetStudentTestException.getInfo(), ViewUriConstant.ERROR500);
		}
		PageBean pageBean = new PageBean(size, list);
		sendJson(pageBean);
	}

	/**
	 * 
	* @Title: dispatchGetExamination
	* @Description: 跳转到前台显示试卷具体内容的页面(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.DISPATCH_GET_EXAMINATION)
	public void dispatchGetExamination() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_FRONT_EXAMINATION);
	}

	/**
	 * 
	* @Title: ajaxGetExaminationOrTestQuestion
	* @Description: ajax请求-显示试卷可见的大题(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_EXAMINATION_OR_TEST_QUESTIONS)
	public void ajaxGetExaminationOrTestQuestion() throws ServletException, IOException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		String examinationId = getString("examinationId");
		try {
			list = paramQuestionsService.selectQuestionsByExam(examinationId);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetExaminationOrTestQuestionException.getInfo(), e);
			to500(ControllerExceptionEnum.GetExaminationOrTestQuestionException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("getQuestions", list);
		sendJson();
	}
    
	
	
	
	
	
	
	/**
	 * 
	* @Title: dispatchAddExamination
	* @Description: 跳转到前台添加试卷页面(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.DISPATCH_ADD_STUDENT_TEST)
	public void dispatchAddExamination() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.ADD_FRONT_EXAMINATION);
	}

	
	/**
	 * 
	* @Title: ajaxGetQuestionType
	* @Description: ajax-查询所有试题题型(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_QUESTION_TYPE)
	public void ajaxGetQuestionType() throws ServletException, IOException {
		List<ParamQuestionsType> list = new ArrayList<ParamQuestionsType>();
		try {
			list = paramQuestionsTypeService.selectType();
			
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetStudentTestException.getInfo(), e);
			to500(ControllerExceptionEnum.GetStudentTestException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("getType", list);
		sendJson();
	}
	
	
	/**
	 * 
	* @Title: ajaxGetQuestions
	* @Description: ajax-根据试题题型查询可见大题(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_QUESTIONS)
	public void ajaxGetQuestions() throws ServletException, IOException {
		List<ParamQuestionsVo> list = new ArrayList<ParamQuestionsVo>();
		String typeId = getString("typeId");
		try {
			list = paramQuestionsService.selectQuestionsByType(typeId);
			
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetStudentTestException.getInfo(), e);
			to500(ControllerExceptionEnum.GetStudentTestException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("getType", list);
		sendJson();
	}
	
	
	/**
	 * 
	* @Title: addStudentTest
	* @Description: 添加一个学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value=RequestConstant.ADD_STUDENT_TEST)
	public void addStudentTest() throws ServletException , IOException {
		StudentTest studentTest = new StudentTest();
		studentTest = getBean(StudentTest.class);
		studentTest.setStudentTestTime(new Date());
		try {
			studentTestService.addStudentTest(studentTest);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.AddStudentTestException.getInfo(),e);
			to500(ControllerExceptionEnum.AddStudentTestException.getInfo(), ViewUriConstant.ERROR500);
		}
		requestDispatcher(ViewUriConstant.EXAMINATION_FRONT_PAGE);
	}

	
	
	/**
	 * 
	* @Title: dispatchStudentDone
	* @Description: 跳转到学生已完成的试卷部分(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.DISPATCH_FRONT_STUDENT_DONE)
	public void dispatchStudentDone() throws ServletException, IOException {
		requestDispatcher(ViewUriConstant.STUDENG_DONE);
	}
	/**
	 * 
	* @Title: ajaxGetStudentDoneExamination
	* @Description: ajax-查询学生已完成的试卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @throws ServletException
	* @param @throws IOException    设定文件
	* @return void    返回类型
	* @throws
	 */
	@RequestMapping(value = RequestConstant.AJAX_GET_STUDENT_DONE_EXAMINATION)
	public void ajaxGetStudentDoneExamination() throws ServletException, IOException {
		List<StudentDone> list = new ArrayList<StudentDone>();
		String studentId = getString("studentId");
		try {
			list = studentDoneService.selectExaminationOrTestId(studentId);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.GetStudentDoneExaminationException.getInfo(), e);
			to500(ControllerExceptionEnum.GetStudentTestException.getInfo(), ViewUriConstant.ERROR500);
		}
		putJson("getStudentDoneExamination", list);
		sendJson();
	}
			
		
	
	
	
	
	
	
	
	
	
	
	
}
