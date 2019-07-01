package xgxt.studentInfo.zjlg.rwxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.CommonQueryDAO;

public class RwxxService {

	RwxxDAO dao = new RwxxDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @throws Exception
	 */
	public boolean saveRwxx(RwxxModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveRwxx(model, request);
	}

	/**
	 * ���������Ϣ�б�
	 */
	public ArrayList<String[]> getRwxxList(String tableName, RwxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getRwxxList(tableName, model, colList);
	}

	/**
	 * ���������Ϣ
	 * 
	 */
	public HashMap<String, String> getRwxx(String pk) {
		return dao.getRwxx(pk);
	}

	/**
	 * ɾ��������Ϣ
	 */
	public boolean delRwxx(String[] key) throws Exception {
		return dao.delRwxx(key);
	}

	/**
	 * ��ý�����Ŀ
	 */
	public List<HashMap<String, String>> getJlxm() {
		return dao.getJlxm();
	}
}
