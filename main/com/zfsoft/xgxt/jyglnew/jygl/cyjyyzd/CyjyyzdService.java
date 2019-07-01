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
	 * 所属行业列表
	 */
	public List<HashMap<String, String>> getSshyList(){
		return dao.getSshyList();
	}
	/**
	 * 增加就业创业教育与指导
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
	 * 修改就业创业教育与指导
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
	 * 是否已存在
	 */
	public boolean checkExistSave(CyjyyzdForm model, User user) {
		return dao.checkExistSave(model, user);
	}
}
