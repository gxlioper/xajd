/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����04:17:35 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-18 ����04:17:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	private JcszDao dao = new JcszDao();
	
	/**
	 * 
	 * @����: ��ȡ���ر���Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:01:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getOneKgzt(){
		return dao.getOneKgzt();
	}
	
	public JcszForm getModel() throws Exception{
		return dao.getModel(new JcszForm());
	}
	
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:45:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> splCx() throws Exception{
		return dao.splCx();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���ÿ���״̬
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-18 ����05:03:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kgzt
	 * @param spl
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean setupKgsz(String kgzt , String spl,String xjxn) throws Exception{
		return dao.setupKgsz(kgzt, spl,xjxn);
	}
}
