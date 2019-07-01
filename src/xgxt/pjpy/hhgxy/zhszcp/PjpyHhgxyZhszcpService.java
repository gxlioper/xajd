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
	 * �������зֲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDycxQryTitle() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xh", "xm", "bj", "xn", "xq", "dm", "df", "zf"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��", "����", "�÷�", "�ܷ�"};
		return utilsDAO.getQryTitle(enList, cnList);
	}
	
	/**
	 * ���������ֲ�ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDycxf(ZhszcpModel model) throws Exception {
		return dao.queryDycxf(model);
	}
	
	/**
	 * ��������������ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteDycxf(String[] keys) throws Exception {
		return dao.deleteDycxf(keys);
	}
	
	/**
	 * �������зֵ����޸�
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
	 * �������зֵ�������
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
	 * �����������޸���ʾ��Ϣ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewDycxf(String pkValue) throws Exception {
		return dao.viewDycxf(pkValue);
	}
	
	/**
	 * ������Ŀ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxList() throws Exception {
		return dao.getCxList();
	}
}
