package xgxt.xszz.commonN05;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

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
public class XszzCommonN05DAO {
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
			HttpServletRequest request,boolean fdy) throws Exception {
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
		String xq = queryModel.getXq();
		String xh = queryModel.getXh();
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xm = queryModel.getXm();
		String xb = queryModel.getXb();
		String sfzh = queryModel.getSfzh();
		String fdysh = queryModel.getFdysh();
		String xysh = queryModel.getXysh();
		String xxsh = queryModel.getXxsh();
		String zxjdj = queryModel.getZxjdj();
		String xmb = queryModel.getXmb();
		String zxjdm = queryModel.getXmdm();
		String txsj1 = queryModel.getTxsj1();
		String txsj2 = queryModel.getTxsj2();
		String qxfqj = queryModel.getQxfqj();

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
		if (fdy) {
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
		} else {
			if (!StringUtils.isNull(bjdm)) {
				whereSql.append(" and bjdm = ?");
				values.add(bjdm);
			}// end if
		}
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
		if (!StringUtils.isNull(fdysh)) {
			if ("非困难生3".equalsIgnoreCase(fdysh)) {
				whereSql.append(" and fdysh in ('未审核','不困难')");
			} else if ("困难生3".equalsIgnoreCase(fdysh)) {
				whereSql.append(" and fdysh in ('一般困难','困难','特殊困难')");
			} else {
				whereSql.append(" and fdysh = ?");
				values.add(fdysh);
			}
		}// end if
		if (!StringUtils.isNull(xysh)) {
			if ("非困难生3".equalsIgnoreCase(xysh)) {
				whereSql.append(" and xysh in ('未审核','不困难')");
			} else if ("困难生3".equalsIgnoreCase(xysh)) {
				whereSql.append(" and xysh in ('一般困难','困难','特殊困难')");
			} else {
				whereSql.append(" and xysh = ?");
				values.add(xysh);
			}
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			if ("非困难生3".equalsIgnoreCase(xxsh)) {
				whereSql.append(" and xxsh in ('未审核','不困难')");
			} else if ("困难生3".equalsIgnoreCase(xxsh)) {
				whereSql.append(" and xxsh in ('一般困难','困难','特殊困难')");
			} else {
				whereSql.append(" and xxsh = ?");
				values.add(xxsh);
			}
		}// end if
		if (!StringUtils.isNull(zxjdj)) {
			whereSql.append(" and zxjdj = ?");
			values.add(zxjdj);
		}// end if
		if (!StringUtils.isNull(xmb)) {
			whereSql.append(" and xmb = ?");
			values.add(xmb);
		}// end if
		if (!StringUtils.isNull(zxjdm)) {
			whereSql.append(" and zxjdm = ?");
			values.add(zxjdm);
		}// end if
		if (!StringUtils.isNull(txsj1)) {
			whereSql.append(" and txsj>=? ");
			values.add(txsj1);
		}// end if
		if (!StringUtils.isNull(txsj2)) {
			whereSql.append(" and txsj<=? ");
			values.add(txsj2);
		}// end if
		if (!StringUtils.isNull(qxfqj)) {
			whereSql.append(" and qxfqj=? ");
			values.add(qxfqj);
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
			String tabName, HttpServletRequest request,boolean fdy) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();

		String sql = "select * from " + tabName + " where 1=1 ";
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel, request,fdy);
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
	 * 获取xszz_com_xfjm1减免次数
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1cs(String xh) throws Exception {
		String sql = "select count(*) num from xszz_com_xfjm1 where xh=? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		return num;
	}
	
	/**
	 * 获取学生xszz_com_knsrdb1困难等级
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getKns1Rdqk(String xh, String xn) throws Exception {
		String sql = "select (case xxsh when '未审核' then '不困难' else xxsh end) xxsh from xszz_com_knsrdb1 where xh=? and xn=?";
		String xxsh = dao.getOneRs(sql, new String[] { xh, xn }, "xxsh");
		return Base.chgNull(xxsh, "不困难", 0);
	}
	
	/**
	 * 得到项目审核级别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getShjb(String xmb) throws Exception {
		return dao.getOneRs("select shjb from xszz_n05_shjb where xmb=?",
				new String[] { xmb }, "shjb");
	}
	
	/**
	 * 获取项目是否必须困难生才能申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSfkns(String xmb) throws Exception {
		return dao.getOneRs("select sfkns from xszz_n05_shjb where xmb=?",
				new String[] { xmb }, "sfkns");
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
		//--------------------------2010/11/29 edit by lr 广州大学所有用户都要有时间控制-----------------
		if ((StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) || (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm) && StringUtils.isNotNull(xh))) {
			String[] jxjksjssj = null;

			//----------------2010/6/21 edit by luojw-----------------------------
			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc like '%" + zzType
					+ "%' and b.xh=? ";
			//----------------end-----------------------------
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
	 * 项目设置查询结果 Xmszres ---- 项目设置结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXmszRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("xgxt.base.ApplicationResources",Locale.CHINA);
		List<String[]> resList = new ArrayList<String[]>();
		String xy = bundle.getString("lable.xsgzyxpzxy");
		String xx = bundle.getString("lable.xsgzyxpzxx");

		String sql = "select xmb pk,xmmc,(case shjb when '2' then '二级(" + xy + "、" + xx + "审核)' when '3' then '三级(辅导员、" + xy + "、" + xx + "审核)' else shjb end) shjb,(case sfkns when '0' then '否' when '1' then '是' else sfkns end) sfkns from xszz_n05_shjb where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String[] colList = new String[] { "pk", "xmmc", "shjb", "sfkns" };

		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 获得项目列表
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXmList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String sql = "select xmb dm,xmmc mc from xszz_n05_shjb";
		list.addAll(dao.getArrayList(sql, new String[] {}, new String[] { "dm",
				"mc" }));
		return list;
	}

	/**
	 * 获取项目设置信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXmszxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xmb,xmmc,shjb,sfkns from xszz_n05_shjb where xmb = ?";
		String[] colList = new String[] { "xmb", "xmmc", "shjb", "sfkns" };
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
	 * 删除外设助学金项目信息
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delWszxjxm(String[] pk,HttpServletRequest request){
		String[] sqlT = new String[pk.length*3];
		int j = 0;
		for (int i = 0; i < pk.length; i++) {
			sqlT[j] = "delete xszz_com_wszxjdmb where xmdm='" + pk[i] + "'";
			j++;
			sqlT[j] = "delete xszz_com_wszxjxmxxb where xmdm='" + pk[i] + "'";
			j++;
			sqlT[j] = "delete xszz_n05_shjb where xmb='view_xszz_com_wszxj1" + pk[i] + "'";
			j++;
		}
		try {
			dao.runBatch(sqlT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 外设助学金项目信息结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxjxmRes(QueryModel queryModel,HttpServletRequest request,XszzCommonN05ActionForm actionForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";

		String[] colList = new String[] { "pk", "xmdm", "xmmc" };

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		sql = "select zxjdm pk,zxjdm xmdm,xmmc from (select xmdm zxjdm,xmmc from xszz_com_wszxjdmb) where 1=1";
		resList = dao.rsToVator(sql+whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 获取外设助学金项目详细信息
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxjxmByXmdm(String xmdm) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xmdm,xmmc from xszz_com_wszxjdmb where xmdm = ?";
		String[] colList = new String[] { "xmdm", "xmmc" };
		String[] sLen = dao.getOneRs(sql, new String[] { xmdm }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}
		return stuList;
	}
	
	/**
	 * 外设助学金项目金额记录结果
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWszxjXmjeList(String xmdm) {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>();
		String sql = "";

		String[] colList = new String[] { "hh", "pk", "xmje" ,"zxjmc"};

		sql = "select rownum hh,xmdm||zxjmc pk,zxjmc,xmje from xszz_com_wszxjxmxxb where xmdm=?";
		resList = dao.getList(sql, new String[] { xmdm }, colList);
		return resList;
	}
	
	/**
	 * 保存外设助学金项目信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjxm(String xmdm, String xmmc,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String act = Base.chgNull(request.getParameter("act"), "", 1);

		if ("mod".equalsIgnoreCase(act)) {
			bFlag = StandardOperation.update("xszz_com_wszxjdmb",
					new String[] { "xmmc" }, new String[] { xmmc }, "xmdm",
					xmdm, request);
		} else {
			String num = dao.getOneRs(
					"select count(*) num from xszz_com_wszxjdmb where xmdm=?",
					new String[] { xmdm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				bFlag = StandardOperation.insert("xszz_com_wszxjdmb",
						new String[] { "xmdm", "xmmc" }, new String[] { xmdm,
								xmmc }, request);
				StandardOperation.insert("xszz_n05_shjb",
						new String[] { "xmb", "xmmc" }, new String[] { "view_xszz_com_wszxj1"+xmdm,
								xmmc }, request);
			} else {
				request.setAttribute("have", "have");
			}
		}
		return bFlag;
	}
	
	/**
	 * 保存外设助学金金额记录
	 * @param param
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxjje(List<HashMap<String, String>> param, String xmdm)
			throws Exception {
		int length = param.size() + 1;
		String[] sqlT = new String[length];
		boolean doFlag = false;
		sqlT[0] = "delete xszz_com_wszxjxmxxb where xmdm='" + xmdm + "'";
		for (int i = 1; i < length; i++) {
			String xmje = Base.chgNull(param.get(i - 1).get("xmje"), "", 0);
			String zxjmc = param.get(i-1).get("zxjmc");
			sqlT[i] = "insert into xszz_com_wszxjxmxxb(xmdm,zxjmc,xmje) values('"
					+ xmdm
					+ "','"
					+zxjmc
					+ "','"
					+ xmje
					+ "')";
		}
		int[] array = null;
		array = dao.runBatch(sqlT);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}
	
	/**
	 * 获取家庭情况调查1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdc1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xm,xb,mzmc,zzmmmc,csrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtdz,yzbm,xszxlxdh,jtlxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gz,jtcy1_sr,jtcy1_zk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gz,jtcy2_sr,jtcy2_zk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gz,jtcy3_sr,jtcy3_zk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gz,jtcy4_sr,jtcy4_zk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gz,jtcy5_sr,jtcy5_zk,jtrjnsr,jtrjysr,bxnyhzzqk,sfjxf,yjxf,sfjzsf,yjzsf,jttgshf,zxxxf,jtcszrzhqk,jtcstfywqk,jtcyycjnmrndnlrqk,jtcysyqk,jtqzqk,sfdbh,qtqk,mzbm_xxtxdz,mzbm_yzbm,jbrxm,jbrbgdh_qx,jbrbgdh_dh,ddzdshbz,txsj from view_xszz_com_jtqkdc1 where xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "mzmc", "zzmmmc",
				"csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "rxqhk", "jtrks", "byxx", "grtc", "sfgc",
				"sfdq", "sflszn", "jtdz", "yzbm", "xszxlxdh", "jtlxdh",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr",
				"jtcy1_zk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz",
				"jtcy2_sr", "jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gz", "jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk",
				"jtrjnsr", "jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf", "sfjzsf",
				"yjzsf", "jttgshf", "zxxxf", "jtcszrzhqk", "jtcstfywqk",
				"jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk", "sfdbh", "qtqk",
				"mzbm_xxtxdz", "mzbm_yzbm", "jbrxm", "jbrbgdh_qx",
				"jbrbgdh_dh", "ddzdshbz", "txsj" };
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
	 * 判断家庭情况调查1是否重复，重复的返回1，没有重复的返回-1 isjtqkdc1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isJtqkdc1DataCf(String xh) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_jtqkdc1 where xh = ?";
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}
	
	/**
	 * 保存家庭情况调查1信息，成功返回TRUE，反之返回FALSE saveJtqkdc1Sqxx ---- 保存家庭情况调查1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdc1Sqxx(Jtqkdc1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(model.getXh(), "", 1);
		String rxqhk = Base.chgNull(model.getRxqhk(), "", 1);
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);
		String byxx = Base.chgNull(model.getByxx(), "", 1);
		String grtc = Base.chgNull(model.getGrtc(), "", 1);
		String sfgc = Base.chgNull(model.getSfgc(), "", 1);
		String sfdq = Base.chgNull(model.getSfdq(), "", 1);
		String sflszn = Base.chgNull(model.getSflszn(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String xszxlxdh = Base.chgNull(model.getXszxlxdh(), "", 1);
		String jtlxdh = Base.chgNull(model.getJtlxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy1_zk = Base.chgNull(model.getJtcy1_zk(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy2_zk = Base.chgNull(model.getJtcy2_zk(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy3_zk = Base.chgNull(model.getJtcy3_zk(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy4_zk = Base.chgNull(model.getJtcy4_zk(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String jtcy5_zk = Base.chgNull(model.getJtcy5_zk(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String bxnyhzzqk = Base.chgNull(model.getBxnyhzzqk(), "", 1);
		String sfjxf = Base.chgNull(model.getSfjxf(), "", 1);
		String yjxf = Base.chgNull(model.getYjxf(), "", 1);
		String sfjzsf = Base.chgNull(model.getSfjzsf(), "", 1);
		String yjzsf = Base.chgNull(model.getYjzsf(), "", 1);
		String jttgshf = Base.chgNull(model.getJttgshf(), "", 1);
		String zxxxf = Base.chgNull(model.getZxxxf(), "", 1);
		String jtcszrzhqk = Base.chgNull(model.getJtcszrzhqk(), "", 1);
		String jtcstfywqk = Base.chgNull(model.getJtcstfywqk(), "", 1);
		String jtcyycjnmrndnlrqk = Base.chgNull(model.getJtcyycjnmrndnlrqk(),
				"", 1);
		String jtcysyqk = Base.chgNull(model.getJtcysyqk(), "", 1);
		String jtqzqk = Base.chgNull(model.getJtqzqk(), "", 1);
		String sfdbh = Base.chgNull(model.getSfdbh(), "", 1);
		String qtqk = Base.chgNull(model.getQtqk(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(model.getMzbm_xxtxdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(model.getMzbm_yzbm(), "", 1);
		String jbrxm = Base.chgNull(model.getJbrxm(), "", 1);
		String jbrbgdh_qx = Base.chgNull(model.getJbrbgdh_qx(), "", 1);
		String jbrbgdh_dh = Base.chgNull(model.getJbrbgdh_dh(), "", 1);
		String ddzdshbz = Base.chgNull(model.getDdzdshbz(), "", 1);
		String txsj = GetTime.getSystemTime();

		String sHave = isJtqkdc1DataCf(xh);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_jtqkdc1", new String[] {
					"xh", "rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq",
					"sflszn", "jtdz", "yzbm", "xszxlxdh", "jtlxdh", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr", "jtcy1_zk",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz", "jtcy2_sr",
					"jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gz",
					"jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk", "jtrjnsr",
					"jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf", "sfjzsf", "yjzsf",
					"jttgshf", "zxxxf", "jtcszrzhqk", "jtcstfywqk",
					"jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk", "sfdbh", "qtqk",
					"mzbm_xxtxdz", "mzbm_yzbm", "jbrxm", "jbrbgdh_qx",
					"jbrbgdh_dh", "ddzdshbz", "txsj" }, new String[] { xh,
					rxqhk, jtrks, byxx, grtc, sfgc, sfdq, sflszn, jtdz, yzbm,
					xszxlxdh, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gz,
					jtcy1_sr, jtcy1_zk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gz,
					jtcy2_sr, jtcy2_zk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gz,
					jtcy3_sr, jtcy3_zk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gz,
					jtcy4_sr, jtcy4_zk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gz,
					jtcy5_sr, jtcy5_zk, jtrjnsr, jtrjysr, bxnyhzzqk, sfjxf,
					yjxf, sfjzsf, yjzsf, jttgshf, zxxxf, jtcszrzhqk,
					jtcstfywqk, jtcyycjnmrndnlrqk, jtcysyqk, jtqzqk, sfdbh,
					qtqk, mzbm_xxtxdz, mzbm_yzbm, jbrxm, jbrbgdh_qx,
					jbrbgdh_dh, ddzdshbz, txsj }, request);
		} else {
			bFlag = StandardOperation.update("xszz_com_jtqkdc1", new String[] {
					"rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
					"jtdz", "yzbm", "xszxlxdh", "jtlxdh", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr", "jtcy1_zk",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz", "jtcy2_sr",
					"jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gz",
					"jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk", "jtrjnsr",
					"jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf", "sfjzsf", "yjzsf",
					"jttgshf", "zxxxf", "jtcszrzhqk", "jtcstfywqk",
					"jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk", "sfdbh", "qtqk",
					"mzbm_xxtxdz", "mzbm_yzbm", "jbrxm", "jbrbgdh_qx",
					"jbrbgdh_dh", "ddzdshbz", "txsj" }, new String[] { rxqhk,
					jtrks, byxx, grtc, sfgc, sfdq, sflszn, jtdz, yzbm,
					xszxlxdh, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gz,
					jtcy1_sr, jtcy1_zk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gz,
					jtcy2_sr, jtcy2_zk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gz,
					jtcy3_sr, jtcy3_zk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gz,
					jtcy4_sr, jtcy4_zk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gz,
					jtcy5_sr, jtcy5_zk, jtrjnsr, jtrjysr, bxnyhzzqk, sfjxf,
					yjxf, sfjzsf, yjzsf, jttgshf, zxxxf, jtcszrzhqk,
					jtcstfywqk, jtcyycjnmrndnlrqk, jtcysyqk, jtqzqk, sfdbh,
					qtqk, mzbm_xxtxdz, mzbm_yzbm, jbrxm, jbrbgdh_qx,
					jbrbgdh_dh, ddzdshbz, txsj }, "xh", xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除家庭情况调查1信息 delKnsrd1xx ---- 删除家庭情况调查1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdc1xx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_com_jtqkdc1 where xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 家庭情况调查1查询结果 getJtqkdc1Res ---- 家庭情况调查1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdc1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,xh,xm,xb,sfzh,xymc,zymc,bjmc,txsj from (select rownum r,xh pk,xh,xm,xb,sfzh,xymc,zymc,bjmc,txsj from view_xszz_com_jtqkdc1 where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "txsj" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 家庭情况调查1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getJtqkdc1ResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String sql = "select count(*) num from view_xszz_com_jtqkdc1 where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 获取困难生认定1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,xz,nj,sfzh,xydm,xymc," +
				     "zydm,zymc,bjdm,bjmc,jtrjnsr,zxlxdh,sqly,sqsj,fdysh,fdyshsj," +
				     "fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj," +
				     "(select tjzt from xszz_com_bmshtjb b where b.bm='bj' and b.zjz=a.xn" +
				     " and b.dm=a.bjdm and b.tjxm='knsrd1') tjzt from view_xszz_com_knsrdb1 a where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrjnsr", "zxlxdh", "sqly", "sqsj",
				"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
				"xxsh", "xxshsj", "xxshyj", "tjzt"};
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
	 * 获取困难生认定1意见信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1yjxx(String xh) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,yj,txsj from xszz_com_knsrd1_gsyj where xh=?";
		String[] colList = new String[] { "xh", "yj", "txsj" };
		String[] sLen = dao.getOneRs(sql, new String[] { xh }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if

		return stuList;
	}
	
	/**
	 * 判断困难生认定1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsrd1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnsrd1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xxsh in ('一般困难','困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xysh in ('一般困难','困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断困难生认定1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isKnsrd1DataCfSq ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnsrd1DataCfSq(String xh, String xn,String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xxsh in ('一般困难','困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xysh in ('一般困难','困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){//三级审核
					sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and fdysh in ('一般困难','困难','特殊困难','')";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if(!"0".equalsIgnoreCase(num)){
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存困难生认定1信息，成功返回TRUE，反之返回FALSE saveKnsrd1Sqxx ---- 保存困难生认定1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Sqxx(Knsrdb1Model model, HttpServletRequest request,
			String shjb)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(model.getZxlxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isKnsrd1DataCfSq(xh, xn,shjb);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knsrdb1", new String[] {
					"xn", "xh", "jtrjnsr", "zxlxdh", "sqly" }, new String[] {
					xn, xh, jtrjnsr, zxlxdh, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"jtrjnsr", "zxlxdh", "sqly", "fdysh", "fdyshsj", "fdyshyj",
					"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj",
					"sqsj" }, new String[] { jtrjnsr, zxlxdh, sqly, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now }, "xn||xh", xn + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 保存困难生认定1意见信息，成功返回TRUE，反之返回FALSE saveKnsrd1Yj ---- 保存困难生认定1意见信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Yj(String yj, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;

		HttpSession session = request.getSession();

		String xh = session.getAttribute("userName").toString();
		String now = GetTime.getSystemTime();

		String num = dao.getOneRs(
				"select count(*) num from xszz_com_knsrd1_gsyj where xh=?",
				new String[] { xh }, "num");
		if ("0".equalsIgnoreCase(num)) {
			bFlag = StandardOperation.insert("xszz_com_knsrd1_gsyj",
					new String[] { "xh", "yj", "txsj" }, new String[] { xh, yj,
							now }, request);
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrd1_gsyj",
					new String[] { "yj", "txsj" }, new String[] { yj, now },
					"xh", xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除困难生认定1信息 delKnsrd1xx ---- 删除困难生认定1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd1xx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i] + "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xysh not in ('一般困难','困难','特殊困难')";
				} else {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xxsh not in ('一般困难','困难','特殊困难')";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定1审核结果 modKnsrd1xx ---- 批量修改困难生认定1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knsrdb1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_knsrdb1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_knsrdb1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难生认定1查询结果 Knsrd1res ---- 困难生认定1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where fdysh in ('一般困难','困难','特殊困难')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where xysh in ('一般困难','困难','特殊困难')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定1查询结果 Knsrd1res ---- 困难生认定1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjxm='knsrd1')='1' then 'disabled' else '' end) disabled,";

		sql += "(case when nvl(fdysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 a where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd1ResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_knsrdb1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh in ('一般困难','困难','特殊困难')";
				}
			} else {
				sql += " and xysh in ('一般困难','困难','特殊困难')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存困难生认定1审核信息，成功返回TRUE，反之返回FALSE saveKnsrd1Shxx ---- 保存困难生认定1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd1Shxx(Knsrdb1Model model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isKnsrd1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_knsrdb1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_knsrdb1",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj" }, new String[] { fdyshyj, xysh,
									xyshyj, now }, "xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取困难生认定1学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,fdysh,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where xn||bjdm=? and fdysh<>'未审核' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='knsrd1' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,fdysh,xyshjg ,xxshjg from view_xszz_com_knsrdb1 a where xn||bjdm=?";
			}			
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,fdysh,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where xn||xydm=? and xyshjg='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='knsrd1' and a.xydm=b.dm)";
		}
		colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "fdysh", "xyshjg", "xxshjg"};
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取困难生认定1审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd1shtjjg(String pkVal,String userType,String shjb) throws Exception {
		String bm = userType.equalsIgnoreCase("xy") ? "bj" : "xy";
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_knsrdb1 a where xn||bjdm=? and fdysh<>'未审核' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='knsrd1' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_knsrdb1 a where xn||bjdm=?";
			}			
		}else{
			sql = "select xxshyj shyj from view_xszz_com_knsrdb1 a where xn||xydm=? and xyshjg='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='knsrd1' and a.xydm=b.dm)";
		}
		return dao.getMap(sql, new String[]{pkVal}, new String[]{"shyj"});
	}

	/**
	 * 批量修改困难生认定1审核结果 modKnsrd1xxBybj ---- 批量修改困难生认定1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xxBybj(XszzCommonN05ActionForm model,HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String shjb = model.getShjb();
		String[] sqlT = new String[pkT.length];
       
		shyj = Base.isNull(shyj) ? "" : shyj;
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("通过".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_knsrdb1 a set xxshjg='"
							+ shjg
							+ "',xxsh=xysh,xxshsj=to_char(sysdate,'yyyy-mm-dd'),xxshyj='"
							+ shyj 
							+ "' where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='通过'";
				}else if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_knsrdb1 a set xxshjg='未审核',xxsh='未审核',xxshsj='',xxshyj='' where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='通过'";
				}else{
					sqlT[i] = "update xszz_com_knsrdb1 a set xxshjg='"
						+ shjg
						+ "',xxsh='',xxshsj=to_char(sysdate,'yyyy-mm-dd'),xxshyj='"
						+ shyj
						+ "' where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='通过'";
				}
			} else {//学院审核
				if("通过".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_knsrdb1 a set xyshjg='"
							+ shjg
							+ "',xyshyj='"
							+ shyj 
							+"',xysh=fdysh,xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh<>'未审核'";
					}
				}else if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_knsrdb1 a set xyshjg='未审核',xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh<>'未审核'";
				}else{
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_knsrdb1 a set xyshjg='"
							+ shjg
							+ "',xyshyj='"
							+ shyj 
							+ "',xysh='',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh<>'未审核'";
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定1审核结果 modKnsrd1Bmshtjb ---- 批量修改困难生认定1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1Bmshtjb(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String tjr = model.getUserName();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核
					delT[i] = "delete from xszz_com_bmshtjb a where " 
						      + "exists(" 
						      + "select 1 from view_xszz_com_knsrdb1 b where " 
						      + "a.zjz=b.xn and a.dm=b.xydm and b.xn||b.xydm='" 
						      + pkT[i] 
						      + "') and a.bm='xy' and a.tjxm='knsrd1'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)" 
						      + "(select distinct xydm,'xy','xn',xn,'knsrd1','" 
						      + tjzt 
						      + "','" 
						      + shyj 
						      + "','" 
						      + tjr
						      + "' from view_xszz_com_knsrdb1 where xn||xydm='" 
						      + pkT[i] 
						      + "')";
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where " 
						      + "exists(select 1 from view_xszz_com_knsrdb1 b where " 
						      +	"a.zjz=b.xn and a.dm=b.bjdm and b.xn||b.bjdm='" 
						      + pkT[i] 
						      + "') and a.bm='bj' and a.tjxm='knsrd1'";
					sqlT[i] = "insert into xszz_com_bmshtjb" 
						      + "(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)" 
						      + "(select distinct bjdm,'bj','xn',xn,'knsrd1','" 
						      + tjzt 
						      + "','" 
						      + shyj 
						      + "','" 
						      + tjr 
						      + "' from view_xszz_com_knsrdb1 where xn||bjdm='" 
						      + pkT[i] 
						      + "')";
				}
			}
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	/**
	 * 提交学院审核结果
	 * @param String userDep
	 * @param String tjxm
	 * @param String zj
	 * @param String zjz
	 * @return boolean
	 * */
	public boolean modXytjshjg(String userDep,String userName,String tjxm,String zj,String zjz){
		boolean result = true;
		try{
			dao.runUpdate("delete from xszz_com_bmshtjb where dm=? and bm='xy' and zjz=? and tjxm=?", new String[]{userDep,zjz,tjxm});
			dao.insert("insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,tjr)values(?,?,?,?,?,?,?)", new String[]{userDep,"xy",zj,zjz,tjxm,"1",userName});
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 获取学院提交状态
	 * @param String userDep
	 * @param String zjz
	 * @param String tjxm
	 * @return String
	 * */
	public String selectXyshjg(String userDep,String zjz, String tjxm){
		String tjzt = "";
		String sql = "select decode(tjzt,'1','提交','0','未提交','2','退回') tjzt from xszz_com_bmshtjb where dm=? and zjz=? and tjxm=? and bm='xy'";
		tjzt = dao.getOneRs(sql, new String[]{userDep, zjz, tjxm}, "tjzt");
		tjzt = Base.isNull(tjzt) ? "未提交" : tjzt;
		return tjzt;
	}
	
	/**
	 * 批量修改困难生认定1审核结果 modKnsrd1xxByDepModel ---- 批量修改困难生认定1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd1xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {		
		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update xszz_com_knsrdb1 set fdysh='"
				+ shjg
				+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
				+ pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}


	/**
	 * 困难生认定1查询结果 getKnsrd1ResForDM ---- 困难生认定1结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", 
				           "bjmc", "rs", "tjr", "tjsj", "xyshjg", "xxshjg"};
	
		sql = "select disabled,bgcolor,pk,xn,bjmc,xyshjg,xxshjg,rs,tjr,tjsj from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn and b.tjxm='knsrd1') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xymc,xxshjg,rs," 
				+ "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn and b.tjxm='knsrd1')tjr," 
				+ "(select tjsj from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn and b.tjxm='knsrd1')tjsj from (select rownum r,'' disabled,";
		}

		
		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xyshjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					   + "xn||bjdm pk,xn,bjmc,xyshjg,xxshjg,xydm,xymc,zydm,zymc,bjdm,nj,rs," 
					   + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and b.zjz=a.xn and b.tjxm='knsrd1')tjr," 
					   + "(select tjsj from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and b.zjz=a.xn and b.tjxm='knsrd1')tjsj from (" 
					   + "select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xyshjg,xxshjg," 
					   + "(select count(*) from view_xszz_com_knsrdb1 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh<>'未审核') rs " 
					   + "from view_xszz_com_knsrdb1 a where fdysh<>'未审核' and " 
					   + "exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjzt<>'2' and b.tjxm='knsrd1')" 
					   + ") a where 1=1";
			}else{
				sql += "(case when nvl(xyshjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					   + "xn||bjdm pk,xn,bjmc,xyshjg,xxshjg,xydm,xymc,zydm,zymc,bjdm,nj,rs from (" 
					   + "select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xyshjg,xxshjg," 
					   + "(select count(*) from view_xszz_com_knsrdb1 b where a.xn=b.xn and a.bjdm=b.bjdm)rs " 
					   + "from view_xszz_com_knsrdb1 a ) a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc", "rs", "tjr", "tjsj", "xxshjg" };
			sql += "(case when nvl(xxshjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
				+ "xn||xydm pk,xn,xydm,xymc,xxshjg,rs from " 
				+ "(select distinct xn,xydm,xymc,xyshjg,xxshjg," 
				+ "(select count(*) from view_xszz_com_knsrdb1 b where a.xn=b.xn and a.xydm=b.xydm and b.xyshjg='通过' )rs " 
				+ "from view_xszz_com_knsrdb1 a where xyshjg='通过' and " 
				+ "exists(select 1 from xszz_com_bmshtjb b where a.xydm=b.dm " 
				+ "and b.bm='xy' and a.xn=b.zjz and b.tjzt='1' and tjxm='knsrd1')" 
				+ ")a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ") a)", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		
		sql += whereSql + ") a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 获取困难生认定3详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select t.* from view_xszz_com_knsrdb3 t where t.xn||t.xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "rxqhk", "jtrks", "byxx", "grtc",
				"sfgc", "sfdq", "sflszn", "jtdz", "yzbm", "xszxlxdh", "jtlxdh",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr",
				"jtcy1_zk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz",
				"jtcy2_sr", "jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_gz", "jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk",
				"jtrjnsr", "jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf", "sfjzsf",
				"yjzsf", "jttgshf", "zxxxf", "jtcszrzhqk", "jtcstfywqk",
				"jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk", "sfdbh", "qtqk",
				"sqsj", "fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj",
				"xyshyj", "xxsh", "xxshsj", "xxshyj", "fdyshr", "xyshr", "scdz" };
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
	 * 获取困难生认定3意见信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd3yjxx(String xh) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,yj,txsj from xszz_com_knsrd3_gsyj where xh=?";
		String[] colList = new String[] { "xh", "yj", "txsj" };
		String[] sLen = dao.getOneRs(sql, new String[] { xh }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if

		return stuList;
	}
	
	/**
	 * 判断困难生认定3是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsrd3datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnsrd3DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knsrdb3 where xh = ? and xn = ? and xxsh in ('一般困难','困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knsrdb3 where xh = ? and xn = ? and xysh in ('一般困难','困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_knsrdb3 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存困难生认定3信息，成功返回TRUE，反之返回FALSE saveKnsrd3Sqxx ---- 保存困难生认定3申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Sqxx(Knsrdb3Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String rxqhk = Base.chgNull(model.getRxqhk(), "", 1);
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);
		String byxx = Base.chgNull(model.getByxx(), "", 1);
		String grtc = Base.chgNull(model.getGrtc(), "", 1);
		String sfgc = Base.chgNull(model.getSfgc(), "", 1);
		String sfdq = Base.chgNull(model.getSfdq(), "", 1);
		String sflszn = Base.chgNull(model.getSflszn(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String xszxlxdh = Base.chgNull(model.getXszxlxdh(), "", 1);
		String jtlxdh = Base.chgNull(model.getJtlxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy1_zk = Base.chgNull(model.getJtcy1_zk(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy2_zk = Base.chgNull(model.getJtcy2_zk(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy3_zk = Base.chgNull(model.getJtcy3_zk(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy4_zk = Base.chgNull(model.getJtcy4_zk(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String jtcy5_zk = Base.chgNull(model.getJtcy5_zk(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String bxnyhzzqk = Base.chgNull(model.getBxnyhzzqk(), "", 1);
		String sfjxf = Base.chgNull(model.getSfjxf(), "", 1);
		String yjxf = Base.chgNull(model.getYjxf(), "", 1);
		String sfjzsf = Base.chgNull(model.getSfjzsf(), "", 1);
		String yjzsf = Base.chgNull(model.getYjzsf(), "", 1);
		String jttgshf = Base.chgNull(model.getJttgshf(), "", 1);
		String zxxxf = Base.chgNull(model.getZxxxf(), "", 1);
		String jtcszrzhqk = Base.chgNull(model.getJtcszrzhqk(), "", 1);
		String jtcstfywqk = Base.chgNull(model.getJtcstfywqk(), "", 1);
		String jtcyycjnmrndnlrqk = Base.chgNull(model.getJtcyycjnmrndnlrqk(),
				"", 1);
		String jtcysyqk = Base.chgNull(model.getJtcysyqk(), "", 1);
		String jtqzqk = Base.chgNull(model.getJtqzqk(), "", 1);
		String sfdbh = Base.chgNull(model.getSfdbh(), "", 1);
		String qtqk = Base.chgNull(model.getQtqk(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isKnsrd3DataCf(xh, xn);

		String scdz = model.getScdz();// 上传地址

		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knsrdb3", new String[] {
					"xn", "xh", "rxqhk", "jtrks", "byxx", "grtc", "sfgc",
					"sfdq", "sflszn", "jtdz", "yzbm", "xszxlxdh", "jtlxdh",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr",
					"jtcy1_zk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz",
					"jtcy2_sr", "jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
					"jtcy3_gz", "jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_gx", "jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk",
					"jtrjnsr", "jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf",
					"sfjzsf", "yjzsf", "jttgshf", "zxxxf", "jtcszrzhqk",
					"jtcstfywqk", "jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk",
					"sfdbh", "qtqk", "scdz" }, new String[] { xn, xh, rxqhk,
					jtrks, byxx, grtc, sfgc, sfdq, sflszn, jtdz, yzbm,
					xszxlxdh, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gz,
					jtcy1_sr, jtcy1_zk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gz,
					jtcy2_sr, jtcy2_zk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gz,
					jtcy3_sr, jtcy3_zk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gz,
					jtcy4_sr, jtcy4_zk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gz,
					jtcy5_sr, jtcy5_zk, jtrjnsr, jtrjysr, bxnyhzzqk, sfjxf,
					yjxf, sfjzsf, yjzsf, jttgshf, zxxxf, jtcszrzhqk,
					jtcstfywqk, jtcyycjnmrndnlrqk, jtcysyqk, jtqzqk, sfdbh,
					qtqk, scdz }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrdb3", new String[] {
					"rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
					"jtdz", "yzbm", "xszxlxdh", "jtlxdh", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gz", "jtcy1_sr", "jtcy1_zk",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gz", "jtcy2_sr",
					"jtcy2_zk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gz",
					"jtcy3_sr", "jtcy3_zk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gz", "jtcy4_sr", "jtcy4_zk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gz", "jtcy5_sr", "jtcy5_zk", "jtrjnsr",
					"jtrjysr", "bxnyhzzqk", "sfjxf", "yjxf", "sfjzsf", "yjzsf",
					"jttgshf", "zxxxf", "jtcszrzhqk", "jtcstfywqk",
					"jtcyycjnmrndnlrqk", "jtcysyqk", "jtqzqk", "sfdbh", "qtqk",
					"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
					"xxsh", "xxshsj", "xxshyj", "sqsj", "scdz" }, new String[] {
					rxqhk, jtrks, byxx, grtc, sfgc, sfdq, sflszn, jtdz, yzbm,
					xszxlxdh, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gz,
					jtcy1_sr, jtcy1_zk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gz,
					jtcy2_sr, jtcy2_zk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gz,
					jtcy3_sr, jtcy3_zk, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gz,
					jtcy4_sr, jtcy4_zk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gz,
					jtcy5_sr, jtcy5_zk, jtrjnsr, jtrjysr, bxnyhzzqk, sfjxf,
					yjxf, sfjzsf, yjzsf, jttgshf, zxxxf, jtcszrzhqk,
					jtcstfywqk, jtcyycjnmrndnlrqk, jtcysyqk, jtqzqk, sfdbh,
					qtqk, "未审核", "", "", "未审核", "", "", "未审核", "", "", now,
					scdz }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 保存困难生认定3意见信息，成功返回TRUE，反之返回FALSE saveKnsrd3Yj ---- 保存困难生认定3意见信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Yj(String yj, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;

		HttpSession session = request.getSession();

		String xh = session.getAttribute("userName").toString();
		String now = GetTime.getSystemTime();

		String num = dao.getOneRs(
				"select count(*) num from xszz_com_knsrd3_gsyj where xh=?",
				new String[] { xh }, "num");
		if ("0".equalsIgnoreCase(num)) {
			bFlag = StandardOperation.insert("xszz_com_knsrd3_gsyj",
					new String[] { "xh", "yj", "txsj" }, new String[] { xh, yj,
							now }, request);
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrd3_gsyj",
					new String[] { "yj", "txsj" }, new String[] { yj, now },
					"xh", xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除困难生认定3信息 delKnsrd3xx ---- 删除困难生认定3信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd3xx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knsrdb3 where xn||xh='" + pkT[i] + "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knsrdb3 where xn||xh='" + pkT[i]
							+ "' and xysh not in ('一般困难','困难','特殊困难')";
				} else {
					sqlT[i] = "delete xszz_com_knsrdb3 where xn||xh='" + pkT[i]
							+ "' and xxsh not in ('一般困难','困难','特殊困难')";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定3审核结果 modKnsrd3xx ---- 批量修改困难生认定3审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd3xx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knsrdb3 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_knsrdb3 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_knsrdb3 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量评定为困难生
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void plPdkns(String[] pkT, HttpServletRequest request)
			throws Exception {
		String xn = Base.currXn;
		String[] sqlT = new String[pkT.length*2];
		String now = GetTime.getSystemTime();
		int j = 0;
		for (int i = 0; i < pkT.length; i++) {
			String xh = "";
			xh = pkT[i].length()>9?pkT[i].substring(9):"";
			String num = dao
					.getOneRs(
							"select count(*) num from xszz_com_knsrdb3 where xn=? and xh=? and xxsh in ('一般困难','困难','特殊困难')",
							new String[] { xn, xh }, "num");
			if ("0".equalsIgnoreCase(num) && !"".equalsIgnoreCase(xh)) {
				sqlT[j] = "update xszz_com_knsrdb3 set xn='"
						+ xn
						+ "' ,sqsj='"
						+ now
						+ "',fdyshsj='"
						+ now
						+ "',xyshsj='"
						+ now
						+ "',xxshsj='"
						+ now + "'  where xn||xh='"+pkT[i]+"'";
				j++;
			}

		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难生认定3查询结果 Knsrd3res ---- 困难生认定3结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb3 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb3 where fdysh in ('一般困难','困难','特殊困难')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb3 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('一般困难','困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb3 where xysh in ('一般困难','困难','特殊困难')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定3公示结果 getKnsrd3gsRes ---- 困难生认定3公示结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3gsRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn,xh,xm,xb,xymc,zymc,bjmc,sqsj,xxsh from (select rownum r,xn,xh,xm,xb,xymc,zymc,bjmc,sqsj,xxsh from view_xszz_com_knsrdb3 where xxsh in ('一般困难','困难','特殊困难')";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "xymc",
				"zymc", "bjmc", "sqsj", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定3意见结果 getKnsrd3yjRes ---- 困难生认定3意见
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd3yjRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select yj,txsj from (select rownum r,yj,txsj from xszz_com_knsrd3_gsyj where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		sql += whereSql + " order by txsj desc) where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "yj", "txsj" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定3信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3ResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_knsrdb3 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh in ('一般困难','困难','特殊困难')";
				}
			} else {
				sql += " and xysh in ('一般困难','困难','特殊困难')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 困难生认定3公示结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3gsResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String sql = "select count(*) num from view_xszz_com_knsrdb3 where xxsh in ('一般困难','困难','特殊困难')";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 困难生认定3意见结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd3yjResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String sql = "select count(*) num from xszz_com_knsrd3_gsyj where 1=1";

		StringBuffer whereSql = getWhereSql(queryModel, request, false);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存困难生认定3审核信息，成功返回TRUE，反之返回FALSE saveKnsrd3Shxx ---- 保存困难生认定3审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd3Shxx(Knsrdb3Model model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String fdyshr = model.getFdyshr();
		String xyshr = model.getXyshr();
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isKnsrd3DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knsrdb3", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_knsrdb3",
								new String[] { "fdysh", "fdyshyj", "fdyshsj","fdyshr" },
								new String[] { fdysh, fdyshyj, now ,fdyshr}, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_knsrdb3",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj","xyshr" }, new String[] { fdyshyj, xysh,
									xyshyj, now,xyshr }, "xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取困难生认定2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2xx(String pkVal) throws Exception {
//		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrjnsr,zxlxdh,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,(select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjxm='knsrd2')tjzt from view_xszz_com_knsrdb1 a where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrjnsr", "zxlxdh", "sqly", "sqsj",
				"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
				"xxsh", "xxshsj", "xxshyj","tjzt" };
		return dao.getMap(sql, new String[] { pkVal }, colList);
	}
	
	/**
	 * 判断困难生认定2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsrd2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnsrd2DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xxsh in ('困难','特别困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xysh in ('困难','特别困难')";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存困难生认定2信息，成功返回TRUE，反之返回FALSE saveKnsrd2Sqxx ---- 保存困难生认定2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(model.getZxlxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isKnsrd2DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knsrdb1", new String[] {
					"xn", "xh", "jtrjnsr", "zxlxdh", "sqly" }, new String[] {
					xn, xh, jtrjnsr, zxlxdh, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"jtrjnsr", "zxlxdh", "sqly", "fdysh", "fdyshsj", "fdyshyj",
					"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj",
					"sqsj" }, new String[] { jtrjnsr, zxlxdh, sqly, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now }, "xn||xh", xn + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 删除困难生认定2信息 delKnsrd2xx ---- 删除困难生认定2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsrd2xx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i] + "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xysh not in ('困难','特别困难')";
				} else {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xxsh not in ('困难','特别困难')";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定2审核结果 modKnsrd2xx ---- 批量修改困难生认定2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				sqlT[i] = "update xszz_com_knsrdb1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (!isFdyqx) {//学院审核
					sqlT[i] = "update xszz_com_knsrdb1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {//辅导员审核
					sqlT[i] = "update xszz_com_knsrdb1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难生认定2查询结果 Knsrd2res ---- 困难生认定2结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			if (!isFdyqx) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (!isFdyqx) {
					sql += "(case when nvl(xysh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where fdysh in ('困难','特别困难')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where xysh in ('困难','特别困难')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定2查询结果 getKnsrd2ResByFdy ---- 困难生认定2结果（辅导员查询）
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjxm='knsrd2')='1' then 'disabled' else '' end) disabled,";

		sql += "(case when nvl(fdysh,'未审核') in ('困难','特别困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 a where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定2查询结果 Knsrd2res ---- 困难生认定2结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		if (userType.equalsIgnoreCase("xy")) {
			sql = "select disabled,bgcolor,pk,xn,bjmc,xyshjg from (select rownum r,";
			sql += "(case when xxshjg='通过' then 'disabled' else '' end) disabled,";
			
			colList = new String[] { "disabled", "bgcolor", "pk", "xn","bjmc","xyshjg"};
		} else {
			sql = "select disabled,bgcolor,pk,xn,zymc,xyshjg,xxshjg from (select rownum r,";
			sql += "'' disabled,";
			
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "zymc", "xxshjg"};
		}

		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case when nvl(xyshjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||bjdm pk,xn,bjmc,xyshjg,xxshjg from (select distinct xn,bjdm,bjmc,zydm,zymc,xydm,xymc,nj,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where fdysh in ('困难','特别困难','不困难') and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.dm=a.bjdm and b.bm='bj' and b.tjxm='knsrd2' and b.tjzt<>'2')) where 1=1 ";
		} else {
			sql += "(case when nvl(xxshjg,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zydm pk,xn,xymc,zymc,xyshjg,xxshjg from (select distinct xn,xydm,xymc,zydm,zymc,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where xyshjg='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and  b.dm=a.zydm and b.bm='zy' and b.tjxm='knsrd2' and b.tjzt<>'2')) where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + "))", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	
	/**
	 * 困难生认定2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsrd2ResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;
		String sql = "select count(*) num from view_xszz_com_knsrdb1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (!isFdyqx) {
					sql += " and fdysh in ('困难','特别困难')";
				}
			} else {
				sql += " and xysh in ('困难','特别困难')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存困难生认定2审核信息，成功返回TRUE，反之返回FALSE saveKnsrd2Shxx ---- 保存困难生认定2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrd2Shxx(Knsrdb1Model model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;		

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isKnsrd2DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (isFdyqx) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_knsrdb1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_knsrdb1",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj" }, new String[] { fdyshyj, xysh,
									xyshyj, now }, "xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取困难生认定2学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrd2Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,fdysh,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where xn||bjdm=? and fdysh<>'未审核' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='knsrd2' and a.bjdm=b.dm)";
			}
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,fdysh,xyshjg,xxshjg from view_xszz_com_knsrdb1 a where xn||zydm=? and xyshjg='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='zy' and b.tjxm='knsrd2' and a.zydm=b.dm)";
			
		}
		colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "fdysh","xyshjg","xxshjg"};
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取困难生认定2审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrd2shtjjg(String pkVal,String userType) throws Exception {
		String bm = userType.equalsIgnoreCase("xy") ? "bj" : "zy";
		String sql = "select decode(tjzt,'1','通过','2','不通过','未审核')shjg,shyj from xszz_com_bmshtjb where zjz||dm = ? and bm=? and tjxm='knsrd2'";
		String[] colList = new String[] { "shjg", "shyj" };
		return dao.getMap(sql, new String[] { pkVal, bm }, colList);
	}

/**
	 * 批量修改困难生认定2审核结果 modKnsrd2xxBybj ---- 批量修改困难生认定2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xxBybj(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("通过".equalsIgnoreCase(shjg)){//审核通过
					sqlT[i] = "update xszz_com_knsrdb1 a set xxshjg='"
						+ shjg
						+ "',xxsh=xysh,xxshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.zydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='通过'";// where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.zydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='未审核'
				}else{//审核不通过
					sqlT[i] = "update xszz_com_knsrdb1 a set xxshjg='"
							+ shjg
							+ "' ,xxsh='未审核',xxshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.zydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xyshjg='通过'";
				}
			} else {//学院审核
				if("3".equalsIgnoreCase(shjb)){
					if("通过".equalsIgnoreCase(shjg)){//审核通过
						sqlT[i] = "update xszz_com_knsrdb1 a set xyshjg='"
							+ shjg
							+ "',xysh=fdysh,xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh<>'未审核'";// and fdysh<>'未审核'
					}else{//审核不通过
						sqlT[i] = "update xszz_com_knsrdb1 a set xyshjg='"
							+ shjg
							+ "',xysh='未审核',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knsrdb1 b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh<>'未审核'";// and fdysh<>'未审核'
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定2审核结果 modKnsrd2Bmshtjb ---- 批量修改困难生认定2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2Bmshtjb(String[] pkT, String shjg,String shyj, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		String[] xySql = new String[pkT.length];
		String[] xydelSql = new String[pkT.length];
		
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("不通过".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_knsrdb1 b where a.zjz=b.xn and a.dm=b.zydm and b.xn||b.zydm='" + pkT[i] + "') and a.bm='zy' and a.tjxm='knsrd2'";
				sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj)(select distinct zydm,'zy','xn',xn,'knsrd2','" + tjzt + "','" + shyj + "' from view_xszz_com_knsrdb1 where xn||zydm='" + pkT[i] + "')";
			} else {//学院审核
				delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_knsrdb1 b where a.zjz=b.xn and a.dm=b.bjdm and b.xn||b.bjdm='" + pkT[i] + "') and a.bm='bj' and a.tjxm='knsrd2'";
				sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj)(select distinct bjdm,'bj','xn',xn,'knsrd2','" + tjzt + "','" + shyj + "' from view_xszz_com_knsrdb1 where xn||bjdm='" + pkT[i] + "')";
				//提交专业审核结果信息
				xydelSql[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_knsrdb1 b where a.zjz=b.xn and a.dm=b.zydm and b.xn||b.bjdm='" + pkT[i] + "') and a.bm='zy' and a.tjxm='knsrd2'";
				xySql[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj)(select distinct zydm,'zy','xn',xn,'knsrd2','" + tjzt + "','' from view_xszz_com_knsrdb1 where xn||bjdm='" + pkT[i] + "')";
			}
		}
		dao.runBatch(delT);
		dao.runBatch(sqlT);
		dao.runBatch(xydelSql);
		dao.runBatch(xySql);
	}
	
	
	/**
	 * 批量修改困难生认定2审核结果 modXfjm2xxByDepModel ---- 批量修改困难生认定2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsrd2xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		boolean isFdyqx = false;
		if ("3".equalsIgnoreCase(shjb)) {
			isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;
		}

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knsrdb1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (!isFdyqx) {
					sqlT[i] = "update xszz_com_knsrdb1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "'";
				} else {
					sqlT[i] = "update xszz_com_knsrdb1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	
	/**
	 * 获取困难生认定4详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getknsrd4xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrjnsr,zxlxdh,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_knsrdb1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrjnsr", "zxlxdh", "sqly", "sqsj",
				"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
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
	 * 判断困难生认定4是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsrd4datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isknsrd4DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xxsh in ('困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ? and xysh in ('困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_knsrdb1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存困难生认定4信息，成功返回TRUE，反之返回FALSE saveknsrd4Sqxx ---- 保存困难生认定4申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Sqxx(Knsrdb1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(model.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(model.getZxlxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isknsrd4DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knsrdb1", new String[] {
					"xn", "xh", "jtrjnsr", "zxlxdh", "sqly" }, new String[] {
					xn, xh, jtrjnsr, zxlxdh, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"jtrjnsr", "zxlxdh", "sqly", "fdysh", "fdyshsj", "fdyshyj",
					"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj",
					"sqsj" }, new String[] { jtrjnsr, zxlxdh, sqly, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now }, "xn||xh", xn + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 删除困难生认定4信息 delknsrd4xx ---- 删除困难生认定4信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delknsrd4xx(String[] pkT, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i] + "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xysh not in ('困难','特殊困难')";
				} else {
					sqlT[i] = "delete xszz_com_knsrdb1 where xn||xh='" + pkT[i]
							+ "' and xxsh not in ('困难','特殊困难')";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生认定4审核结果 modknsrd4xx ---- 批量修改困难生认定4审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modknsrd4xx(String[] pkT, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knsrdb1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_knsrdb1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_knsrdb1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 困难生认定4查询结果 knsrd4res ---- 困难生认定4结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getknsrd4Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where fdysh in ('困难','特殊困难')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_knsrdb1 where xysh in ('困难','特殊困难')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生认定4信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getknsrd4ResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_knsrdb1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh in ('困难','特殊困难')";
				}
			} else {
				sql += " and xysh in ('困难','特殊困难')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存困难生认定4审核信息，成功返回TRUE，反之返回FALSE saveknsrd4Shxx ---- 保存困难生认定4审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveknsrd4Shxx(Knsrdb1Model model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isknsrd4DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knsrdb1", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_knsrdb1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_knsrdb1",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj" }, new String[] { fdyshyj, xysh,
									xyshyj, now }, "xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取国家奖学金1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		// ------------2010/5/13 edit by luojw-------------
		String sql = "select sfdb,xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,"
				+ "xymc,zydm,zymc,bjdm,bjmc,lxdh,gxnbxkcs,yxkcs,lhkcs,cjpm,zhkpcj,"
				+ "zhkppm,hjqk,yjjl,xjjl,sjjl,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,"
				+"hjsj1,hjsj2,hjsj3,hjsj4,hjmc1,hjmc2,hjmc3,hjmc4,bjdw1,bjdw2,bjdw3,bjdw4,"
				+ "xyshyj,xxsh,xxshsj,xxshyj,(select tjzt from xszz_com_bmshtjb b where b.bm='bj'"
				+ " and b.dm=a.bjdm and b.zjz=a.xn and b.tjxm='gjjxj1')tjzt from view_xszz_com_gjjxj1 a where xn||xh = ?";
		String[] colList = new String[] { "sfdb", "xn", "xh", "xm", "xb",
				"mzmc", "zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh", "gxnbxkcs",
				"yxkcs", "lhkcs", "cjpm", "zhkpcj", "zhkppm", "hjqk", "yjjl",
				"xjjl", "sjjl", "sqly", "sqsj", "fdysh", "fdyshsj", "fdyshyj",
				"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "tjzt",
				"hjsj1","hjsj2","hjsj3","hjsj4","hjmc1","hjmc2","hjmc3",
				"hjmc4","bjdw1","bjdw2","bjdw3","bjdw4"};
		// ------------end-------------
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
	 * 判断国家奖学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjjxj1datacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjjxj1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断国家奖学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjjxj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjjxj1DataCfSq(String xh, String xn, String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){//三级审核
					sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ? and fdysh='通过'";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if(!"0".equalsIgnoreCase(num)){
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_gjjxj1 where xh = ? and xn = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家奖学金1信息，成功返回TRUE，反之返回FALSE saveGjjxj1Sqxx ---- 保存国家奖学金1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Sqxx(Gjjxj1Model model, HttpServletRequest request,String shjb)
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
		String hjsj1 = Base.chgNull(model.getHjsj1(), "", 1);
		String hjsj2 = Base.chgNull(model.getHjsj2(), "", 1);
		String hjsj3 = Base.chgNull(model.getHjsj3(), "", 1);
		String hjsj4 = Base.chgNull(model.getHjsj4(), "", 1);
		String hjmc1 = Base.chgNull(model.getHjmc1(), "", 1);
		String hjmc2 = Base.chgNull(model.getHjmc2(), "", 1);
		String hjmc3 = Base.chgNull(model.getHjmc3(), "", 1);
		String hjmc4 = Base.chgNull(model.getHjmc4(), "", 1);
		String bjdw1 = Base.chgNull(model.getBjdw1(), "", 1);
		String bjdw2 = Base.chgNull(model.getBjdw2(), "", 1);
		String bjdw3 = Base.chgNull(model.getBjdw3(), "", 1);
		String bjdw4 = Base.chgNull(model.getBjdw4(), "", 1);
		String now = GetTime.getSystemTime();
		
		//-------------2010/5/13 edit by luojw -----------
		String sfdb = model.getSfdb();// 是否低保
		String sHave = isGjjxj1DataCfSq(xh, xn, shjb);
		
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjjxj1", new String[] {
					"xn", "xh", "lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm",
					"zhkpcj", "zhkppm", "hjqk", "yjjl", "xjjl", "sjjl", "sqly",
					"sfdb","hjsj1","hjsj2","hjsj3","hjsj4","hjmc1","hjmc2","hjmc3",
					"hjmc4","bjdw1","bjdw2","bjdw3","bjdw4"}, new String[] { xn, xh, lxdh, gxnbxkcs, yxkcs,
					lhkcs, cjpm, zhkpcj, zhkppm, hjqk, yjjl, xjjl, sjjl, sqly,sfdb,hjsj1,hjsj2,hjsj3,hjsj4,hjmc1,
					hjmc2,hjmc3,hjmc4,bjdw1,bjdw2,bjdw3,bjdw4 }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjjxj1", new String[] {
					"lxdh", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj",
					"zhkppm", "hjqk", "yjjl", "xjjl", "sjjl", "sqly", "fdysh",
					"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
					"xxshsj", "xxshyj", "sqsj", "sfdb","hjsj1","hjsj2","hjsj3","hjsj4","hjmc1","hjmc2","hjmc3",
					"hjmc4","bjdw1","bjdw2","bjdw3","bjdw4"}, new String[] { lxdh,
					gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj, zhkppm, hjqk, yjjl,
					xjjl, sjjl, sqly, "未审核", "", "", "未审核", "", "", "未审核", "",
					"", now, sfdb,hjsj1,hjsj2,hjsj3,hjsj4,hjmc1,hjmc2,hjmc3,hjmc4,
					bjdw1,bjdw2,bjdw3,bjdw4 }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家奖学金1信息 delGjjxj1xx ---- 删除国家奖学金1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjjxj1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjjxj1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjjxj1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjjxj1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家奖学金1审核结果 modGjjxj1xx ---- 批量修改国家奖学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjjxj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjjxj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjjxj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjjxj1res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" }
				: new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家奖学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjjxj1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家奖学金1审核信息，成功返回TRUE，反之返回FALSE saveGjjxj1Shxx ---- 保存国家奖学金1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxj1Shxx(Gjjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjjxj1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjjxj1", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjjxj1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjjxj1",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}
	
	
	/**
	 * 国家奖学金1查询结果 getGjjxj1ResByFdy ---- 国家奖学金1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,String shjb)
			throws Exception {		
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";
		sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjxm='gjjxj1')='1' then 'disabled' else '' end) disabled,";
		sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjjxj1 a where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" }
				: new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;		
	}

	/**
	 * 获取国家奖学金1学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,yjjl,xjjl,sjjl,xysh,xxsh,(yjjl+xjjl+sjjl)zje from view_xszz_com_gjjxj1 a where xn||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='gjjxj1' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,yjjl,xjjl,sjjl,xysh,xxsh,(yjjl+xjjl+sjjl)zje from view_xszz_com_gjjxj1 a where xn||bjdm=?";
			}
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xysh","xxsh"};
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,yjjl,xjjl,sjjl,xxsh,(yjjl+xjjl+sjjl)zje from view_xszz_com_gjjxj1 a where xn||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='gjjxj1' and a.xydm=b.dm)";
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "yjjl", "xjjl", "sjjl", "zje", "xxsh"};
		}
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取国家奖学金1审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxj1shtjjg(String pkVal,String userType, String shjb) throws Exception {
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_gjjxj1 a where xn||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='gjjxj1' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_gjjxj1 a where xn||bjdm=?";
			}
		}else{
			sql = "select xxshyj shyj from view_xszz_com_gjjxj1 a where xn||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='gjjxj1' and a.xydm=b.dm)";
		}
		
		return dao.getMap(sql, new String[] { pkVal}, new String[]{"shyj"});
	}

	/**
	 * 批量修改国家奖学金1审核结果 modGjjxj1xxBybj ---- 批量修改国家奖学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xxBybj(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shjb = model.getShjb();
		String shyj = model.getShyj();
		String[] sqlT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_gjjxj1 a set xxsh='未审核',xxshsj='',xxshyj='' where exists(select 1 from (select xh,xn,(select distinct xydm from view_xsjbxx d where c.xh=d.xh)xydm from xszz_com_gjjxj1 c) b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xysh='通过'";
				}else{
					sqlT[i] = "update xszz_com_gjjxj1 a set xxsh='"
							+ shjg
							+ "',xxshyj='" 
							+ shyj 
							+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select distinct xydm from view_xsjbxx d where c.xh=d.xh)xydm from xszz_com_gjjxj1 c) b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xysh='通过'";
				}
			} else {//学院审核
				if("退回".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_gjjxj1 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjjxj1 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_gjjxj1 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjjxj1 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh)";
					}
				}else{
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_gjjxj1 a set xysh='"
							+ shjg
							+ "',xyshyj='" + shyj + "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjjxj1 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_gjjxj1 a set xysh='"
							+ shjg
							+ "',xyshyj='" + shyj+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjjxj1 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh)";
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家奖学金1审核结果 modGjjxj1Bmshtjb ---- 批量修改国家奖学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1Bmshtjb(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String tjr = model.getUserName();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_gjjxj1 b where a.zjz=b.xn and a.dm=b.xydm and b.xn||b.xydm='" + pkT[i] + "') and a.bm='xy' and tjxm='gjjxj1'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct xydm,'xy','xn',xn,'gjjxj1','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_gjjxj1 where xn||xydm='" + pkT[i] + "')";
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_gjjxj1 b where a.zjz=b.xn and a.dm=b.bjdm and b.xn||b.bjdm='" + pkT[i] + "') and a.bm='bj' and tjxm='gjjxj1'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct bjdm,'bj','xn',xn,'gjjxj1','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_gjjxj1 where xn||bjdm='" + pkT[i] + "')";
				}
			}
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	
	/**
	 * 批量修改国家奖学金1审核结果 modGjjxj1xxByDepModel ---- 批量修改国家奖学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxj1xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update xszz_com_gjjxj1 set fdysh='"
				+ shjg
				+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
				+ pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}


	/**
	 * 国家奖学金1查询结果 getGjjxj1ResForDM ---- 国家奖学金1结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc", "rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xysh", "xxsh" };
	
		sql = "select disabled,bgcolor,pk,xn,bjmc,xysh,xxsh,rs,yjjl,xjjl,sjjl," 
			  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn and b.tjxm='gjjxj1')tjr, " 
			  + "(select tjsj from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn and b.tjxm='gjjxj1')tjsj,"
              + "(yjjl+xjjl+sjjl)zje from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn and b.tjxm='gjjxj1') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xymc,xxsh,rs,yjjl,xjjl,sjjl," 
				  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn and b.tjxm='gjjxj1')tjr,"
				  + "(select tjsj from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn and b.tjxm='gjjxj1')tjsj,"
			      +	"(yjjl+xjjl+sjjl)zje from (select rownum r,'' disabled,";
		}

		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,yjjl,xjjl,sjjl from (" +
						    "select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
						    "(select count(*) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过')rs," +
						    "(select sum(yjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过')yjjl," +
						    "(select sum(xjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过')xjjl," +
						    "(select sum(sjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过')sjjl " +
						    "from view_xszz_com_gjjxj1 a where fdysh='通过' " +
						    "and exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjzt<>'2' and tjxm='gjjxj1')" +
						    ")a where 1=1";
			}else{
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,yjjl,xjjl,sjjl from (" +
							"select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
							"(select count(*) from view_xszz_com_gjjxj1 b where a.xn=b.xn and a.bjdm=b.bjdm)rs," +
							"(select sum(yjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn)yjjl," +
						    "(select sum(xjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn)xjjl," +
						    "(select sum(sjjl) from view_xszz_com_gjjxj1 b where a.bjdm=b.bjdm and a.xn=b.xn)sjjl " +
							" from view_xszz_com_gjjxj1 a" +
						")a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc", "rs", "yjjl", "xjjl", "sjjl", "zje", "tjr", "tjsj", "xxsh" };
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
					"xn||xydm pk,xn,xydm,xymc,xxsh,rs,yjjl,xjjl,sjjl from (" +
					    "select distinct xn,xydm,xymc,xysh,xxsh," +
					    "(select count(*) from view_xszz_com_gjjxj1 b where a.xn=b.xn and a.xydm=b.xydm and b.xysh='通过')rs," +
					    "(select sum(yjjl) from view_xszz_com_gjjxj1 b where a.xn=b.xn and a.xydm=b.xydm  and b.xysh='通过')yjjl," +
					    "(select sum(xjjl) from view_xszz_com_gjjxj1 b where a.xn=b.xn and a.xydm=b.xydm  and b.xysh='通过')xjjl," +
					    "(select sum(sjjl) from view_xszz_com_gjjxj1 b where a.xn=b.xn and a.xydm=b.xydm and b.xysh='通过')sjjl " +
					    " from view_xszz_com_gjjxj1 a where " +
					    "xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and a.xn=b.zjz and b.tjzt<>'2' and tjxm='gjjxj1')" +
					    ") a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ") a)", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ") a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	
	/**
	 * 获取国家助学金1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_dw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_dw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_dw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_dw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_dw,sqly,sqsj,zxjdm,zxjdj,zxjje,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_gjzxj1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "chhzjl", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_dw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_dw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_dw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_dw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_dw",
				"sqly", "sqsj", "zxjdm", "zxjdj", "zxjje", "fdysh", "fdyshsj",
				"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
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
	 * 判断国家助学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjzxj1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjzxj1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjzxj1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjzxj1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家助学金1信息，成功返回TRUE，反之返回FALSE saveGjzxj1Sqxx ---- 保存国家助学金1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Sqxx(Gjzxj1Model model, HttpServletRequest request)
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
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_dw = Base.chgNull(model.getJtcy1_dw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_dw = Base.chgNull(model.getJtcy2_dw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_dw = Base.chgNull(model.getJtcy3_dw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_dw = Base.chgNull(model.getJtcy4_dw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_dw = Base.chgNull(model.getJtcy5_dw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isGjzxj1DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjzxj1", new String[] {
					"xn", "xh", "lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_dw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_dw", "sqly" }, new String[] {
					xn, xh, lxdh, chhzjl, jthk, jtzrks, jtyzsr, rjysr, srly,
					jtzz, yzbm, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw,
					jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_dw, jtcy3_xm, jtcy3_nl,
					jtcy3_gx, jtcy3_dw, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_dw,
					jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_dw, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjzxj1", new String[] {
					"lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_dw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_dw", "sqly", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj" }, new String[] { lxdh, chhzjl, jthk,
					jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_nl, jtcy1_gx, jtcy1_dw, jtcy2_xm, jtcy2_nl, jtcy2_gx,
					jtcy2_dw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_dw, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
					jtcy5_dw, sqly, "未审核", "", "", "未审核", "", "", "未审核", "",
					"", now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家助学金1信息 delGjzxj1xx ---- 删除国家助学金1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjzxj1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjzxj1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjzxj1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家助学金1审核结果 modGjzxj1xx ---- 批量修改国家助学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjzxj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjzxj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjzxj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjzxj1res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"zxjdj", "zxjje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家助学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjzxj1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家助学金1审核信息，成功返回TRUE，反之返回FALSE saveGjzxj1Shxx ---- 保存国家助学金1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj1Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjdj = Base.chgNull(model.getZxjdj(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjzxj1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjzxj1", new String[] {
					"zxjdm", "zxjdj", "zxjje", "xxsh", "xxshyj", "xxshsj" },
					new String[] { zxjdm, zxjdj, zxjje, xxsh, xxshyj, now },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjzxj1",
								new String[] { "zxjdm", "zxjdj", "zxjje",
										"fdysh", "fdyshyj", "fdyshsj" },
								new String[] { zxjdm, zxjdj, zxjje, fdysh,
										fdyshyj, now }, "xn||xh", xn + xh,
								request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjzxj1",
							new String[] { "zxjdm", "zxjdj", "zxjje", "xysh",
									"xyshyj", "xyshsj" }, new String[] { zxjdm,
									zxjdj, zxjje, xysh, xyshyj, now },
							"xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	
	/**
	 * 获取国家助学金2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jtzrks,jtyzsr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_dw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_dw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_dw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_dw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_dw,sqly,sqsj,zxjdm,zxjdj,zxjje,rdqk,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_gjzxj2 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "jthk", "jtzrks",
				"jtyzsr", "srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
				"jtcy1_gx", "jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
				"jtcy2_dw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_gx", "jtcy5_dw", "sqly", "sqsj", "zxjdm",
				"zxjdj", "zxjje", "rdqk", "fdysh", "fdyshsj", "fdyshyj",
				"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj" };
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
	 * 判断国家助学金2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxj2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjzxj2DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjzxj2 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjzxj2 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjzxj2 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家助学金2信息，成功返回TRUE，反之返回FALSE saveGjzxj2Sqxx ---- 保存国家助学金2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Sqxx(Gjzxj1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_dw = Base.chgNull(model.getJtcy1_dw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_dw = Base.chgNull(model.getJtcy2_dw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_dw = Base.chgNull(model.getJtcy3_dw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_dw = Base.chgNull(model.getJtcy4_dw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_dw = Base.chgNull(model.getJtcy5_dw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isGjzxj2DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjzxj2", new String[] {
					"xn", "xh", "lxdh", "jthk", "jtzrks", "jtyzsr", "srly",
					"jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_dw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_dw", "sqly" }, new String[] { xn, xh,
					lxdh, jthk, jtzrks, jtyzsr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_nl, jtcy1_gx, jtcy1_dw, jtcy2_xm, jtcy2_nl, jtcy2_gx,
					jtcy2_dw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_dw, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
					jtcy5_dw, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjzxj2", new String[] {
					"lxdh", "jthk", "jtzrks", "jtyzsr", "srly", "jtzz", "yzbm",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_dw", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_dw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_dw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_dw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_dw",
					"sqly", "fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj",
					"xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj" },
					new String[] { lxdh, jthk, jtzrks, jtyzsr, srly, jtzz,
							yzbm, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw,
							jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_dw, jtcy3_xm,
							jtcy3_nl, jtcy3_gx, jtcy3_dw, jtcy4_xm, jtcy4_nl,
							jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_dw, sqly, "未审核", "", "", "未审核", "", "",
							"未审核", "", "", now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家助学金2信息 delGjzxj2xx ---- 删除国家助学金2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjzxj2 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjzxj2 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjzxj2 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家助学金2审核结果 modGjzxj2xx ---- 批量修改国家助学金2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjzxj2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjzxj2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjzxj2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 获取国家助学金3详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		// ------------2010/5/13 edit by luojw-------------
		String sql = "select sfdb,xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,"
				+ "xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,xsyhm,xshm,xszh,jthk,"
				+ "jtzrks,jtyzsr,rjysr,srly,jtzz,chjl,yzbm,jtcy1_xm,jtcy1_nl,"
				+ "jtcy1_gx,jtcy1_dw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_dw,"
				+ "jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_dw,jtcy4_xm,jtcy4_nl,"
				+ "jtcy4_gx,jtcy4_dw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_dw,"
				+ "sqly,sqsj,zxjdm,zxjdj,zxjje,fdysh,fdyshsj,fdyshyj,xysh,"
				+ "xyshsj,xyshyj,xxsh,xxshsj,xxshyj,"
				+ "(select tjzt from xszz_com_bmshtjb b where a.xn=b.zjz "
				+ "and a.bjdm=b.dm and b.tjxm='gjzxj3' and b.bm='bj')tjzt "
				+ "from view_xszz_com_gjzxj3 a where xn||xh = ?";
		String[] colList = new String[] { "sfdb", "xn", "xh", "xm", "xb",
				"mzmc", "zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh", "xsyhm",
				"xshm", "xszh", "jthk", "jtzrks", "jtyzsr", "rjysr", "srly",
				"jtzz","chjl", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_dw",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_dw", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_dw", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_dw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_dw", "sqly", "sqsj", "zxjdm", "zxjdj", "zxjje", "fdysh",
				"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
				"xxshsj", "xxshyj", "tjzt" };
		// ------------end-------------
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
	 * 判断国家助学金3是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxj3datacf ----
	 * 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjzxj3DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断国家助学金3是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxj3datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjzxj3DataCfSq(String xh, String xn, String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){
					sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ? and fdysh='通过'";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if(!num.equalsIgnoreCase("0")){
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_gjzxj3 where xh = ? and xn = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家助学金3信息，成功返回TRUE，反之返回FALSE saveGjzxj3Sqxx ---- 保存国家助学金3申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Sqxx(Gjzxj3Model model, HttpServletRequest request,String shjb)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String xsyhm = Base.chgNull(model.getXsyhm(), "", 1);
		String xshm = Base.chgNull(model.getXshm(), "", 1);
		String xszh = Base.chgNull(model.getXszh(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(model.getRjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String chjl = Base.chgNull(model.getChjl(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_dw = Base.chgNull(model.getJtcy1_dw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_dw = Base.chgNull(model.getJtcy2_dw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_dw = Base.chgNull(model.getJtcy3_dw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_dw = Base.chgNull(model.getJtcy4_dw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_dw = Base.chgNull(model.getJtcy5_dw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();
		// ------------2010/5/13 edit by luojw-------------
		String sfdb = model.getSfdb();// 是否低保
		// ------------end-------------
		
		String sHave = isGjzxj3DataCfSq(xh, xn, shjb);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjzxj3", new String[] {
					"xn", "xh", "lxdh", "xsyhm", "xshm", "xszh", "jthk",
					"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz","chjl", "yzbm",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_dw", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_dw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_dw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_dw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_dw",
					"sqly", "sfdb" }, new String[] { xn, xh, lxdh, xsyhm, xshm,
					xszh, jthk, jtzrks, jtyzsr, rjysr, srly, jtzz,chjl, yzbm,
					jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_dw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_dw,
					jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl,
					jtcy5_gx, jtcy5_dw, sqly, sfdb }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjzxj3", new String[] {
					"lxdh", "xsyhm", "xshm", "xszh", "jthk", "jtzrks",
					"jtyzsr", "rjysr", "srly", "jtzz","chjl", "yzbm", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_dw", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_gx", "jtcy2_dw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
					"jtcy3_dw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_dw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_dw", "sqly",
					"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
					"xxsh", "xxshsj", "xxshyj", "sqsj", "sfdb" }, new String[] {
					lxdh, xsyhm, xshm, xszh, jthk, jtzrks, jtyzsr, rjysr, srly,
					jtzz,chjl, yzbm, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw,
					jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_dw, jtcy3_xm, jtcy3_nl,
					jtcy3_gx, jtcy3_dw, jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_dw,
					jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_dw, sqly, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now, sfdb }, "xn||xh", xn
					+ xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家助学金3信息 delGjzxj3xx ---- 删除国家助学金3信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj3xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjzxj3 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjzxj3 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjzxj3 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家助学金3审核结果 modGjzxj3xx ---- 批量修改国家助学金3审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjzxj3 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjzxj3 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjzxj3 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjzxj3res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"zxjdj", "zxjje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家助学金3信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj3ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjzxj3 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家助学金3审核信息，成功返回TRUE，反之返回FALSE saveGjzxj3Shxx ---- 保存国家助学金3审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj3Shxx(Gjzxj3Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjdj = Base.chgNull(model.getZxjdj(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjzxj3DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjzxj3", new String[] {
					"zxjdm", "zxjdj", "zxjje", "xxsh", "xxshyj", "xxshsj" },
					new String[] { zxjdm, zxjdj, zxjje, xxsh, xxshyj, now },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjzxj3",
								new String[] { "zxjdm", "zxjdj", "zxjje",
										"fdysh", "fdyshyj", "fdyshsj" },
								new String[] { zxjdm, zxjdj, zxjje, fdysh,
										fdyshyj, now }, "xn||xh", xn + xh,
								request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjzxj3",
							new String[] { "zxjdm", "zxjdj", "zxjje", "xysh",
									"xyshyj", "xyshsj" }, new String[] { zxjdm,
									zxjdj, zxjje, xysh, xyshyj, now },
							"xn||xh", xn + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 国家助学金3查询结果 getGjzxj3ResByFdy ---- 国家助学金3结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from (select rownum r,";
		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjxm='gjzxj3')='1' then 'disabled' else '' end) disabled,";
		sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_com_gjzxj3 a where 1=1";
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"zxjdj", "zxjje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		
		
		return resList;
	}

	/**
	 * 获取国家助学金3学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,xysh,xxsh from view_xszz_com_gjzxj3 a where xn||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='gjzxj3' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,xysh,xxsh from view_xszz_com_gjzxj3 a where xn||bjdm=?";
			}
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "xysh", "xxsh"};
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,xxsh from view_xszz_com_gjzxj3 a where xn||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='gjzxj3' and a.xydm=b.dm)";
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje","xxsh"};
		}
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取国家助学金3审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxj3shtjjg(String pkVal,String userType,String shjb) throws Exception {
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_gjzxj3 a where xn||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='bj' and b.tjxm='gjzxj3' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_gjzxj3 a where xn||bjdm=?";
			}
		}else{
			sql = "select xxshyj shyj from view_xszz_com_gjzxj3 a where xn||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn and b.bm='xy' and b.tjxm='gjzxj3' and a.xydm=b.dm)";
		}
		
		return dao.getMap(sql, new String[] { pkVal}, new String[]{"shyj"});
	}

	/**
	 * 批量修改国家助学金3审核结果 Gjzxj3 ---- 批量修改国家助学金3审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xxBybj(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String shjb = model.getShjb();
		String[] sqlT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_gjzxj3 a set xxsh='未审核',xxshsj='',xxshyj='' where exists(select 1 from (select xh,xn,(select distinct xydm from view_xsjbxx d where c.xh=d.xh)xydm from xszz_com_gjzxj3 c) b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xysh='通过'";
				}else{
					sqlT[i] = "update xszz_com_gjzxj3 a set xxsh='"
							+ shjg
							+ "',xxshyj='" 
							+ shyj
							+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select distinct xydm from view_xsjbxx d where c.xh=d.xh)xydm from xszz_com_gjzxj3 c) b where b.xn||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and xysh='通过'";
				}
				
			} else {//学院审核
				if("退回".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_gjzxj3 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjzxj3 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_gjzxj3 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjzxj3 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh)";
					}
				}else{
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_gjzxj3 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjzxj3 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_gjzxj3 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj 
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from (select xh,xn,(select bjdm from view_xsjbxx d where c.xh=d.xh)bjdm from xszz_com_gjzxj3 c) b where b.xn||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xh=b.xh)";
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家助学金3审核结果 modGjzxj3Bmshtjb ---- 批量修改国家助学金3审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3Bmshtjb(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String tjr = model.getUserName();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_gjzxj3 b where a.zjz=b.xn and a.dm=b.xydm and b.xn||b.xydm='" + pkT[i] + "') and a.bm='xy' and a.tjxm='gjzxj3'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct xydm,'xy','xn',xn,'gjzxj3','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_gjzxj3 where xn||xydm='" + pkT[i] + "')";
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_gjzxj3 b where a.zjz=b.xn and a.dm=b.bjdm and b.xn||b.bjdm='" + pkT[i] + "') and a.bm='bj' and a.tjxm='gjzxj3'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct bjdm,'bj','xn',xn,'gjzxj3','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_gjzxj3 where xn||bjdm='" + pkT[i] + "')";
				}
			}
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	
	/**
	 * 批量修改国家助学金3审核结果 modGjzxj3xxByDepModel ---- 批量修改国家助学金3审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj3xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "update xszz_com_gjzxj3 set fdysh='"
				+ shjg
				+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
				+ pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}


	/**
	 * 国家助学金3查询结果 getGjzxj3ResForDM ---- 国家助学金3结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj3ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc", "rs", "zxjje", "tjr", "tjsj", "xysh", "xxsh" };
	
		sql = "select disabled,bgcolor,pk,xn,bjmc,xysh,xxsh,rs," 
			+ "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where a.xn=b.zjz and a.bjdm=b.dm and b.tjxm='gjzxj3' and b.bm='bj')tjr," 
			+ "(select tjsj from xszz_com_bmshtjb b where a.xn=b.zjz and a.bjdm=b.dm and b.tjxm='gjzxj3' and b.bm='bj')tjsj,zxjje from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn and b.tjxm='gjzxj3') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xymc,xxsh,rs," 
				  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where a.xn=b.zjz and a.xydm=b.dm and b.tjxm='gjzxj3' and b.bm='xy')tjr," 
				  + "(select tjsj from xszz_com_bmshtjb b where a.xn=b.zjz and a.xydm=b.dm and b.tjxm='gjzxj3' and b.bm='xy')tjsj,zxjje from (select rownum r,'' disabled,";
		}

		
		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,zxjje from (" +
						   "select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
						   " (select count(*) from view_xszz_com_gjzxj3 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过') rs," +
						   " (select sum(zxjje) from view_xszz_com_gjzxj3 b where a.bjdm=b.bjdm and a.xn=b.xn and b.fdysh='通过') zxjje from " +
						   "view_xszz_com_gjzxj3 a where fdysh='通过' and " +
						   "exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn=b.zjz and b.tjzt<>'2' and b.tjxm='gjzxj3')" +
						   ") a where 1=1";
			}else{
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,zxjje from (" +
						    "select distinct xn,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
						    "(select count(*) from view_xszz_com_gjzxj3 b where a.bjdm=b.bjdm and a.xn=b.xn)rs," +
						    "(select sum(zxjje) from view_xszz_com_gjzxj3 b where a.bjdm=b.bjdm and a.xn=b.xn)zxjje" +
						    " from view_xszz_com_gjzxj3 a" +
						")a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc", "rs", "zxjje", "tjr", "tjsj", "xxsh" };
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
					"xn||xydm pk,xn,xydm,xymc,xxsh,rs,zxjje from (" +
					    "select distinct xn,xydm,xymc,xysh,xxsh," +
					    "(select count(*) from view_xszz_com_gjzxj3 b where a.xn=b.xn and a.xydm=b.xydm and b.xysh='通过')rs," +
					    "(select sum(zxjje) from view_xszz_com_gjzxj3 b where a.xn=b.xn and a.xydm=b.xydm and b.xysh='通过')zxjje from view_xszz_com_gjzxj3 a where xysh='通过' " +
					    "and exists(select 1 from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and a.xn=b.zjz and b.tjzt<>'2' and b.tjxm='gjzxj3')) a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ")a)a", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ")a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家奖学1查询结果 Gjzxj2res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from view_xszz_com_gjzxj2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from view_xszz_com_gjzxj2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from view_xszz_com_gjzxj2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from view_xszz_com_gjzxj2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,rdqk,fdysh,xysh,xxsh from view_xszz_com_gjzxj2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "rdqk",
				"fdysh", "xysh", "xxsh" } : new String[] { "disabled",
				"bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh", "xymc",
				"zymc", "bjmc", "sqsj", "zxjdj", "zxjje", "rdqk", "xysh",
				"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家助学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjzxj2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家助学金2审核信息，成功返回TRUE，反之返回FALSE saveGjzxj2Shxx ---- 保存国家助学金2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxj2Shxx(Gjzxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjdj = Base.chgNull(model.getZxjdj(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjzxj2DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjzxj2", new String[] {
					"zxjdm", "zxjdj", "zxjje", "xxsh", "xxshyj", "xxshsj" },
					new String[] { zxjdm, zxjdj, zxjje, xxsh, xxshyj, now },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjzxj2",
								new String[] { "zxjdm", "zxjdj", "zxjje",
										"fdysh", "fdyshyj", "fdyshsj" },
								new String[] { zxjdm, zxjdj, zxjje, fdysh,
										fdyshyj, now }, "xn||xh", xn + xh,
								request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjzxj2",
							new String[] { "zxjdm", "zxjdj", "zxjje", "xysh",
									"xyshyj", "xyshsj" }, new String[] { zxjdm,
									zxjdj, zxjje, xysh, xyshyj, now },
							"xn||xh", xn + xh, request);
				}
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

		String sql = "select zxjdm dm,zxjdj||'||'||zxjje mc from xszz_com_gjzxj1dmb order by zxjdm desc";
		list.addAll(dao.getArrayList(sql, new String[] {}, new String[] { "dm",
				"mc" }));
		return list;
	}

	/**
	 * 获取国家励志奖学金1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,xxcj,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_gjlzjxj1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "chhzjl", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "xxcj",
				"sqly", "sqsj", "fdysh", "fdyshsj", "fdyshyj", "xysh",
				"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj" };
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
	 * 判断国家励志奖学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjlzjxj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxj1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjlzjxj1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjlzjxj1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjlzjxj1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家励志奖学金1信息，成功返回TRUE，反之返回FALSE saveGjlzjxj1Sqxx ---- 保存国家励志奖学金1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Sqxx(Gjlzjxj1Model model, HttpServletRequest request)
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

		String sHave = isGjlzjxj1DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjlzjxj1", new String[] {
					"xn", "xh", "lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "xxcj", "sqly" },
					new String[] { xn, xh, lxdh, chhzjl, jthk, jtzrks, jtyzsr,
							rjysr, srly, jtzz, yzbm, xxcj, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjlzjxj1", new String[] {
					"lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "xxcj", "sqly", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj" }, new String[] { lxdh, chhzjl, jthk,
					jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm, xxcj, sqly, "未审核",
					"", "", "未审核", "", "", "未审核", "", "", now }, "xn||xh", xn
					+ xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家励志奖学金1信息 delGjlzjxj1xx ---- 删除国家励志奖学金1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjlzjxj1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjlzjxj1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjlzjxj1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家励志奖学金1审核结果 modGjlzjxj1xx ---- 批量修改国家励志奖学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjlzjxj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjlzjxj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjlzjxj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjlzjxj1res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" }
				: new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家励志奖学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjlzjxj1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家励志奖学金1审核信息，成功返回TRUE，反之返回FALSE saveGjlzjxj1Shxx ---- 保存国家励志奖学金1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj1Shxx(Gjlzjxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjlzjxj1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjlzjxj1", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjlzjxj1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjlzjxj1",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取国家励志奖学金2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxj2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,km1,cj1,km2,cj2,km3,cj3,km4,cj4,km5,cj5,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_gjlzjxj2 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "chhzjl", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "km1",
				"cj1", "km2", "cj2", "km3", "cj3", "km4", "cj4", "km5", "cj5",
				"sqly", "sqsj", "fdysh", "fdyshsj", "fdyshyj", "xysh",
				"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj" };
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
	 * 判断国家励志奖学金2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjlzjxj2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxj2DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_gjlzjxj2 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_gjlzjxj2 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_gjlzjxj2 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存国家励志奖学金2信息，成功返回TRUE，反之返回FALSE saveGjlzjxj2Sqxx ---- 保存国家励志奖学金2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Sqxx(Gjlzjxj2Model model, HttpServletRequest request)
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
		String km1 = Base.chgNull(model.getKm1(), "", 1);
		String cj1 = Base.chgNull(model.getCj1(), "", 1);
		String km2 = Base.chgNull(model.getKm2(), "", 1);
		String cj2 = Base.chgNull(model.getCj2(), "", 1);
		String km3 = Base.chgNull(model.getKm3(), "", 1);
		String cj3 = Base.chgNull(model.getCj3(), "", 1);
		String km4 = Base.chgNull(model.getKm4(), "", 1);
		String cj4 = Base.chgNull(model.getCj4(), "", 1);
		String km5 = Base.chgNull(model.getKm5(), "", 1);
		String cj5 = Base.chgNull(model.getCj5(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isGjlzjxj2DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_gjlzjxj2", new String[] {
					"xn", "xh", "lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "km1", "cj1", "km2",
					"cj2", "km3", "cj3", "km4", "cj4", "km5", "cj5", "sqly" },
					new String[] { xn, xh, lxdh, chhzjl, jthk, jtzrks, jtyzsr,
							rjysr, srly, jtzz, yzbm, km1, cj1, km2, cj2, km3,
							cj3, km4, cj4, km5, cj5, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_gjlzjxj2", new String[] {
					"lxdh", "chhzjl", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "km1", "cj1", "km2", "cj2", "km3",
					"cj3", "km4", "cj4", "km5", "cj5", "sqly", "fdysh",
					"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
					"xxshsj", "xxshyj", "sqsj" }, new String[] { lxdh, chhzjl,
					jthk, jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm, km1, cj1,
					km2, cj2, km3, cj3, km4, cj4, km5, cj5, sqly, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now }, "xn||xh", xn + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 删除国家励志奖学金2信息 delGjlzjxj2xx ---- 删除国家励志奖学金2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxj2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_gjlzjxj2 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_gjlzjxj2 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_gjlzjxj2 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改国家励志奖学金2审核结果 modGjlzjxj2xx ---- 批量修改国家励志奖学金2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxj2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_gjlzjxj2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_gjlzjxj2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_gjlzjxj2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Gjlzjxj2res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_gjlzjxj2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" }
				: new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 国家励志奖学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_gjlzjxj2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存国家励志奖学金2审核信息，成功返回TRUE，反之返回FALSE saveGjlzjxj2Shxx ---- 保存国家励志奖学金2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxj2Shxx(Gjlzjxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isGjlzjxj2DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_gjlzjxj2", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_gjlzjxj2",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_gjlzjxj2",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取学费减免1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,ymcs,sxnzhcpmc,sxnzhcprs,bkmc,llk,lxfs,ywqgzx,jtlxfs,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_xfjm1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "ymcs", "sxnzhcpmc", "sxnzhcprs", "bkmc",
				"llk", "lxfs", "ywqgzx", "jtlxfs", "sqly", "sqsj", "fdysh",
				"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
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
	 * 判断学费减免1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isxfjm1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isXfjm1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_xfjm1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_xfjm1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_xfjm1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存学费减免1信息，成功返回TRUE，反之返回FALSE saveXfjm1Sqxx ---- 保存学费减免1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Sqxx(Xfjm1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sxnzhcpmc = Base.chgNull(model.getSxnzhcpmc(), "", 1);
		String sxnzhcprs = Base.chgNull(model.getSxnzhcprs(), "", 1);
		String bkmc = Base.chgNull(model.getBkmc(), "", 1);
		String llk = Base.chgNull(model.getLlk(), "", 1);
		String lxfs = Base.chgNull(model.getLxfs(), "", 1);
		String ywqgzx = Base.chgNull(model.getYwqgzx(), "", 1);
		String jtlxfs = Base.chgNull(model.getJtlxfs(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isXfjm1DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_xfjm1", new String[] {
					"xn", "xh", "sxnzhcpmc", "sxnzhcprs", "bkmc", "llk",
					"lxfs", "ywqgzx", "jtlxfs", "sqly" }, new String[] { xn,
					xh, sxnzhcpmc, sxnzhcprs, bkmc, llk, lxfs, ywqgzx, jtlxfs,
					sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_xfjm1", new String[] {
					"sxnzhcpmc", "sxnzhcprs", "bkmc", "llk", "lxfs", "ywqgzx",
					"jtlxfs", "sqly", "fdysh", "fdyshsj", "fdyshyj", "xysh",
					"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj" },
					new String[] { sxnzhcpmc, sxnzhcprs, bkmc, llk, lxfs,
							ywqgzx, jtlxfs, sqly, "未审核", "", "", "未审核", "", "",
							"未审核", "", "", now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除学费减免1信息 delXfjm1xx ---- 删除学费减免1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfjm1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_xfjm1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_xfjm1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_xfjm1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费减免1审核结果 modXfjm1xx ---- 批量修改学费减免1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjm1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_xfjm1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_xfjm1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_xfjm1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 学费减免1查询结果 Xfjm1res ---- 学费减免1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfjm1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfjm1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfjm1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfjm1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfjm1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" }
				: new String[] { "disabled", "bgcolor", "pk", "xn", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh",
						"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费减免1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_xfjm1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存学费减免1审核信息，成功返回TRUE，反之返回FALSE saveXfjm1Shxx ---- 保存学费减免1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm1Shxx(Xfjm1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isXfjm1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_xfjm1", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_xfjm1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_xfjm1",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取学费减免2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xq,xqmc,xh,xm,xb,nj,sfzh,xydm,xymc,zydm," +
				     "zymc,bjdm,bjmc,qfqk,hjqk,hzqk,jtdz,lxdh,sqly,sqjmje,sqsj," +
				     "pzjmje,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj," +
				     "(select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.xq=b.zjz and b.tjxm='xfjm2')tjzt " +
				     "from view_xszz_com_xfjm2 a where xn||xq||xh = ?";
		String[] colList = new String[] { "xn", "xq", "xqmc", "xh", "xm", "xb",
				"nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"qfqk", "hjqk", "hzqk", "jtdz", "lxdh", "sqly", "sqjmje",
				"sqsj", "pzjmje", "fdysh", "fdyshsj", "fdyshyj", "xysh",
				"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "tjzt" };
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
	 * 获取学费减免2审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjm2shtjjg(String pkVal,String userType,String shjb) throws Exception {
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_xfjm2 a where xn||xq||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='bj' and b.tjxm='xfjm2' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_xfjm2 a where xn||xq||bjdm=?";
			}
		}else{
			sql = "select xxshyj shyj from view_xszz_com_xfjm2 a where xn||xq||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='xy' and b.tjxm='xfjm2' and a.xydm=b.dm)";
		}
		return dao.getMap(sql, new String[] { pkVal}, new String[]{"shyj"});
	}
	
	/**
	 * 获取学费减免2学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqjmje,pzjmje,xysh from view_xszz_com_xfjm2 a where xn||xq||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='bj' and b.tjxm='xfjm2' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqjmje,pzjmje,xysh from view_xszz_com_xfjm2 a where xn||xq||bjdm=?";
			}
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqjmje", "pzjmje", "xysh"};
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqjmje,pzjmje,xxsh from view_xszz_com_xfjm2 a where xn||xq||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='xy' and b.tjxm='xfjm2' and a.xydm=b.dm)";
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqjmje", "pzjmje", "xxsh"};
		}
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}
	
	
	/**
	 * 判断学费减免2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isxfjm2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isXfjm2DataCf(String xh, String xn, String xq) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断学费减免2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isxfjm2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isXfjm2DataCfSq(String xh, String xn, String xq, String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){//三级审核
					sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ? and fdysh='通过'";
					num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_xfjm2 where xh = ? and xn = ? and xq = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存学费减免2信息，成功返回TRUE，反之返回FALSE saveXfjm2Sqxx ---- 保存学费减免2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Sqxx(Xfjm2Model model, HttpServletRequest request,String shjb)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xq = Base.chgNull(model.getXq(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String qfqk = Base.chgNull(model.getQfqk(), "", 1);
		String hjqk = Base.chgNull(model.getHjqk(), "", 1);
		String hzqk = Base.chgNull(model.getHzqk(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String sqjmje = Base.chgNull(model.getSqjmje(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isXfjm2DataCfSq(xh, xn, xq, shjb);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_xfjm2", new String[] {
					"xn", "xq", "xh", "qfqk", "hjqk", "hzqk", "jtdz", "lxdh",
					"sqly", "sqjmje" }, new String[] { xn, xq, xh, qfqk, hjqk,
					hzqk, jtdz, lxdh, sqly, sqjmje }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_xfjm2", new String[] {
					"qfqk", "hjqk", "hzqk", "jtdz", "lxdh", "sqly", "sqjmje",
					"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
					"xxsh", "xxshsj", "xxshyj", "pzjmje", "sqsj" },
					new String[] { qfqk, hjqk, hzqk, jtdz, lxdh, sqly, sqjmje,
							"未审核", "", "", "未审核", "", "", "未审核", "", "", "",
							now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除学费减免2信息 delXfjm2xx ---- 删除学费减免2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfjm2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_xfjm2 where xn||xq||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_xfjm2 where xn||xq||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_xfjm2 where xn||xq||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费减免2审核结果 modXfjm2xx ---- 批量修改学费减免2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_xfjm2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_xfjm2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_xfjm2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费减免2审核结果 modXfjm2xx ---- 批量修改学费减免2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xxBybj(XszzCommonN05ActionForm actionForm, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = actionForm.getPk();
		String shjg = actionForm.getShjg();
		String shjb = actionForm.getShjb();
		String shyj = actionForm.getShyj();
		String[] sqlT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if ("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_xfjm2 a set xxsh='未审核',xxshsj='', pzjmje='',xxshyj='' where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and xysh='通过'";
				}else{
					sqlT[i] = "update xszz_com_xfjm2 a set xxsh='"
							+ shjg
							+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd'),xxshyj='" 
							+ shyj 
							+ "' where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and xysh='通过'";
				}
			} else {//学院审核
				if("退回".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_xfjm2 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_xfjm2 a set xysh='',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh)";
					}
				}else{
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_xfjm2 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj 
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_xfjm2 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj 
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_xfjm2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh)";
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费减免2审核结果 modXfjm2xx ---- 批量修改学费减免2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2Bmshtjb(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String tjr = model.getUserName();
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){//退回
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核					
						delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_xfjm2 b where a.zjz=b.xn||b.xq and a.dm=b.xydm and b.xn||b.xq||b.xydm='" + pkT[i] + "') and a.bm='xy' and a.tjxm='xfjm2'";
						sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj)(select distinct xydm,'xy','xn||xq',xn||xq,'xfjm2','" + tjzt + "','" + shyj + "' from view_xszz_com_xfjm2 where xn||xq||xydm='" + pkT[i] + "')";
					
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_xfjm2 b where a.zjz=b.xn||b.xq and a.dm=b.bjdm and b.xn||b.xq||b.bjdm='" + pkT[i] + "') and a.bm='bj' and a.tjxm='xfjm2'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct bjdm,'bj','xn||xq',xn||xq,'xfjm2','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_xfjm2 where xn||xq||bjdm='" + pkT[i] + "')";
				}
			}		
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	
	/**
	 * 批量修改学费减免2审核结果 modXfjm2xxByDepModel ---- 批量修改学费减免2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjm2xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = false;
		if ("3".equalsIgnoreCase(shjb)) {
			isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;
			
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				sqlT[i] = "update xszz_com_xfjm2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
						+ pkT[i] + "'";
			} else {
				if (!isFdyqx) {//学院审核
					sqlT[i] = "update xszz_com_xfjm2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "'";
				} else {//辅导员审核
					sqlT[i] = "update xszz_com_xfjm2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	
	/**
	 * 学费减免1查询结果 Xfjm2res ---- 学费减免1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,sqjmje,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "sqjmje", "sqsj", "pzjmje",
				"fdysh", "xysh", "xxsh" } : new String[] { "disabled",
				"bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqjmje", "sqsj", "pzjmje", "xysh",
				"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费减免1查询结果 getXfjm2ResByFdy ---- 学费减免1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from (select rownum r," +
			  "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.xq=b.zjz and b.tjxm='xfjm2')='1' then 'disabled' else '' end) disabled," +
			  "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
			  "xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqjmje,sqsj,pzjmje,fdysh,xysh,xxsh from view_xszz_com_xfjm2 a where 1=1";
		      
		StringBuffer whereSql = getWhereSql(queryModel, request, true);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] {
				"disabled", "bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "sqjmje", "sqsj", "pzjmje",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费减免1查询结果 getXfjm2ResForDM ---- 学费减免1结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjm2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc", "bjmc", "rs", "pzjmje", "tjr", "tjsj", "xysh", "xxsh" };
	
		sql = "select disabled,bgcolor,pk,xn,xqmc,bjmc,xysh,xxsh,rs,pzjmje," 
			  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.xq and b.tjxm='xfjm2')tjr," 
			  + "(select tjsj from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.xq and b.tjxm='xfjm2')tjsj from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn||a.xq and b.tjxm='xfjm2') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xqmc,xymc,xxsh,rs,pzjmje," 
				  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.xq and b.tjxm='xfjm2')tjr,"
				  + "(select tjsj from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.xq and b.tjxm='xfjm2')tjsj "
				  +	" from (select rownum r,'' disabled,";
		}

		
		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
						+ actionForm.getPkcolumn() + " pk,xn,xq,xqmc,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzjmje from (" +
								"select distinct xn,xq,xqmc,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
								"(select count(*) from view_xszz_com_xfjm2 b where a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq and b.fdysh='通过')rs," +
								"(select sum(pzjmje) from view_xszz_com_xfjm2 b where a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq and b.fdysh='通过')pzjmje " +
								"from view_xszz_com_xfjm2 a where fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.xq=b.zjz and b.tjxm='xfjm2' and b.tjzt<>'2')" +
								")a where 1=1";
			}else{
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					   + actionForm.getPkcolumn() + " pk,xn,xq,xqmc,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzjmje from (" +
							"select distinct xn,xq,xqmc,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
							"(select count(*) from view_xszz_com_xfjm2 b where a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq)rs," +
							"(select sum(pzjmje) from view_xszz_com_xfjm2 b where a.bjdm=b.bjdm and a.xn=b.xn and a.xq=b.xq)pzjmje " +
							"from view_xszz_com_xfjm2 a" +
							")a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc", "xymc", "rs", "pzjmje", "tjr", "tjsj", "xxsh" };
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
				   + actionForm.getPkcolumn() + " pk,xn,xq,xqmc,xydm,xymc,xxsh,rs,pzjmje from (" +
				   		"select distinct xn,xq,xqmc,xydm,xymc,xxsh," +
				   		"(select count(*) from view_xszz_com_xfjm2 b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and b.xysh='通过')rs," +
				   		"(select sum(pzjmje) from view_xszz_com_xfjm2 b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and b.xysh='通过')pzjmje " +
				   		"from view_xszz_com_xfjm2 a where xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and a.xn||a.xq=b.zjz and b.tjxm='xfjm2' and b.tjzt<>'2')" +
				   		")a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ")a)", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ")a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	
	/**
	 * 学费减免2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfjm2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_xfjm2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存学费减免2审核信息，成功返回TRUE，反之返回FALSE saveXfjm2Shxx ---- 保存学费减免2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjm2Shxx(Xfjm2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xq = Base.chgNull(model.getXq(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String pzjmje = Base.chgNull(model.getPzjmje(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isXfjm2DataCf(xh, xn, xq);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_xfjm2", new String[] {
					"xxsh", "xxshyj", "xxshsj", "pzjmje" }, new String[] {
					xxsh, xxshyj, now, pzjmje }, "xn||xq||xh", xn + xq + xh,
					request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						if(!Base.isNull(pzjmje)){
							bFlag = StandardOperation.update("xszz_com_xfjm2",
									new String[] { "fdysh", "fdyshyj", "fdyshsj",
									               "pzjmje"},
									new String[] { fdysh, fdyshyj, now, pzjmje },
									"xn||xq||xh", xn + xq + xh, request);
						}else{
							bFlag = StandardOperation.update("xszz_com_xfjm2",
									new String[] { "fdysh", "fdyshyj", "fdyshsj" },
									new String[] { fdysh, fdyshyj, now },
									"xn||xq||xh", xn + xq + xh, request);
						}
					}
				} else {
					if(!Base.isNull(pzjmje)){
						bFlag = StandardOperation.update("xszz_com_xfjm2",
								new String[] { "xysh", "xyshyj", "xyshsj","pzjmje" },
								new String[] { xysh, xyshyj, now, pzjmje }, "xn||xq||xh",
								xn + xq + xh, request);
					}else{
						bFlag = StandardOperation.update("xszz_com_xfjm2",
								new String[] { "xysh", "xyshyj", "xyshsj" },
								new String[] { xysh, xyshyj, now }, "xn||xq||xh",
								xn + xq + xh, request);
					}
				}
			}
		}

		return bFlag;
	}
	
	
	
	/**
	 * 获取学费缓交1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxfs,hjqx,hjje,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_xfhj1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "lxfs", "hjqx", "hjje", "sqly", "sqsj",
				"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
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
	 * 判断学费缓交1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isxfhj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isXfhj1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_xfhj1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_xfhj1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_xfhj1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存学费缓交1信息，成功返回TRUE，反之返回FALSE saveXfhj1Sqxx ---- 保存学费缓交1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Sqxx(Xfhj1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxfs = Base.chgNull(model.getLxfs(), "", 1);
		String hjqx = Base.chgNull(model.getHjqx(), "", 1);
		String hjje = Base.chgNull(model.getHjje(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isXfhj1DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_xfhj1", new String[] {
					"xn", "xh", "lxfs", "hjqx", "hjje", "sqly" }, new String[] {
					xn, xh, lxfs, hjqx, hjje, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_xfhj1", new String[] {
					"lxfs", "hjqx", "hjje", "sqly", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj" }, new String[] { lxfs, hjqx, hjje, sqly,
					"未审核", "", "", "未审核", "", "", "未审核", "", "", now },
					"xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除学费缓交1信息 delXfhj1xx ---- 删除学费缓交1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfhj1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_xfhj1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_xfhj1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_xfhj1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费缓交1审核结果 modXfhj1xx ---- 批量修改学费缓交1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfhj1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_xfhj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_xfhj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_xfhj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 学费缓交1查询结果 Xfhj1res ---- 学费缓交1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjje,hjqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "hjje", "hjqx", "sqsj", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "hjje",
				"hjqx", "sqsj", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费缓交1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_xfhj1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存学费缓交1审核信息，成功返回TRUE，反之返回FALSE saveXfhj1Shxx ---- 保存学费缓交1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj1Shxx(Xfhj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isXfhj1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_xfhj1", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_xfhj1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_xfhj1",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取广州大学欠费信息
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public String[] getGzdxQfxx(String pkVal) throws Exception {
		return dao.getOneRs(
				"select qxfje,qzsfje from xg_bks_xscjfmd where xn||xh=?",
				new String[] { pkVal }, new String[] { "qxfje", "qzsfje" });
	}
	
	/**
	 * 获取广州大学欠费信息
	 * 
	 * @author luojw
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGzdxQfxx(String xh, String[] coList)
			throws Exception {

		String sql = "select * from xg_bks_xscjfmd where xh=?";
		return dao.getList(sql, new String[]{xh},coList);
	}
	
	
	/**
	 * 获取学费缓交2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhj2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		// --------------2010/5/27 edit by luojw ---------------
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,"
				+ "hjyy,hjxf,hjzsf,hjxzfqx,sfhdgjzxdk,ywsqgjzxdk,sfkns,sqsj,fdysh,fdyshsj,fdyshyj,"
				+ "xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,hjqtfy from view_xszz_com_xfhj2 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "hjyy", "hjxf", "hjzsf", "hjxzfqx",
				"sfhdgjzxdk", "ywsqgjzxdk", "sfkns", "sqsj", "fdysh",
				"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
				"xxshsj", "xxshyj", "hjqtfy" };
		// --------------end---------------
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
	 * 判断学费缓交2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isxfhj2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isXfhj2DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_xfhj2 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_xfhj2 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_xfhj2 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存学费缓交2信息，成功返回TRUE，反之返回FALSE saveXfhj2Sqxx ---- 保存学费缓交2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Sqxx(Xfhj2Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String hjyy = Base.chgNull(model.getHjyy(), "", 1);
		String hjxf = Base.chgNull(model.getHjxf(), "", 1);
		String hjzsf = Base.chgNull(model.getHjzsf(), "", 1);
		String hjxzfqx = Base.chgNull(model.getHjxzfqx(), "", 1);
		String sfhdgjzxdk = Base.chgNull(model.getSfhdgjzxdk(), "", 1);
		String ywsqgjzxdk = Base.chgNull(model.getYwsqgjzxdk(), "", 1);
		String sfkns = Base.chgNull(model.getSfkns(), "", 1);
		String now = GetTime.getSystemTime();

		// --------------2010/5/27 edit by luojw --------------
		// 缓缴其他费用
		String hjqtfy = Base.chgNull(model.getHjqtfy(), "", 1);

		String sHave = isXfhj2DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_xfhj2", new String[] {
					"xn", "xh", "hjyy", "hjxf", "hjzsf", "hjxzfqx",
					"sfhdgjzxdk", "ywsqgjzxdk", "sfkns", "hjqtfy" },
					new String[] { xn, xh, hjyy, hjxf, hjzsf, hjxzfqx,
							sfhdgjzxdk, ywsqgjzxdk, sfkns, hjqtfy }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_xfhj2", new String[] {
					"hjyy", "hjxf", "hjzsf", "hjxzfqx", "sfhdgjzxdk",
					"ywsqgjzxdk", "sfkns", "fdysh", "fdyshsj", "fdyshyj",
					"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj",
					"sqsj", "hjqtfy" }, new String[] { hjyy, hjxf, hjzsf,
					hjxzfqx, sfhdgjzxdk, ywsqgjzxdk, sfkns, "未审核", "", "",
					"未审核", "", "", "未审核", "", "", now, hjqtfy }, "xn||xh", xn
					+ xh, request);
		}
		// --------------end --------------
		return bFlag;
	}
	
	/**
	 * 删除学费缓交2信息 delXfhj2xx ---- 删除学费缓交2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfhj2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_xfhj2 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_xfhj2 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_xfhj2 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改学费缓交2审核结果 modXfhj2xx ---- 批量修改学费缓交2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfhj2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_xfhj2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_xfhj2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_xfhj2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 学费缓交2查询结果 Xfhj2res ---- 学费缓交2结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm,
			String shjb) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,hjxf,hjzsf,hjxzfqx,sqsj,fdysh,xysh,xxsh from view_xszz_com_xfhj2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "hjxf", "hjzsf", "hjxzfqx", "sqsj",
				"fdysh", "xysh", "xxsh" } : new String[] { "disabled",
				"bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh", "xymc",
				"zymc", "bjmc", "hjxf", "hjzsf", "hjxzfqx", "sqsj", "xysh",
				"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费缓交2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXfhj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_xfhj2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 学费缓交2查询无故欠费名单结果 getXfhj2WgqfRes ---- 学费缓交2无故欠费名单结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2WgqfRes(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xymc,zymc,xh,xm,qxfqj,qxfzje from view_xg_bks_wgqxfmd where 1=1";
		
		QueryModel model = new QueryModel();
		model.setQxfqj(queryModel.getXn());
		
		StringBuffer whereSql = getWhereSql(model, request, "3"
				.equalsIgnoreCase(shjb));
		sql += whereSql + " order by qxfqj,xydm,zydm,bjdm,xh";
		String[] colList = new String[] { "xymc", "zymc", "xh", "xm", "qxfqj", "qxfzje" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 学费缓交2无学费缓缴及无故欠费统计结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhj2HjtjRes(String xn) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		dao.getProVal("{call pro_gzdx_xfhjtj(?)}",
				new String[] { xn }, new int[] {});
		// ----------2010/5/27 edit by luojw ----------------
		String sql = "select xh,xy,tgsqzrs,tghjsqdfknsrs,wgqfrs, qfrs, bz from view_xszz_hjqfmd";
		// -------end ------------------
		String[] colList = new String[] { "xh", "xy", "tgsqzrs", "tghjsqdfknsrs",
				"wgqfrs","qfrs", "bz" };

		resList = dao.rsToVator(sql, new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 保存学费缓交2审核信息，成功返回TRUE，反之返回FALSE saveXfhj2Shxx ---- 保存学费缓交2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhj2Shxx(Xfhj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isXfhj2DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_xfhj2", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_xfhj2",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_xfhj2",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取困难生临时补助1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnslsbz1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,mzmc,zzmmmc,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,sqje,sqly,sqsj,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_com_knslsbz1 where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "xz", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "kndj", "sqje", "sqly", "sqsj", "fdysh",
				"fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh",
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
	 * 判断困难生临时补助1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknslsbz1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isKnslsbz1DataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knslsbz1 where xh = ? and xn = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knslsbz1 where xh = ? and xn = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_knslsbz1 where xh = ? and xn = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存困难生临时补助1信息，成功返回TRUE，反之返回FALSE saveKnslsbz1Sqxx ---- 保存困难生临时补助1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Sqxx(Knslsbz1Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isKnslsbz1DataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knslsbz1", new String[] {
					"xn", "xh", "sqje", "sqly" }, new String[] { xn, xh, sqje,
					sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knslsbz1", new String[] {
					"sqje", "sqly", "fdysh", "fdyshsj", "fdyshyj", "xysh",
					"xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj" },
					new String[] { sqje, sqly, "未审核", "", "", "未审核", "", "",
							"未审核", "", "", now }, "xn||xh", xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 得到困难生临时补助1打印信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getKnslsbz1Dyxx(String pkVal) throws Exception {
		String[] pkT = pkVal.split("@");
		StringBuffer sql = new StringBuffer("select xymc,bjmc,xm,xh,kndj,sqly,sqje from view_xszz_com_knslsbz1 where xn||xh in ('#'");
		
		for (int i = 0; i < pkT.length; i++) {
			sql.append(",'");
			sql.append(pkT[i]);
			sql.append("'");
		}
		sql.append(")");
		
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql
				.toString(), new String[] {}, new String[] { "xymc", "bjmc",
				"xm", "xh", "kndj", "sqly", "sqje" });
		return list;
	}
	
	/**
	 * 删除困难生临时补助1信息 delKnslsbz1xx ---- 删除困难生临时补助1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnslsbz1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knslsbz1 where xn||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knslsbz1 where xn||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_knslsbz1 where xn||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改困难生临时补助1审核结果 modKnslsbz1xx ---- 批量修改困难生临时补助1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnslsbz1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knslsbz1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_knslsbz1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_knslsbz1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 国家奖学1查询结果 Knslsbz1res ---- 国家奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnslsbz1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from view_xszz_com_knslsbz1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from view_xszz_com_knslsbz1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from view_xszz_com_knslsbz1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from view_xszz_com_knslsbz1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,kndj,sqje,sqsj,fdysh,xysh,xxsh from view_xszz_com_knslsbz1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "kndj", "sqje", "sqsj", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "kndj",
				"sqje", "sqsj", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 困难生临时补助1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnslsbz1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_knslsbz1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存困难生临时补助1审核信息，成功返回TRUE，反之返回FALSE saveKnslsbz1Shxx ---- 保存困难生临时补助1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnslsbz1Shxx(Knslsbz1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isKnslsbz1DataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knslsbz1", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_knslsbz1",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "xn||xh",
								xn + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_knslsbz1",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "xn||xh", xn
									+ xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取临时补助2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xq,xqmc,xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc," +
				     "bjdm,bjmc,jtzz,sqje,sqly,sqsj,pzje,fdysh,fdyshsj,fdyshyj," +
				     "xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj," +
				     "(select tjzt from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm" +
				     " and b.zjz=a.xn||a.xq and b.tjxm='lsbz2')tjzt from view_xszz_com_knslsbz2 a where xn||xq||xh = ?";
		String[] colList = new String[] { "xn", "xq", "xqmc", "xh", "xm", "xb",
				"nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"jtzz", "sqje", "sqly", "sqsj", "pzje", "fdysh", "fdyshsj",
				"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
				"xxshyj", "tjzt" };
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
	 * 判断临时补助2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 islsbz2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isLsbz2DataCf(String xh, String xn, String xq) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断临时补助2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 islsbz2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isLsbz2DataCfSq(String xh, String xn, String xq, String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){//三级审核
					sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ? and fdysh='通过'";
					num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_knslsbz2 where xh = ? and xn = ? and xq = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn, xq }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存临时补助2信息，成功返回TRUE，反之返回FALSE saveLsbz2Sqxx ---- 保存临时补助2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Sqxx(Lsbz2Model model, HttpServletRequest request, String shjb)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xq = Base.chgNull(model.getXq(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isLsbz2DataCfSq(xh, xn, xq, shjb);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_knslsbz2", new String[] {
					"xn", "xq", "xh", "jtzz", "sqly", "sqje" }, new String[] {
					xn, xq, xh, jtzz, sqly, sqje }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_knslsbz2", new String[] {
					"jtzz", "sqly", "sqje", "fdysh", "fdyshsj", "fdyshyj",
					"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj",
					"pzje", "sqsj" }, new String[] { jtzz, sqly, sqje, "未审核",
					"", "", "未审核", "", "", "未审核", "", "", "", now }, "xn||xh",
					xn + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除临时补助2信息 delLsbz2xx ---- 删除临时补助2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsbz2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_knslsbz2 where xn||xq||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_knslsbz2 where xn||xq||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_knslsbz2 where xn||xq||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改临时补助2审核结果 modLsbz2xx ---- 批量修改临时补助2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knslsbz2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_knslsbz2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_knslsbz2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 临时补助1查询结果 Lsbz2res ---- 临时补助1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,sqje,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje",
				"fdysh", "xysh", "xxsh" } : new String[] { "disabled",
				"bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje", "xysh",
				"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 临时补助2查询结果 getLsbz2ResByFdy ---- 临时补助2结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";
		sql = "select disabled,bgcolor,pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from (select rownum r,";
		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.xq=b.zjz and b.tjxm='lsbz2')='1' then 'disabled' else '' end) disabled,";
		sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xq||xh pk,xn,xqmc,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 a where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje",
				"fdysh", "xysh", "xxsh" } : new String[] { "disabled",
				"bgcolor", "pk", "xn", "xqmc", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje", "xysh",
				"xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	
	/**
	 * 临时补助2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbz2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_knslsbz2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存临时补助2审核信息，成功返回TRUE，反之返回FALSE saveLsbz2Shxx ---- 保存临时补助2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbz2Shxx(Lsbz2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xq = Base.chgNull(model.getXq(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String pzje = Base.chgNull(model.getPzje(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isLsbz2DataCf(xh, xn, xq);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_knslsbz2", new String[] {
					"xxsh", "xxshyj", "xxshsj", "pzje" }, new String[] {
					xxsh, xxshyj, now, pzje }, "xn||xq||xh", xn + xq + xh,
					request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						if(!Base.isNull(pzje)){
							bFlag = StandardOperation.update("xszz_com_knslsbz2",
									new String[] { "fdysh", "fdyshyj", "fdyshsj",
									               "pzje" },
									new String[] { fdysh, fdyshyj, now, pzje },
									"xn||xq||xh", xn + xq + xh, request);
						}else{
							bFlag = StandardOperation.update("xszz_com_knslsbz2",
									new String[] { "fdysh", "fdyshyj", "fdyshsj" },
									new String[] { fdysh, fdyshyj, now },
									"xn||xq||xh", xn + xq + xh, request);
						}
					}
				} else {
					if(!Base.isNull(pzje)){
						bFlag = StandardOperation.update("xszz_com_knslsbz2",
								new String[] { "xysh", "xyshyj", "xyshsj", "pzje" },
								new String[] { xysh, xyshyj, now, pzje }, "xn||xq||xh",
								xn + xq + xh, request);
					}else{
						bFlag = StandardOperation.update("xszz_com_knslsbz2",
								new String[] { "xysh", "xyshyj", "xyshsj" },
								new String[] { xysh, xyshyj, now }, "xn||xq||xh",
								xn + xq + xh, request);
					}
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取临时补助2学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 a where xn||xq||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='bj' and b.tjxm='lsbz2' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,fdysh,xysh,xxsh from view_xszz_com_knslsbz2 a where xn||xq||bjdm=?";
			}
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqje", "pzje", "fdysh","xysh","xxsh"};
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,xysh,xxsh from view_xszz_com_knslsbz2 a where xn||xq||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='xy' and b.tjxm='lsbz2' and a.xydm=b.dm)";
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqje", "pzje", "xysh","xxsh"};
		}
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取临时补助2审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbz2shtjjg(String pkVal,String userType,String shjb) throws Exception {
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_knslsbz2 a where xn||xq||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='bj' and b.tjxm='lsbz2' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_knslsbz2 a where xn||xq||bjdm=?";
			}
		}else{
			sql = "select xxshyj shyj from view_xszz_com_knslsbz2 a where xn||xq||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.xq and b.bm='xy' and b.tjxm='lsbz2' and a.xydm=b.dm)";
		}
		return dao.getMap(sql, new String[] { pkVal }, new String[]{"shyj"});
	}

	/**
	 * 批量修改临时补助2审核结果 modLsbz2xxBybj ---- 批量修改临时补助2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xxBybj(XszzCommonN05ActionForm actionForm, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = actionForm.getPk();
		String shjg = actionForm.getShjg();
		String shjb = actionForm.getShjb();
		String shyj = actionForm.getShyj();
		String[] sqlT = new String[pkT.length];
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_knslsbz2 a set xxsh='未审核',xxshsj='',pzje='',xxshyj='' where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and xysh='通过'";
				}else{
					sqlT[i] = "update xszz_com_knslsbz2 a set xxsh='"
						+shjg
						+"',xxshsj=to_char(sysdate,'yyyy-mm-dd'),xxshyj='" 
						+ shyj
						+ "' where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and xysh='通过'";
				}
			} else {//学院审核
				if ("退回".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){					
						sqlT[i] = "update xszz_com_knslsbz2 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_knslsbz2 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh)";
					}
				}else{
					if("3".equalsIgnoreCase(shjb)){					
						sqlT[i] = "update xszz_com_knslsbz2 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj 
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_knslsbz2 a set xysh='"
							+ shjg
							+ "',xyshyj='"
							+ shyj
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_knslsbz2 b where b.xn||b.xq||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh)";
					}
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改临时补助2审核结果 modLsbz2Bmshtjb ---- 批量修改临时补助2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2Bmshtjb(XszzCommonN05ActionForm actionForm, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = actionForm.getPk();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		String shjg = actionForm.getShjg();
		String shyj = actionForm.getShyj();
		String tjr = actionForm.getUserName();
		
		shyj = Base.isNull(shyj) ? "" : shyj;
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_knslsbz2 b where a.zjz=b.xn||b.xq and a.dm=b.xydm and b.xn||b.xq||b.xydm='" + pkT[i] + "') and a.bm='xy' and a.tjxm='lsbz2'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct xydm,'xy','xn||xq',xn||xq,'lsbz2','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_knslsbz2 where xn||xq||xydm='" + pkT[i] + "')";
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_knslsbz2 b where a.zjz=b.xn||b.xq and a.dm=b.bjdm and b.xn||b.xq||b.bjdm='" + pkT[i] + "') and a.bm='bj' and a.tjxm='lsbz2'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct bjdm,'bj','xn||xq',xn||xq,'lsbz2','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_knslsbz2 where xn||xq||bjdm='" + pkT[i] + "')";
				}
			}
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	
	/**
	 * 批量修改临时补助2审核结果 modLsbz2xxByDepModel ---- 批量修改临时补助2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbz2xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		boolean isFdyqx = false;
		if ("3".equalsIgnoreCase(shjb)) {
			isFdyqx = request.getSession().getAttribute("fdyQx").equals(true) ? true : false;
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_knslsbz2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
						+ pkT[i] + "'";
			} else {
				if (isFdyqx) {
					sqlT[i] = "update xszz_com_knslsbz2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "'";
				} else {
					sqlT[i] = "update xszz_com_knslsbz2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||xq||xh='"
							+ pkT[i] + "'";
				}
			}
		}
		dao.runBatch(sqlT);
	}


	/**
	 * 临时补助1查询结果 getLsbz2ResForDM ---- 临时补助1结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbz2ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc", "bjmc", "rs", "pzje", "tjr", "tjsj", "xysh", "xxsh" };
	
		sql = "select disabled,bgcolor,pk,xn,xqmc,bjmc,xysh,xxsh,rs,pzje," 
			  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.xq and b.tjxm='lsbz2')tjr," 
			  + "(select tjsj from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.xq and b.tjxm='lsbz2')tjsj from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn||a.xq and b.tjxm='lsbz2') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xqmc,xymc,xxsh,rs,pzje," 
				  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.xq and b.tjxm='lsbz2')tjr,"
				  + "(select tjsj from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.xq and b.tjxm='lsbz2')tjsj "
				  +	" from (select rownum r,'' disabled,";
		}
		
		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					   + actionForm.getPkcolumn() + " pk,xn,xq,xqmc,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzje from (" +
					   		"select distinct xn,xq,xqmc,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
					   		"(select count(*) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm and b.fdysh='通过')rs," +
					   		"(select sum(pzje) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm and b.fdysh='通过')pzje " +
					   		"from view_xszz_com_knslsbz2 a where fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.xq=b.zjz and b.tjxm='lsbz2' and b.tjzt<>'2')" +
					   ")a where 1=1";
			}else{
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
					    + actionForm.getPkcolumn() + " pk,xn,xq,xqmc,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzje " +
					    		"from (select distinct xn,xq,xqmc,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
					    		"(select count(*) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm)rs," +
					    		"(select sum(pzje) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm)pzje" +
					    		" from view_xszz_com_knslsbz2 a" +
					    ") a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xqmc", "xymc", "rs", "pzje", "tjr", "tjsj", "xxsh" };
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," 
				   + actionForm.getPkcolumn() + " pk,xn,xq,xqmc,xydm,xymc,xysh,xxsh,rs,pzje from (" +
				   		"select distinct xn,xq,xqmc,xydm,xymc,xysh,xxsh," +
				   		"(select count(*) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.xydm=b.xydm and b.xysh='通过')rs," +
				   		"(select sum(pzje) from view_xszz_com_knslsbz2 b where a.xn=b.xn and a.xq=b.xq and a.xydm=b.xydm and b.xysh='通过')pzje " +
				   		"from view_xszz_com_knslsbz2 a where xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and a.xn||a.xq=b.zjz and b.tjxm='lsbz2' and b.tjzt<>'2')" +
				   ")a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ")a)", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ")a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 根据项目代码得到外设助学金项目名称
	 * 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 */
	public String getWszxjmc(String xmdm) throws Exception {
		return dao.getOneRs("select xmmc from xszz_com_wszxjdmb where xmdm=?", new String[]{xmdm}, "xmmc");
	}
	
	/**
	 * 获取外设助学金1详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh," +
				     "xxshsj,xxshyj,xn,zxjdm,zxjmc,xh,xm,xb,csrq,nj,sfzh,xydm," +
				     "xymc,zydm,zymc,bjdm,bjmc,jg,pxqk,jtxxdz,lxdh,jtjxxqkknzy," +
				     "sqje,sqsj,pzje," +
				     "(select tjzt from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm" +
				     " and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1')tjzt " +
				     "from view_xszz_com_wszxj1 a where xn||zxjdm||xh = ?";
		String[] colList = new String[] { "fdysh", "fdyshsj", "fdyshyj",
				"xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj", "xxshyj", "xn",
				"zxjdm", "zxjmc", "xh", "xm", "xb", "csrq", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "jg", "pxqk",
				"jtxxdz", "lxdh", "jtjxxqkknzy", "sqje", "sqsj", "pzje", "tjzt" };
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
	 * 判断外设助学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 iswszxj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param zxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isWszxj1DataCf(String xh, String xn, String zxjdm) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 判断外设助学金1是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 iswszxj1datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param zxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isWszxj1DataCfSq(String xh, String xn, String zxjdm, String shjb) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "2";
			} else {
				if("3".equalsIgnoreCase(shjb)){//三级审核
					sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ? and fdysh='通过'";
					num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "2";
					}else{
						sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ?";
						num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
						if (!num.equalsIgnoreCase("0")) {
							sFlag = "1";
						}
					}
				}else{
					sql = "select count(*) num from xszz_com_wszxj1 where xh = ? and xn = ? and zxjdm = ?";
					num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
					if (!num.equalsIgnoreCase("0")) {
						sFlag = "1";
					}
				}
				
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存外设助学金1信息，成功返回TRUE，反之返回FALSE saveWszxj1Sqxx ---- 保存外设助学金1申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Sqxx(Wszxj1Model model, HttpServletRequest request, String shjb)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjmc = getWszxjmc(zxjdm);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String pxqk = Base.chgNull(model.getPxqk(), "", 1);
		String jtxxdz = Base.chgNull(model.getJtxxdz(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String jtjxxqkknzy = Base.chgNull(model.getJtjxxqkknzy(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isWszxj1DataCfSq(xh, xn, zxjdm,shjb);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_wszxj1", new String[] {
					"xn", "xh", "zxjdm", "zxjmc", "jg", "pxqk", "jtxxdz",
					"lxdh", "jtjxxqkknzy", "sqje" }, new String[] { xn, xh,
					zxjdm, zxjmc, jg, pxqk, jtxxdz, lxdh, jtjxxqkknzy, sqje },
					request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_wszxj1", new String[] {
					"zxjmc", "jg", "pxqk", "jtxxdz", "lxdh", "jtjxxqkknzy",
					"sqje", "fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj",
					"xyshyj", "xxsh", "xxshsj", "xxshyj", "sqsj", "pzje" },
					new String[] { zxjmc, jg, pxqk, jtxxdz, lxdh, jtjxxqkknzy,
							sqje, "未审核", "", "", "未审核", "", "", "未审核", "", "",
							now, "" }, "xn||zxjdm||xh", xn + zxjdm + xh,
					request);
		}
		return bFlag;
	}
	
	/**
	 * 删除外设助学金1信息 delWszxj1xx ---- 删除外设助学金1信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delWszxj1xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_wszxj1 where xn||zxjdm||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_wszxj1 where xn||zxjdm||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_wszxj1 where xn||zxjdm||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改外设助学金1审核结果 modWszxj1xx ---- 批量修改外设助学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_wszxj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_wszxj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_wszxj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 外设奖学1查询结果 Wszxj1res ---- 外设奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
				"sqsj", "pzje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 外设奖学1查询结果 getWszxj1ResByFdy ---- 外设奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResByFdy(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "";
		sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from (select rownum r,";
		sql += "(case when (select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.zxjdm=b.zjz and b.tjxm='wszxj1')='1' then 'disabled' else '' end) disabled,";
		sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj1 a where 1=1";
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
				"sqsj", "pzje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 外设助学金1信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj1ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_wszxj1 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存外设助学金1审核信息，成功返回TRUE，反之返回FALSE saveWszxj1Shxx ---- 保存外设助学金1审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj1Shxx(Wszxj1Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String pzje = Base.chgNull(model.getPzje(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isWszxj1DataCf(xh, xn, zxjdm);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_wszxj1", new String[] {
					"pzje", "xxsh", "xxshyj", "xxshsj" }, new String[] { pzje,
					xxsh, xxshyj, now }, "xn||zxjdm||xh", xn + zxjdm + xh,
					request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						if(!Base.isNull(pzje)){
							bFlag = StandardOperation.update("xszz_com_wszxj1",
									new String[] { "fdysh", "fdyshyj", "fdyshsj",
									               "pzje"},
									new String[] { fdysh, fdyshyj, now, pzje },
									"xn||zxjdm||xh", xn + zxjdm + xh, request);
						}else{
							bFlag = StandardOperation.update("xszz_com_wszxj1",
									new String[] { "fdysh", "fdyshyj", "fdyshsj" },
									new String[] { fdysh, fdyshyj, now },
									"xn||zxjdm||xh", xn + zxjdm + xh, request);
						}
					}
				} else {
					if(!Base.isNull(pzje)){
						bFlag = StandardOperation.update("xszz_com_wszxj1",
								new String[] { "xysh", "xyshyj", "xyshsj", "pzje" },
								new String[] { xysh, xyshyj, now, pzje },
								"xn||zxjdm||xh", xn + zxjdm + xh, request);
					}else{
						bFlag = StandardOperation.update("xszz_com_wszxj1",
								new String[] { "xysh", "xyshyj", "xyshsj" },
								new String[] { xysh, xyshyj, now },
								"xn||zxjdm||xh", xn + zxjdm + xh, request);
					}
					
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获取外设助学金1学生审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @param shjb
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1Stush(String pkVal,String userType,String shjb) throws Exception {
		String[] colList = null;
		String sql = "";
		
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,xysh,xxsh from view_xszz_com_wszxj1 a where xn||zxjdm||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.zxjdm and b.bm='bj' and b.tjxm='wszxj1' and a.bjdm=b.dm)";
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,xysh,xxsh from view_xszz_com_wszxj1 a where xn||zxjdm||bjdm=?";
			}
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqje", "pzje", "xysh", "xxsh"};
		}else{
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sqsj,sqje,pzje,xxsh from view_xszz_com_wszxj1 a where xn||zxjdm||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.zxjdm and b.bm='xy' and b.tjxm='wszxj1' and a.xydm=b.dm)";
			colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sqsj", "sqje", "pzje", "xxsh"};
		}
		
		return dao.rsToVator(sql, new String[] { pkVal}, colList);
	}

	/**
	 * 获取外设助学金1审核结果详细信息
	 * 
	 * @param pkVal
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj1shtjjg(String pkVal,String userType, String shjb) throws Exception {
		String sql = "";
		if("xy".equalsIgnoreCase(userType)){
			if("3".equalsIgnoreCase(shjb)){
				sql = "select xyshyj shyj from view_xszz_com_wszxj1 a where xn||zxjdm||bjdm=? and fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.zxjdm and b.bm='bj' and b.tjxm='wszxj1' and a.bjdm=b.dm)";
			}else{
				sql = "select xyshyj shyj from view_xszz_com_wszxj1 a where xn||zxjdm||bjdm=?";
			}
		}else{
			sql = "select xxshyj shyj from view_xszz_com_wszxj1 a where xn||zxjdm||xydm=? and xysh='通过' and exists(select 1 from xszz_com_bmshtjb b where b.zjz=a.xn||a.zxjdm and b.bm='xy' and b.tjxm='wszxj1' and a.xydm=b.dm)";
		}
		return dao.getMap(sql, new String[] { pkVal}, new String[]{"shyj"});
	}

	/**
	 * 批量修改外设助学金1审核结果 modWszxj1xxBybj ---- 批量修改外设助学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xxBybj(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shjb = model.getShjb();
		String shyj = model.getShyj();
		String[] sqlT = new String[pkT.length];
		shyj = Base.isNull(shyj) ? "" : shyj;
		
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {//学校审核
				if("退回".equalsIgnoreCase(shjg)){
					sqlT[i] = "update xszz_com_wszxj1 a set xxsh='未审核',xxshsj='',xxshyj='' where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh) and xysh='通过'";
				}else{
					sqlT[i] = "update xszz_com_wszxj1 a set xxsh='"
						+ shjg
						+ "',xxshyj='" 
						+ shyj 
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.xydm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh) and xysh='通过'";
				}
				
			} else {//学院审核
				if("退回".equalsIgnoreCase(shjg)){
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_wszxj1 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_wszxj1 a set xysh='未审核',xyshsj='',xyshyj='' where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh)";
					}
				}else{
					if("3".equalsIgnoreCase(shjb)){
						sqlT[i] = "update xszz_com_wszxj1 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj 
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh) and fdysh='通过'";
					}else{
						sqlT[i] = "update xszz_com_wszxj1 a set xysh='"
							+ shjg
							+ "',xyshyj='" 
							+ shyj
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where exists(select 1 from view_xszz_com_wszxj1 b where b.xn||b.zxjdm||b.bjdm='" + pkT[i] + "' and a.xn=b.xn and a.zxjdm=b.zxjdm and a.xh=b.xh)";
					}
				}
				
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改外设助学金1审核结果 modWszxj1Bmshtjb ---- 批量修改外设助学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1Bmshtjb(XszzCommonN05ActionForm model, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType") .toString();
		String[] pkT = model.getPk();
		String shjg = model.getShjg();
		String shyj = model.getShyj();
		String tjr = model.getUserName();
		String[] sqlT = new String[pkT.length];
		String[] delT = new String[pkT.length];
		
		String tjzt = "0";
		if("通过".equalsIgnoreCase(shjg) || "不通过".equalsIgnoreCase(shjg)){
			tjzt = "1";
		}else if("退回".equalsIgnoreCase(shjg)){
			tjzt = "2";
		}else{
			tjzt = "0";
		}
		
		if("2".equalsIgnoreCase(tjzt)){
			for (int i = 1; i < pkT.length; i++) {
				if (("admin".equalsIgnoreCase(userType))
						|| "xx".equalsIgnoreCase(userType)) {//学校审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_wszxj1 b where a.zjz=b.xn||b.zxjdm and a.dm=b.xydm and b.xn||b.zxjdm||b.xydm='" + pkT[i] + "') and a.bm='xy' and a.tjxm='wszxj1'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct xydm,'xy','xn||zxjdm',xn||zxjdm,'wszxj1','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_wszxj1 where xn||zxjdm||xydm='" + pkT[i] + "')";
				} else {//学院审核
					delT[i] = "delete from xszz_com_bmshtjb a where exists(select 1 from view_xszz_com_wszxj1 b where a.zjz=b.xn||b.zxjdm and a.dm=b.bjdm and b.xn||b.zxjdm||b.bjdm='" + pkT[i] + "') and a.bm='bj' and a.tjxm='wszxj1'";
					sqlT[i] = "insert into xszz_com_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,shyj,tjr)(select distinct bjdm,'bj','xn||zxjdm',xn||zxjdm,'wszxj1','" + tjzt + "','" + shyj + "','" + tjr + "' from view_xszz_com_wszxj1 where xn||zxjdm||bjdm='" + pkT[i] + "')";
				}
			}
			dao.runBatch(delT);
			dao.runBatch(sqlT);
		}
	}
	
	
	/**
	 * 批量修改外设助学金1审核结果 modWszxj1xxByDepModel ---- 批量修改外设助学金1审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modWszxj1xxByDepModel(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_wszxj1 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_wszxj1 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "'";
				} else {
					sqlT[i] = "update xszz_com_wszxj1 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "'";
				}
			}
		}
		dao.runBatch(sqlT);
	}


	/**
	 * 外设助学金1查询结果 getWszxj1ResForDM ---- 外设助学金1结果(按部门模式查询)
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj1ResForDM(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType").toString();
		String sql = "";
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "xn", "bjmc", "rs", "pzje", "tjr", "tjsj","xysh", "xxsh" };
	
		sql = "select disabled,bgcolor,pk,xn,bjmc,xysh,xxsh,rs,pzje," 
			  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1')tjr," 
			  + "(select tjsj from xszz_com_bmshtjb b where b.bm='bj' and b.dm=a.bjdm and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1')tjsj from (select rownum r,";

		//disabled
		if (userType.equalsIgnoreCase("xy")) {
			sql += "(case (select tjzt from xszz_com_bmshtjb b where a.xydm=b.dm and b.bm='xy' and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1') when '1' then 'disabled' else '' end) disabled,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xymc,xxsh,rs,pzje," 
				  + "(select (select xm from yhb c where c.yhm=b.tjr) from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1')tjr," 
				  + "(select tjsj from xszz_com_bmshtjb b where b.bm='xy' and b.dm=a.xydm and b.zjz=a.xn||a.zxjdm and b.tjxm='wszxj1')tjsj from (select rownum r,'' disabled,";
		}

		if (userType.equalsIgnoreCase("xy")) {//学院审核
			if("3".equalsIgnoreCase(shjb)){
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||zxjdm||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzje,zxjdm from (" +
						    "select distinct xn,zxjdm,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
						    "(select count(*) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.bjdm=b.bjdm and a.fdysh=b.fdysh and a.zxjdm=b.zxjdm)rs," +
						    "(select sum(pzje) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.bjdm=b.bjdm and a.fdysh=b.fdysh and a.zxjdm=b.zxjdm)pzje " +
					    "from view_xszz_com_wszxj1 a where fdysh='通过' and exists(select 1 from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and a.xn||a.zxjdm=b.zjz and b.tjzt<>'2' and b.tjxm='wszxj1')" +
					    ")a where 1=1";
			}else{
				sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
						"xn||zxjdm||bjdm pk,xn,bjmc,xysh,xxsh,xydm,xymc,zydm,zymc,bjdm,nj,rs,pzje,zxjdm from (" +
						    "select distinct xn,zxjdm,xydm,xymc,zydm,zymc,nj,bjdm,bjmc,xysh,xxsh," +
						    "(select count(*) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.bjdm=b.bjdm  and a.zxjdm=b.zxjdm)rs," +
						    " (select sum(pzje) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.bjdm=b.bjdm  and a.zxjdm=b.zxjdm) pzje " +
						    "from view_xszz_com_wszxj1 a" +
						    ")a where 1=1";
			}
		} else {//学校审核
			colList = new String[] { "disabled", "bgcolor", "pk", "xn", "xymc", "rs", "pzje", "tjr", "tjsj", "xxsh" };
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor," +
					"xn||zxjdm||xydm pk,xn,xydm,xymc,nj,xxsh,rs,pzje,zxjdm from (" +
					    "select distinct xn,zxjdm,xydm,xymc,nj,xysh,xxsh," +
					    "(select count(*) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.xydm=b.xydm and a.xysh=b.xysh and a.zxjdm=b.zxjdm)rs," +
					    "(select sum(pzje) from view_xszz_com_wszxj1 b where a.xn=b.xn and a.xydm=b.xydm and a.xysh=b.xysh and a.zxjdm=b.zxjdm)pzje " +
					    "from view_xszz_com_wszxj1 a where xysh='通过' " +
					    "and exists(select 1 from xszz_com_bmshtjb b " +
					        "where a.xydm=b.dm and b.bm='xy' and a.xn||a.zxjdm=b.zjz and b.tjzt<>'2' and b.tjxm='wszxj1')" +
					    ")a where 1=1";
		}
		
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		String num = dao.getOneRs("select count(*) num from (" + sql + whereSql + ")a)", values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		num = "".equalsIgnoreCase(num) || num == null ? "0" : num;
		actionForm.getPages().setMaxRecord(Integer.parseInt(num));
		sql += whereSql + ")a where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	

	/**
	 * 获取外设助学金2详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWszxj2xx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,zxjdm,zxjmc,xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,jtdz,yzbm,lxdh,bxnhdhzzz,bkks,sxnbjzhcppm,zxfscf,jtrk,jtrjysr,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_zy,jtcy1_sr,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_zy,jtcy2_sr,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_zy,jtcy3_sr,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_zy,jtcy4_sr,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_zy,jtcy5_sr,sqyy,sqje,sqsj,pzje,fdysh,fdyshsj,fdyshyj,xy_sfyknzm,xy_sfkns,xy_sxnzhcppm,xy_bjmzpytgl,xy_sftjsq,qtsm,xysh,xyshsj,xxsh,xxshsj,xxshyj from view_xszz_com_wszxj2 where xn||zxjdm||xh = ?";
		String[] colList = new String[] { "xn", "zxjdm", "zxjmc", "xh", "xm",
				"xb", "nj", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "jtdz", "yzbm", "lxdh", "bxnhdhzzz", "bkks",
				"sxnbjzhcppm", "zxfscf", "jtrk", "jtrjysr", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_gx", "jtcy1_zy", "jtcy1_sr", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_zy", "jtcy2_sr", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_zy", "jtcy3_sr", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_zy", "jtcy4_sr", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_gx", "jtcy5_zy", "jtcy5_sr", "sqyy", "sqje",
				"sqsj", "pzje", "fdysh", "fdyshsj", "fdyshyj", "xy_sfyknzm",
				"xy_sfkns", "xy_sxnzhcppm", "xy_bjmzpytgl", "xy_sftjsq",
				"qtsm", "xysh", "xyshsj", "xxsh", "xxshsj", "xxshyj" };
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
	 * 判断外设助学金2是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 iswszxj2datacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param zxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String isWszxj2DataCf(String xh, String xn, String zxjdm) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_com_wszxj2 where xh = ? and xn = ? and zxjdm = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_com_wszxj2 where xh = ? and xn = ? and zxjdm = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_com_wszxj2 where xh = ? and xn = ? and zxjdm = ?";
				num = dao.getOneRs(sql, new String[] { xh, xn, zxjdm }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * 保存外设助学金2信息，成功返回TRUE，反之返回FALSE saveWszxj2Sqxx ---- 保存外设助学金2申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Sqxx(Wszxj2Model model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjmc = Base.chgNull(model.getZxjmc(), "", 1);
		String jtdz = Base.chgNull(model.getJtdz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String bxnhdhzzz = Base.chgNull(model.getBxnhdhzzz(), "", 1);
		String bkks = Base.chgNull(model.getBkks(), "", 1);
		String sxnbjzhcppm = Base.chgNull(model.getSxnbjzhcppm(), "", 1);
		String zxfscf = Base.chgNull(model.getZxfscf(), "", 1);
		String jtrk = Base.chgNull(model.getJtrk(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_zy = Base.chgNull(model.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_zy = Base.chgNull(model.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_zy = Base.chgNull(model.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_zy = Base.chgNull(model.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_zy = Base.chgNull(model.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String sqyy = Base.chgNull(model.getSqyy(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String now = GetTime.getSystemTime();

		String sHave = isWszxj2DataCf(xh, xn, zxjdm);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_com_wszxj2", new String[] {
					"xn", "xh", "zxjdm", "zxjmc", "jtdz", "yzbm", "lxdh",
					"bxnhdhzzz", "bkks", "sxnbjzhcppm", "zxfscf", "jtrk",
					"jtrjysr", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_zy",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_zy",
					"jtcy2_sr", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_zy",
					"jtcy3_sr", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_zy",
					"jtcy4_sr", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_zy",
					"jtcy5_sr", "sqyy", "sqje" }, new String[] { xn, xh, zxjdm,
					zxjmc, jtdz, yzbm, lxdh, bxnhdhzzz, bkks, sxnbjzhcppm,
					zxfscf, jtrk, jtrjysr, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_zy, jtcy1_sr, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_zy,
					jtcy2_sr, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_zy, jtcy3_sr,
					jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_zy, jtcy4_sr, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_zy, jtcy5_sr, sqyy, sqje },
					request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_com_wszxj2", new String[] {
					"zxjmc", "jtdz", "yzbm", "lxdh", "bxnhdhzzz", "bkks",
					"sxnbjzhcppm", "zxfscf", "jtrk", "jtrjysr", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_zy", "jtcy1_sr", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_zy", "jtcy2_sr", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_zy", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_zy", "jtcy4_sr", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_zy", "jtcy5_sr", "sqyy",
					"sqje", "sqsj", "pzje", "fdysh", "fdyshsj", "fdyshyj",
					"xy_sfyknzm", "xy_sfkns", "xy_sxnzhcppm", "xy_bjmzpytgl",
					"xy_sftjsq", "qtsm", "xysh", "xyshsj", "xxsh", "xxshsj",
					"xxshyj" }, new String[] { zxjmc, jtdz, yzbm, lxdh,
					bxnhdhzzz, bkks, sxnbjzhcppm, zxfscf, jtrk, jtrjysr,
					jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_zy, jtcy1_sr, jtcy2_xm,
					jtcy2_nl, jtcy2_gx, jtcy2_zy, jtcy2_sr, jtcy3_xm, jtcy3_nl,
					jtcy3_gx, jtcy3_zy, jtcy3_sr, jtcy4_xm, jtcy4_nl, jtcy4_gx,
					jtcy4_zy, jtcy4_sr, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_zy,
					jtcy5_sr, sqyy, sqje, now, "", "未审核", "", "", "", "", "",
					"", "否", "", "未审核", "", "未审核", "", "" },
					"xn||zxjdm||xh", xn + zxjdm + xh, request);
		}
		return bFlag;
	}
	
	/**
	 * 删除外设助学金2信息 delWszxj2xx ---- 删除外设助学金2信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delWszxj2xx(String[] pkT, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_com_wszxj2 where xn||zxjdm||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_com_wszxj2 where xn||zxjdm||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_com_wszxj2 where xn||zxjdm||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 批量修改外设助学金2审核结果 modWszxj2xx ---- 批量修改外设助学金2审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modWszxj2xx(String[] pkT, String shjg, HttpServletRequest request, String shjb)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String[] sqlT = new String[pkT.length];

		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_com_wszxj2 set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_com_wszxj2 set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_com_wszxj2 set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where xn||zxjdm||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * 外设奖学1查询结果 Wszxj2res ---- 外设奖学1结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWszxj2Res(QueryModel queryModel,
			HttpServletRequest request, XszzCommonN05ActionForm actionForm, String shjb)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		String sql = "";
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from (select rownum r,";
		} else {
			sql = "select disabled,bgcolor,pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,xysh,xxsh from (select rownum r,";
		}

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj2 where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if ("3".equalsIgnoreCase(shjb)) {
					if (userBj.size() == 0) {
						sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj2 where fdysh in ('通过')";
					} else {
						sql += "(case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj2 where 1=1";
					}
				} else {
					sql += "(case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj2 where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||zxjdm||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqje,sqsj,pzje,fdysh,xysh,xxsh from view_xszz_com_wszxj2 where xysh in ('通过')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request, "3".equalsIgnoreCase(shjb));
		sql += whereSql
				+ ") where r<="
				+ (actionForm.getPages().getStart() + actionForm.getPages()
						.getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = "3".equalsIgnoreCase(shjb) ? new String[] {
				"disabled", "bgcolor", "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqje", "sqsj", "pzje", "fdysh",
				"xysh", "xxsh" } : new String[] { "disabled", "bgcolor", "pk",
				"xn", "xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqje",
				"sqsj", "pzje", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * 外设助学金2信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getWszxj2ResNum(QueryModel queryModel,
			HttpServletRequest request, String shjb) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_com_wszxj2 where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0 && "3".equalsIgnoreCase(shjb)) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request, "3"
				.equalsIgnoreCase(shjb));
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}
	
	/**
	 * 保存外设助学金2审核信息，成功返回TRUE，反之返回FALSE saveWszxj2Shxx ---- 保存外设助学金2审核信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWszxj2Shxx(Wszxj2Model model,
			HttpServletRequest request, String shjb) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String pzje = Base.chgNull(model.getPzje(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xy_sfyknzm = Base.chgNull(model.getXy_sfyknzm(), "", 1);
		String xy_sfkns = Base.chgNull(model.getXy_sfkns(), "", 1);
		String xy_sxnzhcppm = Base.chgNull(model.getXy_sxnzhcppm(), "", 1);
		String xy_bjmzpytgl = Base.chgNull(model.getXy_bjmzpytgl(), "", 1);
		String xy_sftjsq = Base.chgNull(model.getXy_sftjsq(), "", 1);
		String qtsm = Base.chgNull(model.getQtsm(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = GetTime.getSystemTime();

		String sHave = isWszxj2DataCf(xh, xn, zxjdm);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_com_wszxj2", new String[] {
					"pzje", "xxsh", "xxshyj", "xxshsj" }, new String[] { pzje,
					xxsh, xxshyj, now }, "xn||zxjdm||xh", xn + zxjdm + xh,
					request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_com_wszxj2",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now },
								"xn||zxjdm||xh", xn + zxjdm + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_com_wszxj2",
							new String[] { "xysh", "xyshsj", "xy_sfyknzm",
									"xy_sfkns", "xy_sxnzhcppm", "xy_bjmzpytgl",
									"xy_sftjsq", "qtsm" }, new String[] { xysh,
									now, xy_sfyknzm, xy_sfkns, xy_sxnzhcppm,
									xy_bjmzpytgl, xy_sftjsq, qtsm },
							"xn||zxjdm||xh", xn + zxjdm + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * 获得外设助学金列表 getWszxjJeList ---- 外设助学金列表
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getWszxjJeList(String zxjdm)
			throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String sql = "select zxjmc,xmje from xszz_com_wszxjxmxxb where xmdm=?";
		list.addAll(dao.getArrayList(sql, new String[] { zxjdm },
				new String[] { "xmje" ,"zxjmc"}));
		return list;
	}
	
	/**
	 * 获取辅导员审核提交表信息
	 * 
	 * @param String tableName
	 * @param XszzCommonN05ActionForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectFdyshtjb(String xm, String bjdm, String zjz){
		String sql = "select distinct a.bjdm,a.bjmc,decode((select tjzt from xszz_com_bmshtjb b where a.bjdm=b.dm and b.bm='bj' and b.tjxm=? and b.zjz=?),'0','未提交','1','提交','2','退回','未提交') tjzt from view_njxyzybj a where a.bjdm=?";
		HashMap<String, String> rs = dao.getMap(sql, new String[]{xm,zjz,bjdm}, new String[]{"bjdm","bjmc","tjzt"});
		return rs;
	}
	
	public boolean jlsfqbsh(String tjxm,String zjz,String pk,String pkValue,String shjb){
		String tableName = " dual ";
		String shzd = "xysh";
		String fdysh = "='通过'";
		if("knsrd1".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_knsrdb1";
			shzd = "xyshjg";
			fdysh = "<>'未审核'";
		}else if("gjzxj3".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_gjzxj3";
		}else if("gjjxj1".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_gjjxj1";
		}else if("xfjm2".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_xfjm2";
		}else if("lsbz2".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_knslsbz2";
		}else if("wszxj1".equalsIgnoreCase(tjxm)){
			tableName = "view_xszz_com_wszxj1";
		}
		String[] zjzArr = zjz.split("!!");
		zjz = "";
		for(int i=0; i<zjzArr.length; i++){
			zjz += "a." + zjzArr[i] + "||";
		}
		zjz = Base.isNull(zjz) ? "" : zjz.substring(0, zjz.length()-2);
		String sql = "";
		if("3".equalsIgnoreCase(shjb)){//三级审核
			sql = "select count(*) num from " + tableName + " a where " + pk 
            + "=? and exists(select 1 from xszz_com_bmshtjb b where b.zjz="
            + zjz 
            + " and b.tjxm='" 
            + tjxm
            +"' and b.bm='bj' and a.bjdm=b.dm and tjzt='1')"
            + " and fdysh " + fdysh +" and " + shzd + "='未审核'";
		}else{//二级审核
			sql = "select count(*) num from " + tableName + " a where " + pk
			      + "=? and " + shzd + "='未审核'";
		}
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		
		return Integer.parseInt(Base.isNull(num) ? "0" : num) > 0 ? true : false;		
	}
	
	/**
	 * 判断是否审核通过
	 * */
	public boolean checkIsShgc(String pkValue, String xm, String shjb){
		String tableName = "";
		String pk = "";
		String sql = "";
		String shzd = "";
		
		//根据审核级别判断最低级别的审核是否通过
		if("2".equalsIgnoreCase(shjb)){
			shzd = "xysh";
		}else{
			shzd = "fdysh";
		}
		//判断检测的项目
		if("xfhj3".equalsIgnoreCase(xm)){//学费缓交3
			tableName = "xszz_com_xfhj3";
			pk = "xh||xn";
		}
		if("zsfhj1".equalsIgnoreCase(xm)){//住宿费缓交1
			tableName = "xszz_com_zsfhj1";
			pk = "xh||xn";
		}
		if("xfjm3".equalsIgnoreCase(xm)){//学费减免3
			tableName = "xszz_com_xfjm3";
			pk = "xh||xn";
		}
		
		sql = StringUtils.joinStr("select count(*)num from ", 
				                  tableName, " a where ", pk, 
				                  "=? and ", shzd, "='通过'");
		return Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue}, "num")) >0 ? true : false;
	}
	/**
	 * 获得学生欠费列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXsqfxxList(String userType,XszzCommonN05ActionForm form,String[] colList) {

		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from view_XG_BKS_XSCJFMD t  where 1=1");
		
		if ("xy".equals(userType)) {
			sql.append(" and t.xydm='");
			sql.append(form.getXydm());
			sql.append("'");
		}
		
		if ("stu".equals(userType)) {
			sql.append(" and t.xh='");
			sql.append(form.getXh());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getXn())) {
			sql.append(" and t.xn='");
			sql.append(form.getXn());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and t.nj='");
			sql.append(form.getNj());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getXb())) {
			sql.append(" and t.xb='");
			sql.append(form.getXb());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and t.xydm='");
			sql.append(form.getXydm());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and t.zydm='");
			sql.append(form.getZydm());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getBjdm())) {
			sql.append(" and t.bjdm='");
			sql.append(form.getBjdm());
			sql.append("'");
		}
		
		if (!Base.isNull(form.getSfzh())) {
			sql.append(" and t.sfzj like'%");
			sql.append(form.getSfzh());
			sql.append("%'");
		}
		
		if (!Base.isNull(form.getXh())) {
			sql.append(" and t.xh like'%");
			sql.append(form.getXh());
			sql.append("%'");
		}
		
		if (!Base.isNull(form.getXm())) {
			sql.append(" and t.xm like'%");
			sql.append(form.getXm());
			sql.append("%'");
		}
		
		
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[] {}, colList);

		return list;
	}
	
	/**
	 * 查询学年项目总金额
	 * @
	 * */
	public Map<String, String> selectXnje(List<HashMap<String, String>> xnList, String xh, String xmmc){
		Map<String, String> map = new HashMap<String, String>();
		String tableName = "";
		String tj = "";
		String sql = "";
		String je = "";
		String jezd = "";
		
		if("qgzx".equalsIgnoreCase(xmmc)){//勤工助学
			tableName = "xscjffb";
			tj = "";
			jezd = "cjje";
		}else if("xfjm".equalsIgnoreCase(xmmc)){//学费减免
			tableName = "xszz_com_xfjm3";
			tj = " and xxsh='通过'";
			jezd = "jmje";
		}else if("zxj".equalsIgnoreCase(xmmc)){//助学金
			tableName = "view_nbty_xszz_jtjjkns1";
			tj = " and xxsh='通过' and bzmc='助学金'";
			jezd = "bzje";
		}else if("lsknbz".equalsIgnoreCase(xmmc)){//临时困难补助
			tableName = "view_nbty_xszz_jtjjkns1";
			tj = " and xxsh='通过' and bzmc='临时困难补助'";
			jezd = "bzje";
		}else if("zxdk".equalsIgnoreCase(xmmc)){//助学贷款
			tableName = "nbty_gjzxdksqb";
			tj = " and xxsh='通过'";
			jezd = "dkje";
		}else if("jxj".equalsIgnoreCase(xmmc)){
			tableName = "xsjxjb";
			tj = " and xxsh='通过'";
			jezd = "je";
		}else if("cszxj".equalsIgnoreCase(xmmc)){
			tableName = "nblg_zxszxj";
			tj = " and xxsh='通过'";
			jezd = "zxjje";
		}
		
		
		float zje = 0;
		for(int i=0; i<xnList.size(); i++){
			sql = "select sum(" + jezd + ") je from " + tableName +  " where xn=? and xh=? " + tj;
			je = dao.getOneRs(sql, new String[]{xnList.get(i).get("xn"),xh},"je");
			je = StringUtils.isNull(je) ? "0" : je;
			map.put(xmmc + "xn" + (i+1), je);
			
			zje += Float.parseFloat(je);
		}
		
		map.put(xmmc + "zje", zje+"");
		return map;
	}
	
	
	public static List<Map<String, String>> getKnsrd2(StringBuffer strBuffer) {
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,zymc,nj,bjmc,xxsh from view_xszz_com_knsrdb1"+strBuffer;
	    List knsrdList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm","xb","zymc","nj","bjmc","xxsh" });
	    return knsrdList;
	}
	
	public static List<Map<String, String>> getGjjxj1(StringBuffer sb) {
		DAO dao = DAO.getInstance();
		String sql = "select xm, sfzh, xymc, zymc,bjmc, xh, xb,mzmc, rxrq from view_xszz_com_gjjxj1"+sb.toString();
	    List gjjxjList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm", "sfzh", "xymc", "zymc","bjmc", "xh", "xb",
				"mzmc", "rxrq" });
	    return gjjxjList;
	}
	
	/**
	 * 获得历年资助信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLnZzInfoList(
			XszzCommonN05ActionForm model) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();

		String sql = "select t.* from view_xszz_com_wszxj2 t where t.xh = ? and t.xxsh = '通过'";

		String[] inputValue = new String[] { xh };
		String[] outputValue = new String[] { "xn","zxjmc","pzje" };

		List<HashMap<String, String>> list = dao.getList(sql, inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * 判断学生是否违纪
	 * 
	 * @author luojw
	 */
	public Boolean isWj(XszzCommonN05ActionForm model) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_wjcf ");
		sql.append("where 1 = 1 ");
		sql.append("and xn = ? ");
		sql.append("and xh = ? ");
		sql.append("and xxsh = '通过' ");

		String[] inputValue = new String[] { xn, xh };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		boolean flag = (Base.isNull(num) || "0".equalsIgnoreCase(num)) ? false
				: true;

		return flag;
		
	}
	
	/**
	 * 判断学生是否有不及格科目
	 * 
	 * @author luojw
	 */
	public Boolean isBjg(XszzCommonN05ActionForm model) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from cjb ");
		sql.append("where 1 = 1 ");
		sql.append("and xn = ? ");
		sql.append("and xh = ? ");
		sql.append("and cj < ? ");

		String[] inputValue = new String[] { xn, xh, "60" };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		boolean flag = (Base.isNull(num) || "0".equalsIgnoreCase(num)) ? false
				: true;

		return flag;
		
	}
	
	/**
	 * 获得学生课程平均成绩
	 * 
	 * @author luojw
	 */
	public String getAvgCj(XszzCommonN05ActionForm model) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();

		StringBuilder sql = new StringBuilder();
		sql.append("select round(avg(cj),2) avgcj from cjb ");
		sql.append("where 1 = 1 ");
		sql.append("and xn = ? ");
		sql.append("and xh = ? ");

		String[] inputValue = new String[] { xn, xh };
		String num = dao.getOneRs(sql.toString(), inputValue, "avgcj");

		return num;

	}
	
	/**
	 * 获得学生所有课程成绩
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<String> getXsCj(XszzCommonN05ActionForm model)
			throws SQLException {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();

		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(cj,0) cj from cjb ");
		sql.append("where 1 = 1 ");
		sql.append("and xn = ? ");
		sql.append("and xh = ? ");

		String[] inputValue = new String[] { xn, xh };
		List<String> list = dao.getList(sql.toString(), inputValue, "cj");

		return list;

	}
	
	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public String getBjrs(XszzCommonN05ActionForm model) {

		DAO dao = DAO.getInstance();

		// 学号
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from view_xsjbxx a ");
		sql.append("where a.bjdm = ");
		sql.append("(select b.bjdm from view_xsjbxx b where b.xh = ?) ");

		String[] inputValue = new String[] { xh };
		String num = dao.getOneRs(sql.toString(), inputValue, "num");

		return num;

	}
	
	/**
	 * 判断项目是否是困难生项目
	 * @param xmdm
	 * @return boolean
	 * */
	public boolean checkZzxmIsKnsxm(String xmdm){
		DAO dao = DAO.getInstance();
		String sql = "select sfkns from xszz_n05_shjb where xmmc=(select xmmc from xszz_com_wszxjdmb where xmdm=?)";
		String result = dao.getOneRs(sql, new String[]{xmdm}, "sfkns");
		return "1".equalsIgnoreCase(result);
	}
}
