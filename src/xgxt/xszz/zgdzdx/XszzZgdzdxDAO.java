package xgxt.xszz.zgdzdx;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.User;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;

/**
 * Title: 学生工作管理系统 Description: 中国地质大学学生资助DAO Copyright: Copyright (c) 2008
 * Company: zfsoft Author: 周觅 Version: 1.0 Time: 2008-10-09
 */
public class XszzZgdzdxDAO {
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
		String xq = queryModel.getXq();
		String xh = DealString.toGBK(queryModel.getXh());
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xz = queryModel.getXz();
		String tjdc = DealString.toGBK(queryModel.getTjdc());
		String xysh = DealString.toGBK(queryModel.getXysh());
		String xxsh = DealString.toGBK(queryModel.getXxsh());
		String xm = DealString.toGBK(queryModel.getXm());
		String sfzh = DealString.toGBK(queryModel.getSfzh());
		String hth = DealString.toGBK(queryModel.getHth());
		String sjfw1 = DealString.toGBK(queryModel.getSjfw1());
		String sjfw2 = DealString.toGBK(queryModel.getSjfw2());
		String shjg = DealString.toGBK(queryModel.getShjg());
		String hkxyqssj = DealString.toGBK(queryModel.getHkxyqssj());

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
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like '%");
			whereSql.append(xm);
			whereSql.append("%' ");
		}// end if
		if (!StringUtils.isNull(sfzh)) {
			whereSql.append(" and sfzh = ?");
			values.add(sfzh);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh like '%");
			whereSql.append(xh);
			whereSql.append("%' ");
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
		if (!StringUtils.isNull(tjdc)) {
			whereSql.append(" and tjdc = ?");
			values.add(tjdc);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}// end if
		if (!StringUtils.isNull(xz)) {
			whereSql.append(" and xz = ?");
			values.add(xz);
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}// end if
		if (!StringUtils.isNull(hkxyqssj)) {
			whereSql.append(" and hkxyqssj = ?");
			values.add(hkxyqssj);
		}// end if
		if (!StringUtils.isNull(hth)) {
			whereSql.append(" and HTBH like '%");
			whereSql.append(hth.toUpperCase());
			whereSql.append("%'");
		}// end if
		if (!StringUtils.isNull(sjfw1)) {
			whereSql.append(" and sqsj>=? ");
			values.add(sjfw1);
		}// end if
		if (!StringUtils.isNull(sjfw2)) {
			whereSql.append(" and sqsj<=? ");
			values.add(sjfw2);
		}// end if
		if (!StringUtils.isNull(shjg)) {
			if ("1".equalsIgnoreCase(shjg)) {
				whereSql.append(" and shjg = ?");
				values.add("未审核");
			} else if ("2".equalsIgnoreCase(shjg)) {
				whereSql.append(" and shjg = ?");
				values.add("通过");
			} else if ("3".equalsIgnoreCase(shjg)) {
				whereSql.append(" and shjg = ?");
				values.add("不通过");
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
	 * 导出数据 getExpDate ---- 款导出数据增加权限控制
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getExpDate(User user, QueryModel queryZxxsdkModel,
			String tabName, HttpServletRequest request) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		
		String condition = user.getQueryCondition();
		String sql = "select a.* from " + tabName + " a where 1=1 ";
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel, request);
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql + whereSql.toString() + condition,
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
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,");
		sql.append("a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,");
		sql.append("(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,");
		sql.append("(select (case b.rxny when null then '' else (case length(b.rxny) ");
		sql.append("when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')");
		sql.append("+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b ");
		sql.append("where b.xh=a.xh) byny,a.qsdh ssdh,(case a.xz when '3' then '专科' else '本科' end) xl ");
		sql.append("from view_stu_details a where a.xh = ?");
		
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny", "ssdh", "xl" };
		return dao.getMap(sql.toString(), new String[] {pkVal}, colList);
	}

	/**
	 * 获取家庭情况调查详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,nj,sfzh,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,rxqhk,sy,jtrks,sfgc,sfdq,sflszn,jt_xxtxdz,jt_yzbm,jt_lxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk,fqsfzh,mqsfzh,jtqnsr,rjnsr,sfncpkdq,sfczxgjt,sffmxcj,sfhzdjb,sfdqjt,sfgr,sfzrzh,sfjtrkd,sfqt,qtnr,pkyyxxsm,jzqzqk,xsrxqyhzzqk,qtqk,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,sh from view_zgdzdx_kns_jtqkdc where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "nj", "sfzh",
				"mzmc", "zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "rxqhk", "sy", "jtrks", "sfgc", "sfdq", "sflszn",
				"jt_xxtxdz", "jt_yzbm", "jt_lxdh", "jtcy1_xm", "jtcy1_nl",
				"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
				"jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr",
				"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
				"jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk", "fqsfzh", "mqsfzh",
				"jtqnsr", "rjnsr", "sfncpkdq", "sfczxgjt", "sffmxcj",
				"sfhzdjb", "sfdqjt", "sfgr", "sfzrzh", "sfjtrkd", "sfqt",
				"qtnr", "pkyyxxsm", "jzqzqk", "xsrxqyhzzqk", "qtqk",
				"mzbm_xxtxdz", "mzbm_yzbm", "mzbm_lxdh", "sqsj", "sh" };
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
	 * 得到家庭情况调查申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getJtqkdcSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_KNS_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='家庭情况调查' and b.xh=? ";// ,nd
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
	 * 保存家庭情况调查申请信息，成功返回TRUE，反之返回FALSE saveJtqkdcSqxx ---- 保存家庭情况调查申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcSqxx(JtqkdcModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String rxqhk = Base.chgNull(model.getRxqhk(), "", 1);
		String sy = Base.chgNull(model.getSy(), "", 1);
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);
		String sfgc = Base.chgNull(model.getSfgc(), "", 1);
		String sfdq = Base.chgNull(model.getSfdq(), "", 1);
		String sflszn = Base.chgNull(model.getSflszn(), "", 1);
		String jt_xxtxdz = Base.chgNull(model.getJt_xxtxdz(), "", 1);
		String jt_yzbm = Base.chgNull(model.getJt_yzbm(), "", 1);
		String jt_lxdh = Base.chgNull(model.getJt_lxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(model.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(model.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(model.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(model.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(model.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(model.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(model.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(model.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(model.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(model.getJtcy5_jkzk(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String jtqnsr = Base.chgNull(model.getJtqnsr(), "", 1);
		String rjnsr = Base.chgNull(model.getRjnsr(), "", 1);
		String sfncpkdq = Base.chgNull(model.getSfncpkdq(), "", 1);
		String sfczxgjt = Base.chgNull(model.getSfczxgjt(), "", 1);
		String sffmxcj = Base.chgNull(model.getSffmxcj(), "", 1);
		String sfhzdjb = Base.chgNull(model.getSfhzdjb(), "", 1);
		String sfdqjt = Base.chgNull(model.getSfdqjt(), "", 1);
		String sfgr = Base.chgNull(model.getSfgr(), "", 1);
		String sfzrzh = Base.chgNull(model.getSfzrzh(), "", 1);
		String sfjtrkd = Base.chgNull(model.getSfjtrkd(), "", 1);
		String sfqt = Base.chgNull(model.getSfqt(), "", 1);
		String qtnr = Base.chgNull(model.getQtnr(), "", 1);
		String pkyyxxsm = Base.chgNull(model.getPkyyxxsm(), "", 1);
		String jzqzqk = Base.chgNull(model.getJzqzqk(), "", 1);
		String xsrxqyhzzqk = Base.chgNull(model.getXsrxqyhzzqk(), "", 1);
		String qtqk = Base.chgNull(model.getQtqk(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(model.getMzbm_xxtxdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(model.getMzbm_yzbm(), "", 1);
		String mzbm_lxdh = Base.chgNull(model.getMzbm_lxdh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");
		model.setSqsj(now);

		String sHave = isJtqkdcDataCf(xh, xn);

		HashMap<String, String> tHs = new HashMap<String, String>();
		tHs = getKnsrdxx(xn + xh);

		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zgdzdx_kns_jtqkdc", new String[] {
					"xn", "xh", "rxqhk", "sy", "jtrks", "sfgc", "sfdq",
					"sflszn", "jt_xxtxdz", "jt_yzbm", "jt_lxdh", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
					"jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr",
					"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
					"jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
					"jtcy5_sr", "jtcy5_jkzk", "fqsfzh", "mqsfzh", "jtqnsr",
					"rjnsr", "sfncpkdq", "sfczxgjt", "sffmxcj", "sfhzdjb",
					"sfdqjt", "sfgr", "sfzrzh", "sfjtrkd", "sfqt", "qtnr",
					"pkyyxxsm", "jzqzqk", "xsrxqyhzzqk", "qtqk", "mzbm_xxtxdz",
					"mzbm_yzbm", "mzbm_lxdh", "sqsj" }, new String[] { xn, xh,
					rxqhk, sy, jtrks, sfgc, sfdq, sflszn, jt_xxtxdz, jt_yzbm,
					jt_lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
					jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr, jtcy2_jkzk,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
					jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
					jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_sr,
					jtcy5_jkzk, fqsfzh, mqsfzh, jtqnsr, rjnsr, sfncpkdq,
					sfczxgjt, sffmxcj, sfhzdjb, sfdqjt, sfgr, sfzrzh, sfjtrkd,
					sfqt, qtnr, pkyyxxsm, jzqzqk, xsrxqyhzzqk, qtqk,
					mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, now }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			StandardOperation.update("zgdzdx_kns_pksrd", new String[] { "sqsj",
					"tjdc", "csly", "xysh", "xxsh" }, new String[] { now,
					"未审核", "", "未审核", "未审核" }, "xn||xh", xn + xh, request);
			bFlag = StandardOperation.update("zgdzdx_kns_jtqkdc", new String[] {
					"rxqhk", "sy", "jtrks", "sfgc", "sfdq", "sflszn",
					"jt_xxtxdz", "jt_yzbm", "jt_lxdh", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr",
					"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
					"jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr",
					"jtcy5_jkzk", "fqsfzh", "mqsfzh", "jtqnsr", "rjnsr",
					"sfncpkdq", "sfczxgjt", "sffmxcj", "sfhzdjb", "sfdqjt",
					"sfgr", "sfzrzh", "sfjtrkd", "sfqt", "qtnr", "pkyyxxsm",
					"jzqzqk", "xsrxqyhzzqk", "qtqk", "mzbm_xxtxdz",
					"mzbm_yzbm", "mzbm_lxdh", "sqsj", "sh" }, new String[] {
					rxqhk, sy, jtrks, sfgc, sfdq, sflszn, jt_xxtxdz, jt_yzbm,
					jt_lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
					jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr, jtcy2_jkzk,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
					jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
					jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_sr,
					jtcy5_jkzk, fqsfzh, mqsfzh, jtqnsr, rjnsr, sfncpkdq,
					sfczxgjt, sffmxcj, sfhzdjb, sfdqjt, sfgr, sfzrzh, sfjtrkd,
					sfqt, qtnr, pkyyxxsm, jzqzqk, xsrxqyhzzqk, qtqk,
					mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, now, "未审核" }, "xn||xh",
					xn + xh, request);
		} else if ("2".equalsIgnoreCase(sHave)
				&& (tHs.size() != 0 && (tHs.get("xxsh")
						.equalsIgnoreCase("一般困难")
						|| tHs.get("xxsh").equalsIgnoreCase("困难") || tHs.get(
						"xxsh").equalsIgnoreCase("特殊困难")))) {
			StandardOperation.update("zgdzdx_kns_pksrd", new String[] { "sqsj",
					"tjdc", "csly", "xysh", "xxsh" }, new String[] { now,
					"未审核", "", "未审核", "未审核" }, "xn||xh", xn + xh, request);
			bFlag = StandardOperation.update("zgdzdx_kns_jtqkdc", new String[] {
					"rxqhk", "sy", "jtrks", "sfgc", "sfdq", "sflszn",
					"jt_xxtxdz", "jt_yzbm", "jt_lxdh", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr",
					"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
					"jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr",
					"jtcy5_jkzk", "fqsfzh", "mqsfzh", "jtqnsr", "rjnsr",
					"sfncpkdq", "sfczxgjt", "sffmxcj", "sfhzdjb", "sfdqjt",
					"sfgr", "sfzrzh", "sfjtrkd", "sfqt", "qtnr", "pkyyxxsm",
					"jzqzqk", "xsrxqyhzzqk", "qtqk", "mzbm_xxtxdz",
					"mzbm_yzbm", "mzbm_lxdh", "sqsj", "sh" }, new String[] {
					rxqhk, sy, jtrks, sfgc, sfdq, sflszn, jt_xxtxdz, jt_yzbm,
					jt_lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
					jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr, jtcy2_jkzk,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
					jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
					jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_sr,
					jtcy5_jkzk, fqsfzh, mqsfzh, jtqnsr, rjnsr, sfncpkdq,
					sfczxgjt, sffmxcj, sfhzdjb, sfdqjt, sfgr, sfzrzh, sfjtrkd,
					sfqt, qtnr, pkyyxxsm, jzqzqk, xsrxqyhzzqk, qtqk,
					mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, now, "未审核" }, "xn||xh",
					xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断家庭情况调查是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isJktqkdcdatacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isJtqkdcDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgdzdx_kns_jtqkdc where xh = ? and xn = ? and sh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0") && !Base.isNull(num)) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgdzdx_kns_jtqkdc where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0") && !Base.isNull(num)) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取家庭情况调查申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcSqb(JtqkdcModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("rxqhk", Base.chgNull(model.getRxqhk(), "", 1));
		stuList.put("sy", Base.chgNull(model.getSy(), "", 1));
		stuList.put("jtrks", Base.chgNull(model.getJtrks(), "", 1));
		stuList.put("sfgc", Base.chgNull(model.getSfgc(), "", 1));
		stuList.put("sfdq", Base.chgNull(model.getSfdq(), "", 1));
		stuList.put("sflszn", Base.chgNull(model.getSflszn(), "", 1));
		stuList.put("jt_xxtxdz", Base.chgNull(model.getJt_xxtxdz(), "", 1));
		stuList.put("jt_yzbm", Base.chgNull(model.getJt_yzbm(), "", 1));
		stuList.put("jt_lxdh", Base.chgNull(model.getJt_lxdh(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(model.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(model.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(model.getJtcy1_gzdw(), "", 1));
		stuList.put("jtcy1_zy", Base.chgNull(model.getJtcy1_zy(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy1_jkzk", Base.chgNull(model.getJtcy1_jkzk(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(model.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(model.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(model.getJtcy2_gzdw(), "", 1));
		stuList.put("jtcy2_zy", Base.chgNull(model.getJtcy2_zy(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy2_jkzk", Base.chgNull(model.getJtcy2_jkzk(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(model.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(model.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(model.getJtcy3_gzdw(), "", 1));
		stuList.put("jtcy3_zy", Base.chgNull(model.getJtcy3_zy(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy3_jkzk", Base.chgNull(model.getJtcy3_jkzk(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(model.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(model.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(model.getJtcy4_gzdw(), "", 1));
		stuList.put("jtcy4_zy", Base.chgNull(model.getJtcy4_zy(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy4_jkzk", Base.chgNull(model.getJtcy4_jkzk(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(model.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(model.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_gzdw", Base.chgNull(model.getJtcy5_gzdw(), "", 1));
		stuList.put("jtcy5_zy", Base.chgNull(model.getJtcy5_zy(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("jtcy5_jkzk", Base.chgNull(model.getJtcy5_jkzk(), "", 1));
		stuList.put("fqsfzh", Base.chgNull(model.getFqsfzh(), "", 1));
		stuList.put("mqsfzh", Base.chgNull(model.getMqsfzh(), "", 1));
		stuList.put("jtqnsr", Base.chgNull(model.getJtqnsr(), "", 1));
		stuList.put("rjnsr", Base.chgNull(model.getRjnsr(), "", 1));
		stuList.put("sfncpkdq", Base.chgNull(model.getSfncpkdq(), "", 1));
		stuList.put("sfczxgjt", Base.chgNull(model.getSfczxgjt(), "", 1));
		stuList.put("sffmxcj", Base.chgNull(model.getSffmxcj(), "", 1));
		stuList.put("sfhzdjb", Base.chgNull(model.getSfhzdjb(), "", 1));
		stuList.put("sfdqjt", Base.chgNull(model.getSfdqjt(), "", 1));
		stuList.put("sfgr", Base.chgNull(model.getSfgr(), "", 1));
		stuList.put("sfzrzh", Base.chgNull(model.getSfzrzh(), "", 1));
		stuList.put("sfjtrkd", Base.chgNull(model.getSfjtrkd(), "", 1));
		stuList.put("sfqt", Base.chgNull(model.getSfqt(), "", 1));
		stuList.put("qtnr", Base.chgNull(model.getQtnr(), "", 1));
		stuList.put("pkyyxxsm", Base.chgNull(model.getPkyyxxsm(), "", 1));
		stuList.put("jzqzqk", Base.chgNull(model.getJzqzqk(), "", 1));
		stuList.put("xsrxqyhzzqk", Base.chgNull(model.getXsrxqyhzzqk(), "", 1));
		stuList.put("qtqk", Base.chgNull(model.getQtqk(), "", 1));
		stuList.put("mzbm_xxtxdz", Base.chgNull(model.getMzbm_xxtxdz(), "", 1));
		stuList.put("mzbm_yzbm", Base.chgNull(model.getMzbm_yzbm(), "", 1));
		stuList.put("mzbm_lxdh", Base.chgNull(model.getMzbm_lxdh(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("sh", Base.chgNull(model.getSh(), "", 1));

		return stuList;
	}

	/**
	 * 删除家庭情况调查信息 delJtqkdcxx ---- 删除家庭情况调查信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete zgdzdx_kns_jtqkdc where xn||xh='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改家庭情况调查审核结果 modJtqkdcxx ---- 批量修改家庭情况调查审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modJtqkdcxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update zgdzdx_kns_jtqkdc set sh='"+shjg+"' where xn||xh='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 家庭情况调查查询表头 Jtqkdctit ---- 家庭情况调查表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "sh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				"性别", "身份证号", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 家庭情况调查查询结果 Jtqkdcres ---- 家庭情况调查结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select rownum r,(case when nvl(sh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,bjmc,sqsj,sh from view_zgdzdx_kns_jtqkdc where 1=1";
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "sh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		
		resList = CommonQueryDAO.commonQuery(sql, whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList, queryModel);
		
		return resList;
	}

	/**
	 * 保存家庭情况调查审核信息，成功返回TRUE，反之返回FALSE saveJtqkdcShxx ---- 保存家庭情况调查审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcShxx(JtqkdcModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String sh = Base.chgNull(model.getSh(), "", 1);

		bFlag = StandardOperation.update("zgdzdx_kns_jtqkdc",
				new String[] { "sh" }, new String[] { sh }, "xn||xh", xn + xh,
				request);

		return bFlag;
	}

	/**
	 * 获取困难生认定详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,nj,sfzh,mzmc,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrjnsr,zxlxdh,jtdz,ssxs,sfdzzzq,xscssqly,sqsj,jtqkdcshjg,tjdc,csly,xysh,xxsh from view_zgdzdx_kns_pksrd where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "nj", "sfzh",
				"mzmc", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"jtrjnsr", "zxlxdh", "jtdz", "ssxs", "sfdzzzq", "xscssqly",
				"sqsj", "jtqkdcshjg", "tjdc", "csly", "xysh", "xxsh" };
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
	 * 得到困难生认定申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getKnsrdSqQx(String sUserType, String userDep, String xh,
			String xn) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		String num = "1";
		if (null != xh && !"".equalsIgnoreCase(xh)) {
			num = dao
					.getOneRs(
							"select count(*) num from zgdzdx_kns_jtqkdc where xh=? and xn=?",
							new String[] { xh, xn }, "num");
		}

		if (!num.equals("0")) {
			if (StringUtils.isEqual(sUserType, "stu")
					|| StringUtils.isEqual(sUserType, "student")) {
				String[] jxjksjssj = null;

				String sql = "select a.kssj,a.jssj from ZGDZDX_KNS_SJB a,"
						+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='困难生申请时间' and b.xh=? ";// ,nd
				jxjksjssj = dao.getOneRs(sql, new String[] { xh },
						new String[] { "kssj", "jssj" });
				if (jxjksjssj != null
						&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
						&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
					sfksq = "1";
				}
			} else {
				sfksq = "1";
			}
		}
		return sfksq;
	}

	/**
	 * 得到困难生认定审核时间
	 * 
	 * @param userDep
	 * @return String[]
	 * @throws Exception
	 */
	public String[] getKnsrdShsj(String userDep) throws Exception {
		String[] shsj = null;

		String sql = "select a.kssj,a.jssj from ZGDZDX_KNS_SJB a where a.xmmc='困难生审核时间' and a.xydm=? ";// ,nd
		shsj = dao.getOneRs(sql, new String[] { userDep }, new String[] {
				"kssj", "jssj" });
		return shsj;
	}

	/**
	 * 得到困难生认定审核权限
	 * 
	 * @param userDep
	 * @return 1 可审核；-1 不可审核
	 * @throws Exception
	 */
	public String getKnsrdShQx(String userType, String userDep)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		String[] shsj = null;

		if (userType.equalsIgnoreCase("xx")
				|| userType.equalsIgnoreCase("admin")) {
			sfksq = "1";
		}

		String sql = "select a.kssj,a.jssj from ZGDZDX_KNS_SJB a where a.xmmc='困难生审核时间' and a.xydm=? ";// ,nd
		shsj = dao.getOneRs(sql, new String[] { userDep }, new String[] {
				"kssj", "jssj" });
		if (shsj != null && shsj[0].compareToIgnoreCase(rightNow) <= 0
				&& shsj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * 保存困难生认定申请信息，成功返回TRUE，反之返回FALSE saveKnsrdSqxx ---- 保存困难生认定申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdSqxx(KnsrdModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(model.getZxlxdh(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String ssxs = Base.chgNull(model.getSsxs(), "", 1);
		String sfdzzzq = Base.chgNull(model.getSfdzzzq(), "", 1);
		String xscssqly = Base.chgNull(model.getXscssqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");
		model.setSqsj(now);

		String sHave = isKnsrdDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zgdzdx_kns_pksrd", new String[] {
					"xn", "xh", "jtrjnsr", "zxlxdh", "jtdz", "ssxs", "sfdzzzq",
					"xscssqly" }, new String[] { xn, xh, jtrjnsr, zxlxdh, jtdz,
					ssxs, sfdzzzq, xscssqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zgdzdx_kns_pksrd", new String[] {
					"jtrjnsr", "zxlxdh", "jtdz", "ssxs", "sfdzzzq", "xscssqly",
					"sqsj", "tjdc", "csly", "xysh", "xxsh" }, new String[] {
					jtrjnsr, zxlxdh, jtdz, ssxs, sfdzzzq, xscssqly, now, "未审核",
					"", "未审核", "未审核" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断困难生认定是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsrddatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnsrdDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgdzdx_kns_pksrd where xh = ? and xn = ? and xxsh in ('一般困难','困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgdzdx_kns_pksrd where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取困难生认定申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdSqb(KnsrdModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("jtrjnsr", Base.chgNull(model.getJtrjnsr(), "", 1));
		stuList.put("zxlxdh", Base.chgNull(model.getZxlxdh(), "", 1));
		stuList.put("jtdz", Base.chgNull(model.getJtdz(), "", 1));
		stuList.put("ssxs", Base.chgNull(model.getSsxs(), "", 1));
		stuList.put("sfdzzzq", Base.chgNull(model.getSfdzzzq(), "", 1));
		stuList.put("xscssqly", Base.chgNull(model.getXscssqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("csly", Base.chgNull(model.getCsly(), "", 1));

		String tjdc = Base.chgNull(model.getTjdc(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);

		if ("".equalsIgnoreCase(tjdc)) {
			tjdc = "未审核";
		}
		if ("".equalsIgnoreCase(xysh)) {
			xysh = "未审核";
		}
		if ("".equalsIgnoreCase(xxsh)) {
			xxsh = "未审核";
		}

		if (tjdc.equalsIgnoreCase(xysh)) {
			stuList.put("tj_xy", "1");
		} else {
			stuList.put("tj_xy", "0");
		}
		if (xysh.equalsIgnoreCase(xxsh)) {
			stuList.put("xy_xx", "1");
		} else {
			stuList.put("xy_xx", "0");
		}

		stuList.put("tjdc", tjdc);
		stuList.put("xysh", xysh);
		stuList.put("xxsh", xxsh);
		return stuList;
	}

	/**
	 * 删除困难生认定信息 delKnsrdxx ---- 删除困难生认定信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsrdxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zgdzdx_kns_pksrd where xn||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete zgdzdx_kns_pksrd where xn||xh='"+pkT[i]+"' and xxsh not in ('困难','一般困难','特殊困难')";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 删除展期协议信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZqxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zgdzdx_zqxy where xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete zgdzdx_zqxy where xh='"+pkT[i]+"' and shjg<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 将信息转到历史信息库
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void addGjzxdkLsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*4];
		int j = 0;
		for (int i = 1; i < pkT.length; i++) {
			String num = dao.getOneRs("select count(*) num from zgdzdx_gjzxdk_ls where xh=?", new String[]{pkT[i]}, "num");
			if (!"0".equalsIgnoreCase(num)){
				StandardOperation.delete("zgdzdx_gjzxdk_ls", "xh", pkT[i], request);
			}
			String sql = "select xh,xm,xb,nj,xz,xl,sfzh,rxny,byny,sfby,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,dkll,dkqxy,jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,sqsj,htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,emailqq,lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,qyjjzk,fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,fk_xn2_sffk,fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,fk_xn4_sffk,fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,fkzje,dkye,sftxzlqrs,sfqdhkxy,hkxyqssj,jkrhkqk,jkrzhyyhlxsj,hkczh,hkzhye,hkqzsj,hkfs,zqsj,zqqx,zqlv,zqje,zqszdw,jkrtqlxze,jkrtqlxsj,jkrtqbjze,jkrtqbjsj,sftqhk,tqhkje,txfs,ytxcs,zjyctxsj,khh from view_zdgzgx_gjzxdk where xh=?";
			String[] sT = dao.getOneRs(sql, new String[] { pkT[i] },
					new String[] { "xh", "xm", "xb", "nj", "xz", "xl", "sfzh",
							"rxny", "byny", "sfby", "mzmc", "zzmmmc", "xydm",
							"xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq",
							"ssdh", "sqdkje", "dkqx", "dkll", "dkqxy",
							"jtxxzz", "jtyb", "jtdh", "fqxm", "fqzy", "fqsfzh",
							"mqxm", "mqzy", "mqsfzh", "jtysr", "sqsj", "htbh",
							"hyzk", "pomc", "polxdh", "gzdw", "dwdh", "yddh",
							"dwdz", "dwyb", "gzhysr", "emailqq", "lxrxm",
							"lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
							"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je",
							"fk_xn1_tksj", "fk_xn1_sffk", "fk_xn2_je",
							"fk_xn2_tksj", "fk_xn2_sffk", "fk_xn3_je",
							"fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
							"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je",
							"fk_xn5_tksj", "fk_xn5_sffk", "fkzje", "dkye",
							"sftxzlqrs", "sfqdhkxy", "hkxyqssj", "jkrhkqk",
							"jkrzhyyhlxsj", "hkczh", "hkzhye", "hkqzsj",
							"hkfs", "zqsj", "zqqx", "zqlv", "zqje", "zqszdw",
							"jkrtqlxze", "jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj",
							"sftqhk", "tqhkje", "txfs", "ytxcs", "zjyctxsj",
							"khh" });
			if (null != sT) {
				StringBuffer sb = new StringBuffer("insert into zgdzdx_gjzxdk_ls(xh,xm,xb,nj,xz,xl,sfzh,rxny,byny,sfby,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,dkll,dkqxy,jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,sqsj,htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,emailqq,lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,qyjjzk,fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,fk_xn2_sffk,fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,fk_xn4_sffk,fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,fkzje,dkye,sftxzlqrs,sfqdhkxy,hkxyqssj,jkrhkqk,jkrzhyyhlxsj,hkczh,hkzhye,hkqzsj,hkfs,zqsj,zqqx,zqlv,zqje,zqszdw,jkrtqlxze,jkrtqlxsj,jkrtqbjze,jkrtqbjsj,sftqhk,tqhkje,txfs,ytxcs,zjyctxsj,khh) values('");
				int x = 0;
				for (; x < sT.length-1; x++){
					sb.append(sT[x] == null ? "" : sT[x]);
					sb.append("', '");
				}
				sb.append(sT[x]);
				sb.append("')");
				sqlT[j] = sb.toString();
				j++;
				sqlT[j] = "delete zgdzdx_hkjzqhhkxyxx where xh='"+pkT[i]+"'";
				j++;
				sqlT[j] = "delete zgdzdx_zqxy where xh='"+pkT[i]+"'";
				j++;
				sqlT[j] = "delete zdgzgx_gjzxdk where xh='"+pkT[i]+"'";
				j++;
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 删除还款协议信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delHkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete zgdzdx_hkjzqhhkxyxx where xh='"+pkT[i]+"' and xz='1'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 删除展期后还款协议信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZqhhkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete zgdzdx_hkjzqhhkxyxx where xh='"+pkT[i]+"' and xz='2'";
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改困难生认定审核结果 modKnsrdxx ---- 批量修改困难生认定审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrdxx(String cbVal, String shType, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				if ("3".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgdzdx_kns_pksrd set xxsh='"+shjg+"' where xn||xh='"+pkT[i]+"'";
				} else if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgdzdx_kns_pksrd set xysh='"+shjg+"' where xn||xh='"+pkT[i]+"'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgdzdx_kns_pksrd set tjdc='"+shjg+"' where xn||xh='"+pkT[i]+"'";
				}
			} else {
				if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgdzdx_kns_pksrd set xysh='"+shjg+"' where xn||xh='"+pkT[i]+"' and xxsh='未审核'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgdzdx_kns_pksrd set tjdc='"+shjg+"' where xn||xh='"+pkT[i]+"' and xxsh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 困难生认定查询表头 Knsrdtit ---- 困难生认定表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrdTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "jtqkdcshjg", "tjdc", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				"身份证号", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "家庭情况调查审核结果", "推荐档次", Base.YXPZXY_KEY+"审核",
				"学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 展期协议查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqxyTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "htbh", "dkqx", "dkye", "zqnx", "zqrq", "shjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", Base.YXPZXY_KEY+"名称",
				"班级名称", "合同编号", "贷款期限", "贷款余额", "展期年限", "展期后日期", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 还款协议查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getHkxyTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "sfby", "htbh", "dkqx", "hkczh", "sqsj", "xyshjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", Base.YXPZXY_KEY+"名称",
				"班级名称", "是否毕业", "合同编号", "贷款期限", "还款帐号", "申请时间", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 展期后还款协议查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqhhkxyTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "sfby", "htbh", "hkczh", "zqnx", "zqhhkrq", "sqsj", "xyshjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", Base.YXPZXY_KEY+"名称",
				"班级名称", "是否毕业", "合同编号", "还款帐号", "展期年限", "展期后还款日期", "申请时间", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 困难生认定查询结果 getKnsrdRes ---- 困难生认定结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrdRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select rownum r,(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,jtqkdcshjg,tjdc,xysh,xxsh from view_zgdzdx_kns_pksrd where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select rownum r,(case when nvl(xysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,jtqkdcshjg,tjdc,xysh,xxsh from view_zgdzdx_kns_pksrd where jtqkdcshjg='通过'";
			} else {
				sql = "select rownum r,(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,jtqkdcshjg,tjdc,xysh,xxsh from view_zgdzdx_kns_pksrd where jtqkdcshjg='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "jtqkdcshjg", "tjdc", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = CommonQueryDAO.commonQuery(sql, whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList, queryModel);
		return resList;
	}

	/**
	 * 展期协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqxyRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(queryModel, request);
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,case when b.sftqhk = '是' and b.fkzje ");
		sql.append(">0 and b.tqhkje>0 then b.fkzje - b.tqhkje ");
		sql.append("else 0 end dkye from (select (case when nvl(shjg,'未审核')='通过' ");
		sql.append("then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,xymc,bjmc,htbh,");
		sql.append("dkqx,zqnx,zqrq,shjg from VIEW_ZGDZDX_ZQXY where 1=1 ");
		sql.append(whereSql);
		sql.append(") a  left join ");
		sql.append("(select xh, fkzje, sftqhk, tqhkje from view_zdgzgx_gjzxdk) b on a.xh = b.xh");
		
		
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "htbh", "dkqx", "dkye", "zqnx", "zqrq", "shjg" };
		resList = dao.rsToVator(sql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 还款协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getHkxyRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,xymc,bjmc,sfby,htbh,dkqx,hkczh,sqsj,xxsh xyshjg from view_zgdzdx_hkjzqhhkxyxx where xz='1'";
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "sfby", "htbh", "dkqx", "hkczh", "sqsj", "xyshjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 展期后还款协议查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqhhkxyRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,xymc,bjmc,sfby,htbh,hkczh,zqnx,zqhhkrq,sqsj,xxsh xyshjg from view_zgdzdx_hkjzqhhkxyxx where xz='2'";
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
				"bjmc", "sfby", "htbh", "hkczh", "zqnx", "zqhhkrq", "sqsj", "xyshjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存困难生认定审核信息，成功返回TRUE，反之返回FALSE saveKnsrdShxx ---- 保存困难生认定审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdShxx(KnsrdModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String tjdc = Base.chgNull(model.getTjdc(), "", 1);
		String csly = Base.chgNull(model.getCsly(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String sHave = isKnsrdDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zgdzdx_kns_pksrd", new String[] {
					"tjdc", "csly", "xysh", "xxsh" }, new String[] { tjdc,
					csly, xysh, xxsh }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zgdzdx_kns_pksrd",
						new String[] { "tjdc", "csly", "xysh" }, new String[] {
								tjdc, csly, xysh }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 得到已有合同编号
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getHtbh(String xydm)
			throws Exception {
		String sql = "select zxhth,zdhth from zgdzdx_htbhfwb where zdhth<>'0000' and xydm<>? order by zxhth";
		return dao.getArrayList(sql, new String[] { xydm }, new String[] {
				"zxhth", "zdhth" });
	}

	/**
	 * 删除生源地贷款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delSyddk(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete zgdzdx_syddk where nd||xh='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 生源地贷款查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm", "xymc",
				"zymc", "bjmc", "bjdd", "lxdh", "je", "sfksqgjzxdk", "bz" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名", Base.YXPZXY_KEY+"名称",
				"专业名称", "行政班号", "办理地点", "联系电话", "金额", "是否可申请国家助学贷款", "备注" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 生源地贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select nd||xh pk,nd,xh,xm,xymc,zymc,bjmc,bldd,lxdh,je,sfksqgjzxdk,bz from view_zgdzdx_syddk where 1=1";
		String[] colList = new String[] { "pk", "nd", "xh", "xm", "xymc",
				"zymc", "bjmc", "bldd", "lxdh", "je", "sfksqgjzxdk", "bz" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 获取国家助学贷款详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb,nj,xz,xl,sfzh,rxny,byny,sfby,mzmc,zzmmmc,");
		/*还款起止时间*/
		sql.append("case when dkqx is not null  then ");
		sql.append("to_char(to_number(substr(to_char(to_date(substr(dkqx,0,8),'yyyymmdd')+1,'yyyymmdd'),0,4))+2)");
		sql.append("||substr(to_char(to_date(substr(dkqx,0,8),'yyyymmdd')+1,'yyyymmdd'),5,4)");
		sql.append("||substr(dkqx,9,10) end hkqzsj,");
		/*展期期限*/
		sql.append("case when dkqx is not null and (select zqnx from zgdzdx_zqxy where xh=a.xh) ");
		sql.append("is not null then to_char(to_number(substr(dkqx,0,4))+2+(select zqnx from zgdzdx_zqxy where xh=a.xh))");
		sql.append("||substr(dkqx,5,4)||'-'||to_char(to_number(substr(dkqx,10,4))+(select zqnx from zgdzdx_zqxy where xh=a.xh))");
		sql.append("||substr(dkqx,14,4) end zqqx,");
		
		sql.append("(select zqnx from zgdzdx_zqxy where xh=a.xh) zqnx,");
		sql.append("(select shjg from zgdzdx_zqxy where xh=a.xh) zqshjg,");
		sql.append("xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,dkll,dkqxy,");
		sql.append("jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,sqsj,");
		sql.append("shjg,shyj,htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,");
		sql.append("emailqq,lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,");
		sql.append("qyjjzk,fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,");
		sql.append("fk_xn2_sffk,fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,");
		sql.append("fk_xn4_sffk,fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,fkzje,dkye,sfqdhkxy,");
		sql.append("hkxyqssj,jkrhkqk,jkrzhyyhlxsj,hkczh,hkzhye,zqlv,zqje,");
		sql.append("zqszdw,jkrtqlxze,jkrtqlxsj,jkrtqbjze,jkrtqbjsj,sftqhk,tqhkje,txfs,");
		sql.append("ytxcs,zjyctxsj,sftxzlqrs,khh,hkfs from view_zdgzgx_gjzxdk a where xh = ?");

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xz", "xl",
				"sfzh", "rxny", "byny", "sfby", "mzmc", "zzmmmc", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", "ssdh",
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "shjg", "shyj", "htbh", "hyzk", "pomc", "polxdh",
				"gzdw", "dwdh", "yddh", "dwdz", "dwyb", "gzhysr", "emailqq",
				"lxrxm", "lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
				"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je", "fk_xn1_tksj",
				"fk_xn1_sffk", "fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk",
				"fk_xn3_je", "fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
				"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj",
				"fk_xn5_sffk", "fkzje", "dkye", "sfqdhkxy", "hkxyqssj",
				"jkrhkqk", "jkrzhyyhlxsj", "hkczh", "hkzhye", "zqqx",
				"zqlv", "zqje", "zqszdw", "jkrtqlxze", "jkrtqlxsj",
				"jkrtqbjze", "jkrtqbjsj", "sftqhk", "tqhkje", "txfs", "ytxcs",
				"zjyctxsj", "sftxzlqrs", "khh", "hkqzsj", "hkfs","zqnx","zqshjg" };
		
		return dao.getMap(sql.toString(), new String[] { pkVal }, colList);
	}
	
	/**
	 * 获取国家助学贷款历史详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdklsxx(String pkVal) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb,nj,xz,xl,sfzh,rxny,byny,sfby,mzmc,zzmmmc,");
		sql.append("xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,dkll,dkqxy,");
		sql.append("jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,sqsj,");
		sql.append("htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,emailqq,");
		sql.append("lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,qyjjzk,");
		sql.append("fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,fk_xn2_sffk,");
		sql.append("fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,fk_xn4_sffk,");
		sql.append("fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,fkzje,dkye,sftxzlqrs,sfqdhkxy,");
		sql.append("hkxyqssj,jkrhkqk,jkrzhyyhlxsj,hkczh,hkzhye,hkqzsj,hkfs,zqsj,");
		sql.append("zqqx,zqlv,zqje,zqszdw,jkrtqlxze,jkrtqlxsj,jkrtqbjze,jkrtqbjsj,sftqhk,");
		sql.append("tqhkje,txfs,ytxcs,zjyctxsj,khh from zgdzdx_gjzxdk_ls a where xh = ?");
		
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xz", "xl",
				"sfzh", "rxny", "byny", "sfby", "mzmc", "zzmmmc", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", "ssdh",
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "htbh", "hyzk", "pomc", "polxdh", "gzdw", "dwdh",
				"yddh", "dwdz", "dwyb", "gzhysr", "emailqq", "lxrxm", "lxrxb",
				"lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz", "bz", "sfwfkyhddjm",
				"qyjjzk", "fk_xn1_je", "fk_xn1_tksj", "fk_xn1_sffk",
				"fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk", "fk_xn3_je",
				"fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je", "fk_xn4_tksj",
				"fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj", "fk_xn5_sffk",
				"fkzje", "dkye", "sftxzlqrs", "sfqdhkxy", "hkxyqssj",
				"jkrhkqk", "jkrzhyyhlxsj", "hkczh", "hkzhye", "hkqzsj", "hkfs",
				"zqsj", "zqqx", "zqlv", "zqje", "zqszdw", "jkrtqlxze",
				"jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj", "sftqhk", "tqhkje",
				"txfs", "ytxcs", "zjyctxsj", "khh" };
		return dao.getMap(sql.toString(), new String[] { pkVal }, colList);
	}

	/**
	 * 获取国家助学贷款合同名称
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public String getHtmc(String xh) throws Exception {
		String htmc = dao
				.getOneRs(
						"select a.htmc from zgdzdx_htbhfwb a,view_stu_details b where a.xydm=b.xydm and b.xh=?",
						new String[] { xh }, "htmc");

		return "".equalsIgnoreCase(htmc) ? "DZDX" : htmc;
	}

	/**
	 * 获取国家助学贷款审核通过信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdktgxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xz,xl,sfzh,rxny,byny,sfby,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,dkll,dkqxy,jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,sqsj,shjg,shyj,htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,emailqq,lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,qyjjzk,fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,fk_xn2_sffk,fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,fk_xn4_sffk,fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,dkye,sfqdhkxy,hkxyqssj,jkrhkqk,jkrzhyyhlxsj,hkczh,hkzhye,zqsj,zqqx,zqlv,zqje,jkrtqlxze,jkrtqlxsj,jkrtqbjze,jkrtqbjsj,sftqhk,tqhkje,txfs,ytxcs,zjyctxsj,sftxzlqrs from view_zdgzgx_gjzxdk where xh = ? and shjg='通过'";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xz", "xl",
				"sfzh", "rxny", "byny", "sfby", "mzmc", "zzmmmc", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", "ssdh",
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "shjg", "shyj", "htbh", "hyzk", "pomc", "polxdh",
				"gzdw", "dwdh", "yddh", "dwdz", "dwyb", "gzhysr", "emailqq",
				"lxrxm", "lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
				"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je", "fk_xn1_tksj",
				"fk_xn1_sffk", "fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk",
				"fk_xn3_je", "fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
				"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj",
				"fk_xn5_sffk", "dkye", "sfqdhkxy", "hkxyqssj", "jkrhkqk",
				"jkrzhyyhlxsj", "hkczh", "hkzhye", "zqsj", "zqqx", "zqlv",
				"zqje", "jkrtqlxze", "jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj",
				"sftqhk", "tqhkje", "txfs", "ytxcs", "zjyctxsj", "sftxzlqrs" };
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
	 * 获取还款协议信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gethkxyxx(String pkVal) throws Exception {
		
		//2010.8.12 quph修改(用加号拼接的字符串改成StringBuilder，dao返回数组改为返回Map)
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb,nj,xz,xl,sfzh,rxny,(select byny from xsxxb where xh=a.xh) byny,");
		sql.append("to_char(to_date(substr(dkqx,10,10),'yyyymmdd'),'yyyy-mm-dd') dkjssj,");
		sql.append("substr((select byny from xsxxb where xh=a.xh),0,4) bynf,");
		sql.append("(select to_char(to_date(byny,'yyyy-mm-dd')+1,'yyyy-mm-dd') from xsxxb where xh=a.xh) bynyn,");
		sql.append("sfby,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,ssdh,sqdkje,dkqx,");
		sql.append("dkll,dkqxy,jtxxzz,jtyb,jtdh,fqxm,fqzy,fqsfzh,mqxm,mqzy,mqsfzh,jtysr,");
		sql.append("sqsj,shjg,shyj,htbh,hyzk,pomc,polxdh,gzdw,dwdh,yddh,dwdz,dwyb,gzhysr,");
		sql.append("emailqq,lxrxm,lxrxb,lxrcsrq,lxrgx,lxrlxdh,lxrdwdz,bz,sfwfkyhddjm,qyjjzk,");
		sql.append("fk_xn1_je,fk_xn1_tksj,fk_xn1_sffk,fk_xn2_je,fk_xn2_tksj,fk_xn2_sffk,");
		sql.append("fk_xn3_je,fk_xn3_tksj,fk_xn3_sffk,fk_xn4_je,fk_xn4_tksj,fk_xn4_sffk,");
		sql.append("fk_xn5_je,fk_xn5_tksj,fk_xn5_sffk,dkye,sfqdhkxy,hkxyqssj,jkrhkqk,");
		sql.append("jkrzhyyhlxsj,hkczh,hkzhye,zqsj,zqqx,zqlv,zqje,jkrtqlxze,jkrtqlxsj,");
		sql.append("jkrtqbjze,jkrtqbjsj,sftqhk,tqhkje,txfs,ytxcs,zjyctxsj,sftxzlqrs,khh,");
		sql.append("((case fk_xn1_sffk when '是' then round(nvl(fk_xn1_je,'0')) else 0 end)+");
		sql.append("(case fk_xn2_sffk when '是' then round(nvl(fk_xn2_je,'0')) else 0 end)+");
		sql.append("(case fk_xn3_sffk when '是' then round(nvl(fk_xn3_je,'0')) else 0 end)+");
		sql.append("(case fk_xn4_sffk when '是' then round(nvl(fk_xn4_je,'0')) else 0 end)+");
		sql.append("(case fk_xn5_sffk when '是' then round(nvl(fk_xn5_je,'0')) else 0 end)) sjffje,");
		sql.append("substr(DKQX,10,4) rq1_year,substr(DKQX,14,2) rq1_mon,");
		sql.append("substr((select byny from xsxxb where xh=a.xh),0,4)+2 rq2_year,");
/*		sql.append("months_between(to_date(substr(dkqx,10,6),'yyyymm'),");
		sql.append("to_date(substr(byny,0,4)||'0701','yyyymmdd'))+1 monnum1,");
		sql.append("months_between(to_date(substr(dkqx,10,6),'yyyymm'),");
		sql.append("to_date(substr((select byny from xsxxb where xh=a.xh),0,4)||'0701','yyyymmdd'))-23 monnum2,");*/
		sql.append("NVL((select z.xxsh from zgdzdx_hkjzqhhkxyxx z where z.xh=a.xh and z.xz='1'),'未审核') xyshjg ");
		sql.append(" from view_zdgzgx_gjzxdk a where xh = ? and shjg='通过'");
		
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xz", "xl",
				"sfzh", "rxny", "byny", "bynf", "sfby", "mzmc", "zzmmmc", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", "ssdh",
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "shjg", "shyj", "htbh", "hyzk", "pomc", "polxdh",
				"gzdw", "dwdh", "yddh", "dwdz", "dwyb", "gzhysr", "emailqq",
				"lxrxm", "lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
				"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je", "fk_xn1_tksj",
				"fk_xn1_sffk", "fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk",
				"fk_xn3_je", "fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
				"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj",
				"fk_xn5_sffk", "dkye", "sfqdhkxy", "hkxyqssj", "jkrhkqk",
				"jkrzhyyhlxsj", "hkczh", "hkzhye", "zqsj", "zqqx", "zqlv",
				"zqje", "jkrtqlxze", "jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj",
				"sftqhk", "tqhkje", "txfs", "ytxcs", "zjyctxsj", "sftxzlqrs",
				"khh", "sjffje", "rq1_year", "rq1_mon", "rq2_year",
				 "xyshjg","bynyn","dkjssj" };
		
		HashMap<String, String>  stuList = dao.getMap(sql.toString(), new String[] { pkVal }, colList);
		// ----------- 2010/5/6 luojw begin ------------

		String fkzje = stuList.get("sjffje");// 放款总金额
		String sftqhk = stuList.get("sftqhk");// 是否提前还款
		String tqhkje = stuList.get("tqhkje");// 提前还款金额
		String dkye = fkzje;// 贷款余额

		// 贷款余额:放款总金额-提前还款金额
		if ("是".equalsIgnoreCase(sftqhk) && !Base.isNull(fkzje)
				&& !Base.isNull(tqhkje)) {
			float float_dkje = Float.parseFloat(fkzje)
					- Float.parseFloat(tqhkje);
			dkye = String.valueOf(float_dkje);

		}
		stuList.put("sjffje", dkye);

		// ----------- end ------------
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		stuList.put("sjffje", stuList.get("sjffje") == null ? "0" : stuList
				.get("sjffje"));
		String[] temp = dao.getProVal(sqlT, new String[] { stuList
				.get("sjffje") }, new int[] { 2 });
		stuList.put("sjffje_dx", temp[0].replace("元整", ""));
		return stuList;
	}

	/**
	 * 获取展期后还款协议信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqhhkxyxx(String pkVal) throws Exception {
		//2010.8.12 quph修改(用加号拼接的字符串改成StringBuilder，dao返回数组改为返回Map)
		StringBuilder sql = new StringBuilder();
		
		sql.append("select x.*,(select byny from xsxxb where xh=x.xh) byny,");
		sql.append("(select to_char(to_date(byny,'yyyy-mm-dd')+1,'yyyy-mm-dd') from xsxxb where xh=x.xh) bynyn,");
		sql.append("to_char(add_months(to_date(substr(x.zqhbyny,1,7),'yyyy-mm'),24),'yyyy-mm') hbrq,");
		sql.append("months_between(to_date(substr(x.zqrq,1,6),'yyyymm'),");
		sql.append("to_date(substr(x.zqhbyny,1,7),'yyyy-mm'))+1 num1,");
		sql.append("months_between(to_date(substr(x.zqrq,1,6),'yyyymm'),");
		sql.append("add_months(to_date(substr(x.zqhbyny,1,7),'yyyy-mm'),24))+1 num2");
		sql.append(" from (select a.xh,a.xm,a.xb,a.nj,a.xz,a.xl,a.sfzh,a.rxny,a.byny,");
		sql.append("(case b.zqnx WHEN '0' THEN to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),");
		sql.append("months_between(to_date(substr(b.zqrq,1,6),'yyyymm'),");
		sql.append("to_date(substr(b.dkqx,10,6),'yyyy-mm'))+1),'yyyy-mm-dd') ");
		sql.append("ELSE to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),");
		sql.append("b.zqnx*12+1),'yyyy-mm-dd') END) zqhbyny,");
		sql.append("a.sfby,a.mzmc,a.zzmmmc,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,");
		sql.append("a.csrq,a.ssdh,a.sqdkje,a.dkqx,a.dkll,a.dkqxy,a.jtxxzz,a.jtyb,");
		sql.append("a.jtdh,a.fqxm,a.fqzy,a.fqsfzh,a.mqxm,a.mqzy,a.mqsfzh,a.jtysr,");
		sql.append("a.sqsj,a.shjg,a.shyj,a.htbh,a.hyzk,a.pomc,a.polxdh,a.gzdw,");
		sql.append("a.dwdh,a.yddh,a.dwdz,a.dwyb,a.gzhysr,a.emailqq,a.lxrxm,a.lxrxb,");
		sql.append("a.lxrcsrq,a.lxrgx,a.lxrlxdh,a.lxrdwdz,a.bz,a.sfwfkyhddjm,");
		sql.append("a.qyjjzk,a.fk_xn1_je,a.fk_xn1_tksj,a.fk_xn1_sffk,a.fk_xn2_je,");
		sql.append("a.fk_xn2_tksj,a.fk_xn2_sffk,a.fk_xn3_je,a.fk_xn3_tksj,");
		sql.append("a.fk_xn3_sffk,a.fk_xn4_je,a.fk_xn4_tksj,a.fk_xn4_sffk,");
		sql.append("a.fk_xn5_je,a.fk_xn5_tksj,a.fk_xn5_sffk,a.dkye,a.sfqdhkxy,");
		sql.append("a.hkxyqssj,a.jkrhkqk,a.jkrzhyyhlxsj,a.hkczh,a.hkzhye,a.zqsj,");
		sql.append("a.zqqx,a.zqlv,a.zqje,a.zqszdw,a.jkrtqlxze,a.jkrtqlxsj,a.jkrtqbjze,");
		sql.append("a.jkrtqbjsj,a.sftqhk,a.tqhkje,a.txfs,a.ytxcs,a.zjyctxsj,a.sftxzlqrs,khh,");
		sql.append("((case a.fk_xn1_sffk when '是' then round(nvl(a.fk_xn1_je,'0')) else 0 end)+");
		sql.append("(case a.fk_xn2_sffk when '是' then round(nvl(a.fk_xn2_je,'0')) else 0 end)+");
		sql.append("(case a.fk_xn3_sffk when '是' then round(nvl(a.fk_xn3_je,'0')) else 0 end)+");
		sql.append("(case a.fk_xn4_sffk when '是' then round(nvl(a.fk_xn4_je,'0')) else 0 end)+");
		sql.append("(case a.fk_xn5_sffk when '是' then round(nvl(a.fk_xn5_je,'0')) else 0 end)) sjffje,");
		sql.append("b.zqnx,b.zqrq,NVL((select z.xxsh from zgdzdx_hkjzqhhkxyxx z where z.xh=a.xh and z.xz='2'),");
		sql.append("'未审核') xyshjg from view_zdgzgx_gjzxdk a,view_zgdzdx_zqxy b where a.xh=b.xh and a.xh = ?) x");
		
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xz", "xl",
				"sfzh", "rxny", "byny", "zqhbyny", "sfby", "mzmc", "zzmmmc",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "csrq", "ssdh",
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "shjg", "shyj", "htbh", "hyzk", "pomc", "polxdh",
				"gzdw", "dwdh", "yddh", "dwdz", "dwyb", "gzhysr", "emailqq",
				"lxrxm", "lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
				"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je", "fk_xn1_tksj",
				"fk_xn1_sffk", "fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk",
				"fk_xn3_je", "fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
				"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj",
				"fk_xn5_sffk", "dkye", "sfqdhkxy", "hkxyqssj", "jkrhkqk",
				"jkrzhyyhlxsj", "hkczh", "hkzhye", "zqsj", "zqqx", "zqlv",
				"zqje", "zqszdw", "jkrtqlxze", "jkrtqlxsj", "jkrtqbjze",
				"jkrtqbjsj", "sftqhk", "tqhkje", "txfs", "ytxcs", "zjyctxsj",
				"sftxzlqrs", "khh", "sjffje", "zqnx", "zqrq", "xyshjg", "hbrq",
				"num1", "num2","byny","bynyn" };
		
		HashMap<String, String> stuList = dao.getMap(sql.toString(), new String[] { pkVal }, colList);
		
		String sjffje = String.valueOf(Integer.valueOf(stuList.get("sjffje")== null ? "0" : stuList.get("sjffje"))-Integer.valueOf(stuList.get("tqhkje")== null ? "0" : stuList.get("tqhkje")));
		
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		stuList.put("sjffje", sjffje == null ? "0" : sjffje);
		String[] temp = dao.getProVal(sqlT, new String[] { stuList
				.get("sjffje") }, new int[] { 2 });
		stuList.put("sjffje_dx", temp[0].replace("元整", ""));
		return stuList;
	}

	/**
	 * 获取展期协议信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqxyxx(String pkVal) throws Exception {
		
		//2010.8.12 quph修改(用加号拼接的字符串改成StringBuilder，dao返回数组改为返回Map)
		StringBuilder sql = new StringBuilder();
		
		sql.append("select (select b.fkzje from view_zdgzgx_gjzxdk b where a.xh = b.xh) fkzje,");
		sql.append("(select b.sftqhk from zdgzgx_gjzxdk b where a.xh = b.xh) sftqhk,");
		sql.append("(select b.tqhkje from zdgzgx_gjzxdk b where a.xh = b.xh) tqhkje,");
		sql.append("(select to_char(to_date(byny,'yyyy-mm-dd')+1,'yyyy-mm-dd') from xsxxb where xh=a.xh) bynyn,");
		sql.append("(select b.gfcs from (select xh,(xn1 + xn2 + xn3 + xn4) gfcs from (select b.xh,");
		sql.append("case when b.fk_xn1_je is not null then 1 else 0 end xn1,");
		sql.append("case when b.fk_xn2_je is not null then 1 else 0 end xn2,");
		sql.append("case when b.fk_xn3_je is not null then 1 else 0 end xn3,");
		sql.append("case when b.fk_xn4_je is not null then 1 else 0 end xn4,");
		sql.append("case when b.fk_xn5_je is not null then 1 else 0 end xn5 from zdgzgx_gjzxdk b)) b where a.xh = b.xh) gfcs,");
		sql.append("(select b.yfcs from(select xh,(xn1 + xn2 + xn3 + xn4) yfcs from (select b.xh,");
		sql.append("case when b.fk_xn1_sffk = '是' then 1 else 0 end xn1,");
		sql.append("case when b.fk_xn2_sffk = '是' then 1 else 0 end xn2,");
		sql.append("case when b.fk_xn3_sffk = '是' then 1 else 0 end xn3,");
		sql.append("case when b.fk_xn4_sffk = '是' then 1 else 0 end xn4,");
		sql.append("case when b.fk_xn5_sffk = '是' then 1 else 0 end xn5 from zdgzgx_gjzxdk b)) b where a.xh = b.xh) yfcs,");
		sql.append("xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,(select byny from xsxxb where xh=a.xh) byny,sqdkje,dkqx,dkqxy,");
		sql.append("htbh,jhfkcs,sjfkcs,dkye,zqnx,zqrq,zqrq zqhhkrq,shjg,");
		sql.append("(case zqnx WHEN '0' THEN to_char(add_months(to_date((select byny from xsxxb where xh=a.xh),'yyyy-mm-dd'),");
		sql.append("months_between(to_date(substr(zqrq,1,6),'yyyymm'),to_date(substr(dkqx,10,6),'yyyy-mm'))),");
		sql.append("'yyyy-mm-dd') ELSE to_char(add_months(to_date((select byny from xsxxb where xh=a.xh),'yyyy-mm-dd'),zqnx*12),'yyyy-mm-dd') END) ");
		sql.append("zqhbyny from VIEW_ZGDZDX_ZQXY a where xh = ?");
		
		// ----------- 2010/5/6 edit by luojw begin ------------
		
		String[] colList = new String[] { "fkzje", "sftqhk", "tqhkje", "gfcs",
				"yfcs", "xh", "xm", "xb", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "byny", "sqdkje", "dkqx", "dkqxy",
				"htbh", "jhfkcs", "sjfkcs", "dkye", "zqnx", "zqrq", "zqhhkrq",
				"shjg", "zqhbyny","bynyn" };
		
		HashMap<String, String> stuList = dao.getMap(sql.toString(), new String[] { pkVal }, colList);
		
		String fkzje = stuList.get("fkzje");// 放款总金额
		String sftqhk = stuList.get("sftqhk");// 是否提前还款
		String tqhkje = stuList.get("tqhkje");// 提前还款金额
		String dkye = fkzje;// 贷款余额

		// 贷款余额:放款总金额-提前还款金额
		if ("是".equalsIgnoreCase(sftqhk) && !Base.isNull(fkzje)
				&& !Base.isNull(tqhkje)) {
			float float_dkje = Float.parseFloat(fkzje)
					- Float.parseFloat(tqhkje);
			dkye = String.valueOf(float_dkje);

		}
		stuList.put("dkye", dkye);

		// ----------- end ------------
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		stuList.put("sqdkje", stuList.get("sqdkje") == null ? "0" : stuList
				.get("sqdkje"));
		String[] temp = dao.getProVal(sqlT, new String[] { stuList
				.get("sqdkje") }, new int[] { 2 });
		stuList.put("sqdkje_dx", temp[0].replace("元整", ""));

		stuList.put("dkye", stuList.get("dkye") == null ? "0" : stuList.get("dkye"));
		temp = dao.getProVal(sqlT, new String[] { stuList.get("dkye") },new int[] { 2 });
		stuList.put("dkye_dx", temp[0].replace("元整", ""));
		
		return stuList;
	}

	

	/**
	 * 保存国家助学贷款申请信息，成功返回TRUE，反之返回FALSE saveGjzxdkSqxx ---- 保存国家助学贷款申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sqdkje = Base.chgNull(model.getSqdkje(), "", 1);
		String dkqx = Base.chgNull(model.getDkqx(), "", 1);
		String dkqxy = Base.chgNull(model.getDkqxy(), "", 1);
		String jtxxzz = Base.chgNull(model.getJtxxzz(), "", 1);
		String csrq = Base.chgNull(model.getCsrq(), "", 1);
		String ssdh = Base.chgNull(model.getSsdh(), "", 1);
		String qyjjzk = Base.chgNull(model.getQyjjzk(), "", 1);
		String emailqq = Base.chgNull(model.getEmailqq(), "", 1);
		String sfwfkyhddjm = Base.chgNull(model.getSfwfkyhddjm(), "", 1);
		String hkczh = Base.chgNull(model.getHkczh(), "", 1);
		String jtyb = Base.chgNull(model.getJtyb(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String fqzy = Base.chgNull(model.getFqzy(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String mqzy = Base.chgNull(model.getMqzy(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String jtysr = Base.chgNull(model.getJtysr(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		String fk_xn1_je = model.getFk_xn1_je();
		String fk_xn2_je = model.getFk_xn2_je();
		String fk_xn3_je = model.getFk_xn3_je();
		String fk_xn4_je = model.getFk_xn4_je();
		String fk_xn5_je = model.getFk_xn5_je();
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkdatacf(xh);

		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zdgzgx_gjzxdk", new String[] {
					"xh", "sqdkje", "dkqx", "dkqxy", "jtxxzz", "jtyb", "jtdh",
					"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh",
					"jtysr", "bz", "sqsj", "csrq", "ssdh", "qyjjzk", "emailqq",
					"sfwfkyhddjm", "hkczh","fk_xn1_je","fk_xn2_je","fk_xn3_je",
					"fk_xn4_je","fk_xn5_je" }, new String[] { xh, sqdkje, dkqx,
					dkqxy, jtxxzz, jtyb, jtdh, fqxm, fqzy, fqsfzh, mqxm, mqzy,
					mqsfzh, jtysr, bz, now, csrq, ssdh, qyjjzk, emailqq,
					sfwfkyhddjm, hkczh,fk_xn1_je,fk_xn2_je,fk_xn3_je,fk_xn4_je,fk_xn5_je }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
					"sqdkje", "dkqx", "dkqxy", "jtxxzz", "jtyb", "jtdh",
					"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh",
					"jtysr", "bz", "sqsj", "shjg", "shyj", "csrq", "ssdh",
					"qyjjzk", "emailqq", "sfwfkyhddjm", "hkczh", "fk_xn1_je",
					"fk_xn2_je", "fk_xn3_je", "fk_xn4_je", "fk_xn5_je" },
					new String[] { sqdkje, dkqx, dkqxy, jtxxzz, jtyb, jtdh,
							fqxm, fqzy, fqsfzh, mqxm, mqzy, mqsfzh, jtysr, bz,
							now, "未审核", "", csrq, ssdh, qyjjzk, emailqq,
							sfwfkyhddjm, hkczh, fk_xn1_je, fk_xn2_je,
							fk_xn3_je, fk_xn4_je, fk_xn5_je }, "xh", xh,
					request);
		} else if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
					"sqdkje", "dkqx", "dkqxy", "jtxxzz", "jtyb", "jtdh",
					"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh",
					"jtysr", "bz", "csrq", "ssdh", "qyjjzk", "emailqq",
					"sfwfkyhddjm", "hkczh","fk_xn1_je","fk_xn2_je","fk_xn3_je",
					"fk_xn4_je","fk_xn5_je"  }, new String[] { sqdkje, dkqx,
					dkqxy, jtxxzz, jtyb, jtdh, fqxm, fqzy, fqsfzh, mqxm, mqzy,
					mqsfzh, jtysr, bz, csrq, ssdh, qyjjzk, emailqq,
					sfwfkyhddjm, hkczh, fk_xn1_je, fk_xn2_je,
					fk_xn3_je, fk_xn4_je, fk_xn5_je }, "xh", xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * 保存国家助学贷款详细信息，成功返回TRUE，反之返回FALSE saveGjzxdkxx ---- 保存国家助学贷款详细信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sqdkje = Base.chgNull(model.getSqdkje(), "", 1);
		String dkqx = Base.chgNull(model.getDkqx(), "", 1);
		String dkll = Base.chgNull(model.getDkll(), "", 1);
		String dkqxy = Base.chgNull(model.getDkqxy(), "", 1);
		String jtxxzz = Base.chgNull(model.getJtxxzz(), "", 1);
		String jtyb = Base.chgNull(model.getJtyb(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String fqzy = Base.chgNull(model.getFqzy(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String mqzy = Base.chgNull(model.getMqzy(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String jtysr = Base.chgNull(model.getJtysr(), "", 1);
		String sqsj = Base.chgNull(model.getSqsj(), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String hyzk = Base.chgNull(model.getHyzk(), "", 1);
		String pomc = Base.chgNull(model.getPomc(), "", 1);
		String polxdh = Base.chgNull(model.getPolxdh(), "", 1);
		String gzdw = Base.chgNull(model.getGzdw(), "", 1);
		String dwdh = Base.chgNull(model.getDwdh(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String dwdz = Base.chgNull(model.getDwdz(), "", 1);
		String dwyb = Base.chgNull(model.getDwyb(), "", 1);
		String gzhysr = Base.chgNull(model.getGzhysr(), "", 1);
		String emailqq = Base.chgNull(model.getEmailqq(), "", 1);
		String lxrxm = Base.chgNull(model.getLxrxm(), "", 1);
		String lxrxb = Base.chgNull(model.getLxrxb(), "", 1);
		String lxrcsrq = Base.chgNull(model.getLxrcsrq(), "", 1);
		String lxrgx = Base.chgNull(model.getLxrgx(), "", 1);
		String lxrlxdh = Base.chgNull(model.getLxrlxdh(), "", 1);
		String lxrdwdz = Base.chgNull(model.getLxrdwdz(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		String sfwfkyhddjm = Base.chgNull(model.getSfwfkyhddjm(), "", 1);
		String qyjjzk = Base.chgNull(model.getQyjjzk(), "", 1);
		String fk_xn1_je = Base.chgNull(model.getFk_xn1_je(), "", 1);
		String fk_xn1_tksj = Base.chgNull(model.getFk_xn1_tksj(), "", 1);
		String fk_xn1_sffk = Base.chgNull(model.getFk_xn1_sffk(), "", 1);
		String fk_xn2_je = Base.chgNull(model.getFk_xn2_je(), "", 1);
		String fk_xn2_tksj = Base.chgNull(model.getFk_xn2_tksj(), "", 1);
		String fk_xn2_sffk = Base.chgNull(model.getFk_xn2_sffk(), "", 1);
		String fk_xn3_je = Base.chgNull(model.getFk_xn3_je(), "", 1);
		String fk_xn3_tksj = Base.chgNull(model.getFk_xn3_tksj(), "", 1);
		String fk_xn3_sffk = Base.chgNull(model.getFk_xn3_sffk(), "", 1);
		String fk_xn4_je = Base.chgNull(model.getFk_xn4_je(), "", 1);
		String fk_xn4_tksj = Base.chgNull(model.getFk_xn4_tksj(), "", 1);
		String fk_xn4_sffk = Base.chgNull(model.getFk_xn4_sffk(), "", 1);
		String fk_xn5_je = Base.chgNull(model.getFk_xn5_je(), "", 1);
		String fk_xn5_tksj = Base.chgNull(model.getFk_xn5_tksj(), "", 1);
		String fk_xn5_sffk = Base.chgNull(model.getFk_xn5_sffk(), "", 1);
		String sfqdhkxy = Base.chgNull(model.getSfqdhkxy(), "", 1);
		String hkxyqssj = Base.chgNull(model.getHkxyqssj(), "", 1);
		String jkrhkqk = Base.chgNull(model.getJkrhkqk(), "", 1);
		String jkrzhyyhlxsj = Base.chgNull(model.getJkrzhyyhlxsj(), "", 1);
		String hkczh = Base.chgNull(model.getHkczh(), "", 1);
		String hkzhye = Base.chgNull(model.getHkzhye(), "", 1);
		String zqsj = Base.chgNull(model.getZqsj(), "", 1);
		String zqqx = Base.chgNull(model.getZqqx(), "", 1);
		String zqlv = Base.chgNull(model.getZqlv(), "", 1);
		String zqje = Base.chgNull(model.getZqje(), "", 1);
		String jkrtqlxze = Base.chgNull(model.getJkrtqlxze(), "", 1);
		String jkrtqlxsj = Base.chgNull(model.getJkrtqlxsj(), "", 1);
		String jkrtqbjze = Base.chgNull(model.getJkrtqbjze(), "", 1);
		String jkrtqbjsj = Base.chgNull(model.getJkrtqbjsj(), "", 1);
		String sftqhk = Base.chgNull(model.getSftqhk(), "", 1);
		String tqhkje = Base.chgNull(model.getTqhkje(), "", 1);
		String txfs = Base.chgNull(model.getTxfs(), "", 1);
		String ytxcs = Base.chgNull(model.getYtxcs(), "", 1);
		String zjyctxsj = Base.chgNull(model.getZjyctxsj(), "", 1);
		String csrq = Base.chgNull(model.getCsrq(), "", 1);
		String ssdh = Base.chgNull(model.getSsdh(), "", 1);
		String sftxzlqrs = Base.chgNull(model.getSftxzlqrs(), "", 1);
		String zqszdw = Base.chgNull(model.getZqszdw(), "", 1);
		String khh = Base.chgNull(model.getKhh(), "", 1);
		String hkqzsj = Base.chgNull(model.getHkqzsj(), "", 1);
		String hkfs = Base.chgNull(model.getHkfs(), "", 1);

		
		bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
				"sqdkje", "dkqx", "dkll", "dkqxy", "jtxxzz", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr",
				"sqsj", "htbh", "hyzk", "pomc", "polxdh", "gzdw", "dwdh",
				"yddh", "dwdz", "dwyb", "gzhysr", "emailqq", "lxrxm", "lxrxb",
				"lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz", "bz", "sfwfkyhddjm",
				"qyjjzk", "fk_xn1_je", "fk_xn1_tksj", "fk_xn1_sffk",
				"fk_xn2_je", "fk_xn2_tksj", "fk_xn2_sffk", "fk_xn3_je",
				"fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je", "fk_xn4_tksj",
				"fk_xn4_sffk", "fk_xn5_je", "fk_xn5_tksj", "fk_xn5_sffk",
				"sfqdhkxy", "hkxyqssj", "jkrhkqk", "jkrzhyyhlxsj", "hkczh",
				"hkzhye", "zqsj", "zqqx", "zqlv", "zqje", "jkrtqlxze",
				"jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj", "sftqhk", "tqhkje",
				"txfs", "ytxcs", "zjyctxsj", "csrq", "ssdh", "sftxzlqrs",
				"zqszdw", "khh", "hkqzsj", "hkfs" }, new String[] { sqdkje,
				dkqx, dkll, dkqxy, jtxxzz, jtyb, jtdh, fqxm, fqzy, fqsfzh,
				mqxm, mqzy, mqsfzh, jtysr, sqsj, htbh, hyzk, pomc, polxdh,
				gzdw, dwdh, yddh, dwdz, dwyb, gzhysr, emailqq, lxrxm, lxrxb,
				lxrcsrq, lxrgx, lxrlxdh, lxrdwdz, bz, sfwfkyhddjm, qyjjzk,
				fk_xn1_je, fk_xn1_tksj, fk_xn1_sffk, fk_xn2_je, fk_xn2_tksj,
				fk_xn2_sffk, fk_xn3_je, fk_xn3_tksj, fk_xn3_sffk, fk_xn4_je,
				fk_xn4_tksj, fk_xn4_sffk, fk_xn5_je, fk_xn5_tksj, fk_xn5_sffk,
				sfqdhkxy, hkxyqssj, jkrhkqk, jkrzhyyhlxsj, hkczh, hkzhye, zqsj,
				zqqx, zqlv, zqje, jkrtqlxze, jkrtqlxsj, jkrtqbjze, jkrtqbjsj,
				sftqhk, tqhkje, txfs, ytxcs, zjyctxsj, csrq, ssdh, sftxzlqrs,
				zqszdw, khh, hkqzsj, hkfs }, "xh", xh, request);

		return bFlag;
	}
	
	/**
	 * 保存国家助学贷款历史详细信息，成功返回TRUE，反之返回FALSE saveGjzxdklsxx ---- 保存国家助学贷款历史详细信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdklsxx(GjzxdkLsModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xm = Base.chgNull(model.getXm(), "", 1);
		String xb = Base.chgNull(model.getXb(), "", 1);
		String nj = Base.chgNull(model.getNj(), "", 1);
		String xz = Base.chgNull(model.getXz(), "", 1);
		String xl = Base.chgNull(model.getXl(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String rxny = Base.chgNull(model.getRxny(), "", 1);
		String byny = Base.chgNull(model.getByny(), "", 1);
		String sfby = Base.chgNull(model.getSfby(), "", 1);
		String mzmc = Base.chgNull(model.getMzmc(), "", 1);
		String zzmmmc = Base.chgNull(model.getZzmmmc(), "", 1);
		String xydm = Base.chgNull(model.getXydm(), "", 1);
		String xymc = Base.chgNull(model.getXymc(), "", 1);
		String zydm = Base.chgNull(model.getZydm(), "", 1);
		String zymc = Base.chgNull(model.getZymc(), "", 1);
		String bjdm = Base.chgNull(model.getBjdm(), "", 1);
		String bjmc = Base.chgNull(model.getBjmc(), "", 1);
		String csrq = Base.chgNull(model.getCsrq(), "", 1);
		String ssdh = Base.chgNull(model.getSsdh(), "", 1);
		String sqdkje = Base.chgNull(model.getSqdkje(), "", 1);
		String dkqx = Base.chgNull(model.getDkqx(), "", 1);
		String dkll = Base.chgNull(model.getDkll(), "", 1);
		String dkqxy = Base.chgNull(model.getDkqxy(), "", 1);
		String jtxxzz = Base.chgNull(model.getJtxxzz(), "", 1);
		String jtyb = Base.chgNull(model.getJtyb(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String fqzy = Base.chgNull(model.getFqzy(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String mqzy = Base.chgNull(model.getMqzy(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String jtysr = Base.chgNull(model.getJtysr(), "", 1);
		String sqsj = Base.chgNull(model.getSqsj(), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String hyzk = Base.chgNull(model.getHyzk(), "", 1);
		String pomc = Base.chgNull(model.getPomc(), "", 1);
		String polxdh = Base.chgNull(model.getPolxdh(), "", 1);
		String gzdw = Base.chgNull(model.getGzdw(), "", 1);
		String dwdh = Base.chgNull(model.getDwdh(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String dwdz = Base.chgNull(model.getDwdz(), "", 1);
		String dwyb = Base.chgNull(model.getDwyb(), "", 1);
		String gzhysr = Base.chgNull(model.getGzhysr(), "", 1);
		String emailqq = Base.chgNull(model.getEmailqq(), "", 1);
		String lxrxm = Base.chgNull(model.getLxrxm(), "", 1);
		String lxrxb = Base.chgNull(model.getLxrxb(), "", 1);
		String lxrcsrq = Base.chgNull(model.getLxrcsrq(), "", 1);
		String lxrgx = Base.chgNull(model.getLxrgx(), "", 1);
		String lxrlxdh = Base.chgNull(model.getLxrlxdh(), "", 1);
		String lxrdwdz = Base.chgNull(model.getLxrdwdz(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		String sfwfkyhddjm = Base.chgNull(model.getSfwfkyhddjm(), "", 1);
		String qyjjzk = Base.chgNull(model.getQyjjzk(), "", 1);
		String fk_xn1_je = Base.chgNull(model.getFk_xn1_je(), "", 1);
		String fk_xn1_tksj = Base.chgNull(model.getFk_xn1_tksj(), "", 1);
		String fk_xn1_sffk = Base.chgNull(model.getFk_xn1_sffk(), "", 1);
		String fk_xn2_je = Base.chgNull(model.getFk_xn2_je(), "", 1);
		String fk_xn2_tksj = Base.chgNull(model.getFk_xn2_tksj(), "", 1);
		String fk_xn2_sffk = Base.chgNull(model.getFk_xn2_sffk(), "", 1);
		String fk_xn3_je = Base.chgNull(model.getFk_xn3_je(), "", 1);
		String fk_xn3_tksj = Base.chgNull(model.getFk_xn3_tksj(), "", 1);
		String fk_xn3_sffk = Base.chgNull(model.getFk_xn3_sffk(), "", 1);
		String fk_xn4_je = Base.chgNull(model.getFk_xn4_je(), "", 1);
		String fk_xn4_tksj = Base.chgNull(model.getFk_xn4_tksj(), "", 1);
		String fk_xn4_sffk = Base.chgNull(model.getFk_xn4_sffk(), "", 1);
		String fk_xn5_je = Base.chgNull(model.getFk_xn5_je(), "", 1);
		String fk_xn5_tksj = Base.chgNull(model.getFk_xn5_tksj(), "", 1);
		String fk_xn5_sffk = Base.chgNull(model.getFk_xn5_sffk(), "", 1);
		String fkzje = Base.chgNull(model.getFkzje(), "", 1);
		String dkye = Base.chgNull(model.getDkye(), "", 1);
		String sftxzlqrs = Base.chgNull(model.getSftxzlqrs(), "", 1);
		String sfqdhkxy = Base.chgNull(model.getSfqdhkxy(), "", 1);
		String hkxyqssj = Base.chgNull(model.getHkxyqssj(), "", 1);
		String jkrhkqk = Base.chgNull(model.getJkrhkqk(), "", 1);
		String jkrzhyyhlxsj = Base.chgNull(model.getJkrzhyyhlxsj(), "", 1);
		String hkczh = Base.chgNull(model.getHkczh(), "", 1);
		String hkzhye = Base.chgNull(model.getHkzhye(), "", 1);
		String hkqzsj = Base.chgNull(model.getHkqzsj(), "", 1);
		String hkfs = Base.chgNull(model.getHkfs(), "", 1);
		String zqsj = Base.chgNull(model.getZqsj(), "", 1);
		String zqqx = Base.chgNull(model.getZqqx(), "", 1);
		String zqlv = Base.chgNull(model.getZqlv(), "", 1);
		String zqje = Base.chgNull(model.getZqje(), "", 1);
		String zqszdw = Base.chgNull(model.getZqszdw(), "", 1);
		String jkrtqlxze = Base.chgNull(model.getJkrtqlxze(), "", 1);
		String jkrtqlxsj = Base.chgNull(model.getJkrtqlxsj(), "", 1);
		String jkrtqbjze = Base.chgNull(model.getJkrtqbjze(), "", 1);
		String jkrtqbjsj = Base.chgNull(model.getJkrtqbjsj(), "", 1);
		String sftqhk = Base.chgNull(model.getSftqhk(), "", 1);
		String tqhkje = Base.chgNull(model.getTqhkje(), "", 1);
		String txfs = Base.chgNull(model.getTxfs(), "", 1);
		String ytxcs = Base.chgNull(model.getYtxcs(), "", 1);
		String zjyctxsj = Base.chgNull(model.getZjyctxsj(), "", 1);
		String khh = Base.chgNull(model.getKhh(), "", 1);

		
		bFlag = StandardOperation
				.update("zgdzdx_gjzxdk_ls", new String[] { "xm", "xb", "nj",
						"xz", "xl", "sfzh", "rxny", "byny", "sfby", "mzmc",
						"zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm",
						"bjmc", "csrq", "ssdh", "sqdkje", "dkqx", "dkll",
						"dkqxy", "jtxxzz", "jtyb", "jtdh", "fqxm", "fqzy",
						"fqsfzh", "mqxm", "mqzy", "mqsfzh", "jtysr", "sqsj",
						"htbh", "hyzk", "pomc", "polxdh", "gzdw", "dwdh",
						"yddh", "dwdz", "dwyb", "gzhysr", "emailqq", "lxrxm",
						"lxrxb", "lxrcsrq", "lxrgx", "lxrlxdh", "lxrdwdz",
						"bz", "sfwfkyhddjm", "qyjjzk", "fk_xn1_je",
						"fk_xn1_tksj", "fk_xn1_sffk", "fk_xn2_je",
						"fk_xn2_tksj", "fk_xn2_sffk", "fk_xn3_je",
						"fk_xn3_tksj", "fk_xn3_sffk", "fk_xn4_je",
						"fk_xn4_tksj", "fk_xn4_sffk", "fk_xn5_je",
						"fk_xn5_tksj", "fk_xn5_sffk", "fkzje", "dkye",
						"sftxzlqrs", "sfqdhkxy", "hkxyqssj", "jkrhkqk",
						"jkrzhyyhlxsj", "hkczh", "hkzhye", "hkqzsj", "hkfs",
						"zqsj", "zqqx", "zqlv", "zqje", "zqszdw", "jkrtqlxze",
						"jkrtqlxsj", "jkrtqbjze", "jkrtqbjsj", "sftqhk",
						"tqhkje", "txfs", "ytxcs", "zjyctxsj", "khh" },
						new String[] { xm, xb, nj, xz, xl, sfzh, rxny, byny,
								sfby, mzmc, zzmmmc, xydm, xymc, zydm, zymc,
								bjdm, bjmc, csrq, ssdh, sqdkje, dkqx, dkll,
								dkqxy, jtxxzz, jtyb, jtdh, fqxm, fqzy, fqsfzh,
								mqxm, mqzy, mqsfzh, jtysr, sqsj, htbh, hyzk,
								pomc, polxdh, gzdw, dwdh, yddh, dwdz, dwyb,
								gzhysr, emailqq, lxrxm, lxrxb, lxrcsrq, lxrgx,
								lxrlxdh, lxrdwdz, bz, sfwfkyhddjm, qyjjzk,
								fk_xn1_je, fk_xn1_tksj, fk_xn1_sffk, fk_xn2_je,
								fk_xn2_tksj, fk_xn2_sffk, fk_xn3_je,
								fk_xn3_tksj, fk_xn3_sffk, fk_xn4_je,
								fk_xn4_tksj, fk_xn4_sffk, fk_xn5_je,
								fk_xn5_tksj, fk_xn5_sffk, fkzje, dkye,
								sftxzlqrs, sfqdhkxy, hkxyqssj, jkrhkqk,
								jkrzhyyhlxsj, hkczh, hkzhye, hkqzsj, hkfs,
								zqsj, zqqx, zqlv, zqje, zqszdw, jkrtqlxze,
								jkrtqlxsj, jkrtqbjze, jkrtqbjsj, sftqhk,
								tqhkje, txfs, ytxcs, zjyctxsj, khh }, "xh", xh,
						request);

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
	public String isGjzxdkdatacf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zdgzgx_gjzxdk where xh = ? and shjg in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zdgzgx_gjzxdk where xh = ?";
			num = dao.getOneRs(sql, new String[] { xh }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 通过学号得到学生可申请贷款金额
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getZxdkJe(String xh) throws Exception {

		String[] xmdm = dao.getRs("select xmdm from zgdzdx_zxdk_xsxfxmdmb",
				new String[] {}, "xmdm");

		StringBuffer sb = new StringBuffer();

		if (null != xmdm) {
			sb.append(" and fydm in ('###'");
			for (int i = 0; i < xmdm.length; i++) {
				sb.append(", '");
				sb.append(xmdm[i]);
				sb.append("' ");
			}
			sb.append(")");
		}

		String yjxf = dao.getOneRs(
				"select floor(NVL(sum(fybz),'0')) yjxf from cw_bks_xsfybz where xh=? and nd=?"
						+ sb.toString(), new String[] { xh, Base.currNd },"yjxf");

		String dkje = 6000 >= Integer.parseInt(yjxf) ? yjxf : "6000";
		
		StringBuilder sql = new StringBuilder();
		sql.append("select (case ((select to_char(sysdate,'yyyy') now from dual)-a.nj) when 0 then '");
		sql.append(dkje);
		sql.append("'*4 when 1 then '");
		sql.append(dkje);
		sql.append("'*3 when 2 then '");
		sql.append(dkje);
		sql.append("'*2 when 3 then ");
		sql.append(dkje);
		sql.append(" else 0 end) sqdkje from view_stu_details a where xh=?");
		sql.append("");
		
		String zddkje = dao.getOneRs(sql.toString(),new String[] { xh }, "sqdkje");

		String[] je = new String[] { zddkje, dkje };

		return je;
	}
	
	/**
	 * 通过学号得到学生每年贷款金额
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String[] getMndkje(String xh) throws Exception {
		String[] mndkjeT = new String[] { "", "", "", "" };
		
		// ------2010/5/7 edit by luojw begin ---------
		String sql = "select round(mndkje) mndkje,fknx from ( SELECT sqdkje/(case (5-substr(dkqx,0,4)+nj) "
				+ "when 0 then 1 else (5-substr(dkqx,0,4)+nj) end) mndkje,(case (5-substr(dkqx,0,4)+nj) "
				+ "when 0 then 1 else (5-substr(dkqx,0,4)+nj) end) fknx FROM view_zdgzgx_gjzxdk where xh=?)";
		// ------end ---------
		String[] dkxx = dao.getOneRs(sql, new String[] { xh }, new String[] {
				"mndkje", "fknx" });
		for (int i = 0; i < mndkjeT.length && !Base.isNull(dkxx[1]) && i < Integer.parseInt(dkxx[1]); i++) {
			mndkjeT[i] = dkxx[0];
		}

		return mndkjeT;
	}

	/**
	 * 通过学生信息得到国家助学贷款自动生成数据
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkStu(HashMap<String, String> map)
			throws Exception {

		String[] dkqxT = dao.getOneRs(
						"select dknx*12 dkqxy, " +
						"to_char(to_date(dkkssj,'yyyy-mm-dd')," +
						"'yyyymmdd')||'-'||to_char(add_months" +
						"(to_date(dkkssj,'yyyy-mm-dd'),dknx*12),'yyyymmdd') dkqx from zgdzdx_xsdknxb where bjdm=?",
						new String[] { map.get("bjdm") }, new String[] {
								"dkqxy", "dkqx" });

		String[] je = this.getZxdkJe(map.get("xh"));

		map.put("zddkje", je[0]);
		map.put("mndkje", je[1]);
		map.put("dkqxy", dkqxT[0]);
		map.put("dkqx", dkqxT[1]);

		return map;
	}

	/**
	 * 获取国家助学贷款申请审批表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(HashMap<String, String> map,
			HttpServletRequest request) throws Exception {
		map.put("xxmc", "中国地质大学(武汉)");
		map.put("nd", Base.currNd);
		String csrq = map.get("csrq");
		String csrqYear = "";
		String csrqMon = "";
		if (!Base.isNull(csrq) && csrq.length() == 10) {
			csrqYear = csrq.substring(0, 4);
			csrqMon = csrq.substring(5, 7);
		}
		map.put("csrqYear", csrqYear);
		map.put("csrqMon", csrqMon);

		String dkqxy = map.get("dkqxy");
		String dkqx = map.get("dkqx");
		String dkqx1_year = "";
		String dkqx1_mon = "";
		String dkqx1_day = "";
		String dkqx2_year = "";
		String dkqx2_mon = "";
		String dkqx2_day = "";
		if (!"0".equalsIgnoreCase(dkqxy) && !"".equalsIgnoreCase(dkqxy)) {
			dkqx1_year = dkqx.substring(0, 4);
			dkqx1_mon = dkqx.substring(4, 6);
			dkqx1_day = dkqx.substring(6, 8);
			dkqx2_year = dkqx.substring(9, 13);
			dkqx2_mon = dkqx.substring(13, 15);
			dkqx2_day = dkqx.substring(15);
			map.put("dkqxy", dkqxy);
		} else {
			map.put("dkqxy", "");
		}
		map.put("dkqx1_year", dkqx1_year);
		map.put("dkqx1_mon", dkqx1_mon);
		map.put("dkqx1_day", dkqx1_day);
		map.put("dkqx2_year", dkqx2_year);
		map.put("dkqx2_mon", dkqx2_mon);
		map.put("dkqx2_day", dkqx2_day);

		return map;
	}

	/**
	 * 删除国家助学贷款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*3];
		int j = 0;
		for (int i = 1; i < pkT.length; i++) {
			sqlT[j] = "delete zdgzgx_gjzxdk where xh='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete zgdzdx_hkjzqhhkxyxx where xh='"+pkT[i]+"'";
			j++;
			sqlT[j] = "delete ZGDZDX_ZQXY where xh='"+pkT[i]+"'";
			j++;
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 国家助学贷款查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj",
				"shjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", "性别",
				"身份证号", Base.YXPZXY_KEY+"名称", "行政班号", "贷款金额", "贷款期限", "合同编号", "申请时间", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 国家助学贷款查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTitT() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj", "sfqdhkxy",
				"shjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", "性别",
				"身份证号", Base.YXPZXY_KEY+"名称", "行政班号", "贷款金额", "贷款期限", "合同编号", "申请时间",
				"是否签定还款协议", "审核结果" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 国家助学贷款历史查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdklsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xh", "xm", "xb", "sfzh",
				"xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj" };
		String[] cnList = new String[] { "主键", "学号", "姓名", "性别", "身份证号",
				Base.YXPZXY_KEY+"名称", "行政班号", "贷款金额", "贷款期限", "合同编号", "申请时间" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkResT(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select (case when nvl(shjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,xb,sfzh,xymc,bjmc,sqdkje,dkqx,htbh,sqsj,sfqdhkxy,shjg from view_zdgzgx_gjzxdk where 1=1";
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj", "sfqdhkxy",
				"shjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select (case when nvl(shjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,xb,sfzh,xymc,bjmc,sqdkje,dkqx,htbh,sqsj,shjg from view_zdgzgx_gjzxdk where 1=1";
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj",
				"shjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家助学贷款历史查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdklsRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select xh pk,xh,xm,xb,sfzh,xymc,bjmc,sqdkje,dkqx,htbh,sqsj from zgdzdx_gjzxdk_ls where 1=1";
		String[] colList = new String[] { "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "bjmc", "sqdkje", "dkqx", "htbh", "sqsj" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存国家助学贷款审核信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String shjg = Base.chgNull(model.getShjg(), "", 1);
		String shyj = Base.chgNull(model.getShyj(), "", 1);
		String htmc = Base.chgNull(model.getHtmc(), "", 1);
		String nd = Base.currNd;

		switch (htbh.length()) {
		case 1:
			htbh = "7245-" + nd + "-" + htmc + "-000" + htbh;
			break;
		case 2:
			htbh = "7245-" + nd + "-" + htmc + "-00" + htbh;
			break;
		case 3:
			htbh = "7245-" + nd + "-" + htmc + "-0" + htbh;
			break;
		case 4:
			htbh = "7245-" + nd + "-" + htmc + "-" + htbh;
			break;
		default:
			htbh = "";
			break;
		}

		bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
				"shjg", "shyj", "htbh" }, new String[] { shjg, shyj, htbh },
				"xh", xh, request);

		return bFlag;
	}

	/**
	 * 得到学院合同编号
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public String[] getXyHtbh(String xydm) throws Exception {
		String sql = "select zxhth,zdhth from zgdzdx_htbhfwb where zdhth<>'0000' and xydm=? order by zxhth";
		return dao.getOneRs(sql, new String[] { xydm }, new String[] { "zxhth",
				"zdhth" });
	}

	/**
	 * 得到已分配合同编号
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getYyHtbhList(String xh,String nd)
			throws Exception {
		String sql = "select htbh from zdgzgx_gjzxdk where shjg='通过' and xh<>? and substr(htbh,6,4)=?";
		return dao.getArrayList(sql, new String[] { xh, nd },
				new String[] { "htbh" });
	}

	/**
	 * 得到资料确认书申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getZlqrsSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='资料确认书' and b.xh=? ";// ,nd
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
	 * 得到还款协议申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getHkxyQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='还款协议' and b.xh=? ";// ,nd
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
	 * 得到展期后还款协议申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getZqhHkxyQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='展期后还款协议' and b.xh=? ";// ,nd
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
	 * 得到展期协议申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getZqxyQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='展期协议' and b.xh=? ";// ,nd
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
	 * 保存资料确认书申请信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZlqrsSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtxxzz = Base.chgNull(model.getJtxxzz(), "", 1);
		String csrq = Base.chgNull(model.getCsrq(), "", 1);
		String hyzk = Base.chgNull(model.getHyzk(), "", 1);
		String pomc = Base.chgNull(model.getPomc(), "", 1);
		String polxdh = Base.chgNull(model.getPolxdh(), "", 1);
		String gzdw = Base.chgNull(model.getGzdw(), "", 1);
		String dwdh = Base.chgNull(model.getDwdh(), "", 1);
		String dwdz = Base.chgNull(model.getDwdz(), "", 1);
		String dwyb = Base.chgNull(model.getDwyb(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String lxrxm = Base.chgNull(model.getLxrxm(), "", 1);
		String lxrxb = Base.chgNull(model.getLxrxb(), "", 1);
		String lxrcsrq = Base.chgNull(model.getLxrcsrq(), "", 1);
		String lxrgx = Base.chgNull(model.getLxrgx(), "", 1);
		String lxrlxdh = Base.chgNull(model.getLxrlxdh(), "", 1);
		String lxrdwdz = Base.chgNull(model.getLxrdwdz(), "", 1);
		String emailqq = Base.chgNull(model.getEmailqq(), "", 1);
		String jtyb = Base.chgNull(model.getJtyb(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String fqzy = Base.chgNull(model.getFqzy(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String mqzy = Base.chgNull(model.getMqzy(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);

		bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
				"jtxxzz", "csrq", "hyzk", "pomc", "polxdh", "gzdw", "dwdh",
				"dwdz", "dwyb", "yddh", "htbh", "lxrxm", "lxrxb", "lxrcsrq",
				"lxrgx", "lxrlxdh", "lxrdwdz", "emailqq", "jtyb", "jtdh",
				"fqxm", "fqzy", "fqsfzh", "mqxm", "mqzy", "mqsfzh", "bz",
				"sftxzlqrs" }, new String[] { jtxxzz, csrq, hyzk, pomc, polxdh,
				gzdw, dwdh, dwdz, dwyb, yddh, htbh, lxrxm, lxrxb, lxrcsrq,
				lxrgx, lxrlxdh, lxrdwdz, emailqq, jtyb, jtdh, fqxm, fqzy,
				fqsfzh, mqxm, mqzy, mqsfzh, bz, "是" }, "xh", xh, request);
		return bFlag;
	}

	/**
	 * 得到资料确认书申请表打印信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZlqrsSqb(HashMap<String, String> map,
			HttpServletRequest request) throws Exception {
		map.put("xxmc", "中国地质大学(武汉)");
		map.put("nd", Base.currNd);
		if (map.size() != 2){
		String csrq = map.get("csrq");
		String csrqYear = "";
		String csrqMon = "";
		String csrqDay = "";
		if (csrq.length() == 10) {
			csrqYear = csrq.substring(0, 4);
			csrqMon = csrq.substring(5, 7);
			csrqDay = csrq.substring(8);
		}
		map.put("csrqYear", csrqYear);
		map.put("csrqMon", csrqMon);
		map.put("csrqDay", csrqDay);

		String lxrcsrq = map.get("lxrcsrq");
		String lxrcsrqYear = "";
		String lxrcsrqMon = "";
		String lxrcsrqDay = "";
		if (lxrcsrq!=null && lxrcsrq.length() == 10) {
			lxrcsrqYear = lxrcsrq.substring(0, 4);
			lxrcsrqMon = lxrcsrq.substring(5, 7);
			lxrcsrqDay = lxrcsrq.substring(8);
		}
		map.put("lxrcsrqYear", lxrcsrqYear);
		map.put("lxrcsrqMon", lxrcsrqMon);
		map.put("lxrcsrqDay", lxrcsrqDay);
		}
		return map;
	}

	/**
	 * 保存国家助学贷款信息，成功返回TRUE，反之返回FALSE saveGjzxdkxx ---- 保存国家助学贷款信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model, String xz, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String gzdw = Base.chgNull(model.getGzdw(), "", 1);
		String dwyb = Base.chgNull(model.getDwyb(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String hkczh = Base.chgNull(model.getHkczh(), "", 1);

		String num = dao
				.getOneRs(
						"select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz=? and xxsh='通过'",
						new String[] { xh, xz }, "num");
		if ("0".equalsIgnoreCase(num)) {
			num = dao
					.getOneRs(
							"select count(*) num from zgdzdx_hkjzqhhkxyxx where xh=? and xz=?",
							new String[] { xh, xz }, "num");
			if ("0".equalsIgnoreCase(num)) {
				StandardOperation.insert("zgdzdx_hkjzqhhkxyxx", new String[] {
						"xh", "xz" }, new String[] { xh, xz }, request);
			} else {
				StandardOperation
						.update(
								"zgdzdx_hkjzqhhkxyxx",
								"update zgdzdx_hkjzqhhkxyxx set xxsh='未审核',sqsj=to_char(sysdate,'yyyy-mm-dd') where xh='"
										+ xh + "' and xz='" + xz + "'", request);
			}
			bFlag = StandardOperation.update("zdgzgx_gjzxdk", new String[] {
					"gzdw", "dwyb", "yddh", "hkczh", "sfqdhkxy", "hkxyqssj" },
					new String[] { gzdw, dwyb, yddh, hkczh, "否", "" }, "xh",
					xh, request);
		}
		return bFlag;
	}

	/**
	 * 保存展期协议信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZqxyxx(ZqxyModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String zqnx = Base.chgNull(model.getZqnx(), "", 1);
		String zqhhkrq = Base.chgNull(model.getZqhhkrq(), "", 1);

		String sHave = isZqxydatacf(xh);

		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zgdzdx_zqxy", new String[] {
					"xh", "zqnx", "zqhhkrq" },
					new String[] { xh, zqnx, zqhhkrq }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zgdzdx_zqxy", new String[] {
					"zqnx", "shjg", "zqhhkrq" }, new String[] { zqnx, "未审核",
					zqhhkrq }, "xh", xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断展期协议是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isGjzxdkdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isZqxydatacf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgdzdx_zqxy where xh = ? and shjg in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgdzdx_zqxy where xh = ?";
			num = dao.getOneRs(sql, new String[] { xh }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 批量修改展期协议审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZqxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*2];
		int j = 0;
		if ("通过".equalsIgnoreCase(shjg)) {
			for (int i = 1; i < pkT.length; i++) {
				String[] rs = dao
						.getOneRs(
								"select x.*,to_char(add_months(to_date(replace(substr(x.zqhbyny,1,7),'-',''),'yyyymm'),24),'yyyymm')||'01-'||substr(x.zqrq,1,6)||'01' zqqx from (SELECT (case b.zqnx WHEN '0' THEN to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),months_between(to_date(substr(b.zqrq,1,6),'yyyymm'),to_date(substr(b.dkqx,10,6),'yyyy-mm'))+1),'yyyy-mm-dd') ELSE to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),b.zqnx*12+1),'yyyy-mm-dd') END) zqhbyny,b.zqnx,b.zqrq,(SELECT z.dkye FROM VIEW_ZGDZDX_ZQXY z WHERE z.xh=b.xh) zqje,(SELECT z.gzdw FROM Zdgzgx_Gjzxdk z WHERE z.xh=b.xh) gzdw from view_zgdzdx_zqxy b where b.xh = ?) x",
								new String[] { pkT[i] },
								new String[] { "zqhbyny", "zqnx", "zqrq",
										"zqje", "gzdw", "zqqx" });
				
				if (null != rs) {
					sqlT[j] = "update zgdzdx_zqxy set shjg='" + shjg
							+ "' where xh='" + pkT[i] + "'";
					j++;
					sqlT[j] = "update Zdgzgx_Gjzxdk set zqsj='" + rs[1]
							+ "',zqqx='" + rs[5] + "',zqje='" + rs[3]
							+ "',zqszdw='" + rs[4] + "' where xh='" + pkT[i]
							+ "'";
					j++;
				}
			}
		} else {
			for (int i = 1; i < pkT.length; i++) {
				sqlT[j] = "update zgdzdx_zqxy set shjg='" + shjg
						+ "' where xh='" + pkT[i] + "'";
				j++;
				sqlT[j] = "update Zdgzgx_Gjzxdk set zqsj='',zqqx='',zqje='',zqszdw='' where xh='"
						+ pkT[i] + "'";
				j++;
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改还款协议审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modHkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*2];
		String da = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') da from dual", new String[]{}, "da");
		int j = 0;
		if ("通过".equalsIgnoreCase(shjg)) {
//			addGjzxdkLsxx(cbVal, request);
			for (int i = 1; i < pkT.length; i++) {
				sqlT[j] = "update zgdzdx_hkjzqhhkxyxx set xxsh='" + shjg
						+ "',sqsj='" + da + "' where xh='" + pkT[i]
						+ "' and xz='1'";
				j++;
				sqlT[j] = "update Zdgzgx_Gjzxdk set sfqdhkxy='是', hkxyqssj='"
						+ da
						+ "', hkqzsj=(SELECT substr(byny,0,4)+2||'0701-'||substr(DKQX,10,6)||'01' FROM view_zdgzgx_gjzxdk where xh='"
						+ pkT[i]
						+ "'), hkfs='(二)等额本金还款法' where xh='"
						+ pkT[i]
						+ "'";
				j++;
			}
		} else {
			for (int i = 1; i < pkT.length; i++) {
				sqlT[j] = "update zgdzdx_hkjzqhhkxyxx set xxsh='" + shjg
						+ "' where xh='" + pkT[i] + "' and xz='1'";
				j++;
				sqlT[j] = "update Zdgzgx_Gjzxdk set sfqdhkxy='否', hkxyqssj='', hkqzsj='', hkfs='' where xh='"
						+ pkT[i] + "'";
				j++;
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改展期后还款协议审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZqhhkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		int j = 0;
		if ("通过".equalsIgnoreCase(shjg)) {
//			addGjzxdkLsxx(cbVal, request);
			for (int i = 1; i < pkT.length; i++) {
				String[] rs = dao
						.getOneRs(
								"select x.*,to_char(add_months(to_date(replace(substr(x.zqhbyny,1,7),'-',''),'yyyymm'),24),'yyyymm')||'01-'||substr(x.zqrq,1,6)||'01' zqqx from (SELECT (case b.zqnx WHEN '0' THEN to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),months_between(to_date(substr(b.zqrq,1,6),'yyyymm'),to_date(substr(b.dkqx,10,6),'yyyy-mm'))+1),'yyyy-mm-dd') ELSE to_char(add_months(to_date(b.byny,'yyyy-mm-dd'),b.zqnx*12+1),'yyyy-mm-dd') END) zqhbyny,b.zqrq from view_zgdzdx_zqxy b where b.xh = ?) x",
								new String[] { pkT[i] }, new String[] {
										"zqhbyny", "zqrq", "zqqx" });
				
				if (null != rs) {
					sqlT[j] = "update zgdzdx_hkjzqhhkxyxx set xxsh='" + shjg
							+ "' where xh='" + pkT[i] + "' and xz='2'";
					j++;
				}
			}
		} else {
			for (int i = 1; i < pkT.length; i++) {
				sqlT[j] = "update zgdzdx_hkjzqhhkxyxx set xxsh='" + shjg
						+ "' where xh='" + pkT[i] + "' and xz='2'";
				j++;
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * @author ZhouMi
	 * @describe 得到奖助学金项目列表
	 */
	public synchronized List<HashMap<String, String>> getJzxjxmList()
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select xmdm,xmmc from jzxj_xmdmb order by xmdm";
		return dao.getList(sql, new String[] {},
				new String[] { "xmdm", "xmmc" });
	}

	/**
	 * @author ZhouMi
	 * @describe 保存奖助学金具体人数
	 */
	public void saveJzxjjtrs(String[] pks, String[] szrs, String[] sfyx,
			HttpServletRequest request) throws Exception {
		String[] sql = new String[pks.length];
		for (int i = 0; i<pks.length; i++){
			sql[i] = "update jzxj_rswfb2 set szrs='"+szrs[i]+"',sfyx='"+sfyx[i]+"' where xmdm||xydm='"+pks[i]+"'";
		}
		dao.runBatch(sql);
	}
	
	/**
	 * 保存模版格式信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean insertTemplate(String topstr, String leftstr, String fontstr, String tmpname, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String con = dao.getOneRs(
				"select count(*) con from LATEINF where name=?",
				new String[] { tmpname }, "con");

		if ("0".equalsIgnoreCase(con)){
			bFlag = StandardOperation.insert("LATEINF", new String[] {
					"topstr", "leftstr", "fontstr", "name" }, new String[] { topstr,
					leftstr, fontstr, tmpname }, request);
		} else {
			bFlag = StandardOperation.update("LATEINF", new String[] {
					"topstr", "leftstr", "fontstr" }, new String[] { topstr, leftstr,
					fontstr }, "name", tmpname, request);
		}
		return bFlag;
	}
	
	/**
	 * 得到模版格式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTeplateInf(String name) throws Exception {
		HashMap<String, String> hs = new HashMap<String, String>();
		String sql = "select name,topstr,leftstr,fontstr from lateinf where name=?";
		hs = dao.getMap(sql, new String[]{name}, new String[]{"name","topstr","leftstr","fontstr"});
    	return hs;
	}
	
	/**
	 * 获取国家助学贷款借据打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkju(String xh,String qs,String jjrq) throws Exception {
		HashMap<String, String> hs = new HashMap<String, String>();
		String sql = "select xm,khh,dkll,htbh,'556057544207' zh,hkczh kkzh,NVL((case (select '"
				+ qs
				+ "' from dual) when '1' then fk_xn1_je when '2' then fk_xn2_je when '3' then fk_xn3_je when '4' then fk_xn4_je end),'0') bqdkje,dkqx,'"
				+ jjrq
				+ "'||substr(dkqx,9,9) bcjkqx,'国家助学贷款' jkyt,'中国地质大学(武汉)' hm,'中行东湖开发区支行' khyh from view_ZDGZGX_GJZXDK where xh=?";
		hs = dao.getMap(sql, new String[] { xh }, new String[] { "xm", "khh",
				"dkll", "htbh", "zh", "kkzh", "bqdkje", "dkqx", "bcjkqx",
				"jkyt", "hm", "khyh" });

		hs.put("year", jjrq.substring(0, 4));
		hs.put("mon", jjrq.substring(4, 6));
		hs.put("day", jjrq.substring(6));
		
		hs.put("qs", qs);

		String bqdkje = hs.get("bqdkje");
		
		String sqlT = "{call pro_Disp_dxje(?,?)}";
		String[] temp = dao.getProVal(sqlT, new String[] { bqdkje }, new int[] { 2 });
		hs.put("dkje_dx", temp[0]);
		
		String[] sL = new String[]{"","","","","","","",""};
    	
    	sL[7-bqdkje.length()] = "￥";
    	
    	for (int i = 0; i < bqdkje.length(); i++){
    		sL[8-bqdkje.length()+i] = bqdkje.substring(i, i+1);
    	}
    	hs.put("qianW", sL[0]);
    	hs.put("baiW", sL[1]);
    	hs.put("shiW", sL[2]);
    	hs.put("wan", sL[3]);
    	hs.put("qian", sL[4]);
    	hs.put("bai", sL[5]);
    	hs.put("shi", sL[6]);
    	hs.put("yuan", sL[7]);
    	hs.put("jiao", "0");
    	hs.put("fen", "0");
    	
    	hs.put("yearS1", hs.get("dkqx").substring(0, 4));
		hs.put("monS1", hs.get("dkqx").substring(4, 6));
		hs.put("dayS1", hs.get("dkqx").substring(6, 8));
		hs.put("yearS2", hs.get("dkqx").substring(9, 13));
		hs.put("monS2", hs.get("dkqx").substring(13, 15));
		hs.put("dayS2", hs.get("dkqx").substring(15));
		hs.put("yearB1", jjrq.substring(0, 4));
		hs.put("monB1", jjrq.substring(4, 6));
		hs.put("dayB1", jjrq.substring(6));
		hs.put("yearB2", hs.get("dkqx").substring(9, 13));
		hs.put("monB2", hs.get("dkqx").substring(13, 15));
		hs.put("dayB2", hs.get("dkqx").substring(15));
		
		return hs;
	}	
	/**
	 * 获取学生奖助学金相关信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJzxjInfo(String pkValue) {
		String pk = "xn||xq||xmdm||xh";
		HashMap<String, String> hs = new HashMap<String, String>();
		String sql = "select xm,xn,xmmc,jxjbh from view_jzxj_xssqb where "+pk+"=?";
		hs = dao.getMap(sql, new String[]{pkValue}, new String[]{"xm","xn","xmmc","jxjbh"});
    	return hs;
	}
	/**
	 * 删除综合测评信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZhcpxx(String[] pkT) throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete jzxj_zhcpxx where xn||xq||xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 综合测评查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhcpRes(QueryModel queryModel,
			HttpServletRequest request, XszzZgdzdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select pk,xn,xqmc,xh,xm,nj,xymc,zymc,bjmc,zhszzcj,zhszpm from (select rownum r,xn||xq||xh pk,xn,xqmc,xh,xm,nj,xymc,zymc,bjmc,zhszzcj,zhszpm from view_jzxj_zhcpxx where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "zhszzcj", "zhszpm" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 综合测评信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZhcpResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String sql = "select count(*) num from view_jzxj_zhcpxx where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取综合测评详细信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhcpxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xq,xqmc,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,zhszzcj,zhszpm,bz from view_jzxj_zhcpxx where xn||xq||xh = ?";
		String[] colList = new String[] { "xn", "xq", "xqmc", "xh", "xm", "xb",
				"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"zhszzcj", "zhszpm", "bz" };
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
	 * 保存综合测评详细信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpxx(ZhcpxxModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xq = Base.chgNull(model.getXq(), "", 1);
		String zhszzcj = Base.chgNull(model.getZhszzcj(), "", 1);
		String zhszpm = Base.chgNull(model.getZhszpm(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);

		boolean sHave = isZhcpDataCf(xh, xq, xn);
		if (sHave) {
			bFlag = StandardOperation.insert("jzxj_zhcpxx", new String[] {
					"xn", "xq", "xh", "zhszzcj", "zhszpm", "bz" },
					new String[] { xn, xq, xh, zhszzcj, zhszpm, bz }, request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("jzxj_zhcpxx", new String[] {
						"zhszzcj", "zhszpm", "bz" }, new String[] { zhszzcj,
						zhszpm, bz }, "xn||xq||xh", xn + xq + xh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}
	
	/**
	 * 判断综合测评是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isZhcpDataCf(String xh, String xq, String xn) throws Exception {
		String sql = "select count(*) num from jzxj_zhcpxx where xh = ? and xq = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[] { xh, xq, xn }, "num");
		return "0".equalsIgnoreCase(num);
	}
	

	/**
	 * 获取展期还款详细信息
	 * 
	 * @return
	 * @author luojw
	 */
	public HashMap<String, String> getZqxyInfo(String xh) {
		
		String sql = "select * from zgdzdx_zqxy where xh = ?";
		String[] inputValue = new String[] { xh };
		String[] outputValue = new String[] { "zqnx", "shjg", "zqhhkrq" };
		
		HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
		
		return map;
	}
	
	
	/**
	 * 删除新生助学贷款申请时间设置
	 * 触发 trigger delXssjsz
	 * 初始化时间设置
	 * @return
	 * @throws Exception
	 */
	public boolean xssjszReset() throws Exception {
		String sql = "delete from xszz_zgdzdx_xssjsz";
		
		return dao.runUpdate(sql, new String[] {});
	}

	
	/**
	 * 新生助学贷款申请批量设置
	 * @return
	 * @throws SQLException
	 */
	public boolean xssjszSave(String[] pkValue,String kssj,String jssj) throws SQLException {
		
		String[] sqlArr = new String[pkValue.length];
		
		for (int i = 0 ; i < pkValue.length ; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("update xszz_zgdzdx_xssjsz set kssj='");
			sb.append(kssj);
			sb.append("',jssj='");
			sb.append(jssj);
			sb.append("' where xydm='");
			sb.append(pkValue[i]);
			sb.append("'");
			
			sqlArr[i] = sb.toString();
		}
		
		int[] result = dao.runBatch(sqlArr); 
		return dao.checkBatch(result);
	}


	/**
	 * 是新生返回ID，不是返回NULL
	 * @param xh
	 * @return
	 */
	public String isXs(String xh) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from xszz_zgdzdx_xssz where");
		sb.append("(select to_number(replace(rxrq,'-')) rxrq from xsxxb where xh='");
		sb.append(xh);
		sb.append("') ");
		sb.append("between to_number(qssj) and to_number(jssj)");
		
		return dao.getOneRs(sb.toString(), new String[] {}, "id");
	}
	
	
	/**
	 * 老生助学贷款申请时间段
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getLsSqsj(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from zgdzdx_zxdk_sjb where  ((select");
		sb.append(" to_char(sysdate,'yyyy-mm-dd') from dual) between kssj and jssj)");
		sb.append(" and xmmc='助学贷款申请' and ");
		sb.append("xydm=(select a.xydm from view_xsjbxx a where a.xh=? ");
		sb.append("and not exists (select 1 from zdgzgx_gjzxdk where xh = a.xh))");
		
		
		return dao.getOneRs(sb.toString(), new String[] {xh}, "xmmc");
		
	}
	
	
	/**
	 * 新生助学贷款申请时间段
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getXsSqsj(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from xszz_zgdzdx_xssjsz where ");
		sb.append(" ((select to_char(sysdate,'yyyymmdd') from dual) between kssj and jssj)");
		sb.append(" and xydm=(select a.xydm from view_xsjbxx a where a.xh=?");
		sb.append("  and not exists (select 1 from zdgzgx_gjzxdk where xh = a.xh))");
		return dao.getOneRs(sb.toString(), new String[] {xh}, "xydm");
		
	}
	
	/**
	 * 获取老生放款次数
	 * @param xh
	 * @return
	 */
	public String getLsFkcs(String xh) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xz-((select dqnd from xtszb)-nj ) ");
		sql.append("fkcs from xsxxb where xh=?");
		
		return dao.getOneRs(sql.toString(), new String[] {xh}, "fkcs");
	}
	
	
	/**
	 * 国家助学贷款的贷款期限
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getDkqx(String xh) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.dknx,a.dknx*12 dkqxy,");
		sql.append("case when a.dkkssj is not null and a.dkjssj is not null then a.dkkssj||'-'||a.dkjssj end dkqx");
		sql.append(" from (select dknx,");
		sql.append("case when dkkssj is not null then replace(dkkssj,'-','') end dkkssj ,");
		sql.append("case when dkkssj is not null then to_number(substr(dkkssj,0,4))+dknx||(substr(dkkssj,6,2)) ||(substr(dkkssj,9,2)) end dkjssj");
		sql.append(" from zgdzdx_xsdknxb where bjdm=(select bjdm from view_xsjbxx where xh=?))a");
		

		String[] je = getZxdkJe(xh);

		if (null != je && je.length>0) {
			map.put("zddkje", je[0]);
			map.put("mndkje", je[1]);
		}
		
		map.putAll(dao.getMap(sql.toString(), new String[] {xh}, new String[] {"dknx","dkqxy","dkqx"}));
		return  map;
	}
	
	/**
	 * 生源地贷款查询表头_武汉铁路
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit_whtl() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm", "xymc",
				"zymc", "bjmc", "lxdh", "htje", "bz" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名", "学院名称",
				"专业名称", "行政班号", "联系电话", "金额", "备注" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 生源地贷款查询结果——武汉铁路
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes_whtl(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		boolean isFdy=false;
		boolean isBzr=false;
		HttpSession session=request.getSession();
		if(session.getAttribute("isFdy")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("isFdy").toString());
		}
		if(session.getAttribute("isBzr")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("isBzr").toString());
		}
		
		
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";
		String view_whtl_syddk="(select b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.* "+
		" from whtl_syddk a left join view_xsjbxx b on a.xh=b.xh)";
		sql = "select rownum r, nd||xh pk,nd,xh,xm,xymc,zymc,bjmc,lxdh,htje,bz from "+view_whtl_syddk+" a  where ";
		if(isFdy||isBzr){//添加辅导员或班主任权限
			sql+=" exists (select 1 from "+getFdyBzrQxBjView(request)+" b where b.bjdm=a.bjdm) ";
		}else{
			sql+=" 1=1 ";
		}
		String[] colList = new String[] { "pk", "nd", "xh", "xm", "xymc",
				"zymc", "bjmc", "lxdh", "htje", "bz" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
//		resList = dao.rsToVator(sql + whereSql, values != null ? values
//				.toArray(new String[0]) : new String[] {}, colList);
		resList = CommonQueryDAO.commonPageQuery(queryModel.getPages(), sql+whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 获取辅导员班主任的班级权限的视图
	 * @param request
	 * @return
	 */
	private String getFdyBzrQxBjView(HttpServletRequest request){
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("isFdy")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("isFdy").toString());
		}
		if(session.getAttribute("isBzr")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("isBzr").toString());
		}
		String userName=session.getAttribute("userName").toString();//用户名
		String sql_fdy="";
		if(isFdy&&isBzr){//同时为辅导员和班主任
			sql_fdy="select bjdm from fdybjb where zgh='"+userName+"' union select bjdm from bzrbbb where zgh='"+userName+"'";
		}else if(isFdy){//辅导员
			sql_fdy="select bjdm from fdybjb where zgh='"+userName+"'";
		}else{//班主任
			sql_fdy="select bjdm from bzrbbb where zgh='"+userName+"'";
		}
		return "("+sql_fdy+")";
	}
	
	/**
	 * 删除生源地贷款信息_武汉铁路
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delSyddk_whtl(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if(Base.isNull(pkT[i])){
				sqlT[i] = "delete whtl_syddk where 1=2";
			}else{
				sqlT[i] = "delete whtl_syddk where nd||xh='"+pkT[i].trim()+"'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 保存生源地贷款信息_武汉铁路
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String nd =Base.currNd;
		String xh = myForm.getXh();
		String htje = myForm.getHtje();
		String dkcs = myForm.getDkcs();
		String lxdh = myForm.getLxdh();
		String szzzx = myForm.getSzzzx();
		String xzzzx = myForm.getXzzzx();
		String htbh = myForm.getHtbh();
		String htzt = myForm.getHtzt();
		String dljg = myForm.getDljg();
		String dljswd = myForm.getDljswd();
		String grzh = myForm.getGrzh();
		String dkqx = myForm.getDkqx();
		String ffrq = myForm.getFfrq();
		String dqrq = myForm.getDqrq();
		String bz = myForm.getBz();

		sql.append(" insert into whtl_syddk(nd,xh,htje,dkcs,lxdh,szzzx,xzzzx,htbh,htzt,dljg,dljswd,grzh,dkqx,ffrq,dqrq,bz) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String[] inputV = {nd,xh,htje,dkcs,lxdh,szzzx,xzzzx,htbh,htzt,dljg,dljswd,grzh,dkqx,ffrq,dqrq,bz};
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 修改生源地贷款信息_武汉铁路
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String pkValue = myForm.getPk()[0];
		String nd =myForm.getNd();
		String xh = myForm.getXh();
		String htje = myForm.getHtje();
		String dkcs = myForm.getDkcs();
		String lxdh = myForm.getLxdh();
		String szzzx = myForm.getSzzzx();
		String xzzzx = myForm.getXzzzx();
		String htbh = myForm.getHtbh();
		String htzt = myForm.getHtzt();
		String dljg = myForm.getDljg();
		String dljswd = myForm.getDljswd();
		String grzh = myForm.getGrzh();
		String dkqx = myForm.getDkqx();
		String ffrq = myForm.getFfrq();
		String dqrq = myForm.getDqrq();
		String bz = myForm.getBz();
		
		sql.append(" update whtl_syddk  ");
		sql.append(" set htje=?,dkcs=?,lxdh=?,szzzx=?,xzzzx=?,htbh=?,htzt=?,dljg=?,dljswd=?,grzh=?,dkqx=?,ffrq=?,dqrq=?,bz=? ");
		sql.append(" where nd||xh= ? ");
		
		String[] inputV = {htje,dkcs,lxdh,szzzx,xzzzx,htbh,htzt,dljg,dljswd,grzh,dkqx,ffrq,dqrq,bz, pkValue};
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 获取生源地贷款_武汉铁路
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneSyddk_whtl(XszzZgdzdxActionForm myForm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String pkValue = myForm.getPk()[0];
		sql.append(" select a.nd||a.xh pkValue,a.* from view_whtl_syddk a ");
		sql.append(" where nd||xh= ? ");
		String colList[] = { "pkValue","xh","xm", "nd", "xb", "xymc", "nj", "zymc", "bjmc","sfzh","xz","rxrq","bysj",
				"htje","dkcs","lxdh","szzzx","xzzzx","htbh","htzt","dljg","dljswd","grzh","dkqx","ffrq","dqrq","bz" };
		return dao.getMap(sql.toString(), new String[] { pkValue }, colList);
	}
}
