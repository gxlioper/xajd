/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:13:42 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-院设奖学金
 * @类功能描述: TODO(院设奖学金) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:13:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YsjxjService extends SuperServiceImpl<YsjxjForm,YsjxjDao>{
	
	/**
	 * @描述:TODO(获取学期名称)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 下午03:11:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/**
	 * @描述:TODO(获取资金来源名称)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 下午03:11:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zjly
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZjlymc(String zjly){
		return dao.getZjlymc(zjly);
	}
	
	/**
	 * @描述:TODO(获取资金来源列表)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 上午10:43:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjlyList() {
		return dao.getZjlyList();
	}
	
	/**
	 * @描述:TODO(判断唯一性)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-29 下午03:13:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String xn,String xq,String xmmc){
		return dao.isHaveRecord(xh,xn,xq,xmmc);
	}
	/**
	 * @描述:TODO(修改判断唯一性)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-8-1 下午03:06:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @param juid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCheck(String xh,String xn,String xq,String xmmc,String juid){
		return dao.updateCheck(xh,xn,xq,xmmc,juid);
	}
}
