package xgxt.xszz.zjjdzyjsxy;

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
 * Description: �㽭����ְҵ����ѧԺѧ������DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
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
 * Time: 2008-07-22
 * </p>
 */
public class XszzZjjdDAO {
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
	 *  ��ȡѧ�������������Ϣ
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuZzqk(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		stuList.put("yhzzqk_gjzxdk",dao.getOneRs("SELECT SUM(xfdk) je FROM view_xszz_zjjd_gjzxdk WHERE xxsh='ͨ��' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjzxj",dao.getOneRs("SELECT SUM(zxjje) je FROM view_zjjd_gjzxjsqb WHERE xxsh='ͨ��' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjlzjxj",dao.getOneRs("SELECT count(*)*5000 je FROM view_xszz_zjjd_gjlzjxj WHERE xxsh='ͨ��' AND xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_gjjxj",dao.getOneRs("SELECT SUM(JLJE) je FROM view_xsjxjb WHERE xxsh='ͨ��' AND xh=? AND JXJDM='001'", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_xyjxj",dao.getOneRs("SELECT SUM(JLJE) je FROM view_xsjxjb WHERE xxsh='ͨ��' AND xh=? AND JXJDM in ('002','003','004','005')", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_qgzx",dao.getOneRs("SELECT SUM(cjje) je FROM view_xscjff WHERE xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_xfjm",dao.getOneRs("SELECT SUM(XFJMJE) je FROM view_xszz_zjjd_xfjm WHERE xh=?", new String[]{pkVal}, "je"));
		stuList.put("yhzzqk_lxbz",dao.getOneRs("SELECT SUM(LSBZJE) je FROM view_xszz_zjjd_lsbz WHERE xh=?", new String[]{pkVal}, "je"));
		return stuList;
	}

	/**
	 * ��ȡ��������ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,jtrks,sfgc,sfdq,sflszn,xxtxdz,yzbm,lxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk,yhzzqk_gjzxdk,yhzzqk_gjzxj,yhzzqk_gjjxj,yhzzqk_gjlzjxj,yhzzqk_xyjxj,yhzzqk_qgzx,yhzzqk_xfjm,yhzzqk_lxbz,yhzzqk_ddzfshjxj,yhzzqk_hj,jtzszrzhqk,jttfywsj,jtcyycjnmndlqk,jtcysyqk,jtqzqk,qtqk,jtjjqkxxsm,mzbm_xxtxdz,mzbm_yzbm,mzbm_lxdh,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_zjjd_knsxx where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "csrq", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "jtrks", "sfgc", "sfdq", "sflszn",
				"xxtxdz", "yzbm", "lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
				"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr",
				"jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
				"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
				"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy",
				"jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk", "yhzzqk_gjzxj",
				"yhzzqk_gjjxj", "yhzzqk_gjlzjxj", "yhzzqk_xyjxj",
				"yhzzqk_qgzx", "yhzzqk_xfjm", "yhzzqk_lxbz",
				"yhzzqk_ddzfshjxj", "yhzzqk_hj", "jtzszrzhqk", "jttfywsj",
				"jtcyycjnmndlqk", "jtcysyqk", "jtqzqk", "qtqk", "jtjjqkxxsm",
				"mzbm_xxtxdz", "mzbm_yzbm", "mzbm_lxdh", "sqsj", "fdysh",
				"fdyshyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
	 * ����������������Ϣ���ɹ�����TRUE����֮����FALSE saveKnsSqxx ---- ����������������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel knsModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(knsModel.getNd(), "", 1);
		String xh = Base.chgNull(knsModel.getXh(), "", 1);
		String jtrks = Base.chgNull(knsModel.getJtrks(), "", 1);
		String sfgc = Base.chgNull(knsModel.getSfgc(), "", 1);
		String sfdq = Base.chgNull(knsModel.getSfdq(), "", 1);
		String sflszn = Base.chgNull(knsModel.getSflszn(), "", 1);
		String xxtxdz = Base.chgNull(knsModel.getXxtxdz(), "", 1);
		String yzbm = Base.chgNull(knsModel.getYzbm(), "", 1);
		String lxdh = Base.chgNull(knsModel.getLxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(knsModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(knsModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(knsModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(knsModel.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(knsModel.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(knsModel.getJtcy1_sr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(knsModel.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(knsModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(knsModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(knsModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(knsModel.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(knsModel.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(knsModel.getJtcy2_sr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(knsModel.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(knsModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(knsModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(knsModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(knsModel.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(knsModel.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(knsModel.getJtcy3_sr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(knsModel.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(knsModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(knsModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(knsModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(knsModel.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(knsModel.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(knsModel.getJtcy4_sr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(knsModel.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(knsModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(knsModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(knsModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(knsModel.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(knsModel.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(knsModel.getJtcy5_sr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(knsModel.getJtcy5_jkzk(), "", 1);
		String yhzzqk_gjzxdk = Base.chgNull(knsModel.getYhzzqk_gjzxdk(), "", 1);
		String yhzzqk_gjzxj = Base.chgNull(knsModel.getYhzzqk_gjzxj(), "", 1);
		String yhzzqk_gjjxj = Base.chgNull(knsModel.getYhzzqk_gjjxj(), "", 1);
		String yhzzqk_gjlzjxj = Base.chgNull(knsModel.getYhzzqk_gjlzjxj(), "", 1);
		String yhzzqk_xyjxj = Base.chgNull(knsModel.getYhzzqk_xyjxj(), "", 1);
		String yhzzqk_qgzx = Base.chgNull(knsModel.getYhzzqk_qgzx(), "", 1);
		String yhzzqk_xfjm = Base.chgNull(knsModel.getYhzzqk_xfjm(), "", 1);
		String yhzzqk_lxbz = Base.chgNull(knsModel.getYhzzqk_lxbz(), "", 1);
		String yhzzqk_ddzfshjxj = Base.chgNull(knsModel.getYhzzqk_ddzfshjxj(),
				"", 1);
		String yhzzqk_hj = Base.chgNull(knsModel.getYhzzqk_hj(), "", 1);
		String jtzszrzhqk = Base.chgNull(knsModel.getJtzszrzhqk(), "", 1);
		String jttfywsj = Base.chgNull(knsModel.getJttfywsj(), "", 1);
		String jtcyycjnmndlqk = Base.chgNull(knsModel.getJtcyycjnmndlqk(), "",
				1);
		String jtcysyqk = Base.chgNull(knsModel.getJtcysyqk(), "", 1);
		String jtqzqk = Base.chgNull(knsModel.getJtqzqk(), "", 1);
		String qtqk = Base.chgNull(knsModel.getQtqk(), "", 1);
		String jtjjqkxxsm = Base.chgNull(knsModel.getJtjjqkxxsm(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(knsModel.getMzbm_xxtxdz(), "", 1);
		String mzbm_yzbm = Base.chgNull(knsModel.getMzbm_yzbm(), "", 1);
		String mzbm_lxdh = Base.chgNull(knsModel.getMzbm_lxdh(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isKnsDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zjjd_knsxx", new String[] { "nd",
					"xh", "jtrks", "sfgc", "sfdq", "sflszn", "xxtxdz", "yzbm",
					"lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
					"jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr",
					"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
					"jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk",
					"yhzzqk_gjzxj", "yhzzqk_gjjxj", "yhzzqk_gjlzjxj",
					"yhzzqk_xyjxj", "yhzzqk_qgzx", "yhzzqk_xfjm",
					"yhzzqk_lxbz", "yhzzqk_ddzfshjxj", "yhzzqk_hj",
					"jtzszrzhqk", "jttfywsj", "jtcyycjnmndlqk", "jtcysyqk",
					"jtqzqk", "qtqk", "jtjjqkxxsm", "mzbm_xxtxdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj" }, new String[] { nd, xh, jtrks, sfgc,
					sfdq, sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm, jtcy1_nl,
					jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk,
					jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
					jtcy2_sr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
					jtcy3_gzdw, jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr,
					jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
					jtcy5_zy, jtcy5_sr, jtcy5_jkzk, yhzzqk_gjzxdk,
					yhzzqk_gjzxj, yhzzqk_gjjxj, yhzzqk_gjlzjxj, yhzzqk_xyjxj,
					yhzzqk_qgzx, yhzzqk_xfjm, yhzzqk_lxbz, yhzzqk_ddzfshjxj,
					yhzzqk_hj, jtzszrzhqk, jttfywsj, jtcyycjnmndlqk, jtcysyqk,
					jtqzqk, qtqk, jtjjqkxxsm, mzbm_xxtxdz, mzbm_yzbm,
					mzbm_lxdh, now }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zjjd_knsxx", new String[] {
					"jtrks", "sfgc", "sfdq", "sflszn", "xxtxdz", "yzbm",
					"lxdh", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy1_zy", "jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
					"jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
					"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr",
					"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
					"jtcy4_gzdw", "jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
					"jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk", "yhzzqk_gjzxdk",
					"yhzzqk_gjzxj", "yhzzqk_gjjxj", "yhzzqk_gjlzjxj",
					"yhzzqk_xyjxj", "yhzzqk_qgzx", "yhzzqk_xfjm",
					"yhzzqk_lxbz", "yhzzqk_ddzfshjxj", "yhzzqk_hj",
					"jtzszrzhqk", "jttfywsj", "jtcyycjnmndlqk", "jtcysyqk",
					"jtqzqk", "qtqk", "jtjjqkxxsm", "mzbm_xxtxdz", "mzbm_yzbm",
					"mzbm_lxdh", "sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj",
					"xxsh", "xxshyj" }, new String[] { jtrks, sfgc, sfdq,
					sflszn, xxtxdz, yzbm, lxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm,
					jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr,
					jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
					jtcy3_zy, jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
					jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk,
					jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
					jtcy5_sr, jtcy5_jkzk, yhzzqk_gjzxdk, yhzzqk_gjzxj,
					yhzzqk_gjjxj, yhzzqk_gjlzjxj, yhzzqk_xyjxj, yhzzqk_qgzx,
					yhzzqk_xfjm, yhzzqk_lxbz, yhzzqk_ddzfshjxj, yhzzqk_hj,
					jtzszrzhqk, jttfywsj, jtcyycjnmndlqk, jtcysyqk, jtqzqk,
					qtqk, jtjjqkxxsm, mzbm_xxtxdz, mzbm_yzbm, mzbm_lxdh, now,
					"δ���", "", "δ���", "", "δ���", "" }, "nd||xh", nd + xh,
					request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * �ж��������Ƿ��ظ����ظ���ͨ��ѧУ��˵ķ���2���ظ���ͨ��ѧԺ��˵ķ���3���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isknsdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isKnsDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ? and xxsh in ('һ������','����','��������')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ? and xysh in ('һ������','����','��������')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from zjjd_knsxx where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}

	/**
	 * ��ȡ�������������ϸ��Ϣ
	 * 
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
		stuList.put("xz", Base.chgNull(knsModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(knsModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(knsModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(knsModel.getSfzh(), "", 1));
		stuList.put("csrq", Base.chgNull(knsModel.getCsrq(), "", 1));
		stuList.put("xydm", Base.chgNull(knsModel.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(knsModel.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(knsModel.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(knsModel.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(knsModel.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(knsModel.getBjmc(), "", 1));
		stuList.put("jtrks", Base.chgNull(knsModel.getJtrks(), "", 1));
		stuList.put("sfgc", Base.chgNull(knsModel.getSfgc(), "", 1));
		stuList.put("sfdq", Base.chgNull(knsModel.getSfdq(), "", 1));
		stuList.put("sflszn", Base.chgNull(knsModel.getSflszn(), "", 1));
		stuList.put("xxtxdz", Base.chgNull(knsModel.getXxtxdz(), "", 1));
		stuList.put("yzbm", Base.chgNull(knsModel.getYzbm(), "", 1));
		stuList.put("lxdh", Base.chgNull(knsModel.getLxdh(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(knsModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(knsModel.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(knsModel.getJtcy1_gx(), "", 1));
		stuList
				.put("jtcy1_gzdw", Base
						.chgNull(knsModel.getJtcy1_gzdw(), "", 1));
		stuList.put("jtcy1_zy", Base.chgNull(knsModel.getJtcy1_zy(), "", 1));
		stuList.put("jtcy1_sr", Base.chgNull(knsModel.getJtcy1_sr(), "", 1));
		stuList
				.put("jtcy1_jkzk", Base
						.chgNull(knsModel.getJtcy1_jkzk(), "", 1));
		stuList.put("jtcy2_xm", Base.chgNull(knsModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(knsModel.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(knsModel.getJtcy2_gx(), "", 1));
		stuList
				.put("jtcy2_gzdw", Base
						.chgNull(knsModel.getJtcy2_gzdw(), "", 1));
		stuList.put("jtcy2_zy", Base.chgNull(knsModel.getJtcy2_zy(), "", 1));
		stuList.put("jtcy2_sr", Base.chgNull(knsModel.getJtcy2_sr(), "", 1));
		stuList
				.put("jtcy2_jkzk", Base
						.chgNull(knsModel.getJtcy2_jkzk(), "", 1));
		stuList.put("jtcy3_xm", Base.chgNull(knsModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(knsModel.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(knsModel.getJtcy3_gx(), "", 1));
		stuList
				.put("jtcy3_gzdw", Base
						.chgNull(knsModel.getJtcy3_gzdw(), "", 1));
		stuList.put("jtcy3_zy", Base.chgNull(knsModel.getJtcy3_zy(), "", 1));
		stuList.put("jtcy3_sr", Base.chgNull(knsModel.getJtcy3_sr(), "", 1));
		stuList
				.put("jtcy3_jkzk", Base
						.chgNull(knsModel.getJtcy3_jkzk(), "", 1));
		stuList.put("jtcy4_xm", Base.chgNull(knsModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(knsModel.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(knsModel.getJtcy4_gx(), "", 1));
		stuList
				.put("jtcy4_gzdw", Base
						.chgNull(knsModel.getJtcy4_gzdw(), "", 1));
		stuList.put("jtcy4_zy", Base.chgNull(knsModel.getJtcy4_zy(), "", 1));
		stuList.put("jtcy4_sr", Base.chgNull(knsModel.getJtcy4_sr(), "", 1));
		stuList
				.put("jtcy4_jkzk", Base
						.chgNull(knsModel.getJtcy4_jkzk(), "", 1));
		stuList.put("jtcy5_xm", Base.chgNull(knsModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(knsModel.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(knsModel.getJtcy5_gx(), "", 1));
		stuList
				.put("jtcy5_gzdw", Base
						.chgNull(knsModel.getJtcy5_gzdw(), "", 1));
		stuList.put("jtcy5_zy", Base.chgNull(knsModel.getJtcy5_zy(), "", 1));
		stuList.put("jtcy5_sr", Base.chgNull(knsModel.getJtcy5_sr(), "", 1));
		stuList
				.put("jtcy5_jkzk", Base
						.chgNull(knsModel.getJtcy5_jkzk(), "", 1));
		stuList.put("yhzzqk_gjzxdk", Base.chgNull(knsModel.getYhzzqk_gjzxdk(),
				"", 1));
		stuList.put("yhzzqk_gjzxj", Base.chgNull(knsModel.getYhzzqk_gjzxj(),
				"", 1));
		stuList.put("yhzzqk_gjjxj", Base.chgNull(knsModel.getYhzzqk_gjjxj(),
				"", 1));
		stuList.put("yhzzqk_gjlzjxj", Base.chgNull(knsModel.getYhzzqk_gjlzjxj(),
				"", 1));
		stuList.put("yhzzqk_xyjxj", Base.chgNull(knsModel.getYhzzqk_xyjxj(),
				"", 1));
		stuList.put("yhzzqk_qgzx", Base.chgNull(knsModel.getYhzzqk_qgzx(), "",
				1));
		stuList.put("yhzzqk_xfjm", Base.chgNull(knsModel.getYhzzqk_xfjm(), "",
				1));
		stuList.put("yhzzqk_lxbz", Base.chgNull(knsModel.getYhzzqk_lxbz(), "",
				1));
		stuList.put("yhzzqk_ddzfshjxj", Base.chgNull(knsModel
				.getYhzzqk_ddzfshjxj(), "", 1));
		stuList.put("yhzzqk_hj", Base.chgNull(knsModel.getYhzzqk_hj(), "", 1));
		stuList
				.put("jtzszrzhqk", Base
						.chgNull(knsModel.getJtzszrzhqk(), "", 1));
		stuList.put("jttfywsj", Base.chgNull(knsModel.getJttfywsj(), "", 1));
		stuList.put("jtcyycjnmndlqk", Base.chgNull(
				knsModel.getJtcyycjnmndlqk(), "", 1));
		stuList.put("jtcysyqk", Base.chgNull(knsModel.getJtcysyqk(), "", 1));
		stuList.put("jtqzqk", Base.chgNull(knsModel.getJtqzqk(), "", 1));
		stuList.put("qtqk", Base.chgNull(knsModel.getQtqk(), "", 1));
		stuList
				.put("jtjjqkxxsm", Base
						.chgNull(knsModel.getJtjjqkxxsm(), "", 1));
		stuList.put("mzbm_xxtxdz", Base.chgNull(knsModel.getMzbm_xxtxdz(), "",
				1));
		stuList.put("mzbm_yzbm", Base.chgNull(knsModel.getMzbm_yzbm(), "", 1));
		stuList.put("mzbm_lxdh", Base.chgNull(knsModel.getMzbm_lxdh(), "", 1));
		stuList.put("sqsj", Base.chgNull(knsModel.getSqsj(), "", 1));
		stuList.put("fdysh", Base.chgNull(knsModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(knsModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(knsModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(knsModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(knsModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(knsModel.getXxshyj(), "", 1));

		return stuList;
	}

	/**
	 * ɾ��������Ϣ delKnsxx ---- ɾ����������Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"' and xxsh not in ('һ������','����','��������')";
				} else {
					sqlT[i] = "delete zjjd_knsxx where nd||xh='"+pkT[i]+"' and xysh not in ('һ������','����','��������')";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * �����޸���������˽�� modKnsxx ---- �����޸���������˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update zjjd_knsxx set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update zjjd_knsxx set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
				} else {
					sqlT[i] = "update zjjd_knsxx set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='δ���'";
				}
			}
		}
		dao.runBatch(sqlT);
	}

	/**
	 * ��������ѯ��ͷ knstit ---- ��������ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", "���֤��", "ϵ����", "�༶����", "����ʱ��", "���������", "ϵ���", Base.YXPZXY_KEY+"���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}

	/**
	 * ��������ѯ��� knsres ---- ���������
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('һ������','����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0){
					sql = "select (case when nvl(xysh,'δ���') in ('һ������','����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where fdysh in ('һ������','����','��������')";
				} else {
					sql = "select (case when nvl(fdysh,'δ���') in ('һ������','����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('һ������','����','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,sfzh,xymc,bjmc,sqsj,fdysh,xysh,xxsh from view_zjjd_knsxx where xysh in ('һ������','����','��������')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "sfzh", "xymc", "bjmc", "sqsj", "fdysh", "xysh", "xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}

	/**
	 * ���������������Ϣ���ɹ�����TRUE����֮����FALSE saveKnsShxx ---- ���������������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel saveKnsModel, HttpServletRequest request)
			throws Exception {
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
		String sHave = isKnsDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zjjd_knsxx", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("zjjd_knsxx",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("zjjd_knsxx",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
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
		String sql = "select nd,zxjdjdm,zxjdjmc,zxjje,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,jthk,jthkrs,jtyzsr,jtrjysr,jtsrly,yzbm,jtzz,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,zxjdjdm||'!!OneSpile!!'||zxjdjmc||'!!OneSpile!!'||zxjje dm from view_zjjd_gjzxjsqb where nd||xh = ?";
		String[] colList = new String[] { "nd", "zxjdjdm", "zxjdjmc", "zxjje",
				"xh", "xm", "xb", "nj", "xz", "mzmc", "zzmmmc", "sfzh", "rxny",
				"csrq", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh",
				"jthk", "jthkrs", "jtyzsr", "jtrjysr", "jtsrly", "yzbm",
				"jtzz", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdw", "sqly", "sqsj", "xysh", "xyshyj", "xxsh",
				"xxshyj", "dm" };
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
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjzxjModel.getNd(), "", 1);
		String zxjdjdm = Base.chgNull(gjzxjModel.getZxjdjdm(), "", 1);
		String zxjdjmc = Base.chgNull(gjzxjModel.getZxjdjmc(), "", 1);
		String zxjje = Base.chgNull(gjzxjModel.getZxjje(), "", 1);
		String xh = Base.chgNull(gjzxjModel.getXh(), "", 1);
		String lxdh = Base.chgNull(gjzxjModel.getLxdh(), "", 1);
		String jthk = Base.chgNull(gjzxjModel.getJthk(), "", 1);
		String jthkrs = Base.chgNull(gjzxjModel.getJthkrs(), "", 1);
		String jtyzsr = Base.chgNull(gjzxjModel.getJtyzsr(), "", 1);
		String jtrjysr = Base.chgNull(gjzxjModel.getJtrjysr(), "", 1);
		String jtsrly = Base.chgNull(gjzxjModel.getJtsrly(), "", 1);
		String yzbm = Base.chgNull(gjzxjModel.getYzbm(), "", 1);
		String jtzz = Base.chgNull(gjzxjModel.getJtzz(), "", 1);
		String jtcy1_xm = Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(gjzxjModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "", 1);
		String jtcy2_xm = Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(gjzxjModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "", 1);
		String jtcy3_xm = Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(gjzxjModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "", 1);
		String jtcy4_xm = Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(gjzxjModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "", 1);
		String jtcy5_xm = Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(gjzxjModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(gjzxjModel.getJtcy5_gzdw(), "", 1);
		String sqly = Base.chgNull(gjzxjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("zjjd_gjzxjsqb", new String[] {
					"nd", "zxjdjdm", "zxjdjmc", "zxjje", "xh", "lxdh", "jthk",
					"jthkrs", "jtyzsr", "jtrjysr", "jtsrly", "yzbm", "jtzz",
					"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
					"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
					"jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly" },
					new String[] { nd, zxjdjdm, zxjdjmc, zxjje, xh, lxdh, jthk,
							jthkrs, jtyzsr, jtrjysr, jtsrly, yzbm, jtzz,
							jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy2_xm,
							jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy3_xm, jtcy3_nl,
							jtcy3_gx, jtcy3_gzdw, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy5_xm, jtcy5_nl, jtcy5_gx,
							jtcy5_gzdw, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
					"zxjdjdm", "zxjdjmc", "zxjje", "lxdh", "jthk", "jthkrs",
					"jtyzsr", "jtrjysr", "jtsrly", "yzbm", "jtzz", "jtcy1_xm",
					"jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy2_xm",
					"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy3_xm",
					"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy5_xm",
					"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw", "sqly", "xysh",
					"xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					zxjdjdm, zxjdjmc, zxjje, lxdh, jthk, jthkrs, jtyzsr,
					jtrjysr, jtsrly, yzbm, jtzz, jtcy1_xm, jtcy1_nl, jtcy1_gx,
					jtcy1_gzdw, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy4_xm,
					jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy5_xm, jtcy5_nl,
					jtcy5_gx, jtcy5_gzdw, sqly, "δ���", "", "δ���", "", now },
					"nd||xh", nd + xh, request);
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
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjzxjModel.getNd(), "", 1));
		stuList.put("zxjdjmc", Base.chgNull(gjzxjModel.getZxjdjmc(), "", 1));
		stuList.put("zxjje", Base.chgNull(gjzxjModel.getZxjje(), "", 1));
		stuList.put("xh", Base.chgNull(gjzxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjzxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjzxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjzxjModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjzxjModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjzxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjzxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjzxjModel.getSfzh(), "", 1));
		stuList.put("rxny", Base.chgNull(gjzxjModel.getRxny(), "", 1));
		stuList.put("csrq", Base.chgNull(gjzxjModel.getCsrq(), "", 1));
		stuList.put("xymc", Base.chgNull(gjzxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjzxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjzxjModel.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjzxjModel.getLxdh(), "", 1));
		stuList.put("jthk", Base.chgNull(gjzxjModel.getJthk(), "", 1));
		stuList.put("jthkrs", Base.chgNull(gjzxjModel.getJthkrs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(gjzxjModel.getJtyzsr(), "", 1));
		stuList.put("jtrjysr", Base.chgNull(gjzxjModel.getJtrjysr(), "", 1));
		stuList.put("jtsrly", Base.chgNull(gjzxjModel.getJtsrly(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjzxjModel.getYzbm(), "", 1));
		stuList.put("jtzz", Base.chgNull(gjzxjModel.getJtzz(), "", 1));
		stuList.put("jtcy1_xm", Base.chgNull(gjzxjModel.getJtcy1_xm(), "", 1));
		stuList.put("jtcy1_nl", Base.chgNull(gjzxjModel.getJtcy1_nl(), "", 1));
		stuList.put("jtcy1_gx", Base.chgNull(gjzxjModel.getJtcy1_gx(), "", 1));
		stuList.put("jtcy1_gzdw", Base.chgNull(gjzxjModel.getJtcy1_gzdw(), "",
				1));
		stuList.put("jtcy2_xm", Base.chgNull(gjzxjModel.getJtcy2_xm(), "", 1));
		stuList.put("jtcy2_nl", Base.chgNull(gjzxjModel.getJtcy2_nl(), "", 1));
		stuList.put("jtcy2_gx", Base.chgNull(gjzxjModel.getJtcy2_gx(), "", 1));
		stuList.put("jtcy2_gzdw", Base.chgNull(gjzxjModel.getJtcy2_gzdw(), "",
				1));
		stuList.put("jtcy3_xm", Base.chgNull(gjzxjModel.getJtcy3_xm(), "", 1));
		stuList.put("jtcy3_nl", Base.chgNull(gjzxjModel.getJtcy3_nl(), "", 1));
		stuList.put("jtcy3_gx", Base.chgNull(gjzxjModel.getJtcy3_gx(), "", 1));
		stuList.put("jtcy3_gzdw", Base.chgNull(gjzxjModel.getJtcy3_gzdw(), "",
				1));
		stuList.put("jtcy4_xm", Base.chgNull(gjzxjModel.getJtcy4_xm(), "", 1));
		stuList.put("jtcy4_nl", Base.chgNull(gjzxjModel.getJtcy4_nl(), "", 1));
		stuList.put("jtcy4_gx", Base.chgNull(gjzxjModel.getJtcy4_gx(), "", 1));
		stuList.put("jtcy4_gzdw", Base.chgNull(gjzxjModel.getJtcy4_gzdw(), "",
				1));
		stuList.put("jtcy5_xm", Base.chgNull(gjzxjModel.getJtcy5_xm(), "", 1));
		stuList.put("jtcy5_nl", Base.chgNull(gjzxjModel.getJtcy5_nl(), "", 1));
		stuList.put("jtcy5_gx", Base.chgNull(gjzxjModel.getJtcy5_gx(), "", 1));
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
				sqlT[i] = "delete zjjd_gjzxjsqb where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete zjjd_gjzxjsqb where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
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
				sqlT[i] = "update zjjd_gjzxjsqb set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update zjjd_gjzxjsqb set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
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
	public String isGjzxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from zjjd_gjzxjsqb where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from zjjd_gjzxjsqb where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
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
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "zxjdjmc", "zxjje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", Base.YXPZXY_KEY+"����", "�༶����", "��ѧ������", "��ѧ����", "����ʱ��", "ϵ���", Base.YXPZXY_KEY+"���" };
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
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,zxjdjmc,zxjje,sqsj,xysh,xxsh from view_zjjd_gjzxjsqb where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "zxjdjmc", "zxjje", "sqsj", "xysh",
				"xxsh" };

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
		String nd = Base.chgNull(gjzxjModel.getNd(), "", 1);
		String xysh = Base.chgNull(gjzxjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjzxjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjzxjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjzxjModel.getXxshyj(), "", 1);
		String sHave = isGjzxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("zjjd_gjzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
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
		String sql = "select '' dm,'-------��ѡ��-------' mc from dual union select zxjdjdm||'!!OneSpile!!'||zxjdjmc||'!!OneSpile!!'||zxjje dm,zxjdjmc mc from zjjd_gjzxjdmb order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}

	/**
	 * ��ȡ������־��ѧ����ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,mzmc,zzmmmc,sfzh,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,chhzjl,jthk,jtrkzs,jtyzsr,rjysr,srly,jtzz,yzbm,xxcj,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_gjlzjxj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"mzmc", "zzmmmc", "sfzh", "csrq", "rxny", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "lxdh", "chhzjl", "jthk",
				"jtrkzs", "jtyzsr", "rjysr", "srly", "jtzz", "yzbm", "xxcj",
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
	 * �õ�������־��ѧ������Ȩ��
	 * 
	 * @param sUserType
	 * @param userDep
	 * @param xh
	 * @param nd
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
	 * ���������־��ѧ��������Ϣ���ɹ�����TRUE����֮����FALSE saveGjlzjxjSqxx ---- ���������־��ѧ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjlzjxjModel.getNd(), "", 1);
		String xh = Base.chgNull(gjlzjxjModel.getXh(), "", 1);
		String lxdh = Base.chgNull(gjlzjxjModel.getLxdh(), "", 1);
		String chhzjl = Base.chgNull(gjlzjxjModel.getChhzjl(), "", 1);
		String jthk = Base.chgNull(gjlzjxjModel.getJthk(), "", 1);
		String jtrkzs = Base.chgNull(gjlzjxjModel.getJtrkzs(), "", 1);
		String jtyzsr = Base.chgNull(gjlzjxjModel.getJtyzsr(), "", 1);
		String rjysr = Base.chgNull(gjlzjxjModel.getRjysr(), "", 1);
		String srly = Base.chgNull(gjlzjxjModel.getSrly(), "", 1);
		String jtzz = Base.chgNull(gjlzjxjModel.getJtzz(), "", 1);
		String yzbm = Base.chgNull(gjlzjxjModel.getYzbm(), "", 1);
		String xxcj = Base.chgNull(gjlzjxjModel.getXxcj(), "", 1);
		String sqly = Base.chgNull(gjlzjxjModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjlzjxjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_gjlzjxj", new String[] {
					"nd", "xh", "lxdh", "chhzjl", "jthk", "jtrkzs", "jtyzsr",
					"rjysr", "srly", "jtzz", "yzbm", "xxcj", "sqly" },
					new String[] { nd, xh, lxdh, chhzjl, jthk, jtrkzs, jtyzsr,
							rjysr, srly, jtzz, yzbm, xxcj, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
					"lxdh", "chhzjl", "jthk", "jtrkzs", "jtyzsr", "rjysr",
					"srly", "jtzz", "yzbm", "xxcj", "sqly", "xysh", "xyshyj",
					"xxsh", "xxshyj", "sqsj" }, new String[] { lxdh, chhzjl,
					jthk, jtrkzs, jtyzsr, rjysr, srly, jtzz, yzbm, xxcj, sqly,
					"δ���", "", "δ���", "", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}

	/**
	 * ��ȡ������־��ѧ���������ϸ��Ϣ
	 * 
	 * @param gjlzjxjModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjlzjxjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjlzjxjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjlzjxjModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjlzjxjModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjlzjxjModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjlzjxjModel.getXz(), "", 1));
		stuList.put("mzmc", Base.chgNull(gjlzjxjModel.getMzmc(), "", 1));
		stuList.put("zzmmmc", Base.chgNull(gjlzjxjModel.getZzmmmc(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjlzjxjModel.getSfzh(), "", 1));
		stuList.put("csrq", Base.chgNull(gjlzjxjModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(gjlzjxjModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(gjlzjxjModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjlzjxjModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjlzjxjModel.getBjmc(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjlzjxjModel.getLxdh(), "", 1));
		stuList.put("chhzjl", Base.chgNull(gjlzjxjModel.getChhzjl(), "", 1));
		stuList.put("jthk", Base.chgNull(gjlzjxjModel.getJthk(), "", 1));
		stuList.put("jtrkzs", Base.chgNull(gjlzjxjModel.getJtrkzs(), "", 1));
		stuList.put("jtyzsr", Base.chgNull(gjlzjxjModel.getJtyzsr(), "", 1));
		stuList.put("rjysr", Base.chgNull(gjlzjxjModel.getRjysr(), "", 1));
		stuList.put("srly", Base.chgNull(gjlzjxjModel.getSrly(), "", 1));
		stuList.put("jtzz", Base.chgNull(gjlzjxjModel.getJtzz(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjlzjxjModel.getYzbm(), "", 1));
		stuList.put("xxcj", Base.chgNull(gjlzjxjModel.getXxcj(), "", 1));
		stuList.put("sqly", Base.chgNull(gjlzjxjModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(gjlzjxjModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(gjlzjxjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjlzjxjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjlzjxjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjlzjxjModel.getXxshyj(), "", 1));

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
				sqlT[i] = "delete xszz_zjjd_gjlzjxj where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_gjlzjxj where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸Ĺ�����־��ѧ����˽�� modGjlzjxjxx ---- �����޸Ĺ�����־��ѧ����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_gjlzjxj set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_gjlzjxj set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ������־��ѧ���ѯ��ͷ gjlzjxjtit ---- ������־��ѧ���ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "sqsj", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", Base.YXPZXY_KEY+"����", "�༶����", "����ʱ��", "ϵ���", Base.YXPZXY_KEY+"���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ������־��ѧ���ѯ��� gjlzjxjres ---- ������־��ѧ���
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
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xysh,xxsh from view_xszz_zjjd_gjlzjxj where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "sqsj", "xysh", "xxsh" };

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
	public boolean saveGjlzjxjShxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(gjlzjxjModel.getXh(), "", 1);
		String nd = Base.chgNull(gjlzjxjModel.getNd(), "", 1);
		String xysh = Base.chgNull(gjlzjxjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjlzjxjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjlzjxjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjlzjxjModel.getXxshyj(), "", 1);
		String sHave = isGjlzjxjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_gjlzjxj", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * �жϹ�����־��ѧ���Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isgjlzjxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjlzjxjDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_gjlzjxj where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_gjlzjxj where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * ��ȡ��ʱ���Ѳ�����ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select id,xh,xm,xb,nj,mzmc,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,lsbzdm,lsbzmc,lsbzje,sfkns,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,lsbzdm||'!!OneSpile!!'||lsbzmc||'!!OneSpile!!'||lsbzje dm from view_xszz_zjjd_lsbz where id = ?";
		String[] colList = new String[] { "id", "xh", "xm", "xb", "nj", "mzmc",
				"csrq", "rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"lsbzdm", "lsbzmc", "lsbzje", "sfkns", "sqly", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "dm" };
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
	public String getLsbzSqQx(String sUserType, String userDep, String xh,
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
	 * �����ʱ���Ѳ����б� lsbzList ---- ��ʱ���Ѳ����б�
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getLsbzList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select '' dm,'-------��ѡ��-------' mc from dual union select lsbzdm||'!!OneSpile!!'||lsbzmc||'!!OneSpile!!'||lsbzje dm,lsbzmc mc from ZJJD_LSBZDMB order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
	
	/**
	 * ������ʱ���Ѳ���������Ϣ���ɹ�����TRUE����֮����FALSE saveLsbzSqxx ---- ������ʱ���Ѳ���������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzSqxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(lsbzModel.getXh(), "", 1);
		String id = Base.chgNull(lsbzModel.getId(), "", 1);
		String lsbzdm = Base.chgNull(lsbzModel.getLsbzdm(), "", 1);
		String lsbzmc = Base.chgNull(lsbzModel.getLsbzmc(), "", 1);
		String lsbzje = Base.chgNull(lsbzModel.getLsbzje(), "", 1);
		String sfkns = Base.chgNull(lsbzModel.getSfkns(), "", 1);
		String sqly = Base.chgNull(lsbzModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isLsbzDataCf(id);
		if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
					"xh", "lsbzdm", "lsbzmc", "lsbzje", "sfkns", "sqly",
					"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					xh, lsbzdm, lsbzmc, lsbzje, sfkns, sqly, "δ���", "",
					"δ���", "", now }, "id", id, request);
		} else {
			bFlag = StandardOperation.insert("xszz_zjjd_lsbz",
					new String[] { "xh", "lsbzdm", "lsbzmc", "lsbzje",
							"sfkns", "sqly" }, new String[] { xh, lsbzdm,
							lsbzmc, lsbzje, sfkns, sqly }, request);
		}
		return bFlag;
	}
	
	/**
	 * �ж���ʱ���Ѳ����Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isgjzxjdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isLsbzDataCf(String id) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_lsbz where id = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { id }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_lsbz where id = ?";
			num = dao.getOneRs(sql, new String[] { id }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * ��ȡ��ʱ���Ѳ����������ϸ��Ϣ
	 * 
	 * @param lsbzModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzSqb(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

//		stuList.put("nd", Base.chgNull(lsbzModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(lsbzModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(lsbzModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(lsbzModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(lsbzModel.getNj(), "", 1));
		stuList.put("mzmc", Base.chgNull(lsbzModel.getMzmc(), "", 1));
		stuList.put("csrq", Base.chgNull(lsbzModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(lsbzModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(lsbzModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(lsbzModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(lsbzModel.getBjmc(), "", 1));
		stuList.put("lsbzmc", Base.chgNull(lsbzModel.getLsbzmc(), "", 1));
		stuList.put("lsbzje", Base.chgNull(lsbzModel.getLsbzje(), "", 1));
		stuList.put("sfkns", Base.chgNull(lsbzModel.getSfkns(), "", 1));
		stuList.put("sqly", Base.chgNull(lsbzModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(lsbzModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(lsbzModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(lsbzModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(lsbzModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(lsbzModel.getXxshyj(), "", 1));

		return stuList;
	}
	
	/**
	 * ɾ����ʱ���Ѳ�����Ϣ delLsbzxx ---- ɾ����ʱ���Ѳ�����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delLsbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_lsbz where id='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_lsbz where id='"+pkT[i]+"' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸���ʱ���Ѳ�����˽�� modLsbzxx ---- �����޸���ʱ���Ѳ�����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modLsbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_lsbz set xxsh='"+shjg+"' where id='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_lsbz set xysh='"+shjg+"' where id='"+pkT[i]+"' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��ͷ Lsbztit ---- ��ʱ���Ѳ������ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbzTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "xh", "xm",
				"xb", "xymc", "bjmc", "lsbzmc", "lsbzje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "ѧ��", "����",
				"�Ա�", Base.YXPZXY_KEY+"����", "�༶����", "��ʱ��������", "��ʱ�������", "����ʱ��", "ϵ���", Base.YXPZXY_KEY+"���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��� Lsbzres ---- ��ʱ���Ѳ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbzRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,id pk,xh,xm,xb,xymc,bjmc,lsbzmc,lsbzje,sqsj,xysh,xxsh from view_xszz_zjjd_lsbz where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "xh", "xm",
				"xb", "xymc", "bjmc", "lsbzmc", "lsbzje", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ��ʱ���Ѳ��������Ϣ���ɹ�����TRUE����֮����FALSE saveLsbzShxx ---- ������ʱ���Ѳ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzShxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String id = Base.chgNull(lsbzModel.getId(), "", 1);
		String xysh = Base.chgNull(lsbzModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(lsbzModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(lsbzModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(lsbzModel.getXxshyj(), "", 1);
		String sHave = isLsbzDataCf(id);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"id", id, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_lsbz", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"id", id, request);
			}
		}

		return bFlag;
	}
	
	/**
	 * ��ȡ������ѧ������ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,xz,rxny,byny,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,yxjtzz,lxdh,yzbm,jz1_xm,jz1_lxdh,jz2_xm,jz2_lxdh,jtjjqk,xfdk,yjxf,rxlbjgkm,rxlbktgkm,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_gjzxdk where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"rxny", "byny", "sfzh", "xydm", "xymc", "zydm", "zymc", "bjdm",
				"bjmc", "yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh",
				"jz2_xm", "jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
				"rxlbktgkm", "sqsj", "fdysh", "fdyshyj", "xysh", "xyshyj",
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
	 * ���������ѧ����������Ϣ���ɹ�����TRUE����֮����FALSE saveGjlzjxjSqxx ---- ���������ѧ����������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(gjzxdkModel.getNd(), "", 1);
		String xh = Base.chgNull(gjzxdkModel.getXh(), "", 1);
		String yxjtzz = Base.chgNull(gjzxdkModel.getYxjtzz(), "", 1);
		String lxdh = Base.chgNull(gjzxdkModel.getLxdh(), "", 1);
		String yzbm = Base.chgNull(gjzxdkModel.getYzbm(), "", 1);
		String jz1_xm = Base.chgNull(gjzxdkModel.getJz1_xm(), "", 1);
		String jz1_lxdh = Base.chgNull(gjzxdkModel.getJz1_lxdh(), "", 1);
		String jz2_xm = Base.chgNull(gjzxdkModel.getJz2_xm(), "", 1);
		String jz2_lxdh = Base.chgNull(gjzxdkModel.getJz2_lxdh(), "", 1);
		String jtjjqk = Base.chgNull(gjzxdkModel.getJtjjqk(), "", 1);
		String xfdk = Base.chgNull(gjzxdkModel.getXfdk(), "", 1);
		String yjxf = Base.chgNull(gjzxdkModel.getYjxf(), "", 1);
		String rxlbjgkm = Base.chgNull(gjzxdkModel.getRxlbjgkm(), "", 1);
		String rxlbktgkm = Base.chgNull(gjzxdkModel.getRxlbktgkm(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isGjzxdkDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_gjzxdk", new String[] {
					"nd", "xh", "yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh",
					"jz2_xm", "jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
					"rxlbktgkm" }, new String[] { nd, xh, yxjtzz, lxdh, yzbm,
					jz1_xm, jz1_lxdh, jz2_xm, jz2_lxdh, jtjjqk, xfdk, yjxf,
					rxlbjgkm, rxlbktgkm }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_gjzxdk", new String[] {
					"yxjtzz", "lxdh", "yzbm", "jz1_xm", "jz1_lxdh", "jz2_xm",
					"jz2_lxdh", "jtjjqk", "xfdk", "yjxf", "rxlbjgkm",
					"rxlbktgkm", "fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh",
					"xxshyj", "sqsj" },
					new String[] { yxjtzz, lxdh, yzbm, jz1_xm, jz1_lxdh,
							jz2_xm, jz2_lxdh, jtjjqk, xfdk, yjxf, rxlbjgkm,
							rxlbktgkm, "δ���", "", "δ���", "", "δ���", "", now },
					"nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * �жϹ�����ѧ�����Ƿ��ظ����ظ���ͨ��ѧУ��˵ķ���2���ظ���ͨ��ѧԺ��˵ķ���3���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isgjzxdkdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isGjzxdkDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ? and xysh in ('ͨ��')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_zjjd_gjzxdk where xh = ? and nd = ?";
				num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
				if (!num.equalsIgnoreCase("0")) {
					sFlag = "1";
				}
			}
		}
		return sFlag;
	}
	
	/**
	 * ��ȡ������ѧ�����������ϸ��Ϣ
	 * 
	 * @param gjzxdkModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(gjzxdkModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(gjzxdkModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(gjzxdkModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(gjzxdkModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(gjzxdkModel.getNj(), "", 1));
		stuList.put("xz", Base.chgNull(gjzxdkModel.getXz(), "", 1));
		stuList.put("rxny", Base.chgNull(gjzxdkModel.getRxny(), "", 1));
		stuList.put("byny", Base.chgNull(gjzxdkModel.getByny(), "", 1));
		stuList.put("sfzh", Base.chgNull(gjzxdkModel.getSfzh(), "", 1));
		stuList.put("xymc", Base.chgNull(gjzxdkModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(gjzxdkModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(gjzxdkModel.getBjmc(), "", 1));
		stuList.put("yxjtzz", Base.chgNull(gjzxdkModel.getYxjtzz(), "", 1));
		stuList.put("lxdh", Base.chgNull(gjzxdkModel.getLxdh(), "", 1));
		stuList.put("yzbm", Base.chgNull(gjzxdkModel.getYzbm(), "", 1));
		stuList.put("jz1_xm", Base.chgNull(gjzxdkModel.getJz1_xm(), "", 1));
		stuList.put("jz1_lxdh", Base.chgNull(gjzxdkModel.getJz1_lxdh(), "", 1));
		stuList.put("jz2_xm", Base.chgNull(gjzxdkModel.getJz2_xm(), "", 1));
		stuList.put("jz2_lxdh", Base.chgNull(gjzxdkModel.getJz2_lxdh(), "", 1));
		stuList.put("jtjjqk", Base.chgNull(gjzxdkModel.getJtjjqk(), "", 1));
		stuList.put("xfdk", Base.chgNull(gjzxdkModel.getXfdk(), "", 1));
		stuList.put("yjxf", Base.chgNull(gjzxdkModel.getYjxf(), "", 1));
		stuList.put("rxlbjgkm", Base.chgNull(gjzxdkModel.getRxlbjgkm(), "", 1));
		stuList.put("rxlbktgkm", Base.chgNull(gjzxdkModel.getRxlbktgkm(), "", 1));
		stuList.put("fdysh", Base.chgNull(gjzxdkModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(gjzxdkModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(gjzxdkModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(gjzxdkModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(gjzxdkModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(gjzxdkModel.getXxshyj(), "", 1));
		String sqsj = Base.chgNull(gjzxdkModel.getSqsj(), "", 1);
		
		if (sqsj != null && sqsj.length() == 10){
			String year = sqsj.substring(0, 4);
			String mon = sqsj.substring(5, 7);
			String day = sqsj.substring(8);
			
			sqsj = year + "��" + mon + "��" + day + "��";
		}
		stuList.put("sqsj", sqsj);

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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete xszz_zjjd_gjzxdk where nd||xh='"+pkT[i]+"' and xysh<>'ͨ��'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸Ĺ�����ѧ������˽�� modGjzxdkxx ---- �����޸Ĺ�����ѧ������˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_gjzxdk set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_zjjd_gjzxdk set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
				} else {
					sqlT[i] = "update xszz_zjjd_gjzxdk set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='δ���'";
				}
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ������ѧ�����ѯ��ͷ gjzxdktit ---- ������ѧ�����ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfdk", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", "ϵ����", "�༶����", "������", "����ʱ��", "���������", "ϵ���", Base.YXPZXY_KEY+"���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ������ѧ�����ѯ��� gjzxdkres ---- ������ѧ������
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0) {
					sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where fdysh in ('ͨ��')";
				} else {
					sql = "select (case when nvl(fdysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfdk,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,sqsj,xfdk,fdysh,xysh,xxsh from view_xszz_zjjd_gjzxdk where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfdk", "sqsj", "fdysh", "xysh", "xxsh" };

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
	public boolean saveGjzxdkShxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xh = Base.chgNull(gjzxdkModel.getXh(), "", 1);
		String nd = Base.chgNull(gjzxdkModel.getNd(), "", 1);
		String fdysh = Base.chgNull(gjzxdkModel.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(gjzxdkModel.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(gjzxdkModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(gjzxdkModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(gjzxdkModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(gjzxdkModel.getXxshyj(), "", 1);
		String sHave = isGjzxdkDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_gjzxdk", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_gjzxdk",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_gjzxdk",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * ��ȡѧ�Ѽ�����ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nd,xh,xm,xb,nj,mzmc,csrq,rxny,xydm,xymc,zydm,zymc,bjdm,bjmc,xfjmdm,xfjmmc,xfjmje,sfkns,sqly,sqsj,xysh,xyshyj,xxsh,xxshyj,xfjmdm||'!!OneSpile!!'||xfjmmc||'!!OneSpile!!'||xfjmje dm from view_xszz_zjjd_xfjm where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "mzmc",
				"csrq", "rxny", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
				"xfjmdm", "xfjmmc", "xfjmje", "sfkns", "sqly", "sqsj", "xysh",
				"xyshyj", "xxsh", "xxshyj", "dm" };
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
	 * �õ�ѧ�Ѽ�������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getXfjmSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		String sfksq = "-1";
		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));
		if (StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student")) {
			String[] jxjksjssj = null;

			String sql = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc='ѧ�Ѽ���' and b.xh=? ";
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
	 * ���ѧ�Ѽ����б� xfjmList ---- ѧ�Ѽ����б�
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXfjmList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select '' dm,'-------��ѡ��-------' mc from dual union select xfjmdm||'!!OneSpile!!'||xfjmmc||'!!OneSpile!!'||xfjmje dm,xfjmmc mc from ZJJD_XFJMDMB order by dm desc";
		list = dao.getArrayList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
	
	/**
	 * ����ѧ�Ѽ���������Ϣ���ɹ�����TRUE����֮����FALSE saveXfjmSqxx ---- ����ѧ�Ѽ���������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmSqxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nd = Base.chgNull(xfjmModel.getNd(), "", 1);
		String xh = Base.chgNull(xfjmModel.getXh(), "", 1);
		String xfjmdm = Base.chgNull(xfjmModel.getXfjmdm(), "", 1);
		String xfjmmc = Base.chgNull(xfjmModel.getXfjmmc(), "", 1);
		String xfjmje = Base.chgNull(xfjmModel.getXfjmje(), "", 1);
		String sfkns = Base.chgNull(xfjmModel.getSfkns(), "", 1);
		String sqly = Base.chgNull(xfjmModel.getSqly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isXfjmDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_xfjm",
					new String[] { "nd", "xh", "xfjmdm", "xfjmmc", "xfjmje",
							"sfkns", "sqly" }, new String[] { nd, xh, xfjmdm,
							xfjmmc, xfjmje, sfkns, sqly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
					"nd", "xh", "xfjmdm", "xfjmmc", "xfjmje", "sfkns", "sqly",
					"xysh", "xyshyj", "xxsh", "xxshyj", "sqsj" }, new String[] {
					nd, xh, xfjmdm, xfjmmc, xfjmje, sfkns, sqly, "δ���", "",
					"δ���", "", now }, "nd||xh", nd + xh, request);
		} else {
			request.setAttribute("isPASS", "is");
		}
		return bFlag;
	}
	
	/**
	 * �ж�ѧ�Ѽ����Ƿ��ظ����ظ���ͨ����˵ķ���2���ظ���ûͨ����˵ķ���1��û���ظ��ķ���-1 isXfjmdatacf ---- �����Ƿ��ظ�
	 * 
	 * @param xh
	 * @param nd
	 * @return
	 * @throws Exception
	 */
	public String isXfjmDataCf(String xh, String nd) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from xszz_zjjd_xfjm where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_xfjm where xh = ? and nd = ?";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	/**
	 * ��ȡѧ�Ѽ����������ϸ��Ϣ
	 * 
	 * @param xfjmModel,request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmSqb(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(xfjmModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(xfjmModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(xfjmModel.getXm(), "", 1));
		stuList.put("xb", Base.chgNull(xfjmModel.getXb(), "", 1));
		stuList.put("nj", Base.chgNull(xfjmModel.getNj(), "", 1));
		stuList.put("mzmc", Base.chgNull(xfjmModel.getMzmc(), "", 1));
		stuList.put("csrq", Base.chgNull(xfjmModel.getCsrq(), "", 1));
		stuList.put("rxny", Base.chgNull(xfjmModel.getRxny(), "", 1));
		stuList.put("xymc", Base.chgNull(xfjmModel.getXymc(), "", 1));
		stuList.put("zymc", Base.chgNull(xfjmModel.getZymc(), "", 1));
		stuList.put("bjmc", Base.chgNull(xfjmModel.getBjmc(), "", 1));
		stuList.put("xfjmmc", Base.chgNull(xfjmModel.getXfjmmc(), "", 1));
		stuList.put("xfjmje", Base.chgNull(xfjmModel.getXfjmje(), "", 1));
		stuList.put("sfkns", Base.chgNull(xfjmModel.getSfkns(), "", 1));
		stuList.put("sqly", Base.chgNull(xfjmModel.getSqly(), "", 1));
		stuList.put("sqsj", Base.chgNull(xfjmModel.getSqsj(), "", 1));
		stuList.put("xysh", Base.chgNull(xfjmModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(xfjmModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(xfjmModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(xfjmModel.getXxshyj(), "", 1));

		return stuList;
	}
	
	/**
	 * ɾ��ѧ�Ѽ�����Ϣ delLsbzxx ---- ɾ��ѧ�Ѽ�����Ϣ
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delXfjmxx(String cbVal, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_xfjm where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "delete xszz_zjjd_xfjm where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �����޸�ѧ�Ѽ�����˽�� modXfjmxx ---- �����޸�ѧ�Ѽ�����˽��
	 * 
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void modXfjmxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_xfjm set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				sqlT[i] = "update xszz_zjjd_xfjm set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
			}
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ѧ�Ѽ����ѯ��ͷ Xfjmtit ---- ѧ�Ѽ�����ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjmTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfjmmc", "xfjmje", "sqsj", "xysh",
				"xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"�Ա�", Base.YXPZXY_KEY+"����", "�༶����", "ѧ�Ѽ�������", "ѧ�Ѽ�����", "����ʱ��", "ϵ���", Base.YXPZXY_KEY+"���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ѧ�Ѽ����ѯ��� Xfjmres ----ѧ�Ѽ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjmRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where 1=1";
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,xb,xymc,bjmc,xfjmmc,xfjmje,sqsj,xysh,xxsh from view_xszz_zjjd_xfjm where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"xb", "xymc", "bjmc", "xfjmmc", "xfjmje", "sqsj", "xysh",
				"xxsh" };

		StringBuffer whereSql = getWhereSql(queryModel, request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ѧ�Ѽ��������Ϣ���ɹ�����TRUE����֮����FALSE saveXfjmShxx ---- ����ѧ�Ѽ��������Ϣ
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmShxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String xh = Base.chgNull(xfjmModel.getXh(), "", 1);
		String nd = Base.chgNull(xfjmModel.getNd(), "", 1);
		String xysh = Base.chgNull(xfjmModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(xfjmModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(xfjmModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(xfjmModel.getXxshyj(), "", 1);
		String sHave = isXfjmDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if ("2".equalsIgnoreCase(sHave)) {
				request.setAttribute("xxshjg", "pass");
			} else {
				bFlag = StandardOperation.update("xszz_zjjd_xfjm", new String[] {
						"xysh", "xyshyj" }, new String[] { xysh, xyshyj },
						"nd||xh", nd + xh, request);
			}
		}

		return bFlag;
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
		String sql = "select nd,xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,hjje,hjly,sqsj,fdysh,fdyshyj,xysh,xyshyj,xxsh,xxshyj from view_xszz_zjjd_xfhj where nd||xh = ?";
		String[] colList = new String[] { "nd", "xh", "xm", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "hjje", "hjly", "sqsj",
				"fdysh", "fdyshyj", "xysh", "xyshyj", "xxsh", "xxshyj" };
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
		String hjly = Base.chgNull(xfhjModel.getHjly(), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		String sHave = isXfhjDataCf(xh, nd);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("xszz_zjjd_xfhj", new String[] {
					"nd", "xh", "hjje", "hjly" }, new String[] { nd, xh, hjje,
					hjly }, request);
		} else if ("1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.update("xszz_zjjd_xfhj", new String[] {
					"hjje", "hjly", "sqsj", "fdysh", "fdyshyj", "xysh",
					"xyshyj", "xxsh", "xxshyj" }, new String[] { hjje, hjly,
					now, "δ���", "", "δ���", "", "δ���", "" }, "nd||xh", nd + xh,
					request);
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
		String sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ? and xxsh in ('ͨ��')";
		String num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "2";
		} else {
			sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ? and xysh in ('ͨ��')";
			num = dao.getOneRs(sql, new String[] { xh, nd }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "3";
			} else {
				sql = "select count(*) num from xszz_zjjd_xfhj where xh = ? and nd = ?";
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
	public HashMap<String, String> getXfhjSqb(XfhjModel xfhjModel,
			HttpServletRequest request) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();

		stuList.put("nd", Base.chgNull(xfhjModel.getNd(), "", 1));
		stuList.put("xh", Base.chgNull(xfhjModel.getXh(), "", 1));
		stuList.put("xm", Base.chgNull(xfhjModel.getXm(), "", 1));
		stuList.put("nj", Base.chgNull(xfhjModel.getNj(), "", 1));
		stuList.put("xydm", Base.chgNull(xfhjModel.getXydm(), "", 1));
		stuList.put("xymc", Base.chgNull(xfhjModel.getXymc(), "", 1));
		stuList.put("zydm", Base.chgNull(xfhjModel.getZydm(), "", 1));
		stuList.put("zymc", Base.chgNull(xfhjModel.getZymc(), "", 1));
		stuList.put("bjdm", Base.chgNull(xfhjModel.getBjdm(), "", 1));
		stuList.put("bjmc", Base.chgNull(xfhjModel.getBjmc(), "", 1));
		stuList.put("hjje", Base.chgNull(xfhjModel.getHjje(), "", 1));
		stuList.put("hjly", Base.chgNull(xfhjModel.getHjly(), "", 1));
		stuList.put("fdysh", Base.chgNull(xfhjModel.getFdysh(), "", 1));
		stuList.put("fdyshyj", Base.chgNull(xfhjModel.getFdyshyj(), "", 1));
		stuList.put("xysh", Base.chgNull(xfhjModel.getXysh(), "", 1));
		stuList.put("xyshyj", Base.chgNull(xfhjModel.getXyshyj(), "", 1));
		stuList.put("xxsh", Base.chgNull(xfhjModel.getXxsh(), "", 1));
		stuList.put("xxshyj", Base.chgNull(xfhjModel.getXxshyj(), "", 1));
		String sqsj = Base.chgNull(xfhjModel.getSqsj(), "", 1);

		if (sqsj != null && sqsj.length() == 10){
			String year = sqsj.substring(0, 4);
			String mon = sqsj.substring(5, 7);
			String day = sqsj.substring(8);
			
			sqsj = year + "��" + mon + "��" + day + "��";
		}
		stuList.put("sqsj", sqsj);
		
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (userType.equalsIgnoreCase("admin")
					|| userType.equalsIgnoreCase("xx")) {
				sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete xszz_zjjd_xfhj where nd||xh='"+pkT[i]+"' and xysh<>'ͨ��'";
				}
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 1; i < pkT.length; i++) {
			if (("admin".equalsIgnoreCase(userType))
					|| "xx".equalsIgnoreCase(userType)) {
				sqlT[i] = "update xszz_zjjd_xfhj set xxsh='"+shjg+"' where nd||xh='"+pkT[i]+"'";
			} else {
				if (userBj.size() == 0) {
					sqlT[i] = "update xszz_zjjd_xfhj set xysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xxsh='δ���'";
				} else {
					sqlT[i] = "update xszz_zjjd_xfhj set fdysh='"+shjg+"' where nd||xh='"+pkT[i]+"' and xysh='δ���'";
				}
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
				"zymc", "bjmc", "hjje", "sqsj", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "bgcolor", "����", "���", "ѧ��", "����",
				"רҵ����", "�༶����", "���ɽ��", "����ʱ��", "���������", "ϵ���", Base.YXPZXY_KEY+"���" };
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String sql = "";

		if (queryModel.getIsQuery().equalsIgnoreCase("is")) {
			sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where 1=1";
		} else {
			if (userType.equalsIgnoreCase("xy")) {
				if (userBj.size() == 0){
					sql = "select (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where fdysh in ('ͨ��')";
				} else {
					sql = "select (case when nvl(fdysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where 1=1";
				}
			} else {
				sql = "select (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor,nd||xh pk,nd,xh,xm,zymc,bjmc,hjje,sqsj,fdysh,xysh,xxsh from view_xszz_zjjd_xfhj where xysh in ('ͨ��')";
			}
		}
		String[] colList = new String[] { "bgcolor", "pk", "nd", "xh", "xm",
				"zymc", "bjmc", "hjje", "sqsj", "fdysh", "xysh", "xxsh" };

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
	public boolean saveXfhjShxx(XfhjModel saveXfhjModel, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xh = Base.chgNull(saveXfhjModel.getXh(), "", 1);
		String nd = Base.chgNull(saveXfhjModel.getNd(), "", 1);
		String fdysh = Base.chgNull(saveXfhjModel.getFdysh(), "", 1);
		String fdyshyj = Base.chgNull(saveXfhjModel.getFdyshyj(), "", 1);
		String xysh = Base.chgNull(saveXfhjModel.getXysh(), "", 1);
		String xyshyj = Base.chgNull(saveXfhjModel.getXyshyj(), "", 1);
		String xxsh = Base.chgNull(saveXfhjModel.getXxsh(), "", 1);
		String xxshyj = Base.chgNull(saveXfhjModel.getXxshyj(), "", 1);
		String sHave = isXfhjDataCf(xh, nd);

		if (userType.equalsIgnoreCase("admin")
				|| userType.equalsIgnoreCase("xx")) {
			bFlag = StandardOperation.update("xszz_zjjd_xfhj", new String[] {
					"xxsh", "xxshyj" }, new String[] { xxsh, xxshyj },
					"nd||xh", nd + xh, request);
		} else {
			if (userBj.size() == 0) {
				if ("2".equalsIgnoreCase(sHave)) {
					request.setAttribute("xxshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_xfhj",
							new String[] { "xysh", "xyshyj" }, new String[] {
									xysh, xyshyj }, "nd||xh", nd + xh, request);
				}
			} else {
				if ("3".equalsIgnoreCase(sHave)) {
					request.setAttribute("xyshjg", "pass");
				} else {
					bFlag = StandardOperation.update("xszz_zjjd_xfhj",
							new String[] { "fdysh", "fdyshyj" }, new String[] {
									fdysh, fdyshyj }, "nd||xh", nd + xh, request);
				}
			}
		}

		return bFlag;
	}
	
	/**
	 * ɾ���ſ���Ϣ
	 * delFkxx ---- ɾ���ſ���Ϣ
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delFkxx(String cbVal, HttpServletRequest request) throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete ZJJD_ZXDK_FKXX where xh||hth='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * �ſ���Ϣ��ѯ��ͷ
	 * fkxxtit ---- �ſ���Ϣ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFkxxTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xh", "hth", "xm", "nj",
				"xymc", "zymc", "bjmc", "htje", "fkze" };
		String[] cnList = new String[] { "����", "ѧ��", "��ͬ��", "����",
				"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "��ͬ���", "�ſ��ܶ�" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * �ſ���Ϣ��ѯ���
	 * getFkxxRes ---- �ſ���Ϣ��� 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFkxxRes(QueryModel queryModel,HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||hth pk,xh,hth,xm,nj,xymc,zymc,bjmc,htje,fkze from view_zjjd_zxdk_fkxx where 1=1 ";
		String[] colList = new String[] { "pk", "xh", "hth", "xm", "nj",
				"xymc", "zymc", "bjmc", "htje", "fkze" };
		StringBuffer whereSql = getWhereSql(queryModel,request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ��ȡ�ſ���Ϣ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFkxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select xh,xm,xb,nj,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,hth,htje,htqx,dkyh,dkll,fkje1,fkrq1,fknf1,fkje2,fkrq2,fknf2,fkje3,fkrq3,fknf3,fkje4,fkrq4,fknf4,fkze,bz from view_zjjd_zxdk_fkxx where xh||hth = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "sfzh",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "hth", "htje",
				"htqx", "dkyh", "dkll", "fkje1", "fkrq1", "fknf1", "fkje2",
				"fkrq2", "fknf2", "fkje3", "fkrq3", "fknf3", "fkje4", "fkrq4",
				"fknf4", "fkze", "bz" };
		String[] sLen = dao.getOneRs(sql, new String[]{pkVal}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString() : "");
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * ����ſ���Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveFkxx ---- ����ſ���Ϣ
	 * @param fkxxModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFkxx(FkxxModel fkxxModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(fkxxModel.getXh(), "", 1);
		String hth = Base.chgNull(fkxxModel.getHth(), "", 1);
		String htje = Base.chgNull(fkxxModel.getHtje(), "", 1);
		String htqx = Base.chgNull(fkxxModel.getHtqx(), "", 1);
		String dkyh = Base.chgNull(fkxxModel.getDkyh(), "", 1);
		String dkll = Base.chgNull(fkxxModel.getDkll(), "", 1);
		String fkje1 = Base.chgNull(fkxxModel.getFkje1(), "", 1);
		String fkrq1 = Base.chgNull(fkxxModel.getFkrq1(), "", 1);
		String fknf1 = Base.chgNull(fkxxModel.getFknf1(), "", 1);
		String fkje2 = Base.chgNull(fkxxModel.getFkje2(), "", 1);
		String fkrq2 = Base.chgNull(fkxxModel.getFkrq2(), "", 1);
		String fknf2 = Base.chgNull(fkxxModel.getFknf2(), "", 1);
		String fkje3 = Base.chgNull(fkxxModel.getFkje3(), "", 1);
		String fkrq3 = Base.chgNull(fkxxModel.getFkrq3(), "", 1);
		String fknf3 = Base.chgNull(fkxxModel.getFknf3(), "", 1);
		String fkje4 = Base.chgNull(fkxxModel.getFkje4(), "", 1);
		String fkrq4 = Base.chgNull(fkxxModel.getFkrq4(), "", 1);
		String fknf4 = Base.chgNull(fkxxModel.getFknf4(), "", 1);
		String fkze = Base.chgNull(fkxxModel.getFkze(), "", 1);
		String bz = Base.chgNull(fkxxModel.getBz(), "", 1);
		boolean bHave = isFkxxcf(xh, hth);
		if (bHave) {
			bFlag = StandardOperation.update("zjjd_zxdk_fkxx", new String[] {
					"htje", "htqx", "dkyh", "dkll", "fkje1", "fkrq1", "fknf1",
					"fkje2", "fkrq2", "fknf2", "fkje3", "fkrq3", "fknf3",
					"fkje4", "fkrq4", "fknf4", "fkze", "bz" },
					new String[] { htje, htqx, dkyh, dkll, fkje1, fkrq1, fknf1,
							fkje2, fkrq2, fknf2, fkje3, fkrq3, fknf3, fkje4,
							fkrq4, fknf4, fkze, bz }, "xh||hth", xh + hth,
					request);
		} else {
			bFlag = StandardOperation.insert("zjjd_zxdk_fkxx",
					new String[] { "xh", "hth", "htje", "htqx", "dkyh", "dkll",
							"fkje1", "fkrq1", "fknf1", "fkje2", "fkrq2",
							"fknf2", "fkje3", "fkrq3", "fknf3", "fkje4",
							"fkrq4", "fknf4", "fkze", "bz" }, new String[] {
							xh, hth, htje, htqx, dkyh, dkll, fkje1, fkrq1,
							fknf1, fkje2, fkrq2, fknf2, fkje3, fkrq3, fknf3,
							fkje4, fkrq4, fknf4, fkze, bz }, request);
		}
		return bFlag;
	}
	
	/**
	 * �жϷſ���Ϣ�����Ƿ��ظ����ظ�����TRUE����֮����FALSE
	 * isFkxxcf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isFkxxcf(String xh, String hth) throws Exception {
		boolean bFlag = false;
		String sql = "select count(*) num from zjjd_zxdk_fkxx where xh = ? and hth = ?";
		String num = dao.getOneRs(sql, new String[]{xh, hth}, "num");
		if (!num.equalsIgnoreCase("0")) {
			bFlag = true;
		}//end if
		return bFlag;
	}
}
