/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��12��27�� ����11:38:27 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-������Ź���ģ��
 * @�๦������: ѧ������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2016��12��27�� ����11:38:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzkffService extends SuperServiceImpl<ZzkffForm, ZzkffDao>{
	
	private ZzkffDao zzkffDao = new ZzkffDao();
	
	public ZzkffService(){
		super.setDao(zzkffDao);
	}

	/**
	 * @����:�жϸ�ѧ����ͬѧ��ѧ���Ƿ���ͬ��Ŀ����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��3�� ����4:33:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zzkffForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean IsXmmcRepeat(ZzkffForm zzkffForm){
		return zzkffDao.IsXmmcRepeat(zzkffForm);
	}
	
	/**
	 * @����:����getModel
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��28�� ����5:59:05
	 * @throws Exception 
	 */
	public ZzkffForm getModel(String id) throws Exception{
		return zzkffDao.getModel(id);
	}
}
