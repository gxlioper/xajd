package xgxt.pjpy.jhzy.zhf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class ZhfDAO extends JhzyPjpyDAO {

	/**
	 * @author luo
	 * @describe 获得综合素质分列表
	 */
	public ArrayList<String[]> getZhfList(String tableName, ZhfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		return commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 保存综合素质分上限
	 * @throws Exception 
	 */
	public boolean saveZhfsx(ZhfModel model, HttpServletRequest request)
			throws Exception {
		String dyfsx = model.getDyfsx();
		dyfsx=Base.isNull(dyfsx)?"0":dyfsx;
		
		String zyfsx = model.getZyfsx();
		zyfsx=Base.isNull(zyfsx)?"0":zyfsx;
		
		String tyfsx = model.getTyfsx();
		tyfsx=Base.isNull(tyfsx)?"0":tyfsx;
		
		String jnfsx = model.getJnfsx();
		jnfsx=Base.isNull(jnfsx)?"0":jnfsx;
		
		String jcfsx = model.getJcfsx();
		jcfsx=Base.isNull(jcfsx)?"0":jcfsx;
		
		boolean flg = StandardOperation.delete("jhzy_zhf_sx", "xn||xq", Base.getJxjsqxn()
				+ Base.getJxjsqxq(), request);
		if (flg) {
			flg = StandardOperation.insert("jhzy_zhf_sx", new String[] { "xn",
					"xq", "dyfsx", "zyfsx", "tyfsx", "jnfsx", "jcfsx" },
					new String[] { Base.getJxjsqxn(), Base.getJxjsqxq(), dyfsx,
							zyfsx, tyfsx, jnfsx, jcfsx }, request);
		}

		return flg;
	}
	
	/**
	 * 获得综合素质分上限
	 * @throws Exception 
	 */
	public HashMap<String, String> getZhfsx() {
		DAO dao = DAO.getInstance();

		return dao.getMap("select * from jhzy_zhf_sx where xn=? and xq=?",
				new String[] { Base.getJxjsqxn() , Base.getJxjsqxq() }, new String[] {
						"dyfsx", "zyfsx", "tyfsx", "jnfsx", "jcfsx" });
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}
		
	/**
	 * 计算综合分
	 * 
	 * @throws Exception
	 */
	public boolean jsZhf(String xn, String xq) throws Exception {
		
		DAO dao = DAO.getInstance();
		boolean flg = false;
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into jhzy_zhf (xh, xn, xq, dyf, zyf, tyf, jnf, jcf, zhf) ");
		sql.append("select xh,xn,xq,dyf,zyf,tyf,jnf,jcf,(dyf + zyf + tyf + jnf + jcf) zhf ");
		sql.append("from (select a.xh, a.xn, a.xq,  ");
		sql.append("case when to_number(a.dyf) > to_number(b.dyfsx) then b.dyfsx else a.dyf end dyf, ");
		sql.append("case when to_number(a.zyf) > to_number(b.zyfsx) then b.zyfsx else a.zyf end zyf, ");
		sql.append("case when to_number(a.tyf) > to_number(b.tyfsx) then b.tyfsx else a.tyf end tyf, ");
		sql.append("case when to_number(a.jnf) > to_number(b.jnfsx) then b.jnfsx else to_char(a.jnf) end jnf, ");
		sql.append("case when to_number(a.jcf) > to_number(b.jcfsx) then b.jcfsx else to_char(a.jcf) end jcf ");
		sql.append("from (select a.xh,a.bjdm,a.xm,'"+xn+"' xn,'"+xq+"'xq,nvl(b.dyf, 0) dyf, ");
		sql.append("nvl(trim(to_char(c.zyf, 9999.99)), 0) zyf,nvl(d.jnf, 0) jnf, ");
		sql.append("nvl(e.tyf, 0) tyf,nvl(trim(to_char(f.jcf, 9999.99)), 0) jcf from view_xsjbxx a ");
		sql.append("left join (select xh, xn, xq, dyf from jhzy_dyf) b on a.xh = b.xh and b.xn= '"+xn+"' and b.xq='"+xq+"' ");
		sql.append("left join (select xn, xq, xh, grzf / bjzgf * 50 zyf from (select a.xn, ");
		sql.append("a.xq,a.xh,a.bjdm,a.grzf,b.bjzgf from (select a.xn,a.xq,a.xh,b.bjdm, ");
		sql.append("sum(a.cj) grzf from view_zhhcjb a, view_xsjbxx b where a.xh = b.xh and a.kcmc not like '%体育%' group by a.xn, ");
		sql.append("a.xq, a.xh, b.bjdm) a left join (select xn,xq,bjdm,max(grzf) bjzgf ");
		sql.append("from (select a.xn,a.xq,a.xh,b.bjdm,sum(a.cj) grzf from view_zhhcjb a,view_xsjbxx b ");
		sql.append("where a.xh = b.xh and a.kcmc not like '%体育%' group by a.xn,a.xq,a.xh,b.bjdm) ");
		sql.append("group by xn, xq, bjdm) b on a.xn = b.xn and a.xq = b.xq ");
		sql.append("and a.bjdm = b.bjdm order by a.xn, a.xq, a.bjdm)) c on a.xh = c.xh ");
		sql.append("and c.xn= '"+xn+"' and c.xq='"+xq+"' left join (select xn, xq, xh, (fs + jnf) jnf ");
		sql.append("from (select a.xn,a.xq,a.xh,nvl(c.fs,0) fs, ");
		sql.append("case when a.jnf > 4 then 4 else to_number(nvl(a.jnf,0)) end jnf ");
		sql.append("from jhzy_jnf a left join xsdjksb b on a.xh = b.xh ");
		sql.append("left join jhzy_djksfsb c on b.djksmc = c.ksmc ");
		sql.append(")) d on a.xh = d.xh and d.xn= '"+xn+"' and d.xq='"+xq+"' ");
		sql.append("left join (select xh, xn, xq, tyf from jhzy_tyf) e on a.xh = e.xh ");
		sql.append("and e.xn= '"+xn+"' and e.xq='"+xq+"' left join (select xn, xq, xh, grzf / bjzgf * 10 jcf ");
		sql.append("from (select a.xn,a.xq,a.xh,a.bjdm,a.grzf,b.bjzgf from (select a.xn, ");
		sql.append("a.xq,a.xh,a.bjdm,sum(a.jcf) grzf from view_jhzy_jcf a group by a.xn, a.xq, ");
		sql.append("a.xh, a.bjdm) a left join (select xn,xq,bjdm,max(grzf) bjzgf from (select a.xn, ");
		sql.append("a.xq,a.xh,a.bjdm,sum(a.jcf) grzf from view_jhzy_jcf a group by a.xn,a.xq, ");
		sql.append("a.xh,a.bjdm) group by xn, xq, bjdm) b on a.xn = b.xn and a.xq = b.xq ");
		sql.append("and a.bjdm = b.bjdm order by a.xn, a.xq, a.bjdm)) f on a.xh = f.xh ");
		sql.append("and f.xn= '"+xn+"' and f.xq='"+xq+"') a,jhzy_zhf_sx b where a.xn = b.xn ");
		sql.append("and a.xq = b.xq) ");
		
		flg = dao.runUpdate("delete from jhzy_zhf where xn=? and xq=?", new String[] { xn, xq });
		if (flg) {
			flg = dao.runUpdate(sql.toString(), new String[] {});
		}
		//System.out.println(sql);
		return flg;
	}
}
