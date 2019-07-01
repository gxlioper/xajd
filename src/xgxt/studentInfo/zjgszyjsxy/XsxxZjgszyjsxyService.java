package xgxt.studentInfo.zjgszyjsxy;

import java.util.List;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江工商职业技术学院学生信息Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-06</p>
 */
public class XsxxZjgszyjsxyService {
	XsxxZjgszyjsxyDAO dao = new XsxxZjgszyjsxyDAO();
	
	/**
	 * 获取代码列表
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName,String[] columns){
		return dao.getDmList(tableName,columns);
	}
}
