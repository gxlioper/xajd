package xsgzgl.pjpy.general.xmsz.xmjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��Ŀ���_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszXmjdDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * ��ý����(������Ŀ)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmjdList(String xmdm) {

		DAO dao = DAO.getInstance();

		// �û�����
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.xmdm,a.xmmc,b.fjddm sfjd, ");
		sql.append("case when length(a.xmmc)>5 then substr(a.xmmc,0,5)||'...' ");
		sql.append("else a.xmmc end xmsx, ");
		sql.append("decode(a.xmlx,'01','��ѧ��','�����ƺ�') xmlx ");
		sql.append("from xg_pjpy_pjxmwhb a ");
		sql.append("left join (select xmdm,fjddm from xg_pjpy_jdszb where xmdm = ? ) b ");
		sql.append("on a.xmdm = b.fjddm ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.xmdm <> ? ");
		sql.append("and (a.sfqy is null or a.sfqy = 'yes' or a.sfqy = '��') ");

		// ====================SQLƴװ end================================

		String[] inputValue = new String[] { xmdm, pjxn, pjxq, xmdm };
		String[] outputValue = new String[] { "xmdm", "xmmc", "xmlx", "xmsx",
				"sfjd" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	// ==================ִ�в�ѯ���� end==============================

	// ==================ִ���������� begin=============================
	/**
	 * �������ݣ�xg_pjpy_jdszb:������ñ�
	 * 
	 * @table ������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertJdszb(String xmdm, String[] fjddm, User user)
			throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_jdszb ");
		sql.append("(xmdm,fjddm)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (fjddm != null && fjddm.length > 0) {
			for (int i = 0; i < fjddm.length; i++) {
				String[] value = new String[] { xmdm, fjddm[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * �������ݣ�xg_pjpy_jdszb:������ñ�
	 * 
	 * @table ������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertJdszb(String[] fjddm, String xmdm, User user)
			throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_jdszb ");
		sql.append("(xmdm,fjddm)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (fjddm != null && fjddm.length > 0) {
			for (int i = 0; i < fjddm.length; i++) {
				String[] value = new String[] { fjddm[i], xmdm };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================ִ���������� end=============================
	
	// ==================ִ��ɾ������ begin=============================
	/**
	 * ɾ�����ݣ�xg_pjpy_jdszb:������ñ�
	 * 
	 * @table ������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteJdszb(String xmdm, User user) throws Exception {

		// ����
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_jdszb ");
		sql.append("where 1=1 ");
		sql.append("and (xmdm=? or fjddm=?) ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { xmdm, xmdm });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ��ɾ������ end =============================
}
