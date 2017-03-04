package cn.edu.nenu.clzc.commons.vo.examination;

import java.util.List;

import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestion;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestions;

/**
 * 
 * @author 我要睡觉了
 * @Title ParamQuestionsVo.java
 * @Description 大题的扩展类，扩展出小题list和大题的文章
 * @time 2016年12月10日 上午12:50:16
 */

public class ParamQuestionsVo extends ParamQuestions {

	List<ParamQuestionVo> questionlist;

	String questionsArticle;
	
	String questionsArticleInfo;
	
	public String getQuestionsArticleInfo() {
		return questionsArticleInfo;
	}

	public void setQuestionsArticleInfo(String questionsArticleInfo) {
		this.questionsArticleInfo = questionsArticleInfo;
	}

	public String getQuestionsArticle() {
		return questionsArticle;
	}

	public void setQuestionsArticle(String questionsArticle) {
		this.questionsArticle = questionsArticle;
	}

	public List<ParamQuestionVo> getQuestionlist() {
		return questionlist;
	}

	public void setQuestionlist(List<ParamQuestionVo> questionlist) {
		this.questionlist = questionlist;
	}
	
}
