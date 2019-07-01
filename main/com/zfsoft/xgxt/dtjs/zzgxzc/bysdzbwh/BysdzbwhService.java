/**
 * @部门:学工产品事业部
 * @日期：2017年2月4日 下午2:30:27 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出管理模块
 * @类功能描述: 毕业生党支部代码维护Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月4日 下午2:30:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysdzbwhService extends SuperServiceImpl<BysdzbwhForm,BysdzbwhDao>{

	/** 
	 * @描述:判断党支部代码或名称是否已经存在
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:41:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bysdzbwhForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(BysdzbwhForm bysdzbwhForm) {
		boolean result = false;
		String type = bysdzbwhForm.getType();
		if("save".equalsIgnoreCase(type)){
			result = dao.isExistForAdd(bysdzbwhForm);
		}else{
			result = dao.isExistForUpdate(bysdzbwhForm);
		}
		return result;
	}

	/** 
	 * @描述:判断党支部代码是否已经被使用
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月7日 上午10:14:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public HashMap<String,Object> isUsed(String [] ids) {
		List<HashMap<String,String>> dzbUsedList = dao.getUsedList(ids);
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("isUsed", false);
		if(dzbUsedList.size()>0){
			result.put("isUsed", true);
			result.put("dzbmc", dzbUsedList.get(0).get("dzbmc"));
		}
		return result;
	}

}
