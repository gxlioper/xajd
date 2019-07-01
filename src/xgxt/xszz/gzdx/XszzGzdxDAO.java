package xgxt.xszz.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 广州大学学生资助DAO</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-24</p>
 */
public class XszzGzdxDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();// 查询条件值

	/**
	 * 公用方法：获取查询条件
	 * 
	 * @param queryModel
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
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xq = queryModel.getXq();
		String xh = queryModel.getXh();
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xm = queryModel.getXm();
		String xb = queryModel.getXb();
		String sfzh = queryModel.getSfzh();
		String btsj = queryModel.getBtsj();
		String htbh = queryModel.getHtbh();
		String sfqbhqdk = queryModel.getSfqbhqdk();

		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
		if (userType.equalsIgnoreCase("stu")
				|| userType.equalsIgnoreCase("student")) {
			xh = userName;
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
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(xb)) {
			whereSql.append(" and xb = ?");
			values.add(xb);
		}// end if
		if (!StringUtils.isNull(sfzh)) {
			whereSql.append(" and sfzh = ?");
			values.add(sfzh);
		}// end if
		if (!StringUtils.isNull(htbh)) {
			whereSql.append(" and htbh = ?");
			values.add(htbh);
		}// end if
		if (!StringUtils.isNull(sfqbhqdk)) {
			whereSql.append(" and sfqbhqdk = ?");
			values.add(sfqbhqdk);
		}// end if
		if (!StringUtils.isNull(btsj)) {
			whereSql.append(" and btsj = ?");
			values.add(btsj);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.mzmc,a.zzmmmc,(select b.rxrq from view_xsxxb b where a.xh=b.xh) rxrq,(select (c.xz+c.nj)||'-07' from view_xsjbxx c where a.xh=c.xh) byrq from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "mzmc",
				"zzmmmc", "rxrq", "byrq" };
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
	 * 删除贷款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXsdkxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_gzdx_xsdkxxb where xn||xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 贷款信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsdkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xn,dkcs,xh,xm,xb,sfzh,xymc,zymc,bjmc,dknj,dkze,htbh from (select rownum r,xn||xh pk,xn,dkcs,xh,xm,xb,sfzh,dknj,xymc,zymc,bjmc,dkze,htbh from view_xszz_gzdx_xsdkxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xn", "dkcs", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "dknj", "dkze", "htbh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 贷款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsdkxxResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_xszz_gzdx_xsdkxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取贷款信息详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,dkcs,xh,xm,xb,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,dknj,jtzz,yzbm,lxdh1,lxdh2,xfdk,zsfdk,dkze,dkyh,htbh,yhkh,dknx,dkqx,dkll,dkffr,dkdqr,txzzr,xjbdqk,bz1,bz2 from view_xszz_gzdx_xsdkxxb where xn||xh = ?";
		String[] colList = new String[] { "xn", "dkcs", "xh", "xm", "xb", "xz",
				"nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"dknj", "jtzz", "yzbm", "lxdh1", "lxdh2", "xfdk", "zsfdk",
				"dkze", "dkyh", "htbh", "yhkh", "dknx", "dkqx", "dkll",
				"dkffr", "dkdqr", "txzzr", "xjbdqk", "bz1", "bz2" };
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
	 * 获取贷款总信息详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkzxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,dkcs,xh,xm,xb,nj,xz,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,dknj,jtzz,yzbm,lxdh1,lxdh2,xfdk,zsfdk,dkze,dkyh,htbh,yhkh,dknx,dkqx,dkll,dkffr,dkdqr,txzzr,xjbdqk,bz1,bz2,zqyy,zqdqrq,zqtxzzrq,tqhksj,tqhkje,lxghqk_1,lxghqk_2,lxghqk_3,lxghqk_4,lxghqk_5,lxghqk_6,lxghqk_7,wysj,wyje,wyyy,sfqbhqdk,bz from view_xszz_gzdx_xsdkxxzb where xn||xh = ?";
		String[] colList = new String[] { "xn", "dkcs", "xh", "xm", "xb", "nj",
				"xz", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"dknj", "jtzz", "yzbm", "lxdh1", "lxdh2", "xfdk", "zsfdk",
				"dkze", "dkyh", "htbh", "yhkh", "dknx", "dkqx", "dkll",
				"dkffr", "dkdqr", "txzzr", "xjbdqk", "bz1", "bz2", "zqyy",
				"zqdqrq", "zqtxzzrq", "tqhksj", "tqhkje", "lxghqk_1",
				"lxghqk_2", "lxghqk_3", "lxghqk_4", "lxghqk_5", "lxghqk_6",
				"lxghqk_7", "wysj", "wyje", "wyyy", "sfqbhqdk", "bz" };
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
	 * 判断贷款信息是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isXsdkxxDataCf(String xh,String xn) throws Exception {
		String sql = "select count(*) num from xszz_gzdx_xsdkxxb where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		return "0".equalsIgnoreCase(num);
	}
	
	/**
	 * 保存贷款信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsdkxx(XsdkxxbModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String dkcs = Base.chgNull(model.getDkcs(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String dknj = Base.chgNull(model.getDknj(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String lxdh1 = Base.chgNull(model.getLxdh1(), "", 1);
		String lxdh2 = Base.chgNull(model.getLxdh2(), "", 1);
		String xfdk = Base.chgNull(model.getXfdk(), "", 1);
		String zsfdk = Base.chgNull(model.getZsfdk(), "", 1);
		String dkze = Base.chgNull(model.getDkze(), "", 1);
		String dkyh = Base.chgNull(model.getDkyh(), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String yhkh = Base.chgNull(model.getYhkh(), "", 1);
		String dknx = Base.chgNull(model.getDknx(), "", 1);
		String dkqx = Base.chgNull(model.getDkqx(), "", 1);
		String dkll = Base.chgNull(model.getDkll(), "", 1);
		String dkffr = Base.chgNull(model.getDkffr(), "", 1);
		String dkdqr = Base.chgNull(model.getDkdqr(), "", 1);
		String txzzr = Base.chgNull(model.getTxzzr(), "", 1);
		String xjbdqk = Base.chgNull(model.getXjbdqk(), "", 1);
		String bz1 = Base.chgNull(model.getBz1(), "", 1);
		String bz2 = Base.chgNull(model.getBz2(), "", 1);

		boolean sHave = isXsdkxxDataCf(xh, xn);
		if (sHave) {
			bFlag = StandardOperation.insert("xszz_gzdx_xsdkxxb", new String[] {
					"xn", "dkcs", "xh", "dknj", "jtzz", "yzbm", "lxdh1",
					"lxdh2", "xfdk", "zsfdk", "dkze", "dkyh", "htbh", "yhkh",
					"dknx", "dkqx", "dkll", "dkffr", "dkdqr", "txzzr",
					"xjbdqk", "bz1", "bz2" }, new String[] { xn, dkcs, xh,
					dknj, jtzz, yzbm, lxdh1, lxdh2, xfdk, zsfdk, dkze, dkyh,
					htbh, yhkh, dknx, dkqx, dkll, dkffr, dkdqr, txzzr, xjbdqk,
					bz1, bz2 }, request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("xszz_gzdx_xsdkxxb",
						new String[] { "dkcs", "dknj", "jtzz", "yzbm", "lxdh1",
								"lxdh2", "xfdk", "zsfdk", "dkze", "dkyh",
								"htbh", "yhkh", "dknx", "dkqx", "dkll",
								"dkffr", "dkdqr", "txzzr", "xjbdqk", "bz1",
								"bz2" }, new String[] { dkcs, dknj, jtzz, yzbm,
								lxdh1, lxdh2, xfdk, zsfdk, dkze, dkyh, htbh,
								yhkh, dknx, dkqx, dkll, dkffr, dkdqr, txzzr,
								xjbdqk, bz1, bz2 }, "xn||xh", xn + xh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}

	/**
	 * 删除就业信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXsjyxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_gzdx_dkxsjyxxb where xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 就业信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsjyxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,xb,sfzh,xymc,zymc,bjmc from (select rownum r,xh pk,xh,xm,xb,sfzh,xymc,zymc,bjmc from view_xszz_gzdx_dkxsjyxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 就业信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsjyxxResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_xszz_gzdx_dkxsjyxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取就业信息详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsjyxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,jtzz,yzbm,lxdh1,xdh2,xdh3,gzdw,dwdh,bz from view_xszz_gzdx_dkxsjyxxb where xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "jtzz", "yzbm",
				"lxdh1", "xdh2", "xdh3", "gzdw", "dwdh", "bz" };
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
	 * 判断就业信息是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isXsjyxxDataCf(String xh) throws Exception {
		String sql = "select count(*) num from xszz_gzdx_dkxsjyxxb where xh = ?";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		return "0".equalsIgnoreCase(num);
	}
	
	/**
	 * 保存就业信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsjyxx(XsjyxxbModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String lxdh1 = Base.chgNull(model.getLxdh1(), "", 1);
		String xdh2 = Base.chgNull(model.getXdh2(), "", 1);
		String xdh3 = Base.chgNull(model.getXdh3(), "", 1);
		String gzdw = Base.chgNull(model.getGzdw(), "", 1);
		String dwdh = Base.chgNull(model.getDwdh(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);

		boolean sHave = isXsjyxxDataCf(xh);
		if (sHave) {
			bFlag = StandardOperation.insert("xszz_gzdx_dkxsjyxxb",
					new String[] { "xh", "jtzz", "yzbm", "lxdh1", "xdh2",
							"xdh3", "gzdw", "dwdh", "bz" }, new String[] { xh,
							jtzz, yzbm, lxdh1, xdh2, xdh3, gzdw, dwdh, bz },
					request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("xszz_gzdx_dkxsjyxxb",
						new String[] { "jtzz", "yzbm", "lxdh1", "xdh2",
								"xdh3", "gzdw", "dwdh", "bz" },
						new String[] { jtzz, yzbm, lxdh1, xdh2, xdh3, gzdw,
								dwdh, bz }, "xh", xh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}
	
	/**
	 * 删除还款信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXshkxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_gzdx_hkxxb where htbh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 还款信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXshkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,htbh,xn,dkcs,dkze,sfqbhqdk from (select rownum r,htbh pk,htbh,xn,dkcs,dkze,sfqbhqdk from view_xszz_gzdx_hkxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "htbh", "xn", "dkcs", "dkze",
				"sfqbhqdk" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 还款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXshkxxResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_xszz_gzdx_hkxxb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取还款信息详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXshkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select htbh,xn,xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,dknj,dkcs,dkyh,yhkh,dkze,dkffr,dkdqr,txzzr,zqyy,zqdqrq,zqtxzzrq,tqhksj,tqhkje,lxghqk_1,lxghqk_2,lxghqk_3,lxghqk_4,lxghqk_5,lxghqk_6,lxghqk_7,wysj,wyje,wyyy,sfqbhqdk,bz from view_xszz_gzdx_hkxxb where htbh = ?";
		String[] colList = new String[] { "htbh", "xn", "xh", "xm", "xb", "nj",
				"sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "dknj",
				"dkcs", "dkyh", "yhkh", "dkze", "dkffr", "dkdqr", "txzzr",
				"zqyy", "zqdqrq", "zqtxzzrq", "tqhksj", "tqhkje", "lxghqk_1",
				"lxghqk_2", "lxghqk_3", "lxghqk_4", "lxghqk_5", "lxghqk_6",
				"lxghqk_7", "wysj", "wyje", "wyyy", "sfqbhqdk", "bz" };
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
	 *获取贷款信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxxByHtbh(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,dkcs,xh,xm,xb,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,dknj,jtzz,yzbm,lxdh1,lxdh2,xfdk,zsfdk,dkze,dkyh,htbh,yhkh,dknx,dkqx,dkll,dkffr,dkdqr,txzzr,xjbdqk,bz1,bz2 from view_xszz_gzdx_xsdkxxb where htbh = ?";
		String[] colList = new String[] { "xn", "dkcs", "xh", "xm", "xb", "xz",
				"nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"dknj", "jtzz", "yzbm", "lxdh1", "lxdh2", "xfdk", "zsfdk",
				"dkze", "dkyh", "htbh", "yhkh", "dknx", "dkqx", "dkll",
				"dkffr", "dkdqr", "txzzr", "xjbdqk", "bz1", "bz2" };
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
	 * 判断还款信息是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isXshkxxDataCf(String htbh) throws Exception {
		String sql = "select count(*) num from view_xszz_gzdx_hkxxb where htbh = ?";
		String num = dao.getOneRs(sql, new String[] { htbh }, "num");
		return "0".equalsIgnoreCase(num);
	}
	
	/**
	 * 保存还款信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXshkxx(XshkxxbModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String htbh = Base.chgNull(model.getHtbh(), "", 1);
		String zqyy = Base.chgNull(model.getZqyy(), "", 1);
		String zqdqrq = Base.chgNull(model.getZqdqrq(), "", 1);
		String zqtxzzrq = Base.chgNull(model.getZqtxzzrq(), "", 1);
		String tqhksj = Base.chgNull(model.getTqhksj(), "", 1);
		String tqhkje = Base.chgNull(model.getTqhkje(), "", 1);
		String lxghqk_1 = Base.chgNull(model.getLxghqk_1(), "", 1);
		String lxghqk_2 = Base.chgNull(model.getLxghqk_2(), "", 1);
		String lxghqk_3 = Base.chgNull(model.getLxghqk_3(), "", 1);
		String lxghqk_4 = Base.chgNull(model.getLxghqk_4(), "", 1);
		String lxghqk_5 = Base.chgNull(model.getLxghqk_5(), "", 1);
		String lxghqk_6 = Base.chgNull(model.getLxghqk_6(), "", 1);
		String lxghqk_7 = Base.chgNull(model.getLxghqk_7(), "", 1);
		String wysj = Base.chgNull(model.getWysj(), "", 1);
		String wyje = Base.chgNull(model.getWyje(), "", 1);
		String wyyy = Base.chgNull(model.getWyyy(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		String sfqbhqdk = Base.chgNull(model.getSfqbhqdk(), "", 1);

		boolean sHave = isXshkxxDataCf(htbh);
		if (sHave) {
			bFlag = StandardOperation.insert("xszz_gzdx_hkxxb", new String[] {
					"htbh", "zqyy", "zqdqrq", "zqtxzzrq", "tqhksj", "tqhkje",
					"lxghqk_1", "lxghqk_2", "lxghqk_3", "lxghqk_4", "lxghqk_5",
					"lxghqk_6", "lxghqk_7", "wysj", "wyje", "wyyy", "bz",
					"sfqbhqdk" }, new String[] { htbh, zqyy, zqdqrq, zqtxzzrq,
					tqhksj, tqhkje, lxghqk_1, lxghqk_2, lxghqk_3, lxghqk_4,
					lxghqk_5, lxghqk_6, lxghqk_7, wysj, wyje, wyyy, bz,
					sfqbhqdk }, request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("xszz_gzdx_hkxxb",
						new String[] { "zqyy", "zqdqrq", "zqtxzzrq", "tqhksj",
								"tqhkje", "lxghqk_1", "lxghqk_2", "lxghqk_3",
								"lxghqk_4", "lxghqk_5", "lxghqk_6", "lxghqk_7",
								"wysj", "wyje", "wyyy", "bz", "sfqbhqdk" },
						new String[] { zqyy, zqdqrq, zqtxzzrq, tqhksj, tqhkje,
								lxghqk_1, lxghqk_2, lxghqk_3, lxghqk_4,
								lxghqk_5, lxghqk_6, lxghqk_7, wysj, wyje, wyyy,
								bz, sfqbhqdk }, "htbh", htbh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}

	/**
	 * 删除临时补贴信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsbt(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_gzdx_lsbt where btsj||xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 临时补贴信息查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbtRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,btsj,xh,xm,xb,sfzh,xymc,zymc,bjmc,nj,btje from (select rownum r,btsj||xh pk,btsj,xh,xm,xb,sfzh,xymc,zymc,bjmc,nj,btje from view_xszz_gzdx_lsbt where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "btsj", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "nj", "btje" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 临时补贴信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbtResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_xszz_gzdx_lsbt where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取临时补贴信息详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbt(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,btsj,btje from view_xszz_gzdx_lsbt where btsj||xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "btsj", "btje" };
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
	 * 判断临时补贴信息是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isLsbtDataCf(String xh,String btsj) throws Exception {
		String sql = "select count(*) num from xszz_gzdx_lsbt where xh = ? and btsj = ?";
		String num = dao.getOneRs(sql, new String[] { xh, btsj }, "num");
		return "0".equalsIgnoreCase(num);
	}
	
	/**
	 * 保存临时补贴信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbt(LsbtModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String btsj = Base.chgNull(model.getBtsj(), "", 1);
		String btje = Base.chgNull(model.getBtje(), "", 1);

		boolean sHave = isLsbtDataCf(xh, btsj);
		if (sHave) {
			bFlag = StandardOperation.insert("xszz_gzdx_lsbt", new String[] {
					"xh", "btsj", "btje" }, new String[] { xh, btsj, btje },
					request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("xszz_gzdx_lsbt",
						new String[] { "btje" }, new String[] { btje },
						"btsj||xh", btsj + xh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}
}
