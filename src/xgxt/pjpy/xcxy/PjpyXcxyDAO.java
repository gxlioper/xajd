package xgxt.pjpy.xcxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyXcxyDAO {

	private DAO dao = DAO.getInstance();

	private static PjpyXcxyDAO mydao = new PjpyXcxyDAO();

	public static PjpyXcxyDAO getInstance() {
		return mydao;
	}

	private ArrayList<String> values = new ArrayList<String>();

	public StringBuffer whereSql(PjpyXcxyModel model) {
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
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
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
			whereSql.append(" and xm like '%" + DealString.toGBK(model.getXm())
					+ "%'");

		}

		return whereSql;

	}

	/**
	 * 查询操行分表头
	 * 
	 * @param fs
	 * @return
	 */
	public List<HashMap<String, String>> queryCxfTitle(String fs) {
		String[] enList = new String[] { "pk", "rownum", "xn", "xq", "xh",
				"xm", "bjmc", "cxxm", "cxfs", "cxlb" };
		String[] cnList = new String[] { "pk", "行号", "学年", "学期", "学号", "姓名",
				"班级", "操行项目", "操行分数", "类别" };
		if ("3".equalsIgnoreCase(fs)) {
			enList = new String[] { "pk", "rownum", "xn", "xq", "xh", "xm",
					"bjmc", "cxzf" };
			cnList = new String[] { "pk", "行号", "学年", "学期", "学号", "姓名", "班级",
					"操行总分" };
		} else if ("4".equalsIgnoreCase(fs)) {
			enList = new String[] { "pk", "rownum", "xn", "xq", "xh", "xm",
					"bjmc", "cxjf", "cxkf", "cxzf" };
			cnList = new String[] { "pk", "行号", "学年", "学期", "学号", "姓名", "班级",
					"操行加分", "操行扣分", "操行总分" };
		}
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 查询操行分结果
	 * 
	 * @param model
	 * @param fs
	 * @return
	 */
	public List<String[]> queryCxfResult(PjpyXcxyModel model, String fs,
			HttpServletRequest request, PjpyXcxyActionForm pjpyForm) {
		StringBuffer whereSql = whereSql(model);
		String sql = "select guid pk,rownum r,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,(case when trim(cxlb)='1' then '加分' when trim(cxlb)='0' then '扣分' else cxlb end) cxlb from view_pjpy_cxfb a where 1=1"
				+ whereSql.toString();
		String[] opList = new String[] { "pk", "r", "xn", "xqmc", "xh", "xm",
				"bjmc", "cxxm", "cxfs", "cxlb" };
		if ("1".equalsIgnoreCase(fs)) {
			sql = "select guid pk,rownum r,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,'加分' cxlb from view_pjpy_cxfb a where trim(cxlb)='1'"
					+ whereSql.toString();
		} else if ("2".equalsIgnoreCase(fs)) {
			sql = "select guid pk,rownum r,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,'扣分' cxlb from view_pjpy_cxfb a where trim(cxlb)='0'"
					+ whereSql.toString();
		} else if ("3".equalsIgnoreCase(fs)) {
			sql = "select a.*,rownum r,'' pk from (select distinct xh,xm,bjmc,xn,xq,sum(to_number(cxfs)) over (partition by xh,xn,xq) cxzf "
					+ "from (select xh,xn,xq,bjmc,xm,(case when trim(cxlb)='1' then cxfs when trim(cxlb)='0'"
					+ " then '-'||cxfs else cxfs end) cxfs from view_pjpy_cxfb where 1=1 "
					+ whereSql.toString() + ")) a";
			opList = new String[] { "pk", "r", "xn", "xq", "xh", "xm", "bjmc",
					"cxzf" };
		} else if ("4".equalsIgnoreCase(fs)) {

		}
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if ("true".equalsIgnoreCase(isFdy)) {
			sql += " and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"
					+ request.getSession().getAttribute("userName").toString()
					+ "')";
		}
		return dao.rsToVator("select * from ("
				+ sql
				+ ") where r<="
				+ (pjpyForm.getPages().getStart() + pjpyForm.getPages()
						.getPageSize()) + " and r> "
				+ pjpyForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	/**
	 * 查询操行分记录数
	 * 
	 * @param model
	 * @param fs
	 * @param request
	 * @return
	 */
	public int queryCxfNum(PjpyXcxyModel model, String fs,
			HttpServletRequest request) {
		StringBuffer whereSql = whereSql(model);
		String sql = "select guid pk,rownum,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,(case when trim(cxlb)='1' then '加分' when trim(cxlb)='0' then '扣分' else cxlb end) cxlb from view_pjpy_cxfb a where 1=1"
				+ whereSql.toString();
		String[] opList = new String[] { "pk", "rownum", "xn", "xqmc", "xh",
				"xm", "bjmc", "cxxm", "cxfs", "cxlb" };
		if ("1".equalsIgnoreCase(fs)) {
			sql = "select guid pk,rownum,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,'加分' cxlb from view_pjpy_cxfb a where trim(cxlb)='1'"
					+ whereSql.toString();
		} else if ("2".equalsIgnoreCase(fs)) {
			sql = "select guid pk,rownum,xn,xqmc,xh,xm,bjmc,cxxm,cxfs,'扣分' cxlb from view_pjpy_cxfb a where trim(cxlb)='0'"
					+ whereSql.toString();
		} else if ("3".equalsIgnoreCase(fs)) {
			sql = "select a.*,rownum,'' pk from (select distinct xh,xm,bjmc,xn,xq,sum(to_number(cxfs)) over (partition by xh,xn,xq) cxzf "
					+ "from (select xh,xn,xq,bjmc,xm,(case when trim(cxlb)='1' then cxfs when trim(cxlb)='0'"
					+ " then '-'||cxfs else cxfs end) cxfs from view_pjpy_cxfb where 1=1 "
					+ whereSql.toString() + ")) a";
			opList = new String[] { "pk", "rownum", "xn", "xq", "xh", "xm",
					"bjmc", "cxzf" };
		} else if ("4".equalsIgnoreCase(fs)) {

		}
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if ("true".equalsIgnoreCase(isFdy)) {
			sql += " and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"
					+ request.getSession().getAttribute("userName").toString()
					+ "')";
		}
		return dao.rsToVator(
				sql,
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList).size();
	}

	/**
	 * 删除操行分信息
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteCxf(String[] keys) throws Exception {
		String rs = "";
		String[] sql = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			StringBuffer sb = new StringBuffer(
					"delete from pjpy_cxfb where guid='");
			sb.append(keys[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		int flag[] = dao.runBatch(sql);
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == -1) {
				rs += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}

	/**
	 * 操行分增加保存
	 * 
	 * @param param
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cxfAddSave(List<HashMap<String, String>> param, String xn,
			String xq) throws Exception {
		int length = param.size();
		String[] inserSql = new String[length];
		boolean doFlag = false;
		for (int i = 0; i < length; i++) {
			String xh = DealString.toGBK(param.get(i).get("xh"));
			String cxxm = param.get(i).get("cxxm");
			String cxfs = DealString.toGBK(param.get(i).get("cxfs"));
			String cxlb = DealString.toGBK(param.get(i).get("cxlb"));
			if (!Base.isNull(xh) && !Base.isNull(cxxm) && !Base.isNull(xn)
					&& !Base.isNull(xq) && !Base.isNull(cxfs)) {
				StringBuffer sql = new StringBuffer();
				sql
						.append("insert into pjpy_cxfb (xh,xn,xq,cxxm,cxfs,cxlb) values ('");
				sql.append(xh);
				sql.append("','");
				sql.append(xn);
				sql.append("','");
				sql.append(xq);
				sql.append("','");
				sql.append(cxxm);
				sql.append("','");
				sql.append(cxfs);
				sql.append("','");
				sql.append(cxlb);
				sql.append("')");
				inserSql[i] = sql.toString();
			}
		}
		int[] array = null;
		array = dao.runBatch(inserSql);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}

	/**
	 * 显示详细信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCxf(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,cxxm,cxfs,cxlb from view_pjpy_cxfb where guid=?",
						new String[] { pkValue });
	}

	/**
	 * 修改操行分信息
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateCxf(String pkValue, PjpyXcxyModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_cxfb", new String[] { "xn", "xq",
				"cxxm", "cxfs", "cxlb" }, new String[] { model.getXn(),
				model.getXq(), DealString.toGBK(model.getCxxm()),
				model.getCxfs(), model.getCxlb() }, "guid", pkValue, request);
	}

	/**
	 * 评奖成绩自动计算
	 * 
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean autoAccount(String xydm, String xn, String xq)
			throws Exception {
		return dao.runProcuder("{call PJPY_XCXY_PJCJAUTOACCOUNT(?, ?, ?)}",
				new String[] { xydm, xn, xq });
	}

	/**
	 * 评奖成绩查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryPjcjTitle() {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "bjmc",
				"xn", "xq", "pjcj", "cxcj", "zcj", "pm" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "班级", "学年",
				"学期", "平均成绩", "操作成绩", "总成绩", "排名" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 评奖成绩查询结果记录数(分页用)
	 * 
	 * @param model
	 * @return
	 */
	public int queryPjcjResultNum(PjpyXcxyModel model) {
		StringBuffer whereSql = whereSql(model);
		String[] enList = new String[] { "pk", "r", "xh", "xm", "bjmc", "xn",
				"xq", "pjcj", "cxcj", "zcj", "pm" };
		String sql = "select xh,xn,xq pk,rownum r,xh,xm,bjmc,xn,xq,pjcj,cxcj,zcj,(dense_rank() over (partition by xn,xq,bjdm order by to_number(zcj))) pm from view_xcxy_pjxscjb where 1=1";
		return dao.rsToVator(
				sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, enList).size();
	}

	/**
	 * 评奖成绩查询结果
	 * 
	 * @param model
	 * @param pjpyForm
	 * @return
	 */
	public List<String[]> queryPjcjResult(PjpyXcxyModel model,
			PjpyXcxyActionForm pjpyForm) {
		StringBuffer whereSql = whereSql(model);
		String[] enList = new String[] { "pk", "r", "xh", "xm", "bjmc", "xn",
				"xq", "pjcj", "cxcj", "zcj", "pm" };
		String sql = "select * from (select xh||xn||xq pk,rownum r,xh,xm,bjmc,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjcj,cxcj,zcj,pm from view_xcxy_pjxscjb a where 1=1";
		sql += whereSql.toString();
		return dao.rsToVator(sql
				+ ") where r<="
				+ (pjpyForm.getPages().getStart() + pjpyForm.getPages()
						.getPageSize()) + " and r> "
				+ pjpyForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, enList);
	}

	public HashMap<String, String> queryXyJxjshxx(String pkVal) {
		return dao
				.getMapNotOut(
						"select sqly,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,nd,xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jlje,drzw,xysh yesNo,xyshyj yj,(select b.pjcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pjcj,(select b.cxcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cxcj,(select b.zcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zcj,(select b.pm from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pm,(select b.pjcjpm from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pjcjpm from view_xsjxjb a where xn||nd||xh||jxjdm=?",
						new String[] { pkVal });
	}

	public HashMap<String, String> queryXxJxjshxx(String pkVal) {
		return dao
				.getMapNotOut(
						"select sqly,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,nd,xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jlje,drzw,xxsh yesNo,xxshyj yj,(select b.pjcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pjcj,(select b.cxcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cxcj,(select b.zcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zcj,(select b.pm from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pm,(select b.pjcjpm from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pjcjpm from view_xsjxjb a where xn||nd||xh||jxjdm=?",
						new String[] { pkVal });
	}

	public boolean updateXyjxjsh(String pkVal, PjpyXcxyModel model,
			HttpServletRequest request) throws Exception{
		return StandardOperation.update("xsjxjb", new String[] { "xysh",
				"xyshyj" }, new String[] { DealString.toGBK(model.getYesNo()),
				DealString.toGBK(model.getYj()) }, "xn||nd||xh||jxjdm", pkVal,
				request);
	}
	
	public boolean updateXxjxjsh(String pkVal, PjpyXcxyModel model,
			HttpServletRequest request) throws Exception{
		return StandardOperation.update("xsjxjb", new String[] { "xxsh",
				"xxshyj" }, new String[] { DealString.toGBK(model.getYesNo()),
				DealString.toGBK(model.getYj()) }, "xn||nd||xh||jxjdm", pkVal,
				request);
	}
	
		
	/**
	 * 奖学金发放表
	 * @param model
	 * @return
	 */
	public List<String[]> getJxjffbList(PjpyXcxyModel model) {
		List<String[]> jxjffbList = dao.rsToVator("select distinct * from (select xymc,jlje,count(xh) over " +
				"(partition by xn,nd,xydm) rs,sum(jlje) over " +
				"(partition by xn,nd,xydm) je from view_xsjxjb where " +
				"jxjdm=? and xn=? and nd=? and xysh='通过' and xxsh='通过' order by xydm)", new String[]{model.getJxjdm(), model.getXn(), model.getNd()}, new String[]{"xymc", "jlje", "rs", "je"});
		jxjffbList.addAll(dao.rsToVator("select distinct '合计' xymc,jlje,count(xh) over " +
				"(partition by xn,nd) rs,sum(jlje) over (partition by xn,nd) je from " +
				"view_xsjxjb where jxjdm=? and xn=? and nd=? and xysh='通过' and xxsh='通过'", new String[]{model.getJxjdm(), model.getXn(), model.getNd()}, new String[]{"xymc", "jlje", "rs", "je"}));
		return jxjffbList;
	}
	
	public String getJxjmc(String jxjdm) {
		return dao.getOneRs("select jxjmc from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjmc");
	}
	
	public boolean accountYxxsjxj(String xn, String xq)
	throws Exception {
			return dao.runProcuder("{call PJPY_XCXY_YXXSJXJHZ(?, ?)}",
			new String[] {xn,xq});
	}
	
	public List<String[]> getYxxsjxjList() {
		List<String[]> rs = dao
				.rsToVator(
						"select a.xydm,(select distinct b.xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,xsrs,td ,tdje,yd ,ydje,ed ,edje,sd ,sdje,jexj from xcxy_yxxsjxjffb a",
						new String[] {}, new String[] { "xymc", "xsrs", "td",
								"tdje", "yd", "ydje", "ed", "edje", "sd",
								"sdje", "jexj" });
		rs
				.addAll(dao
						.rsToVator(
								"select '合计' xymc,sum(trim(xsrs)) xsrs,sum(trim(td)) td,sum(trim(tdje)) tdje,sum(trim(yd)) yd,sum(trim(ydje)) ydje,sum(trim(ed)) ed,sum(trim(edje)) edje,sum(trim(sd)) sd,sum(trim(sdje)) sdje,sum(trim(jexj)) jexj from xcxy_yxxsjxjffb",
								new String[] {}, new String[] { "xymc", "xsrs",
										"td", "tdje", "yd", "ydje", "ed",
										"edje", "sd", "sdje", "jexj" }));
		return rs;
	}
	
	public HashMap<String, String> getXyYxjxjTjList(String xydm) {
		HashMap<String, String> rs = dao
				.getMapNotOut(
						"select a.xydm,(select distinct b.xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,xsrs,td ,tdje,yd ,ydje,ed ,edje,sd ,sdje,jexj from xcxy_yxxsjxjffb a where a.xydm = ?",
						new String[] {xydm});
		return rs;
	}
	
	public List<String[]> getJxjje() {
		return dao.rsToVator("select jlje from jxjdmb where jxjmc like '%优秀学生特等%' or jxjmc like '%优秀学生一等%' or jxjmc like '%优秀学生二等%' or jxjmc like '%优秀学生三等%' order by jlje desc ", new String[]{}, new String[]{"jlje"});
	}

	public HashMap<String, String> getTjsz(String jxjdm) {
		// 得到条件设置
		return commonQueryOne("xcxyjxjtjsz",new String []{"jxjdm","zdpjcj","zdcxcj","zdzcj"},"jxjdm",jxjdm);
	}
		
	public HashMap<String, String> commonQueryOne(String tableName,String [] colList,String pk,String pkValue) {
	    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
			DAO dao = DAO.getInstance();
			HashMap<String, String> map = new HashMap<String, String>();
			int size = colList.length-1;
			if(pkValue.equalsIgnoreCase("")){
				for(int i=0;i<size;i++){
					map.put(colList[i], "");
				}
			}else{
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<size;i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
				}
				sqlBuffer.append(colList[size]);
				sqlBuffer.append(" from ");
				sqlBuffer.append(tableName); 
				sqlBuffer.append(" where ");
				sqlBuffer.append(pk);
				sqlBuffer.append("=?");
				String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
				for(int i=0;i<=size;i++){
					map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
				}
			}
			return map;
		}

	public boolean updateTjsz(PjpyXcxyModel model, HttpServletRequest request) throws Exception {
		// 更新奖学金条件设置
		String jxjdm = DealString.toGBK(model.getJxjdm());
		String zdpjcj = DealString.toGBK(model.getZdpjcj());
		String zdcxcj = DealString.toGBK(model.getZdcxcj());
		String zdzcj = DealString.toGBK(model.getZdzcj());
		Boolean update = StandardOperation.delete("xcxyjxjtjsz","jxjdm",jxjdm,request);
		if(update){
			StandardOperation.insert("xcxyjxjtjsz", new String[]{"jxjdm","zdpjcj","zdcxcj","zdzcj"}, new String[]{jxjdm,zdpjcj,zdcxcj,zdzcj}, request);
		}
		return update;
	}

	public List<String[]> getXyYxjxjFfList(String xydm,String xn) {
		String xq   = Base.getJxjsqxq();
		String sql = "select rownum num,xm,bjmc,replace(jxjmc,'优秀学生','') jxjdj,jlje,fkh from view_xsjxjb where xydm = ? and xn = ? and xq = ? and jxjmc like '%优秀学生%' order by bjmc,jxjdm,xh";
		return dao.rsToVator(sql,new String[]{xydm,xn,xq},new String[]{"num","xm","bjmc","jxjdj","jlje","fkh"});
	}

	public String getXymcById(String xydm) {
		// TODO 自动生成方法存根
		return dao.getXymcById(xydm);
	}
	
	public boolean accountZyxsjxj(String xn, String xq)
	throws Exception {
		//计算专业奖学金汇总
			return dao.runProcuder("{call PJPY_XCXY_ZYXSJXJHZ(?, ?)}",
			new String[] {xn,xq});
	}

	public List<String[]> getZyxsjxjList() {
		// 专业奖学金发放汇总
		String sql = "select (select bmmc from zxbz_xxbmdm where a.xydm=bmdm) xymc,yd,ydje,ed,edje,sd,sdje,xsrs,jexj from xcxy_zyxsjxjffb a";
		return dao.rsToVator(sql, new String []{} , new String []{"xymc","yd","ydje","ed","edje","sd","sdje","xsrs","jexj"});
	}
	
	public List<String[]> getZyJxjje() {
		return dao.rsToVator("select jlje from jxjdmb where jxjmc like '%专业一等%' or jxjmc like '%专业二等%' or jxjmc like '%专业三等%' order by jlje desc ", new String[]{}, new String[]{"jlje"});
	}
	
	public List<String[]> getMyZyJxjje() {
		return dao.rsToVator("select jlje/5 jlje from jxjdmb where jxjmc like '%专业一等%' or jxjmc like '%专业二等%' or jxjmc like '%专业三等%' order by jlje desc ", new String[]{}, new String[]{"jlje"});
	}
	
	public List<String[]> getYxjxjffhzList() {
		List<String[]> rs = dao
				.rsToVator(
						"select a.xydm,(select distinct b.xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,td ,tdje,yd ,ydje,ed ,edje,sd ,sdje,zhjrs,jexj from xcxy_yxxsjxjffb a",
						new String[] {}, new String[] { "xymc","td",
								"tdje", "yd", "ydje", "ed", "edje", "sd",
								"sdje", "zhjrs","jexj" });
		return rs;
	}

	public List<String[]> getXyZyjxjFfList(String xydm, String xn) {
		String xq   = Base.getJxjsqxq();
		String sql = "select rownum num,xm,bjmc,replace(jxjmc,'专业','') jxjdj,jlje/5 jlje,fkh from view_xsjxjb where xydm = ? and xn = ? and xq = ? and jxjmc like '%专业%' order by bjmc,jxjdm,xh";
		return dao.rsToVator(sql,new String[]{xydm,xn,xq},new String[]{"num","xm","bjmc","jxjdj","jlje","fkh"});
	}
	
	public List<String[]> getMyzyjxjhzList(String xydm, String xn) {
	String xq   = Base.getJxjsqxq();
	String sql = "select a.bjmc,nvl(b.ydrs,'0') ydrs,nvl(b.ydje,'0') ydje,nvl(c.edrs,'0') edrs,nvl(c.edje,'0') edje,nvl(d.sdrs,'0') sdrs,nvl(d.sdje,0) sdje,(nvl(ydrs,0)+nvl(edrs,0)+nvl(sdrs,0)) zrs,(nvl(ydje,0)+nvl(edje,0)+nvl(sdje,0)) zje   from (select bjdm,bjmc from view_njxyzybj where xydm = ?) a left join "+
			"(select count(xh) ydrs,sum(jlje) ydje,bjdm from view_xsjxjb where jxjmc like '%专业一等%' and xydm = ? and xn =? and xq = ? group by bjdm) b on a.bjdm = b.bjdm left join"+ 
			"(select count(xh) edrs,sum(jlje) edje,bjdm from view_xsjxjb where jxjmc like '%专业二等%' and xydm = ? and xn =? and xq = ? group by bjdm) c on a.bjdm = c.bjdm left join"+ 
			"(select count(xh) sdrs,sum(jlje) sdje,bjdm from view_xsjxjb where jxjmc like '%专业三等%' and xydm = ? and xn =? and xq = ? group by bjdm) d on a.bjdm = d.bjdm";
		return dao.rsToVator(sql,new String[]{xydm,xydm,xn,xq,xydm,xn,xq,xydm,xn,xq},new String[]{"bjmc","ydrs","ydje","edrs","edje","sdrs","sdje","zrs","zje"});
	}
}
