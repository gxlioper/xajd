package xgxt.xszz.zgmsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * Title: 学生工作管理系统 Description: 中国美术学院学生资助DAO Copyright: Copyright (c) 2008
 * Company: zfsoft Author: 周觅 Version: 1.0 Time: 2008-12-16
 */
public class XszzZgmsxyDAO{
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
		String xh = "%" + DealString.toGBK(queryModel.getXh()) + "%";
		String xm = "%" + DealString.toGBK(queryModel.getXm()) + "%";
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String sffk = queryModel.getSffk();
		String sfzh = "%" + DealString.toString(queryModel.getSfzh()) + "%";
		String nj = queryModel.getNj();
		String xysh = DealString.toGBK(queryModel.getXysh());
		String xxsh = DealString.toGBK(queryModel.getXxsh());
		String mzpyjg = DealString.toGBK(queryModel.getMzpyjg());
		String yxsh = DealString.toGBK(queryModel.getYxsh());//院系审核

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
		if (!StringUtils.isNull(queryModel.getXh())) {
			whereSql.append(" and xh like ?");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(queryModel.getXm())) {
			whereSql.append(" and xm like ?");
			values.add(xm);
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
		if (!StringUtils.isNull(mzpyjg)) {
			whereSql.append(" and mzpyjg = ?");
			values.add(mzpyjg);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}// end if
		if (!StringUtils.isNull(queryModel.getSfzh())) {
			whereSql.append(" and sfzh like ?");
			values.add(sfzh);
		}// end if
		if (!StringUtils.isNull(sffk)) {
			if ("1".equalsIgnoreCase(sffk)) {
				whereSql.append(" and hth is not null");
			} else {
				whereSql.append(" and hth is null");
			}
		}// end if
		if (!StringUtils.isNull(yxsh)) {
			whereSql.append(" and yxsh = ?");
			values.add(yxsh);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny,a.qsdh ssdh,(case a.xz when '3' then '专科' else '本科' end) xl from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny", "ssdh", "xl" };
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
	 * 学生申请-获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStudent(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.xymc,a.xydm,a.xz,a.zymc,a.zydm,a.bjmc,a.bjdm,a.sfzh,a.csrq,a.mzmc,a.zzmmmc,a.rxrq,a.byny,a.lxdh,a.sjhm,a.jtyb,a.jtdz from view_xsxxb a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq",
				"mzmc", "zzmmmc", "rxrq", "byny", "lxdh","sjhm","jtyb","jtdz" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				//System.out.println(colList[i]+":"+sLen[i] != null ? sLen[i].toString(): "");
				//System.out.println("------");
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
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
		String sql = "select xn,xh,xm,xb,sfzh,csny,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,rxqhk,byxx,jtrks,jtrjnsr,qs,xslxdh,grtc,sfgc,sfdq,sflszn,jt_xxtxdz,jt_yzbm,jt_lxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk,jtcy6_xm,jtcy6_nl,jtcy6_gx,jtcy6_gzdw,jtcy6_zy,jtcy6_sr,jtcy6_jkzk,jtqnsr,xybxnyhzzqk,jtzszrzhqk,jtzstfywsj,jtcyycjssldl,jtcysyqk,jtqzqk,qtqk,dddbbz,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqly,sqsj,mzpyjg,csly,xysh,xyshyj,xxsh,xxshyj from view_zgmsxy_knsxx where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"csny", "mzmc", "zzmmmc", "nj", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "rxqhk", "byxx", "jtrks", "jtrjnsr", "qs",
				"xslxdh", "grtc", "sfgc", "sfdq", "sflszn", "jt_xxtxdz",
				"jt_yzbm", "jt_lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
				"jtcy5_sr", "jtcy5_jkzk", "jtcy6_xm", "jtcy6_nl", "jtcy6_gx",
				"jtcy6_gzdw", "jtcy6_zy", "jtcy6_sr", "jtcy6_jkzk", "jtqnsr",
				"xybxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjssldl",
				"jtcysyqk", "jtqzqk", "qtqk", "dddbbz", "mzbm_xxtxdz",
				"mzbm_yzbm", "mzbm_lxdh", "sqly", "sqsj", "mzpyjg", "csly",
				"xysh", "xyshyj", "xxsh", "xxshyj" };
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
	public String getKnsrdSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from COMMON_NEW_GJZXDK_SJB a,view_xsjbxx b where a.xmmc='困难生' and b.xh=? and a.xydm=b.xydm ";
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
		String rxqhk = Base.chgNull(model.getRxqhk(), "", 1);
		String byxx = Base.chgNull(model.getByxx(), "", 1);
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String qs = Base.chgNull(model.getQs(), "", 1);
		String xslxdh = Base.chgNull(model.getXslxdh(), "", 1);
		String grtc = Base.chgNull(model.getGrtc(), "", 1);
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
		String jtcy6_xm = Base.chgNull(model.getJtcy6_xm(), "", 1);
		String jtcy6_nl = Base.chgNull(model.getJtcy6_nl(), "", 1);
		String jtcy6_gx = Base.chgNull(model.getJtcy6_gx(), "", 1);
		String jtcy6_gzdw = Base.chgNull(model.getJtcy6_gzdw(), "", 1);
		String jtcy6_zy = Base.chgNull(model.getJtcy6_zy(), "", 1);
		String jtcy6_sr = Base.chgNull(model.getJtcy6_sr(), "", 1);
		String jtcy6_jkzk = Base.chgNull(model.getJtcy6_jkzk(), "", 1);
		String jtqnsr = Base.chgNull(model.getJtqnsr(), "", 1);
		String xybxnyhzzqk = Base.chgNull(model.getXybxnyhzzqk(), "", 1);
		String jtzszrzhqk = Base.chgNull(model.getJtzszrzhqk(), "", 1);
		String jtzstfywsj = Base.chgNull(model.getJtzstfywsj(), "", 1);
		String jtcyycjssldl = Base.chgNull(model.getJtcyycjssldl(), "", 1);
		String jtcysyqk = Base.chgNull(model.getJtcysyqk(), "", 1);
		String jtqzqk = Base.chgNull(model.getJtqzqk(), "", 1);
		String qtqk = Base.chgNull(model.getQtqk(), "", 1);
		String dddbbz = Base.chgNull(model.getDddbbz(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(model.getMzbm_xxtxdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(model.getMzbm_yzbm(), "", 1);
		String mzbm_lxdh = Base.chgNull(model.getMzbm_lxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsrdDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zgmsxy_knsxx", new String[] {
					"xn", "xh", "rxqhk", "byxx", "jtrks", "jtrjnsr", "qs",
					"xslxdh", "grtc", "sfgc", "sfdq", "sflszn", "jt_xxtxdz",
					"jt_yzbm", "jt_lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy",
					"jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr",
					"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
					"jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk",
					"jtcy6_xm", "jtcy6_nl", "jtcy6_gx", "jtcy6_gzdw",
					"jtcy6_zy", "jtcy6_sr", "jtcy6_jkzk", "jtqnsr",
					"xybxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjssldl",
					"jtcysyqk", "jtqzqk", "qtqk", "dddbbz", "mzbm_xxtxdz",
					"mzbm_yzbm", "mzbm_lxdh", "sqly" }, new String[] { xn, xh,
					rxqhk, byxx, jtrks, jtrjnsr, qs, xslxdh, grtc, sfgc, sfdq,
					sflszn, jt_xxtxdz, jt_yzbm, jt_lxdh, jtcy1_xm, jtcy1_nl,
					jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk,
					jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
					jtcy2_sr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
					jtcy3_gzdw, jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr,
					jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
					jtcy5_zy, jtcy5_sr, jtcy5_jkzk, jtcy6_xm, jtcy6_nl,
					jtcy6_gx, jtcy6_gzdw, jtcy6_zy, jtcy6_sr, jtcy6_jkzk,
					jtqnsr, xybxnyhzzqk, jtzszrzhqk, jtzstfywsj, jtcyycjssldl,
					jtcysyqk, jtqzqk, qtqk, dddbbz, mzbm_xxtxdz, mzbm_yzbm,
					mzbm_lxdh, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zgmsxy_knsxx", new String[] {
					"rxqhk", "byxx", "jtrks", "jtrjnsr", "qs", "xslxdh",
					"grtc", "sfgc", "sfdq", "sflszn", "jt_xxtxdz", "jt_yzbm",
					"jt_lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy",
					"jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr",
					"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
					"jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk",
					"jtcy6_xm", "jtcy6_nl", "jtcy6_gx", "jtcy6_gzdw",
					"jtcy6_zy", "jtcy6_sr", "jtcy6_jkzk", "jtqnsr",
					"xybxnyhzzqk", "jtzszrzhqk", "jtzstfywsj", "jtcyycjssldl",
					"jtcysyqk", "jtqzqk", "qtqk", "dddbbz", "mzbm_xxtxdz",
					"mzbm_yzbm", "mzbm_lxdh", "sqly", "sqsj", "mzpyjg", "csly",
					"xysh", "xyshyj", "xxsh", "xxshyj" }, new String[] { rxqhk,
					byxx, jtrks, jtrjnsr, qs, xslxdh, grtc, sfgc, sfdq, sflszn,
					jt_xxtxdz, jt_yzbm, jt_lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm,
					jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr,
					jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
					jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
					jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk,
					jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
					jtcy5_sr, jtcy5_jkzk, jtcy6_xm, jtcy6_nl, jtcy6_gx,
					jtcy6_gzdw, jtcy6_zy, jtcy6_sr, jtcy6_jkzk, jtqnsr,
					xybxnyhzzqk, jtzszrzhqk, jtzstfywsj, jtcyycjssldl,
					jtcysyqk, jtqzqk, qtqk, dddbbz, mzbm_xxtxdz, mzbm_yzbm,
					mzbm_lxdh, sqly, now, "未审核", "", "未审核", "", "未审核", "" },
					"xn||xh", xn + xh, request);
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
		String sql = "select count(*) num from zgmsxy_knsxx where xh = ? and xn = ? and xxsh in ('困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgmsxy_knsxx where xh = ? and xn = ?";
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
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("rxqhk", Base.chgNull(model.getRxqhk(), "", 1));
		stuList.put("byxx", Base.chgNull(model.getByxx(), "", 1));
		stuList.put("jtrks", Base.chgNull(model.getJtrks(), "", 1));
		stuList.put("jtrjnsr", Base.chgNull(model.getJtrjnsr(), "", 1));
		stuList.put("qs", Base.chgNull(model.getQs(), "", 1));
		stuList.put("xslxdh", Base.chgNull(model.getXslxdh(), "", 1));
		stuList.put("grtc", Base.chgNull(model.getGrtc(), "", 1));
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
		stuList.put("jtcy6_xm", Base.chgNull(model.getJtcy6_xm(), "", 1));
		stuList.put("jtcy6_nl", Base.chgNull(model.getJtcy6_nl(), "", 1));
		stuList.put("jtcy6_gx", Base.chgNull(model.getJtcy6_gx(), "", 1));
		stuList.put("jtcy6_gzdw", Base.chgNull(model.getJtcy6_gzdw(), "", 1));
		stuList.put("jtcy6_zy", Base.chgNull(model.getJtcy6_zy(), "", 1));
		stuList.put("jtcy6_sr", Base.chgNull(model.getJtcy6_sr(), "", 1));
		stuList.put("jtcy6_jkzk", Base.chgNull(model.getJtcy6_jkzk(), "", 1));
		stuList.put("jtqnsr", Base.chgNull(model.getJtqnsr(), "", 1));
		stuList.put("xybxnyhzzqk", Base.chgNull(model.getXybxnyhzzqk(), "", 1));
		stuList.put("jtzszrzhqk", Base.chgNull(model.getJtzszrzhqk(), "", 1));
		stuList.put("jtzstfywsj", Base.chgNull(model.getJtzstfywsj(), "", 1));
		stuList.put("jtcyycjssldl", Base
				.chgNull(model.getJtcyycjssldl(), "", 1));
		stuList.put("jtcysyqk", Base.chgNull(model.getJtcysyqk(), "", 1));
		stuList.put("jtqzqk", Base.chgNull(model.getJtqzqk(), "", 1));
		stuList.put("qtqk", Base.chgNull(model.getQtqk(), "", 1));
		stuList.put("dddbbz", Base.chgNull(model.getDddbbz(), "", 1));
		stuList.put("mzbm_xxtxdz", Base.chgNull(model.getMzbm_xxtxdz(), "", 1));
		stuList.put("mzbm_yzbm", Base.chgNull(model.getMzbm_yzbm(), "", 1));
		stuList.put("mzbm_lxdh", Base.chgNull(model.getMzbm_lxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("csly", Base.chgNull(model.getCsly(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));

		String mzpyjg = Base.chgNull(model.getMzpyjg(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);

		if ("".equalsIgnoreCase(mzpyjg)) {
			mzpyjg = "未审核";
		}
		if ("".equalsIgnoreCase(xysh)) {
			xysh = "未审核";
		}
		if ("".equalsIgnoreCase(xxsh)) {
			xxsh = "未审核";
		}

		if (mzpyjg.equalsIgnoreCase(xysh)) {
			stuList.put("tj_xy", "1");
		} else {
			stuList.put("tj_xy", "0");
		}
		if (xysh.equalsIgnoreCase(xxsh)) {
			stuList.put("xy_xx", "1");
		} else {
			stuList.put("xy_xx", "0");
		}

		stuList.put("mzpyjg", mzpyjg);
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
				sqlT[i] = "delete zgmsxy_knsxx where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete zgmsxy_knsxx where xn||xh='" + pkT[i]
						+ "' and xxsh not in ('困难','特殊困难')";
			}
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
					sqlT[i] = "update zgmsxy_knsxx set xxsh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgmsxy_knsxx set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgmsxy_knsxx set mzpyjg='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				}
			} else {
				if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgmsxy_knsxx set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update zgmsxy_knsxx set mzpyjg='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='未审核'";
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
				"sfzh", "xymc", "bjmc", "sqsj", "mzpyjg", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学年", "学号", "姓名",
				"身份证号", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "评议结果", Base.YXPZXY_KEY+"审核", "学校审核" };
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
			sql = "select (case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpyjg,xysh,xxsh from view_zgmsxy_knsxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpyjg,xysh,xxsh from view_zgmsxy_knsxx where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpyjg,xysh,xxsh from view_zgmsxy_knsxx where xysh in ('困难','特殊困难')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "mzpyjg", "xysh", "xxsh" };

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
		String mzpyjg = Base.chgNull(model.getMzpyjg(), "", 1);
		String csly = Base.chgNull(model.getCsly(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isKnsrdDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zgmsxy_knsxx", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zgmsxy_knsxx", new String[] {
						"mzpyjg", "csly", "xysh", "xyshyj" }, new String[] {
						mzpyjg, csly, xysh, xyshyj }, "xn||xh", xn + xh,
						request);
			}
		}

		return bFlag;
	}

	/**
	 * 删除国家助学贷款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "删除失败！";
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zgmsxy_gjzxdkdkxx where nd||sfzh='" + pkT[i]
						+ "'";
			} else {
				sqlT[i] = "delete zgmsxy_gjzxdkdkxx where nd||sfzh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		try {
			dao.runBatch(sqlT);
			message = "删除成功！";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}

	/**
	 * 国家助学贷款查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "bjmc", "hth", "dkqx", "htffje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", "身份证号", "班级", "合同号", "贷款期限", "发放金额", "申请时间", Base.YXPZXY_KEY+"审核",
				"学校审核" };
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
			enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
					"xb", "sfzh", "bjmc", "hth", "dkqx","sqje", "htffje","sjffje", "sqsj","htqsrq","htjsrq", "xysh",
					"xxsh" };
			cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
					"性别", "身份证号", "班级", "合同号", "贷款期限","贷款金额","合同金额","实际发放金额", "申请时间","合同起始日期","合同结束日期", Base.YXPZXY_KEY+"审核",
					"学校审核" };
		}
		
		
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学贷款上报信息查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkSbxxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "xh", "xm", "sfzh", "xymc", "bjmc",
				"hth1", "hth2", "hth3", "hth4", "hth5"};
		String[] cnList = new String[] { "学号", "姓名", "身份证号",Base.YXPZXY_KEY, "班级",
				"合同号1", "合同号2", "合同号3", "合同号4", "合同号5"};
		
		if(Globals.XXDM_WHKJDX.equalsIgnoreCase(Base.xxdm)){
			
			enList = new String[] { "xh", "xm", "sfzh", "xymc", "bjmc",
					"hth1", "hth2", "hth3", "hth4", "hth5","byny","dyxnxf","dexnxf",
					"dsanxnxf","dsixnxf","dyxnzsf","dexnzsf","dsanxnzsf",
					"dsixnzsf","dyxnshf","dexnshf","dsanxnshf","dsixnshf","jzrxm","yjzrgx",
					"jzrlxdh","jzrzz" };
			
			cnList = new String[] { "学号", "姓名", "身份证号",Base.YXPZXY_KEY, "班级",
						"合同号1", "合同号2", "合同号3", "合同号4", "合同号5","毕业年月","第一学年学费",
						"第二学年学费","第三学年学费","第四学年学费","第一学年住宿费",
						"第二学年住宿费","第三学年住宿费","第四学年住宿费","第一学年生活费","第二学年生活费",
						"第三学年生活费","第四学年生活费","见证人姓名","与见证人关系","见证人联系电话","见证人住址"};
		}
		
		
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
	public List<String[]> getGjzxdkRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "bjmc", "hth", "dkqx", "htffje", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 2011.9.6 qlj(修改:带分页)重载方法 国家助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";
		String[] colList=null;
		//非成都体育
		if(!Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
			colList = new String[] { "bgcolor","dis" ,"pk", "nd", "xh", "xm",
					"xb", "sfzh", "bjmc", "hth", "dkqx", "htffje", "sqsj", "xysh",
					"xxsh" };
			if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
				    sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,''dis,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where 1=1";
			} else {
				if (userType.equalsIgnoreCase("xy")) {
					sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,''dis,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where 1=1";
				} else {
					sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,''dis,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,htffje,sqsj,xysh,xxsh from view_zgmsxy_gjzxdk where 1=1 ";
				}
			}
		}else{//成都体育
			colList = new String[] { "bgcolor", "dis","pk", "nd", "xh", "xm",
					"xb", "sfzh", "bjmc", "hth", "dkqx","sqje", "htffje","sjffje", "sqsj","htqsrq","htjsrq", "xysh",
					"xxsh" };
			if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
				
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					+" (case when xxsh<>'未审核' or xysh<>'未审核' then 'disabled' else '' end)dis, "
					+" nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,sqje,htffje,sjffje,sqsj,htqsrq,htjsrq,xysh,xxsh from "
					+" ( select a.*,case when a.xxsh='通过' then to_char(b.sjffje) else '' end sjffje from view_zgmsxy_gjzxdk a left join "
				    +" (select xh,sum(ffje)sjffje from xg_zxdk_gjzxdkffb group by xh)b on a.xh=b.xh) where 1=1";
			} else {
				if (userType.equalsIgnoreCase("xy")) {
					sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					+" ''dis, "
					+" nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,sqje,htffje,sjffje,sqsj,htqsrq,htjsrq,xysh,xxsh from "
						+" ( select a.*,case when a.xxsh='通过' then to_char(b.sjffje) else '' end sjffje from view_zgmsxy_gjzxdk a left join "
					    +" (select xh,sum(ffje)sjffje from xg_zxdk_gjzxdkffb group by xh)b on a.xh=b.xh) where 1=1";
				} else {
					sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,''dis,nd||sfzh pk,nd,xh,xm,xb,sfzh,bjmc,hth,dkqx,sqje,htffje,sjffje,sqsj,htqsrq,htjsrq,xysh,xxsh from "
						+" ( select a.*,case when a.xxsh='通过' then to_char(b.sjffje) else '' end sjffje from view_zgmsxy_gjzxdk a left join "
					    +" (select xh,sum(ffje)sjffje from xg_zxdk_gjzxdkffb group by xh)b on a.xh=b.xh) where 1=1";
				}
			}
		}
		
		
	

		StringBuffer whereSql = getWhereSql(queryModel, request);

		StringBuilder sqlB = new StringBuilder();
		sqlB.append(" select rownum r,a.* from ( ");
		sqlB.append(sql);
		sqlB.append(whereSql);
		sqlB.append(" )a ");

		String[] inputV = values != null ? values.toArray(new String[0])
				: new String[] {};
		// 带分页查询
		resList = CommonQueryDAO.commonQuery(sqlB.toString(), "", inputV,
				colList, model);
		return resList;
	}

	/**
	 * 国家助学贷款上报信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkSbxxRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		sql = "select xh,xm,sfzh,xymc,bjmc,hth1,hth2,hth3,hth4,hth5 from view_zgmsxy_gjzxdksbxx where 1=1";
		String[] colList = new String[] { "xh", "xm", "sfzh", "xymc", "bjmc",
				"hth1", "hth2", "hth3", "hth4", "hth5","byny"};
		
		if(Globals.XXDM_WHKJDX.equalsIgnoreCase(Base.xxdm)){
			sql = "select xh,xm,sfzh,xymc,bjmc,hth1,hth2,hth3,hth4,hth5,byny,dyxnzsf," +
					"dexnzsf,dsanxnzsf,dsixnzsf,dyxnshf,dexnshf,dsanxnshf,dsixnshf,jzrxm," +
					"dyxnxf,dexnxf,dsanxnxf,dsixnxf,"+
					"yjzrgx,jzrlxdh,jzrzz from view_zgmsxy_gjzxdksbxx where 1=1";
			
			colList = new String[] { "xh", "xm", "sfzh", "xymc", "bjmc",
					"hth1", "hth2", "hth3", "hth4", "hth5","byny","dyxnxf","dexnxf",
					"dsanxnxf","dsixnxf","dyxnzsf","dexnzsf","dsanxnzsf","dsixnzsf",
					"dyxnshf","dexnshf","dsanxnshf","dsixnshf","jzrxm","yjzrgx",
					"jzrlxdh","jzrzz"};
		}

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
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjzxdkdatacf(nd, sfzh);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zgmsxy_gjzxdkdkxx", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||sfzh", nd + sfzh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zgmsxy_gjzxdkdkxx",
						new String[] { "xysh", "xyshyj" }, new String[] { xysh,
								xyshyj }, "nd||sfzh", nd + sfzh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 批量修改国家助学贷款审核结果 modGjzxdkxx ---- 批量修改国家助学贷款审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg,
			HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "操作失败！";
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update zgmsxy_gjzxdkdkxx set xxsh='" + shjg
						+ "' where nd||sfzh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update zgmsxy_gjzxdkdkxx set xysh='" + shjg
						+ "' where nd||sfzh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		try {
			dao.runBatch(sqlT);
			message = "操作成功!";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}

	/**
	 * 毕业生信息表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBysxxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm", "nj",
				"xb", "zymc", "bjmc", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名", "年级",
				"性别", "专业", "班级", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 毕业生信息
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where xysh='通过'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "nj",
				"xb", "zymc", "bjmc", "xysh", "xxsh" };
		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 2011.9.6 qlj(修改:带分页查询) 毕业生信息
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {

		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		StringBuilder sql = new StringBuilder();

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql
					.append("select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where 1=1");
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql
						.append("select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where 1=1");
			} else {
				sql
						.append("select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xh pk,xh,xm,nj,xb,zymc,bjmc,xysh,xxsh from view_zgmsxy_gjzxdkgrxx where xysh='通过'");
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm", "nj",
				"xb", "zymc", "bjmc", "xysh", "xxsh" };
		StringBuffer whereSql = getWhereSql(queryModel, request);
		// sql.append(" select rownum r,bgcolor,pk,xh,xm,nj,xb,xymc, ")

		StringBuilder sqlStr = new StringBuilder();

		sqlStr.append(" select rownum r,a.* from ( ");
		sqlStr.append(sql.toString());
		sqlStr.append(whereSql);
		sqlStr.append(" )a ");

		String[] inputV = values != null ? values.toArray(new String[0])
				: new String[] {};
		resList = CommonQueryDAO.commonQuery(sqlStr.toString(), "", inputV,
				colList, model);

		return resList;
	}

	/**
	 * 删除毕业生信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delBysxx(String cbVal, HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "删除失败！";

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zgmsxy_gjzxdkgrxx where xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete zgmsxy_gjzxdkgrxx where xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		try {
			dao.runBatch(sqlT);
			message = "删除成功！";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}

	/**
	 * 批量修改毕业生信息审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modBysxx(String cbVal, String shjg, HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "操作失败！";
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update zgmsxy_gjzxdkgrxx set xxsh='" + shjg
						+ "' where xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update zgmsxy_gjzxdkgrxx set xysh='" + shjg
						+ "' where xh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		try {
			dao.runBatch(sqlT);
			message = "操作成功！";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}

	/**
	 * 获取毕业生信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,mzmc,zzmmmc,sfzh,lxdh,jtzz,yzbm,fqxm,fqgzdw,fqysr,fqdh,mqxm,byny,mqgzdw,mqysr,"
				+ "(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny,"
				+ "mqdh,brjyqxhdw,jtgddh,brdzyxjdzlxfs,dqgzdwjdz,dqgzdwyb,dqgzdwdh,lxfsbgqk,xysh,xyshyj,xxsh,xxshyj from view_zgmsxy_gjzxdkgrxx a where xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "byny", "mzmc",
				"zzmmmc", "sfzh", "lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw",
				"fqysr", "fqdh", "mqxm", "mqgzdw", "mqysr", "mqdh",
				"brjyqxhdw", "jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz",
				"dqgzdwyb", "dqgzdwdh", "lxfsbgqk", "xysh", "xyshyj", "xxsh",
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
	 * 保存毕业生信息，成功返回TRUE，反之返回FALSE ---- 保存毕业生信息审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isBysDataCf(xh);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zgmsxy_gjzxdkgrxx", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj }, "xh",
					xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zgmsxy_gjzxdkgrxx",
						new String[] { "xysh", "xyshyj" }, new String[] { xysh,
								xyshyj }, "xh", xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 判断毕业生信息是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 ---- 数据是否重复
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String isBysDataCf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgmsxy_gjzxdkgrxx where xh = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgmsxy_gjzxdkgrxx where xh = ? ";
			num = dao.getOneRs(sql, new String[] { xh }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
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
		String sql = "";
		String[] colList;
		sql = "select dkyh,dknx,dkyhdd,nd,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,mzmc,zzmmmc,sfzh,lxdh,jtzz,yzbm,fqxm,fqgzdw,fqysr,fqdh,mqxm,mqgzdw,mqysr,mqdh,brjyqxhdw,jtgddh,brdzyxjdzlxfs,dqgzdwjdz,dqgzdwyb,dqgzdwdh,lxfsbgqk,sqsj,sqje,xysh,xyshyj,xxsh,xxshyj,hth,dkqx,htffje,sjffrq,dkzl,htye,grzhdkzh,hzdwmc,jqbj,jqlx,zhkzh,dkxt,wyys,schkr from view_zgmsxy_gjzxdk where nd||sfzh = ?";
		colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "zzmmmc",
				"sfzh", "lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw", "fqysr",
				"fqdh", "mqxm", "mqgzdw", "mqysr", "mqdh", "brjyqxhdw",
				"jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz", "dqgzdwyb", "dqgzdwdh",
				"lxfsbgqk", "sqsj", "sqje", "xysh", "xyshyj", "xxsh", "xxshyj",
				"hth", "dkqx", "htffje", "sjffrq", "dkzl", "htye", "grzhdkzh",
				"hzdwmc", "jqbj", "jqlx", "zhkzh", "dkxt", "wyys", "schkr",
				"dkyh", "dknx", "dkyhdd" };
		if(Base.xxdm.equals("10488")){
			sql = "select a.dkyh,a.dknx,a.dkyhdd,a.nd,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.lxdh,a.jtzz,a.yzbm,a.fqxm,a.fqgzdw,a.fqysr,a.fqdh,a.mqxm,a.mqgzdw,a.mqysr,a.mqdh,a.brjyqxhdw,a.jtgddh,a.brdzyxjdzlxfs,a.dqgzdwjdz,a.dqgzdwyb,a.dqgzdwdh,a.lxfsbgqk,a.sqsj,a.sqje,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.hth,a.dkqx,a.htffje,a.sjffrq,a.dkzl,a.htye,a.grzhdkzh,a.hzdwmc,a.jqbj,a.jqlx,a.zhkzh,a.dkxt,a.wyys,a.schkr,b.fqsfzh,b.mqsfzh, b.jzrxm,b.jzrlxdh,b.yjzrgx,b.jzrzz,b.dyxnzsf,b.dexnzsf,b.dsanxnzsf,b.dsixnzsf,b.dwuxnzsf,b.dyxnshf,b.dexnshf,b.dsanxnshf,b.dsixnshf,b.dwuxnshf,b.dyxnxf,b.dexnxf,b.dsanxnxf,b.dsixnxf,b.dwuxnxf from view_zgmsxy_gjzxdk a,zgmsxy_gjzxdkgrxx b where a.xh=b.xh and a.nd||a.sfzh = ?";
			colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "zzmmmc",
					"sfzh", "lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw", "fqysr",
					"fqdh", "mqxm", "mqgzdw", "mqysr", "mqdh", "brjyqxhdw",
					"jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz", "dqgzdwyb", "dqgzdwdh",
					"lxfsbgqk", "sqsj", "sqje", "xysh", "xyshyj", "xxsh", "xxshyj",
					"hth", "dkqx", "htffje", "sjffrq", "dkzl", "htye", "grzhdkzh",
					"hzdwmc", "jqbj", "jqlx", "zhkzh", "dkxt", "wyys", "schkr",
					"dkyh", "dknx", "dkyhdd","fqsfzh","mqsfzh","jzrxm","jzrlxdh",
					"yjzrgx","jzrzz","dyxnzsf","dexnzsf","dsanxnzsf",
					"dsixnzsf","dwuxnzsf","dyxnshf","dexnshf","dsanxnshf","dsixnshf","dwuxnshf",
					"dyxnxf","dexnxf","dsanxnxf","dsixnxf","dwuxnxf"};
			
		}
		
		//江西理工大学
		else if(Base.xxdm.equals("10407")){
			sql = "select a.dkyh,a.dknx,a.dkyhdd,a.nd,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.lxdh,a.jtzz,a.yzbm,a.fqxm,a.fqgzdw,a.fqysr,a.fqdh,a.mqxm,a.mqgzdw,a.mqysr,a.mqdh,a.brjyqxhdw,a.jtgddh,a.brdzyxjdzlxfs,a.dqgzdwjdz,a.dqgzdwyb,a.dqgzdwdh,a.lxfsbgqk,a.sqsj,a.sqje,a.xysh,a.xyshyj,a.xxsh,a.xxshyj,a.hth,a.dkqx,a.htffje,a.sjffrq,a.dkzl,a.htye,a.grzhdkzh,a.hzdwmc,a.jqbj,a.jqlx,a.zhkzh,a.dkxt,a.wyys,a.schkr,b.dkxn,b.xf,b.zsf from view_zgmsxy_gjzxdk a,zgmsxy_gjzxdkgrxx b where a.xh=b.xh and nd||sfzh = ?";
			colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "zzmmmc",
					"sfzh", "lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw", "fqysr",
					"fqdh", "mqxm", "mqgzdw", "mqysr", "mqdh", "brjyqxhdw",
					"jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz", "dqgzdwyb", "dqgzdwdh",
					"lxfsbgqk", "sqsj", "sqje", "xysh", "xyshyj", "xxsh", "xxshyj",
					"hth", "dkqx", "htffje", "sjffrq", "dkzl", "htye", "grzhdkzh",
					"hzdwmc", "jqbj", "jqlx", "zhkzh", "dkxt", "wyys", "schkr",
					"dkyh", "dknx", "dkyhdd","dkxn","xf","zsf" };
		}
		
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
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='助学贷款申请' and b.xh=? ";// ,nd
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				String sT = dao
						.getOneRs(
								"select xh from view_zgdzdx_syddk where sfksqgjzxdk='否' group by xh having xh=?",
								new String[] { xh }, "xh");

				if (null == sT || "".equalsIgnoreCase(sT)) {
					sfksq = "1";
				} else {
					sfksq = "-1";
				}
			}
		} else {
			sfksq = "1";
		}

		return sfksq;
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
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String fqgzdw = Base.chgNull(model.getFqgzdw(), "", 1);
		String fqdh = Base.chgNull(model.getFqdh(), "", 1);
		String fqysr = Base.chgNull(model.getFqysr(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String mqgzdw = Base.chgNull(model.getMqgzdw(), "", 1);
		String mqdh = Base.chgNull(model.getMqdh(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String mqysr = Base.chgNull(model.getMqysr(), "", 1);
		String brjyqxhdw = Base.chgNull(model.getBrjyqxhdw(), "", 1);
		String jtgddh = Base.chgNull(model.getJtgddh(), "", 1);
		String brdzyxjdzlxfs = Base.chgNull(model.getBrdzyxjdzlxfs(), "", 1);
		String dqgzdwjdz = Base.chgNull(model.getDqgzdwjdz(), "", 1);
		String dqgzdwyb = Base.chgNull(model.getDqgzdwyb(), "", 1);
		String dqgzdwdh = Base.chgNull(model.getDqgzdwdh(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String dkyh = Base.chgNull(model.getDkyh(), "", 1);
		String dknx = Base.chgNull(model.getDknx(), "", 1);
		String dkyhdd = Base.chgNull(model.getDkyhdd(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String dkxn = Base.chgNull(model.getDkxn(), "", 1);
		String xf = Base.chgNull(model.getXf(), "", 1);
		String zsf = Base.chgNull(model.getZsf(), "", 1);
		String nd = Base.currNd;
		
		String jzrxm = Base.chgNull(model.getJzrxm(), "",1);//见证人姓名
		String jzrlxdh = Base.chgNull(model.getJzrlxdh(), "",1);//见证人联系电话
		String yjzrgx = Base.chgNull(model.getYjzrgx(), "",1);// 与见证人关系
		String jzrzz = Base.chgNull(model.getJzrzz(), "",1);// 见证人住址
		
		String dyxnzsf = Base.chgNull(model.getDyxnzsf(), "",1);// 第一学年住宿费
		String dexnzsf = Base.chgNull(model.getDexnzsf(), "",1);// 第二学年住宿费
		String dsanxnzsf = Base.chgNull(model.getDsanxnzsf(), "",1);// 第三学年住宿费
		String dsixnzsf = Base.chgNull(model.getDsixnzsf(), "",1);// 第四学年住宿费
		String dwuxnzsf = Base.chgNull(model.getDwuxnzsf(), "",1);// 第五学年住宿费
		
		String dyxnshf = Base.chgNull(model.getDyxnshf(), "",1);// 第一学年生活费
		String dexnshf = Base.chgNull(model.getDexnshf(), "",1);// 第二学年生活费
		String dsanxnshf = Base.chgNull(model.getDsanxnshf(), "",1);// 第三学年生活费
		String dsixnshf = Base.chgNull(model.getDsixnshf(), "",1);// 第四学年生活费
		String dwuxnshf = Base.chgNull(model.getDwuxnshf(), "",1);// 第五学年生活费
		
		String dyxnxf=Base.chgNull(model.getDyxnxf(), "", 1); // 第一学年学费
		String dexnxf=Base.chgNull(model.getDexnxf(), "", 1); // 第二学年学费
		String dsanxnxf=Base.chgNull(model.getDsanxnxf(), "", 1); // 第三学年学费
		String dsixnxf=Base.chgNull(model.getDsixnxf(), "", 1); // 第四学年学费
		String dwuxnxf=Base.chgNull(model.getDwuxnxf(), "", 1); // 第五学年学费
	
		model.setNd(nd);

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkdatacf(nd, sfzh);
		//String sHave = "1";
		String sXsxx = isGjzxdkXscf(xh);
		// 判断国家助学贷款是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1
		String[] columnsList;
		String[] valueList;
		columnsList = new String[] { "xh", "lxdh", "jtzz", "yzbm",
				"fqxm", "fqgzdw", "fqdh", "fqysr", "mqxm", "mqgzdw", "mqdh",
				"mqysr", "brjyqxhdw", "jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz",
				"dqgzdwyb", "dqgzdwdh", "dkyh", "dknx", "dkyhdd" };

		valueList = new String[] { xh, lxdh, jtzz, yzbm, fqxm, fqgzdw,
				fqdh, fqysr, mqxm, mqgzdw, mqdh, mqysr, brjyqxhdw, jtgddh,
				brdzyxjdzlxfs, dqgzdwjdz, dqgzdwyb, dqgzdwdh, dkyh, dknx,
				dkyhdd };
		if(Base.xxdm.equals("10488")){
			columnsList = new String[] { "xh", "lxdh", "jtzz", "yzbm",
					"fqxm", "fqgzdw", "fqdh", "fqysr", "mqxm", "mqgzdw", "mqdh",
					"mqysr", "brjyqxhdw", "jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz",
					"dqgzdwyb", "dqgzdwdh", "dkyh", "dknx", "dkyhdd","fqsfzh","mqsfzh",
					"jzrxm","jzrlxdh","yjzrgx","jzrzz","dyxnzsf","dexnzsf","dsanxnzsf",
					"dsixnzsf","dwuxnzsf","dyxnshf","dexnshf","dsanxnshf","dsixnshf","dwuxnshf","dyxnxf",
					"dexnxf","dsanxnxf","dsixnxf","dwuxnxf"};
	
			valueList = new String[] { xh, lxdh, jtzz, yzbm, fqxm, fqgzdw,
					fqdh, fqysr, mqxm, mqgzdw, mqdh, mqysr, brjyqxhdw, jtgddh,
					brdzyxjdzlxfs, dqgzdwjdz, dqgzdwyb, dqgzdwdh, dkyh, dknx,
					dkyhdd,fqsfzh,mqsfzh,jzrxm,jzrlxdh,yjzrgx,jzrzz,dyxnzsf,dexnzsf,dsanxnzsf,
					dsixnzsf,dwuxnzsf,dyxnshf,dexnshf,dsanxnshf,dsixnshf,dwuxnshf,dyxnxf,dexnxf,dsanxnxf,
					dsixnxf,dwuxnxf};
		}
		
		//江西理工大学
		if(Base.xxdm.equals("10407")){
			columnsList = new String[] { "xh", "lxdh", "jtzz", "yzbm",
					"fqxm", "fqgzdw", "fqdh", "fqysr", "mqxm", "mqgzdw", "mqdh",
					"mqysr", "brjyqxhdw", "jtgddh", "brdzyxjdzlxfs", "dqgzdwjdz",
					"dqgzdwyb", "dqgzdwdh", "dkyh", "dknx", "dkyhdd","dkxn","xf","zsf" };

			valueList = new String[] { xh, lxdh, jtzz, yzbm, fqxm, fqgzdw,
					fqdh, fqysr, mqxm, mqgzdw, mqdh, mqysr, brjyqxhdw, jtgddh,
					brdzyxjdzlxfs, dqgzdwjdz, dqgzdwyb, dqgzdwdh, dkyh, dknx,
					dkyhdd,dkxn,xf,zsf };
		}

		if ("2".equalsIgnoreCase(sHave)) {
			
			request.setAttribute("isPASS", "is");
		} else {
			
			if ("-1".equals(sXsxx)) {
				StandardOperation.insert("zgmsxy_gjzxdkgrxx", columnsList,
						valueList, request);
			} else {
				StandardOperation.update("zgmsxy_gjzxdkgrxx", columnsList,
						valueList, "xh", xh, request);
			}
			
			if ("-1".equalsIgnoreCase(sHave)) {
				bFlag = StandardOperation.insert("zgmsxy_gjzxdkdkxx",
						new String[] { "nd", "xh", "sfzh", "sqje" },
						new String[] { nd, xh, sfzh, sqje }, request);
			} else {
				bFlag = StandardOperation.update("zgmsxy_gjzxdkdkxx",
						new String[] { "sqje", "sqsj", "xxsh", "xysh" },
						new String[] { sqje, now, "未审核", "未审核" }, "sfzh||nd",
						sfzh + nd, request);
			}

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
	public String isGjzxdkdatacf(String nd, String sfzh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgmsxy_gjzxdkdkxx where sfzh = ? and nd=? and xysh in ('通过') and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { sfzh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgmsxy_gjzxdkdkxx where sfzh = ? and nd= ? ";
			num = dao.getOneRs(sql, new String[] { sfzh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 判断申请国家助学贷款人是否重复，重复1，没有重复的返回-1 isGjzxdkXscf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxdkXscf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgmsxy_gjzxdkgrxx where xh = ? ";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}

	/**
	 * 得到学生贷款基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxx(String xh) {
		DAO daoz = new DAO();
		String sql = "select a.lxdh, a.jtzz, a.yzbm, a.fqxm, a.fqysr, a.fqdh, a.fqgzdw,"
				+ " a.mqxm, a.mqysr, a.mqdh, a.mqgzdw,b.sfzh,b.sqje from zgmsxy_gjzxdkgrxx a,"
				+ " zgmsxy_gjzxdkdkxx b where a.xh = b.xh and a.xh=?";
		HashMap<String, String> map = daoz.getMap(sql, new String[] { xh },
				new String[] { "sfzh", "lxdh", "jtzz", "yzbm", "fqxm", "fqysr",
						"fqdh", "fqgzdw", "mqxm", "mqysr", "mqdh", "mqgzdw",
						"sqje" });

		return map;
	}

	/**
	 * 得到毕业生信息采集申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getBysxxcjSqQx(String sUserType, String xh) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from ZGDZDX_ZXDK_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='毕业生信息采集' and b.xh=? ";// ,nd
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				String sT = dao
						.getOneRs(
								"select xh from view_zgdzdx_syddk where sfksqgjzxdk='否' group by xh having xh=?",
								new String[] { xh }, "xh");

				if (null == sT || "".equalsIgnoreCase(sT)) {
					sfksq = "1";
				} else {
					sfksq = "-1";
				}
			}
		} else {
			sfksq = "1";
		}

		return sfksq;
	}

	/**
	 * 获取毕业生申请信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxxcjMap(String pkValue)
			throws Exception {
		return dao
				.getMapNotOut(
						"select XH,XM,XB,NJ,XYDM,XYMC,ZYDM,ZYMC,BJDM,BJMC,MZMC,ZZMMMC,SFZH,LXDH,JTZZ,YZBM,FQXM,FQGZDW,FQYSR,FQDH,MQXM,MQGZDW,MQYSR,MQDH,BRJYQXHDW,JTGDDH,BRDZYXJDZLXFS,DQGZDWJDZ,DQGZDWYB,DQGZDWDH,LXFSBGQK,XYSH,XYSHYJ,XXSH,XXSHYJ from view_zgmsxy_gjzxdkgrxx where xh=?",
						new String[] { pkValue });
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
	public String isBysxxdatacf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgmsxy_gjzxdkgrxx where xh = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgmsxy_gjzxdkgrxx where xh = ?";
			num = dao.getOneRs(sql, new String[] { xh }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 保存毕业生申请信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveByscjxx(QueryModel model, HttpServletRequest request)
			throws Exception {
		String sFlag = isBysxxdatacf(model.getXh());
		boolean bFlag = false;
		if ("-1".equalsIgnoreCase(sFlag)) {
			bFlag = StandardOperation.insert("zgmsxy_gjzxdkgrxx", new String[] {
					"xh", "lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw", "fqdh",
					"mqxm", "mqgzdw", "mqdh", "brjyqxhdw", "jtgddh",
					"brdzyxjdzlxfs", "dqgzdwjdz", "dqgzdwyb", "dqgzdwdh",
					"lxfsbgqk" }, new String[] { model.getXh(),
					model.getLxdh(), DealString.toGBK(model.getJtzz()),
					model.getYzbm(), DealString.toGBK(model.getFqxm()),
					DealString.toGBK(model.getFqgzdw()),
					DealString.toGBK(model.getFqdh()),
					DealString.toGBK(model.getMqxm()),
					DealString.toGBK(model.getMqgzdw()), model.getMqdh(),
					DealString.toGBK(model.getBrjyqxhdw()), model.getJtgddh(),
					DealString.toGBK(model.getBrdzyxjdzlxfs()),
					DealString.toGBK(model.getDqgzdwjdz()),
					model.getDqgzdwyb(), model.getDqgzdwdh(),
					DealString.toGBK(model.getLxfsbgqk()) }, request);
		} else if ("1".equalsIgnoreCase(sFlag)) {
			bFlag = StandardOperation.update("zgmsxy_gjzxdkgrxx", new String[] {
					"lxdh", "jtzz", "yzbm", "fqxm", "fqgzdw", "fqdh", "mqxm",
					"mqgzdw", "mqdh", "brjyqxhdw", "jtgddh", "brdzyxjdzlxfs",
					"dqgzdwjdz", "dqgzdwyb", "dqgzdwdh", "lxfsbgqk", "xxsh",
					"xysh" }, new String[] { model.getLxdh(),
					DealString.toGBK(model.getJtzz()), model.getYzbm(),
					DealString.toGBK(model.getFqxm()),
					DealString.toGBK(model.getFqgzdw()),
					DealString.toGBK(model.getFqdh()),
					DealString.toGBK(model.getMqxm()),
					DealString.toGBK(model.getMqgzdw()), model.getMqdh(),
					DealString.toGBK(model.getBrjyqxhdw()), model.getJtgddh(),
					DealString.toGBK(model.getBrdzyxjdzlxfs()),
					DealString.toGBK(model.getDqgzdwjdz()),
					model.getDqgzdwyb(), model.getDqgzdwdh(),
					DealString.toGBK(model.getLxfsbgqk()), "未审核", "未审核" },
					"xh", model.getXh(), request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 获取生源地贷款信息列表(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getSyddkList(XszzZgmsxyActionForm myForm)
			throws Exception {
		
		User user=myForm.getUser();
		String[] colList = { "pkValue", "xn", "xh", "xm","nj", "xymc", "zymc",
				"bjmc", "xfbz" ,"sqje","ffje"};
		String[] col = { "xn", "nj", "xydm", "zydm", "bjdm" };
		String[] colLike = { "xh", "xm" };
		StringBuilder sql = new StringBuilder();
		MakeQuery mQuery = new MakeQuery();

		mQuery.makeQuery(col, colLike, myForm);
		sql.append("  select rownum r,xh||'!!@@!!'||xn pkValue,xh,xm,xn,xfbz, ");
		sql.append("  sqje,jtdz,lxdh,sjhm,xymc,zymc,bjmc,nj,ffje from xg_view_zxdk_syddk a ");
		
		
		String userName=user.getUserName();
		String userDep=user.getUserDep();
		String userType=user.getUserType();
		StringBuilder userQuery=new StringBuilder();
		
		if(myForm.isFdyQx() && myForm.isBzrQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if(myForm.isFdyQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if(myForm.isBzrQx()){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString()+userQuery.toString(), mQuery.getInputList(), colList, myForm);
	}
	
	/**
	 * 获取生源地贷款信息列表(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZxdkFfList(XszzZgmsxyActionForm myForm)
			throws Exception {

		String[] colList = { "pkValue", "xn", "xh", "xm","hth", "nj", "xymc", "zymc",
				"bjmc", "ffje" };
		String[] col = { "xn", "nj", "xydm", "zydm", "bjdm" };
		String[] colLike = { "xh", "xm" };
		StringBuilder sql = new StringBuilder();
		MakeQuery mQuery = new MakeQuery();

		mQuery.makeQuery(col, colLike, myForm);
		sql.append("  select rownum r,xh||'!!@@!!'||xn pkValue,xh,xm,xn,hth,nj, ");
		sql.append("  xydm,xymc,zydm,zymc,bjdm,bjmc,ffje from xg_view_zxdk_gjzxdkff ");

		return CommonQueryDAO.commonQuery(sql.toString(), mQuery
				.getQueryString(), mQuery.getInputList(), colList, myForm);
	}

	/**
	 * 保存生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String xh = myForm.getXh();
		String xn = myForm.getXn();
		String xfbz = myForm.getXfbz();
		String sqje = myForm.getSqje();
		String jtdz = myForm.getJtdz();
		String lxdh = myForm.getLxdh();
		String sjhm = myForm.getSjhm();
		String ffje = myForm.getFfje();

		sql.append(" insert into xg_zxdk_syddkb(xh,xn,xfbz,sqje,jtdz,lxdh,sjhm,ffje)  ");
		sql.append(" values(?,?,?,?,?,?,?,?)");
		String[] inputV = { xh, xn, xfbz, sqje, jtdz, lxdh, sjhm,ffje };
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 修改生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		String xh = myForm.getXh();
		String xn = myForm.getXn();
		String xfbz = myForm.getXfbz();
		String sqje = myForm.getSqje();
		String jtdz = myForm.getJtdz();
		String lxdh = myForm.getLxdh();
		String sjhm = myForm.getSjhm();
		String ffje = myForm.getFfje();
		
		sql.append(" update xg_zxdk_syddkb  ");
		sql.append(" set xfbz=?, ");
		sql.append(" sqje=?, ");
		sql.append(" jtdz=?, ");
		sql.append(" lxdh=?, ");
		sql.append(" sjhm=?, ");
		sql.append(" ffje=? ");
		sql.append(" where xh||'!!@@!!'||xn= ? ");
		
		String[] inputV = { xfbz, sqje, jtdz, lxdh, sjhm,ffje, xh+"!!@@!!"+xn};
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 删除生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delSyddk(XszzZgmsxyActionForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		for (int i = 0; i < pkValue.length; i++) {

			sql[i] = " delete from xg_zxdk_syddkb where xh||'!!@@!!'||xn='"
					+ pkValue[i] + "'  ";
		}

		return dao.saveArrDate(sql);
	}

	public HashMap<String, String> getOneSyddk(XszzZgmsxyActionForm myForm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String pkValue = myForm.getPkV()[0];
		sql.append(" select xh,xm,xn,xfbz,xb,sqje,jtdz,lxdh,sjhm,nj,xymc,zymc,bjmc from xg_view_zxdk_syddk");
		sql.append(" where xh||'!!@@!!'||xn= ? ");
		String colList[] = { "xh","xm", "xn", "xb", "xfbz", "sqje", "jtdz", "lxdh",
				"sjhm", "xymc", "nj", "zymc", "bjmc" };
		return dao.getMap(sql.toString(), new String[] { pkValue }, colList);
	}
	
	/**
	 * 删除助学贷款发放信息(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delZxdkFf(XszzZgmsxyActionForm myForm) throws Exception {

		String[] pkValue = myForm.getPkV();
		String[] sql = new String[pkValue.length];
		CommDAO dao = new CommDAO();
		for (int i = 0; i < pkValue.length; i++) {

			sql[i] = " delete from xg_zxdk_gjzxdkffb where xh||'!!@@!!'||xn='"
					+ pkValue[i] + "'  ";
		}

		return dao.saveArrDate(sql);
	}
	/**
	 * 判断学生申请-学生申请是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-6
	 */
	public String isXssqkdatacf(String nd, String sfzh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xg_zxdk_sq where sfzh = ? and nd=? and yxsh<>'未审核'";
		String sql2 = "select count(*) num from xg_zxdk_sq where sfzh = ? and nd=? and xxsh<>'未审核'";
		String num1 = dao.getOneRs(sql, new String[] { sfzh, nd }, "num");
		String num2 = dao.getOneRs(sql2, new String[] { sfzh, nd }, "num");
		String num = (Integer.parseInt(num1)+Integer.parseInt(num2))+"";
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xg_zxdk_sq where sfzh = ? and nd= ? ";
			num = dao.getOneRs(sql, new String[] { sfzh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}else{
				sFlag = "-1";
			}
		}
		return sFlag;
	}
	/**
	 * 判断学生申请-学生申请人是否重复，重复1，没有重复的返回-1 isGjzxdkXscf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isXssqSfcf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xg_zxdk_sq where xh = ? ";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}
	/**
	 * 保存学生申请-学生申请信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqxx(GjzxdkModel model, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String sfzyxzzrq = Base.chgNull(model.getSfzyxzzrq(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String lxdh2 = Base.chgNull(model.getLxdh2(), "", 1);
		String lxdh3 = Base.chgNull(model.getLxdh3(), "", 1);
		String xq1 = Base.chgNull(model.getXq1(), "", 1);
		String xq2 = Base.chgNull(model.getXq2(), "", 1);
		String xq3 = Base.chgNull(model.getXq3(), "", 1);
		String xq4 = Base.chgNull(model.getXq4(), "", 1);
		String xq5 = Base.chgNull(model.getXq5(), "", 1);
		String dkhj = Base.chgNull(model.getDkhj(), "", 1);
		String xfje = Base.chgNull(model.getXfje(), "", 1);
		String zsfje = Base.chgNull(model.getZsfje(), "", 1);
		String qq = Base.chgNull(model.getQq(), "", 1);
		String email = Base.chgNull(model.getEmail(), "", 1);
		String yxsh = Base.chgNull(model.getYxsh(), "未审核", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "未审核", 1);
		String nd = Base.chgNull(model.getNd(), Base.currNd, 1);
		//String nd = Base.currNd;
		
		model.setNd(nd);
		
		String sHave = isXssqkdatacf(nd, sfzh);
		String sXsxx = isXssqSfcf(xh);
		// 判断国家助学贷款是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1
		String[] columnsList;
		String[] valueList;
		columnsList = new String[] { "xh", "sfzh", "sfzyxzzrq", "fqxm",
				"fqsfzh", "mqxm", "mqsfzh", "lxdh2", "lxdh3", "xq1", "xq2",
				"xq3", "xq4", "xq5", "dkhj", "xfje",
				"zsfje", "qq", "email","nd"  };
		
		valueList = new String[] { xh, sfzh, sfzyxzzrq, fqxm, fqsfzh, mqxm,
				mqsfzh, lxdh2, lxdh3, xq1, xq2, xq3, xq4, xq5,
				dkhj, xfje, zsfje, qq, email,nd };
		
		String[] columnsList2;
		String[] valueList2;
		columnsList2 = new String[] { "xh", "sfzh", "sfzyxzzrq", "fqxm",
				"fqsfzh", "mqxm", "mqsfzh", "lxdh2", "lxdh3", "xq1", "xq2",
				"xq3", "xq4", "xq5", "dkhj", "xfje",  
				"zsfje", "qq", "email", "nd", "yxsh", "xxsh" };
		
		valueList2 = new String[] { xh, sfzh, sfzyxzzrq, fqxm, fqsfzh, mqxm,
				mqsfzh, lxdh2, lxdh3, xq1, xq2, xq3, xq4, xq5,
				dkhj, xfje, zsfje, qq, email, nd,yxsh,xxsh };

		if ("2".equalsIgnoreCase(sHave)) {
			
			request.setAttribute("isPASS", "is");
		} else {
			
			//if ("-1".equals(sXsxx)) {
			if ("-1".equalsIgnoreCase(sHave)) {
				bFlag = StandardOperation.insert("xg_zxdk_sq", columnsList,
						valueList, request);
			} else {
				bFlag = StandardOperation.update("xg_zxdk_sq", columnsList2,
						valueList2, "xh||nd", xh+nd, request);
			}
//			if ("-1".equalsIgnoreCase(sHave)) {
//				bFlag = StandardOperation.insert("xg_zxdk_sq",
//						new String[] { "nd", "xh", "sfzh", "xfje" },
//						new String[] { nd, xh, sfzh, xfje }, request);
//			} else {
//				bFlag = StandardOperation.update("xg_zxdk_sq",
//						new String[] { "xfje", "sqsj", "xxsh", "yxsh" },
//						new String[] { xfje, now, "未审核", "未审核" }, "sfzh||nd",
//						sfzh + nd, request);
//			}
		
		}
		return bFlag;
	}
	
	/**
	 * 保存学生申请-学生申请审核信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqshxx(GjzxdkModel model, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sfzh = Base.chgNull(model.getSfzh(), "", 1);
		String sfzyxzzrq = Base.chgNull(model.getSfzyxzzrq(), "", 1);
		String fqxm = Base.chgNull(model.getFqxm(), "", 1);
		String mqxm = Base.chgNull(model.getMqxm(), "", 1);
		String fqsfzh = Base.chgNull(model.getFqsfzh(), "", 1);
		String mqsfzh = Base.chgNull(model.getMqsfzh(), "", 1);
		String lxdh2 = Base.chgNull(model.getLxdh2(), "", 1);
		String lxdh3 = Base.chgNull(model.getLxdh3(), "", 1);
		String xq1 = Base.chgNull(model.getXq1(), "", 1);
		String xq2 = Base.chgNull(model.getXq2(), "", 1);
		String xq3 = Base.chgNull(model.getXq3(), "", 1);
		String xq4 = Base.chgNull(model.getXq4(), "", 1);
		String xq5 = Base.chgNull(model.getXq5(), "", 1);
		String dkhj = Base.chgNull(model.getDkhj(), "", 1);
		String xfje = Base.chgNull(model.getXfje(), "", 1);
		String zsfje = Base.chgNull(model.getZsfje(), "", 1);
		String qq = Base.chgNull(model.getQq(), "", 1);
		String email = Base.chgNull(model.getEmail(), "", 1);
		String yxsh = Base.chgNull(model.getYxsh(), "未审核", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "未审核", 1);
		String nd = Base.chgNull(model.getNd(), Base.currNd, 1);
		String yxshyj = Base.chgNull(model.getYxshyj(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		//String nd = Base.currNd;
		model.setNd(nd);
		String[] columnsList;
		String[] valueList;
		
		columnsList = new String[] { "xh", "sfzh", "sfzyxzzrq", "fqxm",
				"fqsfzh", "mqxm", "mqsfzh", "lxdh2", "lxdh3", "xq1", "xq2",
				"xq3", "xq4", "xq5", "dkhj", "xfje",  
				"zsfje", "qq", "email", "nd", "yxsh", "xxsh","yxshyj","xxshyj" };
		
		valueList = new String[] { xh, sfzh, sfzyxzzrq, fqxm, fqsfzh, mqxm,
				mqsfzh, lxdh2, lxdh3, xq1, xq2, xq3, xq4, xq5,
				dkhj, xfje, zsfje, qq, email, nd,yxsh,xxsh,yxshyj,xxshyj };

		bFlag = StandardOperation.update("xg_zxdk_sq", columnsList,
				valueList, "xh||nd", xh+nd, request);
		return bFlag;
	}
	/**
	 * 学生申请-学生申请查询表头
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<HashMap<String, String>> getXssqTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "bjmc", "dkhj", "yxsh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", "身份证号", "班级", "贷款合计", Base.YXPZXY_KEY+"审核",
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
	 *  学生申请-学生申请查询结果(修改:带分页)重载方法
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
		.toString();
		User user=model.getUser();
		//String fdyQx=user.getFdyQx();
		//String bzrQx=user.getBzrQx();
		String fdysql = "select count(*) num from fdybjb where zgh='"+userName+"'";
		int fdy =Integer.parseInt(dao.getOneRs(fdysql, new String[]{}, "num"));
		boolean fdyQx = fdy >0 ? true : false;
		String bzrsql = "select count(*) num from bzrbbb where zgh='"+userName+"'";
		int bzr =Integer.parseInt(dao.getOneRs(bzrsql, new String[]{}, "num"));
		boolean bzrQx = bzr >0 ? true : false;
		String sql = "";
		String[] colList=null;
		colList = new String[] { "bgcolor","dis" ,"pk", "bj","nd", "xh", "xm",
				"xb", "sfzh", "bjmc","dkhj", "yxsh",
				"xxsh" };
		if (userType.equalsIgnoreCase("xy")) {
			//判断是否是辅导员或是班主任
			if(true==fdyQx && true==bzrQx){
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when yxsh<>'未审核' or xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm "
					+" union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ";
			}else if(true==fdyQx){
				//辅导员
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when yxsh<>'未审核' or xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ";
			}else if(true==bzrQx){
				//班主任
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when yxsh<>'未审核' or xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+"and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm )";
			}else{
				//学院用户
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when yxsh<>'未审核' or xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq where 1=1";
			}
			
		} else {
			sql = "select (case when xxsh='通过' or xxsh='不通过'  then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when yxsh<>'未审核' or xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq where 1=1 ";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request);

		StringBuilder sqlB = new StringBuilder();
		sqlB.append(" select rownum r,a.* from ( ");
		sqlB.append(sql);
		sqlB.append(whereSql);
		sqlB.append(" )a ");

		String[] inputV = values != null ? values.toArray(new String[0])
				: new String[] {};
		// 带分页查询
		resList = CommonQueryDAO.commonQuery(sqlB.toString(), "", inputV,
				colList, model);
		return resList;
	}
	
	/**
	 *  学生申请-学生申请审核查询结果(修改:带分页)重载方法
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqshRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
		.toString();
		User user=model.getUser();
		//String fdyQx=user.getFdyQx();
		//String bzrQx=user.getBzrQx();
		String fdysql = "select count(*) num from fdybjb where zgh='"+userName+"'";
		int fdy =Integer.parseInt(dao.getOneRs(fdysql, new String[]{}, "num"));
		String fdyQx = fdy >0 ? "true" : "false";
		String bzrsql = "select count(*) num from bzrbbb where zgh='"+userName+"'";
		int bzr =Integer.parseInt(dao.getOneRs(bzrsql, new String[]{}, "num"));
		String bzrQx = bzr >0 ? "true" : "false";
		String sql = "";
		String[] colList=null;
		colList = new String[] { "bgcolor","dis" ,"pk", "bj","nd", "xh", "xm",
				"xb", "sfzh", "bjmc","dkhj", "yxsh",
				"xxsh" };
		if (userType.equalsIgnoreCase("xy")) {
			//判断是否是辅导员或是班主任
			if("true".equalsIgnoreCase(fdyQx) && "true".equalsIgnoreCase(bzrQx)){
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm "
					+" union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ";
			}else if("true".equalsIgnoreCase(fdyQx)){
				//辅导员
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ";
			}else if("true".equalsIgnoreCase(bzrQx)){
				//班主任
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq a where 1=1"
					+"and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm )";
			}else{
				//学院用户
				sql = "select (case when yxsh='通过' or yxsh='不通过' then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,(case when xxsh<>'未审核' then 'disabled' else '' end ) bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq where 1=1";
			}
			
		} else {
			sql = "select (case when xxsh='通过' or xxsh='不通过'  then '#FFFFFF' else '#FFFFFF' end) bgcolor,'' dis,nd||sfzh pk,'' bj,nd,xh,xm,xb,sfzh,bjmc,dkhj,yxsh,xxsh from view_xg_zxdk_sq where 1=1 and yxsh='通过'";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request);

		StringBuilder sqlB = new StringBuilder();
		sqlB.append(" select rownum r,a.* from ( ");
		sqlB.append(sql);
		sqlB.append(whereSql);
		sqlB.append(" )a ");

		String[] inputV = values != null ? values.toArray(new String[0])
				: new String[] {};
		// 带分页查询
		resList = CommonQueryDAO.commonQuery(sqlB.toString(), "", inputV,
				colList, model);
		return resList;
	}
	
	/**
	 * 批量修改学生申请-学生申请审核结果
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void modXssqxx(String cbVal, String shjg,
			HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "操作失败！";
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xg_zxdk_sq set xxsh='" + shjg
						+ "' where nd||sfzh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update xg_zxdk_sq set yxsh='" + shjg
						+ "' where nd||sfzh='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		try {
			dao.runBatch(sqlT);
			message = "操作成功!";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}
	/**
	 * 删除学生申请-学生申请信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void delXssqxx(String cbVal, HttpServletRequest request) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		String message = "删除失败！";
		for (int i = 1; i < pkT.length; i++) {
			//if (userType.equalsIgnoreCase("admin")
			//		|| userType.equalsIgnoreCase("xx")) {
			//	sqlT[i] = "delete xg_zxdk_sq where nd||sfzh='" + pkT[i]
			//			+ "'";
			//} else {
				sqlT[i] = "delete xg_zxdk_sq where nd||sfzh='" + pkT[i]
						+ "' and xxsh<>'通过' and xxsh <> '不通过' and  yxsh<>'通过' and yxsh<> '不通过' ";
			//}
		}
		try {
			dao.runBatch(sqlT);
			message = "删除成功！";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("message", message);
	}
	/**
	 * 获取学生申请信息
	 * @param pkVal
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public HashMap<String, String> getXssqxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "";
		String[] colList;
		sql = "select xh,xm,sfzh,nd,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,lxdh2,lxdh3,jtdz,jtyb,qq,email,fqxm,fqsfzh,mqxm,mqsfzh,dkhj,xq1,xq2,xq3,xq4,xq5,xfje,zsfje,sjhm,yxsh,xxsh,sfzyxzzrq,csrq,rxrq,byny,yxshyj,xxshyj from view_xg_zxdk_sq where nd||sfzh = ?";
		colList = new String[] { "xh","xm","sfzh","nd","xb","xz","xydm","xymc","zydm","zymc",
				"bjdm","bjmc","lxdh","lxdh2","lxdh3","jtdz","jtyb","qq","email",
				"fqxm","fqsfzh","mqxm","mqsfzh","dkhj","xq1","xq2","xq3","xq4",
				"xq5","xfje","zsfje","sjhm","yxsh","xxsh","sfzyxzzrq","csrq",
				"rxrq","byny","yxshyj","xxshyj" };

		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}

}
