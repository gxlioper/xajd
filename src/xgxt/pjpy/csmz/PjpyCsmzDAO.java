
package xgxt.pjpy.csmz;

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
 * <p>Description: ��ɳ����ѧԺ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzDAO extends DAO{
	DAO dao = DAO.getInstance();
	List<String> values = new ArrayList<String>();//��ѯ����ֵ
	
	/**
	 * ��ȡѧ�������Ϣ���༶��רҵ��ѧԺ���Ա��꼶,������ѧ�ţ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		HashMap<String, String> stuList = new HashMap<String,String>();
		String sql = "select xh,xm,xb,xymc,bjmc,zymc,nj from view_xsjbxx where xh = ?";
		String[] colList = new String[]{"xh","xm","xb","xymc","bjmc","zymc","nj"};
		String[] sLen = dao.getOneRs(sql, new String[]{xh}, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i].toString());
			}//end for
		}//end if
		return stuList;
	}
	
	/**
	 * ��ȡ��ѧ�������Ϣ(��ѧ����룬���ƣ������)
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjInfo(HashMap<String, String> jxjMap) throws Exception {
		String sql = "select jxjdm,jxjmc,jxjlb,jlje from jxjdmb where jxjmc='��άѧ��'";
		String[] colList = new String[]{"jxjdm","jxjmc","jxjlb","jlje"};
		String[] jxjList = dao.getOneRs(sql, new String[]{}, colList);
		if (jxjList != null) {
			for (int i = 0; i < colList.length; i++) {
				jxjMap.put(colList[i].toString(), jxjList[i].toString());
			}//end for
		}//end if
		return jxjMap;
	}
	
	/**
	 * �ж������Ƿ��ظ����ظ�����TRUE����֮����FALSE
	 * isdatacf ---- �����Ƿ��ظ�
	 * @param xh
	 * @param jxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isDataCf(String xh, String jxjdm, String xn) throws Exception {
		boolean bFlag = false;
		String sql = "select xh,jxjdm,xn,sqsj from xsjxjb where xh = ? and jxjdm = ? and xn = ?";
		HashMap<String, String> resMap = dao.getMapNotOut(sql, new String[]{xh, jxjdm, xn});
		if (resMap != null && resMap.size() > 0) {
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * ������άѧ�𣬳ɹ�����TRUE������������ѧ����Ϣ��ѧ��������Ϣ����֮����FALSE
	 * issaveshjxj ---- ������άѧ�� 
	 * @param shjxjModel (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean isSaveShJxj(SaveShJxjModel shjxjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = shjxjModel.getXh();
		String jxjdm = shjxjModel.getJxjdm();
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		String drzw = DealString.toGBK(shjxjModel.getDrzw());
		String xxjl = DealString.toGBK(shjxjModel.getXxjl());
		String jxj1 = DealString.toGBK(shjxjModel.getJxj1());
		String jxj2 = DealString.toGBK(shjxjModel.getJxj2());
		String jxj3 = DealString.toGBK(shjxjModel.getJxj3());
		String jxj4 = DealString.toGBK(shjxjModel.getJxj4());
		String shyg1 = DealString.toGBK(shjxjModel.getShyg1());
		String shyg2 = DealString.toGBK(shjxjModel.getShyg2());
		String shyg3 = DealString.toGBK(shjxjModel.getShyg3());
		String shyg4 = DealString.toGBK(shjxjModel.getShyg4());
		String ddj1 = DealString.toGBK(shjxjModel.getDdj1());
		String ddj2 = DealString.toGBK(shjxjModel.getDdj2());
		String ddj3 = DealString.toGBK(shjxjModel.getDdj3());
		String ddj4 = DealString.toGBK(shjxjModel.getDdj4());
		String bxkpjcj1 = DealString.toGBK(shjxjModel.getBxkpjcj1());
		String bxkpjcj2 = DealString.toGBK(shjxjModel.getBxkpjcj2());
		String bxkpjcj3 = DealString.toGBK(shjxjModel.getBxkpjcj3());
		String bxkpjcj4 = DealString.toGBK(shjxjModel.getBxkpjcj4());
		String bjcjpx1 = DealString.toGBK(shjxjModel.getBjcjpx1());
		String bjcjpx2 = DealString.toGBK(shjxjModel.getBjcjpx2());
		String bjcjpx3 = DealString.toGBK(shjxjModel.getBjcjpx3());
		String bjcjpx4 = DealString.toGBK(shjxjModel.getBjcjpx4());
		String zycjpx1 = DealString.toGBK(shjxjModel.getZycjpx1());
		String zycjpx2 = DealString.toGBK(shjxjModel.getZycjpx2());
		String zycjpx3 = DealString.toGBK(shjxjModel.getZycjpx3());
		String zycjpx4 = DealString.toGBK(shjxjModel.getZycjpx4());
		String tyhgbz1 = DealString.toGBK(shjxjModel.getTyhgbz1());
		String tyhgbz2 = DealString.toGBK(shjxjModel.getTyhgbz2());
		String tyhgbz3 = DealString.toGBK(shjxjModel.getTyhgbz3());
		String tyhgbz4 = DealString.toGBK(shjxjModel.getTyhgbz4());
		String wysp = DealString.toGBK(shjxjModel.getWysp());
		String sjhm = DealString.toGBK(shjxjModel.getSjhm());
		boolean bDel = StandardOperation.delete("xsjxjb", "xh||jxjdm||xn", xh + jxjdm + xn, request);
		if (bDel) {
			String sql = "insert into xsjxjb (xh, jxjdm, xn, nd, sqsj, ffbj, ffsj, ffwjh, xq, drzw, xxjl)"
					+ " values (?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?)";
			bFlag = dao.runUpdate(sql, new String[] { xh, jxjdm, xn, nd, "0", "",
					"", xq, drzw, xxjl });
			if (bFlag) {// ����ɹ����������ѧ��������Ϣ
				boolean bTemp = StandardOperation.delete("xsjxjxxb",
						new String[] { "xh" }, new String[] { xh }, request);// ���������ظ���ɾ����
				if (bTemp) {
					StandardOperation.insert("xsjxjxxb", new String[] { "xh",
							"jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1",
							"zycjpx1", "tyhgbz1", "jxj2", "shyg2", "ddj2",
							"bxkpjcj2", "bjcjpx2", "zycjpx2", "tyhgbz2",
							"jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3",
							"zycjpx3", "tyhgbz3", "jxj4", "shyg4", "ddj4",
							"bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" },
							new String[] { xh, jxj1, shyg1, ddj1, bxkpjcj1,
									bjcjpx1, zycjpx1, tyhgbz1, jxj2, shyg2,
									ddj2, bxkpjcj2, bjcjpx2, zycjpx2, tyhgbz2,
									jxj3, shyg3, ddj3, bxkpjcj3, bjcjpx3,
									zycjpx3, tyhgbz3, jxj4, shyg4, ddj4,
									bxkpjcj4, bjcjpx4, zycjpx4, tyhgbz4 },
							request);
					sql = "select count(*) num from xsfzxxb where xh = ?";
					String sNum = dao.getOneRs(sql, new String[] { xh },
							new String[] { "num" })[0];// ���������Ƿ����
					sql = "select count(*) num from xsfzxxb where xh=?";
					sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
					if (StringUtils.isEqual(sNum, "0")) {
						sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";
					}// end if
					dao.runUpdate(sql, new String[] { sjhm, wysp, xh });//����ѧ���ֻ�������ˮƽ��Ϣ
				}//end if
			}//end if
		}//end if
		return bFlag;
	}

	/**
	 * ��άѧ���ѯ��ͷ
	 * shjxjtit ---- ��άѧ���ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] {"xh||jxjdm||xn", "nd", "xn", "xh", "xm", "bjmc", "jxjdm",
				"jxjmc", "fdysh", "xysh", "xxsh" };
		String[] cnList = new String[] { "����", "���", "ѧ��", "ѧ��", "����", "�༶����", "��ѧ�����",
				"��ѧ������", "����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ��άѧ���ѯ���
	 * shjxjres ---- ��άѧ���� 
	 * @param shjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryShJxjModel shjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select jxjdm from jxjdmb where jxjmc = ? and rownum = 1";//��άѧ�����
		String sJxjdm = dao.getOneRs(sql, new String[]{"��άѧ��"}, new String[]{"jxjdm"})[0];
		sql = "select xh||jxjdm||xn,nd,xn,xh,xm,bjmc,jxjdm,jxjmc,fdysh,xysh,xxsh from view_xsjxjb where jxjdm = '" + sJxjdm + "' ";
		String[] colList = new String[]{"xh||jxjdm||xn", "nd", "xn", "xh", "xm", "bjmc", "jxjdm", "jxjmc", "fdysh", "xysh", "xxsh"};
		StringBuffer whereSql = getWhereSql(shjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
		return resList;
	}
	
	/**
	 * ͨ��������ȡ��άѧ����Ϣ
	 * shjxjbyPkval ---- ͨ��������ȡ��άѧ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,fdysh,xysh,xxsh,fdyyj,xyshyj,xxshyj from view_xsjxjb where xh||jxjdm||xn = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue.trim()});
		return resMap;
	}
	
	/**
	 * ���÷�������ȡ��ѯ����
	 * @param shjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(QueryShJxjModel shjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = shjxjModel.getXn();
		String xh = DealString.toGBK(shjxjModel.getXh());
		String xydm = shjxjModel.getXydm();
		String zydm = shjxjModel.getZydm();
		String bjdm = shjxjModel.getBjdm();
		String nj = shjxjModel.getNj();
		String xq = shjxjModel.getXq();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
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
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}

	/**
	 * ���÷���������ɾ��
	 * @param keys ����
	 * @return
	 * @throws Exception
	 */
	public String delInfoByPk(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i].trim());// �õ�����
			sql = "delete from xsjxjb where xh||jxjdm||xn = '" + whichxh
					+ "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * ��άѧ����˲�ѯ��ͷ
	 * shjxjtit ---- ��άѧ���ͷ 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit2(String sUserType, String sIsFdy) throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[] {"xh||jxjdm||xn", "bgcolor", "nd", "xn", "xh", "xm", "bjmc",
				"jxjmc", "sh" };
		String[] cnList = null;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			cnList = new String[] { "����","bgcolor", "���", "ѧ��", "ѧ��", "����", "�༶����",
					"��ѧ������", "����Ա���"};
		}//end if ����Ա�û�
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			cnList = new String[] { "����", "bgcolor","���", "ѧ��", "ѧ��", "����", "�༶����",
					"��ѧ������", Base.YXPZXY_KEY+"���"};
		}//end if ѧԺ�û�
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			cnList = new String[] { "����", "bgcolor","���", "ѧ��", "ѧ��", "����", "�༶����",
					"��ѧ������", "ѧУ���"};
		}// end if ѧУ�û�,����Ա
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ��άѧ����˲�ѯ���
	 * shjxjres ---- ��άѧ����˽��
	 * @param shjxjModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes2(QueryShJxjModel shjxjModel,
			String sUserType, String sIsFdy) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select jxjdm from jxjdmb where jxjmc = ? and rownum = 1";// ��άѧ�����
		String sJxjdm = dao.getOneRs(sql, new String[] { "��άѧ��" },
				new String[] { "jxjdm" })[0];
		String[] opList = new String[] { "xh||jxjdm||xn","bgcolor", "nd", "xn", "xh",
				"xm", "bjmc", "jxjmc", "sh" };
		if (StringUtils.isEqual(sIsFdy, "true")) {
			sql = "select xh||jxjdm||xn,(case when(fdysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,fdysh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' ";
		}// end if ����Ա
		if (StringUtils.isEqual(sUserType, "xy")
				&& !(StringUtils.isEqual(sIsFdy, "true"))) {
			sql = "select xh||jxjdm||xn,(case when(xysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,xysh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' and fdysh='ͨ��'";
		}// end if ѧԺ
		if (StringUtils.isEqual(sUserType, "xx")
				|| StringUtils.isEqual(sUserType, "admin")) {
			sql = "select xh||jxjdm||xn,(case when(xxsh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,xn,nd,xh,xm,bjmc,jxjmc,xxsh sh from view_xsjxjb where jxjdm='"
					+ sJxjdm + "' and fdysh='ͨ��' and xysh='ͨ��'";
		}// end if ѧУ
		StringBuffer whereSql = getWhereSql(shjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}
	
	/**
	 * ����Ա���
	 * shjxjbyfdysh ---- ����Ա�����άѧ��
	 * @param shjxjModel
	 * @param keys ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByFdySh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String fdysh = shjxjModel.getShjg();//����Ա���
		String fdyyj = DealString.toGBK(shjxjModel.getShyj());//����Ա���
		fdysh = "tg".equalsIgnoreCase(fdysh) ? "ͨ��" : ("btg".equalsIgnoreCase(fdysh) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "fdysh", "fdyyj" }, new String[] { fdysh,
							fdyyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}
	
	/**
	 * ѧԺ���
	 * shjxjbyXysh ---- ѧԺ�����άѧ��
	 * @param shjxjModel
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByXySh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String xysh = shjxjModel.getShjg();//ѧԺ���
		String xyyj = DealString.toGBK(shjxjModel.getShyj());//ѧԺ���
		xysh = "tg".equalsIgnoreCase(xysh) ? "ͨ��" : ("btg".equalsIgnoreCase(xysh) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "xysh", "xyshyj" }, new String[] { xysh,
							xyyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}

	/**
	 * ѧУ���
	 * shjxjbyXxsh ---- ѧУ�����άѧ��
	 * @param shjxjModel
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] shjxjByXxSh(ShShJxjModel shjxjModel, String[] keys,
			HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String xxsh = shjxjModel.getShjg();//ѧУ���
		String xxyj = DealString.toGBK(shjxjModel.getShyj());//ѧУ���
		xxsh = "tg".equalsIgnoreCase(xxsh) ? "ͨ��" : ("btg".equalsIgnoreCase(xxsh) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++ ) {
			boolean bTemp = StandardOperation.update("xsjxjb",
					new String[] { "xxsh", "xxshyj" }, new String[] { xxsh,
							xxyj }, "xh||jxjdm||xn",
					DealString.toGBK(keys[i].trim()), request);
			result[i] = bTemp;
		}
		return result;
	}
	
	/**
	 * ͨ��������ȡ��άѧ����Ϣ
	 * shjxjbyPkval ---- ͨ��������ȡ��άѧ��
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal2(String sql, String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ����Ա���
	 * shjxjbyfdysh ---- ����Ա�����άѧ��
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByFdySh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//��˽��
		String sYj = DealString.toGBK(shjxjModel.getShyj());//������
		bResult = StandardOperation.update("xsjxjb", new String[] { "fdysh",
				"fdyyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 * ѧԺ���
	 * shjxjbyXysh ---- ѧԺ�����άѧ��
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByXySh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//��˽��
		String sYj = DealString.toGBK(shjxjModel.getShyj());//������
		bResult = StandardOperation.update("xsjxjb", new String[] { "xysh",
				"xyshyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 * ѧУ���
	 * shjxjbyXxsh ---- ѧУ�����άѧ��
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjByXxSh1(ShShJxjModel shjxjModel, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bResult = false;
		String sJg = DealString.toGBK(shjxjModel.getYesNo());//��˽��
		String sYj = DealString.toGBK(shjxjModel.getShyj());//������
		bResult = StandardOperation.update("xsjxjb", new String[] { "xxsh",
				"xxshyj" }, new String[] { sJg, sYj }, "xh||jxjdm||xn", pkValue,
				request);
		return bResult;
	}
	
	/**
	 *����б�
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) {
		return dao.getChkList(iType);
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLb() throws Exception {
		List<HashMap<String, String>> jxjlbList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"Ժ��", "ϵ��"};
		String[] cnList = new String[]{"Ժ��", "ϵ��"};
		for ( int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			jxjlbList.add(tmpMap);
		}
		return jxjlbList;
	}
	
	/**
	 * ����ǰ��������Ƿ��ظ�
	 * @param pk
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pk, String pkValue, String tableName) throws Exception {
		boolean bExists = false;
		String sql = "select count(*) num from " + tableName + " where " + pk + " = " + pkValue;
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"num"});
		if (tmp != null && tmp.length > 0 && tmp[0] != "0") {
			bExists = true;
		}
		return bExists;
	}
	
	/**
	 * ��ѧ����뱣��
	 * @param jxjdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjdm(JxjdmModel jxjdmModel, HttpServletRequest request) throws Exception{
		String jxjlb = DealString.toGBK(jxjdmModel.getJxjlb());
		return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
				"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz", "xydm" ,"sztzxfbz","bmmc", "fbr"},
				new String[] { jxjdmModel.getXydm() + DealString.toGBK(jxjdmModel.getJxjdm()),
						DealString.toGBK(jxjdmModel.getJxjmc()),
						jxjlb, jxjdmModel.getJxjjb(),
						jxjdmModel.getJlje(), jxjdmModel.getSzjdbz(),
						jxjdmModel.getXydm() , jxjdmModel.getSztzxfbz(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm='"+jxjdmModel.getXydm()+"'", new String[]{}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm")}, request);
	}
	
	/**
	 * �����ƺŴ��뱣��
	 * @param rychdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveRychdm(RychdmModel rychdmModel, HttpServletRequest request) throws Exception {
		String rychlb = DealString.toGBK(rychdmModel.getRychlb());
		return StandardOperation.insert("rychdmb", new String[] { "rychdm",
				"rychmc", "rychlb", "xydm" ,"bmmc","fbr"}, new String[] {
				DealString.toGBK(rychdmModel.getXydm() + rychdmModel.getRychdm()), DealString.toGBK(rychdmModel.getRychmc()), rychlb,
				rychdmModel.getXydm(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm='"+rychdmModel.getXydm()+"'", new String[]{}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * ��ѵ�����
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjxdm(JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception{
		return StandardOperation.insert("jxjxdmb", new String[] { "jxdm",
				"jxmc" }, new String[] { jxjxdmModel.getJxdm(),
				DealString.toGBK(jxjxdmModel.getJxmc()) }, request);
	}
	
	/**
	 * ��ѧ�����ɾ��
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delJxjdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("jxjdmb", "jxjdm", pkValue, request);
	}
	
	/**
	 * �����ƺŴ���ɾ��
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delRychdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("rychdmb", "rychdm", pkValue, request);
	}
	
	/**
	 * ��ѵ����ɾ��
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delJxjxdm(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("jxjxdmb", "jxdm", pkValue, request);
	}
	
	/**
	 * ȫ��ɾ�� 
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delDm(String tableName, HttpServletRequest request) throws Exception{
		String sql = "delete from " + tableName;
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * ��ȡ��ѧ�������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjdmInfo(String pkValue) throws Exception {
		String sql = "select jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,xydm,sztzxfbz from jxjdmb where jxjdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * ��ȡ�����ƺŴ�����Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychdmInfo(String pkValue) throws Exception {
		String sql = "select rychdm,rychmc,rychlb,xydm from rychdmb where rychdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * ��ȡ��ѵ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjxdmInfo(String pkValue) throws Exception {
		String sql = "select jxdm,jxmc from jxjxdmb where jxdm=? and rownum=1";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * ��ȡѧ��������
	 * @return
	 * @throws Exception
	 */
	public String getXgbdm() throws Exception {
		String sql = "select xgbdm from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"xgbdm"});
		if (tmp != null && tmp.length > 0) {
			return tmp[0];
		} else {
			return "";
		}
	}
	
	/**
	 * ��ѧ����뱣��
	 * @param jxjdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjdm1(JxjdmModel jxjdmModel, HttpServletRequest request) throws Exception{
		String jxjlb = DealString.toGBK(jxjdmModel.getJxjlb());
		return StandardOperation.insert("jxjdmb", new String[] { "jxjdm",
				"jxjmc", "jxjlb", "jxjjb", "jlje", "szjdbz", "xydm" ,"sztzxfbz", "bmmc", "fbr"},
				new String[] {DealString.toGBK(jxjdmModel.getJxjdm()),
						DealString.toGBK(jxjdmModel.getJxjmc()),
						jxjlb, jxjdmModel.getJxjjb(),
						jxjdmModel.getJlje(), jxjdmModel.getSzjdbz(),
						jxjdmModel.getXydm(), jxjdmModel.getSztzxfbz(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm = ?", new String[]{jxjdmModel.getXydm()}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * �����ƺŴ��뱣��
	 * @param rychdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveRychdm1(RychdmModel rychdmModel, HttpServletRequest request) throws Exception {
		String rychlb = DealString.toGBK(rychdmModel.getRychlb());
		return StandardOperation.insert("rychdmb", new String[] { "rychdm",
				"rychmc", "rychlb", "xydm" ,"bmmc", "fbr"}, new String[] {
				DealString.toGBK( rychdmModel.getRychdm()), DealString.toGBK(rychdmModel.getRychmc()), rychlb,
				rychdmModel.getXydm(),dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm = ?", new String[]{rychdmModel.getXydm()}, "bmmc"),dao.getOneRs("select xm from yhb where yhm=?", new String[]{request.getSession().getAttribute("userName").toString()}, "xm") }, request);
	}
	
	/**
	 * ��ѵ�����
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjxdm1(JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception{
		return StandardOperation.insert("jxjxdmb", new String[] { "jxdm",
				"jxmc" }, new String[] { jxjxdmModel.getJxdm(),
				DealString.toGBK(jxjxdmModel.getJxmc()) }, request);
	}
	
	/**
	 * ����û��Ƿ���Ȩ�޸Ĵ���Ϣ
	 * @param xydm
	 * @param dm
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkUserWritable(String xydm, String dm, String pkValue, String tableName) throws Exception {
		String sql = "select xydm from "+ tableName +" where " + dm + " = " + pkValue;
		String[] tmp = dao.getOneRs(sql, new String[]{}, new String[]{"xydm"});
		if (tmp != null && tmp.length > 0 && tmp[0].equalsIgnoreCase(xydm)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ����Ա��ȡ��Ӧ�༶�б�
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetBjList(String fdyName) throws Exception {
		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
		String sql = "select bjdm from fdybjb where zgh=?";
		String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
		if (bjdmList != null && bjdmList.length > 0) {
			String[] bjmcList = new String[bjdmList.length];
			for (int i = 0; i < bjdmList.length; i++) {
				bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm = ?", new String[]{bjdmList[i]}, "bjmc");
			}
			bjList = dao.arrayToList(bjdmList, bjmcList);
		}
		return bjList;
	}
	
	/**
	 * ����Ա��ȡ��Ӧרҵ�б�
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetZyList(String fdyName) throws Exception {
		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
		String sql = "select bjdm from fdybjb where zgh=?";
		String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
		if (bjdmList != null && bjdmList.length > 0) {
			StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
			for (int i=0;i<bjdmList.length;i++) {
				strSql.append("'");
				strSql.append(bjdmList[i]);
				strSql.append("',");
			}
			sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
			String[] zydmList = dao.getRs(sql, new String[]{}, "zydm");
			String[] zymcList = null;
			if (zydmList != null && zydmList.length>0) {
				zymcList = new String[zydmList.length];
				for (int i=0;i<zydmList.length;i++) {
					zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
				}
			}
			zyList = dao.arrayToList(zydmList, zymcList);
		}
		return zyList;
	}
	
	/**
	 * ��ѧ�𱨱��ӡ
	 * @param xh
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjexpData(String xh, String jxjdm) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnnd = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd"}); 
		String[] opList = new String[]{"xh", "xn", "xm", "nd", "nj", "xb", "xymc", "zymc", "bjmc", "jxjmc", "jxjlb", "jlje", "drzw", "dnshjxj", "xxjl", "kycg", "sqly"};
		resMap = dao.getMap("select a.xh,a.xn,a.xm,a.nd,a.nj,a.xb,a.xymc,a.zymc,a.bjmc,a.jxjmc,a.drzw,a.dnshjxj,a.xxjl,a.kycg,a.sqly,b.jxjlb,b.jlje from view_xsjxjb a left join jxjdmb b on a.jxjdm=b.jxjdm where a.xh=? and a.xn=? and a.nd=? and a.jxjdm=?", new String[]{xh,jxjsqxnnd != null ? jxjsqxnnd[0] : "", jxjsqxnnd != null ? jxjsqxnnd[1]: "",jxjdm}, opList);
		String[] xsfzxx = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?", new String[]{xh}, new String[]{"sjhm", "wysp"});
		if (xsfzxx != null && xsfzxx.length == 2) {
			resMap.put("sjhm", xsfzxx[0]);
			resMap.put("wysp", xsfzxx[1]);
		}
		return resMap;
	}
	
	public ArrayList<String[]> getJxjexpData1(String xh) throws Exception {
		String[] opList = new String[]{"jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1", "zycjpx1", "tyhgbz1","jxj2", "shyg2", "ddj2", "bxkpjcj2", "bjcjpx2", "zycjpx2", "tyhgbz2","jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3", "zycjpx3", "tyhgbz3","jxj4", "shyg4", "ddj4", "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4", };
		return dao.rsToVator("select * from xsjxjxxb where xh=?", new String[]{xh}, opList);
	}
	
	/**
	 * �Զ���ȡ��ѧ�������ƺŴ���
	 * @return
	 * @throws Exception
	 */
	public String getAutoJxjId() throws Exception {
		return dao.getOneRs("select autojxjid.nextval num from dual", new String[]{}, "num");
	}
	
	/**
	 * ��ѧ���޸���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc," +
				"(select b.jxjlb from jxjdmb b where a.jxjdm=b.jxjdm) jxjlb," +
				"a.jxjdm,(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm) jlje," +
				"a.xn,a.nd,a.dnshjxj,a.drzw,a.xxjl,a.kycg,a.sqly,(select b.sjhm from" +
				" xsfzxxb b where a.xh=b.xh) sjhm,(select b.wysp from xsfzxxb b where" +
				" a.xh=b.xh) wysp,b.* from view_xsjxjb a left join xsjxjxxb b on a.xh=b.xh where a.xn||a.nd||a.xh||a.jxjdm = ?";
		rsMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return rsMap;
	}
	
	/**
	 * ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjListN() throws Exception {
		return dao.getList("select jxjdm,jxjmc", new String[] {}, new String[] {	
				"jxjdm", "jxjmc" });
	}
	
	public boolean stujxjDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("xsjxjb", "xn||nd||xh||jxjdm", pkValue, request);
	}
	
	public boolean sturychDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("xsrychb", "xn||nd||rychdm||xh", pkValue, request);
	}
	
	/**
	 * ѧУѡ��ѧԺ����˽�ѧ������
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyJxjList(String xydm) throws Exception {
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb where 1=1 ";
		String[] opList = new String[]{"jxjdm", "jxjmc"};
		if (StringUtils.isNull(xydm)) {
			jxjList = dao.getList(sql, new String[]{}, opList);
		} else {
			jxjList = dao.getList(sql + " and xydm='"+xydm +"'", new String[]{}, opList);
		}
		return jxjList;
	}
	
	/**
	 * ѧУѡ��ѧԺ����������ƺ�����
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyRychList(String xydm) throws Exception {
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select rychdm,rychmc from rychdmb where 1=1 ";
		String[] opList = new String[]{"rychdm", "rychmc"};
		if (StringUtils.isNull(xydm)) {
			jxjList = dao.getList(sql, new String[]{}, opList);
		} else {
			jxjList = dao.getList(sql + " and xydm='"+xydm +"'", new String[]{}, opList);
		}
		return jxjList;
	}
	
	/**
	 * ������Υ�ʹ����ĺż��
	 * @param xh
	 * @param cfwh
	 * @return
	 * @throws Exception
	 */
	public boolean chkStucfwh(String xh, String cfwh) throws Exception {
		String num = dao.getOneRs(
				"select count(*) num from wjcfb where xh=? and cfwh=?",
				new String[] { xh, cfwh }, "num");
		if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ���ѧ���Ƿ��Ǳ�ҵ��
	 * @param xh
	 * @return
	 */
	public boolean chkStuIsBys(String xh) {
		return StandardOperation.isBys(StringUtils.isNull(xh) ? "" : xh.trim());
	}
}
