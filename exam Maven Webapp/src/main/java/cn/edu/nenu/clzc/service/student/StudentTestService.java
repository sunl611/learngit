package cn.edu.nenu.clzc.service.student;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nenu.clzc.commons.entites.student.StudentTest;
import cn.edu.nenu.clzc.commons.exception.ContextException;
import cn.edu.nenu.clzc.commons.extend.ServiceExtend;



/**
* @ClassName: StudentTestService
* @Description: 学生自组卷的service层(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年12月5日 下午4:00:02
 */

public class StudentTestService extends ServiceExtend {
	
	


	/**
	* @Title: addQuestion
	* @Description: 添加一个学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param paramQuestion
	* @param @return
	* @param @throws ContextException    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String addStudentTest(StudentTest studentTest) throws ContextException {
		String id = null;
		try {
			id = studentTestDao.addStudentTest(studentTest);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return id;
	}
	
	/**
	 * 
	* @Title: deleteQuestion
	* @Description: 删除一个学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param id
	* @param @param studentTestIsDelete
	* @param @return
	* @param @throws ContextException    设定文件
	* @return int    返回类型
	* @throws
	 */
	
	public int deleteStudentTest(String id, String studentTestIsDelete) throws ContextException {
		int i = 0;
		try {
			i = studentTestDao.deleteStudentTest(id, studentTestIsDelete);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return i;
	}
	
	/**
	* @Title: selectAllStudentTestByStudentId
	* @Description: 根据学生ID查找所有学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentId
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<StudentTest>    返回类型
	* @throws
	 */
	
	public List<StudentTest> selectAllStudentTestByStudentId (String studentId) throws ContextException {
		List<StudentTest> list = new ArrayList<StudentTest>();
		try {
			list = studentTestDao.selectAllStudentTestByStudentId(studentId);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	/**
	* @Title: selectStudentTestByStudentTestInfo
	* @Description: 根据学生自组卷的描述查找学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentTestInfo
	* @param @return
	* @param @throws ContextException    设定文件
	* @return List<StudentTest>    返回类型
	* @throws
	 */
	public List<StudentTest> selectStudentTestByStudentTestInfo (String studentTestInfo) throws ContextException {
		List<StudentTest> list = new ArrayList<StudentTest>();
		try {
			list = studentTestDao.selectStudentTestByStudentTestInfo(studentTestInfo);
		} catch (Exception e) {
			logger.error(e);
			throw new ContextException(e);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
}
