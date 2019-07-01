package xgxt.pjpy.zgms;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class KkjlService {

	private KkjlDAO dao;
	
	/**
	 * ��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> title() throws Exception {
		dao = new KkjlDAO();
		return dao.title();
	}
	
	/**
	 * ��ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> result(ZgmsModel model) throws Exception {
		dao = new KkjlDAO();
		return dao.result(model);
	}
	
	/**
	 * ����
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
	 * �޸�
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
	 * ɾ��
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
	 * ������ϸ��Ϣ
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
	 * �ɼ���ѯ��¼��
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
	 * ɾ��
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
