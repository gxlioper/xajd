package xgxt.xsgygl.comm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class GyglCommDAO extends GyglTyDAO {
	
	/**
	 * 撤销宿舍分配(批量)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delSsfpPl(GyglTyForm model) throws Exception {

		DAO dao = DAO.getInstance();

		// 年级
		String nj = model.getQueryequals_nj();
		// 学院
		String xydm = model.getQueryequals_xydm();
		// 学区
		String xqdm = model.getQueryequals_xqdm();
		// 楼栋
		String lddm = model.getQueryequals_lddm();
		// 层数
		String cs = model.getQueryequals_cs();
		// 寝室
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
