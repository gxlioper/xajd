package xgxt.dtjs.zjlg.pxxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.DtjsTyService;
import xgxt.utils.CommonQueryDAO;

public class PxxxService extends DtjsTyService{

	PxxxDAO dao = new PxxxDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ������ѵ��Ϣ
	 * 
	 * @throws Exception
	 */
	public boolean savePxxx(PxxxModel model, HttpServletRequest request)
			throws Exception {
		return dao.savePxxx(model, request);
	}

	/**
	 * �����ѵ��Ϣ�б�
	 */
	public ArrayList<String[]> getPxxxList(String tableName, PxxxModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getPxxxList(tableName, model, colList, map);
	}

	/**
	 * ��ö��⽻����Ϣ
	 * 
	 */
	public HashMap<String, String> getPxxxInfo(String pk) {
		return dao.getPxxxInfo(pk);
	}

	/**
	 * ɾ����ѵ��Ϣ
	 */
	public boolean delPxxx(String[] key) throws Exception {
		return dao.delPxxx(key);
	}

	/**
	 * �����ѵ��Ŀ
	 */
	public List<HashMap<String, String>> getPxxm() {
		return dao.getPxxm();
	}
}
