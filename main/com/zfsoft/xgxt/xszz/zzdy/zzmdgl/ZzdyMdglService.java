/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzmdgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xszz.zzdy.jcsz.ZzdyJcszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:40:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyMdglService extends SuperServiceImpl<ZzdyMdglForm, ZzdyMdglDao> {
	private ZzdyMdglDao dao = new ZzdyMdglDao();
	
	/**
	 * 
	 * @描述:资助名单同步
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 下午05:26:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean zzmdTb(String xn,String xq) throws Exception {
		return dao.zzmdTb(xn, xq);
	}
	/**
	 * 
	 * @描述:资助名单修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 下午05:55:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editZzmd(ZzdyMdglForm model) throws Exception {
		model.setFfzt(model.getBghzt());
		model.setYffje(model.getBghje());
			dao.runUpdate(model);
			//记录更新日志
			return dao.insertBgLog(model);
	}
	
	public List<HashMap<String, String>> getBgjlList(ZzdyMdglForm model)
	throws Exception {
		return dao.getBgjlList(model);
	}


}
