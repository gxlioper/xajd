package xgxt.dtjs.zjlg.ybdy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class YbdyService extends DtjsTyService{

	YbdyDAO dao = new YbdyDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ����Ԥ����Ա
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(YbdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(model, request);
	}

	/**
	 * Ԥ����Աת��
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(YbdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(model, request);
	}
	
	/**
	 * ���Ԥ����Ա�б�
	 */
	public ArrayList<String[]> getYbdyList(String tableName, YbdyModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getYbdyList(tableName, model, colList, map);
	}

	/**
	 * ��ö��⽻����Ϣ
	 * 
	 */
	public HashMap<String, String> getYbdyInfo(String pk) {
		return dao.getYbdyInfo(pk);
	}

	/**
	 * ɾ��Ԥ����Ա
	 */
	public boolean delYbdy(String[] key) throws Exception {
		return dao.delYbdy(key);
	}

	/**
	 * ���ѧ�����
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * ������òתΪ��ʽ��Ա
	 */
	public boolean saveZsdy(String[] key, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(key, request);
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
	
	/**
	 * ��������Ԥ����Ա�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveYbdy(SaveForm form, Object model) throws Exception {
		return dao.saveDtjs(form, model);
	}
	
	/**
	 * ����Ԥ����Ա��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateYbdy(SaveForm form, YbdyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	
	/**
	 * �������ѧ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getArrZd(String tableName, String zd, String pk,
			String[] pkValue) throws Exception {
		return dao.getArrZd(tableName, zd, pk, pkValue);
	}
}
