package cn.edu.nenu.clzc.controller.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.edu.nenu.clzc.commons.core.annotation.RequestMapping;
import cn.edu.nenu.clzc.commons.core.annotation.ResponseBody;
import cn.edu.nenu.clzc.commons.entites.system.SysUser;
import cn.edu.nenu.clzc.commons.extend.ControllerExtend;
@WebServlet(value={RequestConstant.EXAMPLE_CONTROLLER})
public class ExampleController extends ControllerExtend {

	 /** * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value=RequestConstant.EXAMPLE_CONTROLLER_MAPPING,method=SystemConstant.GET)
	public void mapping(){
		out.print("success!");
		
	}
	
	@RequestMapping(value="example.php",method=SystemConstant.GET)
	public void dispatchExample() throws ServletException, IOException{
		requestDispatcher("/example/example.jsp");
		
	}
	@RequestMapping(value="fileupload.php",method=SystemConstant.GET)
	public void dispatchFileUpload() throws ServletException, IOException{
		requestDispatcher("/example/fileUpload.jsp");
	}
	@RequestMapping(value="parameter",method=SystemConstant.POST)
	public void testParameter() throws ServletException{
		Integer age = getInt("age");
		Integer hight = getIntDefault("hight",180);
		
		String userName = getString("userShowName");
		
		String hobby=getStringDefault("hobby", "basketball");
		SysUser bean = getBean(SysUser.class);
		
		Double agedouble1 = getDouble("age");
		Double agedoubleDefault = getDoubleDefault("age", 20.0);
		
		out.println(age);
		out.println(hight);
		out.println(userName);
		out.println(hobby);
		out.println("bean : "+bean.toString());
	}
	
	@RequestMapping(value="fileupload",method=SystemConstant.POST)
	public void fileupload() throws ServletException{
		String filePath = getFilePath("file0");
		
		out.print(filePath);
	}
	@RequestMapping(value="json",method=SystemConstant.GET)
	@ResponseBody
	public void testJson(){
		List<SysUser> list=new ArrayList<SysUser>();
		for (int i = 0; i < 10; i++) {
			SysUser user=new SysUser();
			user.setPassword("password"+i);
			user.setUsername("username"+i);

//			user.setUserEmail("useremail"+i);


			user.setUserImage("userimage"+i);
			user.setUserInfo("userinfo"+i);
			user.setUserIsDelete("userisdelete"+i);
			user.setUserPhone("userphone"+i);
			list.add(user);
		}
		putJson("test", "test-value");
		putJson("list", list);
		sendJson();
	}
	@RequestMapping(value="to500",method="GET")
	public void testToo500() throws ServletException, IOException{
		try {
			System.out.println("");
			throw new NullPointerException();
		} catch (Exception e) {
			logger.error("error!");
			to500("空指针了",ViewUriConstant.ERROR500);
		}
	}
	@RequestMapping(value="to-json",method="GET")
	public void dispatchJson() throws ServletException, IOException{
		requestDispatcher("/example/testjson.jsp");
	}
	
	@RequestMapping(value="test",method="GET")
	public void test1(){
		
	}
	
	@RequestMapping(value="ueditor",method="GET")
	public void testUeditor() throws ServletException, IOException{
		System.out.println("");
		requestDispatcher("/example/demo.jsp");
	}
	
	
}
