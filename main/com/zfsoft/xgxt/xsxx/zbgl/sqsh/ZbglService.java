/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.zbgl.zbjg.ZbjgDao;
import com.zfsoft.xgxt.xsxx.zbgl.zbjg.ZbjgModel;

/**
 * 
 * @�๦������: �������� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-3-18 ����01:58:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ZbglService extends SuperAuditService<ZbglModel, ZbglDao> {

	
	private static final String SQSH = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(ZbglModel model) {
		
		
		try {
			dao.runUpdate(model);
			
			ZbjgModel zbjgModel = new ZbjgModel();
			BeanUtils.copyProperties(zbjgModel, model);
			zbjgModel.setSjly(SQSH);
			
			return new ZbjgDao().runInsert(zbjgModel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(ZbglModel model) {
		try {
			return new ZbjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(ZbglModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

}
