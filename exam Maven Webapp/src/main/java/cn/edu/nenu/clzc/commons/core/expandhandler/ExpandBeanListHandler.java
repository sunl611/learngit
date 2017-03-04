package cn.edu.nenu.clzc.commons.core.expandhandler;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.expand.ExpandBeanProcessor;
import org.apache.commons.dbutils.expand.HumpMatcher;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ExpandBeanListHandler<T> extends BeanListHandler<T>{
	/*驼峰命名匹配器*/
	private static  final RowProcessor CONVERT=new BasicRowProcessor(new ExpandBeanProcessor(new HumpMatcher()));
	
	
	public ExpandBeanListHandler(Class<T> type) {
		super(type,CONVERT);
	}

}
