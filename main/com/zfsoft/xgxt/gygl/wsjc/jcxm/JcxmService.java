/**
 * @部门:学工产品事业部
 * @日期：2015-5-28 下午05:10:42 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 检查项目
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-5-28 下午05:10:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcxmService extends SuperServiceImpl<JcxmModel, JcxmDao> {

	
	/**按日程id查询检查项目列表**/
	public List<HashMap<String, String>> getRcxmList(String rcid,String jcdx)
		throws Exception {
		return dao.getRcxmList(rcid,jcdx);
	}
	
	/** 
	 * @描述:是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-3 下午06:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(JcxmModel model){
		return Integer.valueOf(dao.getCountJl(model))>0;
	}
}
