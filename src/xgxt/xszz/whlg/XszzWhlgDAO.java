
package xgxt.xszz.whlg;

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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �人����ѧѧ������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-09</p>
 */
public class XszzWhlgDAO{
	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//��ѯ����ֵ
	
	/**
	 * ���÷�������ȡ��ѯ����
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(QueryModel queryZxxsdkModel,HttpServletRequest request) throws Exception {
		StringBuffer whereSql = new StringBuffer(" ");
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String xn = queryZxxsdkModel.getXn();
		String nd = queryZxxsdkModel.getNd();
		String xh = DealString.toGBK(queryZxxsdkModel.getXh());
		String xydm = queryZxxsdkModel.getXydm();
		String zydm = queryZxxsdkModel.getZydm();
		String bjdm = queryZxxsdkModel.getBjdm();
		String nj = queryZxxsdkModel.getNj();
		String xm = queryZxxsdkModel.getXm();
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.trim().equals(""))) {
			xydm = userDep;
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}//end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}//end if
		return whereSql;
	}
	
	/**
	 * ��������
	 * getExpDate ---- �������
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getExpDate(QueryModel queryZxxsdkModel,String tabName,HttpServletRequest request) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		
		String sql = "select * from " + tabName + " where 1=1 ";
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel,request);
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
		String[] colList = dao.getColumnName("select * from " + tabName + " where 1=2");// �����������
		return dao.getColumnNameCN(colList,  tabName);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xz,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,a.sfzh,a.csrq,a.csrq csny,a.mzmc,a.zzmmmc,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxny,(select (case b.rxny when null then '' else (case length(b.rxny) when 7 then (case b.xz when null then '' else (substr(b.rxny,'0','4')+b.xz)||substr(b.rxny,'5') end) else '' end) end) byny from bks_xsjbxx b where b.xh=a.xh) byny from view_stu_details a where a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "xz",
				"zymc", "bjmc", "xydm", "zydm", "bjdm", "sfzh", "csrq", "csny", "mzmc",
				"zzmmmc", "rxny", "byny" };
		String[] sLen = dao.getOneRs(sql, new String[]{pkVal}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString() : "");
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * ɾ����ͥ��������
	 * delJtqkxx ---- ɾ����ͥ����������Ϣ
	 * @param cbVal,request
	 * @return
	 * @throws Exception
	 */
	public void delJtqkxx(String cbVal, HttpServletRequest request) throws Exception {
		String[] pkT = cbVal.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_whlg_jtqkdcb where xn||xh='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
	}
	
	/**
	 * ��ͥ���������Ϣ��ѯ��ͷ
	 * jtqkdctit ---- ��ͥ���������Ϣ��ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xn", "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc" };
		String[] cnList = new String[] { "����", "ѧ��", "ѧ��", "����", "�Ա�", "���֤��",
				"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ��ͥ��������ѯ���
	 * getJtqkdcRes ---- ��ͥ��������� 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryZxxsdkModel,HttpServletRequest request) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xh pk,xn,xh,xm,xb,sfzh,nj,xymc,zymc,bjmc from view_xszz_whlg_jtqkdcb where 1=1 ";
		String[] colList = new String[] { "pk", "xn", "xh", "xm", "xb",
				"sfzh", "nj", "xymc", "zymc", "bjmc" };
		StringBuffer whereSql = getWhereSql(queryZxxsdkModel,request);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ��ȡ��ͥ���������ϸ
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select xh,xn,xm,xb,nj,xz,csrq,sfzh,mzmc,zzmmmc,xydm,xymc,zydm,zymc,bjdm,bjmc,rxqhk,jtrks,byxx,grtc,sfgc,sfdq,sflszn,jtxxtxdz,jtyzbm,jtlxdh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_sr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_sr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_sr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_sr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_sr,jtcy5_jkzk,jtcy6_xm,jtcy6_nl,jtcy6_gx,jtcy6_gzdw,jtcy6_zy,jtcy6_sr,jtcy6_jkzk,jtrjnsr,jtmytgshf,xsbxnyhzzqk,jtzszrzh,jttfywsj,jtcyycjnmrndnlr,jtcysyqk,qtqk,mzbm_xxtxdz,mzbm_lxdh,mzbm_yzbm from view_xszz_whlg_jtqkdcb where xn||xh = ?";
		String[] colList = new String[] { "xh", "xn", "xm", "xb", "nj", "xz",
				"csrq", "sfzh", "mzmc", "zzmmmc", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "rxqhk", "jtrks", "byxx", "grtc",
				"sfgc", "sfdq", "sflszn", "jtxxtxdz", "jtyzbm", "jtlxdh",
				"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
				"jtcy1_sr", "jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
				"jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_sr",
				"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw",
				"jtcy4_zy", "jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr", "jtcy5_jkzk",
				"jtcy6_xm", "jtcy6_nl", "jtcy6_gx", "jtcy6_gzdw", "jtcy6_zy",
				"jtcy6_sr", "jtcy6_jkzk", "jtrjnsr", "jtmytgshf",
				"xsbxnyhzzqk", "jtzszrzh", "jttfywsj", "jtcyycjnmrndnlr",
				"jtcysyqk", "qtqk", "mzbm_xxtxdz", "mzbm_lxdh", "mzbm_yzbm" };
		String[] sLen = dao.getOneRs(sql, new String[]{pkVal}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString() : "");
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * �����ͥ���������Ϣ���ɹ�����TRUE����֮����FALSE
	 * saveJtqkdcxx ---- �����ͥ���������Ϣ
	 * @param jtqkdcModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcxx(JtqkdcModel jtqkdcModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = Base.chgNull(jtqkdcModel.getXh(), "", 1);
		String xn = Base.chgNull(jtqkdcModel.getXn(), "", 1);
		String rxqhk = Base.chgNull(jtqkdcModel.getRxqhk(), "", 1);
		String jtrks = Base.chgNull(jtqkdcModel.getJtrks(), "", 1);
		String byxx = Base.chgNull(jtqkdcModel.getByxx(), "", 1);
		String grtc = Base.chgNull(jtqkdcModel.getGrtc(), "", 1);
		String sfgc = Base.chgNull(jtqkdcModel.getSfgc(), "", 1);
		String sfdq = Base.chgNull(jtqkdcModel.getSfdq(), "", 1);
		String sflszn = Base.chgNull(jtqkdcModel.getSflszn(), "", 1);
		String jtxxtxdz = Base.chgNull(jtqkdcModel.getJtxxtxdz(), "", 1);
		String jtyzbm = Base.chgNull(jtqkdcModel.getJtyzbm(), "", 1);
		String jtlxdh = Base.chgNull(jtqkdcModel.getJtlxdh(), "", 1);
		String jtcy1_xm = Base.chgNull(jtqkdcModel.getJtcy1_xm(), "", 1);
		String jtcy1_nl = Base.chgNull(jtqkdcModel.getJtcy1_nl(), "", 1);
		String jtcy1_gx = Base.chgNull(jtqkdcModel.getJtcy1_gx(), "", 1);
		String jtcy1_gzdw = Base.chgNull(jtqkdcModel.getJtcy1_gzdw(), "", 1);
		String jtcy1_zy = Base.chgNull(jtqkdcModel.getJtcy1_zy(), "", 1);
		String jtcy1_sr = Base.chgNull(jtqkdcModel.getJtcy1_sr(), "", 1);
		String jtcy1_jkzk = Base.chgNull(jtqkdcModel.getJtcy1_jkzk(), "", 1);
		String jtcy2_xm = Base.chgNull(jtqkdcModel.getJtcy2_xm(), "", 1);
		String jtcy2_nl = Base.chgNull(jtqkdcModel.getJtcy2_nl(), "", 1);
		String jtcy2_gx = Base.chgNull(jtqkdcModel.getJtcy2_gx(), "", 1);
		String jtcy2_gzdw = Base.chgNull(jtqkdcModel.getJtcy2_gzdw(), "", 1);
		String jtcy2_zy = Base.chgNull(jtqkdcModel.getJtcy2_zy(), "", 1);
		String jtcy2_sr = Base.chgNull(jtqkdcModel.getJtcy2_sr(), "", 1);
		String jtcy2_jkzk = Base.chgNull(jtqkdcModel.getJtcy2_jkzk(), "", 1);
		String jtcy3_xm = Base.chgNull(jtqkdcModel.getJtcy3_xm(), "", 1);
		String jtcy3_nl = Base.chgNull(jtqkdcModel.getJtcy3_nl(), "", 1);
		String jtcy3_gx = Base.chgNull(jtqkdcModel.getJtcy3_gx(), "", 1);
		String jtcy3_gzdw = Base.chgNull(jtqkdcModel.getJtcy3_gzdw(), "", 1);
		String jtcy3_zy = Base.chgNull(jtqkdcModel.getJtcy3_zy(), "", 1);
		String jtcy3_sr = Base.chgNull(jtqkdcModel.getJtcy3_sr(), "", 1);
		String jtcy3_jkzk = Base.chgNull(jtqkdcModel.getJtcy3_jkzk(), "", 1);
		String jtcy4_xm = Base.chgNull(jtqkdcModel.getJtcy4_xm(), "", 1);
		String jtcy4_nl = Base.chgNull(jtqkdcModel.getJtcy4_nl(), "", 1);
		String jtcy4_gx = Base.chgNull(jtqkdcModel.getJtcy4_gx(), "", 1);
		String jtcy4_gzdw = Base.chgNull(jtqkdcModel.getJtcy4_gzdw(), "", 1);
		String jtcy4_zy = Base.chgNull(jtqkdcModel.getJtcy4_zy(), "", 1);
		String jtcy4_sr = Base.chgNull(jtqkdcModel.getJtcy4_sr(), "", 1);
		String jtcy4_jkzk = Base.chgNull(jtqkdcModel.getJtcy4_jkzk(), "", 1);
		String jtcy5_xm = Base.chgNull(jtqkdcModel.getJtcy5_xm(), "", 1);
		String jtcy5_nl = Base.chgNull(jtqkdcModel.getJtcy5_nl(), "", 1);
		String jtcy5_gx = Base.chgNull(jtqkdcModel.getJtcy5_gx(), "", 1);
		String jtcy5_gzdw = Base.chgNull(jtqkdcModel.getJtcy5_gzdw(), "", 1);
		String jtcy5_zy = Base.chgNull(jtqkdcModel.getJtcy5_zy(), "", 1);
		String jtcy5_sr = Base.chgNull(jtqkdcModel.getJtcy5_sr(), "", 1);
		String jtcy5_jkzk = Base.chgNull(jtqkdcModel.getJtcy5_jkzk(), "", 1);
		String jtcy6_xm = Base.chgNull(jtqkdcModel.getJtcy6_xm(), "", 1);
		String jtcy6_nl = Base.chgNull(jtqkdcModel.getJtcy6_nl(), "", 1);
		String jtcy6_gx = Base.chgNull(jtqkdcModel.getJtcy6_gx(), "", 1);
		String jtcy6_gzdw = Base.chgNull(jtqkdcModel.getJtcy6_gzdw(), "", 1);
		String jtcy6_zy = Base.chgNull(jtqkdcModel.getJtcy6_zy(), "", 1);
		String jtcy6_sr = Base.chgNull(jtqkdcModel.getJtcy6_sr(), "", 1);
		String jtcy6_jkzk = Base.chgNull(jtqkdcModel.getJtcy6_jkzk(), "", 1);
		String jtrjnsr = Base.chgNull(jtqkdcModel.getJtrjnsr(), "", 1);
		String jtmytgshf = Base.chgNull(jtqkdcModel.getJtmytgshf(), "", 1);
		String xsbxnyhzzqk = Base.chgNull(jtqkdcModel.getXsbxnyhzzqk(), "", 1);
		String jtzszrzh = Base.chgNull(jtqkdcModel.getJtzszrzh(), "", 1);
		String jttfywsj = Base.chgNull(jtqkdcModel.getJttfywsj(), "", 1);
		String jtcyycjnmrndnlr = Base.chgNull(jtqkdcModel.getJtcyycjnmrndnlr(),
				"", 1);
		String jtcysyqk = Base.chgNull(jtqkdcModel.getJtcysyqk(), "", 1);
		String qtqk = Base.chgNull(jtqkdcModel.getQtqk(), "", 1);
		String mzbm_xxtxdz = Base.chgNull(jtqkdcModel.getMzbm_xxtxdz(), "", 1);
		String mzbm_lxdh = Base.chgNull(jtqkdcModel.getMzbm_lxdh(), "", 1);
		String mzbm_yzbm = Base.chgNull(jtqkdcModel.getMzbm_yzbm(), "", 1);
		boolean bHave = isJtqkdatacf(xh, xn);
		if (bHave) {
			bFlag = StandardOperation.update("xszz_whlg_jtqkdcb", new String[] {
					"rxqhk", "jtrks", "byxx", "grtc", "sfgc", "sfdq", "sflszn",
					"jtxxtxdz", "jtyzbm", "jtlxdh", "jtcy1_xm", "jtcy1_nl",
					"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr",
					"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
					"jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk",
					"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
					"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm",
					"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
					"jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
					"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr",
					"jtcy5_jkzk", "jtcy6_xm", "jtcy6_nl", "jtcy6_gx",
					"jtcy6_gzdw", "jtcy6_zy", "jtcy6_sr", "jtcy6_jkzk",
					"jtrjnsr", "jtmytgshf", "xsbxnyhzzqk", "jtzszrzh",
					"jttfywsj", "jtcyycjnmrndnlr", "jtcysyqk", "qtqk",
					"mzbm_xxtxdz", "mzbm_lxdh", "mzbm_yzbm" }, new String[] {
					rxqhk, jtrks, byxx, grtc, sfgc, sfdq, sflszn, jtxxtxdz,
					jtyzbm, jtlxdh, jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw,
					jtcy1_zy, jtcy1_sr, jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
					jtcy2_gx, jtcy2_gzdw, jtcy2_zy, jtcy2_sr, jtcy2_jkzk,
					jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
					jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
					jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk, jtcy5_xm,
					jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy, jtcy5_sr,
					jtcy5_jkzk, jtcy6_xm, jtcy6_nl, jtcy6_gx, jtcy6_gzdw,
					jtcy6_zy, jtcy6_sr, jtcy6_jkzk, jtrjnsr, jtmytgshf,
					xsbxnyhzzqk, jtzszrzh, jttfywsj, jtcyycjnmrndnlr, jtcysyqk,
					qtqk, mzbm_xxtxdz, mzbm_lxdh, mzbm_yzbm }, "xn||xh", xn + xh,
					request);
		} else {
			bFlag = StandardOperation.insert("xszz_whlg_jtqkdcb",
					new String[] { "xh", "xn", "rxqhk", "jtrks", "byxx",
							"grtc", "sfgc", "sfdq", "sflszn", "jtxxtxdz",
							"jtyzbm", "jtlxdh", "jtcy1_xm", "jtcy1_nl",
							"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_sr",
							"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
							"jtcy2_gzdw", "jtcy2_zy", "jtcy2_sr", "jtcy2_jkzk",
							"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
							"jtcy3_zy", "jtcy3_sr", "jtcy3_jkzk", "jtcy4_xm",
							"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
							"jtcy4_sr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
							"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_sr",
							"jtcy5_jkzk", "jtcy6_xm", "jtcy6_nl", "jtcy6_gx",
							"jtcy6_gzdw", "jtcy6_zy", "jtcy6_sr", "jtcy6_jkzk",
							"jtrjnsr", "jtmytgshf", "xsbxnyhzzqk", "jtzszrzh",
							"jttfywsj", "jtcyycjnmrndnlr", "jtcysyqk", "qtqk",
							"mzbm_xxtxdz", "mzbm_lxdh", "mzbm_yzbm" },
					new String[] { xh, xn, rxqhk, jtrks, byxx, grtc, sfgc,
							sfdq, sflszn, jtxxtxdz, jtyzbm, jtlxdh, jtcy1_xm,
							jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_sr,
							jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx,
							jtcy2_gzdw, jtcy2_zy, jtcy2_sr, jtcy2_jkzk,
							jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdw, jtcy3_zy,
							jtcy3_sr, jtcy3_jkzk, jtcy4_xm, jtcy4_nl, jtcy4_gx,
							jtcy4_gzdw, jtcy4_zy, jtcy4_sr, jtcy4_jkzk,
							jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
							jtcy5_sr, jtcy5_jkzk, jtcy6_xm, jtcy6_nl, jtcy6_gx,
							jtcy6_gzdw, jtcy6_zy, jtcy6_sr, jtcy6_jkzk,
							jtrjnsr, jtmytgshf, xsbxnyhzzqk, jtzszrzh,
							jttfywsj, jtcyycjnmrndnlr, jtcysyqk, qtqk,
							mzbm_xxtxdz, mzbm_lxdh, mzbm_yzbm }, request);
		}
		return bFlag;
	}
	
	/**
	 * �жϼ�ͥ������������Ƿ��ظ����ظ�����TRUE����֮����FALSE
	 * isJtqkdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isJtqkdatacf(String xh, String xn) throws Exception {
		boolean bFlag = false;
		String sql = "select count(*) num from xszz_whlg_jtqkdcb where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[]{xh, xn}, "num");
		if (!num.equalsIgnoreCase("0")) {
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/***#########################################################################*/
	public String[] getStuInfo(String xh,String[] columns){
		String sql = "select xh,xm,xb,csrq,mzmc,zzmmmc,xymc,bjmc from view_stu_details where xh=?";
//		String[] rs = dao.getOneRs(sql, new String[]{xh}, columns);
		return dao.getOneRs(sql, new String[]{xh}, columns);
	}
}
