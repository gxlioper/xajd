/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����10:24:32 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�����������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����10:24:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwJcszService extends SuperServiceImpl<ZyfwJcszForm,ZyfwJcszDao>{

	/**
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��4�� ����2:02:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ZyfwJcszForm �������� 
	 * @throws Exception
	 */
	public ZyfwJcszForm getModel() throws Exception {
		
		return dao.getModel();
	}

	/** 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��4�� ����5:14:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String [] �������� 
	 * @throws Exception
	 */
	public String[] getSqShKg() throws Exception{
		
		return dao.getSqShKg();
	}
	
}
