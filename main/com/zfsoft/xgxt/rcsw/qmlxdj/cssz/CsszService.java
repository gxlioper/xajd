/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-10 ����04:49:40 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszForm;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-10 ����04:49:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	public CsszForm getModel() throws Exception{
		return dao.getModel();
	}
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:54:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
}
