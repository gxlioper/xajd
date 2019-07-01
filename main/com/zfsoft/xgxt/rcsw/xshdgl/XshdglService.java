/**
 * @部门:学工产品事业部
 * @日期：2016-6-8 下午02:38:32 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-8 下午02:38:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XshdglService extends SuperServiceImpl<XshdglForm, XshdglDao> {
	/**
	 * 
	 * @描述:获取部门代码list
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-12 上午10:08:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBmdmList(){
		return dao.getBmdmList();
	}
	
	/**
	 * 
	 * @描述:获取学生活动记录map,用于查看功能
	 * @作者：张昌路[工号：982]
	 * @日期：2016-6-12 上午10:09:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXshdMap(String sqid){
        return dao.getXshdMap(sqid);
	}
	
	/**
	 * 
	 * @描述:是否存在同一主办单位同一时间申请两个或两个以上的活动名称，如果是，返回false,如果否，返回true
	 * @作者：张昌路[工号：982]
	 * @日期：2016-6-12 上午10:15:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsExistNotSame(XshdglForm t){
		return dao.checkIsExistNotSame(t);
	}
}
