/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����04:46:34 
 */  
package com.zfsoft.xgxt.jjgl.xsxqsq;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-28 ����04:46:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxqsqService extends SuperServiceImpl<XsxqsqForm, XsxqsqDao> {
	/**
	 * 
	 * @����:��������id��ѧ�Ż�ȡModel
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����08:05:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * XsxqsqForm �������� 
	 * @throws
	 */
	public XsxqsqForm getModelByXqidAndXh(String xqid , String xh) throws Exception{
		return dao.getModelByXqidAndXh(xqid, xh);
	}
}
