
package xgxt.xszz.jhzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 金华职业技术学院学生资助DAO</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-25</p>
 */
public class XszzJhzyDAO {
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
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xh = queryModel.getXh();
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String xm = queryModel.getXm();
		String xb = queryModel.getXb();
		String sfzh = queryModel.getSfzh();
		String mzrd = queryModel.getMzrd();
		String xysh = queryModel.getXysh();
		String xxsh = queryModel.getXxsh();
		String zxjdj = queryModel.getZxjdj();

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
		if (!StringUtils.isNull(mzrd)) {
			whereSql.append(" and mzrd = ?");
			values.add(mzrd);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}// end if
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}// end if
		if (!StringUtils.isNull(zxjdj)) {
			whereSql.append(" and zxjdj = ?");
			values.add(zxjdj);
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
	 * 获取困难生详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,mzmc,zzmmmc,csrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrjnsr,zxlxdh,sqly,knlx,sqsj,fdysh,fdyshyj,fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj from view_xszz_jhzy_kns where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrjnsr", "zxlxdh", "sqly", "knlx",
				"sqsj", "fdysh", "fdyshyj", "fdyshsj", "xysh", "xyshyj",
				"xyshsj", "xxsh", "xxshyj", "xxshsj" };
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
	 * @param sUserType,userDep,xh,nd,zzType(资助名称)
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getKnsSqQx(String zzType, String sUserType, String userDep,
			String xh, String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='" + zzType
					+ "' and b.xh=? ";
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
	 * 判断困难生是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_jhzy_kns where xh = ? and nd = ? and xxsh in ('困难','特殊困难')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_jhzy_kns where xh = ? and nd = ? and xysh in ('困难','特殊困难')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_jhzy_kns where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}

	/**
	 * 保存困难生申请信息，成功返回TRUE，反之返回FALSE saveKnsSqxx ---- 保存困难生申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(saveKnsModel.getNd(), "", 1);
		String xh = Base.chgNull(saveKnsModel.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(saveKnsModel.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(saveKnsModel.getZxlxdh(), "", 1);
		String sqly = Base.chgNull(saveKnsModel.getSqly(), "", 1);
		String[] knlxList = saveKnsModel.getKnlxList();
		StringBuffer knlx = new StringBuffer();
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String[] knlxJt = { "A、来自老少边山穷地区；", "B、被当地政府列为五保户或特困户家庭；",
				"C、家庭成员中无18—55岁青壮年劳动力；", "D、属于享受最低生活保障的城镇家庭；", "E、烈士子女；",
				"F、孤儿或经济困难单亲家庭；", "G、家庭中有两个及以上成员正接受非义务教育；",
				"H、家庭成员因残疾或疾病而丧失劳动能力；", "I、家庭成员因患重大疾病需支付大额医疗费用；",
				"J、家庭及成员因遭遇突发性灾变（如自然灾害等），造成人身及财产重大损失；",
				"K、因父母离异导致家庭经济收入明显下降； ", "L、残疾学生；", "M、导致家庭经济困难的其它情况;" };
		for (int i = 0; i < knlxList.length; i++) {
			if ("1".equalsIgnoreCase(knlxList[i])) {
				knlx.append(knlxJt[i]);
			}
		}

		String sHave = isKnsDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_jhzy_kns", new String[] {
					"nd", "xh", "jtrjnsr", "zxlxdh", "sqly", "knlx" },
					new String[] { nd, xh, jtrjnsr, zxlxdh, sqly,
							knlx.toString() }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_jhzy_kns", new String[] {
					"jtrjnsr", "zxlxdh", "sqly", "knlx", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj" }, new String[] { jtrjnsr, zxlxdh, sqly,
					knlx.toString(), "未审核", "", "", "未审核", "", "", "未审核", "",
					"", now }, "nd||xh", nd + xh, request);
		}
		return bFlag;
	}

	/**
	 * 获取困难生申请表详细信息
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnssqbModel knsModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(knsModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(knsModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(knsModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(knsModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(knsModel.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(knsModel.getZzmmmc(), "", 1));
		stuList.put("mzmc", Base.chgNull(knsModel.getMzmc(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("csrq", Base.chgNull(knsModel.getCsrq(), "", 1));
		stuList.put("jtrjnsr", Base.chgNull(knsModel.getJtrjnsr(), "", 1));
		stuList.put("zxlxdh", Base.chgNull(knsModel.getZxlxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(knsModel.getSqly(), "", 1));
		stuList.put("fdysh", Base.chgNull(knsModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(knsModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(knsModel.getXysh(), "", 1));
		stuList.put("xxsh", Base.chgNull(knsModel.getXxsh(), "", 1));

		String fdysh = Base.chgNull(knsModel.getFdysh(), "", 1);
		String xysh = Base.chgNull(knsModel.getXysh(), "", 1);
		String xxsh = Base.chgNull(knsModel.getXxsh(), "", 1);

		if (!"未审核".equalsIgnoreCase(fdysh)
				&& StringUtils.isIgnoreCaseEqual(fdysh, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"未审核".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}

		return stuList;
	}

	/**
	 * 困难生查询表头 knstit ---- 困难生表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "年度",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
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
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

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
			sql += "(case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_kns where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_kns where fdysh in ('困难','特殊困难')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_kns where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('困难','特殊困难') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_kns where xysh in ('困难','特殊困难')";
			}
		}
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 困难生信息查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num ";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql += " from view_xszz_jhzy_kns where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " from view_xszz_jhzy_kns where fdysh in ('困难','特殊困难')";
				} else {
					sql += " from view_xszz_jhzy_kns where 1=1";
				}
			} else {
				sql += " from view_xszz_jhzy_kns where xysh in ('困难','特殊困难')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}

	/**
	 * 删除困难信息 delKnsxx ---- 删除困难生信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String[] pkT, HttpServletRequest request)
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
				sqlT[i] = "delete xszz_jhzy_kns where nd||xh='" + pkT[i] + "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_jhzy_kns where nd||xh='" + pkT[i]
							+ "' and xysh not in ('困难','特殊困难')";
				} else {
					sqlT[i] = "delete xszz_jhzy_kns where nd||xh='" + pkT[i]
							+ "' and xxsh not in ('困难','特殊困难')";
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
	public void modKnsxx(String[] pkT, String shjg, HttpServletRequest request)
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
				sqlT[i] = "update xszz_jhzy_kns set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_jhzy_kns set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_jhzy_kns set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xysh='未审核'";
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
	public boolean saveKnsShxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
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

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_jhzy_kns", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_jhzy_kns",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "nd||xh",
								nd + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_jhzy_kns",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj" }, new String[] { fdyshyj, xysh,
									xyshyj, now }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取困难生登记表详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,mzmc,zzmmmc,rxrq,byrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,yhkh,ykthm,jtzzjyb,qsh,xxztqk,lxdh,hhzjl,cshzcf,ywzxdkjje,ywsshzzjhe,xfjnqk,xzxfqk,hsf,qtfy,shf_jtgg,shf_qtly,jicy1_cw,jicy1_xm,jicy1_nl,jicy1_sr,jicy1_dw,jicy2_cw,jicy2_xm,jicy2_nl,jicy2_sr,jicy2_dw,jicy3_cw,jicy3_xm,jicy3_nl,jicy3_sr,jicy3_dw,jicy4_cw,jicy4_xm,jicy4_nl,jicy4_sr,jicy4_dw,jicy5_cw,jicy5_xm,jicy5_nl,jicy5_sr,jicy5_dw,jtzrks,rjnsr,jtlx,jtknqk,sqsj from view_xg_zz_knsdjb where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "rxrq", "byrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "yhkh", "ykthm", "jtzzjyb",
				"qsh", "xxztqk", "lxdh", "hhzjl", "cshzcf", "ywzxdkjje",
				"ywsshzzjhe", "xfjnqk", "xzxfqk", "hsf", "qtfy", "shf_jtgg",
				"shf_qtly", "jicy1_cw", "jicy1_xm", "jicy1_nl", "jicy1_sr",
				"jicy1_dw", "jicy2_cw", "jicy2_xm", "jicy2_nl", "jicy2_sr",
				"jicy2_dw", "jicy3_cw", "jicy3_xm", "jicy3_nl", "jicy3_sr",
				"jicy3_dw", "jicy4_cw", "jicy4_xm", "jicy4_nl", "jicy4_sr",
				"jicy4_dw", "jicy5_cw", "jicy5_xm", "jicy5_nl", "jicy5_sr",
				"jicy5_dw", "jtzrks", "rjnsr", "jtlx", "jtknqk", "sqsj" };
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
	 * 判断困难生登记表是否重复，重复的返回1，没有重复的返回-1 isknsdjbdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsdjbDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xg_zz_knsdjb where xh = ? and nd = ?";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		}
		return sFlag;
	}

	/**
	 * 保存困难生登记表信息，成功返回TRUE，反之返回FALSE saveKnsdjbxx ---- 保存困难生登记表信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsdjbxx(KnsdjbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(saveKnsModel.getNd(), "", 1);
		String xh = Base.chgNull(saveKnsModel.getXh(), "", 1);
		String yhkh = Base.chgNull(saveKnsModel.getYhkh(), "", 1);
		String ykthm = Base.chgNull(saveKnsModel.getYkthm(), "", 1);
		String jtzzjyb = Base.chgNull(saveKnsModel.getJtzzjyb(), "", 1);
		String qsh = Base.chgNull(saveKnsModel.getQsh(), "", 1);
		String xxztqk = Base.chgNull(saveKnsModel.getXxztqk(), "", 1);
		String lxdh = Base.chgNull(saveKnsModel.getLxdh(), "", 1);
		String hhzjl = Base.chgNull(saveKnsModel.getHhzjl(), "", 1);
		String cshzcf = Base.chgNull(saveKnsModel.getCshzcf(), "", 1);
		String ywzxdkjje = Base.chgNull(saveKnsModel.getYwzxdkjje(), "", 1);
		String ywsshzzjhe = Base.chgNull(saveKnsModel.getYwsshzzjhe(), "", 1);
		String xfjnqk = Base.chgNull(saveKnsModel.getXfjnqk(), "", 1);
		String xzxfqk = Base.chgNull(saveKnsModel.getXzxfqk(), "", 1);
		String hsf = Base.chgNull(saveKnsModel.getHsf(), "", 1);
		String qtfy = Base.chgNull(saveKnsModel.getQtfy(), "", 1);
		String shf_jtgg = Base.chgNull(saveKnsModel.getShf_jtgg(), "", 1);
		String shf_qtly = Base.chgNull(saveKnsModel.getShf_qtly(), "", 1);
		String jicy1_cw = Base.chgNull(saveKnsModel.getJicy1_cw(), "", 1);
		String jicy1_xm = Base.chgNull(saveKnsModel.getJicy1_xm(), "", 1);
		String jicy1_nl = Base.chgNull(saveKnsModel.getJicy1_nl(), "", 1);
		String jicy1_sr = Base.chgNull(saveKnsModel.getJicy1_sr(), "", 1);
		String jicy1_dw = Base.chgNull(saveKnsModel.getJicy1_dw(), "", 1);
		String jicy2_cw = Base.chgNull(saveKnsModel.getJicy2_cw(), "", 1);
		String jicy2_xm = Base.chgNull(saveKnsModel.getJicy2_xm(), "", 1);
		String jicy2_nl = Base.chgNull(saveKnsModel.getJicy2_nl(), "", 1);
		String jicy2_sr = Base.chgNull(saveKnsModel.getJicy2_sr(), "", 1);
		String jicy2_dw = Base.chgNull(saveKnsModel.getJicy2_dw(), "", 1);
		String jicy3_cw = Base.chgNull(saveKnsModel.getJicy3_cw(), "", 1);
		String jicy3_xm = Base.chgNull(saveKnsModel.getJicy3_xm(), "", 1);
		String jicy3_nl = Base.chgNull(saveKnsModel.getJicy3_nl(), "", 1);
		String jicy3_sr = Base.chgNull(saveKnsModel.getJicy3_sr(), "", 1);
		String jicy3_dw = Base.chgNull(saveKnsModel.getJicy3_dw(), "", 1);
		String jicy4_cw = Base.chgNull(saveKnsModel.getJicy4_cw(), "", 1);
		String jicy4_xm = Base.chgNull(saveKnsModel.getJicy4_xm(), "", 1);
		String jicy4_nl = Base.chgNull(saveKnsModel.getJicy4_nl(), "", 1);
		String jicy4_sr = Base.chgNull(saveKnsModel.getJicy4_sr(), "", 1);
		String jicy4_dw = Base.chgNull(saveKnsModel.getJicy4_dw(), "", 1);
		String jicy5_cw = Base.chgNull(saveKnsModel.getJicy5_cw(), "", 1);
		String jicy5_xm = Base.chgNull(saveKnsModel.getJicy5_xm(), "", 1);
		String jicy5_nl = Base.chgNull(saveKnsModel.getJicy5_nl(), "", 1);
		String jicy5_sr = Base.chgNull(saveKnsModel.getJicy5_sr(), "", 1);
		String jicy5_dw = Base.chgNull(saveKnsModel.getJicy5_dw(), "", 1);
		String jtzrks = Base.chgNull(saveKnsModel.getJtzrks(), "", 1);
		String rjnsr = Base.chgNull(saveKnsModel.getRjnsr(), "", 1);
		String jtlx = Base.chgNull(saveKnsModel.getJtlx(), "", 1);
		String jtknqk = Base.chgNull(saveKnsModel.getJtknqk(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsdjbDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xg_zz_knsdjb", new String[] {
					"nd", "xh", "yhkh", "ykthm", "jtzzjyb", "qsh", "xxztqk",
					"lxdh", "hhzjl", "cshzcf", "ywzxdkjje", "ywsshzzjhe",
					"xfjnqk", "xzxfqk", "hsf", "qtfy", "shf_jtgg", "shf_qtly",
					"jicy1_cw", "jicy1_xm", "jicy1_nl", "jicy1_sr", "jicy1_dw",
					"jicy2_cw", "jicy2_xm", "jicy2_nl", "jicy2_sr", "jicy2_dw",
					"jicy3_cw", "jicy3_xm", "jicy3_nl", "jicy3_sr", "jicy3_dw",
					"jicy4_cw", "jicy4_xm", "jicy4_nl", "jicy4_sr", "jicy4_dw",
					"jicy5_cw", "jicy5_xm", "jicy5_nl", "jicy5_sr", "jicy5_dw",
					"jtzrks", "rjnsr", "jtlx", "jtknqk" }, new String[] { nd,
					xh, yhkh, ykthm, jtzzjyb, qsh, xxztqk, lxdh, hhzjl, cshzcf,
					ywzxdkjje, ywsshzzjhe, xfjnqk, xzxfqk, hsf, qtfy, shf_jtgg,
					shf_qtly, jicy1_cw, jicy1_xm, jicy1_nl, jicy1_sr, jicy1_dw,
					jicy2_cw, jicy2_xm, jicy2_nl, jicy2_sr, jicy2_dw, jicy3_cw,
					jicy3_xm, jicy3_nl, jicy3_sr, jicy3_dw, jicy4_cw, jicy4_xm,
					jicy4_nl, jicy4_sr, jicy4_dw, jicy5_cw, jicy5_xm, jicy5_nl,
					jicy5_sr, jicy5_dw, jtzrks, rjnsr, jtlx, jtknqk }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xg_zz_knsdjb", new String[] {
					"yhkh", "ykthm", "jtzzjyb", "qsh", "xxztqk", "lxdh",
					"hhzjl", "cshzcf", "ywzxdkjje", "ywsshzzjhe", "xfjnqk",
					"xzxfqk", "hsf", "qtfy", "shf_jtgg", "shf_qtly",
					"jicy1_cw", "jicy1_xm", "jicy1_nl", "jicy1_sr", "jicy1_dw",
					"jicy2_cw", "jicy2_xm", "jicy2_nl", "jicy2_sr", "jicy2_dw",
					"jicy3_cw", "jicy3_xm", "jicy3_nl", "jicy3_sr", "jicy3_dw",
					"jicy4_cw", "jicy4_xm", "jicy4_nl", "jicy4_sr", "jicy4_dw",
					"jicy5_cw", "jicy5_xm", "jicy5_nl", "jicy5_sr", "jicy5_dw",
					"jtzrks", "rjnsr", "jtlx", "jtknqk", "sqsj" },
					new String[] { yhkh, ykthm, jtzzjyb, qsh, xxztqk, lxdh,
							hhzjl, cshzcf, ywzxdkjje, ywsshzzjhe, xfjnqk,
							xzxfqk, hsf, qtfy, shf_jtgg, shf_qtly, jicy1_cw,
							jicy1_xm, jicy1_nl, jicy1_sr, jicy1_dw, jicy2_cw,
							jicy2_xm, jicy2_nl, jicy2_sr, jicy2_dw, jicy3_cw,
							jicy3_xm, jicy3_nl, jicy3_sr, jicy3_dw, jicy4_cw,
							jicy4_xm, jicy4_nl, jicy4_sr, jicy4_dw, jicy5_cw,
							jicy5_xm, jicy5_nl, jicy5_sr, jicy5_dw, jtzrks,
							rjnsr, jtlx, jtknqk, now }, "nd||xh", nd + xh,
					request);
		}
		return bFlag;
	}

	/**
	 * 获取困难生登记表详细信息
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbdy(KnsdjbModel knsModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(knsModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(knsModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(knsModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(knsModel.getXb(), "", 1));
		stuList.put("rxrq", Base.chgNull(knsModel.getRxrq(), "", 1));
		stuList.put("byrq", Base.chgNull(knsModel.getByrq(), "", 1));
		stuList.put("xz", Base.chgNull(knsModel.getXz(), "", 1));
		stuList.put("nj", Base.chgNull(knsModel.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("yhkh", Base.chgNull(knsModel.getYhkh(), "", 1));
		stuList.put("ykthm", Base.chgNull(knsModel.getYkthm(), "", 1));
		stuList.put("jtzzjyb", Base.chgNull(knsModel.getJtzzjyb(), "", 1));
		stuList.put("qsh", Base.chgNull(knsModel.getQsh(), "", 1));
		stuList.put("xxztqk", Base.chgNull(knsModel.getXxztqk(), "", 1));
		stuList.put("lxdh", Base.chgNull(knsModel.getLxdh(), "", 1));
		stuList.put("hhzjl", Base.chgNull(knsModel.getHhzjl(), "", 1));
		stuList.put("cshzcf", Base.chgNull(knsModel.getCshzcf(), "", 1));
		stuList.put("ywzxdkjje", Base.chgNull(knsModel.getYwzxdkjje(), "", 1));
		stuList.put("ywsshzzjhe", Base.chgNull(knsModel.getYwsshzzjhe(), "", 1));
		stuList.put("xfjnqk", Base.chgNull(knsModel.getXfjnqk(), "", 1));
		stuList.put("xzxfqk", Base.chgNull(knsModel.getXzxfqk(), "", 1));
		stuList.put("hsf", Base.chgNull(knsModel.getHsf(), "", 1));
		stuList.put("qtfy", Base.chgNull(knsModel.getQtfy(), "", 1));
		stuList.put("shf_jtgg", Base.chgNull(knsModel.getShf_jtgg(), "", 1));
		stuList.put("shf_qtly", Base.chgNull(knsModel.getShf_qtly(), "", 1));
		stuList.put("jicy1_cw", Base.chgNull(knsModel.getJicy1_cw(), "", 1));
		stuList.put("jicy1_xm", Base.chgNull(knsModel.getJicy1_xm(), "", 1));
		stuList.put("jicy1_nl", Base.chgNull(knsModel.getJicy1_nl(), "", 1));
		stuList.put("jicy1_sr", Base.chgNull(knsModel.getJicy1_sr(), "", 1));
		stuList.put("jicy1_dw", Base.chgNull(knsModel.getJicy1_dw(), "", 1));
		stuList.put("jicy2_cw", Base.chgNull(knsModel.getJicy2_cw(), "", 1));
		stuList.put("jicy2_xm", Base.chgNull(knsModel.getJicy2_xm(), "", 1));
		stuList.put("jicy2_nl", Base.chgNull(knsModel.getJicy2_nl(), "", 1));
		stuList.put("jicy2_sr", Base.chgNull(knsModel.getJicy2_sr(), "", 1));
		stuList.put("jicy2_dw", Base.chgNull(knsModel.getJicy2_dw(), "", 1));
		stuList.put("jicy3_cw", Base.chgNull(knsModel.getJicy3_cw(), "", 1));
		stuList.put("jicy3_xm", Base.chgNull(knsModel.getJicy3_xm(), "", 1));
		stuList.put("jicy3_nl", Base.chgNull(knsModel.getJicy3_nl(), "", 1));
		stuList.put("jicy3_sr", Base.chgNull(knsModel.getJicy3_sr(), "", 1));
		stuList.put("jicy3_dw", Base.chgNull(knsModel.getJicy3_dw(), "", 1));
		stuList.put("jicy4_cw", Base.chgNull(knsModel.getJicy4_cw(), "", 1));
		stuList.put("jicy4_xm", Base.chgNull(knsModel.getJicy4_xm(), "", 1));
		stuList.put("jicy4_nl", Base.chgNull(knsModel.getJicy4_nl(), "", 1));
		stuList.put("jicy4_sr", Base.chgNull(knsModel.getJicy4_sr(), "", 1));
		stuList.put("jicy4_dw", Base.chgNull(knsModel.getJicy4_dw(), "", 1));
		stuList.put("jicy5_cw", Base.chgNull(knsModel.getJicy5_cw(), "", 1));
		stuList.put("jicy5_xm", Base.chgNull(knsModel.getJicy5_xm(), "", 1));
		stuList.put("jicy5_nl", Base.chgNull(knsModel.getJicy5_nl(), "", 1));
		stuList.put("jicy5_sr", Base.chgNull(knsModel.getJicy5_sr(), "", 1));
		stuList.put("jicy5_dw", Base.chgNull(knsModel.getJicy5_dw(), "", 1));
		stuList.put("jtzrks", Base.chgNull(knsModel.getJtzrks(), "", 1));
		stuList.put("rjnsr", Base.chgNull(knsModel.getRjnsr(), "", 1));
		stuList.put("jtknqk", Base.chgNull(knsModel.getJtknqk(), "", 1));
		String jtlxT = Base.chgNull(knsModel.getJtlx(), "", 1);
		String[] jtlxAll = { "双亲", "单亲", "离异", "烈属", "孤儿" };
		String[] jtlxVal = { "0", "0", "0", "0", "0" };
		if (!"".equalsIgnoreCase(jtlxT)) {
			String[] jtlxList = jtlxT.split(";");
			for (int i = 0; i < jtlxList.length; i++) {
				for (int j = 0; j < jtlxAll.length; j++) {
					if (jtlxAll[j].equalsIgnoreCase(jtlxList[i])) {
						jtlxVal[j] = "1";
					}
				}
			}
		}
		for (int i = 0; i < jtlxVal.length; i++) {
			stuList.put("jtlx" + (i + 1), jtlxVal[i]);
		}

		String sqsj = Base.chgNull(knsModel.getSqsj(), "", 1);

		String sqsjyear = "null";
		String sqsjmon = "null";
		String sqsjday = "null";

		if (!"".equalsIgnoreCase(sqsj)) {
			sqsjyear = sqsj.substring(0, 4);
			sqsjmon = sqsj.substring(5, 7);
			sqsjday = sqsj.substring(8);
		}
		stuList.put("sqsjyear", sqsjyear);
		stuList.put("sqsjmon", sqsjmon);
		stuList.put("sqsjday", sqsjday);

		return stuList;
	}

	/**
	 * 删除困难登记表信息 delKnsdjb ---- 删除困难登记表信息
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsdjb(String[] pkT, HttpServletRequest request)
			throws Exception {
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			sqlT[i] = "delete xg_zz_knsdjb where nd||xh='" + pkT[i] + "'";
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 困难生登记表查询表头 getKnsdjbTit ---- 困难生登记表表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsdjbTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "nd", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj" };
		String[] cnList = new String[] { "主键", "年度", "学号", "姓名", "性别", "身份证号",
				Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 困难生登记表 getKnsdjbRes ---- 困难生登记表
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsdjbRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();

		String sql = "select pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj from (select rownum r,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj from view_xg_zz_knsdjb where 1=1";

		String[] colList = new String[] { "pk", "nd", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "sqsj" };

		StringBuffer whereSql = getWhereSql(queryModel, request);

		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 困难生登记表查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsdjbResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sql = "select count(*) num from view_xg_zz_knsdjb where 1=1";
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
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
		String sql = "select nd,xh,xm,xb,mzmc,zzmmmc,csrq,rxrq,xz,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_dw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_dw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_dw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_dw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_dw,sqly,sqsj,zxjdm,zxjdj,zxjje,fdysh,fdyshsj,fdyshyj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_jhzy_gjzxj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "csrq", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "jthk", "jtzrks",
				"jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_gx", "jtcy1_dw", "jtcy2_xm", "jtcy2_nl",
				"jtcy2_gx", "jtcy2_dw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
				"jtcy3_dw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_dw",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_dw", "sqly", "sqsj",
				"zxjdm", "zxjdj", "zxjje", "fdysh", "fdyshsj", "fdyshyj",
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
	 * 判断国家助学金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isgjzxjdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_jhzy_gjzxj where xh = ? and nd = ? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_jhzy_gjzxj where xh = ? and nd = ? and xysh='通过'";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_jhzy_gjzxj where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}

	/**
	 * 保存国家助学金申请信息，成功返回TRUE，反之返回FALSE saveGjzxjSqxx ---- 保存国家助学金申请信息
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(model.getNd(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
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
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_jhzy_gjzxj", new String[] {
					"nd", "xh", "lxdh", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_dw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_dw", "sqly" }, new String[] { nd, xh,
					lxdh, jthk, jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
					jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_dw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_dw,
					jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl,
					jtcy5_gx, jtcy5_dw, sqly }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_jhzy_gjzxj", new String[] {
					"lxdh", "jthk", "jtzrks", "jtyzsr", "rjysr", "srly",
					"jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_dw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_dw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_dw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_dw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_dw", "sqly", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj", "zxjdm", "zxjdj", "zxjje" },
					new String[] { lxdh, jthk, jtzrks, jtyzsr, rjysr, srly,
							jtzz, yzbm, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_dw,
							jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_dw, jtcy3_xm,
							jtcy3_nl, jtcy3_gx, jtcy3_dw, jtcy4_xm, jtcy4_nl,
							jtcy4_gx, jtcy4_dw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_dw, sqly, "未审核", "", "", "未审核", "", "",
							"未审核", "", "", now, "", "", "" }, "nd||xh",
					nd + xh, request);
		}
		return bFlag;
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

		stuList.put("nd", Base.chgNull(model.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csrq", Base.chgNull(model.getCsrq(), "", 1));
		stuList.put("rxrq", Base.chgNull(model.getRxrq(), "", 1));
		stuList.put("xz", Base.chgNull(model.getXz(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
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
		stuList.put("jtcy1_dw", Base.chgNull(model.getJtcy1_dw(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(model.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(model.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_dw", Base.chgNull(model.getJtcy2_dw(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(model.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(model.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_dw", Base.chgNull(model.getJtcy3_dw(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(model.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(model.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_dw", Base.chgNull(model.getJtcy4_dw(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(model.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(model.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_dw", Base.chgNull(model.getJtcy5_dw(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));

		return stuList;
	}

	/**
	 * 删除国家助学金 delGjzxj ---- 删除国家助学金
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj(String[] pkT, HttpServletRequest request)
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
				sqlT[i] = "delete xszz_jhzy_gjzxj where nd||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_jhzy_gjzxj where nd||xh='" + pkT[i]
							+ "' and xysh<>'通过'";
				} else {
					sqlT[i] = "delete xszz_jhzy_gjzxj where nd||xh='" + pkT[i]
							+ "' and xxsh<>'通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 批量修改国家助学金审核结果 modGjzxj ---- 批量修改国家助学金审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj(String[] pkT, String shjg, HttpServletRequest request)
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
				sqlT[i] = "update xszz_jhzy_gjzxj set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_jhzy_gjzxj set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_jhzy_gjzxj set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 国家助学金查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "年度",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"助学金等级", "助学金金额", "辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家助学金查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from (select rownum r,";

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
			sql += "(case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_jhzy_gjzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_jhzy_gjzxj where fdysh='通过'";
				} else {
					sql += "(case when nvl(fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_jhzy_gjzxj where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,zxjdj,zxjje,fdysh,xysh,xxsh from view_xszz_jhzy_gjzxj where xysh='通过'";
			}
		}
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"zxjdj", "zxjje", "fdysh", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);

		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 国家助学金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_jhzy_gjzxj where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh='通过'";
				}
			} else {
				sql += " and xysh='通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}

	/**
	 * 保存国家助学金审核信息 saveGjzxjShxx ---- 保存国家助学金审核信息
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String zxjdj = Base.chgNull(model.getZxjdj(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_jhzy_gjzxj", new String[] {
					"zxjdj", "zxjdm", "zxjje", "xxsh", "xxshyj", "xxshsj" },
					new String[] { zxjdj, zxjdm, zxjje, xxsh, xxshyj, now },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_jhzy_gjzxj",
								new String[] { "zxjdj", "zxjdm", "zxjje",
										"fdysh", "fdyshyj", "fdyshsj" },
								new String[] { zxjdj, zxjdm, zxjje, fdysh,
										fdyshyj, now }, "nd||xh", nd + xh,
								request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_jhzy_gjzxj",
							new String[] { "zxjdj", "zxjdm", "zxjje", "xysh",
									"xyshyj", "xyshsj" }, new String[] { zxjdj,
									zxjdm, zxjje, xysh, xyshyj, now },
							"nd||xh", nd + xh, request);
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

		String sql = "select dm,dj||'||'||je mc from xszz_jhzy_gjzxjdmb order by dj desc";
		list.addAll(dao.getArrayList(sql, new String[] {}, new String[] { "dm",
				"mc" }));
		return list;
	}

	/**
	 * 国家励志奖学金
	 */
	public void saveGjlzjxjSq(XszzGjlzjxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = model.getNd();
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String chhzjl = model.getChhzjl();
		String jthk = model.getJthk();
		String jtzrks = model.getJtzrks();
		String jtyzsr = model.getJtyzsr();
		String rjysr = model.getRjysr();
		String srly = model.getSrly();
		String jtzz = model.getJtzz();
		String yzbm = model.getYzbm();
		String gxnbxkcs = model.getGxnbxkcs();
		String yxkcs = model.getYxkcs();
		String lhkcs = model.getLhkcs();
		String cjpm = model.getCjpm();
		String zhkpcj = model.getZhkpcj();
		String zhkppm = model.getZhkppm();
		String sqly = model.getSqly();
		String flag = dao.returntag(
				"select * from xszz_jhzy_gjlzjxj where nd=? and xh=?",
				new String[] { nd, xh });
		if (!serv_getGjlzJxjSh(nd + xh, "xxsh")) {
			if ("empty".equalsIgnoreCase(flag)) {
				bFlag = StandardOperation.insert("xszz_jhzy_gjlzjxj",
						new String[] { "nd", "xh", "lxdh", "chhzjl", "jthk",
								"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz",
								"yzbm", "gxnbxkcs", "yxkcs", "lhkcs", "cjpm",
								"zhkpcj", "zhkppm", "sqly" }, new String[] {
								nd, xh, lxdh, chhzjl, jthk, jtzrks, jtyzsr,
								rjysr, srly, jtzz, yzbm, gxnbxkcs, yxkcs,
								lhkcs, cjpm, zhkpcj, zhkppm, sqly }, request);
			} else {
				bFlag = StandardOperation.update("xszz_jhzy_gjlzjxj",
						new String[] { "lxdh", "chhzjl", "jthk", "jtzrks",
								"jtyzsr", "rjysr", "srly", "jtzz", "yzbm",
								"gxnbxkcs", "yxkcs", "lhkcs", "cjpm", "zhkpcj",
								"zhkppm", "sqly", "fdysh", "xysh", "fdyshyj",
								"xyshyj" }, new String[] { lxdh, chhzjl, jthk,
								jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
								gxnbxkcs, yxkcs, lhkcs, cjpm, zhkpcj, zhkppm,
								sqly, "未审核", "未审核", "", "" }, "nd||xh",
						nd + xh, request);
			}
			request.setAttribute("done", bFlag);
		} else {
			request.setAttribute("isPASS", "is");
		}

	}

	/**
	 * 获取相关信息
	 * 
	 * @param table
	 *            表名
	 * @param querry
	 *            条件
	 * @return
	 */
	public HashMap<String, String> dao_getInfo(String table, String querry) {
		DAO dao = DAO.getInstance();
		String[] colList = dao.getColumnName("select * from " + table);
		for (int i = 0; i < colList.length; i++) {
			colList[i] = colList[i].toLowerCase();
		}
		String sql = "select * from " + table;
		return dao.getMap(sql + querry.toString(), new String[] {}, colList);
	}

	/**
	 * 获取国家励志奖学金相关信息
	 */
	public boolean serv_getGjlzJxjSh(String pkVlaue, String shDj) {
		boolean ret = false;
		String querry = " where nd||xh='" + pkVlaue + "' and " + shDj
				+ "='通过' ";
		HashMap<String, String> map = dao_getInfo("xszz_jhzy_gjlzjxj", querry);
		if (map.size() > 0) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 国家励志奖学金
	 */
	public List<HashMap<String, String>> getGjlzJxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "主键", "年度",
				"学号", "姓名", "性别", "身份证号", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "申请时间",
				"辅导员审核", Base.YXPZXY_KEY+"审核", "学校审核" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * 国家励志奖学金查询结果
	 */
	public List<String[]> getGjlzJxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

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
			sql += "(case when nvl(xxsh,'未审核') in ('通过','不通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') in ('通过','不通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_gjlzjxj where fdysh in ('通过','不通过')";
				} else {
					sql += "(case when nvl(fdysh,'未审核') in ('通过','不通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_gjlzjxj where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核') in ('通过','不通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_gjlzjxj where xysh in ('通过','不通过')";
			}
		}
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };
		StringBuffer whereSql = getWhereSql(queryModel, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();

		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 国家励志奖学金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzJxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_jhzy_gjlzjxj where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh in ('通过','不通过')";
				}
			} else {
				sql += " and xysh in ('通过','不通过')";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}

	/**
	 * 批量修改国家励志奖学金审核结果
	 */
	public void modGjlzjxjxx(String[] pkT, String shjg,
			HttpServletRequest request) throws Exception {
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
				sqlT[i] = "update xszz_jhzy_gjlzjxj set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_jhzy_gjlzjxj set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_jhzy_gjlzjxj set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 删除国家励志奖学金
	 */
	public void delGjlzjxjxx(String[] pkT, HttpServletRequest request)
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
				sqlT[i] = "delete xszz_jhzy_gjlzjxj where nd||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_jhzy_gjlzjxj where nd||xh='"
							+ pkT[i] + "' and xysh not in ('通过','不通过')";
				} else {
					sqlT[i] = "delete xszz_jhzy_gjlzjxj where nd||xh='"
							+ pkT[i] + "' and xxsh not in ('通过','不通过')";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 获取国家励志奖学金申请表详细信息
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(model.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csrq", Base.chgNull(model.getCsrq(), "", 1));
		stuList.put("rxrq", Base.chgNull(model.getRxrq(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
		stuList.put("chhzjl", Base.chgNull(model.getChhzjl(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtzrks", Base.chgNull(model.getJtzrks(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(model.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("gxnbxkcs", Base.chgNull(model.getGxnbxkcs(), "", 1));
		stuList.put("yxkcs", Base.chgNull(model.getYxkcs(), "", 1));
		stuList.put("lhkcs", Base.chgNull(model.getLhkcs(), "", 1));
		stuList.put("cjpm", Base.chgNull(model.getCjpm(), "", 1));
		stuList.put("zhkpcj", Base.chgNull(model.getZhkpcj(), "", 1));
		stuList.put("zhkppm", Base.chgNull(model.getZhkppm(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));

		return stuList;
	}

	/**
	 * 国家励志奖学金审核
	 */
	public boolean saveGjlzjxjSh(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_jhzy_gjlzjxj", new String[] {
					"fdyshyj", "xxsh", "xxshyj", "xxshsj" }, new String[] {
					fdyshyj, xxsh, xxshyj, now }, "nd||xh", nd + xh, request);
		} else {
			if (serv_getGjlzJxjSh(nd + xh, "xxsh")) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if (serv_getGjlzJxjSh(nd + xh, "xysh")) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_jhzy_gjlzjxj",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "nd||xh",
								nd + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_jhzy_gjlzjxj",
							new String[] { "fdyshyj", "xysh", "xyshyj",
									"xyshsj" }, new String[] { fdyshyj, xysh,
									xyshyj, now }, "nd||xh", nd + xh, request);
				}
			}
		}
		return bFlag;
	}

	/**
	 * 国家励志奖学金申请表打印
	 */
	public HashMap<String, String> getGjlzJxjb(XszzGjlzjxjModel model) {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(model.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("csrq", Base.chgNull(model.getCsrq(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("fdysh", Base.chgNull(model.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(model.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));

		// String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		// String xysh = Base.chgNull(model.getXysh(), "", 1);
		// String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		return stuList;
	}

	/**
	 * 得到帮困助学基金申请权限
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 可申请；-1 不可申请
	 * @throws Exception
	 */
	public String getBkzxjjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='帮困助学基金' and b.xh=? ";
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
	 * 是否困难生
	 * 
	 * @throws Exception
	 */
	public boolean isKns(String xh) throws Exception {
		return dao.isKns(xh);
	}

	/**
	 * 有不及格科目
	 * 
	 * @throws Exception
	 */
	public boolean isBjgkm(String nd, String xh) throws Exception {
		boolean flg = false;
		String sql = "select count(*) num from cjb where substr(xn,6,4)=? and xh=? and cj<60";
		String num = dao.getOneRs(sql, new String[] { nd, xh }, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		if (!flg) {
			String kcNum = dao
					.getOneRs(
							"select count(*) num from cjb where substr(xn,6,4)=? and xh=?",
							new String[] { nd, xh }, "num");

			if ("0".equalsIgnoreCase(kcNum)) {
				flg = true;
			}
		}
		return flg;
	}

	/**
	 * 资助等级列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzdjList() throws Exception {
		String sql = "select dm,dj from xszz_jhzy_bkzxjjdmb";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "dj" });
		return list;
	}

	/**
	 * 保存帮困助学基金申请信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @param saveModel
	 *            (数据保存实体MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjSqxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(model.getNd(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jtzzjyb = Base.chgNull(model.getJtzzjyb(), "", 1);// 家庭住址及邮编
		// String jg;// 籍贯
		String jtnjjcsr = Base.chgNull(model.getJtnjjcsr(), "", 1);// 家庭年经济纯收入
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);// 家庭人口数
		String xxztqk = Base.chgNull(model.getXxztqk(), "", 1);// 学习总体情况
		String dy = Base.chgNull(model.getDy(), "", 1);// 德育
		String cshzjl = Base.chgNull(model.getCshzjl(), "", 1);// 曾受何种奖励
		String xfjnqk = Base.chgNull(model.getXfjnqk(), "", 1);// 学费交纳情况
		String cshzwjcf = Base.chgNull(model.getCshzwjcf(), "", 1);// 曾受何种违纪处分
		String zxdkjje = Base.chgNull(model.getZxdkjje(), "", 1);// 助学贷款及金额
		String gjxjxjje = Base.chgNull(model.getGjxjxjje(), "", 1);// 获国家、校奖学金金额
		String gjlzzxjjje = Base.chgNull(model.getGjlzzxjjje(), "", 1);// 获国家励志、助学金及金额
		String bxnhknbzjje = Base.chgNull(model.getBxnhknbzjje(), "", 1);// 本学年获困难补助及金额
		String sqly = Base.chgNull(model.getSqly(), "", 1);// 申请理由
		String zxjjdm = Base.chgNull(model.getZxjjdm(), "", 1);// 助学基金资助代码

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isBkzxjjCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_jhzy_bkzxjj", new String[] {
					"nd", "xh", "jtzzjyb", "jtnjjcsr", "jtrks", "xxztqk", "dy",
					"cshzjl", "xfjnqk", "cshzwjcf", "zxdkjje", "gjxjxjje",
					"gjlzzxjjje", "bxnhknbzjje", "sqly", "zxjjdm" },
					new String[] { nd, xh, jtzzjyb, jtnjjcsr, jtrks, xxztqk,
							dy, cshzjl, xfjnqk, cshzwjcf, zxdkjje, gjxjxjje,
							gjlzzxjjje, bxnhknbzjje, sqly, zxjjdm }, request);
		} else if ("2".equalsIgnoreCase(sHave)) {
			request.setAttribute("isPASS", "is");
		} else {
			bFlag = StandardOperation.update("xszz_jhzy_bkzxjj", new String[] {
					"jtzzjyb", "jtnjjcsr", "jtrks", "xxztqk", "dy", "cshzjl",
					"xfjnqk", "cshzwjcf", "zxdkjje", "gjxjxjje", "gjlzzxjjje",
					"bxnhknbzjje", "sqly", "zxjjdm", "fdysh", "fdyshsj",
					"fdyshyj", "xysh", "xyshsj", "xyshyj", "xxsh", "xxshsj",
					"xxshyj", "sqsj" }, new String[] { jtzzjyb, jtnjjcsr,
					jtrks, xxztqk, dy, cshzjl, xfjnqk, cshzwjcf, zxdkjje,
					gjxjxjje, gjlzzxjjje, bxnhknbzjje, sqly, zxjjdm, "未审核", "",
					"", "未审核", "", "", "未审核", "", "", now }, "nd||xh", nd + xh,
					request);
		}
		return bFlag;
	}

	/**
	 * 获取帮困助学基金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkzxjjXx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select * from view_xszz_jhzy_bkzxjj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "mzmc",
				"zzmmmc", "rxrq", "xz", "nj", "sfzh", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtzzjyb", "jtnjjcsr", "jtrks",
				"xxztqk", "dy", "cshzjl", "xfjnqk", "cshzwjcf", "zxdkjje",
				"gjxjxjje", "gjlzzxjjje", "bxnhknbzjje", "sqly", "zxjjdm",
				"fdysh", "fdyshsj", "fdyshyj", "xysh", "xyshsj", "xyshyj",
				"xxsh", "xxshsj", "xxshyj", "sqsj", "xz" };
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
	 * 判断帮困助学基金是否重复，重复且通过审核的返回2，重复但没通过审核的返回1，没有重复的返回-1 isknsdatacf ---- 数据是否重复
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isBkzxjjCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_jhzy_bkzxjj where xh = ? and nd = ? and xxsh = '通过'";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_jhzy_bkzxjj where xh = ? and nd = ? and xysh = '通过'";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_jhzy_bkzxjj where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}

	/**
	 * 帮困助学基金查询结果
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkzzjjRes(QueryModel model,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select disabled,bgcolor,pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from (select rownum r,";

		if (userType.equalsIgnoreCase("xy")) {
			if (userBj.size() == 0) {
				sql += "(case when xxsh='未审核' then '' else 'disabled' end) disabled,";
			} else {
				sql += "(case when xysh='未审核' then '' else 'disabled' end) disabled,";
			}
		} else {
			sql += "'' disabled,";
		}

		if (model.getIsQuery().equalsIgnoreCase("is")) {
			sql += "(case when nvl(xxsh,'未审核') = '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_bkzxjj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += "(case when nvl(xysh,'未审核') = '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_bkzxjj where fdysh  = '通过'";
				} else {
					sql += "(case when nvl(fdysh,'未审核') = '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_bkzxjj where 1=1";
				}
			} else {
				sql += "(case when nvl(xxsh,'未审核')  = '通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,fdysh,xysh,xxsh from view_xszz_jhzy_bkzxjj where xysh  = '通过'";
			}
		}
		String[] colList = new String[] { "disabled", "bgcolor", "pk", "nd",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"fdysh", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(model, request);
		sql += whereSql + ") where r<=" + (actionForm.getPages().getStart())
				+ (actionForm.getPages().getPageSize()) + " and r>"
				+ actionForm.getPages().getStart();
		resList = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * 帮困助学基金查询结果数
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getBkzzjjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "select count(*) num from view_xszz_jhzy_bkzxjj where 1=1";

		if (!queryModel.getIsQuery().equalsIgnoreCase("is")) {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql += " and fdysh  = '通过'";
				}
			} else {
				sql += " and xysh  = '通过'";
			}
		}

		StringBuffer whereSql = getWhereSql(queryModel, request);
		String sT = dao.getOneRs(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, "num");
		return sT;
	}

	/**
	 * 批量修改帮困助学审核结果
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modBkzxjjxx(String[] pkT, String shjg,
			HttpServletRequest request) throws Exception {
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
				sqlT[i] = "update xszz_jhzy_bkzxjj set xxsh='"
						+ shjg
						+ "',xxshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
						+ pkT[i] + "'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_jhzy_bkzxjj set xysh='"
							+ shjg
							+ "',xyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xxsh='未审核'";
				} else {
					sqlT[i] = "update xszz_jhzy_bkzxjj set fdysh='"
							+ shjg
							+ "',fdyshsj=to_char(sysdate,'yyyy-mm-dd') where nd||xh='"
							+ pkT[i] + "' and xysh='未审核'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 删除帮困助学
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delBkzxjj(String[] pkT, HttpServletRequest request)
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
				sqlT[i] = "delete xszz_jhzy_bkzxjj where nd||xh='" + pkT[i]
						+ "'";
			} else {
				if (userBj.size() != 0) {
					sqlT[i] = "delete xszz_jhzy_bkzxjj where nd||xh='" + pkT[i]
							+ "' and xysh <> '通过'";
				} else {
					sqlT[i] = "delete xszz_jhzy_bkzxjj where nd||xh='" + pkT[i]
							+ "' and xxsh <> '通过'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * 保存帮困助学基金审核信息，成功返回TRUE，反之返回FALSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjShxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String xh = Base.chgNull(model.getXh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(model.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);

		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isBkzxjjCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_jhzy_bkzxjj", new String[] {
					"xxsh", "xxshyj", "xxshsj" }, new String[] { xxsh, xxshyj,
					now }, "nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("shjg", "xxshtg");
			} else {
				if (userBj.size() != 0) {
					if ("3".equalsIgnoreCase(sHave)) {
						request.setAttribute("shjg", "xyshtg");
					} else {
						bFlag = StandardOperation.update("xszz_jhzy_bkzxjj",
								new String[] { "fdysh", "fdyshyj", "fdyshsj" },
								new String[] { fdysh, fdyshyj, now }, "nd||xh",
								nd + xh, request);
					}
				} else {
					bFlag = StandardOperation.update("xszz_jhzy_bkzxjj",
							new String[] { "xysh", "xyshyj", "xyshsj" },
							new String[] { xysh, xyshyj, now }, "nd||xh", nd
									+ xh, request);
				}
			}
		}

		return bFlag;
	}

	/**
	 * 获取帮困助学申请表详细信息
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkzxjjsqb(BkzxjjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(model.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("csrq", Base.chgNull(model.getCsrq(), "", 1));
		stuList.put("jtzzjyb", Base.chgNull(model.getJtzzjyb(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("jtnjjcsr", Base.chgNull(model.getJtnjjcsr(), "", 1));
		stuList.put("jtrks", Base.chgNull(model.getJtrks(), "", 1));
		stuList.put("xxztqk", Base.chgNull(model.getXxztqk(), "", 1));
		stuList.put("dy", Base.chgNull(model.getDy(), "", 1));
		stuList.put("xfjnqk", Base.chgNull(model.getXfjnqk(), "", 1));
		stuList.put("cshzwjcf", Base.chgNull(model.getCshzwjcf(), "", 1));
		stuList.put("zxdkjje", Base.chgNull(model.getZxdkjje(), "", 1));
		stuList.put("gjxjxjje", Base.chgNull(model.getGjxjxjje(), "", 1));
		stuList.put("gjlzzxjjje", Base.chgNull(model.getGjlzzxjjje(), "", 1));
		stuList.put("bxnhknbzjje", Base.chgNull(model.getBxnhknbzjje(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("zxjjdm", Base.chgNull(model.getZxjjdm(), "", 1));
		stuList.put("fdysh", Base.chgNull(model.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(model.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		String fdysh = Base.chgNull(model.getFdysh(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);

		if (!"未审核".equalsIgnoreCase(fdysh)
				&& StringUtils.isIgnoreCaseEqual(fdysh, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"未审核".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}

		return stuList;
	}

	/**
	 * 获取助学基金详细
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public String getZxjjXx(String zxjjdm) throws Exception {
		String sql = "select '助学基金等级:'|| dj ||'  '||'金额:'||je xx from xszz_jhzy_bkzxjjdmb where dm=?";
		return dao.getOneRs(sql, new String[] { zxjjdm }, "xx");
	}
}
