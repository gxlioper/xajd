
package xgxt.pjpy.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class JxjDAO extends PjpyYnysDAO {
	public List<String> values = new ArrayList<String>();//保存查询条件值
	public HashMap<String, String> getJxjxx(String jxjdm, String xh) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc," +
						"(select jxjmc from jxjdmb where jxjdm=?) jxjmc,(select jxjlb " +
						"from jxjdmb where jxjdm=?) jxjlb,(select jlje from jxjdmb where" +
						" jxjdm=?) jlje,(select sjhm from xsfzxxb b where b.xh=view_xsjbxx.xh)" +
						" sjhm,(select wysp from xsfzxxb b where b.xh=view_xsjbxx.xh) wysp" +
						" from view_xsjbxx where xh = ?",
						new String[] { jxjdm, jxjdm, jxjdm, xh });
	}
	
	public boolean saveJxjxx(JxjModel model, HttpServletRequest request)
			throws Exception {
		dao = DAO.getInstance();
		StandardOperation.delete("xsjxjb", "xh||xn||jxjdm", model.getXh()
				+ model.getXn() + model.getJxjdm(), request);
		saveKcb(model);
		saveZpb(model);
		return StandardOperation.insert("xsjxjb", new String[] { "xh", "xn",
				"nd", "xq", "jxjdm", "drzw", "dnshjxj", "zykpjcj", "whkpjcj",
				"zhszcpcj", "zhszcpcjpm", "zysj", "ejyxyj", "xyshyj" },
				new String[] { model.getXh(), model.getXn(), model.getNd(),
						model.getXq(), model.getJxjdm(),
						DealString.toGBK(model.getDrzw()), model.getDnshjxj(),
						model.getZykpjcj(), model.getWhkpjcj(),
						model.getZhszcpcj(), model.getZhszcpcjpm(),
						DealString.toGBK(model.getZysj()),
						DealString.toGBK(model.getEjyxyj()),
						DealString.toGBK(model.getXyyj()) }, request);
	}

	public void saveKcb(JxjModel model) throws Exception {
		String[] sqls = new String[model.getKcmc().length];
		StringBuffer sql;
		for (int i = 0; i < sqls.length; i++) {
			sql = new StringBuffer();
			sql
					.append("insert into ynys_pjpykcb (kid,xh,xn,kcmc,cj,kclx,sfbxk) values (AUTOJXJID.NEXTVAL,");
			sql.append("'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(DealString.toGBK(model.getKcmc()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getCj()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getKclx()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getSfbxk()[i]));
			sql.append("')");
			if (!StringUtils.isNull(model.getKcmc()[i])) {
				sqls[i] = sql.toString();
			}  else {
				sqls[i] = "select sysdate from dual";
			}
		}
		dao.runBatch(sqls);
	}

	public void saveZpb(JxjModel model) throws Exception {
		String[] sqls = new String[model.getFbkw().length];
		StringBuffer sql ;
		for (int i = 0; i < sqls.length; i++) {
			sql = new StringBuffer();
			sql
					.append("insert into ynys_pjpyzpb (zid,xh,xn,fbkw,lwhzpmc,hjjb,hjdj) values (AUTOJXJID.NEXTVAL,");
			sql.append("'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(DealString.toGBK(model.getFbkw()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getLwhzpmc()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHjjb()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getHjdj()[i]));
			sql.append("')");
			if (!StringUtils.isNull(model.getLwhzpmc()[i])) {
				sqls[i] = sql.toString();
			} else {
				sqls[i] = "select sysdate from dual";
			}
		}
		dao.runBatch(sqls);
	}
	
	public List<String[]> getJxjResult(JxjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||jxjdm pk,rownum,xh,xm,xn,nj,bjmc,jxjmc,xysh,xxsh " +
				"from view_xsjxjb where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "xysh", "xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql(JxjModel model) throws Exception {
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
	
	public HashMap<String, String> viewJxjshByxy(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,jxjmc,sxzzddszf," +
				"kxwhszf,sxlxszf,sjlxcxf,zhszcpzf,xysh yesNo,xyshyj yj from view_xsjxjb " +
				"where xn||nd||xh||jxjdm=?", new String[]{pkValue});
	}
	
	public HashMap<String, String> viewJxjshByxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,jxjmc,sxzzddszf," +
				"kxwhszf,sxlxszf,sjlxcxf,zhszcpzf,xxsh yesNo,xxshyj yj from view_xsjxjb " +
				"where xn||nd||xh||jxjdm=?", new String[]{pkValue});
	}
	
	public boolean saveJxjshByxy(String pkValue, String yesNo, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "xysh",
				"xyshyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) }, "xn||nd||xh||jxjdm", pkValue, request);
	}
	
	public boolean saveJxjshByxx(String pkValue, String yesNo, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("xsjxjb", new String[] { "xxsh",
				"xxshyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) }, "xn||nd||xh||jxjdm", pkValue, request);
	}
	
	public String[] getXhList(String[] keys) throws Exception {
		dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("select xh from xsjxjb where xh||xn||jxjdm in (");
		for (int i=0;i<keys.length;i++) {
			sql.append("'");
			sql.append(keys[i]);
			sql.append("',");
		}
		String sl = sql.substring(0, sql.toString().length()-1);
		sl += ")";
		return dao.getOneRs(sl, new String[]{}, new String[]{"xh"});
	}
	
	public void delKcb(String[] xh) throws Exception {
		dao = DAO.getInstance();
		String xn = dao.getOneRs("select jxjsqxn from xtszb", new String[]{}, "jxjsqxn");
		StringBuffer sql = null;
		String[] sl= new String[xh.length];
		for (int i=0;i<xh.length;i++) {
			sql = new StringBuffer();
			sql.append("delete from ynys_pjpykcb where xn='"+xn+"' and xh=");
			sql.append("'");
			sql.append(xh[i]);
			sql.append("'");
			sl[i] = sql.toString();
		}
		dao.runBatch(sl);
	}
	
	public void delZpb(String[] xh) throws Exception {
		dao = DAO.getInstance();
		String xn = dao.getOneRs("select jxjsqxn from xtszb", new String[]{}, "jxjsqxn");
		StringBuffer sql = null;
		String[] sl= new String[xh.length];
		for (int i=0;i<xh.length;i++) {
			sql = new StringBuffer();
			sql.append("delete from ynys_pjpyzpb where xn='"+xn+"' and xh=");
			sql.append("'");
			sql.append(xh[i]);
			sql.append("'");
			sl[i] = sql.toString();
		}
		dao.runBatch(sl);
	}
	
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xn,nd,xm,xb,nj,xymc,zymc,bjmc,jxjdm,drzw,dnshjxj," +
						"zysj,ejyxyj,zykpjcj,whkpjcj,zhszcpcj,zhszcpcjnjpm from view_xsjxjb where xh||xn||jxjdm=?",
						new String[] { pkValue });
	}
	
	public boolean modiJxjsave(String pkValue, JxjModel model,
			HttpServletRequest request) throws Exception {
		dao = DAO.getInstance();
		String[] rs = dao.getOneRs("select jxjsqnd,jxjsqxq from xtszb", new String[]{}, new String[]{"jxjsqnd", "jxjsqxq"});
		if (StringUtils.isEqual(pkValue, model.getXh() + model.getXn()
				+ model.getJxjdm())) {
			return dao
					.runUpdate(
							"update xsjxjb set drzw=?,dnshjxj=?,zysj=?,ejyxyj=? where xh||xn||jxjdm = ?",
							new String[] { DealString.toGBK(model.getDrzw()),
									DealString.toGBK(model.getDnshjxj()),
									DealString.toGBK(model.getZysj()),
									DealString.toGBK(model.getEjyxyj()),pkValue });
		} else {
			dao.runUpdate("delete from xsjxjb where xh||xn||jxjdm=?",
					new String[] { pkValue });
			dao.runUpdate("delete from xsjxjb where xh||xn||jxjdm=?",
					new String[] { model.getXh() + model.getXn()
							+ model.getJxjdm() });
			return dao.runUpdate(
					"insert into xsjxjb (xh,xn,nd,xq,jxjdm,drzw,dnshjxj,zysj,"
							+ "ejyxyj) values (?,?,?,?,?,?,?,?,?)",
					new String[] { model.getXh(), model.getXn(), rs[0], rs[1],
							model.getJxjdm(),
							DealString.toGBK(model.getDrzw()),
							DealString.toGBK(model.getDnshjxj()),
							DealString.toGBK(model.getZysj()),
							DealString.toGBK(model.getEjyxyj()) });
		}
	}
	
	public List<String[]> getKcList(String xh, String xn) throws Exception {
		dao = DAO.getInstance();
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		xn = StringUtils.isNull(xn) ? "" : xn.trim();
		return dao.rsToVator(
				"select kcmc,cj,kclx,sfbxk from ynys_pjpykcb where xh=? and xn=?",
				new String[] { xh, xn }, new String[] {"kcmc", "cj", "kclx", "sfbxk"});
	}
	
	public List<String[]> getZpList(String xh, String xn) throws Exception {
		dao = DAO.getInstance();
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		xn = StringUtils.isNull(xn) ? "" : xn.trim();
		return dao.rsToVator(
				"select fbkw,lwhzpmc,hjjb,hjdj from ynys_pjpyzpb where xh=? and xn=?",
				new String[] { xh, xn }, new String[] {"fbkw", "lwhzpmc", "hjjb", "hjdj"});
	}
	
	/**
	 * 综合素质测评分自动计算
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpAutoCount(String xn, String xydm) throws Exception {
		dao = DAO.getInstance();
		String sql = "{call YNYS_ZHSZCPACCOUNT(?,?)}";
		boolean res = dao.runProcuder(sql, new String[] {xn, xydm});
		if(res){
			return true;		
		}else{
			return false;
		}
	}
}
