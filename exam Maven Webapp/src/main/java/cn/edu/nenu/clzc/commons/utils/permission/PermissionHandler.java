package cn.edu.nenu.clzc.commons.utils.permission;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import cn.edu.nenu.clzc.commons.vo.system.SysUserVo;

public class PermissionHandler {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	 /*加密算法*/
	 private String algorithmName = "md5";
	 /*hash散列次数*/
	 private final int hashIterations = 2;  
	 
	public void encryptPassword(SysUserVo user) {  
        user.setUserSalt(randomNumberGenerator.nextBytes().toHex());
        
        String newPassword = new SimpleHash(  
                algorithmName,  
                user.getPassword(),  
                ByteSource.Util.bytes(user.getCredentialsSalt()),  
                hashIterations).toHex();  
        user.setPassword(newPassword);  
    }  
	public static void main(String[] args) {
		System.out.println(randomNumberGenerator.nextBytes());
		System.out.println(randomNumberGenerator.nextBytes().toHex());
	}
}
