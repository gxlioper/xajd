/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.java2d.loops.DrawGlyphListAA.General;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import common.Globals;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ŀά��-��������
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:07:42
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class RsszService extends
		SuperServiceImpl<RsszModel, RsszDao> {
	
	public static final String czfs = "xyrssz";

	private RsszDao dao = new RsszDao();

	public RsszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String blsz(RsszModel myForm, List<RsszModel> list,User user)
			throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRsfpfs();
		String bmdmFlag = "";
		String zrs = null;
		String fpbl = myForm.getFpbl();
		CsszService csszService = new CsszService();
		String rsjsfs = csszService.getCsz("rsjsfs");	//ytjrs:���ύ���� ,zrs:��������. ������Ŀ�����������ü���ȡֵ�С�
		if (list == null || list.size() == 0) {
			
			myForm.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String, String>> allList = getRsszList(myForm,user);
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
				} else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// ������
					bmdmFlag = "cpzdm";
				}
			}
			for (HashMap<String, String> map : allList) {
				map.put("bmdm", map.get(bmdmFlag));
				if (fpbl != null) {
					zrs = map.get(rsjsfs);
					try {
						if(Base.xxdm.equals(Globals.XXDM_ZJDX)){  //�㽭��ѧҪ����С�������λ
							map.put("zzme", ""+ (double)Math.round(Double.parseDouble(fpbl) * Double.parseDouble(zrs))/100);
						}else{
							map.put("zzme", ""
									+ Math.round(Double.parseDouble(fpbl)
											* Double.parseDouble(zrs) * 0.01));
						}
						
					} catch (Exception e) {
						logger.error("��������fpbl=" + fpbl + ";zrs=" + zrs);
					}
				}
			}

			message = zrsYz(myForm, allList);// ��������������У�飬��������ͨ���������ȶ�
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.PJPY_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			message = zrsYzDg(myForm, list);// ��������������У�飬��������ͨ���������ȶ�
			if (message != null && !message.equals("")) {
				throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS, message);
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
	private String zrsYzDg(RsszModel myForm, List<RsszModel> list)  throws Exception{

		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (RsszModel form : list) {
			map = new HashMap<String, String>();
			map.put("zzme", form.getZzme());
			map.put("bmdm", form.getBmdm());
			map.put("zzme", form.getZzme());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}

	// ��������������У�飬��������ͨ���������ȶ�
	private String zrsYz(RsszModel myForm,
			List<HashMap<String, String>> allList) throws Exception{
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String currXn = csszModel.getXn();// ��ǰѧ��
		String currXq = csszModel.getXq();// ����ѧ��
		
		String rskzfw = myForm.getRsfpfs();
		
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(
				rskzfw, myForm.getXmdm(),currXn,currXq);

		//����
//		shtgrsList = new ArrayList<HashMap<String,String>>();
//		HashMap<String, String> mapTemp = new HashMap<String, String>();
//		mapTemp.put("tgrs","102");
//		mapTemp.put("cpzdm","20012402");
//		shtgrsList.add(mapTemp);
		
		if (shtgrsList == null || shtgrsList.size() == 0) {// ��������ͨ����������ֱ�ӷ���
			return null;
		}

		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();

		String tgrs = null;// ͨ������
		String bmdm = null;// ���Ŵ��룬
		String zzme = null;// ��������
		for (HashMap<String, String> map : allList) {// ѭ����ѧԺ/רҵ/�༶
			zzme = map.get("zzme");
			bmdm = map.get("bmdm");
			if (zzme == null || zzme.trim().length() == 0 || bmdm == null) {// �������õ�������Ϊ�գ�����
				continue;
			}
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(zzme);// ��������ʽ����
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
							cwMap.put("yrs", zzme);
							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					if (bmdm.equals(shtgrsMap.get("xydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					if (bmdm.equals(shtgrsMap.get("zydm"))
							&& map.get("nj").equals(shtgrsMap.get("nj"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}

				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					if (bmdm.equals(shtgrsMap.get("zydm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
							cwMap.put("tgrs", tgrs);
							cwList.add(cwMap);
						}
					}
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// ������
					if (bmdm.equals(shtgrsMap.get("cpzdm"))) {// /
						if (iZzrs < iTgrs) {
							HashMap<String, String> cwMap = map;
							cwMap.put("yrs", zzme);

							map.put("zzme", iTgrs + "");
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
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// ������
					name = dao.getCpzmc(bmdm1);
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
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String fpsz(RsszModel myForm, List<RsszModel> list, String zme,String rsfpnj,User user)
			throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
		if(Base.xxdm.equals("10704")){
			String sfyxgb = myForm.getSfyxgb();
			String xmdm = myForm.getXmdm();
			xmwhDao.updateSfYxgb(xmdm, sfyxgb);
		}
		if (zme == null || zme.trim().equals("")) {
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), "",rsfpnj);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, list,user);
		} else {
			int zrs = dao.getZrs(myForm,rsfpnj);
			int iZme = Integer.parseInt(zme);
			double bl = iZme * 100.0 / zrs;
			if (bl > 100) {
				bl = 100;
				iZme = zrs;
			}
			DecimalFormat df = new DecimalFormat("##0.##");
			myForm.setFpbl(df.format(bl) + "");
			boolean flag = xmwhDao.setZme(myForm.getXmdm(), iZme + "",rsfpnj);
			if (!flag) {
				logger.error("�������ʧ�ܣ�");
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return blsz(myForm, null,user);
		}
	}

	/**
	 * 
	 * @����:������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String setZzrs(RsszModel model, User user) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;

		String[] guids = model.getGuids();
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] cpzdms = model.getCpzdms();
		String[] fpbls = model.getFpbls();
		String rskzfw = model.getRsfpfs();
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzme", zzmes[i]);
				
				if (rskzfw.equals(Constants.RSKZFW_BJ)) {// �༶
					map.put("bmdm", bjdms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// �꼶+ѧԺ
					map.put("bmdm", xydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// �꼶 + רҵ
					map.put("bmdm", zydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					map.put("bmdm", xydms[i]);
				} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// רҵ
					map.put("bmdm", zydms[i]);
				}else if (rskzfw.equals(Constants.RSKZFW_CPZ)) {// ������
					map.put("bmdm", cpzdms[i]);
				}
				if (njs != null && njs[i] != null) {
					map.put("nj", njs[i]);
				}
				allList.add(map);
			}
		}

		message = zrsYz(model, allList);
		if (message != null && !message.equals("")) {
			throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS, message);
		}
		boolean flag = dao.runZzme(model,user);
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
	 * @���ڣ�2013-8-5 ����11:11:11
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
	 * @���ڣ�2013-8-5 ����11:11:11
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
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public boolean getRsszCount(String xmdm) throws Exception {
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	/**
	 * 
	 * @����:�ж��Ƿ���������������----���ӿڵ��� 
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public HashMap<String, String> getRsszData(String xmdm, String xh)
			throws Exception {
//		XmwhDao xmwhDao = new XmwhDao();
//		HashMap<String, String> map = xmwhDao.getDataById(xmdm);
//		String rsfpfs = map.get("rsfpfs");
//		if (rsfpfs == null) {
//			throw new SystemException("��Ŀ����Ϊ�ջ�δ�����������Ʒ�Χ���ã�");
//		}
//		HashMap<String, String> rsszOne = dao.getRsszOne(xmdm, xh, rsfpfs);
//		if (rsszOne != null) {
//			map.put("zzme", rsszOne.get("zzme"));
//		} else {
//			// ////
//		}
//
//		return map;
		return null;
	}

	/**
	 * 
	 * @����:��ѯ��Ŀ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public int getYszrs(RsszModel model) throws Exception {
		return dao.getYszrs(model);
	}

	/**
	 * 
	 * @����:��ѯ����ѧԺ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
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
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String>  getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	/**
	 * 
	 * @����:�����������ã�ѧУ��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-13 ����04:43:56
	 * @�޸ļ�¼: 
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String setZzrsForXx(RsszModel model,User user) throws Exception {
		String message = null;

		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		String currXn = csszModel.getXn();// ��ǰѧ��
		String currXq = csszModel.getXq();// ����ѧ��
		
		//��֤��ͨ������
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(
				Constants.RSKZFW_XX, model.getXmdm(),currXn,currXq);

		//����
//		shtgrsList = new ArrayList<HashMap<String,String>>();
//		HashMap<String, String> mapTemp = new HashMap<String, String>();
//		mapTemp.put("tgrs","102");
//		mapTemp.put("xx","xx");
//		shtgrsList.add(mapTemp);
		
		if (shtgrsList != null && shtgrsList.size()>0) {//
			HashMap<String, String> map = shtgrsList.get(0);
			int iZzrs = 0;
			try {
				iZzrs = Integer.parseInt(model.getZzmes()[0]);// ��������ʽ����
			} catch (Exception e) {
			}		
			String tgrs = map.get("tgrs");
			int iTgrs = 0;
			try {
				iTgrs = Integer.parseInt(tgrs);// ͨ��������ʽ����
			} catch (Exception e) {
			}
			if (iTgrs > iZzrs) {// 
				String[] params = {"("+iZzrs + ")","("+iTgrs + ")"};
					throw new SystemException(MessageKey.PJPY_RSSZ_ZZRSTS_XX, params);
			}
		}
		
		boolean flag = dao.runZzmeForXx(model);
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

	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getRsszList(RsszModel t,User user)
			throws Exception {
		String rskzfw = t.getRsfpfs();
		
		if (StringUtil.isNull(rskzfw)){
			return null;
		}
		XmwhDao xmwhDao = new XmwhDao();
		String pycc = xmwhDao.getPycc(t.getXmdm());
		t.setPycc(pycc);
		
		// �༶
		if (rskzfw.equals(Constants.RSKZFW_BJ)) {
			return dao.getBjrsList(t,user);
		} 
		
		// �꼶ѧԺ
		if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
			return dao.getNjxyrsList(t,user);
		} 
		
		// �꼶רҵ
		if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
			return dao.getNjzyrsList(t,user);
		}
		
		// ѧԺ
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getXyrsList(t,user);
		}
		
		
		// ������
		if (rskzfw.equals(Constants.RSKZFW_CPZ)) {
			return dao.getCpzrsList(t,user);
		}
		
		// ѧУ
		if (rskzfw.equals(Constants.RSKZFW_XX)) {
			return dao.getXxrsList(t,user);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ȡ�������䷽ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-17 ����02:54:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public Map<String, String> getRsfpfs(String xmdm){
		return dao.getRsfpfs(xmdm);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�����Ի���ѧ����������֤
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-19 ����09:29:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param user
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszModel model,User user) throws Exception{
		return dao.getJxjze(model,user);
	}

	
	

}
