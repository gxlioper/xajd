/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:33:25 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.cssz;

import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:33:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkCsszService extends SuperServiceImpl<XnwxdkCsszModel, XnwxdkCsszDao> {
	public XnwxdkCsszModel getModel() throws Exception{
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
		return dao.getSqKg();
	}

}
