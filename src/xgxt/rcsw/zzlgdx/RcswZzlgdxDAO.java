package xgxt.rcsw.zzlgdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江理工DAO</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
 */
public class RcswZzlgdxDAO {
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
		String xh = queryModel.getXh();
		xh = StringUtils.isNotNull(xh) ? "%" + xh + "%" : "";
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xm = queryModel.getXm();
		xm = StringUtils.isNotNull(xm) ? "%" + xm + "%" : "";
		String xb = queryModel.getXb();
		String sfzh = queryModel.getSfzh();
		sfzh = StringUtils.isNotNull(sfzh) ? "%" + sfzh + "%" : "";
		String xysh = queryModel.getXysh();
		String xxsh = queryModel.getXxsh();
		String qjlx = queryModel.getQjlx();
		String shjg = queryModel.getShjg();

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
			whereSql.append(" and xh like ?");
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
			whereSql.append(" and xm like ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(xb)) {
			whereSql.append(" and xb = ?");
			values.add(xb);
		}// end if
		if (!StringUtils.isNull(sfzh)) {
			whereSql.append(" and sfzh like ?");
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
		if (!StringUtils.isNull(qjlx)) {
			whereSql.append(" and qjlx = ?");
			values.add(qjlx);
		}// end if
		if (!StringUtils.isNull(shjg)) {
			whereSql.append(" and shjg = ?");
			values.add(shjg);
		}// end if
		return whereSql;
	}

	/**
	 * 导出数据 getExpDate ---- 导出数据
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
	 * 导出数据 getExpDate ----导出数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getExpShDate(QueryModel model,
			String tabName, HttpServletRequest request) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		String userType = model.getUserType();

		
		StringBuffer whereSql = getWhereSql(model, request);
		String sql = StringUtils.joinStr("select * from ", 
				                         tabName, 
				                         " where 1=1 ",
				                         whereSql.toString());
		if(!"xy".equalsIgnoreCase(userType)){
			sql += " and xysh='通过' ";
		}
		
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// 获得列名数组
		rs.addAll(dao.rsToVator(sql,
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
	 * 删除在校证明信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZxzmxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete rcsw_zzlgdx_zxzmxx where xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 在校证明查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxzmRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,xb,sfzh,nj,xymc,zymc,bjmc,lrsj from (select rownum r,xh pk,xh,xm,xb,sfzh,nj,xymc,zymc,bjmc,lrsj from view_rcsw_zzlgdx_zxzmxx where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "lrsj" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 在校证明信信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxzmResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_rcsw_zzlgdx_zxzmxx where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取在校证明详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxzmxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,zxbxjwhcdbx,lrsj,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc from view_rcsw_zzlgdx_zxzmxx where xh = ?";
		String[] colList = new String[] { "xh", "zxbxjwhcdbx", "lrsj", "xm",
				"xb", "mzmc", "zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc" };
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
	 * 保存在校证明详细信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxzmxx(ZxzmModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String type = Base.chgNull(request.getParameter("type"), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String zxbxjwhcdbx = Base.chgNull(model.getZxbxjwhcdbx(), "", 1);
		String lrsj = GetTime.getSystemTime();

		boolean sHave = isZxzmDataCf(xh);
		if (sHave) {
			bFlag = StandardOperation.insert("rcsw_zzlgdx_zxzmxx",
					new String[] { "xh", "zxbxjwhcdbx" }, new String[] { xh,
							zxbxjwhcdbx }, request);
		} else {
			if ("modi".equalsIgnoreCase(type)) {
				bFlag = StandardOperation.update("rcsw_zzlgdx_zxzmxx",
						new String[] { "zxbxjwhcdbx", "lrsj" }, new String[] {
								zxbxjwhcdbx, lrsj }, "xh", xh, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		}
		return bFlag;
	}
	
	/**
	 * 判断在校证明是否重复，重复返回false，没有重复的返回true
	 * 
	 * @param xh
	 * @param xq
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isZxzmDataCf(String xh) throws Exception {
		String sql = "select count(*) num from rcsw_zzlgdx_zxzmxx where xh = ?";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		return "0".equalsIgnoreCase(num);
	}
	
	/**
	 * 删除出国(境)信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String delCgjxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		String msg = null;
		
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete rcsw_zzlgdx_cgjzm where guid='" + pkT[i] + "'";
		}
		try {
			dao.runBatch(sqlT);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "删除失败！";
			throw e;
		}
		
		return msg;
	}
	
	/**
	 * 出国(境)查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm, boolean b)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String sql = "select disabled,pk,xh,xm,xb,nj,xymc,zymc,bjmc,sqqx,sqsj,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
		} else {
			sql += "'' disabled,";
		}
		sql += "guid pk,xh,xm,xb,nj,xymc,zymc,bjmc,sqqx,sqsj,xysh,xxsh from view_rcsw_zzlgdx_cgjzm where 1=1";
		
		if (b && (("admin".equalsIgnoreCase(userType)) || "xx"
						.equalsIgnoreCase(userType))) {
			sql += " and xysh='通过' ";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "pk", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "sqqx", "sqsj", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 出国(境)信信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getCgjResNum(QueryModel queryModel,
			HttpServletRequest request, boolean b) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "select count(*) num from view_rcsw_zzlgdx_cgjzm where 1=1";

		if (b && (("admin".equalsIgnoreCase(userType)) || "xx"
						.equalsIgnoreCase(userType))) {
			sql += " and xysh='通过' ";
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 出国(境)查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCgjStuRes(String xh) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select guid pk,xh,xm,sqqx,cgrq,fhrq,sqsj,xysh,xyshyj,xxsh,xxshyj from view_rcsw_zzlgdx_cgjzm where xh=? order by sqsj desc";

		String[] colList = new String[] { "pk", "xh", "xm", "sqqx", "cgrq",
				"fhrq", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };

		resList = dao.rsToVator(sql, new String[] { xh }, colList);
		return resList;
	}
	
	/**
	 * 获取出国(境)详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getCgjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select guid,xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxfs_br,lxfs_jz,sqqx,cgrq,fhrq,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_rcsw_zzlgdx_cgjzm where guid = ?";
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "nj",
				"sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"lxfs_br", "lxfs_jz", "sqqx", "cgrq", "fhrq", "sqly", "sqsj",
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
	 * 判断出国(境)是否通过审核，通过返回false,未通过返回true
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isCgjDataCf(String guid) throws Exception {
		String sql = "select count(*) num from rcsw_zzlgdx_cgjzm where guid = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { guid }, "num");
		return num.equalsIgnoreCase("0");
	}
	
	/**
	 * 保存出国(境)信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public String[] saveCgjSqxx(CgjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxfs_br = Base.chgNull(model.getLxfs_br(), "", 1);
		String lxfs_jz = Base.chgNull(model.getLxfs_jz(), "", 1);
		String sqqx = Base.chgNull(model.getSqqx(), "", 1);
		String cgrq = Base.chgNull(model.getCgrq(), "", 1);
		String fhrq = Base.chgNull(model.getFhrq(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		//================2010.10.14 edit by luojw===================
		String sqsj = Base.chgNull(model.getSqsj(), "", 1);
		sqsj = Base.isNull(sqsj) ? now : sqsj;
		String pkValue = xh+sqsj;
		
		if ("modi".equalsIgnoreCase(request.getParameter("type"))) {
			
			if (isCgjDataCf(guid)) {
				bFlag = StandardOperation.update("rcsw_zzlgdx_cgjzm",
						new String[] { "xh", "lxfs_br", "lxfs_jz", "sqqx",
								"cgrq", "fhrq", "sqly", "sqsj", "xysh",
								"xyshyj", "xxsh", "xxshyj" }, new String[] {
								xh, lxfs_br, lxfs_jz, sqqx, cgrq, fhrq, sqly,
								now, "未审核", "", "未审核", "" }, "xh||sqsj", pkValue,
						request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		} else {
			bFlag = StandardOperation.insert("rcsw_zzlgdx_cgjzm",
					new String[] { "xh", "lxfs_br", "lxfs_jz", "sqqx", "cgrq",
							"fhrq", "sqly" }, new String[] { xh, lxfs_br,
							lxfs_jz, sqqx, cgrq, fhrq, sqly }, request);
			guid = dao
					.getOneRs(
							"select guid from rcsw_zzlgdx_cgjzm where xh=? and lxfs_br=? and lxfs_jz=? and sqqx=? and cgrq=? and fhrq=? and sqly=? and xysh='未审核' and xyshyj is null and xxsh='未审核' and xxshyj is null and rownum=1 order by sqsj desc",
							new String[] { xh, lxfs_br, lxfs_jz, sqqx, cgrq,
									fhrq, sqly }, "guid");
		}
		
		return new String[] { bFlag ? "1" : "0", Base.chgNull(guid, "", 1) };
	}
	
	/**
	 * 批量修改出国(境)审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String modCgjxx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update rcsw_zzlgdx_cgjzm set xxsh='" + shjg
						+ "' where guid='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update rcsw_zzlgdx_cgjzm set xysh='" + shjg
						+ "' where guid='" + pkT[i] + "' and xxsh='未审核'";
			}
		}
		
		String msg = "操作成功！";
		
		try {
			dao.runBatch(sqlT);
		} catch (Exception e) {
			msg = "操作失败！";
			throw e;
		}
		
		return msg;
	}
	
	/**
	 * 保存出国(境)审核信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCgjShxx(CgjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();

		String guid = Base.chgNull(model.getGuid(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		boolean sHave = isCgjDataCf(guid);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("rcsw_zzlgdx_cgjzm", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj }, "guid",
					guid, request);
		} else {
			if (!sHave) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				bFlag = StandardOperation.update("rcsw_zzlgdx_cgjzm",
						new String[] { "xysh", "xyshyj" }, new String[] { xysh,
								xyshyj }, "guid", guid, request);
			}
		}

		return bFlag;
	}

	/**
	 * 删除请假信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String delQjxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete rcsw_zzlgdx_qjsqb where guid='" + pkT[i] + "'";
		}
		String msg = "删除失败！";
		try {
			dao.runBatch(sqlT);
			msg = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return msg;
	}
	
	/**
	 * 请假查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,xb,nj,xymc,zymc,bjmc,qjlx,sqsj,shjg from (select rownum r,guid pk,xh,xm,xb,nj,xymc,zymc,bjmc,qjlx,sqsj,shjg from view_rcsw_zzlgdx_qjsqb where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "qjlx", "sqsj", "shjg" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 请假信信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getQjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_rcsw_zzlgdx_qjsqb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 请假查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getQjStuRes(String xh) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select guid pk,xh,xm,qjlx,qjksrq,qjjzrq,sqsj,shjg,shyj from view_rcsw_zzlgdx_qjsqb where xh=? order by sqsj desc";

		String[] colList = new String[] { "pk", "xh", "xm", "qjlx", "qjksrq",
				"qjjzrq", "sqsj", "shjg", "shyj" };

		resList = dao.rsToVator(sql, new String[] { xh }, colList);
		return resList;
	}
	
	/**
	 * 获取请假详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select guid,xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,qjlx,qjksrq,qjjzrq,qjkc1,qjkcs1,qjkc2,qjkcs2,qjkc3,qjkcs3,qjyy,sqsj,shjg,shyj from view_rcsw_zzlgdx_qjsqb where guid = ?";
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "nj",
				"sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "qjlx",
				"qjksrq", "qjjzrq", "qjkc1", "qjkcs1", "qjkc2", "qjkcs2",
				"qjkc3", "qjkcs3", "qjyy", "sqsj", "shjg", "shyj" };
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
	 * 判断请假是否通过审核，通过返回false,未通过返回true
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isQjDataCf(String guid) throws Exception {
		String sql = "select count(*) num from rcsw_zzlgdx_qjsqb where guid = ? and shjg='通过'";
		String num = dao.getOneRs(sql, new String[] { guid }, "num");
		return num.equalsIgnoreCase("0");
	}
	
	/**
	 * 保存请假信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public String[] saveQjSqxx(QjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String qjlx = Base.chgNull(model.getQjlx(), "", 1);
		String qjksrq = Base.chgNull(model.getQjksrq(), "", 1);
		String qjjzrq = Base.chgNull(model.getQjjzrq(), "", 1);
		String qjkc1 = Base.chgNull(model.getQjkc1(), "", 1);
		String qjkcs1 = Base.chgNull(model.getQjkcs1(), "", 1);
		String qjkc2 = Base.chgNull(model.getQjkc2(), "", 1);
		String qjkcs2 = Base.chgNull(model.getQjkcs2(), "", 1);
		String qjkc3 = Base.chgNull(model.getQjkc3(), "", 1);
		String qjkcs3 = Base.chgNull(model.getQjkcs3(), "", 1);
		String qjyy = Base.chgNull(model.getQjyy(), "", 1);
		String now = GetTime.getSystemTime();;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String pkValue = xh+now;
		
		if ("modi".equalsIgnoreCase(request.getParameter("type"))) {
			if (isQjDataCf(guid)) {
				bFlag = StandardOperation.update("rcsw_zzlgdx_qjsqb",
						new String[] { "xh", "qjlx", "qjksrq", "qjjzrq",
								"qjkc1", "qjkcs1", "qjkc2", "qjkcs2", "qjkc3",
								"qjkcs3", "qjyy", "sqsj", "shjg", "shyj" },
						new String[] { xh, qjlx, qjksrq, qjjzrq, qjkc1, qjkcs1,
								qjkc2, qjkcs2, qjkc3, qjkcs3, qjyy, now, "未审核",
								"" }, "xh||sqsj", pkValue, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		} else {
			bFlag = StandardOperation.insert("rcsw_zzlgdx_qjsqb", new String[] {
					"xh", "qjlx", "qjksrq", "qjjzrq", "qjkc1", "qjkcs1",
					"qjkc2", "qjkcs2", "qjkc3", "qjkcs3", "qjyy" },
					new String[] { xh, qjlx, qjksrq, qjjzrq, qjkc1, qjkcs1,
							qjkc2, qjkcs2, qjkc3, qjkcs3, qjyy }, request);
			guid = dao
					.getOneRs(
							"select guid from rcsw_zzlgdx_qjsqb where xh=? and qjlx=? and qjksrq=? and qjjzrq=? and qjkc1=? and qjkcs1=? and qjkc2=? and qjkcs2=? and qjkc3=? and qjkcs3=? and qjyy=? and shjg='未审核' and shyj is null and rownum=1 order by sqsj desc",
							new String[] { xh, qjlx, qjksrq, qjjzrq, qjkc1,
									qjkcs1, qjkc2, qjkcs2, qjkc3, qjkcs3, qjyy },
							"guid");
		}
		
		return new String[] { bFlag ? "1" : "0", Base.chgNull(guid, "", 1) };
	}
	
	/**
	 * 批量修改请假审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String modQjxx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update rcsw_zzlgdx_qjsqb set shjg='" + shjg
					+ "' where guid='" + pkT[i] + "'";
		}
		String msg = "操作失败！";
		try {
			dao.runBatch(sqlT);
			msg = "操作成功！";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return msg;
	}
	
	/**
	 * 保存请假审核信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveQjShxx(QjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String shjg = Base.chgNull(model.getShjg(), "", 1);
		String shyj = Base.chgNull(model.getShyj(), "", 1);

		bFlag = StandardOperation.update("rcsw_zzlgdx_qjsqb", new String[] {
				"shjg", "shyj" }, new String[] { shjg, shyj }, "guid", guid,
				request);

		return bFlag;
	}

	/**
	 * 删除入伍信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String delRwxx(String[] pkT)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete rcsw_zzlgdx_rwxx where guid='" + pkT[i] + "'";
		}
		String msg = "删除失败！";
		try {
			dao.runBatch(sqlT);
			msg = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return msg;
	}
	
	/**
	 * 入伍查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwRes(QueryModel queryModel,
			HttpServletRequest request, RcswZzlgdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,shjg from (select rownum r,guid pk,xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,shjg from view_rcsw_zzlgdx_rwxx where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "sqsj", "shjg" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 入伍信信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getRwResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String sql = "select count(*) num from view_rcsw_zzlgdx_rwxx where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 入伍查询结果-学生
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRwStuRes(String xh) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select guid pk,xh,xm,sj,sqsj,shjg,shyj from view_rcsw_zzlgdx_rwxx where xh=? order by sqsj desc";

		String[] colList = new String[] { "pk", "xh", "xm", "sj", "sqsj",
				"shjg", "shyj" };

		resList = dao.rsToVator(sql, new String[] { xh }, colList);
		return resList;
	}
	
	/**
	 * 获取入伍详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRwxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select guid,xh,xm,xb,csrq,sfzh,mzmc,zzmmmc,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,sj,xjhj,rxqhkszd,jtxxdz,fmlxfs,sfygzjzbty,sqsj,shjg,shyj from view_rcsw_zzlgdx_rwxx where guid = ?";
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "csrq",
				"sfzh", "mzmc", "zzmmmc", "nj", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "sj", "xjhj", "rxqhkszd", "jtxxdz", "fmlxfs",
				"sfygzjzbty", "sqsj", "shjg", "shyj" };
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
	 * 判断入伍是否通过审核，通过返回false,未通过返回true
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isRwDataCf(String guid) throws Exception {
		String sql = "select count(*) num from rcsw_zzlgdx_rwxx where guid = ? and shjg='通过'";
		String num = dao.getOneRs(sql, new String[] { guid }, "num");
		return num.equalsIgnoreCase("0");
	}
	
	/**
	 * 保存入伍信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public String[] saveRwSqxx(RwModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sj = Base.chgNull(model.getSj(), "", 1);
		String xjhj = Base.chgNull(model.getXjhj(), "", 1);
		String rxqhkszd = Base.chgNull(model.getRxqhkszd(), "", 1);
		String jtxxdz = Base.chgNull(model.getJtxxdz(), "", 1);
		String fmlxfs = Base.chgNull(model.getFmlxfs(), "", 1);
		String sfygzjzbty = Base.chgNull(model.getSfygzjzbty(), "", 1);
		String now = GetTime.getSystemTime();;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String pkValue = xh+now;
		
		if ("modi".equalsIgnoreCase(request.getParameter("type"))) {
			if (isRwDataCf(guid)) {
				bFlag = StandardOperation.update("rcsw_zzlgdx_rwxx",
						new String[] { "xh", "sj", "xjhj", "rxqhkszd",
								"jtxxdz", "fmlxfs", "sfygzjzbty", "sqsj",
								"shjg", "shyj" }, new String[] { xh, sj, xjhj,
								rxqhkszd, jtxxdz, fmlxfs, sfygzjzbty, now,
								"未审核", "" }, "xh||sqsj", pkValue, request);
			} else {
				request.setAttribute("isPASS", "is");
			}
		} else {
			bFlag = StandardOperation.insert("rcsw_zzlgdx_rwxx", new String[] {
					"xh", "sj", "xjhj", "rxqhkszd", "jtxxdz", "fmlxfs",
					"sfygzjzbty" }, new String[] { xh, sj, xjhj, rxqhkszd,
					jtxxdz, fmlxfs, sfygzjzbty }, request);
			guid = dao
					.getOneRs(
							"select guid from rcsw_zzlgdx_rwxx where xh=? and sj=? and xjhj=? and rxqhkszd=? and jtxxdz=? and fmlxfs=? and sfygzjzbty=? and shjg='未审核' and shyj is null and rownum=1 order by sqsj desc",
							new String[] { xh, sj, xjhj, rxqhkszd, jtxxdz,
									fmlxfs, sfygzjzbty }, "guid");
		}
		
		return new String[] { bFlag ? "1" : "0", Base.chgNull(guid, "", 1) };
	}
	
	/**
	 * 批量修改入伍审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public String modRwxx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update rcsw_zzlgdx_rwxx set shjg='" + shjg
					+ "' where guid='" + pkT[i] + "'";
		}
		
		String msg = "操作失败！";
		try {
			dao.runBatch(sqlT);
			msg = "操作成功！";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return msg;
	}
	
	/**
	 * 保存入伍审核信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveRwShxx(RwModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String shjg = Base.chgNull(model.getShjg(), "", 1);
		String shyj = Base.chgNull(model.getShyj(), "", 1);

		bFlag = StandardOperation.update("rcsw_zzlgdx_rwxx", new String[] {
				"shjg", "shyj" }, new String[] { shjg, shyj }, "guid", guid,
				request);

		return bFlag;
	}
	
	
	/**
	 * 获取自定义表单字段
	 * @param realTable
	 * @return
	 */
	public List<HashMap<String, String>> getBdZd(String realTable) {
		DAO dao = DAO.getInstance();
		String sql = "select zdid,zdmc,zdlx,zdcd from ty_bdsz where tabname = ?  order by zdlx,zdpx";
		return dao.getList(sql, new String[] { realTable }, new String[] {
				"zdid", "zdmc", "zdlx", "zdcd" });
	}
	
	/**
	 * 获得选项列
	 * 
	 * @param tableName
	 * @param xn
	 * @return
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> getOps(
			String realTable) {
		DAO dao = DAO.getInstance();
		String sql = "select b.zdid,a.opid,a.opmc from ty_bdszxxb a " +
				"left join ty_bdsz b on a.szbzj= b.zdid||b.tabname " +
				"where b.tabname = ?  order by b.zdpx";
		return dao.getOptionList(sql, new String[] { realTable },
				new String[] { "zdid", "opid", "opmc" });
	}
	
	
	/**
	 * 保存含自定义数据
	 * @param realTable
	 * @param colList
	 * @param model
	 * @param request
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public boolean saveData(String realTable, String[] colList, ZxzmModel model,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.insertNoLog(realTable, colList,
				inputList);
		return updata;
	}
	
	
	/**
	 * 修改含自定义数据 
	 * @param realTable
	 * @param model
	 * @param pk
	 * @param pkValue
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public boolean updataZdyDdData(String tableName,String pkValue,
			HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql = "delete from ty_bdsz_bcnr where tabname = ? and zbid = ?";
		boolean del = dao.runUpdate(sql, new String[] { tableName, pkValue });
		if (del) {
			sql = "select zdid from ty_bdsz where  tabname = ?";
			String[] zdyzds = dao.getArray(sql, new String[] {tableName },
					"zdid");
			String[] inputList = new String[zdyzds.length];
			// 实际要修改或插入的sql数
			int j = 0;
			for (int i = 0; i < zdyzds.length; i++) {
				inputList[i] = request.getParameter(zdyzds[i]);
				if (inputList[i] != null) {
					j++;
				}
			}
			String[] sqlmap = new String[j];
			int k = 0;
			for (int i = 0; i < inputList.length; i++) {
				if (inputList[i] != null) {
					sqlmap[k] = "insert into ty_bdsz_bcnr values ('"
							+ zdyzds[i] + "','" + tableName + "','" + pkValue
							+ "','" + inputList[i] + "')";
					k++;
				}
			}
			dao.runBatch(sqlmap);
		}
		return del;
	}
	
	
	public List<HashMap<String, String>> zdyColList(String tableName)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select zdid,zdmc from ty_bdsz where "
				+ " tabname = ? and cxxs = '显示' order by cxxspx";
		return dao.getList(sql, new String[] { tableName }, new String[] {
				"zdid", "zdmc" });
	}
	
	
	/**
	 * 查询含自定义字段数据
	 * @param tableName
	 * @param myModel
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param pkKey
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getZdyQueryList(String tableName,
			ZxzmModel myModel, String[] colList, String[] zdyCol,String realTable,
			String[] pkKey) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] queryList = new String[]{"nj","xydm","zydm","bjdm","shlx","xysh","xxsh","xb","xmmc"};
		if(Globals.XXDM_WHSYFWXY.equals(Base.xxdm)){
			queryList = new String[]{"nj","xydm","zydm","bjdm","shlx","fdysh","xysh","xxsh","xb","xmmc"};
		}
		String[] queryLikeList = new String[]{"xh","xm","sfzh"};
		String[] inputList = new String[] {};
		String query = "";
		if (queryList != null) {
			MakeQuery makeQuery = new MakeQuery();
			makeQuery.makeQuery(queryList, queryLikeList, myModel);
			inputList = makeQuery.getInputList();
			query = makeQuery.getQueryString();
		}
		int size = colList.length - 1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for (int i = 0; i < (size); i++) {
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(" rownum r,a.");
		sqlBuffer.append(colList[size]);

		for (int i = 0; i < zdyCol.length; i++) {
			sqlBuffer
					.append(",(select bcnr from ty_bdsz_bcnr where tabname = '");
			sqlBuffer.append(realTable);
			sqlBuffer.append("' and zdid = '");
			sqlBuffer.append(zdyCol[i]);
			sqlBuffer.append("' and zbid = ");
			for (int j = 0; j < pkKey.length; j++) {
				sqlBuffer.append("a.");
				sqlBuffer.append(pkKey[j]);
				if (j != pkKey.length - 1) {
					sqlBuffer.append("||");
				}
			}
			sqlBuffer.append(") ");
			sqlBuffer.append(zdyCol[i]);
		}
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" a ");
		String[] zcolList = new String[colList.length + zdyCol.length];
		for (int i = 0; i < colList.length; i++) {
			zcolList[i] = colList[i];
		}
		for (int i = 0; i < zdyCol.length; i++) {
			zcolList[colList.length + i] = zdyCol[i];
		}
		return CommonQueryDAO.commonQuery(sqlBuffer.toString(), query,
				inputList, zcolList, myModel);
	}
	
	
	/**
	 * 批量删除含自定义字段数据
	 * @param tableName
	 * @param pkV
	 * @param primaryKey
	 * @return
	 * @throws SQLException
	 */
	public boolean delData(String tableName, String[] pkV, String primaryKey)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] sqlMap = new String[pkV.length];
		String[] sqlZdyMap = new String[pkV.length];
		
		for (int i = 0; i < pkV.length; i++) {
			sqlMap[i] = "delete from " + tableName + " where " + primaryKey
					+ " = '" + pkV[i] + "'";
			sqlZdyMap[i] = "delete from ty_bdsz_bcnr where zbid = '"
					+ pkV[i] + "' and tabname='" + tableName + "'";
		}
		
		int[] result = dao.runBatch(sqlMap);
		
		boolean update = dao.checkBatch(result);
		
		if (update) {
			result = dao.runBatch(sqlZdyMap);
			update = dao.checkBatch(result);
		} else {
			update = false;
		}
		
		return update;
	}
	
	
	/**
	 * 返回单条自定义数据
	 * 
	 * @param tableName
	 * @param realTable
	 * @param colList
	 * @param zdyzdList
	 * @param pkCol
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String[] colList, String[] zdyzdList,
			String pkCol, String pk) {
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName,
				colList, pkCol, pk);
		ArrayList<String[]> zdysjj = CommonQueryDAO.commonQueryNotFy(
				"ty_bdsz_bcnr", " where tabname = ? and zbid = ?",
				new String[] { realTable, pk },
				new String[] { "zdid", "bcnr" }, null);
		for (int i = 0; i < zdysjj.size(); i++) {
			String[] zdyTmp = zdysjj.get(i);
			rs.put(zdyTmp[0], zdyTmp[1]);
		}
		return rs;
	}
	
}
