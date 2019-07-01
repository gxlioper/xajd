package com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszDao;

public class KnsjcszbjpyService extends SuperServiceImpl<KnsjcszbjpyForm, KnsjcszbjpyDao> {

	private KnsjcszbjpyDao dao = new KnsjcszbjpyDao();

	public KnsjcszbjpyService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ѯ����������Ϣ
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-19 ����01:45:50
	 * @param model
	 * @return HashMap<String,String> ��������
	 * @throws
	 */

	public KnsjcszbjpyForm getModel(KnsjcszbjpyForm t) throws Exception {

		return dao.getModel();
	}

	/**
	 * 
	 * @����:��ѯ����������Ϣ(�޲���)
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-19 ����01:45:50
	 * @param model
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public KnsjcszbjpyForm getModel() throws Exception {

		return getModel(new KnsjcszbjpyForm());
	}

	/**
	 * �������������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJcsz(KnsjcszbjpyForm model) throws Exception {
		if("0".equals(model.getSqkg())){
			return dao.updateJcszSqkg(model);
		}
		boolean flag = false;
		flag = dao.deleteJcsz(model);
		if (flag) {
			if("10052".equals(Base.xxdm)){
				model.setKxzrdzb("1");
			}
			flag = dao.runInsert(model);

		}
		return flag;

	}

	/**
	 * 
	 * @����:��ȡ���а���ѧ�����꼶
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-5 ����08:37:45
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-12-9 ����04:05:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� ActionForward ��������
	 * @throws
	 */
	public String fpsz(KnsjcszbjpyForm myForm, List<KnsjcszbjpyForm> list, String zme,
			String rskznj) throws Exception {

		if (zme == null || zme.trim().equals("")) {// ������ʽ��������

			boolean flag = dao.setRsszcs("", rskznj, myForm);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list);
		} else {// �����ʽ��������
			int zrs = dao.getZrs(myForm, rskznj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setBl(df.format(bl) + "");
			boolean flag = dao.setRsszcs(zme, rskznj, myForm);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null);
		}
	}

	/**
	 * @��������������
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-9 ����05:25:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param list
	 * @return String ��������
	 * @throws
	 */
	private String blsz(KnsjcszbjpyForm myForm, List<KnsjcszbjpyForm> list) throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRskzfw();
		String bmdmFlag = "";
		String zrs = null;
		String bl = myForm.getBl();
		if (list == null || list.size() == 0) {
			List<HashMap<String, String>> allList = null;
			if (rskzfw != null) {
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
					bmdmFlag = "bjdm";
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					bmdmFlag = "zydm";
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					bmdmFlag = "xydm";
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					bmdmFlag = "zydm";
				}
			}
			allList = getAllList(myForm);
			for (HashMap<String, String> map : allList) {
				map.put("bmdm", map.get(bmdmFlag));
				if (bl != null) {
					zrs = map.get("zrs");
					try {
						map.put("zzrs", ""
								+ Math.round(Double.parseDouble(bl)
										* Double.parseDouble(zrs) * 0.01));
					} catch (Exception e) {
						logger.error("��������bl=" + bl + ";zrs=" + zrs);
					}
				}
			}

			message = zrsYz(myForm, allList);// ��������������У�飬��������ͨ���������ȶ�
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.XSZZ_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			if (rskzfw != null && rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
				List<HashMap<String, String>> allList = getAllList(myForm);
				if (allList != null && allList.size() > 0) {
					for (KnsjcszbjpyForm KnsjcszbjpyForm : list) {
						String bmdm = KnsjcszbjpyForm.getBmdm();
						if (bmdm == null) {
							continue;
						}
						for (HashMap<String, String> map1 : allList) {
							if (bmdm.equals(map1.get("xydm"))) {
								String zrsTemp = map1.get("zrs");
								try {
									KnsjcszbjpyForm
											.setZzrs(""
													+ Math
															.round(Double
																	.parseDouble(myForm
																			.getBl())
																	* Double
																			.parseDouble(zrsTemp)
																	* 0.01));
								} catch (Exception e) {
								}
							}
						}
					}
				}
			}
			message = zrsYzDg(myForm, list);// ��������������У�飬��������ͨ���������ȶ�
			if (message != null && !message.equals("")) {
				throw new SystemException(MessageKey.XSZZ_RSSZ_ZZRSTS, message);
			}
			flag = dao.runBlsz(myForm, list);
			if (flag) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return message;
	}

	/**
	 * @����:��������������У�飬ͨ����������������������ͨ������Ϊ׼�����δ��ѡ��¼���������ã�
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-10 ����02:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	private String zrsYz(KnsjcszbjpyForm myForm, List<HashMap<String, String>> allList)
			throws Exception {
		String rskzfw = myForm.getRskzfw();
		KnsrdbjpyService knsrdbjpyService = new KnsrdbjpyService();
		List<HashMap<String, String>> shtgrsList = knsrdbjpyService.getShtgrs(
				rskzfw, myForm.getXn(), myForm.getXq());
		if (shtgrsList.size() == 0) {// ��������ͨ����������ֱ�ӷ���
			return null;
		}

		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();

		String tgrs = null;// ͨ������
		String bmdm = null;// ���Ŵ��룬
		String zzrs = null;// ��������
		for (HashMap<String, String> map : allList) {// ѭ����ѧԺ/רҵ/�༶
			zzrs = map.get("zzrs");
			bmdm = map.get("bmdm");
			if (zzrs == null || zzrs.trim().length() == 0 || bmdm == null) {// �������õ�������Ϊ�գ�����
				continue;
			}
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(zzrs);// ��������ʽ����
			} catch (Exception e) {
				continue;
			}
			for (HashMap<String, String> shtgrsMap : shtgrsList) {// ѭ����ͨ����˵�����
				tgrs = shtgrsMap.get("tgrs");
				int iTgrs = 0;
				try {
					iTgrs = Integer.parseInt(tgrs);// ͨ��������ʽ����
				} catch (Exception e) {
					continue;
				}
				if (tgrs == null || tgrs.trim().length() == 0) {// ��ͨ������Ϊ�գ�����
					continue;
				}
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
					if (bmdm.equals(shtgrsMap.get("bjdm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);
							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					if (bmdm.equals(shtgrsMap.get("xydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					if (bmdm.equals(shtgrsMap.get("zydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}

				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					if (bmdm.equals(shtgrsMap.get("zydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzrs);

							map.put("zzrs", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				}
			}
		}

		String cw = "";
		if (cwList.size() > 0) {
			cw += "[";
			boolean flag = false;
			for (HashMap<String, String> cwMap : cwList) {
				String name = "";
				String bmdm1 = cwMap.get("bmdm");

				if (flag) {
					cw += ",";
				} else {
					flag = true;
				}
				XmwhRsszDao xmwhDao = new XmwhRsszDao();
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
					name = xmwhDao.getBjmc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					name = xmwhDao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					name = xmwhDao.getZymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					name = xmwhDao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					name = xmwhDao.getZymc(bmdm1);
				}
				cw += name;

				// cw += ":" + cwMap.get("yrs");
				// cw += "��" + cwMap.get("tgrs");
			}
			cw += "]";
		}
		return cw;
	}

	/**
	 * @����:��������������У�飬ͨ����������������������ͨ������Ϊ׼(����ѹ�ѡ��¼����������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-10 ����02:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	private String zrsYzDg(KnsjcszbjpyForm myForm, List<KnsjcszbjpyForm> list)
			throws Exception {

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (KnsjcszbjpyForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzrs", form.getZzrs());
			map.put("bmdm", form.getBmdm());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	/**
	 * @����:�޸��������Ʒ�Χ,�����ض�Ӧ��Χ�Ƿ���������
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����01:38:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return boolean ��������
	 * @throws
	 */
	public boolean updateRskzfw(KnsjcszbjpyForm myForm) throws Exception {
		boolean result = dao.updateRskzfw(myForm);
		if (result) {
			dao.deleteRssz();
		}
		return result;
	}

	/**
	 * @����:��ȡ����������
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����02:58:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return String ��������
	 * @throws
	 */
	public String getYszrs(KnsjcszbjpyForm myForm) {
		String yszrs = dao.getYszrs(myForm);
		if (StringUtil.isNull(yszrs)) {
			yszrs = "0";
		}
		return yszrs;
	}

	/**
	 * @����:������������
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-11 ����03:50:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return String ��������
	 * @throws
	 */
	public String setZzrs(KnsjcszbjpyForm model) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;

		String[] guids = model.getGuids();
		String[] zzrss = model.getZzrss();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] bls = model.getBls();

		String rskzfw = model.getRskzfw();
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzrs", zzrss[i]);
				String bmdm = "";
				if (rskzfw != null) {
					if (rskzfw.equals(Constants.RSKZFW_BJ)) {
						bmdm = bjdms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
						bmdm = zydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
						bmdm = zydms[i];
					}
				}
				map.put("bmdm", bmdm);
				if (njs != null && njs[i] != null) {
					map.put("nj", njs[i]);
				}
				allList.add(map);
			}
		}

		message = zrsYz(model, allList);
		if (message != null && !message.equals("")) {
			throw new SystemException(MessageKey.XSZZ_RSSZ_ZZRSTS, message);
		}
		boolean flag = dao.runZzrs(model);
		if (flag) {
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				// message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS,
				// message);
			}
		} else {
			message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}

		return message;
	}

	/**
	 * @����:��֤�Ƿ�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-11 ����04:49:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return boolean ��������
	 * @throws
	 */
	public boolean checkRsSfsz(KnsjcszbjpyForm myForm) {
		String num = dao.getYszrsNum(myForm);
		boolean result = false;
		if ("0".equalsIgnoreCase(num)) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
}
