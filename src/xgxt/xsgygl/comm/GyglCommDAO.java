package xgxt.xsgygl.comm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class GyglCommDAO extends GyglTyDAO {
	
	/**
	 * �����������(����)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delSsfpPl(GyglTyForm model) throws Exception {

		DAO dao = DAO.getInstance();

		// �꼶
		String nj = model.getQueryequals_nj();
		// ѧԺ
		String xydm = model.getQueryequals_xydm();
		// ѧ��
		String xqdm = model.getQueryequals_xqdm();
		// ¥��
		String lddm = model.getQueryequals_lddm();
		// ����
		String cs = model.getQueryequals_cs();
		// ����
		String qsh = model.getQueryequals_qsh();

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ssfpb a ");
		sql.append(" where exists (select 1 from view_gygl_cxfp_ss b");
		sql.append(" where a.ssbh = b.ssbh ");
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "'");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "'");
		sql.append(Base.isNull(xqdm) ? "" : " and b.xqdm = '" + xqdm + "'");
		sql.append(Base.isNull(lddm) ? "" : " and b.lddm = '" + lddm + "'");
		sql.append(Base.isNull(cs) ? "" : " and b.cs = '" + cs + "'");
		sql.append(Base.isNull(qsh) ? "" : " and b.qsh = '" + qsh + "'");
		sql.append(" )");

		boolean flag = dao.runUpdate(sql.toString(), new String[] {});
		
		return flag;
	}
}
