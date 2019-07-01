/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-18 ����09:30:58 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.xyrssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.rssz.RsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ѧԺ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-18 ����09:30:58 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyrsszService extends SuperServiceImpl<XyrsszForm,XyrsszDao>{
	
	private XyrsszDao dao = new XyrsszDao();

	public XyrsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ѧԺ������ѯ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-19 ����10:24:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRsszList (XyrsszForm t,User user)
		throws Exception {
		
		String rskzfw = t.getRsfpfs();
		
		if (StringUtil.isNull(rskzfw)){
			return null;
		}

		/*ѧԺ*/
		if (rskzfw.equals(Constants.RSKZFW_XY)) {
			return dao.getRsszList(t,user);
		}
		return null;
	}
	
	/**
	 * @����: ������������ ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-20 ����02:48:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String setZzrs(XyrsszForm model, User user) throws Exception {
		String message = null;
		List<HashMap<String, String>> allList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		
		String[] guids = model.getGuids();
		String[] zzmes = model.getZzmes();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		
		String rskzfw = model.getRsfpfs();
		
		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				map = new HashMap<String, String>();
				map.put("zzme", zzmes[i]);
				 if (rskzfw.equals(Constants.RSKZFW_XY)) {// ѧԺ
					map.put("bmdm", xydms[i]);
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
			message = MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			message = MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}

		return message;
	}
	
	/**
	 * ��������������У�飬��������ͨ���������ȶ�
	 */
	private String zrsYz(XyrsszForm  myForm,List<HashMap<String, String>> allList) throws Exception{
		
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		
		/*��ǰѧ��*/
		String currXn = csszModel.getXn();
		
		/*�������䷽ʽ��ʵ�ʱ������Ǹ������Ի��ģ�������ѧԺ�������ò��ö�����ν�����ҾͰ����ϵ�����������*/
		String rskzfw = myForm.getRsfpfs();
		
		List<HashMap<String, String>> shtgrsList = getShtgrs(rskzfw, myForm.getXmdm(),currXn);
		
		/*��������ͨ����������ֱ�ӷ���*/
		if (shtgrsList == null || shtgrsList.size() == 0) {
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
	 * ��ѯ����ͨ��������
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,String xn) {
		return dao.getZzmeByXy(xmdm, xn);
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
	public List<HashMap<String,String>> getJxjze(XyrsszForm model,User user)throws Exception{
		return dao.getJxjze(model,user);
	}
	
	public int getYszrs(XyrsszForm model) throws Exception {
		return dao.getYszrs(model);
	}
}
