/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-5 ����09:31:43 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ǰ���� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-5 ����09:31:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TqhkService extends SuperAuditService<TqhkModel, TqhkDao> {

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(TqhkModel model) {
		
		HkjgModel hkjgModel = new HkjgModel();

		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(hkjgModel, model);
			hkjgModel.setHkzt(model.getZd2());
			return new HkjgDao().runInsert(hkjgModel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(TqhkModel model) {
		try {
			return new HkjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public List<HashMap<String, String>> getAudingList(TqhkModel t, User user)
		throws Exception {
		return dao.getAudingList(t, user);
	}

}
