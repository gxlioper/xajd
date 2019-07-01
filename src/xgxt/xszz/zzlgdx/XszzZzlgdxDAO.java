package xgxt.xszz.zzlgdx;

import java.sql.SQLException;
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
 * <p>Description: 浙江理工大学DAO</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-17</p>
 */
public class XszzZzlgdxDAO {
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
		String dkhth = queryModel.getDkhth();
		String xh = queryModel.getXh();
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xm = queryModel.getXm();
		String xb = queryModel.getXb();
		String sfzh = queryModel.getSfzh();

		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
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
		if (!StringUtils.isNull(dkhth)) {
			whereSql.append(" and dkhth = ?");
			values.add(dkhth);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.mzmc,a.zzmmmc from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "mzmc",
				"zzmmmc" };
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
	 * 删除助学贷款信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delZxdk(String[] pk,HttpServletRequest request){
		String[] sqlT = new String[pk.length];
		for (int i = 0; i < pk.length; i++) {
			sqlT[i] = "delete xszz_zzlg_zxdk where guid='" + pk[i] + "'";
		}
		try {
			dao.runBatch(sqlT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 助学贷款查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxdkRes(QueryModel queryModel,
			HttpServletRequest request, XszzZzlgdxActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,sfzh,xymc,zymc,bjmc,dkhth,dkje from (select rownum r,guid pk,xh,xm,sfzh,xymc,zymc,bjmc,dkhth,dkje from view_xszz_zzlg_zxdk where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "sfzh", "xymc",
				"zymc", "bjmc", "dkhth", "dkje" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 助学贷款信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxdkResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String sql = "select count(*) num from view_xszz_zzlg_zxdk where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取助学贷款详细信息
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkXx(String guid) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select guid,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxfs,jtzz,yzbm,jtdh,dkhth,dkje,htdkffr,htdkdqr,hkfs,hkzh from view_xszz_zzlg_zxdk where guid = ?";
		String[] colList = new String[] { "guid", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "lxfs", "jtzz", "yzbm", "jtdh", "dkhth",
				"dkje", "htdkffr", "htdkdqr", "hkfs", "hkzh" };
		String[] sLen = dao.getOneRs(sql, new String[] { guid }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}
		return stuList;
	}
	
	/**
	 * 保存助学贷款，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public String[] saveZxdkxx(ZxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String guid = Base.chgNull(model.getGuid(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxfs = Base.chgNull(model.getLxfs(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String dkhth = Base.chgNull(model.getDkhth(), "", 1);
		String dkje = Base.chgNull(model.getDkje(), "", 1);
		String htdkffr = Base.chgNull(model.getHtdkffr(), "", 1);
		String htdkdqr = Base.chgNull(model.getHtdkdqr(), "", 1);
		String hkfs = Base.chgNull(model.getHkfs(), "", 1);
		String hkzh = Base.chgNull(model.getHkzh(), "", 1);

		if ("modi".equalsIgnoreCase(request.getParameter("act"))) {
			bFlag = StandardOperation.update("xszz_zzlg_zxdk", new String[] {
					"xh", "lxfs", "jtzz", "yzbm", "jtdh", "dkhth", "dkje",
					"htdkffr", "htdkdqr", "hkfs", "hkzh" }, new String[] { xh,
					lxfs, jtzz, yzbm, jtdh, dkhth, dkje, htdkffr, htdkdqr,
					hkfs, hkzh }, "guid", guid, request);
		} else {
			bFlag = StandardOperation.insert("xszz_zzlg_zxdk", new String[] {
					"xh", "lxfs", "jtzz", "yzbm", "jtdh", "dkhth", "dkje",
					"htdkffr", "htdkdqr", "hkfs", "hkzh" }, new String[] { xh,
					lxfs, jtzz, yzbm, jtdh, dkhth, dkje, htdkffr, htdkdqr,
					hkfs, hkzh }, request);
			guid = dao
					.getOneRs(
							"select guid from xszz_zzlg_zxdk where xh=? and lxfs=? and jtzz=? and yzbm=? and jtdh=? and dkhth=? and dkje=? and htdkffr=? and htdkdqr=? and hkfs=? and hkzh=? and rownum=1",
							new String[] { xh, lxfs, jtzz, yzbm, jtdh, dkhth,
									dkje, htdkffr, htdkdqr, hkfs, hkzh },
							"guid");
		}
		
		return new String[] { bFlag ? "1" : "0", Base.chgNull(guid, "", 1) };
	}
}
