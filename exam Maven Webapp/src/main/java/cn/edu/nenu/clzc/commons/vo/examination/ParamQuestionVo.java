package cn.edu.nenu.clzc.commons.vo.examination;

import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestion;

/**
 * 
 * @author 我要睡觉了
 * @Title ParamQuestionVo.java
 * @Description 小题的扩展类，扩展出选择类型的题干
 * @time 2016年12月10日 上午12:53:38
 */

public class ParamQuestionVo extends ParamQuestion {

	String[] outline;

	public String[] getOutline() {
		return outline;
	}

	public void setOutline(String[] outline) {
		this.outline = outline;
	}
	
}
