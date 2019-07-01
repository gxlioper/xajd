/**
 * @部门:学工产品事业部
 * @日期：2016-11-7 下午03:13:46 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-7 下午03:13:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JesxService extends SuperServiceImpl<JesxForm, JesxDao> {
	/**
	 * 
	 * @描述:获取单条记录内容
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-7 下午05:43:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJesxMap(String xlccdm){
		return dao.getJesxMap(xlccdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-7 下午05:51:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRs(String[] xlccdms,String[] jesxs) throws Exception{
		return dao.saveRs(xlccdms, jesxs);
	}
	
	/**
	 * 
	 * @描述: 金额上限List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-8 上午09:51:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xlccdms
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJesxList(String[] xlccdms){
		return dao.getJesxList(xlccdms);
	}

}
