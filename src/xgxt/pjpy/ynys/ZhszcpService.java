
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ZhszcpService extends PjpyYnysService {

	ZhszcpDAO dao = null;
	
	/**
	 * 综合测评查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhszcpTitle() throws Exception {
		dao = new ZhszcpDAO();
		String[] enList = new String[] { "pk", "rownum", "xh","xm", "xn", "bjmc",
				"sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf", "zhszcpzf", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学号","姓名", "学年", "班级名称",
				"思想道德分", "科学文化分", "身心能力分", "实践创新分", "综合分", "审核" };
		return dao.getQryTitle(enList, cnList);
	}
	
	public List<String[]> getZhszcpResult(ZhszcpModel model) throws Exception {
		dao = new ZhszcpDAO();
		return dao.getZhszcpResult(model);
	}
	
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new ZhszcpDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	public boolean saveZhszcp(ZhszcpModel model, HttpServletRequest request) throws Exception {
		dao = new ZhszcpDAO();
		return dao.saveZhszcp(model, request);
	}
	
	public HashMap<String, String> viewZhszcp(String pkValue) throws Exception {
		dao = new ZhszcpDAO();
		return dao.viewZhszcp(pkValue);
	}
	
	public boolean updateZhszcp(String pkValue, ZhszcpModel model,
			HttpServletRequest request) throws Exception {
		dao = new ZhszcpDAO();
		return dao.updateZhszcp(pkValue, model, request);
	}
	
	public String zhszcpShres(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		dao = new ZhszcpDAO();
		return dao.zhszcpShres(keys, sJg, request);
	}
}
