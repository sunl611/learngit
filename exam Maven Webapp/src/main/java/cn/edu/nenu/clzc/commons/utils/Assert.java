package cn.edu.nenu.clzc.commons.utils;

import java.util.Collection;

public class Assert {

	public static boolean isEmpty(String string) {
		if (string == null || "".equals(string)) 
			return true;

		return false;
	}
	
	public static boolean isNotNull(Object obj) {
		if (obj != null) 
			return true;
		
		return false;
	}
	public static boolean isNull(Object obj) {
		if (obj == null) 
			return true;
		
		return false;
	}
	public static boolean biggerThanZero(Integer integer) {
		if (integer ==null )
			throw new  NullPointerException("被比较的Integer不能为空！");
		
		return integer>0;
	}
	/**
	 * 校验uri中是否contain了args数组里面的某一个字符串，包含返回true
	 * @param uri
	 * @param args
	 * @return
	 */
	public static boolean contains(String uri,String[] args){
		boolean contailns=false;
		String[] notBlockedArray=args;
		for (int i = 0; i < notBlockedArray.length; i++) {
			String suffix=notBlockedArray[i];
			if (uri.contains(suffix))
				contailns = true;
			
		}
		return contailns;
	}
	/**
	 * 校验数组的长度是否不为0，前提是集合不为null，否则将抛出异常
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array){
		if(array == null)
			throw new NullPointerException("被校验的数组不能为Null！");
		
		return array.length>0;
	}
	/**
	 * 校验集合的大小是否不为0，前提是集合不为null，否则将抛出异常
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		if (collection == null) 
			throw new NullPointerException("被校验的集合对象不能为NULL！");
			
		return collection.size()>0;
	}
	
}
