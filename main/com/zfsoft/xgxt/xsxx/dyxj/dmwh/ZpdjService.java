/**
 * @部门:学工产品事业部
 * @日期：2015-6-18 上午11:12:35 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 自评等级维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-18 上午11:12:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpdjService extends SuperServiceImpl<ZpdjModel, ZpdjDao>{

	/**按等级名称查询**/
	public String getCountByDjmc(String djmc){
		return dao.getCountByDjmc(djmc);
	}
}
