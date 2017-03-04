package cn.edu.nenu.clzc.commons.constant;

import cn.edu.nenu.clzc.commons.core.SystemConstant;

public class ViewUriConstant extends SystemConstant{
	/*jsp页面前缀，在AbstractController中被使用是所以必须被正确指定*/
	public static final String BASE = "/WEB-INF/view";
	
	
	/*系统部分开始*/
	
	/*登录页面*/
	public static final String LOGIN = "/login.jsp";
	/*500页面*/
	public static final String  ERROR500= "/500.jsp";
	/*404页面*/
	public static final String  ERROR404= "/404.jsp";
	/*登录后的主页*/
	public static final String INDEX = "/index.jsp";
	/*未授权*/
	public static final String UNAUTHORIZED = "/unauthorized.jsp";


	public static final String ADD_PERMISSION = "/admin/permission/add.jsp";


	public static final String PERMISSION_LIST = "/admin/permission/list.jsp";


	public static final String TO_PERMISSION_LIST = "/bg/admin/permission?to=to-list";
	
	/*系统部分结束*/
	
	
	
	/*留言部分开始*/
	
	/*前段留言页面*/
	public static final String FRONT_PAGE = "/message/frontpage.jsp";
	/*增加留言的页面*/
	public static final String ADD_MESSAGE = "/message/addmessage.jsp";
	/*后端留言页面*/
	public static final String BACK_PAGE = "/message/backpage.jsp";
	/*后端的模糊查询结果页面*/
	public static final String SELECT_BY_NAME = "message/selectname.jsp";
	

	/*留言部分结束*/
	
	/*试卷后端部分开始*/
	
	public static final String BACK_EDITION = "back_edition";

	public static final String BACK_EXAMINATION = "back_examintion";
	
	public static final String BACK_QUESTIONS = "back_questions";
	
	public static final String ADD_EXAMINATION = "add_examination";

	public static final String ADD_EDITION = "add_edition";

	public static final String ADD_UNIT = "add_unit";
	
	public static final String STUDENT_DONE = "student_done";
	
	public static final String ADD_QUESTIONS = "add_questions";
	
	/*试卷部分开始*/
	/*前端试卷页面*/
	public static final String EXAMINATION_FRONT_PAGE = "/examination/exinationfrontpage.jsp";

    public static final String ADD_FRONT_EXAMINATION = "/examination/addExamination.jsp";




	public static final String STUDENG_DONE = "/examination/studentDone.jsp";













}
