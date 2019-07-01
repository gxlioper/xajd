
package xgxt.xszz.hzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְҵ����ѧԺѧ������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class XszzHzzyDAO{
	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//��ѯ����ֵ
	
	/**
	 * ���÷�������ȡ��ѯ����
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
		String jtzz = queryModel.getJtzz();
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
		if (!StringUtils.isNull(jtzz)) {
			whereSql.append(" and jtzz like ?");
			values.add("%" + jtzz + "%");
		}// end if
		return whereSql;
	}
	
	/**
	 * ��������
	 * getExpDate ---- �������
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
	 * �������ݱ�ͷ
	 * getExpTit ---- �������ݱ�ͷ
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
	 * ��Уѧ��������Ϣ��ѯ��ͷ
	 * zxxsdktit ---- ��Уѧ��������Ϣ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxxsDkxxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "dkje", "fdsj", "dknx" };
		String[] cnList = new String[] { "����", "ѧ��", "ѧ��", "����", "�Ա�", "���֤��",
				Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "������", "�Ŵ�ʱ��", "��������" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ��Уѧ��������Ϣ��ѯ���
	 * zxxsdkres ---- ��Уѧ�������� 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxxsDkxxRes(QueryModel queryZxxsdkModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xh pk,xn,xh,xm,xb,sfzh,xymc,zymc,bjmc,dkje,fdsj,dknx from view_xszz_hzzyjsxy_zxxsdkxx where 1=1 ";
		String[] colList = new String[] { "pk", "xn", "xh", "xm", "xb", "sfzh",
				"xymc", "zymc", "bjmc", "dkje", "fdsj", "dknx" };
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ��ȡ��Уѧ��������ϸ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xh,xn,xm,xb,sfzh,xz,nj,rxny,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,byny,yxlxdh,jtdh,jtdz,dkje,fdsj,dknx from view_xszz_hzzyjsxy_zxxsdkxx where xn||xh = ?";
		String[] colList = new String[] { "xh", "xn", "xm", "xb", "sfzh", "xz",
				"nj", "rxny", "mzmc", "zzmmmc", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "byny", "yxlxdh", "jtdh", "jtdz", "dkje",
				"fdsj", "dknx" };
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
	 * �ж������Ƿ��ظ����ظ�����TRUE����֮����FALSE
	 * isdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isDataCf(String xh, String xn) throws Exception {
		boolean bFlag = false;
		String sql = "select count(*) num from xszz_hzzyjsxy_zxxsdkxx where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[] { xh, xn }, "num");
		if (!num.equalsIgnoreCase("0")) {
			bFlag = true;
		}// end if
		return bFlag;
	}
	
	/**
	 * ������Уѧ��������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveDkxx ---- ���������Ϣ
	 * @param saveZxxsdkModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveDkxx(SaveZxxsdkModel saveZxxsdkModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = Base.chgNull(saveZxxsdkModel.getXn(), "", 1);
		String xh = Base.chgNull(saveZxxsdkModel.getXh(), "", 1);
		String yxlxdh = Base.chgNull(saveZxxsdkModel.getYxlxdh(), "", 1);
		String jtdh = Base.chgNull(saveZxxsdkModel.getJtdh(), "", 1);
		String jtdz = Base.chgNull(saveZxxsdkModel.getJtdz(), "", 1);
		String dkje = Base.chgNull(saveZxxsdkModel.getDkje(), "", 1);
		String fdsj = Base.chgNull(saveZxxsdkModel.getFdsj(), "", 1);
		String dknx = Base.chgNull(saveZxxsdkModel.getDknx(), "", 1);
		boolean bHave = isDataCf(xh, xn);
		if (bHave) {
			bFlag = StandardOperation.update("xszz_hzzyjsxy_zxxsdkxx",
					new String[] { "xn", "xh", "yxlxdh", "jtdh", "jtdz",
							"dkje", "fdsj", "dknx" }, new String[] { xn, xh,
							yxlxdh, jtdh, jtdz, dkje, fdsj, dknx }, "xn||xh",
					xn + xh, request);
		} else {
			bFlag = StandardOperation.insert("xszz_hzzyjsxy_zxxsdkxx",
					new String[] { "xn", "xh", "yxlxdh", "jtdh", "jtdz",
							"dkje", "fdsj", "dknx" }, new String[] { xn, xh,
							yxlxdh, jtdh, jtdz, dkje, fdsj, dknx }, request);
		}
		return bFlag;
	}
	
	/**
	 * ɾ��������Ϣ
	 * delDkxx ---- ɾ��������Ϣ
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delDkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_hzzyjsxy_zxxsdkxx where xn||xh='" + pkT[i]
					+ "'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ��ȡ��������ϸ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,xz,nj,sfzh,zzmmmc,mzmc,xydm,xymc,zydm,zymc,bjdm,bjmc,csny,jtrjnsr,zxlxdh,sqly,mzrd,rdly,xysh,xxsh,sqsj from view_hzzy_knssqb where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "xz", "nj",
				"sfzh", "zzmmmc", "mzmc", "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "csny", "jtrjnsr", "zxlxdh", "sqly", "mzrd",
				"rdly", "xysh", "xxsh", "sqsj" };
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
	 * �ж��������Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1
	 * isknsdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from hzzy_knssqb where xh = ? and nd = ? and xxsh in ('����','��������')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from hzzy_knssqb where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * �жϹ��ҽ�ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1
	 * isgjjxjdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjjxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from hzzy_xszz_gjjxj where xh = ? and nd = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from hzzy_xszz_gjjxj where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * �жϹ�����ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1
	 * isgjzxjdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from hzzy_xszz_gjzxj where xh = ? and nd = ? and xxsh='ͨ��'";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from hzzy_xszz_gjzxj where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * ����������������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveKnsSqxx ---- ����������������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel saveKnsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(saveKnsModel.getNd(), "", 1);
		String xh = Base.chgNull(saveKnsModel.getXh(), "", 1);
		String jtrjnsr = Base.chgNull(saveKnsModel.getJtrjnsr(), "", 1);
		String zxlxdh = Base.chgNull(saveKnsModel.getZxlxdh(), "", 1);
		String sqly = Base.chgNull(saveKnsModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("hzzy_knssqb", new String[] {
					"nd", "xh", "jtrjnsr", "zxlxdh", "sqly" }, new String[] {
					nd, xh, jtrjnsr, zxlxdh, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("hzzy_knssqb", new String[] {
					"jtrjnsr", "zxlxdh", "sqly", "mzrd", "rdly", "xysh",
					"xxsh", "sqsj" }, new String[] { jtrjnsr, zxlxdh, sqly,
					"δ���", "", "δ���", "δ���", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * ��ȡ�������������ϸ��Ϣ
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
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(knsModel.getZzmmmc(), "", 1));
		stuList.put("mzmc", Base.chgNull(knsModel.getMzmc(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("csny", Base.chgNull(knsModel.getCsny(), "", 1));
		stuList.put("jtrjnsr", Base.chgNull(knsModel.getJtrjnsr(), "", 1));
		stuList.put("zxlxdh", Base.chgNull(knsModel.getZxlxdh(), "", 1));
		stuList.put("sqly", Base.chgNull(knsModel.getSqly(), "", 1));
		stuList.put("mzrd", Base.chgNull(knsModel.getMzrd(), "", 1));
		stuList.put("rdly", Base.chgNull(knsModel.getRdly(), "", 1));
		stuList.put("xysh", Base.chgNull(knsModel.getXysh(), "", 1));
		stuList.put("xxsh", Base.chgNull(knsModel.getXxsh(), "", 1));

		String mzrd = Base.chgNull(knsModel.getMzrd(), "", 1);
		String xysh = Base.chgNull(knsModel.getXysh(), "", 1);
		String xxsh = Base.chgNull(knsModel.getXxsh(), "", 1);

		if (!"δ���".equalsIgnoreCase(mzrd)
				&& StringUtils.isIgnoreCaseEqual(mzrd, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"δ���".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}

		return stuList;
	}

	/**
	 * ��������ѯ��ͷ
	 * knstit ---- ��������ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "mzrd", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", "��������", Base.YXPZXY_KEY+"���",
				"ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ��������ѯ���
	 * knsres ---- ��������� 
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
			sql = "select (case when nvl(xxsh,'δ���') in ('����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,mzrd,xysh,xxsh from view_hzzy_knssqb where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,mzrd,xysh,xxsh from view_hzzy_knssqb where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,mzrd,xysh,xxsh from view_hzzy_knssqb where 1=1";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "mzrd", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ɾ��������Ϣ
	 * delKnsxx ---- ɾ����������Ϣ
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
				sqlT[i] = "delete hzzy_knssqb where nd||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "delete hzzy_knssqb where nd||xh='" + pkT[i]
						+ "' and xxsh not in ('����','��������')";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸���������˽��
	 * modKnsxx ---- �����޸���������˽��
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
				sqlT[i] = "update hzzy_knssqb set xxsh='" + shjg
						+ "' where nd||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update hzzy_knssqb set xysh='" + shjg + "',mzrd='"
						+ shjg + "' where nd||xh='" + pkT[i]
						+ "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ���������������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveKnsShxx ---- ���������������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel saveKnsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(saveKnsModel.getXh(), "", 1);
		String nd = Base.chgNull(saveKnsModel.getNd(), "", 1);
		String mzrd = Base.chgNull(saveKnsModel.getMzrd(), "", 1);
		String rdly = Base.chgNull(saveKnsModel.getRdly(), "", 1);
		String xysh = Base.chgNull(saveKnsModel.getXysh(), "", 1);
		String xxsh = Base.chgNull(saveKnsModel.getXxsh(), "", 1);
		String sHave = isKnsDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("hzzy_knssqb", new String[] {
					"mzrd", "rdly", "xysh", "xxsh" }, new String[] { mzrd,
					rdly, xysh, xxsh }, "nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("hzzy_knssqb", new String[] {
						"mzrd", "rdly", "xysh" }, new String[] { mzrd, rdly,
						xysh }, "nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * ��ȡ���ҽ�ѧ����ϸ��Ϣ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,xsjbqk,xysh,xyshyj,xxsh,xxshyj,sqsj from view_hzzy_xszz_gjjxj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "rxny", "csrq", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "xsjbqk", "xysh",
				"xyshyj", "xxsh", "xxshyj", "sqsj" };
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
	 * �õ����ҽ�ѧ������Ȩ��
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getGjjxjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='���ҽ�ѧ��' and b.xh=? ";
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
	 * ������ҽ�ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveGjjxjSqxx ---- ������ҽ�ѧ��������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjSqxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjjxjModel.getNd(), "", 1);
		String xh = Base.chgNull(gjjxjModel.getXh(), "", 1);
		String lxdh = Base.chgNull(gjjxjModel.getLxdh(), "", 1);
		String xsjbqk = Base.chgNull(gjjxjModel.getXsjbqk(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjjxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("hzzy_xszz_gjjxj", new String[] {
					"nd", "xh", "lxdh", "xsjbqk" }, new String[] { nd, xh,
					lxdh, xsjbqk }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("hzzy_xszz_gjjxj", new String[] {
					"lxdh", "xsjbqk", "xysh", "xyshyj", "xxsh", "xxshyj",
					"sqsj" }, new String[] { lxdh, xsjbqk, "δ���", "", "δ���",
					"", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * ��ȡ���ҽ�ѧ���������ϸ��Ϣ
	 * @param gjjxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjSqb(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjjxjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjjxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjjxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjjxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjjxjModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjjxjModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjjxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjjxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjjxjModel.getSfzh(), "", 1));
		stuList.put("rxny", Base.chgNull(gjjxjModel.getRxny(), "", 1));
		stuList.put("csrq", Base.chgNull(gjjxjModel.getCsrq(), "", 1));
		stuList.put("xymc", Base.chgNull(gjjxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjjxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjjxjModel.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjjxjModel.getLxdh(), "", 1));
		stuList.put("xsjbqk", Base.chgNull(gjjxjModel.getXsjbqk(), "", 1));
		stuList.put("xysh", Base.chgNull(gjjxjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjjxjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjjxjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjjxjModel.getXxshyj(), "", 1));
		stuList.put("sqsj", Base.chgNull(gjjxjModel.getSqsj(), "", 1));

		return stuList;
	}
	
	/**
	 * ɾ�����ҽ�ѧ����Ϣ
	 * delGjjxjxx ---- ɾ�����ҽ�ѧ����Ϣ
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delGjjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete hzzy_xszz_gjjxj where nd||xh='" + pkT[i]
						+ "'";
			} else {
				sqlT[i] = "delete hzzy_xszz_gjjxj where nd||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸Ĺ��ҽ�ѧ����˽��
	 * modGjjxjxx ---- �����޸Ĺ��ҽ�ѧ����˽��
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update hzzy_xszz_gjjxj set xxsh='" + shjg
						+ "' where nd||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update hzzy_xszz_gjjxj set xysh='" + shjg
						+ "' where nd||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ���ҽ�ѧ���ѯ��ͷ
	 * gjjxjtit ---- ���ҽ�ѧ���ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ���ҽ�ѧ���ѯ���
	 * gjjxjres ---- ���ҽ�ѧ���� 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_hzzy_xszz_gjjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_hzzy_xszz_gjjxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,sqsj,xysh,xxsh from view_hzzy_xszz_gjjxj where 1=1";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ������ҽ�ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveGjjxjShxx ---- ������ҽ�ѧ�������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjShxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(gjjxjModel.getXh(), "", 1);
		String nd = Base.chgNull(gjjxjModel.getNd(), "", 1);
		String xsjbqk = Base.chgNull(gjjxjModel.getXsjbqk(), "", 1);
		String xyshyj = Base.chgNull(gjjxjModel.getXyshyj(), "", 1);
		String xxshyj = Base.chgNull(gjjxjModel.getXxshyj(), "", 1);
		String xysh = Base.chgNull(gjjxjModel.getXysh(), "", 1);
		String xxsh = Base.chgNull(gjjxjModel.getXxsh(), "", 1);
		String sHave = isGjjxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("hzzy_xszz_gjjxj", new String[] {
					"xsjbqk", "xyshyj", "xxshyj", "xysh", "xxsh" },
					new String[] { xsjbqk, xyshyj, xxshyj, xysh, xxsh },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("hzzy_xszz_gjjxj",
						new String[] { "xsjbqk", "xyshyj", "xysh" },
						new String[] { xsjbqk, xyshyj, xysh }, "nd||xh", nd
								+ xh, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * ��ȡ������ѧ����ϸ��Ϣ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,zxjdj,lxdh,jthk,jtrkzs,jtyzsr,rjsr,srly,jtzz,yzbm,jtcy1_xm,jtcy1_nl,jtcy1_ybrgx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_ybrgx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_ybrgx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_ybrgx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_ybrgx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_hzzy_xszz_gjzxj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "rxny", "csrq", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "zxjdj", "lxdh", "jthk",
				"jtrkzs", "jtyzsr", "rjsr", "srly", "jtzz", "yzbm", "jtcy1_xm",
				"jtcy1_nl", "jtcy1_ybrgx", "jtcy1_gzdw", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_ybrgx", "jtcy2_gzdw", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_ybrgx", "jtcy3_gzdw", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_ybrgx", "jtcy4_gzdw", "jtcy5_xm",
				"jtcy5_nl", "jtcy5_ybrgx", "jtcy5_gzdw", "sqly", "sqsj",
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
	 * �õ�������ѧ������Ȩ��
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
	 * ���������ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveGjzxjSqxx ---- ���������ѧ��������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjzxjModel.getNd(), "", 1);
		String xh = Base.chgNull(gjzxjModel.getXh(), "", 1);
		String zxjdj = Base.chgNull(gjzxjModel.getZxjdj(), "", 1);
		String lxdh = Base.chgNull(gjzxjModel.getLxdh(), "", 1);
		String jthk = Base.chgNull(gjzxjModel.getJthk(), "", 1);
		String jtrkzs = Base.chgNull(gjzxjModel.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(gjzxjModel.getJtyzsr(), "", 1);
		String rjsr = Base.chgNull(gjzxjModel.getRjsr(), "", 1);
		String srly = Base.chgNull(gjzxjModel.getSrly(), "", 1);
		String jtzz = Base.chgNull(gjzxjModel.getJtzz(), "", 1);
		String yzbm = Base.chgNull(gjzxjModel.getYzbm(), "", 1);
		String jtcy1_xm = Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1);
		String jtcy1_ybrgx = Base.chgNull(gjzxjModel.getJtcy1_ybrgx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1);
		String jtcy2_ybrgx = Base.chgNull(gjzxjModel.getJtcy2_ybrgx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1);
		String jtcy3_ybrgx = Base.chgNull(gjzxjModel.getJtcy3_ybrgx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1);
		String jtcy4_ybrgx = Base.chgNull(gjzxjModel.getJtcy4_ybrgx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1);
		String jtcy5_ybrgx = Base.chgNull(gjzxjModel.getJtcy5_ybrgx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(gjzxjModel.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(gjzxjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("hzzy_xszz_gjzxj", new String[] {
					"nd", "xh", "zxjdj", "lxdh", "jthk", "jtrkzs", "jtyzsr",
					"rjsr", "srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_ybrgx", "jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_ybrgx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_ybrgx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_ybrgx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_ybrgx", "jtcy5_gzdw", "sqly" }, new String[] { nd,
					xh, zxjdj, lxdh, jthk, jtrkzs, jtyzsr, rjsr, srly, jtzz,
					yzbm, jtcy1_xm, jtcy1_nl, jtcy1_ybrgx, jtcy1_gzdw,
					jtcy2_xm, jtcy2_nl, jtcy2_ybrgx, jtcy2_gzdw, jtcy3_xm,
					jtcy3_nl, jtcy3_ybrgx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl,
					jtcy4_ybrgx, jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_ybrgx,
					jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("hzzy_xszz_gjzxj", new String[] {
					"zxjdj", "lxdh", "jthk", "jtrkzs", "jtyzsr", "rjsr",
					"srly", "jtzz", "yzbm", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_ybrgx", "jtcy1_gzdw", "jtcy2_xm", "jtcy2_nl",
					"jtcy2_ybrgx", "jtcy2_gzdw", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_ybrgx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
					"jtcy4_ybrgx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_ybrgx", "jtcy5_gzdw", "sqly", "sqsj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { zxjdj, lxdh,
					jthk, jtrkzs, jtyzsr, rjsr, srly, jtzz, yzbm, jtcy1_xm,
					jtcy1_nl, jtcy1_ybrgx, jtcy1_gzdw, jtcy2_xm, jtcy2_nl,
					jtcy2_ybrgx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl, jtcy3_ybrgx,
					jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_ybrgx, jtcy4_gzdw,
					jtcy5_xm, jtcy5_nl, jtcy5_ybrgx, jtcy5_gzdw, sqly, now,
					"δ���", "", "δ���", "" }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * ��ȡ������ѧ���������ϸ��Ϣ
	 * @param gjzxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjzxjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjzxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjzxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjzxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjzxjModel.getNj(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjzxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjzxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjzxjModel.getSfzh(), "", 1));
		stuList.put("rxny", Base.chgNull(gjzxjModel.getRxny(), "", 1));
		stuList.put("csrq", Base.chgNull(gjzxjModel.getCsrq(), "", 1));
		stuList.put("xymc", Base.chgNull(gjzxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjzxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjzxjModel.getBjmc(), "", 1));
		stuList.put("zxjdj", Base.chgNull(gjzxjModel.getZxjdj(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjzxjModel.getLxdh(), "", 1));
		stuList.put("jthk", Base.chgNull(gjzxjModel.getJthk(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(gjzxjModel.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(gjzxjModel.getJtyzsr(), "", 1));
		stuList.put("rjsr", Base.chgNull(gjzxjModel.getRjsr(), "", 1));
		stuList.put("srly", Base.chgNull(gjzxjModel.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(gjzxjModel.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjzxjModel.getYzbm(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_ybrgx", Base.chgNull(gjzxjModel.getJtcy1_ybrgx(),
				"", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "",
				1));
		stuList.put("jtcy2_xm", Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_ybrgx", Base.chgNull(gjzxjModel.getJtcy2_ybrgx(),
				"", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "",
				1));
		stuList.put("jtcy3_xm", Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_ybrgx", Base.chgNull(gjzxjModel.getJtcy3_ybrgx(),
				"", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "",
				1));
		stuList.put("jtcy4_xm", Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_ybrgx", Base.chgNull(gjzxjModel.getJtcy4_ybrgx(),
				"", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "",
				1));
		stuList.put("jtcy5_xm", Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_ybrgx", Base.chgNull(gjzxjModel.getJtcy5_ybrgx(),
				"", 1));
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
	 * ɾ��������ѧ����Ϣ
	 * delGjzxjxx ---- ɾ��������ѧ����Ϣ
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
				sqlT[i] = "delete hzzy_xszz_gjzxj where nd||xh='" + pkT[i]
						+ "'";
			} else {
				sqlT[i] = "delete hzzy_xszz_gjzxj where nd||xh='" + pkT[i]
						+ "' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸Ĺ�����ѧ����˽��
	 * modGjzxjxx ---- �����޸Ĺ�����ѧ����˽��
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
				sqlT[i] = "update hzzy_xszz_gjzxj set xxsh='" + shjg
						+ "' where nd||xh='" + pkT[i] + "'";
			} else {
				sqlT[i] = "update hzzy_xszz_gjzxj set xysh='" + shjg
						+ "' where nd||xh='" + pkT[i] + "' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ������ѧ���ѯ��ͷ
	 * gjzxjtit ---- ������ѧ���ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "zxjdj", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "��ѧ��ȼ�", "����ʱ��", Base.YXPZXY_KEY+"���",
				"ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ������ѧ���ѯ���
	 * gjzxjres ---- ������ѧ���� 
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
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,zxjdj,sqsj,xysh,xxsh from view_hzzy_xszz_gjzxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,zxjdj,sqsj,xysh,xxsh from view_hzzy_xszz_gjzxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,zymc,bjmc,zxjdj,sqsj,xysh,xxsh from view_hzzy_xszz_gjzxj where 1=1";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "zymc", "bjmc", "zxjdj", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ���������ѧ�������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveGjzxjShxx ---- ���������ѧ�������Ϣ
	 * @param saveModel (���ݱ���ʵ��MODEL),request
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
		String zxjdj = Base.chgNull(gjzxjModel.getZxjdj(), "", 1);
		String xyshyj = Base.chgNull(gjzxjModel.getXyshyj(), "", 1);
		String xxshyj = Base.chgNull(gjzxjModel.getXxshyj(), "", 1);
		String xysh = Base.chgNull(gjzxjModel.getXysh(), "", 1);
		String xxsh = Base.chgNull(gjzxjModel.getXxsh(), "", 1);
		String sHave = isGjzxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("hzzy_xszz_gjzxj", new String[] {
					"zxjdj", "xyshyj", "xxshyj", "xysh", "xxsh" },
					new String[] { zxjdj, xyshyj, xxshyj, xysh, xxsh },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("hzzy_xszz_gjzxj",
						new String[] { "zxjdj", "xyshyj", "xysh" },
						new String[] { zxjdj, xyshyj, xysh }, "nd||xh",
						nd + xh, request);
			}
		}

		return bFlag;
	}
}
