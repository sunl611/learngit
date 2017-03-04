package cn.edu.nenu.clzc.service.test.examination;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.examination.ParamQuestionsType;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.service.examination.ParamQuestionsTypeService;

public class ParamQuestionsTypeServiceTest extends BaseTest {
	
	ParamQuestionsTypeService paramQuestionsTypeService = new ParamQuestionsTypeService();
	
	@Test
	public void testAddQuestionsType() throws ContextException {
		ParamQuestionsType test = new ParamQuestionsType();
		test.setQuestionsObjectiveOrSubjective("0");
		test.setQuestionsTypeInfo("类型说明");
		test.setQuestionsTypeName("大题类型");
		paramQuestionsTypeService.addQuestionsType(test);
	}

	@Test
	public void testDeleteQuestionsType() throws ContextException {
		String id = "4ee9ba97-1ce2-4e03-8ca9-8d7b3e993407";
		String questionsTypeIsDelete = "1";
		paramQuestionsTypeService.deleteQuestionsType(id, questionsTypeIsDelete);
	}

	@Test
	public void testSelectType() throws ContextException {
		List<ParamQuestionsType> list = new ArrayList<ParamQuestionsType>();
		list = paramQuestionsTypeService.selectType();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getQuestionsTypeName());
		}
	}

	@Test
	public void testSelectAllType() throws ContextException {
		List<ParamQuestionsType> list = new ArrayList<ParamQuestionsType>();
		list = paramQuestionsTypeService.selectAllType();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getQuestionsTypeName());
		}
	}

}
