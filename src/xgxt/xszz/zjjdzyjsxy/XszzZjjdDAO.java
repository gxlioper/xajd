package xgxt.xszz.zjjdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * Description: 浙江机电职业技术学院学生资助DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
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
 * Time: 2008-07-22
 * </p>
 */
public class XszzZjjdDAO {
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xh = DealString.toGBK(queryModel.getXh());
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String hth = queryModel.getHth();

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
		} else {
			if (userBj.size() != 0) {
				whereSql.append(" and bjdm in ('###'");
				for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
					whereSql.append(", '");
					whereSql.append(it.next());
					whereSql.append("'");
				}
				whereSql.append(" ) ");
			}
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(hth)) {
			whereSql.append(" and hth = ?");
			values.add(hth);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny" };
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
	 *  获取学生获得资助等信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuZzqk(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		stuList.put("yhzzqk_gjzxdk",dao.getOneRs("SELECT SUM(xfdk) je FROM view_xszz_zjjd_gjzxdk WHERE xxsh='通过' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjzxj",dao.getOneRs("SELECT SUM(zxjje) je FROM view_zjjd_gjzxjsqb WHERE xxsh='通过' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjlzjxj",dao.getOneRs("SELECT count(*)*5000 je FROM view_xszz_zjjd_gjlzjxj WHERE xxsh='通过' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjjxj",dao.getOneRs("SELECT SUM(JLJE) je FROM view_xsjxjb WHERE xxsh='通过' AND xh=? AND JXJDM='001'", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_xyjxj",dao.getOneRs("SELECT SUM(JLJE) je FROM view_xsjxjb WHERE xxsh='通过' AND xh=? AND JXJDM in ('002','003','004','005')", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_qgzx",dao.getOneRs("SELECT SUM(cjje) je FROM view_xscjff WHERE xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_xfjm",dao.getOneRs("SELECT SUM(XFJMJE) je FROM view_xszz_zjjd_xfjm WHERE xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_lxbz",dao.getOneRs("SELECT SUM(LSBZJE) je FROM view_xszz_zjjd_lsbz WHERE xh=?", new String[]{pkVal}, "je"));
		return stuList;
	}

	/**
	 * 获取困难生详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrks,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk,yhzzqk_gjzxdk,yhzzqk_gjzxj,yhzzqk_gjjxj,yhzzqk_gjlzjxj,yhzzqk_xyjxj,yhzzqk_qgzx,yhzzqk_xfjm,yhzzqk_lxbz,yhzzqk_ddzfshjxj,yhzzqk_hj,jtzszrzhqk,jttfywsj,jtcyycjnmndlqk,jtcysyqk,jtqzqk,qtqk,jtjjqkxxsm,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_zjjd_knsxx where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "csrq", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrks", "sfgc", "sfdq", "sflszn",
				"xxtxdz", "yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
				"jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk", "yhzzqk_gjzxj",
				"yhzzqk_gjjxj", "yhzzqk_gjlzjxj", "yhzzqk_xyjxj",
				"yhzzqk_qgzx", "yhzzqk_xfjm", "yhzzqk_lxbz",
				"yhzzqk_ddzfshjxj", "yhzzqk_hj", "jtzszrzhqk", "jttfywsj",
				"jtcyycjnmndlqk", "jtcysyqk", "jtqzqk", "qtqk", "jtjjqkxxsm",
				"mzbm_xxtxdz", "mzbm_yzbm", "mzbm_lxdh", "sqsj", "fdysh",
				"fdyshyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 保存困难生申请信息，成功返回TRUE，反之返回FALSE saveKnsSqxx ---- 保存困难生申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel knsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(knsModel.getNd(), "", 1);
		String xh = Base.chgNull(knsModel.getXh(), "", 1);
		String jtrks = Base.chgNull(knsModel.getJtrks(), "", 1);
		String sfgc = Base.chgNull(knsModel.getSfgc(), "", 1);
		String sfdq = Base.chgNull(knsModel.getSfdq(), "", 1);
		String sflszn = Base.chgNull(knsModel.getSflszn(), "", 1);
		String xxtxdz = Base.chgNull(knsModel.getXxtxdz(), "", 1);
		String yzbm = Base.chgNull(knsModel.getYzbm(), "", 1);
		String lxdh = Base.chgNull(knsModel.getLxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(knsModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(knsModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(knsModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(knsModel.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(knsModel.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(knsModel.getJtcy1_sr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(knsModel.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(knsModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(knsModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(knsModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(knsModel.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(knsModel.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(knsModel.getJtcy2_sr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(knsModel.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(knsModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(knsModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(knsModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(knsModel.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(knsModel.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(knsModel.getJtcy3_sr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(knsModel.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(knsModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(knsModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(knsModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(knsModel.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(knsModel.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(knsModel.getJtcy4_sr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(knsModel.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(knsModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(knsModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(knsModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(knsModel.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(knsModel.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(knsModel.getJtcy5_sr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(knsModel.getJtcy5_jkzk(), "", 1);
		String yhzzqk_gjzxdk = Base.chgNull(knsModel.getYhzzqk_gjzxdk(), "", 1);
		String yhzzqk_gjzxj = Base.chgNull(knsModel.getYhzzqk_gjzxj(), "", 1);
		String yhzzqk_gjjxj = Base.chgNull(knsModel.getYhzzqk_gjjxj(), "", 1);
		String yhzzqk_gjlzjxj = Base.chgNull(knsModel.getYhzzqk_gjlzjxj(), "", 1);
		String yhzzqk_xyjxj = Base.chgNull(knsModel.getYhzzqk_xyjxj(), "", 1);
		String yhzzqk_qgzx = Base.chgNull(knsModel.getYhzzqk_qgzx(), "", 1);
		String yhzzqk_xfjm = Base.chgNull(knsModel.getYhzzqk_xfjm(), "", 1);
		String yhzzqk_lxbz = Base.chgNull(knsModel.getYhzzqk_lxbz(), "", 1);
		String yhzzqk_ddzfshjxj = Base.chgNull(knsModel.getYhzzqk_ddzfshjxj(),
				"", 1);
		String yhzzqk_hj = Base.chgNull(knsModel.getYhzzqk_hj(), "", 1);
		String jtzszrzhqk = Base.chgNull(knsModel.getJtzszrzhqk(), "", 1);
		String jttfywsj = Base.chgNull(knsModel.getJttfywsj(), "", 1);
		String jtcyycjnmndlqk = Base.chgNull(knsModel.getJtcyycjnmndlqk(), "",
				1);
		String jtcysyqk = Base.chgNull(knsModel.getJtcysyqk(), "", 1);
		String jtqzqk = Base.chgNull(knsModel.getJtqzqk(), "", 1);
		String qtqk = Base.chgNull(knsModel.getQtqk(), "", 1);
		String jtjjqkxxsm = Base.chgNull(knsModel.getJtjjqkxxsm(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(knsModel.getMzbm_xxtxdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(knsModel.getMzbm_yzbm(), "", 1);
		String mzbm_lxdh = Base.chgNull(knsModel.getMzbm_lxdh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zjjd_knsxx", new String[] { "nd",
					"xh", "jtrks", "sfgc", "sfdq", "sflszn", "xxtxdz", "yzbm",
					"lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
					"jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr",
					"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
					"jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk",
					"yhzzqk_gjzxj", "yhzzqk_gjjxj", "yhzzqk_gjlzjxj",
					"yhzzqk_xyjxj", "yhzzqk_qgzx", "yhzzqk_xfjm",
					"yhzzqk_lxbz", "yhzzqk_ddzfshjxj", "yhzzqk_hj",
					"jtzszrzhqk", "jttfywsj", "jtcyycjnmndlqk", "jtcysyqk",
					"jtqzqk", "qtqk", "jtjjqkxxsm", "mzbm_xxtxdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj" }, new String[] { nd, xh, jtrks, sfgc,
					sfdq, sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm, jtcy1_nl,
					jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk,
					jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
					jtcy2_sr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
					jtcy3_gzdw, jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr,
					jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
					jtcy5_zy, jtcy5_sr, jtcy5_jkzk, yhzzqk_gjzxdk,
					yhzzqk_gjzxj, yhzzqk_gjjxj, yhzzqk_gjlzjxj, yhzzqk_xyjxj,
					yhzzqk_qgzx, yhzzqk_xfjm, yhzzqk_lxbz, yhzzqk_ddzfshjxj,
					yhzzqk_hj, jtzszrzhqk, jttfywsj, jtcyycjnmndlqk, jtcysyqk,
					jtqzqk, qtqk, jtjjqkxxsm, mzbm_xxtxdz, mzbm_yzbm,
					mzbm_lxdh, now }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zjjd_knsxx", new String[] {
					"jtrks", "sfgc", "sfdq", "sflszn", "xxtxdz", "yzbm",
					"lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
					"jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr",
					"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
					"jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk",
					"yhzzqk_gjzxj", "yhzzqk_gjjxj", "yhzzqk_gjlzjxj",
					"yhzzqk_xyjxj", "yhzzqk_qgzx", "yhzzqk_xfjm",
					"yhzzqk_lxbz", "yhzzqk_ddzfshjxj", "yhzzqk_hj",
					"jtzszrzhqk", "jttfywsj", "jtcyycjnmndlqk", "jtcysyqk",
					"jtqzqk", "qtqk", "jtjjqkxxsm", "mzbm_xxtxdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj",
					"xxsh", "xxshyj" }, new String[] { jtrks, sfgc, sfdq,
					sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm,
					jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr,
					jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
					jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
					jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk,
					jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
					jtcy5_sr, jtcy5_jkzk, yhzzqk_gjzxdk, yhzzqk_gjzxj,
					yhzzqk_gjjxj, yhzzqk_gjlzjxj, yhzzqk_xyjxj, yhzzqk_qgzx,
					yhzzqk_xfjm, yhzzqk_lxbz, yhzzqk_ddzfshjxj, yhzzqk_hj,
					jtzszrzhqk, jttfywsj, jtcyycjnmndlqk, jtcysyqk, jtqzqk,
					qtqk, jtjjqkxxsm, mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, now,
					"未审核", "", "未审核", "", "未审核", "" }, "nd||xh", nd + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断困难生是否重复，重复且通过学校审核的返回2，重复且通过学院审核的返回3，重复但没通过审核的返回1，没有重复的返回-1 isknsdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ? and xxsh in ('一般困难','困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ? and xysh in ('一般困难','困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}

	/**
	 * 获取困难生申请表详细信息
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(knsModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(knsModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(knsModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(knsModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(knsModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(knsModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(knsModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(knsModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("csrq", Base.chgNull(knsModel.getCsrq(), "", 1));
		stuList.put("xydm", Base.chgNull(knsModel.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(knsModel.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(knsModel.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("jtrks", Base.chgNull(knsModel.getJtrks(), "", 1));
		stuList.put("sfgc", Base.chgNull(knsModel.getSfgc(), "", 1));
		stuList.put("sfdq", Base.chgNull(knsModel.getSfdq(), "", 1));
		stuList.put("sflszn", Base.chgNull(knsModel.getSflszn(), "", 1));
		stuList.put("xxtxdz", Base.chgNull(knsModel.getXxtxdz(), "", 1));
		stuList.put("yzbm", Base.chgNull(knsModel.getYzbm(), "", 1));
		stuList.put("lxdh", Base.chgNull(knsModel.getLxdh(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(knsModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(knsModel.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(knsModel.getJtcy1_gx(), "", 1));
		stuList
				.put("jtcy1_gzdw", Base
						.chgNull(knsModel.getJtcy1_gzdw(), "", 1));
		stuList.put("jtcy1_zy", Base.chgNull(knsModel.getJtcy1_zy(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(knsModel.getJtcy1_sr(), "", 1));
		stuList
				.put("jtcy1_jkzk", Base
						.chgNull(knsModel.getJtcy1_jkzk(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(knsModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(knsModel.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(knsModel.getJtcy2_gx(), "", 1));
		stuList
				.put("jtcy2_gzdw", Base
						.chgNull(knsModel.getJtcy2_gzdw(), "", 1));
		stuList.put("jtcy2_zy", Base.chgNull(knsModel.getJtcy2_zy(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(knsModel.getJtcy2_sr(), "", 1));
		stuList
				.put("jtcy2_jkzk", Base
						.chgNull(knsModel.getJtcy2_jkzk(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(knsModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(knsModel.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(knsModel.getJtcy3_gx(), "", 1));
		stuList
				.put("jtcy3_gzdw", Base
						.chgNull(knsModel.getJtcy3_gzdw(), "", 1));
		stuList.put("jtcy3_zy", Base.chgNull(knsModel.getJtcy3_zy(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(knsModel.getJtcy3_sr(), "", 1));
		stuList
				.put("jtcy3_jkzk", Base
						.chgNull(knsModel.getJtcy3_jkzk(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(knsModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(knsModel.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(knsModel.getJtcy4_gx(), "", 1));
		stuList
				.put("jtcy4_gzdw", Base
						.chgNull(knsModel.getJtcy4_gzdw(), "", 1));
		stuList.put("jtcy4_zy", Base.chgNull(knsModel.getJtcy4_zy(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(knsModel.getJtcy4_sr(), "", 1));
		stuList
				.put("jtcy4_jkzk", Base
						.chgNull(knsModel.getJtcy4_jkzk(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(knsModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(knsModel.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(knsModel.getJtcy5_gx(), "", 1));
		stuList
				.put("jtcy5_gzdw", Base
						.chgNull(knsModel.getJtcy5_gzdw(), "", 1));
		stuList.put("jtcy5_zy", Base.chgNull(knsModel.getJtcy5_zy(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(knsModel.getJtcy5_sr(), "", 1));
		stuList
				.put("jtcy5_jkzk", Base
						.chgNull(knsModel.getJtcy5_jkzk(), "", 1));
		stuList.put("yhzzqk_gjzxdk", Base.chgNull(knsModel.getYhzzqk_gjzxdk(),
				"", 1));
		stuList.put("yhzzqk_gjzxj", Base.chgNull(knsModel.getYhzzqk_gjzxj(),
				"", 1));
		stuList.put("yhzzqk_gjjxj", Base.chgNull(knsModel.getYhzzqk_gjjxj(),
				"", 1));
		stuList.put("yhzzqk_gjlzjxj", Base.chgNull(knsModel.getYhzzqk_gjlzjxj(),
				"", 1));
		stuList.put("yhzzqk_xyjxj", Base.chgNull(knsModel.getYhzzqk_xyjxj(),
				"", 1));
		stuList.put("yhzzqk_qgzx", Base.chgNull(knsModel.getYhzzqk_qgzx(), "",
				1));
		stuList.put("yhzzqk_xfjm", Base.chgNull(knsModel.getYhzzqk_xfjm(), "",
				1));
		stuList.put("yhzzqk_lxbz", Base.chgNull(knsModel.getYhzzqk_lxbz(), "",
				1));
		stuList.put("yhzzqk_ddzfshjxj", Base.chgNull(knsModel
				.getYhzzqk_ddzfshjxj(), "", 1));
		stuList.put("yhzzqk_hj", Base.chgNull(knsModel.getYhzzqk_hj(), "", 1));
		stuList
				.put("jtzszrzhqk", Base
						.chgNull(knsModel.getJtzszrzhqk(), "", 1));
		stuList.put("jttfywsj", Base.chgNull(knsModel.getJttfywsj(), "", 1));
		stuList.put("jtcyycjnmndlqk", Base.chgNull(
				knsModel.getJtcyycjnmndlqk(), "", 1));
		stuList.put("jtcysyqk", Base.chgNull(knsModel.getJtcysyqk(), "", 1));
		stuList.put("jtqzqk", Base.chgNull(knsModel.getJtqzqk(), "", 1));
		stuList.put("qtqk", Base.chgNull(knsModel.getQtqk(), "", 1));
		stuList
				.put("jtjjqkxxsm", Base
						.chgNull(knsModel.getJtjjqkxxsm(), "", 1));
		stuList.put("mzbm_xxtxdz", Base.chgNull(knsModel.getMzbm_xxtxdz(), "",
				1));
		stuList.put("mzbm_yzbm", Base.chgNull(knsModel.getMzbm_yzbm(), "", 1));
		stuList.put("mzbm_lxdh", Base.chgNull(knsModel.getMzbm_lxdh(), "", 1));
		stuList.put("sqsj", Base.chgNull(knsModel.getSqsj(), "", 1));
		stuList.put("fdysh", Base.chgNull(knsModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(knsModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(knsModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(knsModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(knsModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(knsModel.getXxshyj(), "", 1));

		return stuList;
	}

	/**
	 * 删除困难信息 delKnsxx ---- 删除困难生信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"' and xxsh not in ('一般困难','困难','特殊困难')";
				} else {
					sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"' and xysh not in ('一般困难','困难','特殊困难')";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改困难生审核结果 modKnsxx ---- 批量修改困难生审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update zjjd_knsxx set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update zjjd_knsxx set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
				} else {
					sqlT[i] = "update zjjd_knsxx set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 困难生查询表头 knstit ---- 困难生表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", "身份证号", "系名称", "班级名称", "申请时间", "班主任审核", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 困难生查询结果 knsres ---- 困难生结果
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0){
					sql = "select (case when nvl(xysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where fdysh in ('一般困难','困难','特殊困难')";
				} else {
					sql = "select (case when nvl(fdysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where xysh in ('一般困难','困难','特殊困难')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 保存困难生审核信息，成功返回TRUE，反之返回FALSE saveKnsShxx ---- 保存困难生审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel saveKnsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xh = Base.chgNull(saveKnsModel.getXh(), "", 1);
		String nd = Base.chgNull(saveKnsModel.getNd(), "", 1);
		String fdysh = Base.chgNull(saveKnsModel.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(saveKnsModel.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(saveKnsModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(saveKnsModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(saveKnsModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(saveKnsModel.getXxshyj(), "", 1);
		String sHave = isKnsDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zjjd_knsxx", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("zjjd_knsxx",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("zjjd_knsxx",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取国家助学金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,zxjdjdm,zxjdjmc,zxjje,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jthkrs,jtyzsr,jtrjysr,jtsrly,yzbm,jtzz,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zxjdjdm||'!!OneSpile!!'||zxjdjmc||'!!OneSpile!!'||zxjje dm from view_zjjd_gjzxjsqb where nd||xh = ?";
		String[] colList = new String[] { "nd", "zxjdjdm", "zxjdjmc", "zxjje",
				"xh", "xm", "xb", "nj", "xz", "mzmc", "zzmmmc", "sfzh", "rxny",
				"csrq", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh",
				"jthk", "jthkrs", "jtyzsr", "jtrjysr", "jtsrly", "yzbm",
				"jtzz", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "sqly", "sqsj", "xysh", "xyshyj", "xxsh",
				"xxshyj", "dm" };
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
	 * 保存国家助学金申请信息，成功返回TRUE，反之返回FALSE saveGjzxjSqxx ---- 保存国家助学金申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjzxjModel.getNd(), "", 1);
		String zxjdjdm = Base.chgNull(gjzxjModel.getZxjdjdm(), "", 1);
		String zxjdjmc = Base.chgNull(gjzxjModel.getZxjdjmc(), "", 1);
		String zxjje = Base.chgNull(gjzxjModel.getZxjje(), "", 1);
		String xh = Base.chgNull(gjzxjModel.getXh(), "", 1);
		String lxdh = Base.chgNull(gjzxjModel.getLxdh(), "", 1);
		String jthk = Base.chgNull(gjzxjModel.getJthk(), "", 1);
		String jthkrs = Base.chgNull(gjzxjModel.getJthkrs(), "", 1);
		String jtyzsr = Base.chgNull(gjzxjModel.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(gjzxjModel.getJtrjysr(), "", 1);
		String jtsrly = Base.chgNull(gjzxjModel.getJtsrly(), "", 1);
		String yzbm = Base.chgNull(gjzxjModel.getYzbm(), "", 1);
		String jtzz = Base.chgNull(gjzxjModel.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(gjzxjModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(gjzxjModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(gjzxjModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(gjzxjModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(gjzxjModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(gjzxjModel.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(gjzxjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zjjd_gjzxjsqb", new String[] {
					"nd", "zxjdjdm", "zxjdjmc", "zxjje", "xh", "lxdh", "jthk",
					"jthkrs", "jtyzsr", "jtrjysr", "jtsrly", "yzbm", "jtzz",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { nd, zxjdjdm, zxjdjmc, zxjje, xh, lxdh, jthk,
							jthkrs, jtyzsr, jtrjysr, jtsrly, yzbm, jtzz,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm,
							jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl,
							jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
					"zxjdjdm", "zxjdjmc", "zxjje", "lxdh", "jthk", "jthkrs",
					"jtyzsr", "jtrjysr", "jtsrly", "yzbm", "jtzz", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly", "xysh",
					"xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					zxjdjdm, zxjdjmc, zxjje, lxdh, jthk, jthkrs, jtyzsr,
					jtrjysr, jtsrly, yzbm, jtzz, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm, jtcy5_nl,
					jtcy5_gx, jtcy5_gzdw, sqly, "未审核", "", "未审核", "", now },
					"nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 获取国家助学金申请表详细信息
	 * 
	 * @param gjzxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjzxjModel.getNd(), "", 1));
		stuList.put("zxjdjmc", Base.chgNull(gjzxjModel.getZxjdjmc(), "", 1));
		stuList.put("zxjje", Base.chgNull(gjzxjModel.getZxjje(), "", 1));
		stuList.put("xh", Base.chgNull(gjzxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjzxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjzxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjzxjModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjzxjModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjzxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjzxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjzxjModel.getSfzh(), "", 1));
		stuList.put("rxny", Base.chgNull(gjzxjModel.getRxny(), "", 1));
		stuList.put("csrq", Base.chgNull(gjzxjModel.getCsrq(), "", 1));
		stuList.put("xymc", Base.chgNull(gjzxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjzxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjzxjModel.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjzxjModel.getLxdh(), "", 1));
		stuList.put("jthk", Base.chgNull(gjzxjModel.getJthk(), "", 1));
		stuList.put("jthkrs", Base.chgNull(gjzxjModel.getJthkrs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(gjzxjModel.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(gjzxjModel.getJtrjysr(), "", 1));
		stuList.put("jtsrly", Base.chgNull(gjzxjModel.getJtsrly(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjzxjModel.getYzbm(), "", 1));
		stuList.put("jtzz", Base.chgNull(gjzxjModel.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(gjzxjModel.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "",
				1));
		stuList.put("jtcy2_xm", Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(gjzxjModel.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "",
				1));
		stuList.put("jtcy3_xm", Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(gjzxjModel.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "",
				1));
		stuList.put("jtcy4_xm", Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(gjzxjModel.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "",
				1));
		stuList.put("jtcy5_xm", Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(gjzxjModel.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_gzdw", Base.chgNull(gjzxjModel.getJtcy5_gzdw(), "",
				1));
		stuList.put("sqly", Base.chgNull(gjzxjModel.getSqly(), "", 1));
		stuList.put("xysh", Base.chgNull(gjzxjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjzxjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjzxjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjzxjModel.getXxshyj(), "", 1));

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
				sqlT[i] = "delete zjjd_gjzxjsqb where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete zjjd_gjzxjsqb where nd||xh='"+pkT[i]+"' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改国家助学金审核结果 modGjzxjxx ---- 批量修改国家助学金审核结果
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
				sqlT[i] = "update zjjd_gjzxjsqb set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update zjjd_gjzxjsqb set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 判断国家助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zjjd_gjzxjsqb where xh = ? and nd = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zjjd_gjzxjsqb where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 国家助学金查询表头 gjzxjtit ---- 国家助学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "zxjdjmc", "zxjje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", Base.YXPZXY_KEY+"名称", "班级名称", "助学金名称", "助学金金额", "申请时间", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学金查询结果 gjzxjres ---- 国家助学结果
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
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "zxjdjmc", "zxjje", "sqsj", "xysh",
				"xxsh" };

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
	public boolean saveGjzxjShxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(gjzxjModel.getXh(), "", 1);
		String nd = Base.chgNull(gjzxjModel.getNd(), "", 1);
		String xysh = Base.chgNull(gjzxjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjzxjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjzxjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjzxjModel.getXxshyj(), "", 1);
		String sHave = isGjzxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获得国家助学金列表 gjzxjList ---- 国家助学金列表
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select '' dm,'-------请选择-------' mc from dual union select zxjdjdm||'!!OneSpile!!'||zxjdjmc||'!!OneSpile!!'||zxjje dm,zxjdjmc mc from zjjd_gjzxjdmb order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}

	/**
	 * 获取国家励志奖学金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtrkzs,jtyzsr,rjysr,srly,jtzz,yzbm,xxcj,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_gjlzjxj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "csrq", "rxny", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "chhzjl", "jthk",
				"jtrkzs", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "xxcj",
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
	 * 得到国家励志奖学金申请权限
	 * 
	 * @param sUserType
	 * @param userDep
	 * @param xh
	 * @param nd
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
	 * 保存国家励志奖学金申请信息，成功返回TRUE，反之返回FALSE saveGjlzjxjSqxx ---- 保存国家励志奖学金申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjlzjxjModel.getNd(), "", 1);
		String xh = Base.chgNull(gjlzjxjModel.getXh(), "", 1);
		String lxdh = Base.chgNull(gjlzjxjModel.getLxdh(), "", 1);
		String chhzjl = Base.chgNull(gjlzjxjModel.getChhzjl(), "", 1);
		String jthk = Base.chgNull(gjlzjxjModel.getJthk(), "", 1);
		String jtrkzs = Base.chgNull(gjlzjxjModel.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(gjlzjxjModel.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(gjlzjxjModel.getRjysr(), "", 1);
		String srly = Base.chgNull(gjlzjxjModel.getSrly(), "", 1);
		String jtzz = Base.chgNull(gjlzjxjModel.getJtzz(), "", 1);
		String yzbm = Base.chgNull(gjlzjxjModel.getYzbm(), "", 1);
		String xxcj = Base.chgNull(gjlzjxjModel.getXxcj(), "", 1);
		String sqly = Base.chgNull(gjlzjxjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjlzjxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_gjlzjxj", new String[] {
					"nd", "xh", "lxdh", "chhzjl", "jthk", "jtrkzs", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "xxcj", "sqly" },
					new String[] { nd, xh, lxdh, chhzjl, jthk, jtrkzs, jtyzsr,
							rjysr, srly, jtzz, yzbm, xxcj, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
					"lxdh", "chhzjl", "jthk", "jtrkzs", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "xxcj", "sqly", "xysh", "xyshyj",
					"xxsh", "xxshyj", "sqsj" }, new String[] { lxdh, chhzjl,
					jthk, jtrkzs, jtyzsr, rjysr, srly, jtzz, yzbm, xxcj, sqly,
					"未审核", "", "未审核", "", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 获取国家励志奖学金申请表详细信息
	 * 
	 * @param gjlzjxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjlzjxjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjlzjxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjlzjxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjlzjxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjlzjxjModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjlzjxjModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjlzjxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjlzjxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjlzjxjModel.getSfzh(), "", 1));
		stuList.put("csrq", Base.chgNull(gjlzjxjModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(gjlzjxjModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(gjlzjxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjlzjxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjlzjxjModel.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjlzjxjModel.getLxdh(), "", 1));
		stuList.put("chhzjl", Base.chgNull(gjlzjxjModel.getChhzjl(), "", 1));
		stuList.put("jthk", Base.chgNull(gjlzjxjModel.getJthk(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(gjlzjxjModel.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(gjlzjxjModel.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(gjlzjxjModel.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(gjlzjxjModel.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(gjlzjxjModel.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjlzjxjModel.getYzbm(), "", 1));
		stuList.put("xxcj", Base.chgNull(gjlzjxjModel.getXxcj(), "", 1));
		stuList.put("sqly", Base.chgNull(gjlzjxjModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(gjlzjxjModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(gjlzjxjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjlzjxjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjlzjxjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjlzjxjModel.getXxshyj(), "", 1));

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
				sqlT[i] = "delete xszz_zjjd_gjlzjxj where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_gjlzjxj where nd||xh='"+pkT[i]+"' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家励志奖学金审核结果 modGjlzjxjxx ---- 批量修改国家励志奖学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_gjlzjxj set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_gjlzjxj set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家励志奖学金查询表头 gjlzjxjtit ---- 国家励志奖学金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 国家励志奖学金查询结果 gjlzjxjres ---- 国家励志奖学结果
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
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "sqsj", "xysh", "xxsh" };

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
	public boolean saveGjlzjxjShxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(gjlzjxjModel.getXh(), "", 1);
		String nd = Base.chgNull(gjlzjxjModel.getNd(), "", 1);
		String xysh = Base.chgNull(gjlzjxjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjlzjxjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjlzjxjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjlzjxjModel.getXxshyj(), "", 1);
		String sHave = isGjlzjxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * 判断国家励志奖学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjlzjxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_gjlzjxj where xh = ? and nd = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_gjlzjxj where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取临时困难补助详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select id,xh,xm,xb,nj,mzmc,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,lsbzdm,lsbzmc,lsbzje,sfkns,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,lsbzdm||'!!OneSpile!!'||lsbzmc||'!!OneSpile!!'||lsbzje dm from view_xszz_zjjd_lsbz where id = ?";
		String[] colList = new String[] { "id", "xh", "xm", "xb", "nj", "mzmc",
				"csrq", "rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"lsbzdm", "lsbzmc", "lsbzje", "sfkns", "sqly", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "dm" };
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
	public String getLsbzSqQx(String sUserType, String userDep, String xh,
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
	 * 获得临时困难补助列表 lsbzList ---- 临时困难补助列表
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getLsbzList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select '' dm,'-------请选择-------' mc from dual union select lsbzdm||'!!OneSpile!!'||lsbzmc||'!!OneSpile!!'||lsbzje dm,lsbzmc mc from ZJJD_LSBZDMB order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
	
	/**
	 * 保存临时困难补助申请信息，成功返回TRUE，反之返回FALSE saveLsbzSqxx ---- 保存临时困难补助申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzSqxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(lsbzModel.getXh(), "", 1);
		String id = Base.chgNull(lsbzModel.getId(), "", 1);
		String lsbzdm = Base.chgNull(lsbzModel.getLsbzdm(), "", 1);
		String lsbzmc = Base.chgNull(lsbzModel.getLsbzmc(), "", 1);
		String lsbzje = Base.chgNull(lsbzModel.getLsbzje(), "", 1);
		String sfkns = Base.chgNull(lsbzModel.getSfkns(), "", 1);
		String sqly = Base.chgNull(lsbzModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isLsbzDataCf(id);
		if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
					"xh", "lsbzdm", "lsbzmc", "lsbzje", "sfkns", "sqly",
					"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					xh, lsbzdm, lsbzmc, lsbzje, sfkns, sqly, "未审核", "",
					"未审核", "", now }, "id", id, request);
		} else {
			bFlag = StandardOperation.insert("xszz_zjjd_lsbz",
					new String[] { "xh", "lsbzdm", "lsbzmc", "lsbzje",
							"sfkns", "sqly" }, new String[] { xh, lsbzdm,
							lsbzmc, lsbzje, sfkns, sqly }, request);
		}
		return bFlag;
	}
	
	/**
	 * 判断临时困难补助是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isLsbzDataCf(String id) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_lsbz where id = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { id }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_lsbz where id = ?";
			num = dao.getOneRs(sql, new String[] { id }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取临时困难补助申请表详细信息
	 * 
	 * @param lsbzModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzSqb(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

//		stuList.put("nd", Base.chgNull(lsbzModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(lsbzModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(lsbzModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(lsbzModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(lsbzModel.getNj(), "", 1));
		stuList.put("mzmc", Base.chgNull(lsbzModel.getMzmc(), "", 1));
		stuList.put("csrq", Base.chgNull(lsbzModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(lsbzModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(lsbzModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(lsbzModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(lsbzModel.getBjmc(), "", 1));
		stuList.put("lsbzmc", Base.chgNull(lsbzModel.getLsbzmc(), "", 1));
		stuList.put("lsbzje", Base.chgNull(lsbzModel.getLsbzje(), "", 1));
		stuList.put("sfkns", Base.chgNull(lsbzModel.getSfkns(), "", 1));
		stuList.put("sqly", Base.chgNull(lsbzModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(lsbzModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(lsbzModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(lsbzModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(lsbzModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(lsbzModel.getXxshyj(), "", 1));

		return stuList;
	}
	
	/**
	 * 删除临时困难补助信息 delLsbzxx ---- 删除临时困难补助信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_lsbz where id='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_lsbz where id='"+pkT[i]+"' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改临时困难补助审核结果 modLsbzxx ---- 批量修改临时困难补助审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_lsbz set xxsh='"+shjg+"' where id='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_lsbz set xysh='"+shjg+"' where id='"+pkT[i]+"' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 临时困难补助查询表头 Lsbztit ---- 临时困难补助金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbzTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm",
				"xb", "xymc", "bjmc", "lsbzmc", "lsbzje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "学号", "姓名",
				"性别", Base.YXPZXY_KEY+"名称", "班级名称", "临时补助名称", "临时补助金额", "申请时间", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 临时困难补助查询结果 Lsbzres ---- 临时困难补助结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbzRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm",
				"xb", "xymc", "bjmc", "lsbzmc", "lsbzje", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 临时困难补助审核信息，成功返回TRUE，反之返回FALSE saveLsbzShxx ---- 保存临时困难补助审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzShxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String id = Base.chgNull(lsbzModel.getId(), "", 1);
		String xysh = Base.chgNull(lsbzModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(lsbzModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(lsbzModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(lsbzModel.getXxshyj(), "", 1);
		String sHave = isLsbzDataCf(id);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"id", id, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"id", id, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取国家助学贷款详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,rxny,byny,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,yxjtzz,lxdh,yzbm,jz1_xm,jz1_lxdh,jz2_xm,jz2_lxdh,jtjjqk,xfdk,yjxf,rxlbjgkm,rxlbktgkm,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_gjzxdk where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"rxny", "byny", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh",
				"jz2_xm", "jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
				"rxlbktgkm", "sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj",
				"xxsh", "xxshyj" };
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
	 * 保存国家助学贷款申请信息，成功返回TRUE，反之返回FALSE saveGjlzjxjSqxx ---- 保存国家助学贷款申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjzxdkModel.getNd(), "", 1);
		String xh = Base.chgNull(gjzxdkModel.getXh(), "", 1);
		String yxjtzz = Base.chgNull(gjzxdkModel.getYxjtzz(), "", 1);
		String lxdh = Base.chgNull(gjzxdkModel.getLxdh(), "", 1);
		String yzbm = Base.chgNull(gjzxdkModel.getYzbm(), "", 1);
		String jz1_xm = Base.chgNull(gjzxdkModel.getJz1_xm(), "", 1);
		String jz1_lxdh = Base.chgNull(gjzxdkModel.getJz1_lxdh(), "", 1);
		String jz2_xm = Base.chgNull(gjzxdkModel.getJz2_xm(), "", 1);
		String jz2_lxdh = Base.chgNull(gjzxdkModel.getJz2_lxdh(), "", 1);
		String jtjjqk = Base.chgNull(gjzxdkModel.getJtjjqk(), "", 1);
		String xfdk = Base.chgNull(gjzxdkModel.getXfdk(), "", 1);
		String yjxf = Base.chgNull(gjzxdkModel.getYjxf(), "", 1);
		String rxlbjgkm = Base.chgNull(gjzxdkModel.getRxlbjgkm(), "", 1);
		String rxlbktgkm = Base.chgNull(gjzxdkModel.getRxlbktgkm(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_gjzxdk", new String[] {
					"nd", "xh", "yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh",
					"jz2_xm", "jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
					"rxlbktgkm" }, new String[] { nd, xh, yxjtzz, lxdh, yzbm,
					jz1_xm, jz1_lxdh, jz2_xm, jz2_lxdh, jtjjqk, xfdk, yjxf,
					rxlbjgkm, rxlbktgkm }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_gjzxdk", new String[] {
					"yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh", "jz2_xm",
					"jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
					"rxlbktgkm", "fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh",
					"xxshyj", "sqsj" },
					new String[] { yxjtzz, lxdh, yzbm, jz1_xm, jz1_lxdh,
							jz2_xm, jz2_lxdh, jtjjqk, xfdk, yjxf, rxlbjgkm,
							rxlbktgkm, "未审核", "", "未审核", "", "未审核", "", now },
					"nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * 判断国家助学贷款是否重复，重复且通过学校审核的返回2，重复且通过学院审核的返回3，重复但没通过审核的返回1，没有重复的返回-1 isgjzxdkdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxdkDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ? and xysh in ('通过')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取国家助学贷款申请表详细信息
	 * 
	 * @param gjzxdkModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjzxdkModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjzxdkModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjzxdkModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjzxdkModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjzxdkModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjzxdkModel.getXz(), "", 1));
		stuList.put("rxny", Base.chgNull(gjzxdkModel.getRxny(), "", 1));
		stuList.put("byny", Base.chgNull(gjzxdkModel.getByny(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjzxdkModel.getSfzh(), "", 1));
		stuList.put("xymc", Base.chgNull(gjzxdkModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjzxdkModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjzxdkModel.getBjmc(), "", 1));
		stuList.put("yxjtzz", Base.chgNull(gjzxdkModel.getYxjtzz(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjzxdkModel.getLxdh(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjzxdkModel.getYzbm(), "", 1));
		stuList.put("jz1_xm", Base.chgNull(gjzxdkModel.getJz1_xm(), "", 1));
		stuList.put("jz1_lxdh", Base.chgNull(gjzxdkModel.getJz1_lxdh(), "", 1));
		stuList.put("jz2_xm", Base.chgNull(gjzxdkModel.getJz2_xm(), "", 1));
		stuList.put("jz2_lxdh", Base.chgNull(gjzxdkModel.getJz2_lxdh(), "", 1));
		stuList.put("jtjjqk", Base.chgNull(gjzxdkModel.getJtjjqk(), "", 1));
		stuList.put("xfdk", Base.chgNull(gjzxdkModel.getXfdk(), "", 1));
		stuList.put("yjxf", Base.chgNull(gjzxdkModel.getYjxf(), "", 1));
		stuList.put("rxlbjgkm", Base.chgNull(gjzxdkModel.getRxlbjgkm(), "", 1));
		stuList.put("rxlbktgkm", Base.chgNull(gjzxdkModel.getRxlbktgkm(), "", 1));
		stuList.put("fdysh", Base.chgNull(gjzxdkModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(gjzxdkModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(gjzxdkModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjzxdkModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjzxdkModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjzxdkModel.getXxshyj(), "", 1));
		String sqsj = Base.chgNull(gjzxdkModel.getSqsj(), "", 1);
		
		if (sqsj != null && sqsj.length() == 10){
			String year = sqsj.substring(0, 4);
			String mon = sqsj.substring(5, 7);
			String day = sqsj.substring(8);
			
			sqsj = year + "年" + mon + "月" + day + "日";
		}
		stuList.put("sqsj", sqsj);

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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"' and xysh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家助学贷款审核结果 modGjzxdkxx ---- 批量修改国家助学贷款审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_gjzxdk set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_zjjd_gjzxdk set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_zjjd_gjzxdk set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家助学贷款查询表头 gjzxdktit ---- 国家助学贷款表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfdk", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", "系名称", "班级名称", "贷款金额", "申请时间", "班主任审核", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 国家助学贷款查询结果 gjzxdkres ---- 国家助学贷款结果
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where fdysh in ('通过')";
				} else {
					sql = "select (case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xfdk,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfdk", "sqsj", "fdysh", "xysh", "xxsh" };

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
	public boolean saveGjzxdkShxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xh = Base.chgNull(gjzxdkModel.getXh(), "", 1);
		String nd = Base.chgNull(gjzxdkModel.getNd(), "", 1);
		String fdysh = Base.chgNull(gjzxdkModel.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(gjzxdkModel.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(gjzxdkModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjzxdkModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjzxdkModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjzxdkModel.getXxshyj(), "", 1);
		String sHave = isGjzxdkDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_gjzxdk", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_gjzxdk",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_gjzxdk",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取学费减免详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,mzmc,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xfjmdm,xfjmmc,xfjmje,sfkns,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,xfjmdm||'!!OneSpile!!'||xfjmmc||'!!OneSpile!!'||xfjmje dm from view_xszz_zjjd_xfjm where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "mzmc",
				"csrq", "rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"xfjmdm", "xfjmmc", "xfjmje", "sfkns", "sqly", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "dm" };
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
	 * 得到学费减免申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getXfjmSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='学费减免' and b.xh=? ";
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
	 * 获得学费减免列表 xfjmList ---- 学费减免列表
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXfjmList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select '' dm,'-------请选择-------' mc from dual union select xfjmdm||'!!OneSpile!!'||xfjmmc||'!!OneSpile!!'||xfjmje dm,xfjmmc mc from ZJJD_XFJMDMB order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
	
	/**
	 * 保存学费减免申请信息，成功返回TRUE，反之返回FALSE saveXfjmSqxx ---- 保存学费减免申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmSqxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(xfjmModel.getNd(), "", 1);
		String xh = Base.chgNull(xfjmModel.getXh(), "", 1);
		String xfjmdm = Base.chgNull(xfjmModel.getXfjmdm(), "", 1);
		String xfjmmc = Base.chgNull(xfjmModel.getXfjmmc(), "", 1);
		String xfjmje = Base.chgNull(xfjmModel.getXfjmje(), "", 1);
		String sfkns = Base.chgNull(xfjmModel.getSfkns(), "", 1);
		String sqly = Base.chgNull(xfjmModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isXfjmDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_xfjm",
					new String[] { "nd", "xh", "xfjmdm", "xfjmmc", "xfjmje",
							"sfkns", "sqly" }, new String[] { nd, xh, xfjmdm,
							xfjmmc, xfjmje, sfkns, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
					"nd", "xh", "xfjmdm", "xfjmmc", "xfjmje", "sfkns", "sqly",
					"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					nd, xh, xfjmdm, xfjmmc, xfjmje, sfkns, sqly, "未审核", "",
					"未审核", "", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * 判断学费减免是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isXfjmdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isXfjmDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_xfjm where xh = ? and nd = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_xfjm where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取学费减免申请表详细信息
	 * 
	 * @param xfjmModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmSqb(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(xfjmModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(xfjmModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(xfjmModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(xfjmModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(xfjmModel.getNj(), "", 1));
		stuList.put("mzmc", Base.chgNull(xfjmModel.getMzmc(), "", 1));
		stuList.put("csrq", Base.chgNull(xfjmModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(xfjmModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(xfjmModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(xfjmModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(xfjmModel.getBjmc(), "", 1));
		stuList.put("xfjmmc", Base.chgNull(xfjmModel.getXfjmmc(), "", 1));
		stuList.put("xfjmje", Base.chgNull(xfjmModel.getXfjmje(), "", 1));
		stuList.put("sfkns", Base.chgNull(xfjmModel.getSfkns(), "", 1));
		stuList.put("sqly", Base.chgNull(xfjmModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(xfjmModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(xfjmModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(xfjmModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(xfjmModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(xfjmModel.getXxshyj(), "", 1));

		return stuList;
	}
	
	/**
	 * 删除学费减免信息 delLsbzxx ---- 删除学费减免信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfjmxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_xfjm where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_xfjm where nd||xh='"+pkT[i]+"' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费减免审核结果 modXfjmxx ---- 批量修改学费减免审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjmxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_xfjm set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_xfjm set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 学费减免查询表头 Xfjmtit ---- 学费减免金表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjmTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfjmmc", "xfjmje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"性别", Base.YXPZXY_KEY+"名称", "班级名称", "学费减免名称", "学费减免金额", "申请时间", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 学费减免查询结果 Xfjmres ----学费减免结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjmRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfjmmc", "xfjmje", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费减免审核信息，成功返回TRUE，反之返回FALSE saveXfjmShxx ---- 保存学费减免审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmShxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(xfjmModel.getXh(), "", 1);
		String nd = Base.chgNull(xfjmModel.getNd(), "", 1);
		String xysh = Base.chgNull(xfjmModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(xfjmModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(xfjmModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(xfjmModel.getXxshyj(), "", 1);
		String sHave = isXfjmDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * 获取学费缓缴详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,hjje,hjly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_xfhj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "hjje", "hjly", "sqsj",
				"fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * 得到学费缓缴申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='学费缓缴' and b.xh=? ";
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
	 * 保存学费缓缴申请信息，成功返回TRUE，反之返回FALSE saveXfhjSqxx ---- 保存学费缓缴申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel xfhjModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(xfhjModel.getNd(), "", 1);
		String xh = Base.chgNull(xfhjModel.getXh(), "", 1);
		String hjje = Base.chgNull(xfhjModel.getHjje(), "", 1);
		String hjly = Base.chgNull(xfhjModel.getHjly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isXfhjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_xfhj", new String[] {
					"nd", "xh", "hjje", "hjly" }, new String[] { nd, xh, hjje,
					hjly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_xfhj", new String[] {
					"hjje", "hjly", "sqsj", "fdysh", "fdyshyj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { hjje, hjly,
					now, "未审核", "", "未审核", "", "未审核", "" }, "nd||xh", nd + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * 判断学费缓缴是否重复，重复且通过学校审核的返回2，重复且通过学院审核的返回3，重复但没通过审核的返回1，没有重复的返回-1 isxfhjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isXfhjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ? and xxsh in ('通过')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ? and xysh in ('通过')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 获取学费缓缴申请表详细信息
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel xfhjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(xfhjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(xfhjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(xfhjModel.getXm(), "", 1));
		stuList.put("nj", Base.chgNull(xfhjModel.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(xfhjModel.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(xfhjModel.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(xfhjModel.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(xfhjModel.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(xfhjModel.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(xfhjModel.getBjmc(), "", 1));
		stuList.put("hjje", Base.chgNull(xfhjModel.getHjje(), "", 1));
		stuList.put("hjly", Base.chgNull(xfhjModel.getHjly(), "", 1));
		stuList.put("fdysh", Base.chgNull(xfhjModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(xfhjModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(xfhjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(xfhjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(xfhjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(xfhjModel.getXxshyj(), "", 1));
		String sqsj = Base.chgNull(xfhjModel.getSqsj(), "", 1);

		if (sqsj != null && sqsj.length() == 10){
			String year = sqsj.substring(0, 4);
			String mon = sqsj.substring(5, 7);
			String day = sqsj.substring(8);
			
			sqsj = year + "年" + mon + "月" + day + "日";
		}
		stuList.put("sqsj", sqsj);
		
		return stuList;
	}
	
	/**
	 * 删除学费缓缴信息 delXfhjxx ---- 删除学费缓缴信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"' and xysh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费缓缴审核结果 modXfhjxx ---- 批量修改学费缓缴审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_xfhj set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_zjjd_xfhj set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_zjjd_xfhj set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 学费缓缴查询表头 xfhjtit ---- 学费缓缴表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"zymc", "bjmc", "hjje", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "主键", "年度", "学号", "姓名",
				"专业名称", "班级名称", "缓缴金额", "申请时间", "班主任审核", "系审核", Base.YXPZXY_KEY+"审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 学费缓缴查询结果 xfhjres ---- 学费缓缴结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0){
					sql = "select (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where fdysh in ('通过')";
				} else {
					sql = "select (case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where xysh in ('通过')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"zymc", "bjmc", "hjje", "sqsj", "fdysh", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 保存学费缓缴审核信息，成功返回TRUE，反之返回FALSE saveXfhjShxx ---- 保存学费缓缴审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel saveXfhjModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xh = Base.chgNull(saveXfhjModel.getXh(), "", 1);
		String nd = Base.chgNull(saveXfhjModel.getNd(), "", 1);
		String fdysh = Base.chgNull(saveXfhjModel.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(saveXfhjModel.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(saveXfhjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(saveXfhjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(saveXfhjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(saveXfhjModel.getXxshyj(), "", 1);
		String sHave = isXfhjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_xfhj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_xfhj",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_xfhj",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 删除放款信息
	 * delFkxx ---- 删除放款信息
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delFkxx(String cbVal, HttpServletRequest request) throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete ZJJD_ZXDK_FKXX where xh||hth='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 放款信息查询表头
	 * fkxxtit ---- 放款信息表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFkxxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xh", "hth", "xm", "nj",
				"xymc", "zymc", "bjmc", "htje", "fkze" };
		String[] cnList = new String[] { "主键", "学号", "合同号", "姓名",
				"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "合同金额", "放款总额" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 放款信息查询结果
	 * getFkxxRes ---- 放款信息结果 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFkxxRes(QueryModel queryModel,HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||hth pk,xh,hth,xm,nj,xymc,zymc,bjmc,htje,fkze from view_zjjd_zxdk_fkxx where 1=1 ";
		String[] colList = new String[] { "pk", "xh", "hth", "xm", "nj",
				"xymc", "zymc", "bjmc", "htje", "fkze" };
		StringBuffer whereSql = getWhereSql(queryModel,request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 获取放款信息
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,hth,htje,htqx,dkyh,dkll,fkje1,fkrq1,fknf1,fkje2,fkrq2,fknf2,fkje3,fkrq3,fknf3,fkje4,fkrq4,fknf4,fkze,bz from view_zjjd_zxdk_fkxx where xh||hth = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "hth", "htje",
				"htqx", "dkyh", "dkll", "fkje1", "fkrq1", "fknf1", "fkje2",
				"fkrq2", "fknf2", "fkje3", "fkrq3", "fknf3", "fkje4", "fkrq4",
				"fknf4", "fkze", "bz" };
		String[] sLen = dao.getOneRs(sql, new String[]{pkVal}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString() : "");
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * 保存放款信息，成功返回TRUE，反之返回FALSE
	 * saveFkxx ---- 保存放款信息
	 * @param fkxxModel (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFkxx(FkxxModel fkxxModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(fkxxModel.getXh(), "", 1);
		String hth = Base.chgNull(fkxxModel.getHth(), "", 1);
		String htje = Base.chgNull(fkxxModel.getHtje(), "", 1);
		String htqx = Base.chgNull(fkxxModel.getHtqx(), "", 1);
		String dkyh = Base.chgNull(fkxxModel.getDkyh(), "", 1);
		String dkll = Base.chgNull(fkxxModel.getDkll(), "", 1);
		String fkje1 = Base.chgNull(fkxxModel.getFkje1(), "", 1);
		String fkrq1 = Base.chgNull(fkxxModel.getFkrq1(), "", 1);
		String fknf1 = Base.chgNull(fkxxModel.getFknf1(), "", 1);
		String fkje2 = Base.chgNull(fkxxModel.getFkje2(), "", 1);
		String fkrq2 = Base.chgNull(fkxxModel.getFkrq2(), "", 1);
		String fknf2 = Base.chgNull(fkxxModel.getFknf2(), "", 1);
		String fkje3 = Base.chgNull(fkxxModel.getFkje3(), "", 1);
		String fkrq3 = Base.chgNull(fkxxModel.getFkrq3(), "", 1);
		String fknf3 = Base.chgNull(fkxxModel.getFknf3(), "", 1);
		String fkje4 = Base.chgNull(fkxxModel.getFkje4(), "", 1);
		String fkrq4 = Base.chgNull(fkxxModel.getFkrq4(), "", 1);
		String fknf4 = Base.chgNull(fkxxModel.getFknf4(), "", 1);
		String fkze = Base.chgNull(fkxxModel.getFkze(), "", 1);
		String bz = Base.chgNull(fkxxModel.getBz(), "", 1);
		boolean bHave = isFkxxcf(xh, hth);
		if (bHave) {
			bFlag = StandardOperation.update("zjjd_zxdk_fkxx", new String[] {
					"htje", "htqx", "dkyh", "dkll", "fkje1", "fkrq1", "fknf1",
					"fkje2", "fkrq2", "fknf2", "fkje3", "fkrq3", "fknf3",
					"fkje4", "fkrq4", "fknf4", "fkze", "bz" },
					new String[] { htje, htqx, dkyh, dkll, fkje1, fkrq1, fknf1,
							fkje2, fkrq2, fknf2, fkje3, fkrq3, fknf3, fkje4,
							fkrq4, fknf4, fkze, bz }, "xh||hth", xh + hth,
					request);
		} else {
			bFlag = StandardOperation.insert("zjjd_zxdk_fkxx",
					new String[] { "xh", "hth", "htje", "htqx", "dkyh", "dkll",
							"fkje1", "fkrq1", "fknf1", "fkje2", "fkrq2",
							"fknf2", "fkje3", "fkrq3", "fknf3", "fkje4",
							"fkrq4", "fknf4", "fkze", "bz" }, new String[] {
							xh, hth, htje, htqx, dkyh, dkll, fkje1, fkrq1,
							fknf1, fkje2, fkrq2, fknf2, fkje3, fkrq3, fknf3,
							fkje4, fkrq4, fknf4, fkze, bz }, request);
		}
		return bFlag;
	}
	
	/**
	 * 判断放款信息数据是否重复，重复返回TRUE，反之返回FALSE
	 * isFkxxcf ---- 数据是否重复
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isFkxxcf(String xh, String hth) throws Exception {
		boolean bFlag = false;
		String sql = "select count(*) num from zjjd_zxdk_fkxx where xh = ? and hth = ?";
		String num = dao.getOneRs(sql, new String[]{xh, hth}, "num");
		if (!num.equalsIgnoreCase("0")) {
			bFlag = true;
		}//end if
		return bFlag;
	}
}
