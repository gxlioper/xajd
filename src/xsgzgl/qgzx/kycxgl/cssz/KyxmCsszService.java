/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:29:30 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺2015-11-30 ����04:29:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KyxmCsszService extends SuperServiceImpl<KyxmCsszForm, KyxmCsszDao> {
	

	
	public boolean deleteJcsz(KyxmCsszForm model) throws Exception{
		return dao.deleteJcsz(model);
	}
	
	public HashMap<String,String> getCssz(String xmlb) throws Exception{
		return dao.getCssz(xmlb);
	}

}
