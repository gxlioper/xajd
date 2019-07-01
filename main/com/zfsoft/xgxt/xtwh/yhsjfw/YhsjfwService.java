/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ϵͳ����
 * @�๦������: �û����ݷ�Χ
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class YhsjfwService extends SuperServiceImpl<YhModel, YhsjfwDao> {
	private static String CHAR1 = ";";// �����ķָ��
	private static String CHAR2 = ":";// �꼶��ķ���
	private static String CHAR3 = ",";// ѧԺ��ķ���
	private static String CHAR4 = "-";// ѧԺ��רҵ��ķ���
	private static String CHAR5 = "[|]";// רҵ��ķ���
	private YhsjfwDao dao = new YhsjfwDao();

	public YhsjfwService() {
		super.setDao(dao);
	}

	/**
	 * ��ȡ�û����б�������ѧ����
	 */
	public List<HashMap<String, String>> getYhzForSzdwList() throws Exception {
		return dao.getYhzForSzdwList();
	}

	/**
	 * ��ȡ���Ŵ��룬�����б�
	 */
	public List<HashMap<String, String>> getYjbmList() throws Exception {
		return dao.getYjbmList();
	}

	/**
	 * ��ȡ�꼶ѧԺרҵ�б�
	 */
	public List<HashMap<String, String>> getNjxyzyList() throws Exception {
		return dao.getNjxyzyList();
	}

	/**
	 * 
	 * @����:�����û�����ȡ���ݷ�Χ���롢����
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-27 ����10:13:46
	 * @�޸ļ�¼:
	 * @param yhm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String yhm) throws Exception {
		return dao.getDataById(yhm);
	}

	public String getYhsjfw(String userName, String userStatus, String tableBm,
			String xydm, String bjdm) {
		StringBuilder sb = new StringBuilder();
		String sjfwSql = null;
		String sjfwdm = null;
		try {
			HashMap<String, String> dataMap = dao.getDataById(userName);
			if (dataMap != null) {
				sjfwdm = dataMap.get("sjfwdm");
				if (sjfwdm != null && !sjfwdm.equals("")) {
					sjfwSql = parseSjfw(sjfwdm);
					if (sjfwSql != null && !sjfwSql.equals("")) {

						if ("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
							sb.append(" exists (select 1 ");
							sb.append("  from view_njxyzybj_all  where bjdm=");
							sb.append(Base.isNull(tableBm) ? "" : tableBm);
							sb.append(".");
							sb.append(bjdm);
							sb.append(" and (");
							sb.append(sjfwSql);
							sb.append("))");
						} else if ("bzr".equalsIgnoreCase(userStatus)
								|| "fdy".equalsIgnoreCase(userStatus)
								|| "jd".equalsIgnoreCase(userStatus)) {
							sb.append(" exists (select 1 ");
							sb.append(" from view_njxyzybj_all  where bjdm=");
							sb.append(Base.isNull(tableBm) ? "" : tableBm);
							sb.append(".");
							sb.append(bjdm);
							sb.append(" and (");
							sb.append(sjfwSql);
							sb.append("))");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getYhsjfw(User user, String tableBm, String xydm, String bjdm) {
		// �û���
		String userName = user.getUserName();
		// �û����
		String userStatus = user.getUserStatus();
		return getYhsjfw(userName, userStatus, tableBm, xydm, bjdm);
	}

	public String parseSjfw(String sjfwdm) {
		String sjfwSql = "";

		String[] njxyzysArr = sjfwdm.split(CHAR1);
		boolean njFlag = false;
		if (njxyzysArr != null && njxyzysArr.length >= 0) {
			for (int i = 0; i < njxyzysArr.length; i++) {
				String njxyzys = njxyzysArr[i];
				if (njxyzys == null) {
					continue;
				}
				njxyzys = njxyzys.trim();
				String[] njxyzyArr = njxyzys.split(CHAR2);
				if (njxyzyArr == null || njxyzyArr.length == 0) {
					continue;
				}
				String nj = "";
				String xyzyStr = "";
				nj = njxyzyArr[0].trim();

				if (njFlag) {
					sjfwSql += " or ";
				} else {
					njFlag = true;
				}

				sjfwSql += " nj='" + nj + "' ";

				if (njxyzyArr.length > 1) {
					xyzyStr = njxyzyArr[1];
				}
				if (xyzyStr != null && !xyzyStr.trim().equals("")) {
					sjfwSql += setNjxyzyOne(nj, xyzyStr.trim());
				}

			}
		}
		return sjfwSql;
	}

	private String setNjxyzyOne(String nj, String xyzyStr) {
		String xySjfwSql = "";
		String zySjfwSql = "";

		boolean xyFlag = false;
		String[] xyzyArr = xyzyStr.split(CHAR3);
		for (int i = 0; i < xyzyArr.length; i++) {
			String xyzy = xyzyArr[i];
			if (xyzy == null) {
				continue;
			}
			String[] xyzyOneArr = xyzy.split(CHAR4);
			if (xyzyOneArr == null || xyzyOneArr.length == 0) {
				continue;
			}
			String xydm = xyzyOneArr[0].trim();

			if (xyFlag) {
				xySjfwSql += " or ";
			} else {
				xyFlag = true;
			}

			xySjfwSql += " xydm='" + xydm + "' ";

			String zys = "";
			if (xyzyOneArr.length > 1) {
				zys = xyzyOneArr[1];
			}
			if (zys == null || zys.trim().equals("")) {
				continue;
			}
			zys = zys.trim();
			String[] zyArr = zys.split(CHAR5);
			if (zyArr == null || zyArr.length == 0) {
				continue;
			}
			boolean zyFlag = false;
			zySjfwSql = "";
			for (int j = 0; j < zyArr.length; j++) {
				String zydm = zyArr[j];
				if (zydm == null || zydm.trim().equals("")) {
					continue;
				}
				zydm = zydm.trim();
				if (zyFlag) {
					zySjfwSql += " or ";
				} else {
					zyFlag = true;
				}
				zySjfwSql += " zydm='" + zydm + "' ";
			}
			if (!zySjfwSql.equals("")) {
				xySjfwSql += " and (" + zySjfwSql + ")";
			}
		}
		if (!xySjfwSql.equals("")) {
			xySjfwSql = " and (" + xySjfwSql + ")";
		}

		return xySjfwSql;
	}

	/**
	 * 
	 * @����:���ݷ�Χ��Ȩ����
	 * @���ߣ�ligl
	 * @���ڣ�2014-2-27 ����04:22:11
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runDeal(YhsjfwModel model) throws Exception {
		return dao.runDeal(model);
	}
}
