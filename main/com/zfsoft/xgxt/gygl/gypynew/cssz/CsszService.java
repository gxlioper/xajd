package com.zfsoft.xgxt.gygl.gypynew.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @描述:获取参数设置表里的审批流程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-24 下午04:34:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplc(){
		return dao.getSplc();
	}
}
