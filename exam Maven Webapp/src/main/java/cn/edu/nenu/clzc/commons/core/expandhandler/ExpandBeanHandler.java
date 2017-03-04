package cn.edu.nenu.clzc.commons.core.expandhandler;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.expand.ExpandBeanProcessor;
import org.apache.commons.dbutils.expand.HumpMatcher;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class ExpandBeanHandler<T> extends BeanHandler<T>{
	/*驼峰命名匹配器*/
	private static  final RowProcessor CONVERT=new BasicRowProcessor(new ExpandBeanProcessor(new HumpMatcher()));
	
	
	public ExpandBeanHandler(Class<T> type) {
		super(type,CONVERT);
	}

}
