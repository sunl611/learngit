package cn.edu.nenu.clzc.service.test.examination;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.entites.examination.ParamUnit;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.examination.ParamUnitVo;
import cn.edu.nenu.clzc.service.examination.ParamUnitService;

public class ParamUnitDaoTest extends BaseTest {
 
	ParamUnitService ParamUnitService =new ParamUnitService();
	
	@Test
	public void testAddUnit() throws ContextException  {
		ParamUnit ParamUnit = new ParamUnit();
		ParamUnit.setUnitName("第四单元");
		ParamUnit.setUnitInfo("44");
		ParamUnit.setEditionId("2");
		ParamUnit.setUnitTime(new Date());
		
		ParamUnitService.addUnit(ParamUnit);
		System.out.println("success");
	}

	@Test
	public void testUpdateUNit() throws ContextException {
		ParamUnitService.updateUnit("1", "1");
		System.out.println("Updatasuccess");
	}

	@Test
	public void testSelectUnitByEdition() throws ContextException{
	
		List<ParamUnitVo> list =ParamUnitService.selectUnitByEdition("1",2);
		for(int i = 0; i < list.size();i++){
			System.out.println(list.get(i).getUnitName());
			
		}
		
	
	}
	
	
	@Test
	public void testSelectAllPage() throws ContextException{
		int i =0;
		i = ParamUnitService.selectAllPage();
		System.out.println(i);
		
		
	}
	@Test
	public void testSelectFirstUnitPage() throws ContextException{
		int i= 0;
		i = ParamUnitService.selectFirstUnitPage();
		System.out.println(i);
		
	}
	
	
}


