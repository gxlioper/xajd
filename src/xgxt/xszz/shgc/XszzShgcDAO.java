package xgxt.xszz.shgc;

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
 * Description: 上海工程学生资助DAO
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
 * Time: 2009-06-10
 * </p>
 */
public class XszzShgcDAO {
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
		String knrdjg = DealString.toGBK(queryModel.getKnrdjg());

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
		if (!StringUtils.isNull(knrdjg)) {
			if ("已审核".equalsIgnoreCase(knrdjg)){
				whereSql.append(" and knrdjg<>'未审核'");
			} else if ("已通过".equalsIgnoreCase(knrdjg)) {
				whereSql.append(" and knrdjg in ('一般困难','特别困难','特殊')");
			} else {
				whereSql.append(" and knrdjg=?");
				values.add(knrdjg);
			}
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.mzmc,a.zzmmmc from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh",
				"mzmc", "zzmmmc" };
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
	 * 删除困难生历史库信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnslskxx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete SHGC_KNSXX_LSK where nd||xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难生历史库查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnslskTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "rdsj", "knrdjg" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名", "年级",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "认定时间", "认定结果" };
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
	public List<String[]> getKnslskRes(QueryModel queryModel,
			HttpServletRequest request,XszzShgcActionForm xszzShgcActionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "pk", "nd", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "rdsj", "knrdjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql = "select pk,nd,xh,xm,nj,xymc,zymc,bjmc,rdsj,knrdjg from (select nd||xh pk,rownum r,nd,xh,xm,nj,xymc,zymc,bjmc,rdsj,knrdjg from SHGC_KNSXX_LSK where 1=1"
				+ whereSql
				+ ") where r<="
				+ (xszzShgcActionForm.getPages().getStart()) + (xszzShgcActionForm.getPages().getPageSize())
				+ " and r>"
				+ xszzShgcActionForm.getPages().getStart();
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
	public String getKnslskResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from SHGC_KNSXX_LSK where 1=1";
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
	public HashMap<String, String> getKnslskxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,nd,xm,xb,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,mzmc,zzmmmc,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,jtlx,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sqsj,syd,rdsj,knrdjg from SHGC_KNSXX_LSK where nd||xh = ?";
		String[] colList = new String[] { "xh", "nd", "xm", "xb", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "mzmc",
				"zzmmmc", "lxdh", "rxqhk", "jtzz", "yzbm", "jtlxdh",
				"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx", "jtlx", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
				"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
				"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
				"jtcy4_zy", "jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
				"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
				"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm", "mzbm_lxdh",
				"sqsj", "syd", "rdsj", "knrdjg" };
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
	 * 保存困难生历史库信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsk(KnslskModel model, String act, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String xm = Base.chgNull(model.getXm(), "", 1);
		String xb = Base.chgNull(model.getXb(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String mzmc = Base.chgNull(model.getMzmc(), "", 1);
		String zzmmmc = Base.chgNull(model.getZzmmmc(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String rxqhk = Base.chgNull(model.getRxqhk(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtlxdh = Base.chgNull(model.getJtlxdh(), "", 1);
		String sfyycjcshzyhd = Base.chgNull(model.getSfyycjcshzyhd(), "", 1);
		String sfyysqgjzxdkhqgzx = Base.chgNull(model.getSfyysqgjzxdkhqgzx(),
				"", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(model.getJtcy1_zy(), "", 1);
		String jtcy1_nsr = Base.chgNull(model.getJtcy1_nsr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(model.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(model.getJtcy2_zy(), "", 1);
		String jtcy2_nsr = Base.chgNull(model.getJtcy2_nsr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(model.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(model.getJtcy3_zy(), "", 1);
		String jtcy3_nsr = Base.chgNull(model.getJtcy3_nsr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(model.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(model.getJtcy4_zy(), "", 1);
		String jtcy4_nsr = Base.chgNull(model.getJtcy4_nsr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(model.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(model.getJtcy5_zy(), "", 1);
		String jtcy5_nsr = Base.chgNull(model.getJtcy5_nsr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(model.getJtcy5_jkzk(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String xszbdszqk = Base.chgNull(model.getXszbdszqk(), "", 1);
		String jtzszrzhqk = Base.chgNull(model.getJtzszrzhqk(), "", 1);
		String jtzstfywsj = Base.chgNull(model.getJtzstfywsj(), "", 1);
		String qtqk = Base.chgNull(model.getQtqk(), "", 1);
		String mzbm_txdz = Base.chgNull(model.getMzbm_txdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(model.getMzbm_yzbm(), "", 1);
		String mzbm_lxdh = Base.chgNull(model.getMzbm_lxdh(), "", 1);
		String sqsj = Base.chgNull(model.getSqsj(), "", 1);
		String syd = Base.chgNull(model.getSyd(), "", 1);
		String rdsj = Base.chgNull(model.getRdsj(), "", 1);
		String knrdjg = Base.chgNull(model.getKnrdjg(), "", 1);

		if ("mod".equalsIgnoreCase(act)) {
			bFlag = StandardOperation.update("SHGC_KNSXX_LSK", new String[] {
					"xm", "xb", "sfzh", "mzmc", "zzmmmc", "lxdh", "rxqhk",
					"jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
					"sfyysqgjzxdkhqgzx", "jtlx", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
					"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
					"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
					"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
					"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj", "syd", "rdsj", "knrdjg" },
					new String[] { xm, xb, sfzh, mzmc, zzmmmc, lxdh, rxqhk,
							jtzz, yzbm, jtlxdh, sfyycjcshzyhd,
							sfyysqgjzxdkhqgzx, jtlx, jtcy1_xm, jtcy1_nl,
							jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
							jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx,
							jtcy2_gzdw, jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
							jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
							jtcy3_nsr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
							jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_nsr,
							jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
							jtrjnsr, xszbdszqk, jtzszrzhqk, jtzstfywsj, qtqk,
							mzbm_txdz, mzbm_yzbm, mzbm_lxdh, sqsj, syd, rdsj,
							knrdjg }, "nd||xh", nd + xh, request);
		} else {
			bFlag = StandardOperation.insert("SHGC_KNSXX_LSK", new String[] {
					"xh", "nd", "xm", "xb", "sfzh", "mzmc", "zzmmmc", "lxdh",
					"rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
					"sfyysqgjzxdkhqgzx", "jtlx", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
					"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
					"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
					"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
					"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj", "syd", "rdsj", "knrdjg" },
					new String[] { xh, nd, xm, xb, sfzh, mzmc, zzmmmc, lxdh,
							rxqhk, jtzz, yzbm, jtlxdh, sfyycjcshzyhd,
							sfyysqgjzxdkhqgzx, jtlx, jtcy1_xm, jtcy1_nl,
							jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
							jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx,
							jtcy2_gzdw, jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
							jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
							jtcy3_nsr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
							jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_nsr,
							jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
							jtrjnsr, xszbdszqk, jtzszrzhqk, jtzstfywsj, qtqk,
							mzbm_txdz, mzbm_yzbm, mzbm_lxdh, sqsj, syd, rdsj,
							knrdjg }, request);
		}
		return bFlag;
	}
	
}
