/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-19 ����03:45:29 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�������ݲ�ѯ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-6-19 ����03:45:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CwsjService extends SuperServiceImpl<CwsjForm, CwsjDao> {
	private CwsjDao dao=new CwsjDao();
	public CwsjService(){
		super.setDao(dao);
	}
	public List<String[]> getStuCwsjList(String xh){
		return dao.getStuCwsjList(xh);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwsjList(String xh) {
		return dao.getCwsjList(xh);
	}

}
