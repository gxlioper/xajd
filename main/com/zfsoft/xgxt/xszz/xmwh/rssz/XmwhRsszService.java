/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhRsszService extends
		SuperServiceImpl<XmwhRsszForm, XmwhRsszDao> {

	private XmwhRsszDao dao = new XmwhRsszDao();

	public XmwhRsszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-22 ����05:08:13
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String blsz(XmwhRsszForm myForm, List<XmwhRsszForm> list)
			throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRskzfw();
		String bmdmFlag = "";
		String zrs = null;
		String bl = myForm.getBl();
		if (list == null || list.size() == 0) {//δ��ѡ��¼�����������ñ�����������
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
				} else if(rskzfw.equals(Constants.RSKZFW_SY)){//��Ժ
					bmdmFlag = "xydm";
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
				if(allList != null && allList.size() > 0){
					for (XmwhRsszForm xmwhRsszForm : list) {
						String bmdm = xmwhRsszForm.getBmdm();
						if(bmdm == null){
							continue;
						}
						for (HashMap<String, String> map1 : allList) {
							if(bmdm.equals(map1.get("xydm"))){
								String zrsTemp = map1.get("zrs");
								try {
									xmwhRsszForm.setZzrs( "" + Math.round(Double.parseDouble(myForm.getBl())
													* Double.parseDouble(zrsTemp) * 0.01));
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

	// ��������������У�飬��������ͨ���������ȶ�
	private String zrsYzDg(XmwhRsszForm myForm, List<XmwhRsszForm> list)  throws Exception{

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (XmwhRsszForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzrs", form.getZzrs());
			map.put("bmdm", form.getBmdm());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	// ��������������У�飬��������ͨ���������ȶ�
	private String zrsYz(XmwhRsszForm myForm,
			List<HashMap<String, String>> allList) throws Exception{
		String rskzfw = myForm.getRskzfw();
		XszzSqshService xszzSqshService = new XszzSqshService();
		List<HashMap<String, String>> shtgrsList = xszzSqshService.getShtgrs(
				rskzfw, myForm.getXmdm(), myForm.getXn(), myForm.getXq());
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
				} else if (rskzfw.equals(Constants.RSKZFW_SY)) {// ��Ժ
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
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
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
					name = dao.getBjmc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					name = dao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					name = dao.getZymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					name = dao.getXymc(bmdm1);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					name = dao.getZymc(bmdm1);
				} else if(rskzfw.equals(Constants.RSKZFW_SY)){//��Ժ
					name = dao.getSymc(bmdm1);
				}
				cw += name;

//				cw += ":" + cwMap.get("yrs");
//				cw += "��" + cwMap.get("tgrs");
			}
			cw += "]";
		}
		return cw;
	}

	/**
	 * 
	 * @����:�������ã��ȼ����������ֱ�ӵ��ñ������÷���
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-29 ����11:03:06
	 * @�޸ļ�¼:
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String fpsz(XmwhRsszForm myForm, List<XmwhRsszForm> list, String zme,String rskznj)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		if (zme == null || zme.trim().equals("")) {//������ʽ��������
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), "",rskznj);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list);
		} else {//�����ʽ��������
			int zrs = dao.getZrs(myForm,rskznj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setBl(df.format(bl) + "");
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), iZme + "",rskznj);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null);
		}
	}

	/**
	 * 
	 * @����:������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-22 ����05:08:13
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String setZzrs(XmwhRsszForm model) throws Exception {
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

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzrs", zzrss[i]);
				map.put("bmdm", xydms[i]);
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
	 * 
	 * @����:������Ŀ���룬��ȡ�����õ���������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����10:14:13
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             XmwhJdszForm ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getRssz(String xmdm) throws Exception {
		return dao.getRssz(xmdm);
	}

	/**
	 * 
	 * @����:������Ŀ����ɾ����¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-2 ����03:03:00
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		return dao.runDeleteByXmdm(xmdm);
	}

	/**
	 * 
	 * @����:�ж��Ƿ���������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public boolean getRsszCount(String xmdm) throws Exception {
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	/**
	 * 
	 * @����:�ж��Ƿ���������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public HashMap<String, String> getRsszData(String xmdm, String xh)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		HashMap<String, String> map = xmwhDao.getDataById(xmdm);
		String rskzfw = map.get("rskzfw");
		if (rskzfw == null) {
			throw new SystemException("��Ŀ����Ϊ�ջ�δ�����������Ʒ�Χ���ã�");
		}
		HashMap<String, String> rsszOne = dao.getRsszOne(xmdm, xh, rskzfw);
		if (rsszOne != null) {
			map.put("zzrs", rsszOne.get("zzrs"));
			map.put("xn", rsszOne.get("xn"));
			map.put("xq", rsszOne.get("xq"));
		} else {
			// ////
		}

		return map;
	}

	/**
	 * 
	 * @����:��ѯ��Ŀ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public int getYszrs(XmwhRsszForm model) throws Exception {
		return dao.getYszrs(model);
	}

	/**
	 * 
	 * @����:��ѯ����ѧԺ
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-28 ����02:48:35
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSyxy() throws Exception {

		List<HashMap<String, String>> result = dao.getSyxy();
		return result;
	}
	
	/**
	 * 
	 * @����:��ȡ���а���ѧ�����꼶
	 * @���ߣ�ligl
	 * @���ڣ�2013-7-5 ����08:37:45
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String>  getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	public List<HashMap<String, String>> getSyList(){
		List<HashMap<String, String>> result = null;
		result = dao.getSyList();
		return result;
	}
	/**
	 * 
	 * @����:������Ŀ�����ѯ�������䷽ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-22 ����11:48:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getRsfpfs(String xmdm){
		return dao.getRsfpfs(xmdm);
	}
	

}
