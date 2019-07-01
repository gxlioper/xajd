package xgxt.studentInfo.zjlg.dwjl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.CommonQueryDAO;

public class DwjlService {

	DwjlDAO dao = new DwjlDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 保存对外交流
	 * 
	 * @throws Exception
	 */
	public boolean saveDwjl(DwjlModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveDwjl(model, request);
	}

	/**
	 * 获得对外交流列表
	 */
	public ArrayList<String[]> getDwjlList(String tableName, DwjlModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDwjlList(tableName, model, colList);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getDwjlInfo(String pk) {
		return dao.getDwjlInfo(pk);
	}

	/**
	 * 删除对外交流
	 */
	public boolean delDwgx(String[] key) throws Exception {
		return dao.delDwgx(key);
	}

	/**
	 * 获得交流项目
	 */
	public List<HashMap<String, String>> getJlxm() {
		return dao.getJlxm();
	}
}
