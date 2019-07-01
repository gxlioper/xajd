package com.zfsoft.xgxt.jyglnew.jygl.cyyqgl;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class CyyqglService extends SuperServiceImpl<CyyqglForm, CyyqglDao> {

	private CyyqglDao dao = new CyyqglDao();
	
	public CyyqglService() {
		super.setDao(dao);
	}
	/**
	 * 所属行业列表
	 */
	public List<HashMap<String, String>> getSshyList(){
		return dao.getSshyList();
	}
	/**
	 * 学生列表
	 */
	public List<HashMap<String, String>> getXsxxList(CyyqglForm model, User user) throws Exception {
		return dao.getXsxxList(model, user);
	}
	/**
	 * 团队成员
	 */
	public List<HashMap<String, String>> getTdmxList(CyyqglForm model, User user) throws Exception {
		return dao.getTdmxList(model, user);
	}
	/**
	 * 增加创业园区管理
	 */
	public boolean insertCyyqgl(CyyqglForm model, User user) throws Exception {
		if("0".equals(model.getSfzc())){
			model.setZcgsmc(" ");
			model.setZcsj(" ");
			model.setZczb(" ");
			model.setJyrs(" ");
			model.setZcsshy(" ");
		}
		boolean result = super.runInsert(model);
		if(result){
			result = dao.saveTdmx(model);
		}
		return result;
	}
	/**
	 * 修改创业园区管理
	 */
	public boolean updateCyyqgl(CyyqglForm model, User user) throws Exception {
		if("0".equals(model.getSfzc())){
			model.setZcgsmc(" ");
			model.setZcsj(" ");
			model.setZczb(" ");
			model.setJyrs(" ");
			model.setZcsshy(" ");
		}
		boolean result = super.runUpdate(model);
		if(result){
			result = dao.saveTdmx(model);
		}
		return result;
	}
	/**
	 * 删除创业园区管理
	 */
	public int delCyyqgl(String[] arr) throws Exception {
		int num = dao.runDelete(arr);
		dao.delTdmx(arr);
		return num;
	}
	public HashMap<String, String> getModelMap(CyyqglForm t) throws Exception {
		return dao.getModelMap(t);
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(CyyqglForm model, User user) {
		return dao.checkExistSave(model, user);
	}
}
