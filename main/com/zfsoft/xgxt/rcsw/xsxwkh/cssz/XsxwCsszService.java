/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-05-07����04:29:30 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ����Ϊ���˲�������
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����02:38:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XsxwCsszService extends SuperServiceImpl<XsxwCsszForm, XsxwCsszDao> {
	
	public XsxwCsszForm getModel() throws Exception{
		return dao.getModel();
	}

	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	


}
