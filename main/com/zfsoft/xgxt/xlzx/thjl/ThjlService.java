/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:12:03 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ThjlService extends SuperServiceImpl<ThjlForm, ThjlDao> {
	
	private ThjlDao dao = new ThjlDao();
	
	public ThjlService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:������ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {
		
		return dao.getThjlListById(id);
	}
	/**
	 * 
	 * @����:������ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {
		
		return dao.getThjlListByXh(xh);
	}
	/**
	 * ������̸����¼��Ϣ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(ThjlForm model)
			throws Exception {
		
		return dao.saveThjlInfo(model);
	}
	
	/**
	 * 
	 * @����:ɾ��̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		
		return dao.delThjlById(id);
	}
	
	/**
	 * 
	 * @����:����ID�޸�̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	
	public boolean updateThjlInfo(ThjlForm model) throws Exception{
		
		return dao.updateThjlInfo(model);
	}

	/** 
	 * @����:ͨ��ְ����ȡ�ý�ʦ��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����10:50:17
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}

	/** 
	 * @����:ȡ�ý�ʦ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����11:49:46
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(ThjlForm myForm) {
		return dao.getJsInfoList(myForm);
	}
}
