
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public abstract class PjpyYnysService {

	public PjpyYnysDAO dao = null;
	
	/**
	 * 奖学金申请时间
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getJxjsqsj();
	}
	
	/**
	 * 返回审核结果标致
	 * @param tgRes
	 * @return
	 * @throws Exception
	 */
	public String getShjg(String tgRes) throws Exception {
		return StringUtils.isNull(tgRes) ? "未审核" : (StringUtils.isEqual("tg",
				tgRes) ? "通过" : (StringUtils.isEqual("btg", tgRes) ? "不通过"
				: "未审核"));
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getStuInfo(xh);
	}
	
	public List<String[]> getCjList(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getCjList(xh);
	}
	
	public HashMap<String, String> getCjpm(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getCjpm(xh);
	}
	
	public HashMap<String, String> getZhfpm(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getZhfpm(xh);
	}
}
