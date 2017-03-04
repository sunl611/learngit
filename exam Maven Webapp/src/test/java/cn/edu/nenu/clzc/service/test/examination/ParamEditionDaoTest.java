package cn.edu.nenu.clzc.service.test.examination;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.examination.ParamEdition;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.examination.ParamEditionVo;
import cn.edu.nenu.clzc.service.examination.ParamEditionService;

public class ParamEditionDaoTest extends BaseTest {

	ParamEditionService paramEditionService = new ParamEditionService();
    
	@Test
	public void testAddEdition() throws ContextException {
		ParamEdition ParamEdition = new ParamEdition();
		ParamEdition.setEditionName("六六大顺的顺序");
		ParamEdition.setEditionInfo("第六册");
		ParamEdition.setEditionTime(new Date());
		paramEditionService.addEdition(ParamEdition);
		System.out.println("addsuccess");
		
	}
    
	@Test
	public void testUpdateEdition() throws ContextException {
		System.out.println(paramEditionService.updateEditiion("3", "1"));
	}
    
	@Test
	public void testselectAllEdition() throws ContextException {
		List<ParamEditionVo> list = paramEditionService.selectAllEdition();
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getEditionInfo());
		

		
	}

}
}
