package xgxt.studentInfo.service;

import java.util.List;

import xgxt.studentInfo.dao.StuInfoDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生集体信息Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-16</p>
 */
public class StuGropInfoService {
	/**
	 * 根据班级名称获取该班级下的学生信息
	 * */
	public List getStuinfoByBjmc(String bjmc){
		StuInfoDAO dao = new StuInfoDAO();		
		return dao.selectStuinfoByBjmc(bjmc);
	}
	
	public String[] getTopTr(){
		StuInfoDAO dao = new StuInfoDAO();
		return dao.getTopTr();
	}
}
