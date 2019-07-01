/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.bcjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 技术等级鉴定情况
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:48:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BcjgService extends SuperServiceImpl<BcjgModel, BcjgDao> {

	/**查询拨付明细 **/
	public List<HashMap<String,String>> getBfxxList(String xh){
		return dao.getBfxxList(xh);
	}
	
    /**
     * 
     * @描述:验证是否已有数据
     * @作者：ChenQ[工号：856]
     * @日期：2015-9-7 上午09:01:47
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param djjdForm
     * @return
     * String 返回类型 
     * @throws
     */
	public String getCountByXhAndXn(BcjgModel model) {
		return dao.getCountByXhAndXn(model);
	}
	
	/**
	 * 
	 * @描述:基层就业补偿结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-4 下午05:23:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcjyBcjglist(String xh){
		return dao.getJcjyBcjglist(xh);
	} 
}
