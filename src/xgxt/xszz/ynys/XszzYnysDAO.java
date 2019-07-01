package xgxt.xszz.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * Description: ��������ѧԺѧ������DAO
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
 * Time: 2009-04-28
 * </p>
 */
public class XszzYnysDAO {
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xn = queryModel.getXn();
		String nd = queryModel.getNd();
		String xh = DealString.toGBK(queryModel.getXh());
		String xm = DealString.toGBK(queryModel.getXm());
		String xydm = queryModel.getXydm();
		String zydm = queryModel.getZydm();
		String bjdm = queryModel.getBjdm();
		String nj = queryModel.getNj();
		String hth = queryModel.getHth();
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
			whereSql.append(" and xh like '%'||?||'%'");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm like '%'||?||'%'");
			values.add(xm);
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
		if (!StringUtils.isNull(hth)) {
			whereSql.append(" and hth = ?");
			values.add(hth);
		}// end if
		if (!StringUtils.isNull(xysh)) {
			whereSql.append(" and xysh = ?");
			values.add(xysh);
		}
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh = ?");
			values.add(xxsh);
		}
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
	 * ��ȡѧ�ѻ�����ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,sfzh,rxrq,csrq,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,hjje,hjqx,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_ynys_xfhj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"sfzh", "rxrq", "csrq", "mzmc", "zzmmmc", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "hjje", "hjqx", "sqly", "sqsj",
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
	 * �õ�ѧ�ѻ�������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='ѧ�ѻ���' and b.xh=? ";
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
	 * ����ѧ�ѻ���������Ϣ���ɹ�����TRUE����֮����FALSE saveXfhjSqxx ---- ����ѧ�ѻ���������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel xfhjModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(xfhjModel.getNd(), "", 1);
		String xh = Base.chgNull(xfhjModel.getXh(), "", 1);
		String hjje = Base.chgNull(xfhjModel.getHjje(), "", 1);
		String hjqx = Base.chgNull(xfhjModel.getHjqx(), "", 1);
		String sqly = Base.chgNull(xfhjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isXfhjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_ynys_xfhj", new String[] {
					"nd", "xh", "hjje", "hjqx", "sqly" }, new String[] { nd,
					xh, hjje, hjqx, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_ynys_xfhj", new String[] {
					"hjje", "hjqx", "sqly", "sqsj", "xysh", "xyshyj", "xxsh",
					"xxshyj" }, new String[] { hjje, hjqx, sqly, now, "δ���",
					"", "δ���", "" }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * �ж�ѧ�ѻ����Ƿ��ظ����ظ���ͨ��ѧУ��˵ķ���2���ظ���ͨ��ѧԺ��˵ķ���3���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isxfhjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isXfhjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_ynys_xfhj where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_ynys_xfhj where xh = ? and nd = ? and xysh in ('ͨ��')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_ynys_xfhj where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * ��ȡѧ�ѻ����������ϸ��Ϣ
	 * 
	 * @param knsModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel model,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(model.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(model.getXh(), "", 1));
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
		stuList.put("hjje", Base.chgNull(model.getHjje(), "", 1));
		stuList.put("hjqx", Base.chgNull(model.getHjqx(), "", 1));
		stuList.put("sqly", Base.chgNull(model.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(model.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(model.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(model.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(model.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(model.getXxshyj(), "", 1));
		
		return stuList;
	}
	
	/**
	 * ɾ��ѧ�ѻ�����Ϣ delXfhjxx ---- ɾ��ѧ�ѻ�����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_ynys_xfhj where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_ynys_xfhj where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸�ѧ�ѻ�����˽�� modXfhjxx ---- �����޸�ѧ�ѻ�����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_ynys_xfhj set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_ynys_xfhj set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��ͷ xfhjtit ---- ѧ�ѻ��ɱ�ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"zymc", "bjmc", "hjje", "hjqx", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"רҵ����", "�༶����", "���ɽ��", "��������", "����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��� xfhjres ---- ѧ�ѻ��ɽ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,hjqx,sqsj,xysh,xxsh from view_xszz_ynys_xfhj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,hjqx,sqsj,xysh,xxsh from view_xszz_ynys_xfhj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,hjqx,sqsj,xysh,xxsh from view_xszz_ynys_xfhj where xysh='ͨ��'";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"zymc", "bjmc", "hjje", "hjqx", "sqsj", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ����ѧ�ѻ��������Ϣ���ɹ�����TRUE����֮����FALSE saveXfhjShxx ---- ����ѧ�ѻ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(model.getXh(), "", 1);
		String nd = Base.chgNull(model.getNd(), "", 1);
		String xysh = Base.chgNull(model.getXysh(), "", 1);
		String xyshyj = Base.chgNull(model.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(model.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(model.getXxshyj(), "", 1);
		String sHave = isXfhjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_ynys_xfhj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_ynys_xfhj",
						new String[] { "xysh", "xyshyj" }, new String[] { xysh,
								xyshyj }, "nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * ��ȡ�������ȼ��б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectKnsdjList(){
		String sql = "select distinct dm,mc from kncddmb order by dm";
		return dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
	}
}
