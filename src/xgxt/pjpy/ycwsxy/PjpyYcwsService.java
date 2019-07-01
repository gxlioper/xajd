package xgxt.pjpy.ycwsxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PjpyYcwsService {

	private PjpyYcwsDAO dao = PjpyYcwsDAO.getInstance();
	
	private static PjpyYcwsService service = new PjpyYcwsService();
	
	public static PjpyYcwsService getInstance() {
		return service;
	}
	
	public List<String[]> getBjjxhjList(PjpyYcwsModel model, PjpyYcwsActionForm dataSearchForm) throws Exception {
		return dao.getBjjxhjList(model, dataSearchForm);
	}
	
	public boolean addBjjxhjxx(PjpyYcwsModel model, HttpServletRequest request) {
		return dao.addBjjxhjxx(model, request);
	}
	
	public boolean updateBjjxhjxx(String pkValue, PjpyYcwsModel model,
			HttpServletRequest request) throws Exception {
		return dao.updateBjjxhjxx(pkValue, model, request);
	}
	
	public String deleteBjjxhjxx(String[] keys) throws Exception {
		return dao.deleteBjjxhjxx(keys);
	}
	
	public HashMap<String, String> viewBjjxhjxx(String pkValue) {
		return dao.viewBjjxhjxx(pkValue);
	}
	
	public List<HashMap<String, String>> queryBjjxhjTitle() {
		String[] enList = new String[]{"pk", "r", "xn", "xymc", "zymc", "bjmc", "jxhjmc"};
		String[] cnList = new String[]{"pk", "行号", "学年", "学院", "专业", "班级", "军训获奖名称"};
		return dao.getTitleList(enList, cnList);
	}
	public int getBjjxhjListNum(PjpyYcwsModel model) throws Exception {
		return dao.getBjjxhjListNum(model);
	}
}
