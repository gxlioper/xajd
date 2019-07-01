package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @类功能描述:基础数据维护（年级学院专业班级）
 * @作者： Qilm[工号:964]
 * @时间： 2013-12-5 下午03:31:54 
 * @版本： V1.0
 */
public class JcsjService extends SuperServiceImpl<JcsjForm, JcsjDAO> {
	
	private JcsjDAO dao = new JcsjDAO();
	
	public JcsjService() {
		super.setDao(dao);
		
	}
//
//	/** 
//	 * @描述: 查询取得所有人数
//	 * @作者：Qilm[工号：964]
//	 * @日期：2013-12-6 上午09:02:53
//	 * @param myForm
//	 * @param user
//	 * @return
//	 * int 返回类型 
//	 * @throws 
//	 */
//	public int getCounts(JcsjForm myForm, User user) throws Exception {
//		return dao.getCounts(myForm, user);
//	}
//	
//	/**
//	 * 
//	 * @描述: 更新毕业年月
//	 * @作者：Qilm[工号：964]
//	 * @日期：2013-12-6 上午10:30:31
//	 * @param model
//	 * @param user
//	 * @return
//	 * @throws Exception
//	 * boolean 返回类型 
//	 * @throws
//	 */
//	public boolean runUpdate(JcsjForm model, User user) throws Exception{
//		return dao.runUpdate(model, user);
//	}
//
//	/** 
//	 * @描述: 取消毕业处理
//	 * @作者：Qilm[工号：964]
//	 * @日期：2013-12-6 下午01:36:02
//	 * @param myForm
//	 * @param user
//	 * @return
//	 * boolean 返回类型 
//	 * @throws 
//	 */
//	public boolean runDelete(JcsjForm model, User user) throws Exception{
//		return dao.runDelete(model, user);
//	}

	/** 
	 * @描述: 增加基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-6 下午04:32:43
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public String saveJcsj(JcsjForm myForm) throws Exception{
		
		// 判断是否已存在		
		if(!dao.ishasExist(myForm)){
			boolean bolFlg = dao.saveJcsj(myForm);
			if(bolFlg){
				new Thread(new Base.initialBj()).start();
			}
			return bolFlg ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}else{
			return MessageKey.SYS_SAVE_DM_REPEAT;
		}
	}
	

	
	/**
	 * 
	 * @描述: 获取专业列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午09:12:26
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyList(String xydm) {
		return dao.getZyList(xydm);
	}

	/** 
	 * @描述: 更新基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午11:38:23
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public String updJcsj(JcsjForm myForm) throws Exception{

		// 判断是否已存在		
		if(!dao.ishasExist(myForm)){
			boolean bolFlg = dao.updJcsj(myForm);
			if(bolFlg){
				new Thread(new Base.initialBj()).start();
			}
			return bolFlg ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}else{
			return MessageKey.SYS_SAVE_DM_REPEAT;
		}
	}

	/** 
	 * @描述: 删除基础数据
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 下午03:29:43
	 * @param xzflg
	 * @param split
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int runDelete(String xzflg, String[] split) throws Exception{
		int count =  dao.runDelete(xzflg, split);
		if(count > 0){
			new Thread(new Base.initialBj()).start();
		}
		return count;
	}

	/** 
	 * @描述: 部门类别列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-19 下午05:21:31
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmlbList() {
		return dao.getBmlbList();
	}

	/** 
	 * @描述: 获取学院列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-25 下午02:30:33
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(JcsjForm model, User user) {

		return dao.getAllList(model, user);
	}
	
	/**
	 * @描述: 获取学院列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-9 上午09:08:01
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}

	/**
	 * @throws Exception  
	 * @描述: 基础数据导出
	 * @作者：Qilm[工号：964]
	 * @日期：2014-1-3 下午03:58:16
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllListGet(JcsjForm model, User user) throws Exception {
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);
		return dao.getPageList(model, user);
		
	}
}
