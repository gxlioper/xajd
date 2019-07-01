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
	 * 保存入伍信息
	 * 
	 * @throws Exception
	 */
	public boolean saveRwxx(RwxxModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveRwxx(model, request);
	}

	/**
	 * 获得入伍信息列表
	 */
	public ArrayList<String[]> getRwxxList(String tableName, RwxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getRwxxList(tableName, model, colList);
	}

	/**
	 * 获得入伍信息
	 * 
	 */
	public HashMap<String, String> getRwxx(String pk) {
		return dao.getRwxx(pk);
	}

	/**
	 * 删除入伍信息
	 */
	public boolean delRwxx(String[] key) throws Exception {
		return dao.delRwxx(key);
	}

	/**
	 * 获得交流项目
	 */
	public List<HashMap<String, String>> getJlxm() {
		return dao.getJlxm();
	}
}
