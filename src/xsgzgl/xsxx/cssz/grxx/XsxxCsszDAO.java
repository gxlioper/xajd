package xsgzgl.xsxx.cssz.grxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.model.ZdqxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_������Ϣ_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxCsszDAO extends CommDAO {
	
	/**
	 * ����ֶ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd) {

		StringBuilder sql = new StringBuilder();
		sql.append("select zd,zdm,sslx,zdlx,checked,");
		sql.append("source_table,select_dm,select_mc,xssx, ");
		sql.append("xsqx,lsqx,bm ");
		sql.append("from xg_xsxx_grxx_zdszb ");
		sql.append(Base.isNull(zd) ? "" : "where zd='" + zd + "' ");
		sql.append("order by sslx,to_number(xssx)");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm", "sslx", "zdlx",
						"checked", "source_table", "select_dm", "select_mc",
						"xsqx", "lsqx", "bm" });

		return list;
	}
	
	/**
	 * ������������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSslxList() {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct sslx ");
		sql.append("from xg_xsxx_grxx_zdszb ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "sslx" });

		return list;
	}
	
	/**
	 * ��ʼ���ֶ�Ȩ��
	 * 
	 * @author luojw
	 */
	public boolean initZdqx(XsxxCsszForm model, User user) {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		//�û�����
		String yhlx = model.getYhlx();
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdszb set ");
		sql.append(yhlx+"='0' ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �����ֶ�Ȩ��
	 * 
	 * @author luojw
	 */
	public boolean saveZdqx(XsxxCsszForm model, User user) {

		boolean flag = false;

		// �ֶ�Ȩ��Model
		ZdqxModel zdqxModel = model.getZdqxModel();
		// �û�����
		String yhlx = model.getYhlx();
		// �ֶ�
		String[] zd = zdqxModel.getZd();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdszb set ");
		sql.append(yhlx + "='1' ");
		sql.append("where zd=? ");

		if (zd != null && zd.length > 0) {

			List<String[]> params = new ArrayList<String[]>();

			for (int i = 0; i < zd.length; i++) {
				String[] value = new String[] { zd[i] };
				params.add(value);
			}

			try {
				flag = saveArrDate(sql.toString(), params,
						"xg_xsxx_grxx_zdszb", user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	/**
	 * �����Ƿ��������
	 * 
	 * @author luojw
	 */
	public boolean saveOver(XsxxCsszForm model, User user) {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_szb set ");
		sql.append("over='yes' ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ø�����Ϣ��������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public HashMap<String, String> getGrxxCssz() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select sfsh,lcid,sqkssj,sqjssj ");
		sql.append(",shkssj,shjssj ");
		sql.append("from xg_xsxx_grxx_szb ");
		sql.append("where over='yes' ");
		sql.append("and rownum='1' ");

		return dao.getMap(sql.toString(), new String[] {}, new String[] {
				"sfsh", "lcid", "sqkssj", "sqjssj", "shkssj", "shjssj" });
	}
}
