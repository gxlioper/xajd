package xgxt.pjpy.zjcm.xwjxj;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import xgxt.DAO.DAO;

public class XwjxjService {

	XwjxjDAO dao = new XwjxjDAO();
	XwjxjNewDAO mydao = new XwjxjNewDAO();

	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @describe ���ѧ��������Ϣ
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return XwjxjDAO.getStuInfo(xh);
	}

	/**
	 * @author luo
	 * @describe ��ý�ѧ�������б�
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XwjxjModel model,
			String[] colList, String userType) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getJxjSqList(tableName, model, colList, userType);
	}

	/**
	 * ����걨��ѧ����Ϣ
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbJxjXx(String pk) {
		return dao.getSbJxjXx(pk);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveJxjsh(XwjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {
		return dao.saveJxjsh(model, pk, shzt, userType, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {
		return dao.saveJxjsh(key, jxjdm, shzt, userType);
	}

	/**
	 * ��ý�ѧ���б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		return dao.getJxjList(jxjlb);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ý�ѧ�����
	 */
	public String getJxjlb(String jxjdm) {
		return dao.getJxjlb(jxjdm);
	}

	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ��ý�ѧ����������
	 */
	public String getJxjRs(String jxjdm) {
		return dao.getJxjRs(jxjdm);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ý�ѧ������
	 */
	public String getHdJxjRs(String jxjdm) {
		return dao.getHdJxjRs(jxjdm);
	}

	/**
	 * ���Υ�ʹ����б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return dao.getWjcfList(xh);
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
	
	/**
	 * ���Ӣ��ȼ������б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYyList() {
		return dao.getYyList();
	}
	
	/**
	 * ��ü�����ȼ��б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		return dao.getJsjList();
	}
	
	/*****************���� ����  2010-4-22************/
	public List<HashMap<String, String>> queryXwjxjshTitle() {
		String[] en = new String[] { "pk","color", "r", "xh", "xm", "bjmc", "xn", "xq",
				"jxjmc", "dypm", "zypm", "typm", "zhpm", "xxsh" };
		String[] cn = new String[] { "pk","color", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
				"��ѧ��", "��������", "��������", "��������", "�۲�����", "ѧУ���" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯУ�⽱ѧ����˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXwjxjshResult(XwjxjModel model) throws Exception {
		return mydao.queryXwjxjshResult(model);
	}
	
	/**
	 * �޸�У�⽱ѧ����˽��
	 * @param pk
	 * @param jg
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjshJg(String[] pk, String jg) throws Exception {
		return mydao.updateXwjxjshJg(pk, jg);
	}
	
	/**
	 * ��ѯ����У�⽱�����Ϣ
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXwjxjDgxx(String pkValue) {
		return mydao.queryXwjxjDgxx(pkValue);
	}
	
	/**
	 * �޸ĵ���У�⽱ѧ�������Ϣ
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjDgxx(XwjxjModel model, String pkValue)
			throws Exception {
		return mydao.updateXwjxjDgxx(model, pkValue);
	}
}
