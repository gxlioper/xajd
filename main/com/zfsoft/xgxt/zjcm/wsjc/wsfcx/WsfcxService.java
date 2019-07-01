/**
 * @部门:学工产品事业部
 * @日期：2016-3-10 上午09:30:11 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsfcx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-10 上午09:30:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfcxService extends SuperServiceImpl<WsfcxForm, WsfcxDao>{
	private WsfcxDao dao = new WsfcxDao();
	
	/** 
	 * @描述:撤销提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-10 上午09:31:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param f
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public String cxtj(WsfcxForm f) throws Exception{
		String ids[] = f.getIds();
		List<String> failXms = new ArrayList<String>();
		for(int i = 0;i<ids.length;i++){
			boolean result = dao.cxtj(ids[i]);
			if (!result) {
				failXms.add(ids[i]);
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-10 上午11:03:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param f
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateWsfcx(WsfcxForm f) throws Exception{
		return dao.updateWsfcx(f);
	}
	
	/** 
	 * @描述:获取楼栋号，寝室号，获取层号
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-10 上午11:03:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param f
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCh(WsfcxForm f){
		return dao.getCh(f);
	}
	
	/** 
	 * @描述:卫生分查询信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-10 上午11:30:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param f
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getWsfcx(WsfcxForm f){
		return dao.getWsfcx(f);
	}
}
