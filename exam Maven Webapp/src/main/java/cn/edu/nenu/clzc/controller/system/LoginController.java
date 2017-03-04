package cn.edu.nenu.clzc.controller.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

import cn.edu.nenu.clzc.commons.constant.RequestConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.utils.Assert;


@WebServlet(value=RequestConstant.LOGIN_CONTROLLER)
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Subject subject=SecurityUtils.getSubject();
		
		
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		
		String error=null;
		try {
			subject.login(token);
		} catch(UnknownAccountException e){
			logger.error(ControllerExceptionEnum.UnknownAccountException.getInfo(),e);
			error = "未知账号信息";
			
		} catch (IncorrectCredentialsException e) {
			logger.error(ControllerExceptionEnum.IncorrectCredentialsException.getInfo(),e);
			error = "密码错误";
			
		} catch (AuthenticationException e) {
			logger.error(ControllerExceptionEnum.AuthenticationException.getInfo(),e);
			error = "认证失败 + e.getMessage()";
			
		}catch (AuthorizationException e) {
			logger.error(ControllerExceptionEnum.AuthorizationException.getInfo(),e);
			error = "授权失败 + e.getMessage()";
			
		}
		if(Assert.isNotNull(error)){
			request.setAttribute("error", error);
			request.getRequestDispatcher(ViewUriConstant.LOGIN).forward(request, response);
			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("base", "/exam");
			session.setAttribute("jbase", "/exam/WEB-INF/view");
			
			request.getRequestDispatcher(ViewUriConstant.BASE + ViewUriConstant.INDEX).forward(request, response);
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*Login get璇锋眰灏辩洿鎺ヨ浆鍙戝埌鐧诲綍椤甸潰*/
		req.getRequestDispatcher(ViewUriConstant.BASE + ViewUriConstant.LOGIN).forward(req, resp);
		
	}

}
