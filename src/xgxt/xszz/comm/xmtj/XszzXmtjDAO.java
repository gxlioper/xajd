package xgxt.xszz.comm.xmtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommDAO;

public class XszzXmtjDAO extends XszzCommDAO {

	/**
	 * 获得资助汇总
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getZzhzList(XszzTyForm model) {

		DAO dao = DAO.getInstance();
		
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,t.xm,t.nj,t.xymc,t.zymc,t.bjmc, ");
		sql.append(" count(1) xmNum,sum(t.xmzzje) zje,");
		sql.append(" '" + kssj + "' kssj, ");
		sql.append(" '" + jssj + "' jssj ");
		sql.append(" from (");
		sql.append(getZzhzSql(model));
		sql.append(" ) t where t.xmzzje is not null ");
		sql.append(" and t.xmzzje <> '不涉及金额' ");
		sql.append(" group by t.xh,t.xm,t.nj,t.xymc,t.zymc,t.bjmc");
		
		String[] outputValue = new String[] { "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "xmNum", "zje","kssj","jssj" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outputValue);

		return list;
	}
	
	/**
	 * 获得资助汇总(学生多人)
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXsZzhzList(XszzTyForm model) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,t.xmdm,t.xmmc,t.xmlb, ");
		sql.append(" nvl(t.xmzzjb,'无级别') xmzzjb,t.xmzzje,t.sqsj ");
		sql.append(" from (");
		sql.append(getZzhzSql(model));
		sql.append(" ) t where t.xmzzje is not null ");
		sql.append(" and t.xmzzje <> '不涉及金额' ");

		String[] outputValue = new String[] { "xh", "xmdm", "xmmc", "xmlb",
				"xmzzjb", "xmzzje", "sqsj" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outputValue);

		return list;
	}
	
	/**
	 * 资助汇总SQL
	 * 
	 * @param xh
	 * @return
	 */
	private String getZzhzSql(XszzTyForm model) {
		
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目类别
		String xmlb = model.getXmlb();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目类别
		String xmmc = model.getXmmc();
		// 学号+申请时间
		String[] pkValue = model.getCheckVal();
		
		StringBuilder sql = new StringBuilder();
		

		sql.append(" select t.* from (");
		sql.append(" select a.*,b.xymc,b.zymc,b.bjmc,b.xm,b.xb, ");
		sql.append(" b.nj,c.xmmc,c.shjb,c.xmlb ");
		sql.append(" from xszz_shztb a, view_xsjbxx b, xszz_zzxmb c ");
		sql.append(" where a.xh = b.xh and a.xmdm = c.xmdm ");
		// ===================学生基本信息相关查询条件=================
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ===================项目基本信息相关查询条件=================
		sql.append(Base.isNull(xmlb) ? "" : " and c.xmlb = '" + xmlb + "'");
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "'");
		sql.append(Base.isNull(xmmc) ? "" : " and c.xmmc like '%" + xmmc + "%' ");
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");
		
		sql.append(") t where 1 = 1 ");
		//===================只统计无需审核的与审核通过的项目=================
		sql.append(" and ( ");
		sql.append(" shjb = '无需审核' ");
		sql.append(" or (shjb = '一级审核' and shzt1 = '通过')");
		sql.append(" or (shjb = '两级审核' and shzt2 = '通过')");
		sql.append(" or (shjb = '三级审核' and shzt3 = '通过')");
		sql.append(" ) ");
		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and (");
			sql.append(" xh||sqsj||xmdm <> '" + pkValue[0] + "' ");
			for (int i = 1; i < pkValue.length; i++) {
				sql.append(" and xh||sqsj||xmdm <> '" + pkValue[i] + "' ");
			}
			sql.append(" )");
		}
		return sql.toString();
	}
}