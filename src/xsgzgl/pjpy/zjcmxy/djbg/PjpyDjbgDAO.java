package xsgzgl.pjpy.zjcmxy.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_浙江传媒学院_DAO类
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

public class PjpyDjbgDAO extends CommDAO {

	/**
	 * 获取学生信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> setXsxxInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh, a.xm, b.csrq,  ");
		sql.append("a.nj, a.xymc, a.zymc, a.bjmc,f.bjgbmc zw,g.pjf,  ");
		sql.append("decode(b.xb,'1','男','2','女',b.xb) xb,  ");
		sql.append("(select c.zzmmmc from zzmmdmb c where b.zzmm = c.zzmmdm ) zzmmmc, ");
		sql.append("(select c.mzmc from mzdmb c where b.mz = c.mzdm) mzmc ");
		sql.append("from xg_view_pjpy_pjryk a left join xsxxb b ");
		sql.append("on a.xh=b.xh ");
		sql.append("left join (select d.xh, d.bjgbdm, e.bjgbmc ");
		sql.append("from sxjy_bjgbxxb d, sxjy_bjgbzlb e ");
		sql.append("where d.bjgbdm = e.bjgbdm ");
		sql.append("and d.xh = ? ");
		sql.append("and rownum = 1) f ");
		sql.append("on a.xh = f.xh ");
		sql.append("left join (select xh, round(avg(cj), 1) pjf ");
		sql.append("from view_zhhcjb ");
		sql.append("where xn =? ");
		sql.append("and xh = ? ");
		sql.append("group by xh) g ");
		sql.append("on a.xh = g.xh ");
		sql.append("where a.xh = ? ");

		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				xh, xn, xh, xh }, new String[] { "xh", "xm", "xb", "csrq",
				"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc", "pjf", "zw" });

		return map;
	}
	
	/**
	 * 获取学生成绩列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXscjList(String xh, String xn) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc, cj  ");
		sql.append("from cjb  ");
		sql.append("where xh = ?  ");
		sql.append("and xn = ?  ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {
			xh,xn }, new String[] { "kcmc", "cj"});

		return list;
	}
}