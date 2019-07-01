/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbxx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 设备分类维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:48:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SbxxService extends SuperServiceImpl<SbxxModel, SbxxDao> {

	/**按设备编号查询已经存在的数量*/
	public int getSbslByBh(String sbbh){
		return dao.getSbslByBh(sbbh);
	}
}
