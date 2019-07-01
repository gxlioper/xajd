
package xgxt.pjpy.hygxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院评奖评优奖学金代码Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyJxjdmService extends PjpyHygxyService{

	PjpyJxjdmDAO dao = null;
	
	/**
	 * 奖学金类别
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjlbList() throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.getJxjlbList();
	}
	
	/**
	 * 奖学金代码保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjdmSave(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.jxjdmSave(model, request);
	}
	
	/**
	 * 奖学金修改显示
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjdm(String pkValue) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.viewJxjdm(pkValue);
	}
	
	public boolean updateJxjdm(PjpyJxjdmModel model, HttpServletRequest request) throws Exception {
		dao = new PjpyJxjdmDAO();
		return dao.updateJxjdm(model, request);
	}
}
