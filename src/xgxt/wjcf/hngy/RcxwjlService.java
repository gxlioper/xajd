
package xgxt.wjcf.hngy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RcxwjlService {

	private RcxwjlDAO dao;
	
	/**
	 * 获取WHERE语句公用方法
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 */
	public List<HashMap<String, String>> title() throws Exception {
		dao = new RcxwjlDAO();
		return dao.title();
	}
	
	public List<String[]> result(RcxwjlModel model) throws Exception {
		dao = new RcxwjlDAO();
		return dao.result(model);
	}
	
	public HashMap<String, String> stuinfo(String xh) throws Exception {
		dao = new RcxwjlDAO();
		return dao.stuinfo(xh);
	}
	
	public boolean save(RcxwjlModel model, HttpServletRequest request)
	throws Exception {
		dao = new RcxwjlDAO();
		return dao.save(model, request);
	}
	
	public HashMap<String, String> view(String pkValue) throws Exception {
		dao = new RcxwjlDAO();
		return dao.view(pkValue);
	}
	
	public boolean update(String pkValue, RcxwjlModel model, HttpServletRequest request) throws Exception {
		dao = new RcxwjlDAO();
		return dao.update(pkValue, model, request);
	}
	
	public String delete(String[] keys, HttpServletRequest request) throws Exception {
		dao = new RcxwjlDAO();
		return dao.delete(keys, request);
	}
}
