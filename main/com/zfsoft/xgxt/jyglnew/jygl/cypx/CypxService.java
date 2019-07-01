package com.zfsoft.xgxt.jyglnew.jygl.cypx;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class CypxService extends SuperServiceImpl<CypxForm, CypxDao> {

	private CypxDao dao = new CypxDao();
	
	public CypxService() {
		super.setDao(dao);
	}
	/**
	 * ��ѵ�����б�
	 */
	public List<HashMap<String, String>> getPxlxList(){
		return dao.getPxlxList();
	}
	/**
	 * ��ҵ��ʽ�б�
	 */
	public List<HashMap<String, String>> getJyxsList(){
		return dao.getJyxsList();
	}
	/**
	 * ���Ӵ�ҵ��ѵ
	 */
	public boolean insertCypx(CypxForm model, User user) throws Exception {
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
	 * �޸Ĵ�ҵ��ѵ
	 */
	public boolean updateCypx(CypxForm model, User user) throws Exception {
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
	public HashMap<String, String> getModelMap(CypxForm t) throws Exception {
		return dao.getModelMap(t);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(CypxForm model, User user) {
		return dao.checkExistSave(model, user);
	}
}
