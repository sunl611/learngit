package cn.edu.nenu.clzc.commons.extend;

import org.apache.log4j.Logger;
import cn.edu.nenu.clzc.dao.examination.ParamEditionDao;

import cn.edu.nenu.clzc.dao.examination.ParamUnitDao;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestion;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestionsType;
import cn.edu.nenu.clzc.commons.entites.student.SubjectiveQuestion;
import cn.edu.nenu.clzc.dao.examination.ObjectiveQuestionDao;
import cn.edu.nenu.clzc.dao.examination.ParamEditionDao;
import cn.edu.nenu.clzc.dao.examination.ParamQuestionDao;
import cn.edu.nenu.clzc.dao.examination.ParamQuestionsArticleDao;
import cn.edu.nenu.clzc.dao.examination.ParamQuestionsDao;
import cn.edu.nenu.clzc.dao.examination.ParamQuestionsTypeDao;

import cn.edu.nenu.clzc.dao.examination.SubjectiveQuestionDao;

import cn.edu.nenu.clzc.dao.examination.ParamUnitDao;

import cn.edu.nenu.clzc.dao.examination.TeacherExaminationDao;
import cn.edu.nenu.clzc.dao.message.UserMessageDao;
import cn.edu.nenu.clzc.dao.student.StudentDoneDao;
import cn.edu.nenu.clzc.dao.student.StudentTestDao;
import cn.edu.nenu.clzc.dao.system.SysPermissionDao;
import cn.edu.nenu.clzc.dao.system.SysRoleDao;
import cn.edu.nenu.clzc.dao.system.SysRolePermissionDao;
import cn.edu.nenu.clzc.dao.system.SysUserDao;
import cn.edu.nenu.clzc.dao.system.SysUserRoleDao;

public class ServiceExtend{
	protected Logger logger=Logger.getLogger(this.getClass());
	
	/* 系统模块Dao层开始 */
	
	
	
	protected static SysPermissionDao sysPermissionDao = new SysPermissionDao(); 
	
	protected static SysUserRoleDao sysUserRoleDao = new SysUserRoleDao(); 
	
	protected static SysRolePermissionDao sysRolePermissionDao = new SysRolePermissionDao(); 

	protected static SysRoleDao sysRoleDao = new SysRoleDao(); 
	
	protected static SysUserDao sysUserDao = new SysUserDao(); 
	/* 系统模块Dao层定义结束 */
	
	
	
	/* 留言板模块Dao层定义开始 */
	
	protected static UserMessageDao userMessageDao = new UserMessageDao();
	
	/* 留言板模块Dao层定义结束 */
	
	/*试卷操作模块Dao层定义开始*/

	protected static ParamEditionDao paramEditionDao = new ParamEditionDao();

	protected static ParamUnitDao ParamUnitDao= new ParamUnitDao();

	protected static ParamQuestionDao paramQuestionDao = new ParamQuestionDao();
	
	protected static ParamQuestionsTypeDao paramQuestionsTypeDao = new ParamQuestionsTypeDao();

	
	protected static TeacherExaminationDao teacherExaminationDao = new TeacherExaminationDao();

	
	protected static StudentTestDao studentTestDao = new StudentTestDao();
	
	protected static StudentDoneDao studentDoneDao = new StudentDoneDao(); 

	
	protected static ParamQuestionsArticleDao paramQuestionsArticleDao = new ParamQuestionsArticleDao();
		
	protected static ParamQuestionsDao paramQuestionsDao = new ParamQuestionsDao();
		
	protected static ObjectiveQuestionDao objectiveQuestionDao = new ObjectiveQuestionDao();

	protected static SubjectiveQuestionDao subjectiveQuestionDao = new SubjectiveQuestionDao();
	/*试卷操作模块Dao层定义结束*/
	
	
	
}
