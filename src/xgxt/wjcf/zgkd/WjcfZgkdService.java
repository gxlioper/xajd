package xgxt.wjcf.zgkd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class WjcfZgkdService {

	private WjcfZgkdDAO dao = WjcfZgkdDAO.getInstance();
	
	public static WjcfZgkdService service = new WjcfZgkdService();
	
	public static WjcfZgkdService getInstance() {
		return service;
	}
	
	public List<HashMap<String, String>> getGzjyTitle() {
		return dao.getGzjyTitle();
	}
	
	public List<String[]> getGzjyResult(WjcfZgkdModel model) {
		return dao.getGzjyResult(model);
	}
	
	public boolean gzjyAdd(WjcfZgkdModel model, HttpServletRequest request)
	throws Exception {
		return dao.gzjyAdd(model, request);
	}
	
	public boolean gzjyUpdate(WjcfZgkdModel model, HttpServletRequest request) throws Exception {
		return dao.gzjyUpdate(model, request);
	}
	
	public HashMap<String, String> gzjyView(String pkValue) throws Exception {
		return dao.gzjyView(pkValue);
	}
	
	public String gzjyDelete(String[] keys) throws Exception {
		return dao.gzjyDelete(keys);
	}
	
	public List<HashMap<String, String>> getStuwjcfInfo() {
		return dao.getStuwjcfInfo();
	}
	
	public List<String[]> getStuwjcfResult(WjcfZgkdModel model) {
		return dao.getStuwjcfResult(model);
	}
	
	public HashMap<String, String> getStuwjcfByPk(String pkValue) throws Exception {
		return dao.getStuwjcfByPk(pkValue);
	}
}
