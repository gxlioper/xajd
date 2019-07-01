package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险管理
 */
public class YlbxglService extends SuperServiceImpl<YlbxglForm, YlbxglDao> {
	
	private YlbxglDao dao = new YlbxglDao();
	public static String _BCZSCID="-1";
	
	public YlbxglService() {
		super.setDao(dao);
	}
	
	/**
	 * 唯一性判断
	 */
	public boolean isExist(YlbxglForm model) throws Exception {
		return dao.isExist(model);
	}
	
	/**
	 * 唯一性判断【根据查询结果进行批量增加专用】
	 */
	public boolean isExistPl(YlbxglForm model, List<HashMap<String,String>> resultList) throws Exception {
		return dao.isExistPl(model, resultList);
	}
	
	/**
	 * 保存
	 */
	public boolean insertYlbxgl(YlbxglForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 修改
	 */
	public boolean updateYlbxgl(YlbxglForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 查看
	 */
	public Map<String, String> viewOneYlbxglList(String xh, String jgid) throws Exception {
		return dao.viewOneYlbxglList(xh, jgid);
	}
	
	/**
	 * 【修改页面专用】排除当前记录，查询应交年数
	 */
	public String viewYjnum(String xh, String jgid) {
		return dao.viewYjnum(xh, jgid);
	}
	
	/**
	 * 【查看页面专用】排除当前记录，查询历史保险
	 */
	public List<HashMap<String, String>> viewLsList(String xh) {
		return dao.viewLsList(xh);
	}
	public List<HashMap<String, String>> viewOneList(String xh,String jgid) {
		return dao.viewOneList(xh,jgid);
	}

}
