package xgxt.dtjs.sjxy.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;

public class ZbglService {

	ZbglDAO dao = new ZbglDAO();

	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(SjxyDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		dao.setList(myForm, request);
	}
	
	/**
	 * 获得支部管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getZbglList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getZbglList(tableName, model, colList);
	}
	
	/**
	 * 获得支部管理相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZbglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getZbglInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * 删除支部管理相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delZbgl(SaveForm form, ZbglModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}
	
	/**
	 * 批量保存支部管理相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveZbgl(SaveForm form, ZbglModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * 保存支部管理相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveZbgl(SaveForm form, ZbglModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}
}
