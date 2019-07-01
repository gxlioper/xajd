/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:������DAO </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-7 ����11:23:14</p>
 */
package xgxt.action.zgkd.bkManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.zgkd.SyltForm;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class bkManageDao {
	private ArrayList<String> values = new ArrayList<String>();// ��ѯ����ֵ

	/** ģ�����õ��б� */
	public List<HashMap<String, String>> getCommonList(int index) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = null;
		String sql = "";
		if (index == 1) {// ϵͳ�û��б�
			sql = "select dlm ,nc from sylt_yhb ";
			list = dao.getList(sql, new String[] {},
					new String[] { "dlm", "nc" });
		} else if (index == 2) {// ��̳����б�
			sql = " select bkdm,bkmc from sylt_bkb ";
			list = dao.getList(sql, new String[] {}, new String[] { "bkdm",
					"bkmc" });
		}
		return list;
	}

	/**
	 * ��ȡ������Աƥ���ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBkGlyppTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "rownum", "glym", "nc", "yhjb",
				"bkmc" };
		String[] cnList = new String[] { "����", "�к�", "�û���", "�ǳ�", "�û�����",
				"�������" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tempList = new HashMap<String, String>();
			tempList.put("en", enList[i]);
			tempList.put("cn", cnList[i]);
			topList.add(tempList);
		}
		return topList;
	}

	/**
	 * ��ȡ������Աƥ���ѯ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkGlyppResult(SyltForm bkglModel) throws Exception {
		DAO dao = DAO.getInstance();
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer sqlBf = new StringBuffer();
		sqlBf
				.append("select * from (select rownum r,glym||bkdm pk,rownum,glym,nc,(case when trim(yhjb)='2' then ");
		sqlBf
				.append(" '��������Ա' when trim(yhjb)='1' then '����Ա' else '��ͨ�û�' end) yhjb, ");
		sqlBf.append(" bkmc from view_sylt_bkglypp where 1=1 ");
		sqlBf.append(getWhereSql(bkglModel));
		sqlBf.append(") where  r<= ");
		sqlBf.append((bkglModel.getPages().getStart() + bkglModel.getPages()
				.getPageSize()));
		sqlBf.append(" and r> ");
		sqlBf.append(bkglModel.getPages().getStart());
		String[] opList = new String[] { "pk", "rownum", "glym", "nc", "yhjb",
				"bkmc" };
		resList = dao.rsToVator(sqlBf.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return resList;
	}

	/**
	 * ��ȡ��ѯ������ֵ
	 * 
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(SyltForm bkglModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xm = bkglModel.getXm();
		String yhm = bkglModel.getYhm();
		String bkdm = bkglModel.getBkdm();
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and nc like ?");
			values.add("%" + xm + "%");
		}
		if (!StringUtils.isNull(yhm)) {
			whereSql.append(" and glym = ?");
			values.add(yhm);
		}
		if (!StringUtils.isNull(bkdm)) {
			whereSql.append(" and bkdm = ?");
			values.add(bkdm);
		}
		return whereSql;
	}

	/**
	 * ��֤�û��Ƿ�ע��
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public boolean chkYhmisReg(String yhm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean bFlag = false;
		String sql = "select dlm from sylt_yhb where dlm = ?";
		String[] resList = dao.getOneRs(sql, new String[] { yhm },
				new String[] { "dlm" });
		if (resList != null && resList.length > 0) {
			bFlag = true;
		}
		return bFlag;
	}

	/**
	 * ��ȡ�û�������Ϣ:�ǳ�,����ǩ��
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhInfo(String yhm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select nc,grqm from sylt_yhb where dlm = ?";
		String[] resList = dao.getOneRs(sql, new String[] { yhm },
				new String[] { "nc", "grqm" });
		return resList;
	}

	/**
	 * ��������Ϣ����
	 * 
	 * @param bkglModel
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoSave(BkglyPpModel bkglModel,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String glym = bkglModel.getYhm();
		String bkdm = bkglModel.getBkdm();
		// ������Ϣ
		boolean temp = StandardOperation.delete("sylt_bkglyppb", "glym||bkdm",
				glym + bkdm, request);
		if (temp) {
			bFlag = StandardOperation.insert("sylt_bkglyppb", new String[] {
					"glym", "bkdm" }, new String[] { glym, bkdm }, request);
			if (bFlag) {// �ɹ�������û�ע�������û�����
				StandardOperation.update("sylt_yhb", new String[] { "yhjb" },
						new String[] { "1" }, "dlm", glym, request);
			}
		}
		return bFlag;
	}

	/**
	 * ��ȡ������Աƥ����Ϣ
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkglyPpInfo(String pkValue)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select glym,bkdm,nc,grqm from view_sylt_bkglypp where glym||bkdm = ?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}

	/**
	 * ������Աƥ����Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delBkglyPpInfo(String[] keys, HttpServletRequest request)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		int del = 0;// ɾ����¼��
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			String[] xm = dao.getOneRs(
					"select glym from sylt_bkglyppb where glym||bkdm = ?",
					new String[] { whichxh }, new String[] { "glym" });// ��ȡ�������ڸ����û�����
			boolean bFlag = StandardOperation.delete("sylt_bkglyppb",
					"glym||bkdm", whichxh, request);
			if (bFlag) {
				sql = "select glym,bkdm from sylt_bkglyppb where glym = ?";
				String[] resList = dao.getOneRs(sql,
						new String[] { xm[0] != null && xm[0] != "" ? xm[0]
								: "" }, new String[] { "glym", "bkdm" });
				if (resList == null) {
					if (xm != null && xm.length == 1) {
						StandardOperation.update("sylt_yhb",
								new String[] { "yhjb" }, new String[] { "0" },
								"dlm", xm[0], request);
					}
				}
				del++;
			}
		}// end for
		return String.format("%1$s", del);
	}

	/**
	 * ������Աƥ����Ϣ�޸�
	 * 
	 * @param pkValue
	 * @param yhm
	 * @param bkdm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean bkglInfoModi(String pkValue, String yhm, String bkdm,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean temp = StandardOperation.delete("sylt_bkglyppb", "glym||bkdm",
				pkValue, request);
		if (temp) {
			temp = StandardOperation.delete("sylt_bkglyppb", "glym||bkdm", yhm
					+ bkdm, request);
			if (temp) {
				bFlag = StandardOperation.insert("sylt_bkglyppb", new String[] {
						"glym", "bkdm" }, new String[] { yhm, bkdm }, request);
			}
		}
		return bFlag;
	}

	/**
	 * ��ȡ�û�����
	 * 
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public String[] getYhxm(String yhm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select xm from yhb where yhm=? and rownum=1";
		String[] resMap = dao.getOneRs(sql, new String[] { yhm },
				new String[] { "xm" });
		return resMap;
	}

	/** �����Ϣ�鿴 */
	public ArrayList<String[]> BKResult(SyltForm syltForm) {
		syltForm.getPages().setPageSize(8);
		ArrayList<String[]> rs = new ArrayList<String[]>();
		DAO dao = DAO.getInstance();
		// ����ı���
		String[] opCols = { "r", "bkdm", "bkmc" };
		String sql = "select * from (select rownum r,bkdm,bkmc from sylt_bkb) where r > "
				+ syltForm.getPages().getStart()
				+ " and r<= "
				+ syltForm.getPages().getStart()
				+ syltForm.getPages().getPageSize();
		rs = dao.rsToVator(sql, new String[] {}, opCols);
		sql = "select rownum r,bkdm,bkmc from sylt_bkb";
		syltForm.getPages().setMaxRecord(
				dao.rsToVator(sql, new String[] {}, opCols).size());
		return rs;

	}

	/**
	 * @return ��̳����ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> BKResultTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// �����뷽��getXysbjxjSearch�е��������һ��
		String[] opCols = { "r", "bkdm", "bkmc" };
		String[] cnCols = { "�к�", "ģ�����", "ģ������" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;
	}

	/** �����Ϣ���� */
	public boolean bkwhaddsave(BkWeiHuModel model, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String bkdm = model.getBkdm();
		String bkmc = model.getBkmc();
		String bkms = model.getBkms();
		boolean temp = StandardOperation.delete("sylt_bkb", "bkdm", bkdm,
				request);
		if (temp) {
			bFlag = StandardOperation.insert("sylt_bkb", new String[] { "bkdm",
					"bkmc", "bkms" }, new String[] { bkdm, bkmc, bkms },
					request);
		}
		return bFlag;
	}

	/** �����Ϣ�鿴 */
	public HashMap<String, String> viewBKInfo(String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String[] cols = { "bkdm", "bkmc", "bkms" };
		String sql = " select bkdm,bkmc,bkms from sylt_bkb where bkdm=? ";
		map = dao.getMap(sql, new String[] { pkValue }, cols);
		return map;
	}

	/**
	 * �����Ϣ����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public int bkInFoDel(String[] keys, HttpServletRequest request)
			throws Exception {
		int del = 0;
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]).trim();// �õ�����
			System.out.print("a" + whichxh + "b");
			boolean bFlag = StandardOperation.delete("sylt_bkb", "bkmc",
					whichxh, request);
			if (bFlag) {
				del++;
			}
		}// end for
		return del;
	}

	/**
	 * ���ذ���ȫ����Ϣ bkdm/bkmc
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getBkDmMcList() {
		DAO dao = DAO.getInstance();
		String sql = "select bkdm,bkmc from sylt_bkb order by to_number(xssx)";
		String[] outputValues = new String[] { "bkdm", "bkmc" };
		List<HashMap<String, String>> rs = dao.getList(sql, new String[] {},
				outputValues);
		return rs;
	}
}
