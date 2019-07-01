package xsgzgl.pjpy.general.tjcx.zcbjmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_综测班级名单_通用_DAO类
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

public class TjcxZcbjmdDAO extends PjpyTjcxDAO {

	
	/**
	 * 创建综测班级名单HTMl【等级考试】（新评奖）
	 * 
	 * @author cmj
	 * @param zcxmList 
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getZcxxListNew(String xn,String xq, String bjdm, List<HashMap<String, String>> zcxmList) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select t1.*,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(", t2.fs").append(i).append(", t2.pm").append(i);
		}
		
		sql.append(" from xg_pjpy_new_cpmdb t1 left join (");
		sql.append(" select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append("sum(fs").append(i).append(") fs").append(i).append(",sum(pm").append(i).append(") pm").append(i).append(",");
		}
		
		sql.append(" xh from ( select ");
		
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.fs else '0' end fs")
			   .append(i).append(",");
			sql.append(" case when t.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then t.").append("bjpm").append(" else '' end pm")
			   .append(i).append(",");
		}
		
		sql.append(" t.xh from xg_zhcp_zcfsb t ) group by xh ) t2 on t1.xh=t2.xh");
		sql.append(" left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
		sql.append(" where exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm ");
		sql.append(" and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1') ");
		sql.append(" order by pm0 ) t where 1=1 and (xn = ?) and (xq = ?) and (bjdm = ?)");

		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(),
				new String[] { xn,xq, bjdm });

		return list;
	}
	
	
	
	/**
	 * 创建综测班级名单HTMl【等级考试】
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getZcxxList(String xn, String bjdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r, ");
		sql.append("a.*, ");
		sql.append("rank() over(order by to_number(dyf) desc nulls last) dypm, ");
		//sql.append("rank() over(order by to_number(tyf) desc nulls last) typm, ");
		sql.append("rank() over(order by to_number(nlf) desc nulls last) nlpm ");
		sql.append("from (select xh, ");
		sql.append("xm, ");
		sql.append("bjmc,xymc, ");
		sql.append("zd2     dyf, ");
		sql.append("zd3     zyf, ");
		sql.append("zyfbjpm zypm, ");
		sql.append("zd4     tyf, ");
		sql.append("zd5     nlf, ");
		sql.append("zd1     zcf, ");
		sql.append("zcfbjpm zcpm ");
		sql.append("from (select a.*, b.xm, b.bjdm, b.bjmc,b.xymc ");
		sql.append("from xg_pjpy_zhcpb a ");
		sql.append("left join xg_view_pjpy_pjryk b ");
		sql.append("on a.xh = b.xh ");
		sql.append("where a.xn = ?) ");
		sql.append("where bjdm = ?) a ");
		sql.append("order by to_number(zcpm) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, bjdm }, new String[] { "xh", "xm", "bjmc",
						"dyf", "dypm", "zyf", "zypm", "tyf", "nlf", "nlpm",
						"zcf", "zcpm", "xymc" });

		return list;
	}
	
	/**
	 * 获得评奖信息列表
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getPjxxList(
			List<HashMap<String, String>> zcxxList, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);

		sql.append("select xh,xmlx,xmmc from xg_pjpy_pjlsxxb ");
		sql.append("where 1=1 ");
		sql.append("and xn=? ");
		if (zcxxList != null && zcxxList.size() > 0) {
			sql.append("and xh in ( ");
			for (int i = 0; i < zcxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zcxxList.get(i).get("xh"));
			}
			sql.append(")");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}),
				new String[] { "xh", "xmlx", "xmmc" });

		return list;
	}
	
	/**
	 * 获得评奖信息列表（新评奖）
	 * 
	 * @author 陈敏杰
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getPjxxListNew(
			List<HashMap<String, String>> zcxxList, String xn,String xq) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xq);

		sql.append("select xh,lxdm xmlx,xmmc from xg_pjpy_new_pjjgb ");
		sql.append("where 1=1 ");
		sql.append("and xn=? ");
		sql.append("and xq=? ");
		if (zcxxList != null && zcxxList.size() > 0) {
			sql.append("and xh in ( ");
			for (int i = 0; i < zcxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zcxxList.get(i).get("xh"));
			}
			sql.append(")");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}),
				new String[] { "xh", "xmlx", "xmmc" });

		return list;
	}
	
	/**
	 * 获得评奖信息列表
	 * 
	 * @author 伟大的骆
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getQtxxList(
			List<HashMap<String, String>> zcxxList, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xn);
		params.add(xn);
		params.add(xn);
		
		sql.append("select xh, mc, lx from (");
		sql.append("select xh, to_char(count(1)) mc, 'bjg' lx ");
		sql.append("from cjb ");
		sql.append("where xn = ? ");
		sql.append("and to_number(cj) < 60 ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy3j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET3' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy4j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET4' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy6j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET6' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj1j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机一级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj2j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机二级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj3j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机三级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(count(1)) mc, 'xjtb' lx ");
		sql.append("from view_pjpy_xfjs_xsjljcb b ");
		sql.append("where xn = ? ");
		sql.append("and xq = '01' ");
		sql.append("and wjlxdm is not null ");
		sql.append("and wjlxdm <> ");
		sql.append("(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '旷课') ");
		sql.append("and qjlxdm is null ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(count(1)) mc, 'kkjs' lx ");
		sql.append("from view_pjpy_xfjs_xsjljcb b ");
		sql.append("where xn = ? ");
		sql.append("and xq = '01' ");
		sql.append("and wjlxdm = ");
		sql.append("(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '旷课') ");
		sql.append("and qjlxdm is null ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select distinct xh, cflbmc mc, 'wjcf' lx ");
		sql.append("from xg_wjcf_wjcfb ");
		sql.append("where xn = ? ");
		sql.append("and xq = '01' ");//传媒更改处分增加学期过滤
		sql.append(") ");
		
		if (zcxxList != null && zcxxList.size() > 0) {
			sql.append("where xh in ( ");
			for (int i = 0; i < zcxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zcxxList.get(i).get("xh"));
			}
			sql.append(")");
		}
 
		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}),
				new String[] { "xh", "mc", "lx" });

		return list;
	}
	/**
	 * 获得评奖信息列表（新评奖）
	 * 
	 * @author 陈敏杰
	 * @throws IOException
	 */
	public List<HashMap<String, String>> getQtxxListNew(
			List<HashMap<String, String>> zcxxList, String xn,String xq) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xn);
		params.add(xn);
		params.add(xn);
		
		sql.append("select xh, mc, lx from (");
		sql.append("select xh, to_char(count(1)) mc, 'bjg' lx ");
		sql.append("from cjb ");
		sql.append("where xn = ? ");
		sql.append("and xq = '"+xq+"' ");
		sql.append("and to_number(cj) < 60 ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy3j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET3' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy4j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET4' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'yy6j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = 'CET6' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj1j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机一级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj2j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机二级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(max(cj)) mc, 'jsj3j' lx ");
		sql.append("from xsdjksb ");
		sql.append("where djksmc = '计算机三级' ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(count(1)) mc, 'xjtb' lx ");
		sql.append("from view_pjpy_xfjs_xsjljcb b ");
		sql.append("where xn = ? ");
		sql.append("and xq = '"+xq+"' ");
		sql.append("and wjlxdm is not null ");
		sql.append("and wjlxdm <> ");
		sql.append("(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '旷课') ");
		sql.append("and qjlxdm is null ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select xh, to_char(count(1)) mc, 'kkjs' lx ");
		sql.append("from view_pjpy_xfjs_xsjljcb b ");
		sql.append("where xn = ? ");
		sql.append("and xq = '"+xq+"' ");
		sql.append("and wjlxdm = ");
		sql.append("(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '旷课') ");
		sql.append("and qjlxdm is null ");
		sql.append("group by xh ");
		sql.append("union all ");
		sql.append("select distinct xh, cflbmc mc, 'wjcf' lx ");
		sql.append("from xg_wjcf_wjcfb ");
		sql.append("where xn = ? ");
		sql.append("and xq = '"+xq+"' ");//传媒更改处分增加学期过滤
		sql.append(") ");
		
		if (zcxxList != null && zcxxList.size() > 0) {
			sql.append("where xh in ( ");
			for (int i = 0; i < zcxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zcxxList.get(i).get("xh"));
			}
			sql.append(")");
		}
 
		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}),
				new String[] { "xh", "mc", "lx" });

		return list;
	}
}
