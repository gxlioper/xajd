package xgxt.pjpy.hhgxy.zhszcp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.pjpy.utils.PjpyDAO;

public class PjpyHhgxyZhszcpService {

	private PjpyHhgxyZhszcpDAO dao = PjpyHhgxyZhszcpDAO.getInstance();
	
	private PjpyDAO utilsDAO = new PjpyDAO();
	
	public static PjpyHhgxyZhszcpService service = new PjpyHhgxyZhszcpService();
	
	public static PjpyHhgxyZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * 德育操行分查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDycxQryTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xh", "xm", "bj", "xn", "xq", "dm", "df", "zf"};
		String[] cnList = new String[]{"pk", "行号", "学号", "姓名", "班级", "学年", "学期", "代码", "得分", "总分"};
		return utilsDAO.getQryTitle(enList, cnList);
	}
	
	/**
	 * 德育操作分查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDycxf(ZhszcpModel model) throws Exception {
		return dao.queryDycxf(model);
	}
	
	/**
	 * 德育操作分批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteDycxf(String[] keys) throws Exception {
		return dao.deleteDycxf(keys);
	}
	
	/**
	 * 德育操行分单个修改
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateDycxf(ZhszcpModel model, HttpServletRequest request)
			throws Exception {
		return dao.updateDycxf(model, request);
	}
	
	/**
	 * 德育操行分单个保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveDycxf(ZhszcpModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveDycxf(model, request);
	}
	
	/**
	 * 德育操作分修改显示信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewDycxf(String pkValue) throws Exception {
		return dao.viewDycxf(pkValue);
	}
	
	/**
	 * 操行项目列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxList() throws Exception {
		return dao.getCxList();
	}
}
