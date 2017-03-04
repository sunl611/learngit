package cn.edu.nenu.clzc.service.student;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.student.StudentDone;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;


/**
 * 
* @ClassName: StudentDoneService
* @Description: 学生已完成的试卷的service层(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年12月6日 下午7:47:32
 */


public class StudentDoneService extends ServiceExtend {
	
	/**
	 * 
	* @Title: addStudentDone
	* @Description: 添加一个学生已完成的试卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentDone
	* @param @return
	* @param @throws ContextException    设定文件
	* @return String    返回类型
	* @throws
	 */

    public String addStudentDone(StudentDone studentDone) throws ContextException {
		String id = null;
		try {
			id = studentDoneDao.addStudentDone(studentDone);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
    /**
     * 
    * @Title: updateStudentDoneMark
    * @Description: 更新学生已完成的试卷的成绩(这里用一句话描述这个方法的作用)
    * @author Kyrie Irving
    * @param @param studentDoneMark
    * @param @return
    * @param @throws Exception    设定文件
    * @return int    返回类型
    * @throws
     */
    
    public int updateStudentDoneMark(String studentDoneMark) throws Exception {
    	Integer updateColumn=null;
    	try {
			updateColumn = studentDoneDao.updateStudentDoneMark(studentDoneMark);
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
		return updateColumn;
		
    }
		
    
    
    /**
	 * 
	* @Title: selectStudentDone
	* @Description: 查找学生已完成的试卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<StudentDone>    返回类型
	* @throws
	 */
	
	public List<StudentDone> selectExaminationOrTestId (String studentId) throws ContextException {
		List<StudentDone> list = new ArrayList<StudentDone>();
		try {
			list = studentDoneDao.selectExaminationOrTestId(studentId);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	

}
