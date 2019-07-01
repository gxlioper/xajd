package xgxt.dtjs.zjlg.zsdy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class ZsdyService extends DtjsTyService{

	ZsdyDAO dao = new ZsdyDAO();

	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * 通用方法
	 * 获取学生相关信息
	 * @param xh 学号
	 * @return
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}
	
	/**
	 * 保存正式党员
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(ZsdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZsdy(model, request);
	}

	/**
	 * 组织关系转接
	 * 
	 * @throws Exception
	 */
	public boolean saveZzgx(ZsdyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZzgx(model, request);
	}
	
	/**
	 * 获得正式党员列表
	 */
	public ArrayList<String[]> getZsdyList(String tableName, ZsdyModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZsdyList(tableName, model, colList, map);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getZsdyInfo(String pk) {
		return dao.getZsdyInfo(pk);
	}

	/**
	 * 删除正式党员
	 */
	public boolean delZsdy(String[] key) throws Exception {
		return dao.delZsdy(key);
	}

	/**
	 * 获得学生层次
	 */
	public List<HashMap<String, String>> getXscc() {
		return dao.getXscc();
	}
	
	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, ZsdyModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDyxxList(tableName, model, colList);
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
	 * 批量保存正式党员相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveZsdy(SaveForm form, Object model) throws Exception {
		return dao.saveDtjs(form, model);
	}
	
	/**
	 * 更新正式党员信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateZsdy(SaveForm form, ZsdyModel model) throws Exception {
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
