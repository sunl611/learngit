package cn.edu.nenu.clzc.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nenu.clzc.commons.constant.FilterConstant;
import cn.edu.nenu.clzc.commons.constant.ViewUriConstant;
import cn.edu.nenu.clzc.commons.core.SystemConstant;

@WebFilter(value={SystemConstant.FILTER_JSP,FilterConstant.FILTER_ALL_CONTROLLER})
public class Response404Filter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		
		String requestURI = httpServletRequest.getRequestURI();
		
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		
		int status = httpServletResponse.getStatus();
		
		if(status == 404){
			String location=httpServletRequest.getServletContext().getContextPath()+ViewUriConstant.BASE+ViewUriConstant.ERROR404;
			location += location+"?"+"uri="+requestURI;
			
			httpServletResponse.sendRedirect(location);
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
	}

}
