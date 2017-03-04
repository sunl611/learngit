package cn.edu.nenu.clzc.service.test.examination;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.examination.ParamQuestionVo;
import cn.edu.nenu.clzc.commons.vo.examination.ParamQuestionsVo;
import cn.edu.nenu.clzc.service.examination.ParamQuestionsService;

public class ParamQuestionsServiceTest extends BaseTest {

	ParamQuestionsService paramQuestionsService = new ParamQuestionsService();
	
	@Test
	public void testAddQuestions() throws ContextException {
		List<ParamQuestionVo> questionlist = new ArrayList<ParamQuestionVo>();
		String[] questionOutline = {"tt#aa#bb#cc#dd", "tt1#aa1#bb1#cc1#dd1", "tt2#aa2#bb2#cc2#dd2"};
		String[] questionNumber = {"kk", "kk2", "kk3"};
		String[] questionAnalysis = {"解析", "解析1", "解析2"};
		String[] questionAnswer = {"答案", "答案1", "答案2"};
		Double[] questionMark = {2.0, 2.0, 2.0};			
		for(int i = 0; i < 3; i++) {
			ParamQuestionVo paramQuestionVo = new ParamQuestionVo();
			paramQuestionVo.setQuestionOutline(questionOutline[i]);
			paramQuestionVo.setQuestionNumber(questionNumber[i]);
			paramQuestionVo.setQuestionAnalysis(questionAnalysis[i]);
			paramQuestionVo.setQuestionAnswer(questionAnswer[i]);
			paramQuestionVo.setQuestionMark(questionMark[i]);
			questionlist.add(paramQuestionVo);
		}
		ParamQuestionsVo paramQuestionsVo = new ParamQuestionsVo();
		paramQuestionsVo.setExaminationId("1");
		paramQuestionsVo.setQuestionsNumber("大题编号");
		paramQuestionsVo.setQuestionsTitle("大题题目");
		paramQuestionsVo.setQuestionsInfo("大题描述");
		paramQuestionsVo.setQuestionsTypeId("类型id");
		paramQuestionsVo.setQuestionsArticle("大题文章");
		paramQuestionsVo.setQuestionsArticleInfo("大题文章描述");
		paramQuestionsService.addQuestions(paramQuestionsVo, questionlist);
	}

	@Test
	public void testDeleteQuestions() throws ContextException {
		String id = "748e83b9-0a2e-4af2-ba36-a32af360c421";
		paramQuestionsService.deleteQuestions(id, "0");
	}

	@Test
	public void testSelectQuestionsByExam() throws ContextException {
		String examinationId = "1";
		List<ParamQuestionsVo> list = paramQuestionsService.selectQuestionsByExam(examinationId);
		System.out.println(list.get(0).getId());
		List<ParamQuestionVo> questionlist = list.get(0).getQuestionlist();
		for(int i = 0; i < questionlist.size(); i++) {
			String[] outline = questionlist.get(i).getOutline();		
			for(int j = 0; j < outline.length; j++) {
				System.out.println(outline[j]);
			}
		}
		
		System.out.println(list.get(0).getQuestionsArticle());
	}

	@Test
	public void testSelectAllQuestionsByExam() throws ContextException {
		String examinationId = "1";
		List<ParamQuestionsVo> list = paramQuestionsService.selectAllQuestionsByExam(examinationId);
		System.out.println(list.get(0).getId());
		List<ParamQuestionVo> questionlist = list.get(0).getQuestionlist();
		for(int i = 0; i < questionlist.size(); i++) {
			String[] outline = questionlist.get(i).getOutline();		
			for(int j = 0; j < outline.length; j++) {
				System.out.println(outline[j]);
			}
		}
		
		System.out.println(list.get(0).getQuestionsArticle());
	}

	@Test
	public void testSelectQuestionsByType() throws ContextException {
		String typeId = "类型id";
		List<ParamQuestionsVo> list = paramQuestionsService.selectQuestionsByType(typeId);
		System.out.println(list.get(0).getId());
		List<ParamQuestionVo> questionlist = list.get(0).getQuestionlist();
		for(int i = 0; i < questionlist.size(); i++) {
			String[] outline = questionlist.get(i).getOutline();		
			for(int j = 0; j < outline.length; j++) {
				System.out.println(outline[j]);
			}
		}
		
		System.out.println(list.get(0).getQuestionsArticle());
	}

	@Test
	public void testSelectAllQuestionsByType() throws ContextException {
		String typeId = "类型id";
		List<ParamQuestionsVo> list = paramQuestionsService.selectAllQuestionsByType(typeId);
		System.out.println(list.get(0).getId());
		List<ParamQuestionVo> questionlist = list.get(0).getQuestionlist();
		for(int i = 0; i < questionlist.size(); i++) {
			String[] outline = questionlist.get(i).getOutline();		
			for(int j = 0; j < outline.length; j++) {
				System.out.println(outline[j]);
			}
		}
		
		System.out.println(list.get(0).getQuestionsArticle());

	}

	
}
