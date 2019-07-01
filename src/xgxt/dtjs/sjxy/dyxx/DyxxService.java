package xgxt.dtjs.sjxy.dyxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class DyxxService {

	DyxxDAO dao = new DyxxDAO();

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
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}
	
	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, DyxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getDyxxList(tableName, model, colList);
	}

	/**
	 * 获得党员相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getDyxxInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 删除党员相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}

	/**
	 * 批量保存党员信息相关信息
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
	public boolean saveDyxx(SaveForm form, DyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存党员信息相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveDyxx(SaveForm form, DyxxModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}
	
	/**
	 * 保存所属支部
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveSszb(DyxxModel model,
			HttpServletRequest request) throws Exception {
		
		String realTable = "sjxy_sszbb";
		String[] onezd = new String[] { "xn", "xq", "xh", "xydm", "zbmc", "lx" };
		String pk = "xn||xq||xh||xydm||lx";
		String pkValue = model.getXn() + model.getXq() + model.getXh() + model.getXydm() + model.getLx();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		
		return saveDyxx(saveForm, model, request);
	}
	
	/**
	 * 删除所属支部
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delSszb(DyxxModel model, String[] checkVal) throws Exception {

		String realTable = "sjxy_sszbb";
		String pk = "xn||xq||xh||lx";
		String[] pkValue = new String[checkVal.length];

		for (int i = 0; i < checkVal.length; i++) {
			pkValue[i] = checkVal[i] + model.getLx();
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delDyxx(saveForm, model);
	}

	/**
	 * 修改党员类型
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(DyxxModel model,
			HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String lx = model.getLx();
		if ("积极分子".equalsIgnoreCase(lx)) {
			flag = dao.saveYbdy(model, request);
		} else if ("预备党员".equalsIgnoreCase(lx)) {
			flag = dao.saveZsdy(model, request);
		}
		if (flag) {
			flag = dao.saveZbmc(model, request);
		}
		return flag;
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(String[] key, String lx,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		if ("积极分子".equalsIgnoreCase(lx)) {
			flag = dao.saveYbdy(key, request);
		} else if ("预备党员".equalsIgnoreCase(lx)) {
			flag = dao.saveZsdy(key, request);
		}
		if (flag) {
			flag = dao.saveZbmc(key, lx, request);
		}
		return flag;
	}
}
