package cn.edu.nenu.clzc.dao.examination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.examination.ParamEdition;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.examination.ParamEditionVo;


	/**
	* @param <ParamEditionVo>
	* @Author Kyrie Irving
	* @Title: ParamEditionDao 
	* @Description: 册操作的dao方法
	* @Date 2016年11月18日 下午9:00:11
	 */



public class ParamEditionDao extends AbstractDao {
	
	/**
	* @Title: addEdition 
	* @Author Kyrie Irving
	* @Description: 添加一个册(这里用一句话描述这个方法的作用) 
	* @param @param ParamEdition
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String addEdition(ParamEdition ParamEdition) throws Exception {
		
		String editionName = ParamEdition.getEditionName();
		String editionInfo = ParamEdition.getEditionInfo();
		Date editionTime = ParamEdition.getEditionTime();
		
		Object[] params = { editionName, editionInfo, editionTime};
		String sql = "INSERT INTO param_edition (edition_name , edition_info , edition_time)VALUES(?, ?, ?)";
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddEditionFaild.getInfo());
		}
		return id;
	}
	
	
	/**
	* @Title: updateEdition 
	* @Author Kyrie Irving
	* @Description: 删除册，根据id修改册的显示状态(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @param editionIsDelete
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateEdition(String id,String editionIsDelete) throws Exception {
		Object[] params = {editionIsDelete,id};
		String sql = "UPDATE param_edition SET edition_is_delete =? WHERE  id = ?" ;
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.UpdateEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.UpdateEditionFaild.getInfo());
		}
		return i;
	}


	/**
	* @Title: selectAllEdition
	* @Description: 按册名顺序查询所有可见的册(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<ParamEditionVo>    返回类型
	* @throws
	 */
	public List<ParamEditionVo> selectAllEdition() throws Exception {
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();

		String sql = "SELECT param_edition.edition_name,param_edition.edition_info,param_edition.edition_time FROM param_edition WHERE param_edition.edition_is_delete='0' ORDER BY param_edition.edition_name ASC ";
		try{
			list = query(sql, new ExpandBeanListHandler<ParamEditionVo>(ParamEditionVo.class));
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectEditionFaild.getInfo());
		}
		return list;
	}

	
	
	
	/**
	 * 
	* @Title: selectBackEdition
	* @Description: 根据册名排序查找所有的册(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<ParamEditionVo>    返回类型
	* @throws
	 */
	public List<ParamEditionVo> selectBackEdition() throws Exception {
		List<ParamEditionVo> list = new ArrayList<ParamEditionVo>();

		String sql = "SELECT param_edition.edition_name,param_edition.edition_info,param_edition.edition_time FROM param_edition ORDER BY param_edition.edition_name ASC ";
		try{
			list = query(sql, new ExpandBeanListHandler<ParamEditionVo>(ParamEditionVo.class));
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectBackEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectBackEditionFaild.getInfo());
		}
		return list;
	}
	
	

}
