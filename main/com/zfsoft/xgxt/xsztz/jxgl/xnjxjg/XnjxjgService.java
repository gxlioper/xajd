/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-27 ����04:23:43 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-27 ����04:23:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxjgService extends SuperServiceImpl<XnjxjgForm, XnjxjgDao>{
	
	private XnjxjgDao dao = new XnjxjgDao();
	
	
	
	public boolean runUpdate(XnjxjgForm form) throws Exception {
		return dao.runUpdate(form);
	}
	
	public boolean runInsert(XnjxjgForm form)throws Exception {
		return dao.runInsert(form);		
	}
	
	public boolean runDel(XnjxjgForm form)throws Exception {
		return dao.runDel(form);
	}
	
	/** 
	 * @����:���ģ������ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:41:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runDelforjg(XnjxjgForm form) throws Exception {
		return dao.runDelForJg(form);
	}
	
	/** 
	 * @����:���ģ����������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:42:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runInsertForjg(XnjxjgForm form) throws Exception {
		return dao.runInsertForjg(form);
	}
	
	/** 
	 * @����:���ģ�����ڻ�ȡ������Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:42:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getjginfo(XnjxjgForm form) throws Exception {
		return dao.getJginfo(form);
	}
	
	/** 
	 * @����:��ȡ��������Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:44:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getJg(XnjxjgForm form) throws Exception {
		return dao.getJg(form);
	}
	
	/** 
	 * @����:��ȡ���������Ŀ��Ϣ�����ڽ��ģ����޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:45:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh,String xmdm){
		return dao.getYiShen(xh,xmdm);
	}
	
	
	 
}
