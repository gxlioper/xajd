/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-8 ����11:18:42 
 */  
package com.zfsoft.xgxt.xsxx.xygl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣnew
 * @�๦������: У�ѹ��� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2015-9-8 ����11:18:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyglService extends SuperServiceImpl<XyglForm, XyglDao> {
	
	private XyglDao dao = new XyglDao();
	
	public XyglService() {
		super.setDao(dao);
	}
	
	/**
	 * У�ѹ�����Ϣ��ѯ������У�����ϣ�
	 */
	public List<HashMap<String, String>> getXyglList(XyglForm model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}
	
	/**
	 * ɾ��
	 */
	public boolean delData(String keys) throws Exception {
		return dao.delData(keys);
	}
	
	/**
	 * �޸�
	 */
	public boolean updateRecord(XyglForm myForm,
			HashMap<String, String> valueMap,
			HashMap<String, String> xsfzxxValueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// ����
		String jg = valueMap.get("jg");
		HashMap<String, String> jgMap = getSsx(jg);
		valueMap.put("jgs", jgMap.get("sheng"));
		valueMap.put("jgshi", jgMap.get("shi"));
		valueMap.put("jgx", jgMap.get("xian"));

		boolean result = dao.updateInfo(valueMap);
		if (result) {
			result = dao.updateInfoXsfzxx(xsfzxxValueMap);
		}

		return result;
	}

	/*
	 * ʡ���أ�����ֶα��档����ԭ�б��ֶ����������ԡ��¿���ѧ����Ϣ��ɾ�Ĳ�������˵ȹ��ܣ�����ֶ�δʹ��
	 */
	private HashMap<String, String> getSsx(String dm) {
		HashMap<String, String> ssx = new HashMap<String, String>();
		String sheng = "";
		String shi = "";
		String xian = "";
		if (dm != null && !dm.trim().equals("") && dm.length() >= 6) {
			String tmp0 = dm.substring(0, 2);
			sheng = tmp0 + "0000";
			String tmp1 = dm.substring(2, 4);
			if (!tmp1.equals("00")) {
				shi = tmp0 + tmp1 + "00";
			}
			String tmp2 = dm.substring(4, 6);
			if (!tmp2.equals("00")) {
				xian = dm;
			}
		}
		ssx.put("sheng", sheng);
		ssx.put("shi", shi);
		ssx.put("xian", xian);
		return ssx;
	}
	
	/**
	 * ����ѧ�Ų�ѯ��Ϣ
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		HashMap<String, String> map = dao.getXsxxByXhForUpdate(xh);
		return map;
	}
	
	/**
	 * �ж�ѧ���Ƿ���У�ѹ�����
	 */
	public String chkStuIsExistsXYGL(String xh) {
		return dao.chkStuIsExistsXYGL(xh);
	}
	
	
	public boolean saveRecord(XyglForm myForm,
			HashMap<String, String> valueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// ʡ���أ�����ֶα��档����ԭ�б��ֶ����������ԡ��¿���ѧ����Ϣ��ɾ�Ĳ�������˵ȹ��ܣ�����ֶ�δʹ��
		// ��Դ��
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		boolean result = dao.saveInfo(valueMap);
		if (result) {
			result = dao.saveXsqtxx(myForm);
		}
		return result;
	}
}
