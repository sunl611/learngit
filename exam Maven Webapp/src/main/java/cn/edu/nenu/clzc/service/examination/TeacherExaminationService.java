package cn.edu.nenu.clzc.service.examination;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.teacher.TeacherExamination;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;

/**
 * 
 * @author 我要睡觉了
 * @Title TeacherExaminationService.java
 * @Description 试题的service
 * @time 2016年12月4日 下午8:49:57
 */

public class TeacherExaminationService extends ServiceExtend {

	
	/**
	 *  
	 * @Title: addExamination
	 * @Description: 添加一套试卷
	 * @return: String
	 * @throws ContextException 
	 */
	public String addExamination(TeacherExamination teacherExamination) throws ContextException {
		String id = null;
		try {
			id = teacherExaminationDao.addExamination(teacherExamination);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	
	/**
	 * 
	 * @Title: deleteExamination
	 * @Description: 更改一套试题是否可见
	 * @return: int
	 * @throws: ContextException
	 */
	public int deleteExamination(String id, String value) throws ContextException {
		int temp = 0;
		try {
			temp = teacherExaminationDao.deleteExamination(id, value);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnit
	 * @Description: 根据单元分页查询出所有可见试卷
	 * @return: List<TeacherExamination>
	 * @throws: ContextException
	 */
	public List<TeacherExamination> selectExaminationByUnit(String unitId, String examinationType, int currentPage) throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		try {
			list = teacherExaminationDao.selectExaminationByUnit(unitId, examinationType, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByUnitPage
	 * @Description: 根据单元查询出可见试卷的分页总页数
	 * @return: int
	 * @throws: ContextException
	 */
	public int selectExaminationByUnitPage(String unitId, String examinationType) throws ContextException {
		int size;
		try {
			size = teacherExaminationDao.selectExaminationByUnitPage(unitId, examinationType);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	
	/**
	 * 
	 * @Title: selectAllExaminationByUnit
	 * @Description: 根据单元分页查询出所有试卷
	 * @return: List<TeacherExamination>
	 * @throws ContextException 
	 */
	public List<TeacherExamination> selectAllExaminationByUnit(String unitId, String examinationType, int currentPage) throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		try {
			list = teacherExaminationDao.selectAllExaminationByUnit(unitId, examinationType, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectAllExaminationByUnitPage
	 * @Description: 根据单元查询出所有试卷的分页总页数
	 * @return: int
	 * @throws: ContextException
	 */
	public int selectAllExaminationByUnitPage(String unitId, String examinationType) throws ContextException {
		int size;
		try {
			size = teacherExaminationDao.selectAllExaminationByUnitPage(unitId, examinationType);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByInfo
	 * @Description: 根据试题描述模糊分页查询出试题
	 * @return: List<TeacherExamination>
	 * @throws ContextException 
	 */
	public List<TeacherExamination> selectExaminationByInfo(String examinationInfo, String examinationType, int currentPage) throws ContextException {
		List<TeacherExamination> list = new ArrayList<TeacherExamination>();
		try {
			list = teacherExaminationDao.selectExaminationByInfo(examinationInfo, examinationType, currentPage);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: selectExaminationByInfoPage
	 * @Description: 根据试题描述模糊查询出的试题页数
	 * @return:int
	 * @throws ContextException 
	 */
	public int selectExaminationByInfoPage(String examinationInfo, String examinationType) throws ContextException {
		int size = 0;
		try {
			size = teacherExaminationDao.selectExaminationByInfoPage(examinationInfo, examinationType);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return size;
	}
	

	
}
