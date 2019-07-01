/**
 * @部门:学工产品事业部
 * @日期：2018-3-6 下午05:04:48 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-3-6 下午05:04:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwpgService extends SuperServiceImpl<ZwpgForm, ZwpgDao>{
	private ZwpgDao dao = new ZwpgDao();
	public ZwpgService(){
		super.setDao(dao);
	}
	/** 
	 * @描述:根据序号取自我评估最新的数据
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-19 上午10:15:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * ZwpgForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByXh(String xh) {
		return dao.getInfoByXh(xh);
	}
	public HashMap<String, String> getInfoById(String id) {
		return dao.getInfoById(id);
	}
	
	
	
}
