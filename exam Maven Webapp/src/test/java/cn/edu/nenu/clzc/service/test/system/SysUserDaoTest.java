//package cn.edu.nenu.clzc.service.test.system;
//
//import static org.junit.Assert.fail;
//
//import java.util.Set;
//
//import org.junit.Test;
//
//import cn.edu.nenu.clzc.commons.core.BaseTest;
//import cn.edu.nenu.clzc.commons.exception.ContextException;
//import cn.edu.nenu.clzc.commons.vo.system.SysUserVo;
//import cn.edu.nenu.clzc.service.system.SysUserService;
//
//public class SysUserDaoTest  extends BaseTest{
//	SysUserService service = new SysUserService(); 
//	
//	@Test
//	public void testGetRolesByUsername() throws ContextException {
//		Set<String> rolesByUsername = service.getRolesByUsername("qiuxiao");
//		System.out.println(rolesByUsername);
//	}
//
//	@Test
//	public void testGetPermissionsByUsername() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetUserByUsername() throws ContextException {
//		SysUserVo userByUsername = service.getUserByUsername("qiuxiao");
//		System.out.println(userByUsername);
//		
//	}
//
//}
