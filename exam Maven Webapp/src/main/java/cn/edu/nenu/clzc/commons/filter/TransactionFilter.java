package cn.edu.nenu.clzc.commons.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.nenu.clzc.commons.constant.ERRORConstant;
import cn.edu.nenu.clzc.commons.constant.FilterConstant;
import cn.itcast.jdbc.JdbcUtils;
import funs.ConnectionContext;

/**
 * ThreadLocal + Filter 统一处理数据库事务
 * @author mrj
 *
 */
@WebFilter(urlPatterns = { FilterConstant.FILTER_ALL_CONTROLLER })
public class TransactionFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException { } 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		Logger logger=Logger.getLogger(this.getClass());
		
		
		Connection connection=null;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try {
			// 1、获取数据库连接对象Connection
			connection = JdbcUtils.getConnection();
			// 2、开启事务
			connection.setAutoCommit(false);
			logger.info("transaction is begin!");
			// 3、绑定Connction到当前线程
			ConnectionContext.getInstance().bind(connection);
			logger.info("Connectin is binded!");
			// 4、把请求转发给目标Servlet
			chain.doFilter(request, response);
			// 5、提交事务
			connection.commit();
			logger.info("tracsaction is commit!");
		} catch (Exception e) {
			// 6、回滚事务
			try {
				connection.rollback();
				logger.info(" connection is rollback!");
			} catch (SQLException e1) {
				logger.error("TransactionFilter Connection Failed！", e1);
			}
			try {
				httpServletResponse.sendRedirect(ERRORConstant.ERROR500);
			} catch (IOException e1) {
				logger.error("TransactionFilter sendRedirect failed！", e1);
			}
		}finally{
			try {
				if (connection!=null) {
					connection.close();
					connection=null;
				}
			} catch (SQLException e) {
				logger.error("TransactionFilter connection close failed！", e);
			}
		}
	}

	public void destroy() { } 
}