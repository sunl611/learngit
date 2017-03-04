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

import cn.edu.nenu.clzc.commons.core.SystemConstant;
import cn.itcast.filter.GetRequest;
@WebFilter({SystemConstant.FILTER_GLOBAL})
/**
 * 
 * @author 邱晓
 *
 */
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getMethod().equalsIgnoreCase("GET")) {
			if(!(req instanceof GetRequest)) {
				req = new GetRequest(req, SystemConstant.CHARSET);//处理get请求编码
			}
		} else {
			req.setCharacterEncoding(SystemConstant.CHARSET);//处理post请求编码
		}
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
