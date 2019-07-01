package com.zfsoft.xgxt.jskp.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @描述： 获取审批流程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplc(String lx){
		return dao.getSplc(lx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:47:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cssz
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(CsszForm cssz) throws Exception{
		return dao.saveData(cssz);
	}
	
	/**
	 * @描述: 取参数设置表中的是否审核
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-20 下午08:04:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSfsh() throws Exception{
		return dao.getSfsh();
	}
}
