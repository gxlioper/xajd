/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:12:03 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ThjlService extends SuperServiceImpl<ThjlForm, ThjlDao> {
	
	private ThjlDao dao = new ThjlDao();
	
	public ThjlService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:条件查询谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {
		
		return dao.getThjlListById(id);
	}
	/**
	 * 
	 * @描述:条件查询谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {
		
		return dao.getThjlListByXh(xh);
	}
	/**
	 * 保存至谈话记录信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(ThjlForm model)
			throws Exception {
		
		return dao.saveThjlInfo(model);
	}
	
	/**
	 * 
	 * @描述:删除谈话记录信息
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		
		return dao.delThjlById(id);
	}
	
	/**
	 * 
	 * @描述:根据ID修改谈话记录信息
	 * @作者：1004
	 * @日期：2013-9-11 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	
	public boolean updateThjlInfo(ThjlForm model) throws Exception{
		
		return dao.updateThjlInfo(model);
	}

	/** 
	 * @描述:通过职工号取得教师信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午10:50:17
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}

	/** 
	 * @描述:取得教师列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午11:49:46
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(ThjlForm myForm) {
		return dao.getJsInfoList(myForm);
	}
}
