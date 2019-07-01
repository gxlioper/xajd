package xgxt.pjpy.zgms;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class KkjlService {

	private KkjlDAO dao;
	
	/**
	 * 查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> title() throws Exception {
		dao = new KkjlDAO();
		return dao.title();
	}
	
	/**
	 * 查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> result(ZgmsModel model) throws Exception {
		dao = new KkjlDAO();
		return dao.result(model);
	}
	
	/**
	 * 保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean save(ZgmsModel model, HttpServletRequest request) throws Exception {
		dao = new KkjlDAO();
		return dao.save(model, request);
	}
	
	/**
	 * 修改
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean update(ZgmsModel model, HttpServletRequest request, String pkValue) throws Exception {
		dao = new KkjlDAO();
		return dao.update(model, request, pkValue);
	}
	
	/**
	 * 删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delete(String[] keys, HttpServletRequest request) throws Exception {
		dao = new KkjlDAO();
		return dao.delete(keys, request);
	}
	
	public HashMap<String, String> getStuinfo(String xh) throws Exception {
		dao = new KkjlDAO();
		return dao.getStuinfo(xh);
	}
	
	/**
	 * 旷课详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKkjlinfo(String pkValue) throws Exception {
		dao = new KkjlDAO();
		return dao.getKkjlinfo(pkValue);
	}
	
	public List<HashMap<String, String>> cjTitle() throws Exception {
		dao = new KkjlDAO();
		return dao.cjTitle();
	}
	
	public List<String[]> cjResultNum(ZgmsModel model) throws Exception {
		dao = new KkjlDAO();
		return dao.cjResultNum(model);
	}
	
	/**
	 * 成绩查询记录数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cjResult(ZgmsModel model,
			PjpyZgmsActionForm dataSearchForm) throws Exception {
		return dao.cjResult(model, dataSearchForm);
	}
	
	public HashMap<String, String> cjView(String pkValue) throws Exception {
		dao = new KkjlDAO();
		return dao.cjView(pkValue);
	}
	
	public List<HashMap<String, String>> tydbTitle() throws Exception {
		dao = new KkjlDAO();
		return dao.tydbTitle();
	}
	
	public List<String[]> tydbResult(ZgmsModel model) throws Exception {
		dao = new KkjlDAO();
		return dao.tydbResult(model);
	}
	
	public List<HashMap<String, String>> tydbList() throws Exception {
		dao = new KkjlDAO();
		return dao.tydbList();
	}
	
	public HashMap<String, String> viewTydbinfo(String pkValue) throws Exception {
		dao = new KkjlDAO();
		return dao.viewTydbinfo(pkValue);
	}
	
	/**
	 * 删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteTydb(String[] keys, HttpServletRequest request) throws Exception {
		dao = new KkjlDAO();
		return dao.deleteTydb(keys, request);
	}
	
	public boolean updateTydb(String pkValue, ZgmsModel model,
			HttpServletRequest request) throws Exception {
		dao = new KkjlDAO();
		return dao.updateTydb(pkValue, model, request);
	}
	
	public boolean cjtb(String xn) throws Exception{
		dao = new KkjlDAO();
		return dao.cjtb(xn);
	}
	
	public HashMap<String, String> ryPrint(String pkValue) {
		dao = new KkjlDAO();
		return dao.ryPrint(pkValue);
	}
	
	public HashMap<String, String> rychPrint(String pkValue) {
		dao = new KkjlDAO();
		return dao.rychPrint(pkValue);
	}
}
