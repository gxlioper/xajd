package xgxt.xszz.ycws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 盐城卫生学生资助DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 周觅
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-06-09
 * </p>
 */
public class XszzYcwsDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();// 查询条件值

	/**
	 * 公用方法：获取查询条件
	 * 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = new StringBuffer(" ");
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xh = DealString.toGBK(queryModel.getXh());
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String knrd = DealString.toGBK(queryModel.getKnrd());
		String kndj = DealString.toGBK(queryModel.getKndj());
		String xmmc = DealString.toGBK(queryModel.getXmmc());

		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}// end if
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
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(knrd)) {
			whereSql.append(" and knrd = ?");
			values.add(knrd);
		}// end if
		if (!StringUtils.isNull(kndj)) {
			whereSql.append(" and kndj = ?");
			values.add(kndj);
		}// end if
		if (!StringUtils.isNull(xmmc)) {
			whereSql.append(" and xmmc = ?");
			values.add(xmmc);
		}// end if
		return whereSql;
	}

	/**
	 * 导出数据 getExpDate ---- 款导出数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getExpDate(QueryModel queryZxxsdkModel,
			String tabName, HttpServletRequest request) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();

		String sql = "select * from " + tabName + " where 1=1 ";
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel, request);
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, colList));
		return rs;
	}

	/**
	 * 导出数据表头 getExpTit ---- 导出数据表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getExpTit(String tabName) throws Exception {
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		return dao.getColumnNameCN(colList, tabName);
	}

	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.mzmc,a.zzmmmc,REPLACE(NVL((select b.knrd from ycws_knrdxx b where b.xh=a.xh),'不困难'),'未审核','不困难') kndj from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh",
				"mzmc", "zzmmmc", "kndj" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}

	/**
	 * 删除困难认定信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnrdxx(String[] pk, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pk.length];
		for (int i = 1; i < pk.length; i++) {
			sqlT[i] = "delete ycws_knrdxx where xh='" + pk[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难认定查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnrdTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "knrd" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "年级",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难认定" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 困难认定查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnrdRes(QueryModel queryModel,
			HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "pk", "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "knrd" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql = "select pk,xh,xm,nj,xymc,zymc,bjmc,knrd from (select xh pk,rownum r,xh,xm,nj,xymc,zymc,bjmc,knrd from view_ycws_knrdxx where 1=1"
				+ whereSql
				+ ") where r<="
				+ (xszzYcwsActionForm.getPages().getStart()) + (xszzYcwsActionForm.getPages().getPageSize())
				+ " and r>"
				+ xszzYcwsActionForm.getPages().getStart();
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难认定查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnrdResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from view_ycws_knrdxx where 1=1";
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 得到开关值
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKg() throws Exception {
		return dao
				.getMap(
						"select knrdzsg,knrddr,knrddc,zzzsg,zzdr,zzdc from ycws_sjb where rownum=1",
						new String[] {}, new String[] { "knrdzsg", "knrddr",
								"knrddc", "zzzsg", "zzdr", "zzdc" });
	}
	
	/**
	 * 获取困难认定信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jtlx,sfdb,jtnsr,jtrk,sfyzmcl,knrd from view_ycws_knrdxx where xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "mzmc", "zzmmmc",
				"xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "lxdh", "jtlx", "sfdb", "jtnsr", "jtrk", "sfyzmcl",
				"knrd" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	/**
	 * 保存困难认定信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnrd(KnrdModel model, String act, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String sfdb = Base.chgNull(model.getSfdb(), "", 1);
		String jtnsr = Base.chgNull(model.getJtnsr(), "", 1);
		String jtrk = Base.chgNull(model.getJtrk(), "", 1);
		String sfyzmcl = Base.chgNull(model.getSfyzmcl(), "", 1);
		String knrd = Base.chgNull(model.getKnrd(), "", 1);

		if ("mod".equalsIgnoreCase(act)){
			bFlag = StandardOperation.update("ycws_knrdxx",
					new String[] { "lxdh", "jtlx", "sfdb", "jtnsr", "jtrk",
							"sfyzmcl", "knrd" }, new String[] { lxdh, jtlx,
							sfdb, jtnsr, jtrk, sfyzmcl, knrd }, "xh", xh,
					request);
		} else {
			String sHave = isKnsrdDataCf(xh);
			if ("-1".equalsIgnoreCase(sHave)) {
				bFlag = StandardOperation.insert("ycws_knrdxx", new String[] {
						"xh", "lxdh", "jtlx", "sfdb", "jtnsr", "jtrk",
						"sfyzmcl", "knrd" }, new String[] { xh, lxdh, jtlx,
						sfdb, jtnsr, jtrk, sfyzmcl, knrd }, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}
	
	/**
	 * 判断困难认定是否重复，重复的返回1，没有重复的返回-1 isKnsrdDataCf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsrdDataCf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from ycws_knrdxx where xh = ?";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}
	
	/**
	 * 删除困难资助项目信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnzzxmxx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete ycws_knzzxm where id='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难资助项目查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnzzxmTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "kndj", "xmmc", "hdje", "hdrq" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "年级",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难等级", "项目名称", "金额", "获得日期" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 困难资助项目查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnzzxmRes(QueryModel queryModel,
			HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "pk", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "kndj", "xmmc", "hdje", "hdrq" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql = "select pk,xh,xm,nj,xymc,zymc,bjmc,kndj,xmmc,hdje,hdrq from (select id pk,rownum r,xh,xm,nj,xymc,zymc,bjmc,kndj,xmmc,hdje,hdrq from view_ycws_knzzxm where 1=1"
				+ whereSql
				+ ") where r<="
				+ (xszzYcwsActionForm.getPages().getStart()) + (xszzYcwsActionForm.getPages().getPageSize())
				+ " and r>"
				+ xszzYcwsActionForm.getPages().getStart();
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难资助项目查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnzzxmResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from view_ycws_knzzxm where 1=1";
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取困难资助项目信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnzzxmxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select id,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,xmmc,hdje,hdrq from view_ycws_knzzxm where id = ?";
		String[] colList = new String[] { "id", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "kndj", "xmmc", "hdje", "hdrq" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	/**
	 * 保存困难资助项目信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnzzxm(KnzzxmModel model, String act, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String id = Base.chgNull(model.getId(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xmmc = Base.chgNull(model.getXmmc(), "", 1);
		String hdje = Base.chgNull(model.getHdje(), "", 1);
		String hdrq = Base.chgNull(model.getHdrq(), "", 1);

		if ("mod".equalsIgnoreCase(act)){
			bFlag = StandardOperation.update("ycws_knzzxm", new String[] {
					"xh", "xmmc", "hdje", "hdrq" }, new String[] { xh, xmmc,
					hdje, hdrq }, "id", id, request);
		} else {
			bFlag = StandardOperation.insert("ycws_knzzxm", new String[] {
					"xh", "xmmc", "hdje", "hdrq" }, new String[] { xh, xmmc,
					hdje, hdrq }, request);
		}
		return bFlag;
	}
	
}
