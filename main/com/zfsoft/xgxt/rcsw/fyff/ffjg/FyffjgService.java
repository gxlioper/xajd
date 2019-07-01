/**
 * @部门:学工产品事业部
 * @日期：2014-4-10 上午10:18:47 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——费用发放——发放结果  管理模块
 * @类功能描述: 发放结果维护
 * @作者： cq [工号:785]
 * @时间： 2014-4-10 上午10:18:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffjgService extends SuperServiceImpl<FyffjgForm, FyffjgDao> {
	
	
	
	private FyffjgDao dao = new FyffjgDao();
	
	public FyffjgService() {
		super.setDao(dao);
	}
	
	
	
	/**
	 * 
	 * @描述:判断是否已存在结果库当中（主键：学号，发放时间，发放项目，发放条件）
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:05:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByFfjg(FyffjgForm form) 
			throws Exception {
		
		
		if("save".equalsIgnoreCase(form.getType())){
			//新增判断
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		}else {
			//修改判断
			String num = dao.checkExistForUpdate(form);
			return Integer.valueOf(num) > 0;
		}
		
	}
	
	
	/**
	 * 
	 * @描述:费用发结果单个查询
	 * @作者：cq [工号：785]
	 * @日期：2014-4-10 下午02:10:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneFyffjgList(String guid) {
		
		return dao.getOneFyffjgList(guid);
	}
	
	/**
	 * 
	 * @描述:根据发放项目代码获取发放方式
	 * @作者：cq [工号：785]
	 * @日期：2014-5-29 下午08:07:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffxmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFffs(String ffxmdm){
		
		return dao.getFffs(ffxmdm);
	}
	
}
