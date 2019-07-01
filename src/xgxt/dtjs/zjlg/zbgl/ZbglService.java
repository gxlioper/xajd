package xgxt.dtjs.zjlg.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.DtjsTyService;

public class ZbglService extends DtjsTyService{

	ZbglDAO dao = new ZbglDAO();

	/**
	 * @describe ��õ�֧���б�
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbList(String xydm) {
		return dao.getZbList(xydm);
	}

	/**
	 * @author luo
	 * @throws SQLException
	 * @describe ���浳֧��
	 */
	public boolean saveDzb(ZbglModel model) throws SQLException {
		return dao.saveDzb(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��յ�֧�������༶
	 */
	public boolean clearDzb(ZbglModel model) throws Exception {
		return dao.clearDzb(model);
	}
	
	/**
	 * @describe ���֧����Ա�б�
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbcyList(String zbdm) {
		return dao.getZbcyList(zbdm);
	}

	/**
	 * @describe ���δ����༶�б�
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWfpbjList(String xydm,String zydm) {
		return dao.getWfpbjList(xydm, zydm);
	}
	
	/**
	 * ���֧����Ϣ 
	 * @author luo
	 * @throws Exception
	 */
	public ArrayList<String[]> getZbxxList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZbxxList(tableName, model, colList);
	}
	
	/**
	 * @describe ��õ�֧���б�(����������)
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbWssList(String xydm) {
		return dao.getZbWssList(xydm);
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe ���浳֧��
	 */
	public boolean saveZbmc(ZbglModel model, String pk,
			HttpServletRequest request) throws Exception {
		return dao.saveZbmc(model, pk, request);
	}
	
	/**
	 * ��õ�֧����Ϣ
	 * 
	 * @author luo
	 */
	public HashMap<String, String> getZbmcInfo(String pk) {
		return dao.getZbmcInfo(pk);
	}
	
	/**
	 * ɾ��֧������
	 * 
	 * @author luo
	 */
	public boolean delZbmc(String[] key) throws Exception {
		return dao.delZbmc(key);
	}
	
	/**
	 * ɾ��֧����Ա��������ʽ��Ա��Ԥ����Ա��
	 * 
	 * @author luo
	 */
	public boolean delZbdy(String[] key) throws Exception {
		return dao.delZbdy(key);
	}
}
