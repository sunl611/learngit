/**
* @Title: AbstractService.java
* @Package cn.edu.nenu.www.funs.service
* @Description: TODO(用一句话描述该文件做什么)
* @author qiuxiao
* @date 2016年7月31日
* @version V1.0
*/
package cn.edu.nenu.clzc.commons.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.expand.ExpandBeanProcessor;
import org.apache.commons.dbutils.expand.HumpMatcher;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.itcast.jdbc.TxQueryRunner;
import funs.ConnectionContext;

/*
 * @类功能说明：
 * @公司名称：蓝旭工作室
 * @作者：qiuxiao
 * @创建时间：2016年7月31日 下午11:47:50
 * @版本：V1.0 
 */

public  abstract class AbstractDao {
	
	
	protected Logger logger=Logger.getLogger(this.getClass());
	
	public static QueryRunner qr=new TxQueryRunner();
	
	protected static final Integer PAGESIZE=15;
	
	//利用驼峰命名处理sql语句中的属性与javabean的匹配数据库aa_bb 匹配实体aaBb
	protected static  final RowProcessor CONVERT=new BasicRowProcessor(new ExpandBeanProcessor(new HumpMatcher()));
	
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws ContextException
	 */
	public int update(String sql, Object[] params) throws ContextException{
		Integer updateColumn=null;
		try {
			updateColumn = qr.update(sql, params);
		} catch (SQLException e) {
			throw new ContextException("更新结果集失败！",e);
		}
		return updateColumn;
	}
	/**
	 * INSERT INTO TABLENAME
	 * @param sql
	 * @param params
	 * @return
	 * @throws ContextException
	 * @throws SQLException
	 */
	public  String insert(String sql, Object[] params) throws ContextException, SQLException{
		
		Connection connection = ConnectionContext.getInstance().getConnection();
		String randomUUID = primaryKey(sql,connection);
		sql = updateSql(sql);
		/*构造参数*/
		Object[] paramsAddId = new Object[params.length+1];
		
		paramsAddId[0] = randomUUID;
		for (int i = 0; i < params.length; i++) {
			paramsAddId[i+1]=params[i];
		}
		try {
			qr.update(sql, paramsAddId);
		} catch (SQLException e) {
			throw new ContextException("插入结果集失败！",e);
		}
		return randomUUID;
	}
	/**
	 * 插入数据并且返回数据主键。
	 * @param sql
	 * @param param
	 * @return
	 * @throws ContextException
	 * @throws SQLException 
	 */
	public synchronized String insert(String sql, Object param) throws ContextException, SQLException{
		
		Connection connection = ConnectionContext.getInstance().getConnection();
		String randomUUID = primaryKey(sql,connection);
		
		sql = updateSql(sql);
		/* 构造参数 */
		Object[] paramsAddId = new Object[2];
		
		paramsAddId[0] = randomUUID;
		paramsAddId[1] = param;
		try {
			qr.update(sql, paramsAddId);
		} catch (SQLException e) {
			throw new ContextException("插入结果集失败！",e);
		}
		return randomUUID;
	}
	
	/**
	 * 
	 * @param sql
	 * @param connection
	 * @return
	 * @throws ContextException
	 * @throws SQLException
	 */
	private  String primaryKey(String sql,Connection connection) throws ContextException, SQLException{
		sql=sql.toLowerCase();
		String randomUUID = null;
		if (sql!=null&&sql.contains("insert")&&sql.contains("into")) {
			String tmp=null;
			tmp=sql.replace("insert", "");
			tmp=tmp.replace("into", "").trim();
			String trimSql = tmp;
			
			int leftBracket = tmp.indexOf('(');
			String table=trimSql.substring(0,leftBracket).trim();
			
			while (true) {
				randomUUID=UUID.randomUUID().toString();
				String queryByIdSql="SELECT id FROM "+table+ " WHERE id=?";
				String id = qr.query(connection,queryByIdSql, new ScalarHandler<String>(),randomUUID);
				if (id!=null)
					continue;
				else 
					break;
			}
		}
	return randomUUID;	
	}
	/**
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @param @throws ContextException    参数
	 * @return int    返回类型
	 * @throws
	 */
	public  int update(String sql, Object param) throws ContextException{
		Integer updateColumn=null;
		try {
			Connection connection = ConnectionContext.getInstance().getConnection();
			updateColumn = qr.update(connection,sql, param);
		} catch (SQLException e) {
			throw new ContextException("更新结果集失败！",e);
		}
		return updateColumn;
	}
	/**
	 * 不带参数查询结果集
	* @Title: query
	* @Description: TODO
	* @param @param sql
	* @param @param rsh
	* @param @return
	* @param @throws ContextException    参数
	* @return T    返回类型
	* @throws
	 */
	public <T> T query(String sql,ResultSetHandler<T> rsh) throws ContextException{
		T t=null;
		try {
			Connection connection = ConnectionContext.getInstance().getConnection();
			t=qr.query(connection,sql, rsh);
		} catch (SQLException e) {
			throw new ContextException("查询结果集失败！",e);
		}
		return t;
	}
	/**
	 * 带参数查询结果集
	* @Title: query
	* @Description: TODO
	* @param @param sql
	* @param @param rsh
	* @param @param params
	* @param @return
	* @param @throws ContextException    参数
	* @return T    返回类型
	* @throws
	 */
	public <T> T query(String sql,ResultSetHandler<T> rsh,Object[] params) throws ContextException{
		T t=null;
		try {
			Connection connection = ConnectionContext.getInstance().getConnection();
			t=qr.query(connection,sql, rsh,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ContextException("查询结果集失败！",e);
		}
		return t;
	}
	/**
	 * 带参数查询结果集
	 * @Title: query
	 * @Description: TODO
	 * @param @param sql
	 * @param @param rsh
	 * @param @param params
	 * @param @return
	 * @param @throws ContextException    参数
	 * @return T    返回类型
	 * @throws
	 */
	public  <T> T query(String sql,ResultSetHandler<T> rsh,Object param) throws ContextException{
		T t=null;
		try {
			Connection connection = ConnectionContext.getInstance().getConnection();
			t=qr.query(connection,sql, rsh,param);
		} catch (SQLException e) {
			throw new ContextException("查询结果集失败！",e);
		}
		return t;
	}
	/**
	 * 去除sql语句拼接的时候末尾多余的AND或者WHERE
	* @Title: removeWhereAnd
	* @Description: TODO
	* @param @param sqlBuilder
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	protected String  removeWHEREOrAND(StringBuilder sqlBuilder) {
		if (sqlBuilder.lastIndexOf("WHERE")==( sqlBuilder.length()-5 )) {
			return sqlBuilder.substring(0, sqlBuilder.lastIndexOf("WHERE"));
		}else if (sqlBuilder.lastIndexOf("AND")==( sqlBuilder.length()-3 )) {
			return sqlBuilder.substring(0, sqlBuilder.lastIndexOf("AND"));
		}else {
			return sqlBuilder.toString();
		}
	}
	protected StringBuilder createClause(String clause,StringBuilder  sqlBuilder) {
		sqlBuilder.append("  "+clause);
		return sqlBuilder;
	}
	/**
	 * 在原有的sql语句中加入主键参数
	* @Title: updateSql
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param sql
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	private String updateSql(String sql) {

		if (leftBracketCount(sql) == 2) {
			/*		INSERT INTO table(a,b) VALUES(?,?)*/
			int leftBracket1 = sql.indexOf('(');

			String left = sql.substring(0, leftBracket1 + 1);
			String right = sql.substring(leftBracket1 + 1).trim();

			left = left + "id ,  " ;
			
			int rightLeftBracket = right.indexOf('(');
			
			String rightLeft = right.substring(0,rightLeftBracket+1);
			String rightRight = right.substring(rightLeftBracket+1);
			right = rightLeft + "?, " + rightRight;
			return left+right;
		}else{
			/*INSERT INTO table VALUES(?,?)*/
			int leftBracket1 = sql.indexOf('(');

			String left = sql.substring(0, leftBracket1 + 1);
			String right = sql.substring(leftBracket1 + 1).trim();

			return left+ "?, " + right;
		}
		
	}
	/**
	 * 
	* @Title: leftBracketCount
	* @Description:计算出sql语句中有几个'('
	* @param @param sql
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	private int leftBracketCount(String sql){
		char[] charArray = sql.toCharArray();
		int count =0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '(')
				count++;
			if (count > 1) 
				break;
		}
		return count;
		
	}
	
	
	
	
	// 计算页数
	protected final int size(int totalRow) {
		int size;
		if(totalRow % PAGESIZE == 0){
			size = totalRow / PAGESIZE;
		}else {
			size = totalRow / PAGESIZE + 1;
		}
		return size;
	}
}
