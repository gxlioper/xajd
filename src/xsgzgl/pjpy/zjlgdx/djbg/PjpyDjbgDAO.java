package xsgzgl.pjpy.zjlgdx.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_浙江理工大学_DAO类
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

public class PjpyDjbgDAO extends xsgzgl.pjpy.general.djbg.PjpyDjbgDAO {

	/**
	 * 获取学生信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> setXsxxInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.xm, b.csrq,c.jtszd,b.sjhm,  ");
		sql.append(" a.nj, a.xymc, a.zymc, a.bjmc, b.jtdz,b.zw,b.grjl,b.zd1,b.zd2,d.sydmc,e.zzmmmc, ");
		sql.append(" f.cpzpm,f.zd2 dyf,f.zd3 zyf,f.zd4 tyf,");
		sql.append(" (select sydmc from syddmb x where  (substr(syd, 1, 4) || '00' )=x.syddm)sydshi, ");
		sql.append(" decode(b.xb,'1','男','2','女',b.xb) xb,  ");
		sql.append(" (select c.zzmmmc from zzmmdmb c where b.zzmm = c.zzmmdm ) zzmmmc, ");
		sql.append(" (select c.mzmc from mzdmb c where b.mz = c.mzdm) mzmc ");
		sql.append(" from xg_view_pjpy_pjryk a left join xsxxb b ");
		sql.append(" on a.xh=b.xh ");
		sql.append(" left join xsfzxxb c ");
		sql.append(" on a.xh=c.xh ");
		sql.append(" left join syddmb d ");
		sql.append(" on b.syd=d.syddm ");
		sql.append(" left join zzmmdmb e ");
		sql.append(" on b.zzmm=e.zzmmdm ");
		sql.append(" left join (select * from xg_pjpy_zhcpb where xn =(select pjxn from xg_pjpy_xtszb) and " );
		sql.append(" xq = (select pjxq from xg_pjpy_xtszb) and nd = (select pjnd from xg_pjpy_xtszb)) f ");
		sql.append(" on a.xh=f.xh ");
		sql.append(" where a.xh = ? ");

		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, new String[] { "xh", "xm", "xb", "csrq",
						"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc","cpzpm","dyf","zyf","tyf",
						"jtszd", "sjhm","zw","grjl","zd1","zd2","sydmc","zzmmmc","jtdz","sydshi" });

		return map;
	}
}