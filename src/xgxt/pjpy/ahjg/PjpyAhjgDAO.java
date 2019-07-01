
package xgxt.pjpy.ahjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */ 
public class PjpyAhjgDAO{
	DAO  dao = DAO.getInstance();
	final String XJBJDM = "00001";//先进班级代码
	final String WMSSDM = "00002";//文明宿舍代码
	
	List<String> values = new ArrayList<String>();// 用于存入页面条件值

	String xxdm = dao.getXxdm();
	/**
	 * 公用方法：通过传入二个数组字段来输出页面查询TITLE
	 * 
	 * @param enCol
	 * @param cnCol
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSearchTitle(String[] enCol,
			String[] cnCol) throws Exception {
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> mapTemp = new HashMap<String, String>();
			mapTemp.put("en", enCol[i]);// 英文列名
			mapTemp.put("cn", cnCol[i]);// 中文列名
			listTopTr.add(mapTemp);
		}// end for
		return listTopTr;
	}

	/**
	 * 学生成绩查询结果
	 * 课程类型过滤 校选修课
	 * 课程名称过滤 形势与政策
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getPjpyXscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm, String userType, String userName) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "";
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,xq,cj,xh,xm,xymc,kcxz,bjmc,kcmc," +
					"(select round(avg(b.cj)) from view_cjb b where kcxz <>'校选修课' and " +
					"kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		} else if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)||Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,kcxz,zymc,bjmc,kcmc,khfs,xf," +
			"(select round(avg(b.cj)) from view_cjb b where a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,rownum r from (select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,(select round(avg(cj),2) from view_zhhcjb b where khfs like '考试%' and kcmc not like '%体育%' and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) from cjb b where khfs like '考查%' and kcmc not like '%体育%' and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kccj from cjb a,view_xsjbxx b where a.xh=b.xh) a";
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			// ----------2010/5/25 edit by luojw -------------
//			sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,"
//					+ "rownum r from (select a.*,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,"
//					+ "b.zymc,b.bjdm,b.bjmc,(select round(avg(cj),2) from cjb "
//					+ "b where khfs like '考试%' and kcmc not like '%体育%' and "
//					+ "a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) "
//					+ "from cjb b where khfs like '考查%' and kcmc not like '%体育%' and a.xh=b.xh "
//					+ "and a.xn=b.xn and a.xq=b.xq) kccj, "
//					+ "case when a.bkcj is not null and a.cxcj is not null then '不可能'"
//					+ "when bkcj is not null then a.bkcj || '(补考成绩)'"
//					+ "when cxcj is not null then a.cxcj || '(重修成绩)'"
//					+ "else '无重修或补考' end bkcx "
//					+ "from cjb a,view_xsjbxx b where a.xh=b.xh) a";
			
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,"
					+ "cj,xh,xm,xymc,kcxz,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz like "
					+ "'%必修%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf,"
					+ "case when a.bkcj is not null and a.cxcj is not null then '不可能'"
					+ "when bkcj is not null then a.bkcj || '(补考成绩)'"
					+ "when cxcj is not null then a.cxcj || '(重修成绩)'"
					+ "else '无重修或补考' end bkcx,xf from view_cjb a";
			// ----------end -------------
		} else {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,kcxz,bjmc,kcmc,"
					+ "'' pjf from view_cjb a";
		}
		String bzrSql = "";
		//辅导员用户只查询班级数据
		if ("bzr".equalsIgnoreCase(userType)) {
			bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE条件语句
		String[] opCol = null;
		if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc",
					"bjmc", "kcmc", "kcxz","khfs", "cj","xf", "pjf" };
		}else if(Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)){
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc",
					"zymc","bjmc", "kcmc","khfs", "cj", "pjf" };
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			opCol = new String[] { "xn", "xqmc", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "cj", "kscj", "kccj" };
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "bjmc",
					"kcmc", "kcxz", "xf", "cj","bkcx" };
		} else {
			opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc", "bjmc",
					"kcmc", "kcxz", "cj" };
		}
		
		sql = "select * from ("
			+ sql
			+ whereSql.toString()
			+ bzrSql
			+ " order by xh) where r<="
			+ (dataSearchForm.getPages().getStart() + dataSearchForm
					.getPages().getPageSize()) + " and r> "
			+ dataSearchForm.getPages().getStart();
		listRs = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs;
	}
	
	/**
	 * 学生成绩查询结果数
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @param inCol
	 * @param opCol
	 * @return
	 * @throws Exception
	 */
	public int getPjpyXscjResultNum(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel, String userType, String userName) throws Exception {
		//String sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz <>'校选修课' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		String sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,'' pjf from view_cjb a";
		String xxdm = Base.xxdm;
		if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)||Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,rownum r,bjdm,xn,(select xqmc from xqdzb where xq=xqdm) xq,cj,xh,xm,xymc,zymc,kcxz,bjmc,kcmc," +
			"'' pjf from view_cjb a";
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.* from (select a.*,rownum r,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,'' pjf from cjb a,view_xsjbxx b where a.xh=b.xh) a";
		} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select nj,xydm,zydm,bjdm,xn,xq,cj,xh,xm,xymc,zymc,bjmc,kcmc,(select round(avg(b.cj)) from view_cjb b where kcxz <>'校选修课' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh group by b.xh) pjf from view_cjb a";
		}
		String bzrSql = "";
		//辅导员用户只查询班级数据
		if ("bzr".equalsIgnoreCase(userType)) {
			bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE条件语句
//		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "zymc",
//				"bjmc", "kcmc", "cj", "pjf" };
		
		
		// 防止内存溢出  sjf
		StringBuilder builder = new StringBuilder();
		builder.append("select count(1) count from (")
			   .append(sql)
			   .append(whereSql)
			   .append(bzrSql)
			   .append(")");
		
		
		Map<String, String> map = dao.getMap(builder.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, new String[]{"count"});
		return Integer.parseInt(map.get("count"));
	}

	public List<String[]> getPjpyDjkscjResult(
			PjpyAhjgXscjQryModel pjpyahjgxscjqryModel,
			PjpyAhjgActionForm dataSearchForm) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select rownum r,xn,(select xqmc from xqdzb where xq=xqdm) xq,xh,xm,nj,xymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE条件语句
		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "nj", "xymc",
				"bjmc", "djksmc", "ksrq", "cj" };
		listRs = dao.rsToVator("select * from ("
				+ sql
				+ whereSql
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs;
	}
	
	public int getPjpyDjkscjResultNum(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		List<String[]> listRs = new ArrayList<String[]>();
		String sql = "select xn,xq,xh,xm,xymc,zymc,bjmc,djksmc,ksrq,cj from view_xsdjksb";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);// WHERE条件语句
		String[] opCol = new String[] { "xn", "xq", "xh", "xm", "xymc", "zymc",
				"bjmc", "djksmc", "ksrq", "cj" };
		listRs = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opCol);
		return listRs.size();
	}
	
	/**
	 * 加载WHERE条件查询语句
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = pjpyahjgxscjqryModel.getXydm();
		String zydm = pjpyahjgxscjqryModel.getZydm();
		String bjdm = pjpyahjgxscjqryModel.getBjdm();
		String xn = pjpyahjgxscjqryModel.getXn();
		String nj = pjpyahjgxscjqryModel.getNj();
		String xh = DealString.toGBK(pjpyahjgxscjqryModel.getXh());
		String xm = DealString.toGBK(pjpyahjgxscjqryModel.getXm());
		String xq = pjpyahjgxscjqryModel.getXq();
		String djksmc = pjpyahjgxscjqryModel.getDjksmc();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh like ?");
			values.add("%" + xh + "%");
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like ?");
			values.add("%" + xm + "%");
		}// end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		if (!StringUtils.isNull(djksmc)) {
			whereSql.append(" and djksmc = ?");
			values.add(djksmc);
		}
		// ---------2010/5/24 edit by luojw ------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {// 广州大学
			// TODO
			String[] kcxz = pjpyahjgxscjqryModel.getKcxz();
			if (kcxz != null && kcxz.length > 0) {
				whereSql.append(" and (");
				for (int i = 0; i < kcxz.length; i++) {
					if(i == 0){
						whereSql.append(" kcxz = '" + kcxz[i] + "'");
					}else{
						whereSql.append(" or kcxz = '" + kcxz[i] + "'");
					}
				}
				whereSql.append(" )");
			}
		}
		// --------- end -------------
		return whereSql;
	}

	/**
	 * 班级补考率查询结果
	 * bjbklqryresult ---- 班级补考率查询结果
	 * @param pjpyahjgxscjqryModel(公用) 班级补考率查询MODEL
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjbklQryResult(PjpyAhjgXscjQryModel pjpyahjgxscjqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||bjdm,(case when (to_number(substr(bjbkl,0,length(bjbkl)-1))>=15) then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,bzxm,xsrs,bzr,bjbkl from view_pjpy_bjbkl";
		StringBuffer whereSql = getWhereSql(pjpyahjgxscjqryModel);
		String[] opList = new String[]{"xn||bjdm","bgcolor","rownum", "xn", "xymc", "zymc", "bjmc", "bzxm" , "xsrs", "bzr", "bjbkl"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}

	/**
	 * 保存班级补考率信息，成功返回TRUE，反之返回FALSE
	 * saveBjbkl ----  保存班级补考率
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveBjbkl(BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String bjdm = bjbklSaveModel.getBjdm();
		String xn = bjbklSaveModel.getXn();
		String bzxm = DealString.toGBK(bjbklSaveModel.getBzxm());
		String bzr = DealString.toGBK(bjbklSaveModel.getBzr());
		String xsrs = bjbklSaveModel.getXsrs();
		String bjbkl = bjbklSaveModel.getBjbkl() + "%";
		String[] inList = new String[] { "xn", "bjdm", "bzxm", "bzr", "xsrs",
				"bjbkl" };
		bFlag = StandardOperation.insert("pjpy_bjbklb", inList, new String[] {
				xn, bjdm, bzxm, bzr, xsrs, bjbkl }, request);
		return bFlag;
	}
	
	/**
	 * 保存前检查数据是否重复,存在返回TRUE，反之返回FALSE
	 * chkbjbkl ---- 检查班级补考率
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkBjbkl(BjbklSaveModel bjbklSaveModel) throws Exception {
		boolean bFlag = false;
		String xn = bjbklSaveModel.getXn();
		String bjdm = bjbklSaveModel.getBjdm();
		String sql = "select xn,bjdm,bjbkl from view_pjpy_bjbkl where xn = ? and bjdm = ?";
		String[] tmpList = dao.getRs(sql, new String[]{xn, bjdm}, "bjbkl");
		if (tmpList != null && tmpList.length > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}

	/**
	 * 通过主键获取班级补考率信息
	 * getbjbklbypk ---- 通过主键获取班级补考率信息 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBjbklByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,bjdm,bzxm,xsrs,bzr,bjbkl from view_pjpy_bjbkl where xn||bjdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{!StringUtils.isNull(sPk) ? sPk.trim() : ""});
		return resMap;
	}
	
	/**
	 * 修改班级补考信息
	 * updatebjbkl ---- 修改班级补考率 
	 * @param sPk
	 * @param bjbklSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjbkl(String sPk, BjbklSaveModel bjbklSaveModel, HttpServletRequest request) throws Exception{
		boolean bFlag = false;
		String bzxm = bjbklSaveModel.getBzxm();
		String bzr = bjbklSaveModel.getBzr();
		String xsrs = bjbklSaveModel.getXsrs();
		String bjbkl = bjbklSaveModel.getBjbkl();
		bFlag = StandardOperation.update("pjpy_bjbklb", new String[] { "bzxm",
				"bzr", "xsrs", "bjbkl" },
				new String[] { bzxm, bzr, xsrs, bjbkl + "%" }, "xn||bjdm", sPk,
				request);
		return bFlag;
	}
	
	/**
	 * 班级补考率批量删除
	 * delbjbkl ---- 删除班级补考率
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBjbkl(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from pjpy_bjbklb where xn||bjdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 早操出勤率查询结果
	 * getzccqlresult ---- 获取早操出勤率查询结果 
	 * @param zccqlQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZccqlResult(ZccqlQueryModel zccqlQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||bjdm,(case when (to_number(substr(zccql,0,length(zccql)-1))<90)" +
				" then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xq,xymc,zymc,bjmc,bzxm," +
				"xsrs,bzr,zccql from view_pjpy_bjzccql ";
		StringBuffer whereSql = getWhereSql1(zccqlQryModel);
		String[] opList = new String[] { "xn||bjdm", "bgcolor", "rownum",
				"xn", "xq", "xymc", "zymc", "bjmc", "bzxm", "xsrs", "bzr",
				"zccql" };
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 加载WHERE条件查询语句
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(ZccqlQueryModel zccqlQryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = zccqlQryModel.getXydm();
		String zydm = zccqlQryModel.getZydm();
		String bjdm = zccqlQryModel.getBjdm();
		String xn = zccqlQryModel.getXn();
		String xq = zccqlQryModel.getXq();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		return whereSql;
	}

	/**
	 * 检查早操出勤是否重复，重复返回TURE，反之返回FALSE
	 * chkzccql ---- 检查早操出勤 
	 * @param zccqlSaveModel 早操出勤保存MODEL
	 * @return
	 * @throws Exception
	 */
	public boolean chkZccql(ZccqlSaveModel zccqlSaveModel) throws Exception {
		boolean bFlag = false;
		String xn = zccqlSaveModel.getXn();
		String bjdm = zccqlSaveModel.getBjdm();
		String sql = "select xn,bjdm,zccql from view_pjpy_bjzccql where xn = ? and bjdm = ?";
		String[] tmpList = dao.getRs(sql, new String[]{xn, bjdm}, "zccql");
		if (tmpList != null && tmpList.length > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}

	/**
	 * 保存早操出勤信息,成功返回TRUE，反之返回FALSE
	 * saveZccql ---- 保存早操出勤 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveZccql(ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = zccqlSaveModel.getXn();
		String bzxm = DealString.toGBK(zccqlSaveModel.getBzxm());
		String bzr = DealString.toGBK(zccqlSaveModel.getBzr());
		String xsrs = zccqlSaveModel.getXsrs();
		String bjdm = zccqlSaveModel.getBjdm();
		String zccql = zccqlSaveModel.getZccql() + "%";
		String[] inList = new String[] { "xn", "bjdm", "bzxm", "bzr", "xsrs",
		"zccql" };
		bFlag = StandardOperation.insert("pjpy_bjzccqlb", inList, new String[] {
				xn, bjdm, bzxm, bzr, xsrs, zccql }, request);//保存早操出勤信息
		return bFlag;
	}

	/**
	 * 通过主键获取早操出勤信息
	 * getzccqlbypk ---- 通过主键获取早操出勤 
	 * @param zccqlSaveModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZccqlByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,xsrs,bzr,bzxm,zccql from view_pjpy_bjzccql where xn||bjdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{!StringUtils.isNull(sPk) ? sPk.trim() : ""});
		return resMap;
	} 

	/**
	 * 修改早操出勤信息,成功返回TRUE，反之返回FALSE
	 * updatezccql ---- 修改早操出勤 
	 * @param sPk
	 * @param zccqlSaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZccql(String sPk, ZccqlSaveModel zccqlSaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String bzxm = zccqlSaveModel.getBzxm();
		String bzr = zccqlSaveModel.getBzr();
		String xsrs = zccqlSaveModel.getXsrs();
		String zccql = zccqlSaveModel.getZccql();
		bFlag = StandardOperation.update("pjpy_bjzccqlb", new String[] {
				"bzxm", "bzr", "xsrs", "zccql" }, new String[] { bzxm, bzr,
				xsrs, zccql + "%" }, "xn||bjdm", sPk, request);
		return bFlag;
	}
	
	/**
	 * 早操出勤率批量删除
	 * delbjbkl ---- 早操出勤率
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delZccql(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from pjpy_bjzccqlb where xn||bjdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 保存先进班级信息
	 * savexjbjinfo ---- 保存先进班级信息 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjBjInfo(XjBjSqModel xjbjSqModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String bzxm = DealString.toGBK(xjbjSqModel.getBzxm());
		String bzr = DealString.toGBK(xjbjSqModel.getBzr());
		String xsrs = xjbjSqModel.getXsrs();
		String zysj = DealString.toGBK(xjbjSqModel.getZysj());
		String[] inList = new String[] { "xn", "xq", "rychdm", "bjdm", "bzxm",
				"bzr", "xsrs", "zysj" };
		String rychdm = getRychdm("先进班级");// 获取荣誉称号代码
		bFlag = StandardOperation.insert("pjpy_xjbjandwmsqb", inList,
				new String[] { xn, xq, rychdm, bjdm, bzxm, bzr, xsrs, zysj },
				request);

		return bFlag;
	}
	
	/**
	 * 验证数据是否重复
	 * chkDataByXjbj ---- 验证先进班级数据是否重复 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByXjbj(XjBjSqModel xjbjSqModel) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String rychdm = getRychdm("先进班级");//获取荣誉称号代码;
		String sql = "select xn,xq,bjdm,rychdm from pjpy_xjbjandwmsqb where xn=? and xq=? and bjdm=? and rychdm=?";
		String[] temList = dao.getOneRs(sql, new String[]{xn, xq, bjdm, rychdm}, new String[]{"xn", "xq", "bjdm", "rychdm"});
		if (temList != null && temList.length > 0) {//存在
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * 先进班级查询表头，辅导员
	 * getxjbjtitlebyfdy ---- (辅导员) 先进班级查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByFdy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "xn||xq||bjdm||rychdm", "bgcolor",
				"rownum", "xn", "xymc", "zymc", "rychmc", "bzxm", "xsrs",
				"bzr", "fdysh" };
		String[] cnList = new String[] { "主键", "bgcolor", "行号", "学年", "学院名称",
				"专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员", "辅导员审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 先进班级查询表头，学院
	 * getxjbjtitlebyxy ---- (学院) 先进班级查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByXy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员",  Base.YXPZXY_KEY+"审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 先进班级查询表头，学校
	 * getxjbjtitlebyxx ---- (学校) 先进班级查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitleByXx() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "荣誉称号名称", "学生人数", "辅导员", "学校审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 获取先进班级查询结果，辅导员
	 * getxjbjresultbyfdy ---- 获取先进班级查询结果，辅导员 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByFdy(XjBjQryModel xjbjQryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(fdysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,fdysh from view_pjpy_xjbjandwmsq a where 1=1";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		if ("true".equalsIgnoreCase(isFdy)) {
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取先进班级查询结果，学院
	 * getxjbjresultbyxy ---- 获取先进班级查询结果，学院 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByXy(XjBjQryModel xjbjQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xysh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取先进班级查询结果，学校
	 * getxjbjresultbyxx ---- 获取先进班级查询结果，学校 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResultByXx(XjBjQryModel xjbjQryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xxsh from view_pjpy_xjbjandwmsq where xysh='通过'";
		StringBuffer whereSql = getWhereSql2(xjbjQryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取查询条件及值
	 * getwheresql ---- 获取查询条件 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(XjBjQryModel xjbjQryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjbjQryModel.getXn();
		String shxm = xjbjQryModel.getShxm();
		String xydm = xjbjQryModel.getXydm();
		String zydm = xjbjQryModel.getZydm();
		String bjdm = xjbjQryModel.getBjdm();
		String rychdm = getRychdm("先进班级");//获取荣誉称号代码
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(shxm)) {
			whereSql.append(" and rychdm = ?");
			values.add(shxm);
		}//end if
		
		return whereSql;
	}

	/**
	 * 辅导员审核先进集体
	 * fdyshresult ---- 辅导员审核结果
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//主键
		Arrays2.trim(cbv);	
		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "通过" : ("btg".equalsIgnoreCase(sRes) ? "不通过" : "未审核");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("先进班级");//获取荣誉称号代码
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		for (int i = 0; i < cbv.length; i++) {
			StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
		}//end for
		return bFlag;
	}
	
	/**
	 * 学院审核先进集体
	 * xyshresult ---- 学院审核结果
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public String xyshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//主键
		Arrays2.trim(cbv);	
//		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "通过" : ("btg".equalsIgnoreCase(sRes) ? "不通过" : "未审核");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("先进班级");//获取荣誉称号代码
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		String sBjs = getBjzs();//班级评比比例
		String res = "";
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		if (sJg.equalsIgnoreCase("通过")) {
			for (int i = 0; i < cbv.length; i++) {
				int tg = getXjbjShjg(request);//已通过班级数
				if ((xzs != 0) && (tg >= xzs)) {
					res += (i+1);
					res += ",";
					continue;
				}
				StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		} else {
			for (int i = 0; i < cbv.length; i++) {
				
				StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		}
		return res;
	}
	
	/**
	 * 学校审核先进集体
	 * xxhresult ---- 学校审核结果
	 * @param xjjtShModel
	 * @return
	 * @throws Exception
	 */
	public String xxshResult(XjjtShModel xjjtShModel, String sRes, HttpServletRequest request) throws Exception {
		String[] cbv = xjjtShModel.getCbv();//主键
		Arrays2.trim(cbv);	
		boolean[] bFlag = new boolean[cbv.length]; 
		String sPk = "xn||xq||bjdm||rychdm";
		String sJg = "tg".equalsIgnoreCase(sRes) ? "通过" : ("btg".equalsIgnoreCase(sRes) ? "不通过" : "未审核");
		String shxm = xjjtShModel.getShxm();
		String rychdm = getRychdm("先进班级");//获取荣誉称号代码
		shxm = StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		String sBjs = getBjzs();//班级评比比例
		String res = "";
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		if (sJg.equalsIgnoreCase("通过")) {
			for (int i = 0; i < cbv.length; i++) {
				int tg = getXjbjShjg(request);//已通过班级数
				if ((xzs != 0) && (tg >= xzs)) {
					res += (i+1);
					res += ",";
					continue;
				}
				bFlag[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		} else {
			for (int i = 0; i < cbv.length; i++) {
				
				bFlag[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{sJg}, sPk, DealString.toGBK(cbv[i]), request);
			}//end for
		}
		return res;
	}

	/**
	 * 通过主键获取先进集体信息（辅导员）
	 * getxsjtjg ---- 获取先进集体结果
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByFdy(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,bjbkl,zccql,zysj,xsrs,bzxm,bzr,rychmc,fdysh sh,fdyyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * 通过主键获取先进集体信息（学院）
	 * getxsjtjg ---- 获取先进集体结果
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByXy(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,xsrs,zccql,bjbkl,zysj,bzxm,bzr,rychmc,xysh sh,xyyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * 通过主键获取先进集体信息（学校）
	 * getxsjtjg ---- 获取先进集体结果
	 * @param sPkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJtJgByXx(String sPkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xymc,zymc,bjmc,xsrs,bzxm,bzr,zccql,bjbkl,zysj,rychmc,xxsh sh,xxyj yj from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resList = dao.getMapNotOut(sql, new String[]{sPkValue});
		return resList;
	}
	
	/**
	 * 获取列表值
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		return dao.getChkList(iType);
	}
	
	/**
	 * 辅导员单个审核先进集体
	 * fdysavexjjtone ---- 辅导员单个审核先进集体 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean fdySaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"fdysh", "fdyyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学院单个审核先进集体
	 * fdysavexjjtone ---- 学院单个审核先进集体 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xySaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xysh", "xyyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学校单个审核先进集体
	 * fdysavexjjtone ---- 学校单个审核先进集体 
	 * @param xjjtshModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xxSaveXjjtOne(XjjtShModel xjjtshModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = xjjtshModel.getPkValue();
		String sh = DealString.toGBK(xjjtshModel.getShxm());
		String shyj = DealString.toGBK(xjjtshModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xxsh", "xxyj" }, new String[] { sh, shyj },
				"xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 得到全校班级总数的
	 * getbjzs ---- 得到班级总数 
	 * @return
	 * @throws Exception
	 */
	public String getBjzs() throws Exception {
		String sql = "select round((select count(bjmc) from view_njxyzybj)*pxbl/100) bjzs from pjpy_jtrydmb where rychmc='先进班级'";
		String[] bjList = dao.getRs(sql, new String[]{}, "bjzs");
		String tem = "";
		if (bjList != null && bjList.length >0) {
			tem = bjList[0];
		}
		return tem;
	}

	/**
	 * 获取班级学生违纪总数
	 * getbjcfrs ---- 获取班级学生违纪人数 
	 * @param bjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String getBjcfRs(String bjdm, String xn) throws Exception {
		String sBjcfRs = "";
		String sql = "select xh from view_xsjbxx where bjdm = ?";
		String[] bjList = dao.getRs(sql, new String[]{bjdm}, "xh");//班级所有学生学号
		int j = 0;//计数器
		if (bjList != null && bjList.length > 0){
			for (int i = 0; i < bjList.length; i++) {
				sql = "select xh from view_wjcf where (ssjg='更改处分' or ssjg='维持原处分' or ssjg is null) and xn=? and xh=?";
				String[] tempList = dao.getOneRs(sql, new String[]{xn, bjList[i]}, new String[]{"xh"});
				if (tempList != null && tempList.length > 0){//受处分学生学号
					j++;
				}//end if
			}//end for
			sBjcfRs = j + "";//班级学生违纪总数
		}//end if
		return sBjcfRs;
	}
	
	/**
	 * 先进班级查询结果表头
	 * xjbjjgcxbt ---- 先进班级查询结果表头 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xjbjJgCxBt() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","xysh","xxsh"};
		String[] cnList = new String[]{"主键", "bgcolor", "行号", "学年", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "荣誉称号名称","学生人数", "辅导员", Base.YXPZXY_KEY+"审核", "学校审核"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * 先进班级查询结果
	 * xjbjcxjg ---- 先进班级查询结果 
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xjbjCxJg(XjBjQryModel xjbjqryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='未审核' and xysh='未审核') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,replace(xysh,' ','') xysh,replace(xxsh,' ','') xxsh from view_pjpy_xjbjandwmsq a where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjqryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","xysh", "xxsh"};
		if ("true".equalsIgnoreCase(isFdy)) {//辅导员只负责自己所带班级
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 通过主键获取先进班级信息
	 * xjbjxxbypk ---- 通过主键获取先进班级信息 
	 * @param sPk
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xjbjXxByPk(String sPk) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xn,xq,bjdm,rychdm,xsrs,bzr,bzxm,bjmc,zysj,rychmc from view_pjpy_xjbjandwmsq where xn||xq||bjdm||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{sPk});
		return resMap;
	}
	
	/**
	 * 保存先进班级结果
	 * bcxjbjjg ---- 保存先进班级结果 
	 * @param xjbjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bcxjbjJg (XjBjSqModel xjbjsqModel, String sPk, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xsrs = xjbjsqModel.getXsrs();
		String bzr = xjbjsqModel.getBzr();
		String bzxm = xjbjsqModel.getBzxm();
		String zysj = xjbjsqModel.getZysj();
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
				"xsrs", "bzr", "bzxm", "zysj" }, new String[] { xsrs, bzr,
				bzxm, zysj }, "xn||xq||bjdm||rychdm", sPk, request);//修改
		return bFlag;
	}
	
	/**
	 * 先进班级批量删除
	 * delxjbjxx ---- 先进班级信息批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delXjbjXx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from pjpy_xjbjandwmsqb where xn||xq||bjdm||rychdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 先进个人评比总数
	 * getbjzsbygr ---- 先进个人评比总数 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBjzsByGr() throws Exception {
		List<String[]> bjList = new ArrayList<String[]>();
		String[] jxjSqxnnd = getJxjsqxn();
		String xn = "";
		String nd = "";
		if (jxjSqxnnd != null && jxjSqxnnd.length > 0) {
			xn = jxjSqxnnd[0];
			nd = jxjSqxnnd[1];
		}
		String sql = "select rownum,rychdm,rychmc,pxbl,(case when (rychmc = '\"十佳\"大学生') then '10' when (rychmc = '三好学生') then '占班级人数' || pxbl when (rychmc = '优秀学生干部') then '占班级干部' || pxbl when (rychmc = '校级品学兼优毕业生') then (select sum(bysrs)*substr(pxbl,0,length(pxbl)-1) from xyjxjrs where xn='"+xn+"' and nd= '"+nd+"')||'' when (rychmc = '省级品学兼优毕业生') then (select sum(bysrs)*substr(pxbl,0,length(pxbl)-1) from xyjxjrs where xn='"+xn+"' and nd= '"+nd+"')||'' when (rychmc = '社会工作(实践)积极分子' or rychmc = '文体活动积极分子' or rychmc = '学习(创新)积极分子' or rychmc = '校园文明创建积极分子') then (select round(count(*) * substr(pxbl, 0, length(pxbl) - 1) / 100) from view_xsjbxx) || '' end) bjzs from rychdmb";
		bjList = dao.rsToVator(sql, new String[]{}, new String[]{"rownum", "rychdm", "rychmc", "pxbl", "bjzs"});
		return bjList;
	}
	
	/**
	 * 获取荣誉称号列表
	 * getrychlist ---- 获取荣誉称号列表 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		String sql = "select rychdm,rychmc from rychdmb";
		List<HashMap<String, String>> rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		return rychList;
	}
	
	/**
	 * 辅导员查询先进个人结果
	 * fdyqryxjgrresult ---- 辅导员查询先进个人结果 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyqryXjgrResult(XjgrQryModel xjgrqryModel, String isFdy, String userName) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(fdysh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,fdysh from view_xsrychb a where 1=1 ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "fdysh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		if ("true".equalsIgnoreCase(isFdy)) {
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 *获取查询条件及值 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql3(XjgrQryModel xjgrqryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjgrqryModel.getXn();
		String nd = xjgrqryModel.getNd();
		String nj = xjgrqryModel.getNj();
		String xydm = xjgrqryModel.getXydm();
		String xmdm = xjgrqryModel.getXmdm();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xmdm)) {
			whereSql.append(" and rychdm = ?");
			values.add(xmdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		return whereSql;
	}

	/**
	 * 学院查询先进个人结果
	 * Xyqryxjgrresult ---- 学院查询先进个人结果 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyqryXjgrResult(XjgrQryModel xjgrqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(xysh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "xysh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 学校查询先进个人结果
	 * Xxqryxjgrresult ---- 学校查询先进个人结果 
	 * @param xjgrqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxqryXjgrResult(XjgrQryModel xjgrqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select (case nvl(xxsh,'未审核') when '通过' then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor, xn||nd||xh||rychdm,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and xysh='通过' ";
		String[] opList = new String[] { "bgcolor", "xn||nd||xh||rychdm", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "rychmc", "xxsh" };
		StringBuffer whereSql = getWhereSql3(xjgrqryModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取奖学金申请学年，年度
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqxn() throws Exception {
		String[] colList = null;
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] {"jxjsqxn", "jxjsqnd" });
		return colList;
	}
	
	/**
	 * 通过主键获取先进个人信息(辅导员)
	 * fdyqryXjgrByPk ---- 通过主键获取先进个人信息(辅导员) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> fdyqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,fdysh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 通过主键获取先进个人信息(学院)
	 * XyqryXjgrByPk ---- 通过主键获取先进个人信息(学院) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xyqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,xysh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 通过主键获取先进个人信息(学校)
	 * xxqryXjgrByPk ---- 通过主键获取先进个人信息(学校) 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> xxqryXjgrByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select "
			+ "xn||nd||xh||rychdm"
			+ ",nd,xn,xh,xm,nj,xymc,zymc,bjmc,xb,rychmc,rychdm,xxsh yesNo,dcj,zcj,tcj from view_xsrychb where "
			+ "xn||nd||xh||rychdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 辅导员审核先进个人
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public boolean fdyshXjgr(String yesNo, HttpServletRequest request, String pkValue) throws Exception {
		boolean bFlag = false;
		StandardOperation.update("xsrychb", new String[]{"fdysh"}, new String[]{DealString.toGBK(yesNo)}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * 学院审核先进个人
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public String xyshXjgr(String yesNo, HttpServletRequest request, String pkValue, String rychdm, String oldxh) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqnd"});
		String sql = "select tjzdm,zdbj,tj from jxjhdtj where zdlx='rych' and jxjdm=?";
		List<String[]> tjList = dao.rsToVator(sql, new String[]{rychdm}, new String[]{"tjzdm", "zdbj", "tj"});
		String bjrs = dao
				.getOneRs(
						"select count(*) num from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh=?)",
						new String[] { oldxh }, "num");//班级人数
		bjrs = StringUtils.isNull(bjrs) ? "0" : bjrs;
		if (StringUtils.isEqual(DealString.toGBK(yesNo), "通过")) {//审核通过时验证
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if (tmpList != null && tmpList.length == 3) {
					if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "bl")) {
						float iRs = Float.parseFloat(bjrs)
								* (StringUtils.isNull(tmpList[2]) ? 0 : Float
										.parseFloat(tmpList[2])) / 100;//限制人数
						String sTgrs = dao.getOneRs("select count(*) num from view_xsrychb where xysh='通过' and rychdm=? and xn=? and nd=?  and bjdm=(select bjdm from view_xsjbxx where xh=?)", new String[]{rychdm,jxjsq != null ? jxjsq[0] : "", jxjsq != null ? jxjsq[1] : "",oldxh}, "num");
						int iTgrs = StringUtils.isNull(sTgrs) ? 0 : Integer.parseInt(sTgrs);//班级已通过人数
						if ((iTgrs != 0) && (iRs != 0) && (iTgrs >= Math.round(iRs))) {
							return String
									.format(
											"提示：班级通过人数已超过条件设置限制人数，\n已通过 %1$d 人，条件设置限制 %2$d 人",
											iTgrs, Math.round(iRs));
						}
					} else if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "sjdxspfmc")) {
						String mc = dao.getOneRs("select count(*) num from (select (dense_rank() over (partition by xh order by zf desc nulls last)) mc from sjdxspfxzb where xh=?) where to_number(mc)<='"+tmpList[3]+"'", new String[]{oldxh}, "num");
						if (!StringUtils.isNull(mc) && StringUtils.isEqual(mc, "0")) {
							return "提示：该生十佳大学生测评总分未符合条件！";
						}
					}
				}
			}
		}
		boolean bFlag = StandardOperation.update("xsrychb", new String[] { "xysh" },
				new String[] { DealString.toGBK(yesNo) }, "xn||nd||xh||rychdm",
				pkValue, request);
		if (bFlag) {
			return "";
		} else {
			return "no";
		}
	}
	
	/**
	 * 学校审核先进个人
	 * @param xjgrqryMode
	 * @return
	 * @throws Exception
	 */
	public String xxshXjgr(String yesNo, HttpServletRequest request, String pkValue, String rychdm, String oldxh) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqnd"});
		String sql = "select tjzdm,zdbj,tj from jxjhdtj where zdlx='rych' and jxjdm=?";
		List<String[]> tjList = dao.rsToVator(sql, new String[]{rychdm}, new String[]{"tjzdm", "zdbj", "tj"});
		String bjrs = dao
				.getOneRs(
						"select count(*) num from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh=?)",
						new String[] { oldxh }, "num");//班级人数
		bjrs = StringUtils.isNull(bjrs) ? "0" : bjrs;
		if (StringUtils.isEqual(DealString.toGBK(yesNo), "通过")) {//审核通过时验证
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);
				if (tmpList != null && tmpList.length == 3) {
					if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "bl")) {
						float iRs = Float.parseFloat(bjrs)
								* (StringUtils.isNull(tmpList[2]) ? 0 : Float
										.parseFloat(tmpList[2])) / 100;//限制人数
						String sTgrs = dao.getOneRs("select count(*) num from view_xsrychb where xysh='通过' and xxsh='通过' and rychdm=? and xn=? and nd=?  and bjdm=(select bjdm from view_xsjbxx where xh=?)", new String[]{rychdm,jxjsq != null ? jxjsq[0] : "", jxjsq != null ? jxjsq[1] : "",oldxh}, "num");
						int iTgrs = StringUtils.isNull(sTgrs) ? 0 : Integer.parseInt(sTgrs);//班级已通过人数
						if ((iTgrs != 0) && (iRs != 0) && (iTgrs >= Math.round(iRs))) {
							return String
									.format(
											"提示：班级通过人数已超过条件设置限制人数，\n已通过 %1$d 人，条件设置限制 %2$d 人",
											iTgrs, Math.round(iRs));
						}
					} else if (!StringUtils.isNull(tmpList[0]) && StringUtils.isEqual(tmpList[0], "sjdxspfmc")) {
						String mc = dao.getOneRs("select count(*) num from (select (dense_rank() over (partition by xh order by zf desc nulls last)) mc from sjdxspfxzb where xh=?) where to_number(mc)<='"+tmpList[3]+"'", new String[]{oldxh}, "num");
						if (!StringUtils.isNull(mc) && StringUtils.isEqual(mc, "0")) {
							return "提示：该生十佳大学生测评总分未符合条件！";
						}
					}
				}
			}
		}
		boolean bFlag = false;
		bFlag = StandardOperation.update("xsrychb", new String[] { "xxsh" },
				new String[] { DealString.toGBK(yesNo) }, "xn||nd||xh||rychdm",
				pkValue, request);
		if (bFlag) {
			return "";
		} else {
			return "no";
		}
	}
	
	/**
	 * 通过荣誉称号名称获取荣誉称号代码
	 * getrychdm ---- 获取荣誉称号代码 
	 * @param rychmc
	 * @return
	 * @throws Exception
	 */
	public String getRychdm (String rychmc) throws Exception {
		String rychdm ="";
		String sql = "select rychdm from pjpy_jtrydmb where rychmc=? and rownum=1";
		String[] rychList = dao.getOneRs(sql, new String[]{rychmc}, new String[]{"rychdm"});
		if (rychList != null && rychList.length > 0) {
			rychdm = rychList[0];
		}
		return rychdm;
	}
	
	/**
	 * 获取学习竞赛查询表头
	 * getxxjstitle ---- 获取学习竞赛查询表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjsTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"xh||xn||nd||xxjsdm","rownum","xh","xm","xn","nd","xymc","zymc","bjmc","xxjsmc"};
		String[] cn = new String[]{"主键","行号","学号","姓名","学年","年度", Base.YXPZXY_KEY,"专业","班级","竞赛获奖名称"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			topList.add(tempMap);//荣誉申诉首页选择申请信息
		}//end for
		return topList;
	}
	
	/**
	 * 获取学习竞赛查询结果
	 * getxxjsqryresult ---- 获取学习竞赛查询结果 
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXxjsResult(XxjsQryModel xxjsqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||xxjsdm,rownum,xh,xm,xn,nd,xymc,zymc,bjmc,xxjsmc from view_xsxxjs where 1=1 ";
		StringBuffer whereSql = getWhereSql4(xxjsqryModel);
		String[] opList = new String[]{"xh||xn||nd||xxjsdm","rownum","xh","xm","xn","nd","xymc","zymc","bjmc","xxjsmc"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 获取查询条件值
	 * @param xxjsqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql4(XxjsQryModel xxjsqryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xxjsqryModel.getXn();
		String nd = xxjsqryModel.getNd();
		String xydm = xxjsqryModel.getXydm();
		String xh = xxjsqryModel.getXh();
		String zydm = xxjsqryModel.getZydm();
		String bjdm = xxjsqryModel.getBjdm();
		String xm = DealString.toGBK(xxjsqryModel.getXm());
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		return whereSql;
	}
	
	/**
	 * 学习竞赛列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXxjSList() throws Exception {
		List<HashMap<String, String>> xxjsList = new ArrayList<HashMap<String,String>>();
		String sql = "select xxjsdm,xxjsmc from xxjsdmb";
		xxjsList = dao.getArrayList(sql, new String[]{}, new String[]{"xxjsdm","xxjsmc"});
		return xxjsList;
	}
	
	/**
	 * 通过学号获取学生姓名，学院，专业，班级
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsInfo(String xh) throws Exception {
		String sql = "select xm,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xh});
		return resMap;
	}
	
	/**
	 * 保存学习竞赛信息
	 * @param xsjxsaveModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveXxjsInfo(XsjxSaveModel xsjxsaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = xsjxsaveModel.getXh();
		String xn = xsjxsaveModel.getXn();
		String nd = xsjxsaveModel.getNd();
		String hjsj = xsjxsaveModel.getHjsj();
		String xxjsdm = xsjxsaveModel.getXxjsdm();
		String bz = DealString.toGBK(xsjxsaveModel.getBz());
		boolean bDel = StandardOperation.delete("xsxxjsb", new String[] { "xh",
				"xn", "nd", "xxjsdm" }, new String[] { xh, xn, nd, xxjsdm },
				request);
		if (bDel) {
			bFlag = StandardOperation.insert("xsxxjsb", new String[] { "xh",
					"xn", "nd", "xxjsdm", "hjsj", "bz" }, new String[] { xh,
					xn, nd, xxjsdm, hjsj, bz }, request);
		}
		return bFlag;
	}
	
	/**
	 * 通过主键获取学习竞赛信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXxjsInfoByPk(String pkValue)throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xn,nd,xm,xymc,zymc,bjmc,xxjsdm,hjsj,bz from view_xsxxjs where xh||xn||nd||xxjsdm = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * 学习竞赛批量删除
	 * xxjsdel ---- 学习竞赛批量删除 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String xxjsDel(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from xsxxjsb where xh||xn||nd||xxjsdm = '" + whichxh + "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 获取学生学年内平均分及班级名次
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	/*public HashMap<String, String> getXsPjfMc(String xh, String xn) throws Exception {
		String sql = "select xh,xn,pjf,mc from view_xspjfandmc where xh=? and xn=? and rownum=1";
		HashMap<String, String> xspjfmcList = dao.getMapNotOut(sql, new String[]{xh,xn});
		//System.out.println(xspjfmcList[0]+xspjfmcList[1]);
		return xspjfmcList;
	}
	
	public HashMap<String, String> getXjbjByxx(String xh, String xn) throws Exception {
		String sql = "select a.bjdm from view_xsjbxx a,(select bjdm from view_pjpy_xjbjandwmsq where fdysh = '通过' and xysh = '通过' and xxsh = '通过' and xn=?) b  where xh=? and a.bjdm=b.bjdm";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xn,xh});
		return resMap;
	}*/
	
	/**
	 * 学生评审核信息表
	 */
	public HashMap<String, String> getXsPsxxb(String xh, String xn) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = " select (select bjgbmc from view_bjgbxx where xh='"
			+ xh
			+ "' and rownum=1) drzw,(select xxjsmc from view_xsxxjs where xh='"+xh+"' and xn='"+xn+"' and rownum=1) xxjsmc,(select pjf from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) pjf,(select mc from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) mc,(select b.rychmc from view_xsjbxx a,(select bjdm, xn,rychmc from view_pjpy_xjbjandwmsq where xysh = '通过' and xxsh = '通过') b where a.bjdm = b.bjdm and xh='"+xh+"' and rownum=1) bjrychmc, (select (case when (instr((select xsdjks_xnxq from zfxfzb.xxmc),'CET4',1,1)>0) then (case when (to_number(a.dcj)>=420) then 'tg' else 'btg' end) else (case when a.dcj>=60 then 'tg' else 'btg' end) end) tg from (select xh,xn,xq,max(cj) dcj from zfxfzb.xsdjksb where djksmc like '%大学英语四级%' group by xh,xn,xq) a where a.xh='"+xh+"' and a.xn='"+xn+"' and rownum=1) cet4, (select (case when (to_number(dcj)>60) then 'tg' else 'btg' end) tg from (select xh,xn,max(cj) dcj,djksmc from zfxfzb.xsdjksb a where djksmc like '%二级%' group by xh,xn,djksmc) where xh='"+xh+"' and xn='"+xn+"') jsjej,(select lbmc from view_wmqspbxx where ssbh=(select ssbh from view_xszsxx where xh='"+xh+"' ) and xn='"+xn+"') wmss from dual";
		//select (select drzw from xsgbxxb where xh='"+xh+"' and xn='"+xn+"' and rownum=1) drzw,(select xxjsmc from view_xsxxjs where xh='"+xh+"' and xn='"+xn+"' and rownum=1) xxjsmc,(select pjf from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) pjf,(select mc from view_xspjfandmc where xh='"+xh+"' and xn='"+xn+"' and rownum=1) mc,(select b.rychmc from view_xsjbxx a,(select bjdm, xn,rychmc from view_pjpy_xjbjandwmsq where fdysh = '通过' and xysh = '通过' and xxsh = '通过') b where a.bjdm = b.bjdm and xh='"+xh+"' and rownum=1) rychmc, (select (case when (instr((select xsdjks_xnxq from hzdz.xxmc),'CET4',1,1)>0) then (case when (to_number(a.dcj)>=420) then 'tg' else 'btg' end) else (case when a.dcj>=60 then 'tg' else 'btg' end) end) tg from (select xh,xn,xq,max(cj) dcj from hzdz.xsdjksb where djksmc='CET4' group by xh,xn,xq) a where a.xh='"+xh+"' and a.xn='"+xn+"' and rownum=1) cet4, (select (case when (to_number(dcj)>60) then 'tg' else 'btg' end) tg from (select xh,xn,max(cj) dcj,djksmc from hzdz.xsdjksb a where djksmc like '二级%' group by xh,xn,djksmc) where xh='"+xh+"' and xn='"+xn+"') jsjej from dual";
		resMap = dao.getMapNotOut(sql, new String[]{});
		return resMap;
	}
	
	/**
	 * 学生证打印位置
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String[] getTopLeftStr(String page) throws Exception {
		String[] topleftList = new String[]{};
		String sql = "select top,left from printpage where page=?";
		topleftList = dao.getOneRs(sql, new String[]{page}, new String[]{"top", "left"});
		return topleftList;
	}

	/**
	 * 成绩同步
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjbTb(String xn, String xq) throws Exception {
		return dao.runProcuder("{call PJPY_GDB_CJTB(?,?)}", new String[]{xn,xq});
	}
	
	/**
	 * 与教务等级考试同步成绩
	 * @param xn
	 * @param xq
	 * @param djksmc
	 * @throws Exception
	 */
	public boolean djksCjTb(String xn, String xq) throws Exception {
		boolean flg = false;
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
			flg=dao.runProcuder("{call pjpy_jhzy_djkscjtb(?,?)}", new String[]{xn,xq});
		}else{
			flg=dao.runProcuder("{call PJPY_GDB_DJKSCJTB(?,?)}", new String[]{xn,xq});
		}
		return flg;
	}
	
	/**
	 * 奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		return dao.getList("select jxjdm xmdm,jxjmc xmmc from jxjdmb", new String[]{}, new String[]{"xmdm", "xmmc"});
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjTjList(int type) throws Exception {
		List<HashMap<String, String>> jxjtjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (type == 0) {
			enList = new String[]{ "pjcj"};
			cnList = new String[]{ "平均成绩"};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				enList = new String[]{"xybxf","xybxkf","gybxf","gybxkf","dyfjf", "dyzf", "tycj", "pjcj", "zhszcpcjpm"};
				cnList = new String[]{"校园表现总分","校园表现总扣分","公寓表现总分","公寓表现总扣分", "德育附加分", "德育总分", "体育成绩", "平均成绩", "综合素质排名"};
			}
		} else if (3==type) {
			enList = new String[]{ "dkcj","pjcj"};
			cnList = new String[]{ "单科成绩","平均成绩"};
		} else if (4==type) {
			enList = new String[]{ "jd","dcj","tcj","zfpm"};
			cnList = new String[]{ "平均学分绩点","德育成绩","体育成绩","综合分"};
		} else if (5==type) {
			enList = new String[]{"jd", "tcj", "drxf"};
			cnList = new String[]{"平均学分绩点", "体育成绩", "第二课堂学分"};
		} else {
			enList = new String[]{"pjcj", "cjmc", "sjdxspfmc"};
			cnList = new String[]{"平均成绩", "成绩排名", "十佳大学生评分名次"};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				enList = new String[]{ "dyzf", "tycj", "pjcj", "cjpm", "dkcj", "zhszcpcjpm", "pxbl"};
				cnList = new String[]{ "德育总分", "体育成绩", "平均成绩", "成绩排名", "单科成绩", "综合素质测评成绩排名", "评选比例"};
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				enList = new String[]{"pjcj", "cjmc", "sjdxspfmc","xnxqcj","bjgkm","wjcf"};
				cnList = new String[]{"平均成绩", "成绩排名", "十佳大学生评分名次","学年度每学期平均成绩","不及格科目","违纪处分"};
			}
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("tjdm", enList[i]);
			tmpMap.put("tjmc", cnList[i]);
			jxjtjList.add(tmpMap);
		}
		return jxjtjList;
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdczList(int type) throws Exception {
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (type == 0) {
//			enList = new String[]{"max", "min", "sum", "pm"};
//			cnList = new String[]{"最大值", "最小值", "总和", "排名"};
			enList = new String[]{"max",  "pm"};
			cnList = new String[]{"最大值", "排名"};			
		} else {
			enList = new String[]{"max", "min", "avg", "pm"};
			cnList = new String[]{"最大值", "最小值", "平均值", "排名"};						
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zdczdm", enList[i]);
			tmpMap.put("zdczmc", cnList[i]);
			zdczList.add(tmpMap);
		}
		return zdczList;
	}
	
	/**
	 * 根据TYPE来输出不用的条件列表0奖学金,1荣誉称号
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdbjList(int type) throws Exception {
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
//		enList = new String[]{">=", ">", "=", "<", "<="};
//		cnList = new String[]{"大于或等于", "大于", "等于", "小于", "小于或等于"};
		enList = new String[]{">=","<"};
		cnList = new String[]{"大于或等于","小于"};		
		if (1==type) {
			enList = new String[]{">=", };
			cnList = new String[]{"大于或等于"};
		} else if (2==type) {
			enList = new String[]{">=", ">="};
			cnList = new String[]{"大于或等于","班级排名前"};
		} else if (3==type) {
			enList = new String[]{">="};
			cnList = new String[]{"班级排名前"};
		} else if (4==type) {
			enList = new String[]{">="};
			cnList = new String[]{"大于或等于"};
		}else if (5==type) {
			enList = new String[]{">=","<","="};
			cnList = new String[]{"大于或等于","小于","等于"};		
		}
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zjbjdm", enList[i]);
			tmpMap.put("zjbjmc", cnList[i]);
			zdbjList.add(tmpMap);
		}
		return zdbjList;
	}
	
	/**
	 * 荣誉称号列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRych() throws Exception {
		return dao.getList("select rychdm xmdm,rychmc xmmc from rychdmb", new String[]{}, new String[]{"xmdm", "xmmc"});
	}

	public List<HashMap<String, String>> getZdczbjList(String zdcz) throws Exception {
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		enList = new String[]{">=", ">", "=", "<", "<="};
		cnList = new String[]{"大于或等于", "大于", "等于", "小于", "小于或等于"};
		for (int i = 0;i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("zjbjdm", enList[i]);
			tmpMap.put("zjbjmc", cnList[i]);
			zdbjList.add(tmpMap);
		}
		return zdbjList;
	}
	
	/**
	 * 条件设置保存
	 * @param tjzdModel
	 * @return
	 * @throws Exception
	 */
	public boolean tjSave(TjszModel tjzdModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation
				.delete("jxjhdtj", "jxjdm||tjzdm", 
						tjzdModel.getXmdm() + tjzdModel.getTjdm(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("jxjhdtj", new String[] { "xn",
					"jxjdm", "tjzdm", "tjzdlyb", "tj", "zdcz", "zdbj", "zdlx", "zdmc" ,"czmc"},
					new String[] { tjzdModel.getXn(), tjzdModel.getXmdm(),
							tjzdModel.getTjdm(), tjzdModel.getTjzdlyb(),
							tjzdModel.getZdval(), ">=", tjzdModel
							.getZdbj(), tjzdModel.getCslx(), tjzdModel.getTjmc(),tjzdModel.getCzmc() }, request);
		}
		return bFlag;
	}
	
	/**
	 * 条件设置查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xmmc", "tjzdm", "zdbj", "tj"};
		String[] cnList = new String[]{"pk", "行号", "项目名称", "条件字段名", "字段比较", "对应值"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 条件设置查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTjResult(String xmdm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] enList = new String[]{"pk", "rownum", "jxjmc", "zdmc", "czmc", "tj"};
		String sql = "select a.jxjdm||a.tjzdm pk,rownum,(case when a.zdlx='jxj' then (select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) else (select b.rychmc from rychdmb b where a.jxjdm=b.rychdm) end) jxjmc,a.zdmc,a.czmc,(case  when a.tjzdm='zhszcpcjpm' then a.tj||'%' else a.tj end)tj from jxjhdtj a where a.jxjdm=?";
		resList = dao.rsToVator(sql, new String[]{xmdm}, enList);
		return resList;
	}
	
	/**
	 * 条件设置批量删除
	 * @return
	 * @throws Exception
	 */
	public boolean tjszplDel() throws Exception {
		return dao.runUpdate("delete from jxjhdtj", new String[]{});
	}
	
	/**
	 * 条件设置单个删除
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjszdgDel(String pkValue) throws Exception {
		return dao.runUpdate("delete from jxjhdtj where jxjdm||tjzdm=?", new String[]{pkValue});
	}
	
	/**
	 * 通过学号获取该生的十佳大学生得分及排名
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getStudfAndPm(String xh) throws Exception {
		String[] dfAndPmList = new String[2];
		String sql = "select num,xh,zf from (select rownum num,xh,zf from (select xh,zf from sjdxspfxzb order by to_number(zf) desc)) where xh=? ";
		dfAndPmList = dao.getOneRs(sql, new String[]{xh}, new String[]{"num", "zf"});
		return dfAndPmList;
	}
	
	/**
	 * 先进班级审核结果
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int getXjbjShjg(HttpServletRequest request) throws Exception {
		String[] jxjsq = dao.getOneRs("select jxjsqxn,jxjsqxq from xtszb ", new String[]{}, new String[]{"jxjsqxn","jxjsqxq"}); 
		String tg = "";
		if (request.getSession().getAttribute("userType").toString().equalsIgnoreCase("xy")) {
			tg = dao.getOneRs("select count(*) num from pjpy_xjbjandwmsqb where xn=? and xq=? and xysh='通过'", new String[]{jxjsq != null ? jxjsq[0] : "" , jxjsq != null ? jxjsq[1] : ""}, "num");
		} else {
			tg = dao.getOneRs("select count(*) num from pjpy_xjbjandwmsqb where xn=? and xq=? and xysh='通过' and xxsh='通过'", new String[]{jxjsq != null ? jxjsq[0] : "" , jxjsq != null ? jxjsq[1] : ""}, "num");
		}
		return StringUtils.isNull(tg) ? 0 : Integer.parseInt(tg);
	}
	
	public String[] getJxjsqxnxq() throws Exception {
		return dao.getOneRs("select jxjsqxn,jxjsqxq from xtszb",
				new String[] {}, new String[] { "jxjsqxn", "jxjsqxq" });
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXscj(String xh) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String[] colList = null;
		String[] valList = null;

		List<String[]> resList = new ArrayList<String[]>();
		String[] jxjsqxnxq = getJxjsqxnxq();
		if (jxjsqxnxq == null) {
			jxjsqxnxq = new String[2];
		}

		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			sql = "select * from (select kcmc, (select xqmc from xqdzb where xqdm = xq) xq, cj"
					+ " from cjb where xh = ? and xn = ? and kcxz not like '%校选修课%'and kcmc not like "
					+ " '%形势与政策%'and upper(xkkh) not like '%BY%' union select '平均分' kcmc,'"
					+ jxjsqxnxq[0]
					+ " 学年 '||(select xqmc from xqdzb where xqdm = xq)||'学期' xq, "
					+ " to_Char(round(avg(cj))) cj from view_zhhcjb where xh = ? and xn = ? "
					+ " and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh)"
					+ " not like '%BY%' group by xq) order by xq desc";
			colList = new String[] { xh, jxjsqxnxq[0], xh, jxjsqxnxq[0] };
			valList = new String[] { "kcmc", "xq", "cj" };
		} else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
			sql = "select kcmc,cj from cjb where xh=? and xn=? ";
			colList = new String[] { xh, jxjsqxnxq[0]};
			valList = new String[] { "kcmc", "cj" };
		}else {
			sql = "select kcmc,cj from cjb where xh=? and xn=? and xq=?";
			colList = new String[] { xh, jxjsqxnxq[0], jxjsqxnxq[1] };
			valList = new String[] { "kcmc", "cj" };
		}

		resList = dao.rsToVator(sql, colList, valList);
		return resList;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(Math.round(3.55));
	}
	
	/**
	 * 查询教务版本 1为学年,0为过渡,空则为其他公司教务
	 * @return
	 */
	public String getJwFlag() {
		String jwflag = dao.getOneRs("select jwflag from xtszb", new String[]{}, "jwflag");
		return StringUtils.isNull(jwflag) ? "" : jwflag.trim();
	}
	
	/**
	 * 获得学生考试性质列表
	 * 
	 * @author luojw
	 * @return
	 */
	public List<HashMap<String, String>> getKsxzList() {

		DAO dao = DAO.getInstance();

		String sql = "select distinct (kcxz) dm, kcxz mc from view_cjb where kcxz is not null";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	public List<HashMap<String, String>> getDjksmc() {
		return dao.getList("select distinct djksmc dm,djksmc mc from xsdjksb", new String[]{}, new String[]{"dm", "mc"});
	}
}
