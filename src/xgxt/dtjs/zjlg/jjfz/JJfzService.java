package xgxt.dtjs.zjlg.jjfz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.DtjsTyService;
import xgxt.utils.CommonQueryDAO;

public class JJfzService extends DtjsTyService{

	JjfzDAO dao = new JjfzDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 保存入党积极分子
	 * 
	 * @throws Exception
	 */
	public boolean saveJjfz(JjfzModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJjfz(model, request);
	}

	/**
	 * 转为预备党员
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(JjfzModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(model, request);
	}
	
	/**
	 * 获得入党积极分子列表
	 */
	public ArrayList<String[]> getJjfzList(String tableName, JjfzModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJjfzList(tableName, model, colList, map);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getJjfzInfo(String pk) {
		return dao.getJjfzInfo(pk);
	}

	/**
	 * 删除入党积极分子
	 */
	public boolean delJjfz(String[] key) throws Exception {
		return dao.delJjfz(key);
	}

	/**
	 * 获得学生层次
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * 政治面貌转为预备党员
	 */
	public boolean saveYbdy(String[] key, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(key, request);
	 }
	
	/**
	 * 获得联系人信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getLxrInfo(String xh) {
		return dao.getLxrInfo(xh);
	}
	
	/**
	 * 获得违纪情况
	 * 
	 * @author luojw
	 */
	public String getWjqk(String xh) {
		return dao.getWjqk(xh);
	}
}
