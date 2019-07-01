/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khxmgl.KhxmglForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-11 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhnrglService extends SuperServiceImpl<KhnrglForm, KhnrglDao> {
	private static final String SCZT = "1";//1:ɾ��
	private KhnrglDao dao = new KhnrglDao();
	
	public boolean isHave(KhnrglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @����:�������ݱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editKhnr(KhnrglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xssx=dao.getXssx(model.getKhbid());
			String zbmxid = UniqID.getInstance().getUniqIDHash();
			model.setZbmxid(zbmxid);
			model.setXssx(xssx);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhnr(KhnrglForm t) throws Exception {
		return dao.getKhnr(t);
	}
	public List<HashMap<String, String>> getKhnrList(String khbid)
			throws Exception {
	return dao.getKhnrList(khbid);
	}
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:25:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhnrList() throws Exception {
		return dao.getKhnrList();
	}
	
	public List<HashMap<String, String>> getKhnrList(KhnrglForm model, User user)
	throws Exception{
		return null;
		
	}
	
	

	
	
	



}
