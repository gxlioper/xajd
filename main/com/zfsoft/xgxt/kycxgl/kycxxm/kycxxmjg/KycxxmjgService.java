package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class KycxxmjgService extends SuperServiceImpl<KycxxmjgForm, KycxxmjgDao> {

	private KycxxmjgDao dao = new KycxxmjgDao();
	
	public KycxxmjgService() {
		super.setDao(dao);
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(KycxxmjgForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * 学生列表
	 */
	public List<HashMap<String, String>> getXsxxList(KycxxmjgForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	/**
	 * 根据科研创新项目获取学生列表
	 */
	public List<HashMap<String, String>> getKycxxmcyList(String jgid, User user) throws Exception {
		return dao.getKycxxmcyList(jgid, user);
	}
	/**
	 * 增加科研创新项目结果
	 */
	public boolean insertKycxxmjg(KycxxmjgForm model) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			result = dao.insertKycxxmcy(model.getJgid(),model.getXhArr());
		}
		return result;
	}
	/**
	 * 修改科研创新项目结果
	 */
	public boolean updateKycxxmjg(KycxxmjgForm model) throws Exception {
		boolean result = true;
		if("0".equals(model.getSjly())){
			result = super.runUpdate(model);
		}
		if(result){
			result = dao.insertKycxxmcy(model.getJgid(),model.getXhArr());
		}
		return result;
	}
	/**
	 * 删除科研创新项目结果
	 */
	public boolean delKycxxmcy(String[] jgidArr) throws Exception {
		return dao.delKycxxmcy(jgidArr);
	}
	/**
	 * 科研项目查询
	 */
	public List<HashMap<String, String>> kycxxmjgManageCx(KycxxmjgForm t, User user) throws Exception {
		return dao.kycxxmjgManageCx(t, user);
	}
	/**
	 * 科研项目查询（不分页）
	 */
	public List<HashMap<String, String>> getAllListCx(KycxxmjgForm t, User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.kycxxmjgManageCx(t, user);
	}
}
