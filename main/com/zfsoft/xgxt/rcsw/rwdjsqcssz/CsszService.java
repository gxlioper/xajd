/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-4 ����09:34:22 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsqcssz;

import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-4 ����09:34:22 
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
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
}
