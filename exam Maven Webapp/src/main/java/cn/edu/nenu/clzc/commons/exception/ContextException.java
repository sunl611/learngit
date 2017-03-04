/**
 * @Title: ContextException.java
 * @Package cn.edu.nenu.www.funs.exception
 * @Description: TODO(用一句话描述该文件做什么)
 * @author qiuxiao
 * @date 2016年7月31日
 * @version V1.0
 */
package cn.edu.nenu.clzc.commons.exception;


/*
 * @类功能说明：定义一个整个应用的Exception
 * @公司名称：蓝旭工作室
 * @作者：qiuxiao
 * @创建时间：2016年7月31日 上午11:10:56
 * @版本：V1.0 
 */

public class ContextException extends Exception {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable cause;

	public ContextException() {
	}

	public ContextException(String message) {
		this.message = message;
	}
	
	public ContextException(Throwable cause) {
		this.cause = cause;
	}

	public ContextException(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}

	
	@Override
	public String toString() {
		return "ContextException message : " + getMessage() + "\t\n"
				+ super.toString();
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
