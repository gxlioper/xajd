package com.zfsoft.xgxt.jyglnew.jygl.byqx;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class ByqxService extends SuperServiceImpl<ByqxForm, ByqxDao> {

	private ByqxDao dao = new ByqxDao();
	
	public ByqxService() {
		super.setDao(dao);
	}
	/**
	 * 就业单位性质列表
	 */
	public List<HashMap<String, String>> getJydwxzList(){
		return dao.getJydwxzList();
	}
	/**
	 * 就业形式列表
	 */
	public List<HashMap<String, String>> getJyxsList(){
		return dao.getJyxsList();
	}
	/**
	 * 增加毕业去向
	 */
	public boolean insertByqx(ByqxForm model, User user) throws Exception {
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
	 * 修改毕业去向
	 */
	public boolean updateByqx(ByqxForm model, User user) throws Exception {
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
	public HashMap<String, String> getModelMap(ByqxForm t) throws Exception {
		return dao.getModelMap(t);
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(ByqxForm model, User user) {
		return dao.checkExistSave(model, user);
	}
	
	/**
	 * @description	： 毕业去向
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午06:00:23
	 * @return
	 */
	public List<HashMap<String,String>> getByqxList(){
		return dao.getByqxList();
	}
	
	/**
	 * @description	：就业类别
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午06:02:02
	 * @return
	 */
	public List<HashMap<String,String>> getJylbList(){
		return dao.getJylbList();
	}
	
	/**
	 * @description	： 就业状况
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午06:03:18
	 * @return
	 */
	public List<HashMap<String,String>> getJyzkList(){
		return dao.getJylbList();
	}
}
