/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����07:36:54 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;

import xgxt.comm.CommService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����07:36:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxCsSzService extends CommService{
	BysXxCsSzDao dao = new BysXxCsSzDao();
	
	public boolean csSz(BysXxCsSzForm model) throws Exception {
		boolean result=true;
		result=dao.csSzDel(model);
		result= dao.csSzAdd(model);
		return result;
	}
	
	/**
	 * ��ȡ������������
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> getCssz() throws Exception{
		return dao.getCssz();
	}
	
	/**
	 * ��ȡ����������
	 * @param model
	 * @return 
	 */
	public HashMap<String, String> splCx() throws Exception{
		return dao.splCx();
	}
	

}
