/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:43:34 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:43:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmCsszService extends SuperServiceImpl<XnwxdkjmCsszModel, XnwxdkjmCsszDao> {
	XnwxdkjmCsszDao dao = new XnwxdkjmCsszDao();
	public XnwxdkjmCsszModel getModel() throws Exception{
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
