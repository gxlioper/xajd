package xgxt.studentInfo.sjxy.shgz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.form.SaveForm;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description:����ѧԺ��Ṥ������Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-19
 */
public class ShgzSjxyService {
	/**
	 * ͨ��ѧ�Ż��ѧ����Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh) {
		String[] colList = new String[] { "xh", "xm", "xb", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "sjhm" };
		Map<String, String> map = CommonQueryDAO.commonQueryOne("view_xsxxb",
				colList, "xh", xh);

		// ��ȡ��ѧ������Ա����
		getFdyxm(xh, map);
		return map;
	}

	/**
	 * ��ȡ����Ա����
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */

	private Map<String, String> getFdyxm(String xh, Map<String, String> map) {
		CommonDAO dao = new CommonDAO();
		String[] fdyxms = dao.getFdyBzrXmForXh(xh, "fdy");
		if (fdyxms != null) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < fdyxms.length; i++) {
				if (fdyxms[i] != null) {
					builder.append(fdyxms[i] + "\t");
				}
			}

			String fdyxm = builder.toString();
			if (!StringUtils.isNull(fdyxm)) {
				map.put("fdyxm", fdyxm);
			}
		}
		return map;
	}

	/**
	 * ��ȡ��Ṥ����Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, String> getShgzInfo(String xh) {
		String[] colList = new String[] { "xh", "xm", "xb", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "sjhm", "ftwsh", "xtwsh",
				"ftwshyj", "xtwshyj", "ftwshsj", "xtwshsj" };
		Map<String, String> map = CommonQueryDAO.commonQueryOne(
				"view_sjxy_shgz", colList, "xh", xh);

		getFdyxm(xh, map);

		return map;
	}

	public List<HashMap<String, String>> getRzqkInfo(String xh){
		String[] input = new String[]{xh};
		String[] colList = new String[]{"rzkssj","rzjssj","rzbm","zw","jdbm"};
		String query = " where xh=?";
		
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList("sjxy_rzqkb", query, input, colList, "");
		
		if(list != null){
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(map != null){
					map.put("rzkssj", dealTime(map.get("rzkssj")));
					map.put("rzjssj", dealTime(map.get("rzjssj")));
				}
			}
		}
		
		return list;
	}

	public List<HashMap<String, String>> getHjqkInfo(String xh) {
		String[] input = new String[]{xh};
		String[] colList = new String[]{"hjkssj","hjjssj","jllb","sjbm"};
		String query = " where xh=?";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList("sjxy_hjqkb", query, input, colList, "");
		
		if(list != null){
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				if(map != null){
					map.put("hjkssj", dealTime(map.get("hjkssj")));
					map.put("hjjssj", dealTime(map.get("hjjssj")));
				}
			}
		}
		
		return list;
	}

	/**
	 * �����ύ�����ݣ������Ƿ�ȫ���ɹ�����
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertRecord(ShgzModel model) {
		return insertRzqk(model) && insertShgz(model) && insertHjqk(model);
	}

	/**
	 * �����ύ�����ݣ����ز����ɹ���ʶ��
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateRecord(ShgzModel model) {
		return insertRzqk(model) && insertHjqk(model) && updateShgz(model);
	}

	/**
	 * ����ɾ�������ز����ɹ�����ʶ��
	 * 
	 * @param pkValues
	 * @return
	 */
	public boolean delRecord(String[] pkValues) {
		return delRzqk(pkValues) && delHjqk(pkValues) && delShgz(pkValues);
	}

	/**
	 * �������£�������ְ�����
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertRzqk(ShgzModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String[] arrzd = new String[] { "rzkssj", "rzjssj", "rzbm", "zw",
				"jdbm" };
		String[] onezd = new String[] { "xh" };
		String[] rzkssj = model.getRzkssj();
		String xh = model.getXh();
		String pk = "xh";
		String[] pkValue = new String[] { xh };

		if (rzkssj != null && rzkssj.length > 0) {
			pkValue = new String[rzkssj.length];
			for (int i = 0; i < rzkssj.length; i++) {
				pkValue[i] = xh;
			}
		}

		// �������
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("sjxy_rzqkb");

		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * �������£�����������
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertHjqk(ShgzModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String[] arrzd = new String[] { "hjkssj", "hjjssj", "jllb", "sjbm" };
		String[] onezd = new String[] { "xh" };
		String[] hjkssj = model.getHjkssj();
		String xh = model.getXh();
		String[] pkValue = new String[] { xh };
		String pk = "xh";

		if (hjkssj != null && hjkssj.length > 0) {
			pkValue = new String[hjkssj.length];
			for (int i = 0; i < hjkssj.length; i++) {
				pkValue[i] = xh;
			}
		}

		// ������
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("sjxy_hjqkb");

		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ������Ṥ��������ֻ���ṩѧ��
	 * 
	 * @param model
	 * @return
	 */
	public boolean insertShgz(ShgzModel model) {
		boolean flag = false;

		DAO dao = DAO.getInstance();
		String sql = "insert into sjxy_shgzb(xh) values(?)";
		String[] input = new String[] { model.getXh() };
		try {
			dao.insert(sql, input);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ������Ṥ����
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateShgz(ShgzModel model) {
		// �жϲ����ɹ�����ʶ��
		boolean flag = false;

		DAO dao = DAO.getInstance();

		String sql = "update sjxy_shgzb set ftwsh=?,xtwsh=?,ftwshyj=?,xtwshyj=?,ftwshsj=?,xtwshsj=?";
		sql += " where xh=?";

		String[] input = new String[] { model.getFtwsh(), model.getXtwsh(),
				model.getFtwshyj(), model.getXtwshyj(), model.getFtwshsj(),
				model.getXtwshsj(), model.getXh() };

		try {
			dao.runUpdate2(sql, input);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ����ɾ��shgz��
	 * 
	 * @param pkValues
	 * @return
	 */

	public boolean delShgz(String[] pkValues) {
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("xh");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("sjxy_shgzb");
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
	 * ����ɾ����ְ������
	 * 
	 * @param pkValues
	 * @return
	 */
	public boolean delRzqk(String[] pkValues) {
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("xh");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("sjxy_rzqkb");
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
	 * ����ɾ���������
	 * 
	 * @param pkValues
	 * @return
	 */
	public boolean delHjqk(String[] pkValues) {
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("xh");
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("sjxy_hjqkb");
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
	 * ���������ж������Ƿ�����Ṥ���������д���
	 * 
	 * @param xh
	 * @return
	 */
	public boolean isExists(String xh) {
		BaseDAO baseDao = new BaseDAO();
		String tableName = "sjxy_shgzb";

		return baseDao.checkExists(tableName, "xh", xh);
	}

	/**
	 * �ж�ѧ���Ƿ����
	 * 
	 * @param xh
	 * @return
	 */
	public boolean isAuditing(String xh) {
		boolean isFlag = false;

		// ֻ���жϷ���ί����Ƿ�ͨ��
		DAO dao = DAO.getInstance();
		Map<String, String> map = dao.getMapNotOut(
				"select ftwsh from sjxy_shgzb where xh=?", new String[] { xh });
		// �ж��Ƿ������
		if (map != null) {
			if ("ͨ��".equals(map.get("ftwsh"))) {
				isFlag = true;
			}
		}
		return isFlag;
	}
	
	/** ��ʱ����ַ���ת��Ϊ���β��
	 * @param time
	 * @return
	 */
	public String dealTime(String time) {
		String aftertime = time.substring(0, 4) + "��" + time.substring(4, 6)
				+ "��";
		return aftertime;
	}
}
