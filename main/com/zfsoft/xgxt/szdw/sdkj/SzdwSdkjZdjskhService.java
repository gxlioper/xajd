/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����01:33:26 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cmj 
 * @ʱ�䣺 2013-5-16 ����01:33:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwSdkjZdjskhService extends SuperServiceImpl<ZdlskhForm, SzdwSdkjZdjskhDAO> {
	
	private SzdwSdkjZdjskhDAO dao = new SzdwSdkjZdjskhDAO();
	
	public SzdwSdkjZdjskhService(){
		super.setDao(dao);
	}
	
	public List<HashMap<String,String>> getPageListxx(ZdlskhForm t) throws Exception {
		return dao.getPageListxx(t);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-23 ����10:03:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getndList() {
		// TODO �Զ����ɷ������
		return dao.getndList();
	}
	

}
