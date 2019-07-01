
package xgxt.pjpy.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �����������������Ƚ��༶DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-20</p>
 */
public class XjbjDAO extends PjpyYnysDAO {
	
	public List<String> values = new ArrayList<String>();//�����ѯ����ֵ
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYnys_xjbjb(XjbjModel model, HttpServletRequest request)
			throws Exception {
		dao = DAO.getInstance();
		String xn = dao.getOneRs("select jxjsqxn from xtszb", new String[]{}, "jxjsqxn");
		StandardOperation.delete("ynys_xjbjb", new String[] { "xn", "bjdm" },
				new String[] { xn, model.getBjdm() }, request);
		return StandardOperation.insert("ynys_xjbjb", new String[] { "bjdm",
				"bjrs", "bzr", "xn", "bjdbqk", "bzryj", "fdyyj" },
				new String[] { model.getBjdm(), model.getBjrs(),
						DealString.toGBK(model.getBzr()), xn,
						DealString.toGBK(model.getBjdbqk()),
						DealString.toGBK(model.getBzryj()),
						DealString.toGBK(model.getFdyyj()) }, request);
	}
	
	/**
	 * �Ƚ��༶��ӡ
	 * @param xn
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> printXjbj(String pkValue) throws Exception {
		dao = DAO.getInstance();
		HashMap<String, String> rs = dao
				.getMapNotOut(
						"select xymc,zymc,bjmc,nj,bjrs,bzr,bjdbqk,bzryj,fdyyj,yxyj,xxyj,xyyj,to_char(sysdate,'yyyymmdd') tbrq from view_ynys_xjbj where xn||bjdm=?",
						new String[] { pkValue});
		String tbrq = rs.get("tbrq");
		if (!StringUtils.isNull(tbrq) && tbrq.length() == 8) {
			tbrq = "�������:  " + tbrq.substring(0, 4) + "��"
					+ tbrq.subSequence(4, 6) + "��" + tbrq.subSequence(6, 8)
					+ "��";
		}
		rs.put("tbrq", tbrq);
		return rs;
	}
	
	/**
	 * �����Ŀ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShxmList() throws Exception {
		dao = DAO.getInstance();
		String[] cnList = new String[]{"�Ƚ��༶", "�����ҵ��"};
		return dao.arrayToList(cnList, cnList);
	}
	
	/**
	 * �Ƚ��༶��ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(XjbjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" and xn= ?");
		values.add(dao.getOneRs("select jxjsqxn from xtszb", new String[]{}, "jxjsqxn"));
		
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		return whereSql;
	}
	
	/**
	 * �Ƚ��༶��ѯ���(Ժϵ)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjListByyx(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xn||bjdm pk, rownum,xn,nj,xymc,zymc,bjmc,'�Ƚ��༯��' bjch" +
				",bzr,yxsh from view_ynys_xjbj where 1=1 ";
		String[] opList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
				"bjmc", "bjch", "bzr","yxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * �Ƚ��༶��ѯ���(ѧУ)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjListByxx(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xn||bjdm pk, rownum,xn,nj,xymc,zymc,bjmc,'�Ƚ��༯��' bjch" +
				",bzr,xxsh from view_ynys_xjbj where 1=1 and yxsh='ͨ��' ";
		String[] opList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
				"bjmc", "bjch", "bzr","xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * Ժϵ����Ƚ��༶���
	 * @param keys
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String yxshXjbjResult(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("ynys_xjbjb",
					new String[] { "yxsh" }, new String[] { sJg },
					"xn||bjdm", whichpk, request);
			if (!bFlag) {//���ʧ�ܺ��¼���ʧ�ܵ��к�
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * �����ҵ��ѧԺ���
	 * @param keys
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String yxshYxbysResult(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("ynys_yxbysb",
					new String[] { "yxsh" }, new String[] { sJg },
					"xh||xn", whichpk, request);
			if (!bFlag) {//���ʧ�ܺ��¼���ʧ�ܵ��к�
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * ѧУ����Ƚ��༶���
	 * @param keys
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xxshXjbjResult(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("ynys_xjbjb",
					new String[] { "xxsh" }, new String[] { sJg },
					"xn||bjdm", whichpk, request);
			if (!bFlag) {//���ʧ�ܺ��¼���ʧ�ܵ��к�
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * ѧУ��������ҵ�����
	 * @param keys
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xxshYxbysResult(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("ynys_yxbysb",
					new String[] { "xxsh" }, new String[] { sJg },
					"xh||xn", whichpk, request);
			if (!bFlag) {//���ʧ�ܺ��¼���ʧ�ܵ��к�
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * ��ʾ�Ƚ��༶���������Ϣ
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjshOne(String pkValue, String userType)
			throws Exception {
		dao = DAO.getInstance();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (StringUtils.isEqual("xy", userType)) {
			rsMap = dao
					.getMapNotOut(
							"select xymc,zymc,bjmc,nj,xn,'�Ƚ��༯��' bjch,yxsh yesNo,yxyj yj," +
							"fdyyj,bzryj,bjrs,bzr,bjdbqk from view_ynys_xjbj where xn||bjdm = ?",
							new String[] { pkValue });
		} else {
			rsMap = dao
			.getMapNotOut(
					"select xymc,zymc,bjmc,nj,xn,'�Ƚ��༯��' bjch,xxsh yesNo,xxyj yj,xyyj," +
					"fdyyj,bzryj,bjrs,bzr,bjdbqk from view_ynys_xjbj where xn||bjdm = ?",
					new String[] { pkValue });
		}
		return rsMap;
	}
	
	/**
	 * �����ҵ�����������ʾ
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewYxbysOne(String pkValue, String userType) throws Exception {
		dao = DAO.getInstance();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		if (StringUtils.isEqual("xy", userType)) {
			rsMap = dao
					.getMapNotOut(
							"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,mz,zzmm,jkzk,syd," +
							"jtdz,bjyj,yxsj,yxsh yesNo,yxyj yj from view_ynys_yxbys where xh||xn = ?",
							new String[] { pkValue });
		} else {
			rsMap = dao
			.getMapNotOut(
					"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,mz,zzmm,jkzk,syd,jtdz,bjyj" +
					",yxsj,xxsh yesNo,xxyj yj,jytyj from view_ynys_yxbys where xh||xn = ?",
					new String[] { pkValue });
		}
		return rsMap;
	}
	
	/**
	 * ����б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShList() throws Exception {
		dao = DAO.getInstance();
		return dao.getChkList(3);
	}
	
	/**
	 * Ժϵ��������Ƚ��༶���
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjshone(String pkValue, String yesNo, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("ynys_xjbjb", new String[] { "yxsh",
				"yxyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) }, "xn||bjdm", pkValue, request);
	}
	
	/**
	 * �����ҵ��������˽��ѧԺ
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysshone(String pkValue, String yesNo, String yj,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("ynys_yxbysb", new String[] { "yxsh",
				"yxyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj) }, "xh||xn", pkValue, request);
	}
	
	/**
	 * ѧУ��������Ƚ��༶���
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjshone(String pkValue, String yesNo, String yj,
			String xyyj, HttpServletRequest request) throws Exception {
		return StandardOperation.update("ynys_xjbjb", new String[] { "xxsh",
				"xxyj", "xyyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj), DealString.toGBK(xyyj) }, "xn||bjdm",
				pkValue, request);
	}
	
	/**
	 * ѧУ������������ҵ�����
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysshone(String pkValue, String yesNo, String yj,
			String jytyj, HttpServletRequest request) throws Exception {
		return StandardOperation.update("ynys_yxbysb", new String[] { "xxsh",
				"xxyj", "jytyj" }, new String[] { DealString.toGBK(yesNo),
				DealString.toGBK(yj), DealString.toGBK(jytyj) }, "xh||xn",
				pkValue, request);
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjQryResult(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xn||bjdm||rychdm pk,rownum,xn,nj,xymc,zymc,bjmc,bzr,'�Ƚ��༯��' bjch" +
				",yxsh,xxsh from view_ynys_xjbj where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
				"zymc", "bjmc", "bjch", "bzr", "yxsh", "xxsh" };
		return dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<String[]> nbzyXjbjQryResult(XjbjModel model) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select a.xn||a.bjdm||a.rychdm pk,rownum,a.* from view_pjpy_xjbjandwmsq a where 1=1 ";
		if (Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select a.xn||a.xq||a.rychdm||a.bjdm pk,rownum,a.* from view_pjpy_xjbjandwmsq a where 1=1 ";
		}
		String[] opList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
				"zymc", "bjmc", "rychmc", "bzr", "xysh", "xxsh" };
		if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
			opList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
					"zymc", "bjmc", "rychmc","jtch", "bzr", "xysh", "xxsh" };
		}
		return dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<String[]> nbzyXjbjQryResult1(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select xn||bjdm||rychdm pk,rownum,xn,nj,xymc,zymc,bjmc,bzr,rychmc" +
				",xysh,xxsh from view_pjpy_xjbjandwmsq where 1=1 and rychdm='00001' ";
		String[] opList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
				"zymc", "bjmc", "rychmc", "bzr", "xysh", "xxsh" };
		return dao.rsToVator(sql + whereSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql1(XjbjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		return whereSql;
	}
	
	/**
	 * ��ʾ�Ƚ����޸���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xymc,zymc,bjmc,xn,nj,bjrs,bzr," +
						"bjdbqk,bzryj,fdyyj from view_ynys_xjbj where xn||bjdm||rychdm = ?",
						new String[] { pkValue });
	}
	
	public HashMap<String, String> nbzyXjbjxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xymc,zymc,bjmc,xn,nj,xsrs,bzr,bzxm,jtch," +
						"tzs,bhgqs,ywcf,bjry,zysj,rychmc,bz from view_pjpy_xjbjandwmsq where xn||bjdm||rychdm = ?",
						new String[] { pkValue });
	}
	
	public HashMap<String, String> zjjdXjbjxx(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xymc,zymc,bjmc,xn,nj,xsrs,bzr,bzxm,jtch," +
						"tzs,bhgqs,ywcf,bjry,zysj,rychmc,bz from view_pjpy_xjbjandwmsq where xn||xq||rychdm||bjdm = ?",
						new String[] { pkValue });
	}
	
	/**
	 * �����޸���Ϣ
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjxx(String pkValue, XjbjModel model,
			HttpServletRequest request) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
					"bzr", "xsrs", "bzxm", "xn", "tzs", "bhgqs", "ywcf", "bjry", "zysj"}, new String[] {
					DealString.toGBK(model.getBzr()),model.getXsrs(),
					DealString.toGBK(model.getBzxm()), model.getXn(),
					DealString.toGBK(model.getTzs()), model.getBhgqs(),
					model.getYwcf(), DealString.toGBK(model.getBjry()),
					DealString.toGBK(model.getZysj()) }, "xn||bjdm||rychdm", pkValue,
					request);
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			return StandardOperation.update("ynys_xjbjb", new String[] { "bjrs",
					"bzr", "bjdbqk", "bzryj", "fdyyj" }, new String[] {
					model.getBjrs(), DealString.toGBK(model.getBzr()),
					DealString.toGBK(model.getBjdbqk()),
					DealString.toGBK(model.getBzryj()),
					DealString.toGBK(model.getFdyyj()) }, "xn||bjdm||rychdm", pkValue,
					request);
		} else {
			return StandardOperation.update("pjpy_xjbjandwmsqb", new String[] {
					"bzr", "xsrs", "bzxm", "xn", "tzs", "bhgqs", "ywcf", "bjry", "zysj","jtch"}, new String[] {
					DealString.toGBK(model.getBzr()),model.getXsrs(),
					DealString.toGBK(model.getBzxm()), model.getXn(),
					DealString.toGBK(model.getTzs()), model.getBhgqs(),
					model.getYwcf(), DealString.toGBK(model.getBjry()),
					DealString.toGBK(model.getZysj()) ,DealString.toGBK(model.getJtch())}, "xn||bjdm||rychdm", pkValue,
					request);
		}
	}
	
	/**
	 * ɾ���Ƚ��༶��Ϣ
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delXjbjxx(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			for (int i = 0; i < keys.length; i++) {
				String whichpk = keys[i];
				boolean bFlag = StandardOperation.delete("pjpy_xjbjandwmsqb", "xn||bjdm||rychdm",
						whichpk, request);
				if (!bFlag) {//ɾ��ʧ�ܺ��¼ɾ��ʧ�ܵ��к�
					shRes += (i + 1);
					shRes += ",";
				}
			}
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			for (int i = 0; i < keys.length; i++) {
				String whichpk = keys[i];
				boolean bFlag = StandardOperation.delete("ynys_xjbjb", "xn||bjdm||rychdm",
						whichpk, request);
				if (!bFlag) {//ɾ��ʧ�ܺ��¼ɾ��ʧ�ܵ��к�
					shRes += (i + 1);
					shRes += ",";
				}
			}
		} else {
			for (int i = 0; i < keys.length; i++) {
				String whichpk = keys[i];
				boolean bFlag = StandardOperation.delete("pjpy_xjbjandwmsqb", "xn||bjdm||rychdm",
						whichpk, request);
				if (!bFlag) {//ɾ��ʧ�ܺ��¼ɾ��ʧ�ܵ��к�
					shRes += (i + 1);
					shRes += ",";
				}
			}
		}
		
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * �����ҵ����ѯ��Ϣ(ѧԺ)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getYxbysByxy(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql2(model);
		String sql = "select xh||xn pk,rownum,xh,xm,xn,nj,xymc,zymc,bjmc,'�����ҵ��' grch" +
				",yxsh from view_ynys_yxbys where 1=1 ";
		String[] opList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
				"bjmc", "grch", "yxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		
	}
	
	/**
	 * �����ҵ����ѯ��Ϣ(ѧУ)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getYxbysByxx(XjbjModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql2(model);
		String sql = "select xh||xn pk,rownum,xh,xm,xn,nj,xymc,zymc,bjmc,'�����ҵ��' grch" +
				",xxsh from view_ynys_yxbys where yxsh='ͨ��' ";
		String[] opList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
				"bjmc", "grch", "xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		
	}
	
	public StringBuffer getWhereSql2(XjbjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		return whereSql;
	}
}
