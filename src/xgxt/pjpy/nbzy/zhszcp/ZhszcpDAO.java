package xgxt.pjpy.nbzy.zhszcp;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class ZhszcpDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = null;
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
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
		return whereSql;
	}
	
	public StringBuffer getWhereSql1(ZhszcpModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
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
		return whereSql;
	}
	
	public StringBuffer getWhereSql2(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = '");
			whereSql.append(model.getXh());
			whereSql.append("'");
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = '");
			whereSql.append(model.getNj());
			whereSql.append("'");
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = '");
			whereSql.append(model.getXydm());
			whereSql.append("'");
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = '");
			whereSql.append(model.getZydm());
			whereSql.append("'");
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = '");
			whereSql.append(model.getBjdm());
			whereSql.append("'");
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like '%");
			whereSql.append(DealString.toGBK(model.getXm()));
			whereSql.append("%'");
		}
		return whereSql;
	}
	
	public List<HashMap<String, String>> title(String[] enList, String[] cnList) throws Exception {
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> zyResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select a.zyid pk,a.xh,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.xm,a.xn,a.bjmc,(case when a.zt='0' then '加分' else '扣分' end) zt,a.fs,b.zf from view_pjpy_zysycpb a ";
		String[] opList = new String[]{"pk", "xn", "xq", "xm", "bjmc", "fs", "zt", "zf"};
		String tmp = "left join (select xh,to_number(80+sum(to_number(zf))) zf from (select xh,xn,fs,lr,zt," +
		"(case when zt='1' then '-'||fs else fs end) zf from view_pjpy_zysycpb " +
		"where xn='"+model.getXn()+"') group by xh) b on a.xh=b.xh where 1=1 ";
		ArrayList<String[]> exisList =dao.rsToVator(sql + tmp + whereSql.toString() , values != null ? values
				.toArray(new String[] {}) : new String[] {}, opList);
		sql = "select 'xh'||a.xh pk,'"+model.getXn()+"' xn,a.xh,'' xq,a.xm,a.bjmc,'' fs,'' zt,'80' zf from view_xsjbxx a where not exists (select 1 from view_pjpy_zysycpb b where a.xh=b.xh and b.xn='"+model.getXn()+"') ";
		StringBuffer query = getWhereSql1(model);
		ArrayList<String[]> noexisList = dao.rsToVator(sql + query.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return copyArray(exisList, noexisList);
	}
	
	public List<String[]> zyjnResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select a.zyid pk,a.xh,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.xm,a.xn,a.bjmc,(case when a.zt='0' then '加分' else '扣分' end) zt,a.fs,b.zf from view_pjpy_zyjnsycpb a ";
		String[] opList = new String[]{"pk", "xn", "xq", "xm", "bjmc", "fs", "zt", "zf"};
		String tmp = "left join (select xh,to_number(60+sum(to_number(zf))) zf from (select xh,xn,fs,lr,zt," +
		"(case when zt='1' then '-'||fs else fs end) zf from view_pjpy_zyjnsycpb " +
		"where xn='"+model.getXn()+"') group by xh) b on a.xh=b.xh where 1=1 ";
		ArrayList<String[]> exisList =dao.rsToVator(sql + tmp + whereSql.toString() , values != null ? values
				.toArray(new String[] {}) : new String[] {}, opList);
		sql = "select 'xh'||a.xh pk,'"+model.getXn()+"' xn,a.xh,'' xq,a.xm,a.bjmc,'' fs,'' zt,'60' zf from view_xsjbxx a where not exists (select 1 from view_pjpy_zyjnsycpb b where a.xh=b.xh and b.xn='"+model.getXn()+"') ";
		StringBuffer query = getWhereSql1(model);
		ArrayList<String[]> noexisList = dao.rsToVator(sql + query.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return copyArray(exisList, noexisList);
	}
	
	public List<String[]> kcxfzResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		StringBuffer query = getWhereSql2(model);
		String sql = "select a.zyid pk,rownum,a.xh,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.xm,a.xn,a.bjmc,(case when a.zt='0' then '加分' else '扣分' end) zt,a.fs,b.zf from view_pjpy_kcxfzsycpb a ";
		String[] opList = new String[]{"pk", "xn", "xq", "xm", "bjmc", "fs", "zt", "zf"};
		String tmp = "left join (select xh,zf+nvl((select sum((case when zt='1' then '-'||fs else fs end)) zf from view_pjpy_kcxfzsycpb b where xn='"+model.getXn()+"' and b.xh=a.xh),0) zf from (select xh,trunc(sum(cj * xf) / zxf, 2) zf from (select xh,nvl(cj, 0) cj,nvl(xf, 0) xf,(select sum(nvl(xf, 0)) zxf from view_cjb b where b.xn = '"+model.getXn()+"' and a.xh = b.xh and xf <> 0) zxf from view_cjb a where xn = '"+model.getXn()+"'"+query.toString()+") where 1=1 group by xh) a) b on a.xh=b.xh where 1=1 ";
		ArrayList<String[]> exisList = dao.rsToVator(sql + tmp + whereSql.toString() , values != null ? values
				.toArray(new String[] {}) : new String[] {}, opList);
		sql = "select a.* from (select 'xh'||xh pk,'"+model.getXn()+"' xn,'' xq,(select bjmc from view_xsjbxx b where b.xh=a.xh) bjmc,(select xm from view_xsjbxx b where b.xh=a.xh) xm,(select nj from view_xsjbxx b where b.xh=a.xh) nj,(select xydm from view_xsjbxx b where b.xh=a.xh) xydm,(select bjdm from view_xsjbxx b where b.xh=a.xh) bjdm,(select zydm from view_xsjbxx b where b.xh=a.xh) zydm,'' fs,'' zt,xh,zf from (select xh,trunc(sum(cj * xf) / zxf, 2) zf from (select xh,nvl(cj, 0) cj,nvl(xf, 0) xf,(select sum(nvl(xf, 0)) zxf from view_cjb b where b.xn = '"+model.getXn()+"' and a.xh = b.xh and xf <> 0) zxf from view_cjb a where xn = '"+model.getXn()+"'"+query.toString()+" ) where 1=1 group by xh ) a) a where not exists (select 1 from view_pjpy_kcxfzsycpb b where b.xn='"+model.getXn()+"' and a.xh=b.xh)";
		ArrayList<String[]> noexisList = dao.rsToVator(sql + query.toString(), new String[]{}, opList);
		return copyArray(exisList, noexisList);
	}
	
	public List<String[]> zhszResult(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xn||xh pk,xn,xm,bjmc,zysyf,zyjnsyf,kcxfzsyf,zhcpzf,(dense_rank() over (partition by bjdm order by zhcpzf desc nulls last)) bjpm from view_nbzy_zhszcp a where 1=1 ";
		String[] opList = new String[]{"pk", "xn", "xm", "bjmc", "zysyf", "zyjnsyf", "kcxfzsyf", "zhcpzf", "bjpm"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
	}
	
	public ArrayList<String[]> copyArray(ArrayList<String[]> list1, ArrayList<String[]> list2) throws Exception {
		if (list1 != null) {
			 for (int i=0;i<list2.size();i++) {
				 String[] tmp = list2.get(i);
				 list1.add(tmp);
			 }
			 return list1;
		} else {
			return list1;
		}
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,bjmc,zymc,xydm,zydm,bjdm from view_xsjbxx where xh=?",
						new String[] { xh });
	}
	
	public String zyszfSave(ZhszcpModel model, String tname) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFs().length];
		StringBuffer sql = null;
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into "+tname+" (xh,xn,xq,fs,lr,zt) values('");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getFs()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("','");
			sql.append(model.getZt()[i]);
			sql.append("')");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	public HashMap<String, String> viewSzf(String pkValue, String tname) throws Exception {
		return dao.getMapNotOut("select xn,xq,xh,fs,lr,zt,xm,xb,nj,xymc,zymc,bjmc from "+tname+" where zyid=?", new String[]{pkValue});
	}
	
	public boolean szfModisave(String pkValue, String tname, ZhszcpModel model) throws Exception {
		return dao.runUpdate("update " + tname
				+ " set xn=?,xq=?,fs=?,lr=?,zt=? where zyid=?", new String[] {
				model.getXn(), model.getXq(), model.getTfs(), DealString.toGBK(model.getTlr()),
				model.getTzt(), pkValue });
	}
	
	public String szfDel(String tname, String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			if ("x".equalsIgnoreCase(whichpk.substring(0, 1))) {
				shRes += (i + 1);
				shRes += ",";
			} else {
				boolean bFlag = StandardOperation.delete(tname, "zyid",
						whichpk, request);
				if (!bFlag) {// 删除失败后记录删除失败的行号
					shRes += (i + 1);
					shRes += ",";
				}
			}
			
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	public boolean zhszcpCount(String xn, String xydm, String zydm, String bjdm, String nj) throws Exception {
		String sql = "{call NBZY_ZHSZCPCOUNT(?,?)}";
		boolean res = dao.runProcuder(sql, new String[] {xn, xydm});
		return res;
	}
}
