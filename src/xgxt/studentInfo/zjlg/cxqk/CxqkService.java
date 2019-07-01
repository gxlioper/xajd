package xgxt.studentInfo.zjlg.cxqk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.CommonQueryDAO;

public class CxqkService {

	CxqkDAO dao = new CxqkDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ����������
	 * 
	 * @throws Exception
	 */
	public boolean saveCxqk(CxqkModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveCxqk(model, request);
	}

	/**
	 * ��ó�������б�
	 */
	public ArrayList<String[]> getCxqkList(String tableName, CxqkModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getCxqkList(tableName, model, colList);
	}

	/**
	 * ��ó������
	 * 
	 */
	public HashMap<String, String> getCxqk(String pk) {
		return dao.getCxqk(pk);
	}

	/**
	 * ɾ���������
	 */
	public boolean delCxqk(String[] key) throws Exception {
		return dao.delCxqk(key);
	}
}
