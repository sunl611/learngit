package cn.edu.nenu.clzc.dao.examination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.examination.ParamUnit;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.vo.examination.ParamUnitVo;

/**
* @ClassName: ParamUnitDao
* @Description: 单元操作dao层(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年11月29日 下午12:32:38
 */


public class ParamUnitDao extends AbstractDao {
	/**
	* @Title: addEdition
	* @Description: 添加一个单元(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param ParamEdition
	* @param @return
	* @param @throws Exception    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String addUnit(ParamUnit ParamUnit) throws Exception {
 
		String unitName = ParamUnit.getUnitName();
		String unitInfo = ParamUnit.getUnitInfo();
		Date unitTime = ParamUnit.getUnitTime();
		String editonId = ParamUnit.getEditionId();
		
		Object[] params = {unitName, unitInfo ,editonId, unitTime};
		String sql = "INSERT INTO param_unit ( unit_name , unit_info , edition_id , unit_time ) VALUES (?, ?, ?, ?)";
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddUnitFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddUnitFaild.getInfo());
		}
		return id;
	}
	
	/**
	* @Title: updateEdition
	* @Description: 通过修改ID来控制一个单元的显示状态(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param id
	* @param @param editionIsDelete
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int updateUnit(String id,String unitIsDelete) throws Exception {
		Object[] params = {unitIsDelete,id};
		String sql = "UPDATE param_unit SET unit_is_delete =? WHERE  id = ?" ;
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.UpdateUnitFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.UpdateUnitFaild.getInfo());
		}
		return i;
	}

	
	/**
	* @Title: selectByUnitName
<<<<<<< .mine
	* @Description: 根据册ID来查找所有单元，并实现分页显示(这里用一句话描述这个方法的作用)
=======
	* @Description: 根据册ID来查找可见的单元，并实现分页显示(这里用一句话描述这个方法的作用)
>>>>>>> .r409
	* @author Kyrie Irving
	* @param @param editionName
	* @param @param currentPage
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<ParamEditionVo>    返回类型
	* @throws
	 */
	public List<ParamUnitVo> selectUnitByEdition(String editionId, int currentPage) throws Exception {
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		String sql = "SELECT param_unit.unit_name,param_unit.unit_info,param_unit.unit_time,param_unit.edition_id FROM param_unit WHERE param_unit.edition_id = ? ORDER BY param_unit.unit_name ASC LIMIT ?,?";
		Object[] params = {editionId, (currentPage-1)*PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<ParamUnitVo>(ParamUnitVo.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectUnitByEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectUnitByEditionFaild.getInfo());
		}
		return list;
	}
	
	
	
	/**
	 * 
	* @Title: selectBackUnitByEdition
	* @Description: 根据册名查找后台显示的所有单元(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param editionId
	* @param @param currentPage
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<ParamUnitVo>    返回类型
	* @throws
	 */
	public List<ParamUnitVo> selectBackUnitByEdition(String editionId, int currentPage) throws Exception {
		List<ParamUnitVo> list = new ArrayList<ParamUnitVo>();
		String sql = "SELECT param_unit.unit_name,param_unit.unit_info,param_unit.unit_time,param_unit.edition_id FROM param_unit  AND param_unit.edition_id = ? ORDER BY param_unit.unit_name ASC LIMIT ?,?";
		Object[] params = {editionId, (currentPage-1)*PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<ParamUnitVo>(ParamUnitVo.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectBackUnitByEditionFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectBackUnitByEditionFaild.getInfo());
		}
		return list;
	}
	
	
	
	
	/**
	* @Title: selectAllPage
	* @Description: 查询所有单元的分页页数(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int selectAllPage() throws Exception {
		String sql = "SELECT Count(*) FROM param_unit ";
		/**
		 * 计算总记录数
		 */
		int totalRow = 0;
		try {
			totalRow = query(sql, new ScalarHandler<Long>()).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectAllPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectAllPageFaild.getInfo());
		}
		return size(totalRow);		
	}
	
    /**
    * @Title: selectFirstUnitPage
    * @Description: 查询前台能显示的单元数(这里用一句话描述这个方法的作用)
    * @author Kyrie Irving
    * @param @return
    * @param @throws Exception    设定文件
    * @return int    返回类型
    * @throws
     */
	
	public int selectFirstUnitPage() throws Exception {
		String sql = "SELECT Count(*) FROM param_unit WHERE param_unit.unit_is_delete = '0'";
		int totalRow = 0;
		try {
			totalRow = query(sql, new ScalarHandler<Long>()).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectFirstUnitPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectFirstUnitPageFaild.getInfo());
		}
		return size(totalRow);		
	}
}
