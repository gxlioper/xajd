/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:41:37 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:41:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwJgService extends SuperServiceImpl<ZyfwJgForm,ZyfwJgDao>{

	/** 
	 * @����:�жϽ������ָ��ʱ������Ƿ����ظ���¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����8:24:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwJgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwJgForm zyfwJgForm) {
		
		return dao.isRepeat(zyfwJgForm);
	}

	/** 
	 * @����:־Ը�������༭�ı���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��11�� ����8:30:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwJgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean zyfwSqSaveForEdit(ZyfwJgForm zyfwJgForm) {
		// TODO �Զ����ɷ������
		return false;
	}

	/** 
	 * @����:����ѧ�Ų�ѯ����־Ը��������Ϣ�б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��12�� ����11:59:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZyfwJgListByXh(String xh) {
		
		return dao.getZyfwJgListByXh(xh);
	}
	
	/**
	 * @����:��д����ѯһ��������ϸ��Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����11:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public ZyfwJgForm getModel(String fwid) throws Exception{
		return dao.getModel(fwid);
	}

}
