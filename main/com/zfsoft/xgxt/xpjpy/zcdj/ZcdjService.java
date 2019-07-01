/**
 * @部门:学工产品事业部
 * @日期：2013-9-25 下午02:36:17 
 */  
package com.zfsoft.xgxt.xpjpy.zcdj;

import java.util.HashMap;
import java.util.List;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 徐州工业个性化 增加综测等级条件 
 * @作者： xucy[工号:754]
 * @时间： 2013-9-25 下午02:36:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcdjService {
	
	private ZcdjDao dao = new ZcdjDao();
	
	/**
	 * 
	 * @描述:TODO获取综测等级条件值列表
	 * @作者：xucy[工号：754]
	 * @日期：2013-9-25 下午05:02:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcdjList() {
		List<HashMap<String, String>> list = dao.getZcdjList();
		return list;
	}
	
	//获取所有的年级
	public List<HashMap<String, String>> getViewNjList(){
	    return dao.getViewNjList();
	}
	
}
