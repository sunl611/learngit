package cn.edu.nenu.clzc.dao.examination;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.teacher.TeacherExamination;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;

/**
 * 
 * @author 我要睡觉了
 * @Title TeacherExaminationDao.java
 * @Description 测试卷的dao方法
 * @time 2016年12月4日 下午3:37:03
 */

public class TeacherExaminationDao extends AbstractDao {

	
	/**
	 * 
	 * @Title: addExamintation
	 * @Description: 增加一套试卷
	 * @return: String
	 * @throws Exception 
	 */
	public String addExamination(TeacherExamination teacherExamination) throws Exception {
		String unitId = teacherExamination.getUnitId();
		Double examinationPersistTime = teacherExamination.getExaminationPersistTime();
		String examinationCreateUsername = teacherExamination.getExaminationCreateUsername();
		String examinationType = teacherExamination.getExaminationType();
		Date examinationTime = new Date();
		String examinationInfo = teacherExamination.getExaminationInfo();
		String sql = "INSERT INTO teacher_examination (unit_id, examination_persist_time, examination_create_username, examination_type, examination_time, examination_info) VALUES (?, ?, ?, ?, ?, ?)";
		Object[] params = {unitId, examinationPersistTime, examinationCreateUsername, examinationType, examinationTime, examinationInfo};
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddExaminationFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddExaminationFaild.getInfo());
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: deleteExamination
	 * @Description: 删除一套试卷
	 * @return: int
	 * @throws Exception 
	 */
	public int deleteExamination(String id, String value) throws Exception {
		int temp = 0;
		String sql = "UPDATE teacher_examination SET examination_is_delete = ? WHERE id = ?";
		Object[] params = {id, value};
		try {
			temp = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.DeleteExaminationFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.DeleteExaminationFaild.getInfo());
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnit
	 * @Description: 根据单元分页查询出所有可见试卷
	 * @return: List<TeacherExamination>
	 * @throws Exception 
	 */
	public List<TeacherExamination> selectExaminationByUnit(String unitId, String examinationType, int currentPage) throws Exception {
		List<TeacherExamination> list = new  ArrayList<TeacherExamination>();
		String sql = "SELECT * from teacher_examination WHERE unit_id = ? AND examination_type = ? AND examination_is_delete = '0' ORDER BY examination_time ASC LIMIT ?, ?";
		Object[] params = {unitId, examinationType, (currentPage - 1) * PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<TeacherExamination>(TeacherExamination.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectExaminationByUnitFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectExaminationByUnitFaild.getInfo());
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnitPage
	 * @Description: 根据单元查询出可见试卷的分页总页数
	 * @return: int
	 * @throws Exception 
	 */
	public int selectExaminationByUnitPage(String unitId, String examinationType) throws Exception {
		int totalRow = 0;
		String sql = "SELECT COUNT(*) from teacher_examination WHERE unit_id = ? AND examination_type = ? AND examination_is_delete = '0'";
		Object[] params = {unitId, examinationType};
		try {
			totalRow = query(sql, new ScalarHandler<Long>(), params).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectExaminationByUnitPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectExaminationByUnitPageFaild.getInfo());
		}
		return size(totalRow);
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnit
	 * @Description: 根据单元分页查询出所有试题
	 * @return: List<TeacherExamination>
	 * @throws Exception 
	 */
	public List<TeacherExamination> selectAllExaminationByUnit(String unitId, String examinationType, int currentPage) throws Exception {
		List<TeacherExamination> list = new  ArrayList<TeacherExamination>();
		String sql = "SELECT * from teacher_examination WHERE unit_id = ? AND examination_type = ? ORDER BY examination_time ASC LIMIT ?, ?";
		Object[] params = {unitId, examinationType, (currentPage - 1) * PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<TeacherExamination>(TeacherExamination.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectAllExaminationByUnitFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectAllExaminationByUnitFaild.getInfo());
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnitPage
	 * @Description: 查询出试卷的分页总页数
	 * @return: int
	 * @throws Exception 
	 */
	public int selectAllExaminationByUnitPage(String unitId, String examinationType) throws Exception {
		int totalRow = 0;
		String sql = "SELECT COUNT(*) from teacher_examination WHERE unit_id = ? AND examination_type = ?";
		Object[] params = {unitId, examinationType};
		try {
			totalRow = query(sql, new ScalarHandler<Long>(), params).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectAllExaminationByUnitPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectAllExaminationByUnitPageFaild.getInfo());
		}
		return size(totalRow);
	}
	
	
	/**
	 *  
	 * @Title: selectExaminationByInfo
	 * @Description: 根据试题描述模糊分页查询出试题
	 * @return: List<TeacherExamination>
	 * @throws Exception 
	 */
	public List<TeacherExamination> selectExaminationByInfo(String examinationInfo, String examinationType, int currentPage) throws Exception {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		String sql = "SELECT * from teacher_examination WHERE examination_info LIKE ? AND examination_type = ? AND examination_is_delete = '0' ORDER BY examination_time ASC LIMIT ?, ?";
		Object[] params = {"%" + examinationInfo + "%", examinationType, (currentPage - 1) * PAGESIZE, PAGESIZE};
		try {
			list = query(sql, new ExpandBeanListHandler<TeacherExamination>(TeacherExamination.class), params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectExaminationByInfoFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectExaminationByInfoFaild.getInfo());
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByInfoPage
	 * @Description: 根据试题描述模糊查询出的试题页数
	 * @return: int
	 * @throws Exception 
	 */
	public int selectExaminationByInfoPage(String examinationInfo, String examinationType) throws Exception {
		int totalRow = 0;
		String sql = "SELECT COUNT(*) from teacher_examination WHERE examination_info LIKE ? AND examination_type = ? AND examination_is_delete = '0'";
		Object[] params = {"%" + examinationInfo + "%", examinationType};
		try {
			totalRow = query(sql, new ScalarHandler<Long>(), params).intValue();
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectExaminationByInfoPageFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectExaminationByInfoPageFaild.getInfo());
		}
		return size(totalRow);
	}
	
	
	
}
