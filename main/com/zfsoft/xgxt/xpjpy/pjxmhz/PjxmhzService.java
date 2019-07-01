/**
 * @部门:学工产品事业部
 * @日期：2017-2-22 下午05:09:55 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-统计管理-评奖项目汇总
 * @类功能描述: 统计每学年、每学期、每个项目的获奖人数
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-2-22 下午05:09:55 
 * @版本： Ver 5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzService extends SuperServiceImpl<PjxmhzForm, PjxmhzDao>{
	
	private PjxmhzDao dao = new PjxmhzDao();
	
	public PjxmhzService(){
		super.setDao(dao);
	}
	
	//条件查询资助项目汇总信息
	public List<HashMap<String, String>> viewRs(PjxmhzForm model,User user,Boolean fyFlag) throws Exception{
		return dao.viewRs(model, user,fyFlag);
	}
}
