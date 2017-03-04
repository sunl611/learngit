package cn.edu.nenu.clzc.service.examination;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.examination.ParamEdition;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.vo.examination.ParamEditionVo;

/**
 * 
* @Author Kyrie Irving
* @Title: AddEditionService 
* @Description: 册操作的Sercvice层
* @Date 2016年11月18日 下午9:29:38
 */

public class ParamEditionService extends ServiceExtend {
	
	/**
	* @Title: addEdition 
	* @Author Kyrie Irving
	* @Description: 添加一个册(这里用一句话描述这个方法的作用) 
	* @param @param paramEdition
	* @param @return
	* @param @throws ContextException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addEdition(ParamEdition ParamEdition) throws ContextException {
		String id = null;
		try {
			id = paramEditionDao.addEdition(ParamEdition);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	/**
	 * 
	* @Title: updateEditiion 
	* @Author Kyrie Irving
	* @Description: 修改册的显示状态(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @param editionIsDelete
	* @param @return
	* @param @throws ContextException    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateEditiion(String id, String editionIsDelete) throws ContextException {
		int i = 0;
		try {
			i= paramEditionDao.updateEdition(id, editionIsDelete);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	
	
	/**
	* @Title: selectAllEdition
	* @Description: 查找可见的册(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<ParamEditionVo>    返回类型
	* @throws
	 */
	

	public List<ParamEditionVo> selectAllEdition() throws ContextException{
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();
		try {
			list = paramEditionDao.selectAllEdition();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}

	
	/**
	 * 
	* @Title: selectBackEdition
	* @Description: 后台查找所有的册(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<ParamEditionVo>    返回类型
	* @throws
	 */
	public List<ParamEditionVo> selectBackEdition() throws ContextException{
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();
		try {
			list = paramEditionDao.selectBackEdition();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	

}
	
	
	
	