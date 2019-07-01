package xsgzgl.pjpy.czzyjsxy.wdpj.lssb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_老师上报_池州职业技术学院_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class WdpjLssbDAO extends xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbDAO {

	/**
	 * 获取老师上报班级学生申请项目情况
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 评奖基本设置信息
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();
		// 项目代码
		String xmdm = model.getXmdm();

		String[] colList = new String[] { "xh", "xm", "nj", "bjmc", "zd1",
				"zcfbjpm", "zyfbjpm", "sqqk", "pkValue" };

		String bjdm = model.getBjdm();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		sql.append(" (select a.*, ");
		sql.append(" case when b.xh is null then 'wsq'  ");
		sql
				.append(" when c.xh is null then 'wsh' else 'ysh' end sqqk,b.pkValue from ( ");

		// ------------------------综合测评信息 being---------------------------
		sql.append(" select a.xh,a.xm,a.nj,a.xydm, ");
		sql.append(" a.xymc,a.zydm,a.zymc, a.bjdm, ");
		sql.append(" a.bjmc,b.zd1,b.zcfnjxypm, b.zcfnjzypm,b.zcfbjpm, ");
		sql.append(" b.zyfnjxypm, b.zyfnjzypm,b.zyfbjpm, b.zyfcpzpm,b.cpzpm ");
		sql.append(" from xg_view_pjpy_pjryk a ");
		sql.append(" left join xg_pjpy_zhcpb b on a.xh = b.xh ");
		sql.append(" where bjdm = ?  and xn = ? ");
		sql.append(" and xq = ? and nd = ?)a ");
		// ------------------------综合测评信息 end ---------------------------

		sql.append(" left join ");

		// ------------------------评奖申请信息 begin------------------------
		sql.append(" (select b.*, ");
		sql
				.append(" b.xh||'!!@@!!'|| b.xmdm||'!!@@!!'|| b.pjxn||'!!@@!!'|| b.pjxq||'!!@@!!'|| b.pjnd  ");
		sql.append(" pkValue from xg_pjpy_pjxmsqb b ");
		sql.append("  where b.pjxn=? ");
		sql.append("  and b.pjxq=? and b.pjnd=? ");
		sql.append("  and b.xmdm=?)b on a.xh=b.xh");
		// ------------------------评奖申请信息 end------------------------

		// ------------------------评奖审核信息 begin------------------------
		sql.append(" left join (");
		sql.append(" select * from xg_pjpy_pjxmshb b ");
		sql.append(" where b.pjxn=?");
		sql.append(" and b.pjxq=? and b.pjnd=? ");
		sql.append(" and b.xmdm=? ");
		sql.append(" and (shzt is not null and shzt<>'wsh')");
		sql.append(" )c on a.xh=c.xh)");
		sql.append(" order by to_number(zcfbjpm)");
		// ------------------------评奖审核信息 end------------------------

		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { bjdm, pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, xmdm,
						pjxn, pjxq, pjnd, xmdm }, colList, null);
	}
}
