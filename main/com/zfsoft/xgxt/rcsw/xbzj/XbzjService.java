/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����05:43:29 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: (ѧ��ϵ��֧�̹���--Ϋ��ѧԺ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����05:43:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XbzjService extends SuperServiceImpl<XbzjForm, XbzjDAO> {
	private XbzjDAO dao=new XbzjDAO();
	
	public XbzjService(){
		super.setDao(dao);
	}

	/** 
	 * @����:(�жϸ�ѧ��ѧ�ڸ�ѧ���Ƿ���������֧��ѧ��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����06:48:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(XbzjForm model) {
		return dao.isExist(model);
	}

}
