package cn.edu.nenu.clzc.service.test.system;

import java.util.Date;

import org.junit.Test;

import cn.edu.nenu.clzc.commons.core.BaseTest;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.system.SysPermissionVo;
import cn.edu.nenu.clzc.service.system.SysPermissionService;

public class SysPermissionServiceTest extends BaseTest {

	SysPermissionService service =new SysPermissionService();

	@Test
	public void testInsert() throws ContextException {
		SysPermissionVo sysPermissionVo=new SysPermissionVo();
		sysPermissionVo.setPermissionValue("user:insert");
		sysPermissionVo.setPermissionTime(new Date());
		sysPermissionVo.setPermissionInfo("qiuxiao insert");
		
		boolean insert = service.insert(sysPermissionVo);
		System.out.println(insert);
		
	}

}
