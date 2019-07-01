package xgxt.dtjs.zjlg.jjfz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.DtjsTyService;
import xgxt.utils.CommonQueryDAO;

public class JJfzService extends DtjsTyService{

	JjfzDAO dao = new JjfzDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * �����뵳��������
	 * 
	 * @throws Exception
	 */
	public boolean saveJjfz(JjfzModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJjfz(model, request);
	}

	/**
	 * תΪԤ����Ա
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(JjfzModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(model, request);
	}
	
	/**
	 * ����뵳���������б�
	 */
	public ArrayList<String[]> getJjfzList(String tableName, JjfzModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJjfzList(tableName, model, colList, map);
	}

	/**
	 * ��ö��⽻����Ϣ
	 * 
	 */
	public HashMap<String, String> getJjfzInfo(String pk) {
		return dao.getJjfzInfo(pk);
	}

	/**
	 * ɾ���뵳��������
	 */
	public boolean delJjfz(String[] key) throws Exception {
		return dao.delJjfz(key);
	}

	/**
	 * ���ѧ�����
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * ������òתΪԤ����Ա
	 */
	public boolean saveYbdy(String[] key, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(key, request);
	 }
	
	/**
	 * �����ϵ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getLxrInfo(String xh) {
		return dao.getLxrInfo(xh);
	}
	
	/**
	 * ���Υ�����
	 * 
	 * @author luojw
	 */
	public String getWjqk(String xh) {
		return dao.getWjqk(xh);
	}
}
