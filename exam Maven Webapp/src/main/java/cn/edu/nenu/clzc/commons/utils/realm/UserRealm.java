package cn.edu.nenu.clzc.commons.utils.realm;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.edu.nenu.clzc.commons.constant.system.SystemConstant;
import cn.edu.nenu.clzc.commons.enumeration.exception.ControllerExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.system.SysUserVo;
import cn.edu.nenu.clzc.service.system.SysUserService;

public class UserRealm extends AuthorizingRealm {
	
	private SysUserService sysUserService = new SysUserService();
	
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (
			PrincipalCollection principals) throws AuthorizationException{
		/*获取到登录名*/
		String username = (String)principals.getPrimaryPrincipal();  
		
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        try {
        	
			authorizationInfo.setRoles(sysUserService.getRolesByUsername(username));
			authorizationInfo.setStringPermissions(sysUserService.getPermissionsByUsername(username));
			
		} catch (ContextException e) {
			
			logger.error(ControllerExceptionEnum.AuthorizationFaildException.getInfo(),e);
			throw new AuthorizationException(ControllerExceptionEnum.AuthorizationFaildException.getInfo(),e);//没找到帐号
			
		}  
        
        return authorizationInfo; 
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();  
		
		SysUserVo sysUserVo = null;
		try {
			sysUserVo = sysUserService.getUserByUsername(username);
		} catch (ContextException e) {
			logger.error(ControllerExceptionEnum.UserNotFoundException.getInfo(),e);
			throw new UnknownAccountException(ControllerExceptionEnum.UserNotFoundException.getInfo(),e);//没找到帐号
		}
		if(sysUserVo == null) {  
            throw new UnknownAccountException();//没找到帐号  
        }  
        if(sysUserVo.getUserIsLock().equals(SystemConstant.USER_IS_LOCK)) {  
        	logger.error(ControllerExceptionEnum.UserLockedException.getInfo()); //帐号锁定
            throw new LockedAccountException(ControllerExceptionEnum.UserLockedException.getInfo()); //帐号锁定  
        }
        
        if (sysUserVo.getRoleAvailable().equals(SystemConstant.ROLE_IS_NOT_AVAILABLE)) {
        	logger.error(ControllerExceptionEnum.RoleNotAvailable.getInfo()); //角色不可用
        	throw new DisabledAccountException(ControllerExceptionEnum.RoleNotAvailable.getInfo()); //角色不可用
		}
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
                sysUserVo.getUsername(), //用户名  
                sysUserVo.getPassword(), //密码  
                ByteSource.Util.bytes(sysUserVo.getCredentialsSalt()),//salt=username+salt 
                getName()  //realm name  
        );  
        return authenticationInfo;  
	}

}
