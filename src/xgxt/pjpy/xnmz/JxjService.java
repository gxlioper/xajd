
package xgxt.pjpy.xnmz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class JxjService {

	private JxjDAO dao;
	
	public List<String[]> xyjxjshQryResult(JxjModel model, String userType) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjshQryResult(model);
		} else {
			return dao.xxjxjshQryResult(model);
		}
	}
	
	/**
	 * ѧԺ��˽�ѧ���ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyjxjshQryTitle(String userType) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjshQryTitle();
		} else {
			return dao.xxjxjshQryTitle();
		}
	}
	
	public String xyjxjshRes(String[] keys, String res,
			HttpServletRequest request, JxjModel model, String userType) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {//ѧԺ��˽��
			return dao.xyjxjshRes(keys, res, request, model);
		} else {
			return dao.xxjxjshRes(keys, res, request, model);
		}
	}
}
