package cn.edu.nenu.clzc.commons.extend;

import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.AbstractServlet;
import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.edu.nenu.clzc.commons.entites.examination.ParamEdition;
import cn.edu.nenu.clzc.commons.entites.teacher.TeacherExamination;
import cn.edu.nenu.clzc.commons.vo.examination.ParamUnitVo;
import cn.edu.nenu.clzc.service.examination.ParamEditionService;

import cn.edu.nenu.clzc.service.examination.ParamQuestionsService;

import cn.edu.nenu.clzc.service.examination.ParamQuestionsService;
import cn.edu.nenu.clzc.service.examination.ParamQuestionsTypeService;

import cn.edu.nenu.clzc.service.examination.ParamUnitService;
import cn.edu.nenu.clzc.service.examination.StudentTestService;
import cn.edu.nenu.clzc.service.examination.TeacherExaminationService;
import cn.edu.nenu.clzc.service.examination.ParamEditionService;
import cn.edu.nenu.clzc.service.examination.ParamUnitService;
import cn.edu.nenu.clzc.service.examination.TeacherExaminationService;
import cn.edu.nenu.clzc.service.message.UserMessageService;
import cn.edu.nenu.clzc.service.student.StudentDoneService;
import cn.edu.nenu.clzc.service.system.SysPermissionService;

public class ControllerExtend extends AbstractServlet {
	protected Logger logger=Logger.getLogger(this.getClass());
	
	
	
	private static final long serialVersionUID = 1L;

	protected static SysPermissionService sysPermissionService =new SysPermissionService();
	
	protected static UserMessageService userMessageService = new UserMessageService();
	
	protected static ParamEditionService paramEditionService = new ParamEditionService();
	
	protected static ParamUnitService paramUnitService = new ParamUnitService();
	
	protected static TeacherExaminationService teacherExaminationService = new TeacherExaminationService();

	
	protected static ParamQuestionsService paramQuestionsService = new ParamQuestionsService();
	
	protected static StudentDoneService studentDoneService = new StudentDoneService();

	
	protected static StudentTestService studentTestService = new StudentTestService();
	
	protected static ParamQuestionsTypeService paramQuestionsTypeService = new ParamQuestionsTypeService();

 

	@Override
	protected void handleException(Exception e) {
		
		logger.error("controller inner error, ask for administrator for more help!");
		request.setAttribute("error", "controller inner error, ask for administrator for more help!");
		
		try {
			request.getRequestDispatcher(SystemConstant.JSP_BASE + ViewUriConstant.ERROR500).forward(request, response);
		} catch (ServletException | IOException e1) {
			logger.error("controller inner error, ask for administrator for more help!",e1);
		}
	}

}
