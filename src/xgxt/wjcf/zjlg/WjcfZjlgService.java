package xgxt.wjcf.zjlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

public class WjcfZjlgService {

	/**
	 * ������˲�ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryWjcfsbxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryWjcfsbxxTitle();
	}
	
	/**
	 * �������ѧԺ��ѯ�����¼��
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
	 * �������ѧԺ��ѯ���
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
	 * ���ֵ��������ʾ��ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryWjcfsbxxOne(String pkValue) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryWjcfsbxxOne(pkValue);
	}
	
	/**
	 * ���浥λ�����Ϣ
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
	 * ������˴�����Ϣ
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
	 * ��ѯ��У�쿴��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckcfxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckcfxxTitle();
	}
	
	/**
	 * ��ѯ��У�쿴���
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
	 * ��ѯ��У�쿴���
	 * @param lxcksj
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResultnotExis(WjcfZjlgActionForm zjlgForm, String userType, String userName) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckxxResultnotExis(zjlgForm, userType, userName);
	}
	
	/**
	 * ��ѯ��У�쿴����ά����ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxckxxTitle();
	}
	
	/**
	 * ��ѯ��У�쿴���ݼ�¼��
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
	 * ��ѯ��У�쿴���ݽ��
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
	 * ��У�쿴��Ϣ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfZjlgModel model) throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.saveLxcksqxx(model);
	}
	
	/**
	 * �鿴��У�쿴��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxcksqxx(String pkValue) {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.viewLxcksqxx(pkValue);
	}
	
	/**
	 * �޸���У�쿴��Ϣ
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
	 * ɾ����У�쿴��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delLxcksqxx(String[] pkValue) throws Exception {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.delLxcksqxx(pkValue);
	}
	
	/**
	 * ѧУ��У�쿴�����Ϣ��ѯ�����¼��
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
	 * ѧУ��У�쿴�����Ϣ��ѯ���
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
	 * ��У�쿴��Ϣ��ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryLxcksqshxxTitle() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.queryLxcksqshxxTitle();
	}
	
	/**
	 * �鿴��У�鿴��Ϣ
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
	 * ������У�쿴���������Ϣ
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
	
//	����б�
	public List<HashMap<String, String>> loadShlist() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.loadShlist();
	}
//	��������б�
	public List<HashMap<String, String>> loadJcList() {
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.loadJcList();
	}
	
	/**
	 * ����������У�쿴��Ϣ
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
	
//	ѧ�����ֱ����Ϣ
	public HashMap<String, String> getCfxx(String pkValue) throws Exception{
		WjcfZjlgDAO dao = new WjcfZjlgDAO();
		return dao.getCfxx(pkValue);
	}
}
