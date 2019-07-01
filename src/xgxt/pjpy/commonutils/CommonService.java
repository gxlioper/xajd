
package xgxt.pjpy.commonutils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 评奖评优abstract service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public abstract class CommonService {

	/**
	 * 传入表名返回LIST查询表头
	 * @return
	 * @throws Exception
	 */
	public abstract ArrayList<HashMap<String, String>> getTableQryTitle(String tableName);
	
	/**
	 * 传入对象获取查询结果
	 * @param object
	 * @return
	 */
	public abstract ArrayList<String[]> getTableQryResult(Object object);
}
