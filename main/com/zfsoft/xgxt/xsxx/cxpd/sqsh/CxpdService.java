/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:35:25 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyDao;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������-������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2015-1-14 ����03:55:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CxpdService extends SuperAuditService<CxpdModel, CxpdDao> {

	private static final String SQSH = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(CxpdModel model) {
		
		CxpyForm cxpyModel = new CxpyForm();
		
		cxpyModel.setXh(model.getXh());
		cxpyModel.setXn(model.getXn());
		cxpyModel.setXq(model.getXq());
		cxpyModel.setCxdj(model.getCxdj());
		cxpyModel.setCxpy(model.getCxpy());
		cxpyModel.setBzr(model.getBzr());
		if("13943".equalsIgnoreCase(Base.xxdm)) {
			cxpyModel.setSqsj(model.getSqsj());
		}
		cxpyModel.setSjly(SQSH);
		
		try {
			
			dao.runUpdate(model);
			
			CxpyDao cxpyDao = new CxpyDao();
			String count = cxpyDao.getCount(cxpyModel);
			
			if (Integer.valueOf(count) == 0){
				cxpyModel.setPk(model.getId());
				return cxpyDao.runInsert(cxpyModel);
			} else {
				return cxpyDao.updCxpy(cxpyModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(CxpdModel model) {
		try {
			return new CxpyDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(CxpdModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

	
	public String getCount(CxpdModel t){
		return dao.getCount(t);
	}
	
}
