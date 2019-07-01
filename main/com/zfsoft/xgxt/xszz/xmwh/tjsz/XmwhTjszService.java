/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.tjsz;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.tsxs.TsxsService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xtwh.bjdl.BjdlService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhTjszService extends
		SuperServiceImpl<XmwhTjszForm, XmwhTjszDao> {
	private static String FLAG_INPUT_NUM = "1";
	private static String FLAG_SELECT = "2";
	private static String FLAG_CHECKBOX = "3";
	private static String FLAG_CHECKBOX_DIV = "5";

	private static String FLAG_GXZGS_GDZ = "1";// ��ϵֵ��ʽΪ�̶�ֵʱ
	private static String FLAG_GXZGS_SJKQZ = "2";// ��ϵֵ��ʽΪ���ݿ�ȡֵ,������:����,���ơ�
	private static String FLAG_GXZGS_FF = "3";// ��ϵֵ��ʽΪ����ȫ��#������,��������������ȡ��ǰ�������з���

	private static String CHAR_DH = ",";
	private static String CHAR_SX = "|";
	private static String CHAR_SX_REG = "[|]";
	private static String CHAR_FH = ";";
	private static String CHAR_MH = ":";
	private static String CHAR_JH = "#";

	private XmwhTjszDao dao = new XmwhTjszDao();

	public XmwhTjszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public XmwhTjszViewForm getAll(String xmdm) throws Exception {
		List<HashMap<String, String>> tjList = dao.getAllTj();
		List<HashMap<String, String>> gxList = dao.getAllGx();
		List<HashMap<String, String>> tjgxList = dao.getAllTjGx();
		List<HashMap<String, String>> tjszList = dao.getTjsz(xmdm);

		List<HashMap<String, String>> xnList = Base.getXnndList();
		List<HashMap<String, String>> xqList = Base.getXqList();
		List<HashMap<String, String>> yyfwList = new TsxsService()
				.getTslxList("2");// Ӧ�ö���
		List<HashMap<String, String>> bjdlList = new BjdlService()
				.getBjdlList();// �༶����
		// List<HashMap<String, String>> bjdlList = null;// Ӧ�ö���

		for (HashMap<String, String> map : bjdlList) {
			map.put("lxdm", map.get("dm"));
			map.put("lxmc", map.get("mc"));
			yyfwList.add(map);
		}

		List<HashMap<String, String>> sfqyList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// �Ƿ�����

		for (HashMap<String, String> map : tjgxList) {// ������ϵֵ����
			String gxlx = null;
			String gxz = null;
			String gxzgs = null;
			gxlx = map.get("gxlx");
			gxz = map.get("gxz");
			gxzgs = map.get("gxzgs");
			if (gxlx != null
					&& (gxlx.equals(FLAG_SELECT) || gxlx.equals(FLAG_CHECKBOX) || gxlx
							.equals(FLAG_CHECKBOX_DIV))) {// checkbox��ͨ����������ѯ����ֵ
				gxz = getGxzGsh(gxzgs, gxz);
				map.put("gxz", gxz);
			}
		}

		XmwhTjszViewForm viewForm = new XmwhTjszViewForm();
		viewForm.setTjList(tjList);
		viewForm.setGxList(gxList);
		viewForm.setTjgxList(tjgxList);
		viewForm.setTjszList(tjszList);

		viewForm.setXnList(xnList);
		viewForm.setXqList(xqList);
		viewForm.setBjdlList(yyfwList);
		viewForm.setSfqyList(sfqyList);

		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao()
				.getZhcpTjxm();// �۲�������Ŀ
		viewForm.setZhcpTjxmList(zhcpTjxmList);

		return viewForm;
	}

	/**
	 * 
	 * @����:��ѯѧ��ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public XmwhTjszViewForm getXn(String xmdm) throws Exception {
		List<HashMap<String, String>> xnList = Base.getXnndList();
		List<HashMap<String, String>> xqList = Base.getXqList();
		String cuurXn = Base.currXn;
		if (cuurXn != null) {
			String cuurXnStart = cuurXn.substring(0, 4);
			String cuurXnEnd = cuurXn.substring(5, 9);

			HashMap<String, String> map = null;
			String xn = null;
			for (int i = xnList.size() - 1; i >= 0; i--) {
				map = xnList.get(i);
				xn = map.get("xn");
				if (xn != null && xn.length() >= 9) {
					String xnStart = xn.substring(0, 4);
					String xnEnd = xn.substring(5, 9);
					try {
						if (Integer.parseInt(cuurXnStart) < Integer
								.parseInt(xnStart)
								&& Integer.parseInt(cuurXnEnd) < Integer
										.parseInt(xnEnd)) {
							xnList.remove(i);
						}
					} catch (Exception e) {
						e.printStackTrace();// /////////////////
					}
				}
			}
		}
		XmwhTjszViewForm viewForm = new XmwhTjszViewForm();
		viewForm.setXnList(xnList);
		viewForm.setXqList(xqList);

		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao()
				.getZhcpTjxm();// �۲�������Ŀ
		viewForm.setZhcpTjxmList(zhcpTjxmList);

		return viewForm;
	}

	/**
	 * 
	 * @����:��ȡĳ����Ŀ��������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-2 ����02:02:36
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		return dao.getTjsz(xmdm);
	}

	/*
	 * ���ݹ�ϵֵ��ʽ����ʽ����ϵֵ��ת��Ϊ���߷ָ��ʽ
	 */
	private String getGxzGsh(String gxzgs, String gxz) throws Exception {
		String result = gxz;
		if (gxzgs != null && gxz != null) {
			if (gxzgs.equals(FLAG_GXZGS_SJKQZ)) {// FLAG_GXZGS_SJKQZ =
				// "2";//��ϵֵ��ʽΪ���ݿ�ȡֵ,������:����,���ơ�
				result = getSjkqz(gxz);
			} else if (gxzgs.equals(FLAG_GXZGS_FF)) {// FLAG_GXZGS_FF =
				// "3";//��ϵֵ��ʽΪ����ȫ��#������,
				result = getFf(gxz);
			}
		}
		return result;
	}

	/*
	 * ���ݿ�ȡֵ,������:����,���ơ������ظ�ʽ��"|"���ӣ�key1|value1|key2|value2 �����ò��Ϸ����쳣����
	 */
	private String getSjkqz(String gxz) throws Exception {
		String result = "";
		String bm = null;// ����
		String dm = null;// ����
		String mc = null;// ����
		String key = null;
		String value = null;//
		List<HashMap<String, String>> dmMcList = null;
		if (gxz != null) {
			try {
				String[] gxzs = gxz.split(CHAR_MH);
				if (gxzs != null && gxzs.length > 1) {
					bm = gxzs[0];
					dm = gxzs[1].split(CHAR_DH)[0];
					mc = gxzs[1].split(CHAR_DH)[1];
					dmMcList = dao.getDmMc(bm, dm, mc);
					boolean flag = false;
					for (HashMap<String, String> map : dmMcList) {
						key = map.get(dm);
						value = map.get(mc);
						if (value != null) {
							if (flag) {
								result += CHAR_SX;
							} else {
								flag = true;
							}
							result += key + CHAR_SX + value;
						}
					}
				}
			} catch (Exception e) {
				logger.error("�������ò��Ϸ���", e);
			}
		}
		return result;
	}

	/*
	 * FLAG_GXZGS_FF = "3";//��ϵֵ��ʽΪ����ȫ��#������,�����:����
	 * ���ظ�ʽ��"|"���ӣ�key1|value1|key2|value2 �����ò��Ϸ����쳣����
	 */
	private String getFf(String gxz) {
		String result = "";
		String lm = null;// ����
		String ffms = null;// ������|����
		String ffm = null;// ������
		String param = null;// ����
		if (gxz != null) {
			try {
				String[] gxzs = gxz.split(CHAR_MH);
				if (gxzs != null) {
					lm = gxzs[0].split(CHAR_JH)[0];// ����
					ffms = gxzs[0].split(CHAR_JH)[1];// ������|����
					ffm = ffms.split(CHAR_SX_REG)[0];
					if (ffms.split(CHAR_SX_REG).length > 1) {
						param = ffms.split(CHAR_SX_REG)[1];
					}
					Class t = Class.forName(lm);
					Object o = t.newInstance();
					Method method = null;
					if (param == null) {
						method = t.getMethod(ffm);
					} else {
						method = t.getMethod(ffm, String.class);
					}
					if (gxzs.length == 1) {// ������ ����ȫ��#�������������޲Σ�����Ϊ���߷ָ���ַ���
						result = (String) method.invoke(o);
					} else if (gxzs.length > 1) {// ����ȫ��#������:����,���ƣ������޲Σ�����ΪList<hashMap<String,String>>��ʽ
						List<HashMap<String, String>> list = null;
						String dm = gxzs[1].split(CHAR_DH)[0];
						String mc = gxzs[1].split(CHAR_DH)[1];
						if (param == null) {
							list = (List) method.invoke(o);
						} else {
							list = (List) method.invoke(o, param);
						}

						boolean flag = false;
						for (HashMap<String, String> map : list) {
							String key = map.get(dm);
							String value = map.get(mc);
							if (value != null) {
								if (flag) {
									result += CHAR_SX;
								} else {
									flag = true;
								}
								result += key + CHAR_SX + value;
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("�������ò��Ϸ���", e);
			}
		}
		return result;
	}

	/**
	 * 
	 * @����:��ȡĳ����Ŀ�������������ã���������������-----------���ӿڵ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-2 ����02:02:36
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm, String xh)
			throws Exception {
		List<HashMap<String, String>> xqList = Base.getXqList();// ѧ��
		List<HashMap<String, String>> tjszGlList = dao.getTjszGl(xmdm, xh);
		for (HashMap<String, String> map : tjszGlList) {
			String sqts = null;
			String xn = null;
			String xnView = null;// ѧ����ʾ����
			String tjz = null;
			String tjzView = null;
			String gxmc = null;
			String gxlx = null;
			String gxz = null;
			String gxzgs = null;// ��ϵֵ��ʽ
			String zqlx = null;

			sqts = map.get("sqts");
			xn = map.get("xn");
			tjz = map.get("tjz");
			gxmc = map.get("gxmc");
			gxlx = map.get("gxlx");
			gxz = map.get("gxz");
			gxzgs = map.get("gxzgs");
			zqlx = map.get("zqlx");
			if (sqts != null && !sqts.trim().equals("")) {
				xnView = xn;
				if (zqlx != null && zqlx.equals("3")) {
					xnView = getZhcpTjxmMc(xn);
				} else {
					if (xnView != null && !xnView.trim().equals("")) {// ѧ��
						for (HashMap<String, String> xq : xqList) {
							xnView = xnView.replaceAll(
									CHAR_FH + xq.get("xqdm"), "ѧ��"
											+ xq.get("xqmc") + "ѧ��");// ѧ����������ʾ
							xnView = xnView.replaceAll(CHAR_DH, "��");
						}
					} else {
						xnView = "";
					}
				}
				sqts = sqts.replaceAll("\\$\\{xn\\}", xnView);
				if (gxmc == null) {
					gxmc = "";
				}
				sqts = sqts.replaceAll("\\$\\{gxmc\\}", gxmc);
				tjzView = tjz;
				if (tjzView == null) {
					tjzView = "";
				} else {
					if (gxlx != null) {
						if (gxlx.equals(FLAG_INPUT_NUM)) {// �����ı���
							// ��������
						} else if (gxlx.equals(FLAG_SELECT) && gxz != null) {// ������
							gxz = getGxzGsh(gxzgs, gxz);// ���ݹ�ϵֵ��ʽ����ʽ����ϵֵ��ת��Ϊ���߷ָ��ʽ
							String[] gxzs = gxz.split(CHAR_SX_REG);
							if (gxzs != null) {
								for (int i = 0; i < gxzs.length; i = i + 2) {
									String name = gxzs[i];
									String value = null;
									if (gxzs[i + 1] != null) {
										value = gxzs[i + 1];
									}
									if (tjzView.equals(name)) {
										tjzView = value;
										break;
									}
								}
							}
						} else if ((gxlx.equals(FLAG_CHECKBOX) || gxlx
								.equals(FLAG_CHECKBOX_DIV))
								&& gxz != null&& !gxz.trim().equals("")) {// checkbox
							gxz = getGxzGsh(gxzgs, gxz);// ���ݹ�ϵֵ��ʽ����ʽ����ϵֵ��ת��Ϊ���߷ָ��ʽ
							map.put("gxz", gxz);
							String[] gxzs = gxz.split(CHAR_SX_REG);
							if (gxzs != null && gxzs.length > 0) {
								for (int i = 0; i < gxzs.length; i = i + 2) {
									String name = gxzs[i];
									String value = name;
									if (gxzs.length > i+1 && gxzs[i + 1] != null) {
										value = gxzs[i + 1];
									}
									tjzView = tjzView.replaceAll(name, value);// ����
									tjzView = tjzView.replaceAll(CHAR_DH, "��");// ����
								}
							}
						}
					}
				}
				sqts = sqts.replaceAll("\\$\\{tjz\\}", tjzView);
				map.put("sqts", sqts);
			}
		}
		return tjszGlList;
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-27 ����09:32:08
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */

	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		return dao.delDeal(xmdm, tjdm);
	}

	/**
	 * 
	 * @����:���±���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-27 ����09:32:08
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveOrUpdate(String xmdm, List<XmwhTjszForm> list)
			throws Exception {
		return dao.runDeal(xmdm, list);

	}

	/**
	 * 
	 * @����:����������ͨ�������ȡ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-21 ����03:43:17
	 * @�޸ļ�¼:
	 * @param dm
	 * @return String ��������
	 * @throws
	 */
	public String getZhcpTjxmMc(String dm) {
		String mc = "";
		if (dm == null) {
			return mc;
		}
		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao()
				.getZhcpTjxm();// �۲�������Ŀ
		if (zhcpTjxmList != null && zhcpTjxmList.size() > 0) {
			for (HashMap<String, String> map : zhcpTjxmList) {
				if (dm.equals(map.get("dm"))) {
					mc = map.get("mc");
				}
			}
		}
		return mc;
	}
}
