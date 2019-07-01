package xgxt.dtjs.zjlg.zsdy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class ZsdyService extends DtjsTyService{

	ZsdyDAO dao = new ZsdyDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ͨ�÷���
	 * ��ȡѧ�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}
	
	/**
	 * ������ʽ��Ա
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(ZsdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(model, request);
	}

	/**
	 * ��֯��ϵת��
	 * 
	 * @throws Exception
	 */
	public boolean saveZzgx(ZsdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZzgx(model, request);
	}
	
	/**
	 * �����ʽ��Ա�б�
	 */
	public ArrayList<String[]> getZsdyList(String tableName, ZsdyModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZsdyList(tableName, model, colList, map);
	}

	/**
	 * ��ö��⽻����Ϣ
	 * 
	 */
	public HashMap<String, String> getZsdyInfo(String pk) {
		return dao.getZsdyInfo(pk);
	}

	/**
	 * ɾ����ʽ��Ա
	 */
	public boolean delZsdy(String[] key) throws Exception {
		return dao.delZsdy(key);
	}

	/**
	 * ���ѧ�����
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * ��õ�Ա����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, ZsdyModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDyxxList(tableName, model, colList);
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
	 * ����������ʽ��Ա�����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveZsdy(SaveForm form, Object model) throws Exception {
		return dao.saveDtjs(form, model);
	}
	
	/**
	 * ������ʽ��Ա��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateZsdy(SaveForm form, ZsdyModel model) throws Exception {
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
