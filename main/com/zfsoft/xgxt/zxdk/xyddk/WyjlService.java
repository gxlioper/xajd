/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:57:56 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约记录
 * @作者： 黄辰霁
 * @时间： 2015-11-26 上午9:41:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyjlService extends SuperServiceImpl<WyjlModel, WyjlDao> {

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-26 下午01:16:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWyztList() {
		WyjlDao dao = new WyjlDao();
		return dao.getWyztList();
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-26 下午01:58:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCountByXh(WyjlModel model){
			
		return dao.getCountByXh(model);
	}
	
	/**
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-26 下午01:58:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFkList(WyjlModel model) throws Exception{
			
		return dao.getFkList(model);
	}
	
	/**
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-30  上午08:58:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDkxxList(WyjlModel model) throws Exception{
			
		return dao.getDkxxList(model);
	}

}