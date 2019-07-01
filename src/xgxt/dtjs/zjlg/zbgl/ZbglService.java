package xgxt.dtjs.zjlg.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.dtjs.DtjsTyService;

public class ZbglService extends DtjsTyService{

	ZbglDAO dao = new ZbglDAO();

	/**
	 * @describe 获得党支部列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbList(String xydm) {
		return dao.getZbList(xydm);
	}

	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 保存党支部
	 */
	public boolean saveDzb(ZbglModel model) throws SQLException {
		return dao.saveDzb(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 清空党支部下属班级
	 */
	public boolean clearDzb(ZbglModel model) throws Exception {
		return dao.clearDzb(model);
	}
	
	/**
	 * @describe 获得支部成员列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbcyList(String zbdm) {
		return dao.getZbcyList(zbdm);
	}

	/**
	 * @describe 获得未分配班级列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWfpbjList(String xydm,String zydm) {
		return dao.getWfpbjList(xydm, zydm);
	}
	
	/**
	 * 获得支部信息 
	 * @author luo
	 * @throws Exception
	 */
	public ArrayList<String[]> getZbxxList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZbxxList(tableName, model, colList);
	}
	
	/**
	 * @describe 获得党支部列表(包括无所属)
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZbWssList(String xydm) {
		return dao.getZbWssList(xydm);
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 保存党支部
	 */
	public boolean saveZbmc(ZbglModel model, String pk,
			HttpServletRequest request) throws Exception {
		return dao.saveZbmc(model, pk, request);
	}
	
	/**
	 * 获得党支部信息
	 * 
	 * @author luo
	 */
	public HashMap<String, String> getZbmcInfo(String pk) {
		return dao.getZbmcInfo(pk);
	}
	
	/**
	 * 删除支部名称
	 * 
	 * @author luo
	 */
	public boolean delZbmc(String[] key) throws Exception {
		return dao.delZbmc(key);
	}
	
	/**
	 * 删除支部党员（包括正式党员和预备党员）
	 * 
	 * @author luo
	 */
	public boolean delZbdy(String[] key) throws Exception {
		return dao.delZbdy(key);
	}
}
