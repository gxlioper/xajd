
package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院评奖评优奖学金类别代码SERVICE</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyJxjlbdmService {

	PjpyHygxyJxjlbdmDAO dao = null;
	
	/**
	 * 保存奖学金类别代码
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.saveJxjlbdm(model, request);
	}
	
	/**
	 * 修改奖学金类别代码
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjlbdm(JxjlbdmModel model, HttpServletRequest request)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.updateJxjlbdm(model, request);
	}
	
	/**
	 * 奖学金类别代码修改显示
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjlbdmDetails(String pkValue)
			throws Exception {
		dao = new PjpyHygxyJxjlbdmDAO();
		return dao.getJxjlbdmDetails(pkValue);
	}
}
