package com.zfsoft.xgxt.rcsw.zxzx.cjwtsz;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.zxzx.zxhf.ZxhfForm;
import com.zfsoft.xgxt.rcsw.zxzx.zxhf.ZxhfService;

public class CjwtszService extends SuperServiceImpl<CjwtszForm, CjwtszDao> {

	private CjwtszDao dao = new CjwtszDao();
	
	public CjwtszService() {
		super.setDao(dao);
	}
	/**
	 * ���ӳ�������
	 */
	public boolean insertCjwtsz(CjwtszForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			// ����ظ�
			ZxhfService zxhfService = new ZxhfService();
			ZxhfForm zxhfForm = new ZxhfForm();
			zxhfForm.setZxid(model.getZxid());
			zxhfForm.setHfnr(model.getHfnr());
			zxhfForm.setHfr(user.getUserName());
			zxhfForm.setHfsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			zxhfService.insertZxhf(zxhfForm);
		}
		return result;
	}
	/**
	 * �޸ĳ�������
	 */
	public boolean updateCjwtsz(CjwtszForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		if(result){
			// ����ظ�
			ZxhfService zxhfService = new ZxhfService();
			ZxhfForm zxhfForm = new ZxhfForm();
			zxhfForm.setHfid(model.getHfid());
			zxhfForm.setHfnr(model.getHfnr());
			zxhfForm.setHfr(user.getUserName());
			zxhfForm.setHfsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			zxhfService.updateZxhf(zxhfForm);
		}
		return result;
	}
	/**
	 * ɾ����������
	 */
	public boolean deleteCjwtsz(String values) throws Exception {
		return dao.deleteCjwtsz(values);
	}
}
