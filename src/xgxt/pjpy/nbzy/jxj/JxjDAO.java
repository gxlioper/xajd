package xgxt.pjpy.nbzy.jxj;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.nbzy.zhszcp.ZhszcpModel;
import xgxt.utils.String.StringUtils;

public class JxjDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = null;
	public HashMap<String, String> viewJxjInfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select * from (select a.xh,a.xm,a.bjdm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,b.zysyf,(dense_rank() over (partition by a.bjdm order by to_number(b.zysyf) desc nulls last)) zypm,b.zyjnsyf,b.kcxfzsyf,b.zhcpzf,(dense_rank() over (partition by a.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm,(select jxjsqxn from xtszb) xn from view_xsjbxx a left join view_nbzy_zhszcp b on a.xh=b.xh and b.xn=?) where xh=?",
						new String[] { Base.getJxjsqxn(),xh });
	}
	
	public String sqSave(ZhszcpModel model, HttpServletRequest request) throws Exception {
		boolean bFlag= StandardOperation.insert("xsjxjb", new String[] { "xh", "xn",
				"jxjdm", "sqly", "qsh", "fdyyj" }, new String[] {
				model.getXh(), Base.getJxjsqxn(), model.getJxjdm(),
				DealString.toGBK(model.getSqly()), DealString.toGBK(model.getQsh()),
				DealString.toGBK(model.getFdyyj()) }, request);
		if (bFlag) {
			return "";
		} else {
			return "no";
		}
	}
	
	/**
	 * 查询表头
	 * @param enList
	 * @param cnList目 传入二个数组,英文在前,中文在后
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList,
			String[] cnList) throws Exception {
		dao = DAO.getInstance();
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}
	
	public StringBuffer getWhereSql1(ZhszcpModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and a.xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and a.jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}
	
	public List<String[]> getJxjResult(ZhszcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||jxjdm pk,rownum,xh,xm,xn,nj,bjmc,jxjmc,xysh,xxsh " +
				"from view_xsjxjb where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "xysh", "xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		String sql = "select a.xh,a.xn,a.nd,a.xm,a.xb,a.bjdm,a.nj,a.xymc,a.zymc,a.bjmc," +
		"a.jxjdm,a.sqly,a.fdyyj,a.qsh,b.zysyf,b.zyjnsyf,b.kcxfzsyf," +
		"b.zhcpzf,(select zypm from (select b.xh,(dense_rank() over " +
		"(partition by b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm " +
		"from view_nbzy_zhszcp b where b.xn=?) b where a.xh=b.xh) zypm," +
		"(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn=?) b where a.xh=b.xh) zhpm from view_xsjxjb a left join view_nbzy_zhszcp " +
		"b on a.xh=b.xh and a.xn=? and b.xn=? where a.xh||a.xn||a.jxjdm=?";
		return dao
				.getMapNotOut(sql,
						new String[] {Base.getJxjsqxn(),Base.getJxjsqxn(),Base.getJxjsqxn(), Base.getJxjsqxn(),pkValue });
	}
	
	public boolean jxjmodiSave(ZhszcpModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "qsh",
				"jxjdm", "sqly", "fdyyj" }, new String[] { DealString.toGBK(model.getQsh()),
				model.getJxjdm(), DealString.toGBK(model.getSqly()), DealString.toGBK(model.getFdyyj()) },
				"xh||xn||jxjdm", pkValue, request);
	}
	
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("xsjxjb", "xh||xn||jxjdm",
					whichpk, request);
			if (!bFlag) {//删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	public List<String[]> xyJxjshResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select a.xh||a.xn||a.jxjdm pk,rownum,a.xh,a.xm,a.xn,a.nj,a.bjmc," +
				"a.jxjmc,a.xysh,b.zysyf,b.zhcpzf,"
		+"(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zhpm,(select zypm from (select b.xh,(dense_rank() over "+"(partition by b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm " +
		"from view_nbzy_zhszcp b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zypm from view_xsjxjb a left join view_nbzy_zhszcp b on a.xh=b.xh and a.xh=b.xh and b.xn='"+Base.getJxjsqxn()+"' where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "zysyf", "zypm", "zhcpzf", "zhpm", "xysh"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[] {}) : new String[] {}, opList);
	}
	
	public List<String[]> xxJxjshResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select a.xh||a.xn||a.jxjdm pk,rownum,a.xh,a.xm,a.xn,a.nj,a.bjmc," +
				"a.jxjmc,a.xxsh,b.zysyf,b.zhcpzf,"
		+"(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zhpm,(select zypm from (select b.xh,(dense_rank() over "+"(partition by b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm " +
		"from view_nbzy_zhszcp b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zypm from view_xsjxjb a left join view_nbzy_zhszcp b on a.xh=b.xh and a.xh=b.xh and b.xn='"+Base.getJxjsqxn()+"' where xysh='通过' ";
		String[] opList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "zysyf", "zypm", "zhcpzf", "zhpm", "xxsh"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[] {}) : new String[] {}, opList);
	}
	
	public String xxjxjshResult(String[] keys, String jg, HttpServletRequest request) throws Exception {
		String res = "";
		jg = StringUtils.isNull(jg) ? "未审核" : (StringUtils.isEqual("tg", jg) ? "通过" : "不通过");
		for (int i=0;i<keys.length;i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("xsjxjb",
					new String[] { "xxsh" }, new String[] { jg },
					"xh||xn||jxjdm", whichpk, request);
			if (!bFlag) {
				res += (i+1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res.substring(0, res.length()-1); 
	}
	
	public String xyjxjshResult(String[] keys, String jg, HttpServletRequest request) throws Exception {
		String res = "";
		jg = StringUtils.isNull(jg) ? "未审核" : (StringUtils.isEqual("tg", jg) ? "通过" : "不通过");
		for (int i=0;i<keys.length;i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("xsjxjb",
					new String[] { "xysh" }, new String[] { jg },
					"xh||xn||jxjdm", whichpk, request);
			if (!bFlag) {
				res += (i+1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res.substring(0, res.length()-1); 
	}
	
	public HashMap<String, String> xyjxjShone(String pkValue) throws Exception {
		return dao.getMapNotOut("select a.sqly,a.fdyyj,a.xh,a.xm,a.xb,a.xymc,a.bjmc,a.xysh yesno,a.xypswyhyj yj,a.zymc,a.nj,a.jxjmc,a.qsh,a.xn,b.zysyf,b.zhcpzf,b.zyjnsyf,b.kcxfzsyf,"+"(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zhpm,(select zypm from (select b.xh,(dense_rank() over "+"(partition by b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm "+"from view_nbzy_zhszcp b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zypm from view_xsjxjb a left join view_nbzy_zhszcp b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||a.jxjdm=?", new String[]{pkValue});
	}

	public HashMap<String, String> xxjxjShone(String pkValue) throws Exception {
		return dao.getMapNotOut("select a.sqly,a.fdyyj,a.xh,a.xm,a.xb,a.xymc,a.bjmc,a.xxsh yesno,a.xxshyj yj,a.xyshyj,a.zymc,a.nj,a.jxjmc,a.qsh,a.xn,b.zysyf,b.zhcpzf,b.zyjnsyf,b.kcxfzsyf,"+"(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zhpm,(select zypm from (select b.xh,(dense_rank() over "+"(partition by b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm "+"from view_nbzy_zhszcp b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zypm from view_xsjxjb a left join view_nbzy_zhszcp b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||a.jxjdm=?", new String[]{pkValue});
	}
	
	public boolean xyJxjshonesave(String pkValue, String yesNo, String yj, HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "xysh",
				"xypswyhyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) }, "xh||xn||jxjdm", pkValue, request);
	}
	
	public boolean xxJxjshonesave(String pkValue, String yesNo, String yj,String xyshyj, HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "xxsh",
				"xxshyj", "xyshyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) ,DealString.toGBK(xyshyj)}, "xh||xn||jxjdm", pkValue, request);
	}
	
	public List<HashMap<String, String>> tjTitle() throws Exception {
		String[] enList = new String[]{"pk","rownum","xn", "jxjmc", "tj", "bl"};
		String[] cnList = new String[]{"pk","行号","学年", "奖学金", "条件", "排名"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> tjResult(String xn, String jxjdm) throws Exception {
		StringBuffer query = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			query.append(" and xn='");
			query.append(xn);
			query.append("'");
		}
		if (!StringUtils.isNull(jxjdm)) {
			query.append(" and jxjdm='");
			query.append(jxjdm);
			query.append("'");
		}
		String sql = "select xn||jxjdm||tj pk,rownum,xn,(select jxjmc from jxjdmb b where b.jxjdm=a.jxjdm) jxjmc,(case tj when 'zysyf' then '职业素养分' when 'zyjnsyf' then '职业技能素养分' when 'kcxfzsyf' then '可持续发展分' when 'zhcpzf' then '综合测评总分' when 'zk' then '职业技能与可持发展分总和' else '' end) tj,bl from nbzy_tjszb a where 1=1 ";
		String[] opList = new String[]{"pk","rownum","xn", "jxjmc", "tj", "bl"};
		return dao.rsToVator(sql + query.toString(), new String[]{}, opList);
	}
	
	public boolean tjSave(String xn, String jxjdm, String tj, String bl) throws Exception {
		dao.runUpdate("delete from nbzy_tjszb where xn=? and jxjdm=? and tj=?", new String[]{xn,jxjdm,tj});
		return dao.runUpdate("insert into nbzy_tjszb (xn,jxjdm,tj,bl) values (?,?,?,?)", new String[]{xn,jxjdm,tj,bl});
	}
	
	public boolean tjDel(String[] keys) throws Exception {
		for (int i=0;i<keys.length;i++) {
			dao.runUpdate("delete from nbzy_tjszb where xn||jxjdm||tj=?", new String[]{keys[i]});
		}
		return true;
	}
	
	public ArrayList<String[]> tjList(String xn ,String jxjdm) throws Exception {
		String sql = "select tj,bl from nbzy_tjszb where xn=? and jxjdm=?";
		return dao.rsToVator(sql, new String[]{xn,jxjdm}, new String[]{"tj", "bl"});
	}
	
	public HashMap<String, String> getJxjsqTj(String xh) throws Exception {
		String xn = Base.getJxjsqxn();
		String sql = "select (to_number(zyjnsyf)+to_number(kcxfzsyf)) zkzf,zysyf,zyjnsyf,kcxfzsyf,zhcpzf,(select zhpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zhcpzf) desc nulls last)) zhpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zhpm,(select zypm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zysyf) desc nulls last)) zypm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zypm,(select zjpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.zyjnsyf) desc nulls last)) zjpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zjpm,(select kcxpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(b.kcxfzsyf) desc nulls last)) kcxpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) kcxpm,(select zkpm from (select b.xh,(dense_rank() over (partition by " +
		"b.bjdm order by to_number(to_number(b.zyjnsyf)+to_number(b.kcxfzsyf)) desc nulls last)) zkpm from view_nbzy_zhszcp " +
		"b where b.xn='"+Base.getJxjsqxn()+"') b where a.xh=b.xh) zkpm from nbzy_zhszcp a where xh=? and xn=?";
		return dao.getMapNotOut(sql, new String[]{xh, xn});
	}
	
	public HashMap<String, String> jxjPrint(String pkValue) throws Exception {
		return dao.getMapNotOut("select xn,bjmc,xm,jxjmc,qsh,xh,sqly,fdyyj,xypswyhyj,xxshyj,xyshyj from view_xsjxjb where xh||xn||jxjdm=?", new String[]{pkValue});
	}
}
