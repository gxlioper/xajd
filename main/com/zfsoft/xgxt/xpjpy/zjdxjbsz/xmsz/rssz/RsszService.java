/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����09:29:13 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszModel;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgService;

import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-1 ����09:29:13 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RsszService extends SuperServiceImpl<RsszForm,RsszDao>{
	public static final String czfs = "xyrssz";

	private RsszDao dao = new RsszDao();

	public RsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ��ѧ����������֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����11:17:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxjze(RsszForm model,User user)throws Exception{
		return dao.getJxjze(model,user);
	}
	
	/**
	 * @����: ��ȡ���а���ѧ�����꼶
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����01:49:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = dao.getNj();
		return result;
	}
	
	/**
	 * @����: ������Ŀ��ͬ���������䷽ʽ����ȡ��ͬ��������������
	 * 		    ����Ҫ�����ǹ���Ա�û���ݡ�����ѧԺ�û����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����02:03:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRsszList (RsszForm t,User user)
		throws Exception {
		/*�����Ŀ���������䷽ʽ�����ƣ�Rsfpfs*/
		String rskzfw = t.getRsfpfs();
		/*���ж�*/
		if (StringUtil.isNull(rskzfw)){
			return null;
		}
		/*������Ŀʱ�������䷽ʽΪ��ѧԺ��*/
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getXyrsList(t,user);
		}
		/*������Ŀʱ�������䷽ʽΪ���꼶+ѧԺ��*/
		if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
			return dao.getNjxyrsList(t,user);
		} 
		return null;
	}
	
	/**
	 * @����: ��ò�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����04:55:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm){
		return dao.getCsz(csdm);
	}
	
	/**
	 * @����: ��Ŀ�����õ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-2 ����09:36:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int getYszrs(RsszForm model) throws Exception {
		return dao.getYszrs(model);
	}

	/** 
	 * @����: �������ã��ȼ����������ֱ�ӵ��ñ������÷���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:09:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param list
	 * @param zme
	 * @param rsfpnj
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String fpsz(RsszForm myForm, List<RsszForm> list, String zme,
			String rsfpnj, User user) throws Exception {
		XmwhDao xmwhDao = new XmwhDao();
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
	 * @����: ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:18:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param object
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	private String blsz(RsszForm myForm, List<RsszForm> list,User user)
		throws Exception {
		String message = null;
		boolean flag = false;
		String rskzfw = myForm.getRsfpfs();
		String bmdmFlag = "";
		String zrs = null;
		String fpbl = myForm.getFpbl();
		CsszService csszService = new CsszService();
		/*ytjrs:���ύ���� ,zrs:��������. ������Ŀ�����������ü���ȡֵ�С�*/
		String rsjsfs = csszService.getCsz("rsjsfs");	
		if (list == null || list.size() == 0) {
			myForm.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String, String>> allList = getRsszList(myForm,user);
			if (rskzfw != null) {
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					bmdmFlag = "xydm";
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
			/*��������������У�飬��������ͨ���������ȶ�*/
			message = zrsYz(myForm, allList);
			flag = dao.runBlszAll(myForm, allList);
			if (message == null || message.equals("")) {
				message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
			} else {
				message = MessageUtil.getText(MessageKey.PJPY_RSSZ_ZZRSYZ,
						message);
			}

		} else {
			/*��������������У�飬��������ͨ���������ȶ�*/
			message = zrsYzDg(myForm, list);
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
	
	/**
	 * @����: ��������������У�飬��������ͨ���������ȶ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:48:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param allList
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	private String zrsYz(RsszForm myForm,List<HashMap<String, String>> allList) throws Exception{
		CsszService csszService = new CsszService();
		CsszForm CsszModel = csszService.getCsszModel();
		/*��ǰѧ��*/
		String currXn = CsszModel.getXn();
		/*�������䷽ʽ*/
		String rskzfw = myForm.getRsfpfs();
		/*����ѧ����ұ�ѧ����ͨ��������*/
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> shtgrsList = pjjgService.getShtgrs(rskzfw, myForm.getXmdm(),currXn);
		/*��������ͨ����������ֱ�ӷ���*/
		if (shtgrsList == null || shtgrsList.size() == 0) {
			return null;
		}
		List<HashMap<String, String>> cwList = new ArrayList<HashMap<String, String>>();
		/*����ͨ������*/
		String tgrs = null;
		/*���岿�Ŵ���*/
		String bmdm = null;
		/*������������*/
		String zzme = null;
		/*ѭ����ѧԺ*/
		for (HashMap<String, String> map : allList) {
			zzme = map.get("zzme");
			bmdm = map.get("bmdm");
			/*�������õ�������Ϊ�գ�����*/
			if (zzme == null || zzme.trim().length() == 0 || bmdm == null) {
				continue;
			}
			int iZzrs = 0;
			try {
				/*��������ʽ����*/
				iZzrs = Integer.parseInt(zzme);
			} catch (Exception e) {
				continue;
			}
			/*ѭ����ͨ����˵�����*/
			for (HashMap<String, String> shtgrsMap : shtgrsList) {
				tgrs = shtgrsMap.get("tgrs");
				int iTgrs = 0;
				try {
					/*ͨ��������ʽ����*/
					iTgrs = Integer.parseInt(tgrs); 
				} catch (Exception e) {
					continue;
				}
				/*��ͨ������Ϊ�գ�����*/
				if (tgrs == null || tgrs.trim().length() == 0) {
					continue;
				}
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					if (bmdm.equals(shtgrsMap.get("xydm"))) {// /
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
				if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					name = dao.getXymc(bmdm1);
				}
				cw += name;
			}
			cw += "]";
		}
		return cw;
	}
	
	/**
	 * @����: ��������������У�飬��������ͨ���������ȶ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����02:50:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param list
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	private String zrsYzDg(RsszForm myForm, List<RsszForm> list)  throws Exception{
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		for (RsszForm form : list) {
			map = new HashMap<String, String>();
			map.put("zzme", form.getZzme());
			map.put("bmdm", form.getBmdm());
			map.put("zzme", form.getZzme());
			map.put("nj", form.getNj());
			allList.add(map);
		}
		return zrsYz(myForm, allList);
	}
}
