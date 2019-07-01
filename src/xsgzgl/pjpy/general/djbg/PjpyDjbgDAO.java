package xsgzgl.pjpy.general.djbg;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_通用_DAO类
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
		sql.append("a.nj, a.xymc, a.zymc, a.bjmc, ");
		sql.append("decode(b.xb,'1','男','2','女',b.xb) xb,  ");
		sql.append("(select c.zzmmmc from zzmmdmb c where b.zzmm = c.zzmmdm ) zzmmmc, ");
		sql.append("(select c.mzmc from mzdmb c where b.mz = c.mzdm) mzmc ");
		sql.append("from xg_view_pjpy_pjryk a left join xsxxb b ");
		sql.append("on a.xh=b.xh ");
		sql.append("where a.xh = ? ");

		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, new String[] { "xh", "xm", "xb", "csrq",
						"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc"});

		return map;
	}
}
