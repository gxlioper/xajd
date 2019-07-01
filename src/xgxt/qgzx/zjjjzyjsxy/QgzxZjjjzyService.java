package xgxt.qgzx.zjjjzyjsxy;

import java.util.HashMap;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块浙江经济职业技术学院Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-03</p>
 */
public class QgzxZjjjzyService {
	QgzxZjjjzyDAO dao = new QgzxZjjjzyDAO();
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 判断学生是否是困难生
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		return dao.isKns(xh);
	}
	
	/**
	 * 获取当前时间
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCurrTime(){
		return dao.getCurrTime();
	}
}
