/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����06:12:27 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.ArrayList;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�����������) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����06:12:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdjService extends SuperServiceImpl<CxdjForm, CxdjDao> {
private CxdjDao dao = new CxdjDao();
	
	public CxdjService() {
		super.setDao(dao);
	}

	/** 
	 * @����:TODO(���еȼ������Ƿ����)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-30 ����06:47:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(CxdjForm model) {
		// TODO �Զ����ɷ������
		return dao.isExist(model);
	}
	
	//�㽭���θ��Ի��ж�
	public boolean isNowUsing(String[] pj){
		return dao.isNowUsing(pj);
	}
	
	public boolean checkSameNameIsNotExists(String cxdjdm,String cxdjmc){
		return dao.checkSameNameIsNotExists(cxdjdm, cxdjmc);
	}
}
