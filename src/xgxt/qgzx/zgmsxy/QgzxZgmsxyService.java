package xgxt.qgzx.zgmsxy;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块中国美术学院Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-04</p>
 */
public class QgzxZgmsxyService {
	/**
	 * 查询岗位信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk,String pkValue){
		QgzxZgmsxyDAO dao = new QgzxZgmsxyDAO();
		return dao.getGwInfo(pk,pkValue);
	}
	
	/**
	 * 获取岗位的学生酬金信息
	 * */
	public List getStuPayInfo(String pk,String pkValue){
		QgzxZgmsxyDAO dao = new QgzxZgmsxyDAO();
		return dao.getStuPayInfo(pk,pkValue);
	}
}
