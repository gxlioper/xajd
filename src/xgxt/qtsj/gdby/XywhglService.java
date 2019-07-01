package xgxt.qtsj.gdby;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.dtjs.czxx.dyxx.DyxxDAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class XywhglService {

	/**
	 * У԰�ά��
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertRocord(XywhglModel model) {
		return insertXyglb(model) && insertCdb(model) && insertWzb(model);

	}
	
	/**
	 * У԰�����
	 * @param model
	 * @return
	 */
	public boolean updateRecord(XywhglModel model){
		return updateGlb(model) && insertCdb(model) && insertWzb(model);
	}

	/**
	 * ����ɾ������
	 * @param pkValues
	 * @return
	 */
	public boolean delRecord(String[] pkValues){
		return delGlb(pkValues) && delWzb(pkValues) && delCdb(pkValues);
	}
	
	public boolean delGlb(String[] pkValues){
		String tableName = "gdby_xywhglb";
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("hddm");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName(tableName);
		DAO dao = new DAO();
		boolean flag = false;

		try {
			for(int i=0;i<pkValues.length;i++){
				delFile(pkValues[i]);
			}
			dao.delDate(saveForm, null);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����ɾ�����ر�����
	 * @param pkValues
	 * @return
	 */
	public boolean delCdb(String[] pkValues){
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("hddm");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("gdby_xywhcdb");
		DAO dao = new DAO();
		boolean flag = false;

		try {
			dao.delDate(saveForm, null);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����ɾ�����ʱ�����
	 * @param pkValues
	 * @return
	 */
	public boolean delWzb(String[] pkValues){
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("hddm");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("gdby_xywhwzb");
		DAO dao = new DAO();
		boolean flag = false;

		try {
			dao.delDate(saveForm, null);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ��ȡ���е�У԰�Ļ������Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getXywhList() {
		String[] output = new String[] { "hddm", "hdmc","fj" };
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"gdby_xywhglb", "", new String[] {}, output, "");
		return list;
	}

	/**
	 * ����У԰������
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertXyglb(XywhglModel model) {
		boolean flag = false;

		DAO dao = DAO.getInstance();
		String sql = "insert into gdby_xywhglb(hddm,hdmc,hdms,fj) values(?,?,?,?)";
		String[] input = new String[] { model.getHddm(), model.getHdmc(),
				model.getHdms(), model.getFj() };
		try {
			dao.insert(sql, input);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * �������£�����У԰�Ļ����ر�
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertCdb(XywhglModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String[] arrzd = new String[] { "cdmc" };
		String[] onezd = new String[] { "hddm", "hdmc" };
		String hddm = model.getHddm();
		String pk = "hddm";
		String[] pkValue = new String[] { hddm };

		// �������
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("gdby_xywhcdb");

		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean insertWzb(XywhglModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String[] arrzd = new String[] { "wzmc" };
		String[] onezd = new String[] { "hddm", "hdmc" };
		String hddm = model.getHddm();
		String pk = "hddm";
		String[] pkValue = new String[] { hddm };

		// �������
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("gdby_xywhwzb");

		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ��ȡ����У԰�Ļ����Ϣ
	 * 
	 * @param hddm
	 * @return
	 */
	public Map<String, String> getXywhxxOne(String hddm) {
		String[] output = new String[] { "hddm", "hdmc", "hdms", "fj" };

		Map<String, String> map = CommonQueryDAO.commonQueryOne("gdby_xywhglb",
				output, "hddm", hddm);
		return map;
	}

	/**
	 * ����У԰������
	 * 
	 * @return
	 */
	public boolean insertSqb(XywhglModel model) {
		boolean flag = false;

		DAO dao = DAO.getInstance();

		String sql = "insert into gdby_xywhsqb(username,hddm,lxdh,hdmc,cdmc,wzmc,stmc,sqly) values(?,?,?,?,?,?,?,?)";
		String[] input = new String[] { model.getUsername(), model.getHddm(),
				model.getLxdh(), model.getHdmc(), model.getCdmc()[0],
				model.getWzmc()[0], model.getStmc(), model.getSqly() };
		try {
			dao.insert(sql, input);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateGlb(XywhglModel model){
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		
		String sql = "update gdby_xywhglb set hdmc=?,hdms=?,fj=? where hddm=?";
		
		String[] input = new String[]{model.getHdmc(), model.getHdms(),model.getFj(), model.getHddm()};
		
		try {
			dao.runUpdate2(sql, input);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ����У԰������
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateSqb(XywhglModel model) {
		// �жϲ����ɹ�����ʶ��
		boolean flag = false;

		DAO dao = DAO.getInstance();

		String sql = "update gdby_xywhsqb set username=?,hddm=?,lxdh=?,hdmc=?,cdmc=?,wzmc=?,stmc=?,sqly=?,xxsh=?,xxshyj=?,xxshsj=?";
		sql += " where username=? and hddm=?";

		String[] input = new String[] { model.getUsername(), model.getHddm(),
				model.getLxdh(), model.getHdmc(), model.getCdmc()[0],
				model.getWzmc()[0], model.getStmc(), model.getSqly(),
				model.getXxsh(), model.getXxshyj(), model.getXxshsj(),
				model.getUsername(), model.getHddm() };

		try {
			dao.runUpdate2(sql, input);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 */
	public Map<String, String> getSqxxOne(String pkValue) {
		String[] output = new String[] { "username", "hddm", "lxdh", "hdmc",
				"cdmc", "wzmc", "sqly", "xxsh", "xxshyj", "xxshsj", "stmc"};
		Map<String, String> map = CommonQueryDAO.commonQueryOne("gdby_xywhsqb",
				output, "username||hddm", pkValue);
		return map;
	}

	/**
	 * ��ȡ�����û���������Ϣ
	 * 
	 * @param username
	 * @return
	 */
	public List<HashMap<String, String>> getSqxxByUser(String username) {
		String[] output = new String[] { "username", "hddm", "hdmc","lxdh","cdmc",
				"wzmc", "xxsh" };
		String query = " where username=?";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"gdby_xywhsqb", query, new String[] { username }, output, "");
		return list;
	}

	/**
	 * �Ƿ������
	 * 
	 * @param pkValue
	 * @return
	 */
	public boolean isAuditing(String pkValue) {
		boolean flag = false;
		String[] output = new String[] { "xxsh" };
		Map<String, String> map = CommonQueryDAO.commonQueryOne("gdby_xywhsqb",
				output, "username||hddm", pkValue);
		if (map != null && "ͨ��".equalsIgnoreCase(map.get("xxsh"))) {
			flag = true;
		}

		return flag;
	}

	/**
	 * ĳ������п����볡��
	 * 
	 * @param hddm
	 * @return
	 */
	public List<HashMap<String, String>> getCdList(String hddm) {
		String[] output = new String[] { "cdmc" };
		String query = " where hddm=?";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"gdby_xywhcdb", query, new String[] { hddm }, output, "");

		return list;
	}

	/**
	 * ĳ������п���������
	 * 
	 * @param hddm
	 * @return
	 */
	public List<HashMap<String, String>> getWzList(String hddm) {
		String[] output = new String[] { "wzmc" };
		String query = " where hddm=?";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"gdby_xywhwzb", query, new String[] { hddm }, output, "");

		return list;
	}
	
	/**
	 * ɾ����ظ���
	 * @param hddm
	 * @return
	 */
	public boolean delFile(String hddm){
		DyxxDAO dyxxDao = new DyxxDAO();
		DAO dao = new DAO();
		boolean flag = false;
		
		String tableName = "gdby_xywhglb";
		try {
			dyxxDao.fileDel(tableName, "fj", "hddm", hddm);
			String sql = "update gdby_xywhglb set fj=? where hddm=?";
			dao.runUpdate2(sql, new String[]{"",hddm});
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
