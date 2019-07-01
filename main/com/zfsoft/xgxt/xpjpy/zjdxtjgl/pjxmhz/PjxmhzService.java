/**
 * @部门:学工产品事业部
 * @日期：2018-1-5 上午11:21:58 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优
 * @类功能描述:  浙江大学新评奖评优-统计查询-评奖项目汇总
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-1-5 上午10:26:12 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzService extends SuperServiceImpl<PjxmhzForm, PjxmhzDao>{
	
	private PjxmhzDao dao = new PjxmhzDao();
	
	public PjxmhzService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2018-1-9 上午10:10:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm model,User user,Boolean fyFlag) throws Exception{
		return dao.viewRs(model, user,fyFlag);
	}
}
