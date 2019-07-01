package xgxt.xszz.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �������ϴ�ѧѧ������DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-04-23
 * </p>
 */
public class XszzBjlhDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();// ��ѯ����ֵ

	/**
	 * ���÷�������ȡ��ѯ����
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
	 * �������� getExpDate ---- �������
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
				+ " where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, colList));
		return rs;
	}

	/**
	 * �������ݱ�ͷ getExpTit ---- �������ݱ�ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getExpTit(String tabName) throws Exception {
		String[] colList = dao.getColumnName("select * from " + tabName
				+ " where 1=2");// �����������
		return dao.getColumnNameCN(colList, tabName);
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxrq from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxrq" };
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
	 * ��ȡ������ѧ����ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,zxjdm,zxjmc,zxjje,xm,xb,nj,xz,sfzh,rxrq,csrq,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,kh,chhjjl,jthk,jtrks,jtyzsr,jtyrjsr,jtsrly,yzbm,jtzz,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_bjlhdx_gjzxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "zxjdm", "zxjmc",
				"zxjje", "xm", "xb", "nj", "xz", "sfzh", "rxrq", "csrq",
				"mzmc", "zzmmmc", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "kh", "chhjjl", "jthk", "jtrks", "jtyzsr", "jtyrjsr",
				"jtsrly", "yzbm", "jtzz", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "sqly", "sqsj", "xysh", "xyshyj",
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
	 * �õ�������ѧ������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
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
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='������ѧ��' and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql, new String[] { xh }, new String[] {
					"kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /������ʱ�䷶Χ��
				sfksq = "1";
			}
		} else {
			sfksq = "1";
		}
		return sfksq;
	}

	/**
	 * ���������ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveGjzxjSqxx ---- ���������ѧ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String zxjdm = Base.chgNull(model.getZxjdm(), "", 1);
		String zxjmc = Base.chgNull(model.getZxjmc(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String kh = Base.chgNull(model.getKh(), "", 1);
		String chhjjl = Base.chgNull(model.getChhjjl(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtrks = Base.chgNull(model.getJtrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String jtyrjsr = Base.chgNull(model.getJtyrjsr(), "", 1);
		String jtsrly = Base.chgNull(model.getJtsrly(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(model.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(model.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(model.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(model.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(model.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(model.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(model.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(model.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(model.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(model.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(model.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(model.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(model.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(model.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(model.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_bjlhdx_gjzxj", new String[] {
					"xn", "xh", "zxjdm", "zxjmc", "zxjje", "kh", "chhjjl",
					"jthk", "jtrks", "jtyzsr", "jtyrjsr", "jtsrly", "yzbm",
					"jtzz", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { xn, xh, zxjdm, zxjmc, zxjje, kh, chhjjl,
							jthk, jtrks, jtyzsr, jtyrjsr, jtsrly, yzbm, jtzz,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm,
							jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl,
							jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_bjlhdx_gjzxj", new String[] {
					"zxjdm", "zxjmc", "zxjje", "kh", "chhjjl", "jthk", "jtrks",
					"jtyzsr", "jtyrjsr", "jtsrly", "yzbm", "jtzz", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly", "xysh",
					"xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] { zxjdm,
					zxjmc, zxjje, kh, chhjjl, jthk, jtrks, jtyzsr, jtyrjsr,
					jtsrly, yzbm, jtzz, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm, jtcy5_nl,
					jtcy5_gx, jtcy5_gzdw, sqly, "δ���", "", "δ���", "", now },
					"xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * ��ȡ������ѧ���������ϸ��Ϣ
	 * 
	 * @param gjzxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("zxjdm", Base.chgNull(model.getZxjdm(), "", 1));
		stuList.put("zxjmc", Base.chgNull(model.getZxjmc(), "", 1));
		stuList.put("zxjje", Base.chgNull(model.getZxjje(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(model.getXz(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("rxrq", Base.chgNull(model.getRxrq(), "", 1));
		stuList.put("csrq", Base.chgNull(model.getCsrq(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kh", Base.chgNull(model.getKh(), "", 1));
		stuList.put("chhjjl", Base.chgNull(model.getChhjjl(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtrks", Base.chgNull(model.getJtrks(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("jtyrjsr", Base.chgNull(model.getJtyrjsr(), "", 1));
		stuList.put("jtsrly", Base.chgNull(model.getJtsrly(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(model.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(model.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(model.getJtcy1_gzdw(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(model.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(model.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(model.getJtcy2_gzdw(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(model.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(model.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(model.getJtcy3_gzdw(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(model.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(model.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(model.getJtcy4_gzdw(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(model.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(model.getJtcy5_gx(), "", 1));
		stuList.put("jtcy5_gzdw", Base.chgNull(model.getJtcy5_gzdw(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));

		return stuList;
	}

	/**
	 * ɾ��������ѧ����Ϣ delGjzxjxx ---- ɾ��������ѧ����Ϣ
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
				sqlT[i] = "delete xszz_bjlhdx_gjzxj where xn||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_bjlhdx_gjzxj where xn||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĺ�����ѧ����˽�� modGjzxjxx ---- �����޸Ĺ�����ѧ����˽��
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
				sqlT[i] = "update xszz_bjlhdx_gjzxj set xxsh='"+shjg+"' where xn||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_bjlhdx_gjzxj set xysh='"+shjg+"' where xn||xh='"+pkT[i]+"' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �жϹ�����ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isgjzxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_bjlhdx_gjzxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_bjlhdx_gjzxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ������ѧ���ѯ��ͷ gjzxjtit ---- ������ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "zxjmc", "zxjje", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "��ѧ������", "��ѧ����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ������ѧ���ѯ��� gjzxjres ---- ������ѧ���
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
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,zxjmc,zxjje,sqsj,xysh,xxsh from view_xszz_bjlhdx_gjzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,zxjmc,zxjje,sqsj,xysh,xxsh from view_xszz_bjlhdx_gjzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,zxjmc,zxjje,sqsj,xysh,xxsh from view_xszz_bjlhdx_gjzxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "zxjmc", "zxjje", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ���������ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE saveGjzxjShxx ---- ���������ѧ�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(gjzxjModel.getXh(), "", 1);
		String xn = Base.chgNull(gjzxjModel.getXn(), "", 1);
		String xysh = Base.chgNull(gjzxjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjzxjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjzxjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjzxjModel.getXxshyj(), "", 1);
		String zxjdm = Base.chgNull(gjzxjModel.getZxjdm(), "", 1);
		String zxjmc = Base.chgNull(gjzxjModel.getZxjmc(), "", 1);
		String zxjje = Base.chgNull(gjzxjModel.getZxjje(), "", 1);
		String sHave = isGjzxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_bjlhdx_gjzxj", new String[] {
					"xxsh", "xxshyj", "zxjdm", "zxjmc", "zxjje" },
					new String[] { xxsh, xxshyj, zxjdm, zxjmc, zxjje },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_bjlhdx_gjzxj",
						new String[] { "xysh", "xyshyj", "zxjdm", "zxjmc",
								"zxjje" }, new String[] { xysh, xyshyj, zxjdm,
								zxjmc, zxjje }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ù�����ѧ���б� gjzxjList ---- ������ѧ���б�
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select zxjdm,zxjmc from xszz_bjlh_gjzxjdmb order by zxjdm";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "zxjdm", "zxjmc" });
		return list;
	}
}
