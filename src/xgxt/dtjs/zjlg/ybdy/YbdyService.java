package xgxt.dtjs.zjlg.ybdy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class YbdyService extends DtjsTyService{

	YbdyDAO dao = new YbdyDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 保存预备党员
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(YbdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveYbdy(model, request);
	}

	/**
	 * 预备党员转正
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(YbdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(model, request);
	}
	
	/**
	 * 获得预备党员列表
	 */
	public ArrayList<String[]> getYbdyList(String tableName, YbdyModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getYbdyList(tableName, model, colList, map);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getYbdyInfo(String pk) {
		return dao.getYbdyInfo(pk);
	}

	/**
	 * 删除预备党员
	 */
	public boolean delYbdy(String[] key) throws Exception {
		return dao.delYbdy(key);
	}

	/**
	 * 获得学生层次
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * 政治面貌转为正式党员
	 */
	public boolean saveZsdy(String[] key, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(key, request);
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
	
	/**
	 * 批量保存预备党员相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveYbdy(SaveForm form, Object model) throws Exception {
		return dao.saveDtjs(form, model);
	}
	
	/**
	 * 更新预备党员信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateYbdy(SaveForm form, YbdyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	
	/**
	 * 获得批量学号
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getArrZd(String tableName, String zd, String pk,
			String[] pkValue) throws Exception {
		return dao.getArrZd(tableName, zd, pk, pkValue);
	}
}
