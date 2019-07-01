package xgxt.dtjs.zjlg.zzgx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsTyService;
import xgxt.utils.CommonQueryDAO;

public class ZzgxService extends DtjsTyService{

	ZzgxDAO dao = new ZzgxDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ������֯��ϵת��
	 * 
	 * @throws Exception
	 */
	public boolean saveZzgx(ZzgxModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZzgx(model, request);
	}

	/**
	 * �����֯��ϵת���б�
	 */
	public ArrayList<String[]> getZzgxList(String tableName, ZzgxModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZzgxList(tableName, model, colList, map);
	}

	/**
	 * �����֯��ϵת����Ϣ
	 * 
	 */
	public HashMap<String, String> getZzgxInfo(String pk) {
		return dao.getZzgxInfo(pk);
	}

	/**
	 * ɾ����֯��ϵ
	 */
	public boolean delZzgx(String[] key) throws Exception {
		return dao.delZzgx(key);
	}
	
	/**
	 * ����·��б�
	 */
	public List<HashMap<String, String>> getYfList() {
		DAO dao = DAO.getInstance();
		return dao.getYfList();
	}
}
