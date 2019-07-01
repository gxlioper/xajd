
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class JxjService extends PjpyYnysService {

	JxjDAO dao = null;
	
	public HashMap<String, String> getJxjxx(String jxjdm, String xh) throws Exception {
		dao = new JxjDAO();
		return dao.getJxjxx(jxjdm, xh);
	}
	
	public boolean saveJxjxx(JxjModel model, HttpServletRequest request)
	throws Exception {
		dao = new JxjDAO();
		return dao.saveJxjxx(model, request);
	}
	
	public List<HashMap<String, String>> getJxjTitle() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "xysh", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学号","姓名", "学年", "年级","班级名称",
				"奖学金名称", "学院审核", "学校审核"};
		return dao.getQryTitle(enList, cnList);
	}
	
	public List<String[]> getJxjResult(JxjModel model) throws Exception {
		dao = new JxjDAO();
		return dao.getJxjResult(model);
	}
	
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		String[] xh = dao.getXhList(keys);
		dao.delKcb(xh);
		dao.delZpb(xh);
		return dao.jxjDel(keys, request);
	}
	
	public HashMap<String, String> viewJxjsh(String pkValue, String userType) throws Exception {
		dao = new JxjDAO();
		if (StringUtils.isEqual("xy", userType)) {
			return dao.viewJxjshByxy(pkValue);
		} else {
			return dao.viewJxjshByxx(pkValue);
		}
	}
	
	public boolean saveJxjsh(String pkValue, String userType, String yesNo,
			String yj, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		if (StringUtils.isEqual("xy", userType)) {
			return dao.saveJxjshByxy(pkValue, yesNo, yj, request);
		} else {
			return dao.saveJxjshByxx(pkValue, yesNo, yj, request);
		}
	}
	
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.viewJxjxx(pkValue);
	}
	
	public boolean modiJxjsave(String pkValue, JxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.modiJxjsave(pkValue, model, request);
	}
	
	public List<String[]> getKcList(String xh, String xn) throws Exception {
		dao = new JxjDAO();
		return dao.getKcList(xh, xn);
	}
	
	public List<String[]> getZpList(String xh, String xn) throws Exception {
		dao = new JxjDAO();
		return dao.getZpList(xh, xn);
	}
	
	/**
	 * 综合素质测评分自动计算
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpAutoCount(String xn, String xydm) throws Exception {
		dao = new JxjDAO();
		return dao.zhszcpAutoCount(xn, xydm);
	}
}
