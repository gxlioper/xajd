
package xgxt.pjpy.hygxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyService {

	PjpyHygxyDAO dao = null;
	
	/**
	 * 奖学金申请时间
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = new PjpyHygxyDAO();
		return dao.getJxjsqsj();
	}
	
	/**
	 * 奖学金设置时间
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getPdsj() throws Exception {
		dao = new PjpyHygxyDAO();
		return dao.getPdsj();
	}
	
	/**
	 * 保存奖学金评定时间
	 * @param jxjpdsj
	 * @param rychpdsj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean savePdsj(String jxjpdsj, String rychpdsj,
			HttpServletRequest request) throws Exception {
		dao = new PjpyHygxyDAO();
		return dao.savePdsj(jxjpdsj, rychpdsj, request);
	}
}
