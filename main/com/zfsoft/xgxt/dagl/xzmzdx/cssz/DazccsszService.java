/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:37:04 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ģ��
 * @�๦������: ����ת����������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:37:31 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazccsszService extends SuperServiceImpl<DazccsszForm,DazccsszDao>{
	
	private DazccsszDao dao = new DazccsszDao();
	public DazccsszService(){
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public DazccsszForm getModel(DazccsszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * ��ѯ����������Ϣ(�޲���)
	 */
	public DazccsszForm getModel()throws Exception{
		return getModel(new DazccsszForm());
	}
	
	/**
	 * �������ñ���
	 */
	public boolean dazccsszSave(DazccsszForm model) throws Exception{
		boolean flag = false;
		flag = dao.deleteTableName();
		if(flag){
			flag = dao.runInsert(model);
		}
		return flag;
	}
}
