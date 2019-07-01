/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����11:19:22 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;

/** 
 * @�๦������: �ҽ��ʸ� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����11:19:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjzgService extends SuperAuditService<JjzgForm, JjzgDao> {

	private static final String default_rddj = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(JjzgForm model) {
		
		model.setRddj(default_rddj);
		
		try {
			return dao.runUpdate(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(JjzgForm model) {
		return false;
	}

	
	/**
	 * 
	 * @����:��ѯ�ҽ���ʦ��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����12:42:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String xh) throws Exception{
		return dao.getModelMap(xh);
		
	}
	
	/**
	 * 
	 * @����:��ȡ�ҽ̾����б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-9-2 ����03:01:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getJJjlList(String xh) throws Exception{
		return dao.getJJjlList(xh);
	}
	
	
}
