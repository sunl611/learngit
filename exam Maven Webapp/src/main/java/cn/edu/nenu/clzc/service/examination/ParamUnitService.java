package cn.edu.nenu.clzc.service.examination;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.examination.ParamUnit;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;
import cn.edu.nenu.clzc.commons.vo.examination.ParamUnitVo;

/**
 * 
* @Author Kyrie Irving
* @Title: AddEditionService 
* @Description: 单元操作的Sercvice层
* @Date 2016年11月18日 下午9:29:38
 */

public class ParamUnitService extends ServiceExtend {
	
	/**
	* @Title: addEdition 
	* @Author Kyrie Irving
	* @Description: 添加一个单元(这里用一句话描述这个方法的作用) 
	* @param @param paramEdition
	* @param @return
	* @param @throws ContextException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addUnit(ParamUnit ParamUnit) throws ContextException {
		String id = null;
		try {
			id = ParamUnitDao.addUnit(ParamUnit);
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
	* @Description: 修改单元的显示状态的方法(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @param editionIsDelete
	* @param @return
	* @param @throws ContextException    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateUnit(String id, String unitIsDelete) throws ContextException {
		int i = 0;
		try {
			i = ParamUnitDao.updateUnit(id,unitIsDelete);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	
	
	/**
	* @Title: selectByEditionName 
	* @Author Kyrie Irving
	* @Description:根据册名查找前台可见的单元，并实现分页显示(这里用一句话描述这个方法的作用) 
	* @param @param editionName
	* @param @param currentPage
	* @param @return
	* @param @throws ContextException    设定文件 
	* @return List<ParamUintVo>    返回类型 
	* @throws
	 */
	public List<ParamUnitVo> selectUnitByEdition(String editionId ,int currentPage) throws ContextException{
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		try {
			list = ParamUnitDao.selectUnitByEdition(editionId, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	/**
	 * 
	* @Title: selectBackUnitByEdition
	* @Description: 根据册的id来查找后台显示的所有单元(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param editionId
	* @param @param currentPage
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<ParamUnitVo>    返回类型
	* @throws
	 */
	public List<ParamUnitVo> selectBackUnitByEdition(String editionId ,int currentPage) throws ContextException{
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		try {
			list = ParamUnitDao.selectBackUnitByEdition(editionId, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	/**
	* @Title: selectAllPage
	* @Description: 查询所有单元数(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param currentPage
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<UserMessageVo>    返回类型
	* @throws
	 */
	public int selectAllPage() throws ContextException {
		int size = 0;
		try {
			size = ParamUnitDao.selectAllPage();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	/**
	* @Title: selectFirstUnitPage
	* @Description: 查询前台能显示的单元数(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws ContextException    设定文件
	* @return int    返回类型
	* @throws
	 */
	
	public int selectFirstUnitPage() throws ContextException {
		int size = 0;
		try {
			size = ParamUnitDao.selectFirstUnitPage();
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}

	
}

	
	
	