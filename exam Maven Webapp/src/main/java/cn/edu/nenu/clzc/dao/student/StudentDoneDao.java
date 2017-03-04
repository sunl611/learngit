package cn.edu.nenu.clzc.dao.student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nenu.clzc.commons.core.AbstractDao;
import cn.edu.nenu.clzc.commons.core.expandhandler.ExpandBeanListHandler;
import cn.edu.nenu.clzc.commons.entites.student.StudentDone;
import cn.edu.nenu.clzc.commons.enumeration.exception.DaoExceptionEnum;
import cn.edu.nenu.clzc.commons.exception.ContextException;
/**
 * 
* @ClassName: StudentDoneDao
* @Description: 学生完成过的卷子Dao层(这里用一句话描述这个类的作用)
* @author Kyrie Irving
* @date 2016年12月6日 下午5:54:57
 */
public class StudentDoneDao extends AbstractDao {
    
	/**
	 * 
	* @Title: addStudentDone
	* @Description: 添加学生已经完成的试卷(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentDone
	* @param @return
	* @param @throws Exception    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String addStudentDone(StudentDone studentDone) throws Exception {
		String studentId = studentDone.getStudentId();
		String examinationOrTestId = studentDone.getExaminationOrTestId();
		String studentDoneType = studentDone.getStudentDoneType();
		Date studentDoneTime = new Date();
		
		Object[] params = {studentId,examinationOrTestId,studentDoneType,studentDoneTime};
		
		String sql = "INSERT INTO student_done ( student_done.student_id, student_done.examination_or_test_id, student_done.student_done_type, student_done.student_done_time ) VALUES (?,?,?,?)";
		
		String id = null;
		try {
			id = insert(sql, params);
		} catch (ContextException | SQLException e) {
			logger.error(DaoExceptionEnum.AddStudentDoneFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.AddStudentDoneFaild.getInfo());
		}
		return id;
	}
	
	/**
	 * 
	* @Title: updataStudentDoneMark
	* @Description: 更新学生已完成的试卷成绩(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentDoneMark
	* @param @return
	* @param @throws Exception    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int  updateStudentDoneMark(String studentDoneMark)throws Exception {
		Object[] params = {studentDoneMark};
		String sql="UPDATE student_done INNER JOIN sys_user ON student_done.student_id = sys_user.id SET student_done_mark = ?";
		Integer updateColumn=null;
		try {
			updateColumn= update(sql,params);
		} catch (Exception e) {
			logger.error(DaoExceptionEnum.UpdataStudentDoneMarkFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.UpdataStudentDoneMarkFaild.getInfo());
		}
		return updateColumn;
	}
	
	
	
	/**
	 * 
	* @Title: selectAllStudentTestByStudentId
	* @Description: 查询对应学生已完成的试卷ID和分数(这里用一句话描述这个方法的作用)
	* @author Kyrie Irving
	* @param @param studentId
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<StudentDone>    返回类型
	* @throws
	 */
	public List<StudentDone> selectExaminationOrTestId(String studentId) throws Exception {
		List<StudentDone> list = new ArrayList<StudentDone>();
		String sql = "SELECT student_done.examination_or_test_id,student_done.student_done_mark FROM student_done INNER JOIN sys_user ON student_done.student_id = sys_user.id WHERE student_done.student_id=?";
	    Object param = studentId;
		try {
			list = query(sql, new ExpandBeanListHandler<StudentDone>(StudentDone.class),param);
		} catch (ContextException e) {
			logger.error(DaoExceptionEnum.SelectExaminationOrTestIdFaild.getInfo(),e);
			throw new Exception(DaoExceptionEnum.SelectExaminationOrTestIdFaild.getInfo());
		}
		return list;
	}
	
		
		
		
	


}
