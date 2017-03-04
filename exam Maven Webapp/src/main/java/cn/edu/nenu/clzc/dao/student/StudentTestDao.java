package cn.edu.nenu.clzc.dao.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.student.StudentTest;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
/**
* @ClassName: StudentTestDao
* @Description: 学生自组卷Dao层操作方法(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年12月4日 下午7:46:31
 */

public class StudentTestDao extends AbstractDao {
    /**
    * @Title: addStudentTest
    * @Description: 添加一个学生自组卷(这里用一句话描述这个方法的作用)
    * @author Kyrie Irving
    * @param @param studengTest
    * @param @return
    * @param @throws Exception    设定文件
    * @return String    返回类型
    * @throws
     */
	
	public String addStudentTest(StudentTest studengTest) throws Exception {
		String studentId = studengTest.getStudentId();
		String studentTestType = studengTest.getStudentTestType();
		String studengTestInfo = studengTest.getStudentTestInfo();
		String questionsId = studengTest.getQuestionsId();
		Date studentsDate = new Date();
		Object[] params = {studentId,studentTestType,studengTestInfo,questionsId,studentsDate};
		
		String sql = "INSERT INTO student_test(student_test.student_id,student_test.student_test_type,student_test.student_test_info,student_test.questions_id,student_test.student_test_time)VALUES(?,?,?,?,?)";
		
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddStudentTestFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddStudentTestFaild.getInfo());
		}
		return id;
	}
	
	/**
	* @Title: deleteStudentTest
	* @Description: 删除一个学生自组卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param id
	* @param @param studentTestIsDelete
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	
	public int deleteStudentTest(String id, String studentTestIsDelete) throws Exception {
		String sql = "UPDATE student_test SET student_test_is_delete = ? WHERE id = ?";
		Object[] params = {studentTestIsDelete, id};
		int i = 0;
		try {
			i = update(sql, params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.DeleteStudentTestFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.DeleteStudentTestFaild.getInfo());
		}
		return i;
	}
	
	/**
	 * 
	* @Title: selectAllStudentTestByStudentId
	* @Description:根据学生的ID查询该学生所有可见的自组卷的类型和描述 ，并实现分页显示(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentTestInfo
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<StudentTest>    返回类型
	* @throws
	 */
	public List<StudentTest> selectStudentTestByStudentId(String studentId ) throws Exception {
		List<StudentTest> list = new ArrayList<StudentTest>();
		String sql = "SELECT DISTINCT student_test.student_test_info, student_test.student_test_type,  student_test.student_test_time FROM student_test INNER JOIN sys_user ON student_test.student_id = sys_user.id WHERE student_test.student_test_is_delete = '0' AND student_test.student_id = ? ";
		Object[] params = {studentId } ;
		
		try {
			list = query(sql, new ExpandBeanListHandler<StudentTest>(StudentTest.class),params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectStudentTestByStudentIdFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectStudentTestByStudentIdFaild.getInfo());
		}
		return list;
	}
	
	
	
	
	
	
	/**
	 * 
	* @Title: selectStudentTestByStudentTestInfo 
	* @Author Kyrie Irving
	* @Description: 根据学生ID和试卷描述查询学生自组卷的大题ID,并实现分页(这里用一句话描述这个方法的作用) 
	* @param @param studentTestInfo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<StudentTest>    返回类型 
	* @throws
	 */
	
	public List<StudentTest> selectStudentTestByStudentTestInfo(String studentId, String studentTestInfo) throws Exception {
		List<StudentTest> list = new ArrayList<StudentTest>();
		String sql = "SELECT student_test.questions_id FROM	student_test INNER JOIN sys_user ON student_test.student_id = sys_user.id WHERE	student_test.student_id =?AND student_test.student_test_info=?AND student_test.student_test_is_delete = '0'";
		Object[] params = {studentId,studentTestInfo} ;
		
		try {
			list = query(sql, new ExpandBeanListHandler<StudentTest>(StudentTest.class),params);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectStudentTestByStudentTestInfoFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectStudentTestByStudentTestInfoFaild.getInfo());
		}
		return list;
	}
	

	
	
	
	
	
	



}
