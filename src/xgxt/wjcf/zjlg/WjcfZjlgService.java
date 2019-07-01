package xgxt.wjcf.zjlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

public class WjcfZjlgService {

	/**
	 * 处分审核查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryWjcfsbxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryWjcfsbxxTitle();
	}
	
	/**
	 * 处分审核学院查询结果记录数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int queryWjcfsbxxNum(WjcfZjlgModel model, String userType)
			throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryWjcfsbxxByXyNum(model);
		}
		return dao.queryWjcfsbxxByXxNum(model);
	}

	
	/**
	 * 处分审核学院查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> queryWjcfsbxx(WjcfZjlgModel model, WjcfZjlgActionForm form, String userType)
			throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryWjcfsbxxByXy(model, form);
		}
		return dao.queryWjcfsbxxByXx(model, form);
	}
	
	/**
	 * 处分单个审核显示详细信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryWjcfsbxxOne(String pkValue) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryWjcfsbxxOne(pkValue);
	}
	
	/**
	 * 保存单位审核信息
	 * @param pkValue
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfwhxx(String pkValue, WjcfZjlgModel model, String userType) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.saveCfwhxx(pkValue, model, userType);
	}
	
	public String ArrayToString(String[] list) {
		if (list != null && list.length > 0) {
			String rs = "";
			for (String s : list) {
				rs += s + "!@";
			}
			return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
		}
		return "";
	}
	
	/**
	 * 批量审核处分信息
	 * @param pkValue
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean savePlCfwhxx(String[] pkValue, WjcfZjlgModel model, String userType) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.savePlCfwhxx(pkValue, model, userType);
	}
	
	/**
	 * 查询留校察看表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckcfxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckcfxxTitle();
	}
	
	/**
	 * 查询留校察看结果
	 * 
	 * @param lxcksj
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResult(WjcfZjlgActionForm zjlgForm,
			String userType, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckxxResult(zjlgForm, userType, userName);
	}
	
	/**
	 * 查询留校察看结果
	 * @param lxcksj
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResultnotExis(WjcfZjlgActionForm zjlgForm, String userType, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckxxResultnotExis(zjlgForm, userType, userName);
	}
	
	/**
	 * 查询留校察看数据维护表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckxxTitle();
	}
	
	/**
	 * 查询留校察看数据记录数
	 * @param userType
	 * @param model
	 * @return
	 */
	public int queryLxckxxResultNum(String userType, WjcfZjlgModel model, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryLxckxxResultNumByxy(model, userType, userName);
		} else {
			return dao.queryLxckxxResultNum(model);
		}
	}
	
	/**
	 * 查询留校察看数据结果
	 * @param userType
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResult(WjcfZjlgActionForm zjlgForm, WjcfZjlgModel model, String userType, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryLxckxxResultByxy(model, zjlgForm, userType,userName);
		} else {
			return dao.queryLxckxxResult(model, zjlgForm);
		}
	}
	
	/**
	 * 留校察看信息单个保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfZjlgModel model) throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.saveLxcksqxx(model);
	}
	
	/**
	 * 查看留校察看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxcksqxx(String pkValue) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.viewLxcksqxx(pkValue);
	}
	
	/**
	 * 修改留校察看信息
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiLxcksqxx(String pkValue, WjcfZjlgModel model) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.modiLxcksqxx(pkValue, model);
	}
	
	/**
	 * 删除留校察看信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delLxcksqxx(String[] pkValue) throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.delLxcksqxx(pkValue);
	}
	
	/**
	 * 学校留校察看审核信息查询结果记录数
	 * @param userType
	 * @param userName
	 * @param model
	 * @return
	 */
	public int queryLxckshxxResultNum(
			WjcfZjlgModel model, String userType, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryLxckshxxResultByxyNum(userType, userName, model);
		} else {
			return dao.queryLxckshxxResultByxxNum(model);
		}
	}
	
	/**
	 * 学校留校察看审核信息查询结果
	 * @param userType
	 * @param userName
	 * @param form
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryLxckshxxResult(String userType,
			String userName, WjcfZjlgActionForm form, WjcfZjlgModel model) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryLxckshxxResultByxy(userType, form, userName, model);
		} else {
			return dao.queryLxckshxxResultByxx(form, model);
		}
	}
	
	/**
	 * 留校察看信息查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxcksqshxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxcksqshxxTitle();
	}
	
	/**
	 * 查看留校查看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxcksh(String pkValue, String userType) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.viewLxckshByxy(pkValue);
		} else {
			return dao.viewLxckshByxx(pkValue);
		}
	}
	
	/**
	 * 保存留校察看单个审核信息
	 * @param pkValue
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckDgshxx(String pkValue, String userType, WjcfZjlgModel model) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.saveLxckDgshxx(pkValue, userType, model);
	}
	
//	审核列表
	public List<HashMap<String, String>> loadShlist() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.loadShlist();
	}
//	解除处分列表
	public List<HashMap<String, String>> loadJcList() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.loadJcList();
	}
	
	/**
	 * 批量保存留校察看信息
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxx(String userType, String[] pkValues,
			WjcfZjlgModel model) throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.savePlshLxckxx(userType, pkValues, model);
	}
	
//	学生处分表打信息
	public HashMap<String, String> getCfxx(String pkValue) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.getCfxx(pkValue);
	}
}
