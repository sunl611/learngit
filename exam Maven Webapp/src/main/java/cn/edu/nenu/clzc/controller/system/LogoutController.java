package cn.edu.nenu.clzc.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;


@WebServlet(value=RequestConstant.LOGOUT_CONTROLLER)
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		/*退出登录*/
		subject.logout();
		resp.sendRedirect(this.getServletContext().getContextPath()+ViewUriConstant.BASE+ViewUriConstant.LOGIN);
		
	}

}
