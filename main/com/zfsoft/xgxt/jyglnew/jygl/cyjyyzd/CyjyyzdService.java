package com.zfsoft.xgxt.jyglnew.jygl.cyjyyzd;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class CyjyyzdService extends SuperServiceImpl<CyjyyzdForm, CyjyyzdDao> {

	private CyjyyzdDao dao = new CyjyyzdDao();
	
	public CyjyyzdService() {
		super.setDao(dao);
	}
	/**
	 * ������ҵ�б�
	 */
	public List<HashMap<String, String>> getSshyList(){
		return dao.getSshyList();
	}
	/**
	 * ���Ӿ�ҵ��ҵ������ָ��
	 */
	public boolean insertCyjyyzd(CyjyyzdForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			XsxxService xsxxService = new XsxxService();
			XsxxForm xsxxForm = new XsxxForm();
			xsxxForm.setXh(model.getXh());
			xsxxForm.setSjhm(model.getSjhm());
			xsxxForm.setQqhm(model.getQqhm());
			result = xsxxService.runUpdate(xsxxForm);
		}
		return result;
	}
	/**
	 * �޸ľ�ҵ��ҵ������ָ��
	 */
	public boolean updateCyjyyzd(CyjyyzdForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		if(result){
			XsxxService xsxxService = new XsxxService();
			XsxxForm xsxxForm = new XsxxForm();
			xsxxForm.setXh(model.getXh());
			xsxxForm.setSjhm(model.getSjhm());
			xsxxForm.setQqhm(model.getQqhm());
			result = xsxxService.runUpdate(xsxxForm);
		}
		return result;
	}
	public HashMap<String, String> getModelMap(CyjyyzdForm t) throws Exception {
		return dao.getModelMap(t);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(CyjyyzdForm model, User user) {
		return dao.checkExistSave(model, user);
	}
}
