package cn.edu.nenu.clzc.commons.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import cn.itcast.jdbc.JdbcUtils;
import funs.ConnectionContext;

public class BaseTest {
	Logger logger = Logger.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {
		Connection connection = null;
		connection = JdbcUtils.getConnection();
		connection.setAutoCommit(false);
		// 1、获取数据库连接对象Connection
		connection = JdbcUtils.getConnection();
		// 2、开启事务
		connection.setAutoCommit(false);
		logger.info("transaction is begin!");
		// 3、绑定Connction到当前线程
		ConnectionContext.getInstance().bind(connection);
		logger.info("Connectin is binded!");
	}

	@After
	public void setAfter() throws SQLException {
		// 5、提交事务
		Connection connection = null;
		connection = ConnectionContext.getInstance().getConnection();
		try {
			connection.commit();
			logger.info("tracsaction is commit!");
		} catch (SQLException e) {
			logger.info("tracsaction is rollback!");
			connection.rollback();
		}
	}
}
