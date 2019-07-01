package xsgzgl.xszz.general.ywjk;

import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.xszz.general.XszzGeneralDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助_業務接口_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XszzYwjkDAO extends XszzGeneralDAO {

	/**
	 * 获得学生资助列表
	 * 
	 * @date 2013-01-30
	 * @author 伟大的骆
	 */
	public List<String[]> getXszzList(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*, a.xn || a.xq || a.nd zzzq ");
		sql.append("from (select ");
		sql.append("b.xmmc,a.sqsj, ");
		sql.append("a.xmzzjb,a.xmzzje, ");
		// 学年
		sql.append("case ");
		sql.append("when a.xn is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append(" a.xn || '学年' ");
		sql.append("end xn, ");
		// 学期
		sql.append("case ");
		sql.append("when a.xq is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append("(select b.xqmc from xqdzb b where a.xq = b.xqdm) || '学期' ");
		sql.append("end xq, ");
		// 年度
		sql.append("case ");
		sql.append("when a.nd is null then ");
		sql.append("'' ");
		sql.append("else ");
		sql.append(" a.nd || '年度' ");
		sql.append("end nd ");

		sql.append("from xszz_shztb a, xszz_zzxmb b ");
		sql.append("where a.xmdm = b.xmdm ");
		sql.append("and (b.shjb = '无需审核'  ");
		sql.append("or (b.shjb = '一级审核' and a.shzt1 = '通过')  ");
		sql.append("or (b.shjb = '两级审核' and a.shzt2 = '通过')  ");
		sql.append("or (b.shjb = '三级审核' and a.shzt3 = '通过')) ");
		sql.append("and xh = ?) a ");
		sql.append("order by a.xn, a.xq ,a.nd , a.sqsj ");

		return dao.rsToVator(sql.toString(), new String[] { xh }, new String[] {
				"zzzq", "xmmc", "xmzzjb", "xmzzje", "sqsj" });
	}
}
