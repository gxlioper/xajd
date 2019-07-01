package xgxt.studentInfo.ahjzgyxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑职业技术学院学生信息service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-08</p>
 */
public class XsxxAhjzgyxyService {
	/**
	 * 学生分类管理查询表头
	 */
	public  List<HashMap<String, String>> serv_getStuTypeManageTitle(){
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getStuTypeManageTitle();
	}
	/**
	 * 学生分类管理查询
	 */
	public  ArrayList<List> serv_getStuTypeManage(StuTypeModel model,String userType){
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getStuTypeManage(model,userType);
	}
	/**
	 * 获取类型存在id
	 */
	public String getLxChecked(StuTypeModel model) throws Exception {
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getLxChecked(model);
	}
	/**
	 * 学生分类保存
	 * @throws SQLException 
	 */
	public boolean serv_stuTypeSave(StuTypeModel model) throws SQLException{
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.dao_stuTypeSave(model);
	}
}
