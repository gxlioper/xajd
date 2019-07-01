package xgxt.pjpy.zjcm.zhf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.zjcm.zhf.ZhfModel;
import xgxt.pjpy.zjcm.ZhszcpDAO;

public class ZhfDAO extends ZhszcpDAO {

	/**
	 * @author luo
	 * @describe 获得综合分列表
	 */
	public ArrayList<String[]> getZhfList(String tableName, ZhfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = new DAO();
		
		//HashMap<String,String> map = getFzPm("3061001057","2008-2009","01");
		//List<HashMap<String,String>> map = getCjList("3061001057","2008-2009");
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
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{xq},"xqmc");
		String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.getJxjsqxq()},"xqmc");
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
		query.append(Base.isNull(xqmc) ? " and 1=1" : " and xq='"+xqmc+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select t.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,dyf,zyf,tyf,nlf,zhf from (select *");
		sql.append(" from view_zjcm_zhf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql.append(" '' dyf,'' zyf,'' tyf,'' nlf,'' zhf,'' zhpm from view_xsjbxx b where not exists(select 1 from zjcm_zhf a where ");
		sql.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query+" )");
		sql.append(" where r > "+model.getPages().getStart()+ " and r <= ");
		sql.append((model.getPages().getStart() + model.getPages().getPageSize()));

		//System.out.println(sql);
		ArrayList<String[]> list =dao.rsToVator(sql.toString(), inPutList,colList);
		//	分页
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select count(*) num from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,dyf,zyf,tyf,nlf,zhf from (select *");
		sql2.append(" from view_zjcm_zhf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql2.append(" '' dyf,'' zyf,'' tyf,'' nlf,'' zhf,'' zhpm from view_xsjbxx b where not exists(select 1 from zjcm_zhf a where ");
		sql2.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query);
			
		model.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql2.toString(), inPutList, "num")));
		
		return list;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存综合分
	 */
	public boolean saveZhf(ZhfModel model) throws SQLException {
		
		DAO dao = new DAO();
		
		String[] xh = model.getXhV();
		String[] xn = model.getXnV();
		String[] xq = model.getXqV();
		String[] dyf = model.getDyf();
		String[] zyf = model.getZyf();
		String[] tyf = model.getTyf();
		String[] nlf = model.getNlf();
		
		boolean flg = false;
		String num = "";
		String sql = "";
		String xqdm = "";
		String pk = "";
		StringBuffer sb = new StringBuffer();
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				sql = "select xqdm from xqdzb where xqmc = ?";
				xqdm = dao.getOneRs(sql,
						new String[] { DealString.toGBK(xq[i]) }, "xqdm");
				pk = xh[i] + xn[i] + xqdm;

				sql = "select count(*) num from zjcm_zhf where xh||xn||xq=? and zhf is not null";
				num = dao.getOneRs(sql, new String[]{pk}, "num");
				
				//TODO

				if ("0".equalsIgnoreCase(num)) {

					sql = "delete from zjcm_zhf where xh||xn||xq='" + pk + "'";
					sb.append(sql);
					sb.append("!!#!!");

					sql = "insert into zjcm_zhf(xh,xn,xq,dyf,zyf,tyf,nlf) values('"
							+ xh[i]
							+ "','"
							+ xn[i]
							+ "','"
							+ xqdm
							+ "','"
							+ dyf[i]
							+ "','"
							+ zyf[i]
							+ "','"
							+ tyf[i]
							+ "','"
							+ nlf[i] + "')";
					sb.append(sql);
					sb.append("!!#!!");
				} else {
					sql = "update zjcm_zhf set dyf='" + dyf[i] + "',zyf='"
							+ zyf[i] + "',tyf='" + tyf[i] + "',nlf='" + nlf[i]
							+ "' where xh||xn||xq='" + pk + "'";
					sb.append(sql);
					sb.append("!!#!!");
				}
			}
		}
		String[] actsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 判断是否测评小组成员
	 */
	public boolean isCpzCy(String xh, String xydm) {
		DAO dao = new DAO();
		boolean flg = false;
		String xn = Base.getJxjsqxn();
		String sql = "select count(*) num from view_bjgbxx a where exists (select 1 "
				+ "from zjcm_cpz b where a.bjgbdm = b.zwdm and xydm = ? and xn = ?) and xh = ?";
		String count = dao.getOneRs(sql, new String[] { xydm, xn, xh }, "num");
		if (!"0".equalsIgnoreCase(count)) {
			flg = true;
		}
		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 判断登陆时间是否在所设置的时间范围内
	 */
	public boolean inTime(String xydm) {
		DAO dao = new DAO();
		boolean flg = false;
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from (select distinct(lrkssj),lrjssj,sbkssj,sbjssj from ");
		sql.append("zjcm_cpz where xn=? and xq=? and xydm=? ) where lrkssj <= to_char(sysdate, 'yyyymmdd') ");
		sql.append("and lrjssj >= to_char(sysdate, 'yyyymmdd')");

		String num = dao.getOneRs(sql.toString(), new String[] { xn,xq, xydm },
				"num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}		
	/**
	 * @author luo
	 * @describe 获得学生基本信息
	 */
	public static HashMap<String, String> getStuInfo(String xh) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc" };
		return commonQueryOne("view_xsjbxx", colList, "xh", xh);
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
	 * 获得学期名称
	 * @throws Exception 
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?", new String[]{xqdm}, "xqmc");
	}
	
	/**
	 * 保存综合素质分比例
	 * @throws Exception 
	 */
	public boolean saveZhfBl(ZhfModel model, HttpServletRequest request)
			throws Exception {
		String dyfbl = model.getDyfbl();
		dyfbl = Base.isNull(dyfbl) ? "0" : dyfbl;

		String tyfbl = model.getTyfbl();
		tyfbl = Base.isNull(tyfbl) ? "0" : tyfbl;

		String zyfbl = model.getZyfbl();
		zyfbl = Base.isNull(zyfbl) ? "0" : zyfbl;

		String nlfbl = model.getNlfbl();
		nlfbl = Base.isNull(nlfbl) ? "0" : nlfbl;

		boolean flg = StandardOperation.delete("zjcm_zhf_bl", "xn||xq", Base
				.getJxjsqxn()
				+ Base.getJxjsqxq(), request);
		if (flg) {
			flg = StandardOperation.insert("zjcm_zhf_bl", new String[] { "xn",
					"xq", "dyfbl", "zyfbl", "tyfbl", "nlfbl" }, new String[] {
					Base.getJxjsqxn(), Base.getJxjsqxq(), dyfbl, zyfbl, tyfbl,
					nlfbl }, request);
		}

		return flg;
	}
	
	/**
	 * 获得综合素质分比例
	 * @throws Exception 
	 */
	public HashMap<String, String> getZhfBl() {
		DAO dao = DAO.getInstance();

		return dao.getMap("select * from zjcm_zhf_bl where xn=? and xq=?",
				new String[] { Base.getJxjsqxn(), Base.getJxjsqxq() },
				new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" });
	}
	
	/**
	 * 获得综合素质分比例
	 * 
	 * @throws Exception
	 */
	public String isZhfbl() {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from zjcm_zhf_bl where xn=? and xq=?";
		return dao.getOneRs(sql, new String[] { Base.getJxjsqxn(), Base.getJxjsqxq() },
				"num");
	}
	
	/**
	 * 计算综合分
	 * 
	 * @throws Exception
	 */
	public boolean jsZhf(String xn, String xq) throws Exception {
		
		DAO dao = DAO.getInstance();
		String sql="";
		boolean flg = false;
		StringBuffer insSql = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		
		insSql.append("select xh, xn, xq, case when zhf < 1 then '0'||zhf else zhf end zhf ");
		insSql.append("from (select xh, xn, xq,trim(to_char(zhf,999.99)) zhf ");
		insSql.append("from (select xh, xn, xq, dyf, zyf, tyf, nlf, (dyf * dyfbl / 100) +  ");
		insSql.append("(zyf * zyfbl / 100) + (tyf * tyfbl / 100) + (nlf * nlfbl / 100) zhf ");
		insSql.append("from (select a.xh, a.xn, a.xq, nvl(a.dyf, 0) dyf, b.dyfbl, ");
		insSql.append("nvl(a.zyf, 0) zyf, b.zyfbl, nvl(a.tyf, 0) tyf, b.tyfbl, ");
		insSql.append("nvl(a.nlf, 0) nlf, b.nlfbl from zjcm_zhf a, zjcm_zhf_bl b ");
		insSql.append("where a.xn = b.xn and a.xq = b.xq)) where xn=? and xq=?) ");
		
		//System.out.println(insSql);
		List<HashMap<String, String>> list = dao.getList(insSql.toString(),
				new String[] { xn, xq },
				new String[] { "xh", "xn", "xq", "zhf" }); 
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
							
				sql = "update zjcm_zhf set zhf ='" + map.get("zhf")
						+ "' where xh='" + map.get("xh") + "' and xn='"
						+ map.get("xn") + "' and xq='" + map.get("xq") + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		
		String[] upzhf = sb.toString().split("!!#!!");
		dao.runBatch(upzhf);
		
		insSql = new StringBuffer();
		insSql.append("select * from (select xn,xq,xh,xm,xb,bjdm,bjmc,nvl(zyf,0) zyf,zhf,(rank() ");
		insSql.append("over(partition by xn,xq,bjdm order by to_number(zhf) desc nulls last)) pm  from view_zjcm_zhf ");
		insSql.append("where xn=? and xq=? order by bjdm,pm,zyf desc nulls last)");

		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?",
				new String[] { xq }, "xqmc");
		List<HashMap<String, String>> mapList = dao.getList(insSql.toString(),
				new String[] { xn, xqmc }, new String[] { "xh", "xm", "xb",
						"bjmc", "xn", "xq", "zyf", "zhf", "pm" });
		if (mapList != null && mapList.size() > 0) {
			int pm = 0;
			for (int i = 0; i < mapList.size(); i++) {
				HashMap<String, String> map1 = mapList.get(i);
				if (i > 0) {
					HashMap<String, String> map2 = mapList.get(i - 1);
					if (map2.get("pm").equalsIgnoreCase(map1.get("pm"))) {
						if (!map2.get("zyf").equalsIgnoreCase(map1.get("zyf"))) {
							pm++;
							map1.put("newpm", String.valueOf(Integer
									.parseInt(map1.get("pm"))
									+ pm));
						} else {
							map1.put("newpm", String.valueOf(Integer
									.parseInt(map1.get("pm"))
									+ pm));
						}
					} else {
						pm = 0;
						map1.put("newpm", String.valueOf(Integer.parseInt(map1
								.get("pm"))));
					}
					sql = "update zjcm_zhf set zhpm ='" + map1.get("newpm")
							+ "' where xh='" + map1.get("xh") + "' and xn='"
							+ xn + "' and xq='" + xq + "'";
					sb.append(sql);
					sb.append("!!#!!");
				} else {
					sql = "update zjcm_zhf set zhpm ='" + map1.get("pm")
							+ "' where xh='" + map1.get("xh") + "' and xn='"
							+ xn + "' and xq='" + xq + "'";
					sb.append(sql);
					sb.append("!!#!!");
				}
			}
		}
		
		String[] uppm = sb.toString().split("!!#!!");
		int[] res2 = dao.runBatch(uppm);
		for (int i = 0; i < res2.length; i++) {
			flg = (res2[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		//System.out.println(sql);
		return flg;
	}
}
