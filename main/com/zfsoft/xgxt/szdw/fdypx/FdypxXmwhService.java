/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-5 ����02:38:29 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:����Ա��ѵ��Ŀά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-5 ����02:35:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class FdypxXmwhService extends SuperServiceImpl<FdypxXmwhForm,FdypxXmwhDAO>{

	private FdypxXmwhDAO dao = new FdypxXmwhDAO();


	public FdypxXmwhService() {
		// TODO �Զ����ɷ������
		super.setDao(dao);
	}
}
