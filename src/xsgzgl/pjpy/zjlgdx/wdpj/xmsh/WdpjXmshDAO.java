package xsgzgl.pjpy.zjlgdx.wdpj.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_项目审核_浙江理工大学_DAO类
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

public class WdpjXmshDAO extends xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshDAO {

	// =====================执行查询操作 begin================================
	/**
	 * 获得项目审核结果集
	 * 
	 * @date 2013-01-06
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm model,
			WdpjXmshModel xmshModel, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		SearchModel searchModel = model.getSearchModel();

		DAO dao = DAO.getInstance();

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		String xmdm = xmshModel.getXmdm();

		String spgw = xmshModel.getSpgw();

		HashMap<String, String> nextSpgw = getNextSpMap(xmshModel, user);

		HashMap<String, String> higherUpSpgw = getHigherUpSpMap(xmshModel, user);
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append(" select a.*,rownum r from( ");
		tableSql.append(" select a.* from( ");
		tableSql.append(" select a.*,case when b.xh is not null then ");
		tableSql.append(" 'disabled' else '' end sfsh, c.cpzpm,c.cpzpm||'/'||d.js jspm,c.zd1 zczf from(  ");
		tableSql.append(" select a.xh pkValue, a.xmdm,a.pjxn,a.pjxq, ");
		tableSql.append(" a.pjnd,a.xh,a.sqsj,c.xydm, ");
		tableSql.append(" case when b.shzt='wsh' then '未审核' ");
		tableSql.append("  when b.shzt='tg' then '通过' ");
		tableSql.append("  when b.shzt='btg' then '不通过' ");
		tableSql.append("  when b.shzt='xcs' then '需重审' ");
		tableSql.append("  when b.shzt='th' then '退回' end shzt, ");
		tableSql.append(" c.zydm,c.bjdm,c.xymc,c.zymc,c.bjmc,c.nj,c.xm,c.cpzdm ");
		tableSql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmshb b, xg_view_pjpy_pjryk c ");
		tableSql.append(" where b.xtgwid =? ");
		tableSql.append(" and b.xmdm = ? ");
		tableSql
				.append(" and a.xmdm = b.xmdm and a.pjxn = b.pjxn  and a.pjxq = b.pjxq ");
		tableSql
				.append(" and a.pjnd = b.pjnd  and a.xh = b.xh and a.xh = c.xh ");
		tableSql.append(" and a.pjxn = ? ");
		tableSql.append(" and a.pjxq = ? ");
		tableSql.append(" and a.pjnd = ? ");

		String higherUpgw = higherUpSpgw.get("spgw");
		if (!Base.isNull(higherUpgw)) {
			tableSql
					.append(" and exists( select 1 from xg_pjpy_pjxmshb x where x.xmdm='"
							+ xmdm + "'  ");
			tableSql
					.append(" and b.pjxn=x.pjxn and b.pjxq=x.pjxq and b.pjnd=x.pjnd and b.xh=x.xh ");
			tableSql.append(" and xtgwid='" + higherUpgw
					+ "' and b.xmdm=x.xmdm  and shzt='tg' )");
		}
		tableSql.append("  order by a.xh ");

		// --------------------------下级审核是否已审核-------------------------------
		tableSql.append(" )a left join ( ");
		tableSql.append("  select xh from xg_pjpy_pjxmshb ");
		tableSql.append("  where xtgwid = ? ");
		tableSql.append("  and xmdm = ? ");
		tableSql.append("  and (shzt='tg' or shzt='btg') )b on a.xh=b.xh ");
		tableSql.append(" left join (select xh, cpzpm,zd1 ");
		tableSql.append(" from xg_pjpy_zhcpb ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(" and xn = ? ");
		tableSql.append(" and xq = ? ");
		tableSql.append(" and nd = ?) c ");
		tableSql.append(" on a.xh = c.xh ");
		tableSql.append(" left join (select cpzdm,count(1) js from xg_view_pjpy_pjryk group by cpzdm) d on a.cpzdm=d.cpzdm ");
        tableSql.append(" ) a ");
        tableSql.append(" order by cpzdm,to_number(cpzpm) ) a ");
		// --------------------------下级审核是否已审核-------------------------------

		tableSql.append(query);

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "sfsh", "pkValue", "jspm","xh",
				"xm", "nj", "bjmc", "zymc","sqsj","zczf","shzt" };

		String[] inputZ = { spgw, xmdm, pjxn, pjxq, pjnd, nextSpgw.get("spgw"),
				xmdm, pjxn, pjxq, pjnd };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), dao
						.unionArray(inputZ, inputV), colList);

		return list;
	}
	// =====================执行查询操作 end====================================
}
