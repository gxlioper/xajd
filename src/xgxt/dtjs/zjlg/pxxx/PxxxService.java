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
	 * 保存培训信息
	 * 
	 * @throws Exception
	 */
	public boolean savePxxx(PxxxModel model, HttpServletRequest request)
			throws Exception {
		return dao.savePxxx(model, request);
	}

	/**
	 * 获得培训信息列表
	 */
	public ArrayList<String[]> getPxxxList(String tableName, PxxxModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getPxxxList(tableName, model, colList, map);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getPxxxInfo(String pk) {
		return dao.getPxxxInfo(pk);
	}

	/**
	 * 删除培训信息
	 */
	public boolean delPxxx(String[] key) throws Exception {
		return dao.delPxxx(key);
	}

	/**
	 * 获得培训项目
	 */
	public List<HashMap<String, String>> getPxxm() {
		return dao.getPxxm();
	}
}
