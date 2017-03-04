package cn.edu.nenu.clzc.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;


@WebServlet(value=RequestConstant.UNAUTHORIZED_CONTROLLER)
public class UnauthorizedController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*Login get请求就直接转发到登录页面*/
		req.getRequestDispatcher(ViewUriConstant.LOGIN);
		
	}

}
