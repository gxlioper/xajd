/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-23 ����11:51:32 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_�༶�±�����ģ��
 * @�๦������: TODO(������ҽҩ_�༶�±�_��������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-23 ����11:51:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybcsszService extends
		SuperServiceImpl<BjxqybcsszForm, BjxqybcsszDao> {
	
	private BjxqybcsszDao dao = new BjxqybcsszDao();
	
	@SuppressWarnings("deprecation")
	public BjxqybcsszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-��ѯ����������Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-23 ����03:57:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * BjxqybcsszForm �������� 
	 * @throws
	 */
	public BjxqybcsszForm getModel() throws Exception {		   
		return dao.getModel();
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-�������������Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-23 ����05:07:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBjxqybcssz(BjxqybcsszForm t) throws Exception{		
		boolean flag = false;
		flag = dao.deleteBjxqybcssz(t);
		if(flag){
			flag = dao.runInsert(t);
		}		
		return flag;
	}
}
