package xgxt.xszz.xmlgxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: N05学生资助DAO</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-10-13</p>
 */
public class XszzXmlgxyDAO {
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.mzmc,a.zzmmmc,(select b.rxrq from view_xsxxb b where a.xh=b.xh) rxrq,(select c.yhkh from view_xsxxb c where a.xh=c.xh) yhkh,(select c.yhmc from view_xsxxb c where a.xh=c.xh) yhmc from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "mzmc",
				"zzmmmc", "rxrq", "yhkh", "yhmc" };
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
	 * 得到申请权限
	 * 
	 * @param sUserType,xh,zzType(资助名称)
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getSqQx(String zzType, String sUserType, String xh)
			throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from common_new_gjzxdk_sjb a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc=? and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { zzType, xh }, new String[] {
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
	 * 获取国家励志奖学金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,yhkh,yhmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xxcj,sqly,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_xmlg_gjlzjxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "yhkh", "yhmc", "lxdh",
				"chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr", "srly", "jtzz",
				"yzbm", "xxcj", "sqly", "sqsj", "xysh", "xyshsj", "xyshyj",
				"xxsh", "xxshsj", "xxshyj" };
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
	 * 判断国家励志奖学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjlzjxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_xmlg_gjlzjxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_xmlg_gjlzjxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家励志奖学金信息，成功返回TRUE，反之返回FALSE saveGjlzjxjSqxx ---- 保存国家励志奖学金申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(model.getRjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String xxcj = Base.chgNull(model.getXxcj(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isGjlzjxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_xmlg_gjlzjxj", new String[] {
					"xn", "xh", "lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "xxcj", "sqly" },
					new String[] { xn, xh, lxdh, chhzjl, jthk, jtzrks, jtyzsr,
							rjysr, srly, jtzz, yzbm, xxcj, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_xmlg_gjlzjxj", new String[] {
					"lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "xxcj", "sqly", "xysh", "xyshsj",
					"xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj" },
					new String[] { lxdh, chhzjl, jthk, jtzrks, jtyzsr, rjysr,
							srly, jtzz, yzbm, xxcj, sqly, "未审核", "", "", "未审核",
							"", "", now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家励志奖学金信息 delGjlzjxjxx ---- 删除国家励志奖学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_xmlg_gjlzjxj where xn||xh='" + pkT[i]
						+ "'";
			} else {
				sqlT[i] = "delete xszz_xmlg_gjlzjxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
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
	public void modGjlzjxjxx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_xmlg_gjlzjxj set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				sqlT[i] = "update xszz_xmlg_gjlzjxj set xysh='"
						+ shjg
						+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家励志奖学1查询结果 Gjlzjxjres ---- 国家励志奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzXmlgxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjlzjxj where 1=1";
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjlzjxj where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家励志奖学金信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "select count(*) num from view_xszz_xmlg_gjlzjxj where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (!userType.equalsIgnoreCase("xy")) {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
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
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjlzjxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_xmlg_gjlzjxj", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				bFlag = StandardOperation.update("xszz_xmlg_gjlzjxj",
						new String[] { "xysh", "xyshyj", "xyshsj" },
						new String[] { xysh, xyshyj, now }, "xn||xh", xn + xh,
						request);
			}
		}

		return bFlag;
	}
	
	public List<HashMap<String, String>> getGjlzjxjGsmdXyList(String xn)
			throws Exception {
		List<HashMap<String, String>> list = null;
		String sql = "select nvl(xymc,'') xymc,count(xh) cout   from view_xszz_xmlg_gjlzjxj where xn=? and xxsh='通过' group by xymc order by xymc";
		list = dao.getList(sql, new String[] { xn }, new String[] { "xymc",
				"cout" });
		return list;
	}
	
	public List<HashMap<String, String>> getGjlzjxjGsmdXsList(String xn) {
		List<HashMap<String, String>> list = null;
		String sql = "select nvl(xymc,'') xymc,nvl(bjmc,'') bjmc,nvl(xm,'') xm from view_xszz_xmlg_gjlzjxj where xn=? and xxsh='通过'";
		list = dao.getList(sql, new String[] { xn }, new String[] { "xymc",
				"bjmc", "xm" });
		return list;
	}
	
	public String[] getGjlzjxjYhmcList(String xn) throws Exception {
		return dao.getRs("select distinct yhmc from view_xszz_xmlg_gjlzjxj where xn=? and xxsh='通过' and yhmc is not null",
				new String[] { xn }, "yhmc");
	}
	
	/**
	 * 获取国家奖学金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,zhkppm,hjqk,yjjl,xjjl,sjjl,sqly,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_xmlg_gjjxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "gxnbxkcs", "yxkcs",
				"lhkcs", "cjpm", "zhkpcj", "zhkppm", "hjqk", "yjjl", "xjjl",
				"sjjl", "sqly", "sqsj", "xysh", "xyshsj", "xyshyj", "xxsh",
				"xxshsj", "xxshyj" };
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
	 * 判断国家奖学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjjxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjjxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_xmlg_gjjxj where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_xmlg_gjjxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家奖学金信息，成功返回TRUE，反之返回FALSE saveGjjxjSqxx ---- 保存国家奖学金申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjSqxx(GjjxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String gxnbxkcs = Base.chgNull(model.getGxnbxkcs(), "", 1);
		String yxkcs = Base.chgNull(model.getYxkcs(), "", 1);
		String lhkcs = Base.chgNull(model.getLhkcs(), "", 1);
		String cjpm = Base.chgNull(model.getCjpm(), "", 1);
		String zhkpcj = Base.chgNull(model.getZhkpcj(), "", 1);
		String zhkppm = Base.chgNull(model.getZhkppm(), "", 1);
		String hjqk = Base.chgNull(model.getHjqk(), "", 1);
		String yjjl = Base.chgNull(model.getYjjl(), "", 1);
		String xjjl = Base.chgNull(model.getXjjl(), "", 1);
		String sjjl = Base.chgNull(model.getSjjl(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isGjjxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_xmlg_gjjxj",
					new String[] { "xn", "xh", "lxdh", "gxnbxkcs", "yxkcs",
							"lhkcs", "cjpm", "zhkpcj", "zhkppm", "hjqk",
							"yjjl", "xjjl", "sjjl", "sqly" }, new String[] {
							xn, xh, lxdh, gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj,
							zhkppm, hjqk, yjjl, xjjl, sjjl, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_xmlg_gjjxj", new String[] {
					"lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj",
					"zhkppm", "hjqk", "yjjl", "xjjl", "sjjl", "sqly", "xysh",
					"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj" },
					new String[] { lxdh, gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj,
							zhkppm, hjqk, yjjl, xjjl, sjjl, sqly, "未审核", "",
							"", "未审核", "", "", now }, "xn||xh", xn + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家奖学金信息 delGjjxjxx ---- 删除国家奖学金信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjjxjxx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_xmlg_gjjxj where xn||xh='" + pkT[i]
						+ "'";
			} else {
				sqlT[i] = "delete xszz_xmlg_gjjxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'通过'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家奖学金审核结果 modGjjxjxx ---- 批量修改国家奖学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxjxx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_xmlg_gjjxj set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				sqlT[i] = "update xszz_xmlg_gjjxj set xysh='"
						+ shjg
						+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "' and xxsh='未审核'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjjxjres ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzXmlgxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjjxj where 1=1";
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_xszz_xmlg_gjjxj where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家奖学金信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjjxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "select count(*) num from view_xszz_xmlg_gjjxj where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (!userType.equalsIgnoreCase("xy")) {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家奖学金审核信息，成功返回TRUE，反之返回FALSE saveGjjxjShxx ---- 保存国家奖学金审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjShxx(GjjxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjjxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_xmlg_gjjxj", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				bFlag = StandardOperation.update("xszz_xmlg_gjjxj",
						new String[] { "xysh", "xyshyj", "xyshsj" },
						new String[] { xysh, xyshyj, now }, "xn||xh", xn + xh,
						request);
			}
		}

		return bFlag;
	}
}
