/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-23 ����10:56:13 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-23 ����10:56:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> implements Constants{
	/**
	 * 
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����11:53:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * CsszForm �������� 
	 * @throws
	 */
	public CsszForm getModel() throws Exception{
		CsszForm model = dao.getModel();
		if (model == null){
			model = new CsszForm();
			model.setZckg(CLOSE);
		}
		return model;
	}
	/**
	 * 
	 * @����:����ע�Ὺ��״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����01:44:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsszParam(){
		return dao.getCsszParam();
	}

}
