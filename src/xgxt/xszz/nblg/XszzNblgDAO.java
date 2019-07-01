package xgxt.xszz.nblg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Description: ������ѧ������DAO
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
 * Time: 2009-01-13
 * </p>
 */
public class XszzNblgDAO {
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
		String mzpy = queryModel.getMzpy();
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
		if (!StringUtils.isNull(mzpy)) {
			whereSql.append(" and mzpy = ?");
			values.add(mzpy);
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
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny,REPLACE(NVL((select b.xxsh from nblg_kns b where b.xh=a.xh and b.xn=?),'������'),'δ���','������') kndj from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny",
				"mzmc", "zzmmmc", "rxny", "byny", "kndj" };
		String[] sLen = dao.getOneRs(sql, new String[] { Base.currXn, pkVal },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		sql = "select sjhm,jtszd jtzz,lxdh1 jtdh,yb yzbm,srly,jtcy1_xm,jtcy1_ch jtcy1_cw,jtcy1_nl,jtcy1_gx,jtcy1_gzdz jtcy1_gzdw,jtcy2_xm,jtcy2_ch jtcy2_cw,jtcy2_nl,jtcy2_gx,jtcy2_gzdz jtcy2_gzdw,jtcy3_xm,jtcy3_ch jtcy3_cw,jtcy3_nl,jtcy3_gx,jtcy3_gzdz jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz jtcy5_gzdw from xsfzxxb where xh=?";
		colList = new String[] { "sjhm", "jtzz", "jtdh", "yzbm", "srly",
				"jtcy1_xm", "jtcy1_cw", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy2_xm", "jtcy2_cw", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
				"jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw" };
		sLen = dao.getOneRs(sql, new String[] { pkVal },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		stuList.put("jtcy1_gz", stuList.get("jtcy1_gzdw"));
		stuList.put("jtcy2_gz", stuList.get("jtcy2_gzdw"));
		stuList.put("jtcy3_gz", stuList.get("jtcy3_gzdw"));
		stuList.put("jtcy4_gz", stuList.get("jtcy4_gzdw"));
		stuList.put("jtcy5_gz", stuList.get("jtcy5_gzdw"));
		return stuList;
	}

	/**
	 * ��ȡ��������ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssjssdh,sjhm,jtdh,jtzz,jtrjysr,sqly,sqsj,mzpy,csly,xysh,xxsh from view_nblg_kns where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "ssjssdh", "sjhm", "jtdh", "jtzz",
				"jtrjysr", "sqly", "sqsj", "mzpy", "csly", "xysh", "xxsh" };
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
	 * �õ�����������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
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
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='������' and b.xh=? ";
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
	 * ����������������Ϣ���ɹ�����TRUE����֮����FALSE saveKnsrdSqxx ---- �����������϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String ssjssdh = Base.chgNull(model.getSsjssdh(), "", 1);
		String sjhm = Base.chgNull(model.getSjhm(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation
					.insert("nblg_kns", new String[] { "xn", "xh", "ssjssdh",
							"sjhm", "jtdh", "jtzz", "jtrjysr", "sqly" },
							new String[] { xn, xh, ssjssdh, sjhm, jtdh, jtzz,
									jtrjysr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_kns", new String[] {
					"ssjssdh", "sjhm", "jtdh", "jtzz", "jtrjysr", "sqly",
					"sqsj", "mzpy", "csly", "xysh", "xxsh" }, new String[] {
					ssjssdh, sjhm, jtdh, jtzz, jtrjysr, sqly, now, "δ���", "",
					"δ���", "δ���" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �ж��������Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isknsdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_kns where xh = ? and xn = ? and xxsh in ('����','�ر�����')";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_kns where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ�������������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("ssjssdh", Base.chgNull(model.getSsjssdh(), "", 1));
		stuList.put("sjhm", Base.chgNull(model.getSjhm(), "", 1));
		stuList.put("jtdh", Base.chgNull(model.getJtdh(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("csly", Base.chgNull(model.getCsly(), "", 1));

		String mzpy = Base.chgNull(model.getMzpy(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);

		if ("".equalsIgnoreCase(mzpy)) {
			mzpy = "δ���";
		}
		if ("".equalsIgnoreCase(xysh)) {
			xysh = "δ���";
		}
		if ("".equalsIgnoreCase(xxsh)) {
			xxsh = "δ���";
		}

		if (mzpy.equalsIgnoreCase(xysh)) {
			stuList.put("tj_xy", "1");
		} else {
			stuList.put("tj_xy", "0");
		}
		if (xysh.equalsIgnoreCase(xxsh)) {
			stuList.put("xy_xx", "1");
		} else {
			stuList.put("xy_xx", "0");
		}

		stuList.put("mzpy", mzpy);
		stuList.put("xysh", xysh);
		stuList.put("xxsh", xxsh);
		return stuList;
	}

	/**
	 * ɾ����������Ϣ delKnsxx ---- ɾ����������Ϣ
	 * 
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
				sqlT[i] = "delete nblg_kns where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_kns where xn||xh='" + pkT[i]
						+ "' and xxsh not in ('����','�ر�����')";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ��������ѯ��ͷ Knstit ---- ��������ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "mzpy", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				"���֤��", Base.YXPZXY_KEY+"����", "�༶����", "����ʱ��", "��������", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ��������ѯ��� getKnsRes ---- ���������
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
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('����','�ر�����') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('����','�ر�����') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('����','�ر�����') then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,sfzh,xymc,bjmc,sqsj,mzpy,xysh,xxsh from view_nblg_kns where xysh in ('����','�ر�����')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"sfzh", "xymc", "bjmc", "sqsj", "mzpy", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * �����޸���������˽�� modKnsxx ---- �����޸���������˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shType, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				if ("3".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xxsh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set mzpy='" + shjg
							+ "' where xn||xh='" + pkT[i] + "'";
				}
			} else {
				if ("2".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set xysh='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
				} else if ("1".equalsIgnoreCase(shType)) {
					sqlT[i] = "update nblg_kns set mzpy='" + shjg
							+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ���������������Ϣ���ɹ�����TRUE����֮����FALSE saveKnsShxx ---- ���������������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String mzpy = Base.chgNull(model.getMzpy(), "", 1);
		String csly = Base.chgNull(model.getCsly(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String sHave = isKnsDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_kns",
					new String[] { "xxsh" }, new String[] { xxsh }, "xn||xh",
					xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_kns", new String[] {
						"mzpy", "csly", "xysh" }, new String[] { mzpy, csly,
						xysh }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ��ʱ���Ѳ�����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,chhzjl,yhzzqk,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,sqly,sqje,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_lsknbz where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "chhzjl", "yhzzqk", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "sqly",
				"sqje", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * �õ���ʱ���Ѳ�������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getLsknbzSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='��ʱ���Ѳ���' and b.xh=? ";
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
	 * ������ʱ���Ѳ���������Ϣ���ɹ�����TRUE����֮����FALSE saveLsknbzrdSqxx ---- ������ʱ���Ѳ����϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzSqxx(LsknbzModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String yhzzqk = Base.chgNull(model.getYhzzqk(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtzrks = Base.chgNull(model.getJtzrks(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(model.getRjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isLsknbzDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_lsknbz", new String[] {
					"xn", "xh", "chhzjl", "yhzzqk", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "sqly", "sqje" },
					new String[] { xn, xh, chhzjl, yhzzqk, jthk, jtzrks,
							jtyzsr, rjysr, srly, jtzz, yzbm, sqly, sqje },
					request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
					"chhzjl", "yhzzqk", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "sqly", "sqje", "sqsj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { chhzjl,
					yhzzqk, jthk, jtzrks, jtyzsr, rjysr, srly, jtzz, yzbm,
					sqly, sqje, now, "δ���", "", "δ���", "" }, "xn||xh", xn + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �ж���ʱ���Ѳ����Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isLsknbzdatacf ----
	 * �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isLsknbzDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_lsknbz where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_lsknbz where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ��ʱ���Ѳ����������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzSqb(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("chhzjl", Base.chgNull(model.getChhzjl(), "", 1));
		stuList.put("yhzzqk", Base.chgNull(model.getYhzzqk(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtzrks", Base.chgNull(model.getJtzrks(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(model.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqje", Base.chgNull(model.getSqje(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * ɾ����ʱ���Ѳ�����Ϣ delLsknbzxx ---- ɾ����ʱ���Ѳ�����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsknbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_lsknbz where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_lsknbz where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸���ʱ���Ѳ�����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsknbzxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_lsknbz set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_lsknbz set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ��ʱ���Ѳ�����ѯ��ͷ getLsknbzTit ---- ��ʱ���Ѳ�����ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsknbzTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "������", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ��ʱ���Ѳ�����ѯ��� getLsknbzRes ---- ��ʱ���Ѳ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsknbzRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_lsknbz where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ������ʱ���Ѳ��������Ϣ���ɹ�����TRUE����֮����FALSE saveLsknbzShxx ---- ������ʱ���Ѳ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzShxx(LsknbzModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isLsknbzDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_lsknbz", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ��ѧ����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,chhzjl,sqzxjlx,jthk,jtzrks,jtyzsr,rjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_zxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "chhzjl", "sqzxjlx", "jthk",
				"jtzrks", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
				"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
				"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
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
	 * �õ���ѧ������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getZxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='��ѧ��' and b.xh=? ";
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
	 * ������ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveZxjrdSqxx ---- ������ѧ���϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjSqxx(ZxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String chhzjl = Base.chgNull(model.getChhzjl(), "", 1);
		String sqzxjlx = Base.chgNull(model.getSqzxjlx(), "", 1);
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

		String sHave = isZxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_zxj", new String[] { "xn",
					"xh", "chhzjl", "sqzxjlx", "jthk", "jtzrks", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { xn, xh, chhzjl, sqzxjlx, jthk, jtzrks,
							jtyzsr, rjysr, srly, jtzz, yzbm, jtcy1_xm,
							jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl,
							jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl, jtcy3_gx,
							jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_zxj", new String[] {
					"chhzjl", "sqzxjlx", "jthk", "jtzrks", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
					"jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
					"jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
					"jtcy5_gzdw", "sqly", "sqsj", "xysh", "xyshyj", "xxsh",
					"xxshyj" }, new String[] { chhzjl, sqzxjlx, jthk, jtzrks,
					jtyzsr, rjysr, srly, jtzz, yzbm, jtcy1_xm, jtcy1_nl,
					jtcy1_gx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx,
					jtcy2_gzdw, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
					jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, sqly, now, "δ���", "",
					"δ���", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �ж���ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isZxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isZxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_zxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_zxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ��ѧ���������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjSqb(ZxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("chhzjl", Base.chgNull(model.getChhzjl(), "", 1));
		stuList.put("sqzxjlx", Base.chgNull(model.getSqzxjlx(), "", 1));
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
	 * ɾ����ѧ����Ϣ delZxjxx ---- ɾ����ѧ����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_zxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_zxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸���ѧ����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_zxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_zxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ��ѧ���ѯ��ͷ getZxjTit ---- ��ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqzxjlx", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "������ѧ������", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ��ѧ���ѯ��� getZxjRes ---- ��ѧ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqzxjlx,sqsj,xysh,xxsh from view_nblg_zxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqzxjlx", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ������ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE saveZxjShxx ---- ������ѧ�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjShxx(ZxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isZxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_zxj", new String[] { "xxsh",
					"xxshyj" }, new String[] { xxsh, xxshyj }, "xn||xh", xn
					+ xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_zxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ������ѧ����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,sxnywwjcf,xxqk,chhzjlhzzqk,sqdj,jthk,jtlx,sfly,sfls,jtrkzs,jtyzsr,jtrjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,sqly,sqsj,bdsyj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjzxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "sxnywwjcf", "xxqk",
				"chhzjlhzzqk", "sqdj", "jthk", "jtlx", "sfly", "sfls",
				"jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm",
				"jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm",
				"jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw",
				"jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
				"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr",
				"sqly", "sqsj", "bdsyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * ���������ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveGjzxjrdSqxx ---- ���������ѧ���϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sxnywwjcf = Base.chgNull(model.getSxnywwjcf(), "", 1);
		String xxqk = Base.chgNull(model.getXxqk(), "", 1);
		String chhzjlhzzqk = Base.chgNull(model.getChhzjlhzzqk(), "", 1);
		String sqdj = Base.chgNull(model.getSqdj(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String sfly = Base.chgNull(model.getSfly(), "", 1);
		String sfls = Base.chgNull(model.getSfls(), "", 1);
		String jtrkzs = Base.chgNull(model.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjzxj", new String[] { "xn",
					"xh", "sxnywwjcf", "xxqk", "chhzjlhzzqk", "sqdj", "jthk",
					"jtlx", "sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "sqly" }, new String[] { xn, xh,
					sxnywwjcf, xxqk, chhzjlhzzqk, sqdj, jthk, jtlx, sfly, sfls,
					jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
					"sxnywwjcf", "xxqk", "chhzjlhzzqk", "sqdj", "jthk", "jtlx",
					"sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr", "srly",
					"jtzz", "yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "sqly", "sqsj", "bdsyj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { sxnywwjcf,
					xxqk, chhzjlhzzqk, sqdj, jthk, jtlx, sfly, sfls, jtrkzs,
					jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm, jtcy1_cw,
					jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
					jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw,
					jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr,
					sqly, now, "", "δ���", "", "δ���", "" }, "xn||xh", xn + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �жϹ�����ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isZxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjzxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjzxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ������ѧ���������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("sxnywwjcf", Base.chgNull(model.getSxnywwjcf(), "", 1));
		stuList.put("xxqk", Base.chgNull(model.getXxqk(), "", 1));
		stuList.put("chhzjlhzzqk", Base.chgNull(model.getChhzjlhzzqk(), "", 1));
		stuList.put("sqdj", Base.chgNull(model.getSqdj(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtlx", Base.chgNull(model.getJtlx(), "", 1));
		stuList.put("sfly", Base.chgNull(model.getSfly(), "", 1));
		stuList.put("sfls", Base.chgNull(model.getSfls(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(model.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("bdsyj", Base.chgNull(model.getBdsyj(), "", 1));
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
				sqlT[i] = "delete nblg_gjzxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjzxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĺ�����ѧ����˽��
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
				sqlT[i] = "update nblg_gjzxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjzxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ������ѧ���ѯ��ͷ getGjzxjTit ---- ������ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqdj", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "����ȼ�", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ������ѧ���ѯ��� getGjzxjRes ---- ������ѧ����
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
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqdj,sqsj,xysh,xxsh from view_nblg_gjzxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqdj", "sqsj", "xysh", "xxsh" };

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
	public boolean saveGjzxjShxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String bdsyj = Base.chgNull(model.getBdsyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjzxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjzxj", new String[] {
						"xysh", "xyshyj", "bdsyj" }, new String[] { xysh,
						xyshyj, bdsyj }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ������־��ѧ����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,sxnzycjzypm,sxndycpbjpm,sxnywwjcf,sxnbjgkcs,chhzjlhzzqk,jthk,jtlx,sfly,sfls,jtrkzs,jtyzsr,jtrjysr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,sqly,sqsj,bdsyj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjlzjxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "sxnzycjzypm", "sxndycpbjpm",
				"sxnywwjcf", "sxnbjgkcs", "chhzjlhzzqk", "jthk", "jtlx",
				"sfly", "sfls", "jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz",
				"yzbm", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr",
				"jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm",
				"jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw",
				"jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
				"jtcy5_sr", "sqly", "sqsj", "bdsyj", "xysh", "xyshyj", "xxsh",
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
	 * �õ�������־��ѧ������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
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
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='������־��ѧ��' and b.xh=? ";
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
	 * ���������־��ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveGjlzjxjrdSqxx ---- ���������־��ѧ���϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String sxnzycjzypm = Base.chgNull(model.getSxnzycjzypm(), "", 1);
		String sxndycpbjpm = Base.chgNull(model.getSxndycpbjpm(), "", 1);
		String sxnywwjcf = Base.chgNull(model.getSxnywwjcf(), "", 1);
		String sxnbjgkcs = Base.chgNull(model.getSxnbjgkcs(), "", 1);
		String chhzjlhzzqk = Base.chgNull(model.getChhzjlhzzqk(), "", 1);
		String jthk = Base.chgNull(model.getJthk(), "", 1);
		String jtlx = Base.chgNull(model.getJtlx(), "", 1);
		String sfly = Base.chgNull(model.getSfly(), "", 1);
		String sfls = Base.chgNull(model.getSfls(), "", 1);
		String jtrkzs = Base.chgNull(model.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(model.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(model.getJtrjysr(), "", 1);
		String srly = Base.chgNull(model.getSrly(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjlzjxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjlzjxj", new String[] {
					"xn", "xh", "sxnzycjzypm", "sxndycpbjpm", "sxnywwjcf",
					"sxnbjgkcs", "chhzjlhzzqk", "jthk", "jtlx", "sfly", "sfls",
					"jtrkzs", "jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm",
					"jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm",
					"jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw",
					"jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
					"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr",
					"sqly" }, new String[] { xn, xh, sxnzycjzypm, sxndycpbjpm,
					sxnywwjcf, sxnbjgkcs, chhzjlhzzqk, jthk, jtlx, sfly, sfls,
					jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
					"sxnzycjzypm", "sxndycpbjpm", "sxnywwjcf", "sxnbjgkcs",
					"chhzjlhzzqk", "jthk", "jtlx", "sfly", "sfls", "jtrkzs",
					"jtyzsr", "jtrjysr", "srly", "jtzz", "yzbm", "jtcy1_xm",
					"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
					"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
					"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
					"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "sqly",
					"sqsj", "bdsyj", "xysh", "xyshyj", "xxsh", "xxshyj" },
					new String[] { sxnzycjzypm, sxndycpbjpm, sxnywwjcf,
							sxnbjgkcs, chhzjlhzzqk, jthk, jtlx, sfly, sfls,
							jtrkzs, jtyzsr, jtrjysr, srly, jtzz, yzbm,
							jtcy1_xm, jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm,
							jtcy2_cw, jtcy2_gz, jtcy2_sr, jtcy3_xm, jtcy3_cw,
							jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw, jtcy4_gz,
							jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr,
							sqly, now, "", "δ���", "", "δ���", "" }, "xn||xh", xn
							+ xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �жϹ�����־��ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isZxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjlzjxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjlzjxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ������־��ѧ���������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("sxnzycjzypm", Base.chgNull(model.getSxnzycjzypm(), "", 1));
		stuList.put("sxndycpbjpm", Base.chgNull(model.getSxndycpbjpm(), "", 1));
		stuList.put("sxnywwjcf", Base.chgNull(model.getSxnywwjcf(), "", 1));
		stuList.put("sxnbjgkcs", Base.chgNull(model.getSxnbjgkcs(), "", 1));
		stuList.put("chhzjlhzzqk", Base.chgNull(model.getChhzjlhzzqk(), "", 1));
		stuList.put("jthk", Base.chgNull(model.getJthk(), "", 1));
		stuList.put("jtlx", Base.chgNull(model.getJtlx(), "", 1));
		stuList.put("sfly", Base.chgNull(model.getSfly(), "", 1));
		stuList.put("sfls", Base.chgNull(model.getSfls(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(model.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(model.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(model.getJtrjysr(), "", 1));
		stuList.put("srly", Base.chgNull(model.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("bdsyj", Base.chgNull(model.getBdsyj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * ɾ��������־��ѧ����Ϣ delGjlzjxjxx ---- ɾ��������־��ѧ����Ϣ
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
				sqlT[i] = "delete nblg_gjlzjxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjlzjxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĺ�����־��ѧ����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_gjlzjxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjlzjxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ������־��ѧ���ѯ��ͷ getGjlzjxjTit ---- ������־��ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ������־��ѧ���ѯ��� getGjlzjxjRes ---- ������־��ѧ����
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
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_gjlzjxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ���������־��ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE saveGjlzjxjShxx ---- ���������־��ѧ�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
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
		String bdsyj = Base.chgNull(model.getBdsyj(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjlzjxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjlzjxj", new String[] {
						"xysh", "xyshyj", "bdsyj" }, new String[] { xysh,
						xyshyj, bdsyj }, "xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ�ʺ������ѧ����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,jg,jtzz,jtcy1_xm,jtcy1_cw,jtcy1_gz,jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw,jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm,jtcy5_cw,jtcy5_gz,jtcy5_sr,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_chzxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "jg", "jtzz", "jtcy1_xm",
				"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
				"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
				"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "lxdh", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * �õ��ʺ������ѧ������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getChzxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='�ʺ������ѧ��' and b.xh=? ";
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
	 * ����ʺ������ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveChzxjrdSqxx ---- ����ʺ������ѧ���϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjSqxx(ChzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isChzxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_chzxj", new String[] { "xn",
					"xh", "jg", "jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "lxdh", "sqly" }, new String[] {
					xn, xh, jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz, jtcy1_sr,
					jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr, jtcy3_xm, jtcy3_cw,
					jtcy3_gz, jtcy3_sr, jtcy4_xm, jtcy4_cw, jtcy4_gz, jtcy4_sr,
					jtcy5_xm, jtcy5_cw, jtcy5_gz, jtcy5_sr, lxdh, sqly },
					request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_chzxj", new String[] { "jg",
					"jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz", "jtcy1_sr",
					"jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr", "jtcy3_xm",
					"jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm", "jtcy4_cw",
					"jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
					"jtcy5_sr", "lxdh", "sqly", "sqsj", "xysh", "xyshyj",
					"xxsh", "xxshyj" }, new String[] { jg, jtzz, jtcy1_xm,
					jtcy1_cw, jtcy1_gz, jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz,
					jtcy2_sr, jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
					jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw, jtcy5_gz,
					jtcy5_sr, lxdh, sqly, now, "δ���", "", "δ���", "" },
					"xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �жϲʺ������ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isChzxjdatacf ----
	 * �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isChzxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_chzxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_chzxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ�ʺ������ѧ���������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjSqb(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		stuList.put("xxmc", Base.xxmc);
		return stuList;
	}

	/**
	 * ɾ���ʺ������ѧ����Ϣ delChzxjxx ---- ɾ���ʺ������ѧ����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delChzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_chzxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_chzxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĳʺ������ѧ����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modChzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_chzxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_chzxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �ʺ������ѧ���ѯ��ͷ getChzxjTit ---- �ʺ������ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * �ʺ������ѧ���ѯ��� getChzxjRes ---- �ʺ������ѧ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getChzxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_chzxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ����ʺ������ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE saveChzxjShxx ---- ����ʺ������ѧ�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjShxx(ChzxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isChzxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_chzxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_chzxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ�����ܻ��У��У����ѧ����ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,nj,xydm,xymc," 
					 + "zydm,zymc,bjdm,bjmc,kndj,jg,jtzz,jtcy1_xm,jtcy1_cw,jtcy1_gz," 
					 + "jtcy1_sr,jtcy2_xm,jtcy2_cw,jtcy2_gz,jtcy2_sr,jtcy3_xm,jtcy3_cw," 
					 + "jtcy3_gz,jtcy3_sr,jtcy4_xm,jtcy4_cw,jtcy4_gz,jtcy4_sr,jtcy5_xm," 
					 + "jtcy5_cw,jtcy5_gz,jtcy5_sr,lxdh,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zxjje from view_nblg_zxszxj where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "nj", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "kndj", "jg", "jtzz", "jtcy1_xm",
				"jtcy1_cw", "jtcy1_gz", "jtcy1_sr", "jtcy2_xm", "jtcy2_cw",
				"jtcy2_gz", "jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
				"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz", "jtcy4_sr",
				"jtcy5_xm", "jtcy5_cw", "jtcy5_gz", "jtcy5_sr", "lxdh", "sqly",
				"sqsj", "xysh", "xyshyj", "xxsh", "xxshyj","zxjje" };
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
	 * ��ȡ�����ܻ���ѧ����Ϣ getCszhzxj
	 * author ������
	 * data 2010-7-15
	 * 
	 */
	public List<Map<String,String>>getCszhzxj(StringBuffer strBuffer){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,nl,jtzz,sqly,xymc,nj,zymc,sfzh,zxjje from view_nblg_zxszxj"+strBuffer;
	    List knsrdList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm","xb","nl","jtzz","sqly","xymc","nj","zymc","sfzh","zxjje" });
	    return knsrdList;
	}
	

	/**
	 * �õ������ܻ��У��У����ѧ������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getZxszxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='�����ܻ��У��У����ѧ��' and b.xh=? ";
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
	 * ��ȡ�ʺ������ѧ����Ϣ getChcszxj
	 * author ������
	 * data 2010-7-15
	 * 
	 */
	public List<Map<String,String>>getChcszxj(StringBuffer strBuffer){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,nl,jtzz,sqly,xymc,nj,zymc from view_nblg_chzxj "+strBuffer;
	    List knsrdList = dao.getList(sql, new String[] {}, new String[] {
	    		"xm","xb","nl","jtzz","sqly","xymc","nj","zymc" });
	    return knsrdList;
	}
	
	
	/**
	 * ��������ܻ��У��У����ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveZxszxjrdSqxx ----
	 * ��������ܻ��У��У����ѧ���϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjSqxx(ZxszxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String jtzz = Base.chgNull(model.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(model.getJtcy1_xm(), "", 1);
		String jtcy1_cw = Base.chgNull(model.getJtcy1_cw(), "", 1);
		String jtcy1_gz = Base.chgNull(model.getJtcy1_gz(), "", 1);
		String jtcy1_sr = Base.chgNull(model.getJtcy1_sr(), "", 1);
		String jtcy2_xm = Base.chgNull(model.getJtcy2_xm(), "", 1);
		String jtcy2_cw = Base.chgNull(model.getJtcy2_cw(), "", 1);
		String jtcy2_gz = Base.chgNull(model.getJtcy2_gz(), "", 1);
		String jtcy2_sr = Base.chgNull(model.getJtcy2_sr(), "", 1);
		String jtcy3_xm = Base.chgNull(model.getJtcy3_xm(), "", 1);
		String jtcy3_cw = Base.chgNull(model.getJtcy3_cw(), "", 1);
		String jtcy3_gz = Base.chgNull(model.getJtcy3_gz(), "", 1);
		String jtcy3_sr = Base.chgNull(model.getJtcy3_sr(), "", 1);
		String jtcy4_xm = Base.chgNull(model.getJtcy4_xm(), "", 1);
		String jtcy4_cw = Base.chgNull(model.getJtcy4_cw(), "", 1);
		String jtcy4_gz = Base.chgNull(model.getJtcy4_gz(), "", 1);
		String jtcy4_sr = Base.chgNull(model.getJtcy4_sr(), "", 1);
		String jtcy5_xm = Base.chgNull(model.getJtcy5_xm(), "", 1);
		String jtcy5_cw = Base.chgNull(model.getJtcy5_cw(), "", 1);
		String jtcy5_gz = Base.chgNull(model.getJtcy5_gz(), "", 1);
		String jtcy5_sr = Base.chgNull(model.getJtcy5_sr(), "", 1);		
		String lxdh = Base.chgNull(model.getLxdh(), "", 1);
		String sqly = Base.chgNull(model.getSqly(), "", 1);
		String zxjje = Base.chgNull(model.getZxjje(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isZxszxjDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation
					.insert("nblg_zxszxj", new String[] { "xn", "xh", "jg",
							"jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
							"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz",
							"jtcy2_sr", "jtcy3_xm", "jtcy3_cw", "jtcy3_gz",
							"jtcy3_sr", "jtcy4_xm", "jtcy4_cw", "jtcy4_gz",
							"jtcy4_sr", "jtcy5_xm", "jtcy5_cw", "jtcy5_gz",
							"jtcy5_sr", "lxdh", "sqly", "zxjje" }, new String[] { xn,
							xh, jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz,
							jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
							jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
							jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw,
							jtcy5_gz, jtcy5_sr, lxdh, sqly ,zxjje }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
					"jg", "jtzz", "jtcy1_xm", "jtcy1_cw", "jtcy1_gz",
					"jtcy1_sr", "jtcy2_xm", "jtcy2_cw", "jtcy2_gz", "jtcy2_sr",
					"jtcy3_xm", "jtcy3_cw", "jtcy3_gz", "jtcy3_sr", "jtcy4_xm",
					"jtcy4_cw", "jtcy4_gz", "jtcy4_sr", "jtcy5_xm", "jtcy5_cw",
					"jtcy5_gz", "jtcy5_sr", "lxdh", "sqly", "zxjje", "sqsj", "xysh",
					"xyshyj", "xxsh", "xxshyj" },
					new String[] { jg, jtzz, jtcy1_xm, jtcy1_cw, jtcy1_gz,
							jtcy1_sr, jtcy2_xm, jtcy2_cw, jtcy2_gz, jtcy2_sr,
							jtcy3_xm, jtcy3_cw, jtcy3_gz, jtcy3_sr, jtcy4_xm,
							jtcy4_cw, jtcy4_gz, jtcy4_sr, jtcy5_xm, jtcy5_cw,
							jtcy5_gz, jtcy5_sr, lxdh, sqly, zxjje, now, "δ���", "",
							"δ���", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �жϴ����ܻ��У��У����ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isZxszxjdatacf ----
	 * �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isZxszxjDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_zxszxj where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_zxszxj where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ�����ܻ��У��У����ѧ���������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjSqb(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("jtzz", Base.chgNull(model.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(model.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_cw", Base.chgNull(model.getJtcy1_cw(), "", 1));
		stuList.put("jtcy1_gz", Base.chgNull(model.getJtcy1_gz(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(model.getJtcy1_sr(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(model.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_cw", Base.chgNull(model.getJtcy2_cw(), "", 1));
		stuList.put("jtcy2_gz", Base.chgNull(model.getJtcy2_gz(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(model.getJtcy2_sr(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(model.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_cw", Base.chgNull(model.getJtcy3_cw(), "", 1));
		stuList.put("jtcy3_gz", Base.chgNull(model.getJtcy3_gz(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(model.getJtcy3_sr(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(model.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_cw", Base.chgNull(model.getJtcy4_cw(), "", 1));
		stuList.put("jtcy4_gz", Base.chgNull(model.getJtcy4_gz(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(model.getJtcy4_sr(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(model.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_cw", Base.chgNull(model.getJtcy5_cw(), "", 1));
		stuList.put("jtcy5_gz", Base.chgNull(model.getJtcy5_gz(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(model.getJtcy5_sr(), "", 1));
		stuList.put("lxdh", Base.chgNull(model.getLxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		stuList.put("xxmc", Base.xxmc);
		return stuList;
	}

	/**
	 * ɾ�������ܻ��У��У����ѧ����Ϣ delZxszxjxx ---- ɾ�������ܻ��У��У����ѧ����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delZxszxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_zxszxj where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_zxszxj where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĵ����ܻ��У��У����ѧ����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modZxszxjxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_zxszxj set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_zxszxj set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����ܻ��У��У����ѧ���ѯ��ͷ getZxszxjTit ---- �����ܻ��У��У����ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxszxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * �����ܻ��У��У����ѧ���ѯ��� getZxszxjRes ---- �����ܻ��У��У����ѧ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxszxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqsj,xysh,xxsh from view_nblg_zxszxj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ��������ܻ��У��У����ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE saveZxszxjShxx ----
	 * ��������ܻ��У��У����ѧ�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjShxx(ZxszxjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isZxszxjDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_zxszxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}

	/**
	 * ��ȡ������ѧ������ϸ��Ϣ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,xh,xm,xb,sfzh,mzmc,zzmmmc,csny,rxny,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,kndj,jg,hjszd,jkzk,jtdh,yddh,jttxdz,yzbm,email,sqje,jzr1_xm,jzr1_xb,jzr1_sfzh,jzr1_gx,jzr1_xzz,jzr1_lxdh,jzr2_xm,jzr2_xb,jzr2_sfzh,jzr2_gx,jzr2_xzz,jzr2_lxdh,sqsj,xysh,xyshyj,xxsh,xxshyj from view_nblg_gjzxdk where xn||xh = ?";
		String[] colList = new String[] { "xn", "xh", "xm", "xb", "sfzh",
				"mzmc", "zzmmmc", "csny", "rxny", "xz", "nj", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "kndj", "jg", "hjszd", "jkzk",
				"jtdh", "yddh", "jttxdz", "yzbm", "email", "sqje", "jzr1_xm",
				"jzr1_xb", "jzr1_sfzh", "jzr1_gx", "jzr1_xzz", "jzr1_lxdh",
				"jzr2_xm", "jzr2_xb", "jzr2_sfzh", "jzr2_gx", "jzr2_xzz",
				"jzr2_lxdh", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * �õ�������ѧ��������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
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
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='������ѧ����' and b.xh=? ";
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
	 * ���������ѧ����������Ϣ���ɹ�����TRUE����֮����FALSE saveGjzxdkrdSqxx ---- ���������ѧ�����϶�������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xh = Base.chgNull(model.getXh(), "", 1);
		String jg = Base.chgNull(model.getJg(), "", 1);
		String hjszd = Base.chgNull(model.getHjszd(), "", 1);
		String jkzk = Base.chgNull(model.getJkzk(), "", 1);
		String jtdh = Base.chgNull(model.getJtdh(), "", 1);
		String yddh = Base.chgNull(model.getYddh(), "", 1);
		String jttxdz = Base.chgNull(model.getJttxdz(), "", 1);
		String yzbm = Base.chgNull(model.getYzbm(), "", 1);
		String email = Base.chgNull(model.getEmail(), "", 1);
		String sqje = Base.chgNull(model.getSqje(), "", 1);
		String jzr1_xm = Base.chgNull(model.getJzr1_xm(), "", 1);
		String jzr1_xb = Base.chgNull(model.getJzr1_xb(), "", 1);
		String jzr1_sfzh = Base.chgNull(model.getJzr1_sfzh(), "", 1);
		String jzr1_gx = Base.chgNull(model.getJzr1_gx(), "", 1);
		String jzr1_xzz = Base.chgNull(model.getJzr1_xzz(), "", 1);
		String jzr1_lxdh = Base.chgNull(model.getJzr1_lxdh(), "", 1);
		String jzr2_xm = Base.chgNull(model.getJzr2_xm(), "", 1);
		String jzr2_xb = Base.chgNull(model.getJzr2_xb(), "", 1);
		String jzr2_sfzh = Base.chgNull(model.getJzr2_sfzh(), "", 1);
		String jzr2_gx = Base.chgNull(model.getJzr2_gx(), "", 1);
		String jzr2_xzz = Base.chgNull(model.getJzr2_xzz(), "", 1);
		String jzr2_lxdh = Base.chgNull(model.getJzr2_lxdh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkDataCf(xh, xn);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_gjzxdk",
					new String[] { "xn", "xh", "jg", "hjszd", "jkzk", "jtdh",
							"yddh", "jttxdz", "yzbm", "email", "sqje",
							"jzr1_xm", "jzr1_xb", "jzr1_sfzh", "jzr1_gx",
							"jzr1_xzz", "jzr1_lxdh", "jzr2_xm", "jzr2_xb",
							"jzr2_sfzh", "jzr2_gx", "jzr2_xzz", "jzr2_lxdh" },
					new String[] { xn, xh, jg, hjszd, jkzk, jtdh, yddh, jttxdz,
							yzbm, email, sqje, jzr1_xm, jzr1_xb, jzr1_sfzh,
							jzr1_gx, jzr1_xzz, jzr1_lxdh, jzr2_xm, jzr2_xb,
							jzr2_sfzh, jzr2_gx, jzr2_xzz, jzr2_lxdh }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
					"jg", "hjszd", "jkzk", "jtdh", "yddh", "jttxdz", "yzbm",
					"email", "sqje", "jzr1_xm", "jzr1_xb", "jzr1_sfzh",
					"jzr1_gx", "jzr1_xzz", "jzr1_lxdh", "jzr2_xm", "jzr2_xb",
					"jzr2_sfzh", "jzr2_gx", "jzr2_xzz", "jzr2_lxdh", "sqsj",
					"xysh", "xyshyj", "xxsh", "xxshyj" }, new String[] { jg,
					hjszd, jkzk, jtdh, yddh, jttxdz, yzbm, email, sqje,
					jzr1_xm, jzr1_xb, jzr1_sfzh, jzr1_gx, jzr1_xzz, jzr1_lxdh,
					jzr2_xm, jzr2_xb, jzr2_sfzh, jzr2_gx, jzr2_xzz, jzr2_lxdh,
					now, "δ���", "", "δ���", "" }, "xn||xh", xn + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �жϹ�����ѧ�����Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isGjzxdkdatacf ----
	 * �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxdkDataCf(String xh, String xn) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_gjzxdk where xh = ? and xn = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from nblg_gjzxdk where xh = ? and xn = ?";
			num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ������ѧ�����������ϸ��Ϣ
	 * 
	 * @param model,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("xn", Base.chgNull(model.getXn(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(model.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(model.getXb(), "", 1));
		stuList.put("sfzh", Base.chgNull(model.getSfzh(), "", 1));
		stuList.put("mzmc", Base.chgNull(model.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(model.getZzmmmc(), "", 1));
		stuList.put("csny", Base.chgNull(model.getCsny(), "", 1));
		stuList.put("rxny", Base.chgNull(model.getRxny(), "", 1));
		stuList.put("xz", Base.chgNull(model.getXz(), "", 1));
		stuList.put("nj", Base.chgNull(model.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(model.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(model.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(model.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(model.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(model.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(model.getBjmc(), "", 1));
		stuList.put("kndj", Base.chgNull(model.getKndj(), "", 1));
		stuList.put("jg", Base.chgNull(model.getJg(), "", 1));
		stuList.put("hjszd", Base.chgNull(model.getHjszd(), "", 1));
		stuList.put("jkzk", Base.chgNull(model.getJkzk(), "", 1));
		stuList.put("jtdh", Base.chgNull(model.getJtdh(), "", 1));
		stuList.put("yddh", Base.chgNull(model.getYddh(), "", 1));
		stuList.put("jttxdz", Base.chgNull(model.getJttxdz(), "", 1));
		stuList.put("yzbm", Base.chgNull(model.getYzbm(), "", 1));
		stuList.put("email", Base.chgNull(model.getEmail(), "", 1));
		stuList.put("sqje", Base.chgNull(model.getSqje(), "", 1));
		stuList.put("jzr1_xm", Base.chgNull(model.getJzr1_xm(), "", 1));
		stuList.put("jzr1_xb", Base.chgNull(model.getJzr1_xb(), "", 1));
		stuList.put("jzr1_sfzh", Base.chgNull(model.getJzr1_sfzh(), "", 1));
		stuList.put("jzr1_gx", Base.chgNull(model.getJzr1_gx(), "", 1));
		stuList.put("jzr1_xzz", Base.chgNull(model.getJzr1_xzz(), "", 1));
		stuList.put("jzr1_lxdh", Base.chgNull(model.getJzr1_lxdh(), "", 1));
		stuList.put("jzr2_xm", Base.chgNull(model.getJzr2_xm(), "", 1));
		stuList.put("jzr2_xb", Base.chgNull(model.getJzr2_xb(), "", 1));
		stuList.put("jzr2_sfzh", Base.chgNull(model.getJzr2_sfzh(), "", 1));
		stuList.put("jzr2_gx", Base.chgNull(model.getJzr2_gx(), "", 1));
		stuList.put("jzr2_xzz", Base.chgNull(model.getJzr2_xzz(), "", 1));
		stuList.put("jzr2_lxdh", Base.chgNull(model.getJzr2_lxdh(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		return stuList;
	}

	/**
	 * ɾ��������ѧ������Ϣ delGjzxdkxx ---- ɾ��������ѧ������Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete nblg_gjzxdk where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete nblg_gjzxdk where xn||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸Ĺ�����ѧ������˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update nblg_gjzxdk set xxsh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update nblg_gjzxdk set xysh='" + shjg
						+ "' where xn||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ������ѧ�����ѯ��ͷ getGjzxdkTit ---- ������ѧ�����ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "ѧ��", "����",
				Base.YXPZXY_KEY+"����", "�༶����", "������", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ������ѧ�����ѯ��� getGjzxdkRes ---- ������ѧ������
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
			sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,xn||xh pk,xn,xh,xm,xymc,bjmc,sqje,sqsj,xysh,xxsh from view_nblg_gjzxdk where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xn", "xh", "xm",
				"xymc", "bjmc", "sqje", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ���������ѧ���������Ϣ���ɹ�����TRUE����֮����FALSE saveGjzxdkShxx ---- ���������ѧ���������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String xn = Base.chgNull(model.getXn(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isGjzxdkDataCf(xh, xn);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"xn||xh", xn + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("nblg_gjzxdk", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"xn||xh", xn + xh, request);
			}
		}

		return bFlag;
	}
}
