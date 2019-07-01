/**
 * @部门:学工产品事业部
 * @日期：2015-9-18 下午04:40:36 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-9-18 下午04:40:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HdkhglService extends SuperServiceImpl<hdkhForm,HdkhglDao> {
	//获取活动考核评分list
	public List<HashMap<String, String>> getKhcjList(String xmdm) throws Exception{
		return dao.getKhcjList(xmdm);
	}
	
	//查询
	public List<HashMap<String, String>> getKhglList(hdkhForm t, User user)
	throws Exception {
		// TODO 自动生成方法存根
		return dao.getKhglList(t, user);
     }

}
