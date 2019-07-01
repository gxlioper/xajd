package xgxt.xszz.nblg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Description: 宁波理工学生资助DAO
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
 * Time: 2009-01-13
 * </p>
 */
public class XszzNblgDAO {
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
		String mzpy = queryModel.getMzpy();
		String xysh = queryModel.getXysh();
		String xxsh = queryModel.getXxsh();

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
		if (!StringUtils.isNull(mzpy)) {
			whereSql.append(" and mzpy = ?");
			values.add(mzpy);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny,REPLACE(NVL((select b.xxsh from nblg_kns b where b.xh=a.xh and b.xn=?),'不困难'),'未审核','不困难') kndj from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny", "kndj" };
		String[] sLen = dao.getOneRs(sql, new String[] { Base.currXn, pkVal },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		sql = "select sjhm,jtszd jtzz,lxdh1 jtdh,yb yzbm,srly,jtcy1_xm,jtcy1_ch jtcy1_cw,jtcy1_nl,jtcy1_gx,jtcy1_gzdz jtcy1_gzdw,jtcy2_xm,jtcy2_ch jtcy2_cw,jtcy2_nl,jtcy2_gx,jtcy2_gzdz jtcy2_gzdw,jtcy3_xm,jtcy3_ch jtcy3_cw,jtcy3_nl,jtcy3_gx,jtcy3_gzdz jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz jtcy5_gzdw from xsfzxxb where xh=?";
		colList = new String[] { "sjhm", "jtzz", "jtdh", "yzbm", "srly",
				"jtcy1_xm", "jtcy1_cw", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy2_xm", "jtcy2_cw", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw" };
		sLen = dao.getOneRs(sql, new String[] { pkVal },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		stuList.put("jtcy1_gz", stuList.get("jtcy1_gzdw"));
		stuList.put("jtcy2_gz", stuList.get("jtcy2_gzdw"));
		stuList.put("jtcy3_gz", stuList.get("jtcy3_gzdw"));
		stuList.put("jtcy4_gz", stuList.get("jtcy4_gzdw"));
		stuList.put("jtcy5_gz", stuList.get("jtcy5_gzdw"));
		return stuList;
	}

	/**
	 * 获取困难生详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssjssdh,sjhm,jtdh,jtzz,jtrjysr,sqly,sqsj,mzpy,csly,xysh,xxsh from view_nblg_kns where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "ssjssdh", "sjhm", "jtdh", "jtzz",
				"jtrjysr", "sqly", "sqsj", "mzpy", "csly", "xysh", "xxsh" };
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
	 * 得到困难生申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存困难生申请信息，成功返回TRUE，反之返回FALSE saveKnsrdSqxx ---- 保存困难生认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String ssjssdh = Base.chgNull(model.getSsjssdh(), "", 1);
		String sjhm = Base.chgNull(model.getSjhm(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation
					.insert("nblg_kns", new String[] { "xn", "xh", "ssjssdh",
							"sjhm", "jtdh", "jtzz", "jtrjysr", "sqly" },
							new String[] { xn, xh, ssjssdh, sjhm, jtdh, jtzz,
									jtrjysr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_kns", new String[] {
					"ssjssdh", "sjhm", "jtdh", "jtzz", "jtrjysr", "sqly",
					"sqsj", "mzpy", "csly", "xysh", "xxsh" }, new String[] {
					ssjssdh, sjhm, jtdh, jtzz, jtrjysr, sqly, now, "未审核", "",
					"未审核", "未审核" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断困难生是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_kns where xh = ? and xn = ? and xxsh in ('困难','特别困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_kns where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取困难生申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("ssjssdh", Base.chgNull(model.getSsjssdh(), "", 1));
		stuList.put("sjhm", Base.chgNull(model.getSjhm(), "", 1));
		stuList.put("jtdh", Base.chgNull(model.getJtdh(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("csly", Base.chgNull(model.getCsly(), "", 1));

		String mzpy = Base.chgNull(model.getMzpy(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);

		if ("".equalsIgnoreCase(mzpy)) {
			mzpy = "未审核";
		}
		if ("".equalsIgnoreCase(xysh)) {
			xysh = "未审核";
		}
		if ("".equalsIgnoreCase(xxsh)) {
			xxsh = "未审核";
		}

		if (mzpy.equalsIgnoreCase(xysh)) {
			stuList.put("tj_xy", "1");
		} else {
			stuList.put("tj_xy", "0");
		}
		if (xysh.equalsIgnoreCase(xxsh)) {
			stuList.put("xy_xx", "1");
		} else {
			stuList.put("xy_xx", "0");
		}

		stuList.put("mzpy", mzpy);
		stuList.put("xysh", xysh);
		stuList.put("xxsh", xxsh);
		return stuList;
	}

	/**
	 * 删除困难生信息 delKnsxx ---- 删除困难生信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_kns where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_kns where xn||xh='" + pkT[i]
						+ "' and xxsh not in ('困难','特别困难')";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 困难生查询表头 Knstit ---- 困难生表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "mzpy", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				"身份证号", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "民主评议", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 困难生查询结果 getKnsRes ---- 困难生结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where xysh in ('困难','特别困难')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "mzpy", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 批量修改困难生审核结果 modKnsxx ---- 批量修改困难生审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shType, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				if ("3".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xxsh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set mzpy='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				}
			} else {
				if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set mzpy='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 保存困难生审核信息，成功返回TRUE，反之返回FALSE saveKnsShxx ---- 保存困难生审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String mzpy = Base.chgNull(model.getMzpy(), "", 1);
		String csly = Base.chgNull(model.getCsly(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String sHave = isKnsDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_kns",
					new String[] { "xxsh" }, new String[] { xxsh }, "xn||xh",
					xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_kns", new String[] {
						"mzpy", "csly", "xysh" }, new String[] { mzpy, csly,
						xysh }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取临时困难补助详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,chhzjl,yhzzqk,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sqly,sqje,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_lsknbz where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "chhzjl", "yhzzqk", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "sqly",
				"sqje", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到临时困难补助申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getLsknbzSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='临时困难补助' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存临时困难补助申请信息，成功返回TRUE，反之返回FALSE saveLsknbzrdSqxx ---- 保存临时困难补助认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzSqxx(LsknbzModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String yhzzqk = Base.chgNull(model.getYhzzqk(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(model.getRjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isLsknbzDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_lsknbz", new String[] {
					"xn", "xh", "chhzjl", "yhzzqk", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "sqly", "sqje" },
					new String[] { xn, xh, chhzjl, yhzzqk, jthk, jtzrks,
							jtyzsr, rjysr, srly, jtzz, yzbm, sqly, sqje },
					request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
					"chhzjl", "yhzzqk", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "sqly", "sqje", "sqsj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { chhzjl,
					yhzzqk, jthk, jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
					sqly, sqje, now, "未审核", "", "未审核", "" }, "xn||xh", xn + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断临时困难补助是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isLsknbzdatacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isLsknbzDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_lsknbz where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_lsknbz where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取临时困难补助申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzSqb(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("chhzjl", Base.chgNull(model.getChhzjl(), "", 1));
		stuList.put("yhzzqk", Base.chgNull(model.getYhzzqk(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtzrks", Base.chgNull(model.getJtzrks(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(model.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqje", Base.chgNull(model.getSqje(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * 删除临时困难补助信息 delLsknbzxx ---- 删除临时困难补助信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsknbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_lsknbz where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_lsknbz where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改临时困难补助审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsknbzxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_lsknbz set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_lsknbz set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 临时困难补助查询表头 getLsknbzTit ---- 临时困难补助表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsknbzTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请金额", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 临时困难补助查询结果 getLsknbzRes ---- 临时困难补助结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsknbzRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存临时困难补助审核信息，成功返回TRUE，反之返回FALSE saveLsknbzShxx ---- 保存临时困难补助审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzShxx(LsknbzModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isLsknbzDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取助学金详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,chhzjl,sqzxjlx,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_zxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "chhzjl", "sqzxjlx", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"sqly", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到助学金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getZxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='助学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存助学金申请信息，成功返回TRUE，反之返回FALSE saveZxjrdSqxx ---- 保存助学金认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjSqxx(ZxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String sqzxjlx = Base.chgNull(model.getSqzxjlx(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(model.getRjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isZxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_zxj", new String[] { "xn",
					"xh", "chhzjl", "sqzxjlx", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { xn, xh, chhzjl, sqzxjlx, jthk, jtzrks,
							jtyzsr, rjysr, srly, jtzz, yzbm, jtcy1_xm,
							jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl,
							jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl, jtcy3_gx,
							jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_zxj", new String[] {
					"chhzjl", "sqzxjlx", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
					"jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
					"jtcy5_gzdw", "sqly", "sqsj", "xysh", "xyshyj", "xxsh",
					"xxshyj" }, new String[] { chhzjl, sqzxjlx, jthk, jtzrks,
					jtyzsr, rjysr, srly, jtzz, yzbm, jtcy1_xm, jtcy1_nl,
					jtcy1_gx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx,
					jtcy2_gzdw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
					jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly, now, "未审核", "",
					"未审核", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isZxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isZxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_zxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_zxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取助学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjSqb(ZxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("chhzjl", Base.chgNull(model.getChhzjl(), "", 1));
		stuList.put("sqzxjlx", Base.chgNull(model.getSqzxjlx(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtzrks", Base.chgNull(model.getJtzrks(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(model.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(model.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(model.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(model.getJtcy1_gzdw(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(model.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(model.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(model.getJtcy2_gzdw(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(model.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(model.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(model.getJtcy3_gzdw(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(model.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(model.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(model.getJtcy4_gzdw(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(model.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(model.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_gzdw", Base.chgNull(model.getJtcy5_gzdw(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * 删除助学金信息 delZxjxx ---- 删除助学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_zxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_zxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改助学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_zxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_zxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 助学金查询表头 getZxjTit ---- 助学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqzxjlx", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请助学金类型", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 助学金查询结果 getZxjRes ---- 助学金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqzxjlx", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存助学金审核信息，成功返回TRUE，反之返回FALSE saveZxjShxx ---- 保存助学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjShxx(ZxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isZxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_zxj", new String[] { "xxsh",
					"xxshyj" }, new String[] { xxsh, xxshyj }, "xn||xh", xn
					+ xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_zxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取国家助学金详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,sxnywwjcf,xxqk,chhzjlhzzqk,sqdj,jthk,jtlx,sfly,sfls,jtrkzs,jtyzsr,jtrjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,sqly,sqsj,bdsyj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjzxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "sxnywwjcf", "xxqk",
				"chhzjlhzzqk", "sqdj", "jthk", "jtlx", "sfly", "sfls",
				"jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw",
				"jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
				"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr",
				"sqly", "sqsj", "bdsyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到国家助学金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存国家助学金申请信息，成功返回TRUE，反之返回FALSE saveGjzxjrdSqxx ---- 保存国家助学金认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sxnywwjcf = Base.chgNull(model.getSxnywwjcf(), "", 1);
		String xxqk = Base.chgNull(model.getXxqk(), "", 1);
		String chhzjlhzzqk = Base.chgNull(model.getChhzjlhzzqk(), "", 1);
		String sqdj = Base.chgNull(model.getSqdj(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String sfly = Base.chgNull(model.getSfly(), "", 1);
		String sfls = Base.chgNull(model.getSfls(), "", 1);
		String jtrkzs = Base.chgNull(model.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjzxj", new String[] { "xn",
					"xh", "sxnywwjcf", "xxqk", "chhzjlhzzqk", "sqdj", "jthk",
					"jtlx", "sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "sqly" }, new String[] { xn, xh,
					sxnywwjcf, xxqk, chhzjlhzzqk, sqdj, jthk, jtlx, sfly, sfls,
					jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
					"sxnywwjcf", "xxqk", "chhzjlhzzqk", "sqdj", "jthk", "jtlx",
					"sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr", "srly",
					"jtzz", "yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "sqly", "sqsj", "bdsyj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { sxnywwjcf,
					xxqk, chhzjlhzzqk, sqdj, jthk, jtlx, sfly, sfls, jtrkzs,
					jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm, jtcy1_cw,
					jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
					jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw,
					jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr,
					sqly, now, "", "未审核", "", "未审核", "" }, "xn||xh", xn + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断国家助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isZxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjzxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjzxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取国家助学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("sxnywwjcf", Base.chgNull(model.getSxnywwjcf(), "", 1));
		stuList.put("xxqk", Base.chgNull(model.getXxqk(), "", 1));
		stuList.put("chhzjlhzzqk", Base.chgNull(model.getChhzjlhzzqk(), "", 1));
		stuList.put("sqdj", Base.chgNull(model.getSqdj(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtlx", Base.chgNull(model.getJtlx(), "", 1));
		stuList.put("sfly", Base.chgNull(model.getSfly(), "", 1));
		stuList.put("sfls", Base.chgNull(model.getSfls(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(model.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("bdsyj", Base.chgNull(model.getBdsyj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * 删除国家助学金信息 delGjzxjxx ---- 删除国家助学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_gjzxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjzxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改国家助学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_gjzxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjzxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 国家助学金查询表头 getGjzxjTit ---- 国家助学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqdj", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请等级", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学金查询结果 getGjzxjRes ---- 国家助学金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqdj", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存国家助学金审核信息，成功返回TRUE，反之返回FALSE saveGjzxjShxx ---- 保存国家助学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String bdsyj = Base.chgNull(model.getBdsyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjzxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
						"xysh", "xyshyj", "bdsyj" }, new String[] { xysh,
						xyshyj, bdsyj }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取国家励志奖学金详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,sxnzycjzypm,sxndycpbjpm,sxnywwjcf,sxnbjgkcs,chhzjlhzzqk,jthk,jtlx,sfly,sfls,jtrkzs,jtyzsr,jtrjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,sqly,sqsj,bdsyj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjlzjxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "sxnzycjzypm", "sxndycpbjpm",
				"sxnywwjcf", "sxnbjgkcs", "chhzjlhzzqk", "jthk", "jtlx",
				"sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz",
				"yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr",
				"jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm",
				"jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
				"jtcy5_sr", "sqly", "sqsj", "bdsyj", "xysh", "xyshyj", "xxsh",
				"xxshyj" };
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
	 * 得到国家励志奖学金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getGjlzjxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家励志奖学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存国家励志奖学金申请信息，成功返回TRUE，反之返回FALSE saveGjlzjxjrdSqxx ---- 保存国家励志奖学金认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sxnzycjzypm = Base.chgNull(model.getSxnzycjzypm(), "", 1);
		String sxndycpbjpm = Base.chgNull(model.getSxndycpbjpm(), "", 1);
		String sxnywwjcf = Base.chgNull(model.getSxnywwjcf(), "", 1);
		String sxnbjgkcs = Base.chgNull(model.getSxnbjgkcs(), "", 1);
		String chhzjlhzzqk = Base.chgNull(model.getChhzjlhzzqk(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String sfly = Base.chgNull(model.getSfly(), "", 1);
		String sfls = Base.chgNull(model.getSfls(), "", 1);
		String jtrkzs = Base.chgNull(model.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjlzjxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjlzjxj", new String[] {
					"xn", "xh", "sxnzycjzypm", "sxndycpbjpm", "sxnywwjcf",
					"sxnbjgkcs", "chhzjlhzzqk", "jthk", "jtlx", "sfly", "sfls",
					"jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm",
					"jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm",
					"jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw",
					"jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
					"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr",
					"sqly" }, new String[] { xn, xh, sxnzycjzypm, sxndycpbjpm,
					sxnywwjcf, sxnbjgkcs, chhzjlhzzqk, jthk, jtlx, sfly, sfls,
					jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
					"sxnzycjzypm", "sxndycpbjpm", "sxnywwjcf", "sxnbjgkcs",
					"chhzjlhzzqk", "jthk", "jtlx", "sfly", "sfls", "jtrkzs",
					"jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm", "jtcy1_xm",
					"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
					"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
					"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
					"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "sqly",
					"sqsj", "bdsyj", "xysh", "xyshyj", "xxsh", "xxshyj" },
					new String[] { sxnzycjzypm, sxndycpbjpm, sxnywwjcf,
							sxnbjgkcs, chhzjlhzzqk, jthk, jtlx, sfly, sfls,
							jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm,
							jtcy1_xm, jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm,
							jtcy2_cw, jtcy2_gz, jtcy2_sr, jtcy3_xm, jtcy3_cw,
							jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw, jtcy4_gz,
							jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr,
							sqly, now, "", "未审核", "", "未审核", "" }, "xn||xh", xn
							+ xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断国家励志奖学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isZxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjlzjxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjlzjxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取国家励志奖学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("sxnzycjzypm", Base.chgNull(model.getSxnzycjzypm(), "", 1));
		stuList.put("sxndycpbjpm", Base.chgNull(model.getSxndycpbjpm(), "", 1));
		stuList.put("sxnywwjcf", Base.chgNull(model.getSxnywwjcf(), "", 1));
		stuList.put("sxnbjgkcs", Base.chgNull(model.getSxnbjgkcs(), "", 1));
		stuList.put("chhzjlhzzqk", Base.chgNull(model.getChhzjlhzzqk(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtlx", Base.chgNull(model.getJtlx(), "", 1));
		stuList.put("sfly", Base.chgNull(model.getSfly(), "", 1));
		stuList.put("sfls", Base.chgNull(model.getSfls(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(model.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("bdsyj", Base.chgNull(model.getBdsyj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * 删除国家励志奖学金信息 delGjlzjxjxx ---- 删除国家励志奖学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_gjlzjxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjlzjxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改国家励志奖学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_gjlzjxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjlzjxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 国家励志奖学金查询表头 getGjlzjxjTit ---- 国家励志奖学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家励志奖学金查询结果 getGjlzjxjRes ---- 国家励志奖学金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存国家励志奖学金审核信息，成功返回TRUE，反之返回FALSE saveGjlzjxjShxx ---- 保存国家励志奖学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjShxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String bdsyj = Base.chgNull(model.getBdsyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjlzjxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
						"xysh", "xyshyj", "bdsyj" }, new String[] { xysh,
						xyshyj, bdsyj }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取彩虹慈善助学金详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,jg,jtzz,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_chzxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "jg", "jtzz", "jtcy1_xm",
				"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
				"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
				"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "lxdh", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到彩虹慈善助学金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getChzxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='彩虹慈善助学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存彩虹慈善助学金申请信息，成功返回TRUE，反之返回FALSE saveChzxjrdSqxx ---- 保存彩虹慈善助学金认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjSqxx(ChzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isChzxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_chzxj", new String[] { "xn",
					"xh", "jg", "jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "lxdh", "sqly" }, new String[] {
					xn, xh, jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz, jtcy1_sr,
					jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr, jtcy3_xm, jtcy3_cw,
					jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw, jtcy4_gz, jtcy4_sr,
					jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr, lxdh, sqly },
					request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_chzxj", new String[] { "jg",
					"jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr",
					"jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm",
					"jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw",
					"jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
					"jtcy5_sr", "lxdh", "sqly", "sqsj", "xysh", "xyshyj",
					"xxsh", "xxshyj" }, new String[] { jg, jtzz, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, lxdh, sqly, now, "未审核", "", "未审核", "" },
					"xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断彩虹慈善助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isChzxjdatacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isChzxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_chzxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_chzxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取彩虹慈善助学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjSqb(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		stuList.put("xxmc", Base.xxmc);
		return stuList;
	}

	/**
	 * 删除彩虹慈善助学金信息 delChzxjxx ---- 删除彩虹慈善助学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delChzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_chzxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_chzxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改彩虹慈善助学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modChzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_chzxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_chzxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 彩虹慈善助学金查询表头 getChzxjTit ---- 彩虹慈善助学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 彩虹慈善助学金查询结果 getChzxjRes ---- 彩虹慈善助学金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getChzxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存彩虹慈善助学金审核信息，成功返回TRUE，反之返回FALSE saveChzxjShxx ---- 保存彩虹慈善助学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjShxx(ChzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isChzxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_chzxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_chzxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取慈善总会高校在校生助学金详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc," 
					 + "zydm,zymc,bjdm,bjmc,kndj,jg,jtzz,jtcy1_xm,jtcy1_cw,jtcy1_gz," 
					 + "jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw," 
					 + "jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm," 
					 + "jtcy5_cw,jtcy5_gz,jtcy5_sr,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zxjje from view_nblg_zxszxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "jg", "jtzz", "jtcy1_xm",
				"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
				"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
				"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "lxdh", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj","zxjje" };
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
	 * 获取慈善总汇助学金信息 getCszhzxj
	 * author 裘力俊
	 * data 2010-7-15
	 * 
	 */
	public List<Map<String,String>>getCszhzxj(StringBuffer strBuffer){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,nl,jtzz,sqly,xymc,nj,zymc,sfzh,zxjje from view_nblg_zxszxj"+strBuffer;
	    List knsrdList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm","xb","nl","jtzz","sqly","xymc","nj","zymc","sfzh","zxjje" });
	    return knsrdList;
	}
	

	/**
	 * 得到慈善总会高校在校生助学金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getZxszxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='慈善总会高校在校生助学金' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 获取彩虹慈善助学金信息 getChcszxj
	 * author 裘力俊
	 * data 2010-7-15
	 * 
	 */
	public List<Map<String,String>>getChcszxj(StringBuffer strBuffer){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,nl,jtzz,sqly,xymc,nj,zymc from view_nblg_chzxj "+strBuffer;
	    List knsrdList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm","xb","nl","jtzz","sqly","xymc","nj","zymc" });
	    return knsrdList;
	}
	
	
	/**
	 * 保存慈善总会高校在校生助学金申请信息，成功返回TRUE，反之返回FALSE saveZxszxjrdSqxx ----
	 * 保存慈善总会高校在校生助学金认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjSqxx(ZxszxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);		
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isZxszxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation
					.insert("nblg_zxszxj", new String[] { "xn", "xh", "jg",
							"jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
							"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz",
							"jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
							"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
							"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
							"jtcy5_sr", "lxdh", "sqly", "zxjje" }, new String[] { xn,
							xh, jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz,
							jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
							jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
							jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw,
							jtcy5_gz, jtcy5_sr, lxdh, sqly ,zxjje }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
					"jg", "jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "lxdh", "sqly", "zxjje", "sqsj", "xysh",
					"xyshyj", "xxsh", "xxshyj" },
					new String[] { jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz,
							jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
							jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
							jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw,
							jtcy5_gz, jtcy5_sr, lxdh, sqly, zxjje, now, "未审核", "",
							"未审核", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断慈善总会高校在校生助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isZxszxjdatacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isZxszxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_zxszxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_zxszxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取慈善总会高校在校生助学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjSqb(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		stuList.put("xxmc", Base.xxmc);
		return stuList;
	}

	/**
	 * 删除慈善总会高校在校生助学金信息 delZxszxjxx ---- 删除慈善总会高校在校生助学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZxszxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_zxszxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_zxszxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改慈善总会高校在校生助学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZxszxjxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_zxszxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_zxszxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 慈善总会高校在校生助学金查询表头 getZxszxjTit ---- 慈善总会高校在校生助学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxszxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 慈善总会高校在校生助学金查询结果 getZxszxjRes ---- 慈善总会高校在校生助学金结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxszxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存慈善总会高校在校生助学金审核信息，成功返回TRUE，反之返回FALSE saveZxszxjShxx ----
	 * 保存慈善总会高校在校生助学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjShxx(ZxszxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isZxszxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取国家助学贷款详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,jg,hjszd,jkzk,jtdh,yddh,jttxdz,yzbm,email,sqje,jzr1_xm,jzr1_xb,jzr1_sfzh,jzr1_gx,jzr1_xzz,jzr1_lxdh,jzr2_xm,jzr2_xb,jzr2_sfzh,jzr2_gx,jzr2_xzz,jzr2_lxdh,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjzxdk where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "xz", "nj", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "kndj", "jg", "hjszd", "jkzk",
				"jtdh", "yddh", "jttxdz", "yzbm", "email", "sqje", "jzr1_xm",
				"jzr1_xb", "jzr1_sfzh", "jzr1_gx", "jzr1_xzz", "jzr1_lxdh",
				"jzr2_xm", "jzr2_xb", "jzr2_sfzh", "jzr2_gx", "jzr2_xzz",
				"jzr2_lxdh", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到国家助学贷款申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='国家助学贷款' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存国家助学贷款申请信息，成功返回TRUE，反之返回FALSE saveGjzxdkrdSqxx ---- 保存国家助学贷款认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String hjszd = Base.chgNull(model.getHjszd(), "", 1);
		String jkzk = Base.chgNull(model.getJkzk(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String jttxdz = Base.chgNull(model.getJttxdz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String email = Base.chgNull(model.getEmail(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String jzr1_xm = Base.chgNull(model.getJzr1_xm(), "", 1);
		String jzr1_xb = Base.chgNull(model.getJzr1_xb(), "", 1);
		String jzr1_sfzh = Base.chgNull(model.getJzr1_sfzh(), "", 1);
		String jzr1_gx = Base.chgNull(model.getJzr1_gx(), "", 1);
		String jzr1_xzz = Base.chgNull(model.getJzr1_xzz(), "", 1);
		String jzr1_lxdh = Base.chgNull(model.getJzr1_lxdh(), "", 1);
		String jzr2_xm = Base.chgNull(model.getJzr2_xm(), "", 1);
		String jzr2_xb = Base.chgNull(model.getJzr2_xb(), "", 1);
		String jzr2_sfzh = Base.chgNull(model.getJzr2_sfzh(), "", 1);
		String jzr2_gx = Base.chgNull(model.getJzr2_gx(), "", 1);
		String jzr2_xzz = Base.chgNull(model.getJzr2_xzz(), "", 1);
		String jzr2_lxdh = Base.chgNull(model.getJzr2_lxdh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjzxdk",
					new String[] { "xn", "xh", "jg", "hjszd", "jkzk", "jtdh",
							"yddh", "jttxdz", "yzbm", "email", "sqje",
							"jzr1_xm", "jzr1_xb", "jzr1_sfzh", "jzr1_gx",
							"jzr1_xzz", "jzr1_lxdh", "jzr2_xm", "jzr2_xb",
							"jzr2_sfzh", "jzr2_gx", "jzr2_xzz", "jzr2_lxdh" },
					new String[] { xn, xh, jg, hjszd, jkzk, jtdh, yddh, jttxdz,
							yzbm, email, sqje, jzr1_xm, jzr1_xb, jzr1_sfzh,
							jzr1_gx, jzr1_xzz, jzr1_lxdh, jzr2_xm, jzr2_xb,
							jzr2_sfzh, jzr2_gx, jzr2_xzz, jzr2_lxdh }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
					"jg", "hjszd", "jkzk", "jtdh", "yddh", "jttxdz", "yzbm",
					"email", "sqje", "jzr1_xm", "jzr1_xb", "jzr1_sfzh",
					"jzr1_gx", "jzr1_xzz", "jzr1_lxdh", "jzr2_xm", "jzr2_xb",
					"jzr2_sfzh", "jzr2_gx", "jzr2_xzz", "jzr2_lxdh", "sqsj",
					"xysh", "xyshyj", "xxsh", "xxshyj" }, new String[] { jg,
					hjszd, jkzk, jtdh, yddh, jttxdz, yzbm, email, sqje,
					jzr1_xm, jzr1_xb, jzr1_sfzh, jzr1_gx, jzr1_xzz, jzr1_lxdh,
					jzr2_xm, jzr2_xb, jzr2_sfzh, jzr2_gx, jzr2_xzz, jzr2_lxdh,
					now, "未审核", "", "未审核", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断国家助学贷款是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isGjzxdkdatacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxdkDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjzxdk where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjzxdk where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取国家助学贷款申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("xz", Base.chgNull(model.getXz(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("hjszd", Base.chgNull(model.getHjszd(), "", 1));
		stuList.put("jkzk", Base.chgNull(model.getJkzk(), "", 1));
		stuList.put("jtdh", Base.chgNull(model.getJtdh(), "", 1));
		stuList.put("yddh", Base.chgNull(model.getYddh(), "", 1));
		stuList.put("jttxdz", Base.chgNull(model.getJttxdz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("email", Base.chgNull(model.getEmail(), "", 1));
		stuList.put("sqje", Base.chgNull(model.getSqje(), "", 1));
		stuList.put("jzr1_xm", Base.chgNull(model.getJzr1_xm(), "", 1));
		stuList.put("jzr1_xb", Base.chgNull(model.getJzr1_xb(), "", 1));
		stuList.put("jzr1_sfzh", Base.chgNull(model.getJzr1_sfzh(), "", 1));
		stuList.put("jzr1_gx", Base.chgNull(model.getJzr1_gx(), "", 1));
		stuList.put("jzr1_xzz", Base.chgNull(model.getJzr1_xzz(), "", 1));
		stuList.put("jzr1_lxdh", Base.chgNull(model.getJzr1_lxdh(), "", 1));
		stuList.put("jzr2_xm", Base.chgNull(model.getJzr2_xm(), "", 1));
		stuList.put("jzr2_xb", Base.chgNull(model.getJzr2_xb(), "", 1));
		stuList.put("jzr2_sfzh", Base.chgNull(model.getJzr2_sfzh(), "", 1));
		stuList.put("jzr2_gx", Base.chgNull(model.getJzr2_gx(), "", 1));
		stuList.put("jzr2_xzz", Base.chgNull(model.getJzr2_xzz(), "", 1));
		stuList.put("jzr2_lxdh", Base.chgNull(model.getJzr2_lxdh(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * 删除国家助学贷款信息 delGjzxdkxx ---- 删除国家助学贷款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_gjzxdk where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjzxdk where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改国家助学贷款审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_gjzxdk set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjzxdk set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 国家助学贷款查询表头 getGjzxdkTit ---- 国家助学贷款表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				Base.YXPZXY_KEY+"名称", "班级名称", "申请金额", "申请时间", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学贷款查询结果 getGjzxdkRes ---- 国家助学贷款结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存国家助学贷款审核信息，成功返回TRUE，反之返回FALSE saveGjzxdkShxx ---- 保存国家助学贷款审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjzxdkDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}
}
