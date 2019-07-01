/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.djjd.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.djjd.jdqk.JdqkDao;
import com.zfsoft.xgxt.xsxx.djjd.jdqk.JdqkModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ȼ�����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-12 ����09:35:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DjjdService extends SuperAuditService<DjjdModel, DjjdDao> {

	private static final String SQSH = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DjjdModel model) {
		
		JdqkModel jdqk = new JdqkModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(jdqk, model);
			jdqk.setSjly(SQSH);
			return new JdqkDao().runInsert(jdqk);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DjjdModel model) {
		try {
			return new JdqkDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(DjjdModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

}
