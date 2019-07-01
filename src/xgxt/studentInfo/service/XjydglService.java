package xgxt.studentInfo.service;

import java.util.List;

import xgxt.form.CommanForm;
import xgxt.studentInfo.dao.XjydglDAO;
import xgxt.studentInfo.model.StudentInfoForm;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生学籍异动管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-1</p>
 */
public class XjydglService {
	
	/**
	 * 获取表或视图字段的注释信息
	 * @param tableName
	 * @param colList
	 * @return String[]
	 * */
	public String[] getColumnComment(String tableName, String[] colList){
		XjydglDAO dao = new XjydglDAO();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 查询学生学籍异动信息
	 * @param tableName
	 * @param model
	 * @param outputCol
	 * @return List<String[]>
	 * */
	public List<String[]> queryXjydxx(String tableName, 
									  StudentInfoForm model, 
			                          String[] outputCol){
		XjydglDAO dao = new XjydglDAO();
		return dao.selectXjydxx(tableName, model, outputCol);
	}
}
