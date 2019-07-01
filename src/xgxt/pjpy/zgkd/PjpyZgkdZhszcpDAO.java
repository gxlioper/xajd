package xgxt.pjpy.zgkd;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyZgkdZhszcpDAO {

	DAO dao = DAO.getInstance();
	
	public static PjpyZgkdZhszcpDAO mydao = new PjpyZgkdZhszcpDAO();
	
	public static PjpyZgkdZhszcpDAO getInstance() {
		return mydao;
	}
	
	private ArrayList<String> values = null;
	
	/**
	 * 素质等级代码列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSzdjList() throws Exception {
		return dao.getList("select szdjdm,szdjmc from szdjdmb",
				new String[] {}, new String[] { "szdjdm", "szdjmc" });
	}
	
	public HashMap<String, String> getStuInfo(String xh) {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
						new String[] { xh });
	}
	
	/**
	 * 保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean szfSave(ZhszcpModel model, HttpServletRequest request)
			throws Exception {
		String dj = dao.getOneRs("select szdjmc from szdjdmb where szdjdm=?",
				new String[] { model.getJbszcpdj() }, "szdjmc");
		return StandardOperation.insert("zgkd_zhszcpb", new String[] { "xh",
				"xn", "pdcpdf", "kcxxcjdf", "sxcpdf", "jbszcpdf", "jbszcpdj",
				"fzszcpdf" },
				new String[] { model.getXh(), model.getXn(), model.getPdcpdf(),
						model.getKcxxcjdf(), model.getSxcpdf(),
						model.getJbszcpdf(), dj,
						model.getFzszcpdf() }, request);
	}
	
	/**
	 * 修改
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean szfUpdate(ZhszcpModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		String dj = dao.getOneRs("select szdjmc from szdjdmb where szdjdm=?",
				new String[] { model.getJbszcpdj() }, "szdjmc");
		return StandardOperation.update("zgkd_zhszcpb", new String[] {
				"pdcpdf", "kcxxcjdf", "sxcpdf", "jbszcpdf", "jbszcpdj",
				"fzszcpdf" ,"xn"}, new String[] { model.getPdcpdf(),
				model.getKcxxcjdf(), model.getSxcpdf(), model.getJbszcpdf(),
				dj, model.getFzszcpdf(),model.getXn() }, "xh||xn", pkValue,
				request);
	}
	
	/**
	 * 显示
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> szfView(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xn,pdcpdf,kcxxcjdf,sxcpdf,jbszcpdf,(select szdjdm from szdjdmb b where b.szdjmc=a.jbszcpdj) jbszcpdj,fzszcpdf,xm,xb,nj,xymc,zymc,bjmc from view_zgkd_zhszcp a where xh||xn=?",
						new String[] { pkValue });
	}
	
	/**
	 * 删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String szfDelete(String[] keys, HttpServletRequest request) throws Exception {
		String sFlag = "";
		for (int i=0;i<keys.length;i++) {
			boolean bFlag = StandardOperation.delete("zgkd_zhszcpb", "xh||xn", keys[i], request);
			if (!bFlag) {
				sFlag +=(i+1) + ",";
			}
		}
		return StringUtils.isNull(sFlag) ? "" : sFlag.substring(0, sFlag.length()-1);
	}
	
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getJbszcpdj())) {
			whereSql.append(" and jbszcpdj = ?");
			values.add(model.getJbszcpdj());
		}
		return whereSql;
	}
	
	/**
	 * 查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szfList(ZhszcpModel model, String isFdy, String userName, PjpyZgkdZhszcpActionForm dataSearchForm) throws Exception {
		String pm = model.getPmfs();
		String dj = dao.getOneRs("select szdjmc from szdjdmb where szdjdm=?",
				new String[] { model.getJbszcpdj() }, "szdjmc");
		model.setJbszcpdj(dj);
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn pk,rownum r,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		if ("zy".equalsIgnoreCase(pm)) {
			sql = "select xh||xn pk,rownum r,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		} else if ("bj".equalsIgnoreCase(pm)){
			sql = "select xh||xn pk,rownum r,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		}
		String[] opList = new String[]{"pk","r","xh","xm","bjmc","xn","kcxxcjdf","kpm","pdcpdf","sxcpdf","jbszcpdf","jpm","fzszcpdf","fpm","jbszcpdj"};
		if ("true".equalsIgnoreCase(isFdy)) {//用户是辅导员则只显示其所在班级信息
			whereSql.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
		}
		return dao.rsToVator("select * from (" + sql + whereSql.toString() + ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public int szfListNum(ZhszcpModel model, String isFdy, String userName) throws Exception {
		String pm = model.getPmfs();
		String dj = dao.getOneRs("select szdjmc from szdjdmb where szdjdm=?",
				new String[] { model.getJbszcpdj() }, "szdjmc");
		model.setJbszcpdj(dj);
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		if ("zy".equalsIgnoreCase(pm)) {
			sql = "select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		} else if ("bj".equalsIgnoreCase(pm)){
			sql = "select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a where 1=1";
		}
		String[] opList = new String[]{"pk","num","xh","xm","bjmc","xn","kcxxcjdf","kpm","pdcpdf","sxcpdf","jbszcpdf","jpm","fzszcpdf","fpm","jbszcpdj"};
		if ("true".equalsIgnoreCase(isFdy)) {//用户是辅导员则只显示其所在班级信息
			whereSql.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
		}
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList).size();
	}
	
	/**
	 * 统计结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ArrayList<String[]>> szcpHz(ZhszcpModel model)
			throws Exception {
		String xydm = model.getXydm();
		String[] njList = dao
				.getArray(
						"select distinct nj from view_njxyzybj where xydm=? order by nj asc",
						new String[] { xydm }, "nj");
		boolean bFlag = dao.runProcuder("{call PROC_ZGKD_ZHSZCPHZ(?,?)}",
				new String[] { xydm, model.getXn() });// 将统计出来的结果先插入表中
		ArrayList<ArrayList<String[]>> rs = new ArrayList<ArrayList<String[]>>();
		if (bFlag) {
			if (njList != null && njList.length > 0) {
				for (int i = 0; i < njList.length; i++) {
					ArrayList<String[]> tmp = new ArrayList<String[]>();
					String[] opList = new String[] { "xydm", "xymc",
							"zydm", "nj", "zymc", "zyrs", "yxrs", "lhrs", "hgrs",
							"bhgrs" };
					String sql = "select xydm,(select distinct trim(xymc) from view_njxyzybj b where b.xydm=a.xydm) xymc,zydm,nj,(select distinct trim(zymc) from view_njxyzybj b where b.zydm=a.zydm) zymc,zyrs,(case when yxrs<>0 then yxrs||'('||yxl||'%)' else yxrs end) yxrs,"
							+ "(case when lhrs<>0 then lhrs||'('||lhl||'%)' else lhrs end) lhrs,"
							+ "(case when hgrs<>0 then hgrs||'('||hgl||'%)' else hgrs end) hgrs,"
							+ "(case when bhgrs<>0 then bhgrs||'('||bhgl||'%)' else bhgrs end) bhgrs from zgkd_zhszcp_tmp a where xydm=? and nj=? and xn=?";
					tmp = dao.rsToVator(sql, new String[] { xydm, njList[i],
							model.getXn() }, opList);// 专业素质等级比例
					String[] xyrs = dao
							.getOneRs(
									"select '' xydm,'' xymc,'' zydm,nj,'合计' zymc,xyrs zyrs,(case when yxrs<>0 then yxrs||'('||yxl||'%)' else yxrs end) yxrs,"
											+ "(case when lhrs<>0 then lhrs||'('||lhl||'%)' else lhrs end) lhrs,"
											+ "(case when hgrs<>0 then hgrs||'('||hgl||'%)' else hgrs end) hgrs,"
											+ "(case when bhgrs<>0 then bhgrs||'('||bhgl||'%)' else bhgrs end) bhgrs from zgkd_zhszcp_hztmp a where xydm=? and nj=? and xn=?",
									new String[] { xydm, njList[i],
											model.getXn() }, opList);// 学院素质等级比例
					tmp.add(xyrs);
					rs.add(tmp);
				}
			}
		}
		return rs;
	}
	
	/**
	 * 查询学生综合素质信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szcpInfo(String xh) throws Exception {
		return dao
				.rsToVator(
						"select xn,pdcpdf,kcxxcjdf,sxcpdf,jbszcpdf,jbszcpdj,fzszcpdf from zgkd_zhszcpb where xh=? order by xn asc",
						new String[] { xh }, new String[] { "xn", "pdcpdf",
								"kcxxcjdf", "sxcpdf", "jbszcpdf", "jbszcpdj",
								"fzszcpdf" });
	}
	
	public String getXymc(String xydm) throws Exception {
		return dao.getOneRs("select distinct bmmc from zxbz_xxbmdm where bmdm=?", new String[]{xydm}, "bmmc");
	}
	
	/**
	 * 保存综合素质测评排名方式
	 * @param pmfs
	 * @param request
	 * @return
	 */
	public boolean saveZhszcpPmfs(String pmfs, HttpServletRequest request) throws Exception {
		dao.runUpdate("delete from zhszcppmszb", new String[]{});
		return StandardOperation.insert("zhszcppmszb", new String[]{"pmfs"}, new String[]{pmfs}, request);
	}
	
	/**
	 * 查询综合素质测评排名方式
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> queryZhszcpPmfs() throws Exception {
//		String sl = queryZhszfPmfs();
//		HashMap<String, String> rs = dao.getMapNotOut(sl, new String[]{"3040411010"});
		
		return dao.getMapNotOut("select pmfs from zhszcppmszb", new String[]{});
	} 
	
	/**
	 * 查询综合素质测评排名方式返回sql
	 * 条件where xh=? 传入学号即可查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryZhszfPmfs() throws Exception {
		String pm = dao.getOneRs("select pmfs from zhszcppmszb", new String[]{}, "pmfs");
		pm = StringUtils.isNull(pm) ? "" : pm.trim();
		String sql = "";
		if ("zy".equalsIgnoreCase(pm)) {
			sql = "select xh,xm,bjmc,xn,kcxxcjdf,kpm,pdcpdf,sxcpdf,jbszcpdf,jpm,fzszcpdf,fpm,jbszcpdj from (select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a) where xh=?";
		} else if ("bj".equalsIgnoreCase(pm)) {
			sql = "select xh,xm,bjmc,xn,kcxxcjdf,kpm,pdcpdf,sxcpdf,jbszcpdf,jpm,fzszcpdf,fpm,jbszcpdj from (select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by bjdm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a) where xh=?";
		} else {
			sql = "select xh,xm,bjmc,xn,kcxxcjdf,kpm,pdcpdf,sxcpdf,jbszcpdf,jpm,fzszcpdf,fpm,jbszcpdj from (select xh||xn pk,rownum num,xh,xm,bjmc,xn,kcxxcjdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(kcxxcjdf)) desc nulls last)) kpm,pdcpdf,sxcpdf,jbszcpdf,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(jbszcpdf)) desc nulls last)) jpm,fzszcpdf,jbszcpdj,(dense_rank() over (partition by zydm,nj,xn order by to_number(trim(fzszcpdf)) desc nulls last)) fpm from view_zgkd_zhszcp a) where xh=?";
		}
		return sql;
	}
	
}
