/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.zbjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 技术等级鉴定情况
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:48:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbjgService extends SuperServiceImpl<ZbjgModel, ZbjgDao> {

	
	/**
	 * 
	 * @描述: 导出数据列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-20 下午04:23:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportList(ZbjgModel t, User user) throws Exception{
		
		return dao.getExportList(t, user);
	}
}
