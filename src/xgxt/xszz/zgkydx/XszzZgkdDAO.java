package xgxt.xszz.zgkydx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学学生资助DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-13</p>
 */
public class XszzZgkdDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();//查询条件值

	/**
	 * 公用方法：获取查询条件
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
		String knpd = queryModel.getKnpd();
		String xbshjg = queryModel.getXbshjg();
		String xxshjg = queryModel.getXxshjg();
		String tsrqdm = queryModel.getTsrqdm();
		String zzxmdm = queryModel.getZzxmdm();
		
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
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
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(knpd)) {
			whereSql.append(" and knpd = ?");
			values.add(knpd);
		}//end if
		if (!StringUtils.isNull(xxshjg)) {
			whereSql.append(" and xxshjg = ?");
			values.add(xxshjg);
		}//end if
		if (!StringUtils.isNull(xbshjg)) {
			whereSql.append(" and xbshjg = ?");
			values.add(xbshjg);
		}//end if
		if (!StringUtils.isNull(tsrqdm)) {
			whereSql.append(" and tsrqdm = ?");
			values.add(tsrqdm);
		}//end if
		if (!StringUtils.isNull(zzxmdm)) {
			whereSql.append(" and zzxmdm = ?");
			values.add(zzxmdm);
		}//end if
		return whereSql;
	}

	/**
	 * 导出数据
	 * getExpDate ---- 款导出数据
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
	 * 导出数据表头
	 * getExpTit ---- 导出数据表头
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
			}//end for
		}//end if
		return stuList;
	}

	/**
	 * 得到困难生申请权限
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from COMMON_NEW_GJZXDK_SJB a,view_xsjbxx b where a.xmmc='困难生' and b.xh=? and a.xydm=b.xydm";
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

		return sfksq;
	}
	
	/**
	 * 获取困难生详细
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,ss,dh,jtszd,jtlxdhjyzbm,szjclxdh,fqdwlxdh,mqdwlxdh,jtcy1_xm,jtcy1_xb,jtcy1_gx,jtcy1_nl,jtcy1_gzdw,jtcy1_hklx,jtcy1_cyzt,jtcy2_xm,jtcy2_xb,jtcy2_gx,jtcy2_nl,jtcy2_gzdw,jtcy2_hklx,jtcy2_cyzt,jtcy3_xm,jtcy3_xb,jtcy3_gx,jtcy3_nl,jtcy3_gzdw,jtcy3_hklx,jtcy3_cyzt,jtcy4_xm,jtcy4_xb,jtcy4_gx,jtcy4_nl,jtcy4_gzdw,jtcy4_hklx,jtcy4_cyzt,jtcy5_xm,jtcy5_xb,jtcy5_gx,jtcy5_nl,jtcy5_gzdw,jtcy5_hklx,jtcy5_cyzt,jtcy6_xm,jtcy6_xb,jtcy6_gx,jtcy6_nl,jtcy6_gzdw,jtcy6_hklx,jtcy6_cyzt,jtcy7_xm,jtcy7_xb,jtcy7_gx,jtcy7_nl,jtcy7_gzdw,jtcy7_hklx,jtcy7_cyzt,sqly_lzgjjpkxdncdq,sqly_syxszdshbzdczjt,sqly_gehjjknddqjt,sqly_fmyfhsfxg,sqly_jtcywqzndl,sqly_jtcyycjhjbrssndl,sqly_jtcyyzdjbxzfdefy,sqly_jtcyzylghyscyzjsfywjy,sqly_jtcyzszrzh,sqly_qtqk,zmcl1_mc,zmcl1_jg,zmcl1_dh,zmcl2_mc,zmcl2_jg,zmcl2_dh,zmcl3_mc,zmcl3_jg,zmcl3_dh,zmcl4_mc,zmcl4_jg,zmcl4_dh,sqsj,pyrs,adrs,bdrs,cdrs,btyrs,csbyjdrs,byjdjtqk,dcfyqk,bjpyjg,xbshjg,xbshsj,xxshjg,xxshsj from view_zgkydx_knsxx where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "ss",
				"dh", "jtszd", "jtlxdhjyzbm", "szjclxdh", "fqdwlxdh",
				"mqdwlxdh", "jtcy1_xm", "jtcy1_xb", "jtcy1_gx", "jtcy1_nl",
				"jtcy1_gzdw", "jtcy1_hklx", "jtcy1_cyzt", "jtcy2_xm",
				"jtcy2_xb", "jtcy2_gx", "jtcy2_nl", "jtcy2_gzdw", "jtcy2_hklx",
				"jtcy2_cyzt", "jtcy3_xm", "jtcy3_xb", "jtcy3_gx", "jtcy3_nl",
				"jtcy3_gzdw", "jtcy3_hklx", "jtcy3_cyzt", "jtcy4_xm",
				"jtcy4_xb", "jtcy4_gx", "jtcy4_nl", "jtcy4_gzdw", "jtcy4_hklx",
				"jtcy4_cyzt", "jtcy5_xm", "jtcy5_xb", "jtcy5_gx", "jtcy5_nl",
				"jtcy5_gzdw", "jtcy5_hklx", "jtcy5_cyzt", "jtcy6_xm",
				"jtcy6_xb", "jtcy6_gx", "jtcy6_nl", "jtcy6_gzdw", "jtcy6_hklx",
				"jtcy6_cyzt", "jtcy7_xm", "jtcy7_xb", "jtcy7_gx", "jtcy7_nl",
				"jtcy7_gzdw", "jtcy7_hklx", "jtcy7_cyzt", "sqly_lzgjjpkxdncdq",
				"sqly_syxszdshbzdczjt", "sqly_gehjjknddqjt", "sqly_fmyfhsfxg",
				"sqly_jtcywqzndl", "sqly_jtcyycjhjbrssndl",
				"sqly_jtcyyzdjbxzfdefy", "sqly_jtcyzylghyscyzjsfywjy",
				"sqly_jtcyzszrzh", "sqly_qtqk", "zmcl1_mc", "zmcl1_jg",
				"zmcl1_dh", "zmcl2_mc", "zmcl2_jg", "zmcl2_dh", "zmcl3_mc",
				"zmcl3_jg", "zmcl3_dh", "zmcl4_mc", "zmcl4_jg", "zmcl4_dh",
				"sqsj", "pyrs", "adrs", "bdrs", "cdrs", "btyrs", "csbyjdrs",
				"byjdjtqk", "dcfyqk", "bjpyjg", "xbshjg", "xbshsj", "xxshjg",
				"xxshsj" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}//end for
		}//end if
		return stuList;
	}

	/**
	 * 保存困难生申请信息，成功返回TRUE，反之返回FALSE
	 * saveKnsSqxx ---- 保存困难生申请信息
	 * @param saveModel (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel knsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(knsModel.getNd(), "", 1);
		String xh = Base.chgNull(knsModel.getXh(), "", 1);
		String ss = Base.chgNull(knsModel.getSs(), "", 1);
		String dh = Base.chgNull(knsModel.getDh(), "", 1);
		String jtszd = Base.chgNull(knsModel.getJtszd(), "", 1);
		String jtlxdhjyzbm = Base.chgNull(knsModel.getJtlxdhjyzbm(), "", 1);
		String szjclxdh = Base.chgNull(knsModel.getSzjclxdh(), "", 1);
		String fqdwlxdh = Base.chgNull(knsModel.getFqdwlxdh(), "", 1);
		String mqdwlxdh = Base.chgNull(knsModel.getMqdwlxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(knsModel.getJtcy1_xm(), "", 1);
		String jtcy1_xb = Base.chgNull(knsModel.getJtcy1_xb(), "", 1);
		String jtcy1_gx = Base.chgNull(knsModel.getJtcy1_gx(), "", 1);
		String jtcy1_nl = Base.chgNull(knsModel.getJtcy1_nl(), "", 1);
		String jtcy1_gzdw = Base.chgNull(knsModel.getJtcy1_gzdw(), "", 1);
		String jtcy1_hklx = Base.chgNull(knsModel.getJtcy1_hklx(), "", 1);
		String jtcy1_cyzt = Base.chgNull(knsModel.getJtcy1_cyzt(), "", 1);
		String jtcy2_xm = Base.chgNull(knsModel.getJtcy2_xm(), "", 1);
		String jtcy2_xb = Base.chgNull(knsModel.getJtcy2_xb(), "", 1);
		String jtcy2_gx = Base.chgNull(knsModel.getJtcy2_gx(), "", 1);
		String jtcy2_nl = Base.chgNull(knsModel.getJtcy2_nl(), "", 1);
		String jtcy2_gzdw = Base.chgNull(knsModel.getJtcy2_gzdw(), "", 1);
		String jtcy2_hklx = Base.chgNull(knsModel.getJtcy2_hklx(), "", 1);
		String jtcy2_cyzt = Base.chgNull(knsModel.getJtcy2_cyzt(), "", 1);
		String jtcy3_xm = Base.chgNull(knsModel.getJtcy3_xm(), "", 1);
		String jtcy3_xb = Base.chgNull(knsModel.getJtcy3_xb(), "", 1);
		String jtcy3_gx = Base.chgNull(knsModel.getJtcy3_gx(), "", 1);
		String jtcy3_nl = Base.chgNull(knsModel.getJtcy3_nl(), "", 1);
		String jtcy3_gzdw = Base.chgNull(knsModel.getJtcy3_gzdw(), "", 1);
		String jtcy3_hklx = Base.chgNull(knsModel.getJtcy3_hklx(), "", 1);
		String jtcy3_cyzt = Base.chgNull(knsModel.getJtcy3_cyzt(), "", 1);
		String jtcy4_xm = Base.chgNull(knsModel.getJtcy4_xm(), "", 1);
		String jtcy4_xb = Base.chgNull(knsModel.getJtcy4_xb(), "", 1);
		String jtcy4_gx = Base.chgNull(knsModel.getJtcy4_gx(), "", 1);
		String jtcy4_nl = Base.chgNull(knsModel.getJtcy4_nl(), "", 1);
		String jtcy4_gzdw = Base.chgNull(knsModel.getJtcy4_gzdw(), "", 1);
		String jtcy4_hklx = Base.chgNull(knsModel.getJtcy4_hklx(), "", 1);
		String jtcy4_cyzt = Base.chgNull(knsModel.getJtcy4_cyzt(), "", 1);
		String jtcy5_xm = Base.chgNull(knsModel.getJtcy5_xm(), "", 1);
		String jtcy5_xb = Base.chgNull(knsModel.getJtcy5_xb(), "", 1);
		String jtcy5_gx = Base.chgNull(knsModel.getJtcy5_gx(), "", 1);
		String jtcy5_nl = Base.chgNull(knsModel.getJtcy5_nl(), "", 1);
		String jtcy5_gzdw = Base.chgNull(knsModel.getJtcy5_gzdw(), "", 1);
		String jtcy5_hklx = Base.chgNull(knsModel.getJtcy5_hklx(), "", 1);
		String jtcy5_cyzt = Base.chgNull(knsModel.getJtcy5_cyzt(), "", 1);
		String jtcy6_xm = Base.chgNull(knsModel.getJtcy6_xm(), "", 1);
		String jtcy6_xb = Base.chgNull(knsModel.getJtcy6_xb(), "", 1);
		String jtcy6_gx = Base.chgNull(knsModel.getJtcy6_gx(), "", 1);
		String jtcy6_nl = Base.chgNull(knsModel.getJtcy6_nl(), "", 1);
		String jtcy6_gzdw = Base.chgNull(knsModel.getJtcy6_gzdw(), "", 1);
		String jtcy6_hklx = Base.chgNull(knsModel.getJtcy6_hklx(), "", 1);
		String jtcy6_cyzt = Base.chgNull(knsModel.getJtcy6_cyzt(), "", 1);
		String jtcy7_xm = Base.chgNull(knsModel.getJtcy7_xm(), "", 1);
		String jtcy7_xb = Base.chgNull(knsModel.getJtcy7_xb(), "", 1);
		String jtcy7_gx = Base.chgNull(knsModel.getJtcy7_gx(), "", 1);
		String jtcy7_nl = Base.chgNull(knsModel.getJtcy7_nl(), "", 1);
		String jtcy7_gzdw = Base.chgNull(knsModel.getJtcy7_gzdw(), "", 1);
		String jtcy7_hklx = Base.chgNull(knsModel.getJtcy7_hklx(), "", 1);
		String jtcy7_cyzt = Base.chgNull(knsModel.getJtcy7_cyzt(), "", 1);
		String sqly_lzgjjpkxdncdq = Base.chgNull(knsModel
				.getSqly_lzgjjpkxdncdq(), "", 1);
		String sqly_syxszdshbzdczjt = Base.chgNull(knsModel
				.getSqly_syxszdshbzdczjt(), "", 1);
		String sqly_gehjjknddqjt = Base.chgNull(
				knsModel.getSqly_gehjjknddqjt(), "", 1);
		String sqly_fmyfhsfxg = Base.chgNull(knsModel.getSqly_fmyfhsfxg(), "",
				1);
		String sqly_jtcywqzndl = Base.chgNull(knsModel.getSqly_jtcywqzndl(),
				"", 1);
		String sqly_jtcyycjhjbrssndl = Base.chgNull(knsModel
				.getSqly_jtcyycjhjbrssndl(), "", 1);
		String sqly_jtcyyzdjbxzfdefy = Base.chgNull(knsModel
				.getSqly_jtcyyzdjbxzfdefy(), "", 1);
		String sqly_jtcyzylghyscyzjsfywjy = Base.chgNull(knsModel
				.getSqly_jtcyzylghyscyzjsfywjy(), "", 1);
		String sqly_jtcyzszrzh = Base.chgNull(knsModel.getSqly_jtcyzszrzh(),
				"", 1);
		String sqly_qtqk = Base.chgNull(knsModel.getSqly_qtqk(), "", 1);
		String zmcl1_mc = Base.chgNull(knsModel.getZmcl1_mc(), "", 1);
		String zmcl1_jg = Base.chgNull(knsModel.getZmcl1_jg(), "", 1);
		String zmcl1_dh = Base.chgNull(knsModel.getZmcl1_dh(), "", 1);
		String zmcl2_mc = Base.chgNull(knsModel.getZmcl2_mc(), "", 1);
		String zmcl2_jg = Base.chgNull(knsModel.getZmcl2_jg(), "", 1);
		String zmcl2_dh = Base.chgNull(knsModel.getZmcl2_dh(), "", 1);
		String zmcl3_mc = Base.chgNull(knsModel.getZmcl3_mc(), "", 1);
		String zmcl3_jg = Base.chgNull(knsModel.getZmcl3_jg(), "", 1);
		String zmcl3_dh = Base.chgNull(knsModel.getZmcl3_dh(), "", 1);
		String zmcl4_mc = Base.chgNull(knsModel.getZmcl4_mc(), "", 1);
		String zmcl4_jg = Base.chgNull(knsModel.getZmcl4_jg(), "", 1);
		String zmcl4_dh = Base.chgNull(knsModel.getZmcl4_dh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zgkydx_knsxx", new String[] {
					"nd", "xh", "ss", "dh", "jtszd", "jtlxdhjyzbm", "szjclxdh",
					"fqdwlxdh", "mqdwlxdh", "jtcy1_xm", "jtcy1_xb", "jtcy1_gx",
					"jtcy1_nl", "jtcy1_gzdw", "jtcy1_hklx", "jtcy1_cyzt",
					"jtcy2_xm", "jtcy2_xb", "jtcy2_gx", "jtcy2_nl",
					"jtcy2_gzdw", "jtcy2_hklx", "jtcy2_cyzt", "jtcy3_xm",
					"jtcy3_xb", "jtcy3_gx", "jtcy3_nl", "jtcy3_gzdw",
					"jtcy3_hklx", "jtcy3_cyzt", "jtcy4_xm", "jtcy4_xb",
					"jtcy4_gx", "jtcy4_nl", "jtcy4_gzdw", "jtcy4_hklx",
					"jtcy4_cyzt", "jtcy5_xm", "jtcy5_xb", "jtcy5_gx",
					"jtcy5_nl", "jtcy5_gzdw", "jtcy5_hklx", "jtcy5_cyzt",
					"jtcy6_xm", "jtcy6_xb", "jtcy6_gx", "jtcy6_nl",
					"jtcy6_gzdw", "jtcy6_hklx", "jtcy6_cyzt", "jtcy7_xm",
					"jtcy7_xb", "jtcy7_gx", "jtcy7_nl", "jtcy7_gzdw",
					"jtcy7_hklx", "jtcy7_cyzt", "sqly_lzgjjpkxdncdq",
					"sqly_syxszdshbzdczjt", "sqly_gehjjknddqjt",
					"sqly_fmyfhsfxg", "sqly_jtcywqzndl",
					"sqly_jtcyycjhjbrssndl", "sqly_jtcyyzdjbxzfdefy",
					"sqly_jtcyzylghyscyzjsfywjy", "sqly_jtcyzszrzh",
					"sqly_qtqk", "zmcl1_mc", "zmcl1_jg", "zmcl1_dh",
					"zmcl2_mc", "zmcl2_jg", "zmcl2_dh", "zmcl3_mc", "zmcl3_jg",
					"zmcl3_dh", "zmcl4_mc", "zmcl4_jg", "zmcl4_dh" },
					new String[] { nd, xh, ss, dh, jtszd, jtlxdhjyzbm,
							szjclxdh, fqdwlxdh, mqdwlxdh, jtcy1_xm, jtcy1_xb,
							jtcy1_gx, jtcy1_nl, jtcy1_gzdw, jtcy1_hklx,
							jtcy1_cyzt, jtcy2_xm, jtcy2_xb, jtcy2_gx, jtcy2_nl,
							jtcy2_gzdw, jtcy2_hklx, jtcy2_cyzt, jtcy3_xm,
							jtcy3_xb, jtcy3_gx, jtcy3_nl, jtcy3_gzdw,
							jtcy3_hklx, jtcy3_cyzt, jtcy4_xm, jtcy4_xb,
							jtcy4_gx, jtcy4_nl, jtcy4_gzdw, jtcy4_hklx,
							jtcy4_cyzt, jtcy5_xm, jtcy5_xb, jtcy5_gx, jtcy5_nl,
							jtcy5_gzdw, jtcy5_hklx, jtcy5_cyzt, jtcy6_xm,
							jtcy6_xb, jtcy6_gx, jtcy6_nl, jtcy6_gzdw,
							jtcy6_hklx, jtcy6_cyzt, jtcy7_xm, jtcy7_xb,
							jtcy7_gx, jtcy7_nl, jtcy7_gzdw, jtcy7_hklx,
							jtcy7_cyzt, sqly_lzgjjpkxdncdq,
							sqly_syxszdshbzdczjt, sqly_gehjjknddqjt,
							sqly_fmyfhsfxg, sqly_jtcywqzndl,
							sqly_jtcyycjhjbrssndl, sqly_jtcyyzdjbxzfdefy,
							sqly_jtcyzylghyscyzjsfywjy, sqly_jtcyzszrzh,
							sqly_qtqk, zmcl1_mc, zmcl1_jg, zmcl1_dh, zmcl2_mc,
							zmcl2_jg, zmcl2_dh, zmcl3_mc, zmcl3_jg, zmcl3_dh,
							zmcl4_mc, zmcl4_jg, zmcl4_dh }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zgkydx_knsxx", new String[] {
					"ss", "dh", "jtszd", "jtlxdhjyzbm", "szjclxdh", "fqdwlxdh",
					"mqdwlxdh", "jtcy1_xm", "jtcy1_xb", "jtcy1_gx", "jtcy1_nl",
					"jtcy1_gzdw", "jtcy1_hklx", "jtcy1_cyzt", "jtcy2_xm",
					"jtcy2_xb", "jtcy2_gx", "jtcy2_nl", "jtcy2_gzdw",
					"jtcy2_hklx", "jtcy2_cyzt", "jtcy3_xm", "jtcy3_xb",
					"jtcy3_gx", "jtcy3_nl", "jtcy3_gzdw", "jtcy3_hklx",
					"jtcy3_cyzt", "jtcy4_xm", "jtcy4_xb", "jtcy4_gx",
					"jtcy4_nl", "jtcy4_gzdw", "jtcy4_hklx", "jtcy4_cyzt",
					"jtcy5_xm", "jtcy5_xb", "jtcy5_gx", "jtcy5_nl",
					"jtcy5_gzdw", "jtcy5_hklx", "jtcy5_cyzt", "jtcy6_xm",
					"jtcy6_xb", "jtcy6_gx", "jtcy6_nl", "jtcy6_gzdw",
					"jtcy6_hklx", "jtcy6_cyzt", "jtcy7_xm", "jtcy7_xb",
					"jtcy7_gx", "jtcy7_nl", "jtcy7_gzdw", "jtcy7_hklx",
					"jtcy7_cyzt", "sqly_lzgjjpkxdncdq", "sqly_syxszdshbzdczjt",
					"sqly_gehjjknddqjt", "sqly_fmyfhsfxg", "sqly_jtcywqzndl",
					"sqly_jtcyycjhjbrssndl", "sqly_jtcyyzdjbxzfdefy",
					"sqly_jtcyzylghyscyzjsfywjy", "sqly_jtcyzszrzh",
					"sqly_qtqk", "zmcl1_mc", "zmcl1_jg", "zmcl1_dh",
					"zmcl2_mc", "zmcl2_jg", "zmcl2_dh", "zmcl3_mc", "zmcl3_jg",
					"zmcl3_dh", "zmcl4_mc", "zmcl4_jg", "zmcl4_dh", "sqsj",
					"pyrs", "adrs", "bdrs", "cdrs", "btyrs", "csbyjdrs",
					"byjdjtqk", "dcfyqk", "bjpyjg", "xbshjg", "xbshsj",
					"xxshjg", "xxshsj" }, new String[] { ss, dh, jtszd,
					jtlxdhjyzbm, szjclxdh, fqdwlxdh, mqdwlxdh, jtcy1_xm,
					jtcy1_xb, jtcy1_gx, jtcy1_nl, jtcy1_gzdw, jtcy1_hklx,
					jtcy1_cyzt, jtcy2_xm, jtcy2_xb, jtcy2_gx, jtcy2_nl,
					jtcy2_gzdw, jtcy2_hklx, jtcy2_cyzt, jtcy3_xm, jtcy3_xb,
					jtcy3_gx, jtcy3_nl, jtcy3_gzdw, jtcy3_hklx, jtcy3_cyzt,
					jtcy4_xm, jtcy4_xb, jtcy4_gx, jtcy4_nl, jtcy4_gzdw,
					jtcy4_hklx, jtcy4_cyzt, jtcy5_xm, jtcy5_xb, jtcy5_gx,
					jtcy5_nl, jtcy5_gzdw, jtcy5_hklx, jtcy5_cyzt, jtcy6_xm,
					jtcy6_xb, jtcy6_gx, jtcy6_nl, jtcy6_gzdw, jtcy6_hklx,
					jtcy6_cyzt, jtcy7_xm, jtcy7_xb, jtcy7_gx, jtcy7_nl,
					jtcy7_gzdw, jtcy7_hklx, jtcy7_cyzt, sqly_lzgjjpkxdncdq,
					sqly_syxszdshbzdczjt, sqly_gehjjknddqjt, sqly_fmyfhsfxg,
					sqly_jtcywqzndl, sqly_jtcyycjhjbrssndl,
					sqly_jtcyyzdjbxzfdefy, sqly_jtcyzylghyscyzjsfywjy,
					sqly_jtcyzszrzh, sqly_qtqk, zmcl1_mc, zmcl1_jg, zmcl1_dh,
					zmcl2_mc, zmcl2_jg, zmcl2_dh, zmcl3_mc, zmcl3_jg, zmcl3_dh,
					zmcl4_mc, zmcl4_jg, zmcl4_dh, now, "", "", "", "", "", "",
					"", "", "未审核", "未审核", "", "未审核", "" }, "nd||xh", nd + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * 判断困难生是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1
	 * isknsdatacf ---- 数据是否重复
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zgkydx_knsxx where xh = ? and nd = ? and xxshjg in ('A档','B档','C档')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zgkydx_knsxx where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * 获取困难生申请表详细信息
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(knsModel.getNd(), "", 1));
		stuList.put("xm", Base.chgNull(knsModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(knsModel.getXb(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("xh", Base.chgNull(knsModel.getXh(), "", 1));
		stuList.put("ss", Base.chgNull(knsModel.getSs(), "", 1));
		stuList.put("dh", Base.chgNull(knsModel.getDh(), "", 1));
		stuList.put("jtszd", Base.chgNull(knsModel.getJtszd(), "", 1));
		stuList.put("jtlxdhjyzbm", Base.chgNull(knsModel.getJtlxdhjyzbm(), "",
				1));
		stuList.put("szjclxdh", Base.chgNull(knsModel.getSzjclxdh(), "", 1));
		stuList.put("fqdwlxdh", Base.chgNull(knsModel.getFqdwlxdh(), "", 1));
		stuList.put("mqdwlxdh", Base.chgNull(knsModel.getMqdwlxdh(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(knsModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_xb", Base.chgNull(knsModel.getJtcy1_xb(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(knsModel.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(knsModel.getJtcy1_nl(), "", 1));
		stuList
				.put("jtcy1_gzdw", Base
						.chgNull(knsModel.getJtcy1_gzdw(), "", 1));
		stuList
				.put("jtcy1_hklx", Base
						.chgNull(knsModel.getJtcy1_hklx(), "", 1));
		stuList
				.put("jtcy1_cyzt", Base
						.chgNull(knsModel.getJtcy1_cyzt(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(knsModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_xb", Base.chgNull(knsModel.getJtcy2_xb(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(knsModel.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(knsModel.getJtcy2_nl(), "", 1));
		stuList
				.put("jtcy2_gzdw", Base
						.chgNull(knsModel.getJtcy2_gzdw(), "", 1));
		stuList
				.put("jtcy2_hklx", Base
						.chgNull(knsModel.getJtcy2_hklx(), "", 1));
		stuList
				.put("jtcy2_cyzt", Base
						.chgNull(knsModel.getJtcy2_cyzt(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(knsModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_xb", Base.chgNull(knsModel.getJtcy3_xb(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(knsModel.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(knsModel.getJtcy3_nl(), "", 1));
		stuList
				.put("jtcy3_gzdw", Base
						.chgNull(knsModel.getJtcy3_gzdw(), "", 1));
		stuList
				.put("jtcy3_hklx", Base
						.chgNull(knsModel.getJtcy3_hklx(), "", 1));
		stuList
				.put("jtcy3_cyzt", Base
						.chgNull(knsModel.getJtcy3_cyzt(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(knsModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_xb", Base.chgNull(knsModel.getJtcy4_xb(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(knsModel.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(knsModel.getJtcy4_nl(), "", 1));
		stuList
				.put("jtcy4_gzdw", Base
						.chgNull(knsModel.getJtcy4_gzdw(), "", 1));
		stuList
				.put("jtcy4_hklx", Base
						.chgNull(knsModel.getJtcy4_hklx(), "", 1));
		stuList
				.put("jtcy4_cyzt", Base
						.chgNull(knsModel.getJtcy4_cyzt(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(knsModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_xb", Base.chgNull(knsModel.getJtcy5_xb(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(knsModel.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(knsModel.getJtcy5_nl(), "", 1));
		stuList
				.put("jtcy5_gzdw", Base
						.chgNull(knsModel.getJtcy5_gzdw(), "", 1));
		stuList
				.put("jtcy5_hklx", Base
						.chgNull(knsModel.getJtcy5_hklx(), "", 1));
		stuList
				.put("jtcy5_cyzt", Base
						.chgNull(knsModel.getJtcy5_cyzt(), "", 1));
		stuList.put("jtcy6_xm", Base.chgNull(knsModel.getJtcy6_xm(), "", 1));
		stuList.put("jtcy6_xb", Base.chgNull(knsModel.getJtcy6_xb(), "", 1));
		stuList.put("jtcy6_gx", Base.chgNull(knsModel.getJtcy6_gx(), "", 1));
		stuList.put("jtcy6_nl", Base.chgNull(knsModel.getJtcy6_nl(), "", 1));
		stuList
				.put("jtcy6_gzdw", Base
						.chgNull(knsModel.getJtcy6_gzdw(), "", 1));
		stuList
				.put("jtcy6_hklx", Base
						.chgNull(knsModel.getJtcy6_hklx(), "", 1));
		stuList
				.put("jtcy6_cyzt", Base
						.chgNull(knsModel.getJtcy6_cyzt(), "", 1));
		stuList.put("jtcy7_xm", Base.chgNull(knsModel.getJtcy7_xm(), "", 1));
		stuList.put("jtcy7_xb", Base.chgNull(knsModel.getJtcy7_xb(), "", 1));
		stuList.put("jtcy7_gx", Base.chgNull(knsModel.getJtcy7_gx(), "", 1));
		stuList.put("jtcy7_nl", Base.chgNull(knsModel.getJtcy7_nl(), "", 1));
		stuList
				.put("jtcy7_gzdw", Base
						.chgNull(knsModel.getJtcy7_gzdw(), "", 1));
		stuList
				.put("jtcy7_hklx", Base
						.chgNull(knsModel.getJtcy7_hklx(), "", 1));
		stuList
				.put("jtcy7_cyzt", Base
						.chgNull(knsModel.getJtcy7_cyzt(), "", 1));
		stuList.put("sqly_lzgjjpkxdncdq", Base.chgNull(knsModel
				.getSqly_lzgjjpkxdncdq(), "", 1));
		stuList.put("sqly_syxszdshbzdczjt", Base.chgNull(knsModel
				.getSqly_syxszdshbzdczjt(), "", 1));
		stuList.put("sqly_gehjjknddqjt", Base.chgNull(knsModel
				.getSqly_gehjjknddqjt(), "", 1));
		stuList.put("sqly_fmyfhsfxg", Base.chgNull(
				knsModel.getSqly_fmyfhsfxg(), "", 1));
		stuList.put("sqly_jtcywqzndl", Base.chgNull(knsModel
				.getSqly_jtcywqzndl(), "", 1));
		stuList.put("sqly_jtcyycjhjbrssndl", Base.chgNull(knsModel
				.getSqly_jtcyycjhjbrssndl(), "", 1));
		stuList.put("sqly_jtcyyzdjbxzfdefy", Base.chgNull(knsModel
				.getSqly_jtcyyzdjbxzfdefy(), "", 1));
		stuList.put("sqly_jtcyzylghyscyzjsfywjy", Base.chgNull(knsModel
				.getSqly_jtcyzylghyscyzjsfywjy(), "", 1));
		stuList.put("sqly_jtcyzszrzh", Base.chgNull(knsModel
				.getSqly_jtcyzszrzh(), "", 1));
		stuList.put("sqly_qtqk", Base.chgNull(knsModel.getSqly_qtqk(), "", 1));
		stuList.put("zmcl1_mc", Base.chgNull(knsModel.getZmcl1_mc(), "", 1));
		stuList.put("zmcl1_jg", Base.chgNull(knsModel.getZmcl1_jg(), "", 1));
		stuList.put("zmcl1_dh", Base.chgNull(knsModel.getZmcl1_dh(), "", 1));
		stuList.put("zmcl2_mc", Base.chgNull(knsModel.getZmcl2_mc(), "", 1));
		stuList.put("zmcl2_jg", Base.chgNull(knsModel.getZmcl2_jg(), "", 1));
		stuList.put("zmcl2_dh", Base.chgNull(knsModel.getZmcl2_dh(), "", 1));
		stuList.put("zmcl3_mc", Base.chgNull(knsModel.getZmcl3_mc(), "", 1));
		stuList.put("zmcl3_jg", Base.chgNull(knsModel.getZmcl3_jg(), "", 1));
		stuList.put("zmcl3_dh", Base.chgNull(knsModel.getZmcl3_dh(), "", 1));
		stuList.put("zmcl4_mc", Base.chgNull(knsModel.getZmcl4_mc(), "", 1));
		stuList.put("zmcl4_jg", Base.chgNull(knsModel.getZmcl4_jg(), "", 1));
		stuList.put("zmcl4_dh", Base.chgNull(knsModel.getZmcl4_dh(), "", 1));
		stuList.put("sqsj", Base.chgNull(knsModel.getSqsj(), "", 1));
		stuList.put("pyrs", Base.chgNull(knsModel.getPyrs(), "", 1));
		stuList.put("adrs", Base.chgNull(knsModel.getAdrs(), "", 1));
		stuList.put("bdrs", Base.chgNull(knsModel.getBdrs(), "", 1));
		stuList.put("cdrs", Base.chgNull(knsModel.getCdrs(), "", 1));
		stuList.put("btyrs", Base.chgNull(knsModel.getBtyrs(), "", 1));
		stuList.put("csbyjdrs", Base.chgNull(knsModel.getCsbyjdrs(), "", 1));
		stuList.put("byjdjtqk", Base.chgNull(knsModel.getByjdjtqk(), "", 1));
		stuList.put("dcfyqk", Base.chgNull(knsModel.getDcfyqk(), "", 1));
		stuList.put("bjpyjg", Base.chgNull(knsModel.getBjpyjg(), "", 1));
		stuList.put("xbshjg", Base.chgNull(knsModel.getXbshjg(), "", 1));
		stuList.put("xbshsj", Base.chgNull(knsModel.getXbshsj(), "", 1));
		stuList.put("xxshjg", Base.chgNull(knsModel.getXxshjg(), "", 1));
		stuList.put("xxshsj", Base.chgNull(knsModel.getXxshsj(), "", 1));

		return stuList;
	}

	/**
	 * 删除困难信息
	 * delKnsxx ---- 删除困难生信息
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
				sqlT[i] = "delete zgkydx_knsxx where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete zgkydx_knsxx where nd||xh='"+pkT[i]+"' and xxshjg not in ('A档','B档','C档')";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改困难生审核结果
	 * modKnsxx ---- 批量修改困难生审核结果
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update zgkydx_knsxx set xxshjg='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update zgkydx_knsxx set bjpyjg='" + shjg
						+ "',xbshjg = '" + shjg + "' where nd||xh='" + pkT[i]
						+ "' and xxshjg='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 困难生查询表头
	 * knstit ---- 困难生表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "r", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "bjpyjg", "xbshjg",
				"xxshjg" };
		String[] cnList = new String[] { "bgcolor", "主键", "行号", "年度", "学号", "姓名",
				"性别", "身份证号", Base.YXPZXY_KEY+"名称", "班级名称", "申请时间", "班级评议结果", "系部审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 困难生查询结果
	 * knsres ---- 困难生结果 
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,
			HttpServletRequest request,ActionForm form) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select * from (select (case when nvl(xxshjg,'未审核') in ('A档','B档','C档') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,rownum r,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,bjpyjg,xbshjg,xxshjg from view_zgkydx_knsxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select * from (select (case when nvl(xbshjg,'未审核') in ('A档','B档','C档') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,rownum r,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,bjpyjg,xbshjg,xxshjg from view_zgkydx_knsxx where 1=1";
			} else {
				sql = "select * from (select (case when nvl(xxshjg,'未审核') in ('A档','B档','C档') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,rownum r,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,bjpyjg,xbshjg,xxshjg from view_zgkydx_knsxx where xbshjg in ('A档','B档','C档')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "r", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "bjpyjg", "xbshjg",
				"xxshjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql + ") where r<="
				+ xszzZgkydxActionForm.getPages().getStart()
				+ xszzZgkydxActionForm.getPages().getPageSize() + " and r>"
				+ xszzZgkydxActionForm.getPages().getStart(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生查询结果记录数
	 * knsresNum ---- 困难生结果记录数
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select count(*) num from view_zgkydx_knsxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select count(*) num from view_zgkydx_knsxx where 1=1";
			} else {
				sql = "select count(*) num from view_zgkydx_knsxx where xbshjg in ('A档','B档','C档')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String num = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return num;
	}

	/**
	 * 保存困难生审核信息，成功返回TRUE，反之返回FALSE
	 * saveKnsShxx ---- 保存困难生审核信息
	 * @param saveModel (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel knsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");
		String xh = Base.chgNull(knsModel.getXh(), "", 1);
		String nd = Base.chgNull(knsModel.getNd(), "", 1);
		String pyrs = Base.chgNull(knsModel.getPyrs(), "", 1);
		String adrs = Base.chgNull(knsModel.getAdrs(), "", 1);
		String bdrs = Base.chgNull(knsModel.getBdrs(), "", 1);
		String cdrs = Base.chgNull(knsModel.getCdrs(), "", 1);
		String btyrs = Base.chgNull(knsModel.getBtyrs(), "", 1);
		String csbyjdrs = Base.chgNull(knsModel.getCsbyjdrs(), "", 1);
		String byjdjtqk = Base.chgNull(knsModel.getByjdjtqk(), "", 1);
		String dcfyqk = Base.chgNull(knsModel.getDcfyqk(), "", 1);
		String bjpyjg = Base.chgNull(knsModel.getBjpyjg(), "", 1);
		String xbshjg = Base.chgNull(knsModel.getXbshjg(), "", 1);
		String xxshjg = Base.chgNull(knsModel.getXxshjg(), "", 1);
		String sHave = isKnsDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zgkydx_knsxx", new String[] {
					"xxshjg", "xxshsj" }, new String[] { xxshjg, now },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zgkydx_knsxx", new String[] {
						"pyrs", "adrs", "bdrs", "cdrs", "btyrs", "csbyjdrs",
						"byjdjtqk", "dcfyqk", "bjpyjg", "xbshjg", "xbshsj" },
						new String[] { pyrs, adrs, bdrs, cdrs, btyrs, csbyjdrs,
								byjdjtqk, dcfyqk, bjpyjg, xbshjg, now },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}
		
	/**
	 * 特殊人群设置页面表头
	 * tsrqszTit ---- 特殊人群设置页面表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqszTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm",
				"xb", "xz", "nj", "xymc", "zymc", "bjmc", "xxshjg" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名",
				"性别", "学制", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难程度" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 特殊人群设置 - 查询困难生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqKnsRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select nd||xh pk,nd,xh,xm,xb,xz,nj,xymc,zymc,bjmc,xxshjg from view_zgkydx_knsxx where xxshjg in ('A档','B档','C档')";
		String[] colList = new String[] { "pk", "nd", "xh", "xm",
				"xb", "xz", "nj", "xymc", "zymc", "bjmc", "xxshjg" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 得到特殊人群列表
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqList() throws Exception {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		String sql = "select dm,mc from xszz_zgkd_tsrqdmb";

		resList = dao
				.getList(sql, new String[] {}, new String[] { "dm", "mc" });
		return resList;
	}
	
	/**
	 * 保存特殊人群设置信息
	 * tsrqSave ---- 保存特殊人群设置信息
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void tsrqSave(String cbVal, String tsrqdm, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String nd = "";
		String xh = "";
		String sHave = "-1";
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if(pkT[i].length() > 4 && !"!!splitOne!!".equalsIgnoreCase(pkT[i]) && !"nd||xh".equalsIgnoreCase(pkT[i])){
				nd = pkT[i].substring(0, 4);
				xh = pkT[i].substring(4);
				sHave = this.isTsrqDataCf(xh, nd, tsrqdm);
				if(sHave.equalsIgnoreCase("-1")){
					sqlT[i] = "insert into XSZZ_ZGKD_TSRQ(nd,xh,tsrqdm) values('"+nd+"','"+xh+"','"+tsrqdm+"')";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 判断特殊人群设置是否重复，重复的返回1，没有重复的返回-1
	 * isTsrqDataCf ---- 数据是否重复
	 * @param xh
	 * @param nd
	 * @param tsrqdm
	 * @return
	 * @throws Exception
	 */
	public String isTsrqDataCf(String xh, String nd, String tsrqdm) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from XSZZ_ZGKD_TSRQ where xh = ? and nd = ? and tsrqdm = ?";
		String num = dao.getOneRs(sql, new String[] { xh, nd, tsrqdm }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}

	/**
	 * 特殊人群查询页面表头
	 * tsrqcxTit ---- 特殊人群查询页面表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqcxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc", "knpd", "tsrqmc" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名",
				"性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "困难程度", "特殊人群名称" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 特殊人群记录
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select nd||xh||tsrqdm pk,nd,xh,xm,xb,nj,xymc,zymc,bjmc,knpd,tsrqmc from view_xszz_zgkd_tsrq where 1=1";
		String[] colList = new String[] { "pk", "nd", "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc", "knpd", "tsrqmc" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 删除特殊人群记录
	 * delTsrqxx ---- 删除特殊人群记录
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete XSZZ_ZGKD_TSRQ where nd||xh||tsrqdm='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}
	/**
	 * 查询学生还款信息视图
	 * getHkxsxx ---- 获得学生还款信息列表
	 * @param query (查询条件),request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getHkxsxx(StringBuffer query,ActionForm form,HttpServletRequest request) {
		
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String query1 = query.toString();
		String sql ="";

		CommanForm dataSearchForm = (CommanForm)form;
		sql = "select count(*) count from view_xszz_zgkd_xshkxxb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 还款信息
		sql = "select * from (select a.*,rownum r from (select distinct a.xh,a.xm,a.xb,a.hth,a.xymc,a.zymc,a.bjmc from view_xszz_zgkd_xshkxxb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "xh", "xm", "xb", "hth", "xymc",
				"zymc", "bjmc" };
		rs = dao.getArrayList2(sql, new String[] {}, colList);		
		return rs;
	}
	
	/**
	 * 特殊人群与资助项目页面表头
	 * tsrqxmTit ---- 特殊人群与资助项目页面表头 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqxmTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "zzxmmc", "tsrqmc" };
		String[] cnList = new String[] { "主键", "资助项目名称", "特殊人群名称" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * 查询学生还款信息视图
	 * getHkxsxx ---- 获得学生还款详细信息
	 * @param pkValue 主键值
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap<String, String> getHkxxmoreinfo(String pkValue){
		
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();

		String sql = "select * from view_xszz_zgkd_xshkxxb where xh='" + pkValue
				+ "'";
		String[] colList = dao
				.getColumnName("select * from view_xszz_zgkd_xshkxxb where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
		}
		return map;
	}
	/**
	 * 学生还款信息修改
	 * updateHkxxinfo ---- 学生还款信息修改
	 * @param columns（字段） values(值) pk(主键) pkValue(主键值)
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateHkxxinfo(String[] columns, String[] values,String pk,String pkValue, HttpServletRequest request) throws Exception{
		
		boolean judge = false;	
		judge = StandardOperation.update("xszz_zgkd_xshkxxb",columns,values,		
				pk, pkValue, request);
		return judge;
	}
	
	/**
	 * 学生还款信息删除
	 * updateHkxxinfo ---- 学生还款信息删除
	 * @param pk(主键) pkValue(主键值)
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean delHkxxinfo(String pk,String pkValue,HttpServletRequest request) throws Exception{
		boolean judge = false;
		judge= StandardOperation.delete("xszz_zgkd_xshkxxb", pk, pkValue, request); 
		return judge;
	}
	
	/**
	 * 学生还款信息全部清空
	 * delallHkxxinfo ---- 学生还款信息全部清空
	 * @param tableName(表名)
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean delallHkxxinfo(String tableName) throws Exception{
		boolean judge = false;
		judge= dao.runUpdateTab("truncate table "+tableName);; 
		return judge;
	}
	
	/**
	 * 学生还款信息全部清空
	 * delallHkxxinfo ---- 学生还款信息全部清空
	 * @param tableName(表名)
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean getRsNum(String tableName) throws Exception{
		boolean judge = false;
		judge= dao.runUpdateTab("truncate table "+tableName);; 
		return judge;
	}
	/**
	 * 特殊人群项目
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqxmRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select tsrqdm||zzxmdm pk,zzxmmc,tsrqmc from view_xszz_zgktdx_tsrqxmwh where 1=1";
		String[] colList = new String[] { "pk", "zzxmmc", "tsrqmc" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 得到资助项目列表
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzxmList() throws Exception {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		String sql = "select xmdm dm,xmmc mc from XSZZ_COMMON_NEW_ZZXMDMB";

		resList = dao
				.getList(sql, new String[] {}, new String[] { "dm", "mc" });
		return resList;
	}
	
	/**
	 * 删除特殊人群与资助项目记录
	 * delTsrqxmcxx ---- 删除特殊人群与资助项目记录
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxmcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_zgktdx_tsrqxmwh where tsrqdm||zzxmdm='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 保存特殊人群与资助项目信息
	 * tsrqxmSave ---- 保存特殊人群与资助项目信息
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public boolean tsrqxmSave(String zzxmdm, String tsrqdm,
			HttpServletRequest request) throws Exception {
		String sHave = "-1";
		boolean b = false;
		sHave = this.istsrqxmdatacf(zzxmdm, tsrqdm);
		if (sHave.equalsIgnoreCase("-1")) {
			b = StandardOperation.insert("xszz_zgktdx_tsrqxmwh", new String[] {
					"zzxmdm", "tsrqdm" }, new String[] { zzxmdm, tsrqdm },
					request);
		} else {
			request.setAttribute("have", "have");
		}
		return b;
	}

	/**
	 * 判断特殊人群与资助项目是否重复，重复返回1，没有重复返回-1 istsrqxmdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String istsrqxmdatacf(String zzxmdm, String tsrqdm) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zgktdx_tsrqxmwh where zzxmdm = ? and tsrqdm = ?";
		String num = dao.getOneRs(sql, new String[] { zzxmdm, tsrqdm }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}
}
