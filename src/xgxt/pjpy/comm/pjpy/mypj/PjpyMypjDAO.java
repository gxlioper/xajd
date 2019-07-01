package xgxt.pjpy.comm.pjpy.mypj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyMypjDAO extends PjpyCommDAO {

	/**
	 * 获得项目上报学生列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getMypjTeaList(PjpyMypjForm model,
			User user) {
		
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		String zczq=  XMLReader.getFlowControl("pjpy", "zczq");

		
		
		// 用户名
		String userName = user.getUserName();
		// 班级代码
		String bjdm = model.getBjdm();
		
		StringBuilder sql = new StringBuilder();
		//sql.append("( ");
		sql.append("select xmdm,xmmc,sbrs, ");
		sql.append("to_char(WM_CONCAT(gwmc || '(' || nvl(shrs, 0) || ')')) lv ");
		sql.append("from (select a.xmdm,a.xmmc,a.lcid,nvl(b.sqrs, 0) sbrs, ");
		sql.append("b.gwdm,b.gwmc,nvl(d.shrs, 0) shrs ");
		sql.append("from (select xmdm, xmmc, lcid, xssx ");
		sql.append("from xg_pjpy_pjxmwhb a ");
		sql.append("where pjxn = '"+pjxn+"' ");
		sql.append("and pjxq = '"+pjxq+"' ");
		sql.append("and pjnd = '"+pjnd+"' ");
		sql.append("and (exists (select 1 from xg_pjpy_pjxmsqb b ");
		sql.append("where a.xmdm = b.xmdm and (a.pjxn = b.pjxn or ");
		sql.append("(a.pjxn = b.pjxn and a.pjxq = b.pjxq) or ");
		sql.append("a.pjnd = b.pjnd) and b.tjr = '"+userName+"')  ");
		sql.append("or exists (select 1 from xg_xtwh_spbz c, ");
		sql.append("xg_xtwh_spgw d,xg_xtwh_spgwyh e ");
		sql.append("where a.lcid = c.splc and c.spgw = d.id ");
		sql.append("and c.spgw = e.spgw and e.spyh = '"+userName+"'))) a ");

		sql.append("left join (select b.xmdm, count(1) sqrs ");
		sql.append("from xg_pjpy_pjxmsqb b where (b.pjxn = '"+pjxn+"' or ");
		sql.append("(b.pjxn = '"+pjxn+"' and b.pjxq = '"+pjxq+"') or ");
		sql.append("b.pjnd = '"+pjnd+"') and b.tjr = '"+userName+"' ");
		sql.append("group by b.xmdm) b on a.xmdm = b.xmdm ");

		sql.append("left join (select c.splc, c.xh, d.id gwdm, d.mc gwmc ");
		sql.append("from xg_xtwh_spbz c, xg_xtwh_spgw d, xg_xtwh_spgwyh e ");
		sql.append("where c.spgw = d.id and c.spgw = e.spgw ");
		sql.append("and e.spyh = '"+userName+"' order by c.splc, c.xh) b on a.lcid = b.splc ");

		sql.append("left join ( ");
		
		sql.append(" select lcid,pjxn, pjxq, pjnd, xmdm, xmmc, spgw,count(1)shrs ");
		sql.append(" from (select a.*,b.spgw, b.xmmc ");
		sql.append(" from (select xmdm, pjxn, pjxq, pjnd, xh, min(shjb) shjb,lcid ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where (shzt = '未审核' or shzt = '需重审') ");
		sql.append(" and (exists (select 1 ");
		sql.append(" from xg_view_pjpy_xmsh b ");
		sql.append(" where shzt = '通过' ");
		sql.append(" and to_number(b.shjb) = a.shjb - 1 ");
		sql.append(" and b.xmdm = a.xmdm ");
		sql.append(" and b.pjxn = a.pjxn ");
		sql.append(" and b.pjxq = a.pjxq ");
		sql.append(" and b.pjnd = a.pjnd ");
		sql.append(" and b.xh = a.xh) or a.shjb = 1) ");
		sql.append(" group by xmdm, pjxn, pjxq, pjnd, xh,lcid) a ");
		sql.append(" left join xg_view_pjpy_xmsh b on a.xmdm = b.xmdm ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = b.xh ");
		sql.append(" and a.shjb = b.shjb ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_xtwh_spgwyh c ");
		sql.append(" where b.spgw = c.spgw ");
		sql.append(" and c.spyh = '"+userName+"'))where 1=1 ");
		
		if("xn".equalsIgnoreCase(zczq)){
			
			sql.append(" and pjxn='"+pjxn+"' ");
			sql.append(" and pjxq='无' ");
		}else if("xq".equalsIgnoreCase(zczq)){
			
			sql.append(" and pjxn='"+pjxn+"' ");
			sql.append(" and pjxq='"+pjxq+"' ");
		}
		sql.append(" group by pjxn, pjxq, pjnd, xmdm, xmmc,lcid,spgw ");
		
		sql.append(") d on a.xmdm = d.xmdm ");
		sql.append("and a.lcid = d.lcid and b.gwdm = d.spgw ");
		sql.append("order by a.xssx, b.xh) ");
		sql.append("group by xmdm, xmmc, sbrs ");
	
		return getRsList("", "", new String[] {}, new String[] { "xmdm",
				"xmmc", "sbrs", "lv" }, sql.toString());
	}
	
	/**
	 * 获得学生已申请项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getMypjStuList(PjpyMypjForm model,
			User user) {
		
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 用户名
		String userName = user.getUserName();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmdm, a.xmmc,a.shjb,a.shzt,a.lcid,");
		sql.append("a.pk||(select b.spgw from xg_xtwh_spbz b ");
		sql.append("where b.splc = a.lcid and b.xh = a.shjb) pk ");
		sql.append("from(");
		sql.append("select a.pk,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append("to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt ");
		sql.append("from (select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh pk,");
		sql.append("a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a ");
		sql.append("where xh = '"+userName+"' ");
		sql.append("and (a.pjxn = '"+pjxn+"' or ");
		sql.append("(a.pjxn = '"+pjxn+"' and a.pjxq = '"+pjxq+"') or a.pjnd = '"+pjnd+"') ");
		sql.append("order by xmdm, to_number(shjb)) a ");
		sql.append("group by a.pk,a.xmdm,a.xmmc,a.lcid) a ");

		return getRsList("", "", new String[] {}, new String[] { "pk", "xmdm",
				"xmmc", "shzt", "shjb" }, sql.toString());
	}
}
